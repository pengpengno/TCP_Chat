package qq_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import resource.Chat_Info;
import resource.Client_Info;
import resource.Communication_Action;
import resource.Heart_Beat;
import resource.User_Info;

/**
 * @author 怦怦
 *
 */
public class Client_DataTran implements Runnable{
	public User_Info us_receive=null;
	public User_Info us_send=null;
	public Chat_Info ci_send=null;
	public Chat_Info ci_receive=null;
	public Object ob_receive=null;

	public Vector<User_Info> ve_us_receive=null;	
	public Vector<User_Info> ve_us_send=null;
	public Vector<Chat_Info> ve_ci_receive=null;
	public Vector<Chat_Info> ve_ci_send=null;

	private ObjectOutputStream oos=null;
	private ObjectInputStream ois=null;
	private Socket s=null;
	private final static String IP="150.158.113.254";
	private final static int PORT=10001;

	/*
	 * 初始化线程属性
	 * Client_DataTran  分 两个构造方法     用于初始化 待传输数据，可传输用户类数据（User_Info），或者聊天信息类数据（Chat_Info）
	 * 
	 */
	
	

	public Client_DataTran(User_Info us){
		try {
			s=new Socket(IP,PORT);
			System.out.println("用户类传输初始化向服务器连接完毕");
			us_send=us;
			
	
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			 System.out.println("网络故障，请检查网络后重试");
		}	
	}

	public Client_DataTran(Chat_Info ci){
		try {
			s=new Socket(IP,PORT);
			System.out.println("通讯类传输初始化向服务器连接完毕");
			ci_send=ci;	
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			 System.out.println("网络故障，请检查网络后重试");
		}	
	}

	
	//传送用户类数据包
	public void User_DataTran_send() {		
			try {
				oos=new ObjectOutputStream(this.s.getOutputStream());
				oos.writeObject(this.us_send);
				System.out.println("用户类数据包已发送");
				DataTran_receive();
				User_Info_TypeJudge(ob_receive);
				oos.close();
				ois.close();
				s.close();
				
			} catch (IOException e ) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}					
	}
	
	//用户聊天发送包
	public void Chat_DataTran_send() {	
			
				try {
					oos=new ObjectOutputStream(this.s.getOutputStream());
					oos.writeObject(ci_send);
				
					System.out.println("聊天通讯包已发送");	
					DataTran_receive();
					Chat_Info_TypeJudge(ob_receive);
					oos.close();
					ois.close();
					s.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					System.out.println("网络故障！，发送失败");
					}
			
		
	}
	//用户聊天接收包
	public void DataTran_receive() {
					
				try {		
					ois=new ObjectInputStream(this.s.getInputStream());
					this.ob_receive=ois.readObject();
					System.out.println("数据包已接收，准备分析");
					
					
				} catch (IOException | ClassNotFoundException e) {
					// TODO 自动生成的 catch 块
					System.out.println("网络故障！，接收失败");

				}
			
		}
	/*
	 * 
	 * 用户信息类  筛选
	 */
	public void User_Info_TypeJudge(Object ob) {
		if(ob instanceof User_Info) {
			this.us_receive=(User_Info) ob;
		}

		else if (ob instanceof Vector) {
			this.ve_us_receive=(Vector<User_Info>) ob;
			System.out.println("用户好友关系表已接收");
			
			
			
		}	
	}
	/*
	 * 
	 *  通讯信息类筛选
	 */
	public void Chat_Info_TypeJudge(Object ob) {		
		if(ob instanceof Chat_Info) {
			this.ci_receive=(Chat_Info) ob;

		}
		else if (ob instanceof Vector) {
			this.ve_ci_receive=(Vector<Chat_Info>) ob;
			
		}	
	}
	/*
	 * 
	 * 实现TCP的长链接
	 * 保持 客户端的活跃状态  ，便于接收来自服务器的主动通讯
	 */
	public void Chat_Keep() {
		try {
			oos=new ObjectOutputStream(this.s.getOutputStream());
			oos.writeObject(this.ci_send);
			System.out.println(ci_send.sender+"接收功能正在开启");
			
			ois=new ObjectInputStream(this.s.getInputStream());
			this.ob_receive=ois.readObject();
			Chat_Info_TypeJudge(ob_receive);
		} catch (IOException | ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
			}
	
	@Override
	//用户指令类型判断
	public void run() {
		
		if(us_send!=null) {
			User_DataTran_send();
		}
		else if (ci_send.command==Communication_Action.keep_receive) {			
			Chat_Keep();		
		}
		else {
			 Chat_DataTran_send();
		}
			
	}
	
}

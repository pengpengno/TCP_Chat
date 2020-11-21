package qq_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import resource.Chat_Info;
import resource.Communication_Action;
import resource.Heart_Beat;
import resource.User_Info;

public class Sever_DataTran_Thread implements Runnable{
	public Socket s=null;
	final static private int port=5006; 
	public ServerSocket Sevs=null;	
	Sql_Ms sql_ms=new Sql_Ms();
	public Map<String ,Socket> Online_User_Chat=null;
	Sever_DataTran_Thread(){
		try {
			Sevs = new ServerSocket(port);
			System.out.println("��������ʼ���ɹ�");
			Online_User_Chat=new HashMap<String ,Socket>();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} 
	
	}	
	public void run() {
		while(true) {
			try {
				System.out.println("�ȴ��ͻ�������...");
				s=Sevs.accept();
				System.out.println("�ѽ��տͻ���");
				new Thread(new Client_Thread(s)).start();
				System.out.println("�ͻ��˴����߳̿���...");
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	class Client_Thread implements Runnable{
		public Socket s=null;
		public ObjectInputStream ois=null;
		public ObjectOutputStream oos=null;
		public Object ob=null;
		public User_Info us_receive=null;
		public User_Info us_send=null;
		public Chat_Info ci_send=null;
		public Chat_Info ci_receive=null;
		public Object ob_receive=null;
		public Object ob_send=null;
		public Vector<User_Info> ve_receive=null;
		public Vector<User_Info> ve_send=null;

		public Vector ob_ve_send=null;
		public Client_Thread(Socket s) {			
			this.s=s;
		}
	
		@Override
		public void run() {								
			this.receiveobject();
				//�ͻ��������ж�

		}
		public void receiveobject() {
			try {				
					ois=new ObjectInputStream(s.getInputStream());									
					this.ob_receive=ois.readObject();
					this.Action_Judge(this.ob_receive);
				
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
				System.out.println("���ӶϿ�");
				}
			
			

		}
		public void SendObject() {
			try {
				oos=new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(this.ob_send);				
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
				
			}			
		}
		
		
		//�������ݰ�����ʼ����Ӧ���ݰ�����

		public void Action_Judge(Object ob) {			
			if(ob instanceof User_Info) {
				this.us_receive=(User_Info) ob;
				this.Sever_UserTran(this.us_receive);
			}	
			else if(ob instanceof Chat_Info) {
				this.ci_receive=(Chat_Info) ob;

				this.Sever_ChatTran(this.ci_receive);
			}
			else if(ob instanceof Vector){
				this.ve_receive=(Vector<User_Info>)ob;
			}					
		}
	
		public void Sever_UserTran(User_Info us) {
			switch(us.Command){
			case Communication_Action.user_login:
				this.ob_send=sql_ms.user_login(us);
				System.out.println("login");		
				this.SendObject();	
				
				break;
			case Communication_Action.user_del:
				sql_ms.user_del(us);
				break;
			case Communication_Action.user_register:
				sql_ms.user_login(us);
				break;
			case Communication_Action.user_revamp:
				sql_ms.user_login(us);
				break;
			case Communication_Action.gain_frineds:
				ob_send=sql_ms.gain_friends_relation(us);
				this.SendObject();
				break;			
				
			}
		}
		public void Sever_ChatTran(Chat_Info chat) {
			switch(chat.command){
			case Communication_Action.user_gainchat_history:
				this.ob_send=sql_ms.gain_chat_history(chat);
				this.SendObject();	
				break;
			case Communication_Action.user_chat:
				System.out.println("�ѽ���  �û�ͨѶָ��  ���ڲ���");
				sql_ms.User_chat(chat);
				try {
					if(Sever_DataTran_Thread.this.Online_User_Chat.containsKey(chat.receiver+chat.sender)) {
						new ObjectOutputStream(Sever_DataTran_Thread.
								this.Online_User_Chat.get(chat.receiver+chat.sender).
								getOutputStream()).writeObject(chat);
						System.out.println(chat.sender+"�Ѿ�������Ϣ");
						Sever_DataTran_Thread.this.Online_User_Chat.remove(chat.receiver+chat.sender);
					}
					
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				break;
			case Communication_Action.keep_receive:
				Sever_DataTran_Thread.this.Online_User_Chat.put(chat.sender+chat.receiver, this.s);
				System.out.println(chat.sender+"�Ѿ�����map");
				break;
			}
					
		}		
	}
}

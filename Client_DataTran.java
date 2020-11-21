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
 * @author ����
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
	 * ��ʼ���߳�����
	 * Client_DataTran  �� �������췽��     ���ڳ�ʼ�� ���������ݣ��ɴ����û������ݣ�User_Info��������������Ϣ�����ݣ�Chat_Info��
	 * 
	 */
	
	

	public Client_DataTran(User_Info us){
		try {
			s=new Socket(IP,PORT);
			System.out.println("�û��ഫ���ʼ����������������");
			us_send=us;
			
	
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			 System.out.println("������ϣ��������������");
		}	
	}

	public Client_DataTran(Chat_Info ci){
		try {
			s=new Socket(IP,PORT);
			System.out.println("ͨѶ�ഫ���ʼ����������������");
			ci_send=ci;	
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			 System.out.println("������ϣ��������������");
		}	
	}

	
	//�����û������ݰ�
	public void User_DataTran_send() {		
			try {
				oos=new ObjectOutputStream(this.s.getOutputStream());
				oos.writeObject(this.us_send);
				System.out.println("�û������ݰ��ѷ���");
				DataTran_receive();
				User_Info_TypeJudge(ob_receive);
				oos.close();
				ois.close();
				s.close();
				
			} catch (IOException e ) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}					
	}
	
	//�û����췢�Ͱ�
	public void Chat_DataTran_send() {	
			
				try {
					oos=new ObjectOutputStream(this.s.getOutputStream());
					oos.writeObject(ci_send);
				
					System.out.println("����ͨѶ���ѷ���");	
					DataTran_receive();
					Chat_Info_TypeJudge(ob_receive);
					oos.close();
					ois.close();
					s.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					System.out.println("������ϣ�������ʧ��");
					}
			
		
	}
	//�û�������հ�
	public void DataTran_receive() {
					
				try {		
					ois=new ObjectInputStream(this.s.getInputStream());
					this.ob_receive=ois.readObject();
					System.out.println("���ݰ��ѽ��գ�׼������");
					
					
				} catch (IOException | ClassNotFoundException e) {
					// TODO �Զ����ɵ� catch ��
					System.out.println("������ϣ�������ʧ��");

				}
			
		}
	/*
	 * 
	 * �û���Ϣ��  ɸѡ
	 */
	public void User_Info_TypeJudge(Object ob) {
		if(ob instanceof User_Info) {
			this.us_receive=(User_Info) ob;
		}

		else if (ob instanceof Vector) {
			this.ve_us_receive=(Vector<User_Info>) ob;
			System.out.println("�û����ѹ�ϵ���ѽ���");
			
			
			
		}	
	}
	/*
	 * 
	 *  ͨѶ��Ϣ��ɸѡ
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
	 * ʵ��TCP�ĳ�����
	 * ���� �ͻ��˵Ļ�Ծ״̬  �����ڽ������Է�����������ͨѶ
	 */
	public void Chat_Keep() {
		try {
			oos=new ObjectOutputStream(this.s.getOutputStream());
			oos.writeObject(this.ci_send);
			System.out.println(ci_send.sender+"���չ������ڿ���");
			
			ois=new ObjectInputStream(this.s.getInputStream());
			this.ob_receive=ois.readObject();
			Chat_Info_TypeJudge(ob_receive);
		} catch (IOException | ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
			}
	
	@Override
	//�û�ָ�������ж�
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

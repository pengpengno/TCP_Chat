package resource;

import java.util.Vector;
/*
 * 
 * ��Ҫ������ʼ���ͻ�����Ϣ
 *   Ψһ��; ��װ �������������û���Ϣ�ͺ�����Ϣ��  
 */
public class Client_Info {
	public User_Info us=null;
	public User_Info friend=null;
	public Vector<User_Info>  user_fri=null;
	public Client_Info(User_Info us,Vector<User_Info> user_fri){
		this.us=us;
		this.user_fri=user_fri;
	}
	public Client_Info(User_Info user,User_Info friend) {
		this.us=user;
		this.friend=friend;
		
	}

}

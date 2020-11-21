package resource;

import java.util.Vector;
/*
 * 
 * 主要用来初始化客户端信息
 *   唯一用途 封装 服务器传来的用户信息和好友信息，  
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

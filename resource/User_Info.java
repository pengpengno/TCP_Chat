package resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;
//用户个人信息的修改与保存
public class User_Info implements Serializable{
	private static final long serialVersionUID = 1L;
	//user  ip属性
	public Socket s=null;
	public String ip=null;
	public String hostname=null;
	//user 账号信息
	public String ID,NickName,Psd,Sex,Icon;	
	public String Command;

	public String state;
	public String signature;

	
	//好友系统
	public String friend_comment;
	public String friend_group;
	public Vector<User_Info> friends_sys=new Vector<User_Info>();
	//获取用户登录主机信息
	public User_Info(Socket s) {
		this.s=s;
		this.ip=s.getInetAddress().getHostAddress();
		this.hostname=s.getInetAddress().getHostName();		
			//this.br=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	//实现长连接构造
	public User_Info(String command) {
		this.Command=command;
	}
	
	//用户登录构造
	public User_Info(String id,String psd,String command) {
		this.ID=id;
		this.Psd=psd;		
		this.Command=command;
		
	}
	//用户好友列表
	public User_Info(Vector<User_Info> friends) {
		this.friends_sys=friends;
	}
	
	
	//用户数据包构造
	public User_Info(String ID,String Psd,String NickName,String Sex,String Icon,String state,String signature) {
		super();
		this.ID = ID;
		this.NickName = NickName;
		this.Psd = Psd;
		this.Sex = Sex;
		this.Icon=Icon;
		this.state=state;
		this.signature=signature;
	
	}
	/*
	 * 好友构造
	 * 目前没有用
	 */
	
	public User_Info(String ID,String comment,String group,String command) {
		this.ID=ID;
		this.Command=command;
		this.friend_comment=comment;
		this.friend_group=group;
		
	}
	public User_Info(String id,String nickname,String sex,String icon,String signature,String state) {
		this.ID=id;
		this.NickName=nickname;
		this.Sex=sex;
		this.Icon=icon;
		this.signature=signature;
		this.state=state;		
	}
	public User_Info User_Revamp(User_Info us) {
		this.Psd=us.Psd;
		
		return this;
	}
	public User_Info(String ID, String Psd) {
	super();
	this.ID = ID;
	this.Psd = Psd;
}
	/*public User_Info(String ID) {
		this.ID=ID;
	}*/
	
	
	//重写hashCode和equals
	@Override
	public int hashCode() {
		return this.ID.hashCode();	
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final User_Info other = (User_Info) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
    @Override
    public String toString() {
    return "User [id=" + this.ID + ", name=" + this.NickName + ","
    		+ " password=" + this.Psd+  ", sex=" + this.Sex+ ", Icon=" + this.Icon+
    		 ", State=" + this.state+ ", ip=" + this.ip+ ", socket=" + this.s+"]";
    }
}


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
//�û�������Ϣ���޸��뱣��
public class User_Info implements Serializable{
	private static final long serialVersionUID = 1L;
	//user  ip����
	public Socket s=null;
	public String ip=null;
	public String hostname=null;
	//user �˺���Ϣ
	public String ID,NickName,Psd,Sex,Icon;	
	public String Command;

	public String state;
	public String signature;

	
	//����ϵͳ
	public String friend_comment;
	public String friend_group;
	public Vector<User_Info> friends_sys=new Vector<User_Info>();
	//��ȡ�û���¼������Ϣ
	public User_Info(Socket s) {
		this.s=s;
		this.ip=s.getInetAddress().getHostAddress();
		this.hostname=s.getInetAddress().getHostName();		
			//this.br=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	//ʵ�ֳ����ӹ���
	public User_Info(String command) {
		this.Command=command;
	}
	
	//�û���¼����
	public User_Info(String id,String psd,String command) {
		this.ID=id;
		this.Psd=psd;		
		this.Command=command;
		
	}
	//�û������б�
	public User_Info(Vector<User_Info> friends) {
		this.friends_sys=friends;
	}
	
	
	//�û����ݰ�����
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
	 * ���ѹ���
	 * Ŀǰû����
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
	
	
	//��дhashCode��equals
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


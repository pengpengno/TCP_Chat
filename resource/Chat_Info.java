package resource;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Chat_Info implements Serializable{
	private static final long serialVersion=1L;
	public String sender;
	public String receiver;
	public String message;
	public String datatime;
	public String command;
	public Chat_Info(String sender,String receiver,String command){
		this.sender=sender;
		this.receiver=receiver;
		this.command=command;

	}
	Chat_Info(){
		
	}
	public void gettime() {
		this.datatime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	public Chat_Info(String senderID,String receiverID,String datatime,String message ,String command){
		this.sender=senderID;
		this.receiver=receiverID;
		this.message=message;
		this.datatime=datatime;		
		this.command=command;
		this.datatime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

	}
	public Chat_Info(String senderID,String receiverID,String message,String command){
		this.sender=senderID;
		this.receiver=receiverID;
		this.message=message;
		this.command=command;
		this.datatime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	//÷ÿ–¥hashCode∫Õequals
	@Override
	public int hashCode() {
		return this.message.hashCode();	
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Chat_Info other = (Chat_Info) obj;
		if ( message!= other.message)
			return false;
		return true;
	}

	public String toString(){
		return this.sender+" "+this.message;
		
	}
	public static void main(String args[]) {
		Chat_Info ci=new Chat_Info();
		ci.gettime();
		System.out.println(ci.datatime);
		Scanner  sc= new Scanner(System.in);
		String ii=sc.next();
	}
}

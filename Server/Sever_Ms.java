package qq_server;

import resource.User_Info;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Vector;

public class Sever_Ms implements Runnable{
	public ServerSocket Sevs=null;
	public PreparedStatement pst=null;
	public ResultSet rs=null;
	public DB_CON db=null;
	public Sql_Ms sql_ms=new Sql_Ms();
	final static private int port=5006; 

	Vector<User_Info> client_socket=new Vector<User_Info>();
	HashSet<User_Info> client_hashset =new HashSet<User_Info>();
	public  int online_clientnums=0;
	public Sever_Ms(){
		try {
			Sevs = new ServerSocket(port);
			System.out.println("服务器端口打开成功");
		} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}		
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根		
	}
	//账号生成器
	public User_Info ID_Produce() {		
		Random random = new Random();
		String result="";
		for (int i=0;i<6;i++)
		{
		    result+=random.nextInt(10);
		}
		User_Info us=new User_Info(result);
		return us;
	}

}

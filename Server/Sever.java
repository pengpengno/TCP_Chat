package qq_server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

import resource.Communication_Action;
import resource.User_Info;

public class Sever extends JFrame implements Communication_Action{
	ServerSocket Sevs=null;
	final static private int port=5006; 
	public static int clientnums=0;
	Vector<User_Info> client_socket=new Vector<User_Info>();
	 
	Sever(){	
		new Thread(new Sever_DataTran_Thread()).start();;
		this.setBounds(400,400,900,600);
		DB_CON db=new DB_CON();
		Connection conn=db.getcon();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton close =new JButton("¹Ø±Õ·þÎñÆ÷");
		close.setBounds(0,550,600,50);
		this.add(close); 
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	public static void main(String agrs[]) {
		new Sever();	
	}
}

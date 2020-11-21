package qq_client;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.event.*;

import resource.*;
import qq_server.DB_CON;
public class Login extends JFrame implements Login_UI{
	//客户端UI变量
	public static JLabel id,psd,regsiter,forgetpsd,image_user;
	public static JButton login;
	public static JCheckBox rem_psd,auto_login;
	public static JTextField user_id;
	public static JPasswordField user_psd;
	public static JPanel jp_userlogin;
	private static boolean issuccess=false;
	private final static int login_width=630;
	private final static int login_height=460;
	//客户端通讯变量

	public Client_FunctionCenter client_fc=null;

	Login(){
		super("chat");
		
		this.setBounds(400, 400, login_width, login_height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBackground(Color.white);
				
		Font labelf1=new Font("宋体",Font.PLAIN,23);
		Font labelf2=new Font("宋体",Font.ITALIC,17);
		
		jp_userlogin=new JPanel();
		jp_userlogin.setBounds(0,0,login_width,login_height);
		jp_userlogin.setLayout(null);
		jp_userlogin.setBackground(Color.white);
		
		
		id=new JLabel(sid);
		id.setFont(labelf1);
		id.setBounds(140,163,50,50);
		jp_userlogin.add(id);
		user_id=new JTextField(1);
		user_id.setText("1325624880");
		user_id.setBounds(210,170,270,40);
		jp_userlogin.add(user_id);
		
		psd=new JLabel(spsd);
		psd.setBounds(140, 223, 50, 50);
		psd.setFont(labelf1);
		jp_userlogin.add(psd);
		user_psd=new JPasswordField('*');
		user_psd.setText("wal123");
		user_psd.setBounds(210,230,270,40);
		jp_userlogin.add(user_psd);
		
		regsiter=new JLabel(sregsiter);
		regsiter.setFont(labelf2);
		regsiter.setBounds(10,400,90,30);
		jp_userlogin.add(regsiter);
		
		forgetpsd=new JLabel(sforgetpsd);
		forgetpsd.setBounds(430,300,90,20);
		forgetpsd.setForeground(new Color(128,128,128));
		jp_userlogin.add(forgetpsd);
		
		auto_login=new JCheckBox(sauto_login);
		auto_login.setBounds(140,300,90,20);
		auto_login.setForeground(new Color(128,128,128));
		auto_login.setBackground(Color.WHITE);
		jp_userlogin.add(auto_login);
		
		
		rem_psd=new JCheckBox(srem_psd);
		rem_psd.setForeground(new Color(128,128,128));
		rem_psd.setBounds(280,300,90,20);
		rem_psd.setBackground(Color.WHITE);
		jp_userlogin.add(rem_psd);
		
		
		login=new JButton(slogin);
		login.setBounds(140,340,345,50);
		login.setFont(new Font("微软雅黑",1,19));
		login.setForeground(Color.white);
		login.setBackground(new Color(0,191,255));
		login.setBorderPainted(false);
		
		jp_userlogin.add(login);
		
		//登录界面的头像缩放
		ImageIcon img=new ImageIcon("img/3.png");
		image_user=new JLabel();
		image_user.setBounds(245, 20, 140, 140);
		Image temp=img.getImage();
		temp=temp.getScaledInstance(140, 140, Image.SCALE_DEFAULT);
		img.setImage(temp);
		jp_userlogin.add(image_user);
		image_user.setIcon(img);
		image_user.setSize(140, 140);

		
		
		login.addMouseListener(new mouselistener());
		login.addActionListener(new mousemonitor());
		regsiter.addMouseListener(new mouselistener());
		
		this.add(jp_userlogin);
		this.setResizable(false);
		this.setVisible(true);	
		
	}

	class mousemonitor implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			
			if(e.getSource()==login) {
				//获取账号密码	
				client_fc=new Client_FunctionCenter();
				String ID=user_id.getText();
				String Psd=String.valueOf(user_psd.getPassword());					
				issuccess=client_fc.user_login(ID, Psd);				
			}	
			if(issuccess==true) {
				Login.this.setVisible(false);
			}
		}
	}

	class mouselistener implements  MouseListener{
 
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO 自动生成的方法存根		
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO 自动生成的方法存根
			if(e.getSource()==login) {
				Login.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			else if(e.getSource()==regsiter){
				Login.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource()==regsiter) {
				Login.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			if(e.getSource()==login) {
				Login.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			}		
		}
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Login login=new Login();
		
	}

}

package qq_client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import resource.*;

public class Client extends JFrame implements Client_UI{
	public JTextField seek;

	public Client_Info client_info=null;

	private final static int Client_HEIGHT=900;
	private final static int Client_WIDTH=350;
	private final static int Client_X=1000;
	private final static int Client_Y=100;
	private User_Info us=null;
	public Vector<friends_jpanel>  fri_ve=null;	
	public Client_DataTran client_dt=null;
	//用户、好友 基础面板
	private user_jpanel user_jp=null;
	private friends_jpanel friends_jpanel=null;
	Client(Client_Info client_info){	
		super("chat");
		this.setLayout(null);
		this.setResizable(false);
		this.setBounds(Client_X,Client_Y,Client_WIDTH,Client_HEIGHT);	

		this.client_info=client_info;
		
		friends_jpanel=new friends_jpanel(client_info.user_fri);
		user_jp=new user_jpanel(client_info.us);

		this.add(friends_jpanel);

		this.add(user_jp);		
		this.setVisible(true);		
	}
	class friends_jpanel  extends JPanel{
		final private static int friends_jpanel_width=350;
		final private static int friends_jpanel_height=800;
		final private static int friends_jpanel_x=0;
		final private static int friends_jpanel_y=200;
		private Vector<User_Info> friends_vector=null;
		private Vector<friend_jpanel> friendsJPanel=null;;

		friends_jpanel(Vector<User_Info>  us_ve){
	
			this.friends_vector=us_ve;
			friendsJPanel=new Vector<friend_jpanel>();

			//this.setLayout(friends_jpanel_layout);
			this.setBounds(friends_jpanel_x, friends_jpanel_y, friends_jpanel_width, friends_jpanel_height);					
			this.setLayout(null);

			for(int i =0;i<friends_vector.size();i++) {	
				System.out.println(i);
				friendsJPanel.add(new friend_jpanel(this.friends_vector.elementAt(i),i));
				this.add(friendsJPanel.elementAt(i));
			
			}
		
			this.setVisible(true);
		}
	
	}
	class user_jpanel extends JPanel{	
		private JLabel user_icon=null;
		private JLabel user_nickname= null;
		private JLabel user_state= null;
		private JLabel user_personal_signature=null;
		private User_Info us=null;
		private final static int user_jpanel_height=170;
		private final static int user_jpanel_width=350;
		private final static int user_jpanel_x=0;
		private final static int user_jpanel_y=0;
		
		user_jpanel(User_Info us){
			super();	
			this.us=us;
			this.setLayout(null);
			this.setBounds(user_jpanel_x,user_jpanel_y,user_jpanel_width,user_jpanel_height);
			
			user_icon=new JLabel();
			user_nickname=new JLabel();
			user_state=new JLabel();
			user_personal_signature=new JLabel();		

			
			ImageIcon user_img=new ImageIcon("img/"+us.Icon);
			System.out.println("用户头像加载完毕");
			Image temp=user_img.getImage();
			temp=temp.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
			user_img.setImage(temp);
			this.user_icon.setIcon(user_img);
			this.user_icon.setSize(90, 90);

			user_icon.setBounds(10, 20, 100, 100);
			
			
			user_nickname.setBounds(130,20,160,60);
			user_nickname.setText(us.NickName);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     

			
			user_state.setBounds(110,105,15,15);

			
			user_personal_signature.setBounds(130,90,200,30);
			user_personal_signature.setText(us.signature);

			this.add(user_icon);
			this.add(user_nickname);
			this.add(user_state);
			this.add(user_personal_signature);
			
			this.setVisible(true);
		}
		
		
	}
	
	//好友显示面板
	class friend_jpanel extends JPanel {
		private JLabel fri_icon=null;
		private JLabel fri_nickname=null;
		private JLabel fri_state=null;
		private JLabel fri_personal_signature=null;
		private User_Info  friend_info=null;
		
		private final static int friend_jpanel_width=350;
		private final static int friend_jpanel_height=100;
		private final static int friend_jpanel_vap=20;
		private final Color lightgrey=new Color(211,211,211);
		friend_jpanel(User_Info us,int i){
			super();
			this.setBounds(0,friend_jpanel_height*i+friend_jpanel_vap*i,friend_jpanel_width,friend_jpanel_height);
			this.setLayout(null);
			this.setBackground(lightgrey);
			fri_icon=new JLabel();
			fri_nickname=new JLabel();
			fri_state=new JLabel();
			fri_personal_signature=new JLabel();
			this.friend_info=us;
			
			ImageIcon fri_image=new ImageIcon("img/"+us.Icon);
			System.out.println("好友类头像加载完毕");
			fri_image.setImage(fri_image.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
			this.fri_icon.setIcon(fri_image);
			this.fri_icon.setSize(50, 50);
			this.fri_icon.setBounds(10, 6, 100, 100);
			

			fri_nickname.setBounds(130,6,160,60);
			fri_nickname.setText(us.NickName);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     

			
			fri_state.setBounds(110,105,15,15);
			
			
			fri_personal_signature.setText(us.signature);
			fri_personal_signature.setBounds(130,55,100,30);
			
			fri_icon.addMouseListener(new mousemonitor());
			this.add(fri_icon);
			this.add(fri_nickname);
			this.add(fri_state);
			this.add(fri_personal_signature);			
			this.setVisible(true);
		}		
		class mousemonitor implements MouseListener{
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				
				if(e.getSource()==fri_icon) {
					System.out.println(friend_jpanel.this.friend_info.Icon);
					new Chat_Frame(Client.this.client_info.us,friend_jpanel.this.friend_info);
				}
				
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
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
		}
	
	}
}

package qq_client;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import qq_server.*;
import resource.Chat_Info;
import resource.Communication_Action;
import resource.User_Info;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Chat_Frame extends JFrame{
	private JLabel my_nickname,fri_nickname,my_id,fri_id,fri_icon;


	private JButton send,close;
	private JTextArea Message_Send_Field;
	private JTextArea Message_Receive_Field;
	private JScrollPane Message_Receive__scrollPane;
	private JScrollPane Message_Send_scrollPane;

	
	private final static int Chat_Frame_width=800;
	private final static int Chat_Frame_height=600;
	
	public User_Info user=null;
	public User_Info friend=null;
	private Client_FunctionCenter client_fc=null;
	public Message_Receive_JPanel message_receive_JPanel=null;
	public Message_Send_JPanel message_send_jpanel=null;
	public Client_DataTran  keep_receive=null;
	public boolean Alive=true;
      Chat_Frame(User_Info user,User_Info friend){
    	  super();
    	  this.setTitle("");
    	  this.setSize(Chat_Frame_width, Chat_Frame_height);
    	  this.setLocationRelativeTo(null);/*  将窗体放置屏幕正中央*/
    	  this.setLayout(null);
    	  
    	  this.setResizable(false);
    	  Toolkit toolkit=Toolkit.getDefaultToolkit(); // 获取Toolkit对象
    	  Image icon = toolkit.getImage("img/"+friend.Icon); // 获取图片对象
    	  this.setIconImage(icon); // 设置图标
    	  this.setTitle(friend.NickName);
    	  this.user=user;
    	  this.friend=friend;
    	  message_receive_JPanel=new Message_Receive_JPanel();  	 
    	  
    	  message_send_jpanel=new Message_Send_JPanel();  	  
    	  this.add(message_receive_JPanel);
    	  this.add(message_send_jpanel);
    	  
    	 
    	  
    	  client_fc=new Client_FunctionCenter();
    	 
    	  new Thread(new keep_rece()).start();
    	  review_chat_history( client_fc.gain_message_history(new Chat_Info(user.ID,
					 friend.ID,Communication_Action.user_gainchat_history)),Message_Receive_Field);
    	  this.setVisible(true); 	  
      }
      class keep_rece implements Runnable{

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while(true) {
				keep_receive=new Client_DataTran(new Chat_Info(user.ID,friend.ID,Communication_Action.keep_receive));
				keep_receive.Chat_Keep();
				System.out.println(keep_receive.ci_receive.message);
				Chat_Frame.this.Message_Receive_Field.append(keep_receive.ci_receive.sender+'\t'+
						keep_receive.ci_receive.datatime+'\n'+
						keep_receive.ci_receive.message+'\n');
				Chat_Frame.this.Message_Receive_Field.paintImmediately(Chat_Frame.this.Message_Receive_Field.getBounds()); 
				Chat_Frame.this.Message_Receive_Field.setCaretPosition(Chat_Frame.this.Message_Receive_Field.getText().length());  
			}
				
			}
			
		}   	  
      
      //将接受的 信息显示 再文本框中
      public void review_chat_history(Vector<Chat_Info>  chat_infos,JTextArea review_text ) {
    	  for(int i = 0;i<chat_infos.size();i++) {
    		  review_text.append(chat_infos.elementAt(i).sender+'\t'+
    				  chat_infos.elementAt(i).datatime+'\n'
    				  +chat_infos.elementAt(i).message+'\n');
    		  Chat_Frame.this.Message_Receive_Field.paintImmediately(Chat_Frame.this.Message_Receive_Field.getBounds()); 
    		  Chat_Frame.this.Message_Receive_Field.setCaretPosition(Chat_Frame.this.Message_Receive_Field.getText().length());  
    	  }
    	  
      }
      //聊天信息接收面板UI
     class  Message_Receive_JPanel extends JPanel {
    	 Message_Receive_JPanel(){
    		 super();
	    	 Message_Receive_Field=new JTextArea(); 
	    	// Message_Receive_Field.setLineWrap(true);
	    	 Message_Receive_Field.setEnabled(false);	    	 
	    	 Message_Receive_Field.setFont(new Font("谐体",Font.BOLD|Font.ITALIC,12));
	    	// Message_Receive_Field.setBounds(5,5,600,350);
	    	 
	    	 Message_Receive__scrollPane = new JScrollPane(Message_Receive_Field);     	
	    	 Message_Receive__scrollPane.setBounds(5, 5, 780, 340);          
	    	 Message_Receive__scrollPane.setVisible(true);
	    	 fri_id=new JLabel (Chat_Frame.this.friend.ID);  
	
	    	 fri_nickname=new JLabel (Chat_Frame.this.friend.NickName); 

		    	
		    this.setBounds(0, 0, 800, 350);
		    this.add(Message_Receive__scrollPane);
		    this.add(fri_nickname);
		    this.add(fri_id);

		    this.setLayout(null);
		    this.setVisible(true);
		    
	    	}

      }
    	
     class  Message_Send_JPanel extends JPanel  {
    	 private static final int Jpanel_x=0;
    	 private static final int Jpanel_y=400;
    	 private static final int Jpanel_width=600;
    	 private static final int Jpanel_height=200;
    	 
    	 Message_Send_JPanel(){
    		 super();
    		
	    	  this.setBounds(Jpanel_x, Jpanel_y, Jpanel_width, Jpanel_height);
	    	  this.setLayout(null);
	    	  Message_Send_Field=new JTextArea(); 

	    	  Message_Send_scrollPane = new JScrollPane(Message_Send_Field);
	    	  
	    	  send=new JButton("发送");
	    	  send.setBounds(695,160,90,40);
	    	  close=new JButton("关闭");
	    	  close.setBounds(595,160,90,40);
	    	  
	    	  Message_Send_scrollPane.setBounds(5, 5, 780, 150);    
	    	  send.addActionListener(new mousemonitor());
	    	  close.addActionListener(new mousemonitor());
	    	  this.setBounds(0,360,800,200);
	    	  this.add(Message_Send_scrollPane);   
	    	  this.add(send);
	    	  this.add(close);
	    	  this.setVisible(true);
    	 }
    		class mousemonitor implements ActionListener{

    			@Override
    			public void actionPerformed(ActionEvent e) {
    				// TODO 自动生成的方法存根
    				//发送信息
    				if(e.getSource()==send) {
    					client_fc.chat_info_send(new Chat_Info(Chat_Frame.this.user.ID,Chat_Frame.this.friend.ID,
    							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),Message_Send_Field.getText(),
    							Communication_Action.user_chat));
    					Message_Receive_Field.append(Chat_Frame.this.user.ID+'\t'+
    							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+'\n'+
    							Message_Send_Field.getText()+'\n');
    					Message_Send_Field.setText("");

    				}
    				else if(e.getSource()==close) {
    					Alive=false;
    					Chat_Frame.this.dispose(); 					
    				}
    			}
    		}
      }
      
}

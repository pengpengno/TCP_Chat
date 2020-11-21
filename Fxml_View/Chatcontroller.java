package fxml_view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


import fxml_view.Clientcontroller.User_Pane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import qq_client.Client_DataTran;
import qq_client.Client_FunctionCenter;

import resource.*;

public class Chatcontroller {

    @FXML
    private Pane chat_pane;

    @FXML
    private ImageView friend_icon;

    @FXML
    private AnchorPane message_view_anchorpane;

    @FXML
    private Button close;
    @FXML
    private Button send;
    @FXML
    private Label friend_nickname;
    @FXML
    private  ScrollPane message_view_scrollpane;
    @FXML
    private Pane message_send_pane;
    @FXML
    private Pane friend_info_pane;
    @FXML
    private TextArea  message_send_textarea;
    private User_Info user=null;
    private User_Info friend=null;
    
    private Parent Chatroot;
    private Stage ChatStage;
    private Scene ChatScene;
    
    private Vector<Chat_Info>  message_history=null;
    private Client_FunctionCenter client_function=null;
    private Map<Chat_Info , message_pane>  message_map=null;
    private Client_DataTran keep_tcp =null;
    public void set_chat_info( User_Info user ,User_Info friend) {    	
    	this.user=user;
    	this.friend=friend;   
    	System.out.println(this.user.ID+"初始化完毕");
    }
    public void chat_init(){
    	try {
    		Chatroot = FXMLLoader.load(getClass().getResource("/fxml_view/Chat.fxml"));  
    		ChatStage = new Stage();
    		ChatScene =new Scene(Chatroot, 800, 600);
    		ChatStage.setScene(ChatScene);	
    		
    		this.controller_init();   		//初始化控件
    		
    		show_messagehistory();		//历史记录
    		ChatScene.getStylesheets().add(this.getClass().getResource("css/Chat.css").toExternalForm());  //导入CSS文件
    		receive_message();        //接收信息线程
    		
    		
    		ChatStage.initStyle(StageStyle.DECORATED);
    		ChatStage.setTitle("Chat");
    		//ChatStage.setAlwaysOnTop(true);
    		ChatStage.show();        
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 	
    }
    /*
     * setAlwaysOnTop
     * 弹出聊天窗口  主要在clientcontroller中调用，
     * 避免 同一好友窗体多次打开
     */
    public void setAlwaysOnTop() {
    	ChatStage.setAlwaysOnTop(true);
    	ChatStage.show();
    	ChatStage.setAlwaysOnTop(false);
    }
    /*
     * 
     * controler_init
     *lookup ()  对类内组件初始化  ，获取组件在fxml中已设定的属性
     * send_message()  发送聊天信息chat_info封装包
     * 
     */
    public void controller_init() {
    	message_map=new HashMap<Chat_Info , message_pane>();
    	friend_nickname=(Label) Chatroot.lookup("#friend_nickname");
		friend_nickname.setText(friend.NickName);    	
    	Image friend_image =new Image("file:E:/eclipse/chat/src/img/head"+friend.Icon,30,30,false,false);
		friend_icon=(ImageView) Chatroot.lookup("#friend_icon");

		message_view_anchorpane=(AnchorPane) Chatroot.lookup("#message_view_anchorpane");
		message_view_anchorpane.setBackground(new Background(new BackgroundFill(Color.valueOf("#FFFFFF"), null, null)));
		message_view_scrollpane=(ScrollPane) Chatroot.lookup("#message_view_scrollpane");  
		friend_info_pane=(AnchorPane) Chatroot.lookup("#friend_info_pane");
		friend_info_pane.setBackground(new Background(new BackgroundFill(Color.valueOf("#FFFFFF"), null, null)));
		
		send=(Button)Chatroot.lookup("#send"); 
		close=(Button)Chatroot.lookup("#close");
		
		send_message(user,friend);      //设置发送信息  按钮点击事件
		closewin(); //  按钮关闭窗口
		
		message_send_textarea=(TextArea)Chatroot.lookup("#message_send_textarea");
		
		message_view_scrollpane.setContent(message_view_anchorpane);	//绑定消息查看滚动面板
		message_view_scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		message_view_scrollpane.setHvalue(message_view_scrollpane.getHmax());   //设置滚动面板策略   时期显示最下方	
		System.out.println(friend.Icon);
		friend_icon.setImage(new Image("file:E:/eclipse/chat/src/img/head"+friend.Icon,60,60, false, false));
		send.getStyleClass().add("send");
		close.getStyleClass().add("close");
    }
    /*
     * 保持TCP长链接， 随时 接受服务器 发送来的信息
     */
    public void receive_message() {    	
    	new Thread(new keep_rece());    	
    }
    /*
     * keep_rece
     * 实现 TCP通讯的长链接，  打开窗体后不断接收服务器传来的chat通讯包
     * 
     */
    public void closewin() {
    	close.setOnMouseClicked(event ->{
    		if(event.getClickCount()==1) {
	    		try {
	        		this.ChatStage.close();
	        		}
	        	catch(Exception e){
     		
	        	}
    		}
    		
    	});   	
    }
    class keep_rece implements Runnable{
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while(true) {
				keep_tcp=new Client_DataTran(new Chat_Info(user.ID,friend.ID,Communication_Action.keep_receive));
				keep_tcp.Chat_Keep();
				System.out.println(keep_tcp.ci_receive.message);
				//刷新聊天记录面板
				
				message_map.put(keep_tcp.ci_receive, new message_pane(keep_tcp.ci_receive));
				message_map.get(keep_tcp.ci_receive).setLayoutX(0);
				message_map.get(keep_tcp.ci_receive).setLayoutY(20+60*message_map.size());
				message_view_anchorpane.getChildren().add(message_map.get(keep_tcp.ci_receive));
			}				
			}		
	} 
    
    public void send_message(User_Info user ,User_Info friend) {
    	send.setOnMouseClicked(event ->{
    		if(this.message_send_textarea.getText()=="") {
    			return;
    		}
			if(event.getClickCount()==1) {
				Client_FunctionCenter message_sending=new Client_FunctionCenter();
		    	if (this.message_send_textarea.getText()  instanceof String) {
		    		System.out.println(this.message_send_textarea.getText());
		    	}
		    	System.out.println(user.ID);
		    	Chat_Info message_ready=new Chat_Info(user.ID,friend.ID,this.message_send_textarea.getText(),Communication_Action.user_chat);		    	
		    	message_sending.chat_info_send(message_ready);
		    	
		    	message_map.put(message_ready, new message_pane(message_ready));
		    	message_map.get(message_ready).setLayoutX(700);
				message_map.get(message_ready).setLayoutY(20+60*message_map.size());
				message_view_anchorpane.getChildren().add(message_map.get(message_ready));
		    	this.message_send_textarea.setText("");    	
				
			}
		});
    	
    }
    /*
     * show_messagehistory  获取 历史消息记录
     * 
     */

    public void show_messagehistory() {
    	Client_FunctionCenter show_history=new  Client_FunctionCenter();
    	message_history=show_history.gain_message_history(new Chat_Info(user.ID,friend.ID,Communication_Action.user_gainchat_history));
    	//判定 消息发送者， 并且根据是否为主用户来置放 信息气泡 的位置（好友为左，用户为右）
    	for(int i=0;i<message_history.size();i++) {
    		if(message_history.get(i).sender.equals(user.ID)) {
    			
    			message_map.put(message_history.get(i), new message_pane(message_history.get(i)));
    			message_map.get(message_history.get(i)).setLayoutX(700);
    			message_map.get(message_history.get(i)).setLayoutY(20+60*i);
    		}
    		else {
    			message_map.put(message_history.get(i), new message_pane(message_history.get(i)));
    			message_map.get(message_history.get(i)).setLayoutX(0);
    			message_map.get(message_history.get(i)).setLayoutY(20+60*i);
    			
    		}
    		message_view_anchorpane.getChildren().add(message_map.get(message_history.get(i)));  		
    	}	
    }
    /*
     * 信息气泡类
     * 重写Pane   封装自定义信息类
     * ImageView icon  头像
     * Label nickname  昵称
     * Label message   信息
     */
    class message_pane extends Pane{
    	public User_Info user=null;
    	public Chat_Info chat_info=null;
    	public ImageView icon=null;
    	public Label nickname=null;
    	public Label message=null;
    	public  int max_row_string=15;
    	public TextArea message_area;
    	message_pane(Chat_Info chat_info){
    		this.setPrefSize(100, 150);
    		
    		this.chat_info=chat_info;
    		icon=new ImageView();
    		nickname=new Label();
    		message=new Label();
    		message_area=new TextArea();
    		message_area.setPrefHeight(50);
    		message_area.setPrefWidth(70);
    		message_area.setEditable(false);
    		//message_area.
    		if (Chatcontroller.this.user.ID.equals(chat_info.sender)) {
    			//个人信息气泡单面板的初始化
    			
    			this.user=Chatcontroller.this.user;

    			icon.setImage(new Image("file:E:/eclipse/chat/src/img/head/"+user.Icon,30,30, false, false));
    			nickname.setText(Chatcontroller.this.user.NickName);
    			message.setText(chat_info.message);
    			message_area.setLayoutX(0);
    			message_area.setLayoutX(10);
    			
    			message_area.setText(chat_info.message);
    			
    			nickname.setLayoutX(70);
    			nickname.setLayoutY(0);
    			icon.setLayoutX(70);
    			icon.setLayoutY(25);
    			System.out.println(chat_info.message);
    			message.setLayoutX(0);
    			message.setLayoutY(30);
    			message_area.getStyleClass().add("message_pane_textarea");
    			message.getStyleClass().add("message_pane_label");
    			nickname.getStyleClass().add("message_pane_label");
    		}
    		else {
    			//好友信息气泡单面板的初始化
    			this.user=Chatcontroller.this.friend;
    			icon.setImage(new Image("file:E:/eclipse/chat/src/img/head/"+friend.Icon,30,30, false, false));
    			message.setText(chat_info.message);
    			nickname.setText(user.NickName);
    			nickname.setLayoutX(0);
    			nickname.setLayoutY(0);
    			icon.setLayoutX(0);
    			icon.setLayoutY(30);
    			message_area.setLayoutX(0);
    			message_area.setLayoutX(10);
    			message_area.getStyleClass().add("message_pane_textarea");
    			message_area.setText(chat_info.message);
    			
    			message.setLayoutX(30);
    			message.setLayoutY(25);
    			System.out.println(chat_info.message);
    			message.getStyleClass().add("message_pane_label");
    			nickname.getStyleClass().add("message_pane_label");
    		}
    		
    		this.getChildren().add(icon);
    		this.getChildren().add(nickname);
    		this.getChildren().add(message);
    		//this.getChildren().add(message_area);
    	}
    	/*messagepane_length_judges
    	 * 
    	 * 计算信息字符串，并实现相应气泡的layout
    	 * @  pending_message  待处理的信息字符串
    	 */
    	 
    	public int messagepane_length_judge(String message) {
    		String pending_message=message;
    		while(pending_message.length()>max_row_string) {
    			
    		}
    		int message_length=message.length();
    		
    		return message_length;
    	}
    }
}

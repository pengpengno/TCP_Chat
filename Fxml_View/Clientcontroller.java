/**
 * Sample Skeleton for 'Clinet.fxml' Controller Class
 */

package fxml_view;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import fxml_view.Clientcontroller.Friend_Pane;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import resource.Client_Info;
import resource.User_Info;

public class Clientcontroller extends Windows{
    public Client_Info client_info=null;
    private Parent Clientroot;
    private Stage ClientStage;
    private Scene ClientScene;
   
    private  double xOffset=0;
	private  double yOffset=0;
	@FXML
    private Label user_signature;

    @FXML
    private AnchorPane Client_Pane;

    @FXML
    private Button min;

    @FXML
    private AnchorPane show_friends;

    @FXML
    private Pane user_pane;

    @FXML
    private Label user_nickname;

    @FXML
    private ImageView user_icon;

    @FXML
    private Button close;

    @FXML
    private ImageView user_state;

    @FXML
    private ScrollPane friends_scrollpane;
	/*
	 * friends_map   ����ڿͻ�������ʾ�ĺ������    ����а���friend_icon�� friend_signature��friend_state��friend_nickname 
	 * chatmap      ����뵥��ָ���ĺ���  ���촰�ڵĿ������� ����  show��close
	 * friends  ��Ŵӷ��������յĺ�����Ϣ�б�
	 */
	public Map<String,Friend_Pane> friends_map=null;
	public Vector<User_Info>  friends=null;
	public Map<String,Chatcontroller> chat_map=null;
    public void SetClient_Info(Client_Info ci) {
    	this.client_info=ci;
    	this.friends=ci.user_fri;
    }
    /*
     * Client_init
     * root,stage ��ʼ��
     * ����ؼ���ʼ�� 
     */
    public void Client_init() {	
    	try {
			Clientroot = FXMLLoader.load(getClass().getResource("/fxml_view/Client.fxml"));  
			ClientStage = new Stage();
			ClientScene=new Scene(Clientroot, 350, 800);
	        ClientStage.setScene(ClientScene);	  	        
	        ClientScene.getStylesheets().add(this.getClass().getResource("css/Client.css").toExternalForm());//����css�ļ�
              
	        controller_init();
	        ClientStage.initStyle(StageStyle.DECORATED);
	        ClientStage.setTitle("Client");
	        ClientStage.show();
        
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��			
			e.printStackTrace();
		}
    }
    /*
     * controller_init
     * ��������ʼ���� �Զ���ĸ������׳�ʼ����
     * ��ʼ�� ��fxml�ļ��� �Ŀؼ�
     */
    public  void  controller_init() {
    	Client_Pane=(AnchorPane) Clientroot.lookup("#Client_Pane");
        Client_Pane.setBackground(new Background(new BackgroundFill(Color.valueOf("#FFFFFF"), null, null)));
        
    	friends_map=new HashMap<String, Friend_Pane>();
    	chat_map=new HashMap<String,Chatcontroller>();
    	friends_scrollpane=(ScrollPane) Clientroot.lookup("#friends_scrollpane");
        show_friends=(AnchorPane)Clientroot.lookup("#show_friends");        
	    this.show_friends();
	    friends_scrollpane.setContent(show_friends);
	    friends_scrollpane.setBackground(new Background(new BackgroundFill(Color.valueOf("#FFFFFF"), null, null)));
	    show_friends.setBackground(new Background(new BackgroundFill(Color.valueOf("#FFFFFF"), null, null)));
	    User_Pane user_pane=new User_Pane(client_info.us);        
       
	    min = (Button)Clientroot.lookup("#min"); 
	    close = (Button)Clientroot.lookup("#close"); 
	    min.getStyleClass().add("min");
	    close.getStyleClass().add("close");

	    Client_Pane.getChildren().add(user_pane);
        
    	
    }
    class User_Pane extends Pane {
    	@FXML
    	private Label user_nickname=null;    	
    	@FXML
    	private Label user_signature=null;
    	
    	@FXML
    	private ImageView user_icon=null;  	
    	@FXML
    	private ImageView user_state=null; 	

    	User_Pane(User_Info us){
    		
    		user_nickname=(Label) Clientroot.lookup("#user_nickname");

	        user_nickname.setText(client_info.us.NickName);
	        
    		user_signature=(Label) Clientroot.lookup("#user_signature");
    		user_signature.setText(client_info.us.signature);
    		
    		Image user_image =new Image("file:E:/eclipse/chat/src/img/head/"+us.Icon,60,60,false,false);
    		user_icon=(ImageView) Clientroot.lookup("#user_icon");
    		user_icon.setImage(user_image);	
    		
    		user_state=(ImageView) Clientroot.lookup("#user_state");
    		user_state.setImage(new Image("file:E:/eclipse/chat/src/img/tool/online.png",10,10,false,false));
    	}  	  	
    }
    /* 
     * ������ʾScrollPane   
     * friends_pane��� ��������Pane ��map
     * friends   ��� ���� User_Info��ĺ��Ѹ�����Ϣ
     * friends_panel_width   friends_panel_height  friends_panel_x  friends_panel_y  �����λ�ÿ������
     */
	
		public void show_friends() {
			for(int i=0;i<friends.size();i++) {	
				friends_map.put(friends.get(i).ID,new Friend_Pane(friends.get(i)));
				friends_map.get(friends.get(i).ID).setLayoutX(0);
				friends_map.get(friends.get(i).ID).setLayoutY(0+80*i);
				show_friends.getChildren().add(friends_map.get(friends.get(i).ID));

			}			
			
		}
		/*
		 * 
		 * ��������Pane
		 * 
		 * friend_icon_scale friend_icon_x  friend_icon_y����ͷ��ؼ��������ԣ��Դ�Ϊ�������趨����ؼ�λ��
		 */
		class Friend_Pane extends  Pane{
			public User_Info friend=null;
			public ImageView friend_icon=null;
			public Label friend_nickname=null;
			public Label friend_signature=null;
			public ImageView friend_state=null;
			final static int friend_icon_x=30;
			final static int friend_icon_y=12;
			final static int friend_icon_scale=50;
	
			Friend_Pane(User_Info  us){
				
				this.friend=us;
				this.setMinSize(350, 100);
				this.choose();
				this.chat();
				
				friend_icon=new ImageView();
				friend_icon.setImage(new Image("file:E:/eclipse/chat/src/img/head/"+friend.Icon,friend_icon_scale,friend_icon_scale,false,false));
				friend_icon.setLayoutX(friend_icon_x);
				friend_icon.setLayoutY(friend_icon_y);
				
				friend_nickname=new Label();
				friend_nickname.setLayoutX(200);
				friend_nickname.setLayoutY(12);
				friend_nickname.setText(friend.NickName);
				friend_nickname.setFont(new Font("KaiTi", 18));
				
				friend_signature=new Label();
				friend_signature.setText(friend.signature);
				friend_signature.setFont(new Font("KaiTi", 18));
				friend_signature.setLayoutX(200);
				friend_signature.setLayoutY(45);
				
				friend_state=new ImageView();
				friend_state.setLayoutX(friend_icon_x+friend_icon_scale-3);
				friend_state.setLayoutY(friend_icon_y+friend_icon_scale-20);
				friend_state.setImage(new Image("file:E:/eclipse/chat/src/img/tool/"+friend.state+".png",10,10,false,false));
				//���ð�ť������ɫ
				//friend_state.setTextFill(Paint.valueOf("#00FA9A"));
				
				//this.setBackground(Color.valueOf("#FDF5E6"));
				
				
				this.setBackground(new Background(new BackgroundFill(Color.valueOf("#FFFFFF"), null, null)));
				this.getChildren().add(friend_icon);
				this.getChildren().add(friend_nickname);
				this.getChildren().add(friend_signature);
				this.getChildren().add(friend_state);				
			}
			/*
			 * chat
			 * ���ú���pane��������¼�
			 * �����촰�ڣ�������ɴ����ʼ��
			 */
			public void  chat() {
				this.setOnMouseClicked(event ->{
					if(event.getClickCount()==2) {
						if(chat_map.get(this.friend.ID)!=null) {							
							chat_map.get(this.friend.ID).setAlwaysOnTop();							
							
						}
						else {
							try {
							FXMLLoader loader =new FXMLLoader(getClass().getResource("/fxml_view/Chat.fxml"));
							loader.load();
						  	Chatcontroller  chat_contro=(Chatcontroller) loader.getController();
						  	chat_map.put(this.friend.ID, chat_contro);
						  	chat_contro.set_chat_info(Clientcontroller.this.client_info.us, this.friend);
						  	chat_contro.chat_init();
						  	
							}
							catch (Exception e){
					              e.printStackTrace();
					          }
						}
					}
				});
			}
			/*
			 * choose
			 * �������������    ͻ����ʾ����
			 * setOnMouseEntered�������ѡ�У��������Ϊ�ң�
			 * setOnMouseExited����Ƴ����Ϊ��ɫ
			 */
			public void  choose() {
				
				this.setOnMouseEntered(event ->{
					if(event.getSource()==this) {
						this.setBackground(new Background(new BackgroundFill(Color.valueOf("#D3D3D3"), null, null)));
					}
					
				});
				this.setOnMouseExited(event ->{
					if(event.getSource()==this) {
						this.setBackground(new Background(new BackgroundFill(Color.valueOf("#FFFFFF"), null, null)));
					}
				});
			}
		}


	@Override
	public void win_quit() {
		// TODO �Զ����ɵķ������
		min.setTooltip(new Tooltip("�˳�"));
        min.setOnAction(event -> {           
           System.exit(0);
        });		
	}
	@Override
	public void win_min() {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void win_move() {
		// TODO �Զ����ɵķ������
		
	}
}

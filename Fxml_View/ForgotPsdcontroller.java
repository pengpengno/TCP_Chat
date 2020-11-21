package fxml_view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ForgotPsdcontroller {
	 @FXML
	 private Button back_to_login;
	 @FXML
	 private TextField id_text;
	 @FXML
	 private CheckBox check_man;
     @FXML
     private TextField nickname_text;
     @FXML
     private CheckBox check_woman;
     @FXML
     private TextField contact_info;
     @FXML
     private Label id;
	 @FXML
	 private ScrollPane icon_scrollpane;
	 @FXML
     private Button comfirm;
	private Parent Forgotpsdroot;
	private Stage ForgotpsdStage;
	private Scene ForgotpsdScence;
	
	
	public void  ForgotPsd_init() {
    	try {
    		Forgotpsdroot = FXMLLoader.load(getClass().getResource("/fxml_view/ForgotPsd.fxml"));
			ForgotpsdStage=new Stage();
			ForgotpsdScence = new Scene(Forgotpsdroot, 1000, 600);  
			ForgotpsdScence.getStylesheets().add(this.getClass().getResource("css/ForgotPsd.css").toExternalForm());//设置css文件
	        control_init();
	        ForgotpsdStage.initStyle(StageStyle.TRANSPARENT);  
	        ForgotpsdStage.setScene(ForgotpsdScence);  
	        ForgotpsdStage.setTitle("forgotpsd");  
	        ForgotpsdStage.show();  
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	
    }
    
    public void control_init() {
    	back_to_login=(Button) Forgotpsdroot.lookup("#back_to_login");
    	id_text= (TextField) Forgotpsdroot.lookup("#id_text");
    	check_man=(CheckBox) Forgotpsdroot.lookup("#check_man");
    	nickname_text=(TextField) Forgotpsdroot.lookup("#nickname_text");
    	check_woman=(CheckBox) Forgotpsdroot.lookup("#check_woman");
    	contact_info=(TextField) Forgotpsdroot.lookup("#contact_info");
    	icon_scrollpane= (ScrollPane) Forgotpsdroot.lookup("#icon_scrollpane");
    	back_login();

    }
    /*
     * 返回登录界面
     */
    public void back_login() {
    	back_to_login.setOnMouseClicked(event ->{
    		if(event.getClickCount()==1) {
	    		try {
	        		this.ForgotpsdStage.close();
	        		}
	        	catch(Exception e){
     		
	        	}
    		}
    		
    	});
    }
}

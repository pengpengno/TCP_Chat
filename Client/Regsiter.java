package qq_client;
import javax.swing.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Regsiter extends JFrame{
	private Parent regsiter_root;
	private Scene regsiter_scene;
	private Stage regsoter_stage;	
	@FXML
    private Label address;
    @FXML
    private Button return_login;
    @FXML
    private CheckBox woman;
    @FXML
    private Label signature;
    @FXML
    private TextField nickname_text;
    @FXML
    private Button confirm_regiser;
    @FXML
    private Label sex;
    @FXML
    private Label icon;
    @FXML
    private Label contact_information;
    @FXML
    private AnchorPane choose_icon_pane;
    @FXML
    private PasswordField psd_text;
    @FXML
    private Label password;
    @FXML
    private TextField contact_information_text;
    @FXML
    private Label nickname;
    @FXML
    private TextField address_text;
    @FXML
    private TextField signature_text;
    @FXML
    private CheckBox man;
    public void login_init() {  	
		try {
			regsiter_root = FXMLLoader.load(getClass().getResource("/fxml_view/Regsiter.fxml"));
			regsoter_stage=new Stage();
			regsiter_scene = new Scene(regsiter_root, 600, 400);  
	        control_init();
	        regsoter_stage.initStyle(StageStyle.TRANSPARENT);  
	        regsoter_stage.setScene(regsiter_scene);  
	        regsoter_stage.setTitle("Login");  
	        regsoter_stage.show();  
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	
    }
    public void control_init(){
    	address_text=(TextField) regsiter_root.lookup("#address_text");
    	signature_text= (TextField) regsiter_root.lookup("#signature_text");
    	psd_text=(PasswordField) regsiter_root.lookup("#psd_text");
    	man=(CheckBox) regsiter_root.lookup("#man");
    	woman=(CheckBox) regsiter_root.lookup("#woman");
    	get_local_iconlib();
    }
	
	

	public void get_local_iconlib() {
		
	}
	public  void  regsiter() {
		
	}

}

package qq_client;

import java.io.IOException;
import java.net.URL;

import fxml_view.Chatcontroller;
import fxml_view.Clientcontroller;
import fxml_view.Logincontroller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login_load extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/fxml_view/Login.fxml"));
		loader.load();
	  	Logincontroller  login_contro=(Logincontroller) loader.getController();
	  	
	  	login_contro.login_init();

	}
	public static void main (String args[]) {
		
		Application.launch(Login_load.class, args);
	}
}

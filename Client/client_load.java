package qq_client;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class client_load extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL re=getClass().getResource("/fxml_view/Client.fxml");
		if (re==null) {
			System.out.println(getClass().getResource("/fxml_view/Client.fxml").toString());
			System.out.println("shibai");
			System.exit(0);
		}
		
		System.out.println(getClass().getResource("").toString());
		Parent root = FXMLLoader.load(getClass().getResource("/fxml_view/Client.fxml"));
		  
        Scene scene = new Scene(root, 600, 400);  
        primaryStage.initStyle(StageStyle.DECORATED);  
        primaryStage.setScene(scene);  
        primaryStage.setTitle("Client");  
        primaryStage.show();  

	}
	public static void main (String args[]) {
		
		Application.launch(client_load.class, args);
	}
}

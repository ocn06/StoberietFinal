package dk.stoberiet;

import com.sun.org.apache.bcel.internal.generic.FMUL;
import dk.stoberiet.BusinessLogic.*;
import dk.stoberiet.Models.CredentialModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));
		primaryStage.setTitle("Støberiet");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}

package com.pajarito.inventory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ManageSchoolInventoryJFXApp extends Application {
@Override
	public void start(Stage stage) throws IOException {
		System.out.println("SplashApp:start ");
		FXMLLoader fxmlLoader = new FXMLLoader(ManageSchoolInventoryJFXApp.class.getResource("splash-view.fxml"));
		Parent root = (Parent)fxmlLoader.load();
		SplashViewController splashViewController= fxmlLoader.getController();
		splashViewController.setStage(stage);
		Scene scene = new Scene(root, 460, 200);
		splashViewController.setSplashScene(scene);
		stage.setTitle("School Inventory Management");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
}

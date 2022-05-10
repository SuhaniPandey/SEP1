package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application

{

   public void start(Stage stage) throws Exception
  {
    stage.setTitle("The Overlooking Hotel");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(this.getClass().getResource("main/main.fxml"));
    Scene scene = new Scene((Parent)loader.load());
    stage.setScene(scene);
    stage.setResizable(true);
    stage.show();
  }
}

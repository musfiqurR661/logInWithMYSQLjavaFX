package project.ourproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button LogInBSS;

    @FXML
    private TextField passwordSS;

    @FXML
    private Button signBSS;

    @FXML
    private TextField userNameSS;

    public Controller() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
LogInBSS.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        DBUtils.logInUser(event, userNameSS.getText(), passwordSS.getText());
    }
});
      signBSS.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              DBUtils.ChangeScene(event,"signUp.fxml", "Sign Up !", null, null);
          }
      });
    }
}

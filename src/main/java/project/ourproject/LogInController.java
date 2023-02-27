package project.ourproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {


    @FXML
    private Button logOutB_LB;


    @FXML
    private static Label welcom;
    @FXML
    private static Label easypeasy;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        logOutB_LB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event,"sample.fxml", "Log in!", null, null);

            }
        });
    }
    public static void setUserInformation(String userName, String favChannel)
    {
        welcom.setText("Welcome" + userName + "!");
        easypeasy.setText("Learn and Run in" + favChannel + "!");

    }
}

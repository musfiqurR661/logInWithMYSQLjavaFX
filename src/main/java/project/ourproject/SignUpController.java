package project.ourproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private RadioButton elseRadioB;

    @FXML
    private Button logInBS;

    @FXML
    private RadioButton msRadioB;

    @FXML
    private Button signUpB;

    @FXML
    private TextField suPassword;

    @FXML
    private TextField suUserName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup= new ToggleGroup();
        msRadioB.setToggleGroup(toggleGroup);
        elseRadioB.setToggleGroup(toggleGroup);

        msRadioB.setSelected(true);
        signUpB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName =((RadioButton)toggleGroup.getSelectedToggle()).getText();

                if(suUserName.getText().trim().isEmpty() && suPassword.getText().trim().isEmpty())
                {
                    DBUtils.signUp(event, suUserName.getText(),suPassword.getText(),toggleName);
                }
                else {
                    System.out.println("Please fill all the Information");
                    Alert alert= new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill all the information to SignUp");
                    alert.show();
                }
            }
        });
        logInBS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.ChangeScene(event,"sample.fxml", "Log In", null, null);
            }
        });
    }
}

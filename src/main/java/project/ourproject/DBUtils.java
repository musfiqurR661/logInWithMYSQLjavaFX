package project.ourproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;


public class DBUtils {
    public static void ChangeScene(ActionEvent event, String fxmlFile, String title, String userName, String favChannel) {
        Parent root = null;
        if (userName != null && favChannel != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LogInController logInController = loader.getController();
                LogInController.setUserInformation(userName, favChannel);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    //
    public static void signUp(ActionEvent event, String userName, String password, String favChannel) {
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "username", "password");
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, fav_channel) VALUES (?, ?)");
//            statement.setString(1, userName);
//            statement.setString(2, favChannel);
//            statement.executeUpdate();
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        Connection  connection= null;
        PreparedStatement psInstant= null;
        PreparedStatement psCheackUserExists= null;
        ResultSet resultSet=null;
        try
        {
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "1234");
            psCheackUserExists=connection.prepareStatement("SELECT * FROM user WHERE username= ?");
            psCheackUserExists.setString(1,userName);
            resultSet=psCheackUserExists.executeQuery();

            if(resultSet.isBeforeFirst())
            {
                System.out.println("User already exist!");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You Cannot use this username.");
                alert.show();
            }else {
                psInstant=connection.prepareStatement("INSERT INTO users(username,password,favChannel VALUES (? ? ?)");
                psInstant.setString(1,userName);
                psInstant.setString(3,password);
                psInstant.setString(2,favChannel);
                psInstant.executeUpdate();

              ChangeScene(event, "loging.fxml", "Welcome", userName,favChannel);

            }

        }catch (SQLException e)
        {
            System.out.println(e);
        }finally {
            if(resultSet!=null)
            {
                try
                {
                    resultSet.close();
                }catch (SQLException e)
                {
                    System.out.println(e);
                }

            }
            if(psCheackUserExists !=null)
            {
                try
                {
                    psCheackUserExists.close();
                }catch (SQLException e)
                {
                    System.out.println(e);
                }
            }
            if(psInstant !=null)
            {
                try {
                    psInstant.close();
                }catch (SQLException e)
                {
                    System.out.println(e);
                }
            }
            if(connection != null)
            {
                try {
                    connection.close();
                }catch (SQLException e)
                {
                    System.out.println(e);
                }
            }
        }
    }
    public static void logInUser(ActionEvent event, String username, String password)
    {
        Connection connection=null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "1234");
            preparedStatement=connection.prepareStatement("SELECT password, favChannel FROM users WHERE username =?");
            preparedStatement.setString(1, username);
            resultSet=preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst())
            {
                System.out.println("User Not Found In Database");
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credent are Incorrect");
                alert.show();
            }
            else {
                while (resultSet.next())
                {
                   String retrievedPassword=resultSet.getString("password");
                   String retrievedChannel = resultSet.getString("favChannel");
                   if(retrievedPassword.equals(password))
                   {
                       ChangeScene(event, "login.fxml", "Welcome", username, retrievedChannel);
                   }
                   else
                   {
                       System.out.println("Password did not match");
                       Alert alert= new Alert(Alert.AlertType.ERROR);
                       alert.setContentText("Provided credent are Incorrect");
                       alert.show();
                   }
                }
            }

    }catch (SQLException e)
        {
            System.out.println(e);
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

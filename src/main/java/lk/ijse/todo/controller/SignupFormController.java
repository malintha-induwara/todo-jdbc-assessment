package lk.ijse.todo.controller;

/*
    @author DanujaV
    @created 11/7/23 - 12:18 AM   
*/


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.todo.dto.UsersDto;
import lk.ijse.todo.model.UsersModel;

import java.io.IOException;
import java.sql.SQLException;

public class SignupFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPw;

    @FXML
    private TextField txtUserName;


    private final UsersModel usersModel = new UsersModel();

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

        String userName=txtUserName.getText();
        String email=txtEmail.getText();
        String pw=txtPw.getText();

        UsersDto dto = new UsersDto(email,userName,pw);

        try{
            boolean isSaved = usersModel.saveUser(dto);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"User is Saved").show();
            }

        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }



    }

    @FXML
    void hyperLoginHereOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        root.getChildren().clear();
        Stage primaryStage = (Stage) root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Form");
    }
}

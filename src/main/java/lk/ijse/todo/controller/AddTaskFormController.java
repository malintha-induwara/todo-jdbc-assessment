package lk.ijse.todo.controller;

/*
    @author DanujaV
    @created 11/7/23 - 3:23 AM   
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.ijse.todo.dto.TasksDto;
import lk.ijse.todo.dto.UsersDto;
import lk.ijse.todo.model.TasksModel;
import lk.ijse.todo.model.UsersModel;

import java.sql.SQLException;

public class AddTaskFormController {
    @FXML
    private DatePicker txtDate;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtId;

    private TasksModel tasksModel = new TasksModel();

    @FXML
    void btnAddTaskOnAction(ActionEvent event) {

        try {
            int id = Integer.parseInt(txtId.getText());
            String email = UsersModel.loggedUserEmail;
            String description = txtDescription.getText();
            String dueDate = txtDate.getValue().toString();
            int isCompleted = 0;


            if (description.isEmpty() || dueDate.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Fill the Details").show();
                return;
            }


            TasksDto dto = new TasksDto(id, email, description, dueDate, isCompleted);

            try {

                boolean isSaved = tasksModel.saveTask(dto);
                clearFields();

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Task is Saved").show();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
        catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Fill the Id Details").show();
            txtId.requestFocus();
        }
    }

    private void clearFields() {
        txtId.clear();
        txtDescription.clear();
        txtDate.setValue(null);
    }


}

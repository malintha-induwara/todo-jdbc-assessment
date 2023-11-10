package lk.ijse.todo.controller;

/*
    @author DanujaV
    @created 11/7/23 - 2:51 AM   
*/

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.todo.dto.TasksDto;
import lk.ijse.todo.model.TasksModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DashboardFormController {
    @FXML
    private Label lblCompleted;

    @FXML
    private Label lblDue;

    private TasksModel tasksModel = new TasksModel();

    public void initialize(){
        setDueTasksCount();
        setCompletedTasksCount();
    }

    private void setCompletedTasksCount() {
        try {
            List<TasksDto> dtoList = tasksModel.loadCompletedTasks();
            lblCompleted.setText(String.valueOf(dtoList.size()));
        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setDueTasksCount() {
        try {
            List<TasksDto> dtoList = tasksModel.loadDueTasks(String.valueOf(LocalDate.now()));
            lblDue.setText(String.valueOf(dtoList.size()));
        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}

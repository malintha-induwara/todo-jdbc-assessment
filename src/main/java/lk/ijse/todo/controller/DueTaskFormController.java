package lk.ijse.todo.controller;

/*
    @author DanujaV
    @created 11/7/23 - 3:46 AM   
*/

import com.sun.javafx.scene.control.GlobalMenuAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.todo.dto.TasksDto;
import lk.ijse.todo.dto.tm.DueTm;
import lk.ijse.todo.model.TasksModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DueTaskFormController {

    @FXML
    private TableColumn<?, ?> colComplete;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<DueTm> tblDue;

    private  final TasksModel tasksModel = new TasksModel();

    public void initialize(){
        setCellValueFactory();
        loadDueTasks();
    }

    private void setCellValueFactory() {
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colComplete.setCellValueFactory(new PropertyValueFactory<>("btnComplete"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }

    private void loadDueTasks() {

        try {
            ObservableList<DueTm> obList = FXCollections.observableArrayList();

            // here you need to write the code to load the tasks from database table and add them to the obList.
            // this is just a sample data set.

            List<TasksDto> dtoList = tasksModel.loadDueTasks(String.valueOf(LocalDate.now()));

            for (TasksDto dto : dtoList) {
                DueTm tm =new DueTm(dto.getDescription(), dto.getDueDate());
                obList.add(tm);
            }

            // reason for using a for loop here is to add event handlers to the buttons in the table
            for (int i = 0; i < obList.size(); i++) {

                final int index = i;
                obList.get(i).getBtnComplete().setOnAction(event -> {
                    // here you need to write the code to mark the task as complete on database table

                    int taskId = dtoList.get(index).getTaskId();
                    markComplete(taskId);

                    refreshTable();

                });

                obList.get(i).getBtnDelete().setOnAction(event -> {

                    int taskId = dtoList.get(index).getTaskId();
                    deleteTask(taskId);

                    refreshTable();

                    // here you need to write the code to delete the task from FX table and database table as well.

                });
            }
            tblDue.setItems(obList);
        }
        catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void refreshTable() {
        loadDueTasks();
    }

    private void deleteTask(int taskId) {

        try{
            boolean isDeleted = tasksModel.deleteTask(taskId);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Task is deleted").show();
            }

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void markComplete(int taskId) {

        try {

            boolean isMarked = tasksModel.markComplete(taskId);

            if (isMarked){
                new Alert(Alert.AlertType.CONFIRMATION, "Task is marked as complete").show();
            }

        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }


    }
}

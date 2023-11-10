package lk.ijse.todo.model;

import lk.ijse.todo.db.DbConnection;
import lk.ijse.todo.dto.TasksDto;
import lk.ijse.todo.dto.tm.CompleteTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TasksModel {


    public boolean saveTask(TasksDto dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO tasks VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, dto.getTaskId());
        pstm.setString(2, dto.getEmail());
        pstm.setString(3, dto.getDescription());
        pstm.setString(4, dto.getDueDate());
        pstm.setInt(5, dto.getIsCompleted());

        return pstm.executeUpdate() > 0;

    }

    public List<TasksDto> loadDueTasks(String date) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM tasks WHERE dueDate < ? AND isCompleted = 0 AND email = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, date);
        pstm.setString(2, UsersModel.loggedUserEmail);

        ResultSet resultSet = pstm.executeQuery();

        List<TasksDto> dtoList = new ArrayList<>();

        while (resultSet.next()){

            TasksDto dto = new TasksDto(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );

            dtoList.add(dto);

        }

        return dtoList;
    }

    public boolean markComplete(int taskId) throws SQLException {

       Connection connection = DbConnection.getInstance().getConnection();

         String sql = "UPDATE tasks SET isCompleted = 1 WHERE task_Id = ?";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1, taskId);


            return pstm.executeUpdate() > 0;

    }

    public boolean deleteTask(int taskId) throws SQLException {

       Connection connection = DbConnection.getInstance().getConnection();

         String sql = "DELETE FROM tasks WHERE task_Id = ?";

         PreparedStatement pstm = connection.prepareStatement(sql);

         pstm.setInt(1, taskId);

         return pstm.executeUpdate() > 0;

    }

    public List<TasksDto> loadCompletedTasks() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM tasks WHERE isCompleted = 1 AND email = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,UsersModel.loggedUserEmail);

        ResultSet resultSet = pstm.executeQuery();

        List<TasksDto> dtoList = new ArrayList<>();

        while (resultSet.next()){

            TasksDto dto = new TasksDto(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );

            dtoList.add(dto);

        }

        return dtoList;
    }
}


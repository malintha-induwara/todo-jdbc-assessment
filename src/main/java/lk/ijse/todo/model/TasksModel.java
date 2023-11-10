package lk.ijse.todo.model;

import lk.ijse.todo.db.DbConnection;
import lk.ijse.todo.dto.TasksDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}


package lk.ijse.todo.model;

import lk.ijse.todo.db.DbConnection;
import lk.ijse.todo.dto.UsersDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersModel {

    public static String loggedUserEmail;
    public boolean saveUser(UsersDto dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO users VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEmail());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getPassword());

        return pstm.executeUpdate() > 0;

    }

    public boolean checkLogin(String userName, String pw) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, pw);

        return pstm.executeQuery().next();

    }

    public String getLoggedUserEmail(String userName) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT email FROM users WHERE name = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet resultSet = pstm.executeQuery();

        resultSet.next();
        return resultSet.getString(1);

    }
}


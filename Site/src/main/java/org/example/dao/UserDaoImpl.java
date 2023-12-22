package org.example.dao;

import lombok.SneakyThrows;
import org.example.containers.ConnectionToDataBaseContainer;
import org.example.entity.User;
import org.example.helpers.CryptPassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDaoImpl implements DAO<User>{
    private final Connection connection = ConnectionToDataBaseContainer.getConnection();
    @Override
    @SneakyThrows
    public void save(User user) {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name, surname, password, email, role) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, user.getName());
        statement.setString(2, user.getSurname());
        statement.setInt(3, user.getPassword());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getRole());
        statement.executeUpdate();
    }

    @Override
    public void delete(User user) {

    }

    @SneakyThrows
    @Override
    public User getById(Integer id) {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
        statement.setInt(1, id);
        if (getUser(statement) != null){
            return getUser(statement);
        } else {
            return null;
        }
    }

    @Override
    @SneakyThrows
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            User user = User.builder().id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .password(resultSet.getInt(4))
                    .email(resultSet.getString(5))
                    .teamId(resultSet.getString(6))
                    .taskId(resultSet.getString(7))
                    .build();
            users.add(user);
        }
        return users;
    }

    @SneakyThrows
    @Override
    public void update(User user) {
        PreparedStatement statement2 = connection.prepareStatement("UPDATE users " +
                "SET name=?, surname=?, password=?, email=?, team_id=?, task_id=?, role=? " +
                "WHERE id=?");
        statement2.setString(1, user.getName());
        statement2.setString(2, user.getSurname());
        statement2.setInt(3, user.getPassword());
        statement2.setString(4, user.getEmail());
        statement2.setString(5, user.getTeamId());
        statement2.setString(6, user.getTaskId());
        statement2.setString(7, user.getRole());
        statement2.setInt(8, user.getId());
        statement2.executeUpdate();
    }

    @SneakyThrows
    public User findByEmail(String email){
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email=?");
        statement.setString(1, email);
        return getUser(statement);
    }

    private User getUser(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        User user = new User();
        if (resultSet.next()){
            return User.builder().id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .password(resultSet.getInt(4))
                    .email(resultSet.getString(5))
                    .teamId(resultSet.getString(6))
                    .taskId(resultSet.getString(7))
                    .role(resultSet.getString(8)).build();
        } else {
            return null;
        }
    }

}

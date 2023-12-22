package org.example.dao;

import lombok.SneakyThrows;
import org.example.containers.ConnectionToDataBaseContainer;
import org.example.entity.Task;
import org.example.entity.Team;
import org.example.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskDaoImpl implements DAO<Task> {

    private final Connection connection = ConnectionToDataBaseContainer.getConnection();

    @Override
    @SneakyThrows
    public void save(Task task) {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO tasks (description, admin_id, performer_id, status, comments_id) " +
                "VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, task.getDescription());
        statement.setInt(2, task.getAdminId());
        statement.setInt(3, task.getPerformerId());
        statement.setInt(4, task.getStatus());
        statement.setString(5, listIntToString(task.getComments()));
        statement.executeUpdate();
    }

    @Override
    public void delete(Task task) {

    }

    @Override
    @SneakyThrows
    public Task getById(Integer id) {
        if (id != 0) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks WHERE id=?");
            statement.setInt(1, id);
            if (getTask(statement) != null) {
                return getTask(statement);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    @SneakyThrows
    public void update(Task task) {
        PreparedStatement statement2 = connection.prepareStatement("UPDATE tasks " +
                "SET status=?, comments_id=? " +
                "WHERE id=?");
        statement2.setInt(1, task.getStatus());
        statement2.setString(2, listIntToString(task.getComments()));
        statement2.setInt(3, task.getId());
        statement2.executeUpdate();
    }
    public static String listIntToString(List<Integer> list) {
        StringBuilder s = new StringBuilder();
        for (int i: list)
            s.append(i).append(" ");
        return s.toString();
    }

    public static List<Integer> stringToListInt(String s) {
        List<Integer> result = new ArrayList<>();
        Scanner scan = new Scanner(s);
        while (scan.hasNextInt())
            result.add(scan.nextInt());
        scan.close();
        return result;
    }
    private Task getTask(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            return Task.builder()
                    .id(resultSet.getInt(1))
                    .description(resultSet.getString(2))
                    .adminId(resultSet.getInt(3))
                    .performerId(resultSet.getInt(4))
                    .status(resultSet.getInt(5))
                    .comments(stringToListInt(resultSet.getString(6)))
                    .build();
        } else {
            return null;
        }
    }
}

package org.example.dao;

import lombok.SneakyThrows;
import org.example.containers.ConnectionToDataBaseContainer;
import org.example.entity.Team;
import org.example.entity.User;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamDaoImpl implements DAO<Team>{
    private final Connection connection = ConnectionToDataBaseContainer.getConnection();

    @Override
    @SneakyThrows
    public void save(Team team) {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO teams (name, users_id, team_admin) " +
                "VALUES (?, ?, ?)");
        statement.setString(1, team.getName());
        statement.setString(2, team.getUsersId());
        statement.setInt(3, team.getTeam_admin());
        statement.executeUpdate();
    }

    @Override
    @SneakyThrows
    public void delete(Team team) {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM teams WHERE id=?");
        statement.setInt(1, team.getId());
        /*for (Integer o:team.getUsersId()) {
            User user = userDao.getById(o);
            user.setTeam_id(0);
            user.setTask_id(0);
            user.setRole("user");
        }*/

    }

    @Override
    @SneakyThrows
    public Team getById(Integer id) {
        if (id != 0) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM teams WHERE id=?");
            statement.setInt(1, id);
            if (getTeam(statement) != null) {
                return getTeam(statement);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @SneakyThrows
    public Team getTeamId(Team team) {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM teams WHERE name=? AND users_id=? AND team_admin=?");
        statement.setString(1, team.getName());
        statement.setString(2, team.getUsersId());
        statement.setInt(3, team.getTeam_admin());
        if (getTeam(statement) != null){
            return getTeam(statement);
        } else {
            return null;
        }
    }

    @Override
    public List<Team> getAll() {
        return null;
    }

    @Override
    @SneakyThrows
    public void update(Team team) {
        PreparedStatement statement2 = connection.prepareStatement("UPDATE teams " +
                "SET name=?,users_id=?, team_admin=?, tasksId=? " +
                "WHERE id=?");
        statement2.setString(1, team.getName());
        statement2.setString(2, team.getUsersId());
        statement2.setInt(3, team.getTeam_admin());
        statement2.setString(4, team.getTasksId());
        statement2.setInt(5, team.getId());
        statement2.executeUpdate();
    }

    private Team getTeam(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            return Team.builder()
                    .usersId(resultSet.getString(3))
                    .team_admin(resultSet.getInt(4))
                    .name(resultSet.getString(2))
                    .id(resultSet.getInt(1))
                    .tasksId(resultSet.getString(5))
                    .build();
        } else {
            return null;
        }
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
}

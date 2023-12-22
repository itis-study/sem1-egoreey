package org.example.servlets;

import org.example.dao.TaskDaoImpl;
import org.example.dao.TeamDaoImpl;
import org.example.dao.UserDaoImpl;
import org.example.entity.Team;
import org.example.entity.User;
import org.example.helpers.JsonParserFromAjax;
import org.example.helpers.StringParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/team")
public class TeamServlet extends HttpServlet {
    UserDaoImpl userDao = new UserDaoImpl();
    TeamDaoImpl teamDao = new TeamDaoImpl();
    TaskDaoImpl taskDao = new TaskDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer id = (Integer) session.getAttribute("id");
        Integer team_id = (Integer) session.getAttribute("team_id");
        Team team = teamDao.getById(team_id);
        String role = (String) session.getAttribute("role");
        List<User> users = new ArrayList<>();
        if (team != null){
            List<Integer> usersId = StringParser.stringToListInt(team.getUsersId());
            for (Integer i:usersId) {
                users.add(userDao.getById(i));
            }
        }
        req.setAttribute("users", users);
        if (role.equals("team_admin")) {
            req.getRequestDispatcher("/teamAdmin.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/team.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email= JsonParserFromAjax.getJson(req, "email");
        String act = req.getHeader("Action");
        Integer team_id = (Integer) req.getSession().getAttribute("team_id");
        Team team = teamDao.getById(team_id);
        if (act.equals("Delete")) {
            User userToDelete;
            if ((userToDelete = userDao.findByEmail(email)) != null) {
                Integer id = (Integer) req.getSession().getAttribute("id");
                User user = userDao.getById(id);
                StringParser.stringToListInt(team.getUsersId()).remove(userToDelete.getId());
                StringParser.stringToListInt(userToDelete.getTeamId()).remove(team.getId());
                List<Integer> list1 = StringParser.stringToListInt(userToDelete.getTaskId());
                for (Integer o:list1) {
                    if (taskDao.getById(o).getAdminId().equals(user.getId()) &&
                            taskDao.getById(o).getPerformerId().equals(userToDelete.getId())){
                        list1.remove(o);
                    }
                }
                userToDelete.setTaskId(StringParser.listIntToString(list1));
                userDao.update(userToDelete);
                teamDao.update(team);
                if (userToDelete.getRole().equals("team_admin")){

                }
            }
        }
        if (act.equals("Add")){
            User userToAdd;
            if ((userToAdd = userDao.findByEmail(email)) != null) {
                Integer id = (Integer) req.getSession().getAttribute("id");
                User user = userDao.getById(id);
                StringParser.stringToListInt(team.getUsersId()).add(userToAdd.getId());
                StringParser.stringToListInt(userToAdd.getTeamId()).add(team.getId());
                userDao.update(userToAdd);
                teamDao.update(team);
            }
        }
    }
}

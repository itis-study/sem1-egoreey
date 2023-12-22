package org.example.servlets;

import org.example.dao.TaskDaoImpl;
import org.example.dao.TeamDaoImpl;
import org.example.dao.UserDaoImpl;
import org.example.entity.Team;
import org.example.entity.User;
import org.example.helpers.StringParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/createTask")
public class CreateTaskServlet extends HttpServlet {
    private final TaskDaoImpl taskDao = new TaskDaoImpl();
    UserDaoImpl userDao = new UserDaoImpl();
    private final TeamDaoImpl teamDao = new TeamDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        Integer id = (Integer) session.getAttribute("team_id");
        Integer admin_id = (Integer) session.getAttribute("id");
        log(role);
        log(String.valueOf(id));
        log(String.valueOf(admin_id));
        if (id == null){
            resp.sendRedirect("/Site/myTeams");
        }
        Team team = teamDao.getById(id);
        List<User> users = new ArrayList<>();

        List<Integer> users_id = StringParser.stringToListInt(team.getUsersId());
        for (Integer i:users_id) {
            log(String.valueOf(i));
            users.add(userDao.getById(i));
            log(userDao.getById(i).toString());
        }
        log(String.valueOf(users.size()));
        session.setAttribute("team_users", users);
        req.getRequestDispatcher("/createTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

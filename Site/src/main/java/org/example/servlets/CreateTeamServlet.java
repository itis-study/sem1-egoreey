package org.example.servlets;

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

@WebServlet("/createTeam")
public class CreateTeamServlet extends HttpServlet {
    UserDaoImpl userDao = new UserDaoImpl();
    TeamDaoImpl teamDao = new TeamDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createTeam.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teamName = req.getParameter("team_name");
        HttpSession httpSession = req.getSession();
        Integer id = (Integer) httpSession.getAttribute("id");
        String[] emails = req.getParameterValues("email_1");
        List<Integer> users_id = new ArrayList<>();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < emails.length; i++) {
            if ((!Objects.equals(emails[i], "")) && (userDao.findByEmail(emails[i]) != null)){
                User user = userDao.findByEmail(emails[i]);
                users.add(user);
                users_id.add(user.getId());
            }
        }
        User team_admin = userDao.getById(id);
        team_admin.setRole("team_admin");
        users_id.add(id);
        users.add(team_admin);
        httpSession.setAttribute("role", "team_admin");
        Team team = Team.builder().name(teamName).usersId(StringParser.listIntToString(users_id)).team_admin(id).build();
        teamDao.save(team);
        team = teamDao.getTeamId(team);
        for (User u:users) {
            if (u.getTeamId() != null) {
                StringParser.stringToListInt(u.getTeamId()).add(team.getId());
                userDao.update(u);
            } else {
                String s = String.valueOf(team.getId());
                u.setTeamId(s);
                userDao.update(u);
            }
        }
        resp.sendRedirect("/Site/myTeams");
    }
}

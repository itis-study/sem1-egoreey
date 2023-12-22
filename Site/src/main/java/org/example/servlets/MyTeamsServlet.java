package org.example.servlets;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/myTeams")
public class MyTeamsServlet extends HttpServlet {
    UserDaoImpl userDao = new UserDaoImpl();
    TeamDaoImpl teamDao = new TeamDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer id = (Integer) session.getAttribute("id");
        User user = userDao.getById(id);
        List<Team> teams = new ArrayList<>();
        if (user.getTeamId() != null){
            log(user.getTeamId());
            List<Integer> teamsId = StringParser.stringToListInt(user.getTeamId());
            for (Integer i:teamsId) {
                Team team = teamDao.getById(i);
                teams.add(team);
                log(team.toString());
            }
        }
        req.setAttribute("teams", teams);
        req.getRequestDispatcher("/myTeams.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String team_name = req.getParameter("team_name");
        log(team_name);
        HttpSession session = req.getSession();
        Integer id = (Integer) req.getSession().getAttribute("id");
        User user = userDao.getById(id);
        List<Integer> list = StringParser.stringToListInt(user.getTeamId());
        for (Integer i:list) {
            if ((teamDao.getById(i) != null) && (teamDao.getById(i).getName().equals(team_name))){
                session.setAttribute("team_id", teamDao.getById(i).getId());
                resp.sendRedirect("/Site/team");
            }
        }
    }
}

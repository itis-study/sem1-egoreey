package org.example.servlets;

import org.example.dao.UserDaoImpl;
import org.example.entity.User;
import org.example.helpers.CryptPassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
    private static final UserDaoImpl userDao = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession httpSession = req.getSession();
        if ((userDao.findByEmail(email) != null) ){
            if ((userDao.findByEmail(email).getPassword()).equals((password.hashCode()))){
                User user = userDao.findByEmail(email);
                httpSession.setAttribute("id", user.getId());
                httpSession.setAttribute("role", user.getRole());
                resp.sendRedirect("/Site/myTeams");
            }
        } else {
            req.getRequestDispatcher("/authorization.jsp").forward(req, resp);
        }
    }
}

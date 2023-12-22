package org.example.servlets;

import org.example.containers.ConnectionToDataBaseContainer;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password1 = req.getParameter("password");
        String password2 = req.getParameter("repeat_password");
        HttpSession httpSession = req.getSession();
        if (password1.equals(password2)){
            User user = User.builder().name(name)
                    .password((password1.hashCode()))
                    .email(email).surname(surname).role("user").build();
            userDao.save(user);
            httpSession.setAttribute("id", userDao.findByEmail(email).getId());
            httpSession.setAttribute("role", "user");
            resp.sendRedirect("/myTeams");
        }
    }
}

package estm.dsic.sqleditor.controller;

import estm.dsic.sqleditor.model.Error;
import estm.dsic.sqleditor.model.User;
import estm.dsic.sqleditor.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/auth", "/auth.jsp"})
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) resp.sendRedirect("editor");
        else req.getRequestDispatcher("WEB-INF/auth.page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        try {
            AuthService.authenticate(user);

            req.getSession().setAttribute("user", user);
            resp.sendRedirect("editor");
        } catch (SQLException ex) {
            req.setAttribute("error", Error.fromException(Error.ErrorCode.AUTH_ERROR, ex));
            req.getRequestDispatcher("error.page.jsp").forward(req, resp);
        }
    }
}

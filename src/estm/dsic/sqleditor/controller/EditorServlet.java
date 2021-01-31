package estm.dsic.sqleditor.controller;

import estm.dsic.sqleditor.dao.LoggedOutUserException;
import estm.dsic.sqleditor.model.Error;
import estm.dsic.sqleditor.service.EditorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SqlEditorServlet", urlPatterns = {"/editor", "/editor.jsp"})
public class EditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) resp.sendRedirect("auth");
        req.getRequestDispatcher("WEB-INF/sql.page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String query = req.getParameter("query");
            if (query == null || req.getAttribute("result") != null) doGet(req, resp);
            else {
                String result = EditorService.executeQuery(query);

                req.setAttribute("result", result);
                req.getRequestDispatcher("editor").forward(req, resp);
            }
        } catch (SQLException ex) {
            Error.ErrorCode code = Error.ErrorCode.SQL_ERROR;
            if (ex instanceof LoggedOutUserException) {
                req.getSession().removeAttribute("user");
                code = Error.ErrorCode.AUTH_ERROR;
            }
            req.setAttribute("error", Error.fromException(code, ex));
            req.getRequestDispatcher("error.page.jsp").forward(req, resp);
        }
    }
}

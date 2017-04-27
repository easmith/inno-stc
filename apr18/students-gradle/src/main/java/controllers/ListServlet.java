package controllers;

import services.StudentServiceImpl;
import services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eku on 19.04.17.
 */
public class ListServlet extends HttpServlet {

    private static StudentService studentsService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", studentsService.getAllStudents());
        getServletContext().getRequestDispatcher("/studentList.jsp").forward(req, resp);
    }
}

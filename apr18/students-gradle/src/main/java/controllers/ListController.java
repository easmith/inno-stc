package controllers;

import services.StudentService;
import services.StudentsServiceInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eku on 19.04.17.
 */
public class ListController extends HttpServlet {

    private static StudentsServiceInterface studentsService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("list", studentsService.getAllStudents());
        getServletContext().getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}

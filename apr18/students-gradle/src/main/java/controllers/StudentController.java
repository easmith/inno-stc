package controllers;

import models.DAO.StudentDaoImpl;
import models.POJO.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eku on 18.04.17.
 */
public class StudentController extends HttpServlet {
    private StudentDaoImpl dao;

    public StudentController() {
        super();
        dao = new StudentDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward="";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int studentId = Integer.parseInt(req.getParameter("id"));
            dao.deleteStudent(studentId);
            forward = "/student.jsp";
            req.setAttribute("students", dao.getAllStudents());
        } else if (action.equalsIgnoreCase("edit")){
            forward = "/student.jsp";
            int studentId = Integer.parseInt(req.getParameter("id"));
            Student student = dao.getStudentById(studentId);
            req.setAttribute("studentId", student);
        } else if (action.equalsIgnoreCase("list")){
            forward = "/studentList.jsp";
            req.setAttribute("var", "test");
            req.setAttribute("students", dao.getAllStudents());
        } else {
            forward = "/student.jsp";
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

package controllers;

import Utils.Converter;
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
        String forward = "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int studentId = Integer.parseInt(req.getParameter("id"));
            dao.deleteStudent(studentId);
            req.setAttribute("students", dao.getAllStudents());
            forward = "/studentList.jsp";
        } else if (action.equalsIgnoreCase("list")) {
            req.setAttribute("students", dao.getAllStudents());
            forward = "/studentList.jsp";
        } else if (action.equalsIgnoreCase("edit")) {
            int studentId = Integer.parseInt(req.getParameter("id"));
            Student student = dao.getStudentById(studentId);
            req.setAttribute("student", student);
            forward = "/student.jsp";
        } else {
            forward = "/student.jsp";
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        student.setName(req.getParameter("name"));
        Integer studentId = Converter.strToInteger(req.getParameter("id"));

        if (studentId == 0)
            dao.addStudent(student);
        else {
            student.setId(studentId);
            dao.updateStudent(student);
        }
        RequestDispatcher view = req.getRequestDispatcher("/studentList.jsp");
        req.setAttribute("students", dao.getAllStudents());
        view.forward(req, resp);
    }
}

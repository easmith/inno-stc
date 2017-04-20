package controllers;

import Utils.Converter;
import models.DAO.StudentDaoImpl;
import models.POJO.Student;
import org.apache.log4j.Logger;

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

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    public StudentController() {
        super();
        dao = new StudentDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action == null) {
            action = "list";
        }

        if (action.equalsIgnoreCase("delete")) {
            Integer studentId = Converter.strToInteger(req.getParameter("id"));
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
        Integer studentId = Converter.strToInteger(req.getParameter("id"));
        Student student = dao.getStudentById(studentId);

        String studentName = req.getParameter("name");
        Integer age = Converter.strToInteger(req.getParameter("age"));
        LOGGER.debug("stud name:" + studentName);
        if (studentId == 0) {
            dao.addStudent(new Student(0, studentName, age));
        } else {
            student = dao.getStudentById(studentId);
            if (student != null) {
                student.setName(studentName);
                dao.updateStudent(student);
            }
        }

        RequestDispatcher view = req.getRequestDispatcher("/studentList.jsp");
        req.setAttribute("students", dao.getAllStudents());
        view.forward(req, resp);
    }
}

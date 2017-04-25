package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import utils.Converter;
import models.POJO.Student;
import org.apache.log4j.Logger;
import services.StudentService;
import services.StudentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eku on 18.04.17.
 */
public class StudentController extends HttpServlet {

    @Autowired
    private StudentService studentService;//= new StudentServiceImpl();

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.
                processInjectionBasedOnServletContext(this,
                        config.getServletContext());
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
            studentService.deleteStudent(studentId);
            req.setAttribute("students", studentService.getAllStudents());
            forward = "/studentList.jsp";
        } else if (action.equalsIgnoreCase("list")) {
            req.setAttribute("students", studentService.getAllStudents());
            forward = "/studentList.jsp";
        } else if (action.equalsIgnoreCase("edit")) {
            int studentId = Integer.parseInt(req.getParameter("id"));
            Student student = studentService.getStudentById(studentId);
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
        Student student = studentService.getStudentById(studentId);

        String studentName = req.getParameter("name");
        Integer age = Converter.strToInteger(req.getParameter("age"));
        LOGGER.debug("stud name:" + studentName);
        if (studentId == 0) {
            studentService.addStudent(new Student(0, studentName, age));
        } else {
            student = studentService.getStudentById(studentId);
            if (student != null) {
                student.setName(studentName);
                studentService.updateStudent(student);
            }
        }

        RequestDispatcher view = req.getRequestDispatcher("/studentList.jsp");
        req.setAttribute("students", studentService.getAllStudents());
        view.forward(req, resp);
    }
}

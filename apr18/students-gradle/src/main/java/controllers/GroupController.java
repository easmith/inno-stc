package controllers;

import Utils.Converter;
import models.DAO.GroupDaoImpl;
import models.DAO.StudentDaoImpl;
import models.POJO.Group;
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
public class GroupController extends HttpServlet {
    private GroupDaoImpl dao;

    public GroupController() {
        super();
        dao = new GroupDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action == null) {
            action = "list";
        }

        if (action.equalsIgnoreCase("delete")) {
            int studentId = Integer.parseInt(req.getParameter("id"));
            dao.deleteGroup(studentId);
            req.setAttribute("groups", dao.getAllGroups());
            forward = "/groupList.jsp";
        } else if (action.equalsIgnoreCase("list")) {
            req.setAttribute("groups", dao.getAllGroups());
            forward = "/groupList.jsp";
        } else if (action.equalsIgnoreCase("edit")) {
            int studentId = Integer.parseInt(req.getParameter("id"));
            Group group = dao.getGroupById(studentId);
            req.setAttribute("group", group);
            forward = "/group.jsp";
        } else {
            forward = "/group.jsp";
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Group group = new Group();
//        group.setName(req.getParameter("name"));
        Integer studentId = Converter.strToInteger(req.getParameter("id"));

        if (studentId == 0)
            dao.addGroup(group);
        else {
            group.setId(studentId);
            dao.updateGroup(group);
        }
        RequestDispatcher view = req.getRequestDispatcher("/studentList.jsp");
        req.setAttribute("students", dao.getAllGroups());
        view.forward(req, resp);
    }
}

package controllers;

import utils.Converter;
import models.POJO.Group;
import org.apache.log4j.Logger;
import services.GroupService;
import services.GroupServiceImpl;

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
    private static GroupService groupService = new GroupServiceImpl();

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action == null) {
            action = "list";
        }

        if (action.equalsIgnoreCase("delete")) {
            int groupId = Integer.parseInt(req.getParameter("id"));
            groupService.deleteGroup(groupId);
            req.setAttribute("groups", groupService.getAllGroups());
            forward = "/groupList.jsp";
        } else if (action.equalsIgnoreCase("list")) {
            req.setAttribute("groups", groupService.getAllGroups());
            forward = "/groupList.jsp";
        } else if (action.equalsIgnoreCase("edit")) {
            int groupId = Integer.parseInt(req.getParameter("id"));
            Group group = groupService.getGroupById(groupId);
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
//        group.setName(req.getParameter("name"));
        Integer groupId = Converter.strToInteger(req.getParameter("id"));

        Group group = groupService.getGroupById(groupId);

        String groupName = req.getParameter("name");
        Integer age = Converter.strToInteger(req.getParameter("age"));
        LOGGER.debug("group name:" + groupName);
        if (groupId == 0) {
            groupService.addGroup(new Group(0, groupName));
        } else {
            group = groupService.getGroupById(groupId);
            if (group != null) {
                group.setName(groupName);
                groupService.updateGroup(group);
            }
        }

        RequestDispatcher view = req.getRequestDispatcher("/groupList.jsp");
        req.setAttribute("groups", groupService.getAllGroups());
        view.forward(req, resp);
    }
}

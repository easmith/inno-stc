package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.StudentService;

/**
 * Created by Olesya on 27.04.2017.
 */
@Controller
@RequestMapping(value = "/students")
public class ListController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public String showList(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "studentList";
    }
}

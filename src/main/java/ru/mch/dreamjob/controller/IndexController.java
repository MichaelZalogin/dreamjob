package ru.mch.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mch.dreamjob.entity.User;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    /**
     * http://dreamjobmch.ru domen
     */
    @GetMapping({"/", "/index"})
    public String getIndex(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
        return "index";
    }
}
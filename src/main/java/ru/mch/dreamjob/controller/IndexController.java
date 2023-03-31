package ru.mch.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    /**
     * http://dreamjobmch.ru domen
     */
    @GetMapping({"/", "/index"})
    public String getIndex() {
        return "index";
    }
}
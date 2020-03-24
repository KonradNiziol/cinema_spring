package com.kniziol.web.controller;

import com.kniziol.serwer.dto.MessageInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"", "/","/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/notFound")
    public String notFound(Model model) {
        model.addAttribute("message", MessageInformation.builder()
                .cssStyle("alert-danger")
                .messageStatus("Danger!")
                .message("Page not found")
                .build());
        return "infoMessage";
    }

}

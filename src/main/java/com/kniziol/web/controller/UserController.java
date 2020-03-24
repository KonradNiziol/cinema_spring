package com.kniziol.web.controller;


import com.kniziol.serwer.dto.MessageInformation;
import com.kniziol.serwer.service.CustomerService;
import com.kniziol.serwer.dto.LoginCredentials;
import com.kniziol.serwer.dto.UserCreateDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {


    private final CustomerService customerService;

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("user", new UserCreateDto());
        return "users/create";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("credentials", new LoginCredentials());
        return "users/login";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute UserCreateDto userCreateDto, Model model){
        if (customerService.createCustomer(userCreateDto)){
            model.addAttribute("message", MessageInformation.builder()
                    .cssStyle("alert-success")
                    .messageStatus("Success!")
                    .message(String.format("User with name: %s has been properly created, please log in.", userCreateDto.getName()))
                    .build());
        } else {
            model.addAttribute("message", MessageInformation.builder()
                    .cssStyle("cssStyleAlert.danger")
                    .messageStatus("message.danger")
                    .message(String.format("Something went wrong. Try logging in again.", userCreateDto.getName()))
                    .build());
        }
        return "infoMessage";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginCredentials loginCredentials, Model model){
        if (customerService.loginCustomer(loginCredentials)){
            model.addAttribute("message", MessageInformation.builder()
                    .cssStyle("cssStyleAlert.success")
                    .messageStatus("message.success")
                    .message(String.format("User with email: %s has been properly created, please log in.",loginCredentials.getEmail()))
                    .build());
        } else{
            model.addAttribute("message", MessageInformation.builder()
                    .cssStyle("cssStyleAlert.danger")
                    .messageStatus("message.danger")
                    .message(String.format("Something went wrong. Try logging in again.",loginCredentials.getEmail()))
                    .build());
        }
        return "infoMessage";
    }

}

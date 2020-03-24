package com.kniziol.web.controller;

import com.kniziol.exception.AppException;
import com.kniziol.serwer.dto.MessageInformation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({AppException.class})
    public String appExceptionHandler(AppException e, Model model) {
        model.addAttribute("message", MessageInformation.builder()
                .cssStyle("alert-danger")
                .messageStatus("Danger!")
                .message(e.getMessage())
                .build());
        return "infoMessage";
    }
}

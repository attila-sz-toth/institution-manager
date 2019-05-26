package com.tasz.institutionmanager.controller;

import com.tasz.institutionmanager.model.LoginData;
import com.tasz.institutionmanager.model.User;
import com.tasz.institutionmanager.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    User login(@RequestBody LoginData loginData) {
        return loginService.getUserByUserName(loginData.getUsername());
    }
}

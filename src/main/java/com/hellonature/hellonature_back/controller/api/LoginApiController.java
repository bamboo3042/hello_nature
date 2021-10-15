package com.hellonature.hellonature_back.controller.api;

import com.hellonature.hellonature_back.model.network.Header;
import com.hellonature.hellonature_back.model.network.response.LoginApiResponse;
import com.hellonature.hellonature_back.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginService loginService;

    @GetMapping("/user")
    public Header<LoginApiResponse> userLogin(@RequestParam(name = "id") String id,
                                              @RequestParam(name = "password") String password){
        return loginService.userLogin(id, password);
    }

    @GetMapping("/admin")
    public Header<LoginApiResponse> adminLogin(@RequestParam(name = "id") String id,
                                              @RequestParam(name = "password") String password){
        return loginService.adminLogin(id, password);
    }
}

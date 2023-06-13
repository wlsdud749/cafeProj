package com.example.cafe.Controller;

import com.example.cafe.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@Slf4j
@RequiredArgsConstructor
public class ViewsController {

    @GetMapping(value = "/login")
    public void loginProc() {

    }

    @GetMapping(value = "/boardWrite")
    public void boardWriteProc(HttpServletRequest req) {

        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("userinfo");

    }


    @GetMapping(value = "/user-add")
    public void userAddProc() {

    }

    @GetMapping(value = "/reserve")
    public void reserveProc() {

    }

    @GetMapping(value = "/map")
    public void mapProc() {

    }

    @GetMapping(value = "/search2")
    public void searchProc() {

    }

    @GetMapping("/test")
    public String testpage() {

        return "test";
    }

}


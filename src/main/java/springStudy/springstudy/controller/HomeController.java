package springStudy.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Member;

@Controller
public class HomeController {

    @GetMapping("/")
    public String main(){
        return "index.html";
    }
}

package com.example.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller//클래스에 붙이면
public class BasicController {
    //서버 생성 가능
    @GetMapping("/") //누가 메인 페이지 접속하면
    @ResponseBody
    String hello(){
        return "<h4>Hello World</h4>"; // 이거 보내주세요
        //->보통 html파일은 따로 만들어서 보관하고 불러옴
    }

    @GetMapping("/about") //누가 about으로 접속하면
    //@ResponseBody 이걸 빼면 페이지 보내달라는 뜻, 있으면 문자 그대로 보내주세요
    String about(){
        return "index.html"; // 이거 보내주세요
        //기본 경로가 static 폴더
    }

    @GetMapping("/mypage") //누가 about으로 접속하면
    @ResponseBody
    String mypage(){
        return "마이페이지"; // 이거 보내주세요
    }

    @GetMapping("/date") //누가 about으로 접속하면
    @ResponseBody
    String date(){
        return LocalDate.now().toString(); // 이거 보내주세요
    }

}

package com.example.helloboot;


import com.example.helloboot.Domain.Civies;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    Civies civies;


    @GetMapping("/getWelcome/{print}")
    public static String getWelcome(@PathVariable String print){
        System.out.println("Here");
        return "Welcome "+print;
    }

    @PostMapping("/postreq")
    public static String postWelcome(@RequestParam(name = "print") String print){

        return "Welcome "+print;

    }


    @RequestMapping(value = "/headreq",method = RequestMethod.HEAD )
    public static void headreq(HttpServletResponse response){
        response.setHeader("Custom-Header","This is a custom header");

    }


    @RequestMapping(value="/optionsreq",method = RequestMethod.OPTIONS)
    public static void optionsReq(){
        return;
    }

    @GetMapping("/getCivieInfo")
    public Civies getAuthordet(){

    String sql="select name as name from `sr_admins_tbl` where userid='2' ";
        Civies civies = new Civies();



    try {

        List<Civies> civiesList = this.jdbcTemplate.query(sql,new BeanPropertyRowMapper<Civies>(Civies.class));



        civies.setId(1);
        civies.setAge(26);
        civies.setOccupation("Senior Software Developer");
        civies.setName("Raghavender.k");
    }catch (Exception e){
        System.out.println("Error"+e);
    }
        return civies;
    }




}

package com.mandiri.tokonyadia.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class Hello {

//PATH
    @RequestMapping(value="/Hello", method = RequestMethod.GET)
    public String index(){
        return "Hello Merin's World";
    }
    ////PATH PARAMETER ATAU PATH VARIABLE
    @GetMapping("/pathVar/{var}")
    public String pathVar(@PathVariable String var)
    {
        return "This is Path Variable: "+var;
    }
    //QUERY PARAMETER /REQ PARAM
    @GetMapping("/query-parameter")
    public String queryPar(@RequestParam String var){
        return "Query Parameter: "+var;
    }

    //RESQUEST BODY
    @PostMapping("/req-body")
    public String reqBody(@RequestBody HashMap<String, String> mapBody)
    {
        return "request body: "+mapBody;
    }
}


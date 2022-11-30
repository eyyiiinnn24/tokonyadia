package com.mandiri.tokonyadia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
public class Hello {

//PATH
    @RequestMapping(value="/Hello", method = RequestMethod.GET)
    public String index(){
        return "Hello Merin's World";
    }
    ////PATH PARAMETER ATAU PATH VARIABLE
    @GetMapping("test-logging")
    public String exampleLogging(){
        log.info("Log Info");
        log.warn("Log Warning");
        log.error("Log error");
        for (int i = 0; i < 100 ; i++) {
            log.warn("is that rolling ?");
        }

        return "Run Logging";
    }
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


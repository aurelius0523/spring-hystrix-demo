package com.aurelius.spring.hystrix.demo.controller;


import com.aurelius.spring.hystrix.demo.facade.LuckyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LuckyController {

    @Autowired
    private LuckyFacade luckyFacade;

    @GetMapping("/numbers")
    public String getLuckyNumber() {
        return luckyFacade.getLuckyNumber();
    }

    @GetMapping("/names")
    public String getLuckyName() {
        return luckyFacade.getLuckyName();
    }

    @GetMapping("/groups/one")
    public String getFirstGroupedCommandString() {
        return luckyFacade.getGroupedStringOne();
    }

    @GetMapping("/groups/two")
    public String getSecondGroupedCommandString() {
        return luckyFacade.getGroupedStringTwo();
    }
}


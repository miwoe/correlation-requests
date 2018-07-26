package de.miwoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mwoelm on 18.09.17.
 */
@Controller
public class InnerRequestController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/bar", method = RequestMethod.GET)
    @ResponseBody
    public String bar() {
        return restTemplate.getForObject("http://localhost:8080/42", String.class);
    }

    @RequestMapping(value = "/42", method = RequestMethod.GET)
    @ResponseBody
    public String fourtytwo() {
        return "foo->bar->42";
    }
}

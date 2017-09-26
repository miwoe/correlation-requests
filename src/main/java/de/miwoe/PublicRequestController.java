package de.miwoe;

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
public class PublicRequestController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/foo", method = RequestMethod.GET)
    @ResponseBody
    public String foo() {
        return restTemplate.getForObject("http://localhost:8080/bar", String.class);
    }
}

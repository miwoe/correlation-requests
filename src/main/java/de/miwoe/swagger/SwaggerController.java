package de.miwoe.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mwoelm on 19.09.17.
 */
@RestController
public class SwaggerController {

    @ApiOperation(value = "This will get some useless **things**.")
    @RequestMapping(value = "/swagger/{pathparam}/getsome", method = RequestMethod.GET)
    public SwaggerResource getSome(@RequestParam(value = "foo", required = false) String foo, @PathVariable("pathparam") String abc, @RequestParam("bool") Boolean bar) {
        return SwaggerResource.builder().build();
    }
}

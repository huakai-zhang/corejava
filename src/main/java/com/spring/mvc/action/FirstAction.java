package com.spring.mvc.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/web")
public class FirstAction {

    @RequestMapping("action/{url}/xxx.json")
    public ModelAndView action(@RequestParam("name") String name,
                               @PathVariable("url") String url) {
        return null;
    }

}

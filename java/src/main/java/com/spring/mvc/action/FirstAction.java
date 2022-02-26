package com.spring.mvc.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/web")
public class FirstAction {

    @RequestMapping("action/{url}/xxx.json")
    public ModelAndView action(@RequestParam("name") String name,
                               @PathVariable("url") String url) {
        return null;
    }

    @RequestMapping("/jvm-info")
    @ResponseBody
    public String JvmInfo() {
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        StringBuffer sb = new StringBuffer();
        for (GarbageCollectorMXBean b : l) {
            sb.append(b.getName() + "\n");
        }
        return sb.toString();
    }

    @RequestMapping("/jvm-error")
    public void JvmError() throws InterruptedException {
        for (int i =0; i < 1000; ++i) {
            if (i == 0) {
                Thread.sleep(500L);
                System.out.println("start = [" + new Date() + "]");
            } else {
                Thread.sleep(4000L);
            }
        }
    }
}

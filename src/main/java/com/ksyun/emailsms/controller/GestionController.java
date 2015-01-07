package com.ksyun.emailsms.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 管理页面入口
 */
@Controller
public class GestionController {
	
    @RequestMapping("/g/")
    public String adminIndex(ModelAndView mav) throws IOException {
        return "/emailsms/index";
    }
}

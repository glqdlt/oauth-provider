package com.glqdlt.oauthprovider.webapp;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class WebPages {

    @GetMapping(name = "/")
    public ModelAndView root(ModelAndView modelAndView) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        modelAndView.setViewName("index");
        Map<String, Object> model = modelAndView.getModel();
        model.put("userName", userName);

        return modelAndView;
    }
}

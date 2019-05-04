package com.glqdlt.oauthprovider.webapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ActiveProfiles(profiles = "test")
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WebPages.class)
public class WebAppApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @WithMockUser(username = "test_man", password = "testPassword1234", roles = "ADMIN")
    @Test
    public void root() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(r -> Assert.assertEquals("index.html 말고 다른 페이지가 로드 됨", "index", r.getModelAndView().getViewName()))
                .andExpect(r -> Assert.assertEquals(200, r.getResponse().getStatus()))
                .andExpect(r -> Assert.assertEquals("test_man",r.getModelAndView().getModel().get("userName")))
                .andDo(r -> System.out.println(r.getResponse().getContentAsString()));
    }
}

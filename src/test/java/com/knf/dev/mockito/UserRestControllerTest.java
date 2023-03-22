package com.knf.dev.mockito;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knf.dev.mockito.controller.UserRestController;
import com.knf.dev.mockito.entity.User;
import com.knf.dev.mockito.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUserByName()  throws Exception{
        User user =  new User();
        user.setName("jadu");
        user.setId(4);
        when(userService.getUserByName("jadu")).thenReturn(user);

        mvc.perform(get("/api/user/jadu").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("jadu"));
    }

    @Test
    public void saveUser() throws Exception{
        User user = new User();
        user.setName("jadu");
        user.setId(4);
        when(userService.saveUser(user)).thenReturn(user);

        mvc.perform(post("/api/user").content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }

    @Test
    public void getAllUsers()throws  Exception{
        User user =  new User();
        user.setName("jadu");
        user.setId(4);
        User user1 = new User();
        user1.setName("jadu1");
        user.setId(5);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        when(userService.getAllUsers()).thenReturn(users);

        mvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    public static  String  asJsonString(final Object obj) throws JsonProcessingException{
        return new ObjectMapper().writeValueAsString(obj);
    }

}

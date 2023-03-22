package com.knf.dev.mockito;

import com.knf.dev.mockito.entity.User;
import com.knf.dev.mockito.repository.UserRepository;
import com.knf.dev.mockito.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        User user = new User();
        user.setName("danny boy");
        user.setId(1);
        User user1 =  new User();
        user1.setName("alpha");
        user1.setId(2);
        when(userRepository.findByName(user.getName())).thenReturn(user);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        when(userRepository.findAll()).thenReturn(users);

    }

    @Test
    public void getUserByname(){
        String name = "danny boy";
        User user = userService.getUserByName(name);
        Assert.assertEquals(user.getName(),name);
    }
    @Test
    public void saveUser(){
        User user = new User();
        user.setId(2);
        user.setName("dummy user");
        userService.saveUser(user);
        verify(userRepository,times(1)).save(user);
    }
    @Test
    public void getAllUsers(){
        List<User> users = userService.getAllUsers();
        Assert.assertEquals(users.size(),2);
    }


}

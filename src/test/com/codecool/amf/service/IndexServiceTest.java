package com.codecool.amf.service;

import com.codecool.amf.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexServiceTest {

    @Autowired
    IndexService indexService;

    @Mock
    private
    HttpSession session;

    @Mock
    private
    User user;

    @Test
    public void testIfUserNotLoggedInRedirectToLoginPage() {
        when(session.getAttribute("user")).thenReturn(null);
        assertEquals("login", indexService.handleIndexGet(session));
    }

    @Test
    public void testIfUserLoggedInRedirectToIndexPage() {
        when(session.getAttribute("user")).thenReturn(user);
        assertEquals("index", indexService.handleIndexGet(session));
    }
}
package com.quiz.endpoints;

import com.quiz.controllers.LoginController;
import com.quiz.db.DbManager;
import com.quiz.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginEndpointTest {


    private static String MAIL = "user@email.com";
    private static String PASS = "xyzmondieu";
    private static String LOGIN = "user";
    private static String COOKIE_NAME = "authToken";
    private static String COOKIE_CONTENT = "abc";

    private User USER_1;
    private LoginEndpoint loginEndpoint;
    private LoginController loginController;
    private NewCookie cookie;

    @Mock
    private DbManager<User> dbManager;


    @BeforeEach
    void setUp() throws Exception {
        USER_1 = new User(LOGIN, MAIL, PASS);
        cookie = new NewCookie(COOKIE_NAME, COOKIE_CONTENT);
        loginController = spy(new LoginController(dbManager));
        loginEndpoint = new LoginEndpoint(loginController);

        doNothing().when(loginController).register(USER_1);
        doReturn(cookie).when(loginController).login(USER_1);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void when_requestIsProcessed_then_controllerIsUsedOnce() throws Exception {
        Response response = loginEndpoint.register(USER_1);
        assertNotNull(response);
        verify(loginController, times(1)).register(USER_1);
        response = loginEndpoint.login(USER_1);
        assertNotNull(response);
        verify(loginController, times(1)).login(USER_1);
    }

    @Test
    void when_registrationDataIsCorrect_then_userShouldGetResponse200() {
        Response response = loginEndpoint.register(USER_1);
        assertNotNull(response);
        assertEquals(200, response.getStatus());
    }

    @Test
    void when_loginDataIsCorrect_then_userShouldGetResponse200AndCookie() {
        Response response = loginEndpoint.login(USER_1);
        assertNotNull(response);
        assertEquals(200, response.getStatus());
        Cookie cookie = response.getCookies().get(COOKIE_NAME);
        assertEquals(COOKIE_CONTENT, cookie.getValue());
    }

    /*
    TODO: Unauthorized, Duplicated email, Server error + Login Controller
     */

}
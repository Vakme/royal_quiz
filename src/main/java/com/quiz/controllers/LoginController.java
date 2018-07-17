package com.quiz.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.quiz.db.DbManager;
import com.quiz.models.User;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.management.Query;
import javax.validation.ValidationException;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import java.util.*;


public class LoginController {

    private DbManager<User> db;

    public LoginController() {
        db = new DbManager<>();
    }

    public void register(User user) {
        if(validateEmailAddress(user.getEmail())) {
            db.insertSingle(user);
        }
        else {
            throw new ValidationException();
        }
    }

    public NewCookie login(User user) throws Exception {
        String username = authenticate(user);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.setTimeInMillis(System.currentTimeMillis());
        expirationDate.add(Calendar.MONTH, 1);
        String token = createToken(username, expirationDate.getTime());
        return new NewCookie("authToken", token);
    }

    private String authenticate(User user) throws Exception {
        List<User> users = db.findEqualByParam(User.class, new AbstractMap.SimpleEntry<>("email", String.class), user.getEmail());
        if(!users.get(0).getPassword().equals(user.getPassword())) {
            throw new ValidationException();
        }
        return users.get(0).getLogin();
    }

    private String createToken(String subject, Date expirationDate) {
        Date currentTime = new Date(System.currentTimeMillis());
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                    .withSubject(subject)
                    .withIssuedAt(currentTime)
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims. TODO: logger
        }
        return token;
    }

    private boolean validateEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

}

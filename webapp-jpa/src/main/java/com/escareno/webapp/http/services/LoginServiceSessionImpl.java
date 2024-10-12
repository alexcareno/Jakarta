package com.escareno.webapp.http.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSessionImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession();
        String username = (String) session.getAttribute("username");
        if(username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}

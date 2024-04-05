package com.amex.useremailservice.service;

import com.amex.useremailservice.model.User;


public interface UserService {
    User saveUser(User user);
    Boolean verifyToken(String token);
}

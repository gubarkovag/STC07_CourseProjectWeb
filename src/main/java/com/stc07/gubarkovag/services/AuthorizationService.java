package com.stc07.gubarkovag.services;

import com.stc07.gubarkovag.pojo.User;

import java.util.Map;

public interface AuthorizationService {
    Map<Boolean, User> auth(String login, String password);
}

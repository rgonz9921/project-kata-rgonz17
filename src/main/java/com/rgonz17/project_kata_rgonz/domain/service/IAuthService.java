package com.rgonz17.project_kata_rgonz.domain.service;

import java.util.Map;

public interface IAuthService {
    Map<String, String> login(String email, String password);
}

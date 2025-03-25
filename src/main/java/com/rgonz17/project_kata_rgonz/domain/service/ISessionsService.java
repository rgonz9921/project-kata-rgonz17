package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.domain.model.Sessions;

import java.util.Optional;

public interface ISessionsService {

    Sessions refresh(Sessions sessionService);

    Optional<Sessions> findByEmail(String jwt);
}

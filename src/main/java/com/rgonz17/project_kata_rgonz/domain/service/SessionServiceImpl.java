package com.rgonz17.project_kata_rgonz.domain.service;

import com.rgonz17.project_kata_rgonz.domain.model.Sessions;
import com.rgonz17.project_kata_rgonz.infraestructure.persistence.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionServiceImpl implements ISessionsService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Sessions refresh(Sessions updateSession) {
        return sessionRepository.findByUserEmail(updateSession.getUserEmail())
                .map(session -> {
                    session.setJwt(updateSession.getJwt());
                    return sessionRepository.save(session);
                })
                .orElseGet(() -> {
                    return sessionRepository.save(updateSession);
                });
    }

    @Override
    public Optional<Sessions> findByEmail(String email) {
        return sessionRepository.findByUserEmail(email);
    }
}

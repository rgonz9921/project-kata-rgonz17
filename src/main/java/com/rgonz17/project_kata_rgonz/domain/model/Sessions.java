package com.rgonz17.project_kata_rgonz.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "sessions")
public class Sessions {
    @Id
    private String _id;

    @Field("user_email")
    private String userEmail;

    private String jwt;

    public Sessions() {
    }

    public Sessions( String userEmail, String jwt) {
        this.userEmail = userEmail;
        this.jwt = jwt;
    }

    public Sessions(String _id, String user_email, String jwt) {
        this._id = _id;
        this.userEmail = user_email;
        this.jwt = jwt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}

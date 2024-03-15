package com.example.demo.dao.model;

import com.example.demo.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name="USER")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String objectId;
    private String login;
    private String fio;
    private UserStatus status;

    public User() {
        //empty
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID")
    @Column(name="OBJECT_ID")
    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Column(name="LOGIN",nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name="FIO")
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS",nullable = false)
    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(objectId, that.objectId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return prime* result + (objectId == null ? 0 : objectId.hashCode());
    }

    @Override
    public String toString() {
        return "User{" +
                "objectId='" + objectId + '\'' +
                ", login='" + login + '\'' +
                ", fio='" + fio + '\'' +
                ", status=" + status +
                '}';
    }
}
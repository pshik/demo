package com.example.demo.dao.model;

import com.example.demo.enums.RequestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name="REQUEST")
public class Request  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String objectId;
    private String name;
    private String description;
    private User user;
    private RequestStatus status;

    public Request() {
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
    @Column(name="NAME",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne
    @JoinColumn(name="USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS",nullable = false)
    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request that = (Request) o;
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
        return "Request{" +
                "objectId='" + objectId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", status=" + status +
                '}';
    }
}

package com.example.eat4u.backend.db.entities;

public class UserEntity {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String phone;

    private final String avatar;

    public UserEntity(Long id, String firstname, String lastname, String email, String phone, String avatar) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar() {
        return avatar;
    }
}

package com.example.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Notification {
    @Id
    public Long id;
    public String title;
    public Date date;
}

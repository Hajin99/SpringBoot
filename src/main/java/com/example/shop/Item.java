package com.example.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor; //기본값 써야하면
import lombok.Setter;

@Entity
@Setter
@Getter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer price;

    @Override
    public String toString() {
        return this.title+" "+this.price;
    }
}

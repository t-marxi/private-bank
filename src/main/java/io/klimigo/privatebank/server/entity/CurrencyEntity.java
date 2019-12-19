package io.klimigo.privatebank.server.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "currency")
@Data
public class CurrencyEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String description;
}

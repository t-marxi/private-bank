package io.klimigo.privatebank.server.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
@Data
public class ClientEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String secondName;

    @Column
    private String lastName;

}

package io.klimigo.privatebank.server.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClientOverview {
    private Long id;
    private String name;
    private String secondName;
    private String lastName;
}

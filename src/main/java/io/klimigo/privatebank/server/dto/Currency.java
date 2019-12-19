package io.klimigo.privatebank.server.dto;

import lombok.Data;


@Data
public class Currency {
    private Long id;
    private String code;
    private String description;
}

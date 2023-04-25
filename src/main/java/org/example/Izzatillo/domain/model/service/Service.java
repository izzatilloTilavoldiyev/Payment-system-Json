package org.example.Izzatillo.domain.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Service {
    private Boolean activate;
    private String name;
    private ServiceStatus type;
    private Float commission;
}

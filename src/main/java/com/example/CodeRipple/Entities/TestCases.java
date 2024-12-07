package com.example.CodeRipple.Entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class TestCases {
    private String input;
    private String output ;
}

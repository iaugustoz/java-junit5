package br.com.iaugusto.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Transaction {
    private Long id;
    private String description;
    private Double value;
    private Account account;
    private LocalDate date;
    private Boolean status;

}

package com.opaywallet.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String walletId;

    private double walletBalance;

    @Column(unique=true)
    @NotNull(message = "Phone number cannot be null")
    @Size(max = 11, message = "Phone number cannot be more then eleven characters")
    private String customerPhoneNumber;

    private boolean enabled = true;
}

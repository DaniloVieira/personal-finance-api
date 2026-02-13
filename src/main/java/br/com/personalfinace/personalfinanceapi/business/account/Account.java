package br.com.personalfinace.personalfinanceapi.business.account;

import br.com.personalfinace.personalfinanceapi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "pfi_account")
public class Account  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Long id;

    @Column(name = "nm_account")
    private String name;

    @Column(name = "vl_initial_funds", nullable = false)
    private BigDecimal initialFunds = BigDecimal.ZERO;

}
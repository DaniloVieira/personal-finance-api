package br.com.personalfinace.personalfinanceapi.business.transaction;

import br.com.personalfinace.personalfinanceapi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pfi_transfer_group")
public class TransferGroup  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transfer_group")
    private Long id;

    @OneToMany(mappedBy = "transferGroup")
    private List<Transaction> transactions; // always exactly 2
}
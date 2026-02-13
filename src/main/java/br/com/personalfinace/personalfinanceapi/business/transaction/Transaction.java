package br.com.personalfinace.personalfinanceapi.business.transaction;

import br.com.personalfinace.personalfinanceapi.business.account.Account;
import br.com.personalfinace.personalfinanceapi.business.tag.Tag;
import br.com.personalfinace.personalfinanceapi.business.transaction.enums.TransactionType;
import br.com.personalfinace.personalfinanceapi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pfi_transaction")
public class Transaction  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction", nullable = false)
    private Long id;

    @Column(name = "vl_amount", nullable = false)
    private BigDecimal amount;              // positive = in, negative = out

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_transaction_type", nullable = false)
    private TransactionType type;           // INCOME, EXPENSE, TRANSFER

    @Column(name = "ds_transaction", nullable = false)
    private String description;

    @Column(name = "dt_transaction", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_transaction")
    private Transaction parentTransaction;  // for splits

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_transfer_group")
    private TransferGroup transferGroup;    // links both sides

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "PFI_TRANSACTION_TAG",
            joinColumns = @JoinColumn(name = "fk_transaction"),
            inverseJoinColumns = @JoinColumn(name = "fk_tag")
    )
    private Set<Tag> tags = new HashSet<>();

    public void addTags(Set<Tag> tags) {
        this.tags.addAll(tags);
    }
}

package br.com.personalfinace.personalfinanceapi.business.tag;

import br.com.personalfinace.personalfinanceapi.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="pfi_tag")
public class Tag  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tag")
    private Long id;

    @Column(name="ds_name")
    private String name;

    @Column(name="ds_primary_hex_color")
    private String primaryHexColor;

    @Column(name="ds_secondary_hex_color")
    private String secondaryHexColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_parent_tag")
    private Tag parent;

    @OneToMany(mappedBy = "parent")
    private List<Tag> children = new ArrayList<>();

}

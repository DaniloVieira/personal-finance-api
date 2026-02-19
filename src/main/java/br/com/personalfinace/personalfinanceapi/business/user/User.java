package br.com.personalfinace.personalfinanceapi.business.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "pfi_user")
public class User {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "ds_name", nullable = false)
    private String name;

    @Column(name = "ds_email", nullable = false)
    private String email;

    // No setters, no password field — this is read-only
}

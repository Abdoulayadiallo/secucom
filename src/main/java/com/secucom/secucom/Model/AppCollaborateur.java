package com.secucom.secucom.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppCollaborateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String nom;
    private String prenom;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AppCollaborateur_appRole",
            joinColumns = @JoinColumn(name = "AppCollaborateur_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "appRole_id", referencedColumnName = "id"))
    private Collection<AppRole> appRoles = new java.util.ArrayList<>();


}

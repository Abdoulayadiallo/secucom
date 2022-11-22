package com.secucom.secucom.Repository;

import com.secucom.secucom.Model.AppCollaborateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppCollaborateurRepository extends JpaRepository<AppCollaborateur,Long> {
    AppCollaborateur findByUsername(String username);
}

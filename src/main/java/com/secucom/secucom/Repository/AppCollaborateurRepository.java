package com.secucom.secucom.Repository;

import com.secucom.secucom.Model.AppCollaborateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppCollaborateurRepository extends JpaRepository<AppCollaborateur,Long> {
    @Query("select (count(a) > 0) from AppCollaborateur a")
    boolean existsByUsername(String username);
    AppCollaborateur findByUsername(String username);
}

package com.secucom.secucom.Repository;

import com.secucom.secucom.Model.AppCollaborateur;
import com.secucom.secucom.Model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}

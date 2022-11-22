package com.secucom.secucom.Service;

import com.secucom.secucom.Model.AppCollaborateur;
import com.secucom.secucom.Model.AppRole;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
    AppCollaborateur addNewCollab(AppCollaborateur appCollaborateur);
    AppRole addNewRole(AppRole appRole);
    void addRoleToCollab(String username, String roleName);
    AppCollaborateur loadCollabByUsername(String username);
    List<AppCollaborateur> listCollabs();

}

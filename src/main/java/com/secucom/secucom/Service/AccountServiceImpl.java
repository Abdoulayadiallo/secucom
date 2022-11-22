package com.secucom.secucom.Service;

import com.secucom.secucom.Model.AppCollaborateur;
import com.secucom.secucom.Model.AppRole;
import com.secucom.secucom.Repository.AppCollaborateurRepository;
import com.secucom.secucom.Repository.AppRoleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppCollaborateurRepository appCollaborateurRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppCollaborateur addNewCollab(AppCollaborateur appCollaborateur) {
        String pass = appCollaborateur.getPassword();
        appCollaborateur.setPassword(passwordEncoder.encode(pass));
        return appCollaborateurRepository.save(appCollaborateur);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToCollab(String username, String roleName) {
        AppCollaborateur appCollaborateur = appCollaborateurRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        appCollaborateur.getAppRoles().add(appRole);
    }

    @Override
    public AppCollaborateur loadCollabByUsername(String username) {
        return appCollaborateurRepository.findByUsername(username);
    }

    @Override
    public List<AppCollaborateur> listCollabs() {
        return appCollaborateurRepository.findAll();
    }
}

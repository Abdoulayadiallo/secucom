package com.secucom.secucom.Service;

import com.secucom.secucom.Model.AppCollaborateur;
import com.secucom.secucom.Model.AppRole;
import com.secucom.secucom.Repository.AppCollaborateurRepository;
import com.secucom.secucom.Repository.AppRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;


@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppCollaborateurRepository appCollaborateurRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Object addNewCollab(AppCollaborateur appCollaborateur) {
        String pass = appCollaborateur.getPassword();
        appCollaborateur.setPassword(passwordEncoder.encode(pass));
        if (appCollaborateur.getUsername() != null || appCollaborateur.getPassword() != null) {
            return appCollaborateurRepository.save(appCollaborateur);
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        if(appRole.getRoleName() != null){
            return appRoleRepository.save(appRole);
        }else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return appRole;
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

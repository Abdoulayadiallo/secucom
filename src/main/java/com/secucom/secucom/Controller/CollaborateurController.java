package com.secucom.secucom.Controller;

import com.secucom.secucom.Model.AppCollaborateur;
import com.secucom.secucom.Model.AppRole;
import com.secucom.secucom.Service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class CollaborateurController {
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }
    private AccountService accountService;
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/collaborateur")
    public List<AppCollaborateur> getCollab(){
        return accountService.listCollabs();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/collaborateur")
    public AppCollaborateur addCollab(@RequestBody AppCollaborateur appCollaborateur){
        return accountService.addNewCollab(appCollaborateur);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/role")
    public AppRole addRole(@RequestBody AppRole appRole){
        return accountService.addNewRole(appRole);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/roleToUser")
    public void AddRoleToCollab(@RequestBody RoleUserForm roleUserForm){
      accountService.addRoleToCollab(roleUserForm.getUsername(), roleUserForm.getRoleName());
  }



}
@Data
class RoleUserForm{
    private String username;
    private String RoleName;

}

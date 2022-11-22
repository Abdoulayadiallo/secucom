package com.secucom.secucom.Controller;

import com.secucom.secucom.Model.AppCollaborateur;
import com.secucom.secucom.Model.AppRole;
import com.secucom.secucom.Service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CollaborateurController {
    private AccountService accountService;
    @GetMapping("/collaborateur")
    public List<AppCollaborateur> getCollab(){
        return accountService.listCollabs();
    }
    @PostMapping("/collaborateur")
    public AppCollaborateur addCollab(@RequestBody AppCollaborateur appCollaborateur){
        return accountService.addNewCollab(appCollaborateur);
    }
    @PostMapping("/role")
    public AppRole addRole(@RequestBody AppRole appRole){
        return accountService.addNewRole(appRole);
    }
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

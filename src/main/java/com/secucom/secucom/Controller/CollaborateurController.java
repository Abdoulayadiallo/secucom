package com.secucom.secucom.Controller;

import com.secucom.secucom.Model.AppCollaborateur;
import com.secucom.secucom.Service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Collaborateur")
public class CollaborateurController {
    private AccountService accountService;
    @GetMapping("")
    public List<AppCollaborateur> getCollab(AppCollaborateur appCollaborateur){
        return accountService.listCollabs();
    }

}

package org.example.smarthealthcareappointmentsystem.controller;

import org.example.smarthealthcareappointmentsystem.dto.RoleDTO;
import org.example.smarthealthcareappointmentsystem.service.Impl.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    private RoleServiceImp roleServiceImp;

    @PostMapping("/")
    public ResponseEntity addRole(@RequestBody RoleDTO roleDTO){
        System.out.println("IN");
        return ResponseEntity.ok(this.roleServiceImp.addRole(roleDTO));
    }
}

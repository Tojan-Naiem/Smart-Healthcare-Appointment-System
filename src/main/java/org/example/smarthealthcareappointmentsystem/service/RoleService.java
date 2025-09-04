package org.example.smarthealthcareappointmentsystem.service;

import org.example.smarthealthcareappointmentsystem.dto.RoleDTO;
import org.example.smarthealthcareappointmentsystem.model.Role;
import org.example.smarthealthcareappointmentsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Role addRole(RoleDTO roleDTO){
        Role role=new Role();
        role.setName(roleDTO.getRoleName());
        System.out.println("IN@");
        return this.roleRepository.save(role);
    }
}

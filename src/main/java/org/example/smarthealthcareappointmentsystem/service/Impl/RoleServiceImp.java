package org.example.smarthealthcareappointmentsystem.service.Impl;

import org.example.smarthealthcareappointmentsystem.dto.RoleDTO;
import org.example.smarthealthcareappointmentsystem.entity.Role;
import org.example.smarthealthcareappointmentsystem.repository.RoleRepository;
import org.example.smarthealthcareappointmentsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public Role addRole(RoleDTO roleDTO){
        Role role=new Role();
        role.setName(roleDTO.getRoleName());
        Role newRole=this.roleRepository.save(role);
        return newRole;
    }
}

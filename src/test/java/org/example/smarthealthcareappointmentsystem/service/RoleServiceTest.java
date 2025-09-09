package org.example.smarthealthcareappointmentsystem.service;

import org.example.smarthealthcareappointmentsystem.dto.RoleDTO;
import org.example.smarthealthcareappointmentsystem.entity.Role;
import org.example.smarthealthcareappointmentsystem.repository.RoleRepository;
import org.example.smarthealthcareappointmentsystem.service.Imp.RoleServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class RoleServiceTest {
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private RoleServiceImp roleServiceImp;

    @Test
    public void addRole_returnCorrect(){
        RoleDTO roleDTO=new RoleDTO();
        roleDTO.setRoleName("ADMIN");
        Role savedRole=new Role();
        savedRole.setId(1);
        savedRole.setName(roleDTO.getRoleName());
        when(roleRepository.save(any(Role.class))).thenReturn(savedRole);

        Role newRole=this.roleServiceImp.addRole(roleDTO);
        assertEquals(newRole.getName(),savedRole.getName());
        verify(this.roleRepository).save(any(Role.class));

    }

}
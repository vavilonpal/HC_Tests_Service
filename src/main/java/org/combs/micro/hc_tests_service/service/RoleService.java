package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Role;
import org.combs.micro.hc_tests_service.enums.RoleType;
import org.combs.micro.hc_tests_service.exeptions.notFound.RoleNotFoundException;
import org.combs.micro.hc_tests_service.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;


    public Role getRoleByRoleType(RoleType roleType) {
        System.out.println(roleType);
        String roleName = roleType.toString();
        System.out.println(roleName);
        return roleRepository.findRoleByName(roleType)
                .orElseThrow(() -> new RoleNotFoundException("Role not found by name" + roleName));
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(()-> new RoleNotFoundException("Role not found"));
    }
   /* public Role getRoleByName(String roleName){
        return  roleRepository.findRoleByName(roleName)
                .orElseThrow(()-> new RoleNotFoundException("Role by name:"+roleName+" not found"));
    }*/


    //todo Реилизация на потом
    //public Role createRole(Role role) {}

    //public Role updateRole(Long id, Role user) {}

    // public void deleteRole(Role user) {}
}
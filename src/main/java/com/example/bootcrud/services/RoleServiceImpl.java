package com.example.bootcrud.services;

import com.example.bootcrud.entities.Role;
import com.example.bootcrud.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;

@Component
@Transactional
public class RoleServiceImpl implements RoleServce {

    @Autowired
    private RoleRepo roleRepo;

    public RoleRepo getRoleDao() {
        return roleRepo;
    }

    @Override
    public Role findByname(String rolename) {
        return roleRepo.findByname(rolename);
    }

    @Override
    public HashSet<Role> index() {
        return new HashSet<>(roleRepo.findAll());
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    @Override
    public Role read(int id) {
        return roleRepo.getById(id);
    }

    @Override
    public void update(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void delete(int id) {
        roleRepo.deleteById(id);
    }

    public HashSet<Role> getRoleSet(String[] roles) {
        HashSet<Role> resSet = new HashSet<>();
        for( String roleId: roles) {
            resSet.add(roleRepo.getById(Integer.parseInt(roleId)));
        }
        return resSet;
    }

}

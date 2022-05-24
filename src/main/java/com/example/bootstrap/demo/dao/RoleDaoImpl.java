package com.example.bootstrap.demo.dao;

import com.example.bootstrap.demo.entity.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getAllRoles() {
        List<Role> roleList = entityManager.createQuery("select r from Role r ", Role.class).getResultList();
        return new HashSet<>(roleList);
    }

    @Override
    public Role getRoleByName(String role) {
        return entityManager.createQuery(
                "SELECT r from Role r where r.name=:role", Role.class
        ).setParameter("role", role).getSingleResult();
    }

    @Override
    public Set<Role> getSetOfRoles(String[] roleNames) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roleNames) {
            roleSet.add(getRoleByName(role));
        }
        return roleSet;
    }

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void edit(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role getById(int id) {
        return entityManager.find(Role.class, id);
    }
}
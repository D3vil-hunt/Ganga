package com.ganga.adminservice.Repository;

import com.ganga.adminservice.entity.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin,Long> {
    Admin findByUsername(String username);
}

package com.lxisoft.repository;

import com.lxisoft.domain.User;
import com.lxisoft.domain.UserExtra;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserExtra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtraRepository extends JpaRepository<UserExtra, Long> {



	
}

package com.swe.salfny.classes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, Long> {

}
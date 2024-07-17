package com.ead.authuser.repositories;

import com.ead.authuser.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM UserModel u JOIN u.usersCourses uc WHERE uc.courseId = ?1")
    Page<UserModel> findUsersByCourseId(UUID courseId, Pageable pageable);
}

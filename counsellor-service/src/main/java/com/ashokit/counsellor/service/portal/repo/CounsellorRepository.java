package com.ashokit.counsellor.service.portal.repo;

import com.ashokit.counsellor.service.portal.entity.CounsellorEntity;
import com.ashokit.counsellor.service.portal.entity.EnquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CounsellorRepository extends JpaRepository<CounsellorEntity, Integer> {
    public Optional<CounsellorEntity> findByEmail(String email);
    @Query("select id from CounsellorEntity")
    public List<Integer> getAllCounsellorNumber();



}

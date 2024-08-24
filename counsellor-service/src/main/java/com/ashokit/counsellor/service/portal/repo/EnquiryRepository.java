package com.ashokit.counsellor.service.portal.repo;

import com.ashokit.counsellor.service.portal.entity.EnquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnquiryRepository extends JpaRepository<EnquiryEntity, Integer> {
    @Query(value = "select * from enquires where counsellor_id=:counsellor_id",nativeQuery = true)
    public List<EnquiryEntity> getEnquiresByCounsellorId(Integer counsellor_id);
}

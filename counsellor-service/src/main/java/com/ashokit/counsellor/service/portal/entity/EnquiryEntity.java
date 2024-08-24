package com.ashokit.counsellor.service.portal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "enquires")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnquiryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "student_phno",unique = true)
    private String studentPhoneNumber;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "class_mode")
    private String classMode;
    @Column(name = "enq_status")
    private String inquiriesStatus;
    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDate createdAt;
    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDate updatedAt;
    @ManyToOne
    @JoinColumn(name = "counsellor_id")
    private CounsellorEntity counsellor;

}

package com.ashokit.counsellor.service.portal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "counsellor_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CounsellorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer counsellor_id;
    @Column(name = "counsellor_name")
    private String name;
    @Column(name = "counsellor_email",unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number",unique = true)
    private String phoneNumber;
    @Column(name = "create_at")
    @CreationTimestamp
    private LocalDate createdAt;
    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDate updatedAt;

}

package com.ead.authuser.models;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @Column(unique = true, nullable = false, length = 50)
    String userName;
    @Column(unique = true, nullable = false, length = 50)
    String email;
    @JsonIgnore
    @Column(nullable = false, length = 50)
    String password;
    @Column(nullable = false, length = 50)
    String fullName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserStatus status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserType type;
    @Column(length = 11)
    String phoneNumber;
    @Column(length = 11, nullable = false, unique = true)
    String cpf;
    String imageUrl;
    @CreationTimestamp
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    LocalDateTime updatedAt;
}
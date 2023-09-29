package com.api.usersintegration.model;

import com.api.usersintegration.enums.ProfileEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="profiles")
public class Profile {
    @GeneratedValue
    @Id
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ProfileEnum name;
    
}

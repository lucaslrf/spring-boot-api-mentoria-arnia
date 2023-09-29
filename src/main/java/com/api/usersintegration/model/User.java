package com.api.usersintegration.model;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @GeneratedValue
    @Id
    private Long id;

    @Setter
    @Column
    private String name;

    @Setter
    @Column
    private String login;

    @Setter
    @Column
    private String email;

    @Setter
    @NotBlank
    @Size(max = 120)
    @Column
    private String password;

    @Setter
    @ManyToMany
    @JoinTable(name="users_profiles",
        joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name="profile_id", referencedColumnName = "id")
    )
    private Set<Profile> profiles;

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

}

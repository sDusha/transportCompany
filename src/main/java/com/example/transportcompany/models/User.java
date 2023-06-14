package com.example.transportcompany.models;

import com.example.transportcompany.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @Column(unique = true)
        private String email;

        @Column(unique = true)
        private String phoneNumber;
        private boolean active;
        private String Image; // просто ссылка в resources

        private String information;

        @Column(length = 1000)
        private String password;

        @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER )
        @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
        @Enumerated(EnumType.STRING)
        private Set<Role> roles = new HashSet<>();

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
        private List<Contract> contracts = new ArrayList<>();

        private LocalDateTime dateOfCreate;

        @PrePersist
        private void init(){
                dateOfCreate = LocalDateTime.now();
        }

        //security

        public boolean isAdmin(){
                return roles.contains(Role.ROLE_ADMIN);
        }
        public boolean isOwner(){
                return roles.contains(Role.ROLE_OWNER);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return roles;
        }

        @Override
        public String getUsername() {
                return email;
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return active;
        }
}
package com.thaleszz.challenge_contabilizei.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thaleszz.challenge_contabilizei.dto.models.UserDTO;
import com.thaleszz.challenge_contabilizei.models.client.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private Client client;

    public User(UserDTO data) {
        BeanUtils.copyProperties(data, this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roleAuthorities = new ArrayList<>();
        roleAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (this.role.equals(UserRole.ADMIN)) roleAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return roleAuthorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
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
        return true;
    }
}

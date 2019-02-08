package com.demo.microservices.gatewayservice.model.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class UserPrincipal implements OAuth2User, UserDetails {
	
	@NonNull
	private Long id;
	@NonNull
	private String username;
	@NonNull
	private String name;
	private String email;
	
	@NonNull
	@JsonIgnore
	private String password;
    
	@NonNull
    private Collection<? extends GrantedAuthority> authorities;
    
    private Map<String, Object> attributes;
    
    public static UserPrincipal create(User user) {
    	List<GrantedAuthority> authorities = user.getRoles().stream().map(
    			role -> new SimpleGrantedAuthority(role.getAuthority())
		).collect(Collectors.toList());
    	
    	return new UserPrincipal(
	    			user.getId(),
	    			user.getUsername(),
	    			user.getLastName() + " " + user.getFirstName(),
	    			user.getPassword(),
	    			authorities
    			);
    }
    
    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
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

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.mahaboob.currencyconverter.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "m_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User implements UserDetails, Serializable {
	
	private static final long serialVersionUID = -4334350608165122428L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
	
	@Column(name = "role_id", nullable = false)
    private Long roleId;
	
    @Column(name = "account_expired", nullable = false)
    private boolean accountExpired;
    
    @Column(name = "account_locked", nullable = false)
    private boolean accountLocked;
    
    @Column(name = "credentials_expired", nullable = false)
    private boolean credentialsExpired;

    @Column(name = "base_user", nullable = false)
    private boolean baseUser;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;
    
    @Column(name = "created_date", nullable = false)
    private Date createdDate;
    
    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;
    
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "m_user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "authority_id"))
    @OrderBy
    @JsonIgnore
    private Collection<Authority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return !isAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public boolean isBaseUser() {
		return baseUser;
	}

	public void setBaseUser(boolean baseUser) {
		this.baseUser = baseUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
       
}
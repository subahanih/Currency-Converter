package com.mahaboob.currencyconverter.domain;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "m_authority", uniqueConstraints = {@UniqueConstraint(columnNames = {"authority_name"})})
public class Authority implements GrantedAuthority, Serializable {
	
	private static final long serialVersionUID = 3517371284896804314L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authority_id")
    private Long authorityId;

    @Column(name = "authority_name")
    private String authorityName;

    @Override
    public String getAuthority() {
        return getName();
    }

	public Long getId() {
		return authorityId;
	}

	public void setId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public String getName() {
		return authorityName;
	}

	public void setName(String authorityName) {
		this.authorityName = authorityName;
	}    
    
}

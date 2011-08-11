package org.rimasu.cloister.common.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;

import org.eclipse.persistence.annotations.Index;
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class Principal extends AbstractEntity {

	private String username;

	private String passwordHash;

	private Member member;

	private List<Role> roles;

	public Principal() {
	}

	public Principal(String uuid) {
		super(uuid);
	}

	@NotNull
	@Index
	@Size(min = 4)
	@Column(unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotNull
	@Column(nullable = false)
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@XmlIDREF
	@OneToOne
	@NotNull
	@Column(nullable = false)
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@XmlElementWrapper(name = "roles")
	@XmlElement(name = "role")
	@ElementCollection
	@CollectionTable(name = "PRINCIPAL_ROLES", joinColumns = @JoinColumn(name = "PRINCIPAL_ID"))
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 
	 * @param clearTextPassword
	 */
	public void generatePasswordHash(String clearTextPassword) {
		setPasswordHash(BCrypt.hashpw(clearTextPassword, BCrypt.gensalt()));
	}

	/**
	 * 
	 * @param checkPassword
	 */
	public boolean checkPassword(String clearTextCandidate) {
		return BCrypt.checkpw(clearTextCandidate, passwordHash);
	}
}

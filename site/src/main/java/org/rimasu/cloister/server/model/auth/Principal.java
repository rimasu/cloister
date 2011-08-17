package org.rimasu.cloister.server.model.auth;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;

import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name="PRINCIPALS")
public class Principal {

	private String username;

	private String passwordHash;

	private List<Role> roles;

	public Principal() {
		roles = new ArrayList<Role>();
	}

	@Id
	@XmlID
	@XmlAttribute
	@NotNull
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

	/**
	 * 
	 * @param clearTextPassword
	 */
	public void setPasswordHashFromPassword(String clearTextPassword) {

		setPasswordHash(BCrypt.hashpw(clearTextPassword, BCrypt.gensalt()));
	}

	/**
	 * 
	 * @param checkPassword
	 */
	public boolean checkPassword(String clearTextCandidate) {
		return BCrypt.checkpw(clearTextCandidate, passwordHash);
	}

	@XmlElementWrapper(name = "roles")
	@XmlElement(name = "Role")
	@ElementCollection
	@CollectionTable(name = "PRINCIPAL_ROLES", joinColumns = @JoinColumn(name = "PRINCIPAL_ID"))
	@Enumerated(EnumType.STRING)
	@Column(name="ROLE")
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public static Principal find(String username) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<Principal> findAll(EntityManager manager) {
		Query query = manager.createQuery("SELECT e FROM Principal e");
		return (List<Principal>) query.getResultList();
	}
}

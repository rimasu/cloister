package org.rimasu.cloister.server.model.auth;

import java.util.ArrayList;
import java.util.List;

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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Security Principal Persistent Entity. A principal is responsible for storing
 * connection credentials (username + passwordHash) and authorisation mapping
 * (roles).
 */
@Entity
@Table(name = "PRINCIPALS")
public class Principal {

	/**
	 * Username (id for this entity).
	 */
	private String username;

	/**
	 * Hash used to verify password.
	 */
	private String passwordHash;

	/**
	 * Authorisation roles.
	 */
	private List<Role> roles;

	/**
	 * Constructor.
	 */
	public Principal() {
		roles = new ArrayList<Role>();
	}

	/**
	 * Get the username.
	 * 
	 * @return username as string.
	 */
	@Id
	@XmlID
	@XmlAttribute
	@NotNull
	@Size(min = 4)
	@Pattern(regexp = "[a-zA-Z0-9\\.\\@]*")
	@Column(unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	/**
	 * Set the username. The username must
	 * <ul>
	 * <li>not be null</li>
	 * <li>must be unique with respect to all other principals</li>
	 * <li>at least four characters long</li>
	 * <li>contain only letters, digits, fullstops and at-signs</li>
	 * </ul>
	 * 
	 * @param username
	 */
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
	 * Check whether a password is correct.
	 * 
	 * @param checkPassword
	 *            the password to check.
	 * @return true if the password is correct.
	 */
	public boolean checkPassword(String clearTextCandidate) {
		return BCrypt.checkpw(clearTextCandidate, passwordHash);
	}

	@XmlElementWrapper(name = "roles")
	@XmlElement(name = "Role")
	@ElementCollection
	@CollectionTable(name = "PRINCIPAL_ROLES", joinColumns = @JoinColumn(name = "PRINCIPAL_ID"))
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public static Principal find(String username) {
		return null;
	}

	/**
	 * Find all the Principals in the database.
	 * 
	 * @param manager
	 *            manager used to access database.
	 * @return list of prinipals.
	 */
	@SuppressWarnings("unchecked")
	public static List<Principal> findAll(EntityManager manager) {
		Query query = manager.createQuery("SELECT e FROM Principal e");
		return (List<Principal>) query.getResultList();
	}
}

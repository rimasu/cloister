package org.rimasu.cloister.server.model.auth;

import java.security.SecureRandom;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;

import org.mindrot.jbcrypt.BCrypt;

@Entity
public class Principal  {
	
	private static SecureRandom random = new SecureRandom();

	private String username;

	private String passwordHash;

	private List<Role> roles;

	

	public Principal() {
	}
	
	
	/**
	 * Override the source of randomness used to generate password salts
	 * so that a predictable source can be used during testing. This method
	 * MUST NOT be used in production.
	 * 
	 * @param random
	 */
	public static void resetSaltSeed(){
		Principal.random.setSeed(0);
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

	@XmlElementWrapper(name = "roles")
	@XmlElement(name = "Role")
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
		
		setPasswordHash(BCrypt.hashpw(clearTextPassword, BCrypt.gensalt(10, random)));
	}

	/**
	 * 
	 * @param checkPassword
	 */
	public boolean checkPassword(String clearTextCandidate) {
		return BCrypt.checkpw(clearTextCandidate, passwordHash);
	}
	
	public static Principal find(String username){
		return null;
	}
}

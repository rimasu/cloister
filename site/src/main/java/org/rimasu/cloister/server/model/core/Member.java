package org.rimasu.cloister.server.model.core;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;

import org.rimasu.cloister.server.model.AbstractEntity;
import org.rimasu.cloister.server.model.Fixture;
import org.rimasu.cloister.server.model.auth.Principal;

/**
 * Member is responsible for storing the all details stored for a member, apart
 * from security credentials (username/password/roles).
 * 
 * Each member as a randomly generated UUID which is unique with respect to
 * other members (and likely every other UUID). The UUID should never be exposed
 * via a user interface.
 */
@XmlType(name = "", propOrder = { "firstName", "surname", "principal",  "inbox",
		"sentItems", "messageBoxes", "interests", "projects" })
@Entity
@Table(name = "MEMBERS")
public class Member extends AbstractEntity {

	private String firstName;

	private String surname;
	
	private Principal principal;

	private MessageBox inbox;

	private MessageBox sentItems;

	private List<MessageBox> messageBoxes;

	private List<BlockText> projects;

	private List<BlockText> interests;

	public Member(String uuid) {
		super(uuid);
		this.messageBoxes = new ArrayList<MessageBox>();
		this.interests = new ArrayList<BlockText>();
		this.projects = new ArrayList<BlockText>();
	}

	public Member() {
		this(null);
	}

	@NotNull(message = "{member.firstName.null}")
	@Size(min = 2, message = "{member.firstName.length}")
	@Pattern(regexp = "[A-Za-z\\.\\-\\']*", message = "{member.firstName.legalChars}")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	@NotNull(message = "{member.surname.null}")
	@Size(min = 2, message = "{member.surname.length}")
	@Pattern(regexp = "[A-Za-z\\.\\-\\']*", message = "{member.surname.legalChars}")
	@Column(nullable = false)
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@NotNull
	@XmlIDREF
	@OneToOne
	@JoinColumn(nullable = false, unique = true)
	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}


	@XmlIDREF
	@OneToOne
	@JoinColumn(nullable = false)
	public MessageBox getInbox() {
		return inbox;
	}

	public void setInbox(MessageBox inbox) {
		this.inbox = inbox;
	}

	@XmlIDREF
	@OneToOne
	@JoinColumn(nullable = false)
	public MessageBox getSentItems() {
		return sentItems;
	}

	public void setSentItems(MessageBox sentItems) {
		this.sentItems = sentItems;
	}

	@XmlIDREF
	@XmlElementWrapper(name = "messageBoxes")
	@XmlElement(name = "messageBox")
	@OrderColumn(name = "SORT_ORDER")
	@JoinTable(name = "MEMBER_MESSAGEBOXES", joinColumns = @JoinColumn(name = "MEMBER"))
	@OneToMany
	public List<MessageBox> getMessageBoxes() {
		return messageBoxes;
	}

	public void setMessageBoxes(List<MessageBox> messageBoxes) {
		this.messageBoxes = messageBoxes;
	}

	@XmlElementWrapper(name = "interests")
	@XmlElement(name = "Interest")
	@ElementCollection
	@CollectionTable(name = "MEMBER_INTERESTS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
	@OrderColumn(name = "SORT_ORDER")
	public List<BlockText> getInterests() {
		return interests;
	}

	public void setInterests(List<BlockText> interests) {
		this.interests = interests;
	}

	@XmlElementWrapper(name = "projects")
	@XmlElement(name = "Project")
	@ElementCollection
	@CollectionTable(name = "MEMBER_PROJECTS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
	@OrderColumn(name = "SORT_ORDER")
	public List<BlockText> getProjects() {
		return projects;
	}

	public void setProjects(List<BlockText> projects) {
		this.projects = projects;
	}

	public static Member findMember(String id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<Member> findAllInternal(EntityManager manager) {
		Query query = manager.createQuery("SELECT e FROM Member e");
		return (List<Member>) query.getResultList();
	}

	public static List<Member> findAll() {
		ArrayList<Member> result = new ArrayList<Member>();
		result.add(Fixture.createMember());
		return result;
	}
}

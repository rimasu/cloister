package org.rimasu.cloister.server.model;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.jar.JarException;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.rimasu.cloister.server.model.auth.Principal;
import org.rimasu.cloister.server.model.core.Member;
import org.rimasu.cloister.server.model.core.Message;
import org.rimasu.cloister.server.model.core.MessageBox;
import org.rimasu.cloister.server.model.locator.CallbackService;
import org.rimasu.cloister.server.model.locator.EntityService;
import org.rimasu.cloister.server.model.locator.MemberService;
import org.rimasu.cloister.server.model.locator.MessageBoxService;
import org.rimasu.cloister.server.model.locator.MessageService;
import org.rimasu.cloister.server.model.locator.PrincipalService;
import org.rimasu.cloister.server.model.locator.ServiceLocatorImpl;
import org.rimasu.cloister.server.model.process.Callback;

/**
 * Snapshot contains the entire state of the model. This is used as the root
 * element when generating or parsing XML.
 */
@XmlType(name = "", propOrder = { "captureDate", "principals", "messageBoxes",
		"members", "messages", "callbacks" })
@XmlRootElement(name = "BackUp")
public class Snapshot {

	/**
	 * The date the snapshot was taken.
	 */
	private Calendar captureDate;

	/**
	 * All the principals.
	 */
	private List<Principal> principals;

	/**
	 * All the message boxes.
	 */
	private List<MessageBox> messageBoxes;

	/**
	 * All the members.
	 */
	private List<Member> members;

	/**
	 * All the mesages.
	 */
	private List<Message> messages;

	/**
	 * All the callbacks.
	 */
	private List<Callback> callbacks;

	/**
	 * Constructor. Create a new, empty, snapshot. All the collection are
	 * initialised so can be accessed directly via getters (no need to use
	 * setters).
	 */
	public Snapshot() {
		messageBoxes = new ArrayList<MessageBox>();
		members = new ArrayList<Member>();
		messages = new ArrayList<Message>();
		principals = new ArrayList<Principal>();
		callbacks = new ArrayList<Callback>();
	}

	/**
	 * contextPath Load a snapshot from a URL.
	 * 
	 * @param url
	 *            the url to load the snapshot from.
	 * @return snapshot.
	 * 
	 * @throws IOException
	 *             if failed to load URL.
	 */
	public static Snapshot load(URL url) throws IOException {
		try {
			JAXBContext context = JAXBContext
					.newInstance("org.rimasu.cloister.server.model");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			return (Snapshot) unmarshaller.unmarshal(url);
		} catch (JAXBException e) {
			throw new IOException(e);
		}
	}

	public static Snapshot create() throws IOException {
		Snapshot result = new Snapshot();
		result.setCaptureDate(Calendar.getInstance());
		result.setPrincipals(readAllFrom(PrincipalService.class));
		result.setMembers(readAllFrom(MemberService.class));
		result.setMessageBoxes(readAllFrom(MessageBoxService.class));
		result.setMessages(readAllFrom(MessageService.class));
		result.setCallbacks(readAllFrom(CallbackService.class));
		return result;
	}

	private static <E, S extends EntityService<E>> List<E> readAllFrom(
			Class<S> serviceType) throws IOException {
		return ServiceLocatorImpl.get(serviceType).findAll();
	}

	@NotNull
	@XmlSchemaType(name = "date")
	@XmlAttribute
	public Calendar getCaptureDate() {
		return captureDate;
	}

	public void setCaptureDate(Calendar captureDate) {
		this.captureDate = captureDate;
	}

	@Valid
	@XmlElementWrapper(name = "members")
	@XmlElement(name = "Member")
	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	@Valid
	@XmlElementWrapper(name = "messageBoxes")
	@XmlElement(name = "MessageBox")
	public List<MessageBox> getMessageBoxes() {
		return messageBoxes;
	}

	public void setMessageBoxes(List<MessageBox> messageBoxes) {
		this.messageBoxes = messageBoxes;
	}

	@Valid
	@XmlElementWrapper(name = "messages")
	@XmlElement(name = "Message")
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Valid
	@XmlElementWrapper(name = "principals")
	@XmlElement(name = "Principal")
	public List<Principal> getPrincipals() {
		return principals;
	}

	public void setPrincipals(List<Principal> principals) {
		this.principals = principals;
	}

	@Valid
	@XmlElementWrapper(name = "callbacks")
	@XmlElement(name = "Callback")
	public List<Callback> getCallbacks() {
		return callbacks;
	}

	public void setCallbacks(List<Callback> callbacks) {
		this.callbacks = callbacks;
	}

	public void persistTo(EntityManager manager) {
		persistTo(manager, principals);
		persistTo(manager, messageBoxes);
		persistTo(manager, members);
		persistTo(manager, messages);
		persistTo(manager, callbacks);
	}

	private <T> void persistTo(EntityManager manager, List<T> entities) {
		for (T entity : entities) {
			manager.persist(entity);
		}
	}

	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append("Snapshot@");
		bld.append(captureDate.getTimeInMillis());
		return bld.toString();
	}
}

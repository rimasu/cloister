package org.rimasu.cloister.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;

/**
 * Abstract Base Entity from which all other model entities are derived.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity {

	private String id;
	
	private Integer version;

	public AbstractEntity() {

	}

	public AbstractEntity(String id) {
		this.id = id;
	}

	@Id
	@XmlID
	@XmlAttribute
	@NotNull(message = "{uuid.null}")
	@Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}", message = "{uuid.valid}")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Version
	public Integer getVersion(){
		return version;
	}
	
	public void setVersion(Integer version){
		this.version = version;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()//
				 .append(getClass()//
				 .getSimpleName())//
				 .append("#")//
				 .append(id)//
				 .toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

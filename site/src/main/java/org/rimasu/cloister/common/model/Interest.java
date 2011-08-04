package org.rimasu.cloister.common.model;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Embeddable
public class Interest {


	private String content;

	public Interest() {
	}

	public Interest(String content) {		
		this.content = content;
	}
		

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

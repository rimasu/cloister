package org.rimasu.cloister.common.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;


@Embeddable
public class Interest {
	
	private int sortOrder;	

	private String content;
	
	public Interest() {
		content = "";
	}

	public Interest(String content) {
		this.content = content;
	}

	@NotNull
	@Size(min = 1)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (content == null) {
			this.content = null;
		} else {
			this.content = SimpleHtmlSanitizer.sanitizeHtml(content).asString();
		}
	}
	
	@Column(name="SORT_ORDER")	
	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
}

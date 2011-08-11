package org.rimasu.cloister.common.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;


@Embeddable
public class BlockText {
	
	private String content;
	
	public BlockText() {
		content = "";
	}

	public BlockText(String content) {
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
}

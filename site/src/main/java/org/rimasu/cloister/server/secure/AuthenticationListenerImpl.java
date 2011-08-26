package org.rimasu.cloister.server.secure;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;

import com.google.gwt.core.client.GWT;

public class AuthenticationListenerImpl implements
		ApplicationListener<AbstractAuthenticationEvent> {

	@Override
	public void onApplicationEvent(AbstractAuthenticationEvent event) {

		final StringBuilder builder = new StringBuilder();
		builder.append("Authentication event ");
		builder.append(event.getClass().getSimpleName());
		builder.append(": ");
		builder.append(event.getAuthentication().getName());
		builder.append("; details: ");
		builder.append(event.getAuthentication().getDetails());

		if (event instanceof AbstractAuthenticationFailureEvent) {
			builder.append("; exception: ");
			builder.append(((AbstractAuthenticationFailureEvent) event)
					.getException().getMessage());
		}

		GWT.log(builder.toString());

	}

}
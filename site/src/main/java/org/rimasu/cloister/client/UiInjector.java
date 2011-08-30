package org.rimasu.cloister.client;

import org.rimasu.cloister.client.member.edit.MemberEditPanel;
import org.rimasu.cloister.client.member.view.MemberDisplayPanel;
import org.rimasu.cloister.client.site.ApplicationPanel;
import org.rimasu.cloister.client.ui.UiModuleImpl;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;


@GinModules(UiModuleImpl.class)
public interface UiInjector extends Ginjector {
		
	ApplicationPanel getApplicationPanel();
	MemberEditPanel getMemberEditPanel();
	MemberDisplayPanel getMemberDisplayPanel();
}

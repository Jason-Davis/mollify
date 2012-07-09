/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.common;

import org.sjarvela.mollify.client.ResourceId;
import org.sjarvela.mollify.client.ui.StyleConstants;
import org.sjarvela.mollify.client.ui.action.ActionListener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;

public class ActionLink extends Label {
	public ActionLink(String title) {
		this(title, null, null);
	}

	public ActionLink(String title, String id) {
		this(title, id, null);
	}

	public ActionLink(String title, String id, String styleClass) {
		super(title);

		setStylePrimaryName(StyleConstants.ACTION_LINK);
		if (styleClass != null)
			addStyleDependentName(styleClass);

		if (id != null)
			getElement().setId(id);

		HoverDecorator.decorate(this);
	}

	public void setClickHandler(final ClickHandler clickHandler) {
		this.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				HoverDecorator.clear(ActionLink.this);
				clickHandler.onClick(event);
			}
		});
	}

	public void setAction(final ActionListener actionListener,
			final ResourceId actionId) {
		this.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				HoverDecorator.clear(ActionLink.this);
				actionListener.onAction(actionId, null);
			}
		});
	}
}
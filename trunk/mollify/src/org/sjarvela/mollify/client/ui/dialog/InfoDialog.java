/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.dialog;

import org.sjarvela.mollify.client.localization.TextProvider;
import org.sjarvela.mollify.client.localization.Texts;
import org.sjarvela.mollify.client.ui.StyleConstants;
import org.sjarvela.mollify.client.ui.common.dialog.CenteredDialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class InfoDialog extends CenteredDialog {
	private final TextProvider textProvider;
	private final String text;
	private final String type;
	private final String info;

	public InfoDialog(TextProvider textProvider, String title, String text,
			String info, String type) {
		super(title, type);
		this.textProvider = textProvider;
		this.text = text;
		this.info = info;
		this.type = type;

		initialize();
	}

	@Override
	protected Widget createContent() {
		Panel content = new FlowPanel();
		content.setStylePrimaryName(StyleConstants.INFO_DIALOG_CONTENT);

		Label icon = new Label();
		icon.setStylePrimaryName(StyleConstants.INFO_DIALOG_ICON);
		icon.addStyleDependentName(type);
		content.add(icon);

		Label message = new Label(text);
		message.setStylePrimaryName(StyleConstants.INFO_DIALOG_MESSAGE);
		message.addStyleDependentName(type);
		content.add(message);

		if (info != null) {
			TextBox infoBox = new TextBox();
			infoBox.setStylePrimaryName(StyleConstants.INFO_DIALOG_INFO);
			infoBox.addStyleDependentName(type);

			infoBox.setReadOnly(true);
			infoBox.setText(info);
			infoBox.setTitle(info);
			content.add(infoBox);
		}

		return content;
	}

	@Override
	protected Widget createButtons() {
		HorizontalPanel buttons = new HorizontalPanel();
		buttons.setStylePrimaryName(StyleConstants.INFO_DIALOG_BUTTONS);
		buttons.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);

		buttons.add(createButton(textProvider.getText(Texts.dialogOkButton),
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						InfoDialog.this.hide();
					}
				}, type));

		return buttons;
	}
}

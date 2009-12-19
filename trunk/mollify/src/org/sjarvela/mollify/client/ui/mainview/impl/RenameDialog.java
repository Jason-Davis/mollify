/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.mainview.impl;

import org.sjarvela.mollify.client.filesystem.File;
import org.sjarvela.mollify.client.filesystem.FileSystemItem;
import org.sjarvela.mollify.client.filesystem.handler.RenameHandler;
import org.sjarvela.mollify.client.localization.TextProvider;
import org.sjarvela.mollify.client.ui.StyleConstants;
import org.sjarvela.mollify.client.ui.ViewListener;
import org.sjarvela.mollify.client.ui.common.dialog.CenteredDialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RenameDialog extends CenteredDialog {
	private final FileSystemItem item;
	private final TextProvider textProvider;
	private final RenameHandler renameHandler;
	private TextBox name;

	public RenameDialog(final FileSystemItem item, TextProvider textProvider,
			RenameHandler renameHandler) {
		super(item.isFile() ? textProvider.getStrings().renameDialogTitleFile()
				: textProvider.getStrings().renameDialogTitleDirectory(),
				StyleConstants.RENAME_DIALOG);
		this.item = item;
		this.textProvider = textProvider;
		this.renameHandler = renameHandler;

		this.addViewListener(new ViewListener() {
			public void onShow() {
				focusName();
			}
		});
		initialize();
	}

	private void focusName() {
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				name.setFocus(true);
				if (item.isFile())
					hilightFilename();
			}
		});
	}

	@Override
	protected Widget createContent() {
		VerticalPanel panel = new VerticalPanel();
		panel.addStyleName(StyleConstants.RENAME_DIALOG_CONTENT);

		Label originalNameTitle = new Label(textProvider.getStrings()
				.renameDialogOriginalName());
		originalNameTitle
				.setStyleName(StyleConstants.RENAME_ORIGINAL_NAME_TITLE);
		panel.add(originalNameTitle);

		Label originalName = new Label(item.getName());
		originalName.setStyleName(StyleConstants.RENAME_ORIGINAL_NAME_VALUE);
		panel.add(originalName);

		Label newNameTitle = new Label(textProvider.getStrings()
				.renameDialogNewName());
		newNameTitle.setStyleName(StyleConstants.RENAME_NEW_NAME_TITLE);
		panel.add(newNameTitle);

		name = new TextBox();
		name.addStyleName(StyleConstants.RENAME_NEW_NAME_VALUE);
		name.setText(item.getName());

		panel.add(name);

		return panel;
	}

	@Override
	protected Widget createButtons() {
		HorizontalPanel buttons = new HorizontalPanel();
		buttons.addStyleName(StyleConstants.RENAME_DIALOG_BUTTONS);
		buttons.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);

		buttons.add(createButton(textProvider.getStrings()
				.renameDialogRenameButton(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				onRename();
			}
		}, StyleConstants.RENAME_DIALOG_BUTTON_RENAME));

		buttons.add(createButton(
				textProvider.getStrings().dialogCancelButton(),
				new ClickHandler() {
					public void onClick(ClickEvent event) {
						RenameDialog.this.hide();
					}
				}, StyleConstants.DIALOG_BUTTON_CANCEL));

		return buttons;
	}

	private void hilightFilename() {
		File file = (File) item;
		if (file.getExtension().length() > 0)
			name.setSelectionRange(0, file.getName().length()
					- (file.getExtension().length() + 1));
		name.setFocus(true);
	}

	private void onRename() {
		String newName = name.getText();

		if (newName.length() < 1) {
			focusName();
			return;
		}

		if (newName.equals(item.getName())) {
			hilightFilename();
			return;
		}

		this.hide();
		renameHandler.rename(item, newName);
	}
}

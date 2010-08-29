/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.fileitemcontext.popup.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sjarvela.mollify.client.ResourceId;
import org.sjarvela.mollify.client.filesystem.FileSystemAction;
import org.sjarvela.mollify.client.localization.TextProvider;
import org.sjarvela.mollify.client.ui.StyleConstants;
import org.sjarvela.mollify.client.ui.action.ActionListener;
import org.sjarvela.mollify.client.ui.common.MultiActionButton;
import org.sjarvela.mollify.client.ui.common.popup.DropdownButton;
import org.sjarvela.mollify.client.ui.fileitemcontext.ActionMenuItem;
import org.sjarvela.mollify.client.ui.fileitemcontext.ItemContext;
import org.sjarvela.mollify.client.ui.fileitemcontext.MenuItem;
import org.sjarvela.mollify.client.ui.fileitemcontext.MenuSeparator;
import org.sjarvela.mollify.client.ui.fileitemcontext.component.ItemContextComponent;
import org.sjarvela.mollify.client.ui.fileitemcontext.component.ItemContextSection;

import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ItemContextPopupComponent extends ContextPopupComponent {
	private final TextProvider textProvider;
	private final ActionListener actionListener;

	private Label name;

	private DropdownButton actionsButton;

	private VerticalPanel componentsPanel;
	private Map<ItemContextComponent, Widget> components = new HashMap();
	private FlowPanel buttons;

	public enum Action implements ResourceId {
		addDescription, editDescription, removeDescription, cancelEditDescription, applyDescription, editPermissions, addToDropbox
	}

	public enum DescriptionActionGroup implements ResourceId {
		view, edit
	}

	public ItemContextPopupComponent(TextProvider textProvider,
			boolean generalWritePermissions, ActionListener actionListener) {
		super(StyleConstants.FILE_CONTEXT, null);

		this.textProvider = textProvider;
		this.actionListener = actionListener;

		initialize();
	}

	protected Widget createContent() {
		Panel content = new VerticalPanel();
		content.setStyleName(StyleConstants.FILE_CONTEXT_CONTENT);

		Label widthEnforcer = new Label();
		widthEnforcer.setStyleName(StyleConstants.FILE_CONTEXT_WIDTH_ENFORCER);
		content.add(widthEnforcer);

		name = new Label();
		name.setStyleName(StyleConstants.FILE_CONTEXT_FILENAME);
		content.add(name);

		content.add(createComponentsPanel());
		content.add(createButtons());
		return content;
	}

	public List<ItemContextComponent> setup(ItemContext itemContext) {
		List<ItemContextComponent> contextComponents = new ArrayList(
				itemContext.getComponents());
		this.components.clear();
		this.componentsPanel.clear();

		for (ItemContextComponent c : contextComponents)
			addComponent(c);

		setupDownload(itemContext.getDownloadItems());
		setupActions(itemContext.getActionItems());

		return contextComponents;
	}

	private void setupDownload(List<MenuItem> downloadItems) {
		if (downloadItems.isEmpty())
			return;

		Widget downloadButton;
		if (downloadItems.size() > 1) {
			downloadButton = createDropdownButton(downloadItems);
		} else {
			downloadButton = createButton(downloadItems.get(0));
		}

		if (downloadButton == null)
			return;
		buttons.add(downloadButton);
	}

	private Widget createButton(MenuItem item) {
		if (item instanceof ActionMenuItem) {
			ActionMenuItem menuItem = (ActionMenuItem) item;
			return createActionButton(menuItem.getTitle(), actionListener,
					menuItem.getAction());
		}
		return null;
	}

	private Widget createDropdownButton(List<MenuItem> downloadItems) {
		MultiActionButton downloadButton = createMultiActionButton(
				actionListener, textProvider.getStrings()
						.fileActionDownloadTitle(), FileSystemAction.download
						.name());

		boolean first = true;
		for (MenuItem item : downloadItems) {
			if (item instanceof ActionMenuItem) {
				ActionMenuItem menuItem = (ActionMenuItem) item;
				downloadButton.addAction(menuItem.getAction(), menuItem
						.getTitle());
				if (first)
					downloadButton.setDefaultAction(menuItem.getAction());
				first = false;
			} else if (item instanceof MenuSeparator) {
				downloadButton.addSeparator();
			}
		}

		return downloadButton;
	}

	private void setupActions(List<MenuItem> actions) {
		for (MenuItem item : actions) {
			if (item instanceof ActionMenuItem)
				actionsButton.addAction(((ActionMenuItem) item).getAction(),
						((ActionMenuItem) item).getTitle());
			else if (item instanceof MenuSeparator)
				actionsButton.addSeparator();
		}
		if (!actions.isEmpty())
			buttons.add(actionsButton);
	}

	private void addComponent(ItemContextComponent c) {
		if (c instanceof ItemContextSection) {
			Widget section = createSection((ItemContextSection) c);
			components.put(c, section);
			componentsPanel.add(section);
		} else {
			componentsPanel.add(c.getComponent());
		}
	}

	private Widget createSection(final ItemContextSection section) {
		DisclosurePanel s = new DisclosurePanel(section.getTitle());
		s.setOpen(false);
		s.addStyleName(StyleConstants.ITEM_CONTEXT_SECTION);
		s.getHeader().getElement().getParentElement().setClassName(
				StyleConstants.ITEM_CONTEXT_SECTION_HEADER);

		s.addOpenHandler(new OpenHandler<DisclosurePanel>() {
			@Override
			public void onOpen(OpenEvent<DisclosurePanel> event) {
				section.onOpen();
			}
		});
		s.addCloseHandler(new CloseHandler<DisclosurePanel>() {
			@Override
			public void onClose(CloseEvent<DisclosurePanel> event) {
				section.onClose();
			}
		});

		s.add(section.getComponent());
		return s;
	}

	private Widget createButtons() {
		buttons = new FlowPanel();
		buttons.setStyleName(StyleConstants.FILE_CONTEXT_BUTTONS);

		actionsButton = new DropdownButton(actionListener, textProvider
				.getStrings().fileDetailsActionsTitle(),
				StyleConstants.FILE_CONTEXT_ACTIONS);

		return buttons;
	}

	// private Button createViewButton() {
	// if (!fileView)
	// return null;
	// Button button = createActionButton(textProvider.getStrings()
	// .fileActionViewTitle(), actionListener, FileSystemAction.view);
	// return button;
	// }

	private Widget createComponentsPanel() {
		componentsPanel = new VerticalPanel();
		componentsPanel
				.setStylePrimaryName(StyleConstants.ITEM_CONTEXT_COMPONENTS_PANEL);
		return componentsPanel;
	}

	public void reset() {
		componentsPanel.clear();
		actionsButton.removeAllActions();
		buttons.clear();
	}

	public Label getName() {
		return name;
	}

	public void removeComponent(ItemContextComponent c) {
		Widget w = components.get(c);
		if (w == null)
			w = c.getComponent();
		componentsPanel.remove(w);
	}

	public void removeComponents(List<ItemContextComponent> list) {
		for (ItemContextComponent c : list)
			removeComponent(c);
	}
}

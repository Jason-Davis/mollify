/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.dropbox.impl;

import java.util.List;

import org.sjarvela.mollify.client.filesystem.FileSystemItem;
import org.sjarvela.mollify.client.ui.action.ActionDelegator;
import org.sjarvela.mollify.client.ui.action.ActionHandler;
import org.sjarvela.mollify.client.ui.action.VoidActionHandler;
import org.sjarvela.mollify.client.ui.dnd.DragAndDropManager;
import org.sjarvela.mollify.client.ui.dropbox.DropBox;
import org.sjarvela.mollify.client.ui.dropbox.impl.DropBoxView.Actions;
import org.sjarvela.mollify.client.ui.filelist.DraggableFileSystemItem;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.Widget;

public class DropBoxGlue implements DropBox, DropController {
	private final DropBoxView view;
	private final DropBoxPresenter presenter;

	public DropBoxGlue(ActionDelegator actionDelegator, DropBoxView view,
			final DropBoxPresenter presenter,
			DragAndDropManager dragAndDropManager) {
		this.view = view;
		this.presenter = presenter;

		dragAndDropManager.getController(FileSystemItem.class)
				.registerDropController(this);

		actionDelegator.setActionHandler(Actions.clear,
				new VoidActionHandler() {
					@Override
					public void onAction() {
						presenter.onClear();
					}
				});

		actionDelegator.setActionHandler(Actions.remove,
				new ActionHandler<FileSystemItem>() {
					@Override
					public void onAction(FileSystemItem item) {
						presenter.onRemove(item);
					}
				});

		actionDelegator.setActionHandler(Actions.delete,
				new VoidActionHandler() {
					@Override
					public void onAction() {
						presenter.onDeleteItems();
					}
				});

		actionDelegator.setActionHandler(Actions.copy, new VoidActionHandler() {
			@Override
			public void onAction() {
				presenter.onCopyItems();
			}
		});

		actionDelegator.setActionHandler(Actions.copyHere,
				new VoidActionHandler() {
					@Override
					public void onAction() {
						presenter.onCopyHereItems();
					}
				});
		actionDelegator.setActionHandler(Actions.move, new VoidActionHandler() {
			@Override
			public void onAction() {
				presenter.onMoveItems();
			}
		});

		actionDelegator.setActionHandler(Actions.moveHere,
				new VoidActionHandler() {
					@Override
					public void onAction() {
						presenter.onMoveHereItems();
					}
				});

		actionDelegator.setActionHandler(Actions.downloadAsZip,
				new VoidActionHandler() {
					@Override
					public void onAction() {
						presenter.onDownloadAsZip();
					}
				});
	}

	@Override
	public Widget getWidget() {
		return view;
	}

	// @Override
	// public void close() {
	// view.hide();
	// }

	// @Override
	// public boolean isVisible() {
	// return view.isVisible();
	// }

	// @Override
	// public void toggle(Coords position) {
	// view.toggleShow(position);
	// }

	@Override
	public Widget getDropTarget() {
		return view.getDropTarget();
	}

	@Override
	public void onDrop(DragContext context) {
		presenter
				.onDropItems(((DraggableFileSystemItem) context.selectedWidgets
						.get(0)).getItems());
		((DraggableFileSystemItem) context.selectedWidgets.get(0)).getItems()
				.clear();
	}

	@Override
	public void addItems(List<FileSystemItem> items) {
		presenter.onDropItems(items);
	}

	@Override
	public void onEnter(DragContext context) {
		presenter.onDragEnter();
	}

	@Override
	public void onLeave(DragContext context) {
		presenter.onDragLeave();
	}

	@Override
	public void onMove(DragContext context) {
	}

	@Override
	public void onPreviewDrop(DragContext context) throws VetoDragException {
	}

}
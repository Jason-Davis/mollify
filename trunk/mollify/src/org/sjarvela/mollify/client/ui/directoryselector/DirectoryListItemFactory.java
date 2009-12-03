/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.directoryselector;

import org.sjarvela.mollify.client.filesystem.Folder;
import org.sjarvela.mollify.client.filesystem.foldermodel.FolderProvider;
import org.sjarvela.mollify.client.localization.TextProvider;

public class DirectoryListItemFactory {
	private FolderProvider provider;
	private TextProvider textProvider;

	public DirectoryListItemFactory(TextProvider textProvider,
			FolderProvider provider) {
		this.textProvider = textProvider;
		this.provider = provider;
	}

	public DirectoryListItem createListItem(DirectoryListener listener,
			String style, Folder current, int level, Folder parent) {
		return new DirectoryListItem(style, current, level, parent, provider,
				listener, textProvider);
	}

}

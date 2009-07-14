/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.ui.mainview;

import org.sjarvela.mollify.client.filesystem.directorymodel.FileSystemItemProvider;
import org.sjarvela.mollify.client.filesystem.handler.FileSystemActionHandlerFactory;
import org.sjarvela.mollify.client.localization.TextProvider;
import org.sjarvela.mollify.client.service.FileSystemService;
import org.sjarvela.mollify.client.service.environment.ServiceEnvironment;
import org.sjarvela.mollify.client.session.LogoutHandler;
import org.sjarvela.mollify.client.session.SessionInfo;
import org.sjarvela.mollify.client.session.SessionProvider;
import org.sjarvela.mollify.client.ui.ActionDelegator;
import org.sjarvela.mollify.client.ui.WindowManager;
import org.sjarvela.mollify.client.ui.directoryselector.DirectorySelectorFactory;
import org.sjarvela.mollify.client.ui.fileitemcontext.directorycontext.DirectoryContextPopupFactory;
import org.sjarvela.mollify.client.ui.fileitemcontext.filecontext.FileContextPopupFactory;

public class MainViewFactory {
	private final ServiceEnvironment environment;
	private final TextProvider textProvider;
	private final SessionProvider sessionProvider;

	public MainViewFactory(TextProvider textProvider,
			ServiceEnvironment environment, SessionProvider sessionProvider) {
		this.textProvider = textProvider;
		this.environment = environment;
		this.sessionProvider = sessionProvider;
	}

	public MainView createMainView(WindowManager windowManager,
			LogoutHandler logoutListener) {
		SessionInfo session = sessionProvider.getSession();

		FileSystemItemProvider fileSystemItemProvider = new DefaultFileSystemItemProvider(
				session.getRootDirectories(), environment
						.getFileSystemService());

		FileSystemService fileSystemService = environment
				.getFileSystemService();

		MainViewModel model = new MainViewModel(fileSystemService, session,
				fileSystemItemProvider);

		DirectorySelectorFactory directorySelectorFactory = new DirectorySelectorFactory(
				model, fileSystemService, textProvider, fileSystemItemProvider);
		FileContextPopupFactory fileContextPopupFactory = new FileContextPopupFactory(
				fileSystemService, textProvider, model.getSession());
		DirectoryContextPopupFactory directoryContextPopupFactory = new DirectoryContextPopupFactory(
				textProvider, fileSystemService, model.getSession());
		FileSystemActionHandlerFactory fileSystemActionHandlerFactory = new DefaultFileSystemActionHandlerFactory(
				windowManager, fileSystemService, fileSystemItemProvider);
		ActionDelegator actionDelegator = new ActionDelegator();

		MainView view = new MainView(model, textProvider, actionDelegator,
				directorySelectorFactory, fileContextPopupFactory,
				directoryContextPopupFactory);
		MainViewPresenter presenter = new MainViewPresenter(windowManager,
				model, view, environment.getSessionService(),
				fileSystemService, environment.getConfigurationService(),
				environment.getFileUploadHandler(), textProvider,
				fileSystemItemProvider, logoutListener,
				fileSystemActionHandlerFactory);
		new MainViewGlue(view, presenter, actionDelegator);

		return view;
	}
}

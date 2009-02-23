/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client;

import org.sjarvela.mollify.client.data.SessionInfo;
import org.sjarvela.mollify.client.localization.Localizator;
import org.sjarvela.mollify.client.service.MollifyError;
import org.sjarvela.mollify.client.service.MollifyService;
import org.sjarvela.mollify.client.service.ResultListener;
import org.sjarvela.mollify.client.service.ServiceError;
import org.sjarvela.mollify.client.ui.DialogManager;
import org.sjarvela.mollify.client.ui.WindowManager;
import org.sjarvela.mollify.client.ui.mainview.MainViewFactory;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.RootPanel;

public class App implements EntryPoint, LogoutListener {
	private static final String META_PROPERTY = "mollify:property";
	private static final String PARAM_SERVICE_PATH = "service-path";

	private static final String MOLLIFY_PANEL_ID = "mollify";

	MollifyService service;
	Localizator localizator;
	WindowManager windowManager;
	RootPanel panel;

	public void onModuleLoad() {
		Log.setUncaughtExceptionHandler();

		DeferredCommand.addCommand(new Command() {
			public void execute() {
				onLoad();
			}
		});
	}

	private void onLoad() {
		panel = RootPanel.get(MOLLIFY_PANEL_ID);
		if (panel == null)
			return;

		ParameterParser parser = new ParameterParser(META_PROPERTY);

		try {
			service = createService(parser);
			localizator = Localizator.getInstance();

			MainViewFactory mainViewFactory = new MainViewFactory(localizator,
					new DefaultTextProvider(localizator), service);
			windowManager = new WindowManager(panel, localizator,
					mainViewFactory, new DialogManager(localizator));
		} catch (RuntimeException e) {
			showExceptionError("Error initializing: ", e);
			return;
		}

		start();
	}

	private MollifyService createService(ParameterParser parser) {
		MollifyService service = (MollifyService) GWT
				.create(MollifyService.class);
		service.initialize(parser.getParameter(PARAM_SERVICE_PATH));
		return service;
	}

	private void start() {
		Log.info("Starting Mollify");

		service.getSessionInfo(new ResultListener() {
			public void onFail(MollifyError error) {
				windowManager.getDialogManager().showError(error);
			}

			public void onSuccess(Object... result) {
				startSession((SessionInfo) result[0]);
			}
		});
	};

	private void startSession(SessionInfo info) {
		if (info.isAuthenticationRequired() && !info.getAuthenticated())
			showLogin();
		else
			showMain(info);
	}

	private void showLogin() {
		windowManager.getDialogManager().showLoginDialog(new LoginHandler() {
			public void onLogin(String userName, String password,
					final ConfirmationListener listener) {
				Log.info("User login: " + userName);

				service.authenticate(userName, password, new ResultListener() {
					public void onFail(MollifyError error) {
						if (ServiceError.AUTHENTICATION_FAILED.equals(error)) {
							showLoginError();
							return;
						}
						windowManager.getDialogManager().showError(error);
					}

					public void onSuccess(Object... result) {
						listener.onConfirm();
						showMain((SessionInfo) result[0]);
					}
				});
			}
		});
	}

	private void showMain(SessionInfo info) {
		Log.info("Session started: " + info.asString());
		windowManager.showMainView(info, this);
	}

	public void onLogout(SessionInfo info) {
		Log.info("Logging out");

		service.logout(new ResultListener() {
			public void onFail(MollifyError error) {
				windowManager.empty();
				windowManager.getDialogManager().showError(error);
			}

			public void onSuccess(Object... result) {
				windowManager.empty();
				startSession((SessionInfo) result[0]);
			}
		});
	}

	private void showLoginError() {
		String title = localizator.getStrings().loginDialogTitle();
		String msg = localizator.getStrings().loginDialogLoginFailedMessage();
		windowManager.getDialogManager().showInfo(title, msg);
	}

	private void showExceptionError(String message, Throwable e) {
		Log.error(message, e);
		windowManager.getDialogManager().showInfo("Mollify",
				"Unexpected error: " + e.getMessage());
	}

}

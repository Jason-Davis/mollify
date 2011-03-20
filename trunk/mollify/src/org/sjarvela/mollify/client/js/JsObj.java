/**
 * Copyright (c) 2008- Samuli Järvelä
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 */

package org.sjarvela.mollify.client.js;

import java.util.List;

import org.sjarvela.mollify.client.util.JsUtil;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;

public class JsObj extends JavaScriptObject {
	protected JsObj() {
	}

	public final native String getString(String name) /*-{
		return this[name];
	}-*/;

	public final native int getInt(String name) /*-{
		return this[name];
	}-*/;

	public final native JsObj getJsObj(String name) /*-{
		return this[name];
	}-*/;

	public final native JsArray getArray(String name) /*-{
		return this[name];
	}-*/;

	public final native JavaScriptObject getObject(String name) /*-{
		return this[name];
	}-*/;

	public final native String getAsString(String name) /*-{
		return "" + this[name];
	}-*/;

	public final native boolean getBoolean(String name) /*-{
		return this[name];
	}-*/;

	public final native boolean hasValue(String name) /*-{
		if (!this[name]) return false;
		return true;
	}-*/;

	public final List<String> getKeys() {
		return JsUtil.asList(getKeyList());
	}

	private final native JsArrayString getKeyList() /*-{
		var result = [];
		for (id in this) result.push(id);
		return result;
	}-*/;
}

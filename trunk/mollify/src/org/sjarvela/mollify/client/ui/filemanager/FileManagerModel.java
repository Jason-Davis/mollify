package org.sjarvela.mollify.client.ui.filemanager;

import org.sjarvela.mollify.client.data.Directory;
import org.sjarvela.mollify.client.data.File;

import com.google.gwt.core.client.JsArray;

public class FileManagerModel {
	private JsArray<Directory> rootDirectories;

	private JsArray<Directory> directories;
	private JsArray<File> files;

	private DirectoryModel directoryModel;

	public FileManagerModel() {
		super();
		clear();
	}

	void clear() {
		rootDirectories = JsArray.createArray().cast();
		directories = JsArray.createArray().cast();
		files = JsArray.createArray().cast();
		directoryModel = new DirectoryModel();
	}

	public void setData(JsArray<Directory> directories, JsArray<File> files) {
		this.directories = directories;
		this.files = files;
	}

	public JsArray<Directory> getDirectories() {
		return directories;
	}

	public void setDirectories(JsArray<Directory> directories) {
		this.directories = directories;
	}

	public JsArray<File> getFiles() {
		return files;
	}

	public void setFiles(JsArray<File> files) {
		this.files = files;
	}

	public JsArray<Directory> getRootDirectories() {
		return rootDirectories;
	}

	public void setRootDirectories(JsArray<Directory> dirs) {
		this.rootDirectories = dirs;
	}

	public DirectoryModel getDirectoryModel() {
		return directoryModel;
	}
}

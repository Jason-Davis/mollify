<?php

	/**
	 * Copyright (c) 2008- Samuli J�rvel�
	 *
	 * All rights reserved. This program and the accompanying materials
	 * are made available under the terms of the Eclipse Public License v1.0
	 * which accompanies this distribution, and is available at
	 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
	 * this entire header must remain intact.
	 */

	class GuestServices extends ServicesBase {
		const UPLOAD_SUBJECT = "Upload link";
		const UPLOAD_MESSAGE = "Open following link to upload files: %link%";

		const DOWNLOAD_SUBJECT = "Download link";
		const DOWNLOAD_MESSAGE = "Open following link to download file \"%name%\": %link%";
		
		protected function isValidPath($method, $path) {
			return TRUE;
		}
		
		public function isAuthenticationRequired() {
			return TRUE;
		}
		
		public function processGet() {
			if (count($this->path) != 2) throw $this->invalidRequestException();
			
			if ($this->path[0] == 'upload' and $this->path[1] == 'info') {
				$this->response()->success(array("subject" => self::UPLOAD_SUBJECT, "message" => self::UPLOAD_MESSAGE));
			} else if ($this->path[0] == 'download' and $this->path[1] == 'info') {
				$this->response()->success(array("subject" => self::DOWNLOAD_SUBJECT, "message" => self::DOWNLOAD_MESSAGE));			
			} else {
				throw $this->invalidRequestException();
			}
		}
		
		public function processPost() {
			if (count($this->path) == 0 or count($this->path) > 2) throw $this->invalidRequestException();
			
			if ($this->path[0] == 'upload') {
				if (count($this->path) == 1) $this->doUpload();
				else if ($this->path[1] === 'send') $this->doSendUpload();
			} else if ($this->path[0] == 'download') {
				if ($this->path[1] === 'send') $this->doSendDownload();
			}
			else throw $this->invalidRequestException();
		}

		private function doSendUpload() {			
			if (!$this->request->hasData()) throw $this->invalidRequestException();
			
			$data = $this->request->data;
			if (!isset($data["to"]) or !isset($data["subject"]) or !isset($data["message"])) throw $this->invalidRequestException();
			
			$user = $this->env->configuration()->getUser($this->env->authentication()->getUserId());
			if ($user["email"] == NULL or strlen($user["email"]) == 0) $this->response()->fail("1", "User does not have email defined");
			
			$link = $this->env->getPluginUrl("Guest")."upload/?id=".$this->env->authentication()->getUserId();
			$values = array("link" => $link);
			
			$msg = Util::replaceParams($data["message"], $values);
			$recipient = array(array("email" => $data["to"]));
			
			$this->env->notificator()->send($recipient, $data["subject"], $msg);
			$this->response()->success(array("success"=>TRUE));
		}

		private function doSendDownload() {			
			if (!$this->request->hasData()) throw $this->invalidRequestException();
			
			$data = $this->request->data;
			if (!isset($data["to"]) or !isset($data["subject"]) or !isset($data["message"]) or !isset($data["id"])) throw $this->invalidRequestException();
			
			$user = $this->env->configuration()->getUser($this->env->authentication()->getUserId());
			if ($user["email"] == NULL or strlen($user["email"]) == 0) $this->response()->fail("1", "User does not have email defined");
			
			$link = $this->env->getServiceUrl("public", array("items", $data["id"]), TRUE);
			$values = array("link" => $link);
			
			$msg = Util::replaceParams($data["message"], $values);
			$recipient = array(array("email" => $data["to"]));
			
			$this->env->notificator()->send($recipient, $data["subject"], $msg);
			$this->response()->success(array("success"=>TRUE));
		}
		
		private function doUpload() {
			if (!$this->request->hasParam("id")) throw $this->invalidRequestException();
			
			$folder = $this->getInboxFolder($this->request->param("id"));
			require_once("include/filesystem/plupload.php");
			plupload($folder, $this->env->events());
		}
		
		private function getInboxFolder($id) {
			$folder = $this->env->customizations()->getUserFolder($id);
			$target = $this->env->filesystem()->filesystemFromId($folder["id"]);
			$root = $target->root();
			return $root->folderWithName(KennyHWLCustomizations::$INBOX_NAME);
		}
	}
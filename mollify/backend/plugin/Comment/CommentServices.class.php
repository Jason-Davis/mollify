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

	class CommentServices extends ServicesBase {		
		protected function isValidPath($method, $path) {
			return count($path) == 1;
		}
		
		public function isAuthenticationRequired() {
			return TRUE;
		}
		
		public function processGet() {
			if (count($this->path) != 1) throw $this->invalidRequestException();
			
			$item = $this->item($this->path[0]);
			$this->response()->success($this->handler()->getComments($item));
		}
		
		public function processPost() {
			if (count($this->path) != 1) throw $this->invalidRequestException();
			
			$item = $this->item($this->path[0]);
			$data = $this->request->data;
			if (!isset($data["user"]) or !isset($data["comment"])) throw $this->invalidRequestException("No data");
			
			$this->handler()->addComment($data["user"], $item, $data["comment"]);
			$this->response()->success(TRUE);
		}
		
		private function handler() {
			return $this->env->plugins()->getPlugin("Comment")->getHandler();
		}
	}
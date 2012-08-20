<?php

	/**
	 * Copyright (c) 2008- Samuli Järvelä
	 *
	 * All rights reserved. This program and the accompanying materials
	 * are made available under the terms of the Eclipse Public License v1.0
	 * which accompanies this distribution, and is available at
	 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
	 * this entire header must remain intact.
	 */

	class ArchiveManager {
		private $env;
		
		function __construct($env) {
			$this->env = $env;
		}
		
		public function extract($archive, $to) {
			$zip = new ZipArchive;
			if ($zip->open($archive) !== TRUE)
				throw new ServiceException("REQUEST_FAILED", "Could not open archive ".$archive);
			
			$zip->extractTo($to);
			$zip->close();
		}
		
		public function compress($folder, $to) {
			$zip = $this->zipper();
			$folder->addToZip($zip);
			$zip->finish();
			
			rename($zip->filename(), $to);
		}

		private function zipper() {
			require_once('include/filesystem/zip/MollifyZip.class.php');
			$zipper = $this->env->settings()->setting("zipper", TRUE);
			
			if (strcasecmp($zipper, "ziparchive") === 0) {
				require_once('include/filesystem/zip/MollifyZipArchive.class.php');
				return new MollifyZipArchive($this->env);
			} else if (strcasecmp($zipper, "native") === 0) {
				require_once('include/filesystem/zip/MollifyZipNative.class.php');
				return new MollifyZipNative($this->env);
			} else if (strcasecmp($zipper, "raw") === 0) {
				require_once('include/filesystem/zip/MollifyZipRaw.class.php');
				return new MollifyZipRaw($this->env);
			}
			
			throw new ServiceException("INVALID_CONFIGURATION", "Unsupported zipper configured: ".$zipper);
		}
		
		public function __toString() {
			return "ArchiverManager";
		}
	}
?>
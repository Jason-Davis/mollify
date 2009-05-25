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

	function init_configuration_provider() {}
	
	function get_configuration_settings() {
		return array(
			'permission_update' => TRUE,
			'description_update_default' => FALSE
		);
	}
	
	function on_session_start($user_id, $username) {
	}

	function find_user($username, $password) {
		global $USERS, $PASSWORDS_HASHED;
			
		foreach($USERS as $id => $user) {
			if ($user["name"] != $username)
				continue;
				
			$pw = $user["password"];
			if (!isset($PASSWORDS_HASHED) or $PASSWORDS_HASHED != TRUE) {
				$pw = md5($pw);
			}

			if ($pw != $password) {
				log_error("Invalid password for user [".$user["name"]."]");
			 	return FALSE;
			}
			
			return array("id" => $id, "name" => $user["name"]);
		}
		
		log_error("No user found with name [".$username."]");
		return FALSE;
	}
	
	function get_user($id) {
		if ($id === "") return FALSE;
		global $USERS;
		return $USERS[$id];
	}
	
	function get_default_user_permission_mode($user_id = "") {
		global $USERS, $FILE_PERMISSION_VALUE_ADMIN, $FILE_PERMISSION_VALUE_READWRITE, $FILE_PERMISSION_VALUE_READONLY, $FILE_PERMISSION_MODE;
		
		if ($user_id === "") {
			if (!isset($FILE_PERMISSION_MODE)) return $FILE_PERMISSION_VALUE_READONLY;
			$mode = strtoupper($FILE_PERMISSION_MODE);
		} else {
			if (!isset($USERS[$user_id]["file_permission_mode"])) return $FILE_PERMISSION_VALUE_READONLY;
			$mode = strtoupper($USERS[$user_id]["file_permission_mode"]);
		}

		if ($mode != $FILE_PERMISSION_VALUE_ADMIN and $mode != $FILE_PERMISSION_VALUE_READWRITE and $mode != $FILE_PERMISSION_VALUE_READONLY) {
			if ($user_id === "") log_error("Invalid file permission mode [".$mode."]. Falling back to default.");
			else log_error("Invalid file permission mode ".$mode." for user [".$user_id."]. Falling back to default.");
			return $FILE_PERMISSION_VALUE_READONLY;
		}
		return $mode;
	}

	function authentication_required() {
		global $USERS;
		return ($USERS != FALSE and count($USERS) > 0);
	}
	
	function get_user_root_directories($user_id) {
		global $USERS, $PUBLISHED_DIRECTORIES;

		if (count($USERS) === 0) {
			return $PUBLISHED_DIRECTORIES;
		} else {
			return $PUBLISHED_DIRECTORIES[$user_id];
		}
	}
	
	function get_file_description($file) {
		$path = dirname($file["path"]);
		$name = basename($file["path"]);
		$descriptions = _get_descriptions($path);
		
		if (!isset($descriptions[$name])) return NULL;
		return $descriptions[$name];
	}
	
	function get_dir_description($dir) {
		$path = dirname($dir["path"]);
		$name = basename($dir["path"]);
		$descriptions = _get_descriptions($path);
		
		if (!isset($descriptions[$name])) return NULL;
		return $descriptions[$name];
	}
	
	function set_item_description($item, $description) {
		$path = dirname($item["path"]);
		$name = basename($item["path"]);
	
		$descriptions = _get_descriptions($path);
		$descriptions[$name] = $description;
		return _write_descriptions_to_file(_get_description_filename($path), $descriptions);
	}

	function remove_item_description($item, $recursively = FALSE) {
		# we can ignore recursive flag, file "descript.ion" will be removed automatically when folder is removed		
		$path = dirname($item["path"]);
		$name = basename($item["path"]);
		
		$descriptions = _get_descriptions($path);
		if (!isset($descriptions[$name])) return TRUE;
		
		unset($descriptions[basename($name)]);
		return _write_descriptions_to_file(_get_description_filename($path), $descriptions);
	}
	
	function move_item_description($from, $to, $recursively = FALSE) {
		$from_path = dirname($from["path"]);
		$from_name = basename($from["path"]);

		$to_path = dirname($to["path"]);
		$to_name = basename($to["path"]);
		
		$from_descriptions = _get_descriptions($from_path);
		if (!isset($from_descriptions[$from_name])) return TRUE;
		
		$description = $from_descriptions[$from_name];
		unset($from_descriptions[$from_name]);
		
		if ($to_path === $from_path) $from_descriptions[$to_name] = $description;
		if (!_write_descriptions_to_file(_get_description_filename($from_path), $from_descriptions)) return FALSE;
		
		if ($to_path != $from_path) {
			$to_descriptions = _get_descriptions($to_path);
			$to_descriptions[$to_name] = $description;
			return _write_descriptions_to_file(_get_description_filename($to_path), $to_descriptions);
		}
		return TRUE;
	}
	
	function _get_description_filename($path) {
		return $path.DIRECTORY_SEPARATOR."descript.ion";
	}
	
	function _get_descriptions($path) {
		return _get_descriptions_from_file(_get_description_filename($path));
	}
	
	function _get_descriptions_from_file($descript_ion) {
		$result = array();
		if (!file_exists($descript_ion)) return $result;
	
		$handle = @fopen($descript_ion, "r");
		if (!$handle) return $result;
		
	    while (!feof($handle)) {
	        $line = fgets($handle, 4096);

			// check for quote marks (")
			if (ord(substr($line, 0, 1)) === 34) {
				$line = substr($line, 1);
				$split = strpos($line, chr(34));
			} else {
	        	$split = strpos($line, ' ');
			}
			if ($split <= 0) continue;

			$name = trim(substr($line, 0, $split));
			$desc = str_replace('\n', "\n", trim(substr($line, $split + 1)));
			$result[$name] = $desc;
	    }
	    fclose($handle);
		
		return $result;
	}

	function _write_descriptions_to_file($descript_ion, $descriptions) {
		if (file_exists($descript_ion)) {
			if (!is_writable($descript_ion)) {
				log_error("Description file (".$descript_ion.") is not writable");
				return FALSE;
			}
		} else {
			$dir = dirname($descript_ion);
			if (!is_writable($dir)) {
				log_error("Directory for description file (".$dir.") is not writable");
				return FALSE;
			}
		}
	
		$handle = @fopen($descript_ion, "w");
		if (!$handle) return FALSE;
		
		foreach($descriptions as $name => $description)
			fwrite($handle, sprintf('"%s" %s', $name, str_replace("\n", '\n', $description))."\n");

		fclose($handle);
		
		return TRUE;
	}
		
	function get_item_permission($item, $user_id) {
		$path = $item["path"];

		$items = array();
		if (!is_dir($path)) {
			$items[] = basename($path);
			$path = dirname($path);
		}
		$items[] = ".";	// lookup also folder rights, its default for files unless specific rights is defined
		
		$uac_file = $path.DIRECTORY_SEPARATOR."mollify.uac";
		$permissions = _get_permissions_from_file($uac_file, $user_id, $items);
		
		foreach($items as $id)
			if (array_key_exists($id, $permissions)) return $permissions[$id];
		
		return FALSE;		
	}
	
	function _get_permissions_from_file($uac_file, $for_user_id, $items = NULL) {
		$result = array();
		if (!file_exists($uac_file)) return $result;
	
		$handle = @fopen($uac_file, "r");
		if (!$handle) return $result;
		
		global $FILE_PERMISSION_VALUE_READWRITE, $FILE_PERMISSION_VALUE_READONLY;
		$line_nr = 0;
	    while (!feof($handle)) {
	        $line = fgets($handle, 4096);
			$line_nr = $line_nr + 1;
			
			$parts = explode(chr(9), $line);
			if (count($parts) < 2) return $result;
			
			// results
			$file = trim($parts[0]);
			// if requested only for specific items, skip if not the one of them
			if ($items == NULL or !in_array($file, $items)) continue;
			
			$data = trim($parts[count($parts) - 1]);
			
			$permissions = _parse_permission_string($data);
			if (!$permissions) {
				log_error("Invalid file permission definition in file [".$uac_file."] at line ".$line_nr);
				continue;
			}
			
			$permission = _get_active_permission($permissions, $for_user_id);
			// ignore lines that don't apply to current user
			if (!$permission) continue;
			
			// ignore invalid permissions
			if ($permission != $FILE_PERMISSION_VALUE_READWRITE and $permission != $FILE_PERMISSION_VALUE_READONLY) {
				log_error("Invalid file permission definition [".$permission."] in file [".$uac_file."] at line ".$line_nr);
				continue;
			}
			
			$result[$file] = $permission;
			
			// if requested only for specific items, stop once all of them are found
			if ($items != NULL and count($result) === count($items)) break;
	    }
	    fclose($handle);
		
		return $result;
	}
	
	function _parse_permission_string($string) {
		$result = array();
		if (strlen($string) < 1) return $result;
		
		$parts = explode(',', $string);
		if (count($parts) < 1) return $result;
		
		foreach($parts as $part) {
			$value_parts = explode('=', $part);
			if (count($value_parts) != 2) return FALSE;

			$id = trim($value_parts[0]);
			$permission = strtoupper(trim($value_parts[1]));
			if (strlen($id) == 0 or strlen($permission) == 0) return FALSE;

			$result[$id] = $permission;
		}
		return $result;
	}
	
	function _get_active_permission($permissions, $user_id) {
		if ($user_id != "" and isset($permissions[$user_id])) return $permissions[$user_id];
		if (isset($permissions["*"])) return $permissions["*"];
		return FALSE;
	}
?>
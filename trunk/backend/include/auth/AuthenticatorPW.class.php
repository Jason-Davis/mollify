<?php
	/**
	 * AuthenticatorPW.class.php
	 *
	 * Copyright 2008- Samuli Järvelä
	 * Released under GPL License.
	 *
	 * License: http://www.mollify.org/license.php
	 */

	class Mollify_Authenticator_PW extends Mollify_Authenticator {
		private $env;
		
		public function __construct($env) {
			$this->env = $env;
		}
		
		public function authenticate($user, $pw, $auth) {
			if ($auth["salt"] == "-" and $auth["hash"] == "-") {
				$oldPw = $this->env->configuration()->getUserLegacyPw($user["id"]);
				// old pw auth
				if (strcmp($oldPw, md5($pw)) != 0) throw new ServiceException("AUTHENTICATION_FAILED");
				
				//convert old pws into hash
				Logging::logDebug("Adding new user hash for ".$user["id"]);
				$this->env->configuration()->storeUserAuth($user["id"], $user["name"], 'pw', $pw);
				$this->env->configuration()->removeUserLegacyPw($user["id"]);
				return;
			}
			if ($this->env->passwordHash()->isEqual($pw, $auth["hash"], $auth["salt"])) return;
			throw new ServiceException("AUTHENTICATION_FAILED");
		}
	}
?>
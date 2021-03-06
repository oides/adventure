var AuthProxy = function AuthProxy() {
    this.url = "api/auth";
};

AuthProxy.prototype.login = function($credentials, $success, $error) {
    $.ajax({
	type : "POST",
	url : this.url,
	data : JSON.stringify($credentials),
	contentType : "application/json",
	success : function(data) {
	    if ($success) {
		$success(data);
	    }
	},
	error : function(request) {
	    if ($error) {
		$error(request);
	    }
	}
    });
};

AuthProxy.prototype.logout = function($success, $error) {
    $.ajax({
	type : "DELETE",
	url : this.url,
	success : function(data) {
	    if ($success) {
		$success(data);
	    }
	},
	error : function(request) {
	    if ($error) {
		$error(request);
	    }
	}
    });
};

AuthProxy.prototype.facebookLogin = function(code, $success, $error) {
    $.ajax({
	type : "POST",
	url : this.url + "/facebook",
	data : "code=" + code,
	processData : false,
	success : function(data) {
	    if ($success) {
		$success(data);
	    }
	},
	error : function(request) {
	    if ($error) {
		$error(request);
	    }
	}
    });
};

AuthProxy.prototype.googleLogin = function(code, $success, $error) {
    $.ajax({
	type : "POST",
	url : this.url + "/google",
	data : "code=" + code,
	processData : false,
	success : function(data) {
	    if ($success) {
		$success(data);
	    }
	},
	error : function(request) {
	    if ($error) {
		$error(request);
	    }
	}
    });
};

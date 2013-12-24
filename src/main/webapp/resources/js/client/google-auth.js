var GoogleAuthClient = function GoogleAuthClient() {
	this.url = "api/auth/google";
};

GoogleAuthClient.prototype.login = function(code, $successCallback, $errorCallback) {
	$.ajax({
		type : "POST",
		url : this.url,
		data : "code=" + code,
		processData : false,
		success : function(data) {
			if ($successCallback) {
				$successCallback(data);
			}
		},
		error : function(data) {
			if ($errorCallback) {
				$errorCallback(data);
			}
		}
	});
};
$(document).ready(function(){
	var inputName = $('#nameInput');
	var inputUsername = $('#usernameInput');
	var inputPassword = $('#passwordInput');
	
	$('#registerSubmit').on('click', function(event){
		
		var name = inputName.val();
		var username = inputUsername.val();
		var password = inputPassword.val();
		var role = "KOMENTATOR";
		
		var param = {
				'name': name,
				'username': username,
				'password': password,
				'role': role
		}
		
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url :"http://localhost:8080/api/users",
			data :  JSON.stringify(param),
			dataType : 'json',
			success : function(result) {
				window.location.replace('index.html');
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
		event.preventDefault();
		return false;
	});
	
	
});
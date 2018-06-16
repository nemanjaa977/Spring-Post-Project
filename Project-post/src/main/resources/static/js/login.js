$(document).ready(function(){
	var usernameInput = $('#userNameInput');
	var passwordInput = $('#passwordInput');
	var nav = $('.navBar');
	
	nav.append("<a href='register.html'><i class='fa fa-user-plus'></i> Register </a>");
	
	$('#loginSubmit').on('click', function(event){
		var username = usernameInput.val();
		var password = passwordInput.val();
		
		$.get("http://localhost:8080/api/users/user/"+username,{},function(data){
				if(data.username == username && data.password == password){
					window.location.replace('home.html');
				}
				
		}).fail(function(){
		alert("Wrong username or password!");
		});
	});
	
});
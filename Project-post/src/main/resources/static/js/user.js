$(document).ready(function(){
	var isOwner=false;
	var nav = $('.navBar');
	var userDiv = $('#glavniUser');
	var editUserr = $('#editUserProfile');
	var username = window.location.search.slice(1).split('&')[0].split('=')[1];
	var nameEdit = $('#nameInputEdit');
	var usernameEdit = $('#usernameInputEdit');
	var passwordEdit = $('#passwordInputEdit');
	var currentUser = null;
	var currentUserDelete = null;
	
	var logged=JSON.parse(localStorage.getItem("loggedUser"));
	
	nav.append("<a href='home.html'><i class='fa fa-home'></i> Home </a>");
	
	$.get("http://localhost:8080/api/users/user/"+username,{},function(data){
		console.log(data);
		currentUserDelete = data;
		$('#usernameUser').text("Username:  "+data.username);
		$("#nameUser").text(data.name);
		if(logged.username != data.username){
			isOwner=true;
		}
		
		if(logged.username != data.username && logged.role != "ADMIN"){
			$('#editButton').hide();
		}
		
		if(logged.role == "ADMIN"){
			$('#deleteButton').show();
		}else{
			$('#deleteButton').hide();
		}
		
		if(logged.role == "ADMIN" && logged.username == data.username){
			$('#deleteButton').hide();
		}
	});
	
	$('body').on('click', '#editButton',function(event){
		editUserr.fadeIn();
		$.get("http://localhost:8080/api/users/user/"+username,{},function(data){
			console.log(data);
			currentUser = data;
			nameEdit.val(data.name);
			usernameEdit.val(data.username);
			passwordEdit.val(data.password);
			
			if(logged.username != data.username){
				$('#labelPass').hide();
				$('#passwordInputEdit').hide();
			}
		}).fail(function(){
		alert("You can't find the user with the selected username!");
		});
		
		$('body').on('click', '#editSubmitUser',function(event){
			var n = nameEdit.val();
			var u = usernameEdit.val();
			var p = passwordEdit.val();
			
			var param = {
					'name': n,
					'username': u,
					'password': p,
					'role':currentUser.role
			}
			
			$.ajax({
				type : "PUT",
				contentType : "application/json",
				url :"http://localhost:8080/api/users/"+currentUser.id,
				data :  JSON.stringify(param),
				dataType : 'json',
				success : function(data) {
					if(isOwner){
						localStorage.removeItem("loggedUser");
						localStorage.setItem("loggedUser", JSON.stringify(logged));
					}
					var location="user.html?username="+data.username;
					window.location.replace(location);
				},
				error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
			});
		});
		
		event.preventDefault();
		return false;
	});
	
	$('body').on('click', '#clickk',function(event){
		editUserr.fadeOut("slow");
		
		event.preventDefault();
		return false;
	});
	
	$('body').on('click', '#deleteButton',function(event){
		$.ajax({
			type : "DELETE",
			url :"http://localhost:8080/api/users/"+currentUserDelete.id,
			dataType : 'json',
			success : function() {
				var location="home.html";
				window.location.replace(location);
			},
			error : function(e) {
				var location="home.html";
				window.location.replace(location);
				console.log("ERROR: ", e);
			}
		});
		
		event.preventDefault();
		return false;
	});

});
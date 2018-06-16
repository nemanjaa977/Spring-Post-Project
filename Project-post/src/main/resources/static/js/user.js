$(document).ready(function(){
	var nav = $('.navBar');
	var userDiv = $('#glavniUser');
	var username = window.location.search.slice(1).split('&')[0].split('=')[1];
	
	nav.append("<a href='home.html'><i class='fa fa-home'></i> Home </a>";
	
	$.get("http://localhost:8080/api/users/user/"+username,{},function(data){
		
		userDiv.append("<p id='usernameUser'>Username: "+data.username+"</p>" +
				   		"<p id='nameUser'>"+data.name+"</p>" +
				   		"<button id='editButton'>Edit profile</button>");
	});
});
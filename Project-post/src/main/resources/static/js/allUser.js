$(document).ready(function(){
	var nav = $('.navBar');
	var row = $('.row');
	
	nav.append("<a href='home.html'><i class='fa fa-home'></i> Home </a>");
	
	$.get("http://localhost:8080/api/users",{},function(data){
		for(var i=0; i<data.length; i++){
			user = data[i];
			row.append("<div class='column'> " + 
					"<img alt='User photo' src='photo/admin.png' id='userPhoto'><br>" +
  					"<a id='usernameUser' href='user.html?id="+user.username+"'>"+user.username+"</a>" +
  					"<p id='nameUser'>"+user.name+"</p>" +
  					"<p id='roleUser'>"+user.role+"</p>" +
  				"</div>");
		}
	});
	
});
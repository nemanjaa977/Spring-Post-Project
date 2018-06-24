$(document).ready(function(){
	var row = $('.row');
	var nav = $('.navBar');
	var newPost = $('.newPost');
	var logged=JSON.parse(localStorage.getItem("loggedUser"));
	console.log(logged);
	
	nav.append("<a href='home.html'><i class='fa fa-home'></i> Home </a>" +
			"<a href='user.html?username="+logged.username+"'><i class='fa fa-user-o'></i> Profile</a>" +
			"<button id='buttonLogOut'><i class='fa fa-angle-double-down'></i> Log out</button>");
	
	if(logged.role != "KOMENTATOR"){
		newPost.append("<a id='newPostLink' href='createPost.html'><i class='fa fa-plus'></i> Create new post</a>");
	}
	
	if(logged.role == "ADMIN"){
		newPost.append("<a id='newUserLink' href='createUser.html'><i class='fa fa-plus'></i> Add new user</a>" +
				"<a id='allUserLink' href='allUser.html'><i class='fa fa-address-card-o'></i> View all user</a>");
	}

	$.get("http://localhost:8080/api/posts",{},function(data){
		for(var i=0;i<data.length;i++){
			post=data[i];
			row.append("<div class='column'> " + 
  					"<a id='titlePost' href='post.html?id="+post.id+"'>"+post.title+"</a>" +
  					"<p id='descriptionPost'>"+post.description+"</p>" +
  					"<a id='userPost' href='user.html?username="+post.userDTO.username+"'>"+post.userDTO.username+"</a>" +
  				"</div>");
		}
	});
	
	$('body').on('click', '#buttonLogOut',function(event){
		localStorage.removeItem("loggedUser");
		window.location.replace('index.html');
		
		event.preventDefault();
		return false;
	});
	
	$(document).on('click',"#okSort", function(event){
			var sortBy=$(".nameSort").val();
				$.get("http://localhost:8080/api/posts/order/"+sortBy,{},function(data){
					row.empty();
					for(var i=0;i<data.length;i++){
						post=data[i];
						row.append("<div class='column'> " + 
			  					"<a id='titlePost' href='post.html?id="+post.id+"'>"+post.title+"</a>" +
			  					"<p id='descriptionPost'>"+post.description+"</p>" +
			  					"<a id='userPost' href='user.html?username="+post.userDTO.username+"'>"+post.userDTO.username+"</a>" +
			  				"</div>");
					}
				});
			
		
		event.preventDefault();
		return false;
	});
	
	$('body').on('click', '#okSearchButton',function(event){
		console.log("ddd");
		var preuzietiText = $('#inputAuthor').val();
		$.get("http://localhost:8080/api/posts/search/"+preuzietiText,{},function(data){
			row.empty();
			for(var i=0;i<data.length;i++){
				post=data[i];
				row.append("<div class='column'> " + 
	  					"<a id='titlePost' href='post.html?id="+post.id+"'>"+post.title+"</a>" +
	  					"<p id='descriptionPost'>"+post.description+"</p>" +
	  					"<a id='userPost' href='user.html?username="+post.userDTO.username+"'>"+post.userDTO.username+"</a>" +
	  				"</div>");
			}
		});
		
		event.preventDefault();
		return false;
	});
	
});
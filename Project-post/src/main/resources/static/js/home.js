$(document).ready(function(){
	var row = $('.row');
	var nav = $('.navBar');
	var newPost = $('.newPost');
	
	nav.append("<a href='home.html'><i class='fa fa-home'></i> Home </a>");
	
	newPost.append("<a id='newPostLink' href=''><i class='fa fa-plus'></i> Create new post</a>");
	
	$.get("http://localhost:8080/api/posts",{},function(data){
		for(var i=0;i<data.length;i++){
			post=data[i];
			row.append("<div class='column'> " + 
  					"<a id='titlePost' href='post.html?id="+post.id+"'>"+post.title+"</a>" +
  					"<p id='descriptionPost'>"+post.description+"</p>" +
  					"<a id='userPost' href='user.html?username="+post.userDTO.username+"'>"+post.userDTO.username+"</a>" +
  					"<p id='datePost'>"+post.date+"</p>" +
//  					ostala slika
  				"</div>");
		}
	});
	
	
});
$(document).ready(function(){
	var nav = $('.navBar');
	var inputTitle = $('#titleInputEdit');
	var inputDescription = $('#descriptionInputEdit');
	var postID = window.location.search.slice(1).split('&')[0].split('=')[1];
	var currentPost = null;
	
	var logged=JSON.parse(localStorage.getItem("loggedUser"));
	console.log(logged);
	
	nav.append("<a href='home.html'><i class='fa fa-home'></i> Home </a>");
	
	$.get("http://localhost:8080/api/posts/"+postID,{},function(data){
		console.log(data);
		currentPost = data;
		inputTitle.val(data.title);
		inputDescription.val(data.description);
	}).fail(function(){
	alert("You can't find the post with the selected id!");
	});
	
	$('#editSubmit').on('click', function(event){
		
		var t = inputTitle.val();
		var d = inputDescription.val();
		var date = new Date();
		var like = currentPost.like;
		var dislike = currentPost.dislike;
		var longitude = currentPost.longitude;
		var latitude = currentPost.latitude;
		
		var param = {
				'title': t,
				'description': d,
				'date': date,
				'like': like,
				'dislike': dislike,
				'longitude': longitude,
				'latitude': latitude,
				'userDTO': currentPost.userDTO
		}
		
    	$.ajax({
			type : "PUT",
			contentType : "application/json",
			url :"http://localhost:8080/api/posts/" + currentPost.id,
			data :  JSON.stringify(param),
			dataType : 'json',
			success : function(result) {
				window.location.replace("home.html");
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
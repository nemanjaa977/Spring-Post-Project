$(document).ready(function(){
	var nav = $('.navBar');
	var inputTitle = $('#titleInput');
	var inputDescription = $('#descriptionInput');
	var inputTag = $('#tagInput');
	
	
	var logged=JSON.parse(localStorage.getItem("loggedUser"));
	console.log(logged);
	
	nav.append("<a href='home.html'><i class='fa fa-home'></i> Home </a>");
	
	$('#createSubmit').on('click', function(event){
		var tagId;
		var postId;
		var tagg = inputTag.val();
		var param = {
				'name': tagg
		}
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url :"http://localhost:8080/api/tag",
			data :  JSON.stringify(param),
			dataType : 'json',
			success : function(data) {
				console.log('Success!');
				tagId = data.id;
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
		
		var title = inputTitle.val();
		var description = inputDescription.val();
		var date = new Date();
		var like = 0;
		var dislike = 0;
		var longitude = 0;
		var latitude = 0;
		
		var param = {
				'title': title,
				'description': description,
				'date': date,
				'like': like,
				'dislike': dislike,
				'longitude': longitude,
				'latitude': latitude,
				'userDTO': logged
		}
		
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url :"http://localhost:8080/api/posts",
			data :  JSON.stringify(param),
			dataType : 'json',
			success : function(data) {
				postId = data.id;
				window.location.replace('home.html');
				$.ajax({
					type : "PUT",
					contentType : "application/json",
					url :"http://localhost:8080/api/posts/setTags/"+postId+"/"+tagId,
//					data :  JSON.stringify(),
					dataType : 'json',
					success : function(result) {
						console.log('Success added!');
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});
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
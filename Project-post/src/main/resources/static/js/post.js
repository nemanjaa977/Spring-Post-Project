var currentPost=null;
var allComments=null;

$(document).ready(function(){
	var nav = $('.navBar');
	var glavniPost = $('#glavniPost');
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	var newCommentDiv = $('#newComment');
	var allCommentDiv = $('#allComment');
	
	
	nav.append("<a href='home.html'><i class='fa fa-home'></i> Home </a>");
	
	$.get("http://localhost:8080/api/posts/"+id,{},function(data){
		currentPost=data;
		var date = new Date(data.date);
		glavniPost.append("<p id='titlePost'>"+data.title+"</p>" +
						  "<p id='descriptionPost'>"+data.description+"</p><br>" +
						  "<a id='userPost' href='user.html?username="+data.userDTO.username+"'>"+data.userDTO.username+"</a>" +
						  "<p id='datePost'>"+date.getDate()+'/'+(date.getMonth()+1)+'/'+date.getFullYear()+"</p><br>" + 
						  "<button id='likePostClickk' onClick='likeClick()'><i class='fa fa-thumbs-o-up' id='likePostClick'></i></button>" +
						  "<p id='likePost'>"+data.like+"</p>" +
						  "<button id='dislikePostClickk' onClick='dislikeClick()'><i class='fa fa-thumbs-o-down' id='dislikePostClick'></i></button>" +
						  "<p id='dislikePost'>"+data.dislike+"</p>");
	});
	
	newCommentDiv.append("<input type='text' id='inputCommentTitle' placeholder='Add new title comment ...'>" +
						 "<input type='text' id='inputComment' placeholder='Add new description comment ...'>" + 
						 "<input type='button' id='buttonNewComment' value='Add'>");

	$.get("http://localhost:8080/api/comment/post/"+id,{},function(data){
		allComments=data;
		for(var i=0;i<data.length;i++){
			comment = data[i];
			allCommentDiv.append("<div id='oneComment'>" +
								 	"<p id='commentTitle'>"+comment.title+"</p>" +
								 	"<p id='commentDescription'>"+comment.description+"</p>" +
								 	"<a id='userPostUsername' href='user.html?username="+comment.userDTO.username+"'>"+comment.userDTO.username+"</a>" +
								 	"<p id='commentDate'>"+comment.date+"</p><br>" +
								 	"<button name='"+comment.id+"' id='likeCom'><i class='fa fa-thumbs-o-up' id='likeComment'></i></button>" +
								 	"<p id='commentLike' class='"+comment.id+"' >"+comment.like+"</p>" +
								 	"<button name='"+comment.id+"' id='dislikeCom'><i class='fa fa-thumbs-o-down' id='dislikeComment'></i></button>" +
								 	"<p id='commentDislike' class='"+comment.id+"'>"+comment.dislike+"</p>" +
								 "</div>");
		}
	});	
	
	$('body').on('click', 'button#likeCom',function(event){
		var commId=$(this).attr('name');
		var comment=allComments.find(x => x.id == commId);
		comment.like++;
		console.log(comment);
		$.ajax({
			type : "PUT",
			contentType : "application/json",
			url :"http://localhost:8080/api/comment/"+commId,
			data :  JSON.stringify(comment),
			dataType : 'json',
			success : function(data) {
				var select='#commentLike.'+comment.id;
				console.log(select);
				$(select).text(data.like);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
		
		event.preventDefault();
		return false;
	});
	
	$('body').on('click', 'button#dislikeCom',function(event){
		var commId=$(this).attr('name');
		var comment=allComments.find(x => x.id == commId);
		comment.dislike++;
		console.log(comment);
		$.ajax({
			type : "PUT",
			contentType : "application/json",
			url :"http://localhost:8080/api/comment/"+commId,
			data :  JSON.stringify(comment),
			dataType : 'json',
			success : function(data) {
				var select='#commentDislike.'+comment.id;
				console.log(select);
				$(select).text(data.dislike);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
		
		event.preventDefault();
		return false;
	});
	
	var inputTextTitle = $('#inputCommentTitle');
	var inputText = $('#inputComment');
	$('#buttonNewComment').on('click', function(event){
		
		var text = inputText.val();
		var textTitle = inputTextTitle.val();
		
		$.post("http://localhost:8080/api/comment", {}, function(data){
			
		});
		
		event.preventDefault();
		return false;
	});	
});

function likeClick(){
	console.log(currentPost);
	currentPost.like++
	
	$.ajax({
		type : "PUT",
		contentType : "application/json",
		url :"http://localhost:8080/api/posts/"+currentPost.id,
		data :  JSON.stringify(currentPost),
		dataType : 'json',
		success : function(data) {
			$('#likePost').text(data.like);
		},
		error : function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});
}

function dislikeClick(){
	console.log(currentPost);
	currentPost.dislike++
	
	$.ajax({
		type : "PUT",
		contentType : "application/json",
		url :"http://localhost:8080/api/posts/"+currentPost.id,
		data :  JSON.stringify(currentPost),
		dataType : 'json',
		success : function(data) {
			$('#dislikePost').text(data.dislike);
		},
		error : function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});
}
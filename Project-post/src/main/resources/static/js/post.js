var currentPost=null;
var allComments=null;
var commentID = null;

$(document).ready(function(){
	var nav = $('.navBar');
	var glavniPost = $('#glavniPost');
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	var newCommentDiv = $('#newComment');
	var allCommentDiv = $('#allComment');
	var editPostt = $('.editPostAnd');
	var edittTitle = $('#editTiltleComm');
	var edittDescription = $('#editDescriptionComm');
	
	var logged=JSON.parse(localStorage.getItem("loggedUser"));
	console.log(logged);
	
	nav.append("<a href='home.html'><i class='fa fa-home'></i> Home </a>");
	
	if(logged.role != "KOMENTATOR"){
		editPostt.append("<a id='editPostLink' href='editPost.html?id="+id+"'><i class='fa fa-edit'></i> Edit post</a>" +
				"<button id='deleteButtonPost'><i class='fa fa-trash'></i> Delete post</button>");
	}
	
	//prikaz posta
	$.get("http://localhost:8080/api/posts/"+id,{},function(data){
		console.log(data);
		currentPost=data;
		var date = new Date(data.date);
		glavniPost.append("<p id='titlePost'>"+data.title+"</p>" +
						  "<img src='photo/post.jpg' alt='News photo' id='photos'>" +
						  "<p id='descriptionPost'>"+data.description+"</p><br>" +
						  "<a id='userPost' href='user.html?username="+data.userDTO.username+"'>"+data.userDTO.username+"</a>" +
						  "<p id='datePost'>"+date.getDate()+'/'+(date.getMonth()+1)+'/'+date.getFullYear()+"</p>" + 
						  "<p id='taggovi'></p><br>" +
						  "<button id='likePostClickk' onClick='likeClick()'><i class='fa fa-thumbs-o-up' id='likePostClick'></i></button>" +
						  "<p id='likePost'>"+data.like+"</p>" +
						  "<button id='dislikePostClickk' onClick='dislikeClick()'><i class='fa fa-thumbs-o-down' id='dislikePostClick'></i></button>" +
						  "<p id='dislikePost'>"+data.dislike+"</p>");
		if(logged.username != data.userDTO.username && logged.role == "OBJAVLJIVAC"){
			$('#editPostLink').hide();
			$('#deleteButtonPost').hide();
		}
	});
	
	//prikaz taga
	$.get("http://localhost:8080/api/tag/post/"+id,{},function(data){
		console.log(data);
		for(var i=0; i<data.length; i++){
			xx = data[i];
			$('#taggovi').text(xx.name);
		}
	});
	
	newCommentDiv.append("<input type='text' id='inputCommentTitle' placeholder='Add new title comment ...'>" +
						 "<input type='text' id='inputComment' placeholder='Add new description comment ...'>" + 
						 "<input type='button' id='buttonNewComment' value='Add'>");
	
	//prikaz komentara
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
								 	"<button id='eeditComment' name='"+comment.id+"'><i class='fa fa-edit'></i> Edit comment</button>" + 
								 	"<button id='ddeleteComment' name='"+comment.id+"'><i class='fa fa-trash'></i> Delete comment</button>" +
								 "</div>");
		}
		if(logged.role != "ADMIN"){
			console.log(logged.role);
			$('body #eeditComment').hide();
			$('body #ddeleteComment').hide();
		}
		
	});	
	
	//komentar like
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
	
	//komentar dislike
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
	
	var inputText = $('#inputComment');
	var inputTextTitle = $('#inputCommentTitle');
	
	//dodavanje komentara
	$('body').on('click', '#buttonNewComment',function(event){
		var text = inputText.val();
		var textTitle = inputTextTitle.val();
		var numberLike = 0;
		var numberDislike = 0;
		var date = new Date();
		
		var sadrzaj = {
				'title': text,
				'description': textTitle,
				'date': date,
				'like': numberLike,
				'dislike': numberDislike,
				'postDTO': currentPost,
				'userDTO': logged
		}
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url :"http://localhost:8080/api/comment/",
			data :  JSON.stringify(sadrzaj),
			dataType : 'json',
			success : function(data) {
				allCommentDiv.append("<div id='oneComment'>" +
					 	"<p id='commentTitle'>"+data.title+"</p>" +
					 	"<p id='commentDescription'>"+data.description+"</p>" +
					 	"<a id='userPostUsername' href='user.html?username="+data.userDTO.username+"'>"+data.userDTO.username+"</a>" +
					 	"<p id='commentDate'>"+data.date+"</p><br>" +
					 	"<button name='"+data.id+"' id='likeCom'><i class='fa fa-thumbs-o-up' id='likeComment'></i></button>" +
					 	"<p id='commentLike' class='"+data.id+"' >"+data.like+"</p>" +
					 	"<button name='"+data.id+"' id='dislikeCom'><i class='fa fa-thumbs-o-down' id='dislikeComment'></i></button>" +
					 	"<p id='commentDislike' class='"+data.id+"'>"+data.dislike+"</p>" +
					 	"<button id='eeditComment'><i class='fa fa-edit'></i> Edit comment</button>" + 
					 	"<button id='ddeleteComment'><i class='fa fa-trash'></i> Delete comment</button>" +
					 "</div>");
				//dodam ceo objekat data, u listu allComments
				allComments.push(data);
				console.log(allComments);
				inputText.val("");
				inputTextTitle.val("");
				if(logged.role != "ADMIN"){
					console.log(logged.role);
					$('body #eeditComment').hide();
					$('body #ddeleteComment').hide();
				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
		
		event.preventDefault();
		return false;
	});
	
	if(logged.role != "ADMIN"){
		console.log(logged.role);
		$('body #eeditComment').hide();
		$('body #ddeleteComment').hide();
	}
	
	//brisanje posta
	$('body').on('click', '#deleteButtonPost',function(event){
		
		$.ajax({
			type : "DELETE",
			url :"http://localhost:8080/api/posts/"+id,
			dataType : 'json',
			success : function() {
				window.location.replace("home.html");
			},
			error : function(e) {
				window.location.replace("home.html");
				console.log("ERROR: ", e);
			}
		});
		
		event.preventDefault();
		return false;
	});
	
	$('body').on('click', '#eeditComment',function(event){
		commentID = $(this).attr('name');
		var comment=allComments.find(x => x.id == commentID);
		edittTitle.val(comment.title);
		edittDescription.val(comment.description);
		$('#editComentarDiv').fadeIn();
		
		event.preventDefault();
		return false;
	});
	
	$('body').on('click', '#hideClickk',function(event){
		$('#editComentarDiv').fadeOut();
		
		event.preventDefault();
		return false;
	});
	
	//izmena komentara
	$('body').on('click', '#saveEditComm',function(event){
		var comment=allComments.find(x => x.id == commentID);
		var oo = edittTitle.val();
		var bb = edittDescription.val();
		var date = new Date();
		var like = comment.like;
		var dislike = comment.dislike;
		var post = comment.postDTO;
		var user = comment.userDTO;
		var param = {
				'title': oo,
				'description': bb,
				'date': date,
				'like': like,
				'dislike': dislike,
				'postDTO': post,
				'userDTO': user
		};
		console.log(comment);
		allComments.splice(comment);
		$.ajax({
			type : "PUT",
			contentType : "application/json",
			url :"http://localhost:8080/api/comment/"+commentID,
			data :  JSON.stringify(param),
			dataType : 'json',
			success : function(data) {
				allComments.push(data);
				var replace = 'post.html?id='+id;
				window.location.replace(replace);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
		
		event.preventDefault();
		return false;
	});
	
	//brisanje komentara
	$('body').on('click', '#ddeleteComment',function(event){
		commentID = $(this).attr('name');
		$.ajax({
			type : "DELETE",
			url :"http://localhost:8080/api/comment/"+commentID,
			dataType : 'json',
			success : function() {
				var replace = 'post.html?id='+id;
				window.location.replace(replace);
			},
			error : function(e) {
				var replace = 'post.html?id='+id;
				window.location.replace(replace);
				console.log("ERROR: ", e);
			}
		});
		
		event.preventDefault();
		return false;
	});
	
	$(document).on('click',"#okSort", function(event){
		var sortBy=$(".nameSort").val();
		$.get("http://localhost:8080/api/comment/order/"+sortBy+"/"+currentPost.id,{},function(data){
			allCommentDiv.empty();
			allComments=data;
			console.log(data);
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
									 	"<button id='eeditComment' name='"+comment.id+"'><i class='fa fa-edit'></i> Edit comment</button>" + 
									 	"<button id='ddeleteComment' name='"+comment.id+"'><i class='fa fa-trash'></i> Delete comment</button>" +
									 "</div>");
			}
			if(logged.role != "ADMIN"){
				console.log(logged.role);
				$('body #eeditComment').hide();
				$('body #ddeleteComment').hide();
			}
			
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
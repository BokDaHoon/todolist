(function (window) {
	'use strict';

	// Your starting point. Enjoy the ride!

	var displayItem = function() {
		$.ajax({ 
			type:"GET", 
			url: "/todo/selectTodoList",
			success : function(resData) {
				
				var tag = createElement(resData);
				$(".todo-list").html(tag.toString());
				eventBind();
				count_leftItem();
			}, 
			error : function(xhr, status, e) {
				console.log(e);
				alert("실패");
			} 
		});
	}
	
	var insert_data = function() {
		
		var todoValue = $("#new-todo").val();
		
		var param = {"todo" : todoValue};
		
		$.ajax({ 
			type:"POST", 
			url: "/todo/insert",
			data: param,
			success : function(resData) {
					alert("성공");
					$("#new-todo").val("");
				displayItem();
			}, 
			error : function(xhr, status, e) {
				console.log(e);
				alert("실패");
			} 
		});
	}
	
	var eventBind = function() {
		$("#new-todo").unbind("keyup").bind("keyup", function(e) {
			if (e.keyCode == 13 && $("#new-todo").val() != '') {
				insert_data();
				return;
			}
		});
		
		$(".toggle").click(function(e) {
			var target = $(e.target);
		
			var id = target.parent().next().val();
			var completed;
			
			if(target.attr("checked") == undefined) {
				target.attr("checked", true);
				completed = 1;
			} else {
				target.attr("checked", false);
				completed = 0;
			}
			
			$.ajax({ 
				type:"PUT", 
				url: "/todo/update",
				data: {
					'id' : id ,
					'completed' : completed
				},
				success : function(resData) {
					if (completed > 0) {
						target.closest("li").addClass("completed");
						target.closest("li").removeClass("active");
					} else {
						target.closest("li").addClass("active");
						target.closest("li").removeClass("completed");
					}
					
					count_leftItem();
				}, 
				error : function(xhr, status, e) {
					console.log(e);
					alert("실패");
				} 
			});
		});
		
		$(".destroy").click(function(e) {
			var target = $(e.target);
			var id = target.parent().next().val();
			
			$.ajax({ 
				type:"DELETE", 
				url: "/todo/" + id,
				success : function(resData) {
					target.closest("li").remove();
					count_leftItem();
				}, 
				error : function(xhr, status, e) {
					console.log(e);
					alert("실패");
				} 
			});
		});
		
		$(".filters>li>a").click(function(e) {
			$(".filters>li>a").removeClass("selected");
			$(e.target).addClass("selected");
			
			var click_text = $(e.target).text();
			
			if (click_text == "All") {
				$(".completed").show();
				$(".active").show();
			} else if (click_text == "Active") {
				$(".completed").hide();
				$(".active").show();
			} else if (click_text ==  "Completed") {
				$(".completed").show();
				$(".active").hide();
			} else {
				/* nothing */
			}
			
		});
		
	}
	
	var count_leftItem = function() {
		$.ajax({ 
			type:"GET", 
			url: "/todo/countLeftItem",
			success : function(resData) {
				$(".todo-count").find("strong").text(resData);
			}, 
			error : function(xhr, status, e) {
				console.log(e);
				alert("실패");
			} 
		});
	}
	
	var createElement = function(resData) {
		var tag = new StringBuilderEx();
		
		for (var index in resData) {
			var temp = resData[index];
			
			if (resData[index].completed > 0) {
				tag.append("<li class='completed'>");
				tag.append("<div class='view'>");
				tag.append("<input class='toggle' type='checkbox' checked>");
				tag.append("<label>");
			} else {
				tag.append("<li class='active'>");
				tag.append("<div class='view'>");
				tag.append("<input class='toggle' type='checkbox'>");
				tag.append("<label>");
			}
			
			tag.append(resData[index].todo);
			tag.append("</label>");
			tag.append("<button class='destroy'></button>");
			tag.append("</div>");
			tag.append("<input class='edit' value='");
			tag.append(resData[index].id);
			tag.append("'>");
			tag.append("</li>");
		}
		
		return tag.toString();
	}
	
	$("#clear-completed").click(function() {
		$.ajax({ 
			type:"DELETE", 
			url: "/todo/completedDataDelete",
			success : function(resData) {
				alert("완료한 일 삭제 성공");
				$(".completed").remove();
			}, 
			error : function(xhr, status, e) {
				console.log(e);
				alert("실패");
			} 
		});
	});
	
	displayItem();
	count_leftItem();
		
})(window);

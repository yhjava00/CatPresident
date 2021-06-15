<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script type="text/javascript">
			
			$(document).ready(function() {
				changeContent()
			})
		
			function changeContent() {
				$.ajax({
					url: 'main.myPage',
					async : true,
					type : 'post',
					dataType : 'html',
					cache: false
				}).done(function (data) {
					$('.htmlContent').children().remove()
					$('.htmlContent').html(data)
				})
			}
			
			/* function test() {
	    		$.ajax({
					type: 'post', 
					url: 'test.myPage', 
					data: {'test':'hello'},
					success:function (data) {
						
					},
					error:function () {
						alert('에러가 발생했습니다.');
					}
				})
	    	} */
		</script>
	</head>
	<body>
		
		<jsp:include page="./header.jsp"></jsp:include>
		
		<div>
	        <div class="a2038"></div>
	        <div class="htmlContent" style="position: relative; top: 168px;">
	        
	        </div>
	    </div>
		
	</body>
</html>
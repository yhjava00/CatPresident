
	 function check(){
	    		var user_id = $('#id').val();
	    		var user_pw = $('#pw').val();
	    		var t='아이디없음';
	    		var r='비밀번호틀림';
	    		
	    		$.ajax({
	    			url: 'login.member',
	    			data: { 'user_id': user_id,'user_pw' : user_pw },
	    			type: 'post',
	    			success: function(loginRs) {
	    				if(loginRs==-2){
	    					$("#loginMessage").text("아이디없음 .");
	    				}else if(loginRs==0){
	    					$("#loginMessage").text("비밀번호틀림 .");
	    				}else{
	    					// 로그인성공후 페이지 이동처리
	    					outProduct('main.main')
	    				}
	    			}, error: function() {
	    				console.log("실패");
	    			}
	    		});
	    	}
	 
        document.addEventListener('DOMContentLoaded', () => {

            var text = 0;
            $("#login_checkbox").on("click", function(e){
                if(text==0){
                	 $(".a5013").css("visibility","hidden");
                     $(".a5014").css("visibility","visible");
                    text=1 
                }else{                
                    $(".a5013").css("visibility","visible");
                    $(".a5014").css("visibility","hidden");
                    text=0;
                }
            });
            
            $(".a5006").on("focuce", function(e){
                $(".a5006").css("outline-style","unset");
                $(".a5006").css("outline-color","rgba(162, 0, 199, 1)");
                $(".a5006").css("outline-style","unset");
            });
        })
        
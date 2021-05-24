<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="resPath" value="view/member/resources"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
    	<link rel="stylesheet" href="${resPath}/login.css">
		<title>Insert title here</title>
		<script type="text/javascript">
			function login() {
				var f = document.f
				
				f.method="post"//post
				f.action="/test2";
				
				f.submit();
			}
		</script>
	</head>
	<body>
		
		<jsp:include page="../common/header.jsp"></jsp:include>
	
	    <div>
	        <div class="a2038"></div>
	        <div style="position: relative; top: 168px;">
	            <div class="MuiContainer-root a5001 MuiContainer-disableGutters MuiContainer-maxWidthLg">
			        <h2 class="MuiTypography-root a5002 MuiTypography-h2">로그인</h2>
			        <div class="a5003">
			            <form name="f">
			                <div class="MuiFormControl-root MuiTextField-root a5004 jss341 MuiFormControl-fullWidth">
			                    <div class="MuiInputBase-root MuiFilledInput-root a5005 MuiFilledInput-underline MuiInputBase-fullWidth MuiInputBase-formControl">
			                        <input aria-invalid="false" name="loginId" placeholder="아이디 또는 이메일을 입력해주세요." required="" class="MuiInputBase-input MuiFilledInput-input a5006" value="">
			                    </div>
			                </div>
			                <div class="MuiFormControl-root MuiTextField-root a5004 jss341 MuiFormControl-fullWidth">
			                    <div class="MuiInputBase-root MuiFilledInput-root a5005 MuiFilledInput-underline MuiInputBase-fullWidth MuiInputBase-formControl">
			                        <input aria-invalid="false" name="password" placeholder="비밀번호를 입력해주세요." required="" type="password" class="MuiInputBase-input MuiFilledInput-input a5006" value="">
			                    </div>
			                </div>
			                <div class="a5007">
			                    <label class="MuiFormControlLabel-root a5008">
			                        <span class="MuiButtonBase-root MuiIconButton-root jss361 MuiCheckbox-root MuiCheckbox-colorSecondary Mui-checked MuiIconButton-colorSecondary" aria-disabled="false">
			                            <span class="MuiIconButton-label">
			                                <input class="a5009" name="rememberMe" type="checkbox" data-indeterminate="false" value="" checked="">
			                                <svg class="MuiSvgIcon-root" focusable="false" viewbox="0 0 24 24" aria-hidden="true" role="img">
			                                    <g fill="none" fill-rule="evenodd">
			                                        <!-- <path class="a5013" fill="#FF5152"  d="M2 12c0 5.5 4.5 10 10 10s10-4.5 10-10S17.5 2 12 2 2 6.5 2 12z"></path>
			                                        <path stroke="#FFF" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 11.882L10.503 15 16 9"></path> -->
			                                        <path fill="#c4c4c6" d="M12 2C6.5 2 2 6.5 2 12s4.5 10 10 10 10-4.5 10-10S17.5 2 12 2zm0 18c-4.4 0-8-3.6-8-8s3.6-8 8-8 8 3.6 8 8-3.6 8-8 8z"></path>
			                                        <path stroke="#c4c4c6" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 11.882L10.503 15 16 9"></path>
			                                    </g>
			                                </svg>
			                            </span>
			                            <span class="MuiTouchRipple-root"></span>
			                        </span>
			                        <span class="MuiTypography-root MuiFormControlLabel-label MuiTypography-body1">자동 로그인</span>
			                    </label>
			                </div>
			                <button onclick="login()" class="MuiButtonBase-root MuiButton-root MuiButton-contained a5010 undefined MuiButton-containedPrimary MuiButton-fullWidth" tabindex="0" type="submit">
			                	<span class="MuiButton-label">로그인</span>
			                	<span class="MuiTouchRipple-root"></span>
			                </button>
			            </form>
			        </div>
			        <a class="MuiButtonBase-root MuiButton-root MuiButton-outlined a5010 a5011 MuiButton-colorInherit MuiButton-fullWidth" tabindex="0" aria-disabled="false" href="/member/sign-up">
			        	<span class="MuiButton-label">회원가입</span>
			        	<span class="MuiTouchRipple-root"></span>
			        </a>
			        <div class="a5012">
			            <div role="group" class="MuiButtonGroup-root" aria-label="login Help">
			                <a class="MuiButtonBase-root MuiButtonGroup-grouped MuiButtonGroup-groupedHorizontal MuiButtonGroup-groupedText MuiButtonGroup-groupedTextHorizontal MuiButtonGroup-groupedTextPrimary" tabindex="0" aria-disabled="false" href="/member/find-id" style="border-radius: 0px;">아이디 찾기<span class="MuiTouchRipple-root"></span></a><a class="MuiButtonBase-root MuiButtonGroup-grouped MuiButtonGroup-groupedHorizontal MuiButtonGroup-groupedText MuiButtonGroup-groupedTextHorizontal MuiButtonGroup-groupedTextPrimary" tabindex="0" aria-disabled="false" href="/member/find-password" style="border-radius: 0px;">비밀번호 찾기<span class="MuiTouchRipple-root"></span></a>
			            </div>
			        </div>
			    </div>
			    
			    <jsp:include page="../common/footer.jsp"></jsp:include>
	            
	        </div>
	    </div>
	</body>
</html>
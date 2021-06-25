$("#mobile").keyup(function() {
		var user_phone = $('#mobile').val();
		if (user_phone == "") {
			$('#search_idMessage').text('핸드폰번호를 입력하세요.');
			$("#searchid_submit").attr("disabled", true);
		} else {
			user_phone = user_phone.split('-').join('');
			var regPhone = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
			if (regPhone.test(user_phone)) {
				$('#search_idMessage').text('');
				$("#searchid_submit").attr("disabled", false);
			}
			else {
				$('#search_idMessage').text('핸드폰번호를 올바르게 입력하세요.');
				$("#searchid_submit").attr("disabled", true);
			}

		}
	});
function search_id_rs() {
	var name = $('#name').val();
	var phone = $('#mobile').val();
	$.ajax({
		url: 'search_id.member',
		data: { 'name': name, 'phone': phone },
		type: 'post',
		dataType: 'json',
		success: function(gson) {
			if (gson == null) {
				alert('일치하는 정보가 없습니다.');
			} else {
				$.ajax({
					url: 'search_id_rs.member',
					async: true,
					type: 'post',
					dataType: 'html',
					cache: false
				}).done(function(data) {
					$('#htmlContent').html(data);
					$('#search_id').text(gson.id);
					$('#joindate').text('가입일 :' + gson.joindate);
				})
			}
		}, error: function() {
			alert('서버오류 입니다 관리자에게 문의 하세요.');
		}
	});
}


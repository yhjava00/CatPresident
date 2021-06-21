
function openChat() {
    $('.c1001').css('display', 'block')
    
    $.ajax({
		url: 'chat.main',
		async : true,
		type : 'post',
		dataType : 'html',
		cache: false
	}).done(function (data) {
		$('.c1001').html(data)
	})
}

function closeChat() {
    $('.c1001').css('display', 'none')
    
    $('.c1001').children().remove()
}
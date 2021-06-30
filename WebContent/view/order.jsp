<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/order.css">
        <title>주문결제</title>
        <script>
        function updateQty(idx, qty, type) {
        	$.ajax({
				url: 'paymnet.main',
				data: {'idx':idx, 'type':type},
				async : true,
				type : 'post',
				dataType : 'html',
				cache: false
			}).done(function (data) {
				$('.htmlContent').html(data)
			})
        }	
        </script>
	</head>
	<body>
        <div class="a4001">
            <div class="a4002">
                <h2 class="a4003">주문결제</h2>
            </div>
            <div class="a4004">
                <div class="a4005">
                    <div class="a4006">
                        <span class="a4007">
                            <span class="a4008">
                                <svg class="a4009" focusable="false" viewBox= "0 0 24 24" aria-hidden="true" role="img">
                                    <path d="M8.828 12L17.413 20.645 15.999 22.06 6 12 15.999 2.002 17.413 3.417z" transform="matrix(-1 0 0 1 23.413 0)"></path>
                                </svg>
                            </span>
                            <span class="a4010">
                                <span class="a4011">01장바구니</span>
                            </span>
                        </span>
                    </div>
                    <div class="a4012">
                        <span class="a4013"></span>
                    </div>
                    <div class="a4014">
                        <span class="a4015">
                            <span class="a4016"> 
                                <svg class="a4017" focusable="false" viewBox= "0 0 24 24" aria-hidden="true" role="img">
                                    <path d="M8.828 12L17.413 20.645 15.999 22.06 6 12 15.999 2.002 17.413 3.417z" transform="matrix(-1 0 0 1 23.413 0)"></path>
                                </svg>
                            </span>
                            <span class="a4018">
                                <span class="a4019">02주문결제</span>
                            </span>
                        </span>
                    </div>
                    <div class="a4020">
                        <span class="a4021"></span>
                    </div>
                    <div class="a4022">
                        <span class="a4023">
                            <span class="a4024"> 
                                <svg class="a4025" focusable="false" viewBox= "0 0 24 24" aria-hidden="true" role="img">
                                    <path d="M8.828 12L17.413 20.645 15.999 22.06 6 12 15.999 2.002 17.413 3.417z" transform="matrix(-1 0 0 1 23.413 0)"></path>
                                </svg>
                            </span>
                            <span class="a4026">
                                <span class="a4027">03결제완료</span>
                            </span>
                        </span>
                    </div>
                </div>
            </div>
            <div class="a4028">
                <div class="a4029">
                    <div class="a4030">
                        <div class="a4031">배송정보</div>
                        <div></div>
                    </div>
                    <div class="a4032">
                        <div class="a4033">
                            <div class="a4034" style="overflow: hidden;">
                                <div aria-label="available-review-tabs" class="a4035" role = "tablist">
                                    <button class="a4036" tabindex="-1" type="button" role="tab" aria-selected="false" id="tab-recent" aria-controls="tabpanel-recent">
                                        <span class="a4037">최근 배송지</span>
                                    </button>
                                    <button class="a4038" tabindex="0" type="button" role="tab" aria-selected="true" id="tab-new" aria-controls="tabpanel-new">
                                        <span class="a4039">신규배송지</span>
                                    </button>
                                    <button class="a4040" tabindex="-1" type="button" role="tab" aria-selected="false" id="tab-choice" aria-controls="tabpanel-choice">
                                        <span class="a4041">배송지 선택</span>
                                    </button>                                       
                                </div>
                                <span class="a4043"></span>
                            </div>
                        </div>
                        <div class="a4044" role="tabpanel" id="tabpanel-recent" aria-labelledby="tab-recent" hidden></div>
                        <div class="a4045" role="tabpanel" id="tabpanel-new" aria-labelledby="tab-new">
                            <div class="a4046">
                                <div data-testid="finder" style="display: none; position: fixed; overflow: hidden; z-index: 1;">
                                    <img data-testid="close" src="https://t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" alt="닫기 버튼" style="cursor: pointer; position: absolute; right: 8px; top: 8px; border-radius: 10px; z-index: 1;">
                                </div>
                                <div class="a4047">
                                    <div class="a4048">
                                        <div class="a4049">
                                            <label class="a4050">받는사람</label>
                                            <div class="a4051">
                                                <input aria-invalid="false" placeholder="받으시는 분의 성함을 입력하세요." data-testid="name" class="a4052" value="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="a4053">
                                        <div class="a4054">
                                            <label class="a4055">휴대전화</label>
                                            <div class="a4056">
                                                <input aria-invalid="false" placeholder="휴대전화 번호를 입력하세요." class="a4057" value="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="a4058">
                                        <div class="a4059">
                                            <div class="a4060">
                                                <label class="a4061" data-shrink="true">배송지 주소</label>
                                                <div class="a4062">
                                                    <input aria-invalid="false" disabled="" placeholder="우편번호를 입력하세요." class="a4063" value>
                                                </div>
                                            </div>
                                            <button class="a4064" tabindex="0" type="button"><span class="a4065">우편번호</span><span class="a4066"></span></button>
                                        </div>
                                        <div class="a4067">
                                            <div class="a4068">
                                                <div class="a4069">
                                                    <input aria-invalid="false" disabled="" placeholder="주소를 입력하세요." class="a4070" value="">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="a4071">
                                            <div class="a4072">
                                                <div class="a4073">
                                                    <input aria-invalid="false" placeholder="상세주소를 입력하세요." class="a4074" value="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="a4058">
                                    <div class="a4059">
                                        <div class="a4060">
                                            <label class="a4061" data-shrink="true">배송요청 사항</label>
                                            <div class="a4062">
                                                <input aria-invalid="false" placeholder="배송시 요청 사항을 입력하세요." class="a4063" value>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="a4079">
                                <div class="a4080">
                                    <div class="a4081">주문상품</div>
                                </div>
                                <div class="a4082">
                                    <div class="a4083">
                                        <div class="a4084">
                                            <div class="a4085">대통령발송 상품</div>
                                            <div class="a4086">발송일 : 오늘</div>
                                    </div>
                                    <ul class="a4087">
                                        <li class="a4088">
                                            <div class="a4089">
                                                <div class="a4090">
                                                    <picture class="a4091">
                                                        <img class="a4092" src="https://img.catpre.com/web/catpre/product/38/37592_detail_01136325.jpg" alt="https://img.catpre.com/web/catpre/product/38/37592_detail_01136325.jpg" sizes="auto">
                                                    </picture>
                                                </div>
                                                <div class="a4093">
                                                    <div class="a4094">
                                                        <div class="a4095">
                                                            <h3 class="a4096">츄통령 바삭츄 동결건조 연어 45g</h3>
                                                            <div class="a4097">수량 : 1개</div>
                                                        </div>
                                                        <div class="a4098">
                                                            <strong class="a4099">7,500원</strong>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
            <div class="a4108">
                <div class="a4109">
                    <div class="a4110">보유현금</div>
                </div>
            </div>
            <div class="a4111">
                <div class="a4112">
                    <div class="a4113">
                        <div>
                            <span>현재 보유현금 : 0원</span>
                        </div>
                        <label class="a4114" data-testid="checkbox">
                            <span class="a4115" tabindex="-1" aria-disabled="true">
                                <span class="a4116">
                                    <input class="a4117" disabled="" name="" type="checkbox" data-indeterminate="false" value="">
                                    <svg class="a4118" focusable="false" viewBox="0 0 24 24" aria-hidden="true" role="img">
                                        <g fill="none" fill-rule="evenodd">
                                            <path fill="#c4c4c6" d="M12 2C6.5 2 2 6.5 2 12s4.5 10 10 10 10-4.5 10-10S17.5 2 12 2zm0 18c-4.4 0-8-3.6-8-8s3.6-8 8-8 8 3.6 8 8-3.6 8-8 8z"></path>
                                            <path stroke="#c4c4c6" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 11.882L10.503 15 16 9"></path>
                                        </g>
                                    </svg>
                                </span>
                            </span>
                            <span class="a4119">전액사용</span>
                        </label>
                    </div>
                    <div>
                        <div class="a4120">
                            <div class="a4121">
                                <input aria-invalid="false" type="number" class="a4122" value="0">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="a4123">
                <div class="a4124">
                    <div class="a4125">
                        <div class="a4126">최종 결제금액</div>
                    </div>
                </div>
                <div class="a4127">
                    <div class="a4128">
                        <dl class="a4129">
                            <dt>총 상품 금액</dt>
                            <dd>7,500원</dd>
                        </dl>
                        <dl class="a4129">
                            <dt>배송비</dt>
                            <dd>+3,000</dd>
                        </dl>
                        <dl class="a4129">
                            <dt>쿠폰</dt>
                            <dd>0원</dd>
                        </dl>
                        <dl class="a4129">
                            <dt>보유 현금</dt>
                            <dd>0원</dd>
                        </dl>
                        <dl class="a4130">
                            <dt>총 결제금액</dt>
                            <dd>10,500원</dd>
                        </dl>
                    </div>
                </div>
            </div>
            <div class="a4131">
                <div class="a4132">
                    <div class="a4133">결제방법</div>
                </div>
                <div class="a4134">
                    <div class="a4135">
                        <div class="a4136" role="radiogroup" aria-label="paymentMethods">
                            <div class="a4137">
                                <label class="a4138">
                                    <span class="a4139" aria-disabled="false">
                                        <span class="a4140">
                                            <input class="a4141" name="paymentMethods" type="radio" value="WPAY">
                                            <div class="a4142">
                                                <svg class="a4143" focusable="false" viewBox="0 0 24 24" aria-hidden="true">
                                                    <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 18c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8z"></path>
                                                </svg>
                                                <svg class="a4144" focusable="false" viewBox="0 0 24 24" aria-hidden="true">
                                                    <path d="M8.465 8.465C9.37 7.56 10.62 7 12 7C14.76 7 17 9.24 17 12C17 13.38 16.44 14.63 15.535 15.535C14.63 16.44 13.38 17 12 17C9.24 17 7 14.76 7 12C7 10.62 7.56 9.37 8.465 8.465Z"></path>
                                                </svg>
                                            </div>
                                        </span>
                                        <span class="a4145"></span>
                                    </span>
                                    <span class="a4146">
                                        <div class="a4147">
                                            <svg class="a4148" focusable="false" viewBox="0 0 24 24" aria-hidden="true" role="img" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24" height="24">
                                                <defs>
                                                    <filter id="b">
                                                        <feColorMatrix in="SourceGraphic" values="0 0 0 0 1.000000 0 0 0 0 1.000000 0 0 0 0 1.000000 0 0 0 1.000000 0">
                                                        </feColorMatrix>
                                                    </filter>
                                                    <path id="a" d="M0 0h20v20H0z"></path>
                                                </defs>
                                                <g fill="none" fill-rule="evenodd">
                                                    <path fill="#FFF" d="m0 0 6.367 4.766L12.734 0v9.531l-6.367 4.766L0 9.531z" transform="translate(5.633 5.086)" style="mix-blend-mode: overlay;"></path>
                                                    <path fill="#FFF" fill-opacity=".7" d="m0 9.531 6.367-4.765 6.367 4.765-6.367 4.766z" transform="translate(5.633 5.086)" style="mix-blend-mode: overlay;"></path>
                                                    <path fill="#FFF" d="m0 0 6.367 14.297L12.734 0 6.367 4.766z" transform="translate(5.633 5.086)" style="mix-blend-mode: soft-light;"></path>
                                                    <path fill="#000" fill-opacity=".25" d="m0 0 6.367 14.297L0 2.324V0zm12.734 0v2.324L6.367 14.297 12.734 0z" transform="translate(5.633 5.086)" style="mix-blend-mode: overlay;">
                                                    </path><path fill="#000" d="M22 2v20H12c5.523 0 10-4.477 10-10S17.523 2 12 2 2 6.477 2 12s4.477 10 10 10H2V2h20z"></path>
                                                    <g>
                                                        <use fill="#A200C7" xlink:href="#a" transform="translate(2 2)"></use>
                                                        <g filter="url(#b)" transform="translate(2 2)">
                                                            <g fill="#A200C7">
                                                                <path d="M7.33 6.516h2.913c.598 0 1.292.694 1.292 1.292v2.382a.779.779 0 0 0 1.556 0V5.74a.78.78 0 0 0-.778-.78H7.33a.78.78 0 0 0-.778.778c0 .208.082.404.23.55a.735.735 0 0 0 .549.228"></path>
                                                                <path d="M14.532 13.227h-3.13c-.598 0-1.292-.695-1.292-1.293v-2.25a.778.778 0 1 0-1.556-.001v2.251c0 .598-.694 1.293-1.291 1.293H4.918a.774.774 0 0 0-.778.777c0 .43.349.779.778.779h9.613a.78.78 0 0 0 0-1.556"></path>
                                                            </g>
                                                        </g>
                                                    </g>
                                                    <path d="M0 0h24v24H0z"></path>
                                                </g>
                                            </svg>
                                            <img src="img/pay_bi.svg" alt="고대페이" class="a4149">
                                        </div>
                                    </span>
                                </label>
                            </div>
                            <div class="a4150">
                                <label class="a4151">
                                    <span class="a4152" aria-desabled="false">
                                        <span class="a4153">
                                            <input class="a4154" name="paymentMethods" type="radio" value="NPAY">
                                            <div class="a4155">
                                                <svg class="a4156" focusable="false" viewBox="0 0 24 24" aria-hidden="true">
                                                    <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 18c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8z"></path>
                                                </svg>
                                                <svg class="a4157" focusable="false" viewBox="0 0 24 24" aria-hidden="true">
                                                    <path d="M8.465 8.465C9.37 7.56 10.62 7 12 7C14.76 7 17 9.24 17 12C17 13.38 16.44 14.63 15.535 15.535C14.63 16.44 13.38 17 12 17C9.24 17 7 14.76 7 12C7 10.62 7.56 9.37 8.465 8.465Z"></path>
                                                </svg>
                                            </div>
                                        </span>
                                        <span class="a4158"></span>
                                    </span>
                                    <span class="a4159">
                                        <div class="a4160">
                                            <svg class="a4161" focusable="false" viewBox="0 0 24 24" aria-hidden="true" role="img">
                                                <g fill="none" fill-rule ="evenodd">
                                                    <g>
                                                        <g>
                                                            <g>
                                                                <g>
                                                                    <path fill="#00C73C" d="M0 19.946L19.945 19.946 19.945 0 0 0z" transform="translate(-56 -2472) translate(20 2472) translate(36) translate(2 2)"></path>
                                                                    <path fill="#FFF" d="M11.559 14.538L8.341 9.812 8.341 14.538 4.986 14.538 4.986 5.407 8.388 5.407 11.606 10.133 11.606 5.407 14.959 5.407 14.959 14.538z" transform="translate(-56 -2472) translate(20 2472) translate(36) translate(2 2)"></path>
                                                                </g>
                                                                <path d="M0 0H24V24H0z" transform="translate(-56 -2472) translate(20 2472) translate(36)"></path>
                                                            </g>
                                                        </g>
                                                    </g>
                                                </g>
                                            </svg>
                                            <span class="a4162">네이버페이</span>
                                        </div>
                                    </span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
	        </div>
	        <div class="a4163">
	            <p class="a4164">위 주문 내용을 확인하였으며 결제에 동의합니다.</p>
		            <div>
		                <button class="a4165" tabindex="0" type="button" data-testid="pay">
		                    <span class="a4166">결제하기</span>
		                    <span class="a4167"></span>
		                </button>
           			</div>
        		</div>
   			</div>
		</div>
	</body>
</html>
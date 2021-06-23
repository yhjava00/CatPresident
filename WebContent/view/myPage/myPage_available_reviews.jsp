<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<style>
			.b1001 {
			    margin: 20px 0 10px;
			    padding: 14px 0;
			    border-bottom: 0 none;
			    background-color: #fff;
			    font-size: 1.3rem;
			    font-weight: 700;
			}
			.b1002 {
			    table-layout: fixed;
			    width: 100%;
			    display: table;
			    border-spacing: 0;
			    border-collapse: collapse;
			}
			.b1003 {
			    display: table-header-group;
			}
			.b1004 {
			    color: inherit;
			    display: table-row;
			    outline: 0;
			    vertical-align: middle;
			}
			.b1005 {
			    border: 0 none;
			    padding: 12px 20px;
			    font-size: 1.0rem;
			    background-color: rgba(244, 244, 245, 1);
			    text-align: center;
			    color: rgba(0, 0, 0, 1);
			    font-weight: 700;
			    line-height: 2.4rem;
			    display: table-cell;
			    font-family: Noto Sans KR,sans-serif;
			    vertical-align: inherit;
			}
			.b1006 {
			    display: table-row-group;
			}
			.b1007 {
			    padding: 24px 20px;
			    font-size: 1.0rem;
			    border-color: rgba(235, 235, 235, 1);
			    text-align: center;
			    color: rgba(0, 0, 0, 1);
			    display: table-cell;
			    font-family: Noto Sans KR,sans-serif;
			    font-weight: 400;
			    line-height: 1.43;
			    border-bottom: 1px solid rgba(224, 224, 224, 1);
			    vertical-align: inherit;
			}
			.b1007-1 {
			    border-left: 1px solid rgba(235, 235, 235, 1);
			}
			.b1008 {
			    color: rgba(60, 60, 67, 0.6);
			}
			.b1009 {
			    padding: 24px 20px;
			    font-size: 1.4rem;
			    border-left: 1px solid rgba(235, 235, 235, 1);
			    border-color: rgba(235, 235, 235, 1);
			    color: rgba(0, 0, 0, 1);
			    display: table-cell;
			    text-align: left;
			    font-family: Noto Sans KR,sans-serif;
			    font-weight: 400;
			    line-height: 1.43;
			    border-bottom: 1px solid rgba(224, 224, 224, 1);
			    vertical-align: inherit;
			}
			.b1009-1 {
			    width: 100%;
			    display: flex;
			    padding: 0;
			}
			.b1010 {
			    width: 100px;
			    height: 100px;
			    position: relative;
			    flex-grow: 0;
			    flex-basis: 100px;
			    flex-shrink: 0;
			    margin-right: 20px;
			}
			.b1011 {
			    display: inline;
			}
			.b1012 {
			    width: 100%;
			    margin: auto;
			    display: block;
			    max-width: 100%;
			}
			.b1013 {
			    flex-grow: 1;
			    min-width: 0;
			}
			.b1014 {
			    margin: 0;
			    font-size: .9rem;
			    font-weight: 400;
			    line-height: 1.5;
			}
			.b1014 > a {
			    color: rgba(0, 0, 0, 1);
			    text-decoration: none;
			}
			.b1015 {
			    font-size: 1.1rem;
			    font-weight: 700;
			}
			.b1016 {
			    font-weight: 700;
			}
			/* 페이징 */
			.jss1025 {
			    padding: 75px 0 25px;
			    margin: 0;
			    display: flex;
			    list-style: none;
			    justify-content: center;
			    background-color: #fff;
			}
			.jss1025 button {
			    height: 48px;
			    min-width: 48px;
			    margin: 0 2px;
			    padding: 0 10px;
			    font-size: 1.1rem;
			    border-radius: 8px; 
			    transition: color 250ms cubic-bezier(0.4, 0, 0.2, 1) 0ms,background-color 250ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
			    color: rgba(0, 0, 0, 1);
			    box-sizing: border-box;
			    text-align: center;
			    font-family: Noto Sans KR,sans-serif;
			    font-weight: 400;
			    line-height: 1.43;
			    border: 0;
			    cursor: pointer;
			    display: inline-flex;
			    outline: 0;
			    position: relative;
			    align-items: center;
			    user-select: none;
			    vertical-align: middle;
			    justify-content: center;
			    text-decoration: none;
			    background-color: transparent;
			    -webkit-appearance: none;
			    -webkit-tap-highlight-color: transparent;   
			}
			.jss1025 svg {
			    width: 16px;
			    height: 16px;
			    fill: currentColor;
			    display: inline-block;
			    font-size: 2.4rem;
			    transition: fill 200ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
			    flex-shrink: 0;
			    user-select: none;
			}
			.jss1026 {
			    font-weight: 700 !important;
			    font-size: 1.3rem !important;
			}
			/* 페이징 */
			.b1017 {
			    overflow: hidden;
			    color: rgba(162, 0, 199, 1);
			    background-color: rgba(248, 235, 251, 1);
			    padding: 10px 16px;
			    font-size: 1.0rem;
			    box-shadow: none !important;
			    font-weight: 400;
			    text-transform: capitalize;
			    border-color: currentColor;
			    min-width: 64px;
			    box-sizing: border-box;
			    transition: background-color 250ms cubic-bezier(0.4, 0, 0.2, 1) 0ms,box-shadow 250ms cubic-bezier(0.4, 0, 0.2, 1) 0ms,border 250ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
			    font-family: Noto Sans KR,sans-serif;
			    line-height: 1.75;
			    border-radius: 4px;
			    border: 0;
			    cursor: pointer;
			    margin: 0;
			    display: inline-flex;
			    outline: 0;
			    position: relative;
			    align-items: center;
			    user-select: none;
			    vertical-align: middle;
			    justify-content: center;
			    text-decoration: none;
			    -webkit-appearance: none;
			    -webkit-tap-highlight-color: transparent;
			}
			.b1018 {
			    width: 100%;
			    display: inherit;
			    align-items: inherit;
			    justify-content: inherit;
			}
			.b1019 {
			    top: 0;
			    left: 0;
			    right: 0;
			    bottom: 0;
			    z-index: 0;
			    overflow: hidden;
			    position: absolute;
			    border-radius: inherit;
			    pointer-events: none;
			}
		</style>
		<script type="text/javascript">
			setBtnEvent()
		</script>
		<title>Insert title here</title>
	</head>
	<body>
		<h2 class="b1001">주문 · 배송</h2>
	    <table class="b1002" aria-label="customized table">
	        <colgroup>
	            <col width="150px">
	            <col width="140px">
	            <col width="auto">
	            <col width="180px">
	        </colgroup>
	        <thead class="b1003">
	            <tr class="b1004">
	                <th class="b1005" scope="col">주문 번호</th>
	                <th class="b1005" scope="col">주문 일자</th>
	                <th class="b1005" scope="col">상품 정보</th>
	                <th class="b1005" scope="col">진행 상황</th>
	            </tr>
	        </thead>
	        <tbody class="b1006">
	            <tr class="b1004">
	                <td class="b1007">
	                    <span class="b1008">162107318814</span>
	                </td>
	                <td class="b1007 b1007-1">
	                    <span class="b1008">2021.05.15.</span>
	                </td>
	                <td class="b1009">
	                    <div class="b1009-1">
	                        <div class="b1010">
	                            <a href="#">
	                                <picture class="b1011">
	                                    <source media="(max-width: 1199px)" srcset="https://img.catpre.com/web/catpre/product/38/37592_detail_01136325.jpg">
	                                    <source media="(min-width: 1200px)" srcset="https://img.catpre.com/web/catpre/product/38/37592_detail_01136325.jpg">
	                                    <img class="b1012" src="https://img.catpre.com/web/catpre/product/38/37592_detail_01136325.jpg" alt="https://img.catpre.com/web/catpre/product/38/37592_detail_01136325.jpg" sizes="auto">
	                                </picture>
	                            </a>
	                        </div>
	                        <div class="b1013">
	                            <h3 class="b1014">
	                                <a href="#">츄통령 바삭츄 동결건조 연어 45g</a>
	                            </h3>
	                            <strong class="b1015">10,500원</strong>
	                        </div>
	                    </div>
	                </td>
	                <td class="b1007 b1007-1">
	                    <button class="b1017 buttonP" tabindex="0" type="button">
	                        <span class="b1018">구매후기 작성</span>
	                        <span class="b1019"></span>
	                    </button>
	                </td>
	            </tr>
	        </tbody>
	    </table>
	    <!-- 페이징 -->
	    <nav>
	        <ul class="jss1025">
	            <li>
	                <button tabindex="0" type="button" aria-current="true">
	                    <span>
	                        <svg focusable="false" viewBox="0 0 24 24" aria-hidden="true" role="img"><path d="M8.828 12L17.413 20.645 15.999 22.06 6 12 15.999 2.002 17.413 3.417z"></path></svg>
	                    </span>
	                </button>
	            </li>
	            <li>
	                <button tabindex="0" type="button" aria-current="true">1</button>
	            </li>
	            <li>
	                <button class="jss1026" tabindex="0" type="button" aria-current="true">2</button>
	            </li>
	            <li>
	                <button tabindex="0" type="button" aria-current="true">
	                    <span class="MuiButton-label">
	                        <svg focusable="false" viewBox="0 0 24 24" aria-hidden="true" role="img"><path d="M8.828 12L17.413 20.645 15.999 22.06 6 12 15.999 2.002 17.413 3.417z" transform="matrix(-1 0 0 1 23.413 0)"></path></svg>
	                    </span>
	                </button>
	            </li>
	        </ul>
	    </nav>
	    <!-- 페이징 -->
	</body>
</html>
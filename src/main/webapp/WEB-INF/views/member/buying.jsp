<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../commons/header.jsp" />

<style>
p {
	font-size: 13px;
	font-family: -apple-system, BlinkMacSystemFont, Roboto,
		AppleSDGothicNeo-Regular, NanumBarunGothic, NanumGothic, 나눔고딕,
		Segoe UI, Helveica, Arial, Malgun Gothic, Dotum, sans-serif;
	color: #3d3d3d;
	margin: 0;
}

.productitempic {
	height: 180px;
	position: relative;
	background-position: center center;
}

#imggg {
	border-radius: 0.5em;
	background-color: #ebf0f5;
	height: auto;
	width: auto;
}

.product[data-v-75e33658] {
	padding: 0;
	overflow: hidden;
	position: relative;
	padding-top: 100%;
	border-radius: 8px;
}

.productitemtext {
	padding-top: 11px;
	position: relative;
}

#title {
	display: inline-block;
	vertical-align: top;
	line-height: 16px;
	font-size: 14px;
	letter-spacing: -.21px;
	font-weight: 700;
	color: #333;
}

#name[data-v-6a8ef390] {
	line-height: 16px;
	font-size: 13px;
}

#translated_name[data-v-6a8ef390] {
	line-height: 14px;
	font-size: 12px;
	letter-spacing: -.06px;
	color: rgba(34, 34, 34, .5);
}
</style>

<div data-server-rendered="true" id="__nuxt">
	<!---->
	<div id="__layout">
		<div tabindex="0" class="wrap win_os" data-v-3d7f7af7>
			<!---->
			<!---->
			<div class="container my lg" data-v-3d7f7af7>

				<jsp:include page="../member/mypagesidebar.jsp" />

				<div data-fetch-key="OrderIndex:0" class="content_area"
					data-v-3d7f7af7>
					<div class="my_purchase">
						<div class="content_title" data-v-88eb18f6>
							<div class="title" data-v-88eb18f6>
								<h3 data-v-88eb18f6>구매 내역</h3>
								<!---->
							</div>
							<!---->
						</div>
						<div class="purchase_list_tab detail_tab" data-v-bbed98be>
							<!-- <div class="tab_item total" data-v-bbed98be>
								<a href="#" class="tab_link" data-v-bbed98be><dl
										class="tab_box" data-v-bbed98be>
										<dt class="title" data-v-bbed98be>전체</dt>
										<dd class="count" data-v-bbed98be>${buyDTO.totalCount}</dd>
									</dl></a>
							</div> -->
							<div class="tab_item tab_on" data-v-bbed98be>
								<a href="#" class="tab_link" data-v-bbed98be><dl
										class="tab_box" data-v-bbed98be>
										<dt class="title" data-v-bbed98be>전체</dt>
										<dd class="count" data-v-bbed98be>${buyDTO.totalCount}</dd>
									</dl></a>
							</div>
							<div class="tab_item" data-v-bbed98be>
								<a href="#" class="tab_link" data-v-bbed98be><dl
										class="tab_box" data-v-bbed98be>
										<dt class="title" data-v-bbed98be>구매 입찰</dt>
										<dd class="count" data-v-bbed98be>${buyDTO.bidCount}</dd>
									</dl></a>
							</div>
							<div class="tab_item" data-v-bbed98be>
								<a href="#" class="tab_link" data-v-bbed98be><dl
										class="tab_box" data-v-bbed98be>
										<dt class="title" data-v-bbed98be>종료</dt>
										<dd class="count" data-v-bbed98be>${buyDTO.endCount}</dd>
									</dl></a>
							</div>
						</div>
						<div class="purchase_list bidding bid" data-v-21d527e4>
							<!-- <div class="purchase_head" data-v-21d527e4>
								<div class="head_product" data-v-21d527e4>
									<a href="#" class="btn_filter" data-v-21d527e4> 전체 <svg
											xmlns="http://www.w3.org/2000/svg"
											class="ico-arr-dir-down-circle icon sprite-icons"
											data-v-21d527e4 data-v-21d527e4>
												<use
												href="/_nuxt/1a4fefc9c245c25be8c820c7d584e4d7.svg#i-ico-arr-dir-down-circle"
												xlink:href="/_nuxt/1a4fefc9c245c25be8c820c7d584e4d7.svg#i-ico-arr-dir-down-circle"
												data-v-21d527e4 data-v-21d527e4></use></svg></a>
								</div>
								<div class="head_status" data-v-21d527e4>
									<div class="status_box field_price" data-v-21d527e4>
										<a href="#" class="status_link" data-v-21d527e4><span
											class="status_txt" data-v-21d527e4>구매 희망가</span></a>
									</div>
									<div class="status_box field_date_purchased" data-v-21d527e4>
										<a href="#" class="status_link" data-v-21d527e4><span
											class="status_txt" data-v-21d527e4>구매일</span></a>
									</div>
									<div class="status_box field_date_paid" data-v-21d527e4>
										<a href="#" class="status_link" data-v-21d527e4><span
											class="status_txt" data-v-21d527e4>정산일</span></a>
									</div>
									<div class="status_box field_expires_at" data-v-21d527e4>
										<a href="#" class="status_link" data-v-21d527e4><span
											class="status_txt" data-v-21d527e4>만료일</span></a>
									</div>
									<div class="status_box field_status ascending" data-v-21d527e4>
										<a href="#" class="status_link" data-v-21d527e4><span
											class="status_txt" data-v-21d527e4>상태</span></a>
									</div>
								</div>
							</div> -->
							<div class="empty_area" data-v-e2f6767a data-v-21d527e4>
								<c:choose>
									<c:when test="${buyDTO.totalCount eq 0}">
										<p class="desc" data-v-e2f6767a>구매 입찰 내역이 없습니다.</p>
										<a href="/" class="btn outlinegrey small" data-v-3d1bcc82
											data-v-3d1bcc82 data-v-e2f6767a> SHOP 바로가기 </a>
									</c:when>
									<c:otherwise>
										<c:forEach items="${buyDTO.history}" var="history">
											<div data-v-62dace61="" data-v-21d527e4=""
												class="purchase_item buy" data-itemno="${history.itemNo }">
												<div data-v-62dace61="" class="history_product">
													<div data-v-62dace61="" class="product_box">
														<div data-v-75e33658="" data-v-62dace61="" class="product"
															style="background-color: rgb(235, 240, 245);">

															<picture data-v-548c90f9="" data-v-75e33658=""
																class="picture product_img"> <img
																data-v-548c90f9="" alt="#" src="${history.thImg }"
																class="image"> </picture>

															<!---->
															<!---->
															<!---->
														</div>
													</div>
													<div data-v-62dace61="" class="product_detail">
														<!---->
														<p data-v-62dace61="" class="name">${history.eName}</p>
														<p data-v-62dace61="" class="size">
															<span data-v-62dace61="" class="size_text">${history.shoeSize}</span>
														</p>
													</div>
												</div>
												<div data-v-62dace61="" class="history_status">
													<div data-v-62dace61="" class="status_box field_status">
														<span data-v-62dace61="" class="status_txt text-default">#</span>
														<!---->
													</div>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
							<!---->
							<!---->
							<!---->
							<!---->
						</div>
						<!---->
					</div>
				</div>
			</div>
			<!---->
		</div>
	</div>
</div>

<jsp:include page="../commons/footer.jsp" />

<script src="../../resources/js/member/historylist.js"></script>
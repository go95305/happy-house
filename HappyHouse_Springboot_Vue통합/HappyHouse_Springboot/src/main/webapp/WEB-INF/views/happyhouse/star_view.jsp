<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="bg-site">
	<div class="row">
		<div class="col-sm-12"></div>
	</div>
	<div class="row">

		<div class="col-sm-5 no-padding" id="pricelist">
		</div>
		<div id="map" class="col-sm-7 map-box no-padding"></div>
		<script
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDaNw2J6tuCdYZA-1DKLPyl4AxD3HokA6s&callback=initMap&libraries=&v=weekly"
			defer></script>
		<script type="text/javascript">
			var map;
			var multi = {
				lat : 37.501682,
				lng : 127.039632
			};
			function initMap() {
				map = new google.maps.Map(document.getElementById("map"), {
					center : multi,
					zoom : 15
				});

				var marker = new google.maps.Marker({
					position : multi,
					map : map
				});
			}
		</script>
	</div>
</div>
<div class="modal" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">거래 상세정보</h4>
				<button type="button" id="closebtn2" class="close"
					data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<table class="table table-hover">
				    <thead>
				      <tr>
				        <th>번호</th>
				        <th>동이름</th>
				        <th>아파트명</th>
			           <th>시세정보</th>
				      </tr>
				    </thead>
				    <tbody id ="mbody" >
				      <tr>
				        <td>July</td>
				        <td>Dooley</td>
				        <td>july@example.com</td>
				      </tr>
				    </tbody>
				  </table>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" id="deletebtn" class="btn btn-warning">Delete</button>
				<button type="button" id="closebtn" class="btn btn-danger"
					data-dismiss="modal">Close</button>
			</div>

		</div>
	</div>
</div>

<jsp:include page="../footer.jsp"></jsp:include>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						
						$(document).on({
					        mouseenter: function () {
					        	$(this).css("background-color", "#595959");
								$(this).css("color", "#FFFFFF");
					        },
					        mouseleave: function () {
					        	$(this).css("background-color", "#FFFFFF");
								$(this).css("color", "black");
					        }
					    }, '.price-row');

						
						$(document).on('mousedown','.price-row',function() {
											let apt = $(this).find('h3').text();
											let dongNo = $(this).find('input').val();
											
											$('#mbody').empty();
											$.get("${pageContext.request.contextPath}/interest/detail",
															{apt : apt},
															function(data,status) {
																$.each(data,function(i,it) {
																							let strr = '<tr>'
																							+ '<td>'
																							+ it.no
																							+ "</td>"
																							+ '<td>'
																							+ it.dong
																							+ "</td>"
																							+ '<td>'
																							+ it.aptName
																							+ "</td>"
																							+ '<td>'
																							+ it.dealAmount
																							+ "</td>"
																							+ "<input type='hidden' name='dongNumber' id='dongNumber' value=" + dongNo + ">"
																							+ "</tr>";
																					$("#mbody").append(strr);
																					
																					 /*  <tr>
																				        <td>July</td>
																				        <td>Dooley</td>
																				        <td>july@example.com</td>
																				      </tr> */
																				});//each
															}//function
															, "json");//get;
											$("#myModal").modal('show');
										});

						$('#closebtn').on('click', function() {
							$("#myModal").hide();
							$.ajax({
								url : '${pageContext.request.contextPath}/interest/showStarApi',
								type : 'get',
								success:function(data){
									makelist(data);
								}
							});
						});

						$('#closebtn2').on('click', function() {
							$("#myModal").hide();
							$.ajax({
								url : '${pageContext.request.contextPath}/interest/showStarApi',
								type : 'get',
								success:function(data){
									makelist(data);
								}
							});
						});

					});
	
	
	$('#deletebtn').on('click', function() {
		$.ajax({
			url : '${pageContext.request.contextPath}/interest/delete/' + $('#dongNumber').val(),
			type : 'DELETE',
			dataType : 'text',
			success : function(){
				alert("삭제 ");
			}
		});
	});
	
	$.ajax({
		url : '${pageContext.request.contextPath}/interest/showStarApi',
		type : 'get',
		success:function(data){
			makelist(data);
		}
	});
	
	
	function makelist(data){
		$("#pricelist").empty();
		$.each(data,function(i,it) {
			let strr = '<div class="price-row">'
			+'<h3>' + it.aptName + '</h3>'
			+ '<p>건설연도 :'+ it.buildYear +'년</p>'
			+ "<input type='hidden' name='dongNo' id='dongNo' value=" + it.no + ">"
			+ "</div>";
			$("#pricelist").append(strr);
		});//each
		let strr = '<div class="price-row"></div>'
		+'<div class="price-row"></div>'
		+'<div class="price-row"></div>'
		+'<div class="price-row"></div>'
		+'<div class="price-row"></div>'
		$("#pricelist").append(strr);
	}
	
	
</script>
</body>

</html>
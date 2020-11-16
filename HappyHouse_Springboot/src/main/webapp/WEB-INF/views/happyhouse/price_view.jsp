<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath} "></c:set>
<jsp:include page="../header.jsp"></jsp:include>
<div class="bg-site">
	<div class="row">
		<div class="col-sm-12"></div>
	</div>
	<div style="text-align: center;">
		시도 : <select id="sido">
			<option value="0">선택</option>
		</select> 구군 : <select id="gugun">
			<option value="0">선택</option>
		</select> 읍면동 : <select id="dong">
			<option value="0">선택</option>
		</select>
	</div>
	<div class="row">
		<div class="col-sm-5 no-padding" id="pricelist">

			<div class="price-row"></div>
			<div class="price-row"></div>
			<div class="price-row"></div>
			<div class="price-row"></div>
			<div class="price-row"></div>
			<div class="price-row"></div>
			<div class="price-row"></div>
			<div class="price-row"></div>
			<div class="price-row"></div>
			<div class="price-row"></div>

		</div>
		<div id="map" class="col-sm-7 map-box no-padding"></div>
		<script
			src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
		<script defer
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDaNw2J6tuCdYZA-1DKLPyl4AxD3HokA6s&callback=initMap"></script>
		<script>
                    var multi = {lat: 37.5665734, lng: 126.978179};
                    var markersArray = [];
					var map;
					function initMap() {
						map = new google.maps.Map(document.getElementById('map'), {
							center: multi, zoom: 12
						});
                    }
                    function deleteMarker(){
                        for (var i = 0 ; i < markersArray.length; i++){
                            markersArray[i].setMap(null);
                        }
                    }
					function addMarker(tmpLat, tmpLng, aptName) {
						var marker = new google.maps.Marker({
							position: new google.maps.LatLng(parseFloat(tmpLat),parseFloat(tmpLng)),
							label: aptName,
							title: aptName
						});
						marker.addListener('click', function() {
							map.setZoom(17);
							map.setCenter(marker.getPosition());
							callHouseDealInfo();
						});
                        marker.setMap(map);
                        markersArray.push(marker);
					}
					function callHouseDealInfo() {
						alert("you can call HouseDealInfo");
					}
				</script>
	</div>
</div>
<!-- The Modal -->
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
			<c:if test="${userinfo ne null}">
			<button type="button" id="regist" class="btn btn-primary">관심상품등록</button>
			</c:if>
				<button type="button" id="closebtn" class="btn btn-danger"
					data-dismiss="modal">Close</button>
			</div>

		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$.get("${pageContext.request.contextPath}/map/sido"
		,function(data, status){
		console.log(data);
			$.each(data, function(index, vo) {
				$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
			});//each
		}//function
		, "json"
	);//get
});//ready
    $(document).ready(function () {
    	$("#sido").change(function() {
			$.get("${pageContext.request.contextPath}/map/gugun"
					,{sido:$("#sido").val()}
					,function(data, status){
						$("#gugun").empty();
						$("#gugun").append('<option value="0">선택</option>');
						$.each(data, function(index, vo) {
							$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
						});//each
					}//function
					, "json"
			);//get
		});//change
		$("#gugun").change(function() {
			$.get("${pageContext.request.contextPath}/map/dong"
					,{gugun:$("#gugun").val()}
					,function(data, status){
						$("#dong").empty();
						$("#dong").append('<option value="0">선택</option>');
						$.each(data, function(index, vo) {
							$("#dong").append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
						});//each
					}//function
					, "json"
			);//get
		});//change
		$("#dong").change(function() {
			$.get("${pageContext.request.contextPath}/map/apt"
					,{dong:$("#dong").val()}
					,function(data, status){
						$("#pricelist").empty();
						
						$.each(data, function(index, vo) {
							let str = '<div class="price-row">'
							+ '<input type="hidden" name="act" id="dongNo" value='+vo.no+'>'
							+ '&nbsp&nbsp<h3>' + vo.aptName + "</h3>"
							+ "<p> 건설연도 : " + vo.buildYear + "년</p>"
							+ "<div>";
							
							$("#pricelist").append(str);
							$("#searchResult").append(vo.dong+" "+vo.aptName+" "+vo.jibun+"<br>");
						});//each
						
						
						 $('.price-row').hover(function () {
					            $(this).css("background-color", "#595959");
					            $(this).css("color", "#FFFFFF");
					        }, function () {
					            $(this).css("background-color", "#FFFFFF");
					            $(this).css("color", "black");
                        });
						
						 $('.price-row').mousedown(function () {
							 let dong = $('#dong').val();
					            let apt = $(this).find('h3').text();
					            let dongNo = $(this).find('input').val();
					            $('#mbody').empty();
					            
					            $.get("${pageContext.request.contextPath}/map/deal"
					            		,{apt:apt,dong:dong}
					            		,function(data, status){
					            $.each(data, function(i, it) {
					           	 let strr = '<tr>'
					    	             	+ '<td>' + it.no + "</td>"
					    	             	+ '<td>' + it.dong + "</td>"
					    	             	+ '<td>' + it.aptName + "</td>"
					    	             	+ '<td>' + it.dealAmount + "</td>"
					    	             	+ "<input type='hidden' name='dongNumber' id='dongNumber' value=" + dongNo + ">"
					    	             	+ "<tr>";
					    	             	$("#mbody").append(strr);
					    	             	
					            });//each
					            		}//function
					            		, "json"
					    				);//get;
					    				
					            $("#myModal").modal('show');
						 });
						 
						 $('#closebtn').on('click', function () {
					            $("#myModal").hide();
					        });  
					        
					        $('#closebtn2').on('click', function () {
					            $("#myModal").hide();
					        });
						
						geocode(data);
					}//function
					, "json"
			);//get
		});//change
		
		$('#regist').on('click', function() {
			$.get('${pageContext.request.contextPath}/interest/regist',
					{dongNo:$('#dongNumber').val()},
					function() {
				alert('성공');
			},
			'text'); //get			
		});
		
					
		
    });
    
    function geocode(jsonData) {
                    let idx = 0;
                    deleteMarker();
					$.each(jsonData, function(index, vo) {
						let tmpLat;
						let tmpLng;
						$.get("https://maps.googleapis.com/maps/api/geocode/json"
								,{	key:'AIzaSyDaNw2J6tuCdYZA-1DKLPyl4AxD3HokA6s'
									, address:vo.dong+"+"+vo.aptName+"+"+vo.jibun
								}
								, function(data, status) {
									//alert(data.results[0].geometry.location.lat);
									tmpLat = data.results[0].geometry.location.lat;
									tmpLng = data.results[0].geometry.location.lng;
									$("#lat_"+index).text(tmpLat);
									$("#lng_"+index).text(tmpLng);
									addMarker(tmpLat, tmpLng, vo.aptName);
								}
								, "json"
						);//get
					});//each
				}
</script>
</body>

</html>
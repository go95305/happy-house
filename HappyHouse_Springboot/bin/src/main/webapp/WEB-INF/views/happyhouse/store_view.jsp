<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					function addMarker(tmpLat, tmpLng, storename,kind) {
						if("음식"==(kind))
							var ic='icon38.png'
						else if("소매"==(kind))
							var ic='icon60.png'
						else if("생활서비스"==(kind))
							var ic='icon19.png'
						else if("학문/교육"==(kind))
							var ic='icon2.png'
						else if("관광/여가/오락"==(kind))
							var ic='icon56.png'
						else if("부동산"==(kind))
							var ic='icon48.png'
						else if("숙박"==(kind))
							var ic='icon28.png'
						else if("스포츠"==(kind))
							var ic='icon57.png'
						var marker = new google.maps.Marker({
							position: new google.maps.LatLng(parseFloat(tmpLat),parseFloat(tmpLng)),
							/* label: storename,
							title: storename, */
							
							icon:'https://maps.google.com/mapfiles/kml/pal2/'+ic
						
								
						});
						marker.addListener('click', function() {
							map.setZoom(17);
							map.setCenter(marker.getPosition());
							callstoreinfo(storename,kind);
						});
                        marker.setMap(map);
                        markersArray.push(marker);
					}
					function callstoreinfo(storename,kind) {
						alert("가게명: "+storename+"    업종: "+kind);
					}
				</script>
	</div>
</div>

<jsp:include page="../footer.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$.get("${pageContext.request.contextPath}/storeInterest/sido"
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
			$.get("${pageContext.request.contextPath}/storeInterest/gugun"
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
			$.get("${pageContext.request.contextPath}/storeInterest/dong"
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
			$.get("${pageContext.request.contextPath}/storeInterest/store"
					,{dong:$("#dong").val()}
					,function(data, status){
						console.log(data);
						
						geocode(data);
					}//function
					, "json"
			);//get
		});//change					
		
    });
    
    function geocode(jsonData) {
                    let idx = 0;
                    deleteMarker();
					$.each(jsonData, function(index, vo) {
						let tmpLat;
						let tmpLng;
						$.get("https://maps.googleapis.com/maps/api/geocode/json"
								,{	key:'AIzaSyDaNw2J6tuCdYZA-1DKLPyl4AxD3HokA6s'
									, address:vo.dong+"+"+vo.storename+"+"+vo.kind
								}
								, function(data, status) {
									//alert(data.results[0].geometry.location.lat);
									tmpLat = data.results[0].geometry.location.lat;
									tmpLng = data.results[0].geometry.location.lng;
									$("#lat_"+index).text(tmpLat);
									$("#lng_"+index).text(tmpLng);
									addMarker(tmpLat, tmpLng, vo.storename,vo.kind);
								}
								, "json"
						);//get
					});//each
				}
</script>
</body>

</html>
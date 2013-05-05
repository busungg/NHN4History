<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.security.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="model.dto.Post"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>마이페이지</title>
<link
	href="http://cdn.kendostatic.com/2013.1.319/styles/kendo.common.min.css"
	rel="stylesheet" />
<link
	href="http://cdn.kendostatic.com/2013.1.319/styles/kendo.default.min.css"
	rel="stylesheet" />
<link href="CSS/mypage.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script src="http://cdn.kendostatic.com/2013.1.319/js/kendo.all.min.js"></script>
</head>
<body>
	<div id="wrap">

		<!-- 달력 -->
		<h1>Calendar</h1>
		<div id="calendar"></div>

		<script>
				$(document)
						.ready(
								function() {
									
									var today = new Date(),
									events = [
									
									// 이벤트리스트를 가져온다.
									// events에 배열을 저장한다.
									<%List eventList = (List) request.getAttribute("allData");
			Post event = null;

			for (int i = 0; i < eventList.size(); i++) {
				event = (Post) eventList.get(i);%>
									 <%out.println("+new Date(");%>
									 <%out.println(event.getUptime().substring(0, 4));%>, 
									 <%out.println(Integer.parseInt(event.getUptime().substring(5, 7)
						.replace("0", "")) - 1);%>, 
									 <%out.println(event.getUptime().substring(8, 10).replace("0", ""));%>
									 <%out.println(")");%>
									 <%if (i != eventList.size() - 1)
					out.println(",");%>
									<%}%>
									 ];
									

									$("#calendar")
											.kendoCalendar(
													{
														value : today,
														dates : events,
														month : {
															// template for dates in month view
															content : '# if ($.inArray(+data.date, data.dates) != -1) { #'
																	+ '<div class="'
																	+ '# if (data.value < 10) { #'
																	+ "party"
																	+ '# } else if ( data.value < 20 ) { #'
																	+ "party"
																	+ '# } else { #'
																	+ "party"
																	+ '# } #'
																	+ '">#= data.value #</div>'
																	+ '# } else { #'
																	+ '#= data.value #'
																	+ '# } #'
														},
														footer : false
													});
								});
			</script>

		<div class="clear"></div>
		<h1>Gallery</h1>

		<%
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy",
					Locale.KOREA);
			//SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy/MM/dd", Locale.KOREA );
			Date today = new Date();
			String todayTime = formatter.format(today);
			System.out.println(todayTime);
		%>

		<div id="example" class="k-content">

			<div class="demo-section" style="width: 600px">
				<label for="start">Start date:</label> <input id="start"
					value=<%out.println(todayTime);%> /> <label for="end"
					style="margin-left: 3em">End date:</label> <input id="end"
					value=<%out.println(todayTime);%> />
			</div>

			<script>
					$(document)
							.ready(
									function() {
										function startChange() {
											
											//변경해야한다.
											var startDate = start.value(), 
												endDate = end.value();

											if (startDate) {
												startDate = new Date(startDate);
												startDate.setDate(startDate
														.getDate());
												end.min(startDate);
											} else if (endDate) {
												start.max(new Date(endDate));
											} else {
												endDate = new Date();
												start.max(endDate);
												end.min(endDate);
											}
										}

										function endChange() {
											var endDate = end.value(), startDate = start
													.value();

											if (endDate) {
												endDate = new Date(endDate);
												endDate.setDate(endDate
														.getDate());
												start.max(endDate);
											} else if (startDate) {
												end.min(new Date(startDate));
											} else {
												endDate = new Date();
												start.max(endDate);
												end.min(endDate);
											}
										}

										var start = $("#start")
												.kendoDatePicker({
													change : startChange
												}).data("kendoDatePicker");

										var end = $("#end").kendoDatePicker({
											change : endChange
										}).data("kendoDatePicker");

										start.max(end.value());
										end.min(start.value());
									});
				</script>
		</div>

		<div class="demo-section2">
			<div id="listView"></div>
			<div id="pager" class="k-pager-wrap"></div>
		</div>

		<script type="text/x-kendo-tmpl" id="template">
        <div class="product">
            <img src="../../content/web/foods/${ProductID}.jpg" alt="${ProductName} image" />
            <h3>#:ProductName#</h3>
            <p>#:kendo.toString(UnitPrice, "c")#</p>
        </div>
    </script>

		<script>
        $(document).ready(function() {
            var dataSource = new kendo.data.DataSource({
                    transport: {
                        read: {
                            url: "http://demos.kendoui.com/service/Products",
                            dataType: "jsonp"
                        }
                    },
                    pageSize: 15
                });

            $("#pager").kendoPager({
                dataSource: dataSource
            });

            $("#listView").kendoListView({
                dataSource: dataSource,
                template: kendo.template($("#template").html())
            });
        });
    </script>
    
	</div>

</body>
</html>
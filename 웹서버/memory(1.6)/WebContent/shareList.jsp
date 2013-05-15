<%@page import="model.dto.Share"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>공유 페이지</title>
</head>
<body>

	<div id="share" align="center" style="margin: 10px;">

		<form action="/memory/Service?action=sharedetail" method="post">

			<table>
				<%
					List list = (List) request.getAttribute("shareAll");
					Share data;

					for (int i = 0; i < list.size(); i++) {

						data = (Share) list.get(i);

						String id = data.getUserid();
						String year = data.getSyear();
						String month = data.getSmonth();
						String day = data.getSday();

						if (month.length() != 2) {
							month = "0" + month;
						}

						if (day.length() != 2) {
							day = "0" + day;
						}

						String startDate = year + month + day + "000000";
						String endDate = year + month + day + "235959";
				%>
				<tr>
					<td><a href="/memory/Service?action=sharedetail
					&userId=<%=id%>&startDate=<%=startDate%>&endDate=<%=endDate%>"><%=startDate%></a></td>
				</tr>
				<%
					}
				%>
			</table>
		</form>

	</div>

</body>
</html>
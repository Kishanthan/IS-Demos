<%@ page import="org.wso2.sample.identity.oauth2.OAuth2Constants" %>
<%@ page import="org.wso2.sample.identity.oauth2.OAuth2ServiceClient" %>
<%@ page import="org.wso2.sample.identity.oauth2.BookingInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page import="org.wso2.sample.identity.oauth2.Booking" %>
<%@ page import="java.util.List" %>
<%
	String valid = ((session.getAttribute(OAuth2Constants.TOKEN_VALIDATION) == null) ? "" : (String) session.getAttribute(OAuth2Constants.TOKEN_VALIDATION));
	OAuth2ServiceClient client = new OAuth2ServiceClient();
	String accessTokenIdentifier = request.getParameter("accessToken");
	String flightName = "AA-102";
	String flightImageUrl = "";
	BookingInfo bookingRequest = null;
	List<Booking> bookings = null;
	try {
		bookingRequest = client.invokeAPI(accessTokenIdentifier, flightName);
		bookings = bookingRequest.getBookingsAsList();
	} catch (Exception e) {
%>
<script type="text/javascript">
	window.location = "oauth2.jsp?reset=true&error=<%=e.getMessage()%>";
</script>
<%
	}
%>
<html><head>
<title>WSO2 OAuth2 Playground</title>
<meta charset="UTF-8">
<meta name="description" content="" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<!--[if lt IE 9]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<script type="text/javascript" src="js/prettify.js"></script>                                   <!-- PRETTIFY -->
<script type="text/javascript" src="js/kickstart.js"></script>                                  <!-- KICKSTART -->
<link rel="stylesheet" type="text/css" href="css/kickstart.css" media="all" />                  <!-- KICKSTART -->
<link rel="stylesheet" type="text/css" href="style.css" media="all" />                          <!-- CUSTOM STYLES -->



</head><body><a id="top-of-page"></a><div id="wrap" class="clearfix">
<!-- ===================================== END HEADER ===================================== -->

<!-- 
	
		ADD YOU HTML ELEMENTS HERE
		
		Example: 2 Columns
	 -->
	 <!-- Menu Horizontal -->
	<ul class="menu">
	<li class="current"><a href="index.jsp">Home</a></li>
	
	</ul>

<br/>
	<h3 align="center">WSO2 OAuth2 Playground ~ Flight Booking</h3>
	

<table style="width:100%;text-align:center;'">
		<% if (flightName.startsWith("AA")) {
			flightImageUrl = "images/american_airlines.jpg";
		} else if (flightName.startsWith("BA")) {
			flightImageUrl = "images/british_airlines.jpg";
		} else if (flightName.startsWith("CH")) {
			flightImageUrl = "images/china_airlines.jpg";
		}
		%>
<tr>
<td style="text-align:center;width:100%">        						
<img src=<%=flightImageUrl%> width="300px" height="350px" />
</td>
</tr>
	<% if (bookings != null && bookings.size() > 0)  { %>

	<tr>
		<TABLE>
			<tr>
				<td>Seat Number: </td>
				<% for(int i = 0 ; i< bookings.size(); i++ ){ %>
				<td><%=bookings.get(i).getSeatNumber()%></td>
				<% } %>
			</tr>
			<tr>
				<td>Is booked: </td>
				<% for(int i = 0 ; i< bookings.size(); i++ ){ %>
				<td><%=bookings.get(i).isBooked()%></td>
				<% } %>
			</tr>
		</TABLE>
	</tr>

	<% } %>

<%--<tr>--%>
<%--<td style="text-align:center;width:100%">        						--%>
<%--<img src="images/british_airlines.jpg" width="300px" height="350px" />--%>
<%--</td>--%>
<%--</tr>--%>

<%--<tr>--%>
<%--<td style="text-align:center;width:100%">        						--%>
<%--<img src="images/china_airlines.jpg" width="300px" height="350px" />--%>
<%--</td>--%>
<%--</tr>--%>
    
</table>
<input type="hidden" id="valid" name="valid" value="<%=valid%>">

</div>
</body>
</html>

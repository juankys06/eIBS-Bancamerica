<html>
<head>
<title>e-IBS Client</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
body {    
	background : #FFFFFF;
	font-family : Verdana, Arial, Helvetica, Sans Serif;
	margin-top: 0px;
	margin-left: 16px;
	margin-right: 16px;
	margin-bottom: 0px;
}
#TXTLABEL{
  	text-align : left;border-style : none none none none;
  	cursor : default;
  	font-size : 8pt;
 	font-family : "Arial, Helvetica, sans-serif";
  	color : #010173;
  	background-color :transparent;
}
.title {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-weight: normal; color: #010171; text-decoration: underline}
.title A:VISITED {
	color:#010171;
}

.title A:LINK {
	color:#010171;
}

.title A:HOVER {
	color:#010171;
}
.data {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 9px; font-weight: normal; color: #181DC3; background-color: #EBEFF3; border-color: black #FFFFFF black black; border-right-width: medium; margin-right: auto; padding-right: 2px}
</style>
</head>

<body text="#000000">
<form>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="title" nowrap width="92"><a href="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0800?SCREEN=100" target="main">Cliente Actual</a> |</td>
    <td class="data" nowrap>ID: <input id="TXTLABEL" type=text name="E01CUSIDN" value="" readonly size=15></td>
    <td class="data" nowrap><input id="TXTLABEL" type=text name="E01CUSNA1" value="" readonly size=35></td>
    <td class="data" nowrap><input id="TXTLABEL" type=text name="E01RELBAN" value="" readonly size=35 style="color :red"></td>
  </tr>
</table>
</form>

</body>
</html>

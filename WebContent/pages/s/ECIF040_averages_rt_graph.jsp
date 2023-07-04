<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Promedios de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="aveBean" class="datapro.eibs.beans.JBAverage"  scope="session" />

</head>

<body>
<h3 align="center">Promedios de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="averages_rt_graph.jsp,ECIF040"></h3>
<hr size="4">
  <h4>Gráfica</h4>
  <table class="tableinfo">
    <tr> 
      <td nowrap>
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="100%"> 
				<%= aveBean.getGraph() %>
			</td>
      	  </tr>
		</table>
	  </td>
	</tr>
  </table>	
<p>
    <div align="center"> 
      <input id="EIBSBTN" type=button name="Submit" OnClick='top.close()' value="Cerrar">
    </div>
  </p>
</body>
</html>

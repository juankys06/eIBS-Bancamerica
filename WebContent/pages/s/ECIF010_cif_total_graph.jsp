<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Grafica de Operaciones en Libro por Cliente</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="cifTotal" class="datapro.eibs.beans.ECIF01003Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>

<%@ page import = "datapro.eibs.master.Util" %>

<h3 align="center">Resumen de Operaciones por Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cif_totals_graph.jsp,ECIF010"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECIF010" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="6">
  <h4>Operaciones en Libro</h4>
  <table class="tableinfo" width="90%" height="65%">
    <tr> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0" height="100%">
          <tr id="tbenter">
            <td nowrap colspan=2> 
              <div align="center"> <%
              		if ( !cifTotal.getE03TOTASS().equals("0.00") ) {
					   
              	%> <APPLET archive="eibs_applets.zip" code="datapro.eibs.applets.graph.PieChart.class" width="100%" height="100%" align="absmiddle" codebase="<%=request.getContextPath()%>/pages/s/">
                  <param name=title value="Activos">
                  <param name=showlabel   value="YES">
                  <param name=showpercent value="YES">
                  <param name=bgcolor value="#D1D1D1">
                  <param name=columns value="11">
                  <param name=Plabel1 value="Sobregiros">
                  <param name=Pvalue1 value="<%= Util.parseCCYtoDouble(cifTotal.getP03OVDRFT().trim())%>">
                  <param name=Pcolor1 value="#0066FF">
                  <param name=Plabel2 value="Hipotecarios">
                  <param name=Pvalue2 value="<%= Util.parseCCYtoDouble(cifTotal.getP03LNSMOR().trim())%>">
                  <param name=Pcolor2 value="#CC99FF">
                  <param name=Plabel3 value="Arrendamientos">
                  <param name=Pvalue3 value="<%= Util.parseCCYtoDouble(cifTotal.getP03LNSLSG().trim())%>">
                  <param name=Pcolor3 value="#00CCFF">
                  <param name=Plabel4 value="Consumo">
                  <param name=Pvalue4 value="<%= Util.parseCCYtoDouble(cifTotal.getP03LNSCON().trim())%>">
                  <param name=Pcolor4 value="#00C000">
                  <param name=Plabel5 value="Préstamos">
                  <param name=Pvalue5 value="<%= Util.parseCCYtoDouble(cifTotal.getP03LNSGRL().trim())%>">
                  <param name=Pcolor5 value="#00FFFF">
                  <param name=Plabel6 value="Descontadas">
                  <param name=Pvalue6 value="<%= Util.parseCCYtoDouble(cifTotal.getP03DESDOC().trim())%>">
                  <param name=Pcolor6 value="#FFFF00">
                  <param name=Plabel7 value="Inversiones">
                  <param name=Pvalue7 value="<%= Util.parseCCYtoDouble(cifTotal.getP03INVERA().trim())%>">
                  <param name=Pcolor7 value="#00FF33">
                  <param name=Plabel8 value="Aceptaciones">
                  <param name=Pvalue8 value="<%= Util.parseCCYtoDouble(cifTotal.getP03ACEPTA().trim())%>">
                  <param name=Pcolor8 value="#FFCC33">
                  <param name=Plabel9 value="C. de C.">
                  <param name=Pvalue9 value="<%= Util.parseCCYtoDouble(cifTotal.getP03LCCONF().trim())%>">
                  <param name=Pcolor9 value="#FF3300">
                  <param name=Plabel10 value="Spot">
                  <param name=Pvalue10 value="<%= Util.parseCCYtoDouble(cifTotal.getP03SPTPUR().trim())%>">
                  <param name=Pcolor10 value="#99FF33">
                  <param name=Plabel11 value="Forward">
                  <param name=Pvalue11 value="<%= Util.parseCCYtoDouble(cifTotal.getP03FRWPUR().trim())%>">
                  <param name=Pcolor11 value="#FFFF99">
                </APPLET><%
              		}
              	%> </div>
            </td>
            <td nowrap colspan=2> 
              <div align="center">
              	<%
              		if ( !cifTotal.getE03TOTLIA().equals("0.00") ) {
					   
              	%>
					<APPLET archive="eibs_applets.zip" code="datapro.eibs.applets.graph.PieChart.class" width="100%" height="100%" codebase="<%=request.getContextPath()%>/pages/s/">
                  <param name=title value="Pasivos">
                  <param name=showlabel   value="YES">
                  <param name=showpercent value="YES">
                  <param name=bgcolor value="#D1D1D1">
                  <param name=columns value="10">
                  <param name=Plabel1 value="Cta sin Int">
                  <param name=Pvalue1 value="<%= Util.parseCCYtoDouble(cifTotal.getP03CTACTE().trim())%>">
                  <param name=Pcolor1 value="#00CCFF">
                  <param name=Plabel2 value="Ahorro">
                  <param name=Pvalue2 value="<%= Util.parseCCYtoDouble(cifTotal.getP03CTAAHO().trim())%>">
                  <param name=Pcolor2 value="#FFFF00">
                  <param name=Plabel3 value="Certificados">
                  <param name=Pvalue3 value="<%= Util.parseCCYtoDouble(cifTotal.getP03CDTDPO().trim())%>">
                  <param name=Pcolor3 value="#00FF33">
                  <param name=Plabel4 value="Inversiones">
                  <param name=Pvalue4 value="<%= Util.parseCCYtoDouble(cifTotal.getP03INVERP().trim())%>">
                  <param name=Pcolor4 value="#99FF33">
                  <param name=Plabel5 value="Participaciones">
                  <param name=Pvalue5 value="<%= Util.parseCCYtoDouble(cifTotal.getP03PARTIC().trim())%>">
                  <param name=Pcolor5 value="#FF3300">
                  <param name=Plabel6 value="Aceptaciones">
                  <param name=Pvalue6 value="<%= Util.parseCCYtoDouble(cifTotal.getP03ACEPTP().trim())%>">
                  <param name=Pcolor6 value="#CC99FF">
                  <param name=Plabel7 value="C. de C.">
                  <param name=Pvalue7 value="<%= Util.parseCCYtoDouble(cifTotal.getP03LCPROC().trim())%>">
                  <param name=Pcolor7 value="#0066FF">
                  <param name=Plabel8 value="Spot">
                  <param name=Pvalue8 value="<%= Util.parseCCYtoDouble(cifTotal.getP03SPTSAL().trim())%>">
                  <param name=Pcolor8 value="#FFCC33">
                  <param name=Plabel9 value="Forward">
                  <param name=Pvalue9 value="<%= Util.parseCCYtoDouble(cifTotal.getP03FRWSAL().trim())%>">
                  <param name=Pcolor9 value="#FF0000">
                  <param name=Plabel10 value="Cta con Int">
                  <param name=Pvalue10 value="<%= Util.parseCCYtoDouble(cifTotal.getP03CTAMMK().trim())%>">
                  <param name=Pcolor10 value="#FF6600">
                </APPLET>
              	<%
              		}
              	%>
				  </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <div align="center"> 
          <br>
          <div align="center"> 
            <input id="EIBSBTN" type=button name="Submit" value="Cerrar" onClick="top.close()">
  </div>
</form>
</body>
</html>

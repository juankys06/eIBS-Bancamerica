<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Solicitud de Plastico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0002DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Distribución y Recepción de Plástico
<BR>Nueva
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="plastic_dist_recep_new.jsp, ECD0002"> 
</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0002" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="5">
  <INPUT TYPE=HIDDEN NAME="CONFIRM" VALUE="0">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"># Solicitud : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="hidden" name="E02CDRNUM" size="15" maxlength="13" value="<%= msgCD.getE02CDRNUM() %>">
                <input type="text" name="E02CDRNUMNEW" size="15" maxlength="13" readonly value="NUEVA">
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Tipo de Plastico : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CDRTPL" size="3" maxlength="2" value="<%= msgCD.getE02CDRTPL() %>">
				<a href="javascript:GetDescATMCard('E02CDRTPL')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Oficina Origen : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CDRBRN" size="5" maxlength="4" value="<%= msgCD.getE02CDRBRN() %>" readonly="readonly" >
                <!-- 
				<a href="javascript:GetBranch('E02CDRBRN','')">
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a> 
				 -->
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Oficina Destino : </div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E02CDRBRD" size="5" maxlength="4" value="<%= msgCD.getE02CDRBRD() %>">
				<a href="javascript:GetBranch('E02CDRBRD','')">
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>
                 </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Tarjeta Inicio : </div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02CDRINI" size="20" maxlength="19" value="<%= msgCD.getE02CDRINI() %>">
				<a href="javascript:GetPlastic3('E02CDRINI', document.forms[0].E02CDRTPL.value,document.forms[0].E02CDRBRN.value, '','1')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Tarjeta Final : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CDREND" size="20" maxlength="19" value="<%= msgCD.getE02CDREND() %>">
				<a href="javascript:GetPlastic3('E02CDREND', document.forms[0].E02CDRTPL.value, document.forms[0].E02CDRBRN.value, '', '1')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
			  </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Aceptar">
  </div>
  </form>
</body>
</html>

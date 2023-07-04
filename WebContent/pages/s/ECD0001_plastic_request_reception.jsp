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

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0001DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Solicitud de Plastico 
<BR>Recepci&oacute;n
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="plastic_request_reception.jsp, ECD0001">
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
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0001" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">

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
                <input type="text" name="E01CDRNUM" size="15" maxlength="13" readonly value="<%= msgCD.getE01CDRNUM() %>">
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Estado : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="hidden" name="E01CDRSTS" size="2" maxlength="1" readonly value="<%= msgCD.getE01CDRSTS() %>">
				<input type="text" name="E01CDRSTD" size="17" maxlength="15" readonly value="<%= msgCD.getE01CDRSTD() %>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Tipo de Plastico : </div>
            </td>
            <td nowrap width="20%" >
              <div align="left"> 
                <input type="hidden" name="E01CDRTPL" size="3" maxlength="2" value="<%= msgCD.getE01CDRTPL() %>">
                <input type="text" name="E01CDRNPL" size="35" maxlength="30" readonly value="<%= msgCD.getE01CDRNPL() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right">Proveedor : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="hidden" name="E01CDRVEN" size="12" maxlength="10" value="<%= msgCD.getE01CDRVEN() %>">
                <input type="text" name="E01CDRNAM" size="17" maxlength="15" readonly value="<%= msgCD.getE01CDRNAM() %>">
                 </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Cantidad : </div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CDRQTY" size="8" maxlength="7" readonly value="<%= msgCD.getE01CDRQTY() %>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right">Cantidad Recibida : </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CDRREQ" size="15" maxlength="13" value="<%= msgCD.getE01CDRREQ() %>" onKeypress="enterInteger()">
			  </div>
            </td>
          </tr>
	      <tr id="trclear"> 
	        <td align=CENTER width="18%"> 
	          <div align="right">Fecha de Vencimiento :</div>
	        </td>
	        <td align=CENTER width="34%"> 
	          <div align="left"> 
	            <input type="text" name="E01CDRMOX"  size="3" maxlength="2" value="<%= msgCD.getE01CDRMOX() %>" readonly > / 
	            <input type="text" name="E01CDRYEX"  size="3" maxlength="2" value="<%= msgCD.getE01CDRYEX() %>" readonly >
	            (Mes/A&ntilde;o) 
	          </div>
	        </td>
	        <td align=CENTER width="18%"> </td>
	        <td align=CENTER width="34%"> </td>
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

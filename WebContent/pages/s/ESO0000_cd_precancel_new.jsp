<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<html>
<head>
<title>Solicitud de Pre-Cancelación</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ESO000001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Solicitud de Pre-Cancelación
<BR>Nueva
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cd_precancel_new.jsp, ESO0000"> 
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
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESO0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Número de Certificado : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E01SOLACC" size="15" maxlength="13" value="" readonly>
              	<a href="javascript:GetAccount('E01SOLACC','','CD','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
			  </div>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Cliente : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E01SOLCUN" size="11" maxlength="10" value="">
                <input type="text" name="E01CUSNA1" size="46" maxlength="45" value="">
				<a href="javascript:GetCustomerDescId('E01SOLCUN', 'E01CUSNA1', '')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Capital : </div>
            </td>
            <td nowrap width="25%" > 
              <div align="left"> 
                <input type="text" name="E01SOLPRI" size="20" maxlength="19" value="" onKeypress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento : </div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
                <input type="text" name="E01DEAMA2" size="3" maxlength="2" value="">
                <input type="text" name="E01DEAMA1" size="3" maxlength="2" value="">
                <input type="text" name="E01DEAMA3" size="3" maxlength="2" value="">
                <a href="javascript:DatePicker(document.forms[0].E01DEAMA2, document.forms[0].E01DEAMA1, document.forms[0].E01DEAMA3)">
                <img src="<%=request.getContextPath()%>/images/calendar.gif" alt="Ayuda" align="absmiddle" border="0"></a> 
              </div>
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de Cancelación : </div>
            </td>
            <td nowrap width="25%"> 
              <div align="left"> 
              	<select name="E01SOLPVI">
                  <option value=" "></option>
                  <option value="A">Acreditar en Cuenta</option>
                  <option value="C">Cheque de Gerencia</option>
                  <option value="G">Cuenta Puente</option>
                </select>
              </div>
            </td>
            <td nowrap width="25%" ></td>
            <td nowrap width="25%" ></td>
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

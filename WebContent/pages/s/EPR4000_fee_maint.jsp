<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Parametros Tablas de Comisiones para Transferencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lnParam" class="datapro.eibs.beans.EPR400002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
	init(opth,field,bank,ccy,field1,field2,opcod);
	showPopupHelp();
}

function checkValues() {
	return true;
}
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }       
%>

</head>
<body>
<h3 align="center">Parametros Tablas de Comisiones Para Transferencias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fee_maint.jsp, EPR4000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEPR4000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="200">
  <INPUT TYPE=HIDDEN NAME="OPT" value="<%= request.getParameter("OPT") %>">
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap align=right> 
              <b>Banco :</b>
            </td>
            <td nowrap > 
              <input type="text" name="E02PRCBNK" size="3" maxlength="2" value="<%= lnParam.getE02PRCBNK()%>" readonly>
            </td>
            <td nowrap align=right> 
              <b>Tabla de Comision :</b>
            </td>
            <td nowrap > 
               <input type="text" name="E02PRCCOM" size="3" maxlength="2" value="<%= lnParam.getE02PRCCOM()%>" readonly>
            </td>
          </tr>
          <tr id="trclear">   
            <td nowrap align=right> 
              <b>Origen :</b>
            </td>
            <td nowrap > 
               <input type="text" name="E02PRCORG" size="2" maxlength="1" value="<%= lnParam.getE02PRCORG()%>" readonly>
            </td>
            <td nowrap align=right> 
              <b>Descripcion :</b>
            </td> 
            <td nowrap > 
              <input type="text" name="D02PRCDS1" size="36" maxlength="35" value="<%= lnParam.getD02PRCDS1()%>" readonly>
            </td>            
          </tr>
          <tr id="trdark"> 
            <td nowrap align=right> 
              <b>Tipo Transferencia :</b>
            </td>
            <td nowrap > 
               <input type="text" name="E02PRCTYP" size="3" maxlength="2" value="<%= lnParam.getE02PRCTYP()%>" readonly>
            </td>
            <td nowrap align=right> 
              <b>Descripcion :</b>
            </td>
            <td nowrap>  
               <input type="text" name="D02PRCDS2" size="36" maxlength="35" value="<%= lnParam.getD02PRCDS2()%>" readonly>
            </td>   
          </tr>
          <tr id="trclear"> 
            <td nowrap align=right> 
              <b>Tipo Proceso :</b>
            </td>
            <td nowrap > 
               <input type="text" name="E02PRCCOP" size="2" maxlength="1" value="<%= lnParam.getE02PRCCOP()%>" readonly>
            </td>
            <td nowrap align=right> 
              <b>Descripcion :</b>
            </td>
            <td nowrap>  
               <input type="text" name="D02PRCDS3" size="36" maxlength="35" value="<%= lnParam.getD02PRCDS3()%>" readonly>
            </td>   
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
         <tr id="trdark"> 
            <td nowrap align=right> 
              <b>Monto Minimo :</b>
            </td>
            <td nowrap > 
              <input type="text" name="E02PRCAMI" size="17" maxlength="15" value="<%= lnParam.getE02PRCAMI()%>" onkeypress="enterDecimal()">
            </td>
            <td nowrap align=right> 
              <b>Monto Maximo :</b>
            </td>
            <td nowrap > 
              <input type="text" name="E02PRCAMF" size="17" maxlength="15" value="<%= lnParam.getE02PRCAMF()%>" onkeypress="enterDecimal()">
            </td>
         </tr>  
         <tr id="trclear"> 
            <td nowrap align=right> 
              <b>Codigo ID Swift - 1 :</b>
            </td>
            <td nowrap > 
              <input type="text" name="E02PRCSW1" size="13" maxlength="12" value="<%= lnParam.getE02PRCSW1()%>">
            </td>
            <td nowrap align=right> 
              <b>Codigo ID Swift - 2 :</b>
            </td>
            <td nowrap > 
              <input type="text" name="E02PRCSW2" size="13" maxlength="12" value="<%= lnParam.getE02PRCSW2()%>">
            </td>
         </tr>  
         <tr id="trdark"> 
            <td nowrap align=right> 
              <b>Codigo ID Swift - 3 :</b>
            </td>
            <td nowrap > 
              <input type="text" name="E02PRCSW3" size="13" maxlength="12" value="<%= lnParam.getE02PRCSW3()%>">
            </td>
            <td nowrap align=right> 
              <b>Codigo ID Swift - 4 :</b>
            </td>
            <td nowrap > 
              <input type="text" name="E02PRCSW4" size="13" maxlength="12" value="<%= lnParam.getE02PRCSW4()%>">
            </td>  
         </tr>  
     </table>
    </td>
    </tr>
  </table>
  
  <p align="center"> 
        <input id="EIBSBTN" type=submit name="Submit" value="Ingresar">
  </p>
  
  </form>
</body>
</html>

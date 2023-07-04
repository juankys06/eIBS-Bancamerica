<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Balances de cuentas contables</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="balance" class="datapro.eibs.beans.EGL075002Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


</head>

<body>

<%@ page import = "datapro.eibs.master.Util" %>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>

<h3 align="center">Balances G/L  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="inq_balances.jsp,EGL0750"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.accounting.JSEGL0750" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="4">
  <input type=HIDDEN name="SELMTH">
  <h4>Informaci&oacute;n de la Cuenta Contable</h4>
 <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap>
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%" height="31"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="25%" height="30"> 
              <input type="text" readonly name="BANCO"  size="3" maxlength="2" value="<%= balance.getE02GLBBNK().trim()%>">
              <input type="text" readonly name="SUCURSAL"  size="4" maxlength="3" value="<%= balance.getE02GLBBRN().trim()%>">
              <input type="text" readonly name="MONEDA"  size="4" maxlength="3" value="<%= balance.getE02GLBCCY().trim()%>">
              <input type="text" readonly name="CUENTA"  size="17" maxlength="16" value="<%= balance.getE02GLBGLN().trim()%>">
            </td>
            <td nowrap width="25%" height="31"> 
              <div align="right">Descripcion :</div>
            </td>
            <td nowrap width="25%" height="31"> 
              <input type="text" readonly name="E02GLBDSC"  size="35" maxlength="45" value="<%= balance.getE02GLBDSC().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4></h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap height="36"> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
         <tr id="trclear"> 
            <td nowrap height="25" bordercolor="#000000"> 
              <div align="center"><b></b></div>
            </td>
            <td nowrap height="25" height="50" bordercolor="#000000"> 
              <div align="center"><b>Año Actual</b></div>
            </td>
            <td nowrap height="25" bordercolor="#000000"> 
              <div align="center"><b></b></div>
            </td>
            <td nowrap height="25" height="50" bordercolor="#000000"> 
              <div align="right"><b>Año Anterior</b></div>
            </td>
                       
          </tr>
          <tr id="trdark"> 
            <td nowrap height="20" bordercolor="#000000"> 
              <div align="center"><b></b></div>
            </td>
            <td nowrap height="20" bordercolor="#000000"> 
              <div align="center"><b>Ciclo</b></div>
            </td>
            <td nowrap height="20" bordercolor="#000000"> 
              <div align="center"><b>Acumulado </b></div>
            </td>
            <td nowrap height="20" bordercolor="#000000"> 
              <div align="center"><b>Ciclo</b></div>
            </td>
            <td nowrap height="20" bordercolor="#000000"> 
              <div align="center"><b>Acumulado</b></div>
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000" height="31"> 
              <div align="right"><b>Inicio de Balance :</b></div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBINI" size="17" maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBINI())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYINI" size="17" maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYINI())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="31" bordercolor="#000000" > 
              <div align="center">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Enero :</div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBENE" size="17" maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBENE())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLBQT1" size="17" maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQT1())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYENE" size="17" maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYENE())%>" >
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <input type="text" id="txtright" readonly name="E02GLYQT1" size="17" maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQT1())%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000" height="35"> 
              <div align="right">Febrero : </div>
            </td>
            <td nowrap width="20%" height="35" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBFEB" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBFEB())%>"></div>
            </td>
            <td nowrap width="20%" height="35" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQT2" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQT2())%>">
              </div>
            </td>
            <td nowrap width="20%" height="35" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYFEB" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYFEB())%>">
              </div>
            </td>
            <td nowrap width="20%" height="35" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQT2" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQT2())%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Marzo : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBMAR" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBMAR())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQT3" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQT3())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYMAR" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYMAR())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQT3" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQT3())%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Abril :</div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"> 
                <INPUT
					type="text" id="txtright" readonly name="E02GLBABR" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBABR())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQT4" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQT4())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYABR" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYABR())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQT4" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQT4())%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Mayo :</div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBMAY" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBMAY())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQT5" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQT5())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYMAY" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYMAY())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQT5" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQT5())%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Junio : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBJUN" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBJUN())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQT6" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQT6())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYJUN" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYJUN())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQT6" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQT6())%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Julio : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBJUL" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBJUL())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQT7" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQT7())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYJUL" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYJUL())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQT7" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQT7())%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Agosto : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBAGO" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBAGO())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQT8" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQT8())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYAGO" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYAGO())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQT8" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQT8())%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Setiembre : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBSET" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBSET())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQT9" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQT9())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYSET" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYSET())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQT9" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQT9())%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Octubre : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBOCT" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBOCT())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQTA" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQTA())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYOCT" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYOCT())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQTA" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQTA())%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Noviembre : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBNOV" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBNOV())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQTB" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQTB())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYNOV" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYNOV())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQTB" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQTB())%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right">Diciembre : </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center">
                   <INPUT
					type="text" id="txtright" readonly name="E02GLBDIC" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBDIC())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQTC" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQTC())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYDIC" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYDIC())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQTC" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQTC())%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" bordercolor="#000000"> 
              <div align="right"><b>Final deBalance : </b></div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" >
				<div align="center">
				<INPUT type="text" id="txtright" readonly name="E02GLBFIN"
					size="17" maxlength="15"
					value="<%= Util.formatCCY(balance.getE02GLBFIN())%>">
				<div align="center">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLBQTO" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLBQTO())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYFIN" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYFIN())%>">
              </div>
            </td>
            <td nowrap width="20%" height="22" bordercolor="#000000" > 
              <div align="center"><INPUT
					type="text" id="txtright" readonly name="E02GLYQTO" size="17"
					maxlength="15" value="<%= Util.formatCCY(balance.getE02GLYQTO())%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="5" bordercolor="#000000"> 
              <div align="left"></div>
            </td>
          </tr>
         
          <tr id="trclear"> 
            <td nowrap colspan="5" bordercolor="#000000"> 
              <div align="left"></div>
            </td>
          </tr>
          
        </table>
        
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"></div>
      </td>
      <td width="33%">
        <div align="center">
        </div>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td colspan="2"> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>

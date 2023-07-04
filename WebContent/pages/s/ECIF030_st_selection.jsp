<html>
<head>
<title>Estado de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body nowrap>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if (userPO.getPurpose().equals("INQUIRY")){
%>
<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
%>

<%
}
%>

</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   if (userPO.getPurpose().equals("INQUIRY")){ 
   out.println("<SCRIPT> initMenu(); </SCRIPT>");}
%> 


<h3 align="center"> Estado de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="st_selection.jsp,ECIF030"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF030" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="11" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="50" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="14" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="50" maxlength="45" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr > 
      <td nowrap height="143"> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Seleccionar Transacci&oacute;n por :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <input type="radio" name="E01HISCYC" value="C" checked>
              <b>Este Ciclo</b></td>
            <td nowrap width="10%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="32%"> 
              <input type="radio" name="E01HISCYC" value="H">
              <b>Hist&oacute;rico</b></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Tipo de Fecha :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <input type="radio" value="B" name="E01VALBTH" checked>
              <b> Fecha Proceso</b></td>
            <td nowrap width="10%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="32%"> 
              <input type="radio" value="V" name="E01VALBTH" >
              <b>Fecha Valor</b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Seleccionar Fechas desde :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" name="E01FRDTE1" size="2" maxlength="2">
                <input type="text" name="E01FRDTE2" size="2" maxlength="2">
                <input type="text" name="E01FRDTE3" size="2" maxlength="2">
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="32%"> 
              <div align="left"> 
                <input type="text" name="E01TODTE1" size="2" maxlength="2">
                <input type="text" name="E01TODTE2" size="2" maxlength="2">
                <input type="text" name="E01TODTE3" size="2" maxlength="2">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="32%"> 
              <div align="right"><b>Seleccionar Cheques desde :</b></div>
            </td>
            <td nowrap width="3%">&nbsp;</td>
            <td nowrap width="23%"> 
              <div align="left"> 
                <input type="text" name="E01FRCHKN" size="9" maxlength="9">
              </div>
            </td>
            <td nowrap width="10%"> 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="32%"> 
              <div align="left"> 
                <input type="text" name="E01TOCHKN" size="9" maxlength="9">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="32%" height="26"> 
              <div align="right"><b>Seleccionar Montos desde :</b></div>
            </td>
            <td nowrap width="3%" height="26">&nbsp;</td>
            <td nowrap width="23%" height="26"> 
              <div align="left"> 
                <input type="text" name="E01FRAMNT" size="15" maxlength="15" onKeypress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="10%" height="26"> 
              <div align="right"><b>hasta :</b></div>
            </td>
            <td nowrap width="32%" height="26"> 
              <div align="left"> 
                <input type="text" name="E01TOAMNT" size="15" maxlength="15" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 <br>
 <div align="center"> 
 	<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
 </div>
  </form>
</body>
</html>

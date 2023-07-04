<html>
<head>
<title>Exchange Rate</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cdRate" class= "datapro.eibs.beans.EDL013006Message" scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cdm_m_opt);

</SCRIPT>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu();  </SCRIPT>");
 }
%>

<h3 align="center">Tasa de Cambio<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_xchg_rate.jsp, EDL0130M"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="20">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E06DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E06CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E06DEAACC" size="12" maxlength="9" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E06DEAPRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Tasas</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right"> Tasa Actual :</div>
            </td>
            <td width="61%"> 
              <input type="text" name="E06OLDRTE" size="15" maxlength="15" value="<%= cdRate.getE06OLDRTE().trim()%>" onKeypress="enterSignDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%"> 
              <div align="right">Nueva Tasa :</div>
            </td>
            <td width="61%"> 
              <input type="text" name="E06NEWRTE" size="15" maxlength="15" value="<%= cdRate.getE06NEWRTE().trim()%>" onKeypress="enterSignDecimal()">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%" > 
              <div align="right">Fecha Valor :</div>
            </td>
            <td width="61%">
              <input type="text" name="E06TRNVD1" size="2" maxlength="2" value="<%= cdRate.getE06TRNVD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E06TRNVD2" size="2" maxlength="2" value="<%= cdRate.getE06TRNVD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E06TRNVD3" size="2" maxlength="2" value="<%= cdRate.getE06TRNVD3().trim()%>" onkeypress="enterInteger()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%" > 
              <div align="right">Comentario :</div>
            </td>
            <td width="61%" > 
              <input type="text" name="E06DEANR1" size="30" maxlength="30" value="<%= cdRate.getE06DEANR1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right"></div>
            </td>
            <td width="61%"> 
              <input type="text" name="E06DEANR2" size="30" maxlength="30" value="<%= cdRate.getE06DEANR2().trim()%>">
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

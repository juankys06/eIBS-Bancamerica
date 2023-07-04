<html>
<head>
<title>Exchange Rate</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cdRate" class= "datapro.eibs.beans.EDL010506Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cp_i_opt);


</SCRIPT>


</head>

<body nowrap>



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

<h3 align="center">Tasa de Cambio<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cp_inq_xchg_rate.jsp, EDL0105"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>No. Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E06DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E06CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta:</b></div>
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
                <input type="text" name="E06DEAPRO" size="4" maxlength="4" readonly value="<%= userPO.getProdCode().trim()%>">
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
              <input type="text" name="E06OLDRTE" readonly  size="15" maxlength="15" value="<%= cdRate.getE06OLDRTE().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%"> 
              <div align="right">Nueva Tasa :</div>
            </td>
            <td width="61%"> 
              <input type="text" name="E06NEWRTE" readonly  size="15" maxlength="15" value="<%= cdRate.getE06NEWRTE().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%" > 
              <div align="right">Fecha Valor :</div>
            </td>
            <td width="61%">
              <input type="text" name="E06TRNVD1" readonly  size="2" maxlength="2" value="<%= cdRate.getE06TRNVD1().trim()%>" >
              <input type="text" name="E06TRNVD2" readonly  size="2" maxlength="2" value="<%= cdRate.getE06TRNVD2().trim()%>" >
              <input type="text" name="E06TRNVD3" readonly  size="2" maxlength="2" value="<%= cdRate.getE06TRNVD3().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="39%" > 
              <div align="right">Descripción :</div>
            </td>
            <td width="61%" > 
              <input type="text" name="E06DEANR1" readonly  size="30" maxlength="30" value="<%= cdRate.getE06DEANR1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="39%"> 
              <div align="right"></div>
            </td>
            <td width="61%"> 
              <input type="text" name="E06DEANR2" readonly  size="30" maxlength="30" value="<%= cdRate.getE06DEANR2().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
   <br>
</form>
</body>
</html>

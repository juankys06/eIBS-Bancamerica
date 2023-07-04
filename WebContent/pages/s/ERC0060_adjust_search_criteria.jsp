<html>
<head>
<title>Estado de Cuentas Contables</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<h3 align="center"> Ajuste Transacciones Reconciliaci&oacute;n Bancaria <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="adjust_search_criteria.jsp,ERC0060"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSERC0060?SCREEN=200" >
  <INPUT TYPE=HIDDEN NAME="SCREEN">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
         
          <tr id="trdark">
            <td nowrap width="23%">
              <div align="right"><b>Banco : </b></div>
            </td>
            <td nowrap width="32%">
              <input type="text" name="E01RCTBNK" size="3" maxlength="2">
               </td>
          </tr>



          <tr id="trdark">
            <td nowrap width="23%">
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="32%">
              <input type="text" name="E01RCTCCY" size="3" maxlength="3">
              <a href="javascript:GetCurrency('E01RCTCCY',document.forms[0].E01RCTBNK.value)"> 
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" > 
              </a> </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="23%">
              <div align="right"><b>Cuenta Contable : </b></div>
            </td>
            <td nowrap width="32%">
              <input type="text" name="E01RCTGLN" size="20" maxlength="16" onKeypress="enterInteger()">
              <a href="javascript:GetLedger('E01RCTGLN',document.forms[0].E01RCTBNK.value,document.forms[0].E01RCTCCY.value,'')"> 
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" > 
              </a> </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="23%">
              <div align="right"><b>N&uacute;mero de Cuenta : </b></div>
            </td>
            <td nowrap width="32%">
              <input type="text" name="E01RCTACC" size="12" maxlength="12" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E01RCTACC','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              </a> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <input type="hidden" name="E01TRABNK">
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
  </form>
</body>
</html>

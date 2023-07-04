<HTML>
<HEAD>
<TITLE>
Cheques de Gerencia
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "ewd0115Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<script language="javascript">
//<!-- Hide from old browsers
function enter(bank,ledger,ccy,fmt,desc) {
   document.forms[0].BNK.value = bank;
   document.forms[0].GLN.value = ledger;
   document.forms[0].CCY.value = ccy;
   document.forms[0].FTY.value = fmt;
   document.forms[0].DSC.value = desc;
   document.forms[0].submit();
}
//-->
</script>


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0180">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="BNK" VALUE="">
  <INPUT TYPE=HIDDEN NAME="GLN" VALUE="">
  <INPUT TYPE=HIDDEN NAME="CCY" VALUE="">
  <INPUT TYPE=HIDDEN NAME="FTY" VALUE="">
  <INPUT TYPE=HIDDEN NAME="DSC" VALUE="">
  <h3 align="center">Emisi&oacute;n de Ordenes de no Pago<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_enter_stop_pay, EDD0180"> 
  </h3>
  <hr size="4">
  <%
	if ( ewd0115Help.getNoResult() ) {
 %> 
  <TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
 <BR>
  <table class="tableinfo">
    <tr id="trdark"> 
      <th colspan="4" height="24">
        <div align="left">Formatos :</div>
      </th>
    </tr>
    <tr id ="trdark"> 
      <th width="8%" height="24">Banco</th>
      <th width="8%" height="24">Moneda</th>
      <th width="6%" height="24">Formato </th>
      <th width="78%" height="24">Descripcion</th>
    </tr>
    <%
                ewd0115Help.initRow();
                while (ewd0115Help.getNextRow()) {
                    if (ewd0115Help.getFlag().equals("")) {
                    		out.println(ewd0115Help.getRecord());
                    }
                }
    %> 
  </table>
  <%
  }
%>
</FORM>

</BODY>
</HTML>

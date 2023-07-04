<HTML>
<HEAD>
<TITLE>
Cheques de Gerencia
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "ewd0115Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<BODY>
<script language="javascript">
//<!-- Hide from old browsers
function enter(type , ccy) {
   document.forms[0].E01OFMBNK.value = '01';
   document.forms[0].E01OFMFTY.value = type;
   document.forms[0].E01OFMCCY.value = ccy;
   document.forms[0].submit();
}
//-->
</script>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <INPUT TYPE=HIDDEN NAME="E01OFMBNK" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01OFMFTY" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01OFMCCY" VALUE="">
  <h3 align="center">Emisi&oacute;n de Cheques <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_chk_new, EOF0115"> 
  </h3>
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
  <hr size="4">
  <p>&nbsp;</p>
  <p>&nbsp;</p>
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
  <p align=center>&nbsp; </p>

  <%
  }
%> 
  <div align="center"> </div>
</FORM>

</BODY>
</HTML>

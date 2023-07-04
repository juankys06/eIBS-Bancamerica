<HTML>
<HEAD>
<TITLE>
Cheques de Gerencia
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "ewd0115Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

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


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="800">
  <INPUT TYPE=HIDDEN NAME="E01OFMBNK" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01OFMFTY" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01OFMCCY" VALUE="">
  <input type="hidden" id="E01OFMPTH" name="E01OFMPTH" value="<%= userPO.getHeader23().trim()%>" >
  <h3 align="center">Emisi&oacute;n de Cheques por Desembolso de Préstamos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_chk_new_lns, EOF0115"> 
  </h3>

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
                <input type="text" name="E30DEACUN" size="9" maxlength="9" value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E30CUSNA1" size="45" maxlength="45" value="<%= userPO.getHeader3().trim()%>" >
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E30DEAACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E30DEACCY2" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" >
                <input type="text" name="E30DEAAMT" size="15" maxlength="15" value="<%= userPO.getHeader22().trim()%>" >
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E30DEAPRO" size="4" maxlength="4" value="<%= userPO.getHeader1().trim()%>" >
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  


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

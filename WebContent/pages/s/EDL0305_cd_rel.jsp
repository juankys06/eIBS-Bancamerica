<html>
<head>
<title>Deducciones y Relaciones entre Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cifPos" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cd_i_opt);

</SCRIPT>
</head>

<BODY>

<SCRIPT> initMenu(); </SCRIPT>

<h3 align="center">Relaciones entre Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_rel.jsp,EDL0305"></h3>
<hr size="4">
  <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JEDL0305" >
    <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
	<INPUT TYPE=HIDDEN NAME="CODE" VALUE="">
	<INPUT TYPE=HIDDEN NAME="TYPE" VALUE="">
	<INPUT TYPE=HIDDEN NAME="ACCOUNT" VALUE="">
<%
	if ( cifPos.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center">
        		<font size="3"><b>
        				No hay resultados que correspondan a su criterio de búsqueda 
        		</b></font>
        	</div>
      </TD></TR>
   		</TABLE>
  <%   		
	}
	else {
%> 
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
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
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
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <h4>Relaciones entre Cuentas</h4>
  <TABLE class="tableinfo">
    <TR id=trdark> 
      <TH ALIGN=CENTER >Cuenta</TH>
      <TH ALIGN=CENTER >Nombre</TH>
      <TH ALIGN=CENTER >Vencimiento</TH>
      <TH ALIGN=CENTER >Tasa</TH>
      <TH ALIGN=CENTER >Principal</TH>
      <TH ALIGN=CENTER >Inter&eacute;s</TH>
      <TH ALIGN=CENTER >Tipo</TH>
    </TR>
    <%
                cifPos.initRow();
                while (cifPos.getNextRow()) {
                    if (cifPos.getFlag().equals("2")) {
                    		out.println(cifPos.getRecord());
                    }
                }
    %> 
  </TABLE>
  <h4><%
  }
%> </h4>
</form>
</body>
</html>

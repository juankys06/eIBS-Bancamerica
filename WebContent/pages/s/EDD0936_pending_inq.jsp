<HTML>
<HEAD>
<TITLE>
Cuentas Pendientes de Cobro
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "pending" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


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

</head>

<body>

<%
   if (userPO.getPurpose().equals("INQUIRY")){ 
   out.println("<SCRIPT> initMenu(); </SCRIPT>");}
%> 


<h3 align="center">Cuentas Pendientes de Cobro<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pending_inq.jsp,EDD0936"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0936" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">

<%
	if ( pending.getNoResult() ) {
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
    <tr bordercolor="#FFFFFF"> 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" >
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%= userPO.getHeader2().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="CUSNA1" size="45" readonly maxlength="45" value="<%= userPO.getHeader3().trim()%>" >
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%">
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="ACCNUM" size="14" readonly maxlength="12" value="<%= userPO.getIdentifier().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left">
                <input type="text" name="PROCCY" size="4" readonly maxlength="4" value="<%= userPO.getCurrency().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left">
                <input type="text" name="PROCOD" size="4" readonly maxlength="4" value="<%= userPO.getHeader1().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<h4></h4> 
  
  <TABLE class="tableinfo">
    <TR id="trdark"> 
      <TH ALIGN=CENTER>Fecha</TH>
      <TH ALIGN=CENTER>No de<br>
        Cheque</TH>
      <TH ALIGN=CENTER>TR</TH>
      <TH ALIGN=CENTER>Descripción</TH>
      <TH ALIGN=CENTER>Monto</TH>
      <TH ALIGN=CENTER>No de<br>
        Lote</TH>
      <TH ALIGN=CENTER>Status</TH>
    </TR>
    <%
                pending.initRow();
                while (pending.getNextRow()) {
                    // if (pending.getFlag().equals("")) {
                    		out.println(pending.getRecord());
                    // }
                }
    %> 
  </TABLE>

<%
  }
%>

</FORM>

</BODY>
</HTML>

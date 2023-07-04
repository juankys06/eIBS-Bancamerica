<HTML>
<HEAD>
<TITLE>
Lista de Cheques
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "checks" class= "datapro.eibs.beans.JBList" scope="session" />

<jsp:useBean id="rtBasic" class="datapro.eibs.beans.ECH056503Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>

<h3 align="center">Consulta de Chequeras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="checks_inq.jsp,ECH0565"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0565" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">

<%
	if ( checks.getNoResult() ) {
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
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E03ACMCUN" size="9" maxlength="9" value="<%= rtBasic.getE03ACMCUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E03CUSNA1" size="45" readonly maxlength="45" value="<%= rtBasic.getE03CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E03ACMACC" size="12" readonly maxlength="12" value="<%= rtBasic.getE03ACMACC().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E03ACMCCY" size="4" readonly maxlength="4" value="<%= rtBasic.getE03ACMCCY().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E03ACMPRO" size="4" readonly maxlength="4" value="<%= rtBasic.getE03ACMPRO().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<h4></h4> 
  
  <TABLE class="tableinfo" >
    <TR id ="trdark"> 
      <TH ALIGN=CENTER >Cheque</TH>
      <TH ALIGN=CENTER >Status</TH>
      <TH ALIGN=CENTER >Cheque</TH>
      <TH ALIGN=CENTER >Status</TH>
      <TH ALIGN=CENTER >Cheque</TH>
      <TH ALIGN=CENTER >Status</TH>
      <TH ALIGN=CENTER >Cheque</TH>
      <TH ALIGN=CENTER >Status</TH>
      <TH ALIGN=CENTER >Cheque</TH>
      <TH ALIGN=CENTER >Status</TH>
    </TR>
    <%
                checks.initRow();
                while (checks.getNextRow()) {
                    // if (checks.getFlag().equals("")) {
                    		out.println(checks.getRecord());
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

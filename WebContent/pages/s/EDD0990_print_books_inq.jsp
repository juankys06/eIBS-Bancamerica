<HTML>
<HEAD>
<TITLE>
Lineas de Impresion de Libretas de Ahorro
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "prBooks" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 

<SCRIPT Language="Javascript">

   builtNewMenu(sv_i_opt);

</SCRIPT>

</head>

<body>

<SCRIPT> initMenu(); </SCRIPT>


<h3 align="center">Lineas de Impresi&oacute;n de Libretas de Ahorro<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="print_books_inq.jsp,EDD0990"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0990" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">

<%
	if ( prBooks.getNoResult() ) {
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
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" readonly>
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
                <input type="text" name="ACCNUM" size="12" readonly maxlength="12" value="<%= userPO.getIdentifier().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="PROCCY" size="4" readonly maxlength="4" value="<%= userPO.getHeader2().trim()%>">
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
      <TH ALIGN=CENTER>TR</TH>
      <TH ALIGN=CENTER>Descripción</TH>
      <TH ALIGN=CENTER>Monto<br>
        Débito</TH>
      <TH ALIGN=CENTER>Monto<br>
        Crédito</TH>
      <TH ALIGN=CENTER>Saldo</TH>
      <TH ALIGN=CENTER>Cajero</TH>
      <TH ALIGN=CENTER>Referencia</TH>
      <TH ALIGN=CENTER>Hora</TH>
    </TR>
    <%
                prBooks.initRow();
                while (prBooks.getNextRow()) {
                    // if (prBooks.getFlag().equals("")) {
                    		out.println(prBooks.getRecord());
                    // }
                }
    %> 
  </TABLE>

  <h4></h4>
	<table class="tableinfo">
    <tr > 
      <td nowrap>
        
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trclear"> 
            <td > 
              <div align="right"><b>Saldo de Libreta : </b></div>
            </td>
            <td ><%= userPO.getHeader4() %></td>
            <td > 
              <div align="right"><b>Total de D&eacute;bitos : </b></div>
            </td>
            <td ><%= userPO.getHeader5() %></td>
            <td > 
              <div align="right"><b>Total de C&eacute;ditos : </b></div>
            </td>
            <td ><%= userPO.getHeader6() %></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

<%
  }
%>

</FORM>

</BODY>
</HTML>

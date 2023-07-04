<html>
<head>
<title>Posición del Oficial Por Producto</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cifProd" class= "datapro.eibs.beans.JBList" scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />



<jsp:useBean id="cifTotal" class="datapro.eibs.beans.ECIF17004Message" scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	builtNewMenu(ecif170_i_opt);

</SCRIPT>


</head>

<body>

<SCRIPT> initMenu(); </SCRIPT>


<h3 align="center">Posición del Oficial Por Producto<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cust_products.jsp,ECIF170"></h3>
<hr size="4">
  <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF170" >
    <INPUT TYPE=HIDDEN NAME="SCREEN" value="10">
	<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

<%
	if ( cifProd.getNoResult() ) {
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
  
<p>
<table class="tableinfo">
  <tr bgcolor="#FFFFFF" bordercolor="#FFFFFF"> 
      <td nowrap > 
        <table class=tbhead cellspacing="0" cellpadding="2" width="100%"   align="center">
        <tr>
             
             <td nowrap width="10%" align="right"> Oficial: </td>
             <td nowrap width="12%" align="left">
         			<%= cifTotal.getE04OFICDE()%>
             </td>
             <td nowrap width="6%" align="right">Nombre:  </td>
             <td nowrap width="14%" align="left">
         			<%= cifTotal.getE04OFINME()%>
             </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

  
  
  <h4>Activos (En Moneda Base)</h4>
  <TABLE class="tableinfo" ALIGN=CENTER>
  <TR><TD>
   <TABLE id="headTable1">
    <TR id="trdark"> 
      <TH ALIGN=CENTER nowrap>Descripción</TH>
      <TH ALIGN=CENTER nowrap>Producto</TH>
      <TH ALIGN=CENTER nowrap>Banco</TH>
      <TH ALIGN=CENTER nowrap>MDA</TH>
      <TH ALIGN=CENTER nowrap>N/T</TH>
      <TH ALIGN=CENTER nowrap>Principal</TH>
      <TH ALIGN=CENTER nowrap>Interes</TH>
      <TH ALIGN=CENTER nowrap>Otros</TH>
      <TH ALIGN=CENTER nowrap>Total</TH>
    </TR>	
  </TABLE>
  
  <div id="dataDiv1" class="scbarcolor" style="padding:0" nowrap>
      <table id="dataTable1" >
    <%
                cifProd.initRow();
                int i=0;
                while (cifProd.getNextRow()) {
                    if (cifProd.getFlag().equals("1")) {
                    		out.println(cifProd.getRecord());              		
   							i++;
                    }
                }
                if ( i > 6 ) {
      %>
                    		 <SCRIPT Language="Javascript">
   								 dataDiv1.style.height="120"; 
   								 dataDiv1.style.overflowY="scroll";
   							</SCRIPT>	 
  			<%	 
                          }
                out.println(userPO.getHeader6());
    %> 
    </table>
  </div>
  </TD>  
</TR>	
</TABLE>

  <h4>Pasivos (En Moneda Base)</h4>
  <TABLE class="tableinfo" ALIGN=CENTER>
  <TR><TD>
  
  <TABLE id="headTable2" >
    <TR id="trdark"> 
      <TH ALIGN=CENTER nowrap>Descripción</TH>
      <TH ALIGN=CENTER nowrap>Producto</TH>
      <TH ALIGN=CENTER nowrap>Banco</TH>
      <TH ALIGN=CENTER nowrap>MDA</TH>
      <TH ALIGN=CENTER nowrap>N/T</TH>
      <TH ALIGN=CENTER nowrap>Principal</TH>
      <TH ALIGN=CENTER nowrap>Interes</TH>
      <TH ALIGN=CENTER nowrap>Otros</TH>
      <TH ALIGN=CENTER nowrap>Total</TH>
    </TR>
    </TABLE>
    
      <div id="dataDiv2" class="scbarcolor" style="padding:0" nowrap>
      <table id="dataTable2" >
    <%
                cifProd.initRow();
                int j=0;
                while (cifProd.getNextRow()) {
                    if (cifProd.getFlag().equals("2")) {
                    		out.println(cifProd.getRecord());
                    		j++;
                    }
                }
                 if ( j > 6 ) {
      %>
                    		 <SCRIPT Language="Javascript">
   								 dataDiv2.style.height="120"; 
   								 dataDiv2.style.overflowY="scroll";
   							</SCRIPT>	 
  			<%	 
                          }          
                out.println(userPO.getHeader7());
    %> 
    </table>
     </div>
    </TD>
    </TR>	
  </TABLE>

  <SCRIPT language="javascript">
   function tableresize() {
     adjustEquTables(headTable1,dataTable1,dataDiv1,0,false);
     adjustEquTables(headTable2,dataTable2,dataDiv2,0,false);
     }
  tableresize();
  window.onresize=tableresize;   
  </SCRIPT>
  
  <h4>Totales (En Moneda Base)</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap>
        
      <table cellspacing=0 cellpadding=2 width="100%" border="0">
        <tr id="trclear"> 
          <td width="16%"> 
            <div align="right"><b>Activos : </b></div>
          </td>
          <td width="16%"><%= userPO.getHeader3() %></td>
          <td width="16%"> 
            <div align="right"><b>Pasivos : </b></div>
          </td>
          <td width="16%"><%= userPO.getHeader4() %></td>
          <td width="20%"> 
            <div align="right"><b>Posici&oacute;n Neta : </b></div>
          </td>
          <td width="16%"><%= userPO.getHeader5() %></td>
        </tr>
      </table>
      </td>
    </tr>
  </table>

<%
  }
%>

  </form>
</body>
</html>

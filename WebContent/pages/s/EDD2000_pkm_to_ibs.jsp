<html>
<head>
<title>Transacciones Camara Entrante</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cifPos" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

  function goAction(op) {

  
          document.forms[0].submit();
    
  }
  


	 

</SCRIPT>


</head>

<BODY>

<SCRIPT>  </SCRIPT>

<p>

<h3 align="center">Transacciones Camara Entrante<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDD_pkm_to_ibs.jsp,JSEDD2000"></h3>
<hr size="4">
  <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDD2000" >
    <INPUT TYPE=HIDDEN NAME="SCREEN" value="10">
	<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
	<INPUT TYPE=HIDDEN NAME="fecha" VALUE="<%= request.getParameter("fecha")%>">

	
<%
	if ( cifPos.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center">
        		<font size="3"><b>
        				No Existen Transacciones en la Camara Entrante Para La Fecha Seleccionada
        		</b></font>
        	</div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
		
%>
<table id="TBHELP" align="center" width="100%" height="10%"  border="0">
    <tr> 
      <td nowrap ALIGN="right">
      		Numero de Registros:  </td>
      <td nowrap ALIGN="left"> 
        <INPUT type="text" name="NREG" size="10" maxlength="10" readonly value=<%=userPO.getHeader23() %>>
        </td>	
      <td nowrap ALIGN="right">
      		Monto Total:  </td>
      <td nowrap ALIGN="left"> 
        <INPUT type="text" name="MONTO" size="20" maxlength="20" readonly value=<%=userPO.getHeader22() %>>
        </td>
    </tr> 
    
  </table>  
 
  <div align="center"> 
  		 
	   <input id="EIBSBTN" align=right type=button name="Submit" value="Enviar" onClick="javascript:goAction(1)">
  </div>       
<p>

<h4></h4>
 <TABLE class="tableinfo" ALIGN=CENTER>
 <TABLE class="tableinfo">
  <TR>
  <TD width=100%>
   <TABLE id="headTable2" width=100%>
     <TR id ="trdark">      
      <TH ALIGN=center  nowrap >ID </TH>
      <TH ALIGN=center  nowrap >COD CAMARA</TH>
      <TH ALIGN=center  nowrap >BATCH ID </TH>
      <TH ALIGN=center  nowrap >NUM LOTE </TH>
      <TH ALIGN=center  nowrap >NUM   CHEQUE</TH>
      <TH ALIGN=center  nowrap >NUM CTA ORIGEN </TH>
      <TH ALIGN=center  nowrap >NUM CTA DESTINO </TH>   
      <TH ALIGN=center  nowrap >RUTA ORIGEN</TH>
      <TH ALIGN=center  nowrap >RUTA DESTINO</TH>
      <TH ALIGN=center  nowrap >MONTO </TH>
      <TH ALIGN=center  nowrap >ESTATUS PAGO</TH>
      <TH ALIGN=center  nowrap >ESTATUS PROCESO </TH>
      <TH ALIGN=center  nowrap >TRANSACCION</TH>
      <TH ALIGN=center  nowrap >BANCO  </TH>
      <TH ALIGN=center  nowrap >OFICINA </TH>    
      <TH ALIGN=center  nowrap >FECHA PROCESO</TH>
      <TH ALIGN=center  nowrap>FECHA CAMARA </TH>
      <TH ALIGN=center  nowrap>FECHA DEVOLUCION </TH>
      <TH ALIGN=center  nowrap >COND CHEQUE </TH>
      <TH ALIGN=center  nowrap >TIP DOCUMENTO</TH>
      <TH ALIGN=center  nowrap >NUM CHEQUES </TH>
    <!--    <TH ALIGN=center  nowrap >LINK IMG</TH>-->
      <TH ALIGN=center  nowrap >TIPO CAMARA</TH>    
      <TH ALIGN=center  nowrap >SUBTRANSACCION</TH>
      <TH ALIGN=center  nowrap >GIRADOR</TH>
      <TH ALIGN=center  nowrap >ESTATUS ESCANEO </TH>
      <TH ALIGN=center  nowrap >OBSERVACION </TH>
      <TH ALIGN=center  nowrap >DIGITO VERIFICADOR </TH>
      <TH ALIGN=center  nowrap >DOCUMENT ID </TH>
      <TH ALIGN=center  nowrap >FECHA CHEQUE </TH>
      <TH ALIGN=center  nowrap >BENEFICIARIO </TH>   
      <TH ALIGN=center  nowrap >EST TRUNC</TH>
      <TH ALIGN=center  nowrap >BATCH REFERENCE </TH>
      <TH ALIGN=center  nowrap >USERID</TH>
      <TH ALIGN=center  nowrap >TIP TRANS CHEQUE </TH>
      <TH ALIGN=center  nowrap >DOC ORIGEN</TH>
      <TH ALIGN=center  nowrap >FISA</TH>
      
    </TR>
   </TABLE>   
    <div id="dataDiv2" class="scbarcolor" style="padding:0">
      <table id="dataTable2" width=100%>
      
    <%
                cifPos.initRow();
                int i=0;
                while (cifPos.getNextRow()) {
                    
                    		out.println(cifPos.getRecord());              		
   							i++;
                    
                }
                if ( i > 20 ) {
      %>
                    		 <SCRIPT Language="Javascript">
   								 dataDiv2.style.height="520"; 
   								 dataDiv2.style.overflowY="scroll";
   							</SCRIPT>	 
  			<%	 
                          }
             
    %> 

    </table>
  </div>
  </TD>  
</TR>	
</TABLE>

  


  
	

 


<SCRIPT language="javascript">

  tableResize();
  window.onresize=tableResize; 
</SCRIPT>
<%
  }
%>

  </form>
</body>
</html>
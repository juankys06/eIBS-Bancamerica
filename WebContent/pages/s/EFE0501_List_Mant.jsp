<html>
<head>
<title>Mantenimiento Rango de Tasas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "cifPos" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

  function goAction(op) {

     document.forms[0].opt.value = op;
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "REF") 
      	{
        		ok = true;
        		break;
      	}
      }
	  if ( ok ) {
		  if(op==1){
			  document.forms[0].submit(); 
		  }		  
		  if(op==2){
			  document.forms[0].SCREEN.value = "12";
			  document.forms[0].OPECOD.value = "0005";
			  document.forms[0].submit(); 
		  }	
          	
     }
     else {
			alert("Seleccione una cuenta antes de realizar esta operación");	   
     }

  }
  


	 

</SCRIPT>


</head>

<BODY>

<SCRIPT>  </SCRIPT>

<p>

<h3 align="center">Mantenimiento Parametrizacion Rango de Tasas de Cambio en Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EFE0501_List_Mant.jsp,JSEFE0501"></h3>
<hr size="4">
  <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEFE0501" >
    <INPUT TYPE=HIDDEN NAME="SCREEN" value="22">
	<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
	<INPUT TYPE=HIDDEN NAME="OPECOD" VALUE="">
	
<%
	if ( cifPos.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center">
        		<font size="3"><b>
        				No Existen Parametrizaciones Caragadas Para Ninguna Moneda Extranjera
        		</b></font>
        	</div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>

 
        
<p>

<h4>Rango de Tasas para Compra y Venta de Divisas En Moneda Extranjera</h4>
 <TABLE class="tableinfo" ALIGN=CENTER>
 <TABLE class="tableinfo">
  <TR>
  <TD width=100%>
   <TABLE id="headTable2" width=100%>
     <TR id ="trdark">
      <TH ALIGN=CENTER WIDTH=2%  nowrap></TH>
      <TH ALIGN=CENTER  WIDTH=20% nowrap>Moneda </TH>
      <TH ALIGN=CENTER WIDTH=10%  nowrap>Transaccion </TH>
      <TH ALIGN=LEFT WIDTH=5%  nowrap>Numero de Sucursal</TH>
      <TH ALIGN=CENTER WIDTH=33%  nowrap>Nombre de Sucursal</TH>
      <TH ALIGN=RIGHT WIDTH=10%  nowrap>Tasa de Cambio</TH>
      <TH ALIGN=RIGHT WIDTH=10%  nowrap>Rango en Alta </TH>
      <TH ALIGN=RIGHT WIDTH=10%  nowrap>Rango en Baja</TH>
       
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
   								 dataDiv2.style.height="500"; 
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

  
<div align="center"> 
	   <input id="EIBSBTN" type=button name="Submit" value="Modificar" onClick="javascript:goAction(1)">
	   <input id="EIBSBTN" type=button name="Submit" value="Eliminar" onClick="javascript:goAction(2)">
  </div> 

  
	

 


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
<html>
<head>
<title>Tarjetas De Credito Por Cliente</title>
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
      	if(elementName == "ACCNUM") 
      	{
        		ok = true;
        		break;
      	}
      }
	  if ( ok ) {
          document.forms[0].submit();
     }
     else {
			alert("Seleccione una cuenta antes de realizar esta operación");	   
     }

  }
  


	 builtNewMenu(ecif10_i_opt);

</SCRIPT>


</head>

<BODY>

<SCRIPT> initMenu(); </SCRIPT>

<p>

<h3 align="center">Tarjetas De Credito Por Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cif_consultaTDClist.jsp,ECIF010"></h3>
<hr size="4">
  <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010" >
    <INPUT TYPE=HIDDEN NAME="SCREEN" value="10">
	 <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
<%
	if ( cifPos.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center">
        		<font size="3"><b>
        				El Cliente no Posee Tarjetas de Credito
        		</b></font>
        	</div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>

 
        
<p>

<h4>Tarjetas de Credito</h4>
 <TABLE class="tableinfo" ALIGN=CENTER>
  <TR><TD>
   <TABLE id="headTable1">
     <TR id ="trdark">
      <TH ALIGN=CENTER  nowrap>Numero</TH>
      <TH ALIGN=CENTER  nowrap>Nombre</TH>
      <TH ALIGN=CENTER  nowrap>Tipo de Identificacion</TH>
      <TH ALIGN=CENTER  nowrap>Numero de Identificacion</TH>
      <TH ALIGN=CENTER  nowrap>Tipo de Tarjeta</TH>    
    </TR>
 
  </TABLE>  
   
      <table id="dataTable1">
    <%
                cifPos.initRow();
                int i=0;
                while (cifPos.getNextRow()) {
                    
                    		out.println(cifPos.getRecord());              		
   							i++;
                    
                }
                if ( i > 6 ) {
      %>
                    		 <SCRIPT Language="Javascript">
   								 dataDiv1.style.height="120"; 
   								 dataDiv1.style.overflowY="scroll";
   							</SCRIPT>	 
  			<%	 
                          }
             
    %> 
    </table>

  </TD>  
</TR>	
</TABLE>
<div align="center"> 
	   <input id="EIBSBTN" type=button name="Submit" value="Consultar" onClick="javascript:goAction(1)">
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
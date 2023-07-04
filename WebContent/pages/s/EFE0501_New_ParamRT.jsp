<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Nueva Parametrizacion Rango de Tasas en Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="gaMant" class= "datapro.eibs.beans.EFE0501DSMessage"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />



<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>

<body>
<% 

 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 

<h3 align="center">Nueva Parametrizacion Rango de Tasas en Moneda Extranjera
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EFE5001_SendNewRt.jsp,JSEFE0501"></h3> 




<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEFE0501" >


<INPUT TYPE=HIDDEN NAME="SCREEN" value="12">
<INPUT TYPE=HIDDEN NAME="OPECOD" value="0002">
<INPUT TYPE=HIDDEN NAME="E01FEICOV" value="<%=gaMant.getE01FEICOV().trim() %>">




   <h4></h4>   
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
	        <%    if("PU".equals(gaMant.getE01FEICOV().trim())){%>
	            <td nowrap> 
	              <div align="right">Operacion : </div>
	            </td>
	            <td nowrap ><INPUT type="text" name="E01FEICOD" readonly size="8" maxlength="9" value="Compra">            
	            </td>
	            <%  }if("SL".equals(gaMant.getE01FEICOV().trim())){%>
	            <td nowrap> 
	              <div align="right">Operacion : </div>
	            </td>
	            <td nowrap ><INPUT type="text" name="E01FEICOD" readonly size="8" maxlength="9" value="Venta">            
	            </td>
				 <% }       %>     
            <td nowrap> 
              <div align="right">Moneda : </div>
            </td>
            <td nowrap ><INPUT type="text" name="E01FEICCY" readonly size="3" maxlength="3" value="<%=gaMant.getE01FEICCY().trim() %>" >      
          	  <INPUT type="text" name="E01FEICCD" readonly size="15" maxlength="15" value="<%=gaMant.getE01FEICCD().trim() %>" >      
            </td>
             <td nowrap>  
             <div align="right">Tasa : </div>             
            </td>
            <td nowrap >    <INPUT type="text" name="E01FEIRAB" readonly size="10" maxlength="10" value="<%=  gaMant.getE01FEIRAB()%>">       
            </td>
          </tr>
            <tr id="trclear"> 
	            <td nowrap> 
	              <div align="right">Rango    </div>
	            </td>	   
	            <td nowrap> 	             
	            </td>	         
	             <td nowrap> 
	              <div align="right">Sucursales:     </div>
	            </td>	           
          </tr>
            <tr id="trdark"> 
	            <td nowrap> 
	              <div align="right">Alto : </div>
	            </td>
	            <td nowrap ><INPUT type="text" name="E01FEIALT" onkeypress="enterDecimal()" size="8" maxlength="9" value="">    
	            			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  >          
	            </td> <td nowrap> 
	              <div align="right">Desde : </div>
	            </td>
	            <td nowrap ><INPUT type="text" name="E01FEIBR1" onkeypress="enterInteger()" size="3" maxlength="3" value="">   
	            			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  >           
	            </td>	        
	            <td nowrap >            
	            </td>   
	             <td nowrap >            
	            </td> 
          </tr>
            <tr id="trclear"> 
	            <td nowrap> 
	              <div align="right">Bajo : </div>
	            </td>
	            <td nowrap ><INPUT type="text" name="E01FEIBAJ" onkeypress="enterDecimal()" size="8" maxlength="9" value="">      
	            			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  >        
	            </td> <td nowrap> 
	              <div align="right">Hasta : </div>
	            </td>
	            <td nowrap ><INPUT type="text" name="E01FEIBR2" onkeypress="enterInteger()" size="3" maxlength="3" value="">            
	            </td>	        
	            <td nowrap >            
	            </td>   
          </tr>
         
        </table>
      </td>
    </tr>
  </table>
 



 <p align=center>
  	<input class="EIBSBTN" id="EIBSBTN" type="button" onclick="javascript:enter(1)" value="Enviar">
 
  </p>

 
	

<SCRIPT Language="Javascript">

function enter(op){
	var ok=true;
	if(document.forms[0].E01FEIALT.value == ""){
		alert("Debe Ingresar un Rango Alto");
		ok=false;
		
	}
	if(document.forms[0].E01FEIBAJ.value == ""){
		alert("Debe Ingresar un Rango Bajo ");
		ok=false;
	}
	if(document.forms[0].E01FEIBR1.value == ""){
		alert("Debe Ingresar al Menos Una Sucursal");
		ok=false;
		
	}
	if(document.forms[0].E01FEIBR1.value > document.forms[0].E01FEIBR2.value){
		alert("La Sucursal 'Hasta' No Puede Ser Menor ");
		ok=false;
	}
		
	if(ok){
		document.forms[0].submit();
	}
}




  </SCRIPT> 
  </FORM>
 </BODY>
 </html>
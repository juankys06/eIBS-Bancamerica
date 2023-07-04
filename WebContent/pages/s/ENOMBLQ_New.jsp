<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Nueva Retencion de Cuotas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="BlqMant" class= "datapro.eibs.beans.ENOMBLQ01Message"  scope="session"/>
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
<%if(BlqMant.getH01FLGWK3().equals("M")) {%>
<h3 align="center">Modificar Retencion de Cuotas
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ENOMBLQ_New.jsp,JENOMBLQ"></h3> 

<%}else{if(BlqMant.getH01FLGWK3().equals("E")) {%>
<h3 align="center">Eliminar Retencion de Cuotas
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ENOMBLQ_New.jsp,JENOMBLQ"></h3> 

<%}else {%>
<h3 align="center">Nueva Retencion de Cuotas
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ENOMBLQ_New.jsp,JENOMBLQ"></h3>

<%}}%>



<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSENOMBLQ" >


<INPUT TYPE=HIDDEN NAME="SCREEN" value="22">
<%if(BlqMant.getH01FLGWK3().equals("M")) {%>

<INPUT TYPE=HIDDEN NAME="OPECOD" value="0002">
<%}else{if(BlqMant.getH01FLGWK3().equals("E")) {%>

<INPUT TYPE=HIDDEN NAME="OPECOD" value="0003">
<%}else {%>

<INPUT TYPE=HIDDEN NAME="OPECOD" value="0001">
<%}}%>



   <h4></h4>   
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
	      
            <td nowrap> 
              <div align="right">Cuenta : </div>
            </td>
            <td nowrap >      
          	  <INPUT type="text" name="E01NOMACC"  size="15" maxlength="13" onkeypress="enterInteger()" value="<%=BlqMant.getE01NOMACC().trim() %>" >    
          	  <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  >    
            </td>
             <td nowrap>  
             <div align="right">Credito : </div>             
            </td>
            <%if(BlqMant.getH01FLGWK3().equals("M")) {%>
            <td nowrap >    <INPUT type="text" name="E01NOMCRD" readonly size="15" maxlength="12" value="<%=  BlqMant.getE01NOMCRD()%>">       
            
            </td>
            <%}else{%>
            <td nowrap >    <INPUT type="text" name="E01NOMCRD" onkeypress="enterInteger()" size="15" maxlength="12" value="<%=  BlqMant.getE01NOMCRD()%>">       
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  >  
            </td>
            <%}%>
          </tr>
          
            <tr id="trclear"> 
	            <td nowrap> 
	              <div align="right">Moneda : </div>
	            </td>
	            <td nowrap ><INPUT type="text" name="E01NOMCCY" size="4" maxlength="3" value="<%=  BlqMant.getE01NOMCCY()%>">    
	            <a href="javascript:GetCurrency('E01NOMCCY','')">
        		<img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" onclick="document.forms[0].search[0].click()"></a> 
	            			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  >          
	            </td> <td nowrap> 
	              <div align="right">Monto : </div>
	            </td>
	            <td nowrap ><INPUT type="text" name="E01NOMAMT" onkeypress="enterDecimal()" size="17" maxlength="17" value="<%=  BlqMant.getE01NOMAMT()%>">   
	            			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  >           
	            </td>	      
	         
          </tr>
           <tr id="trdark"> 
	           <td nowrap> 
	              <div align="right">Fecha : </div>
	            </td>
	            <td nowrap >
				<INPUT type="text" name="E01NOMMDD" size="2" maxlength="2" value="<%=  BlqMant.getE01NOMMDD()%>" readonly> 
				 <INPUT type="text" name="E01NOMMDM" size="2"  maxlength="2" value="<%=  BlqMant.getE01NOMMDM()%>" readonly> 
				 <INPUT type="text" name="E01NOMMDY" size="2" maxlength="2" value="<%=  BlqMant.getE01NOMMDY()%>"readonly>
				<a href="javascript:DatePicker(document.forms[0].E01NOMMDD,document.forms[0].E01NOMMDM,document.forms[0].E01NOMMDY)">
				<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="absmiddle" border="0"></a>
	            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  >           
	            </td>	      
	         	<td nowrap >          
	            </td>	
	            <td nowrap >          
	            </td>	
          </tr>
       
         
        </table>
      </td>
    </tr>
  </table>
 



 <p align=center>
  	<input class="EIBSBTN" id="EIBSBTN" type="button" onclick="javascript:enter()" value="Enviar">
 
  </p>

 
	

<SCRIPT Language="Javascript">

function enter(){
	var ok=true;
	if(document.forms[0].E01NOMACC.value == "0"){
		alert("Debe Ingresar una Cuenta");
		ok=false;
		
		
	}
	if(document.forms[0].E01NOMCRD.value == "0"){
		alert("Debe Ingresar un Credito ");
		ok=false;
	}
	if(document.forms[0].E01NOMCCY.value == ""){
		alert("Debe Ingresar Moneda");
		ok=false;
		
	}
	if(document.forms[0].E01NOMAMT.value  == ""){
		alert("Debe Ingresar Monto ");
		ok=false;
	}
	if(document.forms[0].E01NOMMDD.value  == ""){
		alert("Debe Ingresar Fecha ");
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
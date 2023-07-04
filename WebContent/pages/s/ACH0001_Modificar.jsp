<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="java.nio.channels.GatheringByteChannel"%>
<html>
<head>
<title>Mantenimiento de Rechazos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="gaMant" class= "datapro.eibs.beans.EACH00102Message"  scope="session"/>
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
String Ref=gaMant.getACH2REF()+"_"+gaMant.getACH2RDP()+"_"+gaMant.getACH2FTR()+"_"+gaMant.getACH2SEC();
%> 

<%if(gaMant.getACH2REC().trim().equals("") && gaMant.getACH2MOD().trim().equals("")){ %>
<h3 align="center">Modificar Transaccion ACH Rechazada
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ach_modificar.jsp,JSEACH001"></h3> 

<%}else{ %>
<h3 align="center">Aprobar Transaccion ACH Rechazada
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ach_modificar.jsp,JSEACH001"></h3> 

<%} %>


<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH001" >

<%if(gaMant.getACH2REC().trim().equals("") && gaMant.getACH2MOD().trim().equals("")){ %>
<INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
<INPUT TYPE=HIDDEN NAME="OPECOD" value="0001">

<%
System.out.print("recibeee     "+gaMant);
}else{ %>
<INPUT TYPE=HIDDEN NAME="SCREEN" value="5">
<INPUT TYPE=HIDDEN NAME="OPECOD" value="0004">

<%} %>
<input type="hidden" name="SELSEC" value="<%= gaMant.getACH2SELSEC().trim()%>" >
<input type="hidden" name="REF" value="<%= Ref%>" >
    

   
                                     
   <h4></h4>   
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Referencia : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH2REF" readonly size="25" maxlength="24" value="<%= gaMant.getACH2REF().trim()%>">            
            </td>
            <td nowrap> 
              <div align="right">Fecha de Transaccion : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH2FTR" readonly size="10" maxlength="10" value="<%= gaMant.getACH2FTR().trim()%>">            
            </td>
             <td nowrap>               
            </td>
            <td nowrap >           
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Tipo de Transaccion : </div>
            </td>
            <td nowrap ><INPUT type="text" name="TIPTRN" readonly size="4" maxlength="3" value="<%= gaMant.getACH2TIPTRN().trim()%>">            
            </td>
            <td nowrap> 
              <div align="right">Numero de Cuenta : </div>
            </td>
            
            <%if(gaMant.getACH2REC().trim().equals("") && gaMant.getACH2MOD().trim().equals("")){ %>
            <td nowrap ><INPUT type="text" name="ACH2NUC" onKeypress="enterInteger()" size="36" maxlength="35" value="<%= gaMant.getACH2NUC().trim()%>">    
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  >         
            </td>
            <%}else{%>
            <td nowrap ><INPUT type="text" name="ACH2NUC" readonly size="36" maxlength="35" value="<%= gaMant.getACH2NUC().trim()%>">    
                   
            </td>
            <%} %>
            <td nowrap> 
              <div align="right">Cuenta IBS: </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH2ACC" readonly size="36" maxlength="35" value="<%= gaMant.getACH2ACC().trim()%>">            
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tipo de Cuenta : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH2TIC" readonly size="6" maxlength="5" value="<%= gaMant.getACH2TIC().trim()%>">            
            </td>
            <td nowrap> 
              <div align="right">Monto : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH2MNT" readonly size="15" maxlength="15" value="<%= gaMant.getACH2MNT().trim()%>">            
            </td>
             <td nowrap> 
              <div align="right">Moneda : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH2CCY" readonly size="4" maxlength="4" value="<%= gaMant.getACH2CCY().trim()%>">            
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Numero de Cliente: </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH2CUN" readonly size="8" maxlength="8" value="<%= gaMant.getACH2CUN().trim()%>">            
            </td>
            <td nowrap> 
              <div align="right">ID de cliente : </div>
            </td>
              <%if(gaMant.getACH2REC().trim().equals("") && gaMant.getACH2MOD().trim().equals("")){ %>
            <td nowrap ><INPUT type="text" name="ACH2IDC" onKeypress="enterInteger()" size="15" maxlength="15" value="<%= gaMant.getACH2IDC().trim()%>">     
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="bottom" border="0" width="16" height="20"  >        
            </td>
            <%}else{ %>
            <td nowrap ><INPUT type="text" name="ACH2IDC" readonly size="15" maxlength="15" value="<%= gaMant.getACH2IDC().trim()%>">     
                  
            </td>
            <%} %>
            <td nowrap> 
              <div align="right">Nombre: </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH2SHN" readonly size="50" maxlength="50" value="<%= gaMant.getACH2SHN().trim()%>">            
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Descripcion : </div>
            </td>
            <td nowrap > <INPUT type="text" name="ACH2DSC" readonly size="90" maxlength="90" value="<%= gaMant.getACH2DSC().trim()%>">           
            </td>
           <td nowrap> 
             <td nowrap >           
            </td><td nowrap >           
            </td>
            <td nowrap >           
            </td>
             <td nowrap> 
              
            </td>
            <td nowrap >           
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

<INPUT type="hidden" name="ACH2STATUS" readonly size="1" maxlength="2"  value="<%= gaMant.getACH2STATUS().trim()%>"> 
<% if(gaMant.getACH2REC().trim().equals("") && gaMant.getACH2MOD().trim().equals("")){%>
 <p align=center>
  	<input class="EIBSBTN" id="EIBSBTN" type="button" onclick="javascript:enter(1)" value="Aprobar">
  	<input class="EIBSBTN" id="EIBSBTN" type="button"  onclick="javascript:enter(2)" value="Rechazar">
  </p>
  <% }else{%>
  <p align=center>
  	<input class="EIBSBTN" id="EIBSBTN" type="button" onclick="javascript:enter(3)" value="Aprobar">  	
  	<input class="EIBSBTN" id="EIBSBTN" type="button"  onclick="javascript:enter(2)" value="Devolver">
  	
  </p>
<% }%>
 
	

<SCRIPT Language="Javascript">
function enter(op){

	if(op=="1"){
		//alert("Transaccion Modificada Exitosamente");
		document.forms[0].ACH2STATUS.value ="M";		
	}
	if(op=="2"){
		//alert("Transaccion Rechazada Exitosamente");
		document.forms[0].ACH2STATUS.value ="R";
	}
	if(op=="3"){
		//alert("Transaccion Aprobada Exitosamente");
		document.forms[0].ACH2STATUS.value ="A";
	}
	
	document.forms[0].submit();
	
}




  </SCRIPT> 
  </FORM>
 </BODY>
 </html>
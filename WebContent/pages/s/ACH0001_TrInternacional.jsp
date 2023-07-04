
<html>
<head>
<title>Transaccion Saliente ACH (Moneda Local: Pesos DOP)</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="gaMant" class= "datapro.eibs.beans.EACH00301Message"  scope="session"/>
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

<h3 align="center">Transaccion Nacional Saliente ACH (Moneda:  <%= gaMant.getACH3CURCOD()%>)
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ach_modificar.jsp,JSEACH001"></h3> 




<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.ach.JSEACH001" >


<INPUT TYPE=HIDDEN NAME="SCREEN" value="320">

<INPUT TYPE=HIDDEN NAME="ACH3TRNDAT" value="<%= gaMant.getACH3TRNDAT()%>">
<INPUT TYPE=HIDDEN NAME="ACH3TRNSEC" value="<%= gaMant.getACH3TRNSEC()%>">
<INPUT TYPE=HIDDEN NAME="ACH3TRNREF" value="<%= gaMant.getACH3TRNREF()%>">
<INPUT TYPE=HIDDEN NAME="ACH3TRNTYP" value="<%= gaMant.getACH3TRNTYP()%>">
<INPUT TYPE=HIDDEN NAME="ACH3FECTRN" value="<%= gaMant.getACH3FECTRN()%>">
<INPUT TYPE=HIDDEN NAME="ACH3CTEIDN" value="<%= gaMant.getACH3CTEIDN()%>">
<INPUT TYPE=HIDDEN NAME="ACH3CTEFRM" value="<%= gaMant.getACH3CTEFRM()%>">
<INPUT TYPE=HIDDEN NAME="ACH3BCOFRM" value="10171228">
<INPUT TYPE=HIDDEN NAME="destino" value="2">
<INPUT TYPE=HIDDEN NAME="OPECOD" value="0003">
 
     <h4> Datos del Pago</h4>   
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
             <td nowrap> 
              <div align="right">Moneda: </div>
            </td>
                       
            <td nowrap ><INPUT type="text" name="ACH3CURCOD" readonly size="4" maxlength="4" value=" <%= gaMant.getACH3CURCOD()%>">    
                   
            </td>
            <td nowrap> 
            
              
            </td>
            <td nowrap >           
            </td>
             <td nowrap>  
                       
            </td>
            <td nowrap >          
            </td>
          </tr>
          <tr id="trclear"> 
             <td nowrap> 
              <div align="right">Monto a Abonar : </div>
            </td>
            <td nowrap >     
            <INPUT type="text" name="ACH3TRNAMT"  size="13" maxlength="12" onkeypress="enterDecimal()">       
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">        
            </td>   
            <td nowrap> 
              <div align="right">Monto a Tranferir (Letras) : </div>
            </td>                       
            <td nowrap ><INPUT type="text" name="ACH3MONLET"  size="75" maxlength="75" value="<%= gaMant.getACH3MONLET().trim()%>">    
                   
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
  <h4> Datos del Ordenante</h4>   
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">       
   		  <tr id="trclear">
          <td nowrap> 
              <div align="right">Propietario de La cuenta : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3CTENAM" readonly size="50" maxlength="50" value="<%= gaMant.getACH3CTENAM().trim()%>">            
            </td>
           
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Direccion Fisica de Ordenante : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3DFISOR" readonly size="75" maxlength="75" value="<%= gaMant.getACH3DFISOR().trim()%>">            
            </td>          
          </tr>
          <tr>
          <td nowrap> 
              <div align="right">Cuenta a Debitar : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3ACCFRM" readonly size="20" maxlength="19" value="<%= gaMant.getACH3ACCFRM().trim()%>" >            
            </td>
           </tr> 
            <tr id="trdark">
             <td nowrap>  
             <div align="right">Tipo de Transaccion : </div>             
            </td>
            <td nowrap >    <INPUT type="text" name="ACH3ATYTO" readonly size="6" maxlength="6" value="<%= gaMant.getACH3ATYTO().trim()%>">       
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Tipo de cuenta : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3ATYFRM" readonly size="4" maxlength="3" value="<%= gaMant.getACH3ATYFRM().trim()%>">              
            </td>
            </tr> 
            <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Saldo Disponible: </div>
            </td>
                       
            <td nowrap ><INPUT type="text" name="ACH3TRNDIS" readonly size="20" maxlength="20" value="<%= gaMant.getACH3TRNDIS()%>">    
                   
            </td>
            </tr>
        </table>
      </td>
    </tr>
  </table>
 <h4> Datos del Beneficiario</h4>   
      <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  
           <td nowrap>  
              <div align="right">ID Beneficiario : </div>
            </td>             
            <td nowrap ><INPUT type="text" name="ACH3CTETO"  size="16" maxlength="15" >  
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20" value="<%= gaMant.getACH3CTETO().trim()%>">                             
            </td>  
           
          </tr>         
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Direccion  del Beneficiario : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3DIRBEN"  size="75" maxlength="75" value="<%= gaMant.getACH3DIRBEN().trim()%>">            
            </td>           
          </tr>
          <tr >
          	<td nowrap> 
              <div align="right">Cuenta a Abonar : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3ACCTO"  size="40" maxlength="40" onkeypress="enterInteger()"  >   
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">                 
            </td>  
            
          </tr>
        </table>
      </td>
    </tr>
  </table>
    <h4> Datos del Banco del Beneficiario</h4>   
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">       
         <tr id="trdark">
   		  <td nowrap> 
              <div align="right">Banco Beneficiario : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3BCOTO"  size="10" maxlength="10" value="<%= gaMant.getACH3BCOTO().trim()%>"> 
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20" value="<%= gaMant.getACH3CTETO().trim()%>">  
            </td>
           </tr>
           <tr> 
            <td nowrap> 
             <div align="right">Identificacion del Banco (ABA, SWIFT): </div>              
            </td>                       
            <td nowrap > <INPUT type="text" name="ACH3IDEBAN"  size="20" maxlength="20" value="<%= gaMant.getACH3IDEBAN().trim()%>">
            </td>
           
         
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Direccion  del Banco Beneficiario : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3DIBABE"  size="75" maxlength="75" value="<%= gaMant.getACH3DIBABE().trim()%>">            
            </td>      
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4> Datos del Banco Intermediario (si es necesario)</h4>   
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark">
   		  <td nowrap> 
              <div align="right">Banco Intermediario : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3BANINT"  size="20" maxlength="20" value="<%= gaMant.getACH3BANINT().trim()%>"> 
            </td>
           </tr>
           <tr> 
            <td nowrap> 
             <div align="right">Identificacion del Banco Intermediario (ABA, SWIFT): </div>              
            </td>                       
            <td nowrap > <INPUT type="text" name="ACH3IDBAIN"  size="20" maxlength="20" value="<%= gaMant.getACH3IDBAIN().trim()%>">
            </td>
           
         
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Direccion  del Banco Intermediario : </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3DIBAIN"  size="75" maxlength="75" value="<%= gaMant.getACH3DIBAIN().trim()%>">            
            </td>      
            </td>
          </tr>
            
          <tr > 
            <td nowrap> 
              <div align="right">Numero de Cuenta: </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3CTBAIN"  size="2" maxlength="20" value="<%= gaMant.getACH3CTBAIN().trim()%>">            
            </td>
          
          </tr>
        </table>
      </td>
    </tr>
  </table>
      <h4> Informacion adicional</h4>   
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Informacion Adicional: </div>
            </td>
            <td nowrap ><INPUT type="text" name="ACH3INFADC"  size="75" maxlength="75" value="<%= gaMant.getACH3INFADC().trim()%>">            
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
	if(document.forms[0].ACH3BCOTO.value == ""){
		alert("Debe Seleccionar Banco destino");
		ok=false
	}
	if(document.forms[0].ACH3BCOTO.value == "10171228"){
		alert("No Puede Seleccionar BancAmerica Como Banco Destino");
		ok=false
	}
	if(document.forms[0].ACH3ACCTO.value == ""){
		alert("Debe Introducir Una Cuenta a Abonar");
		ok=false
	}
	if(document.forms[0].ACH3CTETO.value == ""){
		alert("Debe Introducir el ID del Beneficiario");
		ok=false
	}
	if(document.forms[0].ACH3TRNAMT.value == ""){
		alert("Debe Introducir Un Monto a Abonar");
		ok=false
	}

	
	
	
	if(ok){
		document.forms[0].submit();
	}
}




  </SCRIPT> 
  </FORM>
 </BODY>
 </html>
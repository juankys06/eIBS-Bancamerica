<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Transacciones Financieras</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id="prMant" class="datapro.eibs.beans.EPR010001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser"      class= "datapro.eibs.beans.ESS0030DSMessage" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<%
String sMes = String.valueOf(currUser.getE01RDM());
String sAno = String.valueOf(currUser.getE01RDY());
String sDia = String.valueOf(currUser.getE01RDD());

if(sMes.length() < 2){
	sMes = "0" + sMes;
}

if(sDia.length()< 2){
	sDia= "0" + sDia;
}
if(sAno.length() < 2){
	sAno= "0" + sAno;
}

switch (sAno.length()) 
{
	case 1 : sAno = "000" + sAno;break;
	case 2 : 
			if (Integer.parseInt(sAno) < 52)
				sAno = "20" + sAno;
			else
				sAno = "19" + sAno;
	break;
	case 3 : sAno = "0"   + sAno;break;
}
String FechaSistema = sAno + sMes + sDia;
%>

<script language="Javascript">



function textCounter(field, maxlimit ) {
  
  if ( field.value.length >= maxlimit )
  {
    field.value = field.value.substring( 0, (maxlimit-1) );
    alert( 'Value can only be ' + maxlimit + ' characters in length.' );
    return false;
  }
  else
  {
    return true;
  }
}



function enviar1(){

	document.forms[0].btenviar.disabled = true;
	document.forms[0].submit();

}

function enviar(){
	var E01PRITCY = trim(document.forms[0].E01PRITCY.value).toUpperCase();
	var E01PRIDCY = trim(document.forms[0].E01PRIDCY.value).toUpperCase();
	var E01PRICCY = trim(document.forms[0].E01PRICCY.value).toUpperCase();
	var	E01PRIOCU = trim(document.forms[0].E01PRIOCU.value).toUpperCase();
	alert("1");
	if (E01PRIOCU == "0" || E01PRIOCU == ""){
		alert("Nro. Cliente Ordenante es Obligatorio");
		document.forms[0].E01PRIOCU.focus();
		return false;
	}
	alert("2");
	if (document.forms[0].E01PRIDAC.value != "0" && document.forms[0].E01PRIDAC.value != "" ){
		if (document.forms[0].E01PRIDRS.value == "0" || document.forms[0].E01PRIDRS.value == "" ){		
			alert("Al debitar una cuenta de detalle, debe Seleccionar un concepto");
			document.forms[0].E01PRIDPD.focus();
			return false;
		}else{
			if (document.forms[0].E01PRIDRS.value != "03" && document.forms[0].E01PRIDRS.value != "3"){
				if (document.forms[0].E01PRIDGL.value =="" || document.forms[0].E01PRIDGL.value =="0"){
					alert("Concepto seleccionado no corresponde para tipo de Cuenta de Debito Seleccionado.");
					document.forms[0].E01PRIDPD.focus();
					return false;
				}
			}
		}
	}

	alert("3");
	
	if (document.forms[0].E01PRICAC.value != "0" && document.forms[0].E01PRICAC.value != "" ){
		if (document.forms[0].E01PRICRS.value == "0" || document.forms[0].E01PRICRS.value == "" ){		
			alert("Al acreditar una cuenta de detalle, debe Seleccionar un concepto");
			document.forms[0].E01PRICPD.focus();
			return false;
		}else{
			if (document.forms[0].E01PRICRS.value != "03" && document.forms[0].E01PRICRS.value != "3"){
				if (document.forms[0].E01PRIDAC.value =="" || document.forms[0].E01PRIDAC.value =="0"){
					alert("Concepto seleccionado no corresponde para tipo de Cuenta de Crédito seleccionado.");
					document.forms[0].E01PRICPD.focus();
					return false;
				}
			}
		}
	}

	alert("4");
	if(valida_fecha_general("E01PRICDD", "E01PRICDM","E01PRICDY","Pago","0","<%=FechaSistema%>","30000000","0","2","E01PRIDRS")){
		if (E01PRITCY == E01PRICCY) {
			if (E01PRITCY == E01PRIDCY){
				document.forms[0].btenviar.disabled = true;
				document.forms[0].submit();
			}else{
				alert("Moneda de la Cuenta de Débito distinta a la moneda de la Transferencia");
				document.forms[0].E01PRIDCY.focus();
				return false;
			}
		}else{
			alert("Moneda de la Cuenta de Crédito distinta a la moneda de la Transferencia");
			document.forms[0].E01PRICCY.focus();
			return false;
		}
	}
	
		alert("5");

}


</script>
<SCRIPT Language="Javascript">
  builtHPopUp();
  
  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
  }

</SCRIPT>

<SCRIPT Language="Javascript">
  
  function showTab(index, name){  
  	for(var i=0;i<4;i++){
   		document.all["Tab"+i].className="tabnormal";
   		document.all["dataTab"+i].style.display="none";
   	}
  
    document.all["Tab"+index].className="tabhighlight";  
  	document.all["dataTab"+index].style.display="";
  	document.all[name].focus();
  }
 
  function showTabB(index,name){  
  	for(var i=0;i<4;i++){
   		document.all["TabB"+i].className="tabnormal";
   		document.all["dataTabB"+i].style.display="none";
   	}
  
    document.all["TabB"+index].className="tabhighlight";  
  	document.all["dataTabB"+index].style.display="";
  	document.all[name].focus();
  }
 
 
  function UpdateCodes(fname,code4,code5,code6){
   if (fname=="E01PRIDPD") {
      document.forms[0].E01DEBSOU.value= code4;
      document.forms[0].E01DEBDIB.value= code5;
	  document.forms[0].E01PRIHDY.value= code6;          
   } else {
      document.forms[0].E01CRESOU.value= code4;
      document.forms[0].E01CREDIB.value= code5;
	  document.forms[0].E01CREDAY.value= code6;
   }
  
  }
  
  function SetTransferType(value){
	  if (value=="OW") {
  	  	document.forms[0].TRANSFTYPE.value= "Y!";
  	  } else {
  	   	document.forms[0].TRANSFTYPE.value = "Y*";
  	  }
  }



function buildField(name, row, lnt ) {
    
    field = document.getElementById(name);
    field.value = '';
    for (x=1;x<=row;x++)
    {
      subname = name + '_' + x;
      subfield = document.getElementById(subname); 
      var v1 = subfield.value;
      if (v1.length < 35 && lnt > 35 ) {
        for(var i = v1.length; i < 35; i++) {
         v1 = v1 + ' ';
        }
      }
      field.value = field.value.concat(v1);
    }  
 }


function getIF01Forms(url) {
		var newurl = url+"&ACCOUNT="+document.forms[0].E01PRICAC.value;
		CenterNamedWindow(newurl, 'pdf', 800, 600, 2);
	}

</SCRIPT>


<%
String READ_ONLY_TAG="";
String DISABLED_TAG="";
   // Determina campos de solo lectura incoming Swift and FED
 if(prMant.getE01PRITTP().equals("IW")){
	if ( prMant.getE01PRIUSC().equals("SAI") || prMant.getE01PRIUSC().equals("FAI")) {
	     READ_ONLY_TAG="readonly";	   	   
	     DISABLED_TAG="disabled";	   	   
		}
	} 
 if (prMant.getE01PRITTP().equals("OW") && prMant.getE01PRISOR().equals("I")){
	 READ_ONLY_TAG="readonly";	
     DISABLED_TAG="disabled";	   	   
}

%>


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
  <%String lectura =" ";
  	String lectura2=" "; 
  	boolean mostrar=true;

//if (userPO.getPurpose().equals("NEW")) { 
//	lectura=" ";
//	lectura2=" ";
//	mostrar=true;
//}else{
//  if (prMant.getE01PRICBF().equals("X")){
//  	lectura="readonly style=\"background-color: #F0F0F0;\"";
//  	lectura2= "disabled style=\"background-color: #F0F0F0;\"";
//	mostrar=false;
//  }
// }
 %>

<h3 align="center">Transacciones Financieras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_maint.jsp,EPR0000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR0000">
 
    <input type=HIDDEN name="SCREEN" value="2">
    <input type=HIDDEN name="TRANSFTYPE" value="YL">
    <input type=HIDDEN name="E01REQCON" value="2">
    
    <div id="DivHead">
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
 		<% 
  			if (userPO.getPurpose().equals("NEW")) { 
		%>           
          
            <td nowrap width="18%"> 
              <input type="checkbox" name="H01FLGWK3" <% if(prMant.getH01FLGWK3().equals("1"))  out.print("checked");%>
			  onClick="document.forms[0].H01FLGWK3.value='1'">
              Tomar informaci&oacute;n de : 
              <input type="text" name="E01CPYNUM" size="10" maxlength="9" onKeyPress="enterInteger()"
			  value="<%= prMant.getE01CPYNUM().trim()%>">
              <a href="javascript:GetCloneTransfer('E01CPYNUM')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"  border="0" ></a> 
            </td>
           <td nowrap > 
                  <div align="right">Tipo de Transferencia :</div>
           </td>
           <td nowrap align="left">
                <% 
                if (!(prMant.getE01PRITTP().equals("IT")) && !(prMant.getE01PRITTP().equals("IW")) && !(prMant.getE01PRITTP().equals("OW")))
                {   prMant.setE01PRITTP("OW"); } 
                %>
               
                <select name="E01PRITTP" <%=lectura2%> onchange="javascript:SetTransferType(this.value)">
                  <option value=" "> </option>
                  <option value="IT" <% if(prMant.getE01PRITTP().equals("IT")){ out.print("selected");} %> >Transferencia Interna</option>
                  <option value="IW" <% if(prMant.getE01PRITTP().equals("IW")){ out.print("selected");} %> >Transferencia Externa Recibida </option>
                  <option value="OW" <% if(prMant.getE01PRITTP().equals("OW")){ out.print("selected");} %> >Transferencia Externa Enviada </option>
                </select>
            </td> 
         </tr>   
        <%}      
        else 
        {%> 
          <tr id="trclear">       
            <td nowrap > 
                  <div align="right">Tipo de Transferencia :</div>
            </td>
            <td nowrap align="left">
            <% 
             if (!(prMant.getE01PRITTP().equals("IT")) && !(prMant.getE01PRITTP().equals("IW")) && !(prMant.getE01PRITTP().equals("OW")))
              {   prMant.setE01PRITTP("OW"); } 
            %> 
            <input type="text" name="E01DESTTP" readonly value="<% if(prMant.getE01PRITTP().equals("IT")) out.print("Transferencia Interna");
              				else if(prMant.getE01PRITTP().equals("IW")) out.print("Transferencia Externa Recibida");
              				else if(prMant.getE01PRITTP().equals("OW")) out.print("Transferencia Externa Enviada");
							else out.print(" ");%>" size="35">
            
            </td>
          </tr>
          <tr>
           	<td nowrap > 
                 <div align="right"></div>
           	</td>
            <td nowrap>
            </td>
          </tr>
          <%}
          %> 
        </table>
      </td>
    </tr>
   </table>        
 </div>
 
  <% 
  if (userPO.getPurpose().equals("NEW")) { 
%> <% } else{ %> 
    <SCRIPT Language="Javascript">
    builtNewMenu(pr_m_opt);
	initMenu();
  </SCRIPT>
    <% } %> 

<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <TD nowrap> 
                  <DIV align="left">Código de Transacción:</DIV>
            </TD>
            <TD nowrap align="left">
                  <INPUT <%=lectura%> type="text" name="E01PRITCD" size="11" maxlength="10" value="<%= prMant.getE01PRITCD().trim()%>">
              <%if (mostrar){%>
	              <A href="javascript:GetCodeTransaction('E01PRITCD','','')">
	              <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"  border="0"></A>                   
            	<%}%>
            </TD>
             <td nowrap align="left">Canal: 
             </td>
              <td nowrap align="left">
                  <input <%=lectura%> type="text" name="E01PRICNL" size="2" maxlength="1" value="<%= prMant.getE01PRICNL().trim()%>" >
                  <a href="javascript:GetCodeTChannel('E01PRICNL','TMPPRACNL','')">
                  <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"  border="0" ></a>                
                  <input	type="text" name="TMPPRACNL" size="27" maxlength="26" readonly>
                </td>

                
                <td nowrap align="left">N&uacute;mero Referencia :
                </td>
                <td nowrap align="left">
                  <input <%=lectura%> type="text" name="E01PRINUM" size="10" maxlength="9" value="<%= prMant.getE01PRINUM().trim()%>" readonly>
                </td>
          </tr>
          
        </table>
      </td>
    </tr>
   </table>
      <BR>
  <div id="OtherOpt"> 
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap VALIGN="TOP"> 
                <div align="right">Cliente Ordenante :</div>
            </td>
            <td nowrap VALIGN="TOP"> 
              <div align="left"> 


                              	<SELECT name="E01PRIORO"  
					  				<OPTION value=" " <% if (!(prMant.getE01PRIORO().equals("A") ||prMant.getE01PRIORO().equals("F") || prMant.getE01PRIORO().equals("K"))) out.print("selected"); %>></OPTION>
									<OPTION value="A" <% if(prMant.getE01PRIORO().equals("A")) out.print("selected");%>>A</OPTION>
									<OPTION value="F" <% if(prMant.getE01PRIORO().equals("F")) out.print("selected");%>>F</OPTION>
									<OPTION value="K" <% if(prMant.getE01PRIORO().equals("K")) out.print("selected");%>>K</OPTION>
								</SELECT>
     

                <input <%=lectura%> type="text" name="E01PRIOCU" size="10" maxlength="10" value="<%= prMant.getE01PRIOCU().trim()%>">
              <%if (mostrar){%>
                <a href="javascript:GetCustomerDescId('E01PRIOCU','','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"  border="0"></a>
              <%}%>
              </div>
                
            </td>
            <td nowrap VALIGN="TOP"> 
                <div align="right">Nombre :<BR>
                  Direcci&oacute;n :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input <%=lectura%> type="text" name="E11PRIORC" size="36" maxlength="35" value="<%= prMant.getE11PRIORC().trim()%>"><br>
                <input <%=lectura%> type="text" name="E21PRIORC" size="36" maxlength="35" value="<%= prMant.getE21PRIORC().trim()%>"><br>
                <input <%=lectura%> type="text" name="E31PRIORC" size="36" maxlength="35" value="<%= prMant.getE31PRIORC().trim()%>"><br>
                <input <%=lectura%> type="text" name="E41PRIORC" size="36" maxlength="35" value="<%= prMant.getE41PRIORC().trim()%>"><br>
                <input <%=lectura%> type="text" name="E51PRIORC" size="36" maxlength="35" value="<%= prMant.getE51PRIORC().trim()%>">
                </div>
            </td>
          </tr>
         </table>
      </td>
    </tr>
  </table>
<BR>
<table class="tableinfo">
    
    <tr > 
      <td nowrap> 
          <table cellspacing=0 cellpadding=2 width="100%" border="0">
            <tr id="trdark"> 
              
              <td nowrap > 
                <div align="left">Moneda/Monto a Transferir:</div>
              </td>
     
              <td nowrap >
               <% 
               if ((prMant.getE01PRITTP().equals("IW")) && (prMant.getE01PRIUSC().equals("SAI")))
                {%> 
                   <input <%=lectura%> readonly type="text" name="E01PRITCY" size="4" maxlength="3" value="<%= prMant.getE01PRITCY().trim()%>" >   
                   <input  type="text" name="E01PRIAMT" size="15" readonly maxlength="13" value="<%= prMant.getE01PRIAMT().trim()%>"  <% if (mostrar) out.print("onKeyPress=\"enterDecimal()\""); %><%=lectura%> >                            
               <%} 
               else
               {%> 
                   <input <%=lectura%> type="text" name="E01PRITCY" size="4" maxlength="3" value="<%= prMant.getE01PRITCY().trim()%>" >                  
                   <%if (mostrar){%>
                     <a href="javascript:GetCurrency('E01PRITCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"  border="0"></a>
	   		       <%}%>
                   <input  type="text" name="E01PRIAMT" size="15" maxlength="13" value="<%= prMant.getE01PRIAMT().trim()%>"  <% if (mostrar) out.print("onKeyPress=\"enterDecimal()\""); %><%=lectura%> >
               <%}
              %> 
             </td>
             
              <td nowrap > 
                <div align="left">Tasa de Cambio:</div>
              </td>
              <td nowrap >
              <% 
               if ((prMant.getE01PRITTP().equals("IW")) && (prMant.getE01PRIUSC().equals("SAI")))
                {%>               
                <input <%=lectura%> type="text" readonly name="E01PRICXR" size="13" maxlength="13" value="<%= prMant.getE01PRICXR().trim()%>">
                <%} 
               else
               {%> 
                <input <%=lectura%> type="text"  name="E01PRICXR" size="13" maxlength="13" value="<%= prMant.getE01PRICXR().trim()%>">
               <%}
               %> 
              </td>               
              <td nowrap >
                <div align="left">Fecha de Transferencia:</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input <%=lectura%> type="text" name="E01PRICDD" size="3" maxlength="2" value="<%= prMant.getE01PRITDD().trim()%>" >
                  <input <%=lectura%> type="text" name="E01PRICDM" size="3" maxlength="2" value="<%= prMant.getE01PRITDM().trim()%>" >
                  <input <%=lectura%> type="text" name="E01PRICDY" size="3" maxlength="2" value="<%= prMant.getE01PRITDY().trim()%>" >
                </div>
              </td>
            </tr>
          </table>
      </td>
    </tr>
  
</table>
    <h4>Cuenta de D&eacute;bito</h4>
    
    <TABLE class="tableinfo">
      <tr id="trdark"> 
        <td nowrap align="center" >Concepto</td>
        <td nowrap align="center" >Banco</td>
        <td nowrap align="center" >Agencia</td>
        <td nowrap align="center" >Moneda</td>
        <td nowrap align="center" >Cuenta Contable</td>
        <td nowrap align="center" >Cuenta</td>
        <td nowrap align="center" >Centro de Costo</td>
        <td nowrap align="center" >Monto</td>
      </tr>
      <tr id="trclear"> 
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRIDRS" size="2" maxlength="2"  value="<%= prMant.getE01PRIDRS().trim()%>" onkeypress="enterInteger()" readonly>
            <input <%=lectura%> type="text" name="E01PRIDPD" size="17" maxlength="16"  value="<%= prMant.getE01PRIDPD().trim()%>" readonly
			 oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01PRIDBK.value,'','E01PRIDGL','E01PRIDRS','93'); return false;">
            <input <%=lectura%> type="hidden" name="E01DEBSOU"  value="<%= prMant.getE01DEBSOU().trim()%>">
            <input <%=lectura%> type="hidden" name="E01DEBDIB"  value="<%= prMant.getE01DEBDIB().trim()%>">
            <!--<input type="hidden" name="E01PRIHDY"  value="<%= prMant.getE01PRIHDY().trim()%>">--> 
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRIDBK" size="2" maxlength="2" value="<%= prMant.getE01PRIDBK().trim()%>" >
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRIDBR" size="4" maxlength="3"  value="<%= prMant.getE01PRIDBR().trim()%>"
           oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01PRIDBK.value,'','','','')" onkeypress="enterInteger()">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRIDCY" size="3" maxlength="3" value="<%= prMant.getE01PRIDCY().trim()%>" 
			oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01PRIDBK.value,'','','','')">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRIDGL" size="17" maxlength="16" value="<%= prMant.getE01PRIDGL().trim()%>" onkeypress="enterInteger()"
			oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01PRIDBK.value,document.forms[0].E01PRIDCY.value,'','','')">
          </div>
        </td>
        <td nowrap  > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRIDAC" size="17" maxlength="16" value="<%= prMant.getE01PRIDAC().trim()%>"
			oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E01PRIDBK.value,'',document.forms[0].E01PRIOCU.value,'','RT'); return false;" onKeyPress="enterInteger()">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRIDCC" size="9" maxlength="8"  value="<%= prMant.getE01PRIDCC().trim()%>" 
			oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01PRIDBK.value,'','','','')">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRIDAM" size="15" maxlength="13" value="<%= prMant.getE01PRIDAM().trim()%>">
          </div>
        </td>
      </tr>

    </table>

    <h4>Cuenta de Cr&eacute;dito</h4>
    <table class="tableinfo" >
      <tr id="trdark"> 
        <td nowrap align="center" >Concepto</td>
        <td nowrap align="center" >Banco </td>
        <td nowrap align="center" >Agencia</td>
        <td nowrap align="center" >Moneda</td>
        <td nowrap align="center" >Cuenta Contable</td>
        <td nowrap align="center" >Cuenta</td>
        <td nowrap align="center" >Centro de Costo</td>
        <td nowrap align="center" >Monto</td>
      </tr>
      <tr id="trclear"> 
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRICRS" size="2" maxlength="2"  value="<%= prMant.getE01PRICRS().trim()%>" onKeyPress="enterInteger()" readonly>
            <input <%=lectura%> type="text" name="E01PRICPD" size="17" maxlength="16"  value="<%= prMant.getE01PRICPD().trim()%>" readonly
			 oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01PRICBK.value,'','E01PRICGL','E01PRICRS','93'); return false;">
            <input <%=lectura%> type="hidden" name="E01CRESOU"  value="<%= prMant.getE01CRESOU().trim()%>">
            <input <%=lectura%> type="hidden" name="E01CREDIB"  value="<%= prMant.getE01CREDIB().trim()%>">
            <input <%=lectura%> type="hidden" name="E01CREDAY">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRICBK" size="2" maxlength="2" value="<%= prMant.getE01PRICBK().trim()%>" >
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRICBR" size="4" maxlength="3"  value="<%= prMant.getE01PRICBR().trim()%>"
			oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01PRICBK.value,'','','','')" onkeypress="enterInteger()">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRICCY" size="3" maxlength="3" value="<%= prMant.getE01PRICCY().trim()%>" 
			oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01PRICBK.value,'','','','')">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRICGL" size="17" maxlength="16" value="<%= prMant.getE01PRICGL().trim()%>" onkeypress="enterInteger()"
            oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01PRICBK.value,document.forms[0].E01PRICCY.value,'','','')">
          </div>
        </td>
        <td nowrap  > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRICAC" size="17" maxlength="16" value="<%= prMant.getE01PRICAC().trim()%>"
		  oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01PRICBK.value,'','','','RT')"  onkeypress="enterInteger()">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
            <input <%=lectura%> type="text" name="E01PRICCC" size="9" maxlength="8"  value="<%= prMant.getE01PRICCC().trim()%>"
			oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01PRICBK.value,'','','','')">
          </div>
        </td>
        <td nowrap >
          <div align="center">
            <input <%=lectura%> type="text" name="E01PRICAM" size="15" maxlength="13" value="<%= prMant.getE01PRICAM().trim()%>">
          </div>
        </td>
      </tr>
      <tr id="trdark"> 
		<td nowrap colspan="4">	</td>
		<td nowrap colspan="2"> 





	</td>
		<td nowrap align="right">D&iacute;as de Retenci&oacute;n : </td>
		<td nowrap align="left"> 
            <input <%=lectura%> type="text" name="E01PRIHDY" size="3" maxlength="3" value="<%= prMant.getE01PRIHDY().trim()%>" onKeyPress="enterInteger()">
      	</td>
      </tr>
    </table>

    <h4>Nuestras Comisiones</h4>
    <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing=0 cellpadding=2 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap width="24%" > 
                <div align="right">Tabla / Monto :</div>
              </td>
              <td nowrap width="30%" > 
                <input <%=lectura%> type="text" name="E01PRICOT" size="2" maxlength="2" value="<%= prMant.getE01PRICOT().trim()%>" onKeyPress="enterInteger()">
                <%if (mostrar){%>
	                <a href="javascript:GetCNTRLPRF('E01PRICOT','E01PRICOM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a> 
                <%}%>
                <input <%=lectura%> type="text" name="E01PRICOM" size="15" maxlength="13" value="<%= prMant.getE01PRICOM().trim()%>" onKeyPress="enterDecimal()" readonly>
              </td>
              <td nowrap width="17%" > 
                <div align="right">Cargos por :</div>
              </td>
              <td nowrap width="29%" > 
                <select name="E01PRICHG" <%=lectura2%>>
				  <option value="A" <% if (!(prMant.getE01PRICHG().equals("B") || prMant.getE01PRICHG().equals("N"))) out.print("selected"); %>>Aplicante</option>
                  <option value="B" <% if (prMant.getE01PRICHG().equals("B")) out.print("selected"); %>>Beneficiario</option>
                  <option value="N" <% if (prMant.getE01PRICHG().equals("N")) out.print("selected"); %>>Ninguno</option>
                </select>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    
    
    
    <h4>Informaci&oacute;n Adicional</h4>
 <table class="tableinfo">
 <tr > 
      <td > 
          <table cellspacing=0 cellpadding=2 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap> 
                <div align="right">V&iacute;a de Pago :</div>
              </td>
              <td nowrap > 
                <select name="E01PRIPVI" <%=lectura2%>>
                  	<option value="" <% if (prMant.getE01PRIPVI().equals("")) out.print("selected"); %>></option>
                	<option value="M" <% if (prMant.getE01PRIPVI().equals("M")) out.print("selected"); %>>Swift MT-102</option>
                  	<option value="3" <% if (prMant.getE01PRIPVI().equals("3")) out.print("selected"); %>>Swift MT-103</option>
                  	<option value="4" <% if (prMant.getE01PRIPVI().equals("4")) out.print("selected"); %>>Swift MT-200</option>
                  	<option value="5" <% if (prMant.getE01PRIPVI().equals("5")) out.print("selected"); %>>Swift MT-202</option>
                  	<option value="7" <% if (prMant.getE01PRIPVI().equals("7")) out.print("selected"); %>>Swift MT-202 COV</option> 
			</select>
              </td>
              <td nowrap > 
                <div align="right">Banco Receptor :</div>
              </td>
              <td nowrap > 
                <input <%=lectura%> type="text" name="E01PRIRID" size="15" maxlength="15" value="<%= prMant.getE01PRIRID().trim()%>">
              <%if (mostrar){%>
	            <a href="javascript:GetSwiftId('E01PRIRID')">
				<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a>
                <%}%>
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap > 
                <div align="right">Instrucciones Recibidas Via :</div>
              </td>
              <td nowrap >
                <input <%=lectura%> type="text" name="E01PRIIRV" size="3" maxlength="1" value="<%= prMant.getE01PRIIRV().trim()%>">
              <%if (mostrar){%>
                <a href="javascript:GetCode('E01PRIIRV','STATIC_pr_via.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0" ></a> 
               <%}%>
               <% 
  			   if (!userPO.getPurpose().equals("NEW")) { 
			   %>          
              	<td nowrap>
              		<div align="right">Banco Remitente :</div> 
              	</td>
              	<td nowrap>
              	 	<input <%=lectura%> type="text" name="E01PRISID" size="13" maxlength="12" value="<%= prMant.getE01PRISID().trim()%>" readonly> 
              	</td>
              <%}%>             
            </tr>
            <tr id="trdark"> 
              <td nowrap  > 
                <div align="right">Motivo de Operaci&oacute;n :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input <%=lectura%> type="text" name="E01PRIUC2" size="5" maxlength="4" value="<%= prMant.getE01PRIUC2().trim()%>">
              <%if (mostrar){%>
                  <a href="javascript:GetCodeCNOFC('E01PRIUC2',document.forms[0].TRANSFTYPE.value)">
                  <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a>
               <%}%>
               </div>
              </td>
              <TD nowrap width="17%"> 
                <DIV align="right">Detalle de Cargos  :</DIV>
              </TD><TD nowrap width="29%"> 
                <% 
                if (!(prMant.getE01PRITTC().equals("BEN")) && !(prMant.getE01PRITTC().equals("OUR")) && !(prMant.getE01PRITTC().equals("SHA")))
                {   prMant.setE01PRITTC("SHA"); } 
                %>
                <SELECT name="E01PRITTC" <%=lectura2%>>
				  <OPTION value="" <% if (!(prMant.getE01PRITTC().equals("B") || prMant.getE01PRITTC().equals("N"))) out.print("selected"); %>></OPTION>
				  <OPTION value="BEN" <% if (prMant.getE01PRITTC().equals("BEN")) out.print("selected"); %>>BEN</OPTION>
                  <OPTION value="OUR" <% if (prMant.getE01PRITTC().equals("OUR")) out.print("selected"); %>>OUR</OPTION>
                  <OPTION value="SHA" <% if (prMant.getE01PRITTC().equals("SHA")) out.print("selected"); %>>SHA</OPTION>
                </SELECT>
              </TD>
              
            </tr>
            <tr id="trclear"> 
              <td nowrap  > 
                <div align="right">Referencia :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input <%=lectura%> type="text" name="E01PRISRF" size="17" maxlength="16" value="<%= prMant.getE01PRISRF().trim()%>">
                </div>
              </td>
              <td nowrap > 
                <div align="right">Referencia Relacionada :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input <%=lectura%> type="text" name="E01PRITHF" size="17" maxlength="16" value="<%= prMant.getE01PRITHF().trim()%>">
                </div>
              </td>
            </tr>
            
              <% 
                if (!(prMant.getE01PRIBKO().equals("CRED")) && !(prMant.getE01PRIBKO().equals("CRTS")) && !(prMant.getE01PRIBKO().equals("SPAY")) && !(prMant.getE01PRIBKO().equals("SPRI")  && !(prMant.getE01PRIBKO().equals("SSTD"))))
                {   prMant.setE01PRIBKO("CRED"); } 
                %>
            
            <tr id="trdark"> 
              <td nowrap  > 
                <div align="right">Cod. Operación Bancaria :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input <%=lectura%> type="text" name="E01PRIBKO" size="6" maxlength="4" value="<%= prMant.getE01PRIBKO()%>">
              <%if (mostrar){%>
                  <a href="javascript:GetCode('E01PRIBKO','STATIC_tr_operations.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0" ></a>
               <%}%>
               </div>
              </td>
              <td nowrap > 
                <div align="right">Código de Orden :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input <%=lectura%> type="text" name="E01PRIITC" size="6" maxlength="4" value="<%= prMant.getE01PRIITC()%>">
	              <%if (mostrar){%>
	                  <a href="javascript:GetCode('E01PRIITC','STATIC_tr_instructions.jsp')">
	                  <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0" ></a> 
                 <%}%>
                  <input <%=lectura%> type="text" name="E01PRIITA" size="11" maxlength="10" value="<%= prMant.getE01PRIITA()%>">
                </div>
              </td>
            </tr>
            
            <tr id="trclear"> 
              <td nowrap  > 
                <div align="right">Ident.Beneficiario :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input <%=lectura%> type="text" name="E01PRIBID" size="15" maxlength="15" value="<%= prMant.getE01PRIBID()%>">
                 </div>
              </td>
              <td nowrap  > 
                <div align="right">Cuenta del Beneficiario :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input <%=lectura%> type="text" name="E01PRIBAC" size="40" maxlength="35" value="<%= prMant.getE01PRIBAC()%>">
                 </div>
              </td>

            </tr>          

	</table>
	</td>
   </tr>
</table>
<br>
  <table class="tbenter" width="100%" border="0" cellspacing=0 cellpadding=0>
    <tr> 
       <td nowrap> 
           <table id="TableTab" cellspacing=0 cellpadding=2 border="0">
          		<tr> 

                      <td nowrap id="Tab0" class="tabnormal" onClick="showTab(0,'E11PRIINB')"> 
                        	<div nowrap >Banco Intermediario</div>
                      </td> 

                  <td nowrap id="Tab1" class="tabnormal" onClick="showTab(1,'E11PRIBBK')"> 
                            <div nowrap >Banco Pagador</div>
                  </td>

                  <td nowrap id="Tab2" class="tabnormal" onClick="showTab(2,'E11PRIBCU')"> 
                            <div >Beneficiario</div>
                  </td> 

                      <td nowrap id="Tab3" class="tabnormal" onClick="showTab(3,'E11PRIBKB')"> 
                        	<div nowrap >Infor. Banco a Banco</div>
                      </td> 
                    
                 
                  <td class="tabempty" width="100%">&nbsp;                       
                  </td> 
                </tr>
        </table>
       </td>
    </tr>
  </table>
	<table class="tabdata" width="100%">
    <tr>
     <td nowrap>
                        <div id="dataTab0" align=center> 
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
		                          	<div><a href="javascript:GetSwiftIdDesc('E11PRIINB','E21PRIINB','E31PRIINB','E41PRIINB','E51PRIINB')">
		                          	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0"></a></div>
                          <input <%=lectura%> type="text" name="E11PRIINB" size="45" maxlength="35" value="<%= prMant.getE11PRIINB().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E21PRIINB" size="45" maxlength="35" value="<%= prMant.getE21PRIINB().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E31PRIINB" size="45" maxlength="35" value="<%= prMant.getE31PRIINB().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E41PRIINB" size="45" maxlength="35" value="<%= prMant.getE41PRIINB().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E51PRIINB" size="45" maxlength="35" value="<%= prMant.getE51PRIINB().trim()%>">
                        	</td>
    						</tr>
 						 	</table>
                        </div>

                        <div id="dataTab1" style="display: none" align=center> 
                        		<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
		                          	<div><a href="javascript:GetSwiftIdDesc('E11PRIBBK','E21PRIBBK','E31PRIBBK','E41PRIBBK','E51PRIBBK')">
		                          	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a></div>
                          <input <%=lectura%> type="text" name="E11PRIBBK" size="45" maxlength="35" value="<%= prMant.getE11PRIBBK().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E21PRIBBK" size="45" maxlength="35" value="<%= prMant.getE21PRIBBK().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E31PRIBBK" size="45" maxlength="35" value="<%= prMant.getE31PRIBBK().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E41PRIBBK" size="45" maxlength="35" value="<%= prMant.getE41PRIBBK().trim()%>">
                        	<br>
                          <input <%=lectura%> type="text" name="E51PRIBBK" size="45" maxlength="35" value="<%= prMant.getE51PRIBBK().trim()%>">
                        	</td>
    						</tr>
 						 	</table>
                        </div>

                        <div id="dataTab2" style="display: none" align=center> 
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
		                          	<div><a href="javascript:GetSwiftIdDesc('E11PRIBCU','E21PRIBCU','E31PRIBCU','E41PRIBCU','E51PRIBCU')">
		                          	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a></div>                        
                          <input <%=lectura%> type="text" name="E11PRIBCU" size="45" maxlength="35" value="<%= prMant.getE11PRIBCU().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E21PRIBCU" size="45" maxlength="35" value="<%= prMant.getE21PRIBCU().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E31PRIBCU" size="45" maxlength="35" value="<%= prMant.getE31PRIBCU().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E41PRIBCU" size="45" maxlength="35" value="<%= prMant.getE41PRIBCU().trim()%>">
                        	<br>
                          <input <%=lectura%> type="text" name="E51PRIBCU" size="45" maxlength="35" value="<%= prMant.getE51PRIBCU().trim()%>">
                        	</td>
    						</tr>
 						 	</table>
                        </div>

                        <div id="dataTab3" style="display: none" align=center>
                       <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    	<tr id="trdark" > 
                      	<td nowrap align=center>  
						<div><a href="javascript:GetSwiftIdDesc('E11PRIBKB','E21PRIBKB','E31PRIBKB','E41PRIBKB')">
		                  	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a></div>
                          <input <%=lectura%> type="text" name="E11PRIBKB" size="45" maxlength="35" value="<%= prMant.getE11PRIBKB().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E21PRIBKB" size="45" maxlength="35" value="<%= prMant.getE21PRIBKB().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E31PRIBKB" size="45" maxlength="35" value="<%= prMant.getE31PRIBKB().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E41PRIBKB" size="45" maxlength="35" value="<%= prMant.getE41PRIBKB().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E51PRIBKB" size="45" maxlength="35" value="<%= prMant.getE51PRIBKB().trim()%>">
                          <br>
                          <input <%=lectura%> type="text" name="E61PRIBKB" size="45" maxlength="35" value="<%= prMant.getE61PRIBKB().trim()%>">
                        	</td>
    						</tr>
 						 	</table>
                        </div>
                        
        </td>
            
    </tr>
  </table>
                 
   <BR>
                    
  <table class="tbenter" width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
       <td nowrap> 
           <table id="TableTabB" cellspacing="0" cellpadding="2" border="0">
                   <tr> 
               
                  <td nowrap id="TabB0" class="tabhighlight" onClick="showTabB(0,'E11PRIDTO')"> 
                            <div nowrap >Detalles para Ordenante </div>
                  </td> 
                
                  <td nowrap id="TabB1" class="tabnormal" onClick="showTabB(1,'E11PRIDTP')"> 
                            <div nowrap >Detalles de Pago </div>
                  </td> 
                    
                      <td nowrap id="TabB2" class="tabnormal" onClick="showTabB(2,'E11PRISCB')"> 
                        	<div nowrap >Bco. Corresponsal Emisor</div>
                      </td> 
                    
                      <td nowrap id="TabB3" class="tabnormal" onClick="showTabB(3,'E11PRIRCB')"> 
                        	<div nowrap >Bco. Corresponsal Receptor</div>
                      </td>
					 <td class="tabempty" width="100%">&nbsp;</td>
                  </tr>
	      </table>
	    </td>
	  </tr>
	</table>	
	<table class="tabdata" width="100%">
    	<tr>
    		 <td nowrap>                     
                        
                        <div id="dataTabB0" align=center> 
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
						<div><a href="javascript:GetSwiftIdDesc('E11PRIDTO','E21PRIDTO','E31PRIDTO','E41PRIDTO','E51PRIDTO')">
		                  	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a></div>
		                          <input type="text" name="E11PRIDTO" size="36" maxlength="35" value="<%= prMant.getE11PRIDTO().trim()%>">
		                          <br>
		                          <input type="text" name="E21PRIDTO" size="36" maxlength="35" value="<%= prMant.getE21PRIDTO().trim()%>">
		                          <br>
		                          <input type="text" name="E31PRIDTO" size="36" maxlength="35" value="<%= prMant.getE31PRIDTO().trim()%>">
		                          <br>
		                          <input type="text" name="E41PRIDTO" size="36" maxlength="35" value="<%= prMant.getE41PRIDTO().trim()%>">
                        		<br>
		                          <input type="text" name="E51PRIDTO" size="36" maxlength="35" value="<%= prMant.getE51PRIDTO().trim()%>">
                        		</td>
    						</tr>
 						 	</table>
                        </div>
                        <div id="dataTabB1" style="display: none" align=center>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
						<div><a href="javascript:GetSwiftIdDesc('E11PRIDTP','E21PRIDTP','E31PRIDTP','E41PRIDTP','E51PRIDTP')">
		                  	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0"></a></div>
                          <input type="text" name="E11PRIDTP" size="36" maxlength="35" value="<%= prMant.getE11PRIDTP().trim()%>">
                          <br>
                          <input type="text" name="E21PRIDTP" size="36" maxlength="35" value="<%= prMant.getE21PRIDTP().trim()%>">
                          <br>
                          <input type="text" name="E31PRIDTP" size="36" maxlength="35" value="<%= prMant.getE31PRIDTP().trim()%>">
                          <br>
                          <input type="text" name="E41PRIDTP" size="36" maxlength="35" value="<%= prMant.getE41PRIDTP().trim()%>">
                        	<br>
                          <input type="text" name="E51PRIDTP" size="36" maxlength="35" value="<%= prMant.getE51PRIDTP().trim()%>">
                        	</td>
    						</tr>
 						 	</table>
                        </div>
                        <div id="dataTabB2" style="display: none">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center>  
						<div><a href="javascript:GetSwiftIdDesc('E11PRISCB','E21PRISCB','E31PRISCB','E41PRISCB','E51PRISCB')">
		                  	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"   border="0"></a></div>
                          <input type="text" name="E11PRISCB" size="36" maxlength="35" value="<%= prMant.getE11PRISCB().trim()%>">
                          <br>
                          <input type="text" name="E21PRISCB" size="36" maxlength="35" value="<%= prMant.getE21PRISCB().trim()%>">
                          <br>
                          <input type="text" name="E31PRISCB" size="36" maxlength="35" value="<%= prMant.getE31PRISCB().trim()%>">
                          <br>
                          <input type="text" name="E41PRISCB" size="36" maxlength="35" value="<%= prMant.getE41PRISCB().trim()%>">
                       		 <br>
                          <input type="text" name="E51PRISCB" size="36" maxlength="35" value="<%= prMant.getE51PRISCB().trim()%>">
                       	</td>
    						</tr>
 						 	</table>
                        </div>
                        <div id="dataTabB3" style="display: none" > 
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<tr id="trdark" > 
                      			<td nowrap align=center> 
						<div><a href="javascript:GetSwiftIdDesc('E11PRIRCB','E21PRIRCB','E31PRIRCB','E41PRIRCB','E51PRIRCB')">
		                  	<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"  border="0"></a></div>
                          <input type="text" name="E11PRIRCB" size="36" maxlength="35" value="<%= prMant.getE11PRIRCB().trim()%>">
                          <br>
                          <input type="text" name="E21PRIRCB" size="36" maxlength="35" value="<%= prMant.getE21PRIRCB().trim()%>">
                          <br>
                          <input type="text" name="E31PRIRCB" size="36" maxlength="35" value="<%= prMant.getE31PRIRCB().trim()%>">
                          <br>
                          <input type="text" name="E41PRIRCB" size="36" maxlength="35" value="<%= prMant.getE41PRIRCB().trim()%>">
                        	<br>
                          <input type="text" name="E51PRIRCB" size="36" maxlength="35" value="<%= prMant.getE51PRIRCB().trim()%>">
                        	 </td>
    						</tr>
 						 	</table>
                        </div>                      
                    </td>
  	</tr>
  </table>
                    
    <BR>
    <h4>Claves Confirmación DCV</h4>
    <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing=0 cellpadding=2 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap width="24%" > 
                <input <%=lectura%> type="text" name="E01PRUCR1" size="16" maxlength="15" value="<%= prMant.getE01PRUCR1().trim()%>">
              </td>
              <td nowrap width="30%" > 
                <input <%=lectura%> type="text" name="E01PRUCR2" size="16" maxlength="15" value="<%= prMant.getE01PRUCR2().trim()%>">
                </td>
              <td nowrap width="17%" > 
                <input <%=lectura%> type="text" name="E01PRUCR3" size="16" maxlength="15" value="<%= prMant.getE01PRUCR3().trim()%>">
              </td>
              <td nowrap width="29%" > 
                <input <%=lectura%> type="text" name="E01PRUCR4" size="16" maxlength="15" value="<%= prMant.getE01PRUCR4().trim()%>">
              </td>
              <td nowrap width="29%" > 
                <input <%=lectura%> type="text" name="E01PRUCR5" size="16" maxlength="15" value="<%= prMant.getE01PRUCR5().trim()%>">
              </td>
            </tr>

            <tr id="trclear"> 
              <td nowrap width="24%" > 
                <input <%=lectura%> type="text" name="E01PRUCR6" size="16" maxlength="15" value="<%= prMant.getE01PRUCR6().trim()%>">
              </td>
              <td nowrap width="30%" > 
                <input <%=lectura%> type="text" name="E01PRUCR7" size="16" maxlength="15" value="<%= prMant.getE01PRUCR7().trim()%>">
                </td>
              <td nowrap width="17%" > 
                <input <%=lectura%> type="text" name="E01PRUCR8" size="16" maxlength="15" value="<%= prMant.getE01PRUCR8().trim()%>">
              </td>
              <td nowrap width="29%" > 
                <input <%=lectura%> type="text" name="E01PRUCR9" size="16" maxlength="15" value="<%= prMant.getE01PRUCR9().trim()%>">
              </td>
              <td nowrap width="29%" > 
                <input <%=lectura%> type="text" name="E01PRUC10" size="16" maxlength="15" value="<%= prMant.getE01PRUC10().trim()%>">
              </td>
            </tr>


          </table>
        </td>
      </tr>
    </table>
    




<br>
<B>Campos Adicionales</B>
<BR>

<TABLE class="tableinfo" id="dataTable">
    
    <TR id=trdark>
		<td NOWRAP align="center" width="5%"><b>Seq</b></td>
		<td NOWRAP align="center" width="5%"><b>TAG</b></td>
		<td NOWRAP align="center" width="20%"><b>Descripcion</b></td>
		<td NOWRAP align="center" width="5%"><b>Opcion</b></td>
		<td NOWRAP align="left" width="65%"><b>Campo</b></td>
	</TR> 
	<% 
	  if (!prMant.getE01PRATG0().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ0" size="2" maxlength="1" value="<%= prMant.getE01PRASQ0().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG0" size="5" maxlength="4" value="<%= prMant.getE01PRATG0().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS0" size="31" maxlength="30" value="<%= prMant.getE01TAGDS0().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP0" size="2" maxlength="1" value="<%= prMant.getE01PRAOP0().trim()%>" READONLY>
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL0" value="<%= prMant.getE01PRAFL0().trim()%>">		
	        <%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE0().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        }  	
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL0().substring(posi,posf);
	          } catch (Exception e) {
	          	if(posi <=  prMant.getE01PRAFL0().length()) {
				  vl = prMant.getE01PRAFL0().substring(posi);;
				}
	          }
			%>
			  <INPUT type="text" name="E01PRAFL0_<%=ix%>" size="40" maxlength="35" value="<%= vl %>"  
	  			   <%=READ_ONLY_TAG%> onchange="buildField('E01PRAFL0',<%=row%>, <%=lnt%>)"><BR> 
			<%}%>   
		</td>
	</TR>
	<% } %>
	<% 
	  if (!prMant.getE01PRATG1().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ1" size="2" maxlength="1" value="<%= prMant.getE01PRASQ1().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG1" size="5" maxlength="4" value="<%= prMant.getE01PRATG1().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS1" size="31" maxlength="30" value="<%= prMant.getE01TAGDS1().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP1" size="2" maxlength="1" value="<%= prMant.getE01PRAOP1().trim()%>" READONLY >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL1" value="<%= prMant.getE01PRAFL1().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE1().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL1().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL1().length()) {
				  vl = prMant.getE01PRAFL1().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL1_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   <%=READ_ONLY_TAG%> onchange="buildField('E01PRAFL1',<%=row%>, <%=lnt%>)"><BR> 
			<%}%>
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG2().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ2" size="2" maxlength="1" value="<%= prMant.getE01PRASQ2().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG2" size="5" maxlength="4" value="<%= prMant.getE01PRATG2().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS2" size="31" maxlength="30" value="<%= prMant.getE01TAGDS2().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP2" size="2" maxlength="1" value="<%= prMant.getE01PRAOP2().trim()%>" READONLY >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL2" value="<%= prMant.getE01PRAFL2().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE2().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL2().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL2().length()) {
				  vl = prMant.getE01PRAFL2().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL2_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   <%=READ_ONLY_TAG%> onchange="buildField('E01PRAFL2',<%=row%>, <%=lnt%>)"><BR> 
			<%}%>
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG3().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ3" size="2" maxlength="1" value="<%= prMant.getE01PRASQ3().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG3" size="5" maxlength="4" value="<%= prMant.getE01PRATG3().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS3" size="31" maxlength="30" value="<%= prMant.getE01TAGDS3().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP3" size="2" maxlength="1" value="<%= prMant.getE01PRAOP3().trim()%>" READONLY >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL3" value="<%= prMant.getE01PRAFL3().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE3().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL3().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL3().length()) {
				  vl = prMant.getE01PRAFL3().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL3_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			  <%=READ_ONLY_TAG%> onchange="buildField('E01PRAFL3',<%=row%>, <%=lnt%>)"><BR> 
			<%}%>
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG4().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ4" size="2" maxlength="1" value="<%= prMant.getE01PRASQ4().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG4" size="5" maxlength="4" value="<%= prMant.getE01PRATG4().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS4" size="31" maxlength="30" value="<%= prMant.getE01TAGDS4().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP4" size="2" maxlength="1" value="<%= prMant.getE01PRAOP4().trim()%>" READONLY >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL4" value="<%= prMant.getE01PRAFL4().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE4().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL4().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL4().length()) {
				  vl = prMant.getE01PRAFL4().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL4_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   <%=READ_ONLY_TAG%> onchange="buildField('E01PRAFL4',<%=row%>, <%=lnt%>)"><BR> 
			<%}%>
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG5().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ5" size="2" maxlength="1" value="<%= prMant.getE01PRASQ5().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG5" size="5" maxlength="4" value="<%= prMant.getE01PRATG5().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS5" size="31" maxlength="30" value="<%= prMant.getE01TAGDS5().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP5" size="2" maxlength="1" value="<%= prMant.getE01PRAOP5().trim()%>" READONLY >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL5" value="<%= prMant.getE01PRAFL5().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE5().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL5().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL5().length()) {
				  vl = prMant.getE01PRAFL5().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL5_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   <%=READ_ONLY_TAG%> onchange="buildField('E01PRAFL5',<%=row%>, <%=lnt%>)"><BR> 
			<%}%>
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG6().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ6" size="2" maxlength="1" value="<%= prMant.getE01PRASQ6().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG6" size="5" maxlength="4" value="<%= prMant.getE01PRATG6().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS6" size="31" maxlength="30" value="<%= prMant.getE01TAGDS6().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP6" size="2" maxlength="1" value="<%= prMant.getE01PRAOP6().trim()%>" READONLY >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL6" value="<%= prMant.getE01PRAFL6().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE6().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL6().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL6().length()) {
				  vl = prMant.getE01PRAFL6().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL6_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   <%=READ_ONLY_TAG%> onchange="buildField('E01PRAFL6',<%=row%>, <%=lnt%>)"><BR> 
			<%}%>
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG7().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ7" size="2" maxlength="1" value="<%= prMant.getE01PRASQ7().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG7" size="5" maxlength="4" value="<%= prMant.getE01PRATG7().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS7" size="31" maxlength="30" value="<%= prMant.getE01TAGDS7().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP7" size="2" maxlength="1" value="<%= prMant.getE01PRAOP7().trim()%>" READONLY >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL7" value="<%= prMant.getE01PRAFL7().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE7().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL7().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL7().length()) {
				  vl = prMant.getE01PRAFL7().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL7_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   READONLY > onchange="buildField('E01PRAFL7',<%=row%>, <%=lnt%>)"><BR> 
			<%}%>
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG8().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ8" size="2" maxlength="1" value="<%= prMant.getE01PRASQ8().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG8" size="5" maxlength="4" value="<%= prMant.getE01PRATG8().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS8" size="31" maxlength="30" value="<%= prMant.getE01TAGDS8().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP8" size="2" maxlength="1" value="<%= prMant.getE01PRAOP8().trim()%>" READONLY >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL8" value="<%= prMant.getE01PRAFL8().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE8().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL8().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL8().length()) {
				  vl = prMant.getE01PRAFL8().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL8_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   READONLY > onchange="buildField('E01PRAFL8',<%=row%>, <%=lnt%>)"><BR> 
			<%}%>
		</td>
	</TR>
	<% } %>  
	<% 
	  if (!prMant.getE01PRATG9().trim().equals("")) { 
	%>
    <TR id=trclear>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRASQ9" size="2" maxlength="1" value="<%= prMant.getE01PRASQ9().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRATG9" size="5" maxlength="4" value="<%= prMant.getE01PRATG9().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="20%" valign="top">
			<INPUT type="text" name="E01TAGDS9" size="31" maxlength="30" value="<%= prMant.getE01TAGDS9().trim()%>" readonly >
		</td>
		<td NOWRAP align="center" width="5%" valign="top">
			<INPUT type="text" name="E01PRAOP9" size="2" maxlength="1" value="<%= prMant.getE01PRAOP9().trim()%>" READONLY >
		</td>
		<td NOWRAP align="left" width="65%" valign="top">
			<INPUT type="hidden" name="E01PRAFL9" value="<%= prMant.getE01PRAFL9().trim()%>">		
			<%
	        int row = 1;
	        int lnt = Integer.parseInt(prMant.getE01TAGLE9().trim());
	        int ln = lnt;
	        if (lnt > 35) {
	          row = lnt / 35;
	          ln = 35;
	        } 
	        for (int ix = 1; ix <= row; ix++) {
	          int posi = (ix * ln) - ln;
	          int posf = (ix * ln) - 1;
	          String vl = "";
	          try {
	            vl =  prMant.getE01PRAFL9().substring(posi,posf);
	          } catch (Exception e) {
	            if(posi <=  prMant.getE01PRAFL9().length()) {
				  vl = prMant.getE01PRAFL9().substring(posi);;
				} 
	          }
			%>
			  <INPUT type="text" name="E01PRAFL9_<%=ix%>" size="40" maxlength="35" value="<%= vl %>" 
			   READONLY > onchange="buildField('E01PRAFL9',<%=row%>, <%=lnt%>)"><BR> 
			<%}%>
		</td>
	</TR>
	<% } %>  



  </TABLE>






    <BR>
    
<%if (mostrar){%>
<h4 style="text-align:center"><input type="checkbox" name="H01FLGWK2" value="A" <% if(prMant.getH01FLGWK2().equals("A")){ out.print("checked");} %>>
      Aceptar con Advertencias</h4>
</div>

<p align="center"> 
<%-- <INPUT id="EIBSBTN" type="button" name ="btenviar" value="Enviar"  onClick="enviar()"> --%>
<INPUT id="EIBSBTN" type="button" name ="btenviar" value="Enviar" onClick="enviar1()"> 
 </p>
<% } %>
</form>
  

</body>
</html>

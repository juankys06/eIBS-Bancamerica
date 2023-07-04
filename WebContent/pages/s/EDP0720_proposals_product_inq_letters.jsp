<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Producto
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id="msgRT" class="datapro.eibs.beans.EDP072001Message"  scope="session" />
<jsp:useBean id="msgRTC" class="datapro.eibs.beans.EDP072201Message"  scope="session" />
<jsp:useBean id="msgList" class="datapro.eibs.beans.EDP072101Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<%
if (!error.getERRNUM().equals("0")) {
      error.setERRNUM("0");
%>
	<SCRIPT Language="Javascript">
		showErrors();
		</SCRIPT>
<%}%>


<SCRIPT language="JavaScript">

builtHPopUp();

function initdp()
{
 // texto comentarios 
 if (document.forms[0].E02DPAC00 != null) {
	document.forms[0].E02DPAC00.value = "<%=msgRTC.getE02DPAC00().trim()%>";
 }
 if (document.forms[0].E02DPAC01 != null) {
	document.forms[0].E02DPAC01.value = "<%=msgRTC.getE02DPAC01().trim()%>";
 }
 if (document.forms[0].E02DPAC02 != null) {
	document.forms[0].E02DPAC02.value = "<%=msgRTC.getE02DPAC02().trim()%>";
 }
 if (document.forms[0].E02DPAC03 != null) {
	document.forms[0].E02DPAC03.value = "<%=msgRTC.getE02DPAC03().trim()%>";
 }
 if (document.forms[0].E02DPAC04 != null) {
	document.forms[0].E02DPAC04.value = "<%=msgRTC.getE02DPAC04().trim()%>";
 }
 if (document.forms[0].E02DPAC05 != null) {
	document.forms[0].E02DPAC05.value = "<%=msgRTC.getE02DPAC05().trim()%>";
 }
 if (document.forms[0].E02DPAC06 != null) {
	document.forms[0].E02DPAC06.value = "<%=msgRTC.getE02DPAC06().trim()%>";
 }
 if (document.forms[0].E02DPAC07 != null) {
	document.forms[0].E02DPAC07.value = "<%=msgRTC.getE02DPAC07().trim()%>";
 }
 if (document.forms[0].E02DPAC08 != null) {
	document.forms[0].E02DPAC08.value = "<%=msgRTC.getE02DPAC08().trim()%>";
 }
 if (document.forms[0].E02DPAC09 != null) {
	document.forms[0].E02DPAC09.value = "<%=msgRTC.getE02DPAC09().trim()%>";
 }
 if (document.forms[0].E02DPAC10 != null) {
	document.forms[0].E02DPAC10.value = "<%=msgRTC.getE02DPAC10().trim()%>";
 }
 if (document.forms[0].E02DPAC11 != null) {
	document.forms[0].E02DPAC11.value = "<%=msgRTC.getE02DPAC11().trim()%>";
 }
 if (document.forms[0].E02DPAC12 != null) {
	document.forms[0].E02DPAC12.value = "<%=msgRTC.getE02DPAC12().trim()%>";
 }
 if (document.forms[0].E02DPAC13 != null) {
	document.forms[0].E02DPAC13.value = "<%=msgRTC.getE02DPAC13().trim()%>";
 }
 if (document.forms[0].E02DPAC14 != null) {
	document.forms[0].E02DPAC14.value = "<%=msgRTC.getE02DPAC14().trim()%>";
 }
 if (document.forms[0].E02DPAC15 != null) {
	document.forms[0].E02DPAC15.value = "<%=msgRTC.getE02DPAC15().trim()%>";
 }
 if (document.forms[0].E02DPAC16 != null) {
	document.forms[0].E02DPAC16.value = "<%=msgRTC.getE02DPAC16().trim()%>";
 }
 if (document.forms[0].E02DPAC98 != null) { 
	document.forms[0].E02DPAC98.value = "<%=msgRTC.getE02DPAC98().trim()%>";
 }
 if (document.forms[0].E02DPAC99 != null) {
	document.forms[0].E02DPAC99.value = "<%=msgRTC.getE02DPAC99().trim()%>";
 }
}


function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
}

function goConfirm(op,TASK, OPECOD,PARAM) {
	
	document.forms[0].OPECOD.value = OPECOD;
	document.forms[0].PARAM.value = PARAM;
   	document.forms[0].TASK.value = TASK;

	document.forms[0].E02DPAPRO.value = document.forms[0].E01PLTPRO.value ;
	document.forms[0].TYP.value = document.forms[0].E01PLTTYP.value ;

	document.forms[0].opt.value = op;

    document.forms[0].SCREEN.value="460";
	document.forms[0].submit();

}

function ProcAgricola(value){
 if (value) {
   SecAgricola.style.display="none";}
 else{
   SecAgricola.style.display=""; }
}


function goCancel(opt) {
	var op = opt;  	  
	document.forms[0].opt.value = op;
	document.forms[0].SCREEN.value="470"; 
	document.forms[0].submit(); 
}

function selK() {
var selectedItem = document.forms[0].E01PLTCCA.selectedIndex;
var selectedV = document.forms[0].E01PLTCCA.options[selectedItem].value;
if (selectedV == '8') {
	document.forms[0].E01PLTCIN.selectedIndex=-1;
	document.forms[0].E01PLTCBI.selectedIndex=-1;
	document.forms[0].E01PLTRTE.value=0;
	document.forms[0].E01PLTPLZ.value=0;
	document.forms[0].E01PLTUPL.selectedIndex=-1;
}
var pagare = document.forms[0].E01XXXREN.value;

if (pagare == "1") {
	var op_forma_pago = document.forms[0].E01PLTCCA.options[selectedItem].value;
	var periodo = document.forms[0].E01PLTPLZ.value;
	document.forms[0].E02DPAC03.value="";			
	switch (op_forma_pago) { 
		case  '1': 
				document.forms[0].E02DPAC03.value="Pagare a un dia prorragrable diario"; 
				break;
		case  '2': 
				document.forms[0].E02DPAC03.value="Pagare a 30 dia(s), prorrograble por periodos mensuales sucesivos, hasta el vencimiento del plazo del credito antes indicado"; 
				break;
	   case  '3': 
				document.forms[0].E02DPAC03.value="Pagare a 60 dia(s), prorrograble por periodos bimestrales sucesivos, hasta el vencimiento del plazo del credito antes indicado";
				break;
		case  '4': 
				document.forms[0].E02DPAC03.value="Pagare a 90 dia(s), prorrograble por periodos trimestrales sucesivos, hasta el vencimiento del plazo del credito antes indicado"; 
				break;		
		case  '5': 
				document.forms[0].E02DPAC03.value="Pagare a 180 dia(s) prorrograble por periodos semestrales sucesivos, hasta el vencimiento del plazo del credito antes indicado"; 
				break;
	   case  '6': 
	   			if (periodo > 1) {
	 		    	document.forms[0].E02DPAC03.value="Pagare a 360 dias dias prorrograble periodos anuales"; 
	 		    }	
		        break;
	   case  '7':
	   			document.forms[0].E02DPAC03.value="Pagare prorrogable por los periodos sucesivos del capital, hasta el vencimiento del plazo del credito antes indicado, en la forma de pago";
	   			break;
	   default :document.forms[0].E02DPAC03.value="";
	   			break;			
	};
 }; 	
}

function cancelBub(){
  event.cancelBubble = true;
}

</SCRIPT>

</HEAD>

<body onload=javascript:initdp()>

<h3 align="center">Detalle del Producto<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="proposals_product_inq_letters.jsp,EDP0720"></h3>



<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0720" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="460">
  <INPUT type=HIDDEN name="TIPPROPUESTA" value="<%= msgRT.getE01PLPPTY().trim()%>">
  <INPUT type=HIDDEN name="E01PLPPTY" value="<%= msgRT.getE01PLPPTY().trim()%>">
  <INPUT type=HIDDEN name="E01PLTPTY" value="<%= msgRT.getE01PLPPTY().trim()%>">
  <INPUT type=HIDDEN name="REFERENCIADEALS" value ="">
  <input type=HIDDEN name="PROD" value="<%= msgRTC.getE02DPAPRO().trim()%>">
  <input type=HIDDEN name="CCY" value="<%= msgList.getE01PLTCCY().trim()%>">
  <input type=HIDDEN name="DST" value="<%= msgList.getE01PLTDST().trim()%>">
  <input type=HIDDEN name="ORI" value="<%= msgList.getE01PLTORI().trim()%>">
  <input type=HIDDEN name="BNK" value="<%= userPO.getBank()%>"> 
  <input type=HIDDEN name="BRN" value="<%= userPO.getBranch()%>"> 
  <input type=HIDDEN name="RUT" value="<%= userPO.getHeader15().trim()%>"> 
  <input type=HIDDEN name="CUS" value="<%= userPO.getCusNum()%>"> 
  <input type=HIDDEN name="OPPRODUCTO" value="<%= userPO.getHeader14()%>"> 
  <input type=HIDDEN name="OTR" value="<%= msgList.getE01PLTOTH().trim()%>">


  <input type=HIDDEN name="E01XXXREN" value="<%= msgRTC.getE02XXXREN().trim()%>">
  <input type=HIDDEN name="E01PLTFL1" value="<%= msgList.getE01PLTFL1().trim()%>">
  <input type=HIDDEN name="OPECOD" value="<%= msgRT.getE01DPWOPC().trim()%>">
  <input type=HIDDEN name="PARAM" value="<%= msgRT.getE01DPWPAR().trim()%>">
  <input type=HIDDEN name="TASK" value="<%= userPO.getHeader23().trim()%>">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="<%= userPO.getHeader16().trim()%>">
  <INPUT TYPE=HIDDEN NAME="H01FLGWK3" VALUE="<%= msgRT.getH01FLGWK3().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E02DPAC98" VALUE="<%= msgRTC.getE02DPAC98().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E02DPAC99" VALUE="<%= msgRTC.getE02DPAC99().trim()%>">
  <input type=HIDDEN name="UN0" value="<%= msgList.getE01DPPDR0().trim()%>">
  <input type=HIDDEN name="UN1" value="<%= msgList.getE01DPPDR1().trim()%>">
  <input type=HIDDEN name="UN2" value="<%= msgList.getE01DPPDR2().trim()%>">
  <input type=HIDDEN name="UN3" value="<%= msgList.getE01DPPDR3().trim()%>">
  <input type=HIDDEN name="UN4" value="<%= msgList.getE01DPPDR4().trim()%>">
  <input type=HIDDEN name="UN5" value="<%= msgList.getE01DPPDR5().trim()%>">
  <input type=HIDDEN name="SB0" value="<%= msgList.getE01DPPSB0().trim()%>">
  <input type=HIDDEN name="SB1" value="<%= msgList.getE01DPPSB1().trim()%>">
  <input type=HIDDEN name="SB2" value="<%= msgList.getE01DPPSB2().trim()%>">
  <input type=HIDDEN name="SB3" value="<%= msgList.getE01DPPSB3().trim()%>">
  <input type=HIDDEN name="SB4" value="<%= msgList.getE01DPPSB4().trim()%>">
  <input type=HIDDEN name="SB5" value="<%= msgList.getE01DPPSB5().trim()%>">
  <input type=HIDDEN name="TRE" value="<%= msgList.getE01DPZTRE().trim()%>">
  <input type=HIDDEN name="ACD" value="<%= msgList.getE01PLTACD().trim()%>">
  <input type=HIDDEN name="E02DPAPRO" value="">
  <input type=HIDDEN name="E02DPATY1" value="">
  <input type=HIDDEN name="AGRIC" value="">
  <input type=HIDDEN name="CART" value="">
  <input type=HIDDEN name="TYP" value="">
  <INPUT TYPE=HIDDEN NAME="LAN" value="<%= msgList.getE01CNTLAN().trim()%>">
  <input type=HIDDEN name="E01YYYC001" value="">
  <input type=HIDDEN name="E01YYYC002" value="">
  <input type=HIDDEN name="E01YYYC003" value="">
  <input type=HIDDEN name="E01YYYC004" value="">
  <input type=HIDDEN name="E01YYYC005" value="">
  <input type=HIDDEN name="E01XXXCUN" value="">

	<% if(msgRTC.getE02DPJC00().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC00" value="">  
	<% } %>
	
	<% if(msgRTC.getE02DPJC01().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC01" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC02().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC02" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC03().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC03" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC04().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC04" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC05().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC05" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC06().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC06" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC07().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC07" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC08().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC08" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC09().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC09" value="">
	<% } %>

	<% if(msgRTC.getE02DPJC11().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC11" value="">
	<% } %>

	<% if(msgRTC.getE02DPJC12().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC12" value="">
	<% } %>

	<% if(msgRTC.getE02DPJC13().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC13" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC14().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC14" value="">
	<% } %>
	<% if(msgRTC.getE02DPJC15().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC15" value="">
	<% } %>
	<% if(msgRTC.getE02DPJC16().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC16" value="">
	<% } %>
	
<hr size="4">

   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type=TEXT name="E01CUSCUN" size=10 maxlength=9  value="<%= msgRT.getE01CUSCUN().trim()%>" readonly>
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
   		<input type="text" name="E01CUSNA1" size="27" maxlength="4" value="<%= msgRT.getE01CUSNA1().trim()%>" readonly>
      </td>
    </tr> 
    
    <tr id="trdark"> 
      <td align="right"  >
         <b>Nro. Propuesta :</b>
      </td>
      <td nowrap > 
		<INPUT type="text" name="E01PLPNPR" size="12" maxlength="12" value="<%= msgRT.getE01PLPNPR().trim()%>" readonly>
      </td>
   
      <td align="right"></td> 
      <td nowrap colspan=3></td>
    </tr>
        
  </table>

  <table class="tableinfo">  
      <tr >      
       <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0" id="opTable">	
		
			<TR id="trclear">
			   
			   <TD width="15%" align="right">
                    <DIV >Producto:</DIV>
				</TD>
		        <td nowrap width=20% align="left"> 
		            <input type=TEXT name="E01PLTPRO" size="6" maxlength="4" value="<%= msgList.getE01PLTPRO().trim()%>" readonly>
		        </td>
				
				<TD width="20%" align="left">  
				  Tipo: 
				  <INPUT type="text" name="E01PLTTYP" size="5" maxlength="4" value="<%= msgList.getE01PLTTYP().trim()%>" readonly>	  
	            </TD>		
				<td nowrap width="20%" align="left">
			          Ruta: <input type="text" name="E01PLTCN1" size="5" maxlength="4" value="<%= msgList.getE01PLTCN1().trim()%>" readonly>
				</td>
				
		    </TR>
		  </table>
		</td>
	  </tr>  		 
  </table>

  <table class="tableinfo">  
	<% if(msgRTC.getE02DPJF22().equals("1")){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
					<div>Numero Referencia IBS:</div>
				</td>
				
				<td  width="50%" align="left">
        	        <INPUT type="text" name="E01PLTRE1" size="13" maxlength="12"
        	        <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					value="<%= msgList.getE01PLTRE1().trim()%>">
					<a href="javascript:GetAccount('E01PLTRE1','','10',' ')">
					 <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" >
					</a> 					               
				</td>
			</tr>
		</table>
		</td>
      </tr>
     <% } %> 		 
		   		  
	<% if((msgRTC.getE02DPJF00().equals("1"))||(msgRTC.getE02DPJF01().equals("1"))){%>	
	<tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0"> 
			<tr id="clear">				  
				  <% if(msgRTC.getE02DPJF00().equals("1")){%>
				    <td width="20%">
				      <div align="left">Monto Solicitado:</div>
				    </td>
				 
				    <td width="50%" align="left">
				    <INPUT type="text" name="E01PLTAMN" size="20" maxlength="20" 
				    value="<%= msgList.getE01PLTAMN().trim()%>"
				    <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
				    <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
				    >
				    </td>  
				  <% }; %>
				 
                 <% if(msgRTC.getE02DPJF01().equals("1")){%>
				    <td width="20%" align="left">
				     <div >Moneda :</div>
				    </td>
				 
				    <td width="50%" align="left">
			              <input type="text" name="E01PLTCCY" size="5" maxlength="4" readonly>
					 <% if(!msgRT.getH01FLGWK3().equals("3")){ %>
						<a href="javascript:GetCurrency('E01PLTCCY','<%= userPO.getBank()%>')">
						<img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>	
					 <% }%>    
				   </td>  
				    
				  <% }; %>
			</tr>
			</table>
			</td>
			</tr>
     <% }; %>
                               
    <%--  xxxx   --%>   		
    <%--  xxxx  --%>

        <% if(msgRTC.getE02DPJF05().equals("1")){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td nowrap align="left" width="20%">Origen de Fondos :</td>

				<td nowrap width="50%" align="left">
			              <input type="text" name="E01PLTORI" size="5" maxlength="4" readonly>
					 <% if(!msgRT.getH01FLGWK3().equals("3")){ %>
			              <a href="javascript:GetCodeCNOFC('E01PLTORI','30')">
			              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
					 <% }%>    
				</td>
			</tr>
		</table>
		</td>
      </tr>
      <% } %>
    
    
    <% if((msgRTC.getE02DPJF04().equals("1"))){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
		   <tr id="trclear">
				
				  <td width="20%" align="right">
					<div align="left">Destino de Fondos :</div>
				  </td>
				
				  <td nowrap width="50%" align="left">
			              <input type="text" name="E01PLTDST" size="5" maxlength="4" readonly>
					 <% if(!msgRT.getH01FLGWK3().equals("3")){ %>
			              <a href="javascript:GetCodeCNOFC('E01PLTDST','38')">
			              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
					 <% }%>    
				   </td> 
			</tr>	
		</table>
		</td>
      </tr>
    <% } %>      
    <% if((msgRTC.getE02DPJC00().equals("1"))){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
		   <tr id="trclear">
					<td width="20%" align="right">
               			<div align="left">Coment. Destino Fondos:</div>
					</td>
				
					<td width="50%" align="left">
					  <TEXTAREA name="E02DPAC00" rows="5" cols="70" value="<%= msgRTC.getE02DPAC00().trim()%>"
						<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}  %>
					  > </TEXTAREA>
       				</td>
			</tr>	
		</table>
		</td>
      </tr>
    <% } %>      
        
   <% if((msgRTC.getE02DPJF07().equals("1"))){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear" >
				<td width="20%" align="left">
					<div >Plazo/Tiempo:</div>
				</td>
				<td width="50%" align="left">
        	          <INPUT type="text" name="E01PLTPLZ" size="4" maxlength="3"
        	        <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					value="<%= msgList.getE01PLTPLZ().trim()%>">
					
					<SELECT name="E01PLTUPL"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""></OPTION>
					<OPTION value="1"
						<%if (msgList.getE01PLTUPL().equals("1")) { out.print("selected"); }%>>Dia(s)</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTUPL().equals("2")) { out.print("selected"); }%>>Mes(es)</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTUPL().equals("3")) { out.print("selected"); }%>>Año(s)</OPTION>
					</SELECT>
				</td>
			</tr>
		</table>
		</td>
      </tr>
<% } %>


   <% if((msgRTC.getE02DPJC01().equals("1"))){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear" >
				<td nowrap align="left" width="20%">Comentarios:</td>
				<td nowrap align="left" width="50%">
				<TEXTAREA name="E02DPAC01" rows="5" cols="70" value="<%= msgRTC.getE02DPAC01().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
				> </TEXTAREA>
				</td>
			</tr>
		</table>
		</td>
      </tr>
    <% } %> 
			


   <% if((msgRTC.getE02DPJC02().equals("1"))){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
      		<tr id="trclear" > 
				
				<td nowrap align="left" width="20%">
					Especifique otra forma de Plazo:
				</td>
				<td nowrap align="left" width="50%">
					<TEXTAREA name="E02DPAC02" rows="5" cols="70" value="<%= msgRTC.getE02DPAC02().trim()%>"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					></TEXTAREA>
				</td>
			</tr>
			
		</table>
		</td>
      </tr>
    <% } %> 

      
	<% if((msgRTC.getE02DPJF06().equals("1"))){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
				<div>Forma de pago - Capital:</div>
				</td>
				<td width="50%" align="left">
				<SELECT name="E01PLTCCA" onchange="selK();"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""></OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTCCA().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTCCA().equals("3")) { out.print("selected"); }%>>Bimestral</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTCCA().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
					<OPTION value="5"
						<%if (msgList.getE01PLTCCA().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
					<OPTION value="6"
						<%if (msgList.getE01PLTCCA().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
					<OPTION value="7"
						<%if (msgList.getE01PLTCCA().equals("7")) { out.print("selected"); }%>>Irregular</OPTION>	
					<OPTION value="8"
						<%if (msgList.getE01PLTCCA().equals("8")) { out.print("selected"); }%>>Vencimiento</OPTION>
				</SELECT>
				</td>
			</tr>
		</table>
		</td>
      </tr>
    <% } %>


	<% if((msgRTC.getE02DPJC03().equals("1"))){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td nowrap align="left" width="20%">Comentarios:</td>
				<td nowrap align="left" width="50%">
				<TEXTAREA name="E02DPAC03" rows="5" cols="70" value="<%= msgRTC.getE02DPAC03().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
				></TEXTAREA>
				</td>
			</tr>
		</table>
		</td>
      </tr>
    <% } %>


	<% if(msgRTC.getE02DPJC04().equals("1")){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td nowrap align="left" width="20%">
				Especifique otra forma Pago Cap.:</td>
				<td nowrap align="left" width="50%">
				<TEXTAREA name="E02DPAC04" rows="5" cols="70" value="<%= msgRTC.getE02DPAC04().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
				></TEXTAREA>
				</td>
			</tr>
		</table>
		</td>
      </tr>
   <% } %> 
             
     <% if((msgRTC.getE02DPJF08().equals("1"))){%>
       <TR>
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
				  <div>Forma de pago - Interes:</div>
				</td>
				<td width="50%" align="left">
				  <SELECT name="E01PLTCIN" 
				   <% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""></OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTCIN().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTCIN().equals("3")) { out.print("selected"); }%>>Bimestral</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTCIN().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
					<OPTION value="5"
						<%if (msgList.getE01PLTCIN().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
					<OPTION value="6"
						<%if (msgList.getE01PLTCIN().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
					<OPTION value="7"
						<%if (msgList.getE01PLTCIN().equals("7")) { out.print("selected"); }%>>Irregular</OPTION>
					<OPTION value="8"
						<%if (msgList.getE01PLTCIN().equals("8")) { out.print("selected"); }%>>Vencimiento</OPTION>	
				  </SELECT>
				 </td>
			</tr>
		</table>
		</td>
      </tr> 
     <% } %>
				
     <% if((msgRTC.getE02DPJC05().equals("1"))){%>
       <TR>
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td nowrap align="left" width="20%">Comentarios:</td>
				<td nowrap align="left" width="50%">
				  <TEXTAREA name="E02DPAC05" rows="5" cols="70"  value="<%= msgRTC.getE02DPAC05().trim()%>"
				  <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
				  ></TEXTAREA>
				</td>
			</tr>
		</table>
		</td>
      </tr> 
     <% } %>
      
 <% if(msgRTC.getE02DPJF17().equals("1")){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
					<div>Cantidad Cuotas Plan 1:</div>
				</td>
				<td align="left" width="10%">
        	        <INPUT type="text" name="E01PLTNC1" size="4" maxlength="3"
        	        <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					value="<%= msgList.getE01PLTNC1().trim()%>">               
				</td>
				<td nowrap align="right" width="30%">
					<div>Frecuencia Cuotas Plan 1:</div>
				</td>
				<td align="left" width="10%">
				        
				<SELECT name="E01PLTFPX"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>>
					<OPTION value=""></OPTION>
					<%if (msgRTC.getE02PLJMAT().substring(0,1).equals("1") ) { %>
					<OPTION value="2"
						<%if (msgList.getE01PLTFPX().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
					<% } %>
					<%if (msgRTC.getE02PLJMAT().substring(1,2).equals("1") ) { %>
					<OPTION value="3"
						<%if (msgList.getE01PLTFPX().equals("3")) { out.print("selected"); }%>>Bimestral</OPTION>
					<% } %>
					<%if (msgRTC.getE02PLJMAT().substring(2,3).equals("1") ) { %>
					<OPTION value="4"
						<%if (msgList.getE01PLTFPX().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
					<% } %>
					<%if (msgRTC.getE02PLJMAT().substring(3,4).equals("1") ) { %>
					<OPTION value="5"
						<%if (msgList.getE01PLTFPX().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
					<% } %>
					<%if (msgRTC.getE02PLJMAT().substring(4,5).equals("1") ) { %>
					<OPTION value="6"
						<%if (msgList.getE01PLTFPX().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
					<% } %>
					<OPTION value="7"
						<%if (msgList.getE01PLTFPX().equals("7")) { out.print("selected"); }%>>Irregular</OPTION>
					<OPTION value="8"
						<%if (msgList.getE01PLTFPX().equals("8")) { out.print("selected"); }%>>Al
					Vencimiento</OPTION>

				</SELECT></td>
			</tr>
		</table>
		</td>
      </tr>
  <% } %> 
     
   <% if(msgRTC.getE02DPJF18().equals("1")){%>
      <tr> 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">

				<td width="20%" align="left">
					<div align="left">Cantidad Cuotas Extraordinarias:</div>
				</td>
				<td width="15%" align="left">
				
				<INPUT type="text" name="E01PLTNC2" size="4" maxlength="3"
					<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%>
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					value="<%= msgList.getE01PLTNC2().trim()%>"></td>
				
				<td align="right" width="20%">
					<div align="right">Frecuencia Cuotas Extraordinarias:</div>
				</td>
				<td align="left" width="15%">
    				<SELECT name="E01PLTFPY"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""></OPTION>
						<%if (msgRTC.getE02PLJMAT().substring(0,1).equals("1") ) { %> 
					<OPTION value="2"
						<%if (msgList.getE01PLTFPY().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
						<% } %>
						<%if (msgRTC.getE02PLJMAT().substring(1,2).equals("1") ) { %> 
					<OPTION value="3"
						<%if (msgList.getE01PLTFPY().equals("3")) { out.print("selected"); }%>>Bimestral</OPTION>
						<% } %>
						<%if (msgRTC.getE02PLJMAT().substring(2,3).equals("1") ) { %> 
					<OPTION value="4"
						<%if (msgList.getE01PLTFPY().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
						<% } %>
						<%if (msgRTC.getE02PLJMAT().substring(3,4).equals("1") ) { %> 
					<OPTION value="5"
						<%if (msgList.getE01PLTFPY().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
						<% } %>
						<%if (msgRTC.getE02PLJMAT().substring(4,5).equals("1") ) { %> 
					<OPTION value="6"
						<%if (msgList.getE01PLTFPY().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
						<% } %>
					<OPTION value="7"
						<%if (msgList.getE01PLTFPY().equals("7")) { out.print("selected"); }%>>Irregular</OPTION>	
					<OPTION value="8"
						<%if (msgList.getE01PLTFPY().equals("8")) { out.print("selected"); }%>>Al Vencimiento</OPTION>
					
				</SELECT>
				</td>
			</tr>
		</table>
		</td>
      </tr>
      <% } %> 

       <% if(msgRTC.getE02DPJF23().equals("1")){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				  <td width="20%" align="left">
					  <div>Tipo de Fianza:</div>
				   </td>
		           
				   <td align="left" width="30%">
				      	<INPUT type="text" name="E01PLTTFO" size="4" maxlength="3"
						value="<%= msgList.getE01PLTTFO().trim()%>"
						<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>>
					     
					     <% if(!msgRT.getH01FLGWK3().equals("3")) {%>
		  				  <A href="javascript:GetCodeDescCNOFC('E01PLTTFO','E01PLTTF1','PF')">
		  					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
		  				  </A>
					    <% } %>
					   
					</td>

					<td nowrap align="right" width="20%">
					     <INPUT type="text" name="E01PLTTF1" size="35" maxlength="35" value="<%= msgList.getE01PLTTF1().trim()%>" readonly> 
					</td>
					
					<td align="left" width="30%">
					</td>
			</tr>
		</table>
		</td>
      </tr>
      <% } %>


            
   	<% if(msgRTC.getE02DPJC06().equals("1")){%>
   	   <tr>
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td nowrap align="left" width="20%">
				Especifique Forma Pago Int.:</td>
				<td nowrap align="left" width="50%">
				<TEXTAREA name="E02DPAC06" rows="5" cols="70" value="<%= msgRTC.getE02DPAC06().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
				></TEXTAREA>
				</td>
			</tr>
		</table>
		</td>
      </tr>
     <% } %> 


	<% if(msgRTC.getE02DPJF09().equals("1")){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
					<div>Forma de Cobro Interes:</div>
				</td>
				<td width="50%" align="left">
				<SELECT name="E01PLTCBI"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""></OPTION>
					<OPTION value="1"
						<%if (msgList.getE01PLTCBI().equals("1")) { out.print("selected"); }%>>Anticipado</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTCBI().equals("2")) { out.print("selected"); }%>>Vencimiento</OPTION>
				</SELECT></td>
			</tr>
		</table>
		</td>
      </tr>
     <% } %> 


	<% if(msgRTC.getE02DPJC11().equals("1")){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
				  <div>Descripcion Mercancia:</div>
				</td>
				
				<td width="50%" align="left">
				  <TEXTAREA name="E02DPAC11" rows="5" cols="70" value="<%= msgRTC.getE02DPAC11().trim()%>"
				  <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}  %>
				  > </TEXTAREA>
				</td>
			</tr>
		</table>
		</td>
      </tr>
      <% } %>
      

	 <% if(msgRTC.getE02DPJC12().equals("1")){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">

				 <% if(msgList.getE01PLTTYP().equals("CCBE")){%>
				<div>Forma de Pago al Banco:</div>
				<% } else {; %>
				<div>Forma Pago(%):</div>
			      <% } %>
				
				</td>
				
				<td width="50%" align="left">
				  <TEXTAREA name="E02DPAC12" rows="5" cols="70" value="<%= msgRTC.getE02DPAC12().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}  %>
				> </TEXTAREA>
				</td>
			</tr>
		</table>
		</td>
      </tr>
     <% } %> 

   <% if(msgRTC.getE02DPJC13().equals("1")){%>				
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
				 <% if(msgList.getE01PLTTYP().equals("CCBE")){%>
				<div>Forma de Pago al Beneficiario:</div>
				<% } else {; %>
				<div>Al Beneficiario:</div>
			     <% } %> 
				</td>
				<td width="50%" align="left">
				   <TEXTAREA name="E02DPAC13" rows="5" cols="70" value="<%= msgRTC.getE02DPAC13().trim()%>"
				    <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}  %>
				    > </TEXTAREA>
				</td>
			</tr>
		</table>
		</td>
      </tr>
    <% } %>
         
       <% if((msgRTC.getE02DPJF10().equals("1"))||(msgRTC.getE02DPJF11().equals("1"))){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
    		  <% if(msgRTC.getE02DPJF10().equals("1")){%>
				<td width="20%" align="left">
					<div>Tasa Propuesta:</div>
				</td>
				<td width="15%" align="left">
				   <INPUT type="TEXT" name="E01PLTRTE" size="12" maxlength="10"
					value="<%= msgList.getE01PLTRTE().trim()%>"
					<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					>
				</td>
				<% }; %>
				
    		  <% if(msgRTC.getE02DPJF11().equals("1")){%>
				<td nowrap width="15%" align="left">
                	<div>Tasa Cliente/Producto :</div>
				</td>
				<td nowrap width="20%" align="left">
				    <INPUT type="TEXT" name="E01XXXRTE" size="14" maxlength="9"
					value="<%= msgList.getE01XXXRTE().trim()%>" readonly>
				</td>
				<% } else {; %>
				<td nowrap width="15%" align="left">
				</td>
				<td nowrap width="20%" align="left">
				</td>
			<% }; %>
			</tr>
		</table>
		</td>
      </tr>
     <% }; %> 
      
     <% if(msgRTC.getE02DPJF12().equals("1")){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
					<div>Comision Apertura:</div>
				</td>
				<td width="50%" align="left">
				   <INPUT type="TEXT" name="E01PLTCOA" size="10" maxlength="9"
					value="<%= msgList.getE01PLTCOA().trim()%>"
					<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					>
				</td>
			</tr>
		</table>
		</td>
      </tr>
     <% }; %> 
      
  <% if(msgRTC.getE02DPJF13().equals("1")){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
					<div>Comision Utilizacion:</div>
				</td>
				<td width="50%" align="left">
				   <INPUT type="TEXT" name="E01PLTCOU" size="10" maxlength="9"
					value="<%= msgList.getE01PLTCOU().trim()%>"
					<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					>
				</td>
			</tr>
		</table>
		</td>
      </tr>
    <% }; %> 
      
  <% if(msgRTC.getE02DPJF14().equals("1")){%>  
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
					<div>Comision Aceptacion:</div>
				</td>
				<td width="50%" align="left">
				   <INPUT type="TEXT" name="E01PLTCOC" size="10" maxlength="9"
					value="<%= msgList.getE01PLTCOC().trim()%>"
					<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					>
				</td>
			</tr>
		</table>
		</td>
      </tr>
<% }; %>  
      
    <% if(msgRTC.getE02DPJC07().equals("1")){%>    
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
					<div> Comisión o Girado:</div>
				</td>
				<td width="50%" align="left">
				  <TEXTAREA name="E02DPAC07" rows="5" cols="70" value="<%= msgRTC.getE02DPAC07().trim()%>"
				 <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
				 >
				 </TEXTAREA>
				</td>				
			</tr>
		</table>
		</td>
      </tr>
    <% } %>  
      
   <% if(msgRTC.getE02DPJC08().equals("1")){%>
      <tr > 
        <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
					<div>Fuente de Repago:</div>
				</td>
				
				<td width="50%" align="left">
				 <TEXTAREA name="E02DPAC08" rows="5" cols="70" value="<%= msgRTC.getE02DPAC08().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
				>
				</TEXTAREA>
				</td>
			</tr>
		</table>
		</td>
      </tr>
    <% } %>  
      
      
	<% if((msgRTC.getE02DPJF16().equals("1"))){%>
	<tr>
       <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="20%" align="left">
					<div>Garantía:</div>
				</td>
				<td width="50%" align="left">
				<SELECT name="E01PLTGAR"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""
						<%if (msgList.getE01PLTGAR().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="0"
						<%if (msgList.getE01PLTGAR().equals("0")) { out.print("selected"); }%>>Sin Garantía</OPTION>
					<OPTION value="1"
						<%if (msgList.getE01PLTGAR().equals("1")) { out.print("selected"); }%>>Garantía</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTGAR().equals("2")) { out.print("selected"); }%>>Aval</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTGAR().equals("3")) { out.print("selected"); }%>>Garantía/Aval</OPTION>
					<OPTION value="5"
						<%if (msgList.getE01PLTGAR().equals("5")) { out.print("selected"); }%>>Fianza</OPTION>	
					<OPTION value="6"
						<%if (msgList.getE01PLTGAR().equals("6")) { out.print("selected"); }%>>Garantía-Fianza</OPTION>	
					<OPTION value="4"
						<%if (msgList.getE01PLTGAR().equals("4")) { out.print("selected"); }%>>Garantía/Co-Solicitante</OPTION>	
				</SELECT></td>
			</tr>
		</table>
		</td>
	  </tr>		
	<% }; %>	
	<% if((msgRTC.getE02DPJC09().equals("1"))){%>
	<tr>
       <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
					<td nowrap align="left" width="20%">
						<div>Comentarios:</div>
					</td>
					<td nowrap align="left" width="50%">
						<TEXTAREA name="E02DPAC09" rows="5" cols="70" value="<%= msgRTC.getE02DPAC09().trim()%>"
						<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
						>
						</TEXTAREA>
					</td>
			</tr>
		</table>
		</td>
	  </tr>		
	<% }; %>	
		      
	 <% if(msgRTC.getE02DPJF21().equals("1")){%>
	<tr>
		<td width="1126">
	   		<table class="tableinfo">
    		<tr>
    			<TD>
    				<h4>Numero de Cuenta</h4>      
		    	</TD>
		    </tr>    
		    <tr> 
    			<td nowrap> 
        			<table cellpadding=2 cellspacing=0 width="100%" border="0">
          				<tr id="trdark"> 
          				   <td nowrap width="15%"> 
              					<div align="center">Referencia</div>
				            </td>
          				  
            				<td nowrap width="33%"> 
              					<div align="center"></div>
            				</td>
            				<td nowrap width="15%"> 
              					<div align="center"></div>
            				</td>
				            <td nowrap width="22%"> 
              					<div align="center"></div>
            				</td>
				            <td nowrap> 
             					<div align="center"></div>
				            </td>
						</tr>
 
 
 						<tr id="trclear">
						   <td nowrap width="10%" > 
              					<div align="center"> 
                					<input type="text" name="E01PLTCCR" size="12" maxlength="12"  value="<%= msgList.getE01PLTCCR().trim()%>"
                 						oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].value,'',document.forms[0].E01CUSCUN.value,'','RT'); return false;">
								</div>
				           </td>
				           	<td nowrap width="33%"> 
              					<div align="center"></div>
            				</td>
            				<td nowrap width="15%"> 
              					<div align="center"></div>
            				</td>
				            <td nowrap width="22%"> 
              					<div align="center"></div>
            				</td>
				            <td nowrap> 
             					<div align="center"></div>
				            </td>
				           
 						</tr>
 						
						<tr id="trdark"> 
            				<td nowrap width="15%" > 
              					<div align="center" nowrap> 
                  					<input type="hidden" name="E01PAGOPE" value="<%= msgList.getE01PAGOPE().trim()%>" size="3" maxlength="3">
                  					<input type="hidden" name="E01PLTGCR" value="<%= msgList.getE01PLTGCR().trim()%>">
                  					<input type="hidden" name="E01PAGCON" size="25" maxlength="25" readonly value="<%= msgList.getE01PAGCON().trim()%>"
									oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01PLPBNK.value,'','E01PLTGCR','E01PAGOPE','<%= msgList.getE01PLTACD().trim()%>'); return false;">
								</div>
				            </td>
				            
            				<td nowrap width="15%" > 
              					<div align="center"> 
                  					<input type="hidden" name="E01PLPBNK"  value="<%= msgRT.getE01PLPBNK().trim()%>" size="2" maxlength="2">
				 				</div>
							</td>
							
            				<td nowrap width="22%" > 
                 				<div align="center"> 
                					<input type="hidden" name="E01PLPBRN" size="3" maxlength="3" value="<%= msgRT.getE01PLPBRN().trim()%>"
                 						oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01PLPBNK.value,'','','',''); return false;"> 
				               </div>
				            </td>
            				<td nowrap width="14%" > 
              					<div align="center"> 
                					<input type="hidden" name="E01PLTCCY99" size="3" maxlength="3" value="<%= msgList.getE01PLTCCY().trim()%>"
						                 oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01PLPBNK.value,'','','',''); return false;">
								</div>
							</td>
			          </tr>
			      </table>
			   </td>  
			</tr>
		</table>  
	   </td>
	  </tr> 		     				
	<% } %>
						
	   
	<% if(msgRTC.getE02DPJF20().equals("1")){%>
	<tr>
		<td width="1126">
	   		<table class="tableinfo">
    		<tr>
    		  
   			  <TD nowrap width="15%" align="right">
   				<h4>Linea Credito </h4>      
	    	  </TD>
	    	  
              <td nowrap width="20%" align="left"> 
                <input type="text" name="E01PLTRAC" size="13" maxlength="12" value="<%= msgList.getE01PLTRAC().trim()%>">
                <input type="text" name="E01PLTLNU" size="5" maxlength="4" value="<%= msgList.getE01PLTLNU().trim()%>">
                <a href="javascript:GetCreditLine('E01PLTLNU',document.forms[0].E01PLTRAC.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0" >
                </a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0"  > 
              </td>
              
              <td width="20%" align="right"></td>
			  <td width="45%" align="left"></td>
		    </tr>    
			</table>		
		</td>
	</tr>
	<% } %>       
    
</table>	     
 
 <% if(msgRTC.getE02DPJF19().equals("1")){%>    
    <h3 align="center">Comisiones</h3>
	<TABLE id="mainTable2" class="tableinfo">
	<TD NOWRAP width="100%">
		<TABLE id="headTable2">
			<tr id="trdark">
				<th align=CENTER nowrap width="10%">
				<div align="center">Comision o Impuesto</div>
				</th>
				<th align=CENTER nowrap width="5%">
				<div align="left">Fac</div>
				</th>
				<th align=CENTER nowrap width="10%">
				<div align="center">Monto</div>
				</th>
				<th align=CENTER nowrap width="5%">
				<div align="right">Fre</div>
				</th>
				<th align=CENTER nowrap width="10%">
				<div align="left">Minimo</div>
				</th>
				<th align=CENTER nowrap width="10%">
				<div align="left">Maximo</div>
				</th>
				<th align=CENTER nowrap width="5%">
				<div align="right">Mda</div>
				</th>
				<th align=CENTER nowrap width="15%">
				<div align="right">Cta/Contable</div>
				</th>
				<th align=CENTER nowrap width="5%">
				<div align="right">Iva</div>
				</th>
			</tr>
		</TABLE>
		
		<div id="dataDiv2" class="scbarcolor">
		<table id="dataTable2">
		
    
	    <tr id="trclear">
				<td nowrap width="10%" align="left">
		            <input type=TEXT name="E01COMDE1" size="30" maxlength="30" "READONLY" 
		            value="<%= msgList.getE01COMDE1().trim()%>"
		            >
				</td>
				
				<td nowrap width="5%"> 
				    <input type=TEXT name="E01COMFA1" size="2" maxlength="1" 
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            value="<%= msgList.getE01COMFA1().trim()%>"
		            >
				</td>
				
				<td nowrap width="10%">
		            <input type=TEXT name="E01COMAM1" size="17" maxlength="17" 
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					value="<%= msgList.getE01COMAM1().trim()%>"
		            >
				</td>
				
				<td nowrap width="5%">
	      	      <input type="text" name="E01COMAP1" size="2" maxlength="1" 
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            value="<%= msgList.getE01COMAP1().trim()%>"
		            >
				</td>
				<td nowrap width="10%">
	      	      <input type="text" name="E01COMMN1" size="17" maxlength="17" 
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            value="<%= msgList.getE01COMMN1().trim()%>"
		            >
				</td>
				<td nowrap width="10%">
	      	      <input type="text" name="E01COMMX1" size="17" maxlength="17" 
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            value="<%= msgList.getE01COMMX1().trim()%>"
		            >
				</td>
				<td nowrap width="5%">
	      	      <input type="text" name="E01COMSG1" size="2" maxlength="1" 
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            value="<%= msgList.getE01COMSG1().trim()%>"
		            >
				</td>
				<td nowrap width="15%">
	      	      <input type="text" name="E01COMGL1" size="13" maxlength="12" 
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            value="<%= msgList.getE01COMGL1().trim()%>"
		            >
				</td>
				<td nowrap width="5%">
	      	      <input type="text" name="E01COMIV1" size="2" maxlength="1" 
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            value="<%= msgList.getE01COMIV1().trim()%>"
		            >
				</td>
			</tr>
	    
	    </TABLE>	    	    
	</TD>
</TABLE>


<SCRIPT language="JavaScript">
     function resizeDoc2() {
      var dataT = document.all["dataTable2"];
       adjustEquTables(headTable2, dataTable2, dataDiv2,1,false);
    }
//	showChecked("COLLCOD2");
	resizeDoc2();
	window.onresize=resizeDoc2;     
</SCRIPT> 
<% } %>


<div id="SecAgricola" style="display:none">

<A NAME="agricola">

<h4>Agrícola</h4>
<table class="tableinfo">
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="10%">
				<div align="right">Número Registro:</div>
				</td>
				<td nowrap width="10%" align="left">
		            <input type=TEXT name="E01DPZTNR" size=17 maxlength=16  value="<%= msgList.getE01DPZTNR().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
				<div align="right">Tipo Registro:</div>
				</td>
				<td nowrap width="80%" align="left">
		              <input type="text" name="E01DPZTRE" size="5" maxlength="4" readonly>
				 <% if(!msgRT.getH01FLGWK3().equals("3")){ %>
		              <a href="javascript:GetCodeCNOFC('E01PLTOTH','PT')">
		              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
				 <% }%>    
				</td>
			</tr>
		</table>
		</td>
      </tr>
      
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="10%">
				<div align="right">Fec.Vcto.Reg.:</div>
				</td>
				<td nowrap width="10%" align="left">
				
			<% if(!msgRT.getH01FLGWK3().equals("3")){ %> 
			<a href="javascript:DatePicker(document.forms[0].E01DPZFA1,document.forms[0].E01DPZFA2,document.forms[0].E01DPZFA3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
			<% } %>				
		            
		            
		            <input type=TEXT name="E01DPZFA1" size=3 maxlength=2   value="<%= msgList.getE01DPZFA1().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
		            <input type=TEXT name="E01DPZFA2" size=3 maxlength=2   value="<%= msgList.getE01DPZFA2().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
		            <input type=TEXT name="E01DPZFA3" size=3 maxlength=2  value="<%= msgList.getE01DPZFA3().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
				<div align="right">Municipio:</div>
				</td>
				<td nowrap width="50%">
				<INPUT type="text" name="E01DPZMUN" size="3" maxlength="4" readonly value="<%= msgList.getE01DPZMUN().trim()%>"> 
				<INPUT type="text" name="D01DPZMUN" size="30" maxlength="30" readonly >
				<A href="javascript:GetCodeDescCNOFC('E01DPZMUN','D01DPZMUN','85')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
				</td>
			</tr>
		</table>
		</td>
      </tr>

      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right">Proveniencia Fondos (P/F):</div>
				</td>
				<td nowrap align="left" width="19%">
				<SELECT name="E01DPZPRF"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""
						<%if (msgList.getE01DPZPRF().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="P"
						<%if (msgList.getE01DPZPRF().equals("P")) { out.print("selected"); }%>>P</OPTION>
					<OPTION value="F"
						<%if (msgList.getE01DPZPRF().equals("F")) { out.print("selected"); }%>>F</OPTION>
				</SELECT>
				</td>
				<td nowrap width="28%">
				<div align="right">Plazo del Crédito: 
				<SELECT name="E01DPZPLA"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""
						<%if (msgList.getE01DPZPLA().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="C"
						<%if (msgList.getE01DPZPLA().equals("C")) { out.print("selected"); }%>>C</OPTION>
					<OPTION value="M"
						<%if (msgList.getE01DPZPLA().equals("M")) { out.print("selected"); }%>>M</OPTION>
					<OPTION value="L"
						<%if (msgList.getE01DPZPLA().equals("L")) { out.print("selected"); }%>>L</OPTION>
				</SELECT>
				</div>
								
				</td>
				<td nowrap width="25%">
			
				</td>
			</tr>
		</table>
		</td>
      </tr>
      
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="15%">
				</td>
				<td nowrap width="15%" align="left">
				</td>
				<td nowrap width="15%">
				<div align="right">Nombre Explotación:</div>
				</td>
				<td nowrap width="5%">
		            <input type=TEXT name="E01DPZEXP" size=25 maxlength=25  value="<%= msgList.getE01DPZEXP().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%> >
				</td>
			</tr>
		</table>
		</td>
      </tr>
      
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="1">
			<tr id="trclear">
				<td nowrap width="10%" align="left">
				Rubro
				</td>
				<td nowrap width="35%">
				<div align="left">Uso</div>
				</td>
				<td nowrap width="10%">
				<div align="left">Cant</div>
				</td>
				<td nowrap width="15%">
				<div align="left">Unidad</div>
				</td>
				<td nowrap width="15%">
				<div align="left">SubSector</div>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">

	      	      <input type="text" name="E01DPPRB0" size="5" maxlength="4" value="<%= msgList.getE01DPPRB0().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%> >
	      	      <a href="javascript:GetCodeDescCNOFC('E01DPPRB0','','PR')">
	      	      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR0" size=26 maxlength=26  value="<%= msgList.getE01DPPUR0().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR0" size=4 maxlength=4  value="<%= msgList.getE01DPPCR0().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
					<td nowrap width="15%" align="left">
			              <input type="text" name="E01DPPDR0" size="5" maxlength="4" readonly>
					 <% if(!msgRT.getH01FLGWK3().equals("3")){ %>
			              <a href="javascript:GetCodeCNOFC('E01DPPDR0','PU')">
			              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
					 <% }%>    
					</td>
				<td nowrap width="15%">
	      	      <input type="text" name="E01DPPSB0" size="5" maxlength="4" value="<%= msgList.getE01DPPSB0().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%> s>
	      	      <a href="javascript:GetCodeAuxCNOFC('E01DPPSB0','PS',document.forms[0].E01DPPRB0.value)">
	      	      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">
	      	      <input type="text" name="E01DPPRB1" size="5" maxlength="4" value="<%= msgList.getE01DPPRB1().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
	      	      <a href="javascript:GetCodeDescCNOFC('E01DPPRB1','','PR')">
	      	      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR1" size=26 maxlength=26  value="<%= msgList.getE01DPPUR1().trim()%>">
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR1" size=4 maxlength=4 onKeypress="enterInteger()" value="<%= msgList.getE01DPPCR1().trim()%>">
				</td>
					<td nowrap width="15%" align="left">
			              <input type="text" name="E01DPPCR1" size="5" maxlength="4" readonly>
					 <% if(!msgRT.getH01FLGWK3().equals("3")){ %>
			              <a href="javascript:GetCodeCNOFC('E01DPPCR1','PU')">
			              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
					 <% }%>    
					</td>
				<td nowrap width="15%">
	      	      <input type="text" name="E01DPPSB1" size="5" maxlength="4" value="<%= msgList.getE01DPPSB1().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
	      	      <a href="javascript:GetCodeAuxCNOFC('E01DPPSB1','PS',document.forms[0].E01DPPRB1.value)">
	      	      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">
	      	      <input type="text" name="E01DPPRB2" size="5" maxlength="4" value="<%= msgList.getE01DPPRB2().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
	      	      <a href="javascript:GetCodeDescCNOFC('E01DPPRB2','','PR')">
	      	      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR2" size=26 maxlength=26  value="<%= msgList.getE01DPPUR2().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR2" size=4 maxlength=4 value="<%= msgList.getE01DPPCR2().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
					<td nowrap width="15%" align="left">
			              <input type="text" name="E01DPPDR2" size="5" maxlength="4" readonly>
					 <% if(!msgRT.getH01FLGWK3().equals("3")){ %>
			              <a href="javascript:GetCodeCNOFC('E01DPPDR2','PU')">
			              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
					 <% }%>    
					</td>
				<td nowrap width="15%">
	      	      <input type="text" name="E01DPPSB2" size="5" maxlength="4" value="<%= msgList.getE01DPPSB2().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%> >
	      	      <a href="javascript:GetCodeAuxCNOFC('E01DPPSB2','PS',document.forms[0].E01DPPRB2.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">
	      	      <input type="text" name="E01DPPRB3" size="5" maxlength="4" value="<%= msgList.getE01DPPRB3().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
	      	      <a href="javascript:GetCodeDescCNOFC('E01DPPRB3','','PR')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR3" size=26 maxlength=26  value="<%= msgList.getE01DPPUR3().trim()%>"
		            				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR3" size=4 maxlength=4  value="<%= msgList.getE01DPPCR3().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
					<td nowrap width="15%" align="left">
			              <input type="text" name="E01DPPDR3" size="5" maxlength="4" readonly>
					 <% if(!msgRT.getH01FLGWK3().equals("3")){ %>
			              <a href="javascript:GetCodeCNOFC('E01DPPDR3','PU')">
			              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
					 <% }%>    
					</td>
				<td nowrap width="15%">
	      	      <input type="text" name="E01DPPSB3" size="5" maxlength="4" value="<%= msgList.getE01DPPSB3().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
	      	      <a href="javascript:GetCodeAuxCNOFC('E01DPPSB3','PS',document.forms[0].E01DPPRB3.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">
	      	      <input type="text" name="E01DPPRB4" size="5" maxlength="4" value="<%= msgList.getE01DPPRB4().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
	      	      <a href="javascript:GetCodeDescCNOFC('E01DPPRB4','','PR')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR4" size=26 maxlength=26  value="<%= msgList.getE01DPPUR4().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR4" size=4 maxlength=4 value="<%= msgList.getE01DPPCR4().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
					<td nowrap width="15%" align="left">
			              <input type="text" name="E01DPPDR4" size="5" maxlength="4" readonly>
					 <% if(!msgRT.getH01FLGWK3().equals("3")){ %>
			              <a href="javascript:GetCodeCNOFC('E01DPPDR4','PU')">
			              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
					 <% }%>    
					</td>
				<td nowrap width="15%">
	      	      <input type="text" name="E01DPPSB4" size="5" maxlength="4" value="<%= msgList.getE01DPPSB4().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
	      	      <a href="javascript:GetCodeAuxCNOFC('E01DPPSB4','PS',document.forms[0].E01DPPRB4.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">
	      	      <input type="text" name="E01DPPRB5" size="5" maxlength="4" value="<%= msgList.getE01DPPRB5().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
	      	      <a href="javascript:GetCodeDescCNOFC('E01DPPRB5','','PR')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR5" size=26 maxlength=26  value="<%= msgList.getE01DPPUR5().trim()%>">
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR5" size=4 maxlength=4 onKeypress="enterInteger()" value="<%= msgList.getE01DPPCR5().trim()%>">
				</td>
					<td nowrap width="15%" align="left">
			              <input type="text" name="E01DPPDR5" size="5" maxlength="4" readonly>
					 <% if(!msgRT.getH01FLGWK3().equals("3")){ %>
			              <a href="javascript:GetCodeCNOFC('E01DPPDR5','PU')">
			              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
					 <% }%>    
					</td>
				<td nowrap width="15%">
	      	      <input type="text" name="E01DPPSB5" size="5" maxlength="4" value="<%= msgList.getE01DPPSB5().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
	      	      <a href="javascript:GetCodeAuxCNOFC('E01DPPSB5','PS',document.forms[0].E01DPPRB5.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				</td>
			</tr>
		</table>
		</td>
      </tr>

    </table>
</div>

<div align="center"> 

	<INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader16() %>')">
</div>
          
</form>

</BODY>
</HTML>

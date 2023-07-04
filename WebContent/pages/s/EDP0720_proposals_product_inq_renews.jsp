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

function goCancel(op) {
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

<body>

<h3 align="center">Detalle del Producto<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="proposals_product_inq_renews.jsp,EDP0720"></h3>



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
		            <input type=TEXT name="E01PLTPRO" size="6" maxlength="4" readonly>
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
      <tr >      
       <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0" id="opTable">	
		
			<TR id="trclear">
			   
			   <TD width="15%" align="right">
                    <DIV >Referencia Renovacion:</DIV>
				</TD> 
				       
				<TD width="20%" align="left">
				    <INPUT type="text" name="E01PLTRRE" size="13" maxlength="12" readonly>
				 </TD>  
				
				<TD width="20%" align="right">  
	            </TD>		
				
		   		 <TD  width="45%" align="left">
		   		 </TD>
		   		  
		    </TR>
		  </table>
		</td>
	  </tr>  		 
  </table>

  <table class="tableinfo">  
      <tr >      
       <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0" id="opTable">	
		
		<TR id="trclear">
			   
			   <TD width="15%" align="right">
                  <DIV >Referencia Renovacion:</DIV>
				</TD>
				
				<TD width="20%" align="left">
				    <INPUT type="text" name="E01PLTRREA1" size="13" maxlength="12"         	       
					value="<%= msgRTC.getE01PLTRRE().trim()%>" READONLY  >  
				 </TD>  
				
				<TD width="20%" align="right">
	            </TD>		
				
		   		 <TD  width="45%" align="left">
		   		 </TD> 
		    </TR>
		
		
			<TR id="trclear">
			   
			   <TD width="15%" align="right">
                    <DIV >Fecha Apertura :</DIV>
				</TD>
				
				<TD width="20%" align="left">
            				<input type=TEXT name="E01PLTRA2" value="<%= msgRTC.getE01PLTRA2() %>" size=3 maxlength=2 onKeypress="enterInteger()" readonly> 
            				<input type=TEXT name="E01PLTRA1" value="<%= msgRTC.getE01PLTRA1() %>" size=3 maxlength=2 onKeypress="enterInteger()" readonly>  
            				<input type=TEXT name="E01PLTRA3" value="<%= msgRTC.getE01PLTRA3() %>" size=5 maxlength=4 onKeypress="enterInteger()" readonly>
				 </TD>  
				
				<TD width="20%" align="right">
				     <DIV >Fecha Vencimiento:</DIV>  
	            </TD>		
				
		   		 <TD  width="45%" align="left">
            				<input type=TEXT name="E01PLTRV2" value="<%= msgRTC.getE01PLTRV2() %>" size=3 maxlength=2 onKeypress="enterInteger()" readonly> 
   				            <input type=TEXT name="E01PLTRV1" value="<%= msgRTC.getE01PLTRV1() %>" size=3 maxlength=2 onKeypress="enterInteger()" readonly>  
            				<input type=TEXT name="E01PLTRV3" value="<%= msgRTC.getE01PLTRV3() %>" size=5 maxlength=4 onKeypress="enterInteger()" readonly>
		   		 </TD> 
		    </TR>
		    
		   <TR id="trclear">
			   
			   <TD width="15%" align="right">
                    <DIV >Monto Original :</DIV>
				</TD>
				
				<TD width="20%" align="left">
				     <INPUT type="text" name="E01PLTMNO" size="15" maxlength="15" 
		   		     value="<%= msgRTC.getE01PLTMNO().trim()%>" readonly
				     >
				 </TD>  
				
				<TD width="20%" align="right">
				       <DIV >Saldo Actual :</DIV>
	            </TD>		
				
		   		 <TD  width="45%" align="left">
		   		     <INPUT type="text" name="E01PLTBL1" size="15" maxlength="15" 
		   		     value="<%= msgRTC.getE01PLTBL1().trim()%>" readonly
				     >
		   		 </TD> 
		    </TR>
		    
		  </table>
		</td>
	  </tr>  		 
  </table>
  
  <table class="tableinfo">  
      <tr >      
       <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0" id="opTable">	
		
			<TR id="trclear">
			   <TD width="15%" align="right">
                    <DIV >Nro. de la Cuota a renovar:</DIV>
				</TD>
				
				<TD width="20%" align="left">
				    <INPUT type="text" name="E01PLTQCU" size="3" maxlength="3" 
				    value="<%= msgList.getE01PLTQCU().trim()%>" readonly>
				 </TD>  
				
				<TD width="20%" align="right">
				     <DIV >Monto Principal en Cuota:</DIV>  
	            </TD>		
				
		   		 <TD   width="45%" align="left">
		 	    	<INPUT type="text" name="E01PLTMPC" size="15" maxlength="15" 
		   		     value="<%= msgList.getE01PLTMPC().trim()%>"
				     READONLY
				    >
		   		 </TD> 
		    </TR>
		    
		   <TR id="trclear">
			   <TD width="15%" align="right">
                    <DIV >Amortizacion Propuesta :</DIV>
				</TD>
				
				<TD width="20%" align="left">
				     <INPUT type="text" name="E01PLTAMO" size="20" maxlength="20" 
				    value="<%= msgList.getE01PLTAMO().trim()%>" readonly>
				 </TD>  
				
				<TD width="20%" align="right">
				     <DIV >Saldo a Renovar:</DIV>  
	            </TD>		
				
		   		 <TD   width="45%" align="left">
		 	    	<INPUT type="text" name="E01PLTBTR" size="15" maxlength="15"  value="<%= msgList.getE01PLTBTR().trim()%>" readonly>
		   		 </TD> 
		    </TR>
		    
			<TR id="trclear">
			
			   <TD width="15%" align="right">
                    <DIV >Tasa de Interes :</DIV>
				</TD>
				
				<TD width="20%" align="left">
				    <INPUT type="TEXT" name="E01PLTRAM" size="10" maxlength="9" value="<%= msgList.getE01PLTRAM().trim()%>" readonly>
				  
				 </TD>  
				
				<TD>
	            </TD>		
				
		   		 <TD>
		   		 </TD> 
		    </TR>		   
		   		    
		    <TR id="trclear">
			   <TD width="15%" align="right">
                    <DIV >Plazo/Tiempo :</DIV>
				</TD>
				
				<TD width="20%" align="left">
			         <INPUT type="text" name="E01PLTPLA" size="4" maxlength="3" value="<%= msgList.getE01PLTPLA().trim()%>" readonly>
			         <INPUT type="text" name="E01PLTUPZ" size="8" maxlength="8" value="
			         	<% if (msgList.getE01PLTUPZ().equals("1")) { 
			         			out.print("Dia(s)"); 
			         		} else if (msgList.getE01PLTUPZ().equals("2")){
			         			out.print("Mes(es)"); 			         		
			         		} else if (msgList.getE01PLTUPZ().equals("3")){			         		
			         			out.print("Año(s)"); 
			         		} else {			         		
			         			out.print(""); 
				         	}%>
				      " readonly>					
				 </TD>  
				
				<TD width="20%" align="right">
	            </TD>		
				
		   		 <TD width="45%" align="left">
		   		 </TD>  
		    </TR>
		    
		  </table>
		</td>
	  </tr>  		 
  </table>
 
<div align="center"> 
	<INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader16() %>')">
</div>
          
</form>

</BODY>
</HTML>

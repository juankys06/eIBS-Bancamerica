<html>
<head>
<title>Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>
<jsp:useBean id= "optCN05" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCN19" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id="l0725" class="datapro.eibs.beans.EDP072501Message"  scope="session" />
<jsp:useBean id="EDP072301Help" class="datapro.eibs.beans.JBObjList" scope="session" />
<jsp:useBean id="gaCodeList" class="datapro.eibs.beans.JBListRec" scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body onload=javascript:init()>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }

%>

<script language="JavaScript">


function goConfirm() {

// alert(document.forms[0].ACTION.value);
	if(document.forms[0].ACTION.value == "1"){
      ok = confirm("Esta seguro que desea crear esta Garantía?");
	       document.forms[0].SCREEN.value="280";
	       document.forms[0].submit();
    } else {
		if(document.forms[0].ACTION.value == "2"){
    	  ok = confirm("Esta seguro que desea modificar esta Garantía?");
			document.forms[0].E01DPCTYP.value = "<%= l0725.getE01DPCTYP().trim()%>";
	    	   document.forms[0].SCREEN.value="280";
	    	   document.forms[0].submit();
 	   }
    }  
}

function init()  
{

var boxLength = document.forms[0].E01DPCTYP.length;
var i=0;
var thisvalue;
 
if (document.forms[0].E01DPCCCY != null) {
	document.forms[0].E01DPCCCY.selectedIndex=0;
}

if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPCTYP.options[i].value;
if (thisvalue == document.forms[0].TYPEG.value) {
	document.forms[0].E01DPCTYP.selectedIndex=i;
	break;
   }
}
}

if (document.forms[0].E01DPCCCY != null) {
//var boxLength = document.forms[0].E01DPCCCY.length;
i=0;
var boxLength = document.forms[0].E01DPCCCY.length;
document.forms[0].E01DPCCCY.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPCCCY.options[i].value;
if (thisvalue == document.forms[0].MONEDA.value) {
	document.forms[0].E01DPCCCY.selectedIndex=i;
	break;
   }
}
}
}

var boxLength = document.forms[0].E01DPIICY.length;
document.forms[0].E01DPIICY.selectedIndex=0;

if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPIICY.options[i].value;
if (thisvalue == document.forms[0].MONEDAINS.value) {
	document.forms[0].E01DPIICY.selectedIndex=i;
	break;
   }
}
}

// texto inst. especiales 
document.forms[0].E01INSESP.value = "<%=l0725.getE01INSESP().trim()%>";

// texto Adicional Descripcion  
document.forms[0].E01DPCDE1.value = "<%=l0725.getE01DPCDE1().trim()%>";

	showCollItems(0);

}


function goCancel() {

document.forms[0].SCREEN.value="200";
document.forms[0].submit(); 
	  		  
}


function hidePoliza(value){
 if (value) {
   Poliza.style.display="none";}
 else{
   Poliza.style.display=""; }
}

  builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

function goActionTit(op) {
document.forms[0].optG.value = op;

   switch (op) {
    case 1 :{

	       document.forms[0].SCREEN.value = 410;
	       document.forms[0].submit();

            break;
            }
    case 2 :{

	       document.forms[0].SCREEN.value = 420;
	       document.forms[0].submit();

            break;
            }
    case 3 :{

	       document.forms[0].SCREEN.value = 420;
	       document.forms[0].submit();

    		break;
            }
    case 4 :{

    		break;
            }
   }  

}


function showCollItems(idx) {
var id = idx
  document.forms[0].COLLCODW.value = id;
  var actTab= document.forms[0].CCOD.value;
  for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
  if (actTab != "") {  
  	document.all["dataTable"+actTab].style.display="none";
  }

	showChecked("COLLCOD");
	resizeDoc();
	window.onresize=resizeDoc;
}


</script>

<H3 align="center">Mantenimiento Garantías<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="guarantee_maintenance.jsp, EDP0725"></H3>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0725" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="<%= userPO.getHeader16().trim()%>">
  <INPUT TYPE=HIDDEN NAME="MONEDA" VALUE="<%= l0725.getE01DPCCCY().trim()%>">
  <INPUT TYPE=HIDDEN NAME="MONEDAINS" VALUE="<%= l0725.getE01DPIICY().trim()%>">
  <INPUT TYPE=HIDDEN NAME="TYPEG" VALUE="<%= l0725.getE01DPCTYP().trim()%>">
	<INPUT TYPE=HIDDEN NAME="COLLCODW" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="CCOD" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="optG" VALUE=""> 

  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
      
      
      </td>
    </tr>
  </table>
   
   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01DPCCUN" size="12" readonly value="<%= userPO.getCusNum()%>">
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E01CUSNA1" size="45" readonly value="<%=userPO.getCusName()%>">
      </td>         
    </tr> 
    <tr id="trdark"> 
      <td align="right"  >
         <b>Nro. Propuesta :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01DPCNPR" size="15" readonly value="<%=l0725.getE01DPCNPR().trim()%>">
      </td>
      <td nowrap align="right">
         <b>Secuencia Garantia:</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E01DPCSEQ" size="5" readonly value="<%=userPO.getHeader7()%>">
      </td>
    </tr>  

  </table> 
  
<% if(l0725.getE01DPCCPR().equals("80")){ %>
   <BR>
<TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trclear"> 
      <td align="right"  >
         <b>Número de Cuenta:</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01DPCACC" size="12" readonly value="<%=l0725.getE01DPCACC().trim()%>">
      </td>
      <td align="right"  >
         <b>Line Com/Number:</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E01DPCLNU" size="5" readonly value="<%=l0725.getE01DPCLNU().trim()%>">
      </td>         
    </tr> 
    <tr id="trdark"> 
      <td align="right"  >
         <b>Código Aplicación:</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01DPCACD" size="3" readonly value="<%=l0725.getE01DPCACD().trim()%>">
      </td>
      <td nowrap align="right">
         <b>Estado:</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E01DPCCPR" size="15" readonly value="<%=l0725.getE01DPCCPR().trim()%>">
      </td>
    </tr>  

  </table> 
<% } %>

  <h4><% if(userPO.getHeader16().equals("2")){out.print("Modificar Garantia");} else {out.print("Crear Garantía:"); } %> </h4> 

<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap align="center"> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
         
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Tipo de Garantía:</div>
            </td>
            <td nowrap height="19" width="55%">
      			<INPUT type="text" name="E01DPCTYP" size="5" maxlength="4" value="<%= l0725.getE01DPCTYP().trim()%>"
				<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");}%>>
				<A href="javascript:GetCodeDescCNOFC('E01DPCTYP','E01DPGDES','05')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
				
			<INPUT type="text" name="E01DPGDES" size="35" maxlength="35" value="<%= l0725.getE01DPGDES().trim()%>" 
			 	<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");}%>>
			</td>
			<td nowrap height="19" width="2%"> 
				<div align="right"> </div>
			</td>
		    <td nowrap width="28%" height="19"> 
				<div align="right"> </div>
			</td>
		</tr>
				
 		 <tr id="trclear"> 
            <td nowrap width="5%" height="19" align="right">Identificación Garantia:
         	</td>
          
            <td nowrap height="19" width="55%">	
      	      <input type="text" name="E01DPCIDN" size="5" maxlength="4" value="<%= l0725.getE01DPCIDN().trim()%>"
			  <% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");}%>>	  
      	      <a href="javascript:GetCodeAuxCNOFC('E01DPCIDN','56',document.forms[0].E01DPCTYP.value)">
      	      <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	      <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
			  <INPUT type="text" name="E01XXXIDN" size="35" maxlength="35" value="<%= l0725.getE01XXXIDN().trim()%>" 
			  <% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			</td>
			
			<td nowrap height="19" width="2%"> 
			  <div align="right"></div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
			   <div align="right"> </div>
			</td>	
         </tr>
         
          <tr id="trdark"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Descripción:</div>
            </td>
            <td nowrap height="23" colspan="1" width="372"> 
   		   	    <input type="text" name="E01DPCDES" size="50" maxlength="50" value="<%= l0725.getE01DPCDES().trim()%>"
				<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %>>
			</td>
			
            <td nowrap height="23" colspan="1" width="14">           
            </td>
            
            <td nowrap height="23" colspan="1"> 
            </td>
            
            <td nowrap height="23" colspan="1">   
            </td>
          </tr>

		  <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Adicional Descripcion:</div>
            </td>
            <td nowrap height="23" colspan="1" width="372"> 					
				<TEXTAREA name="E01DPCDE1" rows="4" cols="50" value="<%= l0725.getE01DPCDE1().trim()%>" 
				<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %>>
				</TEXTAREA>						
			</td>
			
            <td nowrap height="23" colspan="1" width="14">           
            </td>
            
            <td nowrap height="23" colspan="1"> 
            </td>
            
            <td nowrap height="23" colspan="1">   
            </td>
          </tr>

          <%--  Banco Exterior no Soporta avaluo del activo
                Ver en el futuro para que esto sea configurable similar a productos en propuesta
                jan/05/06 by JAV
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Avalúo del bien:</div>
            </td>
            <td nowrap height="19" width="55%">
      			<INPUT type="text" name="E01DPCAPA" size="16" maxlength="16" onkeypress="enterDecimal()" value="<%= l0725.getE01DPCAPA().trim()%>";
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			</td>
			
			<td nowrap height="19" width="2%"> 
				<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
				<div align="right"> </div>
			</td>	
          </tr>

 		 <tr id="trclear"> 
            <td nowrap width="5%" height="19" align="right">Fecha de Avalúo:</td>
            
            <td nowrap height="19" width="55%">
			<INPUT type="text" name="E01DPCAPD" size="2" maxlength="2" value="<%= l0725.getE01DPCAPD().trim()%>"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<INPUT type="text" name="E01DPCAPM" size="2" maxlength="2" value="<%= l0725.getE01DPCAPM().trim()%>"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<INPUT type="text" name="E01DPCAPY" size="2" maxlength="2" value="<%= l0725.getE01DPCAPY().trim()%>"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<% if((userPO.getHeader16().equals("1"))||(userPO.getHeader16().equals("2"))){ %> 
			<a href="javascript:DatePicker(document.forms[0].E01DPCAPD,document.forms[0].E01DPCAPM,document.forms[0].E01DPCAPY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
			<% } %>				
			</td>
			
			<td nowrap height="19" width="2%"> 
				<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
				<div align="right"> </div>
			</td>	
         </tr>
         --%>
         
         
         <%--  **********************************************************************************   --%>
         <%--
         
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Codigo del Garante:</div>
            </td>
            <td nowrap height="19" width="55%">
				<input type=TEXT name="E01DPCOWN" size=15 maxlength="15" onKeypress="enterInteger()" 
			        value="<%= l0725.getE01DPCOWN().trim()%>"
			        	<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %>
			        >
        		    <a href="javascript:GetCustomerDescId('E01DPCOWN','E01DPCNAM','E01DPCOWN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a>
Nombre:			<INPUT 	type="text" name="E01DPCNAM" size="45" maxlength="45"
					value="<%= l0725.getE01DPCNAM().trim()%>"
					readonly>
			</td>
						
			<td nowrap height="19" width="2%"> 
				<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
				<div align="right"> </div>
			</td>	
 		 <tr id="trclear"> 
            <td nowrap width="5%" height="19" align="right">Codigo de Moneda:</td>
            
            <td nowrap height="19" width="55%">
			<SELECT name="E01DPCCCY" 
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} %> >
					<%
      					optCN19.initRow();
       					while (optCN19.getNextRow()) {
           					if (optCN19.getFlag().equals("")) {
           						out.println(optCN19.getRecord());
           	   					}
           					}
					%>
			</SELECT>
			</td>
			
			<td nowrap height="19" width="2%"> 
				<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
				<div align="right"> </div>
			</td>	
         </tr>
         
 		 <tr id="trdark"> 
            <td nowrap width="5%" height="19" align="right">Condicion Registro Grtia:</td>
            
            <td nowrap height="19" width="55%">
			<INPUT type="text" name="E01DPCCON" size="10" maxlength="10"
					value="<%= l0725.getE01DPCCON().trim()%>"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
					</td>
			
			<td nowrap height="19" width="2%"> 
			<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>	
         </tr>

          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Fecha Registro Garantia:</div>
            </td>
            <td nowrap height="19" width="55%">
			<INPUT type="text" name="E01DPCCND" size="2" maxlength="2"
					value="<%= l0725.getE01DPCCND().trim()%>"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<INPUT type="text" name="E01DPCCNM" size="2" maxlength="2"
					value="<%= l0725.getE01DPCCNM().trim()%>"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<INPUT type="text" name="E01DPCCNY" size="2" maxlength="2"
					value="<%= l0725.getE01DPCCNY().trim()%>"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<% if((userPO.getHeader16().equals("1"))||(userPO.getHeader16().equals("2"))){ %> 
			<a href="javascript:DatePicker(document.forms[0].E01DPCCND,document.forms[0].E01DPCCNM,document.forms[0].E01DPCCNY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
			<% } %>
			</td>
			
			<td nowrap height="19" width="2%"> 
			<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>
         </tr>
         
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Clase de Garantia:</div>
            </td>
            <td nowrap height="19" width="55%">
			<SELECT name="E01DPCCLS"
					<% if(userPO.getHeader16().equals("3") || userPO.getHeader16().equals("4")){out.print("DISABLED");} %>>
					<OPTION value=""
						<%if (l0725.getE01DPCCLS().equals("")) { out.print("selected"); }%>>Intervenida</OPTION>
					<OPTION value="1"
						<%if (l0725.getE01DPCCLS().equals("1")) { out.print("selected"); }%>>Intervenida</OPTION>
					<OPTION value="2"
						<%if (l0725.getE01DPCCLS().equals("2")) { out.print("selected"); }%>>No Intervenida</OPTION>
					<OPTION value="3"
						<%if (l0725.getE01DPCCLS().equals("3")) { out.print("selected"); }%>>Concreta Intervenida</OPTION>
					<OPTION value="4"
						<%if (l0725.getE01DPCCLS().equals("4")) { out.print("selected"); }%>>Concreta no Intervenida</OPTION>
				</SELECT></td>
			<td nowrap height="19" width="2%"> 
			<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
				<div align="right"> </div>
			</td>	
         </tr>

         
         --%>
         <%--cambio para banco exterior jan/05/06 by jav  **********************************************************************************   --%>
         
 		 <tr id="trclear"> 
            <td nowrap width="5%" height="19" align="right">Número  Garantia:</td>
            
            <td nowrap height="19" width="55%">
			<INPUT type="text" name="E01DPCNRE" size="15" maxlength="15"
					value="<%= l0725.getE01DPCNRE().trim()%>" readonly
			 >
			</td>
			
			<td nowrap height="19" width="2%"> 
			<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>	
         </tr>
 		 
 		 <tr id="trdark"> 
            <td nowrap width="5%" height="19" align="right">Referencia Garantía:</td>
            
            <td nowrap height="19" width="55%">
			<INPUT type="text" name="E01DPCTRE" size="10" maxlength="10"
					value="<%= l0725.getE01DPCTRE().trim()%>"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
					</td>
			
			<td nowrap height="19" width="2%"> 
			<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>	
         </tr>
         

         
          <tr id="trdark"> 
            <td nowrap > 
            </td>
            <td nowrap >               
            Productos a Garantizar:</td>
            <td nowrap ></td>
            <td nowrap >               
            </td>
          </tr>

		<% if(!l0725.getE01DPCP00().equals("")){%>
          <tr id="trclear"> 
            <td nowrap > 
            </td>
            <td nowrap >               
            <INPUT type="text" name="E01DPCP00" size="4" maxlength="5" readonly value="<%= l0725.getE01DPCP00().trim()%>"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
            <INPUT type="text" name="E01DPCPD0" size="35" maxlength="35" readonly value="<%= l0725.getE01DPCPD0().trim()%>"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<INPUT type="text" name="E01DPCA00" size="16" maxlength="16"
					onkeypress="enterDecimal()"
					value="<%= l0725.getE01DPCA00().trim()%>"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %>></td>
            <td nowrap > 
            
			</td>
            <td nowrap >
            </td>
          </tr>
		<%}%>
		<% if(!l0725.getE01DPCP01().equals("")){%>
          <tr id="trdark"> 
            <td nowrap > 
            </td>
            <td nowrap >               
            <INPUT type="text" name="E01DPCP01" size="4" maxlength="5" readonly value="<%= l0725.getE01DPCP01().trim()%>">
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
            <INPUT type="text" name="E01PLTPD1" size="35" maxlength="35" readonly value="<%= l0725.getE01PLTPD1().trim()%>">
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<INPUT type="text" name="E01DPCA01" size="16" maxlength="16"
					onkeypress="enterDecimal()"
					value="<%= l0725.getE01DPCA01().trim()%>"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %>></td>
            <td nowrap > 
			</td>
            <td nowrap >
            </td>
          </tr>
		<%}%>
		<% if(!l0725.getE01DPCP02().equals("")){%>
          <tr id="trclear"> 
            <td nowrap > 
            </td>
            <td nowrap >               
            <INPUT type="text" name="E01DPCP02" size="4" maxlength="5" readonly value="<%= l0725.getE01DPCP02().trim()%>">
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
            <INPUT type="text" name="E01PLTPD2" size="35" maxlength="35" readonly value="<%= l0725.getE01PLTPD2().trim()%>">
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<INPUT type="text" name="E01DPCA02" size="16" maxlength="16"
				onkeypress="enterDecimal()"
				value="<%= l0725.getE01DPCA02().trim()%>"
				<% if(userPO.getHeader16().equals("3")){out.print("readonly");} %>>

			</td>
            <td nowrap > 
			</td>
            <td nowrap >
            </td>
          </tr>
		<%}%>
		<% if(!l0725.getE01DPCP03().equals("")){%>
          <tr id="trdark"> 
            <td nowrap > 
            </td>
            <td nowrap >               
            <INPUT type="text" name="E01DPCP03" size="4" maxlength="5" readonly value="<%= l0725.getE01DPCP03().trim()%>"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
            <INPUT type="text" name="E01PLTPD3" size="35" maxlength="35" readonly value="<%= l0725.getE01PLTPD3().trim()%>"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<INPUT type="text" name="E01DPCA03" size="16" maxlength="16"
					onkeypress="enterDecimal()"
					value="<%= l0725.getE01DPCA03().trim()%>"
					<% if(userPO.getHeader16().equals("3")){out.print("readonly");} %>
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %>></td>
            <td nowrap > 
            
			</td>
            <td nowrap >
            </td>
          </tr>
		<%}%>
		<% if(!l0725.getE01DPCP04().equals("")){%>
          <tr id="trclear"> 
            <td nowrap > 
            </td>
            <td nowrap >               
            <INPUT type="text" name="E01DPCP04" size="4" maxlength="5" readonly value="<%= l0725.getE01DPCP04().trim()%>"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
            <INPUT type="text" name="E01PLTPD4" size="35" maxlength="35" readonly value="<%= l0725.getE01PLTPD4().trim()%>"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<INPUT type="text" name="E01DPCA04" size="16" maxlength="16"
					onkeypress="enterDecimal()"
					value="<%= l0725.getE01DPCA04().trim()%>"
					<% if(userPO.getHeader16().equals("3")){out.print("readonly");} %>
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %>></td>
            <td nowrap > 
            
			</td>
            <td nowrap >
            </td>
          </tr>
		<%}%>

          <tr id="trdark"> 
            <td nowrap > 
            </td>
            <td nowrap >               
			</td>
            <td nowrap > 
			</td>
            <td nowrap >
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap align="right"> 
            Instrucciones Especiales:</td>
            <td nowrap >               
			<TEXTAREA name="E01INSESP" rows="5" cols="56"
					value="<%= l0725.getE01INSESP().trim()%>">
				</TEXTAREA></td>
            <td nowrap > 
			</td>
            <td nowrap >
            </td>
          </tr>


          <tr id="trdark"> 
            <td nowrap > 
            </td>
            <td nowrap >               
			</td>
            <td nowrap > 
			</td>
            <td nowrap >
            </td>
          </tr>


          
        </table>


        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 

            <td nowrap width="100%" align="center">
<TABLE id="mainTable1" class="tableinfo">

	<TD NOWRAP valign="top" width="100%">

		<table class="tbenter" width=100% align=center>
  		<h4 align="center">Titulares</h4>
		<tr>
			<td class=TDBKG width="33%">
			<div align="center"><a href="javascript:goActionTit(1)"><b>Nuevo</b></a></div>
			</td>
			<td class=TDBKG width="33%">
			<div align="center"><a href="javascript:goActionTit(2)"><b>Modificar</b></a></div>
			</td>
			<td class=TDBKG width="33%">
			<div align="center"><a href="javascript:goActionTit(3)"><b>Borrar</b></a></div>
			</td>
		</tr>

</table>
			
<TR>
	<TD NOWRAP width="100%">
		<TABLE id="headTable1">
			<tr id="trdark">
				<th align=CENTER nowrap width="5%">&nbsp;</th>
				<th align=CENTER nowrap width="10%">
				<div align="center">Identificación</div>
				</th>
				<th align=CENTER nowrap width="35%">
				<div align="center">Nombre</div>
				</th>
				<th align=CENTER nowrap width="25%">
				<div align="right">Fecha Registro</div>
				</th>
				<th align=CENTER nowrap width="25%">
				<div align="right">% Participación</div>
				</th>
			</tr>
		</TABLE>
		<div id="dataDiv1" class="scbarcolor">
		<table id="dataTable">
			<%boolean firstTime = true;
			String gaChk = "";
			gaCodeList.initRow();
			int i = 0;

			EDP072301Help.initRow();

			while (gaCodeList.getNextRow()) {
				if (gaCodeList.getFlag().equals("")) {
					gaChk = (firstTime) ? "checked" : "";
					firstTime = false;

					EDP072301Help.getNextRow();
					datapro.eibs.beans.EDP072301Message msgList =
					(datapro.eibs.beans.EDP072301Message) EDP072301Help.getRecord();%>
				<TR>
					<TD ALIGN=LEFT NOWRAP width="5%" >
					<input type="radio" name="COLLCOD" value="<%=EDP072301Help.getCurrentRow()%>" onClick="showCollItems(this.value)" <%=gaChk%>>
					</TD>
					<TD ALIGN=CENTER NOWRAP width="10%">
					<a href="javascript:radioClick('COLLCOD',<%=i%>)"  >
					<DIV NOWRAP><%=gaCodeList.getRecord(0)%> </DIV>
					</a></TD>
					<TD ALIGN=CENTER NOWRAP width="35%">
					<a href="javascript:radioClick('COLLCOD',<%=i%>)" >
					<DIV NOWRAP><%=gaCodeList.getRecord(1)%></DIV>
					</a>
					</TD>
					<TD ALIGN=RIGHT NOWRAP width="25%">
					<a href="javascript:radioClick('COLLCOD',<%=i%>)" >
					<DIV NOWRAP><%=gaCodeList.getRecord(2)%></DIV>
					</a></TD>
					<TD ALIGN=RIGHT NOWRAP width="25%" >
					<a href="javascript:radioClick('COLLCOD',<%=i%>)" >
					<DIV NOWRAP><%=gaCodeList.getRecord(3)%></DIV>
					</a></TD>

				</TR>

			<%i++;
			};
			};%>
		</table>
		</div>

	</TD>
</TR>
</TABLE>

	<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
	int row;
	String code = "";
	String flag = "";
	try {
		row = Integer.parseInt(request.getParameter("ROW"));
	} catch (Exception e) {
		row = 0;
	}
	%> 
	<%} %> 
	<SCRIPT language="JavaScript">

     function resizeDoc() {
     
      var actvTb = document.forms[0].CCOD.value;
      var dataT = document.all["dataTable"+actvTb];
       adjustEquTables(headTable1, dataTable, dataDiv1,1,false);
    }
	showChecked("COLLCOD");
	resizeDoc();
	window.onresize=resizeDoc;
          
	</SCRIPT> 


		<tr id="trdark"> 
            <td nowrap width="100%" align="center">
			</td>
        </tr>

          
	<div id="Seguro" >
   <table class="tableinfo">
        <tr id="trclear"> 
           <td nowrap width="100%" align="center">
			  Ingresa información de Seguros?: 
			  <INPUT type="radio" name="E01DPCINF" value="Y"
			onclick="hidePoliza(false);"
			<%if(l0725.getE01DPCINF().equals("Y")) out.print("checked");%>>
              Sí 
              <INPUT type="radio" name="E01DPCINF" value="N"
			onclick="hidePoliza(true);"
			<%if(!l0725.getE01DPCINF().equals("Y")) out.print("checked");%>>
              No 
			</td>
        </tr>
        </table>
	</div>
              </table>

  
 



   
            
			<div id="Poliza" style="display:none">
   <h4>Datos Seguro</h4>
   <table class="tableinfo">
    <tr > 
      <td nowrap height="306">
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"> Compa&ntilde;&iacute;a:</div>
            </td>
            <td nowrap colspan=2> 
               
              <input type="text" name="E01DPIICN" size="7" maxlength="5" value="<%= l0725.getE01DPIICN().trim()%>" 
 			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
              <a href="javascript:GetCodeDescCNOFC('E01DPIICN','E01DPIICM','24')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>
               <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"  > 
               
            </td>
            <td nowrap> 
                <div align="right">Nombre:</div>
            </td>
            <td nowrap colspan=2>
              <INPUT type="text" name="E01DPIICM" size="35" maxlength="35" value="<%= l0725.getE01DPIICM().trim()%>"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">N&uacute;mero P&oacute;liza:</div>
            </td>
            <td nowrap colspan=2>
                <INPUT type="text" name="E01DPICIN" size="41" maxlength="40" value="<%= l0725.getE01DPICIN().trim()%>"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
                   <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"  >
            </td>
            <td nowrap> 
                <div align="right">Descripci&oacute;n:</div>
            </td>
            <td nowrap colspan=2>
              	<INPUT type="text" name="E01DPIIPD" size="35" maxlength="35" value="<%= l0725.getE01DPIIPD().trim()%>"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Monto:</div>
            </td>
            <td nowrap colspan=2>
            	<INPUT type="text" name="E01DPIIPA" size="15" maxlength="15" value="<%= l0725.getE01DPIIPA().trim()%>" onkeypress="enterDecimal()"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
            	   <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"  >
            </td>
            <td nowrap > 
                <div align="right">Moneda:</div>
            </td>
            <td nowrap colspan=2>
              	<SELECT name="E01DPIICY" 
				<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} %> >
					<%
      					optCN19.initRow();
       					while (optCN19.getNextRow()) {
           					if (optCN19.getFlag().equals("")) {
           						out.println(optCN19.getRecord());
               					}
           					}
					%>
				</SELECT>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Tipo de Seguro :</div>
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01DPITS1" value="1" <% if (l0725.getE01DPITS1().equals("1")) out.print("checked"); %> 
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} %> >
             Incendio
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01DPITS2" value="1" <% if (l0725.getE01DPITS2().equals("1")) out.print("checked"); %> 
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} %> >
             Vehiculo
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01DPITS3" value="1" <% if (l0725.getE01DPITS3().equals("1")) out.print("checked"); %> 
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} %> >
             Transporte
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01DPITS4" value="1" <% if (l0725.getE01DPITS4().equals("1")) out.print("checked"); %> 
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} %> >
             Adicional
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01DPITS5" value="1" <% if (l0725.getE01DPITS5().equals("1")) out.print("checked"); %> 
				<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} %> >
             
             Otros
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha Emisi&oacute;n:</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E01DPIEMD" size="2" maxlength="2" value="<%= l0725.getE01DPIEMD().trim()%>" onKeyPress="enterInteger()"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
              <input type="text" name="E01DPIEMM" size="2" maxlength="2" value="<%= l0725.getE01DPIEMM().trim()%>" onKeyPress="enterInteger()"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
              <input type="text" name="E01DPIEMY" size="2" maxlength="2" value="<%= l0725.getE01DPIEMY().trim()%>" onKeyPress="enterInteger()"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<% if((userPO.getHeader16().equals("1"))||(userPO.getHeader16().equals("2"))){ %> 
                 <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"  >
              <a href="javascript:DatePicker(document.forms[0].E01DPIEMD,document.forms[0].E01DPIEMM,document.forms[0].E01DPIEMY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
			<% } %>                 
            </td>
            <td nowrap> 
                <div align="right">Emitido:</div>
            </td>
              <td nowrap colspan=2>
                <input type="text" name="E01DPIEMB" size="10" maxlength="9" value="<%= l0725.getE01DPIEMB().trim()%>" 
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha Vencimiento:</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E01DPIMDD" size="2" maxlength="2" value="<%= l0725.getE01DPIMDD().trim()%>" onKeyPress="enterInteger()"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
              <input type="text" name="E01DPIMDM" size="2" maxlength="2" value="<%= l0725.getE01DPIMDM().trim()%>" onKeyPress="enterInteger()"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
             <input type="text" name="E01DPIMDY" size="2" maxlength="2" value="<%= l0725.getE01DPIMDY().trim()%>" onKeyPress="enterInteger()"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
			<% if((userPO.getHeader16().equals("1"))||(userPO.getHeader16().equals("2"))){ %> 
                 <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"  >
              <a href="javascript:DatePicker(document.forms[0].E01DPIMDD,document.forms[0].E01DPIMDM,document.forms[0].E01DPIMDY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
			<% } %>
            </td>
            <td nowrap> 
                <div align="right">Excepci&oacute;n:</div>
            </td>
              <td nowrap colspan=2>
              <input type="hidden" name="E01DPIRGK" value="<%= l0725.getE01DPIRGK()%>"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} %> >
              <input type="radio" name="CE01DPIRGK" value="Y" onClick="document.forms[0].E01DPIRGK.value='Y'"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");}  
			  if(l0725.getE01DPIRGK().equals("Y")) out.print("checked");%>>
                Sí 
                <input type="radio" name="CE01DPIRGK" value="N" onClick="document.forms[0].E01DPIRGK.value='N'"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} 
			  if(l0725.getE01DPIRGK().equals("N")) out.print("checked");%>>
                No 
                </td>
          </tr>
          

          <tr id="trdark"> 
            <td nowrap> 
                <div align="right">Aviso Vencimiento:</div>
            </td>
              <td nowrap colspan=2>
                <input type="hidden" name="E01DPICLF" value="<%= l0725.getE01DPICLF()%>"
				<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
                <input type="radio" name="CE01DPICLF" value="Y" onClick="document.forms[0].E01DPICLF.value='Y'"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} 
			  if(l0725.getE01DPICLF().equals("Y")) out.print("checked");%>>
                Sí 
                <input type="radio" name="CE01DPICLF" value="N" onClick="document.forms[0].E01DPICLF.value='N'"
			<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");}
			  if(l0725.getE01DPICLF().equals("N")) out.print("checked");%>>
                No 
             </td>
            <td nowrap> 
                <div align="right">Banco/Endosa:</div>
            </td>
            <td nowrap colspan=2>
                <input type="radio" name="E01DPIEDO" value="1" <%if(l0725.getE01DPIEDO().equals("1")) out.print("checked");
					if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} %> >
                Sí 
                <input type="radio" name="E01DPIEDO" value="2" <%if(l0725.getE01DPIEDO().equals("2")) out.print("checked");
			 if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("disabled");} %> >
                No
            </td>
          </tr>
          <tr id="trclear">
            <TD align="right">Corredora:</TD>
            <TD colspan=2>
               <INPUT type="text" name="E01DPICRR" size="5" maxlength="4" value="<%= l0725.getE01DPICRR().trim()%>"
					<% if((userPO.getHeader16().equals("3"))||(userPO.getHeader16().equals("4"))){out.print("readonly");} %> >
					<% if((userPO.getHeader16().equals("1"))||(userPO.getHeader16().equals("2"))){ %> 
                <a href="javascript:GetCodeDescCNOFC('E01DPICRR','E01DPINCO','2M')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>
                   <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"  >
                  <% } %>
            </TD>
            
            <td nowrap> 
                <div align="right">Nombre :</div>
            </td>
            <td nowrap colspan=2>
				<input type="text" name="E01DPINCO" size="35" maxlength="35" value="<%= l0725.getE01DPINCO().trim()%>" readonly >
            </td>
          </tr>
         </table>         
      </td>
    </tr>
  </table>
  </div>
 </table>


<div align="center">




<TABLE id="Table" class="tableinfo">
	<div align="center"></div>
</table>



	<script language="JavaScript">
	<% if(l0725.getE01DPCINF().equals("Y")) { %>
 	       hidePoliza(false);
	    <%} else { %>
 	    hidePoliza(true);
	<% }; %>
  		  
	<% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPCTYP.focus()");} %>
	<% if(userPO.getHeader16().equals("2")){out.print("document.forms[0].E01DPCIDN.focus()");} %>

	</script>
	<INPUT id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm()"> 
	<INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel()">
</form>
</body>
</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Mantenimiento Producto Ruta Detalle</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP074801Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "optList3"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optLP4" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCN_PP" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCN48" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

	builtNewMenu(pc_limits);
	initMenu();


function init() {
// calcula cantidad de registros para seleccionar

boxLength = document.forms[0].E01DPJRU0.length;
document.forms[0].E01DPJRU0.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJRU0.options[i].value;
if (thisvalue == document.forms[0].RU0.value) {
	document.forms[0].E01DPJRU0.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJRU1.length;
document.forms[0].E01DPJRU1.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJRU1.options[i].value;
if (thisvalue == document.forms[0].RU1.value) {
	document.forms[0].E01DPJRU1.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJRU2.length;
document.forms[0].E01DPJRU2.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJRU2.options[i].value;
if (thisvalue == document.forms[0].RU2.value) {
	document.forms[0].E01DPJRU2.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJRU3.length;
document.forms[0].E01DPJRU3.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJRU3.options[i].value;
if (thisvalue == document.forms[0].RU3.value) {
	document.forms[0].E01DPJRU3.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJRU4.length;
document.forms[0].E01DPJRU4.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJRU4.options[i].value;
if (thisvalue == document.forms[0].RU4.value) {
	document.forms[0].E01DPJRU4.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJRU5.length;
document.forms[0].E01DPJRU5.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJRU5.options[i].value;
if (thisvalue == document.forms[0].RU5.value) {
	document.forms[0].E01DPJRU5.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJRU6.length;
document.forms[0].E01DPJRU6.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJRU6.options[i].value;
if (thisvalue == document.forms[0].RU6.value) {
	document.forms[0].E01DPJRU6.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJRU7.length;
document.forms[0].E01DPJRU7.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJRU7.options[i].value;
if (thisvalue == document.forms[0].RU7.value) {
	document.forms[0].E01DPJRU7.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJRU8.length;
document.forms[0].E01DPJRU8.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJRU8.options[i].value;
if (thisvalue == document.forms[0].RU8.value) {
	document.forms[0].E01DPJRU8.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJRU9.length;
document.forms[0].E01DPJRU9.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJRU9.options[i].value;
if (thisvalue == document.forms[0].RU9.value) {
	document.forms[0].E01DPJRU9.selectedIndex=i;
	break;
   }
}
}

<%--
boxLength = document.forms[0].E01DPJGU1.length;
document.forms[0].E01DPJGU1.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJGU1.options[i].value;
if (thisvalue == document.forms[0].GU1.value) {
	document.forms[0].E01DPJGU1.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJGU2.length;
document.forms[0].E01DPJGU2.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJGU2.options[i].value;
if (thisvalue == document.forms[0].GU2.value) {
	document.forms[0].E01DPJGU2.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJGU3.length;
document.forms[0].E01DPJGU3.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJGU3.options[i].value;
if (thisvalue == document.forms[0].GU3.value) {
	document.forms[0].E01DPJGU3.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJGU4.length;
document.forms[0].E01DPJGU4.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJGU4.options[i].value;
if (thisvalue == document.forms[0].GU4.value) {
	document.forms[0].E01DPJGU4.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJGU5.length;
document.forms[0].E01DPJGU5.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJGU5.options[i].value;
if (thisvalue == document.forms[0].GU5.value) {
	document.forms[0].E01DPJGU5.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJGU6.length;
document.forms[0].E01DPJGU6.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJGU6.options[i].value;
if (thisvalue == document.forms[0].GU6.value) {
	document.forms[0].E01DPJGU6.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJGU7.length;
document.forms[0].E01DPJGU7.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJGU7.options[i].value;
if (thisvalue == document.forms[0].GU7.value) {
	document.forms[0].E01DPJGU7.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJGU8.length;
document.forms[0].E01DPJGU8.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJGU8.options[i].value;
if (thisvalue == document.forms[0].GU8.value) {
	document.forms[0].E01DPJGU8.selectedIndex=i;
	break;
   }
}
}

boxLength = document.forms[0].E01DPJGU9.length;
document.forms[0].E01DPJGU9.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPJGU9.options[i].value;
if (thisvalue == document.forms[0].GU9.value) {
	document.forms[0].E01DPJGU9.selectedIndex=i;
	break;
   }
}
}
--%>

<%--

document.forms[0].E01YYYC001.checked = false
document.forms[0].E01YYYC002.checked = false
document.forms[0].E01YYYC003.checked = false
document.forms[0].E01YYYC004.checked = false
document.forms[0].E01YYYC005.checked = false


var st = document.forms[0].E01DPJMAT.value;
var stx = st.charAt(0)
 if (stx == "1") {
	document.forms[0].E01YYYC001.checked = true
 }
var stx = st.charAt(1)
 if (stx == "1") {
	document.forms[0].E01YYYC002.checked = true
 }
var stx = st.charAt(2)
 if (stx == "1") {
	document.forms[0].E01YYYC003.checked = true
 }
var stx = st.charAt(3)
 if (stx == "1") {
	document.forms[0].E01YYYC004.checked = true
 }
var stx = st.charAt(4)
 if (stx == "1") {
	document.forms[0].E01YYYC005.checked = true
 }
--%> 
}


function goConfirm(opt) {
var c1="0";
var c2="0";
var c3="0";
var c4="0";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
var c5="0";
<%--
  	box = eval("document.forms[0].E01YYYC001");
  	if (box.checked == true) {
		var c1="1";
    }
  	box = eval("document.forms[0].E01YYYC002");
  	if (box.checked == true) {
		var c2="1";
    }
  	box = eval("document.forms[0].E01YYYC003");
  	if (box.checked == true) {
		var c3="1";
    }
  	box = eval("document.forms[0].E01YYYC004");
  	if (box.checked == true) {
		var c4="1";
    }
  	box = eval("document.forms[0].E01YYYC005");
  	if (box.checked == true) {
		var c5="1";
    }
--%>
	var c1="1";
	var c2="1";
	var c3="1";
	var c4="1";
	var c5="1";
    document.forms[0].E01DPJMAT.value=c1+c2+c3+c4+c5;

    //saveMe();
	var op = opt;  	  
	var msg1 = "Esta seguro que desea ";
	var msg2 = " el registro seleccionado";
	document.forms[0].opt.value = op;

	switch (op) {
	case  "1":  //ok = confirm(msg1 + " Ingresar " + msg2);
				//document.forms[0].SCREEN.value="600";
				//alert(document.forms[0].SCREEN.value);
	 break; 
	case  "2":  //ok = confirm(msg1 + " Actualizar " + msg2);
	            //document.forms[0].SCREEN.value="600";
	 break;   
	case  "3":  ok = confirm(msg1 + " Eliminar " + msg2);
	            document.forms[0].SCREEN.value="630";
	 break;
	};
	
	document.forms[0].submit();		  	       	       
}


function goCancel(fmt) {

document.forms[0].SCREEN.value="200";
document.forms[0].submit(); 
	  		  
}
function callLimits() {
var PROD = document.forms[0].E01DPJPRO.value;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0888?SCREEN=100&H01OPECOD=0015&PROD="+PROD;
    	CenterNamedWindow(page,'Information',650,500,2);
}

</script>

</head>
<body onload=javascript:init()>
<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>

<H3 align="center">Mantenimiento Producto Ruta Detalle<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="product_path_maintenance.jsp, EDP0748"></H3>
<P align="center">
<INPUT type="text" name="DSC" size="35" maxlength="35" value="<%= userPO.getHeader10().trim()%>" readonly>
</P>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0748" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <input type=HIDDEN name="opt">
  <INPUT type=HIDDEN name="RU0" value="<%= brnDetails.getE01DPJRU0().trim()%>" >  
  <INPUT type=HIDDEN name="RU1" value="<%= brnDetails.getE01DPJRU1().trim()%>" >  
  <INPUT type=HIDDEN name="RU2" value="<%= brnDetails.getE01DPJRU2().trim()%>" >  
  <INPUT type=HIDDEN name="RU3" value="<%= brnDetails.getE01DPJRU3().trim()%>" >  
  <INPUT type=HIDDEN name="RU4" value="<%= brnDetails.getE01DPJRU4().trim()%>" >
  <INPUT type=HIDDEN name="RU5" value="<%= brnDetails.getE01DPJRU5().trim()%>" >
  <INPUT type=HIDDEN name="RU6" value="<%= brnDetails.getE01DPJRU6().trim()%>" >
  <INPUT type=HIDDEN name="RU7" value="<%= brnDetails.getE01DPJRU7().trim()%>" >
  <INPUT type=HIDDEN name="RU8" value="<%= brnDetails.getE01DPJRU8().trim()%>" >
  <INPUT type=HIDDEN name="RU9" value="<%= brnDetails.getE01DPJRU9().trim()%>" >
    
  <INPUT type=HIDDEN name="GU1" value="<%= brnDetails.getE01DPJGU1().trim()%>" >  
  <INPUT type=HIDDEN name="GU2" value="<%= brnDetails.getE01DPJGU2().trim()%>" >  
  <INPUT type=HIDDEN name="GU3" value="<%= brnDetails.getE01DPJGU3().trim()%>" >  
  <INPUT type=HIDDEN name="GU4" value="<%= brnDetails.getE01DPJGU4().trim()%>" >
  <INPUT type=HIDDEN name="GU5" value="<%= brnDetails.getE01DPJGU5().trim()%>" >
  <INPUT type=HIDDEN name="GU6" value="<%= brnDetails.getE01DPJGU6().trim()%>" >
  <INPUT type=HIDDEN name="GU7" value="<%= brnDetails.getE01DPJGU7().trim()%>" >
  <INPUT type=HIDDEN name="GU8" value="<%= brnDetails.getE01DPJGU8().trim()%>" >
  <INPUT type=HIDDEN name="GU9" value="<%= brnDetails.getE01DPJGU9().trim()%>" >    
  <INPUT type=HIDDEN name="E01DPJMAT" value="<%= brnDetails.getE01DPJMAT().trim()%>" >    
    
  <INPUT type=HIDDEN name="PRO" value="<%= brnDetails.getE01DPJPRO().trim()%>" >  
<h4>  
      Detalle Producto Ruta a 
      <% if(userPO.getHeader16().equals("1")) {out.print(" Ingresar");} %>
      <% if(userPO.getHeader16().equals("2")) {out.print(" Modificar");} %>
      <% if(userPO.getHeader16().equals("5")) {out.print(" Consultar");} %>
      <% if(userPO.getHeader16().equals("3")) {out.print(" Eliminar");} %>
  </h4> 
  
  <table  class="tableinfo" height="235">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap width="800"> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<TBODY>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
							
								<td	nowrap width="20%">
									<div align="right">Producto :</div>
								</td>
								
								<td nowrap width=80% align="left">  
									<INPUT type="text" name="E01DPJPRO" readonly size="5" maxlength="4" value="<%= brnDetails.getE01DPJPRO().trim()%>" >
									<INPUT type="text" name="E01DPJTYP" readonly size="4" maxlength="4" value="<%= brnDetails.getE01DPJTYP().trim()%>" >
							      <% if(userPO.getHeader16().equals("1")) {; %>
	       							<a href="javascript:GetProposalsProducts('E01DPJPRO','E01DPJTYP','','E01XXXDSC')">
	        						<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
							      <% }; %>
									<INPUT type="text" name="E01XXXDSC" readonly size="50" maxlength="50" value="<%= brnDetails.getE01XXXDSC().trim()%>" >
        						</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="25%">
								    <DIV align="right">Código Ruta Primaria</DIV>
								</TD>
								<td nowrap align="left" width="20%">
								<SELECT name="E01DPJRU0" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<%
                						optCN48.initRow();
                						while (optCN48.getNextRow()) {
                    						if (optCN48.getFlag().equals("")) {
                    							out.println(optCN48.getRecord());
                    						}        
                						}                 

    								%>
								</SELECT>
								</td>
								
								<td nowrap width="10%">
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="50%">
								    <DIV align="left">Rutas Alternas </DIV>
								</TD>
								<td nowrap width="50%">
								    <DIV align="left"> </DIV>
								</TD>
							</tr>
							<tr id="trclear">
								<td nowrap width="50%">
								    <DIV align="right">Código Ruta 1 
								<SELECT name="E01DPJRU1"
								    <% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<%
                						optCN48.initRow();
                						while (optCN48.getNextRow()) {
                    						if (optCN48.getFlag().equals("")) {
                    							out.println(optCN48.getRecord());
                    						}        
                						}                 
    								%>
								</SELECT>
								</DIV>
								</td>
								<td nowrap width="50%">
								    <DIV align="right">Código Ruta 2
								<SELECT name="E01DPJRU2"
								    <% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<%
                						optCN48.initRow();
                						while (optCN48.getNextRow()) {
                    						if (optCN48.getFlag().equals("")) {
                    							out.println(optCN48.getRecord());
                    						}        
                						}                 

    								%>
								</SELECT>
								</DIV>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>



				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="50%">
								    <DIV align="right">Código Ruta 3
								<SELECT name="E01DPJRU3"
								    <% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<%
                						optCN48.initRow();
                						while (optCN48.getNextRow()) {
                    						if (optCN48.getFlag().equals("")) {
                    							out.println(optCN48.getRecord());
                    						}        
                						}                 

    								%>
								</SELECT>
								</DIV>
								</td>
								<td nowrap width="50%">
								    <DIV align="right">Código Ruta 4
								<SELECT name="E01DPJRU4"
								    <% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<%
                						optCN48.initRow();
                						while (optCN48.getNextRow()) {
                    						if (optCN48.getFlag().equals("")) {
                    							out.println(optCN48.getRecord());
                    						}
                						}
    								%>
								</SELECT>
								</DIV>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td nowrap width="50%">
								    <DIV align="right">Código Ruta 5
								<SELECT name="E01DPJRU5" 
								    <% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<%
                						optCN48.initRow();
                						while (optCN48.getNextRow()) {
                    						if (optCN48.getFlag().equals("")) {
                    							out.println(optCN48.getRecord());
                    						}        
                						}                 

    								%>
								</SELECT>
								</DIV>
								</td>
								<td nowrap width="50%">
								    <DIV align="right">Código Ruta 6 
								<SELECT name="E01DPJRU6"
								    <% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<%
                						optCN48.initRow();
                						while (optCN48.getNextRow()) {
                    						if (optCN48.getFlag().equals("")) {
                    							out.println(optCN48.getRecord());
                    						}        
                						}                 
    								%>
								</SELECT>
								</DIV>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
								<td nowrap width="50%">
								    <DIV align="right">Código Ruta 7
								<SELECT name="E01DPJRU7"
								    <% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<%
                						optCN48.initRow();
                						while (optCN48.getNextRow()) {
                    						if (optCN48.getFlag().equals("")) {
                    							out.println(optCN48.getRecord());
                    						}        
                						}                 

    								%>
								</SELECT>
								</DIV>
								</td>
								<td nowrap width="50%">
								    <DIV align="right">Código Ruta 8
								<SELECT name="E01DPJRU8" 
								   <% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<%
                						optCN48.initRow();
                						while (optCN48.getNextRow()) {
                    						if (optCN48.getFlag().equals("")) {
                    							out.println(optCN48.getRecord());
                    						}        
                						}                 

    								%>
								</SELECT>
								</DIV>
								</td>

							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>


				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
								<td nowrap width="50%">
								    <DIV align="right">Código Ruta 9
								<SELECT name="E01DPJRU9" 
								    <% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<%
                						optCN48.initRow();
                						while (optCN48.getNextRow()) {
                    						if (optCN48.getFlag().equals("")) {
                    							out.println(optCN48.getRecord());
                    						}
                						}
    								%>
								</SELECT>
								</DIV>
								</td>
								<td nowrap width="50%">
								    <DIV >
								</DIV>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
		    <% if(!userPO.getHeader16().equals("1")) { %>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
									<DIV align="right">Monto Solicitado :</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF00"  value="1" <%if(brnDetails.getE01DPJF00().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF00" value="0" <%if(!brnDetails.getE01DPJF00().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trdark">
							
								<td nowrap width="25%">
									<DIV align="right">Moneda Monto Solicitado :</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF01"  value="1" <%if(brnDetails.getE01DPJF01().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF01" value="0" <%if(!brnDetails.getE01DPJF01().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
									<DIV align="right">Destino de Fondo :</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF04"  value="1" <%if(brnDetails.getE01DPJF04().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF04" value="0" <%if(!brnDetails.getE01DPJF04().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
								    <DIV align="right">Comentario Destino Fondos :</DIV>
			    				</td>
			    				
    							<td nowrap width="20%">
    							    <input type="radio" name="E01DPJC00"  value="1" <%if(brnDetails.getE01DPJC00().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC00" value="0" <%if(!brnDetails.getE01DPJC00().equals("1")) out.print("checked");%>>
              						No
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trdark">
							
								<td nowrap width="25%">
									<DIV align="right">Origen de Fondo :</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF05"  value="1" <%if(brnDetails.getE01DPJF05().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF05" value="0" <%if(!brnDetails.getE01DPJF05().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
									<DIV align="right">Forma Pago Capital (No SC1/SC2):</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF06"  value="1" <%if(brnDetails.getE01DPJF06().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF06" value="0" <%if(!brnDetails.getE01DPJF06().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
								    <DIV align="right">Comentario Forma de Pago Capital :</DIV>
			    				</td>
			    				
    							<td nowrap width="20%">
    							  <input type="radio" name="E01DPJC03"  value="1" <%if(brnDetails.getE01DPJC03().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC03" value="0" <%if(!brnDetails.getE01DPJC03().equals("1")) out.print("checked");%>>
              						No
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trdark">
							
								<td nowrap width="25%">
								</TD>
																
								<td nowrap width="20%">
								</td>
								
								<td nowrap width="25%">
								    <DIV align="right">Comentario Forma de Pago Capital Otros:</DIV>
			    				</td>
			    				
    							<td nowrap width="20%">
    							  <input type="radio" name="E01DPJC04"  value="1" <%if(brnDetails.getE01DPJC04().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC04" value="0" <%if(!brnDetails.getE01DPJC04().equals("1")) out.print("checked");%>>
              						No
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>	
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
									<DIV align="right">Plazo Tiempo :</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF07"  value="1" <%if(brnDetails.getE01DPJF07().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF07" value="0" <%if(!brnDetails.getE01DPJF07().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
								     <DIV align="right">Comentario Plazo Tiempo </DIV>
			    				</td>
			    				
    							<td nowrap width="20%">
    							   <input type="radio" name="E01DPJC01"  value="1" <%if(brnDetails.getE01DPJC01().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC01" value="0" <%if(!brnDetails.getE01DPJC01().equals("1")) out.print("checked");%>>
              						No
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
									<DIV align="right">Forma Pago Interes (No SC1/SC2):</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF08"  value="1" <%if(brnDetails.getE01DPJF08().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF08" value="0" <%if(!brnDetails.getE01DPJF08().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
								    <DIV align="right">Comentario Forma Pago Intereses </DIV>
			    				</td>
			    				
    							<td nowrap width="20%">
    							    <input type="radio" name="E01DPJC05"  value="1" <%if(brnDetails.getE01DPJC05().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC05" value="0" <%if(!brnDetails.getE01DPJC05().equals("1")) out.print("checked");%>>
              						No 
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trdark">
							
								<td nowrap width="25%">
								</TD>
																
								<td nowrap width="20%"> 
								</td>
								
								<td nowrap width="25%">
								    <DIV align="right">Comentario Forma de Pagos Interes Otros :</DIV>
			    				</td>
			    				
    							<td nowrap width="20%">
    							    <input type="radio" name="E01DPJC06"  value="1" <%if(brnDetails.getE01DPJC06().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC06" value="0" <%if(!brnDetails.getE01DPJC06().equals("1")) out.print("checked");%>>
              						No 
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
									<DIV align="right">Forma Cobro Interes :</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF09"  value="1" <%if(brnDetails.getE01DPJF09().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF09" value="0" <%if(!brnDetails.getE01DPJF09().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">

											<DIV align="right">Tasa de Interes Propuesta</DIV>
											</td>
			    				
    							<td nowrap width="20%">
									<input type="radio" name="E01DPJF10"  value="1" <%if(brnDetails.getE01DPJF10().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF10" value="0" <%if(!brnDetails.getE01DPJF10().equals("1")) out.print("checked");%>>
              						No 
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trdark">
							
								<td nowrap width="25%">
									<DIV align="right">Comision o Girado :</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJC07"  value="1" <%if(brnDetails.getE01DPJC07().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC07" value="0" <%if(!brnDetails.getE01DPJC07().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
									<DIV align="right">Fuente de Repago :</DIV>
			    				</td>
			    				
    							<td nowrap width="20%">
									<input type="radio" name="E01DPJC08"  value="1" <%if(brnDetails.getE01DPJC08().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC08" value="0" <%if(!brnDetails.getE01DPJC08().equals("1")) out.print("checked");%>>
              						No 
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
								</TD>
																
								<td nowrap width="20%">
								</td>
								
								<td nowrap width="25%">
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trdark">
							
								<td nowrap width="25%">
									<DIV align="right">Comisi&oacute;n aperturada (CC):</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF12"  value="1" <%if(brnDetails.getE01DPJF12().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF12" value="0" <%if(!brnDetails.getE01DPJF12().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
									<DIV align="right">Comisi&oacute;n Utilizaci&oacute;n (CC) :</DIV>
			    				</td>
			    				
    							<td nowrap width="20%">
									<input type="radio" name="E01DPJF13"  value="1" <%if(brnDetails.getE01DPJF13().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF13" value="0" <%if(!brnDetails.getE01DPJF13().equals("1")) out.print("checked");%>>
              						No 
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>


				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="clear">
							
								<td nowrap width="25%">
									<DIV align="right">Comisi&oacute;n Aceptaci&oacute;n (CC):</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF14"  value="1" <%if(brnDetails.getE01DPJF14().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF14" value="0" <%if(!brnDetails.getE01DPJF14().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
									<DIV align="right">Descripci&oacute;n Mercaderia (CC):</DIV>
			    				</td>
			    				
    							<td nowrap width="20%">
									<input type="radio" name="E01DPJC11"  value="1" <%if(brnDetails.getE01DPJC11().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC11" value="0" <%if(!brnDetails.getE01DPJC11().equals("1")) out.print("checked");%>>
              						No 
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>


				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trdark">
							
								<td nowrap width="25%">
									<DIV align="right">Forma Pago (%) (CC) </DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJC12"  value="1" <%if(brnDetails.getE01DPJC12().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC12" value="0" <%if(!brnDetails.getE01DPJC12().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
									<DIV align="right">Comentario Al Beneficiario (CC) </DIV>
			    				</td>
			    				
    							<td nowrap width="20%">
									<input type="radio" name="E01DPJC13"  value="1" <%if(brnDetails.getE01DPJC13().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC13" value="0" <%if(!brnDetails.getE01DPJC13().equals("1")) out.print("checked");%>>
              						No 
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
								</TD>
																
								<td nowrap width="20%">
								</td>
								
								<td nowrap width="25%">
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trdark">
							
								<td nowrap width="25%">
									<DIV align="right">Cantidad Cuotas</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF17"  value="1" <%if(brnDetails.getE01DPJF17().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF17" value="0" <%if(!brnDetails.getE01DPJF17().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
									<DIV align="right">Cantidad Cuotas Extraordinarias</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF18"  value="1" <%if(brnDetails.getE01DPJF18().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF18" value="0" <%if(!brnDetails.getE01DPJF18().equals("1")) out.print("checked");%>>
              						No 
								</td>
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
									<DIV align="right">Cuenta de pago(Prest)</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF21"  value="1" <%if(brnDetails.getE01DPJF21().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF21" value="0" <%if(!brnDetails.getE01DPJF21().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trdark">
							
								<td nowrap width="25%">
									<DIV align="right">Origen de la Linea(Prest)</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF20"  value="1" <%if(brnDetails.getE01DPJF20().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF20" value="0" <%if(!brnDetails.getE01DPJF20().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
									<DIV align="right">Información Tipo de Fianzas </DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF23"  value="1" <%if(brnDetails.getE01DPJF23().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF23" value="0" <%if(!brnDetails.getE01DPJF23().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trdark">
							
								<td nowrap width="25%">
									<DIV align="right">Garantia /Avales :</DIV>
								</TD>
																
								<td nowrap width="20%">
									<input type="radio" name="E01DPJF16"  value="1" <%if(brnDetails.getE01DPJF16().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJF16" value="0" <%if(!brnDetails.getE01DPJF16().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">
								  <DIV align="right">Comentario Garantia/Aval </DIV>
			    				</td>
			    				
    							<td nowrap width="20%">
    							   <input type="radio" name="E01DPJC09"  value="1" <%if(brnDetails.getE01DPJC09().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPJC09" value="0" <%if(!brnDetails.getE01DPJC09().equals("1")) out.print("checked");%>>
              						No
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
									<DIV align="right">Monto Máximo Permitido:</DIV>
								</TD>
																
								<td nowrap width="20%">
								 <INPUT type="text" name="E01DPJAMN" size="20" maxlength="20" value="<%= brnDetails.getE01DPJAMN().trim()%>" onkeypress=enterDecimal() >
								</td>
								
								<td nowrap width="25%">
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
									<DIV align="right">Lineas para firmas en propuesta: </DIV>
								</TD>
																
								<td nowrap width="20%">
									<SELECT name="E01DPJCMP" >
									<OPTION value=""></OPTION>
									<OPTION value="1"
									<%if (brnDetails.getE01DPJCMP().equals("1")) { out.print("selected"); }%>>Hipotecario</OPTION>
									<OPTION value="2"
									<%if (brnDetails.getE01DPJCMP().equals("2")) { out.print("selected"); }%>>Otras</OPTION>
									<OPTION value="3"
									<%if (brnDetails.getE01DPJCMP().equals("3")) { out.print("selected"); }%>>3- </OPTION>
									<OPTION value="4"
									<%if (brnDetails.getE01DPJCMP().equals("4")) { out.print("selected"); }%>>4- </OPTION>
									</SELECT>
								</td>
								
								<td nowrap width="25%">
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				



				
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<TR id="trclear">
							
								<td nowrap width="25%">
									<DIV align="right">Código Tabla para Formularios:</DIV>
								</TD>
																
								<td nowrap width="20%">
								 <INPUT type="text" name="E01DPJCBE" size="5" maxlength="4" value="<%= brnDetails.getE01DPJCBE().trim()%>"  >
							        <a href="javascript:GetFormsProposals('E01DPJCBE','D01DPJCBE','')">
			    				    <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
								</td>
								
								<td nowrap width="25%">
								     <INPUT type="text" name="D01DPJCBE" size="35" maxlength="35" readonly> 
			    				</td>
			    				
    							<td nowrap width="20%">
			        			</td>								
			        			
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

			<% } %>
			</TBODY>
		</TABLE>
		</td>
    </tr>
    
  </table>
  
  <div align="center"> 
       <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm('<%= userPO.getHeader16() %>')"
         <% if(userPO.getHeader16().equals("5")){out.print("DISABLED");} %>>								
       <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader9().trim() %>')">
  </div>
          
  <script language="JavaScript">
  		  
  		 <% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPJPRO.focus()");} %>
  		 <% if(userPO.getHeader16().equals("2")){out.print("document.forms[0].E01DPJRU0.focus()");} %>
  		  
  </script>
          
</body>
</html>
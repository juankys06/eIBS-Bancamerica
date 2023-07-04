<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Recaudo Documentos
</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="s0706" class="datapro.eibs.beans.EDP070601Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "optList3"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCNPG"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optLP4" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

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

function init()
{
var boxLength = document.forms[0].E01DPQPRD.length;
i = 0;
document.forms[0].E01DPQPRD.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPQPRD.options[i].value;
if (thisvalue == document.forms[0].PRD.value) {
	document.forms[0].E01DPQPRD.selectedIndex=i;
	break;
   }
}
}

var boxLength = document.forms[0].E01DPQREG.length;
i = 0;
document.forms[0].E01DPQREG.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPQREG.options[i].value;
if (thisvalue == document.forms[0].DOC.value) {
	document.forms[0].E01DPQREG.selectedIndex=i;
	break;
   }
}
}

//alert("<%= s0706.getE01DPQPRD().trim()%>");
var boxLength = document.forms[0].E01DPQPRD.length;
i = 0;
document.forms[0].E01DPQPRD.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPQPRD.options[i].value;
if (thisvalue == document.forms[0].PRD.value) {
	document.forms[0].E01DPQPRD.selectedIndex=i;
	break;
   }
}
}



}


function goConfirm(opt) {

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
	//default : alert("something is wrong"); 
	};

	document.forms[0].PRD.value=document.forms[0].E01DPQPRD.value; 

	document.forms[0].submit();		  	       	       
}

function goCancel(fmt) {

document.forms[0].SCREEN.value="150";
document.forms[0].submit(); 
	  		  
}

</script>

</HEAD>

<body onload=javascript:init()>

	<H3 align="center">Mantenimiento 
	 <% if(userPO.getOption().equals("1")) {out.print("Registro Garantia Detalle");} %>
	 <% if(userPO.getOption().equals("2")) {out.print("Rutas Alternas");} %>
		<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="registros_maintenance.jsp, EDP0706"></H3>
	<P align="center">
	<INPUT type="text" name="DSC" size="35" maxlength="35" value="<%= s0706.getE01DPQPRD().trim()%>" readonly></P>
	
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0706" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <input type=HIDDEN name="PRD" value="<%= s0706.getE01DPQPRD().trim()%>">
  <input type=HIDDEN name="DOC" value="<%= s0706.getE01DPQREG().trim()%>">
  
<h4>  
      Detalle 
      <% if(userPO.getOption().equals("1")) {out.print("Registro Garantia a");} %>
	  <% if(userPO.getOption().equals("2")) {out.print("Rutas Alternas a");} %>
      <% if(userPO.getHeader16().equals("1")) {out.print(" Ingresar");} %>
      <% if(userPO.getHeader16().equals("2")) {out.print(" Modificar");} %>
      <% if(userPO.getHeader16().equals("5")) {out.print(" Consultar");} %>
      <% if(userPO.getHeader16().equals("3")) {out.print(" Eliminar");} %>
  </h4> 
  
  <table  class="tableinfo" height="235">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap width="500"> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<TBODY>
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trclear">
								<td	nowrap width="30%">
									<div align="right">Banco :</div>
								</td>
								<td nowrap align="left" width="70%">
									<INPUT type="text" name="E01DPQBNK" 
									onkeypress="enterInteger()"
									size="2" maxlength="2" value="<%= s0706.getE01DPQBNK().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
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
								<td nowrap width="30%">
									<DIV align="right">Código de Producto :</DIV>
								</TD>
								<td nowrap align="left" width="70%"> 
								<select name="E01DPQPRD" 
								<% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLED");} %> >
								<OPTION value=""></OPTION>
             						<%
                						optList3.initRow();
                						while (optList3.getNextRow()) {
                    						if (optList3.getFlag().equals("")) {
                    							out.println(optList3.getRecord());
                    						}        
                						}                 
    								%> 
            						</select></td>
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
								<td	nowrap width="30%">
									<div align="right">Tipo de Producto :</div>
								</td>
								<td nowrap align="left" width="70%">
									<INPUT type="text" name="E01DPQTYP" 
									onkeypress="enterInteger()"
									size="3" maxlength="3" value="<%= s0706.getE01DPQTYP().trim()%>" <% {out.print("readonly");} %>>
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
							<tr id="trdark">
								<td nowrap width="30%">
								    <% if(userPO.getOption().equals("1")) {out.print("Codigo Registro Garantias");} %>
	   							    <% if(userPO.getOption().equals("2")) {out.print("Operaciones Adicionales");} %>
								</td>
								
								<td nowrap align="left" width="70%">
							    <SELECT name="E01DPQREG" 
									<% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLED");} %> >
									<%
                						optCNPG.initRow();
                						int d=1;
                						while (optCNPG.getNextRow()) {
                    						if (optCNPG.getFlag().equals("")) {
                    							out.println(optCNPG.getRecord());
                    							d++;
                    						}        
                						}                 
    								%>
									</SELECT>	
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
								<td nowrap width="30%">
								<DIV align="right">Estado del Registro :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPQEST" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (s0706.getE01DPQEST().equals(" ")) { out.print("selected"); }%>></option>
									<option value="A" 
									   <%if (s0706.getE01DPQEST().equals("A")) { out.print("selected"); }%>>Activo</option>
									<option value="I"
										<%if (s0706.getE01DPQEST().equals("I")) { out.print("selected"); }%>>No Activo</option>
								</SELECT>								
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
							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Codigó Actividad No. 1</DIV>
								</td>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPQAC0" size="3" maxlength="4" 
									value="<%= s0706.getE01DPQAC0().trim()%>" 
									<% if(userPO.getHeader16().equals("3") ||userPO.getHeader16().equals("5")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPQAD0" size="30" maxlength="30" readonly value="<%= s0706.getE01DPQAD0().trim()%>">
										<A href="javascript:GetCodeDescCNOFC('E01DPQAC0','E01DPQAD0','P2')"><IMG src=" <%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
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
								<td nowrap width="30%" height="19">
									<DIV align="right">Actividad aplica a Renovación  :</DIV>
								</td>
								<td nowrap align="left" width="70%">								
									<input type="radio" name="E01DPQRE0" value="1" <%if(s0706.getE01DPQRE0().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
              						Sí 
              						<input type="radio" name="E01DPQRE0" value="0" 	<%if(!s0706.getE01DPQRE0().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
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
							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Codigó Actividad No. 2</DIV>
								</td>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPQAC1" size="3" maxlength="4" 
									value="<%= s0706.getE01DPQAC1().trim()%>" 
									<% if( userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPQAD1" size="30" maxlength="30" readonly value="<%= s0706.getE01DPQAD1().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPQAC1','E01DPQAD1','P2')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
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
								<td nowrap width="30%" height="19">
									<DIV align="right">Actividad aplica a Renovación  :</DIV>
								</td>
								<td nowrap align="left" width="70%">								
									<input type="radio" name="E01DPQRE1" value="1" <%if(s0706.getE01DPQRE1().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
              						Sí 
              						<input type="radio" name="E01DPQRE1" value="0" 	<%if(!s0706.getE01DPQRE1().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
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
							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Codigó Actividad No. 3</DIV>
								</td>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPQAC2" size="3" maxlength="4" 
									value="<%= s0706.getE01DPQAC2().trim()%>" 
									<% if( userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPQAD2" size="30" maxlength="30" readonly value="<%= s0706.getE01DPQAD2().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPQAC2','E01DPQAD2','P2')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
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
								<td nowrap width="30%" height="19">
									<DIV align="right">Actividad aplica a Renovación  :</DIV>
								</td>
								<td nowrap align="left" width="70%">								
									<input type="radio" name="E01DPQRE2" value="1" <%if(s0706.getE01DPQRE2().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
              						Sí 
              						<input type="radio" name="E01DPQRE2" value="0" 	<%if(!s0706.getE01DPQRE2().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
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
							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Codigó Actividad No. 4</DIV>
								</td>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPQAC3" size="3" maxlength="4" 
									value="<%= s0706.getE01DPQAC3().trim()%>" 
									<% if( userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPQAD3" size="30" maxlength="30" readonly value="<%= s0706.getE01DPQAD3().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPQAC3','E01DPQAD3','P2')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
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
								<td nowrap width="30%" height="19">
									<DIV align="right">Actividad aplica a Renovación  :</DIV>
								</td>
								<td nowrap align="left" width="70%">								
									<input type="radio" name="E01DPQRE3" value="1" <%if(s0706.getE01DPQRE3().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
              						Sí 
              						<input type="radio" name="E01DPQRE3" value="0" 	<%if(!s0706.getE01DPQRE3().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
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
							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Codigó Actividad No. 5</DIV>
								</td>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPQAC4" size="3" maxlength="4" 
									value="<%= s0706.getE01DPQAC4().trim()%>" 
									<% if( userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPQAD4" size="30" maxlength="30" readonly value="<%= s0706.getE01DPQAD4().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPQAC4','E01DPQAD4','P2')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
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
								<td nowrap width="30%" height="19">
									<DIV align="right">Actividad aplica a Renovación  :</DIV>
								</td>
								<td nowrap align="left" width="70%">								
									<input type="radio" name="E01DPQRE4" value="1" <%if(s0706.getE01DPQRE4().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
              						Sí 
              						<input type="radio" name="E01DPQRE4" value="0" 	<%if(!s0706.getE01DPQRE4().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
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
							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Codigó Actividad No. 6</DIV>
								</td>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPQAC5" size="3" maxlength="4" 
									value="<%= s0706.getE01DPQAC5().trim()%>" 
									<% if( userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPQAD5" size="30" maxlength="30" readonly value="<%= s0706.getE01DPQAD5().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPQAC5','E01DPQAD5','P2')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
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
								<td nowrap width="30%" height="19">
									<DIV align="right">Actividad aplica a Renovación  :</DIV>
								</td>
								<td nowrap align="left" width="70%">								
									<input type="radio" name="E01DPQRE5" value="1" <%if(s0706.getE01DPQRE5().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
              						Sí 
              						<input type="radio" name="E01DPQRE5" value="0" 	<%if(!s0706.getE01DPQRE5().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
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
							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Codigó Actividad No. 7</DIV>
								</td>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPQAC6" size="3" maxlength="4" 
									value="<%= s0706.getE01DPQAC6().trim()%>" 
									<% if( userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPQAD6" size="30" maxlength="30" readonly value="<%= s0706.getE01DPQAD6().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPQAC6','E01DPQAD6','P2')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
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
								<td nowrap width="30%" height="19">
									<DIV align="right">Actividad aplica a Renovación  :</DIV>
								</td>
								<td nowrap align="left" width="70%">								
									<input type="radio" name="E01DPQRE6" value="1" <%if(s0706.getE01DPQRE6().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
              						Sí 
              						<input type="radio" name="E01DPQRE6" value="0" 	<%if(!s0706.getE01DPQRE6().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
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
							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Codigó Actividad No. 8</DIV>
								</td>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPQAC7" size="3" maxlength="4" 
									value="<%= s0706.getE01DPQAC7().trim()%>" 
									<% if( userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPQAD7" size="30" maxlength="30" readonly value="<%= s0706.getE01DPQAD7().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPQAC7','E01DPQAD7','P2')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
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
								<td nowrap width="30%" height="19">
									<DIV align="right">Actividad aplica a Renovación  :</DIV>
								</td>
								<td nowrap align="left" width="70%">								
									<input type="radio" name="E01DPQRE7" value="1" <%if(s0706.getE01DPQRE7().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
              						Sí 
              						<input type="radio" name="E01DPQRE7" value="0" 	<%if(!s0706.getE01DPQRE7().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
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
							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Codigó Actividad No. 9</DIV>
								</td>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPQAC8" size="3" maxlength="4" 
									value="<%= s0706.getE01DPQAC8().trim()%>" 
									<% if( userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPQAD8" size="30" maxlength="30" readonly value="<%= s0706.getE01DPQAD8().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPQAC8','E01DPQAD8','P2')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
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
								<td nowrap width="30%" height="19">
									<DIV align="right">Actividad aplica a Renovación  :</DIV>
								</td>
								<td nowrap align="left" width="70%">								
									<input type="radio" name="E01DPQRE8" value="1" <%if(s0706.getE01DPQRE8().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
              						Sí 
              						<input type="radio" name="E01DPQRE8" value="0" 	<%if(!s0706.getE01DPQRE8().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
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
							<tr id="trdark">
								<td nowrap width="33%">
								<DIV align="right">Codigó Actividad No. 10 </DIV>
								</td>
								<td nowrap align="left" width="100%">
									<INPUT type="text" name="E01DPQAC9" size="3" maxlength="4" 
									value="<%= s0706.getE01DPQAC9().trim()%>" 
									<% if( userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPQAD9" size="30" maxlength="30" readonly value="<%= s0706.getE01DPQAD9().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPQAC9','E01DPQAD9','P2')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
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
								<td nowrap width="30%" height="19">
									<DIV align="right">Actividad aplica a Renovación  :</DIV>
								</td>
								<td nowrap align="left" width="70%">								
									<input type="radio" name="E01DPQRE9" value="1" <%if(s0706.getE01DPQRE9().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
              						Sí 
              						<input type="radio" name="E01DPQRE9" value="0" 	<%if(!s0706.getE01DPQRE9().equals("1")) {out.print("checked");}%> <% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLE");} %>>
              						No 
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
		<% if(userPO.getOption().equals("1")) { %>
				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Permanencia Propuesta:</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPQONL" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (s0706.getE01DPQONL().equals(" ")) { out.print("selected"); }%>></option>
									<option value="1" 
									   <%if (s0706.getE01DPQONL().equals("1")) { out.print("selected"); }%>>F.Creaci&oacute;n + dias de Vencimiento</option>
									<option value="2"
								       <%if (s0706.getE01DPQONL().equals("2")) { out.print("selected"); }%>>F.Aprobaci&oacute;n + dias Aprovaci&oacute;n</option>
									<option value="3"
									   <%if (s0706.getE01DPQONL().equals("3")) { out.print("selected"); }%>>F.Desembolso + dias Desembolso</option>
								    <option value="4"
									   <%if (s0706.getE01DPQONL().equals("4")) { out.print("selected"); }%>>F.Creaci&oacute;n + dias Vencimiento y Aprobaci&oacute;n </option>
									<option value="5"
									   <%if (s0706.getE01DPQONL().equals("5")) { out.print("selected"); }%>>F.Creaci&oacute;n + dias Vencimiento, Aprobaci&oacute;n Desembolso</option>
									<option value="6"
									   <%if (s0706.getE01DPQONL().equals("6")) { out.print("selected"); }%>>F.Aprobaci&oacute;n + dias Aprobaci&oacute;n y Desembolso</option>								      
								</SELECT>
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
								<td nowrap width="30%">
								<DIV align="right">Dias Vencimiento :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<INPUT type="text" name="E01DPQDVE" 
									onkeypress="enterInteger()"
									size="4" maxlength="4" value="<%= s0706.getE01DPQDVE().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
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
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Dias Aprobaci&oacute;n :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<INPUT type="text" name="E01DPQDAP" 
									onkeypress="enterInteger()"
									size="4" maxlength="4" value="<%= s0706.getE01DPQDAP().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
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
								<td nowrap width="30%">
								<DIV align="right">Dias Desembolso :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<INPUT type="text" name="E01DPQDDX" 
									onkeypress="enterInteger()"
									size="4" maxlength="4" value="<%= s0706.getE01DPQDDX().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
								</td>
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
		
		<%}%>
				
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
  		  
  		  <% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPQBNK.focus()");} %>
  		//  <% if(userPO.getHeader16().equals("2")){out.print("document.forms[0].E01DPQEST.focus()");} %>
  		  
  		  </script>
          
  </form>
</body>
</html>

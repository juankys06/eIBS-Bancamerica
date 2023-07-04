<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Lista de Accesos
</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP071301Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "optList3"  class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCNP1"  class= "datapro.eibs.beans.JBList"  scope="session" />
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
var boxLength = document.forms[0].E01DPWUID.length;
i = 0;
document.forms[0].E01DPWUID.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPWUID.options[i].value;
if (thisvalue == document.forms[0].USR.value) {
	document.forms[0].E01DPWUID.selectedIndex=i;
	break;
   }
}
}


<%--
var boxLength = document.forms[0].E01DPHDOC.length;
i = 0;
document.forms[0].E01DPHDOC.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPHDOC.options[i].value;
if (thisvalue == document.forms[0].DOC.value) {
	document.forms[0].E01DPHDOC.selectedIndex=i;
	break;
   }
}
}
//OJO
//alert("<%= brnDetails.getE01DPWUID().trim()%>");
var boxLength = document.forms[0].E01DPWUID.length;
i = 0;
document.forms[0].E01DPWUID.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPWUID.options[i].value;
if (thisvalue == document.forms[0].PRD.value) {
	document.forms[0].E01DPWUID.selectedIndex=i;
	break;
   }
}
}
--%>
}


function goConfirm(opt) {

	var op = opt;  	  
	var msg1 = "Esta seguro que desea ";
	var msg2 = " el registro seleccionado";
	document.forms[0].opt.value = op;
	//alert(op);
	//alert(document.forms[0].SCREEN.value);
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

	document.forms[0].USR.value=document.forms[0].E01DPWUID.value; 

	document.forms[0].submit();		  	       	       
}

function goCancel(fmt) {

document.forms[0].SCREEN.value="150";
document.forms[0].submit(); 
	  		  
}

</script>

</HEAD>

<body onload=javascript:init()>

<H3 align="center">Mantenimiento Lista de Accesos Detalle<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ListAccess713_maintenance.jsp, EDP0713"></H3>
<P align="center">

<%--
<INPUT type="text" name="DSC" size="35" maxlength="35" value="<%= userPO.getHeader10().trim()%>" readonly></P>
--%>	
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0713" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <input type=HIDDEN name="USR" value="<%= brnDetails.getE01DPWUID().trim()%>">
  
<h4>  
      Detalle Lista de Acceso a 
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
							<tr id="trdark">
								<td	nowrap width="30%">
									<div align="right">Usuario/Grupo :</div>
								</td>
								
								<td nowrap align="left" width="70%">
									<INPUT type="text" name="E01DPWUID" 
									onkeypress="enterInteger()"
									size="11" maxlength="10" 
									value="<%= brnDetails.getE01DPWUID().trim()%>" 
									readonly
									>
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
								<td	nowrap width="30%">
									<div align="right">Descripcion :</div>
								</td>
								
								<td nowrap align="left" width="70%">
									<INPUT type="text" name="E01DPWUDE" 
									onkeypress="enterInteger()"
									size="46" maxlength="45" 
									value="<%= brnDetails.getE01DPWUDE().trim()%>"
									readonly 
									>
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
								    <DIV align="right">Tipo :</DIV>
								</TD>
								
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPWTID" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
								    <option value=" " 
     								   <%if (brnDetails.getE01DPWTID().equals(" ")) { out.print("selected"); }%>></option>
     								<option value="1" 
     								   <%if (brnDetails.getE01DPWTID().equals("1")) { out.print("selected"); }%>>Grupo</option>
									<option value="2"
									   <%if (brnDetails.getE01DPWTID().equals("2")) { out.print("selected"); }%>>Usuario</option>
								</SELECT>
								</TD>
								
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>

				<TR>
					<TD width="100%">
					<TABLE id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
						<TBODY>
							<tr id="trdclear">
								<td nowrap width="30%">
									<DIV align="right">Codigo Ruta</DIV>
								</td>
								
								<td nowrap align="left" width="70%">
									<INPUT type="text" name="E01DPWRUT" size="5" maxlength="4" 
									value="<%= brnDetails.getE01DPWRUT().trim()%>" 
									<% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPWRDE" size="30" maxlength="30" readonly 
									value="<%= brnDetails.getE01DPWRDE().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPWRUT','E01DPWRDE','48')">
									<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A>
										
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
								<DIV align="right">Codigo Actividad</DIV>
								</td>
								
								<td nowrap align="left" width="70%">
									<INPUT type="text" name="E01DPWACT" size="5" maxlength="4" 
									value="<%= brnDetails.getE01DPWACT().trim()%>" 
									<% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
									<INPUT type="text" name="E01DPWADE" size="30" maxlength="30" readonly 
									value="<%= brnDetails.getE01DPWADE().trim()%>">
									<A href="javascript:GetCodeDescCNOFC('E01DPWACT','E01DPWADE','P2')">
									<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></A>
										
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
								<td	nowrap width="30%">
   									<DIV align="right">Procesa Actividad</DIV>
								</td>
								
								<td nowrap align="left" width="100%">
								<input type="radio" name="E01DPWTAC"  value="1" <%if(brnDetails.getE01DPWTAC().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPWTAC" value="0" <%if(!brnDetails.getE01DPWTAC().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">	
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
								<td	nowrap width="30%">
   									<DIV align="right">Consulta Actividad</DIV>
								</td>
								
								<td nowrap align="left" width="100%">
									<input type="radio" name="E01DPWINQ" value="1" <%if(brnDetails.getE01DPWINQ().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPWINQ" value="0" <%if(!brnDetails.getE01DPWINQ().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">	
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
								<td	nowrap width="30%">
   									<DIV align="right">Modificar Detalle</DIV>
								</td>
								
								<td nowrap align="left" width="100%">
								<input type="radio" name="E01DPWFG1"  value="1" <%if(brnDetails.getE01DPWFG1().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPWFG1" value="0" <%if(!brnDetails.getE01DPWFG1().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">	
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
								<td	nowrap width="30%">
   									<DIV align="right">Ingreso</DIV>
								</td>
								
								<td nowrap align="left" width="100%">
								<input type="radio" name="E01DPWFG2"  value="1" <%if(brnDetails.getE01DPWFG2().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPWFG2" value="0" <%if(!brnDetails.getE01DPWFG2().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">	
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
								<td	nowrap width="30%">
   									<DIV align="right">Procesa Forzar</DIV>
								</td>
								
								<td nowrap align="left" width="100%">
								<input type="radio" name="E01DPWFG3"  value="1" <%if(brnDetails.getE01DPWFG3().equals("1")) out.print("checked");%>>
              						Sí 
              						<input type="radio" name="E01DPWFG3" value="0" <%if(!brnDetails.getE01DPWFG3().equals("1")) out.print("checked");%>>
              						No 
								</td>
								
								<td nowrap width="25%">	
								</td>
								
							</tr>
						</TBODY>
					</TABLE>
					</TD>
				</TR>
				
				
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
  		  
  		  <% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPWTID.focus()");} %>
  		  <% if(userPO.getHeader16().equals("2")){out.print("document.forms[0].E01DPWTID.focus()");} %>  
  		  
  		  </script>
          
  </form>
</body>
</html>

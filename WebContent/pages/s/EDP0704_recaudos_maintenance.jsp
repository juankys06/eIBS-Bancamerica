<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Recaudo Documentos
</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP070401Message"  scope="session" />

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
var boxLength = document.forms[0].E01DPHPRD.length;
i = 0;
document.forms[0].E01DPHPRD.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPHPRD.options[i].value;
if (thisvalue == document.forms[0].PRD.value) {
	document.forms[0].E01DPHPRD.selectedIndex=i;
	break;
   }
}
}

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

//alert("<%= brnDetails.getE01DPHPRD().trim()%>");
var boxLength = document.forms[0].E01DPHPRD.length;
i = 0;
document.forms[0].E01DPHPRD.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPHPRD.options[i].value;
if (thisvalue == document.forms[0].PRD.value) {
	document.forms[0].E01DPHPRD.selectedIndex=i;
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

	document.forms[0].PRD.value=document.forms[0].E01DPHPRD.value; 

	document.forms[0].submit();		  	       	       
}

function goCancel(fmt) {

document.forms[0].SCREEN.value="150";
document.forms[0].submit(); 
	  		  
}

</script>

</HEAD>

<body onload=javascript:init()>

<H3 align="center">Mantenimiento Recaudo Documentos Detalle<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="recaudos_maintenance.jsp, EDP0704"></H3>
<P align="center">
<INPUT type="text" name="DSC" size="35" maxlength="35" value="<%= userPO.getHeader10().trim()%>" readonly></P>
	
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0704" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <input type=HIDDEN name="PRD" value="<%= brnDetails.getE01DPHPRD().trim()%>">
  <input type=HIDDEN name="DOC" value="<%= brnDetails.getE01DPHDOC().trim()%>">
  
<h4>  
      Detalle Recaudo Documento a 
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
									<div align="right">Banco :</div>
								</td>
								<td nowrap align="left" width="70%">
									<INPUT type="text" name="E01DPHBNK" 
									onkeypress="enterInteger()"
									size="2" maxlength="2" value="<%= brnDetails.getE01DPHBNK().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
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
								<td nowrap width="30%">
									<DIV align="right">Código de Producto :</DIV>
								</TD>
								<td nowrap align="left" width="70%"> 
								<select name="E01DPHPRD" 
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
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Código Documento :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								    <SELECT name="E01DPHDOC" 
								<% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLED");} %> >
									<%
                						optCNP1.initRow();
                						int d=1;
                						while (optCNP1.getNextRow()) {
                    						if (optCNP1.getFlag().equals("")) {
                    							out.println(optCNP1.getRecord());
                    							d++;
                    						}        
                						}                 
    								%>
								</SELECT><a href="javascript:GetCodeCNOFC('E01DPHDOC','P1')"></a> 
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
								<td nowrap width="30%">
								    <DIV align="right">Tipo de Documento </DIV>
								</TD>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPHTDO" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
								    <option value=" " 
     								   <%if (brnDetails.getE01DPHTDO().equals(" ")) { out.print("selected"); }%>></option>
     								<option value="1" 
     								   <%if (brnDetails.getE01DPHTDO().equals("1")) { out.print("selected"); }%>>Requerido</option>
									<option value="2"
									   <%if (brnDetails.getE01DPHTDO().equals("2")) { out.print("selected"); }%>>Adicional</option>
									<option value="3"
									   <%if (brnDetails.getE01DPHTDO().equals("3")) { out.print("selected"); }%>>Opcional</option>	
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
							<tr id="trdark">
								<td nowrap width="30%">
								<DIV align="right">Uso del Documento :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPHUDO" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (brnDetails.getE01DPHUDO().equals(" ")) { out.print("selected"); }%>></option>
									<option value="1"
										<%if (brnDetails.getE01DPHUDO().equals("1")) { out.print("selected"); }%>>Personas Juridica</option>
									<option value="2" 
									   <%if (brnDetails.getE01DPHUDO().equals("2")) { out.print("selected"); }%>>Personas Naturales</option>	
									<option value="3"
										<%if (brnDetails.getE01DPHUDO().equals("3")) { out.print("selected"); }%>>Ambos</option>
									
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
								<DIV align="right">Estado del Documento :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPHEST" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (brnDetails.getE01DPHEST().equals(" ")) { out.print("selected"); }%>></option>
									<option value="A" 
									   <%if (brnDetails.getE01DPHEST().equals("A")) { out.print("selected"); }%>>Activo</option>
									<option value="I"
										<%if (brnDetails.getE01DPHEST().equals("I")) { out.print("selected"); }%>>No Activo</option>
								</SELECT>								
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
  		  
  		  <% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPHBNK.focus()");} %>
  		  <% if(userPO.getHeader16().equals("2")){out.print("document.forms[0].E01DPHTDO.focus()");} %>
  		  
  		  </script>
          
  </form>
</body>
</html>

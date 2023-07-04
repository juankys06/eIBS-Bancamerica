<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Recaudo Documentos
</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="msg0701" class="datapro.eibs.beans.EDP070101Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "optCN05"  class= "datapro.eibs.beans.JBList"  scope="session" />
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
var boxLength = document.forms[0].E01DPWGRT.length;
i = 0;
document.forms[0].E01DPWGRT.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPWGRT.options[i].value;
if (thisvalue == document.forms[0].GRT.value) {
	document.forms[0].E01DPWGRT.selectedIndex=i;
	break;
   }
}
}

var boxLength = document.forms[0].E01DPWDGA.length;
i = 0;
document.forms[0].E01DPWDGA.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPWDGA.options[i].value;
if (thisvalue == document.forms[0].DGA.value) {
	document.forms[0].E01DPWDGA.selectedIndex=i;
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

	document.forms[0].PRD.value=document.forms[0].E01DPWGRT.value; 
	document.forms[0].GRT.value=document.forms[0].E01DPWGRT.value; 

	document.forms[0].submit();		  	       	       
}

function goCancel(fmt) {

document.forms[0].GRT.value="<%=userPO.getHeader1() %>"; 
document.forms[0].SCREEN.value="150";
document.forms[0].submit(); 
	  		  
}

</script>

</HEAD>

<body onload=javascript:init()>

<H3 align="center">Mantenimiento Recaudo Garantia Detalle<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="recaudosGAR_maintenance.jsp, EDP0701"></H3>
<P align="center">
<INPUT type="text" name="DSC" size="35" maxlength="35" value="<%= userPO.getHeader10().trim()%>" readonly></P>
	
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0701" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <INPUT TYPE=HIDDEN NAME="PRD" VALUE="">
  <input type=HIDDEN name="GRT" value="<%= msg0701.getE01DPWGRT().trim()%>">
  <input type=HIDDEN name="DGA" value="<%= msg0701.getE01DPWDGA().trim()%>">
  
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
									<INPUT type="text" name="E01DPWBNK" onkeypress="enterInteger()" size="3" maxlength="2" value="<%= msg0701.getE01DPWBNK().trim()%>" <% if(userPO.getHeader18().equals("1")){out.print("readonly");} %>>
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
									<DIV align="right">Código de Garantia :</DIV>
								</TD>
								<td nowrap align="left" width="70%"> 
								<select name="E01DPWGRT" 
								<% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLED");} %> >
								<OPTION value=""></OPTION>
             						<%
                						optCN05.initRow();
                						while (optCN05.getNextRow()) {
                    						if (optCN05.getFlag().equals("")) {
                    							out.println(optCN05.getRecord());
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
								<DIV align="right">Código Recaudo :</DIV>
								</td>
								<td nowrap align="left" width="70%">
								    <SELECT name="E01DPWDGA" 
								<% if(userPO.getHeader16().equals("2") || userPO.getHeader16().equals("3") || userPO.getHeader16().equals("5")){out.print("DISABLED");} %> >
									<OPTION value=""></OPTION>
									<%
                						optCNPG.initRow();
                						while (optCNPG.getNextRow()) {
                    						if (optCNPG.getFlag().equals("")) {
                    							out.println(optCNPG.getRecord());
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
							<TR id="trclear">
								<td nowrap width="30%">
								    <DIV align="right">Tipo de Documento </DIV>
								</TD>
								<td nowrap align="left" width="70%">
								<SELECT name="E01DPWTDO" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
								    <option value=" " 
     								   <%if (msg0701.getE01DPWTDO().equals(" ")) { out.print("selected"); }%>></option>
     								<option value="1" 
     								   <%if (msg0701.getE01DPWTDO().equals("1")) { out.print("selected"); }%>>Requerido</option>
									<option value="2"
									   <%if (msg0701.getE01DPWTDO().equals("2")) { out.print("selected"); }%>>Adicional</option>
									<option value="3"
									   <%if (msg0701.getE01DPWTDO().equals("3")) { out.print("selected"); }%>>Opcional</option>	
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
								<SELECT name="E01DPWUDO" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (msg0701.getE01DPWUDO().equals(" ")) { out.print("selected"); }%>></option>
									<option value="1"
										<%if (msg0701.getE01DPWUDO().equals("1")) { out.print("selected"); }%>>Personas Juridicas</option>
									<option value="2" 
									   <%if (msg0701.getE01DPWUDO().equals("2")) { out.print("selected"); }%>>Personas Naturales</option>	
									<option value="3"
										<%if (msg0701.getE01DPWUDO().equals("3")) { out.print("selected"); }%>>Ambos</option>
									
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
								<SELECT name="E01DPWEST" 
								<% if(userPO.getHeader16().equals("5") || userPO.getHeader16().equals("3")){out.print("DISABLED");} %>>
									<option value=" " 
									   <%if (msg0701.getE01DPWEST().equals(" ")) { out.print("selected"); }%>></option>
									<option value="A" 
									   <%if (msg0701.getE01DPWEST().equals("A")) { out.print("selected"); }%>>Activo</option>
									<option value="I"
										<%if (msg0701.getE01DPWEST().equals("I")) { out.print("selected"); }%>>No Activo</option>
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
  		  
  		  <% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPWBNK.focus()");} %>
  		  <% if(userPO.getHeader16().equals("2")){out.print("document.forms[0].E01DPWTDO.focus()");} %>
  		  
  		  </script>
          
  </form>
</body>
</html>

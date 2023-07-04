<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Análisis DOFA
</TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="msgL0729" class="datapro.eibs.beans.EDP072901Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</HEAD>


<script language="JavaScript">
function init()
{
 // texto comentarios 
 if (document.forms[0].E01CAFM21 != null) {
	document.forms[0].E01CAFM21.value = "<%=msgL0729.getE01CAFM21().trim()%>";
 }
 if (document.forms[0].E01CAFM22 != null) {
	document.forms[0].E01CAFM22.value = "<%=msgL0729.getE01CAFM22().trim()%>";
 }
 if (document.forms[0].E01CAFM23 != null) {
	document.forms[0].E01CAFM23.value = "<%=msgL0729.getE01CAFM23().trim()%>";
 }
 if (document.forms[0].E01CAFM24 != null) {
	document.forms[0].E01CAFM24.value = "<%=msgL0729.getE01CAFM24().trim()%>";
 }
 if (document.forms[0].E01CAFM25 != null) {
	document.forms[0].E01CAFM25.value = "<%=msgL0729.getE01CAFM25().trim()%>";
 }
}

function limitText(limitField, limitNum) {

	if (limitField.value.length > limitNum + 1) { 
		if (document.forms[0].LAN.value == 'S') {
			alert('Límite de texto excedido');
		} else {
			alert('Your input has been truncated');
		}	
	}	
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	}
}


function goConfirm(opt) {
	
	document.forms[0].opt.value = opt;

    if (confirm("Esta seguro que desea crear/modificar este registro?")) {
		document.forms[0].submit();
    }
}

function closeit() {
	close();
}


</script>
<body onload=javascript:init() onunload="closeit()">

<h3 align="center">Aspectos cuantitativos - Indicadores Financieros</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="810">
<INPUT TYPE=HIDDEN NAME="ROW" VALUE="">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="">
<table class="tableinfo">    
 <TR > 
  <TD NOWRAP >
   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type="text" name="Header1" size="12" readonly value="<%= userPO.getHeader1()%>">
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="NAMECUM" size="45" readonly value="<%=userPO.getHeader2()%>">
      </td>         
    </tr> 
    <tr id="trdark"> 
      <td align="right"  >
         <b>Periodo Estados Financieros:</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMFED" size="2" readonly value="<%=userPO.getHeader8()%>">/
         <input type="text" name="E01IFMFEM" size="2" readonly value="<%=userPO.getHeader7()%>">/
         <input type="text" name="E01IFMFEY" size="4" readonly value="<%=userPO.getHeader6()%>">
      </td>
      <td nowrap colspan=3> 
      </td>   
      <td nowrap colspan=3> 
      </td>   
     </tr> 

  </table> 
 </td>        
 </tr>  
</table>
<TABLE  class="tableinfo"> 
		<TR id=trdark> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="60%">
			<b></b>Indicadores Económicos
			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b> 
			</TD>
		</TR>
		<TR id=trclear> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="60%">
			<b></b>Solvencia y Liquidez
			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b> 
			</TD>
		</TR>
		<TR id=trdark> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="60%">
		<TEXTAREA name="E01CAFM21" maxlength="800" rows="10" cols="80" value="<%= msgL0729.getE01CAFM21().trim()%>"
		onKeyDown="limitText(this.form.E01CAFM21,800);" onKeyUp="limitText(this.form.E01CAFM21,800);"
		<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
		>
		</TEXTAREA>

			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b> 
			</TD>
		</TR>
		<TR id=trclear> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="60%">
			<b></b>Endeudamiento
			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b> 
			</TD>
		</TR>
		<TR id=trdark> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="60%">
		<TEXTAREA name="E01CAFM22" maxlength="800" rows="10" cols="80" value="<%= msgL0729.getE01CAFM22().trim()%>"
		onKeyDown="limitText(this.form.E01CAFM22,800);" onKeyUp="limitText(this.form.E01CAFM22,800);"
		<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
		>
		</TEXTAREA>

			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b> 
			</TD>
		</TR>
		<TR id=trclear> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="60%">
			<b></b>Rentabilidad y Cobertura
			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b> 
			</TD>
		</TR>
		<TR id=trclear> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="60%">
		<TEXTAREA name="E01CAFM23" maxlength="800" rows="10" cols="80" value="<%= msgL0729.getE01CAFM23().trim()%>"
		onKeyDown="limitText(this.form.E01CAFM23,800);" onKeyUp="limitText(this.form.E01CAFM23,800);"
		<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
		>
		</TEXTAREA>

			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b> 
			</TD>
		</TR>
		<TR id=trdark> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="60%">
			<b></b>Análisis de Flujo de Caja Proyectado
			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b> 
			</TD>
		</TR>
		<TR id=trdark> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="60%">
		<TEXTAREA name="E01CAFM24" maxlength="800" rows="10" cols="80" value="<%= msgL0729.getE01CAFM24().trim()%>"
		onKeyDown="limitText(this.form.E01CAFM24,800);" onKeyUp="limitText(this.form.E01CAFM24,800);"
		<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
		>
		</TEXTAREA>

			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b> 
			</TD>
		</TR>
		<TR id=trdark> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="60%">
			<b></b>Análisis de la Capacidad de Pago
			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b> 
			</TD>
		</TR>
		<TR id=trdark> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="60%">
		<TEXTAREA name="E01CAFM25" maxlength="800" rows="10" cols="80" value="<%= msgL0729.getE01CAFM25().trim()%>"
		onKeyDown="limitText(this.form.E01CAFM25,800);" onKeyUp="limitText(this.form.E01CAFM25,800);"
		<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
		>
		</TEXTAREA>

			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="10%"><b></b> 
			</TD>
		</TR>
		
		
	</TABLE>
  
	<div align="center"> 		
		<% if(!userPO.getOption().equals("4")){%> 		
       <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm('1')"> 
	    <% }; %> 
	<INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="window.close();">
	</div>

</FORM>

</BODY>
</HTML>

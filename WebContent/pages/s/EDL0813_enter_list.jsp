<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Remesas</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "jlista" class= "datapro.eibs.beans.JBObjList"   scope="session"/>
<jsp:useBean id= "opt" class= "java.lang.String"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</HEAD>

<BODY>
<script language="javascript">
var ATRAS = 0;
var ADELANTE = 1;

function goAction(op){
    document.forms[0].opt.value = op;
   	document.forms[0].submit();
}

function enviar(){
	document.forms[0].SCREEN.value = 300;
   	document.forms[0].submit();
}

function navega(direccion){
	document.forms[0].SCREEN.value = 500 + direccion;
	document.forms[0].submit();
}

function ordena(fieldname){
	document.forms[0].SCREEN.value = "502";
	document.forms[0].FIELD.value = fieldname;
	document.forms[0].submit();
}

function ordenaFecha(dd,mm,yy){
	document.forms[0].SCREEN.value = "503";
	document.forms[0].DAY.value = dd;
	document.forms[0].MONTH.value = mm;
	document.forms[0].YEAR.value = yy;
	document.forms[0].submit();
}
</script>

<h3 align="center">Remesas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="enter_list,EDL0813"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0813" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="">
<INPUT TYPE=HIDDEN NAME="accion" VALUE="<%=opt%>">
<INPUT TYPE=HIDDEN NAME="FIELD" VALUE=""> 
<INPUT TYPE=HIDDEN NAME="DAY"   VALUE=""> 
<INPUT TYPE=HIDDEN NAME="MONTH" VALUE=""> 
<INPUT TYPE=HIDDEN NAME="YEAR"  VALUE=""> 
<INPUT TYPE=HIDDEN NAME="pagina" VALUE="EDL0813_enter_list.jsp">


<TABLE class="tbenter" width="100%">
    <TR>
      <TD ALIGN=CENTER width="12%" class="TDBKG"><a href="javascript:goAction('E')">Envio</a></TD>
      <TD ALIGN=CENTER width="12%" class="TDBKG"><a href="javascript:goAction('R')">Recepción</a></TD>
      <TD ALIGN=CENTER width="12%" class="TDBKG"><a href="javascript:goAction('D')">Devolución</a></TD>
      <TD ALIGN=CENTER width="12%" class="TDBKG"><a href="javascript:goAction('P')">Protesto</a></TD>
      <TD ALIGN=CENTER width="12%" class="TDBKG"><a href="javascript:goAction('N')">Notaria</a></TD>
  	  <TD ALIGN=CENTER width="14%" class="TDBKG"><a href="javascript:goAction('M')">Devoluición<br>Notaria</a></TD>
      <TD ALIGN=CENTER width="14%" class="TDBKG"><a href="javascript:goAction('C')">Cobranza</a></TD>
    </TR>
</TABLE>
<br>
<%if (!opt.equals("") ){%>
	<%if (jlista.getSize() == 0 ) {%>
	<TABLE class="tbenter" width=100% height=90%>
		<TR>
			<TD> 
				<div align="center"> <font size="3"><b> No hay resultado que corresponda a su criterio de búsqueda.</b></font> </div>
			</TD>
		</TR>
	</TABLE>
	<%}else{%>
		<TABLE  id="mainTable" class="tableinfo" >
		     <TR id="trdark"> 
			      <TH ALIGN=CENTER NOWRAP></TH>
			      <TH ALIGN=CENTER NOWRAP>Nro.<br>Documento</TH>
			      <TH ALIGN=CENTER NOWRAP>Rut<br>Aceptante</TH>
			      <TH ALIGN=CENTER NOWRAP>Nombre<br>Aceptante</TH>
			      <TH ALIGN=CENTER NOWRAP>Dirección</TH>
			      <TH ALIGN=CENTER NOWRAP>Código<br>Comuna</TH>
			      <TH ALIGN=CENTER NOWRAP>Comuna</TH>
			      <TH ALIGN=CENTER NOWRAP>Código<br>Ciudad</TH>
			      <TH ALIGN=CENTER NOWRAP>Ciudad</TH>
	     	      <TH ALIGN=CENTER nowrap>Fecha<br>Vencimiento</TH>
			      <TH ALIGN=CENTER NOWRAP>Valor del<br>Documento</TH>
			      <TH ALIGN=CENTER NOWRAP>Rut Cedente</TH>
			      <TH ALIGN=CENTER NOWRAP>Nombre Cedente</TH>
			      <TH ALIGN=CENTER NOWRAP>Tipo <br>Cobranza</TH>
			      <TH ALIGN=CENTER NOWRAP>Carta Guía</TH>
			      <TH ALIGN=CENTER NOWRAP>Ruta de <br>Cobro</TH>
			      <TH ALIGN=CENTER NOWRAP>Plaza de <br>Pago</TH>
			      <TH ALIGN=CENTER NOWRAP>Agente Cobrador</TH>
		  </TR>
     	<%
        jlista.initRow(); 
        while (jlista.getNextRow()) {
           datapro.eibs.beans.EDL081301Message EDL0813 = (datapro.eibs.beans.EDL081301Message) jlista.getRecord();
     	%>               
        	<TR>
				<TD NOWRAP width="2%">
					<input type="checkbox" name="NUMSEQ<%=jlista.getCurrentRow()%>" value="<%= jlista.getCurrentRow()%>" >
				</TD>
				<TD nowrap><DIV align=right><%= EDL0813.getE01DLDNDR() %></DIV></TD>
				<TD nowrap><DIV align=right><%= EDL0813.getE01DLDDID() %></DIV></TD>
				<TD nowrap><DIV align=center><%= EDL0813.getE01DLDNME() %></DIV></TD>
				<TD nowrap><DIV align=center><%= EDL0813.getE01DLDMA2() %></DIV></TD>
				<TD nowrap><DIV align=center><%= EDL0813.getE01DLDCOM() %></DIV></TD>
				<TD nowrap><DIV align=center><%= EDL0813.getD01DLDCOM() %></DIV></TD>
				<TD nowrap><DIV align=center><%= EDL0813.getE01DLDCIU() %></DIV></TD>
				<TD nowrap><DIV align=center><%= EDL0813.getD01DLDCIU() %></DIV></TD>				
				<TD nowrap><DIV align=center><%= datapro.eibs.master.Util.formatDate(EDL0813.getE01DLDMA1(),EDL0813.getE01DLDMA2(),EDL0813.getE01DLDMA3()) %></DIV></TD>
				<TD nowrap><DIV align=right><%= EDL0813.getE01DLDNAM() %></DIV></TD>
				<TD nowrap><DIV align=right><%= EDL0813.getE01DLDCID() %></DIV></TD>
				<TD nowrap><DIV align=left><%= EDL0813.getD01DLDNA1() %></DIV></TD>
				<TD nowrap><DIV align=center><%= EDL0813.getD01DLDTCB() %></DIV></TD>
				<TD nowrap><DIV align=right><%= EDL0813.getE01DLDRFN() %></DIV></TD>
				<TD nowrap><DIV align=center><%= EDL0813.getE01DLDREW() %></DIV></TD>
				<TD nowrap><DIV align=center><%= EDL0813.getE01DLDPYW() %></DIV></TD>
				<TD nowrap><DIV align=center><%= EDL0813.getE01DLDREM() %></DIV></TD>
			</TR>
			<%}%>
		</TABLE>
			<TABLE class="tbenter" WIDTH="100%" >
		<TR>
			<TD WIDTH="50%" ALIGN=LEFT height="25">
		 	<%
		        if ( jlista.getShowPrev() ) {
		      		int pos = jlista.getFirstRec() - 21;
			%>
					<A HREF="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0813?SCREEN=200&pos=<%=pos%>" > 
					<img src="<%=request.getContextPath()%>/images/s/previous_records.gif" border=0></A>		
			<%  } %> 
			</TD>
			<TD ALIGN=CENTER>     
				<p align="center"><INPUT id="EIBSBTN" type="button" name ="btenviar" value="Enviar"   onClick="enviar()"></p>
			</TD>
			<TD WIDTH="50%" ALIGN=RIGHT height="25">
		 	<%
		        if ( jlista.getShowNext() ) {
		      		int pos = jlista.getLastRec();
			%>
					<A HREF="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0813?SCREEN=200&pos=<%=pos%>" > 
					<img src="<%=request.getContextPath()%>/images/s/next_records.gif" border=0></A>		
			<%  } %> 
			</TD>

		</TR>
	</TABLE>
		
		
	<%}%>
<%}%>

<BR>
</FORM>

</BODY>
</HTML>

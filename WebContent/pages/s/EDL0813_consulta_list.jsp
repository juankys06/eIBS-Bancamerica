<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Remesas</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util" %>
<%@ page import="datapro.eibs.beans.EDL081301Message"%>
<%@ page import="com.cb.conn.sck.base.sortList"%>

<%com.cb.conn.sck.base.sortList jlista = (com.cb.conn.sck.base.sortList)session.getAttribute("jlista");%>
<jsp:useBean id= "paginaactual" class= "java.lang.String" 	scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</HEAD>

<BODY>
<script language="javascript">
var ATRAS = 0;
var ADELANTE = 1;

function navega(direccion){
	if (direccion ==  0)
	  	PaginaActual=parseInt(document.forms[0].PaginaActual.value) - 1;  	
	else
		PaginaActual=parseInt(document.forms[0].PaginaActual.value) + 1;  			
	document.forms[0].PaginaActual.value=PaginaActual;	
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

<h3 align="center">Consulta de Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="consulta_list,EDL0813"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/com.cb.products.JSEDL0813" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
<INPUT TYPE=HIDDEN NAME="pagina" VALUE="EDL0813_consulta_list.jsp">
<INPUT TYPE=HIDDEN NAME="FIELD" VALUE=""> 
<INPUT TYPE=HIDDEN NAME="DAY"   VALUE=""> 
<INPUT TYPE=HIDDEN NAME="MONTH" VALUE=""> 
<INPUT TYPE=HIDDEN NAME="YEAR"  VALUE=""> 
<input type=HIDDEN name="PaginaActual" value="<%=paginaactual%>">	

<%if (jlista.getSize() == 0 ) {%>
	<TABLE class="tbenter" width=100% height=90%>
		<TR>
			<TD> 
				<div align="center"> <font size="3"><b> No hay resultado que corresponda a su criterio de búsqueda.</b></font> </div>
			</TD>
		</TR>
	</TABLE>
	<%}else{%>
		<table width="100%" class="tbenter">
			<tr>
		      <TD width="50%">Total Registros : <%=jlista.getSize()- 1%></TD>
		      <TD width="50%" align=right>
		   		<%int totpag = (jlista.getSize()/jlista.getDisplaySize()); 
				if (totpag < (jlista.getSize()/jlista.getDisplaySize()));
					totpag = totpag + 1;%>
					Pagina <%=paginaactual%> de <%=totpag%>
		      </TD>

		    </tr>
		</table>
		<br>
		<TABLE  id="mainTable" class="tableinfo" >
		     <TR id="trdark"> 
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDRFN')">N.Letra</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDNDR')">Nro.<br>Documento</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDDID')">Rut<br>Aceptante</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDNLN')">Cuenta</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDNME')">Nombre<br>Aceptante</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDMA2')">Dirección</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDCOM')">Código<br>Comuna</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('D01DLDCOM')">Comuna</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDCIU')">Código<br>Ciudad</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('D01DLDCIU')">Ciudad</a></TH>
	     	      <TH ALIGN=CENTER nowrap><a href="javascript:ordenaFecha('E01DLDMA1','E01DLDMA2','E01DLDMA3')">Fecha<br>Vencimiento</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDNAM')">Valor del<br>Documento</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDCID')">Rut Cedente</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('D01DLDNA1')">Nombre Cedente</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('D01DLDTCB')">Tipo <br>Cobranza</a></TH>

			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDREW')">Ruta de <br>Cobro</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDPYW')">Plaza de <br>Pago</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDREM')">Agente Cobrador</a></TH>
			      <TH ALIGN=CENTER NOWRAP><a href="javascript:ordena('E01DLDRST')">Estado</a></TH>
		  </TR>
			<%int i=0;
			  for (i = jlista.getBaseIdx(); i < jlista.getSize() && i < jlista.getBaseIdx() + jlista.getDisplaySize(); i++) { 
				EDL081301Message EDL0813 = (EDL081301Message)jlista.getData(i);%>
				<TR id=trclear>
					<TD nowrap><DIV align=right><%= EDL0813.getE01DLDRFN() %></DIV></TD>
					<TD nowrap><DIV align=right><%= EDL0813.getE01DLDNDR() %></DIV></TD>
					<TD nowrap><DIV align=right><%= datapro.eibs.platform.CBUtil.formatRUT(EDL0813.getE01DLDDID()) %></DIV></TD>
					<TD nowrap><DIV align=center><%= EDL0813.getE01DLDNLN() %></DIV></TD>
					<TD nowrap><DIV align=center><%= EDL0813.getE01DLDNME() %></DIV></TD>
					<TD nowrap><DIV align=center><%= EDL0813.getE01DLDMA2() %></DIV></TD>
					<TD nowrap><DIV align=center><%= EDL0813.getE01DLDCOM() %></DIV></TD>
					<TD nowrap><DIV align=center><%= EDL0813.getD01DLDCOM() %></DIV></TD>
					<TD nowrap><DIV align=center><%= EDL0813.getE01DLDCIU() %></DIV></TD>
					<TD nowrap><DIV align=center><%= EDL0813.getD01DLDCIU() %></DIV></TD>				
					<TD nowrap><DIV align=center><%= datapro.eibs.master.Util.formatDate(EDL0813.getE01DLDMA1(),EDL0813.getE01DLDMA2(),EDL0813.getE01DLDMA3()) %></DIV></TD>
					<TD nowrap><DIV align=right><%= EDL0813.getE01DLDNAM() %></DIV></TD>
					<TD nowrap><DIV align=right><%= datapro.eibs.platform.CBUtil.formatRUT(EDL0813.getE01DLDCID()) %></DIV></TD>
					<TD nowrap><DIV align=left><%= EDL0813.getD01DLDNA1() %></DIV></TD>
					<TD nowrap><DIV align=center><%= EDL0813.getD01DLDTCB() %></DIV></TD>

					<TD nowrap><DIV align=center><%= EDL0813.getE01DLDREW() %></DIV></TD>
					<TD nowrap><DIV align=center><%= EDL0813.getE01DLDPYW() %></DIV></TD>
					<TD nowrap><DIV align=center><%= EDL0813.getE01DLDREM() %></DIV></TD>
<%				        String status ="";
						if (EDL0813.getE01DLDRST().trim().equals("A")) 
					       	status = "ACTIVO";
				        if (EDL0813.getE01DLDRST().trim().equals("P")) 
					       	status = "PAGADO";
				        if (EDL0813.getE01DLDRST().trim().equals("Q")) 
					       	status = "DEVUELTO/ANULADO";
				        if (EDL0813.getE01DLDRST().trim().equals("D")) 
					       	status = "ENVIADO A CAMARA";
				        if (EDL0813.getE01DLDRST().trim().equals("N")) 
					       	status = "PRORROGA";
				        if (EDL0813.getE01DLDRST().trim().equals("R")) 
					       	status = "REVERSION PAGO/CHEQUE CAMARA";
				        if (EDL0813.getE01DLDRST().trim().equals("H")) 
					       	status = "PENDIENTE APROBACION";
				        if (EDL0813.getE01DLDRST().trim().equals("X")) 
					       	status = "PAGADO HOY";
				        if (EDL0813.getE01DLDRST().trim().equals("T")) 
					       	status = "PROTESTADO";
	 		              if (EDL0813.getE01DLDRST().trim().equals("U")) 
				          	status = "COBRANZA JUDICIAL";
%>
					<TD nowrap><DIV align=center><%= status%></DIV></TD>
				</TR>
			<%}%>
		</TABLE>
			<TABLE class="tbenter" WIDTH="100%" >
		<TR>
			<TD ALIGN=LEFT>
				<%if (jlista.showPrev()) { %>
					<A  HREF="javascript:navega(0)"><IMG border="0" src="<%=request.getContextPath()%>/images/s/previous_records.gif"></A>
				<%}%>  
			</TD>
			<TD ALIGN=RIGHT>     
				<%if (jlista.showNext()){ %>
					<A HREF="javascript:navega(1)"><IMG border="0" src="<%=request.getContextPath()%>/images/s/next_records.gif"></A>
				<%}%>
			</TD>
		</TR>
	</TABLE>
<%}%>

<BR>
</FORM>

</BODY>
</HTML>

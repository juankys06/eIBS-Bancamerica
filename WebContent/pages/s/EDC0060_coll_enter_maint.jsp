<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Pago/Liquidación de Cobranzas</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="dcPag"  class="datapro.eibs.beans.EDC006001Message" scope="session" />
<SCRIPT Language="Javascript">
  function helpAcc(tipo,fldst,fld1,fld2){
    if(tipo=='A'){
        GetAccByClient(fldst,'','RT','','E01DCMCL1')
    }
    if(tipo=='G'){
       if(fld2==''){
          alert("Seleccione Moneda de Cuenta Contable");
          return;
       } 
        GetLedger(fldst,fld1,fld2,'')
    }    
  }
</SCRIPT>
<SCRIPT Language="javascript">
  function fcvalida(){
     if(document.forms[0].E01DCMACC.value == "0.00"){ 
        alert("Debe de Ingrese monto de Pago");
        return false;
     }
     if(document.forms[0].E01TIPOPE.value == "0"){ 
        alert("Debe de Seleccionar Tipo de Operacion");
        return false;
     }
     
     if(document.forms[0].E01TIPOPE.value == "4" && document.forms[0].E01CONDCA.value == "X"){ 
        alert("Debe de Seleccionar condicion de la cancelacion");
        return false;
     }
     
     return true;
  }
</SCRIPT>

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
%>

</HEAD>
<BODY>

<H3 align="center">Pago/Liquidación de Cobranzas<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="coll_enter_maint.jsp, EDC0060"></H3>
<HR size="4">
<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0060" onsubmit="return fcvalida();">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
    <TR id="trdark"> 
      <TD nowrap width="50%">
      	<div align="right">Número de Cobranza :</div>
      </TD>
      <TD nowrap width="50%">
	    <div align="left">
	        <INPUT type="text" name="E01DCMACC" size="13" maxlength="12" value="<%= dcPag.getE01DCMACC() %>" onKeypress="enterInteger()">
    	    <A href="javascript:GetAccount('E01DCMACC','','CL','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" align="absbottom"></A>
    	</div>
      </TD>
    </TR>

    <TR id="trclear"> 
      <TD nowrap width="50%">
      	<div align="right">Tipo de Operación :</div>
      </TD>
      <TD nowrap width="50%">
	    <div align="left">
    	    <select name="E01TIPOPE">
				<option value="0" selected></option>
				<option value="1" <%= dcPag.getE01TIPOPE().equals("1")?"selected":"" %>>Pago Total</option>
				<option value="2" <%= dcPag.getE01TIPOPE().equals("2")?"selected":"" %>>Pago Parcial</option>
				<option value="3" <%= dcPag.getE01TIPOPE().equals("3")?"selected":"" %>>Cobro de Comisiones</option>
				<option value="4" <%= dcPag.getE01TIPOPE().equals("4")?"selected":"" %>>Cancelacion</option>								
			</select>
			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" border="0" align="absmiddle">
	   	</div>
      </TD>
    </TR>

    <TR id="trdark"> 
      <TD nowrap width="50%">
      	<div align="right">Monto a Pagar :</div>
      </TD>
      <TD nowrap width="50%">
	    <div align="left">
    	    <INPUT	type="text" name="E01MNTPAG" size="28" maxlength="35"	value="<%=dcPag.getE01MNTPAG()%>">
    	    <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" border="0" align="absmiddle"></div>
      </TD>
    </TR>

    <TR id="trclear"> 
      <TD nowrap width="50%">
      	<div align="right">Condición de la Cancelación :</div>
      </TD>
      <TD nowrap width="50%">
	    <div align="left">
    	    <SELECT name="E01CONDCA">
			   <OPTION value="X" selected></OPTION>
			   <OPTION value="F" <%= dcPag.getE01CONDCA().equals("F")?"selected":"" %>>Libre de Pago</OPTION>
			   <OPTION value="R" <%= dcPag.getE01CONDCA().equals("R")?"selected":"" %>>Devuelta Impaga</OPTION>
		    </SELECT>
    	</div>
      </TD>
    </TR>  
    <TR id="trdark">
				  <TD width="25%" align="right" nowrap>Generar Swift MT400:</TD>
				  <TD nowrap width="25%"><INPUT type="radio" name="E01SWFMSG" value="Y">
				    Si
				    <INPUT type="radio" name="E01SWFMSG" value="N">
				    No </TD>
    </TR> 
			  <TR id="trclear">
			    <TD align="right" nowrap>Banco Receptor:</TD>
			    <TD align="left" nowrap><INPUT type="text" name="E01SWFRCV" size="14" maxlength="12">
			      <A href="javascript:GetSwiftIdDesc('E01SWFRCV','','','')"> <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"  border="0"></A> </TD>
			  </TR>
			  <TR id="trdark">
			    <TD align="right" nowrap>Banco Corresponsal:</TD>
			    <TD align="left" nowrap><INPUT type="text" name="E01CRPBID" size="14" maxlength="12">
			      <A href="javascript:GetSwiftIdDesc('E01CRPBID','','','')"> <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"  border="0"></A> </TD>
			  </TR>
		</table>
		</td>
	</tr>
</table>

  <h4>Liquidar con Remitente</h4>
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
		<TR id="trdark">
			<TD align="right" width="670">
				<DIV align="right">Vía de Reembolso :</DIV>
			</TD>
			<TD width="664">
				<SELECT name="E01REMVIA">
					<OPTION value=" " selected></OPTION>
					<!-- OPTION value="1" 	<%= dcPag.getE01REMVIA().equals("1")?"selected":"" %>>Cheque de Oficial</OPTION -->
					<OPTION value="2" 	<%= dcPag.getE01REMVIA().equals("2")?"selected":"" %>>Cuenta Corriente</OPTION>
					<OPTION value="3"	<%= dcPag.getE01REMVIA().equals("3")?"selected":"" %>>Cuenta Contable</OPTION>
					<!-- OPTION value="4" 	<%= dcPag.getE01REMVIA().equals("4")?"selected":"" %>>FED ( USA	)</OPTION -->
					<!-- OPTION value="5"	<%= dcPag.getE01REMVIA().equals("5")?"selected":"" %>>SWIFT</OPTION -->
					<!-- OPTION value="6"	<%= dcPag.getE01REMVIA().equals("6")?"selected":"" %>>Telex</OPTION -->
				</SELECT>
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" border="0" align="absmiddle">
			</TD>
		</TR>
		<TR id="trclear">
			<TD width="670">
				<DIV align="right">Moneda de Reembolso :</DIV>
			</TD>
			<TD width="664">
				<DIV align="left"><INPUT type="text" name="E01REMCCY" size="4" maxlength="3" value="<%=dcPag.getE01REMCCY()%>">
				<A href="javascript:GetCurrency('E01REMCCY','')"><IMG	src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
				align="absbottom" border="0"></A></DIV>
			</TD>
		</TR>
		<TR id="trdark">
			<TD width="670">
				<DIV align="right">Cuenta de Reembolso :</DIV>
			</TD>
			<TD width="664">
				<DIV align="left"><INPUT type="text" name="E01REMACC" size="17" maxlength="16" value="<%=dcPag.getE01REMACC()%>">
				<A href="javascript: helpAcc((document.forms[0].E01REMVIA.value == '2' ?'A': 'G'),'E01REMACC','01',document.forms[0].E01REMCCY.value)">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
				border="0" align="absbottom"></A></DIV>
			</TD>
		</TR>
		<tr>
				  <TD align="right" nowrap>Tipo Mensaje Swift: </TD>
				  <TD align="left" nowrap>
				  	<SELECT name="E01REMSMT">
				  		<OPTION value=""></OPTION>
                      	<OPTION value="103">MT103</OPTION>
                      	<OPTION value="202">MT102</OPTION>
                  	</SELECT>
                  </TD>
		</Tr>	
		</table>
		</td>
	</tr>
</table>

  <h4>Liquidar con Pagador</h4>
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
		<TR id="trdark">
			<TD align="right" width="671">
				<div align="right">Vía de Pago :</div>
			</TD>
			<TD width="663">
				<SELECT name="E01PAGVIA">
					<OPTION value=" " selected></OPTION>
					<OPTION value="2"	<%= dcPag.getE01PAGVIA().equals("2")?"selected":"" %>>Cuenta Corriente</OPTION>
					<OPTION value="3"	<%= dcPag.getE01PAGVIA().equals("3")?"selected":"" %>>Cuenta Contable</OPTION>
				</SELECT>
				<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" border="0" align="absmiddle">
			</TD>
		</TR>
		<TR id="trclear">
			<TD width="671">
				<DIV align="right">Moneda de Pago :</DIV>
			</TD>
			<TD width="663">
				<DIV align="left"><INPUT type="text" name="E01PAGCCY" size="4"
				maxlength="3" value="<%=dcPag.getE01PAGCCY()%>"> <A
				href="javascript:GetCurrency('E01PAGCCY','')"><IMG
				src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
				align="absbottom" border="0"></A></DIV>
			</TD>
		</TR>
		<TR id="trdark">
			<TD width="671">
				<DIV align="right">Número de Cuenta de Pago Principal :</DIV>
			</TD>
			<TD width="663">
				<DIV align="left"><INPUT type="text" name="E01PAGACCP" size="18"
				maxlength="16" value="<%=dcPag.getE01PAGACCP()%>"> <A
				href="javascript: helpAcc((document.forms[0].E01PAGVIA.value == '2' ?'A': 'G'),'E01PAGACCP','01',document.forms[0].E01PAGCCY.value)">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
				border="0" align="absbottom"></A></DIV>
			</TD>
		</TR>
		<TR id="trclear">
			<TD width="671">
				<DIV align="right">Número de Cuenta de Pago de Comisión/Impuestos :</DIV>
			</TD>
			<TD width="663">
				<DIV align="left"><INPUT type="text" name="E01PAGACCC" size="18"
				maxlength="16" value="<%=dcPag.getE01PAGACCC()%>"> <A
				href="javascript: helpAcc((document.forms[0].E01PAGVIA.value == '2' ?'A': 'G'),'E01PAGACCC','01',document.forms[0].E01PAGCCY.value)">
			<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
				border="0" align="absbottom"></A></DIV>
			</TD>
		</TR>
		</table>
		</td>
	</tr>
</table>
<BR>
<div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
</div>
<script language="JavaScript">
  document.forms[0].E01DCMACC.focus();
  document.forms[0].E01DCMACC.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</FORM>
<P><BR>

</BODY>
</HTML>

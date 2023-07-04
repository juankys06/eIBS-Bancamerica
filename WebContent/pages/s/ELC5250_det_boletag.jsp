<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<style>
 #trcolor
 {
 background-color: #F2F1F1;
 }

TD {
	border-left-color: black;
	border-top-color: black;
	border-right-color: black;
	border-bottom-color: black
}</style>
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "bolgaran" class= "datapro.eibs.beans.ELC500001Message"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<BODY>

<script language="JavaScript">

function PrintPreview()
{
	div1.style.display="none";
	window.print();
	div1.style.display="";
}

</script>


<SCRIPT Language="Javascript">
function envia()
{
	document.forms[0].submit();
}

</SCRIPT>


<%
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   if (userPO.getPurpose().equals("INQUIRY")){
   out.println("<SCRIPT> initMenu(); </SCRIPT>");}
%>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC5250" >
<INPUT TYPE=HIDDEN NAME="FechaNow" VALUE=<%=userPO.getHeader19()%>>

<h4></h4>
<h3 align="center">Comprobante de Boleta de Garantia en Efectivo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="det_boletag.jsp,ELC5250"></h3>
<hr size="4">

<TABLE  style="WIDTH: 600px;background-color: #ffffff" cellSpacing=1 cellPadding=1 width="100%" border=0>

  <TR>
    <TD>
       <TABLE style="background-color: #ffffff" cellSpacing="0" cellPadding="0" width="100%" border="0">

        <TR>
          <TD><div align=left>Fecha Emisión </div></TD>
          <TD STYLE="BORDER-RIGHT: black 1px solid;"><div align=left>&nbsp;<%= Util.formatDate(bolgaran.getE01LCMIDM(),bolgaran.getE01LCMIDD(),bolgaran.getE01LCMIDY())%></div></TD>
          <TD id="trcolor"  style="BORDER-TOP: black 1px solid;  HEIGHT:20PX"><div align=left>Nro. Folio</div> </TD>
          <TD id="trcolor"  style="BORDER-TOP: black 1px solid; BORDER-RIGHT: black 1px solid; HEIGHT:20PX" width="96"><div align=left>&nbsp;<%= bolgaran.getE01LCMACC().trim()%></div></TD>
        </TR>
        <TR>
        <%
        	String Hora = bolgaran.getH01TIMSYS();
        	String mmss = Hora.substring(6,12);
        %>
          <TD><div align=left>Hora Emisión </div></TD>
          <TD style="BORDER-RIGHT: black 1px solid"><div align=left>&nbsp;<%= Util.formatTime(mmss)%></div></TD>
          <TD id="trcolor" style="HEIGHT:20PX"><div align=left>Sucursal</div></TD>
          <TD id="trcolor" style="BORDER-RIGHT: black 1px solid; HEIGHT:20PX" width="96"><div align=left>&nbsp;<%= datapro.eibs.platform.CBUtil.getBranchName("01", bolgaran.getE01LCMBRN().trim())%></div></TD>
        </TR>
        <TR id="trcolor">
          <TD colSpan=2  width="250" id=trdark style="BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid" >
          <div align=center>Datos del Tomador</div></TD>
          <TD style="BORDER-BOTTOM: black 1px solid" valign="top"><div align=left>Producto</div></TD>
          <TD style="BORDER-BOTTOM: black 1px solid; BORDER-RIGHT: black 1px solid" valign="top" width="200"><div align=left>&nbsp;<%= bolgaran.getE01PRDNME().trim()%></div></TD>
        </TR>
        <TR id="trcolor">
          <TD style="BORDER-LEFT: black 1px solid; HEIGHT:20PX"><div align=left>Rut</div></TD>
          <TD style="BORDER-RIGHT: black 1px solid; HEIGHT:20PX"><div align=left>&nbsp;<%= datapro.eibs.platform.CBUtil.formatRUT(bolgaran.getE01APLRUT().trim())%></div></TD>
          <TD style="BORDER-RIGHT: black 1px solid" colspan="2">&nbsp;</TD>
        </TR>
        <TR id="trcolor">
          <TD style="BORDER-LEFT: black 1px solid; HEIGHT:20PX"><div align=left>Nombre</div></TD>
          <TD style="BORDER-RIGHT: black 1px solid; HEIGHT:20PX"><div align=left>&nbsp;<%= bolgaran.getE01APLNME().trim()%></div></TD>
          <TD style="HEIGHT:5PX">
                        <div align="left">Monto $</div>
                        </TD>
          <TD style="BORDER-RIGHT: black 1px solid; HEIGHT:5PX" width="100"><div align=right>&nbsp;<%= datapro.eibs.platform.CBUtil.formatAmount(datapro.eibs.platform.CBUtil.unFormatAmount(bolgaran.getE01LCMCAM().trim()))%></div></TD>
        </TR>
        <TR id="trcolor">
          <TD style="BORDER-LEFT: black 1px solid; HEIGHT:20PX"><div align=left>Domicilio</div></TD>
          <TD style="BORDER-RIGHT: black 1px solid; HEIGHT:20PX"><div align=left>&nbsp;<%= bolgaran.getE01APLNM2().trim()%></div></TD>
          <TD style="HEIGHT:5PX"><div align=left>Comisión $</div></TD>
          <TD style="BORDER-RIGHT: black 1px solid; HEIGHT:5PX" width="100"><div align=right>&nbsp;<%= datapro.eibs.platform.CBUtil.formatAmount(datapro.eibs.platform.CBUtil.unFormatAmount(bolgaran.getE01LCMC11().trim()))%></div></TD>
        </TR>
        <TR id="trcolor">
          <TD style="BORDER-LEFT: black 1px solid; HEIGHT:20PX" ><div align=left>Comuna</div></TD>
          <TD style="BORDER-RIGHT: black 1px solid"><div align=left>&nbsp;<%= bolgaran.getE01BNFNM3().trim()%></div></TD>
          <TD style="HEIGHT:5PX"><div align=left>Total $</div></TD>
           <%
		   double monto 		= Double.parseDouble(datapro.eibs.platform.CBUtil.unFormatAmount(bolgaran.getE01LCMCAM().trim()));
		   double comision 		= Double.parseDouble(datapro.eibs.platform.CBUtil.unFormatAmount(bolgaran.getE01LCMC11().trim()));
		   String total_suma	= datapro.eibs.platform.CBUtil.formatAmount(String.valueOf(monto + comision));

		   String comision_str	= datapro.eibs.platform.CBUtil.formatAmount(datapro.eibs.platform.CBUtil.unFormatAmount(bolgaran.getE01LCMC11().trim()));

		   %>
 		   <TD style="BORDER-RIGHT: black 1px solid" width="100">
           <% if ( !comision_str.equals("0") ) { %>
              <div align=right><%= total_suma%></div>
           <% }
              else { %>
          	  <div align=right><%= datapro.eibs.platform.CBUtil.formatAmount(datapro.eibs.platform.CBUtil.unFormatAmount(bolgaran.getE01LCMC11().trim()))%></div>
           <% } %>
           </TR>
        <TR id="trcolor">
          <TD style="BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid"><div align=left>Ciudad</div></TD>
          <TD style="BORDER-RIGHT: black 1px solid; BORDER-BOTTOM: black 1px solid"><div align=left>&nbsp;<%= bolgaran.getE01BNFCTY().trim()%></TD>
          <TD style="BORDER-BOTTOM: black 1px solid"><div align=left>Medio Pago</div></TD>
          <TD style="BORDER-BOTTOM: black 1px solid; BORDER-RIGHT: black 1px solid" width="200">
          <div align=left>&nbsp;
           		 <%   if(bolgaran.getE01CBRFLG().equals("1")) out.print("CUENTA CORRIENTE");
                 else if(bolgaran.getE01CBRFLG().equals("2")) out.print("CAJA");
                 else out.print("");%>
		  </div></TD>
        </TR>
        <TR>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD colspan="2">&nbsp;</TD>
        </TR>
        <TR id="trcolor">
          <TD colSpan=2 id=trdark style="BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid" >
          <div align=center>Datos del Beneficiario</div></TD>

        </TR>
        <TR id="trcolor">
          <TD style="BORDER-LEFT: black 1px solid; HEIGHT:10px" valign=bottom><div align=left>Rut</div></TD>
          <TD style="BORDER-RIGHT: black 1px solid" valign=bottom><div align=left>&nbsp;<%= datapro.eibs.platform.CBUtil.formatRUT(bolgaran.getE01BNFRUT().trim())%></div></TD>
          <TD style="background-color:#ffffff" colspan="2" rowspan="4" align=center><IMG src="<%=request.getContextPath()%>/images/s/timbre.gif" name=timbre></TD>
        </TR>
        <TR id="trcolor">
          <TD style="BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid;HEIGHT:10px"><div align=left>Nombre</div></TD>
          <TD style="BORDER-RIGHT: black 1px solid; BORDER-BOTTOM: black 1px solid" valign=top><div align=left>&nbsp;<%= bolgaran.getE01BNFNME().trim()%></div></TD>
 		</TR>

        <TR>
          <TD colSpan=2><div align=left>RETIRE CONFORME</div></TD>
        </TR>

        <TR>
          <TD colSpan=2><div align=left>FIRMA CLIENTE __________________________________</div></TD>
        </TR>

 		</TABLE>
      </TD>
	</TR>
</TABLE>

<BR>

<% if ( !comision_str.equals("0") ) { %>

<TABLE  style="background-color: #ffffff" cellSpacing=1 cellPadding=1 width="610" border=0>

  <TR>
    <TD>
       <TABLE style="background-color: #ffffff" cellSpacing="0" cellPadding="0" width="100%" border="0">

        <TR>
          <TD width=10>&nbsp;</TD>
          <TD width=290>&nbsp;</TD>
          <TD width=100>&nbsp;</TD>
          <TD width=200 colspan="3">&nbsp;</TD>
        </TR>


		<h4></h4>
		<h3</h3>
		<hr size="4">

        <TR>
           <TD style="HEIGHT:20PX" colSpan="6"><div align=center><b>VALIDO COMO FACTURA</b></div></TD>
        </TR>

        <TR>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD colspan="3"><div align=left>Nro. FOLIO&nbsp;<%= bolgaran.getE01LCMACC().trim()%></div></TD>
        </TR>
        <TR>
          <TD style="HEIGHT:20PX"><div align=left>SEÑOR(ES) </div></TD>
          <TD><div align=left>&nbsp;<%= bolgaran.getE01APLNME().trim()%></TD>
          <TD>&nbsp;</TD>
          <TD style="HEIGHT:20PX" colspan="3"><div align=left>CORPBANCA</div> </TD>
        </TR>
        <TR>
          <TD style="HEIGHT:20PX"><div align=left>RUT</div></TD>
          <TD><div align=left>&nbsp;<%= datapro.eibs.platform.CBUtil.formatRUT(bolgaran.getE01APLRUT().trim())%></div></TD>
          <TD>&nbsp;</TD>
          <TD style="HEIGHT:20PX" colspan="3"><div align=left>RUT 97.023.000-9 </div></TD>
        </TR>
        <TR>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD style="HEIGHT:20PX" colspan="3"><div align=left>HUERFANOS 1072</div> </TD>
        </TR>
        <TR>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD style="HEIGHT:20PX" colspan="3"><div align=left>SANTIAGO </div></TD>
        </TR>
        <TR>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD colspan="3">&nbsp;</TD>
        </TR>
        <TR>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD colspan="3">&nbsp;</TD>
        </TR>
        <TR>
          <TD style="HEIGHT:20PX" colSpan="6" ><div align=left>Por la presente comunicamos a Ud. que por concepto de comisión sobre la emisión de boleta de garantia, hemos emitido el presente documento valido como factura.</div></TD>
        </TR>

         <TR>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD colspan="2">&nbsp;</TD>
          <TD>&nbsp;</TD>
         </TR>
         <TR>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD colspan="2">&nbsp;</TD>
          <TD>&nbsp;</TD>
        </TR>
        <TR>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD style="HEIGHT:20PX"></TD>
          <TD style="HEIGHT:5PX" colspan="2"></TD>
          <TD style="HEIGHT:20PX"></TD>
        </TR>
        <TR>
          <TD>&nbsp;</TD>
          <TD><div align=right>Comisión&nbsp;</div></TD>
          <TD style="HEIGHT:20PX"><div align=left>(iva incluido) $</div></TD>
          <TD style="HEIGHT:5PX" colspan="2"><div align=right>&nbsp;<%= datapro.eibs.platform.CBUtil.formatAmount(datapro.eibs.platform.CBUtil.unFormatAmount(bolgaran.getE01LCMC11().trim()))%></TD>
          <TD style="HEIGHT:20PX"></TD>
        </TR>
        <TR>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD style="HEIGHT:20PX"></TD>
          <TD style="HEIGHT:5PX" colspan="2"></TD>
          <TD style="HEIGHT:20PX"></TD>
        </TR>
        <TR>
          <TD style="HEIGHT:20PX"><div align=left>Fecha</TD>
          <TD><div align=left>&nbsp;<%= Util.formatDate(bolgaran.getE01LCMIDM(),bolgaran.getE01LCMIDD(),bolgaran.getE01LCMIDY())%></div></TD>
          <TD>&nbsp;</TD>
          <TD colspan="2">&nbsp;</TD>
          <TD>&nbsp;</TD>
        </TR>
        <TR>
          <TD style="HEIGHT:20PX"><div align=left>CORPBANCA</div></TD>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
		</TR>
        <TR>
          <TD style="HEIGHT:20PX"><div align=left>OFICINA</div></TD>
          <TD style="HEIGHT:20PX"><div align=left>&nbsp;<%= datapro.eibs.platform.CBUtil.getBranchName("01", bolgaran.getE01LCMBRN().trim())%></TD>
          <TD>&nbsp;</TD>
          <TD colspan="2">&nbsp;</TD>
          <TD>&nbsp;</TD>
        </TR>
        <TR>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD>&nbsp;</TD>
          <TD colspan="2">&nbsp;</TD>
          <TD>&nbsp;</TD>
        </TR>

 		</TABLE>
      </TD>
	</TR>
</TABLE>



<% } %>


 <center>
 <TABLE class=tbenter width="100%" align=center>
  <TR>
    <TD align=middle width="100%">
	<div align="center" id ="div1">
		<img style="cursor: hand;" name="Submit" src="<%=request.getContextPath()%>/images/s/bt_imprimir.gif"  onMouseDown="this.className='' "  onMouseUp="this.className='imgfilter' "  OnClick="PrintPreview();" >
	</div>
    </TD>
  </TR>
 </TABLE>
 </center>

  <BR>

</FORM>

</BODY>
</HTML>

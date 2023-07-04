<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Draft Docs Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<jsp:useBean id="dftBasic" class="datapro.eibs.beans.EDL080001Message"  scope="session" />

<jsp:useBean id="dftAcceptor" class="datapro.eibs.beans.EDL080003Message"  scope="session" />

<jsp:useBean id= "lstDocuments" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT Language="Javascript">

<%if (userPO.getPurpose().equals("MAINTENANCE")){%>
   
    builtNewMenu(ln_m_opt);

<%}%>
  builtHPopUp();
function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
}

function goAction(opt) {
	   document.forms[0].ACTION.value = opt;
       var formLength= document.forms[0].elements.length;
       var ok = false;
       var nmevar = '';
       for(n=0;n<formLength;n++)
       {
      	  var elementName= document.forms[0].elements[n].name;
      	  
      	  for(m=0; m <= document.forms[0].NUMRECORDS.value;m++){
      	    nmevar = "NUMSEQ"+m;
      	  	if(elementName == nmevar && document.forms[0].elements[n].checked) {
        		  ok = true;
        		  break;
      	 	}
      	  }
       }
    if (ok == false && opt == "N") ok = true;

	if ( ok ) {
		if (opt == "A") {
			var where_to = confirm("¿Está seguro de que quiere eliminar todos los documentos?");
			if (where_to == true) {
				document.forms[0].submit();
			}
		}
		else if (opt == "D" || opt == "N") {
			document.forms[0].submit();
		}

	}
	else
	{
		alert("debe seleccionar al menos un registro");
	
	}

}
</SCRIPT>

</head>
<body nowrap>
<% 
 String strBlank = "";
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<h3 align="center">Detalle de Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dft_acceptor_docs_detail.jsp,EDL0800"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1000">
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NUMRECORDS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NUMINI" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="FREC" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TYPFREC" VALUE="">
  <% //TYPFREC : D - days , M - months , Y - years %>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <INPUT type="text" name="E01DEACUN" size="9" maxlength="9" value="<%= userPO.getHeader2()%>" readonly>
                </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <INPUT type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= userPO.getHeader3() %>" readonly>
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <INPUT type="text" name="E01DEAACC" size="12" maxlength="12" value="<%= userPO.getIdentifier() %>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <INPUT type="text" name="E01DEACCY" size="4" maxlength="3" value="<%= userPO.getCurrency() %>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <INPUT type="text" name="E01DEAPRO" size="4" maxlength="4" value="<%= userPO.getHeader1() %>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Documentos del Aceptante</h4> 
<TABLE cellpadding="2" cellspacing="0" width="100%" border="0" class="tableinfo">
 <tr id="trdark">
  <TD nowrap width="16%"> 
  <div align="right">Identificación :</div>
  </TD>
  <TD nowrap width="20%"> 
  <INPUT type="text" name="E03NUMIDE" size="18" maxlength="2" value="<%= dftAcceptor.getE03NUMIDE() %>" readonly>
  </TD>
  <TD nowrap width="16%">
  <div align="right">Nombre :</div>
  </TD>
  <TD nowrap width="20%">
  <INPUT type="text" name="E03CUMMA1" size="35" maxlength="30" value="<%= dftAcceptor.getE03CUMMA1() %>" readonly>
  </TD>
 </tr>  
</TABLE>
<TABLE class="tbenter" width="100%" border="0">
    <TR>
		<td ALIGN=CENTER class=TDBKG> 
			<div align="center"><a href="javascript:goAction('N')"><b>Agregar</b></a></div>
		</td>
		<td ALIGN=CENTER class=TDBKG> 
			<div align="center"><a href="javascript:goAction('D')"><b>Borrar Selección</b></a></div>
		</td>
		<td ALIGN=CENTER class=TDBKG> 
			<div align="center"><a href="javascript:goAction('A')"><b>Borrar Todos</b></a></div>
		</td>
	</TR>
</TABLE>

<TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER NOWRAP>
<tr>
<td nowrap> 
<TABLE id="headTable" NOWRAP>
<TR id="trdark"> 
   
   	<%
   	// checks
		 
		 if (userPO.getHeader8().equals("4"))
		 
		 { %>
	
	<TH ROWSPAN=2 NOWRAP></TH>
    <TH ROWSPAN=2 NOWRAP>Documento</TH>
    <TH ROWSPAN=2 NOWRAP>Fecha<br>Pago</TH>
   <TH ROWSPAN=2 NOWRAP>Valor<br> cheque</TH>
   <TH ROWSPAN=2 NOWRAP>Fecha<br>Vence</TH>
   <TH ROWSPAN=2 NOWRAP>Tipo de<br>Cambio</TH>
   <TH ROWSPAN=2 NOWRAP>Instr.<br>Cobro</TH>
   <TH ROWSPAN=2 NOWRAP>Banco</TH>
   <TH ROWSPAN=2 NOWRAP>Sucur.</TH>
   <TH ROWSPAN=2 NOWRAP>Cuenta<br>Cheque</TH>
   <TH ROWSPAN=2 NOWRAP>Numero<br>Cheque</TH>
	
   <% 
   // other documents
   
   
   } else { %>
   
   
   <TH ROWSPAN=2 NOWRAP></TH>
   <TH ROWSPAN=2 NOWRAP>Secuencia</TH>
   <TH ROWSPAN=2 NOWRAP>Fecha de<br>Vencimiento</TH>
   <TH ROWSPAN=2 NOWRAP>Monto</TH>
   <TH ROWSPAN=2 NOWRAP>Tasa</TH>
   <TH ROWSPAN=2 NOWRAP>Cuenta de<br>Débito<br>Automática</TH>
   <TH ROWSPAN=2 NOWRAP>Ruta de<br>Cobro</TH>
   <TH ROWSPAN=2 NOWRAP>Tipo de<br>Cambio</TH>
   
   <% } %>
   
</TR>
</TABLE>
<div id="dataDiv1" class="scbarcolor" NOWRAP>
<table id="dataTable" NOWRAP>
    <%
       lstDocuments.initRow();
	boolean firstTime = true;
       int k = 0;
	String chk = "";
	String typchg = "";
	while (lstDocuments.getNextRow()) {
         if (lstDocuments.getFlag().equals("")) {
     		out.print("<tr id=\"trclear\">");
            if (firstTime) {
               firstTime = false; 
               out.print("<SCRIPT>document.forms[0].NUMINI.value=\""+lstDocuments.getRecord(0)+"\";");
   			   out.print("</SCRIPT>");
             } else chk = "";

		 
		// checks
		 
		 if (userPO.getHeader8().equals("4"))
		 
		 {
		
		 out.print("<TD nowrap>");
		 out.print("<INPUT type=\"hidden\" name=\"CURSEQ" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(0) +"\" >");
		 out.print("<INPUT type=\"checkbox\" name=\"NUMSEQ" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(0) +"\" " + chk + " >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"9\" maxlength=\"9\" type=\"text\" name=\"E02DLDNDR" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(0) + "\" >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDPY1" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(14).trim() + "\" >");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDPY2" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(15).trim() + "\" >");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDPY3" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(16).trim() + "\" >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"15\" maxlength=\"15\" type=\"text\" name=\"E02DLDOAM" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(4) + "\" onKeyPress=\"enterDecimal()\" >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDMA1" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(1).trim() + "\" >");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDMA2" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(2).trim() + "\" >");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDMA3" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(3).trim() + "\" >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<select name=\"E02DLDEXT" + String.valueOf(k).trim() + "\">");
		 if (lstDocuments.getRecord(7) == null) typchg = " ";
		 else typchg = lstDocuments.getRecord(7).trim();
		 out.print("<option value=\" \" "+ (typchg.trim().equals("")?"selected":" ")+"></option>");
		 out.print("<option value=\"1\" "+ (typchg.trim().equals("1")?"selected":" ")+">Oficial</option>");
         out.print("<option value=\"2\" "+ (typchg.trim().equals("2")?"selected":" ")+">Observado</option>");
         out.print("<option value=\"3\" "+ (typchg.trim().equals("3")?"selected":" ")+">Compra/Venta</option>");
         out.print("</select>");
		 out.print("</TD>");

		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"5\" maxlength=\"4\" type=\"text\" name=\"E02DLDCOI" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(17) + "\"  >");
         out.print("<a href=\"javascript:GetCodeCNOFC('E02DLDCOI"+String.valueOf(k).trim()+"','45')\"><img src=\""+request.getContextPath()+"/images/1b.gif\" alt=\"Ayuda\" align=\"absbottom\" border=\"0\" ></a>");
		 out.print("</TD>");

		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"5\" maxlength=\"4\" type=\"text\" name=\"E02DLDKBK" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(18) + "\"  >");
		 out.print("</TD>");

		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"5\" maxlength=\"4\" type=\"text\" name=\"E02DLDKBR" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(19) + "\"  >");
         out.print("<a href=\"javascript:GetBanksSuc('E02DLDKBK"+String.valueOf(k).trim()+"','E02DLDKBR"+String.valueOf(k).trim()+ "')\"><img src=\""+request.getContextPath()+"/images/1b.gif\" alt=\"Ayuda\" align=\"absbottom\" border=\"0\" ></a>");
		 out.print("</TD>");



		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"13\" maxlength=\"12\" type=\"text\" name=\"E02DLDCTA" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(20) + "\" onKeyPress=\"enterInteger()\" >");
		 out.print("</TD>");

		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"10\" maxlength=\"9\" type=\"text\" name=\"E02DLDCHQ" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(21) + "\" onKeyPress=\"enterInteger()\" >");
		 out.print("</TD>");
		
		}				
		
		else
							
		 {
		 
		 // other documents
		 
		 out.print("<TD nowrap>");
		 out.print("<INPUT type=\"hidden\" name=\"CURSEQ" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(0) +"\" >");
		 out.print("<INPUT type=\"checkbox\" name=\"NUMSEQ" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(0) +"\" " + chk + " >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"9\" maxlength=\"9\" type=\"text\" name=\"E02DLDNDR" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(0) + "\" >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDMA1" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(1).trim() + "\" >");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDMA2" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(2).trim() + "\" >");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDMA3" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(3).trim() + "\" >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"15\" maxlength=\"15\" type=\"text\" name=\"E02DLDOAM" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(4) + "\" onKeyPress=\"enterDecimal()\" >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"10\" maxlength=\"10\" type=\"text\" name=\"E02DLDARC" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(5) + "\" onKeyPress=\"enterDecimal()\" >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"20\" maxlength=\"20\" type=\"text\" name=\"E02DLDACC" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(6) + "\" >");
		 out.print("<a href=\"javascript:GetAccount('E02DLDACC"+String.valueOf(k).trim()+"','','RT','')\"><img src=\""+request.getContextPath()+"/images/1b.gif\" alt=\"Ayuda\" align=\"absbottom\" border=\"0\" ></a>");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"6\" maxlength=\"6\" type=\"text\" name=\"E02DLDREW" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(10) + "\" >");
		 out.print("<a href=\"javascript:GetCodeCNOFC('E02DLDREW"+String.valueOf(k).trim()+"','42')\"><img src=\""+request.getContextPath()+"/images/1b.gif\" alt=\"Ayuda\" align=\"absbottom\" border=\"0\" ></a>");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<select name=\"E02DLDEXT" + String.valueOf(k).trim() + "\">");
		 if (lstDocuments.getRecord(7) == null) typchg = " ";
		 else typchg = lstDocuments.getRecord(7).trim();
		 out.print("<option value=\" \" "+ (typchg.trim().equals("")?"selected":" ")+"></option>");
		 out.print("<option value=\"1\" "+ (typchg.trim().equals("1")?"selected":" ")+">Oficial</option>");
         out.print("<option value=\"2\" "+ (typchg.trim().equals("2")?"selected":" ")+">Observado</option>");
         out.print("<option value=\"3\" "+ (typchg.trim().equals("3")?"selected":" ")+">Compra/Venta</option>");
         out.print("</select>");
		 out.print("</TD>");
		 }
		 
		 
		 
		 out.print("</tr>");
         k++;
         if ( k > 10 ) {     %>
        <SCRIPT Language="Javascript">
               document.forms[0].totalRow.value="<%= k %>";
   		</SCRIPT>	 
  		<%	}  %>
		<SCRIPT Language="Javascript">
               document.forms[0].NUMRECORDS.value="<%= k %>";
   		</SCRIPT>
		<%
       }
    }
    %> 
</table>
</div>
   
</TD>
</TR>	
</TABLE>
<br>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>

<SCRIPT language="JavaScript">
     function resizeDoc() {
	divResize2();
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     resizeDoc();
     window.onresize=resizeDoc;
     
function divResize2() {
  var minValue= mainTable.offsetTop + dataDiv1.offsetTop + 30;
  var h = document.body.clientHeight - minValue ;
  var totalrow= parseInt(document.forms[0].totalRow.value);
  var maxHeight= totalrow * 20; 
  
  if (totalrow > 10) {
     dataDiv1.style.height= maxHeight + ""; 
     dataDiv1.style.overflowY="scroll";
  } else {
	dataDiv1.style.height= maxHeight + ""; 
   	dataDiv1.style.overflowY="";
  } 
}

</SCRIPT>
</form>
</body>
</html>

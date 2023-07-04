<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Mantenimiento Documentos Cobranza</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<jsp:useBean id="dftBasic" class="datapro.eibs.beans.EDL081001Message"  scope="session" />

<jsp:useBean id="dftAcceptor" class="datapro.eibs.beans.EDL081003Message"  scope="session" />

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
          document.forms[0].submit();
       } else {
          alert("you must select at least one document...");
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
<h3 align="center">Detalle de Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dft_acceptor_docs_detail.jsp,EDL0810"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0810" >
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
  <h4>Documentos de Aceptante</h4> 
<TABLE cellpadding="2" cellspacing="0" width="100%" border="0" class="tableinfo">
 <tr id="trdark">
  <TD nowrap width="16%"> 
  <div align="right">Identificación :</div>
  </TD>
  <TD nowrap width="20%"> 
  <INPUT type="text" name="E03DLDIDA" size="18" maxlength="2" value="<%= dftAcceptor.getE03DLDIDA() %>" readonly>
  </TD>
  <TD nowrap width="16%">
  <div align="right">Nombre :</div>
  </TD>
  <TD nowrap width="20%">
  <INPUT type="text" name="E03DLDMA1" size="35" maxlength="30" value="<%= dftAcceptor.getE03DLDMA1() %>" readonly>
  </TD>
 </tr>
</TABLE>
<TABLE class="tbenter" width="100%" border="0">
    <TR>
            <TD ALIGN=CENTER width="33%" colspan="2">
  		<a href="javascript:goAction('N')" id="linkAdd" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/anadir_over.gif',1)"><img name="Image1" alt="Añadir" border="0" src="<%=request.getContextPath()%>/images/s/anadir.gif" ></a>
      </TD>
            <TD ALIGN=CENTER width="33%" colspan="2">
  		<td class=TDBKG width="53%"> 
        <div align="center"><a href="javascript:goAction('D')"><b>Borrar Selecci&oacute;n</b></a></div>
      </td>
        </TR>
  </TABLE>

<TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER NOWRAP>
<tr>
<td nowrap> 
<TABLE id="headTable" NOWRAP>
<TR id="trdark"> 
   <TH ROWSPAN=2 NOWRAP></TH>
   <TH ROWSPAN=2 NOWRAP>N.Letra</TH>
   <TH ROWSPAN=2 NOWRAP>Secuencia</TH>
   <TH ROWSPAN=2 NOWRAP>Fecha de Vcto</TH>
   <TH ROWSPAN=2 NOWRAP>Monto</TH>
   <TH ROWSPAN=2 NOWRAP>Cuenta de D&eacute;bito Aut.</TH>
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
		 out.print("<TD nowrap>");
		 out.print("<INPUT type=\"hidden\" name=\"CURSEQ" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(0) +"\" >");
		 out.print("<INPUT type=\"checkbox\" name=\"NUMSEQ" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(0) +"\" " + chk + " >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"12\" readonly maxlength=\"12\" type=\"text\" name=\"DDIB" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(9) + "\" >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"12\" maxlength=\"12\" type=\"text\" name=\"E02DLDNRI" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(0) + "\" >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDFV1" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(1).trim() + "\" >");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDFV2" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(2).trim() + "\" >");
		 out.print("<INPUT size=\"2\" maxlength=\"2\" type=\"text\" name=\"E02DLDFV3" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(3).trim() + "\" >");
		 out.print("</TD>");
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"15\" maxlength=\"15\" type=\"text\" name=\"E02DLDAMT" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(4) + "\" onkeypress=\"enterDecimal()\">");
		 out.print("</TD>");
		 
		 out.print("<TD nowrap>");
		 out.print("<INPUT size=\"20\" maxlength=\"20\" type=\"text\" name=\"E02DLDNDA" + String.valueOf(k).trim() + "\" value=\"" + lstDocuments.getRecord(6) + "\" >");
		 out.print("<a href=\"javascript:GetAccount('E02DLDNDA"+String.valueOf(k).trim()+"','','RT','')\"><img src=\""+request.getContextPath()+"/images/1b.gif\" alt=\"Ayuda\" align=\"absbottom\" border=\"0\" ></a>");
		 out.print("</TD>");
		 
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

<p align="center">&nbsp; </p>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"></div>
      </td>
      <td width="34%"> 
        <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
      </td>
      <td width="33%"> 
        <div align="center"></div>
      </td>
    </tr>
  </table>
  <p align="center">&nbsp; </p>
  </form>
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
</body>
</html>

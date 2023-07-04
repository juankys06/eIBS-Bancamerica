<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Aprobación Descuento de Documentos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<jsp:useBean id="dftBasic" class="datapro.eibs.beans.EDL080001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "lstAcceptors" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "lstDocuments" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<SCRIPT Language="Javascript">

<%if (userPO.getPurpose().equals("MAINTENANCE")){%>
   
    builtNewMenu(dft_m_opt);

<%}%>
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
}

</SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
if (userPO.getPurpose().equals("MAINTENANCE")){
 out.println("<SCRIPT>  initMenu(); </SCRIPT>");}

  String DEAIPD, DEAPPD;
  String E01FLGDED, E01FLGREF,E01FLGPAY,E01FLGCOL,E01DEACLF;
  boolean isDEAIPDNum = true;
  boolean isDEAPPDNum = true;
  //DEAIPD = lnBasic.getE01DEAIPD().trim();
  //DEAPPD = lnBasic.getE01DEAPPD().trim();
  //E01FLGDED = lnBasic.getE01FLGDED().trim();
  //E01FLGREF = lnBasic.getE01FLGREF().trim();
  //E01FLGPAY = lnBasic.getE01FLGPAY().trim();
  //E01FLGCOL = lnBasic.getE01FLGCOL().trim();
  //E01DEACLF = lnBasic.getE01DEACLF().trim();
  
  String genFlag = "";
  try {
      genFlag = request.getParameter("generic").trim();
      if (genFlag == null) genFlag = "";
  } catch (Exception e) {
    //genFlag = lnBasic.getE01DEACLF().trim();
  }
	
  String strBlank = "";

%> 
<h3 align="center"> <%= dftBasic.getE01DSCPRO().trim() %><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dft_basic.jsp,EDL0800"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="502">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="F">
  <INPUT TYPE=HIDDEN NAME="E01DEABNK" value="<%= dftBasic.getE01DEABNK() %>">
  <input type=HIDDEN name="E01DEATYP" value="<%= dftBasic.getE01DEATYP() %>">
  <input type=hidden name="GRP" value="0">

  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E01DEACUN" size="9" maxlength="9" value="<%= dftBasic.getE01DEACUN() %>" readonly>
                </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= dftBasic.getE01CUSNA1() %>" readonly>
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
<%            
			  String strNroAcc = dftBasic.getE01DEAACC();
              if (dftBasic.getE01DEAACC().equals("999999999999")) {
              	strNroAcc = "NUEVA CUENTA";	  
              }
%>
                <input type="hidden" name="E01DEAACC" size="12" maxlength="12" value="<%= dftBasic.getE01DEAACC()  %>">
                <input type="text" name="E01DEAACCDSC" size="12" maxlength="12" value="<%= strNroAcc  %>" readonly>              

              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="4" maxlength="3" value="<%= dftBasic.getE01DEACCY() %>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01DEAPRO" size="4" maxlength="4" value="<%= dftBasic.getE01DEAPRO() %>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información Basica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Apertura:</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAOD1" <% if (dftBasic.getF01DEAOD1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= dftBasic.getE01DEAOD1() %>" readonly>
              <input type="text" name="E01DEAOD2" <% if (dftBasic.getF01DEAOD2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= dftBasic.getE01DEAOD2() %>" readonly>
              <input type="text" name="E01DEAOD3" <% if (dftBasic.getF01DEAOD3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= dftBasic.getE01DEAOD3() %>" readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap width="27%">
		<input type="text" name="E01DEAOAM" <% if (dftBasic.getF01DEAOAM().equals("Y")) out.print("id=\"txtchanged\""); %> size="15" maxlength="15" value="<%= dftBasic.getE01DEAOAM() %>" readonly>
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Tasa Flotante:</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAFTB" <% if (dftBasic.getF01DEAFTB().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= dftBasic.getE01DEAFTB() %>" readonly> <select name="E01DEAFTY" disabled>
                            <OPTION value=" " <% if (!(dftBasic.getE01DEAFTY().equals("FP") ||dftBasic.getE01DEAFTY().equals("FS"))) out.print("selected"); %>=""></OPTION>
                            <OPTION value="FP" <% if (dftBasic.getE01DEAFTY().equals("FP")) out.print("selected"); %>="">FP</OPTION>
                            <OPTION value="FS" <% if (dftBasic.getE01DEAFTY().equals("FS")) out.print("selected"); %>="">FS</OPTION>
                        </select>
              <input type="text" name="E01FLTRTE" <% if (dftBasic.getF01FLTRTE().equals("Y")) out.print("id=\"txtchanged\""); %> size="9" maxlength="9" value="<%= dftBasic.getE01FLTRTE() %>" readonly>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa de Inter&eacute;s/Spread :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEARTE" <% if (dftBasic.getF01DEARTE().equals("Y")) out.print("id=\"txtchanged\""); %> size="10" maxlength="9" value="<%= dftBasic.getE01DEARTE() %>" readonly> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tasa / Cargo por Mora:</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAPEI" <% if (dftBasic.getF01DEAPEI().equals("Y")) out.print("id=\"txtchanged\""); %> size="8" maxlength="7" value="<%= dftBasic.getE01DEAPEI() %>"  readonly>
              <input type="text" name="E01DEAPIF" <% if (dftBasic.getF01DEAPIF().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= dftBasic.getE01DEAPIF() %>"  readonly> </td>
            <td nowrap width="25%" > 
              <div align="right">N&uacute;mero Referencia:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAREF" <% if (dftBasic.getF01DEAREF().equals("Y")) out.print("id=\"txtchanged\""); %> size="12" maxlength="12" value="<%= dftBasic.getE01DEAREF() %>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Línea de Cr&eacute;dito :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEACMC" <% if (dftBasic.getF01DEACMC().equals("Y")) out.print("id=\"txtchanged\""); %> size="9" maxlength="9" value="<%= dftBasic.getE01DEACMC() %>"  readonly>
              <input type="text" name="E01DEACMN" <% if (dftBasic.getF01DEACMN().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="4" value="<%= dftBasic.getE01DEACMN() %>"  readonly> </td>
            <td nowrap width="25%" > 
              <div align="right">Centro de Costo : </div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEACCN" <% if (dftBasic.getF01DEACCN().equals("Y")) out.print("id=\"txtchanged\""); %> size="8" maxlength="8" value="<%= dftBasic.getE01DEACCN() %>" readonly> </td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Retención de Impuestos:</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAWHF" <% if (dftBasic.getF01DEAWHF().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= dftBasic.getE01DEAWHF() %>" readonly> </td>
            <td nowrap width="25%" > 
              <div align="right">Dirección de Correo:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAMLA" <% if (dftBasic.getF01DEAMLA().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= dftBasic.getE01DEAMLA() %>" readonly> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Comisionista :</div>
           </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEABRK" <% if (dftBasic.getF01DEABRK().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= dftBasic.getE01DEABRK() %>" readonly> </td>
            <td nowrap width="25%" > 
              <div align="right">Porcentaje de Comisión :</div>
            </td>
            <td nowrap width="27%" > 
		<input type="text" name="E01DEABCP" <% if (dftBasic.getF01DEABCP().equals("Y")) out.print("id=\"txtchanged\""); %> size="10" maxlength="9" value="<%= dftBasic.getE01DEABCP() %>"  readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Documento:</div>
            </td>
            <td nowrap width="23%" >
		<select name="E01DEARET" disabled>
                            <OPTION value=" " <% if (dftBasic.getE01DEARET().trim().equals(""))  out.print("selected"); %>=""></OPTION>
                            <OPTION value="1" <% if (dftBasic.getE01DEARET().equals("1"))  out.print("selected"); %>="">Giros o Letras</OPTION>
                            <OPTION value="2" <% if (dftBasic.getE01DEARET().equals("2")) out.print("selected"); %>="">Conformes</OPTION>
                            <OPTION value="3" <% if (dftBasic.getE01DEARET().equals("3")) out.print("selected"); %>="">Facturas</OPTION>
                            <OPTION value="4" <% if (dftBasic.getE01DEARET().equals("4")) out.print("selected"); %>="">Cheques</OPTION>
                            <OPTION value="5" <% if (dftBasic.getE01DEARET().equals("5")) out.print("selected"); %>="">Cupones MasterCard</OPTION>
                        </select>

              </td>
            <td nowrap width="25%" ><INPUT type="hidden" name="E01DEACLF" size="2" maxlength="1" value="<%= dftBasic.getE01DEACLF() %>" readonly></td>
            <td nowrap width="27%" > 
            </td>
          </tr>
          
          <%if (userPO.getPurpose().equals("MAINTENANCE")){%> 
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Inter&eacute;s:</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAICT" <% if (dftBasic.getF01DEAICT().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= dftBasic.getE01DEAICT() %>" readonly>
              </td>
            <td nowrap width="25%" > 
              <div align="right">Período Base :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEABAS" <% if (dftBasic.getF01DEABAS().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= dftBasic.getE01DEABAS() %>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Cálculo de Inter&eacute;s Normal:</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAIFL" <% if (dftBasic.getF01DEAIFL().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= dftBasic.getE01DEAIFL() %>" readonly> </td>
            <td nowrap width="25%" > 
              <div align="right">Cálculo de Inter&eacute;s Mora:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAPCL" <% if (dftBasic.getF01DEAPCL().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= dftBasic.getE01DEAPCL() %>"  readonly>
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Tabla Cargos :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEATLN" <% if (dftBasic.getF01DEATLN().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= dftBasic.getE01DEATLN() %>" readonly> </td>
            <td nowrap width="25%" > 
              <div align="right">Período de Gracia:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAGPD" <% if (dftBasic.getF01DEAGPD().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= dftBasic.getE01DEAGPD() %>"  readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tabla Tasas:</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEARTB" <% if (dftBasic.getF01DEARTB().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= dftBasic.getE01DEARTB() %>" readonly> </td>
            <td nowrap width="25%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="27%" >
              </td>
          </tr>
          <%}%> 
        </table>
      </td>
    </tr>
  </table>

<h4>Cuenta de Pago</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="center">Concepto</div>
            </td>
            <td nowrap width="15%"> 
              <div align="center">Banco</div>
            </td>
            <td nowrap width="22%"> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap> 
              <div align="center">Moneda </div>
            </td>
            <td nowrap> 
              <div align="center">Referencia</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > 
              <div align="center" nowrap> 
                <input type="text" name="E01PAGOPE" value="<%= dftBasic.getE01PAGOPE() %>" size="3" maxlength="3" readonly>
                <input type="hidden" name="E01PAGGLN" value="<%= dftBasic.getE01PAGGLN() %>">
                <input type="text" name="E01PAGCON" size="25" maxlength="25" readonly value="<%= dftBasic.getE01PAGCON() %>"
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01DEABNK.value,'','E01PAGGLN','E01PAGOPE','10')">
			  </div>
            </td>
            <td nowrap width="15%" > 
              <div align="center"> 
                <input type="text" name="E01PAGBNK" <% if (dftBasic.getF01PAGBNK().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= dftBasic.getE01PAGBNK() %>" readonly>
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="center"> 
                <input type="text" name="E01PAGBRN" <% if (dftBasic.getF01PAGBRN().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="3" value="<%= dftBasic.getE01PAGBRN() %>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01DEABNK.value,'','','','')" readonly>
			  </div>
            </td>
            <td nowrap width="14%" > 
              <div align="center"> 
                <input type="text" name="E01PAGCCY" <% if (dftBasic.getF01PAGCCY().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="3" value="<%= dftBasic.getE01PAGCCY() %>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01DEABNK.value,'','','','')" readonly>
			</div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" name="E01PAGACC" <% if (dftBasic.getF01PAGACC().equals("Y")) out.print("id=\"txtchanged\""); %> size="12" maxlength="12"  value="<%= dftBasic.getE01PAGACC() %>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01DEABNK.value,'','','','RT')" readonly>
			  </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" >&nbsp;</td>
            <td nowrap width="15%" >&nbsp;</td>
            <td nowrap width="22%" >&nbsp;</td>
            <td nowrap width="14%" >&nbsp;</td>
            <td nowrap width="16%" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" >Autorización Sobregiro : <input type="text" name="E01DEAODA" <% if (dftBasic.getF01DEAODA().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= dftBasic.getE01DEAODA() %>" readonly> </td>
            <td nowrap width="15%" > 
              <div align="center"> </div>
            </td>
            <td nowrap width="22%" > 
              <div align="right">Autoriza Pagos en Feriados: </div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left">
                <input type="hidden" name="E01DEAHFQ" value="<%= dftBasic.getE01DEAHFQ() %>">
                <input type="radio" name="CE01DEAHFQ" value="1" onClick="document.forms[0].E01DEAHFQ.value='1'"
			  <%if(dftBasic.equals("1")) out.print("checked");%> readonly> Si <input type="radio" name="CE01DEAHFQ" value="2" onClick="document.forms[0].E01DEAHFQ.value='2'"
			  <%if(dftBasic.equals("2")) out.print("checked");%> readonly>
                No </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

<h4>Aceptantes</h4>

<TABLE class="tableinfo">
   <tr > 
      <td nowrap>
            <table cellpadding="2" cellspacing="0" width="100%" border="0">

    <TR bgcolor="#FFFFFF">
    <TD width="14">
    </TD>
    <TD colspan="2" align="center" width="394"> </TD>
    <TD align="left" colspan="4" width="161">
    </TD>
    <TD width="311">
    </TD>
    </TR>
    
                        <% 
	lstAcceptors.initRow();
	boolean firstTime = true;
	String chk = "";
     	while (lstAcceptors.getNextRow()) {
     		out.print("<tr id=\"trdark\">");
    		out.print("<TD nowrap>"); 
    		out.print("<div align=\"center\">Identificación</div>"); 
    		out.print("</TD>");
    		out.print("<TD nowrap>");
    		out.print("<div align=\"center\">Nombre</div>");
    		out.print("</TD>");
    		out.print("<TD nowrap>");
    		out.print("</TD>");
    		out.print("</TR>");
    		
         	if (lstAcceptors.getFlag().equals("")) {
     			out.print("<tr id=\"trclear\">");
				out.print("<TD nowrap width=\"162\">");
				out.print("<div align=\"center\"><INPUT size=\"20\" type=\"text\" name=\"IDACCEPTOR\" value=\"" + lstAcceptors.getRecord(0) + "\" readonly></div>");
				out.print("</TD>");
				out.print("<TD nowrap width=\"151\">");
				out.print("<div align=\"center\"><INPUT size=\"35\" type=\"text\" name=\"NMEACCEPTOR\" value=\"" + lstAcceptors.getRecord(1) + "\" readonly></div>");
				out.print("</TD>");
				out.print("<TD nowrap width=\"232\">");
				
				out.print("</TD>");
				out.print("</tr>");
				lstDocuments.initRow();
				firstTime = true;
				while (lstDocuments.getNextRow()) {
					if (lstDocuments.getFlag().equals("") && lstDocuments.getRecord(8).equals(lstAcceptors.getRecord(0))) {
					    if (firstTime) {
					         out.print("<tr>");
					         out.print("<td colspan=\"3\">");
					         
					         out.print("<table>");
					         firstTime = false;
					         out.print("<tr>");
						     out.print("<TD>");
						     out.print("</td>");
						     out.print("<TD>");
							 out.print("</td>");
							 out.print("<TD>");
							 out.print("<div align=\"center\">Secuencia</div>");
							 out.print("</TD>");
							 out.print("<TD>");
							 out.print("<div align=\"center\">Fecha Vcto</div>");
							 out.print("</TD>");
							 out.print("<TD>");
							 out.print("<div align=\"center\">Monto</div>");
							 out.print("</TD>");
							 out.print("<TD>");
							 out.print("<div align=\"center\">Tasa</div>");
							 out.print("</TD>");
							 out.print("<TD>");
							 out.print("Cuenta Debito Aut.");
							 out.print("</TD>");
							 out.print("<TD>");
							 out.print("Tipo de Cambio");
							 out.print("</TD>");
							 out.println("</tr>"); 
					    }
					    
						out.print("<tr id=\"trclear\">");
						out.print("<TD width=\"14\">");
						out.print("<div align=\"center\"></div></td>");
						out.print("<TD width=\"14\">");
						out.print("<div align=\"center\"></div></td>");
						out.print("<TD nowrap>");
						out.print("<div align=\"center\"><INPUT size=\"5\" type=\"text\" name=\"NDR\" value=\"" + lstDocuments.getRecord(0) + "\" readonly></div>");
						out.print("</TD>");
						out.print("<TD nowrap>");
						out.print("<div align=\"center\"><INPUT size=\"3\" type=\"text\" name=\"MA1\" value=\"" + lstDocuments.getRecord(1) + "\" readonly>");
						out.print("<INPUT size=\"3\" type=\"text\" name=\"MA2\" value=\"" + lstDocuments.getRecord(2) + "\" readonly>");
						out.print("<INPUT size=\"3\" type=\"text\" name=\"MA3\" value=\"" + lstDocuments.getRecord(3) + "\" readonly></div>");
						out.print("</TD>");
						out.print("<TD nowrap>");
						out.print("<div align=\"center\"><INPUT size=\"20\" type=\"text\" name=\"OAM\" value=\"" + lstDocuments.getRecord(4) + "\" readonly></div>");
						out.print("</TD>");
						out.print("<TD nowrap>");
						out.print("<div align=\"center\"><INPUT size=\"15\" type=\"text\" name=\"ARC\" value=\"" + lstDocuments.getRecord(5) + "\" readonly></div>");
						out.print("</TD>");
						out.print("<TD nowrap>");
						out.print("<div align=\"center\"><INPUT size=\"12\" type=\"text\" name=\"ACC\" value=\"" + lstDocuments.getRecord(6) + "\" readonly></div>");
						out.print("</TD>");
						out.print("<TD nowrap>");
						out.print("<div align=\"center\"><INPUT size=\"2\" type=\"text\" name=\"EXT\" value=\"" + lstDocuments.getRecord(7) + "\" readonly></div>");
						out.print("</TD>");
						out.print("</tr>");
					}
				}
				out.print("<TR>");
				out.print("<TD>");
				out.print("</TD>");
				out.print("<TD>");
				out.print("</TD>");
				out.print("<TD>");
				out.print("</TD>");
				out.print("<TD>");
			    out.print("<div align=\"right\">Total :</div>");
				out.print("</TD>");
				out.print("<TD>");
				out.print("<div align=\"center\"><INPUT size=\"20\" type=\"text\" name=\"TOTACCEPTOR\" value=\"" + lstAcceptors.getRecord(2) + "\" readonly></div>");
				out.print("</TD>");
				out.print("<TD>");
				out.print("</TD>");
				out.print("<TD>");
				out.print("</TD>");
				out.print("<TD>");
				out.print("</TD>");
				out.println("</TR>"); 
				
				out.print("</table>");
				out.print("</td>");
				out.print("</tr>");
         	}
       	}
%>
    </td>
    </tr>
   </table>
            </tr>
</table>

<p align="center">&nbsp;</p>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"></div>
      </td>
      <td width="34%"> 
        <div align="center">
        </div>
      </td>
      <td width="33%"> 
        <div align="center"></div>
      </td>
    </tr>
    

    </tr>

  </table>
  <p align="center">&nbsp; </p>
  </form>

</body>
</html>

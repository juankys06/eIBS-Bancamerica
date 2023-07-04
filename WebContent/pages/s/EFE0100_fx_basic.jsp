<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Fx G/L Cross Reference</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="msgBasic" class="datapro.eibs.beans.EFE010001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage" scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<%
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

 String typ = "";
 if (msgBasic.getE01FEGTYP().equals("FWRD")) {
 	typ = "53";
 } else if (msgBasic.getE01FEGTYP().equals("SPOT")) {
 	typ = "51";
 }  else if (msgBasic.getE01FEGTYP().equals("OPTI")) {
 	typ = "57";
 }  else if (msgBasic.getE01FEGTYP().equals("SWAP")) {
 	typ = "55";
 }
%>
<H3 align="center">Referencias Cruzadas Cuenta Contable M/E<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fx_basic, EFE0100"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFE0100" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="5">
  <INPUT TYPE=HIDDEN NAME="BSECCY" value="<%= currUser.getE01BCU()%>">
  <table class="tableinfo">
    <tr >
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark">
            <td nowrap width="25%">
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="25%">
              <div align="left">
                <input type="text" name="E01FEGBNK" size="3" maxlength="2" readonly value="<%= msgBasic.getE01FEGBNK().trim()%>">
			  </div>
            </td>
            <td nowrap width="25%">
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap width="25%">
              <div align="left">
                <input type="text" name="E01FEGCCY" size="4" maxlength="3" readonly value="<%= msgBasic.getE01FEGCCY().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="25%">
              <div align="right"><b>Tipo de Contrato : </b></div>
            </td>
            <td nowrap width="25%">
              <div align="left">
                <input type="text" name="E01FEGTYP" size="5" maxlength="4" readonly value="<%= msgBasic.getE01FEGTYP().trim()%>">
              </div>
            </td>
            <td nowrap width="25%">
              <div align="right"><b>Clasificación :</b></div>
            </td>
            <td nowrap width="25%">
              <div align="left">
                <input type="text" name="E01FEGCLS" size="5" maxlength="4" readonly value="<%= msgBasic.getE01FEGCLS().trim()%>">
                 </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Basic Information</H4>
  <table class="tableinfo">
    <tr >
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="13%">
              <div align="right">Tipo Revaluación  :</div>
            </td>
            <td nowrap width="20%">
              <input type="text" name="E01FEGRVL" size="2" maxlength="1" value="<%= msgBasic.getE01FEGRVL().trim()%>" >
              <a href="javascript:GetCode('E01FEGRVL','STATIC_fe_bo_rev.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="14%">
              <div align="right">Non Delivery Forward: </div>
            </td>
            <td nowrap width="20%">
             <input type="radio" name="E01FEGNDF" value="Y" <%if(!msgBasic.getE01FEGNDF().equals("N")) out.print("checked");%>>Si
             <input type="radio" name="E01FEGNDF" value="N" <%if(msgBasic.getE01FEGNDF().equals("N")) out.print("checked");%>>No
            </td>
            <td nowrap width="13%">
              <div align="right">Descripción :</div>
            </td>
            <td nowrap width="20%">
              <input type="text" name="E01FEGDSC" size="35" maxlength="30" value="<%= msgBasic.getE01FEGDSC().trim()%>" >
            </td>

          </tr>
        </table>
      </td>
    </tr>
  </table>
<H4></H4>
 <table class="tableinfo">
    <tr >
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="25%">
              <div align="right">Compra :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGPGL" size="17" maxlength="16" value="<%= msgBasic.getE01FEGPGL().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGPGL',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'<%= typ%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGPGL" size="35" maxlength="30" value="<%= msgBasic.getD01FEGPGL().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trclear">
            <td nowrap width="25%">
              <div align="right">Contrapartida :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGPCG" size="17" maxlength="16" value="<%= msgBasic.getE01FEGPCG().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGPCG',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGPCG" size="35" maxlength="30" value="<%= msgBasic.getD01FEGPCG().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trdark">
            <td nowrap width="25%">
              <div align="right">Venta :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGSGL" size="17" maxlength="16" value="<%= msgBasic.getE01FEGSGL().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGSGL',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'<%= typ%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGSGL" size="35" maxlength="30" value="<%= msgBasic.getD01FEGSGL().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trclear">
            <td nowrap width="25%">
              <div align="right">Contrapartida :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGSCG" size="17" maxlength="16" value="<%= msgBasic.getE01FEGSCG().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGSCG',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGSCG" size="35" maxlength="30" value="<%= msgBasic.getD01FEGSCG().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trdark">
            <td nowrap width="25%">
              <div align="right">Contrapartida Swap Forward <%= currUser.getE01BCU()%> :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGX01" size="17" maxlength="16" value="<%= msgBasic.getE01FEGX01().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGX01',document.forms[0].E01FEGBNK.value,document.forms[0].BSECCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGX01" size="35" maxlength="30" value="<%= msgBasic.getD01FEGX01().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trclear">
            <td nowrap width="25%">
              <div align="right">Posición Swap Forward  :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGX02" size="17" maxlength="16" value="<%= msgBasic.getE01FEGX02().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGX02',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGX02" size="35" maxlength="30" value="<%= msgBasic.getD01FEGX02().trim()%>" readonly>
            </td>
           </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Transferencia a Spot</H4>
 <table class="tableinfo">
    <tr >
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="25%">
              <div align="right">Compra :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGST1" size="17" maxlength="16" value="<%= msgBasic.getE01FEGST1().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGST1',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'51')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGST1" size="35" maxlength="30" value="<%= msgBasic.getD01FEGST1().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trclear">
            <td nowrap width="25%">
              <div align="right">Contrapartida :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGSC1" size="17" maxlength="16" value="<%= msgBasic.getE01FEGSC1().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGSC1',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGSC1" size="35" maxlength="30" value="<%= msgBasic.getD01FEGSC1().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trdark">
            <td nowrap width="25%">
              <div align="right">Venta :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGST2" size="17" maxlength="16" value="<%= msgBasic.getE01FEGST2().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGST2',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'51')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGST2" size="35" maxlength="30" value="<%= msgBasic.getD01FEGST2().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trclear">
            <td nowrap width="25%">
              <div align="right">Contrapartida :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGSC2" size="17" maxlength="16" value="<%= msgBasic.getE01FEGSC2().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGSC2',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGSC2" size="35" maxlength="30" value="<%= msgBasic.getD01FEGSC2().trim()%>" readonly>
            </td>
           </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Revaluación</H4>
 <table class="tableinfo">
    <tr >
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="25%">
              <div align="right">Ganancias  por Revaluación :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGRVI" size="17" maxlength="16" value="<%= msgBasic.getE01FEGRVI().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGRVI',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGRVI" size="35" maxlength="30" value="<%= msgBasic.getD01FEGRVI().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trclear">
            <td nowrap width="25%">
              <div align="right">Contrapartida por  Ganancia :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGRVC" size="17" maxlength="16" value="<%= msgBasic.getE01FEGRVC().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGRVC',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGRVC" size="35" maxlength="30" value="<%= msgBasic.getD01FEGRVC().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trdark">
            <td nowrap width="25%">
              <div align="right">Perdida por Revaluación :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGRLO" size="17" maxlength="16" value="<%= msgBasic.getE01FEGRLO().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGRLO',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGRLO" size="35" maxlength="30" value="<%= msgBasic.getD01FEGRLO().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trclear">
            <td nowrap width="25%">
              <div align="right">Contrapartida por Perdida :</div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGRCL" size="17" maxlength="16" value="<%= msgBasic.getE01FEGRCL().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGRCL',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGRCL" size="35" maxlength="30" value="<%= msgBasic.getD01FEGRCL().trim()%>" readonly>
            </td>
           </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Perdidas y Ganancias</H4>
 <table class="tableinfo">
    <tr >
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="25%">
              <div align="right"><%=msgBasic.getE01FEGTYP().equals("FWRD")?"Causación por Recibir :":"Ganancia por Contrato :"%></div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGPAL" size="17" maxlength="16" value="<%= msgBasic.getE01FEGPAL().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGPAL',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGPAL" size="35" maxlength="30" value="<%= msgBasic.getD01FEGPAL().trim()%>" readonly>
            </td>
           </tr>
          <tr id="trclear">
            <td nowrap width="25%">
              <div align="right"><%=msgBasic.getE01FEGTYP().equals("FWRD")?"Ganancia por Contrato :":"Perdida por Contrato :"%></div>
            </td>
            <td nowrap width="25%">
              <input type="text" name="E01FEGLOS" size="17" maxlength="16" value="<%= msgBasic.getE01FEGLOS().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetLedger('E01FEGLOS',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></a>
            </td>
            <td nowrap width="50%">
              <input type="text" name="D01FEGLOS" size="35" maxlength="30" value="<%= msgBasic.getD01FEGLOS().trim()%>" readonly>
            </td>
           </tr>
           <% if (msgBasic.getE01FEGTYP().trim().equals("FWRD")) { %>
           <tr id="trdark">
           	<td nowrap>
           		<div align="right">Causación por Pagar:</div>
           	</td>
           	<td nowrap>
           	<INPUT type="text" name="E01FEGX03" size="17"
					maxlength="16" value="<%= msgBasic.getE01FEGX03().trim()%>"
					onkeypress="enterInteger()"><A
					href="javascript:GetLedger('E01FEGX03',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					border="0"></A></td>
           	<td nowrap>
           	<INPUT type="text" name="D01FEGX03" size="35"
					maxlength="30" value="<%= msgBasic.getD01FEGX03().trim()%>"
					readonly></td>           	
           </tr>
           <tr id="trclear">
           	<td nowrap>
           		<div align="right">Perdida por  Contrato :</div>
           	</td>
           	<td nowrap>
           	<INPUT type="text" name="E01FEGX04" size="17"
					maxlength="16" value="<%= msgBasic.getE01FEGX04().trim()%>"
					onkeypress="enterInteger()"><A
					href="javascript:GetLedger('E01FEGX04',document.forms[0].E01FEGBNK.value,document.forms[0].E01FEGCCY.value,'')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					border="0"></A></td>
           	<td nowrap>
           	<INPUT type="text" name="D01FEGX04" size="35"
					maxlength="30" value="<%= msgBasic.getD01FEGX04().trim()%>"
					readonly></td>           	
           </tr>  
           <% } %>         
        </table>
      </td>
    </tr>
  </table>


 <div align="center">
	   <input id="EIBSBTN" type=submit name="Submit" value="Someter">
  </div>
  </form>
</body>
</html>

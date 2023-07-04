<html>
<head>
<title>Archivo de Tarjetas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="refCCY" class="datapro.eibs.beans.EGL036001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

builtHPopUp();
function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   
  
   
%>

<h3 align="center">Mantenimiento Referencias Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fex_reference_currency.jsp,EGL0360"> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEGL0360" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  </p>
  <h4>Informaci&oacute;n B&aacute;sica</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark">
            <td nowrap width="16%" >
              <div align="right">Banco :</div>
            </td>
            <td nowrap >
              <input type="text" name="E01RATBNK" size="3" maxlength="2" value="<%= refCCY.getE01RATBNK()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01RATCCY" size="4" maxlength="3" value="<%= refCCY.getE01RATCCY()%>">
                <font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01RATDSC" size="40" maxlength="35" value="<%= refCCY.getE01RATDSC()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Moneda Base :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01CNTBCU" size="4" maxlength="3" value="<%= refCCY.getE01CNTBCU()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right">Fecha de Hoy :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01CNTRD1" size="3" maxlength="2" value="<%= refCCY.getE01CNTRD1()%>">
                <input type="text" name="E01CNTRD2" size="3" maxlength="2" value="<%= refCCY.getE01CNTRD2()%>">
                <input type="text" name="E01CNTRD3" size="3" maxlength="2" value="<%= refCCY.getE01CNTRD3()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Tasa &quot;Spot&quot; Actual :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01RATEXR" size="11" maxlength="11" value="<%= refCCY.getE01RATEXR()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right">Operaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <select name="E01RATMUD">
                <% boolean flag = false; %>
                <option value="M" <%if (refCCY.getE01RATMUD().equals("M")) { flag = true; out.print("selected"); }%>>Multiplicar</option>
                <option value="D" <%if (refCCY.getE01RATMUD().equals("D")) { flag = true; out.print("selected"); }%>>Dividir</option>
                <option value=" " <%if ( flag == false ) out.print("selected");  %>></option>
              </select>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Adicional</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="26%"> 
              <div align="right">N&uacute;mero de Decimales :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left"> 
                <input type="text" name="E01RATDCP" size="2" maxlength="1" value="<%= refCCY.getE01RATDCP()%>" >
                (0,2)</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" height="23"> 
              <div align="right">Nombre Abreviado :</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="left"> 
                <input type="text" name="E01RATCYA" size="20" maxlength="20" value="<%= refCCY.getE01RATCYA()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" height="23"> 
              <div align="right">Permite Contabilidad :</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="left"> 
                <input type="radio" name="E01RATSCY" value="Y" <%if (refCCY.getE01RATSCY().equals("Y")) out.print("checked"); %>>
                S&iacute; 
                <input type="radio" name="E01RATSCY" value="N" <%if (refCCY.getE01RATSCY().equals("N")) out.print("checked"); %>>
                No</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" height="23"> 
              <div align="right">Referencia :</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="left"> 
                <input type="text" name="E01RATRNM" size="15" maxlength="15" value="<%= refCCY.getE01RATRNM()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Referencias</H4>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
		  
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right"><b><b><b><b></b></b></b></b></div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"><b>Cuenta Contable</b></div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"><b>Descripci&oacute;n</b></div>
            </td>
          </tr>
		  <%if (userPO.getHeader1().equals("3")) {%>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Cuenta Posici&oacute;n :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center">
                <input type="text" name="E01RATPBK" size="2" maxlength="2" value="<%= refCCY.getE01RATPBK()%>">
                <input type="text" name="E01RATPCY" size="3" maxlength="3" value="<%= refCCY.getE01RATPCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01RATPBK.value,'','','','')">
                <input type="text" name="E01RATPGL" size="18" maxlength="17" value="<%= refCCY.getE01RATPGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01RATPBK.value,document.forms[0].E01RATPCY.value,'','','')" >
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center">
                <input type="text" name="E01GLDSCP" size="40" maxlength="35" value="<%= refCCY.getE01GLDSCP()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Contravalor (Moneda Base ) :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center">
                <input type="text" name="E01RATVBK" size="2" maxlength="2" value="<%= refCCY.getE01RATVBK()%>">
                <input type="text" name="E01RATVCY" size="3" maxlength="3" value="<%= refCCY.getE01RATVCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01RATVBK.value,'','','','')">
                <input type="text" name="E01RATVGL" size="18" maxlength="17" value="<%= refCCY.getE01RATVGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01RATVBK.value,document.forms[0].E01RATVCY.value,'','','')" >
              </div>
            </td>
            <%}%>
            <td nowrap width="26%"> 
              <div align="center">
                <input type="text" name="E01GLDSCV" size="40" maxlength="35" value="<%= refCCY.getE01GLDSCV()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Ganancia / P&eacute;rdida :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="center">
                <input type="text" name="E01RATBK1" size="2" maxlength="2" value="<%= refCCY.getE01RATBK1()%>">
                <input type="text" name="E01RATCY1" size="3" maxlength="3" value="<%= refCCY.getE01RATCY1()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01RATBK1.value,'','','','')">
                <input type="text" name="E01RATGL1" size="18" maxlength="17" value="<%= refCCY.getE01RATGL1()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01RATBK1.value,document.forms[0].E01RATCY1.value,'','','')" >
              </div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="center">
                <input type="text" name="E01GLDSC1" size="40" maxlength="35" value="<%= refCCY.getE01GLDSC1()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Ganancia / P&eacute;rdida IBF :</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01RATBK2" size="2" maxlength="2" value="<%= refCCY.getE01RATBK2()%>">
                <input type="text" name="E01RATCY2" size="3" maxlength="3" value="<%= refCCY.getE01RATCY2()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01RATBK2.value,'','','','')">
                <input type="text" name="E01RATGL2" size="18" maxlength="17" value="<%= refCCY.getE01RATGL2()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01RATBK2.value,document.forms[0].E01RATCY2.value,'','','')" >
              </div>
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="center">
                <input type="text" name="E01GLDSC2" size="40" maxlength="35" value="<%= refCCY.getE01GLDSC2()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">
					<%if (userPO.getHeader1().equals("3")) {%>Contrapartida G/P : <%}%>
					<%if (userPO.getHeader1().equals("1")) {%>Cuenta Resultados Años Anteriores G/P : <%}%>
					</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01RATBK3" size="2" maxlength="2" value="<%= refCCY.getE01RATBK3()%>">
                <input type="text" name="E01RATCY3" size="3" maxlength="3" value="<%= refCCY.getE01RATCY3()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01RATBK3.value,'','','','')">
                <input type="text" name="E01RATGL3" size="18" maxlength="17" value="<%= refCCY.getE01RATGL3()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01RATBK3.value,document.forms[0].E01RATCY3.value,'','','')" >
              </div>
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="center">
                <input type="text" name="E01GLDSC3" size="40" maxlength="35" value="<%= refCCY.getE01GLDSC3()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">	
					<%if (userPO.getHeader1().equals("3")) {%>Contrapartida G/P IBF: <%}%>
					<%if (userPO.getHeader1().equals("1")) {%>Cuenta Resultados Años Anteriores G/P IBF : <%}%>
</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01RATBK4" size="2" maxlength="2" value="<%= refCCY.getE01RATBK4()%>">
                <input type="text" name="E01RATCY4" size="3" maxlength="3" value="<%= refCCY.getE01RATCY4()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01RATBK4.value,'','','','')">
                <input type="text" name="E01RATGL4" size="18" maxlength="17" value="<%= refCCY.getE01RATGL4()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01RATBK4.value,document.forms[0].E01RATCY4.value,'','','')" >
              </div>
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="center">
                <input type="text" name="E01GLDSC4" size="40" maxlength="35" value="<%= refCCY.getE01GLDSC4()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF">
      <td width="33%">&nbsp;</td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
        </div>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
</form>
</body>
</html>

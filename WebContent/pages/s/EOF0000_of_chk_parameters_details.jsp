<html>
<head>
<title>Parametros de Cheques de Gerencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="checksParam" class="datapro.eibs.beans.EOF000001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">Parametros de Control de Cheques de Gerencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="of_chk_parameters_details, EOF0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEOF0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Formato :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> <b> 
                <input type="text" name="E01OFCFTY" size="2" maxlength="1"  value="<%= checksParam.getE01OFCFTY().trim()%>" >
                </b></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Descripci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01OFCDSC" size="36"  maxlength="35" value="<%= checksParam.getE01OFCDSC().trim()%>">
                </font></font></font></b></div>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="16%" >
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="20%" ><b>
              <input type="text" name="E01OFCBNK" size="3" maxlength="2"  value="<%= checksParam.getE01OFCBNK().trim()%>" >
              </b></td>
            <td nowrap width="16%" >
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap colspan="3" >
              <div align="left">
                <input type="text" name="E01OFCCCY" size="4" maxlength="3" value="<%= checksParam.getE01OFCCCY().trim()%>"  >
                <a href="javascript:GetCurrency('E01OFCCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Cuenta Contable Cheques X Imprimir :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01OFCXPR" size="17" maxlength="16" value="<%= checksParam.getE01OFCXPR().trim()%>">
                <a href="javascript:GetLedger('E01OFCXPR',document.forms[0].E01OFCBNK.value,document.forms[0].E01OFCCCY.value,'')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap> 
              <div align="right">Cuenta Contable Cheques Impresos :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01OFCPRT" size="17" maxlength="16" value="<%= checksParam.getE01OFCPRT().trim()%>">
                <a href="javascript:GetLedger('E01OFCPRT',document.forms[0].E01OFCBNK.value,document.forms[0].E01OFCCCY.value,'')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Cuenta Contable Retenciones:</div>
            </td>
            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="E01OFCWTH" size="17" maxlength="16" value="<%= checksParam.getE01OFCWTH().trim()%>">
                <a href="javascript:GetLedger('E01OFCWTH',document.forms[0].E01OFCBNK.value,document.forms[0].E01OFCCCY.value,'')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap height="23"> 
              <div align="right">Porcentaje por Retenciones :</div>
            </td>
            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="E01OFCWTP" size="12" maxlength="11" value="<%= checksParam.getE01OFCWTP().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">N&uacute;mero de D&iacute;as Adelanto Generar 
                Pagos :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E01OFCVYS" maxlength="3" size="4" value="<%= checksParam.getE01OFCVYS().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="right">Tipo de N&uacute;meraci&oacute;n :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E01OFCNXO" maxlength="1" size="2" value="<%= checksParam.getE01OFCNXO().trim()%>" >
                <a href="javascript:GetCode('E01OFCNXO','STATIC_of_chk_params_num.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Lineas al Final Varios Pagos en 1 Cheque :</div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="left"> 
                <input type="text" name="E01OFCPCK" maxlength="2" size="3" value="<%= checksParam.getE01OFCPCK().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Tipo de Documento :</div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="left">
				<SELECT name="E01BAFDTY">
					<OPTION value="1" <% if(checksParam.getE01BAFDTY().equals("1")) out.print("selected");%>>Cheque Oficial</OPTION>
					<OPTION value="3" <% if(checksParam.getE01BAFDTY().equals("3")) out.print("selected");%>>Giro</OPTION>
				</SELECT></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Cuenta Contable D&iacute;as Inactivo 1 :</div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="left"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01OFCGI1" size="17" maxlength="16" value="<%= checksParam.getE01OFCGI1().trim()%>">
                <a href="javascript:GetLedger('E01OFCGI1',document.forms[0].E01OFCBNK.value,document.forms[0].E01OFCCCY.value,'')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></font></font></font></b> 
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">D&iacute;as Inactivo 1 :</div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="left"> 
                <input type="text" name="E01OFCDI1" maxlength="3" size="4" value="<%= checksParam.getE01OFCDI1().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Cuenta Contable D&iacute;as Inactivo 2 :</div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="left"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01OFCGI2" size="17" maxlength="16" value="<%= checksParam.getE01OFCGI2().trim()%>">
                <a href="javascript:GetLedger('E01OFCGI2',document.forms[0].E01OFCBNK.value,document.forms[0].E01OFCCCY.value,'')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></font></font></font></b> 
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">D&iacute;as Inactivo 2 :</div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="left"> 
                <input type="text" name="E01OFCDI2" maxlength="3" size="4" value="<%= checksParam.getE01OFCDI2().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Forma eIBS :</div>
            </td>
            <td nowrap colspan="3" height="19">
              <input type="text" name="E01OFCPTH" maxlength="80" size="81" value="<%= checksParam.getE01OFCPTH().trim()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Cuentas</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="center">Secuencia</div>
            </td>
            <td nowrap> 
              <div align="center">Cargo</div>
            </td>
            <td nowrap> 
              <div align="center">Factor</div>
            </td>
            <td nowrap> 
              <div align="center">IVA</div>
            </td>
            <td nowrap> 
              <div align="center">Cuenta Ingresos</div>
            </td>
            <td nowrap> 
              <div align="center">Descripci&oacute;n</div>
            </td>
            <td nowrap> 
              <div align="center">Monto L&iacute;mite</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="center"> 
                <input type="text" name="E01BAF101" value="<%= checksParam.getE01BAF101().trim()%>" size="4" maxlength="3">
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="center"> 
                <input type="text" name="E01BAFA01" value="<%= checksParam.getE01BAFA01().trim()%>" size="17" maxlength="15">
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="center"> 
                <input type="text" name="E01BAF201" size="2" maxlength="1" 
                oncontextmenu="GetCode(this.name,'STATIC_par_eof0000_fact.jsp')"
                value="<%= checksParam.getE01BAF201().trim()%>" >
				 
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="center"> 
                <input type="text" name="E01BAFI01" size="2" maxlength="1" value="<%= checksParam.getE01BAFI01().trim()%>"
				 >
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="center"> 
                <input type="text" name="E01BAFG01" size="17" maxlength="16" value="<%= checksParam.getE01BAFG01().trim()%>"
                	oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01OFCBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="center"> 
                <input type="text" name="F01BAFD01" value="<%= checksParam.getF01BAFD01().trim()%>" size="36" maxlength="35">
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="center"> 
                <input type="text" value="<%= checksParam.getE01BAFL01().trim()%>" size="17" maxlength="15" name="E01BAFL01">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF102" value="<%= checksParam.getE01BAF102().trim()%>" size="4" maxlength="3">
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFA02" value="<%= checksParam.getE01BAFA02().trim()%>" size="17" maxlength="15">
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF202" size="2" maxlength="1" value="<%= checksParam.getE01BAF202().trim()%>"
				 >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFI02" size="2" maxlength="1" value="<%= checksParam.getE01BAFI02().trim()%>"
				 >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFG02" size="17" maxlength="16" value="<%= checksParam.getE01BAFG02().trim()%>"
                	oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01OFCBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="F01BAFD02" value="<%= checksParam.getF01BAFD02().trim()%>" size="36" maxlength="35">
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" value="<%= checksParam.getE01BAFL02().trim()%>" size="17" maxlength="15" name="E01BAFL02">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF103" value="<%= checksParam.getE01BAF103().trim()%>" size="4" maxlength="3">
              </div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFA03" value="<%= checksParam.getE01BAFA03().trim()%>" size="17" maxlength="15">
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF203" size="2" maxlength="1" value="<%= checksParam.getE01BAF203().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFI03" size="2" maxlength="1" value="<%= checksParam.getE01BAFI03().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFG03" size="17" maxlength="16" value="<%= checksParam.getE01BAFG03().trim()%>"
                	oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01OFCBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="F01BAFD03" value="<%= checksParam.getF01BAFD03().trim()%>" size="36" maxlength="35">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" value="<%= checksParam.getE01BAFL03().trim()%>" size="17" maxlength="15" name="E01BAFL03">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF104" value="<%= checksParam.getE01BAF104().trim()%>" size="4" maxlength="3">
              </div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFA04" value="<%= checksParam.getE01BAFA04().trim()%>" size="17" maxlength="15">
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF204" size="2" maxlength="1" value="<%= checksParam.getE01BAF204().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFI04" size="2" maxlength="1" value="<%= checksParam.getE01BAFI04().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFG04" size="17" maxlength="16" value="<%= checksParam.getE01BAFG04().trim()%>"
                	oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01OFCBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="F01BAFD04" value="<%= checksParam.getF01BAFD04().trim()%>" size="36" maxlength="35">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" value="<%= checksParam.getE01BAFL04().trim()%>" size="17" maxlength="15" name="E01BAFL04">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF105" value="<%= checksParam.getE01BAF105().trim()%>" size="4" maxlength="3">
              </div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFA05" value="<%= checksParam.getE01BAFA05().trim()%>" size="17" maxlength="15">
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF205" size="2" maxlength="1" value="<%= checksParam.getE01BAF205().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFI05" size="2" maxlength="1" value="<%= checksParam.getE01BAFI05().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFG05" size="17" maxlength="16" value="<%= checksParam.getE01BAFG05().trim()%>"
                	oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01OFCBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="F01BAFD05" value="<%= checksParam.getF01BAFD05().trim()%>" size="36" maxlength="35">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" value="<%= checksParam.getE01BAFL05().trim()%>" size="17" maxlength="15" name="E01BAFL05">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF106" value="<%= checksParam.getE01BAF106().trim()%>" size="4" maxlength="3">
              </div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFA06" value="<%= checksParam.getE01BAFA06().trim()%>" size="17" maxlength="15">
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF206" size="2" maxlength="1" value="<%= checksParam.getE01BAF206().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFI06" size="2" maxlength="1" value="<%= checksParam.getE01BAFI06().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFG06" size="17" maxlength="16" value="<%= checksParam.getE01BAFG06().trim()%>"
                	oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01OFCBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="F01BAFD06" value="<%= checksParam.getF01BAFD06().trim()%>" size="36" maxlength="35">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" value="<%= checksParam.getE01BAFL06().trim()%>" size="17" maxlength="15" name="E01BAFL06">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF107" value="<%= checksParam.getE01BAF107().trim()%>" size="4" maxlength="3">
              </div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFA07" value="<%= checksParam.getE01BAFA07().trim()%>" size="17" maxlength="15">
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF207" size="2" maxlength="1" value="<%= checksParam.getE01BAF207().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFI07" size="2" maxlength="1" value="<%= checksParam.getE01BAFI07().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFG07" size="17" maxlength="16" value="<%= checksParam.getE01BAFG07().trim()%>"
                	oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01OFCBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="F01BAFD07" value="<%= checksParam.getF01BAFD07().trim()%>" size="36" maxlength="35">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" value="<%= checksParam.getE01BAFL07().trim()%>" size="17" maxlength="15" name="E01BAFL07">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF108" value="<%= checksParam.getE01BAF108().trim()%>" size="4" maxlength="3">
              </div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFA08" value="<%= checksParam.getE01BAFA08().trim()%>" size="17" maxlength="15">
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF208" size="2" maxlength="1" value="<%= checksParam.getE01BAF208().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFI08" size="2" maxlength="1" value="<%= checksParam.getE01BAFI08().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFG08" size="17" maxlength="16" value="<%= checksParam.getE01BAFG08().trim()%>"
                	oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01OFCBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="F01BAFD08" value="<%= checksParam.getF01BAFD08().trim()%>" size="36" maxlength="35">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" value="<%= checksParam.getE01BAFL08().trim()%>" size="17" maxlength="15" name="E01BAFL08">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF109" value="<%= checksParam.getE01BAF109().trim()%>" size="4" maxlength="3">
              </div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFA09" value="<%= checksParam.getE01BAFA09().trim()%>" size="17" maxlength="15">
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF209" size="2" maxlength="1" value="<%= checksParam.getE01BAF209().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFI09" size="2" maxlength="1" value="<%= checksParam.getE01BAFI09().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFG09" size="17" maxlength="16" value="<%= checksParam.getE01BAFG09().trim()%>"
                	oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01OFCBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="F01BAFD09" value="<%= checksParam.getF01BAFD09().trim()%>" size="36" maxlength="35">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" value="<%= checksParam.getE01BAFL09().trim()%>" size="17" maxlength="15" name="E01BAFL09">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF110" value="<%= checksParam.getE01BAF110().trim()%>" size="4" maxlength="3">
              </div>
            </td>
            <td nowrap width="40%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFA10" value="<%= checksParam.getE01BAFA10().trim()%>" size="17" maxlength="15">
              </div>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAF2010" size="2" maxlength="1" value="<%= checksParam.getE01BAF210().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFI10" size="2" maxlength="1" value="<%= checksParam.getE01BAFI10().trim()%>"
				 >
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01BAFG010" size="17" maxlength="16" value="<%= checksParam.getE01BAFG10().trim()%>"
                	oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01OFCBNK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" name="F01BAFD010" value="<%= checksParam.getF01BAFD10().trim()%>" size="36" maxlength="35">
              </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="center"> 
                <input type="text" value="<%= checksParam.getE01BAFL10().trim()%>" size="17" maxlength="15" name="E01BAFL10">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <div align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>

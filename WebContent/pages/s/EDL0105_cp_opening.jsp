<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Commercial Paper Opening</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="cdNew" class="datapro.eibs.beans.EDL010501Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currClient" class= "datapro.eibs.beans.ESD080001Message"  scope="session" />

<SCRIPT LANGUAGE="javascript">
	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
 function CheckSubmit(act)
{
   document.forms[0].ACTION.value=act;
   document.forms[0].submit();
}

</SCRIPT>
<% 
 String CUSCUN = "";
 String CUSNA1 = "";
 
 if (currClient != null && error.getERRNUM().equals("0")) {
    CUSCUN = currClient.getE01CUSCUN().trim();
    CUSNA1 = currClient.getE01CUSNA1().trim();
 } else {
    CUSCUN = cdNew.getE01DEACUN().trim();
    CUSNA1 = cdNew.getE01CUSNA1().trim();
 }
 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
%> 
</head>
<body>
<h3 align="center">Nuevo Papel Comercial <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cp_opening.jsp,EDL0105"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="F">
  <INPUT TYPE=HIDDEN NAME="SCRDEABNK" value="<%= cdNew.getE01DEABNK().trim()%>">
  <input type=HIDDEN name="E01DEAACD" value="<%= cdNew.getE01DEAACD().trim()%>">
  <input type=HIDDEN name="E01DEAGLN" value="<%= cdNew.getE01DEAGLN().trim()%>"">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="10%" > 
              <div align="right"><b>No. de Cliente :</b></div>
            </td>
            <td nowrap width="30%"> 
              <div align="left"> 
                <input type="text" name="E01DEACUN" size="9" maxlength="9" value="<%= CUSCUN%>">
                <a href="javascript:GetCustomerDescId('E01DEACUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
                </div>
            </td>
            <td nowrap width="10%" > 
              <div align="right"><b>Nombre:</b></div>
            </td>
            <td nowrap colspan="3" width="50%"> 
              <div align="left"> 
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= CUSNA1%>">
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" readonly name="E01DEAACC" size="12" maxlength="9" value="<% if (cdNew.getE01DEAACC().trim().equals("999999999")) out.print("NEW ACCOUNT"); else out.print(cdNew.getE01DEAACC().trim()); %>">
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= cdNew.getE01DEACCY().trim()%>" readonly>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
                </b> </div>
            </td>
            <td nowrap width="10%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEAPRO" size="4" maxlength="4" value="<%= cdNew.getE01DEAPRO().trim()%>">
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>  
  <h4> Información Básica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Proceso/Orden :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAOD1" size="2" maxlength="2" value="<%= cdNew.getE01DEAOD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEAOD2" size="2" maxlength="2" value="<%= cdNew.getE01DEAOD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEAOD3" size="2" maxlength="2" value="<%= cdNew.getE01DEAOD3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01DEAOD1,document.forms[0].E01DEAOD2,document.forms[0].E01DEAOD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="middle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de Pago ultimo Cupón/Emisión :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEALI1" size="2" maxlength="2" value="<%= cdNew.getE01DEALI1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEALI2" size="2" maxlength="2" value="<%= cdNew.getE01DEALI2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEALI3" size="2" maxlength="2" value="<%= cdNew.getE01DEALI3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01DEALI1,document.forms[0].E01DEALI2,document.forms[0].E01DEALI3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="middle" border="0"></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAST1" size="2" maxlength="2" value="<%= cdNew.getE01DEAST1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEAST2" size="2" maxlength="2" value="<%= cdNew.getE01DEAST2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEAST3" size="2" maxlength="2" value="<%= cdNew.getE01DEAST3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01DEAST1,document.forms[0].E01DEAST2,document.forms[0].E01DEAST3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="middle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEAMD1" size="2" maxlength="2" value="<%= cdNew.getE01DEAMD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEAMD2" size="2" maxlength="2" value="<%= cdNew.getE01DEAMD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEAMD3" size="2" maxlength="2" value="<%= cdNew.getE01DEAMD3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01DEAMD1,document.forms[0].E01DEAMD2,document.forms[0].E01DEAMD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="middle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="37"> 
              <div align="right">Termino :</div>
            </td>
            <td nowrap width="23%" height="37"> 
             <input type="text" name="E01DEATRM" size="4" maxlength="3" value="<%= cdNew.getE01DEATRM().trim()%>" onkeypress="enterInteger()">
              <select name="E01DEATRC">
                <option value=" " <% if (!(cdNew.getE01DEATRC().equals("D") ||cdNew.getE01DEATRC().equals("M")||cdNew.getE01DEATRC().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(cdNew.getE01DEATRC().equals("D")) out.print("selected");%>>Día(s)</option>
                <option value="M" <% if(cdNew.getE01DEATRC().equals("M")) out.print("selected");%>>Mes(s)</option>
                <option value="Y" <% if(cdNew.getE01DEATRC().equals("Y")) out.print("selected");%>>Año(s)</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
            <td nowrap width="25%" height="37"> 
              <div align="right">Precio de Oferta :</div>
            </td>
            <td nowrap width="27%" height="37">
              <input type="text" name="E01BIDPRC" size="15" maxlength="13" value="<%= cdNew.getE01BIDPRC().trim()%>" onkeypress="enterDecimal()">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" >           
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Valor Nominal :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAOAM" size="15" maxlength="13" value="<%= cdNew.getE01DEAOAM().trim()%>" onkeypress="enterDecimal()">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" ></td>
            <td nowrap width="25%" >
              <div align="right">Tasa del Cupón :</div>
            </td>
            <td nowrap width="27%" >
 				<input type="text" name="E01DEARTE" size="13" maxlength="12" value="<%= cdNew.getE01DEARTE().trim()%>" onKeypress="enterSignDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Saldo de Principal/Monto Total:</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEABAP" size="15" maxlength="13" value="<%= cdNew.getE01DEABAP().trim()%>" onkeypress="enterDecimal()">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" ></td>
            <td nowrap width="25%" >
              <div align="right">Tasa del Cupón :</div>
            </td>
            <td nowrap width="27%" >
				<input type="text" name="E01DEAMXR" size="13" maxlength="12" value="<%= cdNew.getE01DEAMXR().trim()%>" onKeypress="enterSignDecimal()">
			</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Interes Acumulado :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAINT" size="15" maxlength="13" value="<%= cdNew.getE01DEAINT().trim()%>" onkeypress="enterDecimal()"></td>
            <td nowrap width="25%" >
              <div align="right">Periodo de Pago del Cupón :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01DEAROY" size="5" maxlength="4" value="<%= cdNew.getE01DEAROY().trim()%>" onkeypress="enterInteger()">
       			<SELECT name="E01DEAODA">
					<OPTION value=" "
						<% if (!(cdNew.getE01DEAODA().equals("P") ||cdNew.getE01DEAODA().equals("I")||cdNew.getE01DEAODA().equals("M") || cdNew.getE01DEAODA().equals("D") )) out.print("selected"); %>></OPTION>
					<OPTION value="P"
						<% if (cdNew.getE01DEAODA().equals("P")) out.print("selected"); %>>
					Periodos Mensuales(No incluye último día)</OPTION>
					<OPTION value="I"
						<% if (cdNew.getE01DEAODA().equals("I")) out.print("selected"); %>>
					Periodos Mensuales(Incluye último día)</OPTION>
					<OPTION value="M"
						<% if (cdNew.getE01DEAODA().equals("M")) out.print("selected"); %>>
					Mensual(Ultimo Día del Mes)</OPTION>
					<OPTION value="D"
						<% if (cdNew.getE01DEAODA().equals("D")) out.print("selected"); %>>
					Mensual(Día del mes)</OPTION>
				</SELECT>
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0">            
             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Saldo Neto :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01NETPRC" size="15" maxlength="13" value="<%= cdNew.getE01NETPRC().trim()%>" onkeypress="enterDecimal()"></td>
            <td nowrap width="25%" >
              <div align="right">Tipo de Confirmación :</div>
            </td>
            <td nowrap width="27%" >
              <select name="E01DEAF02">
                <option value=" " <% if (!(cdNew.getE01DEAF02().equals("P") ||cdNew.getE01DEAF02().equals("S")||cdNew.getE01DEAF02().equals("N"))) out.print("selected"); %>></option>
                <option value="P" <% if (cdNew.getE01DEAF02().equals("P")) out.print("selected"); %>>Imprimir</option>
                <option value="S" <% if (cdNew.getE01DEAF02().equals("S")) out.print("selected"); %>>Swift</option>
                <option value="N" <% if (cdNew.getE01DEAF02().equals("N")) out.print("selected"); %>>Ninguno</option>
              </select>            
              
             </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Inversión :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAUC6" size="5" maxlength="4" value="<%= cdNew.getE01DEAUC6().trim()%>" >
            </td>
            <td nowrap width="25%" >
              <div align="right">Tipo de Revalorización :</div>
            </td>
            <td nowrap width="27%" >
               <select name="E01DEARRT">
                <option value=" " <% if (!(cdNew.getE01DEARRT().equals("1") ||cdNew.getE01DEARRT().equals("2")||cdNew.getE01DEARRT().equals("3")||cdNew.getE01DEARRT().equals("4")||cdNew.getE01DEARRT().equals("N"))) out.print("selected"); %>></option>
                <option value="1" <% if (cdNew.getE01DEARRT().equals("1")) out.print("selected"); %>>Reval. Diaria Pérdidas y Ganancias</option>
                <option value="2" <% if (cdNew.getE01DEARRT().equals("2")) out.print("selected"); %>>Reval. Diaria Pérdidas</option>
                <option value="3" <% if (cdNew.getE01DEARRT().equals("3")) out.print("selected"); %>>Reval. Mensual Pérdidas y Ganancias</option>
                <option value="4" <% if (cdNew.getE01DEARRT().equals("4")) out.print("selected"); %>>Reval. Mensual Pérdidas</option>
                <option value="N" <% if (cdNew.getE01DEARRT().equals("N")) out.print("selected"); %>>No Reval.</option>
              </select>
             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Número de Custodia :</div>
            </td>
            <td nowrap width="23%" > 
				<input type="text" name="E01SFENUM" size="11" maxlength="10" value="<%= cdNew.getE01SFENUM().trim()%>" onkeypress="enterInteger()"> 
				<a href="javascript:GetCustomerDescId('E01SFENUM','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a>
           </td>
            <td nowrap width="25%" >
              <div align="right">Número de CUSIP/ISIN  :</div>
            </td>
            <td nowrap width="27%" >
             <INPUT type="text" name="E01DEACUI" size="13"	maxlength="12" value="<%= cdNew.getE01DEACUI().trim()%>">
            </td>
          </tr>     
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Número de Common Code  :</div>
            </td>
            <td nowrap width="75%" > 
				<INPUT type="text" name="E01DEAREF" size="13"	maxlength="12" value="<%= cdNew.getE01DEAREF().trim()%>">            
			</td>
            <td nowrap width="25%" >
              <div align="right">Línea de Crédito :</div>
            </td>
            <td nowrap width="27%" >
			  <input type="text" name="E01DEACMC" size="9" maxlength="9" value="<%= cdNew.getE01DEACMC().trim()%>"  onkeypress="enterInteger()">
              <input type="text" name="E01DEACMN" size="4" maxlength="4" value="<%= cdNew.getE01DEACMN().trim()%>"  onkeypress="enterInteger()">
              <a href="javascript:GetCreditLine('E01DEACMN',document.forms[0].E01DEACMC.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
            </td>
			
          </tr>
               
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Agente de Bolsa :</div>
            </td>
            <td nowrap width="75%" colspan="3"> 
              <input type="text" name="E01DEAUC7" size="5" maxlength="4" value="<%= cdNew.getE01DEAUC7().trim()%>" >
              <input type="text" name="D01DEAUC7" size="40" maxlength="35" value="<%= cdNew.getD01DEAUC7().trim()%>" >
              <a href="javascript:GetBrokerT('E01DEAUC7','D01DEAUC7')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="middle" border="0"></a> 
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Ubicación de Documento :</div>
            </td>
            <td nowrap width="75%" colspan="3" > 
            	<input type="text" name="E01DEALOC" size="40" maxlength="35" value="<%= cdNew.getE01DEALOC().trim()%>" >
            </td>
          </tr>  
           <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">País de Residencia :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAGCD" size="5" maxlength="4" value="<%= cdNew.getE01DEAGCD().trim()%>" >
              <a href="javascript:GetCodeCNOFC('E01DEAGCD','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
            </td>
            <td nowrap width="25%" >
              <div align="right">País de Riesgo :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01DEAGRC" size="13" maxlength="12" value="<%= cdNew.getE01DEAGRC().trim()%>" >
              <a href="javascript:GetCodeCNOFC('E01DEAGRC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
             </td>
          </tr>          
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Tasa Flotante :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAFTB" size="2" maxlength="2" value="<%= cdNew.getE01DEAFTB().trim()%>">
              <a href="javascript:GetFloating('E01DEAFTB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="middle" border="0" ></a> 
              <select name="E01DEAFTY">
                <option value=" " <% if (!(cdNew.getE01DEAFTY().equals("FP") ||cdNew.getE01DEAFTY().equals("FS"))) out.print("selected"); %>></option>
                <option value="FP" <% if (cdNew.getE01DEAFTY().equals("FP")) out.print("selected"); %>>Primary</option>
                <option value="FS" <% if (cdNew.getE01DEAFTY().equals("FS")) out.print("selected"); %>>Secondary</option>
              </select>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa Flotante :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01FLTRTE" size="9" maxlength="9" value="<%= cdNew.getE01FLTRTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Fecha de Revisión/Ciclo :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEARD1" size="2" maxlength="2" value="<%= cdNew.getE01DEARD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEARD2" size="2" maxlength="2" value="<%= cdNew.getE01DEARD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DEARD3" size="2" maxlength="2" value="<%= cdNew.getE01DEARD3().trim()%>" onkeypress="enterInteger()">
              / 
              <input type="text" name="E01DEARRP" size="3" maxlength="3" value="<%= cdNew.getE01DEARRP().trim()%>" onkeypress="enterCharNumber()" onblur="rightAlignCharNumber()">
            </td>
            <td nowrap width="25%" > 
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEACCN" size="8" maxlength="6" value="<%= cdNew.getE01DEACCN().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:GetCostCenter('E01DEACCN',document.forms[0].SCRDEABNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="middle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%">
              <div align="right">Banco/Agencia :</div>
            </td>
            <td nowrap width="23%">
              <input type="text" name="E01DEABNK" size="2" maxlength="2" value="<%= cdNew.getE01DEABNK().trim()%>"readonly>
              <input type="text" name="E01DEABRN" size="2" maxlength="3" value="<%= cdNew.getE01DEABRN().trim()%>">
              <a href="javascript:GetBranch('E01DEABRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"  ></a>
            </td>
             <td nowrap width="25%"> 
              <div align="right">Tasa de Cambio : </div>
            </td>
            <td nowrap width="27%" >
            	<input type="text" name="E01DEAEXR" size="10" maxlength="9" value="<%= cdNew.getE01DEAEXR().trim()%>" onKeypress="enterSignDecimal()">
            </td>
          </tr>   
        </table>
      </td>
    </tr>
  </table>  

                
    <h4>Cuentas de Contrapartida</h4>
  <TABLE class="tableinfo" align="center">
  <TR><TD>
  
   <table id="headTable">
    <tr id="trdark"> 
      <td nowrap align="center" >Concepto</td>
      <td nowrap align="center" >Banco </td>
      <td nowrap align="center" >Agencia</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >No. de Referencia</td>
      <td nowrap align="center" >Monto</td>
    </tr>
    </table> 
      
    <div id="dataDiv" class="scbarcolor" style="height:60; overflow-y :scroll; z-index:0" >
     <table id="dataTable" >
    <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
    <tr id="trclear"> 
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01OFFOP<%= name %>" value="<%= cdNew.getField("E01OFFOP"+name).getString().trim()%>" size="2" maxlength="2">
          <input type="hidden" name="E01OFFGL<%= name %>" value="<%= cdNew.getField("E01OFFGL"+name).getString().trim()%>">
          <input type="text" name="E01OFFCO<%= name %>" size="25" maxlength="25" readonly value="<%= cdNew.getField("E01OFFCO"+name).getString().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].SCRDEABNK.value,'','E01OFFGL<%= name %>','E01OFFOP<%= name %>',document.forms[0].E01DEAACD.value)">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01OFFBK<%= name %>" size="2" maxlength="2" value="<%= cdNew.getField("E01OFFBK"+name).getString().trim()%>" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01OFFBR<%= name %>" size="3" maxlength="3" value="<%= cdNew.getField("E01OFFBR"+name).getString().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].SCRDEABNK.value,'','','','')" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E01OFFCY<%= name %>" size="3" maxlength="3" value="<%= cdNew.getField("E01OFFCY"+name).getString().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].SCRDEABNK.value,'','','','')">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01OFFAC<%= name %>" size="12" maxlength="9"  value="<%= cdNew.getField("E01OFFAC"+name).getString().trim()%>"
                oncontextmenu="showPopUp(openingWireHelp,this.name,document.forms[0].SCRDEABNK.value,'','E01OFFAM<%= name %>',document.forms[0]['E01OFFOP<%= name %>'].value,'RC')"  onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
                  <input type="text" name="E01OFFAM<%= name %>" size="15" maxlength="13"  value="<%= cdNew.getField("E01OFFAM"+name).getString().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
    </tr>
    <%
    		}
    		%> 
     </table>
        </div>
    <table id="footTable" > 				
    <tr id="trdark"> 
            <td nowrap align="right"><b>Monto del Contrato en Moneda Equivalente :</b> </td>
      <td nowrap align="center"><b><i><strong> 
          <input type="text" name="E01OFFEQV" size="15" maxlength="13" readonly value="<%= cdNew.getE01OFFEQV().trim()%>">
          </strong></i></b>
      </td>
    </tr>
  </table>
  </TD>  
</TR>	
</TABLE>    

    <h4>Cuentas de Pago</h4>
  <TABLE class="tableinfo" align="center">
  <TR><TD>
  
   <table id="headTable1">
    <tr id="trdark"> 
      <td nowrap align="center" >Concepto</td>
      <td nowrap align="center" >Banco </td>
      <td nowrap align="center" >Agencia</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >No. de Referencia</td>
    </tr>
    </table> 
      
    <div id="dataDiv1" class="scbarcolor" style="height:60; overflow-y :scroll; z-index:0" >
     <table id="dataTable1" >
     <tr id="trclear"> 
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01REPOPE" value="<%= cdNew.getE01REPOPE().trim()%>" size="2" maxlength="2">
          <input type="hidden" name="E01REPGLN" value="<%= cdNew.getE01REPGLN().trim()%>">
          <input type="text" name="E01REPCON" size="25" maxlength="25" readonly value="<%= cdNew.getE01REPCON().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].SCRDEABNK.value,'','E01REPGLN','E01REPOPE',document.forms[0].E01DEAACD.value)">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01REPBNK" size="2" maxlength="2" value="<%= cdNew.getE01REPBNK().trim()%>" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01REPBRN" size="3" maxlength="3" value="<%= cdNew.getE01REPBRN().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].SCRDEABNK.value,'','','','')" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E01REPCCY" size="3" maxlength="3" value="<%= cdNew.getE01REPCCY().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].SCRDEABNK.value,'','','','')">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01REPACC" size="12" maxlength="9"  value="<%= cdNew.getE01REPACC().trim()%>"
                oncontextmenu="showPopUp(openingWireHelp,this.name,document.forms[0].SCRDEABNK.value,'','document.forms[0].E01DEACUN.value','','RT')"  onkeypress="enterInteger()">
        </div>
      </td>
    </tr>
     </table>
        </div>
  </TD>  
</TR>	
</TABLE>    
 <SCRIPT language="javascript">
    function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,0,true);
     adjustEquTables(headTable1,dataTable1,dataDiv1,0,false);
   }
  tableresize();
  window.onresize=tableresize;  
  </SCRIPT>
  <% 
		if (error.getERWRNG().equals("Y")) { 
				error.setERWRNG(" ");
	%>
			<h4 style="text-align:center"><input type="checkbox" name="H01FLGWK2" value="A">
      		Aceptar con Advertencias</h4>
	<% 
		} 
	%>
  <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="CheckSubmit('F')">
  </div>  
  
  
  
  
</form>
</body>
</html>

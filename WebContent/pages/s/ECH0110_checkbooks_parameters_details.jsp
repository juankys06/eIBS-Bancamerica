<html>
<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="checksParam" class="datapro.eibs.beans.ECH011001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">Parametros de Control de Chequeras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="checkbooks_parameters_details.jsp, ECH0110"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSECH0110" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> <b> 
                <!-- <input type="text" name="E01CHKBNK" size="3" maxlength="2"  value="<%= checksParam.getE01CHKBNK().trim()%>" readonly> -->
                 <input type="text" name="E01CHKBNK" size="3" maxlength="2"  value="<%= userPO.getBank().trim()%>" readonly>
                </b></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><b><font face="Arial"><font face="Arial"><font size="2"> 
               <!-- <input type="text" name="E01CHKCCY" size="4"  maxlength="3" value="<%= checksParam.getE01CHKCCY().trim()%>" readonly>  -->
               <input type="text" name="E01CHKCCY" size="4"  maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </font></font></font></b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Tipo de Cuenta:</b></div>
            </td>
            <td nowrap width="20%" > <b> 
              <!-- <input type="text" name="E01CHKATY" size="5" maxlength="4"  value="<%= checksParam.getE01CHKATY()%>" readonly>  -->
              <input type="text" name="E01CHKATY" size="5" maxlength="4"  value="<%= userPO.getType().trim()%>" readonly>
              </b></td>
            <td nowrap width="16%" > 
              <div align="right"><b>Tipo de Chequera :</b></div>
            </td>
            <td nowrap colspan="3" ><b> 
            	<input type="text" size="3" maxlength="2" name="E01CHKTCB" value="<%= checksParam.getE01CHKTCB().trim()%>" ></b>
              	<a href="javascript:GetCodeCNOFC('E01CHKTCB','36')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
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
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01CHKDES" maxlength="35" size="36" value="<%= checksParam.getE01CHKDES().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">N&uacute;mero de Cheques :</div>
            </td>
            <td nowrap height="23"> 
              <input type="text" name="E01CHKNCK" size="5" maxlength="4" value="<%= checksParam.getE01CHKNCK().trim()%>" >
            </td>
            <td nowrap height="23"> 
              <div align="right">Impuesto por Cheque :</div>
            </td>
            <td nowrap height="23">
              <input type="text" name="E01IMPCHK" size="6" maxlength="5" value="<%= checksParam.getE01IMPCHK().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Forma de Cobro :</div>
            </td>
            <td nowrap height="19"> 
              <input type="text" name="E01CHKIVA" maxlength="1" size="2" value="<%= checksParam.getE01CHKIVA().trim()%>" >
              <A href="javascript:GetCode('E01CHKIVA','STATIC_collection_via.jsp')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"  ></a>	  
            </td>
            <td nowrap width="16%"> 
              <div align="right">Cobro de Comisión :</div>
            </td>
            <td nowrap width="16%"> 
                <select name="E01CHKFL2">
                  <option value="R" <% if (!(checksParam.getE01CHKFL2().equals("R") || checksParam.getE01CHKFL2().equals("S"))) out.print("selected"); %>></option>
                  <option value="R" <% if (checksParam.getE01CHKFL2().equals("R")) out.print("selected"); %>>Solicitud</option>
                  <option value="S" <% if (checksParam.getE01CHKFL2().equals("S")) out.print("selected"); %>>Venta</option>
                </select>           
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Documento de Solicitud :</div>
            </td>
            <td nowrap width="40%" height="19"> 
              <input type="radio" name="E01CHKRQD" value="1"  <%if (checksParam.getE01CHKRQD().equals("1")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01CHKRQD" value="2"  <%if (checksParam.getE01CHKRQD().equals("2")) out.print("checked"); %>>
              No</td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Reposici&oacute;n Automatica :</div>
            </td>
            <td nowrap width="28%" height="19">
              <input type="radio" name="E01CHKFL1" value="1"  <%if (checksParam.getE01CHKFL1().equals("1")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01CHKFL1" value="2"  <%if (checksParam.getE01CHKFL1().equals("2")) out.print("checked"); %>>
              No </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Moneda Contable de Comisi&oacute;n :</div>
            </td>
            <td nowrap width="40%" height="19"><b><font face="Arial"><font face="Arial"><font size="2">
              <input type="text" name="E01CHKCOC" size="4"  maxlength="3" value="<%= checksParam.getE01CHKCOC().trim()%>">
              <a href="javascript:GetCurrency('E01CHKCOC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0" ></a></font></font></font></b> 
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Cuenta Contable de Comisi&oacute;n :</div>
            </td>
            <td nowrap width="28%" height="19">
              <input type="text" name="E01CHKCOG" size="17" maxlength="16" value="<%= checksParam.getE01CHKCOG().trim()%>">
				<a href="javascript:GetLedger('E01CHKCOG',document.forms[0].E01CHKBNK.value,document.forms[0].E01CHKCCY.value,'')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Monto de la Comisi&oacute;n :</div>
            </td>
            <td nowrap width="40%" height="19">
              <input type="text" name="E01CHKCOR" maxlength="315" size="17" value="<%= checksParam.getE01CHKCOR().trim()%>" >
            </td>
            <td nowrap width="16%" height="19"></td>
            <td nowrap width="28%" height="19"></td>
          </tr>          
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Balance M&iacute;nimo :</div>
            </td>
            <td nowrap height="19" > 
              <input type="text" name="E01CHKMIN" size="10" maxlength="9" value="<%= checksParam.getE01CHKMIN().trim()%>">
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Cálculo Dígito Verificador :</div>
            </td>
            <td nowrap height="19" > 
             <select name="E01CHKFL3">
                <option value="N" <% if (!(checksParam.getE01CHKFL3().equals("1") ||checksParam.getE01CHKFL3().equals("2") )) out.print("selected"); %>>No Aplica</option>
                <option value="1" <% if (checksParam.getE01CHKFL3().equals("1")) out.print("selected"); %>>Dígito Verificador Corpbanca</option>
                <option value="2" <% if (checksParam.getE01CHKFL3().equals("2")) out.print("selected"); %>>Dígito Verificador Banco Exterior</option>
              </select>
              
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Cheques Devueltos:</div>
            </td>
            <td nowrap height="19" > 
              <input type="text" name="E01CHKRCO" size="4" maxlength="3" value="<%= checksParam.getE01CHKRCO().trim()%>">
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Cheque de Dispensadora :</div>
            </td>
            <td nowrap width="28%" height="19">
              <input type="radio" name="E01CHKFL2" value="1"  <%if (checksParam.getE01CHKFL2().equals("1")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01CHKFL2" value="2"  <%if (!checksParam.getE01CHKFL2().equals("1")) out.print("checked"); %>>
              No </td>
            
          </tr>
        </table>
                </table>
  <h4>Informaci&oacute;n Anulaci&oacute;n</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Moneda Contable  Comisi&oacute;n Anulaci&oacute;n :</div>
            </td>
            <td nowrap width="40%" height="19"><b><font face="Arial"><font face="Arial"><font size="2">
              <input type="text" name="E01CHKCAY" size="4"  maxlength="3" value="<%= checksParam.getE01CHKCAY().trim()%>">
              <a href="javascript:GetCurrency('E01CHKCAY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absmiddle" border="0" ></a></font></font></font></b> 
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Cuenta Contable  Comisi&oacute;n Anulaci&oacute;n:</div>
            </td>
            <td nowrap width="28%" height="19">
              <input type="text" name="E01CHKCAG" size="17" maxlength="16" value="<%= checksParam.getE01CHKCAG().trim()%>">
				<a href="javascript:GetLedger('E01CHKCAG',document.forms[0].E01CHKBNK.value,document.forms[0].E01CHKCCY.value,'')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Monto de la Comisi&oacute;n Anulaci&oacute;n:</div>
            </td>
            <td nowrap width="40%" height="19">
              <input type="text" name="E01CHKCAA" maxlength="315" size="17" value="<%=checksParam.getE01CHKCAA().trim()%>" >
            </td>
            <td nowrap width="16%" height="19" align="right">Días para Anulaci&oacute;n Automática :</td>
            <td nowrap width="28%" height="19">
            <INPUT type="text"	name="E01CHKANM" size="4" maxlength="3"	value="<%= checksParam.getE01CHKANM().trim()%>"></td>
          </tr>
          <tr id="trdark"> 
            <TD nowrap width="16%" height="19"> <div align="right">Cobro de IVA:</div>
            </TD>
            <TD nowrap width="28%" height="19">
              <INPUT type="radio" name="E01CHKAIV" value="Y" <%if (checksParam.getE01CHKAIV().equals("Y")) out.print("checked"); %>>
              Si 
              <INPUT type="radio" name="E01CHKAIV" value="N" <%if (!checksParam.getE01CHKAIV().equals("Y")) out.print("checked"); %>>
              No </TD>
            
            <td nowrap width="16%" height="19"></td>
            <td nowrap height="19" ></td>
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Opciones de Renovacion de Certificados</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="cdRenov" class="datapro.eibs.beans.EDL013008Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	
</SCRIPT>


<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
	 
	 
     
%> 
<h3 align="center">Instrucciones de Cancelación/Renovaci&oacute;n <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cancel_renov_options,EWD0334"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="60">
  <INPUT TYPE=HIDDEN NAME="E08DEABNK" VALUE="<%= cdRenov.getE08DEABNK().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E08DEACCY" VALUE="<%= cdRenov.getE08DEACCY().trim()%>">
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E08DEACUN" size="9" maxlength="9" readonly value="<%= cdRenov.getE08DEACUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E08CUSNA1" size="45" maxlength="45" readonly value="<%= cdRenov.getE08CUSNA1().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E08DEAACC" size="12" maxlength="12" value="<%= cdRenov.getE08DEAACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E08DEACCY" size="3" maxlength="3" value="<%= cdRenov.getE08DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E08DEAPRO" size="4" maxlength="4" readonly value="<%= cdRenov.getE08DEAPRO().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Instrucciones de Renovaci&oacute;n</h4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td>
        <table cellpadding=2 cellspacing=0 align=center width=100% >
          <tr id="trdark"> 
            <td width=56>&nbsp;</td>
            <td >Tasa de Renovaci&oacute;n :</td>
            <td >
              <input type="text" name="E08DEAROR" size="9" maxlength="9" value="<%= cdRenov.getE08DEAROR().trim()%>">
              <select name="E08DEAHTM">
                <option value=" " <% if (!(cdRenov.getE08DEAHTM().equals("5")||cdRenov.getE08DEAHTM().equals("4")||cdRenov.getE08DEAHTM().equals("3")|| cdRenov.getE08DEAHTM().equals("2")||cdRenov.getE08DEAHTM().equals("1"))) out.print("selected"); %>></option>
                <option value="1" <% if (cdRenov.getE08DEAHTM().equals("1")) out.print("selected"); %>>Sumar</option>
                <option value="2" <% if (cdRenov.getE08DEAHTM().equals("2")) out.print("selected"); %>>Restar</option>
                <option value="3" <% if (cdRenov.getE08DEAHTM().equals("3")) out.print("selected"); %>>Sustituir</option>
                <option value="4" <% if (cdRenov.getE08DEAHTM().equals("4")) out.print("selected"); %>>Mantener</option>
                <option value="5" <% if (cdRenov.getE08DEAHTM().equals("5")) out.print("selected"); %>>Apertura</option>
              </select>
            </td>  
              <td><div align="right">Plazo :</div></td>
              <td>
              <input type="text" name="E08DEAROY" size="6" maxlength="6" value="<%= cdRenov.getE08DEAROY().trim()%>">
              <select name="E08DEAODA">
                <option value=" " <% if (!(cdRenov.getE08DEAODA().equals("D")||cdRenov.getE08DEAODA().equals("M")||cdRenov.getE08DEAODA().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if (cdRenov.getE08DEAODA().equals("D")) out.print("selected"); %>>Días</option>
                <option value="M" <% if (cdRenov.getE08DEAODA().equals("M")) out.print("selected"); %>>Meses</option>
                <option value="Y" <% if (cdRenov.getE08DEAODA().equals("Y")) out.print("selected"); %>>Años</option>
              </select>
              
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=56>&nbsp;</td>
            <td colspan="4">&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=56><b> <b> </b> 
              <input type="radio" name="E08DEAROC" value="A" checked <%if (!(cdRenov.getE08DEAROC().equals("B")||
				cdRenov.getE08DEAROC().equals("C")||cdRenov.getE08DEAROC().equals("D")||
				cdRenov.getE08DEAROC().equals("E")||cdRenov.getE08DEAROC().equals("F")||
				cdRenov.getE08DEAROC().equals("G")||cdRenov.getE08DEAROC().equals("H")||
				cdRenov.getE08DEAROC().equals("I")||cdRenov.getE08DEAROC().equals("J")||
				cdRenov.getE08DEAROC().equals("K")||cdRenov.getE08DEAROC().equals("P")||
				cdRenov.getE08DEAROC().equals("S"))) out.print("checked");%>>
              A</b></td>
            <td colspan="4"> Principal m&aacute;s Intereses ser&aacute;n renovados 
              por el per&iacute;odo de tiempo. </td>
          </tr>
          <tr id="trclear"> 
            <td width=56>&nbsp;</td>
            <td colspan="4" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=56 height="32"><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="B" 
			  <%if (cdRenov.getE08DEAROC().equals("B")) out.print("checked");%>>
              </b>B</b></td>
            <td height="32" colspan="4" > Inter&eacute;s ser&aacute; acreditado 
              a la 
              <input type="hidden" name="E08BBBFLG" value="">
              <input type="radio" name="CE08BBBFLG" value="1" onClick="document.forms[0].E08BBBFLG.value='1'"
			  <%if (cdRenov.getE08BBBFLG().equals("1")) out.print("checked");%>>
              Cuenta (Corriente, Ahorro, etc.) o al 
              <input type="radio" name="CE08BBBFLG" value="2" onClick="document.forms[0].E08BBBFLG.value='2'"
			  <%if (cdRenov.getE08BBBFLG().equals("2")) out.print("checked");%>>
              Certificado que tendr&aacute;n como n&uacute;mero 
              <input type="text" name="E08BBBACC" size="12" maxlength="12" value="<%= cdRenov.getE08BBBACC().trim()%>" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E08BBBACC',document.forms[0].E08DEABNK.value,'','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              . El Principal ser&aacute; renovado por el per&iacute;odo 
              de tiempo .</td>
          </tr>
          <tr id="trclear"> 
            <td width=56>&nbsp;</td>
            <td colspan="4" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=56 height="44"><b><b> </b><b> <b><b> 
              <input type="radio" name="E08DEAROC" value="C" 
			  <%if (cdRenov.getE08DEAROC().equals("C")) out.print("checked");%>>
              </b></b></b>C</b></td>
            <td height="44" colspan="4" > Inter&eacute;s ser&aacute; acreditado 
              a la Cuenta Contable n&uacute;mero 
              <input type="text" name="E08CCCGLN" size="18" maxlength="18" value="<%= cdRenov.getE08CCCGLN().trim()%>" onKeypress="enterInteger()">
              <a href="javascript:GetLedger('E08CCCGLN',document.forms[0].E08DEABNK.value,document.forms[0].E08DEACCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              ,emitiendo un 
              <input type="hidden" name="E08CCCFLV" value="">
              <input type="radio" name="CE08CCCFLV" value="C" onClick="document.forms[0].E08CCCFLV.value='C'"
			  <%if (cdRenov.getE08CCCFLV().equals("C")) out.print("checked");%>>
              Cheque de Gerencia, o generando una 
              <input type="radio" name="CE08CCCFLV" value="V" onClick="document.forms[0].E08CCCFLV.value='V'"
			  <%if (cdRenov.getE08CCCFLV().equals("V")) out.print("checked");%>>
              Transferencia de Fondos v&iacute;a 
              <select name="E08CCCPVI">
                <option value="" <% if (!(cdRenov.getE08CCCPVI().equals("F") || cdRenov.getE08CCCPVI().equals("Q")||
				cdRenov.getE08CCCPVI().equals("T") ||cdRenov.getE08CCCPVI().equals("1")||cdRenov.getE08CCCPVI().equals("2") 
				||cdRenov.getE08CCCPVI().equals("3")))out.print("selected"); %>> 
                </option>
                <option value="F" <% if (cdRenov.getE08CCCPVI().equals("F")){out.print("selected");} %>>FED 
                </option>
                <option value="Q" <% if (cdRenov.getE08CCCPVI().equals("Q")){out.print("selected");} %>>Emisi&oacute;n 
                Cupones</option>
                <option value="T" <% if (cdRenov.getE08CCCPVI().equals("T")){out.print("selected");} %>>Telex 
                </option>
                <option value="1" <% if (cdRenov.getE08CCCPVI().equals("1")){out.print("selected");} %>>Swift 
                Formato MT-100</option>
                <option value="2" <% if (cdRenov.getE08CCCPVI().equals("2")){out.print("selected");} %>>Swift 
                Formato MT-200</option>
                <option value="3" <% if (cdRenov.getE08CCCPVI().equals("3")){out.print("selected");} %>>Swift 
                Formato MT-300</option>
              </select>
              . El Principal ser&aacute; renovado por el  per&iacute;odo 
              de tiempo.</td>
          </tr>
          <tr id="trclear"> 
            <td width=56>&nbsp;</td>
            <td colspan="4" >&nbsp;</td>
          </tr>          
          <tr id="trdark"> 
            <td width=56 height="46"><b><b> </b><b><b><b> 
              <input type="radio" name="E08DEAROC" value="D" 
			  <%if (cdRenov.getE08DEAROC().equals("D")) out.print("checked");%>>
              </b></b></b>D</b></td>
            <td height="46" colspan="4" > Al vencimiento cancelar el Dep&oacute;sito 
              m&aacute;s los Intereses acreditando a la Cuenta Contable n&uacute;mero 
              <input type="text" name="E08DDDGLN" size="16" maxlength="16" value="<%= cdRenov.getE08DDDGLN().trim()%>" onKeypress="enterInteger()">
              <a href="javascript:GetLedger('E08DDDGLN',document.forms[0].E08DEABNK.value,document.forms[0].E08DEACCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              ,emitiendo un 
              <input type="hidden" name="E08DDDFLV" value="">
              <input type="radio" name="CE08DDDFLV" value="C" onClick="document.forms[0].E08DDDFLV.value='C'"
			  <%if (cdRenov.getE08DDDFLV().equals("C")) out.print("checked");%>>
              Cheque de Gerencia, o generando una 
              <input type="radio" name="CE08DDDFLV" value="" onClick="document.forms[0].E08DDDFLV.value='V'"
			  <%if (cdRenov.getE08DDDFLV().equals("V")) out.print("checked");%>>
              Transferencia de Fondos v&iacute;a 
              <select name="E08DDDPVI">
                <option value="" <% if (!(cdRenov.getE08DDDPVI().equals("F") || cdRenov.getE08DDDPVI().equals("Q")||
				cdRenov.getE08DDDPVI().equals("T") ||cdRenov.getE08DDDPVI().equals("1")||cdRenov.getE08DDDPVI().equals("2") 
				||cdRenov.getE08DDDPVI().equals("3")))out.print("selected"); %>> 
                </option>
                <option value="F" <% if (cdRenov.getE08DDDPVI().equals("F")){out.print("selected");} %>>FED 
                </option>
                <option value="Q" <% if (cdRenov.getE08DDDPVI().equals("Q")){out.print("selected");} %>>Emisi&oacute;n 
                Cupones</option>
                <option value="T" <% if (cdRenov.getE08DDDPVI().equals("T")){out.print("selected");} %>>Telex 
                </option>
                <option value="1" <% if (cdRenov.getE08DDDPVI().equals("1")){out.print("selected");} %>>Swift 
                Formato MT-100</option>
                <option value="2" <% if (cdRenov.getE08DDDPVI().equals("2")){out.print("selected");} %>>Swift 
                Formato MT-200</option>
                <option value="3" <% if (cdRenov.getE08DDDPVI().equals("3")){out.print("selected");} %>>Swift 
                Formato MT-300</option>
              </select>
              . </td>
          </tr>
          <tr id="trclear"> 
            <td width=56></td>
            <td colspan="4" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=56 height="36"><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="E" 
			  <%if (cdRenov.getE08DEAROC().equals("E")) out.print("checked");%>>
              </b>E</b></td>
            <td height="36" colspan="4" >Al vencimiento cancelar el Dep&oacute;sito 
              m&aacute;s los Intereses acreditandolos a la Cuenta n&uacute;mero 
              <input type="text" name="E08EEEACC" size="12" maxlength="12" value="<%= cdRenov.getE08EEEACC().trim()%>" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E08EEEACC',document.forms[0].E08DEABNK.value,'RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>(Corriente, 
              Ahorro, etc.) . </td>
          </tr>
          <tr id="trclear"> 
            <td width=56></td>
            <td colspan="4" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=56><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="F" 
			  <%if (cdRenov.getE08DEAROC().equals("F")) out.print("checked");%>>
              </b>F</b></td>
            <td colspan="4" > Al vencimiento renovar el Dep&oacute;sito y los 
              Intereses, 
              <input type="radio" name="E08FFFRPT" value="CR" <%if (cdRenov.getE08FFFRPT().equals("CR")) out.print("checked");%>>
              incrementándolo o 
              <input type="radio" name="E08FFFRPT" value="DR" <%if (cdRenov.getE08FFFRPT().equals("DR")) out.print("checked");%>>
              disminuyéndolo por  
              <input type="text" name="E08FFFROA" size="15" maxlength="15" value="<%= cdRenov.getE08FFFROA().trim()%>" onKeypress="enterDecimal()">
              a la Cuenta n&uacute;mero 
              <input type="text" name="E08FFFACC" size="12" maxlength="12" value="<%= cdRenov.getE08FFFACC().trim()%>" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E08FFFACC',document.forms[0].E08DEABNK.value,'RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              (Corriente, Ahorro, etc.) . </td>
          </tr>
          <tr id="trclear"> 
            <td width=56 height="19">&nbsp;</td>
            <td height="19" colspan="4" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=56 height="19"><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="G" 
			  <%if (cdRenov.getE08DEAROC().equals("G")) out.print("checked");%>>
              </b>G</b></td>
            <td height="19" colspan="4" >Al vencimiento renovar el Dep&oacute;sito 
              y los Intereses por otro per&iacute;odo, disminuyendo los 
              Intereses en 
              <input type="text" name="E08GGGROA" size="15" maxlength="15" value="<%= cdRenov.getE08GGGROA().trim()%>" onKeypress="enterDecimal()">
              y acreditando los mismos en la Cuenta n&uacute;mero 
              <input type="text" name="E08GGGACC" size="12" maxlength="12" value="<%= cdRenov.getE08GGGACC().trim()%>" onKeypress="enterInteger()">
              <a href="javascript:GetAccount('E08GGGACC',document.forms[0].E08DEABNK.value,'RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
              (Corriente, Ahorro, etc.) . </td>
          </tr>
          <tr id="trclear"> 
            <td width=56></td>
            <td colspan="4" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=56><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value=" " 
			  <%if (cdRenov.getE08DEAROC().trim().equals("")) out.print("checked");%>>
              </b></b></td>
            <td colspan="4" >Sin Código de Renovación.</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  </form>
</body>
</html>

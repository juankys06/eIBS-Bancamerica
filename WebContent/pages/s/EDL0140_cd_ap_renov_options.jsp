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

<jsp:useBean id= "pmnt" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cd_a_opt);


</SCRIPT>


<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
	 

     out.println("<SCRIPT> initMenu(); </SCRIPT>");
%> 
<h3 align="center">Instrucciones de Renovaci&oacute;n de Certificados de Dep&oacute;sito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_renov_options,EDL0140"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="34">
  <INPUT TYPE=HIDDEN NAME="E08DEABNK" VALUE="<%= cdRenov.getE08DEABNK().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E08DEACCY" VALUE="<%= cdRenov.getE08DEACCY().trim()%>">
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
                <input type="text" name="E08DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E08CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Certificado :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E08DEAACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E08DEAPRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Tipo de Renovaci&oacute;n</h4>
  <table class="tableinfo">
    <tr > 
      <td>
        <table cellpadding=2 cellspacing=0 align=center width=100% bordercolor="#000000">
          <tr id="trdark"> 
            <td width=35>&nbsp;</td>
            <td width="127">Tasa de Renovaci&oacute;n :</td>
            <td width="579"> 
              <input type="text" <% if (cdRenov.getF08DEAROR().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08DEAROR" size="9" maxlength="9" value="<%= cdRenov.getE08DEAROR().trim()%>">
              <input type="text" <% if (cdRenov.getF08DEAHTM().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08DEAHTM" size="9" maxlength="9" 
				value="<% if (cdRenov.getE08DEAHTM().equals("1")) out.print("Sumar");
						  else if (cdRenov.getE08DEAHTM().equals("2")) out.print("Restar");
						  else if (cdRenov.getE08DEAHTM().equals("3")) out.print("Sustituir");
                		  else if (cdRenov.getE08DEAHTM().equals("4")) out.print("Mantener");
						  else out.print(""); %>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=35><b> <b> </b> 
              <input type="radio" name="E08DEAROC" value="A" <%if (!(cdRenov.getE08DEAROC().equals("B")||
				cdRenov.getE08DEAROC().equals("C")||cdRenov.getE08DEAROC().equals("D")||
				cdRenov.getE08DEAROC().equals("E")||cdRenov.getE08DEAROC().equals("F")||
				cdRenov.getE08DEAROC().equals("G")||cdRenov.getE08DEAROC().equals("H")||
				cdRenov.getE08DEAROC().equals("I")||cdRenov.getE08DEAROC().equals("J")||
				cdRenov.getE08DEAROC().equals("K")||cdRenov.getE08DEAROC().equals("P")||
				cdRenov.getE08DEAROC().equals("S"))) out.print("checked");%> disabled >
              A</b></td>
            <td colspan="2"> Principal m&aacute;s Intereses ser&aacute;n renovados 
              por el mismo per&iacute;odo de tiempo. </td>
          </tr>
          <tr id="trdark"> 
            <td width=35>&nbsp;</td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35 height="32"><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="B" 
			  <%if (cdRenov.getE08DEAROC().equals("B")) out.print("checked");%> disabled >
              </b>B</b></td>
            <td height="32" colspan="2" > Inter&eacute;s ser&aacute; acreditado 
              a 
              <input type="text" <% if (cdRenov.getF08BBBFLG().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08BBBFLG" size="40"
				value="<% if (cdRenov.getE08BBBFLG().equals("1")) out.print("la Cuenta (Corriente, Ahorro, etc.)");
						  else if (cdRenov.getE08BBBFLG().equals("2")) out.print("el Certificado"); %>">
              que tendr&aacute;n como n&uacute;mero 
              <input type="text" <% if (cdRenov.getF08BBBACC().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08BBBACC" size="12" maxlength="12" value="<%= cdRenov.getE08BBBACC().trim()%>">
              . El Principal ser&aacute; renovado por el mismo per&iacute;odo 
              de tiempo .</td>
          </tr>
          <tr id="trdark"> 
            <td width=35>&nbsp;</td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35 height="44"><b><b> </b><b> <b><b> 
              <input type="radio" name="E08DEAROC" value="C" 
			  <%if (cdRenov.getE08DEAROC().equals("C")) out.print("checked");%> disabled >
              </b></b></b>C</b></td>
            <td height="44" colspan="2" > Inter&eacute;s ser&aacute; acreditado 
              a la Cuenta Contable n&uacute;mero 
              <input type="text" <% if (cdRenov.getF08CCCGLN().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08CCCGLN" size="16" maxlength="16" value="<%= cdRenov.getE08CCCGLN().trim()%>">
              <input type="text" <% if (cdRenov.getF08CCCFLV().equals("Y")||cdRenov.getF08CCCPVI().equals("Y")) out.print("id=\"txtchanged\""); %> name="E08CCCFLV" readonly size="25"
						value="<% if (cdRenov.getE08CCCFLV().equals("C")) out.print(",emitiendo un Cheque de Gerencia");
						  						else if (cdRenov.getE08CCCFLV().equals("V")) {
													out.print(", o generando una Transferencia de Fondos v&iacute;a");
                								if (cdRenov.getE08CCCPVI().equals("F")) out.print("FED");
													else if (cdRenov.getE08CCCPVI().equals("Q")) out.print("Emisi&oacute;n");
													else if (cdRenov.getE08CCCPVI().equals("T")) out.print("Telex");
													else if (cdRenov.getE08CCCPVI().equals("1")) out.print("Swift Formato MT-100");
													else if (cdRenov.getE08CCCPVI().equals("2")) out.print("Swift Formato MT-200");
													else if (cdRenov.getE08CCCPVI().equals("3")) out.print("Swift Formato MT-300");
													else out.print(""); 
												} %>" >
              . El Principal ser&aacute; renovado por el mismo per&iacute;odo 
              de tiempo.</td>
          </tr>
          <tr id="trdark"> 
            <td width=35>&nbsp;</td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35 height="46"><b><b> </b><b><b><b> 
              <input type="radio" name="E08DEAROC" value="D" 
			  <%if (cdRenov.getE08DEAROC().equals("D")) out.print("checked");%> disabled >
              </b></b></b>D</b></td>
            <td height="46" colspan="2" > Al vencimiento cancelar el Dep&oacute;sito 
              m&aacute;s los Intereses acreditando a la Cuenta Contable n&uacute;mero 
              <input type="text" <% if (cdRenov.getF08DDDGLN().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08DDDGLN" size="16" maxlength="16" value="<%= cdRenov.getE08DDDGLN().trim()%>">
              <input type="text" <% if (cdRenov.getF08DDDFLV().equals("Y")||cdRenov.getF08DDDPVI().equals("Y")) out.print("id=\"txtchanged\""); %> name="E08CCCFLV" readonly size="25"
				value="<% if (cdRenov.getE08DDDFLV().equals("C")) out.print(",emitiendo un Cheque de Gerencia");
						  else if (cdRenov.getE08DDDFLV().equals("V")) {
							out.print(", o generando una Transferencia de Fondos v&iacute;a");
                			if (cdRenov.getE08DDDPVI().equals("F")) out.print("FED");
							else if (cdRenov.getE08DDDPVI().equals("Q")) out.print("Emisi&oacute;n");
							else if (cdRenov.getE08DDDPVI().equals("T")) out.print("Telex");
							else if (cdRenov.getE08DDDPVI().equals("1")) out.print("Swift Formato MT-100");
							else if (cdRenov.getE08DDDPVI().equals("2")) out.print("Swift Formato MT-200");
							else if (cdRenov.getE08DDDPVI().equals("3")) out.print("Swift Formato MT-300");
							else out.print(""); }%>" >
              . </td>
          </tr>
          <tr id="trdark"> 
            <td width=35></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35 height="36"><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="E" 
			  <%if (cdRenov.getE08DEAROC().equals("E")) out.print("checked");%> disabled >
              </b>E</b></td>
            <td height="36" colspan="2" >Al vencimiento cancelar el Dep&oacute;sito 
              m&aacute;s los Intereses acreditandolos a la Cuenta n&uacute;mero 
              <input type="text" <% if (cdRenov.getF08EEEACC().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08EEEACC" size="12" maxlength="12" value="<%= cdRenov.getE08EEEACC().trim()%>">
              (Corriente, Ahorro, etc.) . </td>
          </tr>
          <tr id="trdark"> 
            <td width=35></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="F" 
			  <%if (cdRenov.getE08DEAROC().equals("F")) out.print("checked");%> disabled >
              </b>F</b></td>
            <td colspan="2" > Al vencimiento renovar el Dep&oacute;sito y los 
              Intereses, 
              <input type="TEXT" name="E08FFFRPT" <% if (cdRenov.getF08FFFRPT().equals("Y")) out.print("id=\"txtchanged\""); %> readonly size="15"
				value="<% if (cdRenov.getE08FFFRPT().equals("1")) out.print("increment&aacute;ndolo");
						  else if (cdRenov.getE08FFFRPT().equals("2")) out.print("disminuy&eacute;ndolo"); %>">
              en 
              <input type="text" <% if (cdRenov.getF08FFFROA().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08FFFROA" size="15" maxlength="15" value="<%= cdRenov.getE08FFFROA().trim()%>">
              contra la Cuenta n&uacute;mero 
              <input type="text" <% if (cdRenov.getF08FFFACC().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08FFFACC" size="12" maxlength="12" value="<%= cdRenov.getE08FFFACC().trim()%>">
              (Corriente, Ahorro, etc.) . </td>
          </tr>
          <tr id="trdark"> 
            <td width=35 height="19">&nbsp;</td>
            <td height="19" colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35 height="19"><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="G" 
			  <%if (cdRenov.getE08DEAROC().equals("G")) out.print("checked");%> disabled >
              </b>G</b></td>
            <td height="19" colspan="2" >Al vencimiento renovar el Dep&oacute;sito 
              y los Intereses por otro per&iacute;odo similar, disminuyendo los 
              Intereses en 
              <input type="text" <% if (cdRenov.getF08GGGROA().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08GGGROA" size="15" maxlength="15" value="<%= cdRenov.getE08GGGROA().trim()%>">
              y acreditando los mismos en la Cuenta n&uacute;mero 
              <input type="text" <% if (cdRenov.getF08GGGACC().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08GGGACC" size="12" maxlength="12" value="<%= cdRenov.getE08GGGACC().trim()%>">
              (Corriente, Ahorro, etc.) . </td>
          </tr>
          <tr id="trdark"> 
            <td width=35></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="H" 
			  <%if (cdRenov.getE08DEAROC().equals("H")) out.print("checked");%> disabled >
              </b>H</b></td>
            <td colspan="2" > 
              <p>Inter&eacute;s ser&aacute; pagado cada 
                <input type="text" <% if (cdRenov.getF08HHHROY().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08HHHROY" size="3" maxlength="3" value="<%= cdRenov.getE08HHHROY().trim()%>">
                <input type="text" <% if (cdRenov.getF08HHHODA().equals("Y")) out.print("id=\"txtchanged\""); %> readonly  name="E08HHHODA"
					value="<% if (cdRenov.getE08HHHODA().equals("D")) out.print("Dias");
							  else if (cdRenov.getE08HHHODA().equals("M")) out.print("Meses");
							  else if (cdRenov.getE08HHHODA().equals("Y")) out.print("A&ntilde;os");
							  else out.print(""); %>">
                <input type="text" <% if (cdRenov.getF08HHHFLG().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08HHHFLG" size="40"
					value="<% if (cdRenov.getE08HHHFLG().equals("1")) out.print("a una Cuenta (Corriente, Ahorro, etc.)");
							  else if (cdRenov.getE08HHHFLG().equals("2")) out.print("a un Certificado"); %>" >
                , que tendr&aacute;n como n&uacute;mero 
                <input type="text" <% if (cdRenov.getF08HHHACC().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08HHHACC" size="12" maxlength="12" value="<%= cdRenov.getE08HHHACC().trim()%>">
                . El Principal ser&aacute; renovado por el mismo per&iacute;odo 
                de tiempo.</p>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=35></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="I" 
			  <%if (cdRenov.getE08DEAROC().equals("I")) out.print("checked");%> disabled >
              </b>I</b></td>
            <td colspan="2" > Inter&eacute;s ser&aacute; pagado cada 
              <input type="text" <% if (cdRenov.getF08IIIROY().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08IIIROY" size="3" maxlength="3" value="<%= cdRenov.getE08IIIROY().trim()%>">
              <input type="text" <% if (cdRenov.getF08IIIODA().equals("Y")) out.print("id=\"txtchanged\""); %> readonly size="15"
				value="<% if (cdRenov.getE08IIIODA().equals("D")) out.print("Dias");
						  else if (cdRenov.getE08IIIODA().equals("M")) out.print("Meses");
						  else if (cdRenov.getE08IIIODA().equals("Y")) out.print("A&ntilde;os");
						  else out.print(""); %>" >
              a la Cuenta Contable n&uacute;mero 
              <input type="text" <% if (cdRenov.getF08IIIGLN().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08IIIGLN" size="16" maxlength="16" value="<%= cdRenov.getE08IIIGLN().trim()%>">
              <input type="text" <% if (cdRenov.getF08IIIFLV().equals("Y")||cdRenov.getF08IIIPVI().equals("Y")) out.print("id=\"txtchanged\""); %> name="E08CCCFLV" readonly size="25"
				value="<% if (cdRenov.getE08IIIFLV().equals("C")) out.print(",emitiendo un Cheque de Gerencia");
						  else if (cdRenov.getE08IIIFLV().equals("V")) {
							out.print(", o generando una Transferencia de Fondos v&iacute;a");
                			if (cdRenov.getE08IIIPVI().equals("F")) out.print("FED");
							else if (cdRenov.getE08IIIPVI().equals("Q")) out.print("Emisi&oacute;n");
							else if (cdRenov.getE08IIIPVI().equals("T")) out.print("Telex");
							else if (cdRenov.getE08IIIPVI().equals("1")) out.print("Swift Formato MT-100");
							else if (cdRenov.getE08IIIPVI().equals("2")) out.print("Swift Formato MT-200");
							else if (cdRenov.getE08IIIPVI().equals("3")) out.print("Swift Formato MT-300");
							else out.print(""); }%>" >
              . El Principal ser&aacute; renovado por el mismo per&iacute;odo 
              de tiempo.</td>
          </tr>
          <tr id="trdark"> 
            <td width=35></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="J" 
			  <%if (cdRenov.getE08DEAROC().equals("J")) out.print("checked");%> disabled >
              </b>J</b></td>
            <td colspan="2" > Inter&eacute;s y Principal ser&aacute;n pagados 
              al Certificado de Dep&oacute;sito n&uacute;mero 
              <input type="text" <% if (cdRenov.getF08JJJACC().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08JJJACC" size="12" maxlength="12" value="<%= cdRenov.getE08JJJACC().trim()%>">
              .No hay renovaci&oacute;n en esta opci&oacute;n.</td>
          </tr>
          <tr id="trdark"> 
            <td width=35></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="K" 
			  <%if (cdRenov.getE08DEAROC().equals("K")) out.print("checked");%> disabled >
              </b>K</b></td>
            <td colspan="2" > Inter&eacute;s ser&aacute; pagado <%if (cdRenov.getE08KKKFL1().equals("1")) {%> 
              cada 
              <input type="text" <% if (cdRenov.getF08KKKROY().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08KKKROY" size="3" maxlength="3" value="<%= cdRenov.getE08KKKROY().trim()%>">
              <input type="text" <% if (cdRenov.getF08KKKODA().equals("Y")) out.print("id=\"txtchanged\""); %> readonly size="15"
				value="<% if (cdRenov.getE08KKKODA().equals("D")) out.print("Dias");
						  else if (cdRenov.getE08KKKODA().equals("M")) out.print("Meses");
						  else if (cdRenov.getE08KKKODA().equals("Y")) out.print("A&ntilde;os");
						  else out.print(""); %>" >
              <%}
				else if (cdRenov.getE08KKKFL1().equals("2")) {%> a fin de mes 
              <% } %> 
              <input type="text" <% if (cdRenov.getF08KKKFLG().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08KKKFLG" size="40"
				value="<% if (cdRenov.getE08KKKFLG().equals("1")) out.print("a una Cuenta (Corriente, Ahorro, etc.)");
						  else if (cdRenov.getE08KKKFLG().equals("2")) out.print("a un Certificado"); %>" >
              , que tendr&aacute;n como n&uacute;mero 
              <input type="text" <% if (cdRenov.getF08KKKACC().equals("Y")) out.print("id=\"txtchanged\""); %> readonly name="E08KKKACC" size="12" maxlength="12" value="<%= cdRenov.getE08KKKACC().trim()%>">
              .</td>
          </tr>
          <tr id="trdark"> 
            <td width=35></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="P" 
			  <%if (cdRenov.getE08DEAROC().equals("P")) out.print("checked");%> disabled >
              </b>P</b></td>
            <td colspan="2" >El D&eacute;posito no tiene Instrucciones de Renovaci&oacute;n, 
              ser&aacute; renovado aut&oacute;maticamente despu&eacute;s del Per&iacute;odo 
              de Espera.</td>
          </tr>
          <tr id="trdark"> 
            <td width=35></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td width=35><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="S" 
			  <%if (cdRenov.getE08DEAROC().equals("S")) out.print("checked");%> disabled >
              </b>S</b></td>
            <td colspan="2" >Inter&eacute;s y Principal ser&aacute;n pagados basados 
              en un Plan de Pagos definido previamente.</td>
          </tr>
           <tr id="trclear"> 
            <td width=56></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=56><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value=" " 
			  <%if (cdRenov.getE08DEAROC().trim().equals("")) out.print("checked");%> disabled>
              </b></b></td>
            <td colspan="2" >Sin Código de Renovación.</td>
          </tr>
        </table>
<%
if ( !pmnt.getNoResult() ) {
%>
        
        <table class="tableinfo" style="filter:''">
          <tr > 
            <td nowrap> 
              <table cellpadding=2 cellspacing=0 width="100%" border="0">
                <tr id="trdark"> 
                  <td nowrap> 
                    <div align="center">Cuota No. </div>
                  </td>
                  <td nowrap> 
                    <div align="center">Fecha</div>
                  </td>
                  <td nowrap> 
                    <div align="center">Principal</div>
                  </td>
                  <td nowrap> 
                    <div align="center">Intereses</div>
                  </td>
                </tr>
                <%
                pmnt.initRow();
                while (pmnt.getNextRow()) {
                    if (pmnt.getFlag().equals("")) {
                    		//out.println(coll.getRecord());
	      %> 
                <tr id="trclear"> 
                  <td nowrap> 
                    <div align="center"><%= pmnt.getRecord(0) %></div>
                  </td>
                  <td nowrap> 
                    <div align="center"><%= pmnt.getRecord(1) %></div>
                  </td>
                  <td nowrap> 
                    <div align="center"><%= pmnt.getRecord(2) %></div>
                  </td>
                  <td nowrap> 
                    <div align="center"><%= pmnt.getRecord(3) %></div>
                  </td>
                </tr>
                <%
                    }
                }
    %> 
              </table>
            </td>
          </tr>
        </table>
<%
}
%>

      </td>
    </tr>
  </table>
  </form>
</body>
</html>

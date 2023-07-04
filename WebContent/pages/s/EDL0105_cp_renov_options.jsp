<html>
<head>
<title>Commercial Paper</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="cdRenov" class="datapro.eibs.beans.EDL010508Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cp_m_opt);


</SCRIPT>


<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
	 
	 
     out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> 
<h3 align="center">Intrucciones de Pago  (Renovación)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cp_renov_options,EDL0105"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="8">
  <INPUT TYPE=HIDDEN NAME="E08DEABNK" VALUE="<%= cdRenov.getE08DEABNK().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E08DEACCY" VALUE="<%= cdRenov.getE08DEACCY().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>No. Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" onkeypress="enterInteger()" name="E08DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" onkeypress="enterInteger()" name="E08CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" onkeypress="enterInteger()" name="E08DEAACC" size="12" maxlength="9" value="<%= userPO.getIdentifier().trim()%>" readonly>
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
  <h4>Instrucciones de Pago (Renovación)</h4>
  <table class="tableinfo">
    <tr > 
      <td>
        <table cellpadding=2 cellspacing=0 align=center width=100% bordercolor="#000000">
          <tr id="trdark"> 
            <td width=39 height="46"><b><b> </b><b><b><b> 
              <input type="radio" name="E08DEAROC" value="D" 
			  <%if (cdRenov.getE08DEAROC().equals("D")) out.print("checked");%>>
              </b></b></b>D</b></td>
            <td height="46" colspan="2" > Opción de Cancelación. Principal e Intereses serán pagados a la Cuenta Contable <INPUT
					type="text" onkeypress="enterInteger()" name="E08DDDGLN" size="16"
					maxlength="12" value="<%= cdRenov.getE08DDDGLN().trim()%>">
              <A
					href="javascript:GetLedger('E08DDDGLN',document.forms[0].E08DEABNK.value,document.forms[0].E08DEACCY.value,'')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda"
					align="absbottom" border="0"></A><input type="hidden" name="E08DDDFLV" value="">. </td>
          </tr>
          <tr id="trclear"> 
            <td width=39></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=39 height="36"><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="E" 
			  <%if (cdRenov.getE08DEAROC().equals("E")) out.print("checked");%>>
              </b>E</b></td>
            <td height="36" colspan="2" >Opción de Cancelación. Principal e Intereses serán pagados a la Cuenta <INPUT
					type="text" onkeypress="enterInteger()" name="E08EEEACC" size="12"
					maxlength="9" value="<%= cdRenov.getE08EEEACC().trim()%>">
              <A
					href="javascript:GetAccount('E08EEEACC',document.forms[0].E08DEABNK.value,'RT','')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda"
					align="absbottom" border="0"></A>(Corriente, Ahorros, etc.). </td>
          </tr>
          <tr id="trclear"> 
            <td width=39></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=39><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="H" 
			  <%if (cdRenov.getE08DEAROC().equals("H")) out.print("checked");%>>
              </b>H</b></td>
            <td colspan="2" > 
              <p>Interés será pagado en
                <input type="text" onkeypress="enterInteger()" name="E08HHHROY" size="3" maxlength="3" value="<%= cdRenov.getE08HHHROY().trim()%>">
                <select name="E08HHHODA">
                  <option value="" <% if (!(cdRenov.getE08HHHODA().equals("D") || cdRenov.getE08HHHODA().equals("M")||
                cdRenov.getE08HHHODA().equals("Q")||cdRenov.getE08HHHODA().equals("S")))out.print("selected"); %>> 
                  </option>
                  <option value="D" <% if (cdRenov.getE08HHHODA().equals("D")){out.print("selected");} %>>Días</option>
                  <option value="M" <% if (cdRenov.getE08HHHODA().equals("M")){out.print("selected");} %>>Mensualmente</option>
                  <option value="Q" <% if (cdRenov.getE08HHHODA().equals("Q")){out.print("selected");} %>>Trimestralmente</option>
                  <option value="S" <% if (cdRenov.getE08HHHODA().equals("S")){out.print("selected");} %>>Semestralmente</option>
                </select>
                a una 
                <INPUT type="hidden" name="E08HHHFLG0" value="">
                <INPUT type="radio" name="CE08HHHFLG" value="1"
					onclick="document.forms[0].E08HHHFLG.value='1'"
					<%if (cdRenov.getE08HHHFLG().equals("1")) out.print("checked");%>><input type="hidden" name="E08HHHFLG" value=""> 
					Cuenta de Detalle (Corriente, Ahorros, etc.) , o a
                <INPUT type="radio" name="CE08HHHFLG" value="2"
					onclick="document.forms[0].E08HHHFLG.value='2'"
					<%if (cdRenov.getE08HHHFLG().equals("2")) out.print("checked");%>>
                un Certificado con el número
                <INPUT type="text" onkeypress="enterInteger()"
					name="E08HHHACC" size="12" maxlength="9"
					value="<%= cdRenov.getE08HHHACC().trim()%>">
                <A
					href="javascript:GetAccount('E08HHHACC',document.forms[0].E08DEABNK.value,'','')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda"
					align="absbottom" border="0"></A>. El principal será renovado por el mismo periodo de tiempo.</p>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=39></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=39><b><b> </b><b> 
              <input type="radio" name="E08DEAROC" value="I" 
			  <%if (cdRenov.getE08DEAROC().equals("I")) out.print("checked");%>>
              </b>I</b></td>
            <td colspan="2" > Interés será pagado en   
              <input type="text" onkeypress="enterInteger()" name="E08IIIROY" size="3" maxlength="3" value="<%= cdRenov.getE08IIIROY().trim()%>">
              <select name="E08IIIODA">
                <option value="" <% if (!(cdRenov.getE08IIIODA().equals("D") || cdRenov.getE08IIIODA().equals("M")||
                cdRenov.getE08IIIODA().equals("Q")||cdRenov.getE08IIIODA().equals("S")))out.print("selected"); %>> 
                </option>
                <option value="D" <% if (cdRenov.getE08IIIODA().equals("D")){out.print("selected");} %>>Días</option>
                <option value="M" <% if (cdRenov.getE08IIIODA().equals("M")){out.print("selected");} %>>Mensualmente</option>
                <option value="Q" <% if (cdRenov.getE08IIIODA().equals("Q")){out.print("selected");} %>>Trimestralmente</option>
                <option value="S" <% if (cdRenov.getE08IIIODA().equals("S")){out.print("selected");} %>>Semestralmente</option>
              </select>
             	a la Cuenta Contable 
              <INPUT type="text" onkeypress="enterInteger()"
					name="E08IIIGLN" size="16" maxlength="12"
					value="<%= cdRenov.getE08IIIGLN().trim()%>">
              <A
					href="javascript:GetLedger('E08IIIGLN',document.forms[0].E08DEABNK.value,document.forms[0].E08DEACCY.value,'')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda"
					align="absbottom" border="0"></A>. El principal será renovado por el mismo periodo de tiempo.</td>
          </tr>
          <tr id="trclear"> 
            <td width=39></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=39><b><b> </b><b> 
              <input id="typeS" type="radio" name="E08DEAROC" value="S" 
			  <%if (cdRenov.getE08DEAROC().equals("S")) out.print("checked");%>>
              </b>S</b></td>
            <td colspan="2" >El Principal e Interés será pagado basado en una cronograma de pago 
              <a href="javascript:document.forms[0].typeS.click();showCDPayMaint()"><img src="<%=request.getContextPath()%>/images/eibs_deducciones.gif" alt="Payment Plan" align="absmiddle" border="0" ></a> 
               , y será pagado a la Cuenta Contable 
              <INPUT type="text" onkeypress="enterInteger()"
					name="E08SSSGLN" size="16" maxlength="12"
					value="<%= cdRenov.getE08SSSGLN().trim()%>">
              <A
					href="javascript:GetLedger('E08SSSGLN',document.forms[0].E08DEABNK.value,document.forms[0].E08DEACCY.value,'')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda"
					align="absbottom" border="0"></A>, o a la Cuenta de Detalle <INPUT
					type="text" onkeypress="enterInteger()" name="E08SSSACC" size="12"
					maxlength="9" value="<%= cdRenov.getE08SSSACC().trim()%>">
              <A
					href="javascript:GetAccount('E08SSSACC',document.forms[0].E08DEABNK.value,'RT','')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda"
					align="absbottom" border="0"></A>. </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
   <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>

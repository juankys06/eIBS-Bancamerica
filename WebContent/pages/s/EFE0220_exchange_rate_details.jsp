<html>
<head>
<title>Tasas de Cambio</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="rates" class="datapro.eibs.beans.EFE022001Message"  scope="session" />

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


<H3 align="center">Mantenimiento de Tasas de Cambio<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="exchange_rate_details.jsp, EFE0220"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEFE0220" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="E01RATBNK" value="<%= rates.getE01RATBNK()%>">
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>C&oacute;digo de Moneda:</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> <b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01RATCCY" size="4"  maxlength="3" value="<%= rates.getE01RATCCY().trim()%>">
                <input type="text" name="E01RATDSC" size="40"  maxlength="35" value="<%= rates.getE01RATDSC().trim()%>">
                </font></font></font> </b></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Fecha de Hoy :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><b><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01RATRD1" size="3"  maxlength="2" value="<%= rates.getE01RATRD1().trim()%>" readonly>
                <input type="text" name="E01RATRD2" size="3"  maxlength="2" value="<%= rates.getE01RATRD2().trim()%>" readonly>
                <input type="text" name="E01RATRD3" size="3"  maxlength="2" value="<%= rates.getE01RATRD3().trim()%>" readonly>
                </font></font></font></b></div>
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
              <div align="right">Tasa Spot de Hoy :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01RATEXR" maxlength="11" size="12" value="<%= rates.getE01RATEXR().trim()%>" >
            </td>
            <td nowrap> 
              <div align="right">Multiplica / Divide :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="radio" name="E01RATMUD" value="M"  <%if (rates.getE01RATMUD().equals("M")) out.print("checked"); %>>
                Mult. 
                <input type="radio" name="E01RATMUD" value="D"  <%if (rates.getE01RATMUD().equals("D")) out.print("checked"); %>>
                Div.</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Tasa Equivalente USD :</div>
            </td>
            <td nowrap height="23"> 
              <input type="text" name="E01RATUSD" maxlength="11" size="12" value="<%= rates.getE01RATUSD().trim()%>" >
            </td>
            <td nowrap height="23"> 
              <div align="right">Tasa Impuesto IVA :</div>
            </td>
            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="E01RATRF2" size="12" maxlength="11" value="<%= rates.getE01RATRF2().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Tasa Referencia Operativa :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E01RATRF3" maxlength="11" size="12" value="<%= rates.getE01RATRF3().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="right">Cuenta de M.E. :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E01RATNAC" size="17" maxlength="16" value="<%= rates.getE01RATNAC().trim()%>">
                <a href="javascript:GetLedger('E01RATNAC',document.forms[0].E01RATBNK.value,document.forms[0].E01RATCCY.value,'')"> 
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Tolerancia Tesorero :</div>
            </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01RATTLR" maxlength="5" size="6" value="<%= rates.getE01RATTLR().trim()%>" >
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">D&iacute;as Base :</div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="left"> 
                <input type="text" name="E01RATBSE" maxlength="3" size="4" value="<%= rates.getE01RATBSE().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Tolerancia Operativa :</div>
            </td>
            <td nowrap width="40%" height="19"><b><font face="Arial"><font face="Arial"><font size="2"> 
              <input type="text" name="E01RATTLO" maxlength="5" size="6" value="<%= rates.getE01RATTLO().trim()%>" >
              </font></font></font></b> </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Tipo de Cuenta :</div>
            </td>
            <td nowrap width="28%" height="19"> 
              <div align="left"> 
                <input type="radio" name="E01RATNAT" value="G"  <%if (rates.getE01RATNAT().equals("G")) out.print("checked"); %>>
                Contable 
                <input type="radio" name="E01RATNAT" value="R"  <%if (rates.getE01RATNAT().equals("R")) out.print("checked"); %>>
                Corriente</div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n de Tasas</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="center"><b>Tasa Contravalor</b></div>
            </td>
            <td nowrap colspan="2"> 
              <div align="center"><b>Tasa de Cambio M.E</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Compras :</div>
            </td>
            <td nowrap height="23"> 
              <input type="text" name="E01RATCPR" maxlength="11" size="12" value="<%= rates.getE01RATCPR().trim()%>" >
            </td>
            <td nowrap height="23"> 
              <div align="right">Compras :</div>
            </td>
            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="E01RATFPR" size="12" maxlength="11" value="<%= rates.getE01RATFPR().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Ventas :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E01RATCSR" maxlength="11" size="12" value="<%= rates.getE01RATCSR().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="right">Ventas :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <input type="text" name="E01RATFSR" size="12" maxlength="11" value="<%= rates.getE01RATFSR().trim()%>">
                </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Tasas de Cambio a Futuro</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <div align="center"><b>Puntos</b></div>
            </td>
            <td nowrap> 
              <div align="center"><b>Tasa</b></div>
            </td>
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <div align="center"><b>Puntos</b></div>
            </td>
            <td nowrap> 
              <div align="center"><b>Tasa</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right"><b>07 D&iacute;as :</b></div>
            </td>
            <td nowrap height="23"> 
              <div align="center"> 
                <input type="text" name="E01P7" maxlength="3" size="4" value="<%= rates.getE01P7().trim()%>" >
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="center"> 
                <input type="text" name="E01RATFR1" size="12" maxlength="11" value="<%= rates.getE01RATFR1().trim()%>" >
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="right"><b>15 D&iacute;as :</b></div>
            </td>
            <td nowrap height="23"> 
              <div align="center"> 
                <input type="text" name="E01P15" maxlength="3" size="4" value="<%= rates.getE01P15().trim()%>" >
              </div>
            </td>
            <td nowrap height="23"> 
              <div align="center"> 
                <input type="text" name="E01RATFR2" size="12" maxlength="11" value="<%= rates.getE01RATFR2().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right"><b>30 D&iacute;as :</b></div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01P30" maxlength="3" size="4" value="<%= rates.getE01P30().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01RATFR3" size="12" maxlength="11" value="<%= rates.getE01RATFR3().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="right"><b>60 D&iacute;as :</b></div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01P60" maxlength="3" size="4" value="<%= rates.getE01P60().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01RATFR4" size="12" maxlength="11" value="<%= rates.getE01RATFR4().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right"><b>90 D&iacute;as :</b></div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01P90" maxlength="3" size="4" value="<%= rates.getE01P7().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01RATFR5" size="12" maxlength="11" value="<%= rates.getE01RATFR5().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="right"><b>120 D&iacute;as :</b></div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01P120" maxlength="3" size="4" value="<%= rates.getE01P120().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01RATFR6" size="12" maxlength="11" value="<%= rates.getE01RATFR6().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right"><b>150 D&iacute;as :</b></div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01P150" maxlength="3" size="4" value="<%= rates.getE01P150().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01RATFR7" size="12" maxlength="11" value="<%= rates.getE01RATFR7().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="right"><b>180 D&iacute;as :</b></div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01P180" maxlength="3" size="4" value="<%= rates.getE01P180().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01RATFR8" size="12" maxlength="11" value="<%= rates.getE01RATFR8().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right"><b>270 D&iacute;as :</b></div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01P270" maxlength="3" size="4" value="<%= rates.getE01P270().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01RATFR9" size="12" maxlength="11" value="<%= rates.getE01RATFR9().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="right"><b>1 A&ntilde;o :</b></div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01P360" maxlength="3" size="4" value="<%= rates.getE01P360().trim()%>" >
              </div>
            </td>
            <td nowrap height="19"> 
              <div align="center"> 
                <input type="text" name="E01RATF10" size="12" maxlength="11" value="<%= rates.getE01RATF10().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <div align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>

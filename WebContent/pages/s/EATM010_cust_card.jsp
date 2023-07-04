<html>
<head>
<title>Archivo de Tarjetas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="atmDetail" class="datapro.eibs.beans.EATM01001Message"  scope="session" />

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


<H3 align="center">Mantenimiento de Archivo de Tarjetas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_money, EDD0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEATM010" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01ATMCUN" size="9" maxlength="9" value="<%= atmDetail.getE01ATMCUN()%>">
                <font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01ATMNAM" size="40" maxlength="35" value="<%= atmDetail.getE01ATMNAM()%>">
                </font></font></font></div>
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>N&uacute;mero de Tarjeta :</b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01ATMPAN" size="21" maxlength="19" value="<%= atmDetail.getE01ATMPAN().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Cuentas</H4>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="center"><b>Cuenta</b></div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"><b>Corrientes</b></div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"><b>Ahorro</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="center"><b>1 </b></div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01ATMDD1" size="13" maxlength="12" value="<%= atmDetail.getE01ATMDD1().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','RA'); ">
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E01ATMSA1" size="13" maxlength="15" value="<%= atmDetail.getE01ATMSA1().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','04'); ">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="center"><b>2 </b></div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01ATMDD2" size="13" maxlength="12" value="<%= atmDetail.getE01ATMDD2().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','RA'); ">
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E01ATMSA2" size="13" maxlength="12" value="<%= atmDetail.getE01ATMSA2().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','04'); ">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="center"><b>3 </b></div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="center"> 
                <input type="text" name="E01ATMDD3" size="13" maxlength="12" value="<%= atmDetail.getE01ATMDD3().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','RA'); ">
              </div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="center"> 
                <input type="text" name="E01ATMSA3" size="13" maxlength="12" value="<%= atmDetail.getE01ATMSA3().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','04'); ">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="center"><b>4 </b></div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01ATMDD4" size="13" maxlength="12" value="<%= atmDetail.getE01ATMDD4().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','RA'); ">
              </div>
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01ATMSA4" size="13" maxlength="12" value="<%= atmDetail.getE01ATMSA4().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','04'); ">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="center"><b>5</b></div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01ATMDD5" size="13" maxlength="12" value="<%= atmDetail.getE01ATMDD5().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','RA'); ">
              </div>
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01ATMSA5" size="13" maxlength="12" value="<%= atmDetail.getE01ATMSA5().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','04'); ">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="center"><b>6</b></div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01ATMDD6" size="13" maxlength="12" value="<%= atmDetail.getE01ATMDD6().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','RA'); ">
              </div>
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="center"> 
                <input type="text" name="E01ATMSA6" size="13" maxlength="12" value="<%= atmDetail.getE01ATMSA6().trim()%>" 
				 oncontextmenu="showPopUp(accountHelp,this.name,'','','','','04'); ">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Cupos</H4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="center"><b>Cupos</b></div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"><b>En Linea</b></div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"><b>Fuera Linea</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Asignado :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01ATMCOO" size="16" maxlength="15" value="<%= atmDetail.getE01ATMCOO().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E01ATMCOF" size="16" maxlength="15" value="<%= atmDetail.getE01ATMCOF().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Disponible :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E01ATMCOD" size="16" maxlength="15" value="<%= atmDetail.getE01ATMCOD().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E01ATMCFD" size="16" maxlength="15" value="<%= atmDetail.getE01ATMCFD().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Utilizado :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="center"> 
                <input type="text" name="E01ATMONR" size="16" maxlength="15" value="<%= atmDetail.getE01ATMONR().trim()%>" onKeyPress="enterDecimal()">
              </div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="center"> 
                <input type="text" name="E01ATMOFR" size="16" maxlength="15" value="<%= atmDetail.getE01ATMOFR().trim()%>" onKeyPress="enterDecimal()">
              </div>
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
            <td nowrap width="29%"> 
              <div align="right">Ultima Transacci&oacute;n :</div>
            </td>
            <td nowrap width="19%"> 
              <div align="left"> 
                <input type="text" name="E01ATMLTM" size="3" maxlength="2" value="<%= atmDetail.getE01ATMLTM().trim()%>" onKeyPress="enterInteger()">
                <input type="text" name="E01ATMLTD" size="3" maxlength="2" value="<%= atmDetail.getE01ATMLTD().trim()%>" onKeyPress="enterInteger()">
                <input type="text" name="E01ATMLTY" size="3" maxlength="2" value="<%= atmDetail.getE01ATMLTY().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="right">Transacciones en el Mes :</div>
            </td>
            <td nowrap width="26%"> 
              <div align="left"> 
                <input type="text" name="E01ATMNTR" size="4" maxlength="3" value="<%= atmDetail.getE01ATMNTR().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="left"> 
                <input type="text" name="E01ATMODM" size="3" maxlength="2" value="<%= atmDetail.getE01ATMODM().trim()%>" onKeyPress="enterInteger()">
                <input type="text" name="E01ATMODD" size="3" maxlength="2" value="<%= atmDetail.getE01ATMODD().trim()%>" onKeyPress="enterInteger()">
                <input type="text" name="E01ATMODY" size="3" maxlength="2" value="<%= atmDetail.getE01ATMODY().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="right">Estado de la Tarjeta :</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="left"> 
                <input type="text" name="E01ATMSTS" size="3" maxlength="2" value="<%= atmDetail.getE01ATMSTS().trim()%>" >
                <a href="javascript:GetCode('E01ATMSTS','STATIC_card_status.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">A&ntilde;o de Expiraci&oacute;n :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="left">
                <input type="text" name="E01ATMEXY" size="3" maxlength="2" value="<%= atmDetail.getE01ATMEXY().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="right">Genera Tarjeta :</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="left">
                <input type="radio" name="E01ATMGTA" value="Y" <%if (atmDetail.getE01ATMGTA().equals("Y")) out.print("checked"); %>>
                S&iacute; 
                <input type="radio" name="E01ATMGTA" value="N" <%if (atmDetail.getE01ATMGTA().equals("N")) out.print("checked"); %>>
                No</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Mes de Expiraci&oacute;n :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="left">
                <input type="text" name="E01ATMEXM" size="3" maxlength="2" value="<%= atmDetail.getE01ATMEXM().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="right">Tipo de Tarjeta :</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="left">
                <input type="text" name="E01ATMTYP" size="3" maxlength="2" value="<%= atmDetail.getE01ATMTYP().trim()%>" >
                <a href="javascript:GetCode('E01ATMTYP','STATIC_card_type.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Cobros Pendientes ATM :</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="left">
                <input type="text" name="E01ATMCOT" size="3" maxlength="2" value="<%= atmDetail.getE01ATMCOT().trim()%>" onKeyPress="enterInteger()">
              </div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="left"></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <H4>&nbsp;</H4>
  <p>
    <div align="center"> 
      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
    </div>
  </form>
</body>
</html>

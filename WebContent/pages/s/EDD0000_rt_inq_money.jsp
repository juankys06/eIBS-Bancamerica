<html>
<head>
<title>Control de Lavado de Dinero</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="rtMoney" class="datapro.eibs.beans.ELD000001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
%>

 function CheckSubmit()
{
   if(userPO.getPurpose().equals("NEW")){
   document.forms[0].SCREEN.value=2;}
   else{
   document.forms[0].SCREEN.value=4;}
   document.forms[0].submit();
}
   
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");

%>


<H3 align="center">Par&aacute;metros - Control de Lavado de Dinero<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_inq_money.jsp, EDD0000"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="33">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p><H4>Datos B&aacute;sicos de la Operaci&oacute;n</H4>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%">
              <div align="right"><B>Tipo de Actividad</B></div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"><B>N&uacute;mero</B></div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"><B>Monto</B></div>
            </td>
				<TD nowrap width="26%"></TD>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Dep&oacute;sitos Efectivo:</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E06LDMCDP" size="5" maxlength="5" value="<%= rtMoney.getE06LDMCDP().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E06LDMCDA" size="15" maxlength="15" value="<%= rtMoney.getE06LDMCDA().trim()%>" readonly>
              </div>
            </td>
				<TD nowrap width="26%"></TD>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Dep&oacute;sitos Otros:</div>
            </td>
            <td nowrap width="19%"> 
              <div align="center"> 
                <input type="text" name="E06LDMKDP" size="5" maxlength="5" value="<%= rtMoney.getE06LDMKDP().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%"> 
              <div align="center"> 
                <input type="text" name="E06LDMKDA" size="15" maxlength="15" value="<%= rtMoney.getE06LDMKDA().trim()%>" readonly>
              </div>
            </td>
				<TD nowrap width="26%"></TD>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Retiros en Efectivo:</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <div align="center"> 
                <input type="text" name="E06LDMCWD" size="5" maxlength="5" value="<%= rtMoney.getE06LDMCWD().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="center"> 
                <input type="text" name="E06LDMCWA" size="15" maxlength="15" value="<%= rtMoney.getE06LDMCWA().trim()%>" readonly>
              </div>
            </td>
				<TD nowrap width="26%" height="23"></TD>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Pagos de Cheques:</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text" name="E06LDMCPY" size="5" maxlength="5" value="<%= rtMoney.getE06LDMCPY().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="center"> 
                <input type="text" name="E06LDMCPA" size="15" maxlength="15" value="<%= rtMoney.getE06LDMCPA().trim()%>" readonly>
              </div>
            </td>
				<TD nowrap width="26%" height="19"></TD>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Transferencias Emitidas:</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text" name="E06LDMTIN" size="5" maxlength="5" value="<%= rtMoney.getE06LDMTIN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="center"> 
                <input type="text" name="E06LDMTIA" size="15" maxlength="15" value="<%= rtMoney.getE06LDMTIA().trim()%>" readonly>
              </div>
            </td>
				<TD nowrap width="26%" height="19"></TD>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Transferencias Recibidas</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <div align="center"> 
                <input type="text" name="E06LDMTRV" size="5" maxlength="5" value="<%= rtMoney.getE06LDMTRV().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="center"> 
                <input type="text" name="E06LDMTRA" size="15" maxlength="15" value="<%= rtMoney.getE06LDMTRA().trim()%>" readonly>
              </div>
            </td>
				<TD nowrap width="26%" height="19"></TD>
			</tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Saldos</H4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="38%"> 
              <div align="right">Saldo Promedio en Libros:</div>
            </td>
            <td nowrap width="62%"> 
              <input type="text" name="E06LDMGAV" size="15" maxlength="15" value="<%= rtMoney.getE06LDMGAV().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="38%"> 
              <div align="right">Saldo Promedio Neto:</div>
            </td>
            <td nowrap width="62%"> 
              <input type="text" name="E06LDMNAV" size="15" maxlength="15" value="<%= rtMoney.getE06LDMNAV().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
   <H4>Datos Adicionales</H4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="38%"> 
              <div align="right">Motivo Solicitud Servicio:</div>
            </td>
            <td nowrap width="62%"> 
              <input name="E06LDMMOT" type="text" value="<%= rtMoney.getE06LDMMOT().trim()%>" size="5" maxlength="4" readonly="readonly" >
              <input type="text" name="D06LDMMOT" size="31" maxlength="30" value="<%= rtMoney.getD06LDMMOT().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="38%"> 
              <div align="right">Uso que la dara a Cuenta:</div>
            </td>
            <td nowrap width="62%"> 
              <input name="E06LDMUSO" type="text" value="<%= rtMoney.getE06LDMUSO().trim()%>" size="5" maxlength="4" readonly="readonly" >
              <input type="text" name="D06LDMUSO" size="31" maxlength="30" value="<%= rtMoney.getD06LDMUSO().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="38%"> 
              <div align="right">Volumen Mensual de Ventas:</div>
            </td>
            <td nowrap width="62%"> 
              <input name="E06LDMVOL" type="text" onKeypress="enterDecimal()" value="<%= rtMoney.getE06LDMVOL().trim()%>" size="20" maxlength="20" readonly="readonly">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="38%"> 
              <div align="right">Monto Promedio Mensual de Depositos:</div>
            </td>
            <td nowrap width="62%"> 
              <input name="E06LDMDEP" type="text" onKeypress="enterDecimal()" value="<%= rtMoney.getE06LDMDEP().trim()%>" size="20" maxlength="20" readonly="readonly">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="38%"> 
              <div align="right">Porcentaje Mensual Deposito en Cheques:</div>
            </td>
            <td nowrap width="62%"> 
              <input name="E06LDMPMD" type="text" value="<%= rtMoney.getE06LDMPMD().trim()%>" size="15" maxlength="10" readonly="readonly" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="38%"> 
              <div align="right">Se permitir&aacute;n Transferencias:</div>
            </td>
            <td nowrap width="62%"> 
             <input name="E06LDMTRF" type="hidden" value="<%= rtMoney.getE06LDMTRF()%>" readonly="readonly">
              <input name="CE06LDMTRF" type="radio" disabled onClick="document.forms[0].E06LDMTRF.value='Y'" value="Y" readonly="readonly"
			  <%if(rtMoney.getE06LDMTRF().equals("Y")) out.print("checked");%>>
              Si 
              <input name="CE06LDMTRF" type="radio" disabled onClick="document.forms[0].E06LDMTRF.value='N'" value="N" readonly="readonly"
			  <%if(!rtMoney.getE06LDMTRF().equals("Y")) out.print("checked");%>>
              No
             </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="38%"> 
              <div align="right">Pa&iacute;s de Transferencias 1:</div>
            </td>
            <td nowrap width="62%"> 
              <input type="text" name="E06LDMCN1" size="5" maxlength="4" value="<%= rtMoney.getE06LDMCN1().trim()%>" >
              <input type="text" name="D06LDMCN1" size="31" maxlength="30" value="<%= rtMoney.getD06LDMCN1().trim()%>" readonly>
              <input type="radio" disabled name="E06LDMFG1" value="1" <%if(rtMoney.getE06LDMFG1().equals("1")) out.print("checked");%>>Env. 
              <input type="radio" disabled name="E06LDMFG1" value="2" <%if(rtMoney.getE06LDMFG1().equals("2")) out.print("checked");%>>Rec.
              <input type="radio" disabled name="E06LDMFG1" value="B" <%if(rtMoney.getE06LDMFG1().equals("B")) out.print("checked");%>>Ambas
            </td>
            
          </tr>
          <tr id="trclear"> 
            <td nowrap width="38%"> 
              <div align="right">Pa&iacute;s de Transferencias 2:</div>
            </td>
            <td nowrap width="62%"> 
              <input type="text" name="E06LDMCN2" size="5" maxlength="4" value="<%= rtMoney.getE06LDMCN2().trim()%>" >
              <input type="text" name="D06LDMCN2" size="31" maxlength="30" value="<%= rtMoney.getD06LDMCN2().trim()%>" readonly>
              <input type="radio" disabled name="E06LDMFG2" value="1" <%if(rtMoney.getE06LDMFG2().equals("1")) out.print("checked");%>>Env. 
              <input type="radio" disabled name="E06LDMFG2" value="2" <%if(rtMoney.getE06LDMFG2().equals("2")) out.print("checked");%>>Rec.
              <input type="radio" disabled name="E06LDMFG2" value="B" <%if(rtMoney.getE06LDMFG2().equals("B")) out.print("checked");%>>Ambas
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="38%"> 
              <div align="right">Pa&iacute;s de Transferencias 3:</div>
            </td>
            <td nowrap width="62%"> 
              <input type="text" name="E06LDMCN3" size="5" maxlength="4" value="<%= rtMoney.getE06LDMCN3().trim()%>" >
              <input type="text" name="D06LDMCN3" size="31" maxlength="30" value="<%= rtMoney.getD06LDMCN3().trim()%>" readonly>
              <input type="radio" disabled name="E06LDMFG3" value="1" <%if(rtMoney.getE06LDMFG3().equals("1")) out.print("checked");%>>Env. 
              <input type="radio" disabled name="E06LDMFG3" value="2" <%if(rtMoney.getE06LDMFG3().equals("2")) out.print("checked");%>>Rec.
              <input type="radio" disabled name="E06LDMFG3" value="B" <%if(rtMoney.getE06LDMFG3().equals("B")) out.print("checked");%>>Ambas
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="38%"> 
              <div align="right">Pa&iacute;s de Transferencias 4:</div>
            </td>
            <td nowrap width="62%"> 
              <input type="text" name="E06LDMCN4" size="5" maxlength="4" value="<%= rtMoney.getE06LDMCN4().trim()%>" >
              <input type="text" name="D06LDMCN4" size="31" maxlength="30" value="<%= rtMoney.getD06LDMCN4().trim()%>" readonly>
              <input type="radio" disabled name="E06LDMFG4" value="1" <%if(rtMoney.getE06LDMFG4().equals("1")) out.print("checked");%>>Env. 
              <input type="radio" disabled name="E06LDMFG4" value="2" <%if(rtMoney.getE06LDMFG4().equals("2")) out.print("checked");%>>Rec.
              <input type="radio" disabled name="E06LDMFG4" value="B" <%if(rtMoney.getE06LDMFG4().equals("B")) out.print("checked");%>>Ambas
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="38%"> 
              <div align="right">Modo de Apertura:</div>
            </td>
            <td nowrap width="62%">
              <input name="E06LDMUC1" type="text" value="<%= rtMoney.getE06LDMUC1().trim()%>" size="5" maxlength="4" readonly="readonly" >
              <input type="text" name="D06LDMUC1" size="31" maxlength="30" value="<%= rtMoney.getD06LDMUC1().trim()%>" readonly>
           </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="38%"> 
              <div align="right">Origen de los Fondos:</div>
            </td>
            <td nowrap width="62%"> 
              <input type="text" name="E06LDMUC3" size="5" maxlength="4" value="<%= rtMoney.getE06LDMUC3().trim()%>" readonly>
              <input type="text" name="D06LDMUC3" size="31" maxlength="30" value="<%= rtMoney.getD06LDMUC3().trim()%>" readonly>
           </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="38%"> 
              <div align="right">Monto de Apertura:</div>
            </td>
            <td nowrap width="62%"> 
              <input name="E06LDMAM1" type="text" onKeypress="enterDecimal()" value="<%= rtMoney.getE06LDMAM1().trim()%>" size="20" maxlength="20" readonly="readonly">
           </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>
  
  </form>
</body>
</html>

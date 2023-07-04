<html>
<head>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id= "clBasic" class= "datapro.eibs.beans.ELN006002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cl_i_opt);

</SCRIPT>

<body>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<SCRIPT> initMenu(); </SCRIPT>

<h3 align="center">Informaci&oacute;n B&aacute;sica de la L&iacute;nea de Cr&eacute;dito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cl_inq_basic.jsp, ELN0060"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="32">
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
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="CUSNA1" size="45" readonly maxlength="45" value="<%= userPO.getCusName().trim()%>" >
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Tipo de Linea : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="PROCOD" size="4" readonly maxlength="4" value="<%= userPO.getCreditLineType().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>N&uacute;mero de Linea :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="ACCNUM" size="12" readonly maxlength="12" value="<%= userPO.getCreditLineNum().trim()%>" >
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
                <input type="text" name="PROCCY" size="4" readonly maxlength="4" value="<%= userPO.getCurrency().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4></h4>
    <table class="tableinfo" >
      <tr > 
        <td >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td width=34%> 
              <div align="right"> Comprometido : </div>
            </td>
            <td width=12%> 
              <div align="left"> 
                <input type="text" name="E02LNECOU" readonly  size="3" maxlength="1" value="<%= clBasic.getE02LNECOU().trim()%>">
              </div>
            </td>
            <td width=34%> 
              <div align="right">Cuenta Contable : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" name="E02LNEGLN" readonly  size="18" maxlength="16" value="<%= clBasic.getE02LNEGLN().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=34%> 
              <div align="right"> Banco/Sucursal : </div>
            </td>
            <td width=12% nowrap> 
              <div align="left"> 
                <input type="text" name="E02LNEBNK" readonly  size="4" maxlength="2" value="<%= clBasic.getE02LNEBNK().trim()%>">
                <input type="text" name="E02LNEBRN" readonly  size="5" maxlength="3" value="<%= clBasic.getE02LNEBRN().trim()%>">
              </div>
            </td>
            <td width=34%> 
              <div align="right"> Fecha de Apertura : </div>
            </td>
            <td width=20% nowrap> 
              <div align="left"> 
                <input type="text" name="E02LNEOP1" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNEOP1().trim()%>">
                <input type="text" name="E02LNEOP2" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNEOP2().trim()%>">
                <input type="text" name="E02LNEOP3" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNEOP3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=34%> 
              <div align="right"> Utiliza en Multimoneda : </div>
            </td>
            <td width=12%> 
              <div align="left"> 
                <input type="text" name="E02LNEMUB" readonly  size="3" maxlength="1" value="<%= clBasic.getE02LNEMUB().trim()%>">
              </div>
            </td>
            <td width=34%> 
              <div align="right"> Fecha de Vencimiento : </div>
            </td>
            <td width=20% nowrap> 
              <div align="left"> 
                <input type="text" name="E02LNEMT1" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNEMT1().trim()%>">
                <input type="text" name="E02LNEMT2" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNEMT2().trim()%>">
                <input type="text" name="E02LNEMT3" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNEMT3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=34%> 
              <div align="right"> Tipo Autorizado : </div>
            </td>
            <td width=12%> 
              <div align="left"> 
                <input type="text" name="E02LNEATY" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNEATY().trim()%>">
 				
              </div>
            </td>
            <td width=34%> 
              <div align="right"> Fecha de Autorizaci&oacute;n : </div>
            </td>
            <td width=20% nowrap> 
              <div align="left"> 
                <input type="text" name="E02LNEAU1" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNEAU1().trim()%>">
                <input type="text" name="E02LNEAU2" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNEAU2().trim()%>">
                <input type="text" name="E02LNEAU3" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNEAU3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=34%> 
              <div align="right"> Tipo de Cargos : </div>
            </td>
            <td width=12%> 
              <div align="left"> 
                <input type="text" name="E02LNEFET" readonly  size="4" maxlength="2" value="<%= clBasic.getE02LNEFET().trim()%>">
              </div>
            </td>
            <td width=34%> 
              <div align="right"> Pr&oacute;xima Fecha de Cargos : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" name="E02LNECH1" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNECH1().trim()%>">
                <input type="text" name="E02LNECH2" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNECH2().trim()%>">
                <input type="text" name="E02LNECH3" readonly  size="2" maxlength="2" value="<%= clBasic.getE02LNECH3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=34%> 
              <div align="right">Frecuencia de Cargos : </div>
            </td>
            <td width=12%> 
              <div align="left" nowrap> 
                <input type="text" name="E02LNEPRD" readonly  size="3" maxlength="1" value="<%= clBasic.getE02LNEPRD().trim()%>">
              </div>
            </td>
            <td width=34%> 
              <div align="right"> Tasa Base Cargos : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" name="E02LNEBSR" readonly  size="11" maxlength="9" value="<%= clBasic.getE02LNEBSR().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=34%> 
              <div align="right"> Base en D&iacute;as : </div>
            </td>
            <td width=12%> 
              <div align="left"> 
                <input type="text" name="E02LNEYBS" readonly  size="11" maxlength="9" value="<%= clBasic.getE02LNEYBS().trim()%>">
              </div>
            </td>
            <td width=34%> 
              <div align="right"> Centro de Costo : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" name="E02LNECCS" readonly  size="11" maxlength="9" value="<%= clBasic.getE02LNECCS().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=34%> 
              <div align="right"> Cuenta D&eacute;bitos : </div>
            </td>
            <td colspan="3" nowrap> 
              <div align="left"> 
                <input type="text" name="E02LNEBDA" readonly  size="4" maxlength="2" value="<%= clBasic.getE02LNEBDA().trim()%>">
                <input type="text" name="E02LNEDAB" readonly  size="5" maxlength="3" value="<%= clBasic.getE02LNEDAB().trim()%>">
                <input type="text" name="E02LNECDA" readonly  size="5" maxlength="3" value="<%= clBasic.getE02LNECDA().trim()%>">
                <input type="text" name="E02LNEADN" readonly  size="18" maxlength="16" value="<%= clBasic.getE02LNEADN().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=34%> 
              <div align="right">Categor&iacute;a de Linea : </div>
            </td>
            <td colspan="3"> 
              <div align="left"> 
                <input type="text" name="E02LNECAT" readonly  size="11" maxlength="9" value="<%= clBasic.getE02LNECAT().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=34%> 
              <div align="right">Cliente/Line de Control : </div>
            </td>
            <td colspan="3" nowrap> 
              <div align="left"> 
                <input type="text" name="E02LNECCU" readonly  size="9" maxlength="9" value="<%= clBasic.getE02LNECCU().trim()%>">
                <input type="text" name="E02LNECLN" readonly  size="4" maxlength="4" value="<%= clBasic.getE02LNECLN().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=34%> 
              <div align="right">Cliente/Linea Relacionada :</div>
            </td>
            <td colspan="3" nowrap> 
              <input type="text" name="E02LNECGM" readonly  size="9" maxlength="9" value="<%= clBasic.getE02LNECGM().trim()%>">
              <input type="text" name="E02LNELGM" readonly  size="4" maxlength="4" value="<%= clBasic.getE02LNELGM().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=34%> 
              <div align="right">T&eacute;rmino : </div>
            </td>
            <td colspan="3" nowrap> 
              <input type="text" name="E02LNETER" readonly  size="5" maxlength="3" value="<%= clBasic.getE02LNETER().trim()%>">
              <input type="text" name="E02LNETRC" readonly  size="3" maxlength="1" value="<%= clBasic.getE02LNETRC().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=34%> 
              <div align="right">D&iacute;as y Tasa de Gracia : </div>
            </td>
            <td colspan="3" nowrap> 
              <div align="left"> 
                <input type="text" name="E02LNEMXT" readonly  size="5" maxlength="3" value="<%= clBasic.getE02LNEMXT().trim()%>">
                <input type="text" name="E02LNEMRT" readonly  size="11" maxlength="9" value="<%= clBasic.getE02LNEMRT().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=34%> 
              <div align="right"> C&oacute;digo Tipo Tasa Flotante : </div>
            </td>
            <td width=12% nowrap> 
              <div align="left"> 
                <input type="text" name="E02LNEFTB" readonly  size="4" maxlength="2" value="<%= clBasic.getE02LNEFTB().trim()%>">
                <input type="text" name="E02LNEFTY" readonly  size="4" maxlength="2" value="<%= clBasic.getE02LNEFTY().trim()%>">
              </div>
            </td>
            <td width=34%> 
              <div align="right"> Tasa Base/Spread : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" name="E02LNEORT" readonly  size="11" maxlength="9" value="<%= clBasic.getE02LNEORT().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=34%> 
              <div align="right">Nivel de Cr&eacute;dito Cliente : </div>
            </td>
            <td width=12%> 
              <div align="left"> 
                <input type="text" name="E02LNECRR" readonly  size="3" maxlength="1" value="<%= clBasic.getE02LNECRR().trim()%>">
              </div>
            </td>
            <td width=34%> 
              <div align="right">C&oacute;digo de Impuesto : </div>
            </td>
            <td width=20% nowrap> 
              <div align="left"> 
                <input type="text" name="E02LNEIVA" readonly  size="3" maxlength="1" value="<%= clBasic.getE02LNEIVA().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=34%> 
              <div align="right"> T&eacute;rmino de la L&iacute;nea : </div>
            </td>
            <td width=12%> 
              <div align="left"> 
                <input type="text" name="E02LNETRM" readonly  size="3" maxlength="1" value="<%= clBasic.getE02LNETRM().trim()%>">
              </div>
            </td>
            <td width=34%> 
              <div align="right"> Tabla Tarifa L/C : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" name="E02LNETLC" readonly  size="4" maxlength="2" value="<%= clBasic.getE02LNETLC().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=34%> 
              <div align="right"> Tabla Tarifa Pr&eacute;stamos : </div>
            </td>
            <td width=12%> 
              <div align="left"> 
                <input type="text" name="E02LNETLN" readonly  size="4" maxlength="2" value="<%= clBasic.getE02LNETLN().trim()%>">
              </div>
            </td>
            <td width=34%> 
              <div align="right"> Tabla Tarifa CTA CTE : </div>
            </td>
            <td width=20%> 
              <div align="left"> 
                <input type="text" name="E02LNETRT" readonly  size="4" maxlength="2" value="<%= clBasic.getE02LNETRT().trim()%>">
              </div>
            </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
</form>
</body>
</html>


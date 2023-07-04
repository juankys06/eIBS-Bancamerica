<html>
<head>
<title>Tabla de Cargos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lnServCharge" class="datapro.eibs.beans.ESD0713DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getHeader23().equals("G") ||  userPO.getHeader23().equals("V")){
%>
	builtNewMenu(ln_i_1_opt);
<%   
}
else  {
%>
	builtNewMenu(ln_i_2_opt);
<%   
}
%>

</SCRIPT>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   if (userPO.getPurpose().equals("INQUIRY")){ 
   out.println("<SCRIPT> initMenu(); </SCRIPT>");}
%> 


<h3 align="center">Consulta de Tabla de Cargos de Pr&eacute;stamos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_inq_ren_rate, EDL0160"></h3>
<hr size="4">
<form >
  <p></p>
  
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
  <h4>Informaci&oacute;n General</h4>
  <table class="tableinfo">
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="49%"> 
              <div align="right">C&oacute;digo del Banco:</div>
            </td>
            <td width="51%"> 
              <input type="text" name="E01SELBNK" size="4" maxlength="2" value="<%= lnServCharge.getE01SELBNK().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="49%"> 
              <div align="right">Tipo de Producto :</div>
            </td>
            <td width="51%"> 
              <input type="text" name="E01SELTYP" size="6" maxlength="4" value="<%= lnServCharge.getE01SELTYP().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="49%"> 
              <div align="right">N&uacute;mero de Tabla:</div>
            </td>
            <td width="51%"> 
              <input type="text" name="E01SELTLN" size="4" maxlength="2" value="<%= lnServCharge.getE01SELTLN().trim()%>">
              <input type="text" name="E01DLSDSC" size="37" maxlength="35" value="<%= lnServCharge.getE01DLSDSC().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <h4>Parametros de Control</h4>

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark">
            <td width="27%" >&nbsp;</td>
            <td width="25%" >&nbsp;</td>
            <td width="15%" >&nbsp;</td>
            <td colspan="5" >&nbsp;</td>
            <td >
              <div align="center"><b>Factor</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Per&iacute;odo Base :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLSDAB" size="5" maxlength="3" value="<%= lnServCharge.getE01DLSDAB().trim()%>">
            </td>
            <td nowrap > 
              <div align="right">Tasa Base o Punto :</div>
            </td>
            <td colspan="5" > 
              <input type="text" name="E01DLSRFT" size="3" maxlength="1" value="<%= lnServCharge.getE01DLSRFT().trim()%>">
            </td>
            <td > 
              <div align="center">
                <input type="text" name="E01DLSRFT" size="3" maxlength="1" value="<%= lnServCharge.getE01DLSRFT().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tasa M&iacute;nima Permitida :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLSMIN" size="11" maxlength="9" value="<%= lnServCharge.getE01DLSMIN().trim()%>">
            </td>
            <td nowrap > 
              <div align="right">Tasa M&aacute;xima Permitida :</div>
            </td>
            <td colspan="5" > 
              <input type="text" name="E01DLSMAX" size="11" maxlength="9" value="<%= lnServCharge.getE01DLSMAX().trim()%>">
            </td>
            <td > 
              <div align="center">
                <input type="text" name="E01DLSMMT" size="3" maxlength="1" value="<%= lnServCharge.getE01DLSMMT().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Intereses :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLSICT" size="3" maxlength="1" value="<%= lnServCharge.getE01DLSICT().trim()%>">
            </td>
            <td nowrap > 
              <div align="right">D&iacute;as Retiro D&iacute;as Custodia :</div>
            </td>
            <td colspan="6" > 
              <input type="text" name="E01DLSCUP" size="4" maxlength="2" value="<%= lnServCharge.getE01DLSCUP().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap >
              <div align="right">Control Adelanto Capital</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLSFL1" size="3" maxlength="1" value="<%= lnServCharge.getE01DLSFL1().trim()%>">
            </td>
            <td nowrap>
              <div align="right">Orden Prioridad en Pagos :</div>
            </td>
            <td nowrap> 
              <div align="center">
                <input type="text" name="E01DLSPP1" size="1" maxlength="1" value="<%= lnServCharge.getE01DLSPP1().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center">
                <input type="text" name="E01DLSPP2" size="1" maxlength="1" value="<%= lnServCharge.getE01DLSPP2().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center">
                <input type="text" name="E01DLSPP3" size="1" maxlength="1" value="<%= lnServCharge.getE01DLSPP3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center">
                <input type="text" name="E01DLSPP4" size="1" maxlength="1" value="<%= lnServCharge.getE01DLSPP4().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center">
                <input type="text" name="E01DLSPP5" size="1" maxlength="1" value="<%= lnServCharge.getE01DLSPP5().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="left">
                <input type="text" name="E01DLSPP6" size="1" maxlength="1" value="<%= lnServCharge.getE01DLSPP6().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Permite Pagos Parciales :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLSPPR" size="3" maxlength="1" value="<%= lnServCharge.getE01DLSPPR().trim()%>">
            </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <div align="center"><b>P</b> </div>
            </td>
            <td nowrap > 
              <div align="center"><b>I</b> </div>
            </td>
            <td nowrap > 
              <div align="center"><b>M</b> </div>
            </td>
            <td nowrap > 
              <div align="center"><b>T</b> </div>
            </td>
            <td nowrap > 
              <div align="center"><b>C</b> </div>
            </td>
            <td nowrap > 
              <div align="left"><b>D</b> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Forma Cambio Contable :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLSTCG" size="3" maxlength="1" value="<%= lnServCharge.getE01DLSTCG().trim()%>">
            </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <div align="center"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">D&iacute;as Cambio Contable :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLSPL1" size="5" maxlength="3" value="<%= lnServCharge.getE01DLSPL1().trim()%>">
              <input type="text" name="E01DLSPL2" size="5" maxlength="3" value="<%= lnServCharge.getE01DLSPL2().trim()%>">
              <input type="text" name="E01DLSPL3" size="5" maxlength="3" value="<%= lnServCharge.getE01DLSPL3().trim()%>">
              <input type="text" name="E01DLSPL4" size="5" maxlength="3" value="<%= lnServCharge.getE01DLSPL4().trim()%>">
              <input type="text" name="E01DLSPL5" size="5" maxlength="3" value="<%= lnServCharge.getE01DLSPL5().trim()%>">
            </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td colspan="6" height="32" >&nbsp; </td>
          </tr>
        </table>

        
      </td>
    </tr>
  </table>
  <h4>Comisiones o Impuestos</h4>
  <table class="tableinfo">
    <tr > 
      <td height="197" > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="12%" > 
              <div align="center"><b>Descripci&oacute;n</b></div>
            </td>
            <td width="7%" > 
              <div align="center"><b>Fac</b></div>
            </td>
            <td width="16%" > 
              <div align="center"><b>Monto</b></div>
            </td>
            <td width="12%" > 
              <div align="center"><b>Fre</b></div>
            </td>
            <td width="12%" > 
              <div align="center"><b>M&iacute;nimo</b></div>
            </td>
            <td width="12%" > 
              <div align="center"><b>M&aacute;ximo</b></div>
            </td>
            <td width="12%" > 
              <div align="center"><b>Moneda</b></div>
            </td>
            <td width="17%" > 
              <div align="center"><b>Cuenta Contable</b></div>
            </td>
            <td colspan="6" width="17%" > 
              <div align="center"><b>IVA</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="12%" > 
              <div align="left"><%= lnServCharge.getE01DLSDE1().trim()%></div>
            </td>
            <td width="7%" > 
              <div align="center"><%= lnServCharge.getE01DLSFA1().trim()%></div>
            </td>
            <td width="16%" > 
              <div align="right"><%= lnServCharge.getE01DLSAM1().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSAP1().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMN1().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMX1().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSGB1().trim()%></div>
            </td>
            <td width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSGL1().trim()%></div>
            </td>
            <td colspan="6" width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSWH1().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="12%" > 
              <div align="left"><%= lnServCharge.getE01DLSDE2().trim()%></div>
            </td>
            <td width="7%" > 
              <div align="center"><%= lnServCharge.getE01DLSFA2().trim()%></div>
            </td>
            <td width="16%" > 
              <div align="right"><%= lnServCharge.getE01DLSAM2().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSAP2().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMN2().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMX2().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSGB2().trim()%></div>
            </td>
            <td width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSGL2().trim()%></div>
            </td>
            <td colspan="6" width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSWH2().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="12%" height="22" > 
              <div align="left"><%= lnServCharge.getE01DLSDE3().trim()%></div>
            </td>
            <td width="7%" height="22" > 
              <div align="center"><%= lnServCharge.getE01DLSFA3().trim()%></div>
            </td>
            <td width="16%" height="22" > 
              <div align="right"><%= lnServCharge.getE01DLSAM3().trim()%></div>
            </td>
            <td width="12%" height="22" > 
              <div align="center"><%= lnServCharge.getE01DLSAP3().trim()%></div>
            </td>
            <td width="12%" height="22" > 
              <div align="right"><%= lnServCharge.getE01DLSMN3().trim()%></div>
            </td>
            <td width="12%" height="22" > 
              <div align="right"><%= lnServCharge.getE01DLSMX3().trim()%></div>
            </td>
            <td width="12%" height="22" > 
              <div align="center"><%= lnServCharge.getE01DLSGB3().trim()%></div>
            </td>
            <td width="17%" height="22" > 
              <div align="right"><%= lnServCharge.getE01DLSGL3().trim()%></div>
            </td>
            <td colspan="6" width="17%" height="22" > 
              <div align="right"><%= lnServCharge.getE01DLSWH3().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="12%" > 
              <div align="left"><%= lnServCharge.getE01DLSDE4().trim()%></div>
            </td>
            <td width="7%" > 
              <div align="center"><%= lnServCharge.getE01DLSFA4().trim()%></div>
            </td>
            <td width="16%" > 
              <div align="right"><%= lnServCharge.getE01DLSAM4().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSAP4().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMN4().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMX4().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSGB4().trim()%></div>
            </td>
            <td width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSGL4().trim()%></div>
            </td>
            <td colspan="6" width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSWH4().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="12%" > 
              <div align="left"><%= lnServCharge.getE01DLSDE5().trim()%></div>
            </td>
            <td width="7%" > 
              <div align="center"><%= lnServCharge.getE01DLSFA5().trim()%></div>
            </td>
            <td width="16%" > 
              <div align="right"><%= lnServCharge.getE01DLSAM5().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSAP5().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMN5().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMX5().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSGB5().trim()%></div>
            </td>
            <td width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSGL5().trim()%></div>
            </td>
            <td colspan="6" width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSWH5().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="12%" height="20" > 
              <div align="left"><%= lnServCharge.getE01DLSDE6().trim()%></div>
            </td>
            <td width="7%" height="20" > 
              <div align="center"><%= lnServCharge.getE01DLSFA6().trim()%></div>
            </td>
            <td width="16%" height="20" > 
              <div align="right"><%= lnServCharge.getE01DLSAM6().trim()%></div>
            </td>
            <td width="12%" height="20" > 
              <div align="center"><%= lnServCharge.getE01DLSAP6().trim()%></div>
            </td>
            <td width="12%" height="20" > 
              <div align="right"><%= lnServCharge.getE01DLSMN6().trim()%></div>
            </td>
            <td width="12%" height="20" > 
              <div align="right"><%= lnServCharge.getE01DLSMX6().trim()%></div>
            </td>
            <td width="12%" height="20" > 
              <div align="center"><%= lnServCharge.getE01DLSGB6().trim()%></div>
            </td>
            <td width="17%" height="20" > 
              <div align="right"><%= lnServCharge.getE01DLSGL6().trim()%></div>
            </td>
            <td colspan="6" width="17%" height="20" > 
              <div align="right"><%= lnServCharge.getE01DLSWH6().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="12%" height="18" > 
              <div align="left"><%= lnServCharge.getE01DLSDE7().trim()%></div>
            </td>
            <td width="7%" height="18" > 
              <div align="center"><%= lnServCharge.getE01DLSFA7().trim()%></div>
            </td>
            <td width="16%" height="18" > 
              <div align="right"><%= lnServCharge.getE01DLSAM7().trim()%></div>
            </td>
            <td width="12%" height="18" > 
              <div align="center"><%= lnServCharge.getE01DLSAP7().trim()%></div>
            </td>
            <td width="12%" height="18" > 
              <div align="right"><%= lnServCharge.getE01DLSMN7().trim()%></div>
            </td>
            <td width="12%" height="18" > 
              <div align="right"><%= lnServCharge.getE01DLSMX7().trim()%></div>
            </td>
            <td width="12%" height="18" > 
              <div align="center"><%= lnServCharge.getE01DLSGB7().trim()%></div>
            </td>
            <td width="17%" height="18" > 
              <div align="right"><%= lnServCharge.getE01DLSGL7().trim()%></div>
            </td>
            <td colspan="6" width="17%" height="18" > 
              <div align="right"><%= lnServCharge.getE01DLSWH7().trim()%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="12%" > 
              <div align="left"><%= lnServCharge.getE01DLSDE8().trim()%></div>
            </td>
            <td width="7%" > 
              <div align="center"><%= lnServCharge.getE01DLSFA8().trim()%></div>
            </td>
            <td width="16%" > 
              <div align="right"><%= lnServCharge.getE01DLSAM8().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSAP8().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMN8().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMX8().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSGB8().trim()%></div>
            </td>
            <td width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSGL8().trim()%></div>
            </td>
            <td colspan="6" width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSWH8().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="12%" > 
              <div align="left"><%= lnServCharge.getE01DLSDE9().trim()%></div>
            </td>
            <td width="7%" > 
              <div align="center"><%= lnServCharge.getE01DLSFA9().trim()%></div>
            </td>
            <td width="16%" > 
              <div align="right"><%= lnServCharge.getE01DLSAM9().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSAP9().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMN9().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="right"><%= lnServCharge.getE01DLSMX9().trim()%></div>
            </td>
            <td width="12%" > 
              <div align="center"><%= lnServCharge.getE01DLSGB9().trim()%></div>
            </td>
            <td width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSGL9().trim()%></div>
            </td>
            <td colspan="6" width="17%" > 
              <div align="right"><%= lnServCharge.getE01DLSWH9().trim()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center">&nbsp;</p>
  </form>
</body>
</html>

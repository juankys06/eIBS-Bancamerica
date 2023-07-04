<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Inquiry of Certificates</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="cdMant" class="datapro.eibs.beans.EDL016002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<% if cdMant.getE02DEAACD().equals("13")) {%>
    builtNewMenu(plpt_i_opt);
<% } else { %> 
	builtNewMenu(cdt_i_opt);
<% }
	initMenuT();
 %>
   
</SCRIPT>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
	 error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>

<h3 align="center">Datos Básicos de Contrato<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_basic.jsp,EDL0160T"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="41">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>No. Cliente :</b></div>
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
              <div align="right"><b>Contrato :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right"><b>Oficial :</b></div>
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
  <h4>Información Básica</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="26%" height="35"> 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap width="31%" height="35"> 
              <input type="text" name="E02DEAOAM" size="15" maxlength="15" value="<%= cdMant.getE02DEAOAM().trim()%>" readonly>
            </td>
            <td nowrap width="20%" height="35"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="23%" height="35"> 
              <input type="text" name="E02DEAOD1" size="2" maxlength="2" value="<%= cdMant.getE02DEAOD1().trim()%>" readonly>
              <input type="text" name="E02DEAOD2" size="2" maxlength="2" value="<%= cdMant.getE02DEAOD2().trim()%>" readonly>
              <input type="text" name="E02DEAOD3" size="2" maxlength="2" value="<%= cdMant.getE02DEAOD3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%"> 
              <div align="right">Tasa de Interés/Spread :</div>
            </td>
            <td nowrap width="31%"> 
              <input type="text" name="E02DEARTE" size="10" maxlength="9" value="<%= cdMant.getE02DEARTE().trim()%>" readonly>
            </td>
            <td nowrap width="20%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02DEAMD1" size="2" maxlength="2" value="<%= cdMant.getE02DEAMD1().trim()%>" readonly>
              <input type="text" name="E02DEAMD2" size="2" maxlength="2" value="<%= cdMant.getE02DEAMD2().trim()%>" readonly>
              <input type="text" name="E02DEAMD3" size="2" maxlength="2" value="<%= cdMant.getE02DEAMD3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="26%" height="23">
              <div align="right">Base de Cálculo :</div>
            </td>
            <td nowrap width="31%" height="23"> 
              <input type="text" name="E02DEABAS" size="3" maxlength="3" value="<%= cdMant.getE02DEABAS().trim()%>" readonly>
            </td>
            <td nowrap width="20%" height="23"> 
              <div align="right">Término :</div>
            </td>
            <td nowrap width="23%" height="23"> 
              <input type="text" name="E02DEATRM" size="4" maxlength="4" value="<%= cdMant.getE02DEATRM().trim()%>" readonly>
              <input type="text" name="E02DEATRC" size="10" 
				  value="<% if (cdMant.getE02DEATRC().equals("D")) out.print("Dia(s)");
							else if (cdMant.getE02DEATRC().equals("M")) out.print("Mes(es)");
							else if (cdMant.getE02DEATRC().equals("Y")) out.print("Año(s)");
							else out.print("");%>" 
				readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" height="23"> 
              <div align="right">Calculo de Intereses :</div>
            </td>
            <td nowrap width="31%" height="23"> 
              <input type="text" name="E02DEAIFL" size="3" 
				  value="<% if (cdMant.getE02DEAIFL().equals("N")) out.print("Si");
							else if (cdMant.getE02DEAIFL().equals("1")) out.print("No");
							else out.print("");%>" 
				readonly maxlength="3">
            </td>
            <td nowrap width="20%" height="23"> 
              <div align="right">Tasa Flotante :</div>
            </td>
            <td nowrap width="23%" height="23"> 
              <input type="text" name="E02DEAFTB" size="2" maxlength="2" value="<%= cdMant.getE02DEAFTB().trim()%>" readonly>
              <input type="text" name="E02DEAFTY" size="20" 
				  value="<% if (cdMant.getE02DEAFTY().equals("FP")) out.print("Flotante Primaria");
							else if (cdMant.getE02DEAFTY().equals("FS")) out.print("Flotante Secundaria");
							else out.print("");%>" 
				readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" height="19"> 
              <div align="right">Tipo de Intereses :</div>
            </td>
            <td nowrap width="31%" height="19"> 
              <input type="text" name="E02DEAICT" size="2" maxlength="1" value="<%= cdMant.getE02DEAICT().trim()%>" readonly>
            </td>
            <td nowrap width="20%" height="19"> 
              <div align="right">Estado del Contrato :</div>
            </td>
            <td nowrap width="23%" height="19"> 
              <input type="text" name="E02DEADLC" size="10" 
				  value="<% if (cdMant.getE02DEADLC().equals("1")) out.print("Vigente");
							else if (cdMant.getE02DEADLC().equals("2")) out.print("Suspendido");
							else if (cdMant.getE02DEADLC().equals("3")) out.print("Expirado");
							else out.print("");%>" 
				readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" height="19"> 
              <div align="right">Banco / Agencia :</div>
            </td>
            <td nowrap width="31%" height="19"> 
              <input type="text" name="E02DEABNK" size="2" maxlength="2" value="<%= cdMant.getE02DEABNK().trim()%>" readonly>
              / 
              <input type="text" name="E02DEABRN" size="3" maxlength="3" value="<%= cdMant.getE02DEABRN().trim()%>" readonly>
            </td>
            <td nowrap width="20%" height="19"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="23%" height="19">
              <input type="text" name="E02DEAGLN" size="16" maxlength="16" value="<%= cdMant.getE02DEAGLN().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" height="19"> 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap width="31%" height="19"> 
              <input type="text" name="E02DEAEXR" size="11" maxlength="11" value="<%= cdMant.getE02DEAEXR().trim()%>" readonly>
            </td>
            <td nowrap width="20%" height="19"> 
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap width="23%" height="19">
              <input type="text" name="E02DEACCN" size="8" maxlength="8" value="<%= cdMant.getE02DEACCN().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" height="19"> 
              <div align="right">Cuenta de Contrapartida :</div>
            </td>
            <td nowrap width="31%" height="19"> 
              <input type="text" name="E02DEAOFB" size="2" maxlength="2" value="<%= cdMant.getE02DEAOFB().trim()%>" readonly>
              <input type="text" name="E02DEAOCR" size="3" maxlength="3" value="<%= cdMant.getE02DEAOCR().trim()%>" readonly>
              <input type="text" name="E02DEAOCY" size="3" maxlength="3" value="<%= cdMant.getE02DEAOCY().trim()%>" readonly>
              <input type="text" name="E02DEAOFA" size="16" maxlength="16" value="<%= cdMant.getE02DEAOFA().trim()%>" readonly>
            </td>
            <td nowrap width="20%" height="19"> 
              <div align="right">Línea de Crédito :</div>
            </td>
            <td nowrap width="23%" height="19">
              <input type="text" name="E02DEACMC" size="9" maxlength="9" value="<%= cdMant.getE02DEACMC().trim()%>" readonly>
              <input type="text" name="E02DEACMN" size="4" maxlength="4" value="<%= cdMant.getE02DEACMN().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" height="19"> 
              <div align="right">Ciclo de Revision / Fecha :</div>
            </td>
            <td nowrap width="31%" height="19"> 
              <input type="text" name="E02DEARRP" size="3" maxlength="3" value="<%= cdMant.getE02DEARRP().trim()%>" readonly>
              / 
              <input type="text" name="E02DEARR1" size="2" maxlength="2" value="<%= cdMant.getE02DEARR1().trim()%>" readonly>
              <input type="text" name="E02DEARR2" size="2" maxlength="2" value="<%= cdMant.getE02DEARR2().trim()%>" readonly>
              <input type="text" name="E02DEARR3" size="2" maxlength="2" value="<%= cdMant.getE02DEARR3().trim()%>" readonly>
            </td>
            <td nowrap width="20%" height="19"> 
              <div align="right">Retenciones/Deducciones :</div>
            </td>
            <td nowrap width="23%" height="19"> 
              <input type="text" name="E02DEAWHF" size="2" maxlength="1" value="<%= cdMant.getE02DEAWHF().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%" height="19"> 
              <div align="right">No. de Dirección :</div>
            </td>
            <td nowrap width="31%" height="19"> 
              <input type="text" name="E02DEAMLA2" size="2" maxlength="1" value="<%= cdMant.getE02DEAMLA().trim()%>" readonly>
            </td>
            <td nowrap width="20%" height="19"> 
              <div align="right">Número de Referencia :</div>
            </td>
            <td nowrap width="23%" height="19"> 
              <input type="text" name="E02DEAREF" size="12" maxlength="12" value="<%= cdMant.getE02DEAREF().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%" height="19"> 
              <div align="right">Porcentaje de Garantía :</div>
            </td>
            <td nowrap width="31%" height="19"> 
              <input type="text" name="E02DEACPE2" size="7" maxlength="7" value="<%= cdMant.getE02DEACPE().trim()%>" readonly>
            </td>
            <td nowrap width="20%" height="19"> 
              <div align="right">Clase de Contrato :</div>
            </td>
            <td nowrap width="23%" height="19"> 
              <input type="text" name="E02DEACLF" size="2" maxlength="1" value="<%= cdMant.getE02DEACLF().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Instrucciones de Renovación</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="24%"> 
              <div align="right">Código de Renovación  :</div>
            </td>
            <td nowrap width="35%"> 
              <input type="text" name="E02DEAROC" size="2" maxlength="1" value="<%= cdMant.getE02DEAROC().trim()%>" readonly>
            </td>
            <td nowrap width="18%"> 
              <div align="right">Vía de Pago :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02DEAPVI" size="2" maxlength="1" value="<%= cdMant.getE02DEAPVI().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="24%"> 
              <div align="right">Cuenta de Reembolso :</div>
            </td>
            <td nowrap width="35%"> 
              <input type="text" name="E02DEAREB" size="2" maxlength="2" value="<%= cdMant.getE02DEAREB().trim()%>" readonly>
              <input type="text" name="E02DEARPR" size="3" maxlength="3" value="<%= cdMant.getE02DEARPR().trim()%>" readonly>
              <input type="text" name="E02DEARPC" size="3" maxlength="3" value="<%= cdMant.getE02DEARPC().trim()%>" readonly>
              <input type="text" name="E02DEARAC" size="16" maxlength="16" value="<%= cdMant.getE02DEARAC().trim()%>" readonly>
            </td>
            <td nowrap width="18%"> 
              <div align="right">Monto de Reembolso :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02DEAROA" size="15" maxlength="15" value="<%= cdMant.getE02DEAROA().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  </form>
</body>
</html>

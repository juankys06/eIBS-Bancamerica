<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Consulta de Cobranzas Simples</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="collBasic" class="datapro.eibs.beans.EDC010001Message"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(coll_i_opt);

</SCRIPT>

</head>

<body >

<%@ page import = "datapro.eibs.master.Util" %>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");

%>

<H3 align="center">Consulta de Cobranzas Simples<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="coll_simp_inq_basic,EDC0100"></h3>
<hr size="4">
<form method="post">
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
  <H4>Informaci&oacute;n B&aacute;sica</H4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Monto del Cheque :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text"  name="E01DCMOAM" readonly size="15" maxlength="13" value="<%= Util.formatCCY(collBasic.getE01DCMOAM().trim())%>">
            </td>
            <td nowrap width="27%"> 
              <div align="right">Fecha de Emisi&oacute;n :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DCMID1" readonly size="4" maxlength="2" value="<%= collBasic.getE01DCMID1().trim()%>">
              <input type="text" name="E01DCMID2" readonly size="4" maxlength="2" value="<%= collBasic.getE01DCMID2().trim()%>">
              <input type="text" name="E01DCMID3" readonly size="4" maxlength="2" value="<%= collBasic.getE01DCMID3().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">N&uacute;mero del Cheque :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text"  name="E01DCMCKN" readonly size="10" maxlength="9" value="<%= collBasic.getE01DCMCKN().trim()%>">
            </td>
            <td nowrap width="27%"> 
              <div align="right">Estado de la Cobranza :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DCMCST" size="30" 
				  value="<% if (collBasic.getE01DCMCST().equals("0")) out.print("Esperando Confirmación Recibida");
							else if (collBasic.getE01DCMCST().equals("1")) out.print("Pendiente de Aceptación");
							else if (collBasic.getE01DCMCST().equals("2")) out.print("Pendiente de Pago");
							else if (collBasic.getE01DCMCST().equals("3")) out.print("Aceptada");
							else if (collBasic.getE01DCMCST().equals("4")) out.print("Protestada Vigente");
							else if (collBasic.getE01DCMCST().equals("5")) out.print("Prorrogada/Extendida");
							else if (collBasic.getE01DCMCST().equals("6")) out.print("Cancelada - Libre de Pago");
							else if (collBasic.getE01DCMCST().equals("7")) out.print("Cancelada - Devuelta");
							else if (collBasic.getE01DCMCST().equals("8")) out.print("Cerrada Parcialmente Pagada");
							else if (collBasic.getE01DCMCST().equals("9")) out.print("Cerrada Completamente Pagada");
							else if (collBasic.getE01DCMCST().equals("P")) out.print("Vencida Protestada");
							else if (collBasic.getE01DCMCST().equals("V")) out.print("Vencida sin Protestar"); %>"
				readonly>
            </td>
            <td nowrap width="27%" height="19">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Avisar No Pago V&iacute;a :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMADF" size="20" 
				  value="<% if (collBasic.getE01DCMADF().equals("T")) out.print("Telex");
							else if (collBasic.getE01DCMADF().equals("S")) out.print("Swift");
							else if (collBasic.getE01DCMADF().equals("A")) out.print("Correo Aereo");
							else if (collBasic.getE01DCMADF().equals("C")) out.print("Courier");
							else out.print("Fax");%>" 
				readonly>
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Tasa de Cambio Mda. Ext. :</div>
            </td>
            <td nowrap width="27%" height="19"> 
              
            <INPUT type="text" name="E01DCMOFX" readonly size="13"
					maxlength="11" value="<%= collBasic.getE01DCMOFX().trim()%>"></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Nuestros Cargos por Cuenta de :</div>
            </td>
            <td nowrap width="18%" height="19"> 			
				<select disabled="disabled" name="E01DCMOCF" >
					<OPTION value=" " <% if ((!collBasic.getE01DCMOCF().equals("D")) && (!collBasic.getE01DCMOCF().equals("P")) && (!collBasic.getE01DCMOCF().equals("N"))) out.println("selected"); %>></OPTION>
					<OPTION value="D" <% if (collBasic.getE01DCMOCF().equals("D")) out.println("selected"); %>>Girado/Importador</OPTION>
					<OPTION value="P" <% if (collBasic.getE01DCMOCF().equals("P")) out.println("selected"); %>>Girador/Exportador</OPTION>
					<OPTION value="N" <% if (collBasic.getE01DCMOCF().equals("N")) out.println("selected"); %>>Sin Cargos</OPTION>
				</SELECT>
				
            </td>
            <td nowrap width="26%" height="19">
              <div align="right"> Avisar Pago V&iacute;a :</div>
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="left">
 				<select disabled="disabled" name="E01DCMADF">
					<OPTION value=" " <% if ((!collBasic.getE01DCMADF().equals("T")) && (!collBasic.getE01DCMADF().equals("S")) && (!collBasic.getE01DCMADF().equals("A")) && (!collBasic.getE01DCMADF().equals("C")) && (!collBasic.getE01DCMADF().equals("F"))) out.println("selected"); %>></OPTION>
					<OPTION value="T" <% if (collBasic.getE01DCMADF().equals("T")) out.println("selected"); %>>Télex</OPTION>
					<OPTION value="S" <% if (collBasic.getE01DCMADF().equals("S")) out.println("selected"); %>>Swift</OPTION>
					<OPTION value="A" <% if (collBasic.getE01DCMADF().equals("A")) out.println("selected"); %>>Correo Aéreo</OPTION>
					<OPTION value="C" <% if (collBasic.getE01DCMADF().equals("C")) out.println("selected"); %>>Mensaje</OPTION>
					<OPTION value="F" <% if (collBasic.getE01DCMADF().equals("F")) out.println("selected"); %>>Fax</OPTION>
				</SELECT></div>               
                </td>
            <td nowrap width="27%" height="19">
              
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right"></div>
            </td>
            <td nowrap width="18%" height="19">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right"> Sus Cargos por Cuenta de :</div>
            </td>
            <td nowrap width="27%" height="19">
            <select disabled="disabled" name="E01DCMOBF">
				<OPTION value=" "	<% if ((!collBasic.getE01DCMOBF().equals("D")) && (!collBasic.getE01DCMOBF().equals("P"))) out.println("selected"); %>></OPTION>
				<OPTION value="D"	<% if (collBasic.getE01DCMOBF().equals("D")) out.println("selected"); %>>Girado/Importador</OPTION>
				<OPTION value="P"	<% if (collBasic.getE01DCMOBF().equals("P")) out.println("selected"); %>>Girador/Exportador</OPTION>
			</SELECT>                        
		  </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">N&uacute;mero Tarifas de Cargos :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMTAR" readonly size="3" maxlength="2" value="<%= collBasic.getE01DCMTAR().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Tipo de Cuenta :</div>
            </td>
            <td nowrap width="27%" height="19"> 
              <input type="text" name="E01DCMSTY" readonly size="2" maxlength="1" value="<%= collBasic.getE01DCMSTY().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Banco / Sucursal :</div>
            </td>
            <td nowrap width="18%" height="19"> 
              <input type="text" name="E01DCMBNK" readonly size="3" maxlength="2" value="<%= collBasic.getE01DCMBNK().trim()%>">
              / 
              <input type="text" name="E01DCMBRN" readonly size="4" maxlength="3" value="<%= collBasic.getE01DCMBRN().trim()%>">
            </td>
            <td nowrap width="27%" height="19"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="27%" height="19"> 
              <input type="text" name="E01DCMGLN" readonly size="17" maxlength="16" value="<%= collBasic.getE01DCMGLN().trim()%>">
            </td>
          </tr>
        </table>
      </td>
      <td nowrap>&nbsp;</td>
    </tr>
  </table>
  <H4>Informaci&oacute;n Adicional</H4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="center"><b>Girador</b></div>
            </td>
            <td nowrap width="12%">&nbsp; </td>
            <td nowrap colspan="2"> 
              <div align="center"><b>Girado (Beneficiario)</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDR1" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDR1().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp; </td>
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMRB1" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRB1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDR2" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDR2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMRB2" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRB2().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDR3" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDR3().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMRB3" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRB3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDR4" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDR4().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMRB4" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMRB4().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%"> 
              <div align="left">Cliente / Cuenta No. :</div>
            </td>
            <td nowrap width="11%"> 
              <input type="text" name="E01DCMDRA" readonly size="13" maxlength="12" value="<%= collBasic.getE01DCMDRA().trim()%>">
            </td>
            <td nowrap width="12%"> 
              <div align="left"> </div>
            </td>
            <td nowrap width="36%"> 
              <div align="left">Cliente / Cuenta No. :</div>
            </td>
            <td nowrap width="15%"> 
              <input type="text" name="E01DCMRBA" readonly size="13" maxlength="12" value="<%= collBasic.getE01DCMRBA().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%">Referencia :</td>
            <td nowrap width="11%"> 
              <input type="text" name="E01DCMDRF" readonly size="17" maxlength="16" value="<%= collBasic.getE01DCMDRF().trim()%>">
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap>Referencia :</td>
            <td nowrap> 
              <input type="text" name="E01DCMRRF" readonly size="17" maxlength="16" value="<%= collBasic.getE01DCMRRF().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="center"><b>Banco Girado</b></div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center"><b>Banco Reembolsador</b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMCL1" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMCL1().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDW1" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDW1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMCL2" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMCL2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDW2" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDW2().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMCL3" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMCL3().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDW3" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDW3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMCL4" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMCL4().trim()%>">
              </div>
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap colspan="2"> 
              <div align="center"> 
                <input type="text" name="E01DCMDW4" readonly size="40" maxlength="35" value="<%= collBasic.getE01DCMDW4().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="26%">Cliente / Cuenta No. :</td>
            <td nowrap width="11%"> 
              <input type="text" name="E01DCMCLA" readonly size="13" maxlength="12" value="<%= collBasic.getE01DCMCLA().trim()%>">
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap width="36%">Cliente / Cuenta No. :</td>
            <td nowrap width="15%"> 
              <input type="text" name="E01DCMDWA" readonly size="13" maxlength="12" value="<%= collBasic.getE01DCMDWA().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="26%">Referencia :</td>
            <td nowrap width="11%"> 
              <input type="text" name="E01DCMCRF" readonly size="17" maxlength="16" value="<%= collBasic.getE01DCMCRF().trim()%>">
            </td>
            <td nowrap width="12%">&nbsp;</td>
            <td nowrap>Sub - Cuenta :</td>
            <td nowrap>
              <input type="text" name="E01DCMDWS" readonly size="7" maxlength="5" value="<%= collBasic.getE01DCMDWS().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>

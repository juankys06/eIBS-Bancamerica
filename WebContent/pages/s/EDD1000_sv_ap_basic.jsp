<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Cuentas de Ahorro</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="svBasic" class="datapro.eibs.beans.EDD000001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	builtNewMenu(sv_a_opt); 
 
</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   out.println("<SCRIPT> initMenu(); </SCRIPT>");
%> 
<H3 align="center"> Aprobacion de Cuentas de Ahorro<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sv_ap_basic.jsp, EDD1000" width="32" height="32"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="30">
  <INPUT TYPE=HIDDEN NAME="E01ACMBNK" VALUE="<%= svBasic.getE01ACMBNK().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01ACMATY" VALUE="<%= svBasic.getE01ACMATY().trim()%>">
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
                <input type="text" name="E01ACMCUN" size="9" maxlength="9" value="<%= svBasic.getE01ACMCUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= svBasic.getE01CUSNA1().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E01ACMACC" size="12" maxlength="12" value="<%= svBasic.getE01ACMACC().trim()%>">
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
                <input type="text" name="E01ACMPRO" size="4" maxlength="4" value="<%= svBasic.getE01ACMPRO().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Datos B&aacute;sicos de la Operaci&oacute;n</H4>
<table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
         <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Nombre de la Cuenta:</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMNME().equals("Y")) out.print("id=\"txtchanged\""); %>  name="E01ACMNME" size="81" maxlength="80" value="<%= svBasic.getE01ACMNME().trim()%>">
            </td>
            <td nowrap width="26%"> 
              <div align="right">C&oacute;digo Referencial:</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMRCD().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMRCD" size="25" maxlength="30" 
				  value="<% if (svBasic.getE01ACMRCD().equals("D")) out.print("Referencia Debitos");
							else if (svBasic.getE01ACMRCD().equals("C")) out.print("Referencia Creditos");
							else if (svBasic.getE01ACMRCD().equals("A")) out.print("Referencia Ambos");
						    else out.print("");%>" 
				>
            </td>
          </tr>        
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Fecha de Apertura:</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMOP1().equals("Y")) out.print("id=\"txtchanged\""); %>  name="E01ACMOP1" size="2" maxlength="2" value="<%= svBasic.getE01ACMOP1().trim()%>">
              <input type="text" readonly <% if (svBasic.getF01ACMOP2().equals("Y")) out.print("id=\"txtchanged\""); %>  name="E01ACMOP2" size="2" maxlength="2" value="<%= svBasic.getE01ACMOP2().trim()%>">
              <input type="text" readonly <% if (svBasic.getF01ACMOP3().equals("Y")) out.print("id=\"txtchanged\""); %>  name="E01ACMOP3" size="2" maxlength="2" value="<%= svBasic.getE01ACMOP3().trim()%>">
            </td>
            <td nowrap width="26%"> 
              <div align="right">Fecha de Ingreso:</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMSU1().equals("Y")) out.print("id=\"txtchanged\""); %>  name="E01ACMSU1" size="2" maxlength="2" value="<%= svBasic.getE01ACMSU1().trim()%>">
              <input type="text" readonly <% if (svBasic.getF01ACMSU2().equals("Y")) out.print("id=\"txtchanged\""); %>  name="E01ACMSU2" size="2" maxlength="2" value="<%= svBasic.getE01ACMSU2().trim()%>">
              <input type="text" readonly <% if (svBasic.getF01ACMSU3().equals("Y")) out.print("id=\"txtchanged\""); %>  name="E01ACMSU3" size="2" maxlength="2" value="<%= svBasic.getE01ACMSU3().trim()%>"> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Retenci&oacute;n de Impuestos:</div>
            </td>
            <td nowrap width="19%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMWHF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMWHF" size="2" maxlength="1" value="<%= svBasic.getE01ACMWHF().trim()%>">
            </td>
            <td nowrap width="26%"> 
              <div align="right">Firmas Autorizadas:</div>
            </td>
            <td nowrap width="26%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMPEC().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMPEC" size="20" maxlength="20" 
				  value="<% if (svBasic.getE01ACMPEC().equals("1")) out.print("Individual");
							else if (svBasic.getE01ACMPEC().equals("2")) out.print("Mancomunada");
							else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="23"> 
              <div align="right">Mensajes al Cliente:</div>
            </td>
            <td nowrap width="19%" height="23"> 
              <input type="text" readonly <% if (svBasic.getF01ACMPMF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMPMF" size="3" maxlength="2" 
				  value="<% if (svBasic.getE01ACMPMF().equals("Y")) out.print("Si");
							else if (svBasic.getE01ACMPMF().equals("N")) out.print("No");
							else out.print("");%>" 
				>
            </td>
            <td nowrap width="26%" height="23"> 
              <div align="right">Manejo de Sub-Cuentas:</div>
            </td>
            <td nowrap width="26%" height="23"> 
              <input type="text" readonly <% if (svBasic.getF01ACMPTF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMPTF" size="3" maxlength="2" 
				  value="<% if (svBasic.getE01ACMPTF().equals("Y")) out.print("Si");
							else if (svBasic.getE01ACMPTF().equals("N")) out.print("No");
							else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">N&uacute;mero de Direcci&oacute;n Postal:</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <input type="text" readonly <% if (svBasic.getF01ACMMLA().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMMLA" size="2" maxlength="1" value="<%= svBasic.getE01ACMMLA().trim()%>">
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="right">Tabla de Documentos Requeridos:</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" size="2" maxlength="1" readonly value="<%= svBasic.getE01ACMMLA().trim()%>" name="text">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%" height="19"> 
              <div align="right">Diferidos Locales:</div>
            </td>
            <td nowrap width="19%" height="19"> 
              <input type="text" readonly <% if (svBasic.getF01ACMWLF().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= svBasic.getE01ACMWLF().trim()%>" name="E01ACMWLF">
            </td>
            <td nowrap width="26%" height="19"> 
              <div align="right">Diferidos No Locales:</div>
            </td>
            <td nowrap width="26%" height="19"> 
              <input type="text" size="2" readonly <% if (svBasic.getF01ACMWNF().equals("Y")) out.print("id=\"txtchanged\""); %> maxlength="1" value="<%= svBasic.getE01ACMWNF().trim()%>" name="E01ACMWNF">
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="29%" height="19">
              <div align="right">Banco/Sucursal :</div>
            </td>
            <td nowrap colspan=3>            
              <input type="text" name="E01ACMBNK" size="2" maxlength="2" value="<%= svBasic.getE01ACMBNK().trim()%>" readonly>
              <input type="text" name="E01ACMBRN" size="2" maxlength="3" value="<%= svBasic.getE01ACMBRN().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Informaci&oacute;n para Cargos por Servicios</H4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Cargos por Servicios :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMSCF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMSCF" size="3" maxlength="2" 
				  value="<% if (svBasic.getE01ACMSCF().equals("Y")) out.print("Si");
							else if (svBasic.getE01ACMSCF().equals("N")) out.print("No");
							else out.print("");%>" 
				>
            </td>
            <td nowrap width="28%"> 
              <div align="right">Frecuencia Cobro de Cargos :</div>
            </td>
            <td nowrap width="24%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMSHF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMSHF" size="25" maxlength="30" 
				  value="<% if (svBasic.getE01ACMSHF().equals("D")) out.print("Diario");
							else if (svBasic.getE01ACMSHF().equals("W")) out.print("Semanal");
							else if (svBasic.getE01ACMSHF().equals("B")) out.print("Quincenal");
							else if (svBasic.getE01ACMSHF().equals("M")) out.print("Mensual");
							else if (svBasic.getE01ACMSHF().equals("Q")) out.print("Trimestral");
						    else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digos Tabla de Cargos :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMACL().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMACL" size="2" maxlength="2" value="<%= svBasic.getE01ACMACL().trim()%>">
            </td>
            <td nowrap width="28%"> 
              <div align="right">Ciclo/D&iacute;a Cobro de Cargos :</div>
            </td>
            <td nowrap width="24%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMSHY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMSHY" size="3" maxlength="2" value="<%= svBasic.getE01ACMSHY().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Informaci&oacute;n Estado de Cuenta</H4>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Frecuencia de Estado de Cuenta :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMSTF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMSTF" size="25" maxlength="30" 
				  value="<% if (svBasic.getE01ACMSTF().equals("D")) out.print("Diario");
							else if (svBasic.getE01ACMSTF().equals("W")) out.print("Semanal");
							else if (svBasic.getE01ACMSTF().equals("B")) out.print("Quincenal");
							else if (svBasic.getE01ACMSTF().equals("M")) out.print("Mensual");
							else if (svBasic.getE01ACMSTF().equals("Q")) out.print("Trimestral");
						    else out.print("");%>" 
				>
            </td>
            <td nowrap width="28%"> 
              <div align="right">Retenci&oacute;n de Correos :</div>
            </td>
            <td nowrap width="24%"><font face="Arial" size="2"> 
              <input type="text" readonly <% if (svBasic.getF01ACMHSF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMHSF" size="3" maxlength="2" 
				  value="<% if (svBasic.getE01ACMHSF().equals("H")) out.print("Si");
							else if (svBasic.getE01ACMHSF().equals("")) out.print("No");
							else out.print("");%>" 
				>
              </font> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Ciclo/D&iacute;a Impresi&oacute;n Estado de Cuentas 
                :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" readonly <% if (svBasic.getF01ACMSDY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMSDY" size="3" maxlength="2" value="<%= svBasic.getE01ACMSDY().trim()%>">
            </td>
            <td nowrap width="28%"> 
              <div align="right">Estado de Cuentas Consolidado :</div>
            </td>
            <td nowrap width="24%"><font face="Arial" size="2"> </font><font face="Arial" size="2"> 
              <input type="text" readonly <% if (svBasic.getF01ACMCSF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMCSF" size="3" maxlength="2" 
				  value="<% if (svBasic.getE01ACMCSF().equals("Y")) out.print("Si");
							else if (svBasic.getE01ACMCSF().equals("N")) out.print("No");
							else out.print("");%>" 
				>
              </font></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%" height="23"> 
              <div align="right">Tipo de Estado de Cuenta :</div>
            </td>
            <td nowrap width="18%" height="23"> 
              <input type="text" readonly <% if (svBasic.getF01ACMSTY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMSTY" size="25" maxlength="25" 
				  value="<% if (svBasic.getE01ACMSTY().equals("P")) out.print("Personal");
							else if (svBasic.getE01ACMSTY().equals("C")) out.print("Corporativa");
							else if (svBasic.getE01ACMSTY().equals("N")) out.print("Ninguna");
						    else out.print("");%>" 
				>
            </td>
            <td nowrap width="28%" height="23"> 
              <div align="right">Forma de Env&iacute;o de Estado de Cuenta :</div>
            </td>
            <td nowrap width="24%" height="23"> 
              <input type="text" readonly <% if (svBasic.getF01ACMSTE().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACMSTE" size="25" maxlength="25" 
				  value="<% if (svBasic.getE01ACMSTE().equals("T")) out.print("Telex");
							else if (svBasic.getE01ACMSTE().equals("P")) out.print("Impresora");
							else if (svBasic.getE01ACMSTE().equals("F")) out.print("Facsimil");
							else if (svBasic.getE01ACMSTE().equals("E")) out.print("Correo Electronico");
							else if (svBasic.getE01ACMSTE().equals("N")) out.print("Ninguno");
						    else out.print("");%>" 
				>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Asignaci&oacute;n de Libreta de Ahorros</H4>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Libreta de Ahorros N&uacute;mero:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" size="12" maxlength="12" readonly name="E01ACMPBN" value="<%= svBasic.getE01ACMPBN().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<% if (svBasic.getH01FLGMAS().equals("N")) {%>  
  <H4>Origen de Fondos</H4>
  <TABLE id="mainTable" class="tableinfo">
  <TR><TD>
  
   <table id="headTable" >
    <tr id="trdark"> 
      <td nowrap align="center" > Concepto</td>
      <td nowrap align="center" >Banco </td>
      <td nowrap align="center" >Sucursal</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >Referencia</td>
      <td nowrap align="center" >Monto</td>
    </tr>
    </table> 
      
    <div id="dataDiv" style="height:60; overflow-y :scroll; z-index:0" >
     <table id="dataTable" >
            <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
                <input type="hidden" name="E01OFFOP<%= name %>" value="<%= svBasic.getField("E01OFFOP"+name).getString().trim()%>">
                <input type="hidden" name="E01OFFGL<%= name %>" value="<%= svBasic.getField("E01OFFGL"+name).getString().trim()%>">
                <input type="text" name="E01OFFCO<%= name %>" size="25" maxlength="25" readonly value="<%= svBasic.getField("E01OFFCO"+name).getString().trim()%>" >
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFBK<%= name %>" size="2" maxlength="2" value="<%= svBasic.getField("E01OFFBK"+name).getString().trim()%>"  readonly >
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFBR<%= name %>" size="3" maxlength="3" value="<%= svBasic.getField("E01OFFBR"+name).getString().trim()%>"
                 readonly >
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFCY<%= name %>" size="3" maxlength="3" value="<%= svBasic.getField("E01OFFCY"+name).getString().trim()%>"
                 readonly >
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFAC<%= name %>" size="12" maxlength="12"  value="<%= svBasic.getField("E01OFFAC"+name).getString().trim()%>"
                 readonly >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01OFFAM<%= name %>" size="15" maxlength="15"  value="<%= svBasic.getField("E01OFFAM"+name).getString().trim()%>"  readonly >
              </div>
            </td>
          </tr>
          <%
    		}
    		%> 
           </table>
        </div>
        
        <TABLE id="footTable" > 
          <tr id="trdark"> 
            <td nowrap align="right"><b>Equivalente Moneda de la Cuenta:</b> 
            </td>
            <td nowrap  align="center"> 
                <input type="text" name="E01OFFEQV" size="15" maxlength="15" readonly value="<%= svBasic.getE01OFFEQV().trim()%>">
            </td>
          </tr>
        </table>
        
   </TD>  
</TR>	
</TABLE>    
 <SCRIPT language="javascript">
    function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,0,true);
   }
  tableresize();
  window.onresize=tableresize;
  </SCRIPT>
 <%
    		}
    		%>       
  </form>
</body>
</html>

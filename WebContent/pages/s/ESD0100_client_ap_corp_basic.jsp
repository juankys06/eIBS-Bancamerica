<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Informacion Basica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/Address.js"> </SCRIPT>
 


<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008002Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
   <SCRIPT Language="Javascript">
       
   builtNewMenu(client_ap_corp_opt);
   
function getIF01Forms(url) {
	var newurl = url+"&CUSNUM="+document.forms[0].E02CUN.value;
	CenterNamedWindow(newurl, 'pdf', 800, 600, 2);
}
       
   </SCRIPT>

</head>

<body bgcolor="#FFFFFF">

<SCRIPT> initMenu(); </SCRIPT>

 
<h3 align="center">Informaci&oacute;n Cliente Juridica <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_ap_corp_basic, ESD0100" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="12">
  <h4>Raz&oacute;n Social</h4>
  <div align="center">
    <table class="tableinfo">
      <tr > 
        <td nowrap > 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap  width="23%"> 
                <div align="right">No Cliente 
                  :</div>
              </td>
              <td nowrap  colspan=3> 
                <input type="text" readonly <% if (client.getF02CUN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02CUN" size="10" maxlength="9" value="<%= client.getE02CUN()%>">
			  </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="44%"> 
                <div align="right">Nombre Legal 
                  :</div>
              </td>
              <td nowrap   width="56%">  
                <input type="text" readonly name="E02NA1" <% if (client.getF02NA1().equals("Y")) out.print("id=\"txtchanged\""); %> size="46" maxlength="45" value="<%= client.getE02NA1().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap  width="44%"> 
                <p align="right">Nombre Anterior 
                  :</p>
              </td>
              <td nowrap  width="56%">  
                <input type="text" readonly <% if (client.getF02CP1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02CP1" size="31" maxlength="30" value="<%= client.getE02CP1().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap  width="44%"> 
                <div align="right">Nombre Corto 
                  :</div>
              </td>
              <td nowrap  width="56%">  
                <input type="text" readonly <% if (client.getF02SHN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02SHN" size="16" maxlength="15" value="<%= client.getE02SHN().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap  width="44%"> 
                <div align="right">Identificaci&oacute;n de Central de Riesgo :</div>
              </td>
              <td nowrap  width="56%">  
                <input type="text" readonly <% if (client.getF02FN2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02FN2" size="31" maxlength="30" value="<%= client.getE02FN2().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap  width="44%"> 
                <div align="right"></div>
              </td>
              <td nowrap  width="56%">  
                <input type="text" readonly <% if (client.getF02LN1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02LN1" size="31" maxlength="30" value="<%= client.getE02LN1().trim()%>">
                </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    
	<h4>Dirección </h4>
	<table class="tableinfo">
		<% String pageName = "ESD0080_address_template_country" + client.getH02SCR() + ".jsp"; %>
			<jsp:include page="<%= pageName %>" flush="true">
			<jsp:param name="messageName" value="client" />
			<jsp:param name="basic" value="true" />
			<jsp:param name="readOnly" value="true" />
			<jsp:param name="approval" value="true" />
			<jsp:param name="index" value="02" />
		</jsp:include>	
	</table>

	    <% if( client.getH02SCR().equals("21")){%> 	
		<jsp:include page="ESD0080_ident_template_dominicana.jsp" flush="true">
			<jsp:param name="messageName" value="client" />
			<jsp:param name="readOnly" value="true" />
			<jsp:param name="title" value="Identificación" />
			<jsp:param name="suffix" value="02" />
		</jsp:include>
	
	<%} else {%> 
		<jsp:include page="ESD0080_ident_template_generic.jsp" flush="true">
			<jsp:param name="messageName" value="client" />
			<jsp:param name="readOnly" value="true" />
			<jsp:param name="title" value="Identificación" />
			<jsp:param name="suffix" value="02" />

		</jsp:include>
	<%} %>   
 
    <h4>Fechas</h4>
  </div>
  <div align="center">
    <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap width="42%"> 
                <div align="right">Fecha de Fundaci&oacute;n 
                  :</div>
              </td>
              <td nowrap width="58%">  
                <input type="text" readonly <% if (client.getF02BDD().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02BDD" size="2" maxlength="2" value="<%= client.getE02BDD().trim()%>">
                <input type="text" readonly <% if (client.getF02BDM().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02BDM" size="2" maxlength="2" value="<%= client.getE02BDM().trim()%>">
                <input type="text" readonly <% if (client.getF02BDY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02BDY" size="4" maxlength="4" value="<%= client.getE02BDY().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="42%"> 
                <div align="right">Fecha de Primer Contacto 
                  :</div>
              </td>
              <td nowrap width="58%">  
                <input type="text" readonly <% if (client.getF02IDM().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02IDM" size="2" maxlength="2" value="<%= client.getE02IDM().trim()%>">
                <input type="text" readonly <% if (client.getF02IDD().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02IDD" size="2" maxlength="2" value="<%= client.getE02IDD().trim()%>">
                <input type="text" readonly <% if (client.getF02IDY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02IDY" size="2" maxlength="2" value="<%= client.getE02IDY().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="42%"> 
                <div align="right">Fecha Inicio de Operaciones 
                  :</div>
              </td>
              <td nowrap width="58%">  
                <input type="text" readonly <% if (client.getF02IEM().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02IEM" size="2" maxlength="2" value="<%= client.getE02IEM().trim()%>">
                <input type="text" readonly <% if (client.getF02IED().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02IED" size="2" maxlength="2" value="<%= client.getE02IED().trim()%>">
                <input type="text" readonly <% if (client.getF02IEY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02IEY" size="2" maxlength="2" value="<%= client.getE02IEY().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="42%"> 
                <div align="right">Fecha de Registro 
                  :</div>
              </td>
              <td nowrap width="58%">  
                <input type="text" readonly <% if (client.getF02REM().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02REM" size="2" maxlength="2" value="<%= client.getE02REM().trim()%>">
                <input type="text" readonly <% if (client.getF02RED().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02RED" size="2" maxlength="2" value="<%= client.getE02RED().trim()%>">
                <input type="text" readonly <% if (client.getF02REY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02REY" size="2" maxlength="2" value="<%= client.getE02REY().trim()%>">
                </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Tel&eacute;fonos</h4>
  </div>
  <div align="center">
    <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap width="42%"> 
                <div align="right">Tel&eacute;fono Oficina 1 :</div>
              </td>
              <td nowrap width="58%">  
                <input type="text" readonly <% if (client.getF02HPN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02HPN" size="12" maxlength="11" value="<%= client.getE02HPN().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap  width="42%"> 
                <div align="right">Tel&eacute;fono Oficina 2 :</div>
              </td>
              <td nowrap width="58%">  
                <input type="text" readonly <% if (client.getF02PHN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02PHN" size="12" maxlength="11" value="<%= client.getE02PHN().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="42%"> 
                <div align="right">Tel&eacute;fono de Fax :</div>
              </td>
              <td nowrap width="58%">  
                <input type="text" readonly <% if (client.getF02FAX().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02FAX" size="12" maxlength="11" value="<%= client.getE02FAX().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="42%"> 
                <div align="right">Tel&eacute;fono Celular :</div>
              </td>
              <td nowrap width="58%">  
                <input type="text" readonly <% if (client.getF02PH1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02PH1" size="12" maxlength="11" value="<%= client.getE02PH1().trim()%>">
                </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>C&oacute;digo de Clasificaci&oacute;n</h4>
  </div>
  <div align="center">
    <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
            <tr id="trdark"> 
              <td nowrap width="42%"> 
                <div align="right">Oficial 
                  Primario :</div>
              </td>
              <td nowrap width="58%" >  
                <input type="text" readonly <% if (client.getF02OFC().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02OFC" size="35" maxlength="35" value="<%= client.getD02OFC().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="42%"> 
                <div align="right">Oficial Secundario 
                  :</div>
              </td>
              <td nowrap width="58%" > 
                <input type="text" readonly <% if (client.getF02OF2().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02OF2" size="35" maxlength="35" value="<%= client.getD02OF2().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="42%"> 
                <div align="right">C&oacute;digo 
                  de Industria :</div>
              </td>
              <td nowrap width="58%" >  
                <input type="text" readonly <% if (client.getF02INC().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02INC" size="35" maxlength="35" value="<%= client.getD02INC().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="42%"> 
                <div align="right">C&oacute;digo de 
                  Negocio :</div>
              </td>
              <td nowrap width="58%" > 
                <input type="text" readonly <% if (client.getF02BUC().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02BUC" size="35" maxlength="35" value="<%= client.getD02BUC().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="42%"> 
                <div align="right">Pa&iacute;s 
                  de Residencia :</div>
              </td>
              <td nowrap width="58%" >  
                <input type="text" readonly <% if (client.getF02GEC().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02GEC" size="35" maxlength="35" value="<%= client.getD02GEC().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="42%"> 
                <div align="right">Tipo de Vinculaci&oacute;n :</div>
              </td>
              <td nowrap  width="58%" > 
                <input type="text" readonly <% if (client.getF02UC1().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02UC1" size="35" maxlength="35" value="<%= client.getD02UC1().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="42%"> 
                <div align="right">C&oacute;digo de 
                  Usuario 2 :</div>
              </td>
              <td nowrap width="58%" >  
                <input type="text" readonly <% if (client.getF02UC2().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02UC2" size="35" maxlength="35" value="<%= client.getD02UC2().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="42%"> 
                <div align="right">C&oacute;digo de 
                  Usuario 3 :</div>
              </td>
              <td nowrap width="58%" > 
                <input type="text" readonly <% if (client.getF02UC3().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02UC3" size="35" maxlength="35" value="<%= client.getD02UC3().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="42%"> 
                <div align="right">C&oacute;digo de 
                  Usuario 4 :</div>
              </td>
              <td nowrap width="58%" > 
                <input type="text" readonly <% if (client.getF02UC4().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02UC4" size="35" maxlength="35" value="<%= client.getD02UC4().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="42%"> 
                <div align="right">C&oacute;digo de 
                  Usuario 5 :</div>
              </td>
              <td nowrap width="58%" >  
                <input type="text" readonly <% if (client.getF02UC5().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02UC5" size="35" maxlength="35" value="<%= client.getD02UC5().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="42%"> 
                <div align="right">C&oacute;digo de 
                  Usuario 6 :</div>
              </td>
              <td nowrap width="58%" >  
                <input type="text" readonly <% if (client.getF02UC6().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02UC6" size="35" maxlength="35" value="<%= client.getD02UC6().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="42%"> 
                <div align="right">Indicador Banco/Cooperativa :</div>
              </td>
              <td nowrap width="58%" >  
                <input type="text" readonly name="E02CRF" size="25" maxlength="25"
				  <% if (client.getF02CRF().equals("Y")) out.print("id=\"txtchanged\""); %>
                  value="<% if (client.getE02CRF().equals("1")) { out.print("Banco Corresponsal"); }
							else if (client.getE02CRF().equals("2")) { out.print("Banco Regular"); }
							else if (client.getE02CRF().equals("3")) { out.print("Cooperativa"); }
    						else if (client.getE02CRF().equals("0")) { out.print("Ninguno"); }
							else { out.print(""); } %>" >
              </td>
            </tr>
            
            
            
            <%--
            <tr id="trclear"> 
              <td nowrap width="42%"> 
                <div align="right">C&oacute;digo de 
                  Usuario 7 :</div>
              </td>
              <td nowrap width="58%" >  
                <input type="text" readonly <% if (client.getF02UC7().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02UC7" size="35" maxlength="35" value="<%= client.getD02UC7().trim()%>">
                </td>
            </tr> 
            --%>                       
          </table>
        </td>
      </tr>
    </table>
    <h4>Datos Adicionales</h4>
  </div>
  <div align="center">
    <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap  width="22%"> 
                <div align="right">No. de Registro :</div>
              </td>
              <td nowrap width="30%"> 
                <input type="text" readonly <% if (client.getF02REN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02REN" size="16" maxlength="15" value="<%= client.getE02REN().trim()%>">
              </td>
              <td nowrap width="18%"> 
                <div align="right">A&ntilde;os Establecidos :</div>
              </td>
              <td nowrap width="31%"> 
                <input type="text" readonly <% if (client.getF02NSO().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02NSO" size="3" maxlength="2" value="<%= client.getE02NSO().trim()%>">
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap  width="22%"> 
                <div align="right">No. de Acciones :</div>
              </td>
              <td nowrap width="30%"> 
                <input type="text" readonly <% if (client.getF02NST().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02NST" size="8" maxlength="7" value="<%= client.getE02NST().trim()%>">
              </td>
              <td nowrapwidth="25%" width="18%"> 
                <div align="right">No. de Accionistas ::</div>
              </td>
              <td nowrap width="31%"> 
                <input type="text" readonly <% if (client.getF02NSH().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02NSH" size="8" maxlength="7" value="<%= client.getE02NSH().trim()%>">
              </td>
            </tr>
			<TR id="trdark">
				<TD nowrapwidth="22%" width="22%" align="right">No. de Tomo :</TD>
				<TD nowrap width="30%"><INPUT
					type="text" name="E02TOMO" size="11" maxlength="10"
					value="<%=client.getE02TOMO()%>" readonly></TD>
				<TD nowrap align="right" width="18%">No. de Folio :</TD>
				<TD nowrap width="31%"><INPUT type="text" name="E02FOLIO" size="11"
					maxlength="10" value="<%=client.getE02FOLIO() %>" readonly></TD>
			</TR>
			<tr id="trclear"> 
              <td nowrapwidth="22%" width="22%"> 
                <div align="right">Capital Inicial :</div>
              </td>
              <td nowrap width="30%"> 
                <input type="text" readonly <% if (client.getF02CAI().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02CAI" size="16" maxlength="15" value="<%= client.getE02CAI().trim()%>"></td>
              <td nowrap width="18%"> 
                <div align="right">Capital Suscrito :</div>
              </td>
              <td nowrap  width="31%"> 
                <input type="text" readonly <% if (client.getF02CAS().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02CAS" size="16" maxlength="15" value="<%= client.getE02CAS().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="22%"> 
                <div align="right">Capital Pagado :</div>
              </td>
              <td nowrap width="30%"> 
                <input type="text" readonly <% if (client.getF02CAP().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02CAP" size="16" maxlength="15" value="<%= client.getE02CAP().trim()%>">
              </td>
              <td nowrap width="18%"> 
                <div align="right">Nivel de Ventas :</div>
              </td>
              <td nowrap width="31%"> 
                <input type="text" readonly <% if (client.getF02INL().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02INL" size="2" maxlength="1" value="<%= client.getE02INL().trim()%>">
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap n width="22%"> 
                <div align="right">Tama&ntilde;o del Negocio :</div>
              </td>
              <td nowrap width="30%"> 
                <input type="text" readonly <% if (client.getF02SEX().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02SEX" size="2" maxlength="1" value="<%= client.getE02SEX().trim()%>">
              </td>
              <td nowrap width="18%"> 
                <div align="right">Area de Negocio :</div>
              </td>
              <td nowrap  width="31%"> 
                <input type="text" readonly <% if (client.getF02FL3().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02FL3" size="2" maxlength="1" value="<%= client.getE02FL3().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap  width="22%"> 
                <div align="right">Fuentes de Ingresos :</div>
              </td>
              <td nowrap width="215"> 
                <input type="text" readonly <% if (client.getF02SOI().equals("Y")) out.print("id=\"txtchanged\""); %> name="D02SOI" size="35" maxlength="35" value="<%= client.getD02SOI().trim()%>">
              </td>
              <td nowrap width="129">
                <div align="right">Tabla de Previsi&oacute;n:</div>
              </td>
              <td nowrap>
                <input type="text" readonly name="E02PRV" size="4" maxlength="2" value="<%= client.getE02PRV().trim()%>">
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap n width="22%"> 
                <div align="right">Nivel de Riesgo :</div>
              </td>
              <td nowrap width="30%"> 
                <INPUT type="text" readonly name="E02RSL" size="5" maxlength="4" value="<%= client.getE02RSL().trim()%>">
              </td>
              <td nowrap width="18%"> 
              </td>
              <td nowrap  width="31%"> 
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Datos Operativos</h4>
  </div>
  <div align="center">
    <table class="tableinfo">
      <tr > 
        <td  nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap width="28%"> 
                <div align="right">Estado del Cliente  :</div>
              </td>
              <td nowrap  width="28%" bordercolor="#FFFFFF">  
                <input type="text" readonly name="E02STS" size="15" maxlength="15" <% if (client.getF02STS().equals("Y")) out.print("id=\"txtchanged\""); %>
				value="<% if (client.getE02STS().equals("1")) { out.print("Inactivo"); }
                    	  else if (client.getE02STS().equals("2")) { out.print("Activo"); }
                          else if (client.getE02STS().equals("3")) { out.print("Lista Negra"); }
                          else { out.print(""); } %>">
                </td>
              <td nowrap  width="22%"> 
                <div align="right">Clase de Cliente 
                  :</div>
              </td>
              <td nowrap  width="22%" >  
                <input type="text" readonly <% if (client.getF02CCL().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02CCL" size="2" maxlength="1" value="<%= client.getE02CCL().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap  width="28%"> 
                <div align="right">Tipo de Cliente 
                  :</div>
              </td>
              <td nowrap width="28%" bordercolor="#FFFFFF">  
              <input type="text" readonly name="E02TYP" size="10" maxlength="10" 
			  <% if (client.getF02TYP().equals("Y")) out.print("id=\"txtchanged\""); %>
    		  value="<% if (client.getE02TYP().equals("R")) { out.print("Regular"); }
						else if (client.getE02TYP().equals("M")) { out.print("Master"); }
		                else if (client.getE02TYP().equals("G")) { out.print("Grupo"); }
						else { out.print(""); } %>">
			  	
              <td nowrap  width="22%"> 
                <div align="right">No. de Grupo :</div>
              </td>
              <td nowrap  width="22%">  
                <input type="text" readonly <% if (client.getF02GRP().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02GRP" size="10" maxlength="9" value="<%= client.getE02GRP().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap  width="28%"> 
                <div align="right">Lenguaje :</div>
              </td>
              <td nowrap width="28%" bordercolor="#FFFFFF">  
                <input type="text" readonly name="E02LIF" size="10" maxlength="10" 
				<% if (client.getF02LIF().equals("Y")) out.print("id=\"txtchanged\""); %>
			    value="<% if (client.getE02LIF().equals("E")) { out.print("Ingles"); } 
                		else if (client.getE02LIF().equals("S")) { out.print("Español"); }
						else { out.print(""); } %>" >
              </td>
              <td nowrap  width="22%"> 
                <div align="right">Calificaci&oacute;n 
                  :</div>
              </td>
              <td nowrap width="22%">  
                <input type="text" readonly <% if (client.getF02FL2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02FL2" size="2" maxlength="1" value="<%= client.getE02FL2().trim()%>">
                </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="28%"> 
                <div align="right">Impuestos/Retenciones 
                  :</div>
              </td>
              <td nowrap colspan="3" bordercolor="#FFFFFF">  
                <input type="text" readonly <% if (client.getF02TAX().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02TAX" size="2" maxlength="1" value="<%= client.getE02TAX().trim()%>">
                </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Miscelaneos</h4>
  </div>
  <div align="center">
    <table class="tableinfo">
      <tr > 
        <td  nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap  width="18%"> 
                <div align="right">Referido por :</div>
              </td>
              <td nowrap  width="39%">  
                <input type="text" readonly <% if (client.getF02RBY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02RBY" size="5" maxlength="4" value="<%= client.getE02RBY().trim()%>">
                <input type="text" readonly <% if (client.getF02RBN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02RBN" size="16" maxlength="15" value="<%= client.getE02RBN().trim()%>">
                </td>
              <td nowrap  width="23%"> 
                <div align="right">Residente del Pa&iacute;s :</div>
              </td>
              <td nowrap   width="20%">  
              <input type="text" readonly name="E02FL1" size="5" maxlength="5" 
			  <% if (client.getF02FL1().equals("Y")) out.print("id=\"txtchanged\""); %>
			  value="<%if (client.getE02FL1().equals("1")) { out.print("Si"); } 
						else {out.print("No"); } %>" >
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap  width="18%" > 
                <div align="right">Nivel de Consulta 
                  :</div>
              </td>
              <td nowrap width="39%">  
                <input type="text" readonly <% if (client.getF02ILV().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02ILV" size="1" maxlength="1" value="<%= client.getE02ILV().trim()%>">
                </td>
              <td nowrap width="23%" > 
                <div align="right">No. de Tarjetas ATM :</div>
              </td>
              <td nowrap width="20%" >  
                <input type="text" readonly <% if (client.getF02ATM().equals("Y")) out.print("id=\"txtchanged\""); %> name="E02ATM" size="2" maxlength="1" value="<%= client.getE02ATM().trim()%>">
                </td>
            </tr>
           <tr id="trdark"> 
              
            <td nowrap width="18%"> 
              <div align="right">Fuente :</div>
              </td>
              
            <td nowrap  width="39%"> 
               <%
              	boolean bTesoreria = (client.getE02FL8().indexOf("T") > -1);
              	boolean bFideicomiso = (client.getE02FL8().indexOf("F") > -1);
              	boolean bFEM = (client.getE02FL8().indexOf("E") > -1);
              	boolean bTerceros = (client.getE02FL8().indexOf("R") > -1);
              %>
              <INPUT type="checkbox" name="E02FL8_TES" value="1" <% if (bTesoreria == true) out.print("checked"); %> readonly>Tesoreria
              <INPUT type="checkbox" name="E02FL8_FID" value="1" <% if (bFideicomiso == true) out.print("checked"); %> readonly>Fideicomiso
              <INPUT type="checkbox" name="E02FL8_FEM" value="1" <% if (bFEM == true) out.print("checked"); %> readonly>FEM
              <INPUT type="checkbox" name="E02FL8_TER" value="1" <% if (bTerceros == true) out.print("checked"); %> readonly>Terceros
             </td>
              
            <td nowrap width="23%"> 
              
              </td>
              
            <td nowrap  width="20%"> 
              
              </td>
            </tr>            
            
          </table>
        </td>
      </tr>
    </table> 
  </div>
</form>
</body>
</html>

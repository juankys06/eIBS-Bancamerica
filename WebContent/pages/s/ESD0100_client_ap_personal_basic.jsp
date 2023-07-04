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
 


<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

   <SCRIPT Language="Javascript">
       
   builtNewMenu(client_ap_personal_opt);
   
function getIF01Forms(url) {
	var newurl = url+"&CUSNUM="+document.forms[0].E01CUN.value;
	CenterNamedWindow(newurl, 'pdf', 800, 600, 2);
} 
   </SCRIPT>

</head>

<body bgcolor="#FFFFFF">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%>

<h3 align="center">Informaci&oacute;n Cliente Personal<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_ap_personal_basic, ESD0100"  ></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <h4> Nombre y Apellidos</h4>
    <div align="left">
      
    <table class="tableinfo">
      <tr > 
          <td nowrap > 
            <div align="center"> 
              
            <table cellspacing="0" cellpadding="2" width="100%" border="0">
              <tr  id="trdark"> 
                <td nowrap width="32%"> 
                  <div align="right">No Cliente :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" readonly <% if (client.getF01CUN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01CUN" size="15" maxlength="10" value="<%= client.getE01CUN().trim()%>">
                </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="32%"> 
                  <div align="right">Primer Nombre 
                    :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" readonly <% if (client.getF01FNA().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FNA" size="35" maxlength="30" value="<%= client.getE01FNA().trim()%>">
                </td>
              </tr>
              <tr id="trdark"> 
                <td nowrap width="32%" > 
                  <div align="right">Segundo Nombre 
                    :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" readonly <% if (client.getF01FN2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FN2" size="35" maxlength="30" value="<%= client.getE01FN2().trim()%>">
                </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="32%"> 
                  <div align="right">Primer Apellido 
                    :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" readonly <% if (client.getF01LN1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01LN1" size="35" maxlength="30" value="<%= client.getE01LN1().trim()%>">
                </td>
              </tr>
              <tr id="trdark"> 
                <td nowrap width="32%"> 
                  <div align="right">Segundo Apellido 
                    :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" readonly <% if (client.getF01LN2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01LN2" size="35" maxlength="30" value="<%= client.getE01LN2().trim()%>">
                </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="32%"> 
                  <div align="right">Nombre de Casada 
                    :</div>
                </td>
                <td nowrap colspan=4> 
                  <input type="text" readonly <% if (client.getF01ACA().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ACA" size="35" maxlength="30" value="<%= client.getE01ACA().trim()%>">
                </td>
              </tr>
              <tr id="trdark"> 
                <td nowrap width="32%" > 
                  <div align="right">Nombre Legal 
                    :</div>
                </td>
                <td nowrap colspan=4 > 
                  <input type="text" readonly <% if (client.getF01NA1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01NA1" size="45" maxlength="45" value="<%= client.getE01NA1().trim()%>">
                </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="32%" > 
                  <div align="right">Nombre Corto 
                    :</div>
                </td>
                <td nowrap colspan=4 > 
                  <input type="text" readonly <% if (client.getF01SHN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01SHN" size="25" maxlength="15" value="<%= client.getE01SHN().trim()%>">
                </td>
              </tr>
              <tr id="trdark"> 
                <td nowrap width="32%" > 
                  <div align="right">Sexo 
                    :</div>
                </td>
                <td nowrap  width="23%"> 
                  <p> 
                    <input type="text" readonly name="E01SEX" size="13" maxlength="13"
					 <% if (client.getF01SEX().equals("Y")) out.print("id=\"txtchanged\""); %>
				     value="<% if (client.getE01SEX().equals("F")) { out.print("Femenino"); }
							else if (client.getE01SEX().equals("M")) { out.print("Masculino"); }
							else { out.print(""); } %>" >
                  </p>
                </td>
                <td nowrap width="15%" > 
                  <div align="right">Estado Civil 
                    :</div>
                </td>
                <td nowrap width="30%" > 
                  <input type="text" readonly name="E01MST" size="13" maxlength="13"
				  <% if (client.getF01MST().equals("Y")) out.print("id=\"txtchanged\""); %>
                  value="<% if (client.getE01MST().equals("1")) { out.print("Soltero(a)"); }
							else if (client.getE01MST().equals("2")) { out.print("Casado(a)"); }
	                    	else if (client.getE01MST().equals("3")) { out.print("Divorciado(a)"); }
							else if (client.getE01MST().equals("4")) { out.print("Viudo(a)"); }
							else if (client.getE01MST().equals("5")) { out.print("Cohabitante"); }
							else { out.print(""); } %>" >
                </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="32%" > 
                  <div align="right">Nacionalidad 
                    :</div>
                </td>
                <td nowrap  colspan="3"> 
                  <input type="text" readonly <% if (client.getF01CCS().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01CCS" size="35" maxlength="35" value="<%= client.getD01CCS().trim()%>">
                </td>
              </tr>
              <tr id="trdark"> 
                <td nowrap width="32%" > 
                  <div align="right"> 
                    Dependientes :</div>
                </td>
                <td nowrap  colspan="3"> 
                  <input type="text" readonly <% if (client.getF01NSO().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01NSO" size="4" maxlength="2" value="<%= client.getE01NSO().trim()%>">
                </td>
              </tr>
            </table>
            
          </div>
          </td>
        </tr>
      </table>
    
    
  </div>
    
   <h4>Direcci&oacute;n</h4> 

  <table class="tableinfo" >
		<% String pageName = "ESD0080_address_template_country" + client.getH01SCR() + ".jsp"; %>
			<jsp:include page="<%= pageName %>" flush="true">
			<jsp:param name="messageName" value="client" />
			<jsp:param name="basic" value="true" />
			<jsp:param name="readOnly" value="true" />
			<jsp:param name="approval" value="true" />
			<jsp:param name="index" value="01" />
		</jsp:include>	
    </table>
    
    <% if( client.getH01SCR().equals("21")){%> 	
		<jsp:include page="ESD0080_ident_template_dominicana.jsp" flush="true">
			<jsp:param name="messageName" value="client" />
			<jsp:param name="readOnly" value="true" />
			<jsp:param name="title" value="Identificación" />
			<jsp:param name="suffix" value="01" />
		</jsp:include>
	
	<%} else {%> 
		<jsp:include page="ESD0080_ident_template_generic.jsp" flush="true">
			<jsp:param name="messageName" value="client" />
			<jsp:param name="readOnly" value="true" />
			<jsp:param name="title" value="Identificación" />
			<jsp:param name="suffix" value="01" />

		</jsp:include>
	<%} %>  

  <h4>Fechas</h4>
    
  <table class="tableinfo">
    <tr > 
        <td nowrap > 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
              <td nowrap> 
                
              <div align="right">Fecha de Nacimiento :</div>
              </td>
              <td nowrap> 
                <input type="text" readonly <% if (client.getF01BDD().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01BDD" size="3" maxlength="2" value="<%= client.getE01BDD().trim()%>">
                <input type="text" readonly <% if (client.getF01BDM().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01BDM" size="3" maxlength="2" value="<%= client.getE01BDM().trim()%>">
                <input type="text" readonly <% if (client.getF01BDY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01BDY" size="5" maxlength="4" value="<%= client.getE01BDY().trim()%>">
              </td>
              <td nowrap> 
                
              <div align="right">Fecha 1er Contacto 
                :</div>
              </td>
              <td nowrap> 
                
              <input type="text" readonly <% if (client.getF01IDM().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01IDM" size="3" maxlength="2" value="<%= client.getE01IDM().trim()%>">
                
              <input type="text" readonly <% if (client.getF01IDD().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01IDD" size="3" maxlength="2" value="<%= client.getE01IDD().trim()%>">
                
              <input type="text" readonly <% if (client.getF01IDY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01IDY" size="3" maxlength="2" value="<%= client.getE01IDY().trim()%>">
              </td>
            </tr>
          </table>
          
        </td>
      </tr>
    </table>
    <h4>Tel&eacute;fonos</h4>
    
  <table class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
            <td nowrap width="27%"> 
              <div align="right">Tel&eacute;fono Casa 
                :</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" readonly <% if (client.getF01HPN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01HPN" size="12" maxlength="11" value="<%= client.getE01HPN().trim()%>">
            </td>
            <td nowrap width="29%"> 
              <div align="right">Tel&eacute;fono Oficina 
                :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" readonly <% if (client.getF01PHN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01PHN" size="12" maxlength="11" value="<%= client.getE01PHN().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" > 
              <div align="right">Tel&eacute;fono Fax 
                :</div>
            </td>
            <td nowrap width="21%" > 
              <input type="text" readonly <% if (client.getF01FAX().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FAX" size="12" maxlength="11" value="<%= client.getE01FAX().trim()%>">
            </td>
            <td nowrap width="29%" > 
              <div align="right">Tel&eacute;fono Celular 
                :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" readonly <% if (client.getF01PH1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01PH1" size="12" maxlength="11" value="<%= client.getE01PH1().trim()%>">
            </td>
          </tr>
        </table>
        </td>
    </tr>
  </table>
    <h4>C&oacute;digos de Clasificaci&oacute;n</h4>
    
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
            <td nowrap width="35%"> 
              <div align="right">Oficial Principal 
                :</div>
            </td>
            <td nowrap width="65%"> 
              <input type="text" readonly <% if (client.getF01OFC().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01OFC" size="35" maxlength="35" value="<%= client.getD01OFC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%"> 
              <div align="right">Oficial Secundario 
                :</div>
            </td>
            <td nowrap width="65%"> 
              <input type="text" readonly <% if (client.getF01OF2().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01OF2" size="35" maxlength="35" value="<%= client.getD01OF2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="35%"> 
              <div align="right">C&oacute;digo de 
                Industria :</div>
            </td>
            <td nowrap width="65%"> 
              <input type="text" readonly <% if (client.getF01INC().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01INC" size="35" maxlength="35" value="<%= client.getD01INC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%"> 
              <div align="right">C&oacute;digo de 
                Negocio :</div>
            </td>
            <td nowrap width="65%"> 
              <input type="text" readonly <% if (client.getF01BUC().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01BUC" size="35" maxlength="35" value="<%= client.getD01BUC().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="35%"> 
              <div align="right">Pa&iacute;s de Residencia 
                :</div>
            </td>
            <td nowrap width="65%"> 
              <input type="text" readonly <% if (client.getF01GEC().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01GEC" size="35" maxlength="35" value="<%= client.getD01GEC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%"> 
              <div align="right">Tipo de Vinculaci&oacute;n:</div>
            </td>
            <td nowrap width="65%"> 
              <input type="text" readonly <% if (client.getF01UC1().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01UC1" size="35" maxlength="35" value="<%= client.getD01UC1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="35%"> 
              <div align="right">C&oacute;digo de 
                Usuario 2 :</div>
            </td>
            <td nowrap width="65%"> 
              <input type="text" readonly <% if (client.getF01UC2().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01UC2" size="35" maxlength="35" value="<%= client.getD01UC2().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  width="35%"> 
              <div align="right">C&oacute;digo de 
                Usuario 3 :</div>
            </td>
            <td nowrap  width="65%"> 
              <input type="text" readonly <% if (client.getF01UC3().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01UC3" size="35" maxlength="35" value="<%= client.getD01UC3().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  width="35%"> 
              <div align="right">C&oacute;digo de 
                Usuario 4  :</div>
            </td>
            <td nowrap  width="65%"> 
              <input type="text" readonly <% if (client.getF01UC4().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01UC4" size="35" maxlength="35" value="<%= client.getD01UC4().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  width="35%"> 
              <div align="right">C&oacute;digo de 
                Usuario 5 :</div>
            </td>
            <td nowrap  width="65%"> 
              <input type="text" readonly <% if (client.getF01UC5().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01UC5" size="35" maxlength="35" value="<%= client.getD01UC5().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap  width="35%"> 
              <div align="right">C&oacute;digo de 
                Usuario 6 :</div>
            </td>
            <td nowrap  width="65%"> 
              <input type="text" readonly <% if (client.getF01UC6().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01UC6" size="35" maxlength="35" value="<%= client.getD01UC6().trim()%>">
            </td>
          </tr>
          <%--
          <tr id="trclear"> 
            <td nowrap  width="35%"> 
              <div align="right">C&oacute;digo de 
                Usuario 7 :</div>
            </td>
            <td nowrap  width="65%"> 
              <input type="text" readonly <% if (client.getF01UC7().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01UC7" size="35" maxlength="35" value="<%= client.getD01UC7().trim()%>">
            </td>
          </tr>  
          --%>                  
        </table>
      </td>
    </tr>
  </table>
  <h4>Datos Adicionales</h4>
    
  <table class="tableinfo" >
    <tr > 
        <td nowrap >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="41%"> 
              <div align="right">Nivel de Educaci&oacute;n 
                :</div>
            </td>
            <td nowrap width="59%"> 
              <input type="text" readonly <% if (client.getF01EDL().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01EDL" size="35" maxlength="35" value="<%= client.getD01EDL().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="41%"> 
              <div align="right">Profesi&oacute;n/Ocupaci&oacute;n 
                :</div>
            </td>
            <td nowrap width="59%"> 
              <input type="text" readonly <% if (client.getF01UC9().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01UC9" size="35" maxlength="35" value="<%= client.getD01UC9().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="41%"> 
              <div align="right">Nivel de Ingreso 
                :</div>
            </td>
            <td nowrap width="59%"> 
              <input type="text" readonly <% if (client.getF01INL().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01INL" size="3" maxlength="1" value="<%= client.getE01INL().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="41%"> 
              <div align="right">Fuente de Ingreso 
                :</div>
            </td>
            <td nowrap width="59%"> 
              <input type="text" readonly <% if (client.getF01SOI().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01SOI" size="35" maxlength="35" value="<%= client.getD01SOI().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="41%"> 
              <div align="right">Nivel de Riesgo :</div>
            </td>
            <td nowrap width="59%"> 
              <input type="text" readonly <% if (client.getF01RSL().equals("Y")) out.print("id=\"txtchanged\""); %> name="D01RSL" size="35" maxlength="35" value="<%= client.getD01RSL().trim()%>">
            </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    <h4>Datos Operativos</h4>
    
  <table class="tableinfo">
    <tr > 
        <td nowrap >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
              
            <td nowrap width="23%"> 
              <div align="right">Estado del Cliente :</div>
              </td>
              
            <td nowrap width="36%"> 
            <input type="text" readonly name="E01STS" size="15" maxlength="15"
			  <% if (client.getF01STS().equals("Y")) out.print("id=\"txtchanged\""); %>
			  value="<% if (client.getE01STS().equals("1")) { out.print("Activo"); }
					  	else if (client.getE01STS().equals("2")) { out.print("Inactivo"); }
						else if (client.getE01STS().equals("3")) { out.print("Lista Negra"); }
						else { out.print(""); } %> " >
            </td>
              
            <td nowrap width="25%"> 
              <div align="right">Clase de Cliente 
                :</div>
              </td>
              
            <td nowrap width="16%"> 
              <input type="text" readonly <% if (client.getF01CCL().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01CCL" size="3" maxlength="1" value="<%= client.getE01CCL().trim()%>">
            </td>
            </tr>
            <tr id="trclear"> 
              
            <td nowrap width="23%" > 
              <div align="right">Tipo de Cliente :</div>
              </td>
              
            <td nowrap width="36%" > 
              <input type="text" readonly name="E01TYP" size="10" maxlength="10"
			  <% if (client.getF01TYP().equals("Y")) out.print("id=\"txtchanged\""); %>
			  value="<% if (client.getE01TYP().equals("R")) { out.print("Regular"); }
						else if (client.getE01TYP().equals("M")) { out.print("Master"); }	
						else if (client.getE01TYP().equals("G")) { out.print("Grupo"); }
						else { out.print("Grupo"); } %>" >
			</td>
            <td nowrap width="25%" > 
              <div align="right">No. de Grupo :</div>
              </td>
              
            <td nowrap width="16%" > 
              <input type="text" readonly <% if (client.getF01GRP().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01GRP" size="10" maxlength="9" value="<%= client.getE01GRP().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              
            <td nowrap width="23%"> 
              <div align="right">Idioma :</div>
              </td>
              
            <td nowrap width="36%"> 
              <input type="text" readonly name="E01LIF" size="10" maxlength="10"
			  <% if (client.getF01LIF().equals("Y")) out.print("id=\"txtchanged\""); %>
			  value="<% if (client.getE01LIF().equals("S")) { out.print("Espa&ntilde;ol"); }
						else if (client.getE01LIF().equals("E")) { out.print("Ingl&eacute;s"); }
						else { out.print(""); } %>" >
			</td>
              
            <td nowrap width="25%"> 
              <div align="right">Impuestos/Retenciones 
                :</div>
              </td>
              
            <td width="16%"> 
              <input type="text" readonly <% if (client.getF01TAX().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01TAX" size="3" maxlength="1" value="<%= client.getE01TAX().trim()%>">
            </td>
            </tr>
            <tr id="trclear"> 
              
            <td width="23%"> 
              <div align="right">Forma Calificaci&oacute;n 
                :</div>
              </td>
              
            <td width="36%"> 
              <input type="text" readonly <% if (client.getF01FL2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01FL2" size="3" maxlength="1" value="<%= client.getE01FL2().trim()%>">
            </td>
              
            <td width="25%"> 
              <div align="right">Tabla de Previsi&oacute;n:</div>
              </td>
              
            <td width="16%">
              <input type="text" readonly name="E01PRV" size="4" maxlength="2" value="<%= client.getE01PRV().trim()%>">
            </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Miscelaneos</h4>
    
  <table class="tableinfo">
    <tr > 
        <td nowrap >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
              
            <td nowrap  width="18%"> 
              <div align="right">Referido por :</div>
              </td>
              
            <td nowrap  width="36%"> 
              <input type="text" readonly <% if (client.getF01RBY().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01RBY" size="5" maxlength="4" value="<%= client.getE01RBY().trim()%>">
                <input type="text" readonly <% if (client.getF01RBN().equals("Y")) out.print("id=\"txtchanged\""); %>  name="E01RBN" size="20" maxlength="15" value="<%= client.getE01RBN().trim()%>">
            </td>
              
            <td nowrap  width="21%"> 
              <div align="right">Tipo Rel. Familiar 
                :</div>
              </td>
              
            <td nowrap  width="25%"> 
              <input type="text" readonly name="E01FL3" size="15" maxlength="15"
			  <% if (client.getF01FL3().equals("Y")) out.print("id=\"txtchanged\""); %>
              value="<% if (client.getE01FL3().equals("1")) { out.print("Ninguno"); }
						else if (client.getE01FL3().equals("2")) { out.print("Primo(a)"); }
						else if (client.getE01FL3().equals("3")) { out.print("Padre"); }
						else if (client.getE01FL3().equals("4")) { out.print("Madre"); }
               			else if (client.getE01FL3().equals("5")) { out.print("Hermano(a)"); }
						else if (client.getE01FL3().equals("6")) { out.print("Esposo(a)"); }
               			else if (client.getE01FL3().equals("7")) { out.print("Abuelo(a)"); }
						else { out.print(""); } %>" >
            </td>
            </tr>
            <tr id="trclear"> 
              
            <td nowrap width="18%"> 
              <div align="right">Rel. con el Banco :</div>
              </td>
              
            <td nowrap width="36%"> 
              <input type="text" readonly <% if (client.getF01STF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01STF" size="3" maxlength="1" value="<%= client.getE01STF().trim()%>">
            </td>
              
            <td nowrap width="21%"> 
              <div align="right">Residente :</div>
              </td>
              
            <td nowrap width="25%"> 
              <input type="text" readonly name="E01FL1" size="3" maxlenth="3"
			  <% if (client.getF01FL1().equals("Y")) out.print("id=\"txtchanged\""); %>
			  value="<% if (client.getE01FL1().equals("1")) { out.print("Si"); }
						else { out.print("No"); } %>" >
            </td>
            </tr>
            <tr id="trdark"> 
              
            <td nowrap width="18%"> 
              <div align="right">Tarjeta ATM :</div>
              </td>
              
            <td nowrap width="36%"> 
              <input type="text" readonly <% if (client.getF01ATM().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ATM" size="2" maxlength="1" value="<%= client.getE01ATM().trim()%>">
            </td>
              
            <td nowrap width="21%"> 
              <div align="right">Nivel de Consulta 
                :</div>
              </td>
              
            <td nowrap width="25%"> 
              <input type="text" readonly <% if (client.getF01ILV().equals("Y")) out.print("id=\"txtchanged\""); %> name="E01ILV" size="4" maxlength="1" value="<%= client.getE01ILV().trim()%>">
              </td>
            </tr>
            <tr id="trclear"> 
              
            <td nowrap width="20%"> 
              <div align="right">Fuente :</div>
              </td>
              
            <td nowrap  width="42%"> 
               <%
              	boolean bTesoreria = (client.getE01FL8().indexOf("T") > -1);
              	boolean bFideicomiso = (client.getE01FL8().indexOf("F") > -1);
              	boolean bFEM = (client.getE01FL8().indexOf("E") > -1);
              	boolean bTerceros = (client.getE01FL8().indexOf("R") > -1);
              %>
              <INPUT type="checkbox" name="E01FL8_TES" value="1" <% if (bTesoreria == true) out.print("checked"); %> readonly>Tesoreria
              <INPUT type="checkbox" name="E01FL8_FID" value="1" <% if (bFideicomiso == true) out.print("checked"); %> readonly>Fideicomiso
              <INPUT type="checkbox" name="E01FL8_FEM" value="1" <% if (bFEM == true) out.print("checked"); %> readonly>FEM
              <INPUT type="checkbox" name="E01FL8_TER" value="1" <% if (bTerceros == true) out.print("checked"); %> readonly>Terceros
             </td>
              
            <td nowrap width="18%"> 
              
              </td>
              
            <td nowrap  width="20%"> 
              
              </td>
            </tr>            
            
          </table>
        </td>
      </tr>
    </table>
    </form>
</body>
</html>


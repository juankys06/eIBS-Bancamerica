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
<SCRIPT Language="Javascript">

//  Process according with user selection
 function goAction(op) {
	
   	switch (op){
	// Validate & Write 
  	case 1:  {
    	document.forms[0].APPROVAL.value = 'N';
       	break;
        }
	// Validate and Approve
	case 2:  {
 		document.forms[0].APPROVAL.value = 'Y';
       	break;
		}
	}
	document.forms[0].submit();
 }

function protectFields(updtyp) {
		document.getElementById("E01SHN").disabled=true;  
		document.getElementById("E01NA1").disabled=true; 
 
} 

function getClientPicture() {
	var pg = "<%=request.getContextPath()%>/pages/s/ESD0080_client_personal_picture.jsp";
	CenterWindow(pg,360,400,2);
}

function getIF01Forms(url) {
	var newurl = url+"&CUSNUM="+document.forms[0].E01CUN.value;
	CenterNamedWindow(newurl, 'pdf', 800, 600, 2);
}
</SCRIPT>

 
<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<%
 if ( !userPO.getPurpose().equals("NEW") ) {
%>

   <SCRIPT Language="Javascript">
     builtNewMenu(client_personal_opt);
   </SCRIPT>

<%
}
%>

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

<h3 align="center">Informaci&oacute;n Cliente Personal<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_personal_basic, ESD0080"  ></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="APPROVAL" VALUE="N">
  <input type="hidden" name="E01LGT" size="15" maxlength="10" value="<%= client.getE01LGT().trim()%>">
  <h4> Nombre y Apellidos</h4>
    <div align="left">
    <table class="tableinfo" >
      <tr > 
          <td nowrap > 
            <div align="center"> 
              
            <table cellspacing="0" cellpadding="2" width="100%" border="0">
              <tr id="trdark"> 
                <td nowrap width="25%"> 
                  <div align="right">No Cliente :</div>
                </td>
                <td nowrap colspan="4"> 
                  <input type="hidden" name="E01CUN" size="15" maxlength="10" value="<%= client.getE01CUN().trim()%>">
                  <%= client.getE01CUN().trim()%> 
					<% if (!client.getE01JPG().equals("")) {%>
						<a href="javascript:getClientPicture()">
						<img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Ver Foto" align="bottom" border="0" ></a> 
					<% } %>
                  </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="25%"> 
                  <div align="right">Primer Nombre :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" name="E01FNA" size="35" maxlength="30" value="<%= client.getE01FNA().trim() %>"
                  <% if (client.getE01GEC().equals("DR") && !client.getH01WK3().equals("M")) { %>
                   readonly
                   <% } %> >
                  <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
                </td>
              </tr>
              <tr id="trdark"> 
                <td nowrap width="25%" > 
                  <div align="right">Segundo Nombre :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" name="E01FN2" size="35" maxlength="30" value="<%= client.getE01FN2().trim()%>"
                  <% if (client.getE01GEC().equals("DR") && !client.getH01WK3().equals("M")) { %>
                   readonly
                   <% } %> >
                </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="25%"> 
                  <div align="right">Primer Apellido :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" name="E01LN1" size="35" maxlength="30" value="<%= client.getE01LN1().trim()%>"
                  <% if (client.getE01GEC().equals("DR") && !client.getH01WK3().equals("M")) { %>
                   readonly
                   <% } %> >
                  <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
                </td>
              </tr>
              <tr id="trdark"> 
                <td nowrap width="25%"> 
                  <div align="right">Segundo Apellido :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" name="E01LN2" size="35" maxlength="30" value="<%= client.getE01LN2().trim()%>"
                  <% if (client.getE01GEC().equals("DR") && !client.getH01WK3().equals("M")) { %>
                   readonly 
                   <% } %> >
                </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="25%"> 
                  <div align="right">Nombre de Casada :</div>
                </td>
                <td nowrap colspan=4> 
                  <input type="text" name="E01ACA" size="35" maxlength="30" value="<%= client.getE01ACA().trim()%>">
                </td>
              </tr>
              <tr id="trdark"> 
                <td nowrap width="25%" > 
                  <div align="right">Nombre Legal :</div>
                </td>
                <td nowrap colspan=4 > 
                  <input type="text" name="E01NA1" size="45" maxlength="45" value="<%= client.getE01NA1().trim()%>">
                </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="25%" > 
                  <div align="right">Nombre Corto :</div>
                </td>
                <td nowrap colspan=4 > 
                  <input type="text" name="E01SHN" size="25" maxlength="15" value="<%= client.getE01SHN().trim()%>">
                  <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"> 
                </td>
              </tr>
              <tr id="trdark"> 
                <td nowrap width="25%" > 
                  <div align="right">Sexo :</div>
                </td>
                <td nowrap  width="31%"> 
                  <p> 
                    <input type="radio" name="E01SEX" value="F" <%if (client.getE01SEX().equals("F")) out.print("checked"); %>>
                    Femenino 
                    <input type="radio" name="E01SEX" value="M" <%if (!client.getE01SEX().equals("F")) out.print("checked"); %>>
                    Masculino </p>
                </td>
                <td nowrap width="21%" > 
                  <div align="right">Estado Civil :</div>
                </td>
                <td nowrap width="23%" > 
                  <select name="E01MST">
                    <option value=" " <% if (!(client.getE01MST().equals("1")||client.getE01MST().equals("2") || client.getE01MST().equals("3")||client.getE01MST().equals("4")||client.getE01MST().equals("5"))) out.print("selected"); %>> 
                    </option>
                    <% if (client.getH01SCR().equals("07")) { %>
                    <option value="1" <% if (client.getE01MST().equals("1")) out.print("selected"); %>>Soltero(a)</option>
                    <option value="2" <% if (client.getE01MST().equals("2")) out.print("selected"); %>>Casado(a)</option>                   
					<%} else { %>
                    <option value="1" <% if (client.getE01MST().equals("1")) out.print("selected"); %>>Soltero(a)</option>
                    <option value="2" <% if (client.getE01MST().equals("2")) out.print("selected"); %>>Casado(a)</option>                   
                    <option value="3" <% if (client.getE01MST().equals("3")) out.print("selected"); %>>Divorciado(a)</option>
                    <option value="4" <% if (client.getE01MST().equals("4")) out.print("selected"); %>>Viudo(a)</option>
                    <option value="5" <% if (client.getE01MST().equals("5")) out.print("selected"); %>>Otro</option>
					
					<% } %>
                  </select>
                  <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
                </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="25%" > 
                  <div align="right"> Dependientes :</div>
                </td>
                <td nowrap  width="31%"> 
                  <input type="text" name="E01NSO" size="4" maxlength="2" value="<%= client.getE01NSO().trim()%>">
                </td>
                <td nowrap width="21%" > 
                  <div align="right">Nacionalidad :</div>
                </td>
                <td nowrap width="23%" > 
                  <input type="text" name="E01CCS" size="6" maxlength="4" value="<%= client.getE01CCS().trim()%>">
                  <a href="javascript:GetCodeCNOFC('E01CCS','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
                </td>
              </tr>
            </table>
            
          </div>
          </td>
        </tr>
      </table>
   </div>
   
  <h4>Dirección </h4>
	<table class="tableinfo">
		<% String pageName = "ESD0080_address_template_country" + client.getH01SCR() + ".jsp"; %>
			<jsp:include page="<%= pageName %>" flush="true">
			<jsp:param name="messageName" value="client" />
			<jsp:param name="basic" value="true" />
			<jsp:param name="readOnly" value="false" />
			<jsp:param name="index" value="01" />
		</jsp:include> 
	</table>
	
	<% if( client.getH01SCR().equals("21")){%> 	
		<jsp:include page="ESD0080_ident_template_dominicana.jsp" flush="true">
			<jsp:param name="messageName" value="client" />
			<jsp:param name="readOnly" value="false" />
			<jsp:param name="title" value="Identificación" />
			<jsp:param name="suffix" value="01" />
			<jsp:param name="lastDig" value="2" />
		</jsp:include>
	<%} else {%> 
		<jsp:include page="ESD0080_ident_template_generic.jsp" flush="true">
			<jsp:param name="messageName" value="client" />
			<jsp:param name="readOnly" value="false" />
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
                <input type="text" name="E01BDD" size="3" maxlength="2" value="<%= client.getE01BDD().trim()%>">
                <input type="text" name="E01BDM" size="3" maxlength="2" value="<%= client.getE01BDM().trim()%>">
                <input type="text" name="E01BDY" size="5" maxlength="4" value="<%= client.getE01BDY().trim()%>">
                <a href="javascript:DOBPicker(document.forms[0].E01BDM,document.forms[0].E01BDD,document.forms[0].E01BDY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="middle" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
                (DD-MM-AAAA)
              </td>
              <td nowrap> 
                
              <div align="right">Fecha 1er Contacto 
                :</div>
              </td>
              <td nowrap> 
	              <input type="text" name="E01IDM" size="3" maxlength="2" value="<%= client.getE01IDM().trim()%>" readonly>
	              <input type="text" name="E01IDD" size="3" maxlength="2" value="<%= client.getE01IDD().trim()%>" readonly>
	              <input type="text" name="E01IDY" size="3" maxlength="2" value="<%= client.getE01IDY().trim()%>" readonly>
              </td>
            </tr>
          </table>
          
        </td>
      </tr>
    </table>
    <h4>Tel&eacute;fonos</h4>
    
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
            <td nowrap width="27%"> 
              <div align="right">Tel&eacute;fono Casa 
                :</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" name="E01HPN" size="12" maxlength="11" value="<%= client.getE01HPN().trim()%>">
               <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
             </td>
            <td nowrap width="29%"> 
              <div align="right">Tel&eacute;fono Oficina 
                :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01PHN" size="12" maxlength="11" value="<%= client.getE01PHN().trim()%>">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" > 
              <div align="right">Tel&eacute;fono Fax 
                :</div>
            </td>
            <td nowrap width="21%" > 
              <input type="text" name="E01FAX" size="12" maxlength="11" value="<%= client.getE01FAX().trim()%>">
            </td>
            <td nowrap width="29%" > 
              <div align="right">Tel&eacute;fono Celular 
                :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01PH1" size="12" maxlength="11" value="<%= client.getE01PH1().trim()%>">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
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
        <table cellspacing="0" cellpadding="2" width="100%" border="0"  align="center">
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Oficial Principal 
                :</div>
            </td>
            <td nowrap width="17%"> 
              <input type="text" name="E01OFC" size="5" maxlength="4" value="<%= client.getE01OFC().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01OFC','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
            <td nowrap width="33%"> 
              <div align="right">Oficial Secundario 
                :</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" name="E01OF2" size="5" maxlength="4" value="<%= client.getE01OF2().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01OF2','10')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">C&oacute;digo de Industria :</div>
            </td>
            <td nowrap width="17%"> 
              <input type="text" name="E01INC" size="5" maxlength="4" value="<%= client.getE01INC().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01INC','09')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
            </td>
            <td nowrap width="33%"> 
              <div align="right">C&oacute;digo de Negocio :</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" name="E01BUC" size="5" maxlength="4" value="<%= client.getE01BUC().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01BUC','12')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            <IMG src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="bottom" border="0"></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="29%"> 
              <div align="right">Pa&iacute;s de Residencia 
                :</div>
            </td>
            <td nowrap width="17%"> 
              <input type="text" name="E01GEC" size="5" maxlength="4" value="<%= client.getE01GEC().trim()%>" readonly="readonly" >
			  <%--
              <a href="javascript:GetCodeCNOFC('E01GEC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
              --%> 
            </td>
            <td nowrap width="33%"> 
              <div align="right">Tipo de Vinculaci&oacute;n :</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" name="E01UC1" size="5" maxlength="4" value="<%= client.getE01UC1().trim()%>" >
              <a href="javascript:GetCodeCNOFC('E01UC1','2A')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            <IMG src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="bottom" border="0"></td>              
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Clasificaci&oacute;n :</div>
            </td>
            <td nowrap width="17%"> 
              <input type="text" name="E01UC2" size="5" maxlength="4" value="<%= client.getE01UC2().trim()%>" readonly>
              <a href="javascript:GetCodeCNOFC('E01UC2','2B')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
            <td nowrap width="33%"> 
              <div align="right">C&oacute;digo de Usuario 3 :</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" name="E01UC3" size="5" maxlength="4" value="<%= client.getE01UC3().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01UC3','2C')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
         
            <td nowrap  width="29%"> 
              <div align="right">C&oacute;digo de Usuario 4 :</div>
            </td>
            <td nowrap  width="17%"> 
              <input type="text" name="E01UC4" size="8" maxlength="6" value="<%= client.getE01UC4().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01UC4','2D')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a>               
            </td>
            
            <td nowrap  width="33%"> 
              <div align="right">C&oacute;digo de Usuario 5 :</div>
            </td>
            <td nowrap  width="21%"> 
              <input type="text" name="E01UC5" size="5" maxlength="4" value="<%= client.getE01UC5().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01UC5','2E')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
            </tr>
           <tr id="trclear"> 
          
            <td nowrap  width="29%"> 
              <div align="right">C&oacute;digo de Usuario 6 :</div>
            </td>
            <td nowrap  width="17%"> 
              <input type="text" name="E01UC6" size="5" maxlength="4" value="<%= client.getE01UC6().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01UC6','2F')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
            <%--
            <td nowrap  width="33%"> 
              <div align="right">C&oacute;digo de Usuario 7 :</div>
            </td>
            <td nowrap  width="21%"> 
              <input type="text" name="E01UC7" size="5" maxlength="4" value="<%= client.getE01UC7().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01UC7','2G')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
            --%>
            <td></td> <td></td>
          </tr>          
        </table>
      </td>
    </tr>
  </table>
  <h4>Datos Adicionales</h4>
    
  <table class="tableinfo">
    <tr > 
        <td nowrap >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
              
            <td nowrap width="28%"> 
              <div align="right">Nivel de Educaci&oacute;n 
                :</div>
              </td>
              
            <td nowrap width="21%"> 
              <input type="text" name="E01EDL" size="5" maxlength="4" value="<%= client.getE01EDL().trim()%>">
                <a href="javascript:GetCodeCNOFC('E01EDL','29')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
              <IMG src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="bottom"></td>
              
            <td nowrap width="30%"> 
              <div align="right">Profesi&oacute;n/Ocupaci&oacute;n 
                :</div>
              </td>
              
            <td nowrap width="21%"> 
              <input type="text" name="E01UC9" size="5" maxlength="4" value="<%= client.getE01UC9().trim()%>">
                <a href="javascript:GetCodeCNOFC('E01UC9','49')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
              <IMG src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="bottom"></td>
            </tr>
            <tr id="trclear"> 
              
            <td nowrap width="28%"> 
              <div align="right">Nivel de Ingreso 
                :</div>
              </td>
              
            <td nowrap width="21%"> 
              <input type="text" name="E01INL" size="3" maxlength="1" value="<%= client.getE01INL().trim()%>">
              </td>
              
            <td nowrap width="30%"> 
              <div align="right">Fuente de Ingreso 
                :</div>
              </td>
              
            <td nowrap width="21%"> 
              <input type="text" name="E01SOI" size="5" maxlength="4" value="<%= client.getE01SOI().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01SOI','30')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" width="16" height="20"  > 
            </td>
            </tr>
            <tr id="trdark"> 
              
            <td nowrap width="28%"> 
              <div align="right">Nivel de Riesgo :</div>
              </td>
              
            <td nowrap width="21%"> 
              <input type="text" name="E01RSL" size="5" maxlength="4" value="<%= client.getE01RSL().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01RSL','31')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
              
            <td nowrap width="30%"></td>
              
            <td nowrap width="21%">&nbsp;</td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Datos Operativos</h4>
    
  <table cellspacing="0" cellpadding="2" width="100%" border="1" bordercolor="#000000" bgcolor="#FFFFFF" >
    <tr bordercolor="#FFFFFF"> 
        <td nowrap >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
              
            <td nowrap width="23%"> 
              <div align="right">Estado del Cliente :</div>
              </td>
              
            <td nowrap width="36%"> 
              <select name="E01STS">
				<option value=" " <% if (!(client.getE01STS().equals("1")||client.getE01STS().equals("2")||client.getE01STS().equals("3"))) out.print("selected"); %>> </option>               
                <option value="1" <% if (client.getE01STS().equals("1")) out.print("selected"); %>>Activo</option>
                <option value="2" <% if (client.getE01STS().equals("2")) out.print("selected"); %>>Inactivo</option>
                <option value="3" <% if (client.getE01STS().equals("3")) out.print("selected"); %>>Lista Negra</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"> 
            </td>
              
            <td nowrap width="25%"> 
              <div align="right">Clase de Cliente 
                :</div>
              </td>
              
            <td nowrap width="16%"> 
              <input type="text" name="E01CCL" size="3" maxlength="1" value="<%= client.getE01CCL().trim()%>">
              <a href="javascript:GetCode('E01CCL','STATIC_client_help_class.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
            </tr>
            <tr id="trclear"> 
              
            <td nowrap width="23%" > 
              <div align="right">Tipo de Cliente :</div>
              </td>
              
            <td nowrap width="36%" > 
              <input type="radio" name="E01TYP" value="R" <%if (!(client.getE01TYP().equals("M") || client.getE01TYP().equals("G"))) out.print("checked"); %>>
              Regular 
              <input type="radio" name="E01TYP" value="M" <%if (client.getE01TYP().equals("M")) out.print("checked"); %>>
              Master 
              <input type="radio" name="E01TYP" value="G" <%if (client.getE01TYP().equals("G")) out.print("checked"); %>>
              Grupo 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
              
            <td nowrap width="25%" > 
              <div align="right">No. de Grupo :</div>
              </td>
              
            <td nowrap width="16%" > 
              <input type="text" name="E01GRP" size="10" maxlength="9" value="<%= client.getE01GRP().trim()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              
            <td nowrap width="23%"> 
              <div align="right">Idioma :</div>
              </td>
              
            <td nowrap width="36%"> 
              <input type="radio" name="E01LIF" value="S" <%if (client.getE01LIF().equals("S")) out.print("checked"); %>>
              Espa&ntilde;ol 
              <input type="radio" name="E01LIF" value="E" <%if (client.getE01LIF().equals("E")) out.print("checked"); %>>
              Ingl&eacute;s <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
            </td>
              
            <td nowrap width="25%"> 
              <div align="right">Impuestos/Retenciones 
                :</div>
              </td>
              
            <td nowrap width="16%"> 
              <input type="text" name="E01TAX" size="3" maxlength="1" value="<%="".equals(client.getE01TAX().trim())?"N":client.getE01TAX().trim()%>">
              <a href="javascript:GetCode('E01TAX','STATIC_client_help_tax_instructions.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
            </td>
            </tr>
            <tr id="trclear"> 
              
            <td nowrap width="23%"> 
              <div align="right">Forma Calificaci&oacute;n 
                :</div>
              </td>
              
            <td nowrap width="36%"> 
              <input type="text" name="E01FL2" size="3" maxlength="1" value="<%= client.getE01FL2().trim()%>">
              <a href="javascript:GetCode('E01FL2','STATIC_client_help_qualification_type.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
            </td>
              
            <td nowrap width="25%"> 
              <div align="right">Tabla de Previsi&oacute;n:</div>
              </td>
              
            <td nowrap width="16%">
              <input type="text" name="E01PRV" size="4" maxlength="2" value="<%= client.getE01PRV().trim()%>">
              <a href="javascript:GetPrevTable('E01PRV')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"  ></a>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  >               
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Miscelaneos</h4>
    
  <table class="tableinfo">
    <tr > 
        <td nowrap  >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
              
            <td nowrap   width="20%"> 
              <div align="right">Referido por :</div>
              </td>
              
            <td nowrap  width="42%"> 
              <input type="text" name="E01RBY" size="5" maxlength="4" value="<%= client.getE01RBY().trim()%>">
                <input type="text" name="E01RBN" size="20" maxlength="15" value="<%= client.getE01RBN().trim()%>">
                <a href="javascript:GetCodeDescCNOFC('E01RBY','E01RBN','28')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a> 
              </td>
              
            <td nowrap   width="18%"> 
              <div align="right">Tipo Rel. Familiar 
                :</div>
              </td>
              
            <td nowrap   width="20%"> 
              <select name="E01FL3">
                <option value=" " <% if (!(client.getE01FL3().equals("1")||client.getE01FL3().equals("2") ||client.getE01FL3().equals("3")||client.getE01FL3().equals("4") ||client.getE01FL3().equals("5")||client.getE01FL3().equals("6")||client.getE01FL3().equals("7")))  out.print("selected"); %>></option>
   	     	    <option value="1" <% if (client.getE01FL3().equals("1")) out.print("selected"); %>>Ninguna</option>
	   	   	<option value="2" <% if (client.getE01FL3().equals("2")) out.print("selected"); %>>Primo(a)</option>
                <option value="3" <% if (client.getE01FL3().equals("3")) out.print("selected"); %>>Padre</option>
                <option value="4" <% if (client.getE01FL3().equals("4")) out.print("selected"); %>>Madre</option>
                <option value="5" <% if (client.getE01FL3().equals("5")) out.print("selected"); %>>Hermano(a)</option>
                <option value="6" <% if (client.getE01FL3().equals("6")) out.print("selected"); %>>Esposo(a)</option>
                <option value="7" <% if (client.getE01FL3().equals("7")) out.print("selected"); %>>Abuelo(a)</option>
                <option value="8" <% if (client.getE01FL3().equals("8")) out.print("selected"); %>>Hijo(a)</option>
              </select>
            </td>
            </tr>
            <tr id="trclear"> 
              
            <td nowrap  width="20%"> 
              <div align="right">Relación con el Banco :</div>
              </td>
              
            <td nowrap width="42%"> 
              <input type="text" name="E01STF" size="3" maxlength="1" value="<%= client.getE01STF().trim()%>">
              <a href="javascript:GetCode('E01STF','STATIC_client_help_bank_rel.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
            </td>
              
            <td nowrap  width="18%"> 
              <div align="right">Residente :</div>
              </td>
              
            <td nowrap  width="20%"> 
              <input type="radio" name="E01FL1" value="1" <%if (client.getE01FL1().equals("1")) out.print("checked"); %>>
                S&iacute; 
                <input type="radio" name="E01FL1" value="2" <%if (!client.getE01FL1().equals("1")) out.print("checked"); %>>
                No 
                </td>
            </tr>
            <tr id="trdark"> 
              
            <td nowrap width="20%"> 
              <div align="right">Tarjeta ATM :</div>
              </td>
              
            <td nowrap  width="42%"> 
              <input type="text" name="E01ATM" size="2" maxlength="1" value="<%= client.getE01ATM().trim()%>">
            </td>
              
            <td nowrap width="18%"> 
              <div align="right">Nivel de Consulta 
                :</div>
              </td>
              
            <td nowrap  width="20%"> 
              <input type="text" name="E01ILV" size="4" maxlength="1" value="<%= client.getE01ILV().trim()%>">
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
              <INPUT type="checkbox" name="E01FL8_TES" value="1" <% if (bTesoreria == true) out.print("checked"); %> >Tesoreria
              <INPUT type="checkbox" name="E01FL8_FID" value="1" <% if (bFideicomiso == true) out.print("checked"); %> >Fideicomiso
              <INPUT type="checkbox" name="E01FL8_FEM" value="1" <% if (bFEM == true) out.print("checked"); %> >FEM
              <INPUT type="checkbox" name="E01FL8_TER" value="1" <% if (bTerceros == true) out.print("checked"); %> >Terceros
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
 
	<br>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    	<tr bgcolor="#FFFFFF"> 
      		<td width="33%"> 
        		<div align="center"><input type="checkbox" name="H01WK2" value="1" >Aceptar con Avisos</div>
      		</td>
    	</tr>
  	</table>

<table width="100%">		
  	<tr>
		<% if (client.getE01SFR().equals("Y")) {%>
  		<td width="50%">
		<%} else { %>
		<td width="100%">
		<% } %>

  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Submit" value="Enviar" onClick="javascript:goAction(1);">
     	  </div>	
  		</td>
		<% if (client.getE01SFR().equals("Y")) {%>
		<td width="50%">
  		  		<div align="center">
				<input id="EIBSBTN" type="button" name="Submit2" value="Crear" onClick="javascript:goAction(2);">
     	  	 	</div>	
  		</td>
		<%} %>
  	</tr>	
</table>	
	<SCRIPT language="JavaScript">
		protectFields('<%= userPO.getPurpose().trim()%>');	
		
	<% if (!client.getE01JPG().equals("")) {%>
		getClientPicture();
	<% } %>
	</SCRIPT>

</form>
</body>
</html>


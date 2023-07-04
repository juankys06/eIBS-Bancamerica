<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Cliente Juridico</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

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
		document.getElementById("E02SHN").disabled=true; 
} 

function getIF01Forms(url) {
	var newurl = url+"&CUSNUM="+document.forms[0].E02CUN.value;
	CenterNamedWindow(newurl, 'pdf', 800, 600, 2);
}
</SCRIPT>


<%
 if ( !userPO.getPurpose().equals("NEW") ) {
%>

   <SCRIPT Language="Javascript">
       	builtNewMenu(client_corp_opt);
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
 
 <h3 align="center">Informaci&oacute;n Cliente Persona Jur&iacute;dica  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_corp_basic, ESD0080" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="12">
  <INPUT TYPE=HIDDEN NAME="APPROVAL" VALUE="N">
  <input type="hidden" name="E02LGT" size="10" maxlength="9" value="<%= client.getE02LGT()%>">
  <h4>Raz&oacute;n Social</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="39%"> 
              <div align="right">No Cliente :</div>
            </td>
            <td nowrap colspan="2" > 
              <input type="hidden" name="E02CUN" size="10" maxlength="9" value="<%= client.getE02CUN()%>">
              <%= client.getE02CUN()%> </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="39%"> 
              <div align="right">Nombre Legal :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02NA1" size="46" maxlength="45" value="<%= client.getE02NA1().trim()%>" <%= client.getH02WK3().trim().equals("Y") ? "readonly" : "" %>>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom"> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="39%"> 
              <p align="right">Nombre Anterior :</p>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02CP1" size="31" maxlength="30" value="<%= client.getE02CP1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="39%"> 
              <div align="right">Nombre Corto :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02SHN" size="16" maxlength="15" value="<%= client.getE02SHN().trim()%>">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom"> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="39%"> 
              <div align="right">Identificaci&oacute;n de Central de Riesgo :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02FN2" size="31" maxlength="30" value="<%= client.getE02FN2().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="39%"> 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E02LN1" size="31" maxlength="30" value="<%= client.getE02LN1().trim()%>">
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
			<jsp:param name="readOnly" value="false" />
			<jsp:param name="index" value="02" />
		</jsp:include>	
	</table>
	
	<% if( client.getH02SCR().equals("21")){%> 	
		<jsp:include page="ESD0080_ident_template_dominicana.jsp" flush="true">
			<jsp:param name="messageName" value="client" />
			<jsp:param name="readOnly" value="false" />
			<jsp:param name="title" value="Identificación" />
			<jsp:param name="suffix" value="02" />
			<jsp:param name="lastDig" value="1" />
			<jsp:param name="vidno" value="RNC" />
			<jsp:param name="param2" value="Republica Dominicana" />
			<jsp:param name="param3" value="1" />
		</jsp:include>
	
	<%} else {%> 
		<jsp:include page="ESD0080_ident_template_generic.jsp" flush="true">
			<jsp:param name="messageName" value="client" />
			<jsp:param name="readOnly" value="false" />
			<jsp:param name="title" value="Identificación" />
			<jsp:param name="suffix" value="02" />

		</jsp:include>
	<%} %>   
    
    <h4>Fechas</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap width="44%"> 
                <div align="right">Fecha de Fundaci&oacute;n 
                  :</div>
              </td>
              <td nowrap width="56%">  
                <input type="text" name="E02BDD" size="2" maxlength="2" value="<%= client.getE02BDD().trim()%>">
                <input type="text" name="E02BDM" size="2" maxlength="2" value="<%= client.getE02BDM().trim()%>">
                <input type="text" name="E02BDY" size="4" maxlength="4" value="<%= client.getE02BDY().trim()%>">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom"> 
            </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap width="44%"> 
                <div align="right">Fecha de Primer Contacto 
                  :</div>
              </td>
              <td nowrap width="56%">  
                <input type="text" name="E02IDM" size="2" maxlength="2" value="<%= client.getE02IDM().trim()%>" readonly>
                <input type="text" name="E02IDD" size="2" maxlength="2" value="<%= client.getE02IDD().trim()%>" readonly>
                <input type="text" name="E02IDY" size="2" maxlength="2" value="<%= client.getE02IDY().trim()%>" readonly>
            </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="44%"> 
                <div align="right">Fecha Inicio de Operaciones 
                  :</div>
              </td>
              <td nowrap width="56%">  
                <input type="text" name="E02IEM" size="2" maxlength="2" value="<%= client.getE02IEM().trim()%>">
                <input type="text" name="E02IED" size="2" maxlength="2" value="<%= client.getE02IED().trim()%>">
                <input type="text" name="E02IEY" size="2" maxlength="2" value="<%= client.getE02IEY().trim()%>">
            </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap width="44%"> 
                <div align="right">Fecha de Registro 
                  :</div>
              </td>
              <td nowrap width="56%">  
                <input type="text" name="E02REM" size="2" maxlength="2" value="<%= client.getE02REM().trim()%>">
                <input type="text" name="E02RED" size="2" maxlength="2" value="<%= client.getE02RED().trim()%>">
                <input type="text" name="E02REY" size="2" maxlength="2" value="<%= client.getE02REY().trim()%>">
            </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Tel&eacute;fonos</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap width="44%"> 
                <div align="right">Tel&eacute;fono Oficina 1 :</div>
              </td>
              <td nowrap width="56%">  
                <input type="text" name="E02HPN" size="12" maxlength="11" value="<%= client.getE02HPN().trim()%>">
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >  	     		
              </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap width="44%"> 
                <div align="right">Tel&eacute;fono Oficina 2 :</div>
             </td>
              <td nowrap width="56%">  
                <input type="text" name="E02PHN" size="12" maxlength="11" value="<%= client.getE02PHN().trim()%>">
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >  	     		
               </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="44%"> 
                <div align="right">Tel&eacute;fono de Fax :</div>
              </td>
              <td nowrap width="56%">  
                <input type="text" name="E02FAX" size="12" maxlength="11" value="<%= client.getE02FAX().trim()%>">
                </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap width="44%"> 
                <div align="right">Tel&eacute;fono Celular :</div>
              </td>
              <td nowrap width="56%">  
                <input type="text" name="E02PH1" size="12" maxlength="11" value="<%= client.getE02PH1().trim()%>">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >  	     		
                </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>C&oacute;digo de Clasificaci&oacute;n</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
            <tr id="trdark"> 
              <td nowrap width="22%"> 
                <div align="right">Oficial Primario :</div>
              </td>
              <td nowrap width="18%">  
                <input type="text" name="E02OFC" size="5" maxlength="4" value="<%= client.getE02OFC().trim()%>">
                <a href="javascript:GetCodeCNOFC('E02OFC','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" > 
              </td>
              <td nowrap width="29%"> 
                <div align="right">Oficial Secundario 
                  :</div>
              </td>
              <td nowrap width="31%" >  
                <input type="text" name="E02OF2" size="5" maxlength="4" value="<%= client.getE02OF2().trim()%>">
                <a href="javascript:GetCodeCNOFC('E02OF2','10')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom">
              </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap width="22%"> 
                <div align="right">C&oacute;digo de Industria :</div>
              </td>
              <td nowrap width="18%">  
                <input type="text" name="E02INC" size="5" maxlength="4" value="<%= client.getE02INC().trim()%>">
                <a href="javascript:GetCodeCNOFC('E02INC','09')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"  ></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" ></td>
              <td nowrap width="29%"> 
                <div align="right">C&oacute;digo de Negocio :</div>
              </td>
              <td nowrap width="31%">  
                <input type="text" name="E02BUC" size="5" maxlength="4" value="<%= client.getE02BUC().trim()%>">
                <a href="javascript:GetCodeCNOFC('E02BUC','12')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a><IMG
					src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="bottom"></td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="22%"> 
                <div align="right">Pa&iacute;s de Residencia :</div>
              </td>
              <td nowrap width="18%">  
                <input type="text" name="E02GEC" size="5" maxlength="4" value="<%= client.getE02GEC().trim()%>" readonly="readonly">
				<%-- 
                <a href="javascript:GetCodeCNOFC('E02GEC','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"  ></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >
                --%>
              </td>
              <td nowrap width="29%"> 
                <div align="right">Tipo de Vinculaci&oacute;n :</div>
              </td>
              <td nowrap width="31%" >  
                <input type="text" name="E02UC1" size="5" maxlength="4" value="<%= client.getE02UC1().trim()%>" >

                <a href="javascript:GetCodeCNOFC('E02UC1','2A')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"  ></a>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"> 
              </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap width="22%"> 
                <div align="right">Clasificaci&oacute;n :</div>
              </td>
              <td nowrap width="18%">  
                <input type="text" name="E02UC2" size="5" maxlength="4" value="<%= client.getE02UC2().trim()%>" readonly>
<%--
                <a href="javascript:GetCodeCNOFC('E02UC2','2B')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
--%>
                </td>
              <td nowrap width="29%"> 
                <div align="right">C&oacute;digo de Usuario 3 :</div>
              </td>
              <td nowrap width="31%" >  
                <input type="text" name="E02UC3" size="5" maxlength="4" value="<%= client.getE02UC3().trim()%>">
                <a href="javascript:GetCodeCNOFC('E02UC3','2C')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a></td>
            </tr>
            <tr id="trdark"> 
            
              <td nowrap width="22%"> 
                <div align="right">C&oacute;digo Usuario 4 :</div>
              </td>
              <td nowrap width="18%" >  
                <input type="text" name="E02UC4" size="8" maxlength="6" value="<%= client.getE02UC4().trim()%>">
                <a href="javascript:GetCodeCNOFC('E02UC4','2D')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a></td>
              
              <td nowrap width="29%"> 
                <div align="right">C&oacute;digo de Usuario 5 :</div>
              </td>
              <td nowrap width="31%" >  
                <input type="text" name="E02UC5" size="5" maxlength="4" value="<%= client.getE02UC5().trim()%>">
                <a href="javascript:GetCodeCNOFC('E02UC5','2E')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
                </td>
              </tr>
              <tr id="trclear">   
           
              <td nowrap width="22%"> 
                <div align="right">C&oacute;digo de Usuario 6 :</div>
              </td>
              <td nowrap width="18%" >  
                <input type="text" name="E02UC6" size="5" maxlength="6" value="<%= client.getE02UC6().trim()%>">
                <a href="javascript:GetCodeCNOFC('E02UC6','2F')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a>
              </td>
             <%--
              <td nowrap width="29%"> 
                <div align="right">C&oacute;digo de Usuario 7 :</div>
              </td>
              <td nowrap width="31%" >  
                <input type="text" name="E02UC7" size="5" maxlength="4" value="<%= client.getE02UC7().trim()%>">
                <a href="javascript:GetCodeCNOFC('E02UC7','2G')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
                </td>
              --%>  
               <td></td><td></td> 
             </tr>            
          </table>
        </td>
      </tr>
    </table>
    <h4>Datos Adicionales</h4>

  
     <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <TBODY><TR id="trdark"> 
              <TD nowrap width="22%"> 
                <DIV align="right">No. de Registro 
                  :</DIV>
              </TD>
              <TD nowrap width="22%">  
                <INPUT type="text" name="E02REN" size="16" maxlength="15" value="<%= client.getE02REN().trim()%>">
                </TD>
              <TD nowrap width="25%"> 
                <DIV align="right">A&ntilde;os Establecidos 
                  :</DIV>
              </TD>
              <TD nowrap width="31%">  
                <INPUT type="text" name="E02NSO" size="3" maxlength="2" value="<%= client.getE02NSO().trim()%>">
                </TD>
            </TR>
            <TR id="trclear"> 
              <TD nowrap width="22%"> 
                <DIV align="right">No. de Acciones 
                  :</DIV>
              </TD>
              <TD nowrap width="22%">  
                <INPUT type="text" name="E02NST" size="8" maxlength="7" value="<%= client.getE02NST().trim()%>">
                </TD>
              <TD nowrap width="25%"> 
                <DIV align="right">No. de Accionistas 
                  :</DIV>
              </TD>
              <TD nowrap width="31%">  
                <INPUT type="text" name="E02NSH" size="8" maxlength="7" value="<%= client.getE02NSH().trim()%>">
                </TD>
            </TR>
			<TR id="trdark" >
				<TD nowrap width="22%" width="22%" align="right">No. de Tomo :</TD><TD nowrap width="30%"><INPUT
						type="text" name="E02TOMO" size="11" maxlength="10"
						value="<%=client.getE02TOMO()%>"></TD><TD nowrap align="right" width="18%">No. de Folio :</TD><TD nowrap width="31%"><INPUT
						type="text" name="E02FOLIO" size="11" maxlength="10"
						value="<%=client.getE02FOLIO() %>"></TD>
				
				
				
			</TR>
			<TR id="trclear"> 
              <TD nowrap width="22%"> 
                <DIV align="right">Capital Inicial 
                  :</DIV>
              </TD>
              <TD nowrap width="22%">  
                <INPUT type="text" name="E02CAI" size="16" maxlength="15" value="<%= client.getE02CAI().trim()%>" onKeypress="enterDecimal()">
                </TD>
              <TD nowrap width="25%"> 
                <DIV align="right">Capital Suscrito 
                  :</DIV>
              </TD>
              <TD nowrap width="31%">  
                <INPUT type="text" name="E02CAS" size="16" maxlength="15" value="<%= client.getE02CAS().trim()%>" onKeypress="enterDecimal()">
                </TD>
            </TR>
            <TR id="trdark"> 
              <TD nowrap width="22%"> 
                <DIV align="right">Capital Pagado 
                  :</DIV>
              </TD>
              <TD nowrap width="22%">  
                <INPUT type="text" name="E02CAP" size="16" maxlength="15" value="<%= client.getE02CAP().trim()%>" onKeypress="enterDecimal()">
                </TD>
              <TD nowrap width="25%"> 
                <DIV align="right">Nivel de Ventas  
                :</DIV>
              </TD>
              <TD nowrap width="31%">  
                <INPUT type="text" name="E02INL" size="2" maxlength="1" value="<%= client.getE02INL().trim()%>">
                </TD>
            </TR>
            <TR id="trclear"> 
              <TD nowrap width="22%"> 
                <DIV align="right">Tama&ntilde;o del Negocio :</DIV>
              </TD>
              <TD nowrap width="22%">  
                <INPUT type="text" name="E02SEX" size="2" maxlength="1" value="<%= client.getE02SEX().trim()%>">
                </TD>
              <TD nowrap width="25%"> 
                <DIV align="right">Area de Negocio 
                  :</DIV>
              </TD>
              <TD nowrap width="31%">  
                <INPUT type="text" name="E02FL3" size="2" maxlength="1" value="<%= client.getE02FL3().trim()%>">
                </TD>
            </TR>
            <TR id="trdark"> 
              <TD nowrap width="22%"> 
                <DIV align="right">Fuentes de Ingresos 
                  :</DIV>
              </TD>
              <TD nowrap width="22%">  
                <INPUT type="text" name="E02SOI" size="5" maxlength="4" value="<%= client.getE02SOI().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02SOI','30')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></A> 
                </TD>
              <TD nowrap width="25%"> 
                <DIV align="right">Tipo Banco Corresponsal</DIV>
              </TD>
              <TD nowrap width="31%"><SELECT name="E02CRF">
						<OPTION value=" "></OPTION>
						<OPTION value="1" <% if(client.getE02CRF().equals("1")) out.print("selected");%>>Corresponsal - No Linea</OPTION>
						<OPTION value="4" <% if(client.getE02CRF().equals("4")) out.print("selected");%>>Corresponsal - Linea</OPTION>
						<OPTION value="5" <% if(client.getE02CRF().equals("5")) out.print("selected");%>>Reembolsador</OPTION>
						<OPTION value="6" <% if(client.getE02CRF().equals("6")) out.print("selected");%>>Corresponsal y Reembolsador</OPTION>						
					</SELECT></TD>
            </TR>
            <TR id="trclear"> 
              <TD nowrap width="22%"> 
                <DIV align="right">Nivel de Riesgo :</DIV>
              </TD>
              <TD nowrap width="22%">  
                <INPUT type="text" name="E02RSL" size="5" maxlength="4" value="<%= client.getE02RSL().trim()%>">
                <A href="javascript:GetCodeCNOFC('E02RSL','31')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></A> 
              </TD>
              <TD nowrap width="25%"> 
              </TD>
              <TD nowrap width="31%">  
              </TD>
            </TR>
          </TBODY></TABLE>
        </td>
      </tr>
    </table>
    <h4>Datos Operativos</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              
            <td nowrap width="10%"> 
              <div align="right">Estado del Cliente 
                  :</div>
              </td>
              
            <td nowrap width="15%"> 
              <select name="E02STS">
                  <% boolean flag = false; %>
                  <option value="2" <%if (client.getE02STS().equals("2")) { flag = true; out.print("selected"); }%>>Inactivo</option>
                  <option value="1" <%if (client.getE02STS().equals("1")) { flag = true; out.print("selected"); }%>>Activo</option>
                  <option value="3" <%if (client.getE02STS().equals("3")) { flag = true; out.print("selected"); }%>>Lista Negra</option>
                  <option value=" " <%if ( flag == false ) out.print("selected");  %>></option>
                </select>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom"> 
                </td>
              
            <td nowrap width="8%"> 
              <div align="right">Clase de Cliente 
                  :</div>
              </td>
              
            <td nowrap width="67%"> 
              <input type="text" name="E02CCL" size="2" maxlength="1" value="<%= client.getE02CCL().trim()%>">
              <a href="javascript:GetCode('E02CCL','STATIC_client_help_class.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a> 
            </td>
            </tr>
            <tr id="teclear"> 
              
            <td nowrap width="10%"> 
              <div align="right">Tipo de Cliente 
                  :</div>
              </td>
              
            <td nowrap width="15%" bordercolor="#FFFFFF"> 
              <input type="radio" name="E02TYP" value="R" <% if (!(client.getE02TYP().equals("M") || client.getE02TYP().equals("G"))) out.print("checked"); %>>
                Regular
                <input type="radio" name="E02TYP" value="M" <% if (client.getE02TYP().equals("M")) out.print("checked"); %>>
                Master
                <input type="radio" name="E02TYP" value="G" <% if (client.getE02TYP().equals("G")) out.print("checked"); %>>
              Grupo <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom"></td>
              
            <td nowrap width="8%"> 
              <div align="right">No. de Grupo :</div>
              </td>
              
            <td nowrap width="67%"> 
              <input type="text" name="E02GRP" size="10" maxlength="9" value="<%= client.getE02GRP().trim()%>">
                </td>
            </tr>
            <tr id="trdark"> 
              
            <td nowrap width="10%"> 
              <div align="right">Lenguaje :</div>
              </td>
              
            <td nowrap width="15%" bordercolor="#FFFFFF"> 
              <input type="radio" name="E02LIF" value="E"  <%if (client.getE02LIF().equals("E")) out.print("checked"); %>>
                Ingles
                <input type="radio" name="E02LIF" value="S"  <%if (client.getE02LIF().equals("S")) out.print("checked"); %>>
                Espa&ntilde;ol 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom"></td>
              
            <td nowrap width="8%"> 
              <div align="right">Calificaci&oacute;n
                  :</div>
              </td>
              
            <td nowrap width="67%"> 
              <input type="text" name="E02FL2" size="2" maxlength="1" value="<%= client.getE02FL2().trim()%>">
                <a href="javascript:GetCode('E02FL2','STATIC_client_help_qualification_type.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a><IMG
					src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="bottom"></td>
            </tr>
            <tr id="teclear"> 
              
            <td nowrap width="10%"> 
              <div align="right">Impuestos/Retenciones 
                  :</div>
              </td>
              
            <td nowrap width="15%" bordercolor="#FFFFFF"> 
              <input type="text" name="E02TAX" size="2" maxlength="1" value="<%= client.getE02TAX().trim()%>">
              <a href="javascript:GetCode('E02TAX','STATIC_client_help_tax_instructions.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a><img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom"></td>
              
            <td nowrap width="8%"> 
              <div align="right">Tabla de Previsi&oacute;n:</div>
              </td>
              
            <td nowrap width="67%"> 
              <input type="text" name="E02PRV" size="4" maxlength="2" value="<%= client.getE02PRV().trim()%>">
              <a href="javascript:GetPrevTable('E02PRV')">
                 <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" >
                  </a> 
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
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap  width="18%"> 
                <div align="right">Referido por :</div>
              </td>
              <td nowrap  width="39%">  
                <input type="text" name="E02RBY" size="5" maxlength="4" value="<%= client.getE02RBY().trim()%>">
                <input type="text" name="E02RBN" size="16" maxlength="15" value="<%= client.getE02RBN().trim()%>">
                <a href="javascript:GetCodeDescCNOFC('E02RBY','E02RBN','28')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a> 
                </td>
              <td nowrap  width="23%"> 
                <div align="right">Residente del Pa&iacute;s :</div>
              </td>
              <td nowrap  width="20%">  
                <input type="radio" name="E02FL1" value="1" <%if (client.getE02FL1().equals("1")) out.print("checked"); %>>
                S&iacute; 
                <input type="radio" name="E02FL1" value="2" <%if (!client.getE02FL1().equals("1")) out.print("checked"); %>>
                No </td>
            </tr>
            <tr id="teclear"> 
              <td nowrap width="18%" > 
                <div align="right">Nivel de Consulta 
                  :</div>
              </td>
              <td nowrap width="39%" >  
                <input type="text" name="E02ILV" size="1" maxlength="1" value="<%= client.getE02ILV().trim()%>">
                </td>
              <td nowrap width="23%" > 
                <div align="right">No. de Tarjetas ATM :</div>
              </td>
              <td nowrap width="20%" >  
                <input type="text" name="E02ATM" size="2" maxlength="1" value="<%= client.getE02ATM().trim()%>">
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
              <INPUT type="checkbox" name="E02FL8_TES" value="1" <% if (bTesoreria == true) out.print("checked"); %> >Tesoreria
              <INPUT type="checkbox" name="E02FL8_FID" value="1" <% if (bFideicomiso == true) out.print("checked"); %> >Fideicomiso
              <INPUT type="checkbox" name="E02FL8_FEM" value="1" <% if (bFEM == true) out.print("checked"); %> >FEM
              <INPUT type="checkbox" name="E02FL8_TER" value="1" <% if (bTerceros == true) out.print("checked"); %> >Terceros
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
 
	<br>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    	<tr bgcolor="#FFFFFF"> 
      		<td width="33%"> 
        		<div align="center"><input type="checkbox" name="H02WK2" value="1" >Aceptar con Avisos</div>
      		</td>
    	</tr>
  	</table>

<table width="100%">		
  	<tr>
		<% if (client.getE02SFR().equals("Y")) {%>
  		<td width="50%">
		<%} else { %>
		<td width="100%">
		<% } %>

  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Submit" value="Enviar" onClick="javascript:goAction(1);">
     	  </div>	
  		</td>
		<% if (client.getE02SFR().equals("Y")) {%>
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
	</SCRIPT>


</form>
</body>
</html>

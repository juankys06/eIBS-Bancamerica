<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head>
<title>Acceso de Usuarios</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="user" class="datapro.eibs.beans.ESD000701Message"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>

<body bgcolor="#FFFFFF">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0") ;
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">Acceso de Usuarios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="user_access_basic, ESD0007" ></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESD0007" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  </p>
  <h4>Información de Usuario </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Usuario :</div>
            </td>
            <td nowrap> 
              <input type="hidden" name="E01BTHKEY" size="17" maxlength="15" value="<%= user.getE01BTHKEY().trim()%>">
              <%= user.getE01BTHKEY().trim()%>
             </td> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap> 
             </td>  
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01EUPNME" size="40" maxlength="45" value="<%= user.getE01EUPNME().trim()%>">
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
             </td> 
            <td nowrap> 
              <div align="right">Identificación :</div>
            </td>
            <td nowrap> 
             	<input type="text" name="E01EUPIDN" size="17" maxlength="15" value="<%= user.getE01EUPIDN().trim()%>" >
             </td>  
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">E-mail :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01EUPEML" size="40" maxlength="40" value="<%= user.getE01EUPEML().trim()%>">
              </td> 
            <td nowrap> 
              <div align="right">Teléfono :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01EUPPHN" size="13" maxlength="12" value="<%= user.getE01EUPPHN().trim()%>" onkeypress="enterInteger()">
            	Ext :
            	<input type="text" name="E01EUPPXT" size="5" maxlength="4" value="<%= user.getE01EUPPXT().trim()%>" onkeypress="enterInteger()">
             </td>  
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Contraseña eIBS :</div>
            </td>
            <td nowrap> 
              	<input type="password" name="E01EUPACP" size="12" maxlength="10" value="<%= user.getE01EUPACP().trim()%>">
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
             </td> 
            <td nowrap> 
              <div align="right">Contraseña Aprobación:</div>
            </td>
            <td nowrap> 
            	<input type="password" name="E01BTHPSW" size="6" maxlength="4" value="<%= user.getE01BTHPSW().trim()%>">
            	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
             </td>  
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Contraseña Requerida :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01EUPOPR" value="1" <%if (user.getE01EUPOPR().equals("1")) out.print("checked"); %>>Sí
              <input type="radio" name="E01EUPOPR" value="0" <%if (user.getE01EUPOPR().equals("0")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Estado Usuario :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01EUPSTS" size="2" maxlength="1" value="<%= user.getE01EUPSTS().trim()%>">
              	<a href="javascript:GetCode('E01EUPSTS','STATIC_user_status.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0" ></a> 
             </td>  
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Ocasiones  Frecuencia  Contraseña :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01EUPCHG" size="5" maxlength="4" value="<%= user.getE01EUPCHG().trim()%>" onkeypress="enterInteger()">
             </td> 
            <td nowrap> 
              <div align="right">Lenguaje :</div>
            </td>
            <td nowrap> 
               <select name="E01EUPF02">
                <option value=" " <% if (!(user.getE01EUPF02().equals("E")||user.getE01EUPF02().equals("S"))) out.print("selected"); %>> 
                </option>
                <option value="E" <% if (user.getE01EUPF02().equals("E")) out.print("selected"); %>>Inglés</option>
                <option value="S" <% if (user.getE01EUPF02().equals("S")) out.print("selected"); %>>Español</option>
              </select>
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
             </td>  
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Banco :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01BTHUBK" size="3" maxlength="2" value="<%= user.getE01BTHUBK().trim()%>">
             </td> 
            <td nowrap> 
              <div align="right">Sucursal :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01BTHUBR" size="5" maxlength="4" value="<%= user.getE01BTHUBR().trim()%>" onkeypress="enterInteger()">
            	<a href="javascript:GetBranch('E01BTHUBR','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0"  ></a>
             </td>  
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Centro Costo :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01EUPCCN" size="10" maxlength="9" value="<%= user.getE01EUPCCN().trim()%>" onkeypress="enterInteger()">
              	<a href="javascript:GetCostCenter('E01EUPCCN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
             </td> 
            <td nowrap> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01BTHOFC" size="4" maxlength="3" value="<%= user.getE01BTHOFC().trim()%>">
            	<a href="javascript:GetCodeCNOFC('E01BTHOFC','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
             </td>  
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">No. Cliente :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01BTHCUN" size="12" maxlength="10" value="<%= user.getE01BTHCUN().trim()%>" onkeypress="enterInteger()">
              	<a href="javascript:GetCustomer('E01BTHCUN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0" ></a>
             </td> 
            <td nowrap> 
              <div align="right">Identif. Grupo :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01BTHGRP" size="15" maxlength="10" value="<%= user.getE01BTHGRP().trim()%>">
            	<a href="javascript:GetGroupId('E01BTHGRP')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"></a> 
             </td>  
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Nivel de Autorización :</div>
            </td>
            <td nowrap > 
              	<input type="text" name="E01BTHAUT" size="2" maxlength="1" value="<%= user.getE01BTHAUT().trim()%>">
              	<a href="javascript:GetCode('E01BTHAUT','STATIC_user_authorization_level.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0" ></a> 
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
             </td> 
            <td nowrap> 
              <div align="right">Tipo Moneda Transf./Tesorería :</div>
            </td>
            <td nowrap> 
            	<select name="E01BTHFL7">
                	<option value=" " <% if (!(user.getE01BTHFL7().equals("1")||user.getE01BTHFL7().equals("2") || user.getE01BTHFL7().equals("3"))) out.print("selected"); %>> </option>
                	<option value="1" <% if (user.getE01BTHFL7().equals("1")) out.print("selected"); %>>Moneda Nacional</option>
                	<option value="2" <% if (user.getE01BTHFL7().equals("2")) out.print("selected"); %>>Moneda Extranjera</option>
                	<option value="3" <% if (user.getE01BTHFL7().equals("3")) out.print("selected"); %>>Ambas</option>
              	</select>
             </td>  
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Nivel de Consulta :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01BTHINL" size="3" maxlength="2" value="<%= user.getE01BTHINL().trim()%>" onkeypress="enterInteger()">(0 - 9)
              </td> 
            <td nowrap> 
              <div align="right">Nivel Contabilización :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01BTHACL" size="3" maxlength="2" value="<%= user.getE01BTHACL().trim()%>" onkeypress="enterInteger()">(0 - 9)
             </td>  
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Rango Lote 1 Desde :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01BTHFB1" size="5" maxlength="4" value="<%= user.getE01BTHFB1().trim()%>" onkeypress="enterInteger()">
              </td> 
            <td nowrap> 
              <div align="right">Rango Lote 1 Hasta :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01BTHTB1" size="5" maxlength="4" value="<%= user.getE01BTHTB1().trim()%>" onkeypress="enterInteger()">
             </td>  
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Rango Lote 2 Desde :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01BTHFB2" size="5" maxlength="4" value="<%= user.getE01BTHFB2().trim()%>" onkeypress="enterInteger()">
              </td> 
            <td nowrap> 
              <div align="right">Rango Lote 2 Hasta :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01BTHTB2" size="5" maxlength="4" value="<%= user.getE01BTHTB2().trim()%>" onkeypress="enterInteger()">
             </td>  
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Negociación Carta Crédito Desde :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01BTHFB3" size="5" maxlength="4" value="<%= user.getE01BTHFB3().trim()%>" onkeypress="enterInteger()">
              </td> 
            <td nowrap> 
              <div align="right">Negociación Carta Crédito Hasta :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01BTHTB3" size="5" maxlength="4" value="<%= user.getE01BTHTB3().trim()%>" onkeypress="enterInteger()">
             </td>  
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Lote por Omisión :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01BTHDIB" size="5" maxlength="4" value="<%= user.getE01BTHDIB().trim()%>" onkeypress="enterInteger()">
              </td> 
            <td nowrap> 
              <div align="right">No. Lote P&R :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01BTHPRB" size="5" maxlength="4" value="<%= user.getE01BTHPRB().trim()%>" onkeypress="enterInteger()">
             </td>  
          </tr>
         </table>
      </td>
    </tr>
  </table>
  <h4>Información Aprobación</h4>
   <table  class="tableinfo">
    <tr > 
      <td nowrap >
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Transacción (Moneda/Monto) :</div>
            </td> 
            <td nowrap> 
              	<input type="text" name="E01BTHCCY" size="4" maxlength="3" value="<%= user.getE01BTHCCY().trim()%>">
              	<a href="javascript:GetCurrency('E01BTHCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"></a> 
              	<input type="text" name="E01BTHALM" size="17" maxlength="15" value="<%= user.getE01BTHALM().trim()%>" onkeypress="enterDecimal()">
              </td> 
            <td nowrap> 
              <div align="right">Total :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01BTHAM2" size="17" maxlength="15" value="<%= user.getE01BTHAM2().trim()%>" onkeypress="enterDecimal()">
             </td>  
          </tr>
          
          <!--  NR  -->
          <% if (user.getH01FLGWK3().equals("Y")) { %>
          <TR>
				<TD nowrap align="right">Moneda Extranjera Rango de Tolerancia :</TD>
				<TD nowrap>
					<INPUT type="text" name="E01EUPANG" size="7" maxlength="6" value="<%= user.getE01EUPANG().trim()%>" onkeypress="enterDecimal()"> %
				</TD>
				<TD nowrap></TD>
				<TD nowrap></TD>
			</TR>
			<% } %>
          <!--  NR  -->          
          
          <tr id="trdark"> 
            <td nowrap colspan="4"> 
              <div align="left"><b>Aprobación Devolución de Cheques</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Máximo Diario (Moneda/Monto) :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01EUPCCY" size="4" maxlength="3" value="<%= user.getE01EUPCCY().trim()%>">
              	<a href="javascript:GetCurrency('E01EUPCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"></a> 
              	<input type="text" name="E01EUPAM2" size="17" maxlength="15" value="<%= user.getE01EUPAM2().trim()%>" onkeypress="enterDecimal()">
              </td> 
            <td nowrap> 
              <div align="right">Nivel de Aprobación :</div>
            </td>
            <td nowrap> 
               <select name="E01EUPAPL">
                <option value=" " <% if (!(user.getE01EUPAPL().equals("1")||user.getE01EUPAPL().equals("2") || user.getE01EUPAPL().equals("3"))) out.print("selected"); %>> 
                </option>
                <option value="1" <% if (user.getE01EUPAPL().equals("1")) out.print("selected"); %>>Banco</option>
                <option value="2" <% if (user.getE01EUPAPL().equals("2")) out.print("selected"); %>>Lote</option>
                <option value="3" <% if (user.getE01EUPAPL().equals("3")) out.print("selected"); %>>Todo</option>
              </select>
             </td>  
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4"> 
              <div align="left"><b>Usuario Extranjero</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Nivel de Autorización :</div>
            </td>
            <td nowrap> 
               <select name="E01EUPF01">
                <option value=" " <% if (!(user.getE01EUPF01().equals("1")||user.getE01EUPF01().equals("2") || user.getE01EUPF01().equals("3")|| user.getE01EUPF01().equals("N"))) out.print("selected"); %>> 
                </option>
                <option value="N" <% if (user.getE01EUPF01().equals("N")) out.print("selected"); %>>usuario no extranjero</option>
                <option value="1" <% if (user.getE01EUPF01().equals("1")) out.print("selected"); %>>Entrar Solicitud</option>
                <option value="2" <% if (user.getE01EUPF01().equals("2")) out.print("selected"); %>>Aprobar Solicitud</option>
                <option value="3" <% if (user.getE01EUPF01().equals("3")) out.print("selected"); %>>Entrar/Aprobar Solicitud</option>
               </select>
              </td> 
            <td nowrap> 
              <div align="right">Sucursal Extranjera :</div>
            </td>
            <td nowrap> 
            	<input type="text" name="E01EUPUC2" size="4" maxlength="3" value="<%= user.getE01EUPUC2().trim()%>">
             </td>  
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Código Oficial Extranjero :</div>
            </td>
            <td nowrap> 
				<input type="text" name="E01EUPUC1" size="4" maxlength="3" value="<%= user.getE01EUPUC1().trim()%>"> 
				<a href="javascript:GetCodeCNOFC('E01EUPUC1','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>              
			</td> 
            <td nowrap> 
              <div align="right">Código Agencia Física(CAB) : </div>
			</td>
            <td nowrap> 
             <INPUT type="text" name="E01EUPAOF" size="5" maxlength="4" value="<%= user.getE01EUPAOF().trim()%>" onkeypress="enterInteger()">
            	<A href="javascript:GetCodeCNOFC('E01EUPAOF','OV')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0">
				</A></td>  
          </tr>
          
        </table>
        </td>
      </tr>
    </table>
    
  <h4>Características Adicionales</h4>
    
  <table  class="tableinfo">
    <tr> 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
           <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Asientos Contables :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHCGL" value="Y" <%if (user.getE01BTHCGL().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHCGL" value="N" <%if (!user.getE01BTHCGL().equals("Y")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Cambiar No. de Clientes :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHCCU" value="Y" <%if (user.getE01BTHCCU().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHCCU" value="N" <%if (!user.getE01BTHCCU().equals("Y")) out.print("checked"); %>>No
             </td>  
          </tr>
           <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Asientos Cuentas de Detalle :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHRTL" value="Y" <%if (user.getE01BTHRTL().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHRTL" value="N" <%if (!user.getE01BTHRTL().equals("Y")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Cambiar Estado de la Cuenta :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHCAC" value="Y" <%if (user.getE01BTHCAC().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHCAC" value="N" <%if (!user.getE01BTHCAC().equals("Y")) out.print("checked"); %>>No
             </td>  
          </tr>
           <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Asientos Contratos (Activos) :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHASS" value="Y" <%if (user.getE01BTHASS().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHASS" value="N" <%if (!user.getE01BTHASS().equals("Y")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Cambiar Retenciones y Garantías :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHCHL" value="Y" <%if (user.getE01BTHCHL().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHCHL" value="N" <%if (!user.getE01BTHCHL().equals("Y")) out.print("checked"); %>>No
             </td>  
          </tr>
           <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Asientos Contratos(Pasivos) :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHLIB" value="Y" <%if (user.getE01BTHLIB().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHLIB" value="N" <%if (!user.getE01BTHLIB().equals("Y")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Aprobar Contratos (Activos) :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHLNA" value="Y" <%if (user.getE01BTHLNA().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHLNA" value="N" <%if (!user.getE01BTHLNA().equals("Y")) out.print("checked"); %>>No
             </td>  
          </tr>
           <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Asientos Cartas de Crédito :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHLCR" value="Y" <%if (user.getE01BTHLCR().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHLCR" value="N" <%if (!user.getE01BTHLCR().equals("Y")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Aprobar Contratos(Pasivos) :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHCDA" value="Y" <%if (user.getE01BTHCDA().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHCDA" value="N" <%if (!user.getE01BTHCDA().equals("Y")) out.print("checked"); %>>No
             </td>  
          </tr>
           <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Asientos Reembolso :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHRBM" value="Y" <%if (user.getE01BTHRBM().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHRBM" value="N" <%if (!user.getE01BTHRBM().equals("Y")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Aprobar S/G Pagos & Recibos :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHAOP" value="Y" <%if (user.getE01BTHAOP().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHAOP" value="N" <%if (!user.getE01BTHAOP().equals("Y")) out.print("checked"); %>>No
             </td>  
          </tr>
           <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Asientos Hipotecas :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHMLF" value="Y" <%if (user.getE01BTHMLF().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHMLF" value="N" <%if (!user.getE01BTHMLF().equals("Y")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Aprobar Garantías :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHCLP" value="Y" <%if (user.getE01BTHCLP().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHCLP" value="N" <%if (!user.getE01BTHCLP().equals("Y")) out.print("checked"); %>>No
             </td>  
          </tr>
           <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Asientos Varios :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHOTH" value="Y" <%if (user.getE01BTHOTH().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHOTH" value="N" <%if (!user.getE01BTHOTH().equals("Y")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Aprobar Cartas de Crédito :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHLEC" value="Y" <%if (user.getE01BTHLEC().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHLEC" value="N" <%if (!user.getE01BTHLEC().equals("Y")) out.print("checked"); %>>No
             </td>  
          </tr>
           <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Inversiones Bancarias :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHAIV" value="Y" <%if (user.getE01BTHAIV().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHAIV" value="N" <%if (!user.getE01BTHAIV().equals("Y")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Aprobar Líneas de Crédito :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHLIC" value="Y" <%if (user.getE01BTHLIC().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHLIC" value="N" <%if (!user.getE01BTHLIC().equals("Y")) out.print("checked"); %>>No
             </td>  
          </tr>
           <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Cambiar Asientos Extendidos :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHILF" value="Y" <%if (user.getE01BTHILF().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHILF" value="N" <%if (!user.getE01BTHILF().equals("Y")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Aprobar S/G Lineas de Crédito :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHODL" value="Y" <%if (user.getE01BTHODL().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHODL" value="N" <%if (!user.getE01BTHODL().equals("Y")) out.print("checked"); %>>No
             </td>  
          </tr>
           <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Asientos Transferencias Externas :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHAEW" value="Y" <%if (user.getE01BTHAEW().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHAEW" value="N" <%if (!user.getE01BTHAEW().equals("Y")) out.print("checked"); %>>No
             </td> 
            <td nowrap> 
              <div align="right">Aprobar Contratos Moneda Extranjera :</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHAFE" value="Y" <%if (user.getE01BTHAFE().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHAFE" value="N" <%if (!user.getE01BTHAFE().equals("Y")) out.print("checked"); %>>No
             </td>  
          </tr>
           <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Tipo de Banca :</div>
            </td>
            <td nowrap> 
            <div align="left"> 
                <input type="text" name="E01EUPUC3" size="5" maxlength="4" value="<%= user.getE01EUPUC3().trim()%>">
                <a href="javascript:GetCodeCNOFC('E01EUPUC3','TB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a></div>
			</td> 
            <td nowrap> 
              <div align="right">Aprobar Sobregiro Operaciones Comerciales Exterior:</div>
            </td>
            <td nowrap> 
              <input type="radio" name="E01BTHF02" value="Y" <%if ( user.getE01BTHF02().equals("Y")) out.print("checked"); %>>Sí
              <input type="radio" name="E01BTHF02" value="N" <%if (!user.getE01BTHF02().equals("Y")) out.print("checked"); %>>No
             </td> 
          </tr>
           <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tipos Cheques de Gerencia :</div>
            </td>
            <td nowrap> 
               <select name="E01BTHOCK">
                <option value=" " <% if (!(user.getE01BTHOCK().equals("A")||user.getE01BTHOCK().equals("N"))) out.print("selected"); %>> 
                </option>
                <option value="A" <% if (user.getE01BTHOCK().equals("A")) out.print("selected"); %>>Todos los tipos</option>
                <option value="N" <% if (user.getE01BTHOCK().equals("N")) out.print("selected"); %>>No Autorizado</option>
                </select>
             </td> 
            <td nowrap align="right">Nivel de Aprobación de Clientes :</td>
            <td nowrap> 
             <SELECT name="E01BTHF31">
					<OPTION value=" "
						<% if (!(user.getE01BTHF31().equals("N")||user.getE01BTHF31().equals("1")||user.getE01BTHF31().equals("2"))) out.print("selected"); %>>
					</OPTION>
					<OPTION value="N"
						<% if (user.getE01BTHF31().equals("N")) out.print("selected"); %>>No Autorizado
					</OPTION>
					<OPTION value="1"
						<% if (user.getE01BTHF31().equals("1")) out.print("selected"); %>>Regular
					</OPTION>
					<OPTION value="2"
						<% if (user.getE01BTHF31().equals("2")) out.print("selected"); %>>Regular con Cambio de ID 
					</OPTION>
			 </SELECT>
			</td> 
          </tr>
            <tr id="trclear">
            <td nowrap>
              <div align="right">Aprobar Precio Mercado Sobre/Debajo Tolerancia :</div>
            </td>
            <td nowrap>
             <INPUT type="radio" name="E01EUPF03" value="Y"
					<%if (user.getE01EUPF03().equals("Y")) out.print("checked"); %>>Si
              <INPUT type="radio" name="E01EUPF03" value="N"
					<%if (!user.getE01EUPF03().equals("Y")) out.print("checked"); %>>No
             </td>            
            <td nowrap align="right">Aprobación de Transferencias : 
            </td>
            <td nowrap> 
              <SELECT name="E01BTHFL9">
					<OPTION value="1"
						<% if (user.getE01BTHFL9().equals("1")) out.print("selected"); %>>Internas
					</OPTION>
					<OPTION value="2"
						<% if (user.getE01BTHFL9().equals("2")) out.print("selected"); %>>Externas Recibidas e Internas
					</OPTION>
					<OPTION value="3"
						<% if (user.getE01BTHFL9().equals("3")) out.print("selected"); %>>Externas Recibidas
					</OPTION>
					<OPTION value="4"
						<% if (user.getE01BTHFL9().equals("4")) out.print("selected"); %>>Externas Enviadas 
					</OPTION>
					<OPTION value="5"
						<% if (user.getE01BTHFL9().equals("5")) out.print("selected"); %>>Todas
					</OPTION>
			 </SELECT>
             </td> 
            </tr>
			<TR id="trdark">
				<TD nowrap align="right">Aprobar Excedente en Tasa :</TD>
				<TD nowrap>
             <INPUT type="radio" name="E01BTHFL4" value="Y"
					<%if (user.getE01BTHFL4().equals("Y")) out.print("checked"); %>>Si
              <INPUT type="radio" name="E01BTHFL4" value="N"
					<%if (!user.getE01BTHFL4().equals("Y")) out.print("checked"); %>>No
				</TD>
				<TD nowrap align="right">Tipo Usuario Inversiones :</TD>
				<TD nowrap>
					<select name="E01EUPF04">
						<option value="PR"
						<% if (user.getE01EUPF04().equals("PR")) out.print("selected"); %>>Banca Privada
						</option>
						<option value="TR"
						<% if (user.getE01EUPF04().equals("TR")) out.print("selected"); %>>Tesorer&iacute;a
						</option>
						<option value="  "
						<% if (!user.getE01EUPF04().equals("TR") && !user.getE01EUPF04().equals("PR")) out.print("selected"); %>>Ninguno
						</option>						
					</select>
				<%-- 
				<INPUT type="radio" name="E01EUPF04" value="PR" <%if (user.getE01EUPF04().equals("PR")) out.print("checked"); %>>Banca Privada 
				<INPUT type="radio" name="E01EUPF04" value="TR" <%if (user.getE01EUPF04().equals("TR")) out.print("checked"); %>>Tesorería
				<INPUT type="radio" name="E01EUPF04" value="  " <%if (!user.getE01EUPF04().equals("TR") && !user.getE01EUPF04().equals("PR")) out.print("checked"); %>>Ninguno
				--%>
				</TD>
			</TR>          
        </table>
      </td>
    </tr>
  </table>
  <br>
   <p align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Submit">
  </p>
</form>
</body>
</html>


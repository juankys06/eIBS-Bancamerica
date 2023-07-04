<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Corporate User</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="msgData" class="datapro.eibs.beans.ESS570001Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT> 
<script language="Javascript1.1">
  function GetIMFHelp(code){   
  	fieldName=code;
	page= prefix +language + "ESS5700_Message_Help.jsp";
	CenterWindow(page,450,300,2);    
  }
</script>
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
<h3 align="center">Mensajes de Confirmación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cliente_personal_new_data, ESS2000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS5700" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <h4>Información General</h4>
  <table class="tableinfo">
     <tr id="trdark"> 
        <td nowrap><div align="right">Numero de Mensaje :</div></td>
          <td nowrap>
               <div align="left"> 
                  <input type="text" ID="MANDATORY" name="E01IMFCDE" size="6" maxlength="4" value="<%= msgData.getE01IMFCDE() %>" <%= !msgData.getE01IMFCDE().trim().equals("0")?"readonly":""  %> >
                  <% if(msgData.getE01IMFCDE().trim().equals("0")){  %>
                     <a href="javascript:GetIMFHelp('E01IMFCDE')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"  ></a>                   
                  <% } %>   
                     <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  >
               </div>
          </td>        
     </tr>
     <tr id="trclear"> 
        <td nowrap><div align="right">Idioma :</div></td>
          <td nowrap>
               <div align="left"> 
                  <SELECT NAME="E01IMFLIF">
                     <OPTION <%= msgData.getE01IMFLIF().equals("E")?"selected":"" %>  VALUE="E">Ingles</OPTION>
                     <OPTION <%= msgData.getE01IMFLIF().equals("S")?"selected":"" %>  VALUE="S">Espanol</OPTION>
                  </SELECT>
                   <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  >
               </div>
          </td>        
     </tr>
     
     <tr id="trdark">
        <td nowrap><div align="right">Texto  de Mansaje :</div></td>
        <td nowrap>
           <div align="left"> 
              <input type="text" ID="MANDATORY" name="E01IMFLN1" size="75" maxlength="60" value="<%= msgData.getE01IMFLN1() %>"><br>                                    
              <input type="text" ID="MANDATORY" name="E01IMFLN2" size="75" maxlength="60" value="<%= msgData.getE01IMFLN2() %>"><br>                                    
              <input type="text" ID="MANDATORY" name="E01IMFLN3" size="75" maxlength="60" value="<%= msgData.getE01IMFLN3() %>"><br>                                                                
           </div>
        </td>
  	 </tr>
  </table>
  <br>
<h4>Ayuda para Configurar Mensajes</h4>
  <table class="tableinfo" >
     <tr id="trdark"> 
        <td nowrap>
           Este programa permite costomizar los mensajes aplicativos que genera el IBS para el apliccaion D-IBS. Cada Mensaje puede tener asociada una consulta Internet.                                                                  
        </td>     
     </tr>
  </table> 
  <br>  
  <table class="tableinfo">
     <tr id="trdark"> 
        <TH>Código</TH>
        <TH>Descripción</TH>
        <TH>Dato</TH>
        <TH>Dato Editado</TH>
     </tr>
     <tr>
       <td>A1</td>
       <td>Cuenta Primaria</td>
       <td>12</td>
       <td>12</td>
     </tr>
	<TR>
		<TD>A2</TD>
		<TD>Cuenta Secundaria</TD>
		<TD>12</TD>
		<TD>12</TD>
	</TR>
	<TR>
		<TD>A3</TD>
		<TD>Número de Referencia</TD>
		<TD>19</TD>
		<TD>19</TD>
	</TR>
	<TR>
		<TD>M1</TD>
		<TD>Monto de la transaccion o Capital</TD>
		<TD>15,2</TD>
		<TD>19</TD>
	</TR>
	<TR>
		<TD>M2</TD>
		<TD>Monto de Intereses o Monto del Pago </TD>
		<TD>15,2</TD>
		<TD>19</TD>
	</TR>
	<TR>
		<TD>M3</TD>
		<TD>Monto Alterno</TD>
		<TD>15,2</TD>
		<TD>19</TD>
	</TR>
	<TR>
		<TD>D1</TD>
		<TD>Fecha de la Transaccion o Apertura </TD>
		<TD>6,0</TD>
		<TD>8</TD>
	</TR>
	<TR>
		<TD>D2</TD>
		<TD>Fecha de Vencimiento o Pago </TD>
		<TD>6,0</TD>
		<TD>8</TD>
	</TR>
	<TR>
		<TD>R1</TD>
		<TD>Tasa de Interes</TD>
		<TD>9,6</TD>
		<TD>10</TD>
	</TR>
	<TR>
		<TD>T1</TD>
		<TD>Comentarios</TD>
		<TD>30</TD>
		<TD>30</TD>
	</TR></table>  
  <table class="tableinfo" >
     <tr id="trdark"> 
        <td>
           Cada mensaje se compone de 3 lineas de 60 carecteres para que se ingrese el
texto mas los datos variables. Los datos Variables (A1,A2,...) deben de    
estar precedidos de un Caracter (@) y dejar a al derecha espacio suficiente
para incluir el dato editado. Los datos variables comienzan donde desde el 
caracter (@)                                                               
        </td>      
     </tr>
  </table>     
  <p align="center"> 
  <input id="EIBSBTN" type=submit name="Submit" value="Enviar"> </p>
</form>
</body>
</html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Informacion No-Clientes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="client" class="datapro.eibs.beans.ESD008701Message" 	scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage" 	scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" 	scope="session" />
<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="javascript"> 
//<!-- Hide from old browsers
function a() {
 code = document.forms[0].E01LN3.value;
 top.opener.document.forms[0][top.opener.fieldName].value = code;
 top.opener.document.forms[0][top.opener.fieldName].focus();
 top.close();
 }
//-->
</script>

</head>

<body bgcolor="#FFFFFF">

<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
	out.println("<SCRIPT Language=\"Javascript\">");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
	}
 
%>



<h3 align="center">Informacion No-Clientes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="noclientes_maint_help, ESD0087"></h3>
<hr size="4">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0087">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="0003"> 

<h4>Informaci&oacute;n Basica</h4>
<div align="left">
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"border="0">
	 <tr id="trclear">
	   <td nowrap width="25%">
		 <div align="right">Identificaci&oacute;n Dominicana : </div>
	   </td>
	   <td nowrap width="20%">
		 <input type="text" name="E01LN3" size="31"  readonly maxlength="30" value="<%=client.getE01LN3().trim()%>">
	   </td>
	   <td nowrap width="25%">
		   <div align="right">Tipo Cliente :</div>
		</td>
		<td nowrap width="20%">
		 <select name="E01LGT">
                <option value=" " <% if (!(client.getE01LGT().equals("1") || client.getE01LGT().equals("2") || client.getE01LGT().equals("3")
				)) out.print("selected"); %>></option>
                <option value="1" <%if (client.getE01LGT().equals("1")) out.print("selected"); %>>Jur&iacute;dico</option>
                <option value="2" <%if (client.getE01LGT().equals("2")) out.print("selected"); %>>F&iacute;sico</option>
                <option value="3" <%if (client.getE01LGT().equals("3")) out.print("selected"); %>>Otros</option>
           </select>
               <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" > 
          
	    </td>
	</tr>
	<tr id="trdark">    
	   <td nowrap width="25%">
		 <div align="right">Identificaci&oacute;n :</div>
	   </td>
	   <td nowrap width="20%">
		 <input type="text" name="E01IDN" size="16" maxlength="15" value="<%=client.getE01IDN().trim()%>">
	   </td>
	    <td nowrap width="25%">
		  <div align="right">Tipo Identificaci&oacute;n :</div>
		</td>
	    <td width="60%" nowrap> 
          <input type="text" name="E01TID" size="5" maxlength="4" value="<%=client.getE01TID().trim()%>">
          <a href="javascript:GetCodeAuxCNOFC('E01TID','34','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a>     
        </td>
    </tr>
    <tr id="trclear">      
		<td nowrap width="25%">
		  <div align="right">Pais Identificaci&oacute;n :</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E01PID" size="5" maxlength="4" value="<%=client.getE01PID().trim()%>">   
          <a href="javascript:GetCodeCNOFC('E01PID','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
	    </td>
	    <td nowrap width="30%">
		  <div align="right">Fecha Nacimiento/Creaci&oacute;n :</div>
		</td>
        <td nowrap width="25%">
                <input type="text" name="E01BDD" size="3" maxlength="2" value="<%= client.getE01BDD().trim()%>">
                <input type="text" name="E01BDM" size="3" maxlength="2" value="<%= client.getE01BDM().trim()%>">
                <input type="text" name="E01BDY" size="5" maxlength="4" value="<%= client.getE01BDY().trim()%>">
                <a href="javascript:DOBPicker(document.forms[0].E01BDM,document.forms[0].E01BDD,document.forms[0].E01BDY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="middle" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
                (DD-MM-AAAA)
        </td>
	</tr>    
	<tr id="trdark"> 
		<td nowrap width="30%">
		  <div align="right">Primer Nombre :</div>
		</td>
        <td nowrap width="25%">
          <input type="text" name="E01NA1" size="31"  maxlength="30" value="<%=client.getE01NA1().trim()%>">
        </td> 
        <td nowrap width="25%">
		  <div align="right">Segundo Nombre :</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E01NAS" size="31" maxlength="30" value="<%=client.getE01NAS().trim()%>">  
		</td>
	</tr>
	<tr id="trclear">
		<td nowrap width="30%">
		 <div align="right">Primer Apellido :</div>
		</td>
        <td nowrap width="25%">
           <input type="text" name="E01LN1" size="31"  maxlength="30" value="<%=client.getE01LN1().trim()%>">
        </td>
        <td nowrap width="30%">
		 <div align="right">Segundo Apellido :</div>
		</td>
        <td nowrap width="25%">
           <input type="text" name="E01LN2" size="31"  maxlength="30" value="<%=client.getE01LN2().trim()%>">
        </td>
	</tr>
	<tr id="trdark">
		<td nowrap width="25%">
		  <div align="right">Sexo :</div>
		</td>
		<td nowrap width="25%">
		  <select name="E01SEX">
                <option value=" " <% if (!(client.getE01SEX().equals("F") || client.getE01LGT().equals("M")
				)) out.print("selected"); %>></option>
                <option value="F" <%if (client.getE01SEX().equals("F")) out.print("selected"); %>>Femenino</option>
                <option value="M" <%if (client.getE01SEX().equals("M")) out.print("selected"); %>>Masculino</option>
           </select>
	    </td>
		<td nowrap width="33%"> 
          <div align="right">C&oacute;digo de Negocio :</div>
        </td>
        <td nowrap width="21%"> 
          <input type="text" name="E01BUC" size="5" maxlength="4" value="<%= client.getE01BUC().trim()%>">
          <a href="javascript:GetCodeCNOFC('E01BUC','12')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
        </td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		<div align="right">C&eacute;dula Anterior :</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E01IDF" size="16" maxlength="15" value="<%=client.getE01IDF().trim()%>">
		</td>
		 <td nowrap width="25%">
		  <div align="right">Tipo Identificaci&oacute;n Anterior :</div>
		</td>
	    <td width="60%" nowrap> 
          <input type="text" name="E01TIF" size="5" maxlength="4" value="<%=client.getE01TIF().trim()%>">
          <a href="javascript:GetCodeAuxCNOFC('E01TIF','34','' )"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a>     
        </td>
     </TR>
     <TR id="trdark">   
		<td nowrap width="25%">
		  <div align="right">Pais Identificaci&oacute;n Anterior:</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E01PIF" size="5" maxlength="4" value="<%=client.getE01PIF().trim()%>">   
          <a href="javascript:GetCodeCNOFC('E01PIF','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
	    </td>
		<td nowrap width="30%">
		 <div align="right">No. Pasaporte :</div>
		</td>
        <td nowrap width="25%">
          <input type="text" name="E01PAS" size="16"  maxlength="15" value="<%=client.getE01PAS().trim()%>">
        </td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		  <div align="right">Nacionalidad Origen :</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E01CCS" size="5" maxlength="4" value="<%=client.getE01CCS().trim()%>">
		  <a href="javascript:GetCodeCNOFC('E01CCS','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
		</td>
		<td nowrap width="25%">
		 <div align="right">Nacionalidad Adquiridad :</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E01CCA" size="5" maxlength="4" value="<%=client.getE01CCA().trim()%>">
	     <a href="javascript:GetCodeCNOFC('E01CCA','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 	
		</td>
	</tr>
	<tr id="trdark">
	    <td nowrap width="25%">
		<div align="right">RNC :</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E01RNC" size="31" maxlength="30" value="<%=client.getE01RNC().trim()%>">
		</td> 
		<td nowrap width="25%">
		  <div align="right">Otros :</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E01OTR" size="31" maxlength="30" value="<%=client.getE01OTR().trim()%>">
		</td>
	</tr>
</table>

<h4>Direcci&oacute;n</h4>
<div align="left">
<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%"border="0">
	<tr id="trclear">
		<td nowrap width="25%">
		  <div align="right">Calle :</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E01NA2" size="31"  maxlength="30" value="<%=client.getE01NA2().trim()%>">
	    </td>
	  <td nowrap width="25%">
		<div align="right">Casa No. :</div>
	  </td>
	  <td nowrap width="20%">
		 <input type="text" name="E01NA4" size="31"  maxlength="30" value="<%=client.getE01NA4().trim()%>">
	   </td>
	</tr>
	<tr id="trdark">
		<td nowrap width="30%">
		 <div align="right">Edificio :</div>
		</td>
        <td nowrap width="25%">
           <input type="text" name="E01EDI" size="11"  maxlength="10" value="<%=client.getE01EDI().trim()%>">
        </td>
        <td nowrap width="30%">
		  <div align="right">Residencia :</div>
		</td>
        <td nowrap width="25%">
          <input type="text" name="E01NA3" size="31"  maxlength="30" value="<%=client.getE01NA3().trim()%>"> 
        </td>
	</tr>
	<tr id="trclear">
		<td nowrap width="25%">
		  <div align="right">Provincia :</div>
		</td>
		<td nowrap width="25%">
		  <input type="text" name="E01STE" size="5"  maxlength="4" value="<%=client.getE01STE().trim()%>">
          <A href="javascript:GetCodeDescCNOFC('E01STE','','PV')" >
          <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand" >
	      </A> 
	    </td>
	    <td nowrap width="30%">
		  <div align="right">Distrito :</div>
		</td>
        <td nowrap width="25%">
          <input type="text" name="E01UC7" size="5" maxlength="4" value="<%=client.getE01UC7().trim()%>"> 
          <a href="javascript:Get2FilterCodes('E01UC7','','PI',document.forms[0].E01STE.value,'')" >
		  <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand" ></a>      
        </td>
	</tr>
	<tr id="trdark">
		<td nowrap width="30%">
		  <div align="right">Sector :</div>
		</td>
        <td nowrap width="25%">
          <input type="text" name="E01UC8" size="5" maxlength="4" value="<%=client.getE01UC8().trim()%>"> 
          <a href="javascript:Get2FilterCodes('E01UC8','','PE',document.forms[0].E01STE.value,document.forms[0].E01UC7.value)" >
		  <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" style="cursor:hand" ></a>      
        </td>
        <td nowrap width="25%">
		</td>
		<td nowrap width="20%">
		</td>
	</tr>
	<tr id="trclear">
		<td nowrap width="30%">
		  <div align="right">Ciudad/Municipio/Secci&oacute;n :</div>
		</td>
        <td nowrap width="25%">
           <input type="text" name="E01CTY" size="31" maxlength="30" value="<%=client.getE01CTY().trim()%>">
        </td>
        <td nowrap width="25%">
		  <div align="right">Estado :</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E01EST" size="31" maxlength="30" value="<%=client.getE01EST().trim()%>">
		</td>
	</tr>
	<tr id="trdark">
		<td nowrap width="30%">
		   <div align="right">Tel&eacute;fono Casa :</div>
		</td>
        <td nowrap width="25%">
           <input type="text" name="E01HPN" size="12"  maxlength="11" value="<%=client.getE01HPN().trim()%>">
        </td>
        <td nowrap width="25%">
		  <div align="right">Tel&eacute;fono Oficina :</div>
		</td>
		<td nowrap width="20%">
		  <input type="text" name="E01PHN" size="12" maxlength="11" value="<%=client.getE01PHN().trim()%>">
		</td>
	</tr>
	<tr id="trclear">
		<td nowrap width="30%">
		 <div align="right">Tel&eacute;fono Celular :</div>
		</td>
        <td nowrap width="25%">
          <input type="text" name="E01PH1" size="12" maxlength="11" value="<%=client.getE01PH1().trim()%>">
        </td>
        <td nowrap width="30%">
          <div align="right">Comentarios :</div>
		</td>
        <td nowrap width="25%">
          <input type="text" name="E01COM" size="46" maxlength="45" value="<%=client.getE01COM().trim()%>">
        </td>
	</tr>
</table>

<div align="center"><input id="EIBSBTN" type=submit name="Submit" value="Enviar" onclick="a()"></div>

</form>
</body>
</html>


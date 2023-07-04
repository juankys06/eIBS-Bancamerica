<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Corporate User</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="msgData" class="datapro.eibs.beans.ESS560001Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />
<jsp:useBean id="msgDataTxt" class="java.lang.String" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT> 
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
 
 
<h3 align="center">Mensaje de Mercadeo a Usuario de Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cliente_personal_new_data, ESS2000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS5600" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <INPUT TYPE=HIDDEN NAME="IMDUNQ" VALUE="<%= msgData.getIMDUNQ() %>">
  <h4>Información General</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap><div align="right">Descripción :</div></td>
            <td nowrap colspan="3"><div align="left"> <input type="text" ID="MANDATORY" name="IMDDSC" size="65" maxlength="60" value="<%= msgData.getIMDDSC().trim() %>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
			</tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Fecha Inicio :</div></td>
            <td nowrap width="60%" ><div align="left"> 
                <input type="text" name="IMDSTDD" size="3" maxlength="2" value="<%= msgData.getIMDSTDD().trim() %>">
                <input type="text" name="IMDSTMM" size="3" maxlength="2" value="<%= msgData.getIMDSTMM().trim() %>">
                <input type="text" name="IMDSTYY" size="5" maxlength="4" value="<%= msgData.getIMDSTYY().trim() %>">
                <a href="javascript:DOBPicker(document.forms[0].IMDSTMM,document.forms[0].IMDSTDD,document.forms[0].IMDSTYY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="middle" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
                (DD-MM-AAAA)
            
            </div></td>
				<TD nowrap width="60%"><div align="right">Fecha Final :</div></TD>
				<TD nowrap width="60%"><div align="left">
                <input type="text" name="IMDENDD" size="3" maxlength="2" value="<%= msgData.getIMDENDD().trim() %>">
                <input type="text" name="IMDENDM" size="3" maxlength="2" value="<%= msgData.getIMDENDM().trim() %>">
                <input type="text" name="IMDENDY" size="5" maxlength="4" value="<%= msgData.getIMDENDY().trim() %>">
                <a href="javascript:DOBPicker(document.forms[0].IMDENDM,document.forms[0].IMDENDD,document.forms[0].IMDENDY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" align="middle" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
                (DD-MM-AAAA)                       
            </div></TD>
			</tr>                
		  <tr id="trdark">
		     <td><div align="right">Cliente :</div></td>
		     <td><input type="text" name="IMDCUN" size="10" maxlength="9" value="<%= msgData.getIMDCUN().trim() %>">
		         <a href="javascript:GetCustomerDescId('IMDCUN','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0" ></a>
		     (Blanco si Todos )</td>
		     <td><div align="right">Producto :</div></td>
		     <td><input type="text" name="IMDPRD" size="6" maxlength="4" value="<%= msgData.getIMDPRD().trim() %>"> (Blanco si Todos )</td>
		  </tr>	  
		  <input type=hidden name="IMDFRQ" value="1">	
		  <input type=hidden name="IMDNUM" value="1">	
          <!--  tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Via :</div></td>
            <td nowrap width="60%"><div align="left">
		            <SELECT name="IMDVIA">
						<OPTION value=" " <%= msgData.getIMDVIA().trim().equals("")?"SELECTED":"" %> >Todas</OPTION>
						<OPTION value="E" <%= msgData.getIMDVIA().trim().equals("E")?"SELECTED":"" %> >e Mail a Cliente</OPTION>
						<OPTION value="M" <%= msgData.getIMDVIA().trim().equals("M")?"SELECTED":"" %> >Mensaje en Banca por Internet</OPTION>
					</SELECT>
             <IMG src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="bottom" border="0"></div>
           </td>
           <td><div align="right">Proceso Especial :</div></td>
           <td><input type="text" name="IMDTYP" size="3" maxlength="2" value="<%= msgData.getIMDTYP() %>"></td>
			</tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Frecuencia :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT name="IMDFRQ">
										<OPTION value="1" <%= msgData.getIMDFRQ().equals("1")?"SELECTED":"" %>>1 Vez</OPTION>
										<OPTION value="D" <%= msgData.getIMDFRQ().equals("D")?"SELECTED":"" %>>Diaria</OPTION>
										<OPTION value="W" <%= msgData.getIMDFRQ().equals("W")?"SELECTED":"" %>>Semanal</OPTION>										
										<OPTION value="M" <%= msgData.getIMDFRQ().equals("M")?"SELECTED":"" %>>Mensual</OPTION>
										<OPTION value="Y" <%= msgData.getIMDFRQ().equals("Y")?"SELECTED":"" %>>Anual</OPTION>
										</SELECT>
								<IMG src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="bottom" border="0"></div></td>
				<TD nowrap width="60%"><div align="right">Numero de Mensajes </div></TD>
				<TD nowrap width="60%"><INPUT type="text" name="IMDNUM" size="4" maxlength="3" value="<%= msgData.getIMDNUM() %>">
				                      <IMG	src="<%=request.getContextPath()%>/images/Check.gif"alt="mandatory field" align="bottom" border="0">
				</TD>
			</tr -->
        </table>
        </td>
      </tr>
    </table>
  <!-- 
  <h4>Filtros de Proceso (Seleccionar solo los que desea aplicar)</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="15%" ><div align="right">Tipo de Cliente  :</div></td>
            <td nowrap width="30%" ><div align="left"><SELECT
					name="IMDLGT">
					<OPTION value="0" <%= msgData.getIMDLGT().equals("0")?"SELECTED":"" %>>Todos</OPTION>
					<OPTION value="2" <%= msgData.getIMDLGT().equals("2")?"SELECTED":"" %>>Personas</OPTION>
					<OPTION value="1" <%= msgData.getIMDLGT().equals("1")?"SELECTED":"" %>>Empresas</OPTION>
				</SELECT><IMG src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="bottom"></div></td>
				<TD nowrap width="15%"><div align="right">Sexo :</div></TD>
				<TD nowrap width="30%"><SELECT name="IMDSEX">
				    <OPTION value=" " <%= msgData.getIMDSEX().equals(" ")?"SELECTED":"" %>>Ambos</OPTION>
					<OPTION value="M" <%= msgData.getIMDSEX().equals("M")?"SELECTED":"" %>>Masculino</OPTION>
					<OPTION value="F" <%= msgData.getIMDSEX().equals("F")?"SELECTED":"" %>>Femenino</OPTION>
				</SELECT><IMG src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="bottom"></TD>
			</TR>
			<tr id="teclear"> 
            <td nowrap ><div align="right">Nivel de Educación :</div></td>
            <td nowrap ><div align="left"> <input type="text" name="IMDEDU" size="5" maxlength="4" value="<%= msgData.getIMDEDU() %>">
			                 <a href="javascript:GetCodeCNOFC('IMDEDU','29')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a>
            	</div></td>
				<TD nowrap ><div align="right">Profesión / Educación :</div></TD>
				<TD nowrap >
	              <input type="text" name="IMDUC9" size="5" maxlength="4" value="<%= msgData.getIMDUC9() %>">
                    <a href="javascript:GetCodeCNOFC('IMDUC9','49')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a>
				</TD>
			</TR>
			<TR id="trdark">
				<TD nowrap ><div align="right">Codigo de Usuario1 :</div></TD>
				<TD nowrap ><input type="text" name="IMDUC1" size="5" maxlength="4" value="<%= msgData.getIMDUC1() %>">
              <a href="javascript:GetCodeCNOFC('IMDUC1','2A')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
              </TD>
				<TD nowrap ><div align="right">Codigo de Industria : </div></TD>
				<TD nowrap >
				  <input type="text" name="IMDINC" size="5" maxlength="4" value="<%= msgData.getIMDINC() %>">
	              <a href="javascript:GetCodeCNOFC('IMDINC','09')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 				
				</TD>
			</TR>
			<TR id="teclear">
				<TD nowrap ><div align="right">Nivel de Ingreso</div></TD>
				<TD nowrap ><INPUT type="text" name="IMDINL" size="2" maxlength="1" value="<%= msgData.getIMDINL() %>"></TD>
				<TD nowrap ><div align="right">Nivel de Riesgo :</div></TD>
				<TD nowrap ><input type="text" name="IMDRSL" size="5" maxlength="4" value="<%= msgData.getIMDRSL() %>">
                   <a href="javascript:GetCodeCNOFC('IMDRSL','31')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 				
				</TD>
			</TR>
			<tr id="trdark"> 
              <td nowrap ><div align="right">Oficial :</div></td>
              <td nowrap ><div align="left">
                           <input type="text" name="IMDOFC" size="5" maxlength="4" value="<%= msgData.getIMDOFC() %>">
              <a href="javascript:GetCodeCNOFC('IMDOFC','15')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
             </div>
           </td>
            <td nowrap ><div align="right">Tipo de Cuenta :</div></td>
            <td nowrap ><div align="left"><INPUT type="text" name="IMDPRD" size="5" maxlength="4" value="<%= msgData.getIMDPRD() %>">
                      <a href="javascript:GetProduct('IMDPRX', '', '','','IMDPRD')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"  ></a> 
            </div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap ><div align="right"> Saldo Minimo en Tipo  Cuenta :</div></td>
            <td nowrap  ><div align="left"><INPUT type="text" name="IMDAMT"	size="20" maxlength="12" value="<%= msgData.getIMDAMT() %>"></div></td> 
            <td nowrap  ><div align="right">Estatus de Cuenta :</div></td>
            <td nowrap  ><div align="left"><INPUT type="text" name="IMDAST" size="2" maxlength="1" value="<%= msgData.getIMDAST() %>"></div></td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    -->
    
    <h4>Contenido del Mensaje</h4>
    <table class="tableinfo">
      <tr>
        <td><DIV align="CENTER"><TEXTAREA rows="20" cols="80" name="TEXTDATA" ><%= msgDataTxt %></TEXTAREA></DIV></td>
      </tr>
    </table> 
  &lt;BR&gt; = Agrega un Salto de Linea<p align="center"> 
  <input id="EIBSBTN" type=submit name="Submit" value="Enviar"> </p>
</form>
</body>
</html>

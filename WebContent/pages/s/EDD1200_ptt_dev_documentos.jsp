<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Devoluciones de Documentos
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "msgList" class= "datapro.eibs.beans.EDD120001Message"  scope="session" />

<SCRIPT language="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="javascript">
function enter(){
	if (trim(document.form.E01NRODOC.value).length < 1){
		alert("Debe introducir Número Documento");
		return false;
	}
	if (document.form.E01MONTO.value.length < 1){
		alert("Debe introducir Monto Documento");
		return false;
	}
	if (trim(document.form.E01NROBCO.value).length < 1){
		alert("Debe introducir Banco Emisor");
		return false;
	}
	if (trim(document.form.E01CREOBR.value).length < 1){
		alert("Debe introducir Sucursal Digitadora");
		return false;
	}
	if (trim(document.form.E01MOTIVO.value).length < 1){
		alert("Debe introducir Motivo");
		return false;
	}
	return true;
}

</SCRIPT>


</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>
<h3 align="center">Devoluciones Documentos en Deposito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ptt_dev_documentos.jsp , EDD1200"></h3>
<hr size="4">

<FORM Method="post" name="form" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD1200" onsubmit="return(enter())">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0"> 

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><%= userPO.getHeader1().trim()%></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><%= userPO.getHeader5().trim()%></div>
            </td>
			
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta del Deposito :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"><%= userPO.getIdentifier().trim()%></div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><%= userPO.getCurrency().trim()%></div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto: </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><%= userPO.getHeader6().trim()%></div>
            </td>
          </tr>
		  
		  <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Ejecutivo :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"><%= userPO.getHeader10().trim()%></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><%= userPO.getHeader11().trim()%></div>
            </td>
          </tr>
		  
		  <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Saldo Contable :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="rigth"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(userPO.getHeader12().trim())%></div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Saldo Disponible : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="rigth"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(userPO.getHeader13().trim())%></div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Retención : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="rigth"><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %><%= Util.fcolorCCY(userPO.getHeader14().trim())%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  <table class="tableinfo" >
    <tr > 
      <td nowrap align="center"> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="50%"> 
              <div align="right"><b>Fecha Deposito : </b></div>
            </td>
            <td nowrap> 																						
              <div align="left"><b><input type="text" name="E01RUNDT1" size="2" maxlength="2"  onKeyPress="enterInteger()" value="<%= msgList.getE01RUNDT1().trim() %>" >/<input type="text" name="E01RUNDT2" size="2" maxlength="2" onKeyPress="enterInteger()" value="<%= msgList.getE01RUNDT2().trim() %>" >/<input type="text" name="E01RUNDT3" size="2" maxlength="2" onKeyPress="enterInteger()" value="<%= msgList.getE01RUNDT3().trim() %>"> (dd/mm/aa)</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%"> 
              <div align="right"><b>Dias Retenci&oacute;n :</b></div>
            </td>
            <td nowrap> 
              <div align="left"><input type="text" name="E01NRODIAS" size="2" maxlength="2" onKeyPress="enterInteger()" value="<%= msgList.getE01NRODIAS().trim() %>"><b>(Dias)</b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="35%" > 
              <div align="right"><b>Tipo Documento : </b></div>
            </td>
            <td nowrap>               
                <select name="E01TIPODO">
                 	<option value=" " <% if (msgList.getE01TIPODO().trim().equals("")) out.print("selected"); %>>Cheque Normal</option>
                	<option value="1" <% if (msgList.getE01TIPODO().trim().equals("1")) out.print("selected"); %>>Vale Vista</option>
                	<option value="2" <% if (msgList.getE01TIPODO().trim().equals("2")) out.print("selected"); %>>Cheque Certificado</option>
                	<option value="3" <% if (msgList.getE01TIPODO().trim().equals("3")) out.print("selected"); %>>Certificado Deposito</option>
                	<option value="4" <% if (msgList.getE01TIPODO().trim().equals("4")) out.print("selected"); %>>Orden Pago INP</option>
                	<option value="5" <% if (msgList.getE01TIPODO().trim().equals("5")) out.print("selected"); %>>Cheque Fiscal</option>
                	<option value="6" <% if (msgList.getE01TIPODO().trim().equals("6")) out.print("selected"); %>>Vale Camara</option>
                </select>                 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%" > 
              <div align="right"><b>Número Documento : </b></div>
            </td>
            <td nowrap> 
               
                <input type="text" name="E01NRODOC" size="9" maxlength="9" onKeyPress="enterInteger()" value="<%= msgList.getE01NRODOC().trim() %>" >
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" > 
                
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="35%" > 
              <div align="right"><b>Monto : </b></div>
            </td>
            <td align="center" nowrap> 
              <div align="left"><b><% if ( userPO.getCurrency().trim().equals("USD")) {out.print("US$ ");} else {out.print("$ ");} %></b>
                <input type="text" name="E01MONTO" size="12" maxlength="12" onKeypress="enterDecimal()" value="<%= msgList.getE01MONTO().trim() %>" >
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" ></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%" > 
              <div align="right"><b>Banco Emisor : </b></div>
            </td>
            <td nowrap> 
              <div align="left"><b> 
                <input type="text" name="E01NROBCO" size="4" maxlength="4" onKeypress="enterInteger()" value="<%= msgList.getE01NROBCO().trim() %>" >
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" > 
                <A href="javascript:GetCodeCNOFC('E01NROBCO','X3')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda Banco" align="absbottom" border="0"></A> 
                </b> </div>
            </td>
          </tr>
		  <tr id="trdark"> 
              <td NOWRAP width="35%"> 
                  
              <div align="right"><b>Sucursal Digitadora : </b></div>
               </td>
               <td NOWRAP > 
                   <div align="left"> 
                      <input type="text" name="E01CREOBR"  size=4 maxlength=3 onKeypress="enterInteger()" value="<%= msgList.getE01CREOBR().trim() %>">
					  <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0">
               		  <A href="javascript:GetCodeCNOFC('E01CREOBR','10')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></A>
					</div>
			   </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="35%"> 
              <div align="right"><b>Motivo : </b></div>
            </td>
            <td nowrap>  
                <input type="text" name="E01MOTIVO" size="5" maxlength="4" value="<%=msgList.getE01MOTIVO()%>">
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" >
            	<A href="javascript:GetCodeCNOFC('E01MOTIVO','35')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></A>
			</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="35%"> 
              <div align="right"><b>Narrativa : </b></div>
            </td>
            <td nowrap>  
                <input type="text" name="E01NARRTV" size="35" maxlength="30" value="<%=msgList.getE01NARRTV()%>">
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0" >
			</td>
          </tr>          
        </table>
      </td>
    </tr>
  </table>
  
      
<p align="center"> 
  	<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>

</FORM>

</BODY>
</HTML>

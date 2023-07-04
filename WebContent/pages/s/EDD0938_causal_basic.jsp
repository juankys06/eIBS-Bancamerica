<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Cuentas Corrientes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="caBasic" class="datapro.eibs.beans.EDD093801Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

</head>
<body>

<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(optHelp,fieldname,bank,ccy,aux1,aux2,opcode) {
   		init(optHelp,fieldname,bank,ccy,aux1,aux2,opcode);
		showPopupHelp();
    }
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) 
 {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }


%> 

<H3 align="center">Tabla de Cargos Camara/Devoluciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="causal_basic.jsp, EDD0938"></H3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0938" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="300">
  
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>C&oacute;digo de Causal :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CDVCAU" size="5" maxlength="4" readonly value="<%= caBasic.getE01CDVCAU().trim()%>">
			</div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Descripci&oacute;n :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01CDVDSC" size="31" maxlength="30" value="<%= caBasic.getE01CDVDSC().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<BR>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Moneda de Comisi&oacute;n :</div>
            </td>
            <td nowrap width="19%"><INPUT type="text" name="E01CDVCCY" size="4" maxlength="3" value="<%= caBasic.getE01CDVCCY().trim()%>">
            	<a href="javascript:GetCurrency('E01CDVCCY', '')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
            </td>
            <td nowrap width="26%"><div align="right">Debito / Credito a Cuenta :</div></td>
            <td nowrap width="26%">
            	<SELECT name="E01CDVDEB">
					<OPTION value="N"
						<% if (caBasic.getE01CDVDEB().equals("N")) out.print("selected"); %>>No Debita Ni Acredita</OPTION>
					<OPTION value="1"
						<% if (caBasic.getE01CDVDEB().equals("1")) out.print("selected"); %>>Debita a Cuenta</OPTION>
					<OPTION value="2"
						<% if (caBasic.getE01CDVDEB().equals("2")) out.print("selected"); %>>Debita y Acredita a Cuentas</OPTION>
				</SELECT></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="29%"> 
              <div align="right">Tipo de Devolución :</div>
            </td>
            <td nowrap width="19%">
            	            	<SELECT name="E01CDVFLG">
					<OPTION value="N"
						<% if (caBasic.getE01CDVFLG().equals("N")) out.print("selected"); %>>No Aplica</OPTION>
					<OPTION value="1"
						<% if (caBasic.getE01CDVFLG().equals("1")) out.print("selected"); %>>Devoluci&oacute;n por Forma</OPTION>
					<OPTION value="2"
						<% if (caBasic.getE01CDVFLG().equals("2")) out.print("selected"); %>>Devoluci&oacute;n por Fondos</OPTION>
				</SELECT>
            </td>
            <td nowrap width="26%"><div align="right">Causal Alterno :</div></td>
            <td nowrap width="26%"><INPUT type="text" name="E01CDVALT" size="7" maxlength="6" value="<%= caBasic.getE01CDVALT().trim()%>"></td>
          </tr>
        </table>
      </td>
    </tr>
</table>
<BR>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" width="100%" border="0">
          <tr id="trdark"> 
          		<td><div align="center">Descripci&oacute;n</div></td>
          		<td><div align="center">Factor</div></td>
          		<td><div align="center">Monto</div></td>
          		<td><div align="center">Tipo</div></td>
          		<td><div align="center">Minimo</div></td>
          		<td><div align="center">Maximo</div></td>
          		<td><div align="center">Moneda</div></td>
          		<td><div align="center">Contabilidad</div></td>
          		<td><div align="center">Iva</div></td>
          	</tr>
          	<tr id="trclear">          	
            	<td><INPUT type="text" name="E01CDVDE1" size="21" maxlength="20" value="<%= caBasic.getE01CDVDE1().trim()%>"></td>
            	<td>
            		<SELECT name="E01CDVCF1">
						<OPTION value=" "<% if (caBasic.getE01CDVCF1().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="%"<% if (caBasic.getE01CDVCF1().equals("%")) out.print("selected"); %>>Porcentaje</OPTION>
						<OPTION value="F"<% if (caBasic.getE01CDVCF1().equals("F")) out.print("selected"); %>>Fijo</OPTION>
					</SELECT>
            	</td>
            	<td><INPUT type="text" name="E01CDVCA1" size="11" maxlength="9" value="<%= caBasic.getE01CDVCA1().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td>
            		<SELECT name="E01CDVAP1">
						<OPTION value=" "<% if (caBasic.getE01CDVAP1().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="1"<% if (caBasic.getE01CDVAP1().equals("1")) out.print("selected"); %>>Imp. Protestos</OPTION>
						<OPTION value="2"<% if (caBasic.getE01CDVAP1().equals("2")) out.print("selected"); %>>Imp. Rechazos</OPTION>
						<OPTION value="3"<% if (caBasic.getE01CDVAP1().equals("3")) out.print("selected"); %>>Imp. Aceptar</OPTION>
						<OPTION value="A"<% if (caBasic.getE01CDVAP1().equals("A")) out.print("selected"); %>>Com. Protestos</OPTION>
						<OPTION value="B"<% if (caBasic.getE01CDVAP1().equals("B")) out.print("selected"); %>>Com. Rechazos</OPTION>
						<OPTION value="C"<% if (caBasic.getE01CDVAP1().equals("C")) out.print("selected"); %>>Com. Aceptar</OPTION>						
					</SELECT>
            	</td>            	
            	<td><INPUT type="text" name="E01CDVMN1" size="11" maxlength="9" value="<%= caBasic.getE01CDVMN1().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td><INPUT type="text" name="E01CDVMX1" size="11" maxlength="9" value="<%= caBasic.getE01CDVMX1().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td>
            		<SELECT name="E01CDVGF1">
						<OPTION value=" "<% if (caBasic.getE01CDVGF1().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="B"<% if (caBasic.getE01CDVGF1().equals("B")) out.print("selected"); %>>Moneda Base</OPTION>
						<OPTION value="N"<% if (caBasic.getE01CDVGF1().equals("N")) out.print("selected"); %>>Moneda Transacci&oacute;n</OPTION>
					</SELECT>
            	</td>            	
            	<td>
            		<input type="text" name="E01CDVGL1" size="17" maxlength="16" onKeypress="enterInteger()" value="<%= caBasic.getE01CDVGL1()%>" oncontextmenu="showPopUp(ledgerHelp,this.name,'<%=currUser.getE01UBK()%>',document.forms[0].E01CDVCCY.value,'','','')"  id="txtright">
            	</td>
            	<td>
            		<SELECT name="E01CDVIV1">
						<OPTION value=" "<% if (caBasic.getE01CDVIV1().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="Y"<% if (caBasic.getE01CDVIV1().equals("Y")) out.print("selected"); %>>Si</OPTION>
						<OPTION value="N"<% if (caBasic.getE01CDVIV1().equals("N")) out.print("selected"); %>>No</OPTION>
					</SELECT>
            	</td>            	            	
          </tr>

          	<tr id="trclear">          	
            	<td><INPUT type="text" name="E01CDVDE2" size="21" maxlength="20" value="<%= caBasic.getE01CDVDE2().trim()%>"></td>
            	<td>
            		<SELECT name="E01CDVCF2">
						<OPTION value=" "<% if (caBasic.getE01CDVCF2().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="%"<% if (caBasic.getE01CDVCF2().equals("%")) out.print("selected"); %>>Porcentaje</OPTION>
						<OPTION value="F"<% if (caBasic.getE01CDVCF2().equals("F")) out.print("selected"); %>>Fijo</OPTION>
					</SELECT>
            	</td>
            	<td><INPUT type="text" name="E01CDVCA2" size="11" maxlength="9" value="<%= caBasic.getE01CDVCA2().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td>
            		<SELECT name="E01CDVAP2">
						<OPTION value=" "<% if (caBasic.getE01CDVAP2().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="1"<% if (caBasic.getE01CDVAP2().equals("1")) out.print("selected"); %>>Imp. Protestos</OPTION>
						<OPTION value="2"<% if (caBasic.getE01CDVAP2().equals("2")) out.print("selected"); %>>Imp. Rechazos</OPTION>
						<OPTION value="3"<% if (caBasic.getE01CDVAP2().equals("3")) out.print("selected"); %>>Imp. Aceptar</OPTION>
						<OPTION value="A"<% if (caBasic.getE01CDVAP2().equals("A")) out.print("selected"); %>>Com. Protestos</OPTION>
						<OPTION value="B"<% if (caBasic.getE01CDVAP2().equals("B")) out.print("selected"); %>>Com. Rechazos</OPTION>
						<OPTION value="C"<% if (caBasic.getE01CDVAP2().equals("C")) out.print("selected"); %>>Com. Aceptar</OPTION>						
					</SELECT>
            	</td>            	
            	<td><INPUT type="text" name="E01CDVMN2" size="11" maxlength="9" value="<%= caBasic.getE01CDVMN2().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td><INPUT type="text" name="E01CDVMX2" size="11" maxlength="9" value="<%= caBasic.getE01CDVMX2().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td>
            		<SELECT name="E01CDVGF2">
						<OPTION value=" "<% if (caBasic.getE01CDVGF2().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="B"<% if (caBasic.getE01CDVGF2().equals("B")) out.print("selected"); %>>Moneda Base</OPTION>
						<OPTION value="N"<% if (caBasic.getE01CDVGF2().equals("N")) out.print("selected"); %>>Moneda Transacci&oacute;n</OPTION>
					</SELECT>
            	</td>            	
            	<td><input type="text" name="E01CDVGL2" size="17" maxlength="16" onKeypress="enterInteger()" value="<%= caBasic.getE01CDVGL2()%>" oncontextmenu="showPopUp(ledgerHelp,this.name,'<%=currUser.getE01UBK()%>',document.forms[0].E01CDVCCY.value,'','','')"  id="txtright"></td>
            	<td>
            		<SELECT name="E01CDVIV2">
						<OPTION value=" "<% if (caBasic.getE01CDVIV2().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="Y"<% if (caBasic.getE01CDVIV2().equals("Y")) out.print("selected"); %>>Si</OPTION>
						<OPTION value="N"<% if (caBasic.getE01CDVIV2().equals("N")) out.print("selected"); %>>No</OPTION>
					</SELECT>
            	</td>            	            	
          </tr>

          	<tr id="trclear">          	
            	<td><INPUT type="text" name="E01CDVDE3" size="21" maxlength="20" value="<%= caBasic.getE01CDVDE3().trim()%>"></td>
            	<td>
            		<SELECT name="E01CDVCF3">
						<OPTION value=" "<% if (caBasic.getE01CDVCF3().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="%"<% if (caBasic.getE01CDVCF3().equals("%")) out.print("selected"); %>>Porcentaje</OPTION>
						<OPTION value="F"<% if (caBasic.getE01CDVCF3().equals("F")) out.print("selected"); %>>Fijo</OPTION>
					</SELECT>
            	</td>
            	<td><INPUT type="text" name="E01CDVCA3" size="11" maxlength="9" value="<%= caBasic.getE01CDVCA3().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td>
            		<SELECT name="E01CDVAP3">
						<OPTION value=" "<% if (caBasic.getE01CDVAP3().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="1"<% if (caBasic.getE01CDVAP3().equals("1")) out.print("selected"); %>>Imp. Protestos</OPTION>
						<OPTION value="2"<% if (caBasic.getE01CDVAP3().equals("2")) out.print("selected"); %>>Imp. Rechazos</OPTION>
						<OPTION value="3"<% if (caBasic.getE01CDVAP3().equals("3")) out.print("selected"); %>>Imp. Aceptar</OPTION>
						<OPTION value="A"<% if (caBasic.getE01CDVAP3().equals("A")) out.print("selected"); %>>Com. Protestos</OPTION>
						<OPTION value="B"<% if (caBasic.getE01CDVAP3().equals("B")) out.print("selected"); %>>Com. Rechazos</OPTION>
						<OPTION value="C"<% if (caBasic.getE01CDVAP3().equals("C")) out.print("selected"); %>>Com. Aceptar</OPTION>						
					</SELECT>
            	</td>            	
            	<td><INPUT type="text" name="E01CDVMN3" size="11" maxlength="9" value="<%= caBasic.getE01CDVMN3().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td><INPUT type="text" name="E01CDVMX3" size="11" maxlength="9" value="<%= caBasic.getE01CDVMX3().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td>
            		<SELECT name="E01CDVGF3">
						<OPTION value=" "<% if (caBasic.getE01CDVGF3().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="B"<% if (caBasic.getE01CDVGF3().equals("B")) out.print("selected"); %>>Moneda Base</OPTION>
						<OPTION value="N"<% if (caBasic.getE01CDVGF3().equals("N")) out.print("selected"); %>>Moneda Transacci&oacute;n</OPTION>
					</SELECT>
            	</td>            	
            	<td><input type="text" name="E01CDVGL3" size="17" maxlength="16" onKeypress="enterInteger()" value="<%= caBasic.getE01CDVGL3()%>" oncontextmenu="showPopUp(ledgerHelp,this.name,'<%=currUser.getE01UBK()%>',document.forms[0].E01CDVCCY.value,'','','')"  id="txtright"></td>
            	<td>
            		<SELECT name="E01CDVIV3">
						<OPTION value=" "<% if (caBasic.getE01CDVIV3().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="Y"<% if (caBasic.getE01CDVIV3().equals("Y")) out.print("selected"); %>>Si</OPTION>
						<OPTION value="N"<% if (caBasic.getE01CDVIV3().equals("N")) out.print("selected"); %>>No</OPTION>
					</SELECT>
            	</td>            	            	
          </tr>

          	<tr id="trclear">          	
            	<td><INPUT type="text" name="E01CDVDE4" size="21" maxlength="20" value="<%= caBasic.getE01CDVDE4().trim()%>"></td>
            	<td>
            		<SELECT name="E01CDVCF4">
						<OPTION value=" "<% if (caBasic.getE01CDVCF4().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="%"<% if (caBasic.getE01CDVCF4().equals("%")) out.print("selected"); %>>Porcentaje</OPTION>
						<OPTION value="F"<% if (caBasic.getE01CDVCF4().equals("F")) out.print("selected"); %>>Fijo</OPTION>
					</SELECT>
            	</td>
            	<td><INPUT type="text" name="E01CDVCA4" size="11" maxlength="9" value="<%= caBasic.getE01CDVCA4().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td>
            		<SELECT name="E01CDVAP4">
						<OPTION value=" "<% if (caBasic.getE01CDVAP4().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="1"<% if (caBasic.getE01CDVAP4().equals("1")) out.print("selected"); %>>Imp. Protestos</OPTION>
						<OPTION value="2"<% if (caBasic.getE01CDVAP4().equals("2")) out.print("selected"); %>>Imp. Rechazos</OPTION>
						<OPTION value="3"<% if (caBasic.getE01CDVAP4().equals("3")) out.print("selected"); %>>Imp. Aceptar</OPTION>
						<OPTION value="A"<% if (caBasic.getE01CDVAP4().equals("A")) out.print("selected"); %>>Com. Protestos</OPTION>
						<OPTION value="B"<% if (caBasic.getE01CDVAP4().equals("B")) out.print("selected"); %>>Com. Rechazos</OPTION>
						<OPTION value="C"<% if (caBasic.getE01CDVAP4().equals("C")) out.print("selected"); %>>Com. Aceptar</OPTION>						
					</SELECT>
            	</td>            	
            	<td><INPUT type="text" name="E01CDVMN4" size="11" maxlength="9" value="<%= caBasic.getE01CDVMN4().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td><INPUT type="text" name="E01CDVMX4" size="11" maxlength="9" value="<%= caBasic.getE01CDVMX4().trim()%>" onkeypress="enterDecimal()" id="txtright"></td>
            	<td>
            		<SELECT name="E01CDVGF4">
						<OPTION value=" "<% if (caBasic.getE01CDVGF4().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="B"<% if (caBasic.getE01CDVGF4().equals("B")) out.print("selected"); %>>Moneda Base</OPTION>
						<OPTION value="N"<% if (caBasic.getE01CDVGF4().equals("N")) out.print("selected"); %>>Moneda Transacci&oacute;n</OPTION>
					</SELECT>
            	</td>            	
            	<td><input type="text" name="E01CDVGL4" size="17" maxlength="16" onKeypress="enterInteger()" value="<%= caBasic.getE01CDVGL4()%>" oncontextmenu="showPopUp(ledgerHelp,this.name,'<%=currUser.getE01UBK()%>',document.forms[0].E01CDVCCY.value,'','','')"  id="txtright"></td>
            	<td>
            		<SELECT name="E01CDVIV4">
						<OPTION value=" "<% if (caBasic.getE01CDVIV4().equals(" ")) out.print("selected"); %>>  </OPTION>
						<OPTION value="Y"<% if (caBasic.getE01CDVIV4().equals("Y")) out.print("selected"); %>>Si</OPTION>
						<OPTION value="N"<% if (caBasic.getE01CDVIV4().equals("N")) out.print("selected"); %>>No</OPTION>
					</SELECT>
            	</td>            	            	
          	</tr>
        </table>
      </td>
    </tr>
  </table>        
  
<% if (!caBasic.getH01FLGWK2().equals("N")) { %> 
	<h4 style="text-align:center"><input type="checkbox" name="H01FLGWK1" value="D">Marcar para Eliminar</h4>
<% } %>
        
<p align="center"> 
     <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
</p>
</form>

</body>
</html>

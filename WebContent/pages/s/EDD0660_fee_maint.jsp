<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Pagos y Recibos - Tablas de cargos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lnParam" class="datapro.eibs.beans.EDD066002Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
	init(opth,field,bank,ccy,field1,field2,opcod);
	showPopupHelp();
}

function checkValues() {
	return true;
}
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }       
%>

</head>
<body>
<h3 align="center">Estructura de Cargos de Pagos y Recibos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fee_maint.jsp, EDD0660"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDD0660" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="200">
  <INPUT TYPE=HIDDEN NAME="OPT" value="<%= request.getParameter("OPT") %>">
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap align=right> 
              <b>Banco :</b>
            </td>
            <td nowrap > 
              <input type="text" name="E02PRFBNK" size="3" maxlength="2" value="<%= lnParam.getE02PRFBNK()%>" readonly>
            </td>
            <td nowrap align=right> 
              <b>Estructura de Cargo :</b>
            </td>
            <td nowrap > 
               <input type="text" name="E02PRFTBL" size="4" maxlength="3" value="<%= lnParam.getE02PRFTBL()%>" readonly>
            </td>
            <td nowrap align=right> 
              <b>Moneda de Cargo :</b>
            </td>
            <td nowrap > 
               <input type="text" name="E02PRFFCY" size="4" maxlength="3" value="<%= lnParam.getE02PRFFCY()%>" >
			   <a href="javascript:GetCurrency('E02PRFFCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0"></a>
            </td>            
          </tr>
          <tr id="trdark"> 
            <td nowrap align=right> 
              <b>Cliente :</b>
            </td>
            <td nowrap > 
               <input type="text" name="E02PRFCUN" size="4" maxlength="3" value="<%= lnParam.getE02PRFCUN()%>" readonly>
            </td>
            <td nowrap align=right> 
              <b>Nombre :</b>
            </td>
            <td nowrap>  
               <input type="text" name="E02PRFDSC" size="35" maxlength="35" value="<%= lnParam.getE02PRFDSC()%>" readonly>
            </td>
			<td>
			</td>
			<td>
			</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <th nowrap >Tipo<br>Cargo</th>
            <th nowrap >Monto<br>Cargo</th>
            <th nowrap >Monto<br>Máximo</th>
            <th nowrap >Monto<br>Mínimo</th>
            <th nowrap >Iva</th>
            <th nowrap >Moneda</th>
            <th nowrap >Cuenta</th>
            <th nowrap >Descripción</th>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFFT1">
	                <option value=" " <% if (!(lnParam.getE02PRFFT1().equals("F") ||lnParam.getE02PRFFT1().equals("%"))) out.print("selected"); %>></option>
	                <option value="F" <% if (lnParam.getE02PRFFT1().equals("F")) out.print("selected"); %>>Monto Fijo</option>
	                <option value="%" <% if (lnParam.getE02PRFFT1().equals("%")) out.print("selected"); %>>Porcentaje</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
              	<input type="text" name="E02PRFFA1" value="<%= lnParam.getE02PRFFA1() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFX1" value="<%= lnParam.getE02PRFFX1() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFM1" value="<%= lnParam.getE02PRFFM1() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFIV1">
	                <option value=" " <% if (!(lnParam.getE02PRFIV1().equals("Y") ||lnParam.getE02PRFIV1().equals("N"))) out.print("selected"); %>></option>
	                <option value="Y" <% if (lnParam.getE02PRFIV1().equals("Y")) out.print("selected"); %>>Si</option>
	                <option value="N" <% if (lnParam.getE02PRFIV1().equals("N")) out.print("selected"); %>>No</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
 				<input type="text" name="E02PRFAC1" value="<%= lnParam.getE02PRFAC1() %>" size="4" maxlength="3" oncontextmenu="showPopUp(currencyHelp,this.name,'','','','','')">              
 			  </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E02PRFGL1" size="18" maxlength="17" value="<%= lnParam.getE02PRFGL1()%>" oncontextmenu="showPopUp(ledgerHelp,this.name,'','','','','')">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="D02GL1DSC" size="35" maxlength="35" value="<%= lnParam.getD02GL1DSC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFFT2">
	                <option value=" " <% if (!(lnParam.getE02PRFFT2().equals("F") ||lnParam.getE02PRFFT2().equals("%"))) out.print("selected"); %>></option>
	                <option value="F" <% if (lnParam.getE02PRFFT2().equals("F")) out.print("selected"); %>>Monto Fijo</option>
	                <option value="%" <% if (lnParam.getE02PRFFT2().equals("%")) out.print("selected"); %>>Porcentaje</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
              	<input type="text" name="E02PRFFA2" value="<%= lnParam.getE02PRFFA2() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFX2" value="<%= lnParam.getE02PRFFX2() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFM2" value="<%= lnParam.getE02PRFFM2() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFIV2">
	                <option value=" " <% if (!(lnParam.getE02PRFIV2().equals("Y") ||lnParam.getE02PRFIV2().equals("N"))) out.print("selected"); %>></option>
	                <option value="Y" <% if (lnParam.getE02PRFIV2().equals("Y")) out.print("selected"); %>>Si</option>
	                <option value="N" <% if (lnParam.getE02PRFIV2().equals("N")) out.print("selected"); %>>No</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
 				<input type="text" name="E02PRFAC2" value="<%= lnParam.getE02PRFAC2() %>" size="4" maxlength="3" oncontextmenu="showPopUp(currencyHelp,this.name,'','','','','')">              
 			  </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E02PRFGL2" size="18" maxlength="17" value="<%= lnParam.getE02PRFGL2()%>" oncontextmenu="showPopUp(ledgerHelp,this.name,'','','','','')">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="D02GL2DSC" size="35" maxlength="35" value="<%= lnParam.getD02GL2DSC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFFT3">
	                <option value=" " <% if (!(lnParam.getE02PRFFT3().equals("F") ||lnParam.getE02PRFFT3().equals("%"))) out.print("selected"); %>></option>
	                <option value="F" <% if (lnParam.getE02PRFFT3().equals("F")) out.print("selected"); %>>Monto Fijo</option>
	                <option value="%" <% if (lnParam.getE02PRFFT3().equals("%")) out.print("selected"); %>>Porcentaje</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
              	<input type="text" name="E02PRFFA3" value="<%= lnParam.getE02PRFFA3() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFX3" value="<%= lnParam.getE02PRFFX3() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFM3" value="<%= lnParam.getE02PRFFM3() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFIV3">
	                <option value=" " <% if (!(lnParam.getE02PRFIV3().equals("Y") ||lnParam.getE02PRFIV3().equals("N"))) out.print("selected"); %>></option>
	                <option value="Y" <% if (lnParam.getE02PRFIV3().equals("Y")) out.print("selected"); %>>Si</option>
	                <option value="N" <% if (lnParam.getE02PRFIV3().equals("N")) out.print("selected"); %>>No</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
 				<input type="text" name="E02PRFAC3" value="<%= lnParam.getE02PRFAC3() %>" size="4" maxlength="3" oncontextmenu="showPopUp(currencyHelp,this.name,'','','','','')">              
 			  </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E02PRFGL3" size="18" maxlength="17" value="<%= lnParam.getE02PRFGL3()%>" oncontextmenu="showPopUp(ledgerHelp,this.name,'','','','','')">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="D02GL3DSC" size="35" maxlength="35" value="<%= lnParam.getD02GL3DSC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFFT4">
	                <option value=" " <% if (!(lnParam.getE02PRFFT4().equals("F") ||lnParam.getE02PRFFT4().equals("%"))) out.print("selected"); %>></option>
	                <option value="F" <% if (lnParam.getE02PRFFT4().equals("F")) out.print("selected"); %>>Monto Fijo</option>
	                <option value="%" <% if (lnParam.getE02PRFFT4().equals("%")) out.print("selected"); %>>Porcentaje</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
              	<input type="text" name="E02PRFFA4" value="<%= lnParam.getE02PRFFA4() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFX4" value="<%= lnParam.getE02PRFFX4() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFM4" value="<%= lnParam.getE02PRFFM4() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFIV4">
	                <option value=" " <% if (!(lnParam.getE02PRFIV4().equals("Y") ||lnParam.getE02PRFIV4().equals("N"))) out.print("selected"); %>></option>
	                <option value="Y" <% if (lnParam.getE02PRFIV4().equals("Y")) out.print("selected"); %>>Si</option>
	                <option value="N" <% if (lnParam.getE02PRFIV4().equals("N")) out.print("selected"); %>>No</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
 				<input type="text" name="E02PRFAC4" value="<%= lnParam.getE02PRFAC4() %>" size="4" maxlength="3" oncontextmenu="showPopUp(currencyHelp,this.name,'','','','','')">              
 			  </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E02PRFGL4" size="18" maxlength="17" value="<%= lnParam.getE02PRFGL4()%>" oncontextmenu="showPopUp(ledgerHelp,this.name,'','','','','')">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="D02GL4DSC" size="35" maxlength="35" value="<%= lnParam.getD02GL4DSC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFFT5">
	                <option value=" " <% if (!(lnParam.getE02PRFFT5().equals("F") ||lnParam.getE02PRFFT5().equals("%"))) out.print("selected"); %>></option>
	                <option value="F" <% if (lnParam.getE02PRFFT5().equals("F")) out.print("selected"); %>>Monto Fijo</option>
	                <option value="%" <% if (lnParam.getE02PRFFT5().equals("%")) out.print("selected"); %>>Porcentaje</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
              	<input type="text" name="E02PRFFA5" value="<%= lnParam.getE02PRFFA5() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFX5" value="<%= lnParam.getE02PRFFX5() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFM5" value="<%= lnParam.getE02PRFFM5() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFIV5">
	                <option value=" " <% if (!(lnParam.getE02PRFIV5().equals("Y") ||lnParam.getE02PRFIV5().equals("N"))) out.print("selected"); %>></option>
	                <option value="Y" <% if (lnParam.getE02PRFIV5().equals("Y")) out.print("selected"); %>>Si</option>
	                <option value="N" <% if (lnParam.getE02PRFIV5().equals("N")) out.print("selected"); %>>No</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
 				<input type="text" name="E02PRFAC5" value="<%= lnParam.getE02PRFAC5() %>" size="4" maxlength="3" oncontextmenu="showPopUp(currencyHelp,this.name,'','','','','')">              
 			  </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E02PRFGL5" size="18" maxlength="17" value="<%= lnParam.getE02PRFGL5()%>" oncontextmenu="showPopUp(ledgerHelp,this.name,'','','','','')">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="D02GL5DSC" size="35" maxlength="35" value="<%= lnParam.getD02GL5DSC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFFT6">
	                <option value=" " <% if (!(lnParam.getE02PRFFT6().equals("F") ||lnParam.getE02PRFFT6().equals("%"))) out.print("selected"); %>></option>
	                <option value="F" <% if (lnParam.getE02PRFFT6().equals("F")) out.print("selected"); %>>Monto Fijo</option>
	                <option value="%" <% if (lnParam.getE02PRFFT6().equals("%")) out.print("selected"); %>>Porcentaje</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
              	<input type="text" name="E02PRFFA6" value="<%= lnParam.getE02PRFFA6() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFX6" value="<%= lnParam.getE02PRFFX6() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFM6" value="<%= lnParam.getE02PRFFM6() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFIV6">
	                <option value=" " <% if (!(lnParam.getE02PRFIV6().equals("Y") ||lnParam.getE02PRFIV6().equals("N"))) out.print("selected"); %>></option>
	                <option value="Y" <% if (lnParam.getE02PRFIV6().equals("Y")) out.print("selected"); %>>Si</option>
	                <option value="N" <% if (lnParam.getE02PRFIV6().equals("N")) out.print("selected"); %>>No</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
 				<input type="text" name="E02PRFAC6" value="<%= lnParam.getE02PRFAC6() %>" size="4" maxlength="3" oncontextmenu="showPopUp(currencyHelp,this.name,'','','','','')">              
 			  </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E02PRFGL6" size="18" maxlength="17" value="<%= lnParam.getE02PRFGL6()%>" oncontextmenu="showPopUp(ledgerHelp,this.name,'','','','','')">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="D02GL6DSC" size="35" maxlength="35" value="<%= lnParam.getD02GL6DSC()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFFT7">
	                <option value=" " <% if (!(lnParam.getE02PRFFT7().equals("F") ||lnParam.getE02PRFFT7().equals("%"))) out.print("selected"); %>></option>
	                <option value="F" <% if (lnParam.getE02PRFFT7().equals("F")) out.print("selected"); %>>Monto Fijo</option>
	                <option value="%" <% if (lnParam.getE02PRFFT7().equals("%")) out.print("selected"); %>>Porcentaje</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
              	<input type="text" name="E02PRFFA7" value="<%= lnParam.getE02PRFFA7() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
              </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFX7" value="<%= lnParam.getE02PRFFX7() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="right"> 
 				<input type="text" name="E02PRFFM7" value="<%= lnParam.getE02PRFFM7() %>" size="15" maxlength="13" onkeypress="enterDecimal()">              
 			  </div>
            </td>
            <td nowrap > 
              <div align="center"> 
	              <select name="E02PRFIV7">
	                <option value=" " <% if (!(lnParam.getE02PRFIV7().equals("Y") ||lnParam.getE02PRFIV7().equals("N"))) out.print("selected"); %>></option>
	                <option value="Y" <% if (lnParam.getE02PRFIV7().equals("Y")) out.print("selected"); %>>Si</option>
	                <option value="N" <% if (lnParam.getE02PRFIV7().equals("N")) out.print("selected"); %>>No</option>        
	              </select>                
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
 				<input type="text" name="E02PRFAC7" value="<%= lnParam.getE02PRFAC7() %>" size="4" maxlength="3" oncontextmenu="showPopUp(currencyHelp,this.name,'','','','','')">              
 			  </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E02PRFGL7" size="18" maxlength="17" value="<%= lnParam.getE02PRFGL7()%>" oncontextmenu="showPopUp(ledgerHelp,this.name,'','','','','')">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="D02GL7DSC" size="35" maxlength="35" value="<%= lnParam.getD02GL7DSC()%>" >
              </div>
            </td>
          </tr>
         <tr id="trclear"> 
            <td nowrap colspan="7"> Cargo Fijo (entero), Porcentaje (3 decimales) 
            </td>
          </tr>
     </table>
    </td>
    </tr>
  </table>
  <p align="center"> 
        <input id="EIBSBTN" type=submit name="Submit" value="Ingresar">
  </p>
 
  </form>
</body>
</html>

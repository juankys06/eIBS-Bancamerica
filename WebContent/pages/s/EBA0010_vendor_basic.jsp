<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head>
<title>Customer Bills & Accounts Payable Vendors Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="vendor" class="datapro.eibs.beans.EBA001001Message"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="javascript">

	function changePaymentVia(value) {
		if (value == "A") {
			document.getElementById("ABA_NAME").disabled = false; 
			<% if (!vendor.getH01FLGWK1().equals("7")) { %>
				document.getElementById("ABA_NUMBER").disabled = false; 
				document.getElementById("HELP").style.visibility = "visible"; 
			<% } %>	
			document.getElementById("ACCOUNT").value = ""; 
			document.getElementById("ACCOUNT").disabled = true; 
		} else {
			if (value == "C") {
				document.getElementById("ABA_NAME").value = ""; 
				<% if (!vendor.getH01FLGWK1().equals("7")) { %>
						document.getElementById("ABA_NUMBER").value = ""; 
						document.getElementById("ABA_NUMBER").disabled = true; 
						document.getElementById("HELP").style.visibility = "hidden"; 
				<% } %>		
				document.getElementById("ABA_NAME").disabled = true; 
				document.getElementById("ACCOUNT").disabled = false; 
			} else {
				document.getElementById("ABA_NAME").value = ""; 
				<% if (!vendor.getH01FLGWK1().equals("7")) { %>
						document.getElementById("ABA_NUMBER").value = ""; 
						document.getElementById("ABA_NUMBER").disabled = true; 
						document.getElementById("HELP").style.visibility = "hidden"; 
				<% } %>		
				document.getElementById("ACCOUNT").value = ""; 
				document.getElementById("ABA_NAME").disabled = true; 
				document.getElementById("ACCOUNT").disabled = true; 
			}
		}		
	}

</SCRIPT>

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

<h3 align="center">Mantenimiento Pagos y Recibos de Clientes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="vendor_basic, EBA0010" ></h3>
<hr size="4">
 <FORM name="form" METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEBA0010" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  </p>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
			
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Numero de Proveedor :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="hidden" name="E01BAVNUM" size="10" maxlength="9" value="<%= vendor.getE01BAVNUM().trim()%>">
              <%= vendor.getE01BAVNUM().trim()%>
             </td>  
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Nombre Proveedor :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01BAVNM1" size="40" maxlength="35" value="<%= vendor.getE01BAVNM1().trim()%>">
               
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Dirección de Proveedor :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01BAVNM2" size="40" maxlength="35" value="<%= vendor.getE01BAVNM2().trim()%>">
               
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01BAVNM3" size="40" maxlength="35" value="<%= vendor.getE01BAVNM3().trim()%>">
              
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <input type="text" name="E01BAVNM4" size="40" maxlength="35" value="<%= vendor.getE01BAVNM4().trim()%>">
              
            </td>
            <td nowrap > 
              <div align="right">Tipo de Proveedor :</div>
            </td>
	    	<td nowrap >
              <select name="E01BAVTYP" >
                <option value=" " <% if (!(vendor.getE01BAVTYP().equals("B")||vendor.getE01BAVTYP().equals("I") || vendor.getE01BAVTYP().equals("S"))) out.print("selected"); %>>     </option>
                <option value="B" <% if (vendor.getE01BAVTYP().equals("B")) out.print("selected"); %>>Proveedor del Banco</option>
                <option value="I" <% if (vendor.getE01BAVTYP().equals("I")) out.print("selected"); %>>Proveedor de Internet</option>
                <option value="S" <% if (vendor.getE01BAVTYP().equals("S")) out.print("selected"); %>>Proveedor de Servicios </option>
              </select>									
			</td>   
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <input type="text" name="E01BAVNM5" size="40" maxlength="35" value="<%= vendor.getE01BAVNM5().trim()%>">             
            </td>
            <td nowrap > 
              <div align="right">Clasificacion :</div>
            </td>
				<td nowrap >
					<input type="text" name="E01BAVCLS" size="6" maxlength="4" value="<%= vendor.getE01BAVCLS().trim()%>"> 
					<a href="javascript:GetCodeCNOFC('E01BAVCLS','BA')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a> 
				</td>            
          </tr>
          <% if (!vendor.getH01FLGWK1().equals("7")) { %>
	          <tr id="trdark"> 
	            <td nowrap > 
	              <div align="right">Estado :</div>
	            </td>
					<td nowrap >
						<input type="text" name="E01BAVSTE" size="3" maxlength="2" value="<%= vendor.getE01BAVSTE().trim()%>"> 
						<a href="javascript:GetCodeCNOFC('E01BAVSTE','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a> 
					</td>
	             <td nowrap > 
	              <div align="right">Zip Code :</div>
	            </td>
				<td nowrap  >
					<input type="text" name="E01BAVZIP" size="12" maxlength="10" value="<%= vendor.getE01BAVZIP().trim()%>">
				</td>
			  </tr>
		  <% } %>
          <tr id="trclear"> 
          <% if (!vendor.getH01FLGWK1().equals("7")) { %>
	            <td nowrap> 
	              <div align="right">Nombre Corto :</div>
	            </td>
	            <td nowrap> 
	              <input type="text" name="E01BAVSNM" size="25" maxlength="15" value="<%= vendor.getE01BAVSNM().trim()%>">
	            </td>
		  <% } else { %>
            	<td nowrap></td> 
            	<td nowrap></td> 
		  <% } %>
             <td nowrap> 
              <div align="right">Forma de Pago :</div>
            </td>
            <td nowrap> 
              <select name="E01BAVPMT" id="PMTVIA" onchange="changePaymentVia(this.options[this.selectedIndex].value)">
                <option value=" " <% if (!(vendor.getE01BAVPMT().equals("T")||vendor.getE01BAVPMT().equals("M") || vendor.getE01BAVPMT().equals("A")||vendor.getE01BAVPMT().equals("C"))) out.print("selected"); %>></option>
                <option value="T" <% if (vendor.getE01BAVPMT().equals("T")) out.print("selected"); %>>Cheque</option>
                <option value="M" <% if (vendor.getE01BAVPMT().equals("M")) out.print("selected"); %>>Multiples Cheques</option>
                <option value="A" <% if (vendor.getE01BAVPMT().equals("A")) out.print("selected"); %>>Pago en ACH </option>
                <option value="C" <% if (vendor.getE01BAVPMT().equals("C")) out.print("selected"); %>>Acredita Cuenta de Proveedor</option>
              </select>
            </td>
          </tr>
          <% if (!vendor.getH01FLGWK1().equals("7")) { %>
	          <tr id="trdark"> 
	            <td nowrap> 
	              <div align="right">Tax W/H Type :</div>
	            </td>
	            <td nowrap> 
	              <select name="E01BAVWTH">
	                <option value=" " <% if (!(vendor.getE01BAVWTH().equals("W")||vendor.getE01BAVWTH().equals("F") || vendor.getE01BAVWTH().equals("B")||vendor.getE01BAVWTH().equals("N"))) out.print("selected"); %>></option>
	                <option value="W" <% if (vendor.getE01BAVWTH().equals("W")) out.print("selected"); %>>Withholding</option>
	                <option value="F" <% if (vendor.getE01BAVWTH().equals("F")) out.print("selected"); %>>Forma 1099</option>
	                <option value="B" <% if (vendor.getE01BAVWTH().equals("B")) out.print("selected"); %>>Ambas</option>
	                <option value="N" <% if (vendor.getE01BAVWTH().equals("N")) out.print("selected"); %>>Ninguna</option>
	              </select>
	            </td>
	            <td nowrap> 
	              <div align="right">Tax ID or SS :</div>
	            </td>
	            <td nowrap> 
	              <input type="text" name="E01BAVVID" size="17" maxlength="15" value="<%= vendor.getE01BAVVID().trim()%>" >
	            </td>
	          </tr>        
		  <% } %>
          <tr id="trclear"> 
          <% if (!vendor.getH01FLGWK1().equals("7")) { %>
	            <td nowrap> 
	              <div align="right">Número ABA Proveedor :</div>
	            </td>
	            <td nowrap> 
	              <input type="text" name="E01BAVABA" id="ABA_NUMBER" size="11" maxlength="10" value="<%= vendor.getE01BAVABA().trim()%>" >
	              <a href="javascript:GetBanksAba('E01BAVABA','','D')"><img id="HELP" src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a> 
	            </td>
		  <% } else { %>
            	<td nowrap></td> 
            	<td nowrap></td> 
		  <% } %>
            <td nowrap> 
              <div align="right">Nombre ABA  :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01BAVSNR" id="ABA_NAME" size="20" maxlength="18" value="<%= vendor.getE01BAVSNR().trim()%>" >
            </td>
          </tr>        
           <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Remarks :</div>
            </td>
            <td nowrap  > 
              <input type="text" name="E01BAVRK1" size="22" maxlength="20" value="<%= vendor.getE01BAVRK1().trim()%>" >
            </td>
           <td nowrap> 
              <div align="right">Cuenta Proveedor :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01BAVVAN" id="ACCOUNT" size="22" maxlength="20" value="<%= vendor.getE01BAVVAN().trim()%>" >
            </td>            
          </tr>        
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <input type="text" name="E01BAVRK2" size="22" maxlength="20" value="<%= vendor.getE01BAVRK2().trim()%>" >
            </td>
            <td nowrap> 
              <div align="right">Monto Pagado YTD :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01BAVPYT" size="17" maxlength="15" value="<%= vendor.getE01BAVPYT().trim()%>" readonly>
            </td>            
          </tr>        
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap  > 
              <input type="text" name="E01BAVRK3" size="22" maxlength="20" value="<%= vendor.getE01BAVRK3().trim()%>" >
            </td>
             <td nowrap> 
              <div align="right">Amount Withheld YTD :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01BAVPLT" size="17" maxlength="15" value="<%= vendor.getE01BAVPLT().trim()%>" readonly>
            </td>
          </tr>
                 
        </table>
      </td>
    </tr>
  </table>
 
  <br>
   <p align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Aceptar">
  </p>
</form>

<SCRIPT Language="javascript">
		document.getElementById("ABA_NAME").disabled = true; 
		<% if (!vendor.getH01FLGWK1().equals("7")) { %>
			document.getElementById("ABA_NUMBER").disabled = true; 
			document.getElementById("HELP").style.visibility = "hidden"; 
		<% } %>	
		document.getElementById("ACCOUNT").disabled = true; 
</SCRIPT>

</body>
</html>


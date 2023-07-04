<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Investments Sales/Purchase Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="cpMant" class="datapro.eibs.beans.EDL010801Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>
<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  
  
 String sTitle = "";
 if (cpMant.getE01DLIOPT().equals("0"))
 	sTitle = "Compra Adicional";
 else if (cpMant.getE01DLIOPT().equals("1"))
 	sTitle = "Venta al vencimiento";	
 else if (cpMant.getE01DLIOPT().equals("2"))
 	sTitle = "Re-Venta";	
 else if (cpMant.getE01DLIOPT().equals("3"))
 	sTitle = "Re-compra";	
 else if (cpMant.getE01DLIOPT().equals("4"))
 	sTitle = "Liberación";	

%>

<h3 align="center">Mantenimiento <%=sTitle%> <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cp_maint.jsp,EDL0108"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0108" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <INPUT TYPE=HIDDEN NAME="E01DLIOPT" VALUE="<%= cpMant.getE01DLIOPT().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="80%" > 
              <div align="left"> 
                <input type="text" name="E01DEAACC" size="15" maxlength="13" value="<%= cpMant.getE01DEAACC().trim()%>" readonly>
               </div>
            </td>
		  </tr>
          <tr id="trdark">            
            <td nowrap width="20%" > 
              <div align="right"><b>Contrapartida :</b> </div>
            </td>
            <td nowrap width="80%"> 
              <div align="left"> 
                <input type="text" name="E01DLICUS" size="11" maxlength="10" value="<%= cpMant.getE01DLICUS().trim()%>" readonly>
                <input type="text" name="E01DLINAM" size="50" maxlength="45" value="<%= cpMant.getE01DLINAM().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%"> 
              <div align="right"><b>No. Cliente :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01DEACUN" size="11" maxlength="10" value="<%= cpMant.getE01DEACUN().trim()%>" readonly>
                <input type="text" name="E01CUSNA1" size="50" maxlength="45" value="<%= cpMant.getE01CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información Básica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Proceso/Orden :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DLITD1" size="2" maxlength="2" value="<%= cpMant.getE01DLITD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DLITD2" size="2" maxlength="2" value="<%= cpMant.getE01DLITD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DLITD3" size="2" maxlength="2" value="<%= cpMant.getE01DLITD3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01DLITD1,document.forms[0].E01DLITD2,document.forms[0].E01DLITD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DLISD1" size="2" maxlength="2" value="<%= cpMant.getE01DLISD1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DLISD2" size="2" maxlength="2" value="<%= cpMant.getE01DLISD2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01DLISD3" size="2" maxlength="2" value="<%= cpMant.getE01DLISD3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01DLISD1,document.forms[0].E01DLISD2,document.forms[0].E01DLISD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Nominal :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01DLIFCV" size="15" maxlength="13" value="<%= cpMant.getE01DLIFCV().trim()%>" onkeypress="enterDecimal()">
               <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Precio de Venta/Compra :</div>
            </td>
            <td nowrap width="27%"> 
 				<input type="text" name="E01DLIPRC" size="15" maxlength="13" value="<%= cpMant.getE01DLIPRC().trim()%>" onkeypress="enterSignDecimal()">
               <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" height="37"> 
              <div align="right">Interés Acumulado :</div>
            </td>
            <td nowrap width="23%" height="37"> 
				<input type="text" name="E01DLIINT" size="15" maxlength="13" value="<%= cpMant.getE01DLIINT().trim()%>" onkeypress="enterDecimal()">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
            </td>
            <td nowrap width="25%" height="37"> 
              <div align="right">Saldo Total :</div>
            </td>
            <td nowrap width="27%" height="37">
              <input type="text" name="E01DLIBAP" size="15" maxlength="13" value="<%= cpMant.getE01DLIBAP().trim()%>" onkeypress="enterDecimal()">
                        
            </td>
          </tr>                  
        </table>
      </td>
    </tr>
  </table>  
  <h4> Cuenta de Pago</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Banco :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01DLIOBN" size="2" maxlength="2" value="<%= cpMant.getE01DLIOBN().trim()%>" onkeypress="enterInteger()">           
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Agencia :</div>
            </td>
            <td nowrap width="27%"> 
				<input type="text" name="E01DLIOBR" size="3" maxlength="3"value="<%= cpMant.getE01DLIOBN().trim()%>" >
				<a href="javascript:GetBranch('E01DLIOBR','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0"  ></a>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="23%"> 
				<input type="text" name="E01DLIOCY" size="3" maxlength="3" value="<%= cpMant.getE01DLIOBN().trim()%>" >
				<a href="javascript:GetCurrency('E01DLIOCY', '')"> <img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"  ></a>				
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" >
            </td>
            <td nowrap width="25%"> 
              <div align="right">Cuenta :</div>
            </td>
            <td nowrap width="27%"> 
				<input type="text" name="E01DLIOAC" size="17" maxlength="16" value="<%= cpMant.getE01DLIOAC().trim()%>" onkeypress="enterInteger()">
              	<a href="javascript:GetLedger('E01DLIOAC',document.forms[0].E01DLIOBN.value,document.forms[0].E01DLIOCY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0" ></a> 
              	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>    
	<% 
		if (error.getERWRNG().equals("Y")) { 
			error.setERWRNG(" ");
	%>
			<h4 style="text-align:center"><input type="checkbox" name="H01FLGWK2" value="A">
      		Aceptar con Advertencias</h4>
	<% 
		} 
	%>       
 
   <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
  </div>
  </form>
</body>
</html>

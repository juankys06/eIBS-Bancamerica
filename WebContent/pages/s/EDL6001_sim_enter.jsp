<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Cotizador de Préstamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "lnBasic" class= "datapro.eibs.beans.EDL600101Message"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

 function CheckSubmit()
{
   document.forms[0].submit();
}


</SCRIPT>


</head>



<body bgcolor="#FFFFFF">

<h3 align="center">Cotizador de Préstamos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sim_enter,EDL6001"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL6001">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">


          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Banco :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01CDBANK" size="3" maxlength="2" value="<%= lnBasic.getE01CDBANK().trim()%>"></td>
            <td nowrap width="25%" > 
              <div align="right">Código de Empresa :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01ENTCDE" size="8" maxlength="6" value="<%= lnBasic.getE01ENTCDE().trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E01ENTCDE',' ','2D')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></a> 

<%--              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0"  > --%>
            </td>
          </tr>


          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Producto :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAPRO" size="5" maxlength="4" value="<%= lnBasic.getE01DEAPRO().trim()%>">
	            <a href="javascript:GetProduct('E01DEAPRO','10','01','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></a> 
            </td>
            <td nowrap width="25%" > 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEACUN" size="10" maxlength="9" value="<%= lnBasic.getE01DEACUN().trim()%>">
                <a href="javascript:GetCustomerDescId('E01DEACUN','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>
            </td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Nacimiento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01FNACO1" size="2" maxlength="2" value="<%= lnBasic.getE01FNACO1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01FNACO2" size="2" maxlength="2" value="<%= lnBasic.getE01FNACO2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01FNACO3" size="2" maxlength="2" value="<%= lnBasic.getE01FNACO3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01FNACO1,document.forms[0].E01FNACO2,document.forms[0].E01FNACO3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" border="0"></a> 
            <td nowrap width="25%" > 
              <div align="right">Edad :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01EDADTI" size="3" maxlength="2" value="<%= lnBasic.getE01EDADTI().trim()%>" onKeypress="enterInteger()">
            </td>
          </tr>


          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Titulares :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01NTITUL" size="3" maxlength="2" value="<%= lnBasic.getE01NTITUL().trim()%>">
            </td>
            <td nowrap width="25%" > 
              <div align="right">Sexo : </div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01CLISEX" size="3" maxlength="2" value="<%= lnBasic.getE01CLISEX().trim()%>">
            </td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Tipo de Calculo de Interes :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01INFECI" size="15" maxlength="15" value="<%= lnBasic.getE01INFECI().trim()%>" onKeypress="enterDecimal()"></td>
            <td nowrap width="25%" > 
              <div align="right">Tasa :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01TASAPR" size="12" maxlength="10" value="<%= lnBasic.getE01TASAPR().trim()%>" onKeypress="enterRate()">
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Monto del Préstamo :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01MONPRE" size="15" maxlength="15" value="<%= lnBasic.getE01MONPRE().trim()%>" onKeypress="enterDecimal()"></td>
            <td nowrap width="25%" > 
              <div align="right">Tasa Efectiva :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01EFERTE" size="12" maxlength="10" value="<%= lnBasic.getE01EFERTE().trim()%>" onKeypress="enterRate()" >
            </td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Gastos de Cierre :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01GASCIE" size="15" maxlength="15" value="<%= lnBasic.getE01GASCIE().trim()%>" onKeypress="enterDecimal()"></td>
            <td nowrap width="25%" > 
              <div align="right">Número de Pagos :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01NCUOTA" size="4" maxlength="3" value="<%= lnBasic.getE01NCUOTA().trim()%>" onKeypress="enterInteger()">
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Servicio de Descuento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01SRVDES" size="15" maxlength="15" value="<%= lnBasic.getE01SRVDES().trim()%>" onKeypress="enterDecimal()"></td>
            <td nowrap width="25%" > 
              <div align="right">Plazo Propuesto :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01PLAZOP" size="4" maxlength="3" value="<%= lnBasic.getE01PLAZOP().trim()%>" onKeypress="enterInteger()">
            </td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right"> Aplica Pago FECI: </div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAWHF" size="3" maxlength="1" value="<%= lnBasic.getE01DEAWHF().trim()%>" onKeypress="enterInteger()"> 
            </td>
            <td nowrap width="25%" > 
              <div align="right">Fecha de Primer Pago :</div>
            </td>
            <td nowrap width="27%" > 
			  <input type="text" name="E01PPAGO1" size="2" maxlength="2" value="<%= lnBasic.getE01PPAGO1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01PPAGO2" size="2" maxlength="2" value="<%= lnBasic.getE01PPAGO2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01PPAGO3" size="2" maxlength="2" value="<%= lnBasic.getE01PPAGO3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01PPAGO1,document.forms[0].E01PPAGO2,document.forms[0].E01PPAGO3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" border="0"></a>
			</td>
              
          </tr>

          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01MATDT1" size="2" maxlength="2" value="<%= lnBasic.getE01MATDT1().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01MATDT2" size="2" maxlength="2" value="<%= lnBasic.getE01MATDT2().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01MATDT3" size="2" maxlength="2" value="<%= lnBasic.getE01MATDT3().trim()%>" onkeypress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01MATDT1,document.forms[0].E01MATDT2,document.forms[0].E01MATDT3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="ayuda" border="0"></a> </td>
            <td nowrap width="25%" >
				<div align="right">Comisiones :</div> 
            </td>
            <td nowrap width="27%" > 
				<input type="text" name="E01COMISI" size="15" maxlength="15" value="<%= lnBasic.getE01COMISI().trim()%>" onKeypress="enterDecimal()">
			</td>              
          </tr>


          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Bruto a Recibir :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01PAGTOT" size="15" maxlength="15" value="<%= lnBasic.getE01PAGTOT().trim()%>" onKeypress="enterDecimal()"></td>
            <td nowrap width="25%" > 
              <div align="right">Periodo de Gracia :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01GRACIA" size="4" maxlength="3" value="<%= lnBasic.getE01GRACIA().trim()%>" onKeypress="enterInteger()"></td>
          </tr>


          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Total (Total de Cuotas) :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01VLNETO" size="15" maxlength="15" value="<%= lnBasic.getE01VLNETO().trim()%>" onKeypress="enterDecimal()"></td>
            <td nowrap width="25%" > 
              <div align="right">Mes de No Pago :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01NOPAGO" size="3" maxlength="2" value="<%= lnBasic.getE01NOPAGO().trim()%>" onKeypress="enterInteger()"></td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Desglose de Mensualidad</div>
            </td>
            <td nowrap width="23%" > 
              <div align="right"> </div>
            </td>
            <td nowrap width="25%" > 
              <div align="right"> </div>
            </td>
            <td nowrap width="27%" > 
              <div align="right"> </div>
            </td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Capital e Intereses:</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01PAGPRI" size="15" maxlength="15" value="<%= lnBasic.getE01PAGPRI().trim()%>" onKeypress="enterDecimal()"></td>
            <td nowrap width="25%" > 
              <div align="right">Seguro de Vida :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01PAGSEG" size="15" maxlength="15" value="<%= lnBasic.getE01PAGSEG().trim()%>" onKeypress="enterDecimal()"></td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">FECI :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01PAGFEC" size="15" maxlength="15" value="<%= lnBasic.getE01PAGFEC().trim()%>" onKeypress="enterDecimal()"></td>
            <td nowrap width="25%" > 
              <div align="right">Mensualidad Total :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01VCUOTA" size="15" maxlength="15" value="<%= lnBasic.getE01VCUOTA().trim()%>" onKeypress="enterDecimal()"></td>
          </tr>

          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Interes (Primer Pago) :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01PAGINT" size="15" maxlength="15" value="<%= lnBasic.getE01PAGINT().trim()%>" onKeypress="enterDecimal()"></td>
            <td nowrap width="25%" > 

            </td>
            <td nowrap width="27%" > 
          
          </tr>


        </table>
      </td>
    </tr>
  </table>
  

  <div align="center"> 
    <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="CheckSubmit()">
  </div>
     
  
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>

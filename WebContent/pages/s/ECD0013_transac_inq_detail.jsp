<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Consulta de Transacciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgTransac" class= "datapro.eibs.beans.ECD001301Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<h3 align="center">Consulta de Transacciones<BR>Detalle de transacción
<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="transac_inq_detail.jsp, ECD0013"></h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0013" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">

  <h4>Información de la transacción</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
          <TBODY>
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Mensaje :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT type="text" name="E01JRNMEN" size="5" maxlength="4" readonly value="<%= msgTransac.getE01JRNMEN() %>">
			  </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">En Línea :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT type="text" name="E01JRNONL" size="2" maxlength="1" readonly value="<%= msgTransac.getE01JRNONL() %>">
              </DIV>
            </TD>
          </TR>
         <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Número de Tarjeta :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT type="text" name="E01JRNDE2" size="20" maxlength="19" readonly value="<%= msgTransac.getE01JRNDE2() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Código de Proceso :</DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="left">
                 <INPUT type="text" name="E01JRNDE3" size="7" maxlength="6" readonly value="<%= msgTransac.getE01JRNDE3() %>">
              </DIV>
            </TD>
          </TR>  
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Monto de la Transacción :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE4" size="15" maxlength="14" readonly value="<%= msgTransac.getE01JRNDE4() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Monto del Cliente :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
	              <INPUT type="text" name="E01JRNDE6" size="15" maxlength="14" readonly value="<%= msgTransac.getE01JRNDE6() %>">
              </DIV>
            </TD>
          </TR>
         <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Fecha/Hora local :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT type="text" name="E01JRNDE7" size="11" maxlength="10" readonly value="<%= msgTransac.getE01JRNDE7() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Tasa de Concesión :</DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="left">
                 <INPUT type="text" name="E01JRNDE10" size="15" maxlength="14" readonly value="<%= msgTransac.getE01JRNDE10() %>">
              </DIV>
            </TD>
          </TR>  
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Traza :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE11" size="7" maxlength="6" readonly value="<%= msgTransac.getE01JRNDE11() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Hora local :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
	              <INPUT type="text" name="E01JRNDE12" size="7" maxlength="6" readonly value="<%= Util.formatTime(msgTransac.getE01JRNDE12()) %>">
              </DIV>
            </TD>
          </TR>
         <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Fecha local :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT type="text" name="E01JRNDE13" size="5" maxlength="4" readonly value="<%= msgTransac.getE01JRNDE13() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Fecha de Vencimiento :</DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="left">
                 <INPUT type="text" name="E01JRNDE14" size="5" maxlength="4" readonly value="<%= msgTransac.getE01JRNDE14() %>">
              </DIV>
            </TD>
          </TR>  
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Fecha de Captura :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE15" size="5" maxlength="4" readonly value="<%= msgTransac.getE01JRNDE15() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Fecha de Liquidación :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
	              <INPUT type="text" name="E01JRNDE17" size="5" maxlength="4" readonly value="<%= msgTransac.getE01JRNDE17() %>">
              </DIV>
            </TD>
          </TR>
         <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Categoría de Comercio :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT type="text" name="E01JRNDE18" size="5" maxlength="4" readonly value="<%= msgTransac.getE01JRNDE18() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">País :</DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="left">
                 <INPUT type="text" name="E01JRNDE19" size="4" maxlength="3" readonly value="<%= msgTransac.getE01JRNDE19() %>">
              </DIV>
            </TD>
          </TR>  
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">ID Adquiriente  :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE32" size="12" maxlength="11" readonly value="<%= msgTransac.getE01JRNDE32() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Track  1 :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNDE45" size="80" maxlength="79" readonly value="<%= msgTransac.getE01JRNDE45() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">ID de Red :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE33" size="12" maxlength="11" readonly value="<%= msgTransac.getE01JRNDE33() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Track  2 :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNDE35" size="38" maxlength="37" readonly value="<%= msgTransac.getE01JRNDE35() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Número de Referencia   :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE37" size="13" maxlength="12" readonly value="<%= msgTransac.getE01JRNDE37() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Número   de Autorización :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNDE38" size="7" maxlength="6" readonly value="<%= msgTransac.getE01JRNDE38() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Código de Respuesta :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE39" size="3" maxlength="2" readonly value="<%= msgTransac.getE01JRNDE39() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Card Acept. Terminal :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNDE41" size="17" maxlength="16" readonly value="<%= msgTransac.getE01JRNDE41() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Code Card Acept. :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE42" size="16" maxlength="15" readonly value="<%= msgTransac.getE01JRNDE42() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Nombre/Ubicación :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNDE43" size="41" maxlength="40" readonly value="<%= msgTransac.getE01JRNDE43() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Datos de Respuesta CVV2 :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE44" size="12" maxlength="11" readonly value="<%= msgTransac.getE01JRNDE44() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Código   de Comercio :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNDE48" size="13" maxlength="12" readonly value="<%= msgTransac.getE01JRNDE48() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Moneda de la Transacción :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE49" size="4" maxlength="3" readonly value="<%= msgTransac.getE01JRNDE49() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Moneda   del Cliente :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNDE51" size="4" maxlength="3" readonly value="<%= msgTransac.getE01JRNDE51() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">PIN Block :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE52" size="17" maxlength="16" readonly value="<%= msgTransac.getE01JRNDE52() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Llave de Zona :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNDE53" size="3" maxlength="2" readonly value="<%= msgTransac.getE01JRNDE53() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Monto en Efectivo :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE54" size="15" maxlength="14" readonly value="<%= msgTransac.getE01JRNDE54() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Data  de POS :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNDE60" size="17" maxlength="16" readonly value="<%= msgTransac.getE01JRNDE60() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Reverso   :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE61" size="15" maxlength="14" readonly value="<%= msgTransac.getE01JRNDE61() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Data del  Cheque :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNDE63" size="20" maxlength="19" readonly value="<%= msgTransac.getE01JRNDE63() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Tipo de mensaje :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE70" size="4" maxlength="3" readonly value="<%= msgTransac.getE01JRNDE70() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Data Original :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNDE90" size="43" maxlength="42" readonly value="<%= msgTransac.getE01JRNDE90() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Monto   de Reverso :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNDE95" size="15" maxlength="14" readonly value="<%= msgTransac.getE01JRNDE95() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Data Entrada POS? :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRND100" size="12" maxlength="11" readonly value="<%= msgTransac.getE01JRND100() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Cuenta :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRND102" size="21" maxlength="20" readonly value="<%= msgTransac.getE01JRND102() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Cuenta Destino :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRND103" size="21" maxlength="20" readonly value="<%= msgTransac.getE01JRND103() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">PIN   Nuevo :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRND121" size="21" maxlength="20" readonly value="<%= msgTransac.getE01JRND121() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">CVV2   Visa :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRND124" size="10" maxlength="9" readonly value="<%= msgTransac.getE01JRND124() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Saldo   Disponible :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRND125" size="13" maxlength="12" readonly value="<%= msgTransac.getE01JRND125() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Saldo Real :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRND126" size="39" maxlength="38" readonly value="<%= msgTransac.getE01JRND126() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Monto Disponible :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNAVAL" size="18" maxlength="17" readonly value="<%= msgTransac.getE01JRNAVAL() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Monto Total :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNGROS" size="18" maxlength="17" readonly value="<%= msgTransac.getE01JRNGROS() %>">
              </DIV>
            </TD>
          </TR>
			</TBODY>
			</TABLE>
      </td>
    </tr>
    
  </table>
  <h4>Información de respuesta del e-IBS</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
          <TBODY>
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Código de Error :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNERRO" size="3" maxlength="2" readonly value="<%= msgTransac.getE01JRNERRO() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Error IBS :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNH01E" size="5" maxlength="4" readonly value="<%= msgTransac.getE01JRNH01E() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trclear"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Fecha :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNFECH" size="9" maxlength="8" readonly value="<%= msgTransac.getE01JRNFECH() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right">Hora :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
				  <INPUT type="text" name="E01JRNHORA" size="7" maxlength="6" readonly value="<%= msgTransac.getE01JRNHORA() %>">
              </DIV>
            </TD>
          </TR>
          <TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right">Conciliación :</DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">                 
				  <INPUT type="text" name="E01JRNCONC" size="2" maxlength="1" readonly value="<%= msgTransac.getE01JRNCONC() %>">
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right"></DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left">
              </DIV>
            </TD>
          </TR>
			</TBODY>
			</TABLE>
      </td>
    </tr>
    
  </table>
  <br>
  
  </form>
</body>
</html>

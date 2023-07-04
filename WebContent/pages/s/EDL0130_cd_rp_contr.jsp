<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Contrato de Certificados de Deposito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<style type="text/css">
<!--
td {text-align:justify; text-family:Arial;}
-->
</style>

<jsp:useBean id="cdInquiry" class="datapro.eibs.beans.EDL016001Message" scope="session"/>

<jsp:useBean id="cdFinish" class="datapro.eibs.beans.EFT000010Message"  scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos" scope="session"/>


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</HEAD>

<BODY BGCOLOR="#FFFFFF" marginheight="0">
<table width="650" border="0" cellspacing="2" cellpadding="0" height="860">
  <tr valign="top"> 
    <td> 
      <table width="100%" border="0" cellspacing="2" cellpadding="0">
        <tr> 
          <td colspan="2"> 
            <div align="center"><img src="<%=request.getContextPath()%>/images/logo.gif"><br>
              <br>
            </div>
          </td>
        </tr>
        <tr> 
          <td colspan="2"> 
            <div align="center"><font face="Arial, Helvetica, sans-serif" size="2"><b><font size="3">CONTRATO 
              DE DEPOSITO A PLAZO FIJO</font></b></font></div>
          </td>
        </tr>
      </table>
      <br>
      <br>
      <table width="100%" border="0" cellspacing="2" cellpadding="0">
        <tr> 
          <td><font face="Arial, Helvetica, sans-serif" size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Entre 
            los suscritos a saber, <%= cdFinish.getE10BANKNM().trim()%>, entidad 
            bancaria debidamente organizada y existente de conformidad con las 
            leyes de Estados Unidos, quien en adelante se denominar&aacute; EL 
            BANCO, por una parte, y por la otra parte, <%= cdInquiry.getE01CUSNA1().trim()%> 
            con n&uacute;mero de identificaci&oacute;n personal: <%= cdFinish.getE10CUSIDN().trim()%>, 
            actuando en su propio nombre y representaci&oacute;n y quien en adelante 
            se denominar&aacute; EL CLIENTE, hemos convenido en celebrar el presente 
            contrato de dep&oacute;sito a plazo fijo de acuerdo a los siguientes 
            t&eacute;rminos y condiciones:<br>
            <br>
            </font> 
            <hr width="100%" noshade>
            <font face="Arial, Helvetica, sans-serif" size="2"><br>
            <b>Primero:</b> Los datos de identificaci&oacute;n de EL CLIENTE y 
            los t&eacute;rminos del dep&oacute;sito a plazo fijo, son los siguientes:</font></td>
        </tr>
      </table>
      <br>
      <br>
      <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#666666">
        <tr> 
          <td colspan="2"> 
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr> 
                <td width="13%"><font face="Arial, Helvetica, sans-serif" size="2">Monto:</font></td>
                <td width="87%"><font face="Arial, Helvetica, sans-serif" size="2"><%= Util.fcolorCCY(cdFinish.getE10DEAOAM().trim())%></font></td>
              </tr>
            </table>
          </td>
          <td colspan="3"> 
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr> 
                <td width="20%"><font face="Arial, Helvetica, sans-serif" size="2">Sucursal:</font></td>
                <td width="80%"><font face="Arial, Helvetica, sans-serif" size="2"><%= cdFinish.getE10BRNNME().trim()%></font></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
          <td colspan="2">
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr> 
                <td width="20%"><font face="Arial, Helvetica, sans-serif" size="2">Moneda</font></td>
                <td width="80%"><font face="Arial, Helvetica, sans-serif" size="2"><%= cdInquiry.getE01DEACCY().trim()%></font></td>
              </tr>
            </table>
          </td>
          <td colspan="3">&nbsp; </td>
        </tr>
        <tr> 
          <td width="167" height="20" valign="top"> 
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr valign="top"> 
                <td><font face="Arial, Helvetica, sans-serif" size="1">No. de 
                  la Cuenta</font></td>
              </tr>
              <tr> 
                <td><font face="Arial, Helvetica, sans-serif" size="2"><%= cdFinish.getE10DEAACC().trim()%></font></td>
              </tr>
            </table>
          </td>
          <td width="188" height="20" valign="top"> 
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr valign="top"> 
                <td><font face="Arial, Helvetica, sans-serif" size="1">Fecha Efectiva</font></td>
              </tr>
              <tr> 
                <td><font face="Arial, Helvetica, sans-serif" size="2"><%= Util.formatDate(cdFinish.getE10DEAOD1().trim(),cdFinish.getE10DEAOD2().trim(),cdFinish.getE10DEAOD3().trim())%></font></td>
              </tr>
            </table>
          </td>
          <td width="143" height="20" valign="top"> 
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr valign="top"> 
                <td><font face="Arial, Helvetica, sans-serif" size="1">Fecha de 
                  Vencimiento</font></td>
              </tr>
              <tr> 
                <td><font face="Arial, Helvetica, sans-serif" size="2"><%= Util.formatDate(cdFinish.getE10DEAMD1(), cdFinish.getE10DEAMD2(),cdFinish.getE10DEAMD3())%></font></td>
              </tr>
            </table>
          </td>
          <td width="192" height="20" colspan="2" valign="top"> 
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr valign="top"> 
                <td><font face="Arial, Helvetica, sans-serif" size="1">Fecha</font></td>
              </tr>
              <tr> 
                <td><font face="Arial, Helvetica, sans-serif" size="2"><%= Util.formatDate(cdFinish.getE10CNTRD1().trim(),cdFinish.getE10CNTRD2().trim(),cdFinish.getE10CNTRD3().trim())%></font></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
          <td width="167" valign="top"> 
            <table width="99%" border="0" cellspacing="2" cellpadding="0">
              <tr valign="top"> 
                <td><font face="Arial, Helvetica, sans-serif" size="1">Tasa Nominal 
                  de Inter&eacute;s</font></td>
              </tr>
              <tr> 
                <td><font face="Arial, Helvetica, sans-serif" size="2"><%=cdFinish.getE10RATE().trim()%></font></td>
              </tr>
            </table>
          </td>
          <td width="188"> 
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr valign="top"> 
                <td><font face="Arial, Helvetica, sans-serif" size="1">Tasa de 
                  Inter&eacute;s Efectiva</font></td>
              </tr>
              <tr> 
                <td><font face="Arial, Helvetica, sans-serif" size="2"><%= cdFinish.getE10DEASPR().trim()%></font></td>
              </tr>
            </table>
          </td>
          <td width="143"> 
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr valign="top"> 
                <td><font face="Arial, Helvetica, sans-serif" size="1">No. de 
                  D&iacute;as</font></td>
              </tr>
              <tr> 
                <td><font size="2" face="Arial, Helvetica, sans-serif"><%= cdFinish.getE10DEATRM().trim()%> 
                  - <% if(cdFinish.getE10DEATRC().equals("Y")) out.print("Año(s)");
              				else if(cdFinish.getE10DEATRC().equals("M")) out.print("Mes(es)");
							else out.print("Día(s)");%></font></td>
              </tr>
            </table>
          </td>
          <td width="192" colspan="2"> 
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr valign="top"> 
                <td><font face="Arial, Helvetica, sans-serif" size="1">M&eacute;todo 
                  de C&aacute;lculo</font></td>
              </tr>
              <tr> 
                <td><font face="Arial, Helvetica, sans-serif" size="2"><%= cdFinish.getE10DEAICT().trim()%></font></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
          <td colspan="2"> 
            <table width="99%" border="0" cellspacing="2" cellpadding="0">
              <tr valign="top"> 
                <td><font face="Arial, Helvetica, sans-serif" size="1">Nombre</font></td>
              </tr>
              <tr> 
                <td><font face="Arial, Helvetica, sans-serif" size="2"><%= cdInquiry.getE01CUSNA1().trim()%></font></td>
              </tr>
            </table>
          </td>
          <td width="143"> 
            <table width="99%" border="0" cellspacing="2" cellpadding="0">
              <tr valign="top"> 
                <td><font face="Arial, Helvetica, sans-serif" size="1">N&uacute;mero 
                  de Identificaci&oacute;n </font></td>
              </tr>
              <tr> 
                <td><font face="Arial, Helvetica, sans-serif" size="2"><%= cdFinish.getE10CUSIDN().trim()%></font></td>
              </tr>
            </table>
          </td>
          <td width="192" colspan="2"> 
            <table width="99%" border="0" cellspacing="2" cellpadding="0">
              <tr valign="top"> 
                <td><font face="Arial, Helvetica, sans-serif" size="1">Tipo de 
                  Identificaci&oacute;n </font></td>
              </tr>
              <tr> 
                <td><font face="Arial, Helvetica, sans-serif" size="2"><%= cdFinish.getE10CUSTID().trim()%></font></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <br>
      <br>
      <hr width="100%" noshade>
      <br>
      <table width="100%" border="0" cellspacing="2" cellpadding="0">
        <tr>
          <td>
            <table width="100%" cellspacing="2" cellpadding="0">
              <tr> 
                <td width="46%"> 
                  <div align="right"><font face="Arial, Helvetica, sans-serif" size="3"><b>Certificado 
                    de Dep&oacute;sito -</b></font></div>
                </td>
                <td width="54%"><font face="Arial, Helvetica, sans-serif" size="3"><b><%= cdFinish.getE10DSCPRO().trim()%></b></font></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <br>
      <hr width="100%" noshade>
      <br>
      <table width="100%" border="0" cellspacing="2" cellpadding="0">
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><font face="Arial, Helvetica, sans-serif" size="2"><b><font size="3">Instrucciones 
                  de Renovaci&oacute;n al Vencimiento</font></b></font></td>
              </tr>
              <tr> 
                <td width="30"><font face="Arial, Helvetica, sans-serif" size="2"></font></td>
              </tr>
              <tr> 
                <td> 
                  <table width="100%" border="1" cellspacing="2" cellpadding="2" bordercolor="#999999">
                    <tr> 
                      <td><font face="Arial, Helvetica, sans-serif" size="2"><%
 if (cdInquiry.getE01DEAROC().equals("A")) out.print("Principal m&aacute;s Intereses ser&aacute;n renovados  por el mismo per&iacute;odo de tiempo. ");
 else if (cdInquiry.getE01DEAROC().equals("B")) {
 					out.print("Inter&eacute;s ser&aacute; acreditado a " + cdInquiry.getE01TEXTO2().trim() + " que tendr&aacute;n como n&uacute;mero" 
 				  + cdInquiry.getE01DEARAC().trim() + ". El Principal ser&aacute; renovado por el mismo per&iacute;odo de tiempo ");
 				}
 else if (cdInquiry.getE01DEAROC().equals("C")) {
 					out.print("Inter&eacute;s ser&aacute; acreditado  a la cuenta contable n&uacute;mero " + cdInquiry.getE01DEARAC().trim() + ", emitiendo " 
 				  + cdInquiry.getE01TEXTO2().trim() + ". El Principal ser&aacute; renovado por el mismo per&iacute;odo de tiempo ");
 				}
else if (cdInquiry.getE01DEAROC().equals("D")) {
 					out.print("Al vencimiento cancelar el Dep&oacute;sito mas los Intereses acreditando a la cuenta contable n&uacute;mero  " + cdInquiry.getE01DEARAC().trim()
 					+ ", emitiendo " +  cdInquiry.getE01TEXTO2().trim()) ;
 				}
else if (cdInquiry.getE01DEAROC().equals("E")) {
 					out.print("Al vencimiento cancelar el Dep&oacute;sito mas los Intereses acreditando a la cuenta  (Corriente, Ahorro, etc.) n&uacute;mero  " +  cdInquiry.getE01DEARAC().trim() ) ;  
 				}
else if (cdInquiry.getE01DEAROC().equals("F")) {
 					out.print("Al vencimiento renovar el Dep&oacute;sito y los Intereses,  " + cdInquiry.getE01TEXTO2().trim() + "  en  "   +  cdInquiry.getE01DEAROA().trim()  	+  " contra la cuenta (Corriente, Ahorro, etc.) n&uacute;mero "  +  cdInquiry.getE01DEARAC().trim()) ;
 				} 
else if (cdInquiry.getE01DEAROC().equals("G")) {
 					out.print("Al vencimiento renovar el Dep&oacute;sito y los Intereses por otro per&iacute;odo similar, disminuyendo los Intereses en " +  cdInquiry.getE01DEAROA().trim()  +  " y acreditando los mismos en la Cuenta (Corriente, Ahorro, etc.) n&uacute;mero "  
 				  + cdInquiry.getE01DEARAC().trim()  ); 
 				}	
else if (cdInquiry.getE01DEAROC().equals("H")) {
 					out.print("Inter&eacute;s ser&aacute; pagado cada  " + cdInquiry.getE01DEAROY().trim() + "  " + cdInquiry.getE01TEXTO1().trim() + "  a " + cdInquiry.getE01TEXTO2().trim()
					+ " que tendr&aacute;  como n&uacute;mero " + cdInquiry.getE01DEARAC().trim() + ". El Principal ser&aacute; renovado por el mismo per&iacute;odo de tiempo ");
 				}		
 else if (cdInquiry.getE01DEAROC().equals("I")) {
 					out.print("Inter&eacute;s ser&aacute; pagado cada " +  cdInquiry.getE01DEAROY().trim() +  "  " + cdInquiry.getE01TEXTO1().trim() + "  a la cuenta contable n&uacute;mero"  +
 				    cdInquiry.getE01DEARAC().trim() + ", emitiendo un " +  cdInquiry.getE01TEXTO2().trim() + ". El Principal ser&aacute; renovado por el mismo per&iacute;odo de tiempo ");
 				}	
 else if (cdInquiry.getE01DEAROC().equals("J")) {
 					out.print("Inter&eacute;s y Principal ser&aacute;n pagados al Certificado de Dep&oacute;sito n&uacute;mero  " +  cdInquiry.getE01DEARAC().trim()
 					+ " .No hay renovaci&oacute;n en esta opci&oacute;n");  
 				}
 else if (cdInquiry.getE01DEAROC().equals("K")) {
 					out.print("Inter&eacute;s ser&aacute; pagado cada " +  cdInquiry.getE01DEAROY().trim() + "  " +  cdInquiry.getE01TEXTO1().trim()  +  "  a "  +
 				    cdInquiry.getE01TEXTO2().trim() + ",  que tendr&aacute; como n&uacute;mero " +  cdInquiry.getE01DEARAC().trim());  
 				}	
else if (cdInquiry.getE01DEAROC().equals("P")) out.print("El D&eacute;posito no tiene Instrucciones de Renovaci&oacute;n,  ser&aacute; renovado aut&oacute;maticamente despu&eacute;s del Per&iacute;odo  de Espera "); 		
else if (cdInquiry.getE01DEAROC().equals("S")) out.print("Inter&eacute;s y Principal ser&aacute;n pagados basados  en un Plan de Pagos definido previamente "); 		
					
%></font></td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <br>
      <hr width="100%" noshade>
      <br>
      <table width="100%" border="0" cellspacing="2" cellpadding="0">
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="2" cellpadding="0">
              <tr> 
                <td><b><font size="2" face="Arial, Helvetica, sans-serif">Segundo:</font></b><font size="2" face="Arial, Helvetica, sans-serif"> 
                  Acuerdan ambas partes en el presente contrato que los datos 
                  antes mencionados ser&aacute;n para todos los efectos los correctos 
                  para EL BANCO hasta tanto EL CLIENTE no haya notificado por 
                  escrito a EL BANCO sobre cualquier cambio o modificaci&oacute;n 
                  en los mismos.</font></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<br>
<table width="650" border="0" cellspacing="2" cellpadding="0" height="860">
  <tr valign="top"> 
    <td height="20"> 
      <table width="100%" border="0" cellspacing="2" cellpadding="0">
        <tr>
          <td>
            <hr width="100%" noshade>
          </td>
        </tr>
        <tr> 
          <td><font face="Arial, Helvetica, sans-serif" size="2"><b> Tercero:</b> 
            Acuerda ambas partes que el presente contrato se regir&aacute; por 
            el siguiente Reglamento:<br>
            <br>
            </font></td>
        </tr>
        <tr> 
          <td> 
            <div align="center"><font face="Arial, Helvetica, sans-serif" size="2"><b>Reglamento<br>
              </b></font></div>
          </td>
        </tr>
        <tr> 
          <td> 
            <ol>
              <li><font face="Arial, Helvetica, sans-serif" size="2">EL BANCO 
                emite el presente documento como &uacute;nico recibo a favor de 
                EL CLIENTE, se&ntilde;alando las condiciones a que queda sujeto 
                este dep&oacute;sito a plazo fijo.</font></li>
              <li><font face="Arial, Helvetica, sans-serif" size="2">Los dep&oacute;sitos 
                a plazo fijo deber&aacute;n pactarse por un plazo no menor de 
                treinta (30) d&iacute;as.</font></li>
              <li><font face="Arial, Helvetica, sans-serif" size="2">Este dep&oacute;sito 
                no es transferible a terceros, salvo mediante aviso previo a EL 
                BANCO y entrega del presente documento para su Cancelaci&oacute;n 
                y la Expedici&oacute;n de un nuevo documento a favor del cesionario.</font></li>
              <li><font face="Arial, Helvetica, sans-serif" size="2">EL BANCO 
                pagar&aacute; el dinero depositado a su vencimiento, m&aacute;s 
                los intereses devengados, de acuerdo con las instrucciones indicadas 
                por EL CLIENTE.</font></li>
              <li><font face="Arial, Helvetica, sans-serif" size="2">El monto 
                de este dep&oacute;sito no podra rebajarse ni retirarse antes 
                de su vencimiento.</font></li>
              <li><font face="Arial, Helvetica, sans-serif" size="2">El dep&oacute;sito 
                a plazo fijo por un periodo igual o mayor a un (1) a&ntilde;o, 
                podr&aacute; incrementarse antes de su fecha de vencimiento, mediante 
                aportes mensuales por un m&iacute;nimo de CIEN DOLARES (US$100.00) 
                y un m&aacute;ximo del 3% del monto inicial.</font></li>
              <li><font face="Arial, Helvetica, sans-serif" size="2">A la fecha 
                de vencimiento del dep&oacute;sito a plazo fijo, si EL BANCO no 
                ha recibido instrucciones por parte de EL CLIENTE, EL BANCO renovar&aacute; 
                autom&aacute;ticamente el dep&oacute;sito a plazo fijo por el 
                mismo per&iacute;odo y aplicar&aacute; la tasa de inter&eacute;s 
                vigente al momento de la renovaci&oacute;n.</font></li>
              <li><font face="Arial, Helvetica, sans-serif" size="2">En caso de 
                fallecimiento de EL CLIENTE, y a&uacute;n estando el dep&oacute;sito 
                a plazo fijo sometido a la renovaci&oacute;n autom&aacute;tica, 
                a su vencimiento, el mismo ser&aacute; colocado en una cuenta 
                especial y devengar&aacute; intereses a la tasa de inter&eacute;s 
                vigente en las cuentas de ahorros, hasta que la autoridad competente 
                expida una orden sobre la disposici&oacute;n de dichos fondos.</font></li>
              <li><font face="Arial, Helvetica, sans-serif" size="2">EL CLIENTE 
                declara que conoce y acepta que le ser&aacute;n aplicables al 
                presente contrato las condiciones generales de negocio establecidas 
                por EL BANCO, con todo lo que no contravengan las disposiciones 
                del presente reglamento.</font></li>
              <li><font face="Arial, Helvetica, sans-serif" size="2">Cualquier 
                cambio o modificaci&oacute;n de las instrucciones dadas por EL 
                CLIENTE relativas a cualquier dep&oacute;sito a plazo fijo deber&aacute; 
                ser comunicada a EL BANCO por escrito y con dos (2) d&iacute;as 
                laborables de anticipaci&oacute;n a la fecha de vencimiento del 
                respectivo dep&oacute;sito a plazo fijo.</font></li>
            </ol>
            <br>
            <br>
          </td>
        </tr>
        <tr> 
          <td><font face="Arial, Helvetica, sans-serif" size="2">Para constancia 
            se firma el presente contrato a los <%= cdFinish.getE10CNTRD1().trim()%> 
            d&iacute;as del mes de <% if((cdFinish.getE10CNTRD2().equals("1"))||(cdFinish.getE10CNTRD2().equals("01"))) out.print("Enero");
              				else if((cdFinish.getE10CNTRD2().equals("2"))||(cdFinish.getE10CNTRD2().equals("02"))) out.print("Febrero");
							else if((cdFinish.getE10CNTRD2().equals("3"))||(cdFinish.getE10CNTRD2().equals("03"))) out.print("Marzo");
							else if((cdFinish.getE10CNTRD2().equals("4"))||(cdFinish.getE10CNTRD2().equals("04"))) out.print("Abril");
							else if((cdFinish.getE10CNTRD2().equals("5"))||(cdFinish.getE10CNTRD2().equals("05"))) out.print("Mayo");
							else if((cdFinish.getE10CNTRD2().equals("6"))||(cdFinish.getE10CNTRD2().equals("06"))) out.print("Junio");
							else if((cdFinish.getE10CNTRD2().equals("7"))||(cdFinish.getE10CNTRD2().equals("07"))) out.print("Julio");
							else if((cdFinish.getE10CNTRD2().equals("8"))||(cdFinish.getE10CNTRD2().equals("08"))) out.print("Agosto");
							else if((cdFinish.getE10CNTRD2().equals("9"))||(cdFinish.getE10CNTRD2().equals("09"))) out.print("Septiembre");
							else if((cdFinish.getE10CNTRD2().equals("10"))) out.print("Octubre");
							else if((cdFinish.getE10CNTRD2().equals("11"))) out.print("Noviembre");
							else if((cdFinish.getE10CNTRD2().equals("12"))) out.print("Diciembre");
							%> del a&ntilde;o 200<%= cdFinish.getE10CNTRD3().trim()%></font></td>
        </tr>
      </table>
      <br>
      <table width="100%">
        <tr> 
          <td width="3%">&nbsp;</td>
          <td width="43%"><br>
            <br>
            <br>
            <br>
            <br>
            <br>
            ____________________________ </td>
          <td width="2%">&nbsp;</td>
          <td width="52%"><br>
            <br>
            <br>
            <br>
            <br>
            <br>
            ____________________________</td>
        </tr>
        <tr> 
          <td width="3%">&nbsp;</td>
          <td width="43%"><font face="Arial, Helvetica, sans-serif" size="1"><b><font size="2">EL 
            CLIENTE:</font></b> <%= cdInquiry.getE01CUSNA1().trim()%></font></td>
          <td width="2%">&nbsp;</td>
          <td width="52%"><font face="Arial, Helvetica, sans-serif" size="1"> 
            <b><font size="2">EL BANCO:</font></b> <%= cdFinish.getE10BANKNM().trim()%></font></td>
        </tr>
        <tr> 
          <td width="3%">&nbsp;</td>
          <td width="43%"><font face="Arial, Helvetica, sans-serif" size="1">No. 
            Identificaci&oacute;n</font><font face="Arial, Helvetica, sans-serif" size="2">: 
            <%= cdFinish.getE10CUSIDN().trim()%></font></td>
          <td width="2%">&nbsp;</td>
          <td width="52%">&nbsp; </td>
        </tr>
      </table>
      <br>
    </td>
  </tr>
</table>
<br>
</BODY>
</HTML>

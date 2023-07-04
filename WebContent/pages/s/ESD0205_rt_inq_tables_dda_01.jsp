<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Cargos por Servicios</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id= "charges" class= "datapro.eibs.beans.ESD020502Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


 
</head>

<body bgcolor="#FFFFFF">

<SCRIPT Language="Javascript">

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
%>

</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   out.println("<SCRIPT> initMenu(); </SCRIPT>");
 
 %> 
 
 
<h3 align="center">Cargos por Servicios y Tasas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ESD0205_rt_inq_tables_dda_01, ESD0205" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0205I">

    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    
  <h4>Información Básica</h4>
  <table class="tableinfo">
          <tr > 
        <td nowrap> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >

          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">N&uacute;mero de Tabla :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02RTETAR" size="3" maxlength="2" value="<%= charges.getE02RTETAR()%>" readonly>
                <input type="text" name="E02RTEDSC" size="35" maxlength="35" value="<%= charges.getE02RTEDSC()%>" readonly>
              </div>
            </td>
            <td nowrap>
              <div align="right">Producto :</div>
            </td>
            <td nowrap>
              <input type="text" name="E02RTEATY" size="5" maxlength="4" value="<%= charges.getE02RTEATY()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Moneda Comisi&oacute;n :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="E02RTEFCY" size="4" maxlength="3" value="<%= charges.getE02RTEFCY()%>" readonly>
               </td>
            <td nowrap width="29%"> 
              <div align="right">L&iacute;mite Balance :</div>
            </td>
            <td nowrap width="31%"> 
              <input type="text" name="E02RTEMBM" size="10" maxlength="9" value="<%= charges.getE02RTEMBM()%>" readonly>
            </td>
          </tr>
		  <%if(!charges.getE02RTECUN().equals("0")){%>
          <tr id="trdark"> 
            <td nowrap width="20%" align=right> 
              Cliente :
            </td>
            <td nowrap colspan=3>  
               
              <input type="text" name="E02RTECUN" size="4" maxlength="2" value="<%= charges.getE02RTECUN()%>" readonly>
            </td>
          </tr>
          <%}%>

        </table>
        </td>
      </tr>


</table>
  <h4>Cargos Por Servicios</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo por Envio de Correo :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                <input type="text" name="E02RTESTC" size="10" maxlength="9" value="<%= charges.getE02RTESTC()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right"> Tipo de Balance :</div>
            </td>
            <td nowrap width="31%"> 
              <div align="left"> 
                <select name="E02RTEBT1" disabled>
                  <option value=" " <% if (!(charges.getE02RTEBT1().equals("NT") ||charges.getE02RTEBT1().equals("GR")||charges.getE02RTEBT1().equals("AN")||charges.getE02RTEBT1().equals("AG"))) out.print("selected"); %>></option>
                  <option value="NT" <%if (charges.getE02RTEBT1().equals("NT")) { out.print("selected"); }%>>Balance 
                  Neto</option>
                  <option value="GR" <%if (charges.getE02RTEBT1().equals("GR")) { out.print("selected"); }%>>Balance 
                  Global</option>
                  <option value="AN" <%if (charges.getE02RTEBT1().equals("AN")) { out.print("selected"); }%>>Promedio 
                  Neto</option>
                  <option value="AG" <%if (charges.getE02RTEBT1().equals("AG")) { out.print("selected"); }%>>Promedio 
                  Global</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%" height="34"> 
              <div align="right">Cargo Saldo M&iacute;nimo en Libros :</div>
            </td>
            <td nowrap width="18%" height="34"> 
              <div align="left"> 
                <input type="text" name="E02RTECH1" size="10" maxlength="9" value="<%= charges.getE02RTECH1()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%" height="34"> 
              <div align="right">Saldo M&iacute;nimo en Libros :</div>
            </td>
            <td nowrap width="31%" height="34" > 
              <div align="left"> 
                <input type="text" name="E02RTEBL1" size="15" maxlength="15" value="<%= charges.getE02RTEBL1()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo Saldo M&iacute;nimo Disponible :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                <input type="text" name="E02RTECH2" size="10" maxlength="9" value="<%= charges.getE02RTECH2()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Saldo M&iacute;nimo Disponible :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEBL2" size="15" maxlength="15" value="<%= charges.getE02RTEBL2()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo Saldo M&iacute;n. Prom. Libros :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTECH3" size="10" maxlength="9" value="<%= charges.getE02RTECH3()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Saldo M&iacute;n. Prom. Libros :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEBL3" size="15" maxlength="15" value="<%= charges.getE02RTEBL3()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo Saldo M&iacute;n. Prom. Disp. :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTECH4" size="10" maxlength="9" value="<%= charges.getE02RTECH4()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Saldo M&iacute;n. Prom. Disp. :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEBL4" size="15" maxlength="15" value="<%= charges.getE02RTEBL4()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo de Timbres x Cheque :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTEMKC" size="10" maxlength="9" value="<%= charges.getE02RTEMKC()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">L&iacute;mite No.de Cheques Sin Cargo de Timbre :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEMKK" size="4" maxlength="3" value="<%= charges.getE02RTEMKK()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo x Inactivas Tipo Uno :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTEICH" size="10" maxlength="9" value="<%= charges.getE02RTEICH()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Nro. D&iacute;as Inactivas Tipo Uno :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEIDL" size="5" maxlength="4" value="<%= charges.getE02RTEIDL()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo x Inactivas Tipo Dos :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTEDCH" size="10" maxlength="9" value="<%= charges.getE02RTEDCH()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Nro. D&iacute;as Inactivas Tipo Dos :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEDDL" size="5" maxlength="4" value="<%= charges.getE02RTEDDL()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo M&aacute;x. x Cheque N Local :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTENCX" size="10" maxlength="9" value="<%= charges.getE02RTENCX()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Comisi&oacute;n Cheques N. Locales :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTENCP" size="4" maxlength="3" value="<%= charges.getE02RTENCP()%>" readonly>
                %</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo M&iacute;n. x Cheque N Local :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTENCM" size="10" maxlength="9" value="<%= charges.getE02RTENCM()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Comisi&oacute;n Anual :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEYRC" size="15" maxlength="15" value="<%= charges.getE02RTEYRC()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo Aprobaci&oacute;n Al Descubierto :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTENSC" size="10" maxlength="9" value="<%= charges.getE02RTENSC()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Cargo Aprobaci&oacute;n Fondos en Transito :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEUNC" size="10" maxlength="9" value="<%= charges.getE02RTEUNC()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Comisi&oacute;n de Apertura :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTEOPC" size="10" maxlength="9" value="<%= charges.getE02RTEOPC()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Cargo x Cheques Depositados :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEPDC" size="10" maxlength="9" value="<%= charges.getE02RTEPDC()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Suspensi&oacute;n de Pagos Cheques :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTESPC" size="10" maxlength="9" value="<%= charges.getE02RTESPC()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Cargo x Retenci&oacute;n de Correo :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEHCH" size="10" maxlength="9" value="<%= charges.getE02RTEHCH()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">D&iacute;as Retener x Confirmaci&oacute;n :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTENDH" size="3" maxlength="2" value="<%= charges.getE02RTENDH()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Balance M&iacute;nimo Confirmaci&oacute;n :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEMAC" size="10" maxlength="9" value="<%= charges.getE02RTEMAC()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Importe M&iacute;nimo de Cheques :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTEMAK" size="10" maxlength="9" value="<%= charges.getE02RTEMAK()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Monto M&iacute;n. Apertura Cuentas :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEMOA" size="10" maxlength="9" value="<%= charges.getE02RTEMOA()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo M&iacute;n. x Al Descubierto :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTEOMC" size="10" maxlength="9" value="<%= charges.getE02RTEOMC()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">D&iacute;as Dif. Local/No Local :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEWLC" size="2" maxlength="1" value="<%= charges.getE02RTEWLC()%>" readonly>
                / 
                <input type="text" name="E02RTEWNL" size="2" maxlength="1" value="<%= charges.getE02RTEWNL()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">N&uacute;mero Unidades Cr&eacute;dito :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTESCU" size="6" maxlength="5" value="<%= charges.getE02RTESCU()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">M&iacute;n. D&iacute;as x Cancelaci&oacute;n 
                :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTENDC" size="4" maxlength="3" value="<%= charges.getE02RTENDC()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Monto de Unidades Cr&eacute;dito :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTESCA" size="6" maxlength="5" value="<%= charges.getE02RTESCA()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Balance Usado x Unid Cr&eacute;dito :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <select name="E02RTEBUS" disabled>
                  <option value=" " <% if (!(charges.getE02RTEBUS().equals("NT") ||charges.getE02RTEBUS().equals("GR")||charges.getE02RTEBUS().equals("AN")||charges.getE02RTEBUS().equals("AG"))) out.print("selected"); %>></option>
                  <option value="NT" <%if (charges.getE02RTEBUS().equals("NT")) { out.print("selected"); }%>>Balance 
                  Neto</option>
                  <option value="GR" <%if (charges.getE02RTEBUS().equals("GR")) { out.print("selected"); }%>>Balance 
                  Global</option>
                  <option value="AN" <%if (charges.getE02RTEBUS().equals("AN")) { out.print("selected"); }%>>Promedio 
                  Neto</option>
                  <option value="AG" <%if (charges.getE02RTEBUS().equals("AG")) { out.print("selected"); }%>>Promedio 
                  Global</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">D&iacute;as Vigencia Cheq. Certificados :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTECAB" size="4" maxlength="3" value="<%= charges.getE02RTECAB()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Meses de Historia :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEHIS" size="3" maxlength="2" value="<%= charges.getE02RTEHIS()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4"> 
              <div align="right"></div>
              <div align="left"></div>
              <div align="right"></div>
              <div align="left"></div>
            </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    
  <h4>Tasas de Interes</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
          <tr id="trdark"> 
            <td nowrap colspan="4"> 
              <div align="center"><b>Tasas Activas</b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Tasa de Al Descubierto  :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                <input type="text" name="E02RTEOR1" size="10" maxlength="9" value="<%= charges.getE02RTEOR1()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">D&iacute;as L&iacute;mite  :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEDO1" size="3" maxlength="2" value="<%= charges.getE02RTEDO1()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="22%"> 
              <div align="right">Tasa de Uso Fondos en Transito :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                <input type="text" name="E02RTEOR2" size="10" maxlength="9" value="<%= charges.getE02RTEOR2()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">D&iacute;as 1 :</div>
            </td>
            <td nowrap width="31%"> 
              <div align="left"> 
                <input type="text" name="E02RTEDO2" size="3" maxlength="2" value="<%= charges.getE02RTEDO2()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Tasa de Uso Fondos en Transito :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                <input type="text" name="E02RTEOR3" size="10" maxlength="9" value="<%= charges.getE02RTEOR3()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">D&iacute;as 2 :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEDO3" size="3" maxlength="2" value="<%= charges.getE02RTEDO3()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Tasa de Uso Fondos en Transito :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                <input type="text" name="E02RTEOR4" size="10" maxlength="9" value="<%= charges.getE02RTEOR4()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">D&iacute;as + 2 :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEDO4" size="3" maxlength="2" value="<%= charges.getE02RTEDO4()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Tasa Mora Al Descubierto :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTER07" size="10" maxlength="9" value="<%= charges.getE02RTER07()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">D&iacute;as Lim. Mora :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEMST" size="5" maxlength="4" value="<%= charges.getE02RTEMST()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Tipo Calc. Interes :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <select name="E02RTECAM" disabled>
                  <option value=" " <% if (!(charges.getE02RTECAM().equals("S") ||charges.getE02RTECAM().equals("1"))) out.print("selected"); %>></option>
                  <option value="S" <%if (charges.getE02RTECAM().equals("S")) { out.print("selected"); }%>>Simple</option>
                  <option value="1" <%if (charges.getE02RTECAM().equals("1")) { out.print("selected"); }%>>Tasa 
                  Exponencial</option>
                </select>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">D&iacute;as Al Descubierto Suspenso :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEC01" size="5" maxlength="4" value="<%= charges.getE02RTEC01()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Periodo Base Al Descubierto:</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTEC03" size="4" maxlength="3" value="<%= charges.getE02RTEC03()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Comisi&oacute;n de Al Descubierto :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTEODC" size="10" maxlength="9" value="<%= charges.getE02RTEODC()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Tipo de Comisi&oacute;n :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <select name="E02RTEODF" disabled>
                  <option value=" " <% if (!(charges.getE02RTEODF().equals("S") ||charges.getE02RTEODF().equals("1")||charges.getE02RTEODF().equals("2"))) out.print("selected"); %>></option>
                  <option value="S" <%if (charges.getE02RTEODF().equals("S")) { out.print("selected"); }%>>Fija 
                  por Total Ciclo</option>
                  <option value="1" <%if (charges.getE02RTEODF().equals("1")) { out.print("selected"); }%>>% 
                  Saldo Maximo de Al Descubierto</option>
                  <option value="2" <%if (charges.getE02RTEODF().equals("2")) { out.print("selected"); }%>>% 
                  Saldo de Al Descubierto</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Tasa Flotante :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E02RTEFTA" size="3" maxlength="2" value="<%= charges.getE02RTEFTA()%>" readonly>
                <select name="E02RTEFYA" disabled>
                  <option value=" " <% if (!(charges.getE02RTEFYA().equals("FP") ||charges.getE02RTEFYA().equals("FS"))) out.print("selected"); %>></option>
                  <option value="FP" <%if (charges.getE02RTEFYA().equals("FP")) { out.print("selected"); }%>>FP</option>
                  <option value="FS" <%if (charges.getE02RTEFYA().equals("FS")) { out.print("selected"); }%>>FS</option>
                </select>
                Factor: 
                <input type="text" name="E02RTEFCA" size="5" maxlength="5" value="<%= charges.getE02RTEFCA()%>" readonly>
                Operaci&oacute;n : 
                <select name="E02RTEFFA" disabled>
                  <option value=" " <% if (!(charges.getE02RTEFFA().equals("A") ||charges.getE02RTEFFA().equals("S")||charges.getE02RTEFFA().equals("M"))) out.print("selected"); %>></option>
                  <option value="A" <%if (charges.getE02RTEFFA().equals("A")) { out.print("selected"); }%>>+ 
                  Tasa</option>
                  <option value="S" <%if (charges.getE02RTEFFA().equals("S")) { out.print("selected"); }%>>% 
                  - Tasa</option>
                  <option value="M" <%if (charges.getE02RTEFFA().equals("M")) { out.print("selected"); }%>>% 
                  * Tasa</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4"> 
              <div align="right"></div>
              <div align="left"></div>
              <div align="right"></div>
              <div align="center"><b>Tasas Pasivas</b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Tasa Interes Inversiones (24 h) :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTECIR" size="10" maxlength="9" value="<%= charges.getE02RTECIR()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Periodo Base :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEABA" size="4" maxlength="3" value="<%= charges.getE02RTEABA()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Tasa Flotante :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E02RTEFTL" size="3" maxlength="2" value="<%= charges.getE02RTEFTL()%>" readonly>
                <select name="E02RTEFYL" disabled>
                  <option value=" " <% if (!(charges.getE02RTEFYL().equals("FP") ||charges.getE02RTEFYL().equals("FS"))) out.print("selected"); %>></option>
                  <option value="FP" <%if (charges.getE02RTEFYL().equals("FP")) { out.print("selected"); }%>>FP</option>
                  <option value="FS" <%if (charges.getE02RTEFYL().equals("FS")) { out.print("selected"); }%>>FS</option>
                </select>
                Factor : 
                <input type="text" name="E02RTEFCL" size="5" maxlength="5" value="<%= charges.getE02RTEFCL()%>" readonly>
                Operaci&oacute;n : 
                <select name="E02RTEFFL" disabled>
                  <option value=" " <% if (!(charges.getE02RTEFFL().equals("A") ||charges.getE02RTEFFL().equals("S")||charges.getE02RTEFFL().equals("M"))) out.print("selected"); %>></option>
                  <option value="A" <%if (charges.getE02RTEFFL().equals("A")) { out.print("selected"); }%>>+ 
                  Tasa</option>
                  <option value="S" <%if (charges.getE02RTEFFL().equals("S")) { out.print("selected"); }%>>% 
                  - Tasa</option>
                  <option value="M" <%if (charges.getE02RTEFFL().equals("M")) { out.print("selected"); }%>>% 
                  * Tasa</option>
                </select>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>
</form>
</body>
</html>

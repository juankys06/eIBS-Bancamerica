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
 
 
<h3 align="center">Cargos por Servicios y Tasas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ESD0205_rt_inq_tables_dda_04, ESD0205" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0205I">
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
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
          <tr id="teclear"> 
            <td nowrap width="22%"> 
              <div align="right">Moneda Comisi&oacute;n :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="E02RTEFCY" size="4" maxlength="3" value="<%= charges.getE02RTEFCY()%>" readonly>
              </td>
            <td nowrap width="29%"> 
              <div align="right"> 
                <%if(!charges.getE02RTECUN().equals("0")){%>
                Cliente : 
                <%}%>
              </div>
            </td>
            <td nowrap width="31%"> 
              <%if(!charges.getE02RTECUN().equals("0")){%>
              <input type="text" name="E02RTECUN" size="4" maxlength="2" value="<%= charges.getE02RTECUN()%>" readonly>
              <%}%>
            </td>
          </tr>
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
              <div align="right">Valor L&iacute;mite :</div>
            </td>
            <td nowrap width="31%"> 
              <div align="left"> 
                <input type="text" name="E02RTEMBM" size="10" maxlength="9" value="<%= charges.getE02RTEMBM()%>" readonly>
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
            <td nowrap width="22%"> 
              <div align="right">Cargo x Debajo M&iacute;nimo 1 :</div>
            </td>
            <td nowrap width="18%"> 
              <div align="left"> 
                <input type="text" name="E02RTECH3" size="10" maxlength="9" value="<%= charges.getE02RTECH3()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Valor L&iacute;mite :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEBL3" size="10" maxlength="9" value="<%= charges.getE02RTEBL3()%>" readonly>
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
            <td nowrap width="22%" height="34"> 
              <div align="right">Cargo x Debajo M&iacute;nimo 2 :</div>
            </td>
            <td nowrap width="18%" height="34"> 
              <div align="left"> 
                <input type="text" name="E02RTEMMC" size="10" maxlength="9" value="<%= charges.getE02RTEMMC()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%" height="34"> 
              <div align="right">Valor L&iacute;mite :</div>
            </td>
            <td nowrap width="31%" height="34" > 
              <div align="left"> 
                <input type="text" name="E02RTEMBR" size="10" maxlength="9" value="<%= charges.getE02RTEMBR()%>" readonly>
                <select name="E02RTEBT2" disabled>
                  <option value=" " <% if (!(charges.getE02RTEBT2().equals("NT") ||charges.getE02RTEBT2().equals("GR")||charges.getE02RTEBT2().equals("AN")||charges.getE02RTEBT2().equals("AG"))) out.print("selected"); %>></option>
                  <option value="NT" <%if (charges.getE02RTEBT2().equals("NT")) { out.print("selected"); }%>>Balance 
                  Neto</option>
                  <option value="GR" <%if (charges.getE02RTEBT2().equals("GR")) { out.print("selected"); }%>>Balance 
                  Global</option>
                  <option value="AN" <%if (charges.getE02RTEBT2().equals("AN")) { out.print("selected"); }%>>Promedio 
                  Neto</option>
                  <option value="AG" <%if (charges.getE02RTEBT2().equals("AG")) { out.print("selected"); }%>>Promedio 
                  Global</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo x Exceso de Retiros :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTEMCD" size="10" maxlength="9" value="<%= charges.getE02RTEMCD()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">N&uacute;mero de Retiros :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEMDA" size="4" maxlength="3" value="<%= charges.getE02RTEMDA()%>" readonly>
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
                % </div>
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
              <div align="right">N&uacute;mero de D&iacute;as Gracia :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEGPS" size="4" maxlength="3" value="<%= charges.getE02RTEGPS()%>" readonly>
                **</div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo Aprobaci&oacute;n Sobregiros :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTENSC" size="10" maxlength="9" value="<%= charges.getE02RTENSC()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Cargo Aprobaci&oacute;n Diferidos :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEUNC" size="10" maxlength="9" value="<%= charges.getE02RTEUNC()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo x Suspensi&oacute;n de Pagos :</div>
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
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo x Apertura de Cuenta :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTEOPC" size="10" maxlength="9" value="<%= charges.getE02RTEOPC()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Cargo Anual de Servicios :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEYRC" size="10" maxlength="9" value="<%= charges.getE02RTEYRC()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%" height="33"> 
              <div align="right">Retiro M&iacute;nimo Permitido :</div>
            </td>
            <td nowrap width="18%" height="33" > 
              <div align="left"> 
                <input type="text" name="E02RTEMAK" size="10" maxlength="9" value="<%= charges.getE02RTEMAK()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%" height="33"> 
              <div align="right">Monto M&iacute;n. Apertura Cuentas :</div>
            </td>
            <td nowrap width="31%" height="33" > 
              <div align="left"> 
                <input type="text" name="E02RTEMOA" size="10" maxlength="9" value="<%= charges.getE02RTEMOA()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">D&iacute;as Retener Confirmaci&oacute;n :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTENDH" size="3" maxlength="2" value="<%= charges.getE02RTENDH()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Monto M&aacute;ximo a Confirmar :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEMAC" size="10" maxlength="9" value="<%= charges.getE02RTEMAC()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">D&iacute;as para Cancelar :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTENDC" size="6" maxlength="5" value="<%= charges.getE02RTENDC()%>" readonly>
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
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">Cargo M&iacute;n. x Sobregiro :</div>
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
          <tr id="trclear"> 
            <td nowrap width="22%"> 
              <div align="right">Manejo de Libreta :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <select name="E02RTEPSB" disabled>
                  <option value=" " <% if (!(charges.getE02RTEPSB().equals("Y") ||charges.getE02RTEPSB().equals("N"))) out.print("selected"); %>></option>
                  <option value="Y" <%if (charges.getE02RTEPSB().equals("Y")) { out.print("selected"); }%>>Si</option>
                  <option value="N" <%if (charges.getE02RTEPSB().equals("N")) { out.print("selected"); }%>>No</option>
                </select>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right">Gracia Mora :</div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> 
                <input type="text" name="E02RTEC02" size="5" maxlength="5" value="<%= charges.getE02RTEC02()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="22%"> 
              <div align="right">D&iacute;as para Giros Diferidos :</div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> 
                <input type="text" name="E02RTECAB" size="4" maxlength="3" value="<%= charges.getE02RTECAB()%>" readonly>
              </div>
            </td>
            <td nowrap width="29%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="31%" > 
              <div align="left"> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right"></div>
              <div align="left"> </div>
              <div align="right"></div>
            </td>
            <td nowrap colspan="2">**Provisi&oacute;n de Intereses</td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    
  <h4>Tasas de Interes</h4>
  <h4>Tasas Pasivas</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
          <tr id="trdark"> 
            <td nowrap width="6%"> 
              <div align="center"><b>L&iacute;mite</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="center"><b>Balance</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="center"><b>Tasa</b></div>
            </td>
            <td nowrap width="11%" > 
              <div align="center"><b>L&iacute;mite</b></div>
            </td>
            <td nowrap width="14%" > 
              <div align="center"><b>Balance</b></div>
            </td>
            <td nowrap width="27%" > 
              <div align="center"><b>Tasa</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="6%"> 
              <div align="center"><b>(1)</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMKL" size="10" maxlength="9" value="<%= charges.getE02RTEMKL()%>" readonly>
              </div>
            </td>
            <td nowrap width="9%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMKR" size="10" maxlength="9" value="<%= charges.getE02RTEMKR()%>" readonly>
              </div>
            </td>
            <td nowrap width="11%"> 
              <div align="center"><b>(2)</b></div>
            </td>
            <td nowrap width="14%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMB1" size="10" maxlength="9" value="<%= charges.getE02RTEMB1()%>" readonly>
              </div>
            </td>
            <td nowrap width="27%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMR1" size="10" maxlength="9" value="<%= charges.getE02RTEMR1()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="6%"> 
              <div align="center"><b>(3)</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMB2" size="10" maxlength="9" value="<%= charges.getE02RTEMB2()%>" readonly>
              </div>
            </td>
            <td nowrap width="9%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMR2" size="10" maxlength="9" value="<%= charges.getE02RTEMR2()%>" readonly>
              </div>
            </td>
            <td nowrap width="11%"> 
              <div align="center"><b>(4)</b></div>
            </td>
            <td nowrap width="14%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMB3" size="10" maxlength="9" value="<%= charges.getE02RTEMB3()%>" readonly>
              </div>
            </td>
            <td nowrap width="27%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMR3" size="10" maxlength="9" value="<%= charges.getE02RTEMR3()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="6%"> 
              <div align="center"><b>(5)</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMB4" size="10" maxlength="9" value="<%= charges.getE02RTEMB4()%>" readonly>
              </div>
            </td>
            <td nowrap width="9%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMR4" size="10" maxlength="9" value="<%= charges.getE02RTEMR4()%>" readonly>
              </div>
            </td>
            <td nowrap width="11%"> 
              <div align="center"><b>(6)</b></div>
            </td>
            <td nowrap width="14%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMB5" size="10" maxlength="9" value="<%= charges.getE02RTEMB5()%>" readonly>
              </div>
            </td>
            <td nowrap width="27%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMR5" size="10" maxlength="9" value="<%= charges.getE02RTEMR5()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="6%"> 
              <div align="center"><b>(7)</b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMB6" size="10" maxlength="9" value="<%= charges.getE02RTEMB6()%>" readonly>
              </div>
            </td>
            <td nowrap width="9%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMR6" size="10" maxlength="9" value="<%= charges.getE02RTEMR6()%>" readonly>
              </div>
            </td>
            <td nowrap width="11%"> 
              <div align="center"><b>(8)</b></div>
            </td>
            <td nowrap width="14%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMB7" size="10" maxlength="9" value="<%= charges.getE02RTEMB7()%>" readonly>
              </div>
            </td>
            <td nowrap width="27%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMR7" size="10" maxlength="9" value="<%= charges.getE02RTEMR7()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="6%"> 
              <div align="center"><b>(9)</b></div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMB8" size="10" maxlength="9" value="<%= charges.getE02RTEMB8()%>" readonly>
              </div>
            </td>
            <td nowrap width="9%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMR8" size="10" maxlength="9" value="<%= charges.getE02RTEMR8()%>" readonly>
              </div>
            </td>
            <td nowrap width="11%"> 
              <div align="center"><b>(10)</b></div>
            </td>
            <td nowrap width="14%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMB9" size="10" maxlength="9" value="<%= charges.getE02RTEMB9()%>" readonly>
              </div>
            </td>
            <td nowrap width="27%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMR9" size="10" maxlength="9" value="<%= charges.getE02RTEMR9()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="6%"> 
              <div align="center"><b>(11)</b></div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMBX" size="10" maxlength="9" value="<%= charges.getE02RTEMBX()%>" readonly>
              </div>
            </td>
            <td nowrap width="9%"> 
              <div align="center"> 
                <input type="text" name="E02RTEMRX" size="10" maxlength="9" value="<%= charges.getE02RTEMRX()%>" readonly>
              </div>
            </td>
            <td nowrap width="11%"> 
              <div align="center"><b>(12)</b></div>
            </td>
            <td nowrap width="14%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMBY" size="10" maxlength="9" value="<%= charges.getE02RTEMBY()%>" readonly>
              </div>
            </td>
            <td nowrap width="27%" > 
              <div align="center"> 
                <input type="text" name="E02RTEMRY" size="10" maxlength="9" value="<%= charges.getE02RTEMRY()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
  </table>
<br>
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right">Tipo de Balance :</div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <select name="E02RTEBTY" disabled>
                  <option value=" " <% if (!(charges.getE02RTEBTY().equals("NT") ||charges.getE02RTEBTY().equals("GR")||charges.getE02RTEBTY().equals("AN")||charges.getE02RTEBTY().equals("AG")||charges.getE02RTEBTY().equals("MG")
				||charges.getE02RTEBTY().equals("MN")||charges.getE02RTEBTY().equals("RG")||charges.getE02RTEBTY().equals("RN"))) out.print("selected"); %>></option>
                  <option value="NT" <%if (charges.getE02RTEBTY().equals("NT")) { out.print("selected"); }%>>Balance 
                  Neto</option>
                  <option value="GR" <%if (charges.getE02RTEBTY().equals("GR")) { out.print("selected"); }%>>Balance 
                  Global</option>
                  <option value="AN" <%if (charges.getE02RTEBTY().equals("AN")) { out.print("selected"); }%>>Promedio 
                  Neto</option>
                  <option value="AG" <%if (charges.getE02RTEBTY().equals("AG")) { out.print("selected"); }%>>Promedio 
                  Global</option>
                  <option value="MG" <%if (charges.getE02RTEBTY().equals("MG")) { out.print("selected"); }%>>Minimo 
                  Bruto</option>
                  <option value="MN" <%if (charges.getE02RTEBTY().equals("MN")) { out.print("selected"); }%>>Minimo 
                  Neto</option>
                  <option value="RG" <%if (charges.getE02RTEBTY().equals("RG")) { out.print("selected"); }%>>Reversion 
                  Global</option>
                  <option value="RN" <%if (charges.getE02RTEBTY().equals("RN")) { out.print("selected"); }%>>Reversion 
                  Neto</option>

                </select>

              </div>
            </td>
            <td nowrap width="17%"> 
              <div align="right">C&aacute;lculo Interes : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E02RTECAM" size="2" maxlength="1" value="<%= charges.getE02RTECAM()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right">Base : </div>
            </td>
            <td nowrap width="9%"> 
              <div align="left">
                <input type="text" name="E02RTEABA" size="4" maxlength="3" value="<%= charges.getE02RTEABA()%>" readonly>
              </div>
            </td>
            <td nowrap width="17%"> 
              <div align="right">Reversi&oacute;n Intereses :</div>
            </td>
            <td nowrap > 
              <div align="left">
                <input type="text" name="E02RTEOMP" size="4" maxlength="3" value="<%= charges.getE02RTEOMP()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right">Tasa Flotante :</div>
            </td>
            <td nowrap colspan="3"> 
              <div align="left"></div>
              <div align="right"></div>
              <div align="left"> </div>
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
            </td>
          </tr>
        </table>
  </table>
  
  <h4>Tasas Activas</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left" >
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right">Tasa Base :</div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02RTEOR1" size="10" maxlength="9" value="<%= charges.getE02RTEOR1()%>" readonly>
              </div>
            </td>
            <td nowrap > 
              <div align="right">Periodo Base S/Giro:</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E02RTEC03" size="4" maxlength="3" value="<%= charges.getE02RTEC03()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right">Comisi&oacute;n :</div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"></div>
              <div align="right"></div>
              <div align="right"></div>
              <input type="text" name="E02RTEODC" size="10" maxlength="9" value="<%= charges.getE02RTEODC()%>" readonly>
            </td>
            <td nowrap width="39%"> 
              <div align="right">Tipo de Comisi&oacute;n : </div>
            </td>
            <td nowrap colspan="2">
              <select name="E02RTEODF" disabled>
                <option value=" " <% if (!(charges.getE02RTEODF().equals("S") ||charges.getE02RTEODF().equals("1")||charges.getE02RTEODF().equals("2"))) out.print("selected"); %>></option>
                <option value="S" <%if (charges.getE02RTEODF().equals("S")) { out.print("selected"); }%>>Fija 
                por Total Ciclo</option>
                <option value="1" <%if (charges.getE02RTEODF().equals("1")) { out.print("selected"); }%>>% 
                Saldo Maximo de Sobregiro</option>
                <option value="2" <%if (charges.getE02RTEODF().equals("2")) { out.print("selected"); }%>>% 
                Saldo de Sobregiro</option>
              </select>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right">Tasa Flotante :</div>
            </td>
            <td nowrap colspan="4"> 
              <div align="left"></div>
              <div align="right"></div>
              <div align="left"> 
                <input type="text" name="E02RTEFTA" size="3" maxlength="2" value="<%= charges.getE02RTEFTA()%>" readonly>
                <select name="E02RTEFYA" disabled>
                  <option value=" " <% if (!(charges.getE02RTEFYA().equals("FP") ||charges.getE02RTEFYA().equals("FS"))) out.print("selected"); %>></option>
                  <option value="FP" <%if (charges.getE02RTEFYA().equals("FP")) { out.print("selected"); }%>>FP</option>
                  <option value="FS" <%if (charges.getE02RTEFYA().equals("FS")) { out.print("selected"); }%>>FS</option>
                </select>
                Factor : 
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
            <td nowrap colspan="2"> 
              <div align="right">Tasa Mora :</div>
            </td>
            <td nowrap colspan="4"> 
              <div align="left"> 
                <input type="text" name="E02RTER07" size="10" maxlength="9" value="<%= charges.getE02RTER07()%>" readonly>
                D&iacute;as Cobro : 
                <input type="text" name="E02RTEMST" size="5" maxlength="4" value="<%= charges.getE02RTEMST()%>" readonly>
                Plazo Financiamiento : 
                <input type="text" name="E02RTEC01" size="5" maxlength="5" value="<%= charges.getE02RTEC01()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>

  </table>
  
</form>
</body>
</html>

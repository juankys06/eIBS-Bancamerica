<html>
<head>
<title>Códigos de Referencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="refCodes" class="datapro.eibs.beans.ESD003002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>"); 
    }
    
%>


<H3 align="center">C&oacute;digos de Referencias del Sistema - Tipos de Proceso<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cnof_process_type_details.jsp, ESD0030"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0030" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <h4>Informaci&oacute;n B&aacute;sica</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Clasificaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02CNOCFL" size="3" maxlength="2" value="<%= refCodes.getE02CNOCFL().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">C&oacute;digo :</div>
            </td>
            <td nowrap height="23"> 
              <div align="left"> 
                <input type="text" name="E02CNORCD" size="6" maxlength="4" value="<%= refCodes.getE02CNORCD().trim()%>">
                 
                <input type="text" name="E02CNODSC" size="36" maxlength="35" value="<%= refCodes.getE02CNODSC().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Chequear D&iacute;gito :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <select name="E02CNOF01">
                  
                  <option value="0" <%if (refCodes.getE02CNOF01().equals("0")) { out.print("selected"); }%>>M&oacute;dulo 10</option>
                  <option value="1" <%if (refCodes.getE02CNOF01().equals("1")) { out.print("selected"); }%>>M&oacute;dulo 11</option>
                  <option value="N" <%if (refCodes.getE02CNOF01().equals("N")) { out.print("selected"); }%>>No Aplica</option>
                  
                </select>
              </div>
            </td>
          </tr>
          <%if (refCodes.getE02CNOCFL().equals("04")) {%>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Fuente :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <select name="E02CNOF04">
                  <option value="T" <%if (refCodes.getE02CNOF04().equals("T")) { out.print("selected"); }%>>Tesorería</option>
                  <option value="F" <%if (refCodes.getE02CNOF04().equals("F")) { out.print("selected"); }%>>Fideicomiso</option>
                  <option value="E" <%if (refCodes.getE02CNOF04().equals("E")) { out.print("selected"); }%>>FEM</option>
                  <option value="R" <%if (refCodes.getE02CNOF04().equals("R")) { out.print("selected"); }%>>Terceros</option>
                  <option value="A" <%if (refCodes.getE02CNOF04().equals("A")) { out.print("selected"); }%>>Todos</option>
                  <option value="N" <%if (refCodes.getE02CNOF04().equals("N")) { out.print("selected"); }%>>No Aplica</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Tipo Moneda Transf./Tesorería  :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <select name="E02CNOCPF">
                  <option value="1" <%if (refCodes.getE02CNOCPF().equals("1")) { out.print("selected"); }%>>Moneda Nacional</option>
                  <option value="2" <%if (refCodes.getE02CNOCPF().equals("2")) { out.print("selected"); }%>>Moneda Extranjera</option>
                  <option value="3" <%if (refCodes.getE02CNOCPF().equals("3")) { out.print("selected"); }%>>Ambas</option>
                </select>
              </div>
            </td>
          </tr>          
          <%}%>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="6"> 
              <div align="center"><b>Tipo de Proceso</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2" height="23"> 
              <div align="center"><b>Cuentas Corrientes</b></div>
            </td>
            <td nowrap height="23" colspan="2"> 
              <div align="center"><b>Moneda Extranjera / Tesorer&iacute;a</b></div>
            </td>
            <td nowrap height="23" colspan="2"> 
              <div align="center"><b>Cobranzas</b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="01" <%if (refCodes.getE02CNOACD().equals("01")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap height="19" width="43%"> 
              <div align="left">Cuentas Corrientes sin Interes</div>
            </td>
            <td nowrap height="19" width="4%"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="31" <%if (refCodes.getE02CNOACD().equals("31")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap height="19" width="30%">Spot</td>
            <td nowrap height="19" width="4%"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="50" <%if (refCodes.getE02CNOACD().equals("50")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap height="19" width="15%">Cobranza Simple</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="02" <%if (refCodes.getE02CNOACD().equals("02")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
              <div align="left">Cuentas Corrientes con Interes Limitado </div>
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="32" <%if (refCodes.getE02CNOACD().equals("32")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">Forwards</td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="51" <%if (refCodes.getE02CNOACD().equals("51")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="15%" height="19">Cobranza Documentaria</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="03" <%if (refCodes.getE02CNOACD().equals("03")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
              <div align="left">Cuentas Corrientes con Interes</div>
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="33" <%if (refCodes.getE02CNOACD().equals("33")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">Swaps</td>
            <td nowrap colspan="2" height="19"> 
              <div align="center"></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="04" <%if (refCodes.getE02CNOACD().equals("04")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
              <div align="left">Cuentas de Ahorro</div>
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="34" <%if (refCodes.getE02CNOACD().equals("34")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">Opciones</td>
            <td nowrap colspan="2" height="19"> 
              <div align="center"><b>Otros Productos</b></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2" height="19"> 
              <div align="center"></div>
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="35" <%if (refCodes.getE02CNOACD().equals("35")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">FRA's</td>
            <td nowrap width="4%" height="19"> 
              <div align="center">
                <input type="radio" name="E02CNOACD" value="70" <%if (refCodes.getE02CNOACD().equals("70")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="15%" height="19">Activos del Exterior</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="2" height="19"> 
              <div align="center"><b>Contratos (CDS,Prestamos, Inversiones,etc.)</b></div>
            </td>
            <td nowrap colspan="2" height="19"> 
              <div align="center"><b>Cartas de Cr&eacute;dito</b></div>
            </td>
            <td nowrap height="19" width="4%"> 
              <div align="center">
                <input type="radio" name="E02CNOACD" value="71" <%if (refCodes.getE02CNOACD().equals("71")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap height="19" width="15%">Pasivos del Exterior</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="10" <%if (refCodes.getE02CNOACD().equals("10")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
              <div align="left">Prestamos</div>
            </td>
            <td nowrap height="19" width="4%"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="40" <%if (refCodes.getE02CNOACD().equals("40")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap height="19" width="30%">Cartas de Cr&eacute;dito</td>
            <td nowrap height="19" width="4%"> 
              <div align="center">
                <input type="radio" name="E02CNOACD" value="90" <%if (refCodes.getE02CNOACD().equals("90")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap height="19" width="15%">Lineas de Cr&eacute;dito</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="11" <%if (refCodes.getE02CNOACD().equals("11")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
              <div align="left">Certificados de Deposito</div>
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="41" <%if (refCodes.getE02CNOACD().equals("41")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">Aceptaciones Bancarias</td>
            <td nowrap width="4%" height="19"> 
              <div align="center">
                <input type="radio" name="E02CNOACD" value="91" <%if (refCodes.getE02CNOACD().equals("91")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="15%" height="19">Garant&iacute;as</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="12" <%if (refCodes.getE02CNOACD().equals("12")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
              <div align="left">Depositos Temporales</div>
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="42" <%if (refCodes.getE02CNOACD().equals("42")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">Pagos Futuros</td>
            <td nowrap width="4%" height="19"> 
              <div align="center">
                <input type="radio" name="E02CNOACD" value="92" <%if (refCodes.getE02CNOACD().equals("92")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="15%" height="19">Amortizaciones</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="13" <%if (refCodes.getE02CNOACD().equals("13")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
              <div align="left">Inversiones</div>
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="43" <%if (refCodes.getE02CNOACD().equals("43")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">Stand By</td>
            <td nowrap width="4%" height="19"> 
              <div align="center">
                <input type="radio" name="E02CNOACD" value="93" <%if (refCodes.getE02CNOACD().equals("93")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="15%" height="19">Transferencias</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="14" <%if (refCodes.getE02CNOACD().equals("14")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
              <div align="left">Aceptaciones Descontadas</div>
            </td>
            <td nowrap colspan="2" height="19"> 
              <div align="center"><b>Portafolio</b></div>
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center">
                <input type="radio" name="E02CNOACD" value="94" <%if (refCodes.getE02CNOACD().equals("94")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="15%" height="19">Tarjetas de Cr&eacute;dito</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="15" <%if (refCodes.getE02CNOACD().equals("15")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
              <div align="left">Inversiones 24 Horas</div>
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="98" <%if (refCodes.getE02CNOACD().equals("98")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">Bonos</td>
            <td nowrap width="4%" height="19"> 
              <div align="center">
                <input type="radio" name="E02CNOACD" value="95" <%if (refCodes.getE02CNOACD().equals("95")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="15%" height="19">Fondos de Retiro</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="19" <%if (refCodes.getE02CNOACD().equals("19")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
              <div align="left">Proyectos de Constructor</div>
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="88" <%if (refCodes.getE02CNOACD().equals("88")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">Equities</td>
            <td nowrap width="4%" height="19"> 
              <div align="center">
                <input type="radio" name="E02CNOACD" value="96" <%if (refCodes.getE02CNOACD().equals("96")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="15%" height="19">Fondos de Inversiones</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="89" <%if (refCodes.getE02CNOACD().equals("89")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">Comodities</td>
            <td nowrap width="4%" height="19"> 
              <div align="center">
              </div>
            </td>
            <td nowrap width="15%" height="19"></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="97" <%if (refCodes.getE02CNOACD().equals("97")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">Fondo Mutuo</td>
            <td nowrap width="4%" height="19"> 
              <div align="center">
              </div>
            </td>
            <td nowrap width="15%" height="19"></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
              </div>
            </td>
            <td nowrap width="43%" height="19"> 
            </td>
            <td nowrap width="4%" height="19"> 
              <div align="center"> 
                <input type="radio" name="E02CNOACD" value="99" <%if (refCodes.getE02CNOACD().equals("99")) out.print("checked"); %> onclick="showTypeAcd(this.value)">
              </div>
            </td>
            <td nowrap width="30%" height="19">Preferred Stocks</td>
            <td nowrap width="4%" height="19"> 
              <div align="center">
              </div>
            </td>
            <td nowrap width="15%" height="19"></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
    
<div id="hiddenDivAmort">
  <h4>Informaci&oacute;n para Amortizacion</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Tipo de Amortizacion :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <select name="E02CNODCB">
                  <option value="L" <%if (refCodes.getE02CNODCB().equals("L")) { out.print("selected"); }%>>Linea Recta Amortizacion Uniforme</option>
                  <option value="S" <%if (refCodes.getE02CNODCB().equals("S")) { out.print("selected"); }%>>Flat Total En una Fecha Indicada</option>
                  <option value="M" <%if (refCodes.getE02CNODCB().equals("M")) { out.print("selected"); }%>>En Relacion a la Curva de Capital</option>
                  <option value="I" <%if (refCodes.getE02CNODCB().equals("I")) { out.print("selected"); }%>>En Relacion a la Curva de Intereses</option>
                  <option value="P" <%if (refCodes.getE02CNODCB().equals("P")) { out.print("selected"); }%>>En relacion a el Plazo</option>
                </select>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Caracteristicas  :</div>
            </td>
            <td nowrap height="19"> 
              <div align="left"> 
                <select name="E02CNOTCF">
                  <option value="D" <%if (refCodes.getE02CNOTCF().equals("D")) { out.print("selected"); }%>>Devengo de Intereses</option>
                  <option value="P" <%if (refCodes.getE02CNOTCF().equals("P")) { out.print("selected"); }%>>Indicador de Productividad</option>
                </select>
              </div>
            </td>
          </tr>    
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Monto Minimo Amortizar :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E02CNOCHG" size="16" maxlength="15" value="<%= refCodes.getE02CNOCHG().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</div>
  
  <div align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
    
<SCRIPT LANGUAGE="JavaScript">
function showTypeAcd(acd){
	if(acd == '92'){
		setVisibility(document.getElementById("hiddenDivAmort"), "visible");
	} else {
		setVisibility(document.getElementById("hiddenDivAmort"), "hidden");	
	}
}

showTypeAcd('<%=refCodes.getE02CNOACD()%>');
</SCRIPT>
  
</body>
</html>

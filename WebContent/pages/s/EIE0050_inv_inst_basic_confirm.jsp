<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Instrumentos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">

<jsp:useBean id="invInst" class="datapro.eibs.beans.EIE005001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language = "javascript">
function doPrint(){
	if(!window.print){
       var msg ="This browser doesn't allow print";
	   alert(msg);
	   return;}
	
    window.focus();
	window.print();

	return;
}
</SCRIPT>
</head>

<body>

 
<h3 align="center"> Confirmación</h3>
<hr size="4">
<table border="0" class="tableinfo" width="100%">
  <tr> 
    <td colspan="3">
      <div align="center"> No válida como recibo oficial</div>
    </td>
  </tr>
</table>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0308P">
  <h4>Informaci&oacute;n B&aacute;sica 
    <input type="hidden" name="SCREEN"  value="1" >
    </h4>
    <table  class="tableinfo" width="715">
      <tr > 
        <td nowrap> 
          
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Código / Descripción :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ISIIIC" size="5" maxlength="3" value="<%= invInst.getE01ISIIIC()%>" >
              <input type="text" readonly  name="E01ISIDSC" size="35" maxlength="30" value="<%= invInst.getE01ISIDSC()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Estado Interno :</div>
            </td>
            <td nowrap width="33%" > <% if(invInst.getE01ISISTS().equals("P")) out.print("Pendiente");
				 else if(invInst.getE01ISISTS().equals("R")) out.print("Liberado");
				 else if(invInst.getE01ISISTS().equals("A")) out.print("Activo");
				 else if(invInst.getE01ISISTS().equals("C")) out.print("Cancelado");
				 else out.print("Activo");%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Moneda del Instrumento :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ISICCY" size="5" maxlength="3" value="<%= invInst.getE01ISICCY()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Moneda (Bloomberg) :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ISIBCY" size="5" maxlength="3" value="<%= invInst.getE01ISIBCY()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Instrumento :</div>
            </td>
            <td nowrap width="36%" ><% if(invInst.getE01ISIPTY().equals("BND")) out.print("Bonos");
				 else if(invInst.getE01ISIPTY().equals("EQT")) out.print("Acciones");
				 else if(invInst.getE01ISIPTY().equals("MUT")) out.print("Fondos Mutuales");
				 else if(invInst.getE01ISIPTY().equals("PFS")) out.print("Acciones Preferentes");
			  %> 
              <input type="hidden" name="E01ISIPTY"  value="<%= invInst.getE01ISIPTY()%>" >
            </td>
            <td nowrap width="14%" > 
              <div align="right">Símbolo :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ISISYM" size="20" maxlength="15" value="<%= invInst.getE01ISISYM()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Nombre de Emisor :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ISIENM" size="50" maxlength="45" value="<%= invInst.getE01ISIENM()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Número ISIN / Serie :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ISINUM" size="12" maxlength="12" value="<%= invInst.getE01ISINUM()%>">
              / 
              <input type="text" readonly  name="E01ISISER" size="12" maxlength="12" value="<%= invInst.getE01ISISER()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Marcar como Preferencial :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="hidden" name="E01ISIPRE" value="<%= invInst.getE01ISIPRE()%>">
              <input type="radio" disabled  name="CE01ISIPRE" value="Y" onClick="document.forms[0].E01ISIPRE.value='Y'"
			  <%if(invInst.getE01ISIPRE().equals("Y")) out.print("checked");%>>
              Si 
              <input type="radio" disabled  name="CE01ISIPRE" value="N" onClick="document.forms[0].E01ISIPRE.value='N'"
			  <%if(invInst.getE01ISIPRE().equals("N")) out.print("checked");%>>
              No </td>
            <td nowrap width="14%" > 
              <div align="right">CUSIP :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ISICUS" size="12" maxlength="12" value="<%= invInst.getE01ISICUS()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Código de País :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ISICTC" size="5" maxlength="3" value="<%= invInst.getE01ISICTC()%>">
              <a href="javascript:GetCodeCNOFC('E01ISICTC','03')"> </a> </td>
            <td nowrap width="14%" > 
              <div align="right">Nombre de País :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ISICTR" size="30" maxlength="30" value="<%= invInst.getE01ISICTR()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Rating 'Standard &amp; Poor':</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ISIRSP" size="10" maxlength="10" value="<%= invInst.getE01ISIRSP()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Rating 'Moodys' :</div>
            </td>
            <td nowrap width="33%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01ISIRMD" size="10" maxlength="10" value="<%= invInst.getE01ISIRMD()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Nota de Rango :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01ISIRGN" size="45" maxlength="45" value="<%= invInst.getE01ISIRGN()%>">
            </td>
            <td nowrap > 
              <div align="right">Precio en Porciento :</div>
            </td>
            <td nowrap > 
              <input type="hidden" name="E01ISIPER" value="<%= invInst.getE01ISIPER()%>">
              <input type="radio" disabled  name="CE01ISIPER " value="Y" onClick="document.forms[0].E01ISIPER .value='Y'"
			  <%if(invInst.getE01ISIPER ().equals("Y")) out.print("checked");%>>
              Sí 
              <input type="radio" disabled  name="CE01ISIPER " value="N" onClick="document.forms[0].E01ISIPER .value='N'"
			  <%if(invInst.getE01ISIPER ().equals("N")) out.print("checked");%>>
              No </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="17%" > 
              <div align="right">Fecha de Emisión :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ISISD1" size="3" maxlength="2" value="<%= invInst.getE01ISISD1()%>">
              <input type="text" readonly  name="E01ISISD2" size="3" maxlength="2" value="<%= invInst.getE01ISISD2()%>">
              <input type="text" readonly  name="E01ISISD3" size="3" maxlength="2" value="<%= invInst.getE01ISISD3()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Modo de Actualización :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="hidden" name="E01ISIMOD" value="<%= invInst.getE01ISIMOD()%>">
              <input type="radio" disabled  name="CE01ISIMOD" value="M" onClick="document.forms[0].E01ISIMOD.value='M'"
			  <%if(invInst.getE01ISIMOD().equals("M")) out.print("checked");%>>
              Manual 
              <input type="radio" disabled  name="CE01ISIMOD" value="B" onClick="document.forms[0].E01ISIMOD.value='B'"
			  <%if(invInst.getE01ISIMOD().equals("B")) out.print("checked");%>>
              Bloomberg </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="17%" > 
              <div align="right">Tipo de Cálculo :</div>
            </td>
            <td nowrap width="36%" > 
              <input type="text" readonly  name="E01ISICTP" size="7" maxlength="6" value="<%= invInst.getE01ISICTP()%>">
            </td>
            <td nowrap width="14%" > 
              <div align="right">Descripción del Tipo de Cálculo :</div>
            </td>
            <td nowrap width="33%" > 
              <input type="text" readonly  name="E01ISICTD" size="35" maxlength="35" value="<%= invInst.getE01ISICTD()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="34%" > 
              <div align="right">Rendimiento :</div>
            </td>
            <td nowrap width="34%" > 
              <input type="text" name="E01ISIYLD" size="11" maxlength="9" value="<%= invInst.getE01ISIYLD()%>" >
            </td>
            <td nowrap width="34%" > 
              <div align="right">Estado de Mercado del Instrumento :</div>
            </td>
            <td nowrap width="66%" > 
              <input type="text" name="E01ISIMKS" size="15" maxlength="10" value="<%= invInst.getE01ISIMKS()%>" >
            </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    <% if(invInst.getE01ISIPTY().equals("BND") || invInst.getE01ISIPTY().equals("PFS")) { %> 
    <h4>Información Adicional</h4>
    <table  class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellpadding=2 cellspacing=0 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap width="34%" > 
                <div align="right">Fecha de Vencimiento :</div>
              </td>
              <td nowrap width="34%" > 
                <input type="text" readonly  name="E01ISIMA1" size="3" maxlength="2" value="<%= invInst.getE01ISIMA1()%>">
                <input type="text" readonly  name="E01ISIMA2" size="3" maxlength="2" value="<%= invInst.getE01ISIMA2()%>">
                <input type="text" readonly  name="E01ISIMA3" size="3" maxlength="2" value="<%= invInst.getE01ISIMA3()%>">
              </td>
              <td nowrap width="34%" > 
                <div align="right"></div>
              </td>
              <td nowrap width="66%" >&nbsp; </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="34%" > 
                <div align="right">Tasa de Interes :</div>
              </td>
              <td nowrap > 
                <div align="left"> 
                  <input type="text" readonly  name="E01ISIRTE" size="11" maxlength="11" value="<%= invInst.getE01ISIRTE()%>">
                </div>
              </td>
              <td nowrap > 
                <div align="right">Tipo de Acumulación :</div>
              </td>
              <td nowrap > 
                <select disabled  name="E01ISIATY">
                  <option value="1" <%if (invInst.getE01ISIATY().equals("1")) { out.print("selected"); }%>>Actual/Actual</option>
                  <option value="2" <%if (invInst.getE01ISIATY().equals("2")) {  out.print("selected"); }%>>Actual/365</option>
                  <option value="3" <%if (invInst.getE01ISIATY().equals("3")) {  out.print("selected"); }%>>Actual/365(366 
                  Año Bisiesto)</option>
                  <option value="4" <%if (invInst.getE01ISIATY().equals("4")) {  out.print("selected"); }%>>Actual/360</option>
                  <option value="5" <%if (invInst.getE01ISIATY().equals("5")) {  out.print("selected"); }%>>30/360</option>
                  <option value="6" <%if (invInst.getE01ISIATY().equals("6")) {  out.print("selected"); }%>>30E/360</option>
                </select>
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="34%" > 
                <div align="right">Base de Cálculo :</div>
              </td>
              <td nowrap width="34%" > 
                <input type="text" readonly  name="E01ISIBAS" size="5" maxlength="3" value="<%= invInst.getE01ISIBAS()%>">
              </td>
              <td nowrap width="34%" > 
                <div align="right">Días de Cálculo :</div>
              </td>
              <td nowrap width="66%" > 
                <select disabled  name="E01ISIICT">
                  <option value="1" <%if (invInst.getE01ISIICT().equals("1")) { out.print("selected"); }%>>Días Calendario 
                  </option>
                  <option value="2" <%if (invInst.getE01ISIICT().equals("2")) {  out.print("selected"); }%>>Días Comerciales</option>
                </select>
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="34%" > 
                <div align="right">Tabla de Tasa Flotante :</div>
              </td>
              <td nowrap width="34%" > 
                <input type="text" readonly  name="E01ISIFTB" size="2" maxlength="2" value="<%= invInst.getE01ISIFTB().trim()%>">
                <select disabled  name="E01ISIFTY">
                  <option value="1" <%if (invInst.getE01ISIFTY().equals("FP")) { out.print("selected"); }%>> 
                  Primaria</option>
                  <option value="2" <%if (invInst.getE01ISIFTY().equals("FS")) {  out.print("selected"); }%>> 
                  Secundaria</option>
                </select>
              </td>
              <td nowrap width="34%" > 
                <div align="right">Tasa Flotante :</div>
              </td>
              <td nowrap width="66%" > 
                <input type="text" readonly  name="E01ISIFRT" size="11" maxlength="11" value="<%= invInst.getE01ISIFRT()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="34%" > 
                <div align="right">Días / Fecha T + N :</div>
              </td>
              <td nowrap width="34%" > 
                <input type="text" readonly  name="E01ISIDTN" size="11" maxlength="11" value="<%= invInst.getE01ISIDTN()%>">
              </td>
              <td nowrap width="34%" > 
                <div align="right">Frecuencia de Pago :</div>
              </td>
              <td nowrap width="66%" > 
                <input type="text" readonly  name="E01ISIPFV" size="4" maxlength="4" value="<%= invInst.getE01ISIPFV().trim()%>" onKeyPress="enterInteger()">
                <select disabled  name="E01ISIPFC">
                  <option value="D" <% if(invInst.getE01ISIPFC().equals("D")) out.print("selected");%>>Día(s)</option>
                  <option value="M" <% if(invInst.getE01ISIPFC().equals("M")) out.print("selected");%>>Mes(es)</option>
                  <option value="Q" <% if(invInst.getE01ISIPFC().equals("Q")) out.print("selected");%>>Trimestre</option>
                  <option value="S" <% if(invInst.getE01ISIPFC().equals("S")) out.print("selected");%>>Semianual</option>
                  <option value="Y" <% if(invInst.getE01ISIPFC().equals("Y")) out.print("selected");%>>Anual</option>
                </select>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <%
     
    }
  %> 
    <h4>Fechas</h4>
    <table  class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellpadding=2 cellspacing=0 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap width="22%" > 
                <div align="right">Ultima Fecha de Pago de Interes :</div>
              </td>
              <td nowrap width="18%" > 
                <div align="left"> 
                  <input type="text" readonly  name="E01ISIIN1" size="3" maxlength="2" value="<%= invInst.getE01ISIIN1()%>">
                  <input type="text" readonly  name="E01ISIIN2" size="3" maxlength="2" value="<%= invInst.getE01ISIIN2()%>">
                  <input type="text" readonly  name="E01ISIIN3" size="3" maxlength="2" value="<%= invInst.getE01ISIIN3()%>">
                </div>
              </td>
              <td nowrap width="41%" > 
                <div align="right">Ultima Fecha de Pago de Capital :</div>
              </td>
              <td nowrap width="19%" > 
                <div align="left"> 
                  <input type="text" readonly  name="E01ISICA1" size="3" maxlength="2" value="<%= invInst.getE01ISICA1()%>">
                  <input type="text" readonly  name="E01ISICA2" size="3" maxlength="2" value="<%= invInst.getE01ISICA2()%>">
                  <input type="text" readonly  name="E01ISICA3" size="3" maxlength="2" value="<%= invInst.getE01ISICA3()%>">
                </div>
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="22%" > 
                <div align="right">Ultima Fecha de Pago de Dividendos :</div>
              </td>
              <td nowrap width="18%" > 
                <div align="left"> 
                  <input type="text" readonly  name="E01ISIDI1" size="3" maxlength="2" value="<%= invInst.getE01ISIDI1()%>">
                  <input type="text" readonly  name="E01ISIDI2" size="3" maxlength="2" value="<%= invInst.getE01ISIDI2()%>">
                  <input type="text" readonly  name="E01ISIDI3" size="3" maxlength="2" value="<%= invInst.getE01ISIDI3()%>">
                </div>
              </td>
              <td nowrap width="41%" > 
                <div align="right"></div>
              </td>
              <td nowrap width="19%" > 
                <div align="left"></div>
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="22%" > 
                <div align="right">Próxima Fecha de Pago de Interes :</div>
              </td>
              <td nowrap width="18%" > 
                <input type="text" readonly  name="E01ISINI1" size="3" maxlength="2" value="<%= invInst.getE01ISINI1()%>">
                <input type="text" readonly  name="E01ISINI2" size="3" maxlength="2" value="<%= invInst.getE01ISINI2()%>">
                
              <input type="text" readonly  name="E01ISINI3" size="3" maxlength="2" value="<%= invInst.getE01ISINI3()%>">
              </td>
              <td nowrap width="41%" > 
                <div align="right">Primera Fecha de Pago de Cupón :</div>
              </td>
              <td nowrap width="19%" > 
                <input type="text" readonly  name="E01ISIFI1" size="3" maxlength="2" value="<%= invInst.getE01ISIFI1()%>">
                
              <input type="text" readonly  name="E01ISIFI2" size="3" maxlength="2" value="<%= invInst.getE01ISIFI2()%>">
                <input type="text" readonly  name="E01ISIFI3" size="3" maxlength="2" value="<%= invInst.getE01ISIFI3()%>">
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="22%" > 
                <div align="right">Próxima Fecha de Pago de Capital :</div>
              </td>
              <td nowrap width="18%" > 
                <div align="left"> 
                  <input type="text" readonly  name="E01ISINC1" size="3" maxlength="2" value="<%= invInst.getE01ISINC1()%>">
                  <input type="text" readonly  name="E01ISINC2" size="3" maxlength="2" value="<%= invInst.getE01ISINC2()%>">
                  <input type="text" readonly  name="E01ISINC3" size="3" maxlength="2" value="<%= invInst.getE01ISINC3()%>">
                </div>
              </td>
              <td nowrap width="41%" > 
                <div align="right"> Primera Fecha de Acumulado :</div>
              </td>
              <td nowrap width="19%" > 
                <input type="text" readonly  name="E01ISIFA1" size="3" maxlength="2" value="<%= invInst.getE01ISIFA1()%>">
                <input type="text" readonly  name="E01ISIFA2" size="3" maxlength="2" value="<%= invInst.getE01ISIFA2()%>">
                <input type="text" readonly  name="E01ISIFA3" size="3" maxlength="2" value="<%= invInst.getE01ISIFA3()%>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="22%" > 
                <div align="right">Póxima Fecha de Pago de Dividendos :</div>
              </td>
              <td nowrap width="18%" > 
                <input type="text" readonly  name="E01ISIND1" size="3" maxlength="2" value="<%= invInst.getE01ISIND1()%>">
                <input type="text" readonly  name="E01ISIND2" size="3" maxlength="2" value="<%= invInst.getE01ISIND2()%>">
                <input type="text" readonly  name="E01ISIND3" size="3" maxlength="2" value="<%= invInst.getE01ISIND3()%>">
              </td>
              <td nowrap width="41%" > 
                <div align="right">Primera Fecha Efectiva :</div>
              </td>
              <td nowrap width="19%" > 
                <input type="text" readonly  name="E01ISIFS1" size="3" maxlength="2" value="<%= invInst.getE01ISIFS1()%>">
                <input type="text" readonly  name="E01ISIFS2" size="3" maxlength="2" value="<%= invInst.getE01ISIFS2()%>">
                <input type="text" readonly  name="E01ISIFS3" size="3" maxlength="2" value="<%= invInst.getE01ISIFS3()%>">
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="22%" > 
                <div align="right">Próxima Fecha de Call :</div>
              </td>
              <td nowrap width="18%" > 
                <input type="text" readonly  name="E01ISICL1" size="3" maxlength="2" value="<%= invInst.getE01ISICL1()%>">
                <input type="text" readonly  name="E01ISICL2" size="3" maxlength="2" value="<%= invInst.getE01ISICL2()%>">
                <input type="text" readonly  name="E01ISICL3" size="3" maxlength="2" value="<%= invInst.getE01ISICL3()%>">
              </td>
              <td nowrap width="41%" > 
                <div align="right">Próxima Fecha de Put :</div>
              </td>
              <td nowrap width="19%" > 
                <input type="text" readonly  name="E01ISIPU1" size="3" maxlength="2" value="<%= invInst.getE01ISIPU1()%>">
                <input type="text" readonly  name="E01ISIPU2" size="3" maxlength="2" value="<%= invInst.getE01ISIPU2()%>">
                <input type="text" readonly  name="E01ISIPU3" size="3" maxlength="2" value="<%= invInst.getE01ISIPU3()%>">
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    
 
    <h4>Emisores</h4>
    <table  class="tableinfo" width="546">
      <tr > 
        <td nowrap> 
          <table cellpadding=2 cellspacing=0 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap width="34%" > 
                <div align="right">Monto Emitido :</div>
              </td>
              <td nowrap width="34%" > 
                <input type="text" readonly  name="E01ISIAMT" size="17" maxlength="16" value="<%= invInst.getE01ISIAMT()%>" onKeyPress="enterDecimal()">
              </td>
              <td nowrap width="34%" > 
                <div align="right">Precio de Emisión :</div>
              </td>
              <td nowrap width="66%" > 
                <input type="text" readonly  name="E01ISIIPR" size="17" maxlength="16" value="<%= invInst.getE01ISIIPR()%>" onKeyPress="enterDecimal()">
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="34%" > 
                <div align="right">Mínimo Precio :</div>
              </td>
              <td nowrap width="34%" > 
                <input type="text" readonly  name="E01ISIMIA" size="17" maxlength="16" value="<%= invInst.getE01ISIMIA()%>" onKeyPress="enterDecimal()">
              </td>
              <td nowrap width="34%" > 
                <div align="right">Precio al Vencimiento :</div>
              </td>
              <td nowrap width="66%" > 
                <input type="text" readonly  name="E01ISIMPR" size="17" maxlength="16" value="<%= invInst.getE01ISIMPR()%>" onKeyPress="enterDecimal()">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="34%" > 
                <div align="right">Monto de Incremento :</div>
              </td>
              <td nowrap width="34%" > 
                <input type="text" readonly  name="E01ISIINA" size="17" maxlength="16" value="<%= invInst.getE01ISIINA()%>" onKeyPress="enterDecimal()">
              </td>
              <td nowrap width="34%" > 
                <div align="right">Precio Promedio Mensual :</div>
              </td>
              <td nowrap width="66%" > 
                <input type="text" readonly  name="E01ISIMAP" size="17" maxlength="16" value="<%= invInst.getE01ISIMAP()%>" onKeyPress="enterDecimal()">
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="34%" > 
                <div align="right">Monto de Participación :</div>
              </td>
              <td nowrap width="34%" > 
                <input type="text" readonly  name="E01ISIPAR" size="17" maxlength="16" value="<%= invInst.getE01ISIPAR()%>" onKeyPress="enterDecimal()">
              </td>
              <td nowrap width="34%" > 
                <div align="right"></div>
              </td>
              <td nowrap width="66%" >&nbsp;</td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <h4>Información de Mantenimiento</h4>
    <table  class="tableinfo" width="546">
      <tr > 
        <td nowrap> 
          <table cellpadding=2 cellspacing=0 width="100%" border="0">
            <tr id="trdark"> 
              <td nowrap width="34%" > 
                <div align="right">Ultima Fecha de Actualización :</div>
              </td>
              <td nowrap width="34%" > 
                <input type="text" readonly  name="E01ISIUP1" size="3" maxlength="2" value="<%= invInst.getE01ISIUP1()%>" >
                <input type="text" readonly  name="E01ISIUP2" size="3" maxlength="2" value="<%= invInst.getE01ISIUP2()%>" >
                <input type="text" readonly  name="E01ISIUP3" size="3" maxlength="2" value="<%= invInst.getE01ISIUP3()%>" >
              </td>
              <td nowrap width="34%" > 
                <div align="right">Estado de Actualización :</div>
              </td>
              <td nowrap width="66%" ><% if(invInst.getE01ISIUST().equals("0")) out.print("OK");
				 else out.print("Error");%> </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  
<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
  <tr bgcolor="#FFFFFF"> 
    <td width="33%">&nbsp;</td>
    <td width="33%">&nbsp;</td>
  </tr>
  <tr bgcolor="#FFFFFF"> 
    <td width="33%"> 
      <div align="center"> 
        <input id="EIBSBTN" type=button name="Submit" value="Imprimir" onClick="doPrint()">
      </div>
    </td>
    <td width="33%">
      <div align="center">
        <input id="EIBSBTN" type=submit name="Submit2" value="Continuar">
      </div>
    </td>
  </tr>
  <tr bgcolor="#FFFFFF"> 
    <td> 
      <div align="center"> </div>
    </td>
    <td>&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>

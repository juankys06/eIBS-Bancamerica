<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Tabla de Gastos y Comisiones de Cobranzas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="sc" class="datapro.eibs.beans.EDC060001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   	builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

</SCRIPT>

</head>

<body>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
    out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  
%>

<h3 align="center">Tabla de Gastos y Comisiones de Cobranzas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sc_basic.jsp,EDC0600"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0600" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <INPUT TYPE=HIDDEN NAME="E01RCOATY" VALUE="<%=sc.getE01RCOATY()%>"> 
  <INPUT TYPE=HIDDEN NAME="OPT" VALUE="<%= userPO.getPurpose()%>">

<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap  > 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap  > 
              <div align="left"> 
                <input type="text" name="E01RCOBNK" size="3" maxlength="2" value="<%= sc.getE01RCOBNK().trim()%>" readonly>
               </div>
            </td>
            <td nowrap  > 
              <div align="right"><b>Número de Tabla :</b> </div>
            </td>
            <td nowrap  > 
              <div align="left"> 
                <input type="text" name="E01RCOTAR" size="3" maxlength="2" value="<%= sc.getE01RCOTAR().trim()%>" readonly>
              </div>
            </td>
            <td nowrap  > 
              <div align="right"><b>Moneda de Tarifa :</b> </div>
            </td>
            <td nowrap  > 
              <div align="left"> 
                <input type="text" name="E01RCOTCY" size="4" maxlength="3" value="<%= sc.getE01RCOTCY().trim()%>" >
                <a href="javascript:GetCurrency('E01RCOTCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
              </div>
            </td>
           </tr>
          <tr id="trclear"> 
            <td nowrap  > 
              <div align="right"><b>Moneda de Cuenta :</b></div>
            </td>
            <td nowrap  > 
              <div align="left"> 
                <input type="text" name="E01RCOACY" size="4" maxlength="3" value="<%= sc.getE01RCOACY().trim()%>" >
                <a href="javascript:GetCurrency('E01RCOACY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
               </div>
            </td>
            <td nowrap  > 
              <div align="right"><b>Descripción :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01RCODSC" size="30" maxlength="25" value="<%= sc.getE01RCODSC().trim()%>" >
              </div>
            </td>
           </tr>
  </table>

<% if (userPO.getPurpose().equals("N")) {%>
<BR>
	<table class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0" >
		<tr id="trdark">
			<TD nowrap>
			<DIV align="right"><B>Copiar Desde </B></DIV>
			</TD>
			<td nowrap></td>
			<td nowrap>
			<div align="right"><b>Tipo de Producto :</b></div>
			</td>
			<td nowrap><input type="text" name="E01CPYATY" size=5
				maxlength="8" value=""> <a
				href="javascript:GetProductRates('E01CPYATY','LC')"> <img
				src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
				align="middle" border="0"></a></td>
			<td nowrap>
			<div align="right"><b>Número de Tabla :</b></div>
			</td>
			<td nowrap><input type="text" name="E01CPYTAR" size="3"
				maxlength="2" value="<%= sc.getE01CPYTAR().trim()%>"></td>
		</tr>
	</table>
<%}%>

  <h4> Cargos</h4> 
  <TABLE class="tableinfo"  cellspacing="0" cellpadding="2" width="100%" border="0">
    <tr id="trdark"> 
      <td nowrap align="center" ><b>Concepto</b></td>
      <td nowrap align="center" ><b>Monto</b></td>
      <td nowrap align="center" ><b>Porcentaje</b></td>
      <td nowrap align="center" ><b>Período</b></td>
      <td nowrap align="center" ><b>Mínimo</b></td>
      <td nowrap align="center" ><b>Máximo</b></td>
      <td nowrap align="center" ><b>IVA</b></td>
    </tr>
      
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Cobranzas Simples :</div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOCRT" size="12" maxlength="9" value="<%= sc.getE01RCOCRT().trim()%>" >
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E01RCOCCP" size="5" maxlength="4" value="<%= sc.getE01RCOCCP().trim()%>" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
      
        <div align="center"> 
          <input type="text" name="E01RCOCMF" size="12" maxlength="11" value="<%= sc.getE01RCOCMF().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOCMX" size="12" maxlength="11" value="<%= sc.getE01RCOCMX().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI01">
            <option value=" " <% if (!(sc.getE01RCOI01().equals("Y")||sc.getE01RCOI01().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI01().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI01().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>
      </td>     
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Documentarias :</div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCODRT" size="12" maxlength="9" value="<%= sc.getE01RCODRT().trim()%>">
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E01RCODCP" size="5" maxlength="4" value="<%= sc.getE01RCODCP().trim()%>" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCODMF" size="12" maxlength="11" value="<%= sc.getE01RCODMF().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCODMX" size="12" maxlength="11" value="<%= sc.getE01RCODMX().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <select name="E01RCOI02">
            <option value=" " <% if (!(sc.getE01RCOI02().equals("Y")||sc.getE01RCOI02().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI02().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI02().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>
 
      </td>
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Directas :</div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCORRT" size="12" maxlength="9" value="<%= sc.getE01RCORRT().trim()%>">
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E01RCORCP" size="5" maxlength="4" value="<%= sc.getE01RCORCP().trim()%>" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCORMF" size="12" maxlength="11" value="<%= sc.getE01RCORMF().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCORMX" size="12" maxlength="11" value="<%= sc.getE01RCORMX().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <select name="E01RCOI03">
            <option value=" " <% if (!(sc.getE01RCOI03().equals("Y")||sc.getE01RCOI03().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI03().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI03().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>
 
      </td>
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Extensión de Validez :</div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOEBR" size="12" maxlength="9" value="<%= sc.getE01RCOEBR().trim()%>">
        </div>
      </td>
      <td nowrap  > 
        <div align="center"> 
          <input type="text" name="E01RCOEBP" size="5" maxlength="4" value="<%= sc.getE01RCOEBP().trim()%>" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOEMF" size="12" maxlength="11" value="<%= sc.getE01RCOEMF().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOEMX" size="12" maxlength="11" value="<%= sc.getE01RCOEMX().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <select name="E01RCOI04">
            <option value=" " <% if (!(sc.getE01RCOI04().equals("Y")||sc.getE01RCOI04().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI04().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI04().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>
 
      </td>
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Gastos Banco Corresponsal :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOPPM" size="12" maxlength="11" value="<%= sc.getE01RCOPPM().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI05">
            <option value=" " <% if (!(sc.getE01RCOI05().equals("Y")||sc.getE01RCOI05().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI05().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI05().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Tracers :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOPRT" size="12" maxlength="11" value="<%= sc.getE01RCOPRT().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI06">
            <option value=" " <% if (!(sc.getE01RCOI06().equals("Y")||sc.getE01RCOI06().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI06().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI06().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Segunda Notificación :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOTR1" size="12" maxlength="11" value="<%= sc.getE01RCOTR1().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI07">
            <option value=" " <% if (!(sc.getE01RCOI07().equals("Y")||sc.getE01RCOI07().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI07().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI07().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Notificaciones Adicionales :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOTR2" size="12" maxlength="11" value="<%= sc.getE01RCOTR2().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Intereses :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOUNP" size="12" maxlength="11" value="<%= sc.getE01RCOUNP().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI08">
            <option value=" " <% if (!(sc.getE01RCOI08().equals("Y")||sc.getE01RCOI08().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI08().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI08().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Enmiendas :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOAMF" size="12" maxlength="11" value="<%= sc.getE01RCOAMF().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI09">
            <option value=" " <% if (!(sc.getE01RCOI09().equals("Y")||sc.getE01RCOI09().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI09().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI09().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Swift Aviso de Pago :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOADP" size="12" maxlength="11" value="<%= sc.getE01RCOADP().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI10">
            <option value=" " <% if (!(sc.getE01RCOI10().equals("Y")||sc.getE01RCOI10().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI10().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI10().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Timbres :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOESD" size="12" maxlength="11" value="<%= sc.getE01RCOESD().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI11">
            <option value=" " <% if (!(sc.getE01RCOI11().equals("Y")||sc.getE01RCOI11().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI11().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI11().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Carta de Indemnidad :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOLIF" size="12" maxlength="11" value="<%= sc.getE01RCOLIF().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      	<div align="center"> 
          <input type="text" name="E01RCOLIP" size="5" maxlength="4" value="<%= sc.getE01RCOLIP().trim()%>" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI12">
            <option value=" " <% if (!(sc.getE01RCOI12().equals("Y")||sc.getE01RCOI12().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI12().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI12().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
    <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Swift Aviso Aceptacion :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOPDA" size="12" maxlength="11" value="<%= sc.getE01RCOPDA().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      	<div align="center"> 
          <input type="text" name="E01RCOPDP" size="5" maxlength="4" value="<%= sc.getE01RCOPDP().trim()%>" onkeypress="enterInteger()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI13">
            <option value=" " <% if (!(sc.getE01RCOI13().equals("Y")||sc.getE01RCOI13().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI13().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI13().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Swift de Remesa :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOWTF" size="12" maxlength="11" value="<%= sc.getE01RCOWTF().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI14">
            <option value=" " <% if (!(sc.getE01RCOI14().equals("Y")||sc.getE01RCOI14().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI14().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI14().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>   
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Portes :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOPST" size="12" maxlength="11" value="<%= sc.getE01RCOPST().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI15">
            <option value=" " <% if (!(sc.getE01RCOI15().equals("Y")||sc.getE01RCOI15().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI15().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI15().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Portes - Correo Certificado :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOPTC" size="12" maxlength="11" value="<%= sc.getE01RCOPTC().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Servicio Courier - Local :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOCOD" size="12" maxlength="11" value="<%= sc.getE01RCOCOD().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI16">
            <option value=" " <% if (!(sc.getE01RCOI16().equals("Y")||sc.getE01RCOI16().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI16().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI16().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Servicio Courier - Internacional :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOCOF" size="12" maxlength="11" value="<%= sc.getE01RCOCOF().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
       </td>
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Servicio de Fax/Telefono - Local :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOFXD" size="12" maxlength="11" value="<%= sc.getE01RCOFXD().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI17">
            <option value=" " <% if (!(sc.getE01RCOI17().equals("Y")||sc.getE01RCOI17().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI17().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI17().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
      <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Servicio de Fax/Telefono - Internacional :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOFXF" size="12" maxlength="11" value="<%= sc.getE01RCOFXF().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
    </tr>
       <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Swift Acuse de Recibo :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOLTX" size="12" maxlength="11" value="<%= sc.getE01RCOLTX().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI18">
            <option value=" " <% if (!(sc.getE01RCOI18().equals("Y")||sc.getE01RCOI18().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI18().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI18().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
       <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Telex - Mensaje Corto :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOSTX" size="12" maxlength="11" value="<%= sc.getE01RCOSTX().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
    </tr>
       <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Mensaje Swift :</div>
      </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01RCOSWF" size="12" maxlength="11" value="<%= sc.getE01RCOSWF().trim()%>" onkeypress="enterDecimal()">
        </div>
      </td>
      <td nowrap > 
      </td>
      <td nowrap  > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
      </td>
      <td nowrap > 
        <div align="center"> 
          <select name="E01RCOI19">
            <option value=" " <% if (!(sc.getE01RCOI19().equals("Y")||sc.getE01RCOI19().equals("N"))) out.print("selected"); %>></option>
            <option value="Y" <% if (sc.getE01RCOI19().equals("Y")) out.print("selected"); %>>Si</option>
            <option value="N" <% if (sc.getE01RCOI19().equals("N")) out.print("selected"); %>>No</option>
          </select>          
        </div>      
      </td>
    </tr>
</TABLE> 

  <h4> Cuentas Contables</h4> 
  <TABLE class="tableinfo" cellspacing="0" cellpadding="2" width="100%" border="0">
    <tr id="trdark"> 
      <td nowrap align="center" ><b>Concepto</b></td>
      <td nowrap align="center" ><b>Cuenta</b></td>
      <td nowrap align="center" ><b>Descripción</b></td>
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Cobranzas Simples :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGCC" size="18" maxlength="16" value="<%= sc.getE01RCOGCC().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGCC',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGCC" size="40" maxlength="35" value="<%= sc.getE01DSCGCC().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Documentarias :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGDC" size="18" maxlength="16" value="<%= sc.getE01RCOGDC().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGDC',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGDC" size="40" maxlength="35" value="<%= sc.getE01DSCGDC().trim()%>" readonly>
        </div>
      </td>  
    </tr>
      <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Directas :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGRD" size="18" maxlength="16" value="<%= sc.getE01RCOGRD().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGRD',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGRD" size="40" maxlength="35" value="<%= sc.getE01DSCGRD().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Extensión de Validez :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGEV" size="18" maxlength="16" value="<%= sc.getE01RCOGEV().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGEV',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGEV" size="40" maxlength="35" value="<%= sc.getE01DSCGEV().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Gasto Banco Corresponsal :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGPP" size="18" maxlength="16" value="<%= sc.getE01RCOGPP().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGPP',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGPP" size="40" maxlength="35" value="<%= sc.getE01DSCGPP().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Tracers :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGPR" size="18" maxlength="16" value="<%= sc.getE01RCOGPR().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGPR',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGPR" size="40" maxlength="35" value="<%= sc.getE01DSCGPR().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Notificaciones :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGTR" size="18" maxlength="16" value="<%= sc.getE01RCOGTR().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGTR',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGTR" size="40" maxlength="35" value="<%= sc.getE01DSCGTR().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Intereses :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGUI" size="18" maxlength="16" value="<%= sc.getE01RCOGUI().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGUI',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGUI" size="40" maxlength="35" value="<%= sc.getE01DSCGUI().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Enmiendas :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGAM" size="18" maxlength="16" value="<%= sc.getE01RCOGAM().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGAM',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGAM" size="40" maxlength="35" value="<%= sc.getE01DSCGAM().trim()%>" readonly>
        </div>
      </td>  
    </tr>
      <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Swift Aviso de Pago :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGAP" size="18" maxlength="16" value="<%= sc.getE01RCOGAP().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGAP',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGAP" size="40" maxlength="35" value="<%= sc.getE01DSCGAP().trim()%>" readonly>
        </div>
      </td>  
    </tr>
      <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Timbres :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGED" size="18" maxlength="16" value="<%= sc.getE01RCOGED().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGED',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGED" size="40" maxlength="35" value="<%= sc.getE01DSCGED().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Carta de Indemnidad :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGLI" size="18" maxlength="16" value="<%= sc.getE01RCOGLI().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGLI',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGLI" size="40" maxlength="35" value="<%= sc.getE01DSCGLI().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Swift Aviso Aceptacion:</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGPD" size="18" maxlength="16" value="<%= sc.getE01RCOGPD().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGPD',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGPD" size="40" maxlength="35" value="<%= sc.getE01DSCGPD().trim()%>" readonly>
        </div>
      </td>  
    </tr>   
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Swift de Remesa :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGWT" size="18" maxlength="16" value="<%= sc.getE01RCOGWT().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGWT',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGWT" size="40" maxlength="35" value="<%= sc.getE01DSCGWT().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Portes :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGPO" size="18" maxlength="16" value="<%= sc.getE01RCOGPO().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGPO',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGPO" size="40" maxlength="35" value="<%= sc.getE01DSCGPO().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Servicio de Courier :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGCR" size="18" maxlength="16" value="<%= sc.getE01RCOGCR().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGCR',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGCR" size="40" maxlength="35" value="<%= sc.getE01DSCGCR().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Servicio de Fax/Telefono :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGFX" size="18" maxlength="16" value="<%= sc.getE01RCOGFX().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGFX',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGFX" size="40" maxlength="35" value="<%= sc.getE01DSCGFX().trim()%>" readonly>
        </div>
      </td>  
    </tr>
     <tr id="trdark"> 
      <td nowrap > 
        <div align="right">Swift Acuse de Recibo /Telex :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGTX" size="18" maxlength="16" value="<%= sc.getE01RCOGTX().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGTX',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGTX" size="40" maxlength="35" value="<%= sc.getE01DSCGTX().trim()%>" readonly>
        </div>
      </td>  
    </tr>
       <tr id="trclear"> 
      <td nowrap > 
        <div align="right">Mensaje Swift :</div>
      </td>
      <td nowrap > 
         <div align="center"> 
          <input type="text" name="E01RCOGSW" size="18" maxlength="16" value="<%= sc.getE01RCOGSW().trim()%>" onkeypress="enterInteger()">
          <a href="javascript:GetLedger('E01RCOGSW',document.forms[0].E01RCOBNK.value,document.forms[0].E01RCOACY.value,'')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align=bottom border="0" ></a> 
        </div>
       </td>
      <td nowrap > 
        <div align="center"> 
          <input type="text" name="E01DSCGSW" size="40" maxlength="35" value="<%= sc.getE01DSCGSW().trim()%>" readonly>
        </div>
      </td>  
    </tr>
</TABLE> 

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

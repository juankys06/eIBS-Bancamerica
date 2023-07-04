<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Treasury Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="deal" class="datapro.eibs.beans.EDL0120DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT> 
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

function Calculate(){

  document.forms[0].action = "<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120?SCREEN=4700";
  document.forms[0].submit();

}
function GetProductB(name,desc,app,bank)
{
	page= prefix +language + "EWD0008B_client_help_container.jsp"
	fieldName=name;
	fieldAux1 = desc; 
	AppCode = app;
	ProductBank = bank;				
	CenterWindow(page,600,400,1);
}

</script>
</head>
<body >
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<h3 align="center"> Inversiones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_tps.jsp, EDL0120"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120" >
<%
String ogen = "";
if (deal.getE01DLSSTS().equals("T")) {
	ogen = "Tesorería";
} else if (deal.getE01DLSSTS().equals("F")) {
	ogen = "Fideicomiso";
}  else if (deal.getE01DLSSTS().equals("E")) {
	ogen = "FEM";
}  else if (deal.getE01DLSSTS().equals("R")) {
	ogen = "Terceros";
}
%>
  <input type=HIDDEN name="SCREEN" value="18">
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="hidden" name="D01DLSCP1"  value="<%= deal.getD01DLSCP1()%>" >
                <%= deal.getD01DLSCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="D01DLSCP2"  value="<%= deal.getD01DLSCP2()%>" >
              <%= deal.getD01DLSCP2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" width="85%" > <%= deal.getD01DLSCP3()%> 
              </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="E01DLSSTS"  value="<%= deal.getE01DLSSTS()%>" >
              <%= ogen%></td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo" width="597">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha Pacto :</div>
            </td>
            <td nowrap > <%= Util.formatDate(deal.getE01DLSDD1(),deal.getE01DLSDD2(),deal.getE01DLSDD3())%></td>
            <td nowrap > 
              <div align="right"> 
                <input type="hidden" name="E01DLSSBT" value="<%= deal.getE01DLSSBT()%>">
				Tipo de Operación :
              </div>
            </td>
            <td nowrap > 
              <input type="radio" name="CE01DLSSBT" value="PU" onClick="document.forms[0].E01DLSSBT.value='PU'"
			  <%if(deal.getE01DLSSBT().equals("PU")) out.print("checked");%>>
              Compra 
              <input type="radio" name="CE01DLSSBT" value="SL" onClick="document.forms[0].E01DLSSBT.value='SL'"
			  <%if(deal.getE01DLSSBT().equals("SL")) out.print("checked");%>>
              Emisi&oacute;n</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
            </td>
            <td nowrap > 
              <div align="right"> Moneda :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLSCCY" size="4" maxlength="3" value="<%= deal.getE01DLSCCY().trim()%>" >
              <a href="javascript:GetCurrency('E01DLSCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Valor Nominal :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLSAMN" size="15" maxlength="13" value="<%= deal.getE01DLSAMN()%>" 
			  onKeyPress="enterDecimal()">
            </td>
            <td nowrap > 
              <div align="right">Tasa :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01DLSRA1" size="11" maxlength="11" value="<%= deal.getE01DLSRA1()%>" 
			  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DLSVD1" size="3" maxlength="2" value="<%= deal.getE01DLSVD1().trim()%>" 
			  >
              <input type="text" name="E01DLSVD2" size="3" maxlength="2" value="<%= deal.getE01DLSVD2().trim()%>" 
			  >
              <input type="text" name="E01DLSVD3" size="3" maxlength="2" value="<%= deal.getE01DLSVD3().trim()%>" 
			  >
            </td>
            <td nowrap align="right">Fecha de Vencimiento :</td>
            <td nowrap> 
              <input type="text" name="E01DLSMA1" size="3" maxlength="2" value="<%= deal.getE01DLSMA1().trim()%>" 
			  >
              <input type="text" name="E01DLSMA2" size="3" maxlength="2" value="<%= deal.getE01DLSMA2().trim()%>" 
			  >
              <input type="text" name="E01DLSMA3" size="3" maxlength="2" value="<%= deal.getE01DLSMA3().trim()%>" 
			  >
              <input type="hidden" name="E01DLSCMM"  value="<%= deal.getE01DLSCMM()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Plazo :</div>
            </td>
            <td nowrap width="25%" > 
              <input type="text" name="E01DLSROY" size="4" maxlength="3" value="<%= deal.getE01DLSROY().trim()%>" >
              <select name="E01DLSODA">
                <option value="D" <% if (deal.getE01DLSODA().equals("D")) out.print("selected"); %>> 
                Dia</option>
                <option value="M" <% if (deal.getE01DLSODA().equals("M")) out.print("selected"); %>> 
                Mes</option>
                <option value="Y" <% if (deal.getE01DLSODA().equals("Y")) out.print("selected"); %>> 
                Año</option>
              </select>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tipo de Calculo de Interes:</div>
            </td>
            <td nowrap width="27%" > 
              <select  name="E01DEARRP">
                <option value=" " <% if (!(deal.getE01DEARRP().equals("1") ||deal.getE01DEARRP().equals("2")||deal.getE01DEARRP().equals("3")||deal.getE01DEARRP().equals("4")||deal.getE01DEARRP().equals("5")||deal.getE01DEARRP().equals("6"))) out.print("selected"); %>></option>
                <option value="1" <% if (deal.getE01DEARRP().equals("1")) out.print("selected"); %>> 
                Actual/actual (en periodo)</option>
                <option value="2" <% if (deal.getE01DEARRP().equals("2")) out.print("selected"); %>> 
                Actual/365</option>
                <option value="3" <% if (deal.getE01DEARRP().equals("3")) out.print("selected"); %>> 
                Actual/365 (366 en bisiesto)</option>
                <option value="4" <% if (deal.getE01DEARRP().equals("4")) out.print("selected"); %>> 
                Actual/360</option>
                <option value="5" <% if (deal.getE01DEARRP().equals("5")) out.print("selected"); %>> 
                30/360</option>
                <option value="6" <% if (deal.getE01DEARRP().equals("6")) out.print("selected"); %>> 
                30E/360</option>
              </select>
            </td>
          </tr>
          <tr id="trclear"> 
          <% if(deal.getH01FLGWK3().equals("Y")){%>
          	<td nowrap width="21%" > 
              <div align="right">Producto : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E01DLSPRO" size="5" maxlength="4" value="<%= deal.getE01DLSPRO()%>">
              <a href="javascript:GetProduct('E01DLSPRO','CD','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
              <input type="text" name="D01PRDDSC" size="30" value="<%= deal.getD01PRDDSC()%>" readonly>
            </td>
           <%} else {%>
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap >&nbsp; </td>
           <%}%> 
            <td nowrap > 
              <div align="right"> </div>
            </td>
            <td nowrap > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01DLSOT1" size="70" maxlength="60" value="<%= deal.getE01DLSOT1().trim()%>" 
			  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" name="E01DLSOT2" size="70" maxlength="60" value="<%= deal.getE01DLSOT2().trim()%>" 
			  >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="3" ><%= deal.getE01DLSDID().trim()%> - <%= deal.getD01USRDSC().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap >&nbsp;</td>
            <td nowrap colspan="3" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Interes :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01DLSAM5" size="15" maxlength="13" value="<%= deal.getE01DLSAM5()%>" 
			  readonly>
              </div>
            </td>
            <td nowrap > 
              <div align="right">Capital + Interes :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01DLSAM1" size="15" maxlength="13" value="<%= deal.getE01DLSAM1()%>" 
			  readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark">
            <td nowrap>&nbsp;</td>
            <td nowrap  colspan="2"> 
              <div align="center"><b>Monto L&iacute;mite</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto L&iacute;mite <br>Disponible</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Monto L&iacute;mite <br>Final</b></div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap>L&iacute;neas de Cr&eacute;dito</td>
            <td nowrap  colspan="2"> 
              <div align="right"><%= Util.fcolorCCY(deal.getD01LIMAMT())%>:</div>
            </td>
            <td nowrap > 
              <div align="right"><%= Util.fcolorCCY(deal.getD01LIMAVL())%></div>
            </td>
            <td nowrap > 
              <div align="right"><b><%= Util.fcolorCCY(deal.getD01LIMEND())%></b></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input type="checkbox" name="H01FLGWK1" value="1" >
          Aceptar con Errores</div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="16%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
        </div>
      </td>
      <td width="17%">
        <div align="center">
          <input id="EIBSBTN" type=button name="Submit2" value="Calcular" onClick="Calculate()">
        </div>
      </td>
    </tr>
  </table>
  
  </form>
<font size="2">
<input type="hidden" name="D01DLSCP3"  value="<%= deal.getD01DLSCP3()%>">
</font>
</body>
</html>

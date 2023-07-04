<html>
<head>
<title>Mercadeo</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "marketing" class= "datapro.eibs.beans.ESD008009Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

     <% 
   if ( userPO.getOption().equals("CLIENT_P") ) {
   %>
		builtNewMenu(client_inq_personal_opt);
  <%      
   }
   else
   {
   %>
		builtNewMenu(client_inq_corp_opt);
   <%
   }
   %>

</SCRIPT>

</head>

<body bgcolor="#FFFFFF">

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%> 

<form>
  <h3 align="center">Mercadeo</h3>
  <h4 align="left">¿ Le gustar&iacute;a Invertir ? </h4>
<table class="tableinfo">
    <tr > 
      <td nowrap wrap >
        <table  cellpadding="3" cellspacing="0" width="100%" border="0" align="center">
          <tr id="trdark"> 
            <td nowrap width="50%"> 
              <div align="right">¿ Cu&aacute;l es su elecci&oacute;n en Inversiones 
                ? </div>
            </td>
            <td nowrap width="50%">&nbsp;</td>
          </tr>
          <tr> 
            <td nowrap width="50%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="50%"> 
              <input type="hidden" name="E09INV" value="<%= marketing.getE09INV()%>">
              <input type="radio"  disabled name="CE09INV" value="1" onClick="document.forms[0].E09INV.value='1'"
			  <%if(marketing.getE09INV().equals("1")) out.print("checked");%>>
              Productos de Renta Fija </td>
          </tr>
          <tr> 
            <td nowrap width="50%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="50%"> 
              <input type="radio"  disabled name="CE09INV" value="2" onClick="document.forms[0].E09INV.value='2'"
			  <%if(marketing.getE09INV().equals("2")) out.print("checked");%>>
              Productos de Renta Variable </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="50%"> 
              <div align="right">¿ Qu&eacute; riesgos asumir&iacute;a ?:</div>
            </td>
            <td nowrap width="50%">&nbsp;</td>
          </tr>
          <tr> 
            <td nowrap width="50%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="50%"> 
              <input type="hidden" name="E09RSG" value="<%= marketing.getE09RSG()%>">
              <input type="radio"  disabled name="CE09RSG" value="1" onClick="document.forms[0].E09RSG.value='1'"
			  <%if(marketing.getE09RSG().equals("1")) out.print("checked");%>>
              Alto</td>
          </tr>
          <tr> 
            <td nowrap width="50%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="50%"> 
              <input type="radio"  disabled name="CE09RSG" value="2" onClick="document.forms[0].E09RSG.value='2'"
			  <%if(marketing.getE09RSG().equals("2")) out.print("checked");%>>
              Mediano </td>
          </tr>
          <tr> 
            <td nowrap width="50%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="50%"> 
              <input type="radio"  disabled name="CE09RSG" value="2" onClick="document.forms[0].E09RSG.value='2'"
			  <%if(marketing.getE09RSG().equals("3")) out.print("checked");%>>
              No me arriesgar&iacute;a</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Actividades</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap wrap >
        <table cellpadding="3" cellspacing="0" width="100%" border="0" align="center">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Canal de Televisi&oacute;n :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" readonly name="E09CTV" <% if (marketing.getF09CTV().equals("Y")) out.print("id=\"txtchanged\""); %> value="<%= marketing.getE09CTV().trim()%>" size="20" maxlength="20">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Deporte Preferido :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" readonly name="E09DPA" <% if (marketing.getF09DPA().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="20" value="<%= marketing.getE09DPA().trim()%>">
            </td>
          </tr>
          <tr> 
            <td nowrap width="25%"> 
              <div align="right">Canal de Cable :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" readonly name="E09CNT" <% if (marketing.getF09CNT().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="20" value="<%= marketing.getE09CNT().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Deporte que Pr&aacute;ctica :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" readonly name="E09DPP" <% if (marketing.getF09DPP().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="20" value="<%= marketing.getE09DPP().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Radio AM :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" readonly name="E09RD1" <% if (marketing.getF09RD1().equals("Y")) out.print("id=\"txtchanged\""); %> value="<%= marketing.getE09RD1().trim()%>" size="20" maxlength="20">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Peri&oacute;dico Preferido :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" readonly name="E09PER" <% if (marketing.getF09PER().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="20" value="<%= marketing.getE09PER().trim()%>">
            </td>
          </tr>
          <tr> 
            <td nowrap width="25%"> 
              <div align="right">Radio FM :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" readonly name="E09RD2" <% if (marketing.getF09RD2().equals("Y")) out.print("id=\"txtchanged\""); %> value="<%= marketing.getE09RD2().trim()%>" size="20" maxlength="20">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Club Social :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" readonly name="E09CLB" <% if (marketing.getF09CLB().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="20" value="<%= marketing.getE09CLB().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Pasatiempo Preferido :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" readonly name="E09PTP" <% if (marketing.getF09PTP().equals("Y")) out.print("id=\"txtchanged\""); %> value="<%= marketing.getE09PTP().trim()%>" size="20" maxlength="20">
            </td>
            <td nowrap width="25%"> 
              <div align="right">Evento Cultural :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" readonly name="E09EVC" <% if (marketing.getF09EVC().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="20" value="<%= marketing.getE09EVC().trim()%>">
            </td>
          </tr>
          <tr> 
            <td nowrap width="25%" rowspan="4"> 
              <div align="right">Obras de Caridad :</div>
            </td>
            <td nowrap width="25%" rowspan="4"> 
              <input type="text" readonly name="E09OBR" <% if (marketing.getF09OBR().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="20" value="<%= marketing.getE09OBR().trim()%>">
            </td>
            <td nowrap width="25%" rowspan="4"> 
              <div align="right">Idiomas Conocidos :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" readonly name="E09LN1" <% if (marketing.getF09LN1().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="15" value="<%= marketing.getE09LN1().trim()%>">
            </td>
          </tr>
          <tr>
            <td nowrap width="25%">
              <input type="text" readonly name="E09LN2" <% if (marketing.getF09LN2().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="15" value="<%= marketing.getE09LN2().trim()%>">
            </td>
          </tr>
          <tr>
            <td nowrap width="25%">
              <input type="text" readonly name="E09LN3" <% if (marketing.getF09LN3().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="15" value="<%= marketing.getE09LN3().trim()%>">
            </td>
          </tr>
          <tr>
            <td nowrap width="25%">
              <input type="text" readonly name="E09LN4" <% if (marketing.getF09LN3().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="15" value="<%= marketing.getE09LN4().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Preferencias en Internet</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap wrap >
        <table cellpadding="3" cellspacing="0" width="100%" border="0" align="center">
          <tr id="trdark"> 
            <td nowrap width="48%"> 
              <div align="right">¿Tiene conexi&oacute;n a Internet en la actualidad 
                ? :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="hidden" name="E09INT" value="<%= marketing.getE09INT()%>">
              <input type="radio"  disabled name="CE09INT" value="Y" onClick="document.forms[0].E09INT.value='Y'"
			  <%if(marketing.getE09INT().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio"  disabled name="CE09INT" value="N" onClick="document.forms[0].E09INT.value='N'"
			  <%if(marketing.getE09INT().equals("N")) out.print("checked");%>>
              No </td>
            <td nowrap width="26%">&nbsp;</td>
          </tr>
          <tr> 
            <td nowrap width="48%"> 
              <div align="right">¿Qué navegador de Internet utiliza ? :</div>
            </td>
            <td nowrap width="26%">
              <input type="text" readonly <% if (marketing.getF09NVG().equals("Y")) out.print("id=\"txtchanged\""); %> name="E09NVG" size="25" maxlength="25" 
				  value="<% if (marketing.getE09NVG().equals("1")) out.print("Microsoft Internet Explorer");
							else if (marketing.getE09NVG().equals("2")) out.print("Netscape Navigator");
							else out.print("Otro");%>" 
				>
            </td>
            <td nowrap width="26%">&nbsp; </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="48%"> 
              <div align="right">¿Ha hecho compra por Internet anteriormente ? :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="hidden" name="E09CIN" value="<%= marketing.getE09CIN()%>">
              <input type="radio"  disabled name="CE09CIN" value="Y" onClick="document.forms[0].E09CIN.value='Y'"
			  <%if(marketing.getE09CIN().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio"  disabled name="CE09CIN" value="N" onClick="document.forms[0].E09CIN.value='N'"
			  <%if(marketing.getE09CIN().equals("N")) out.print("checked");%>>
              No </td>
            <td nowrap width="26%">&nbsp;</td>
          </tr>
          <tr> 
            <td nowrap width="48%"> 
              <div align="right">¿ Le interesar&iacute;a comprar por Internet ? :</div>
            </td>
            <td nowrap width="26%"> 
              <input type="hidden" name="E09DCI" value="<%= marketing.getE09DCI()%>">
              <input type="radio"  disabled name="CE09DCI" value="Y" onClick="document.forms[0].E09DCI.value='Y'"
			  <%if(marketing.getE09DCI().equals("Y")) out.print("checked");%>>
              S&iacute; 
              <input type="radio"  disabled name="CE09DCI" value="N" onClick="document.forms[0].E09DCI.value='N'"
			  <%if(marketing.getE09DCI().equals("N")) out.print("checked");%>>
              No </td>
            <td nowrap width="26%">&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="48%"> 
              <div align="right">P&aacute;ginas preferidas que visita en Internet 
                : </div>
            </td>
            <td nowrap width="26%">&nbsp; </td>
            <td nowrap width="26%">&nbsp; </td>
          </tr>
          <tr> 
            <td nowrap width="48%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PFI"  onClick="document.forms[0].E09PFI.value='X'"
				<%if(marketing.getE09PFI().equals("X")) out.print("checked");%>>
              Finanzas </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PCE"  onClick="document.forms[0].E09PCE.value='X'"
				<%if(marketing.getE09PCE().equals("X")) out.print("checked");%>>
              Ciencia </td>
          </tr>
          <tr> 
            <td nowrap width="48%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PTE"  onClick="document.forms[0].E09PTE.value='X'"
				<%if(marketing.getE09PTE().equals("X")) out.print("checked");%>>
              Tecnolog&iacute;a </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PCO"  onClick="document.forms[0].E09PCO.value='X'"
				<%if(marketing.getE09PCO().equals("X")) out.print("checked");%>>
              Computaci&oacute;n </td>
          </tr>
          <tr> 
            <td nowrap width="48%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PEL"  onClick="document.forms[0].E09PEL.value='X'"
				<%if(marketing.getE09PEL().equals("X")) out.print("checked");%>>
              Electr&oacute;nica </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PNE"  onClick="document.forms[0].E09PNE.value='X'"
				<%if(marketing.getE09PNE().equals("X")) out.print("checked");%>>
              Negocios </td>
          </tr>
          <tr> 
            <td nowrap width="48%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PCI"  onClick="document.forms[0].E09PCI.value='X'"
				<%if(marketing.getE09PCI().equals("X")) out.print("checked");%>>
              Cine y Televisi&oacute;n</td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PAU"  onClick="document.forms[0].E09PAU.value='X'"
				<%if(marketing.getE09PAU().equals("X")) out.print("checked");%>>
              Automovilismo </td>
          </tr>
          <tr> 
            <td nowrap width="48%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PDE"  onClick="document.forms[0].E09PDE.value='X'"
				<%if(marketing.getE09PDE().equals("X")) out.print("checked");%>>
              Deportes </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PFO"  onClick="document.forms[0].E09PFO.value='X'"
				<%if(marketing.getE09PFO().equals("X")) out.print("checked");%>>
              Foros de Opini&oacute;n </td>
          </tr>
          <tr> 
            <td nowrap width="48%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PFA"  onClick="document.forms[0].E09PFA.value='X'"
				<%if(marketing.getE09PFA().equals("X")) out.print("checked");%>>
              Familia </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09PMU"  onClick="document.forms[0].E09PMU.value='X'"
				<%if(marketing.getE09PMU().equals("X")) out.print("checked");%>>
              M&uacute;sica </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="48%"> 
              <div align="right">Prefiere utilizar internet para comprar :</div>
            </td>
            <td nowrap width="26%">&nbsp;</td>
            <td nowrap width="26%">&nbsp;</td>
          </tr>
          <tr> 
            <td nowrap width="48%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09CIV"  onClick="document.forms[0].E09CIV.value='X'"
				<%if(marketing.getE09CIV().equals("X")) out.print("checked");%>>
              Inversiones </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09CLI"  onClick="document.forms[0].E09CLI.value='X'"
				<%if(marketing.getE09CLI().equals("X")) out.print("checked");%>>
              Libros </td>
          </tr>
          <tr> 
            <td nowrap width="48%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09CTE"  onClick="document.forms[0].E09CTE.value='X'"
				<%if(marketing.getE09CTE().equals("X")) out.print("checked");%>>
              Tecnolog&iacute;a </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09CPA"  onClick="document.forms[0].E09CPA.value='X'"
				<%if(marketing.getE09CPA().equals("X")) out.print("checked");%>>
              Pasajes A&eacute;reos</td>
          </tr>
          <tr> 
            <td nowrap width="48%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09CRE"  onClick="document.forms[0].E09CRE.value='X'"
				<%if(marketing.getE09CRE().equals("X")) out.print("checked");%>>
              Revistas </td>
            <td nowrap width="26%"> 
              <input type="checkbox" disabled name="E09CVI"  onClick="document.forms[0].E09CVI.value='X'"
				<%if(marketing.getE09CVI().equals("X")) out.print("checked");%>>
              Videos </td>
          </tr>
          <tr> 
            <td nowrap width="48%" height="26">&nbsp;</td>
            <td nowrap width="26%" height="26"> 
              <input type="checkbox" disabled name="E09CAU"  onClick="document.forms[0].E09CAU.value='X'"
				<%if(marketing.getE09CAU().equals("X")) out.print("checked");%>>
              Autom&oacute;viles </td>
            <td nowrap width="26%" height="26"> 
              <input type="checkbox" disabled name="E09CTR"  onClick="document.forms[0].E09CTR.value='X'"
				<%if(marketing.getE09CTR().equals("X")) out.print("checked");%>>
              Otros </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
 
  <p>&nbsp;</p>
</form>
</body>
</html>

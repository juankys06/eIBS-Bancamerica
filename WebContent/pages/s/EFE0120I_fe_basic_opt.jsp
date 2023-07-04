<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Foreign Exchange Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0120DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

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
<h3 align="center"> Consulta - Opciones de Moneda<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_basic_opt.jsp,EFE120I"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120P" >
<%
String ogen = "";
if (fex.getE01FESTIN().equals("T")) {
	ogen = "Tesorería";
} else if (fex.getE01FESTIN().equals("F")) {
	ogen = "Fideicomiso";
}  else if (fex.getE01FESTIN().equals("E")) {
	ogen = "FEM";
}  else if (fex.getE01FESTIN().equals("R")) {
	ogen = "Terceros";
}
%>
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b> 
                <input type=HIDDEN name="SCREEN" value="12">
                Contraparte :</b></div>
            </td>
            <td nowrap width="85%" > 
              <div align="left"> 
                <input type="hidden" name="D01FESCP1"  value="<%= fex.getD01FESCP1()%>" >
                <%= fex.getD01FESCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap width="85%" >
              <input type="hidden" name="D01FESCP2"  value="<%= fex.getD01FESCP2()%>" >
              <%= fex.getD01FESCP2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="85%" >
              <input type="hidden" name="D01FESCP3"  value="<%= fex.getD01FESCP3()%>" >
              <%= fex.getD01FESCP3()%> </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap width="85%" >
              <input type="hidden" name="E01FESTIN"  value="<%= fex.getE01FESTIN()%>" >
              <%= ogen%></td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="23%" >&nbsp;</td>
            <td nowrap colspan="4" > 
              <div align="right">Fecha Pacto :<%= Util.formatDate(fex.getE01FESDD1(),fex.getE01FESDD2(),fex.getE01FESDD3())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="4" > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Acci&oacute;n Tomada :</div>
            </td>
            <td nowrap> 
              <input type="hidden" name="E01FESSBT" value="<%= fex.getE01FESSBT()%>">
              <input type="radio" name="CE01FESSBT" value="BC" onClick="document.forms[0].E01FESSBT.value='BC'"
			  <%if(fex.getE01FESSBT().equals("BC")) out.print("checked");%> disabled>
              Buy Call Option 
              <input type="radio" name="CE01FESSBT" value="WC" onClick="document.forms[0].E01FESSBT.value='WC'"
			  <%if(fex.getE01FESSBT().equals("WC")) out.print("checked");%> disabled>
              Write Call Option 
              <input type="radio" name="CE01FESSBT" value="BP" onClick="document.forms[0].E01FESSBT.value='BP'"
			  <%if(fex.getE01FESSBT().equals("BP")) out.print("checked");%> disabled>
              Buy a Put 
              <input type="radio" name="CE01FESSBT" value="WP" onClick="document.forms[0].E01FESSBT.value='WP'"
			  <%if(fex.getE01FESSBT().equals("WP")) out.print("checked");%> disabled>
              Write a Put 
            </td>
            <td nowrap><div align="right">Concepto : </div></td>
            <td nowrap >
            	<input type="text" readonly name="E01FESBRC" size="5" maxlength="4" value="<%= fex.getE01FESBRC().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Moneda Primaria :</div>
            </td>
            <td nowrap width="38%" > 
              <input type="text" readonly  name="E01FESCCY" size="4" maxlength="3" value="<%= fex.getE01FESCCY().trim()%>" >
              <input type="text" readonly  name="E01FESAMN" size="15" maxlength="13" value="<%= fex.getE01FESAMN()%>" 
			   onKeyPress="enterDecimal()">
            </td>
            <td nowrap width="16%" > 
              <div align="right">Tipo de Opci&oacute;n :</div>
            </td>
            <td nowrap colspan="2" width="23%" > 
              <div align="left"> 
                <input type="hidden" name="E01FESOPT" value="<%= fex.getE01FESOPT()%>">
                <input type="radio" name="CE01FESOPT" value="1" onClick="document.forms[0].E01FESOPT.value='1'"
			  <%if(fex.getE01FESOPT().equals("1")) out.print("checked");%> disabled>
                Americana 
                <input type="radio" name="CE01FESOPT" value="2" onClick="document.forms[0].E01FESOPT.value='2'"
			  <%if(fex.getE01FESOPT().equals("2")) out.print("checked");%> disabled>
                Europea</div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Moneda Base :</div>
            </td>
            <td nowrap width="38%" > 
              <input type="text" readonly  name="E01FESDCY" size="4" maxlength="3" value="<%= fex.getE01FESDCY().trim()%>" >
              <input type="text" readonly  name="E01FESDAM" size="15" maxlength="13" value="<%= fex.getE01FESDAM()%>" 
			  onKeyPress="enterDecimal()">
            </td>
            <td nowrap width="16%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2" width="23%" > 
              <div align="left"> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Strike Price : </div>
            </td>
            <td nowrap width="38%" > 
              <input type="text" readonly  name="E01FESEXR" size="15" maxlength="13" value="<%= fex.getE01FESEXR()%>" 
			 onKeyPress="enterDecimal()" >
            </td>
            <td nowrap width="16%" > 
              <div align="right">Fecha del Contrato:</div>
            </td>
            <td nowrap colspan="2" width="23%"> 
              <div align="left"> 
                <input type="text" readonly  name="E01FESVD1" size="3" maxlength="2" value="<%= fex.getE01FESVD1().trim()%>" 
			 >
                <input type="text" readonly  name="E01FESVD2" size="3" maxlength="2" value="<%= fex.getE01FESVD2().trim()%>" 
			  >
                <input type="text" readonly  name="E01FESVD3" size="3" maxlength="2" value="<%= fex.getE01FESVD3().trim()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap width="38%" > 
              <input type="text" readonly  name="E01FESTRM" size="5" maxlength="4" value="<%= fex.getE01FESTRM().trim()%>">
              <input type="hidden" name="E01FESTRC" value="<%= fex.getE01FESTRC()%>">
              <input type="radio" name="CE01FESTRC" value="M" onClick="document.forms[0].E01FESTRC.value='M'"
			  <%if(fex.getE01FESTRC().equals("M")) out.print("checked");%> disabled>
              Mensual 
              <input type="radio" name="CE01FESTRC" value="D" onClick="document.forms[0].E01FESTRC.value='D'"
			  <%if(fex.getE01FESTRC().equals("D")) out.print("checked");%> disabled>
              Diario
<input type="radio" name="CE01FESTRC" value="Y" onClick="document.forms[0].E01FESTRC.value='Y'"
			  <%if(fex.getE01FESTRC().equals("Y")) out.print("checked");%> disabled>
              Anual</td>
            <td nowrap width="16%" > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap colspan="2" width="23%" > 
              <div align="left"> 
                <input type="text" readonly  name="E01FESMA1" size="3" maxlength="2" value="<%= fex.getE01FESMA1().trim()%>" 
			  >
                <input type="text" readonly  name="E01FESMA2" size="3" maxlength="2" value="<%= fex.getE01FESMA2().trim()%>" 
			  >
                <input type="text" readonly  name="E01FESMA3" size="3" maxlength="2" value="<%= fex.getE01FESMA3().trim()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">V&iacute;a de Pago :</div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" readonly  name="E01FESCOT" size="4" maxlength="2" value="<%= fex.getE01FESCOT().trim()%>" >
              </div>
            </td>
            <td nowrap > 
              <div align="right">Referencia Spot :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"> 
                <input type="text" readonly  name="E01FESSPR" size="11" maxlength="11" value="<%= fex.getE01FESSPR()%>" 
			  >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap height="35" width="23%" > 
              <div align="right">Notas :</div>
            </td>
            <td nowrap height="35" colspan="4" > 
              <div align="left"> 
                <input type="text" readonly  name="E01FESOT1" size="65" maxlength="60" value="<%= fex.getE01FESOT1()%>" 
			  >
                <input type="hidden" name="E01FESCMM"  value="<%= fex.getE01FESCMM()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="4" > 
              <input type="text" readonly  name="E01FESOT2" size="65" maxlength="60" value="<%= fex.getE01FESOT2()%>" 
			  >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="4" ><%= fex.getE01FESDID()%> - <%= fex.getD01FEGDSC().trim()%></td>
          </tr>
         </table>
      </td>
    </tr>
  </table>
  <br><table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark"> 
            <td nowrap  colspan="2"> 
              <div align="center"><b>L&iacute;mites</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>L&iacute;nea de Cr&eacute;dito </b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Actividad Diaria</b></div>
            </td>
            <td nowrap > 
              <div align="right">Posici&oacute;n D&iacute;a Anterior:</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01YTDBAL())%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  colspan="2"> 
              <div align="right">Monto L&iacute;mite :</div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fex.getD01LIMAMT())%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fex.getD01FEOLIM())%></div>
            </td>
            <td nowrap > 
              <div align="right">(+) Compras :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01TOTPUR())%> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right">L&iacute;mite Disponible :</div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01LIMAVL())%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01FEOAVL())%> </div>
            </td>
            <td nowrap > 
              <div align="right">(-) Ventas :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01TOTSAL())%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  colspan="2"> 
              <div align="right">Monto L&iacute;mite Final :</div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01LIMEND())%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= Util.fcolorCCY(fex.getD01FEOEND())%> </div>
            </td>
            <td nowrap > 
              <div align="right">Disponible :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fex.getD01POSBAL())%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>

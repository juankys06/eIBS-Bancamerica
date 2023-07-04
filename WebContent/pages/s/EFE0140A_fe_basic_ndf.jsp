<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<title>Foreign Exchange Module</title>

<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0140DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"></SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(nda_bo_opt);

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

 out.println("<SCRIPT> initMenu();  </SCRIPT>");

%> 
<h3 align="center"> NDF - Moneda Extranjera</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140" >
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
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap width="85%" > 
              <div align="left"> 
                <input type="hidden" name="D01WKFCP1"  value="<%= fex.getD01WKFCP1()%>" >
                <%= fex.getD01WKFCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap width="85%" > 
              <input type="hidden" name="D01WKFCP2"  value="<%= fex.getD01WKFCP2()%>" readonly>
              <%= fex.getD01WKFCP2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="85%" >
              <input type="hidden" name="D01WKFCP3"  value="<%= fex.getD01WKFCP3()%>" readonly>
              <%= fex.getD01WKFCP3()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>N&uacute;mero de Cuenta :</b></div>
            </td>
            <td nowrap width="85%" >
              <input type="hidden" name="E01WKFACC"  value="<%= fex.getE01WKFACC()%>" readonly>
              <%= fex.getE01WKFACC()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap width="85%" >
              <input type="hidden" name="E01FESTIN"  value="<%= fex.getE01FESTIN()%>" readonly>
              <%= ogen%></td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica 
    <input type="hidden" name="SCREEN"  value="18" >
  </h4>
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap ><%= fex.getE01WKFREF()%></td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <div align="right">Fecha Pacto :<%= Util.formatDate(fex.getE01WKFDD1(),fex.getE01WKFDD2(),fex.getE01WKFDD3())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Operaci&oacute;n :</div>
            </td>
            <td nowrap > <% if(fex.getE01WKFSBT().equals("PU")) out.print("Activa");
						else out.print("Pasiva");%></td>
			<td nowrap><div align="right">Concepto : </div></td>
            <td nowrap > <%=fex.getE01WKFBRC()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Moneda Primaria :</div>
            </td>
            <td nowrap > <%= fex.getE01WKFCCY().trim()%> : <%= Util.fcolorCCY(fex.getE01WKFSOA())%> 
            </td>
            <td nowrap > 
              <div align="right">Tasa de Cambio : </div>
            </td>
            <td nowrap><%= fex.getE01WKFEXR().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Moneda Base :</div>
            </td>
            <td nowrap > 
              <input type="hidden" name="E01WKFCCY"  value="<%= fex.getE01WKFCCY()%>" >
              <%= fex.getE01WKFDCY().trim()%> : <%= Util.fcolorCCY(fex.getE01WKFDOA())%> 
            </td>
            <td nowrap align="right"> 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap> <%= Util.formatDate(fex.getE01WKFVD1(),fex.getE01WKFVD2(),fex.getE01WKFVD3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="3" ><%= fex.getE01WKFDID()%> - <%= fex.getD01FEGDSC()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Informaci&oacute;n Adicional </h4>
  
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Clasificaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <%= fex.getE01WKFCLS()%>
            </td>
            <td nowrap> 
              <div align="right">Tipo de Reevaluaci&oacute;n :</div>
            </td>
            <td nowrap > 
              <%= fex.getE01WKFRVF()%>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Confirmaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <%= fex.getE01WKFNTF()%>
            </td>
            <td nowrap> 
              <div align="right">Forma de Pago :</div>
            </td>
            <td nowrap > 
              <%= fex.getE01WKFBPV()%>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Fecha Cambio de Tasa :</div>
            </td>
            <td nowrap>
              <%= fex.getE01WKFMA1()%> / <%= fex.getE01WKFMA2()%> / <%= fex.getE01WKFMA3()%>
            </td>
            <td nowrap >&nbsp;</td>
            <td nowrap >&nbsp;</td>
          </tr>
          
       </table>   
       
       <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <h5 align="center">Banco</h5>
            </td>
            <td nowrap> 
              <h5 align="center">Sucursal</h5>
            </td>
            <td nowrap > 
              <h5 align="center">Moneda</h5>
            </td>
            <td nowrap > 
              <h5 align="center">Cuenta Contable</h5>
            </td>
            <td nowrap > 
              <h5 align="center">Referencia</h5>
            </td>
            <td nowrap > 
              <h5 align="center">Centro de Costo</h5>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Cuenta Nostro :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <%= fex.getE01WKFOBK()%>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
				<%= fex.getE01WKFOBR()%>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <%= fex.getE01WKFOCY()%>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <%= fex.getE01WKFOGL()%> 
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <%= fex.getE01WKFOAC()%>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <%= fex.getE01WKFOCC()%>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Cuenta Vostro :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <%= fex.getE01WKFCBK()%>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <%= fex.getE01WKFCBR()%>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <%= fex.getE01WKFCCU()%>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <%= fex.getE01WKFCGL()%>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <%= fex.getE01WKFCAC()%>
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <%= fex.getE01WKFCCC()%>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  

  <p><b>L&iacute;mites</b></p>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark"> 
            <td nowrap  colspan="2"> 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <div align="center"><b>L&iacute;nea de Cr&eacute;dito</b></div>
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
              <div align="right">Disponibles :</div>
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

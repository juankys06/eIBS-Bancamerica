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

   builtNewMenu(opa_bo_opt);

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
<h3 align="center"> Opciones de Moneda - Moneda Extranjera </h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_basic_opt.jsp,EFE0140A"> 
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
  <h4>Informaci&oacute;n B&aacute;sica <b> 
    <input type=HIDDEN name="SCREEN" value="12">
    </b></h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap > 
              <div align="left"><%= fex.getE01WKFREF()%></div>
            </td>
            <td nowrap >&nbsp;</td>
            <td nowrap >Fecha Pacto :<%= Util.formatDate(fex.getE01WKFDD1(),fex.getE01WKFDD2(),fex.getE01WKFDD3())%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Tipo de Operaci&oacute;n :</div>
            </td>
            <td nowrap > <% if(fex.getE01WKFSBT().equals("BC")) out.print("Buy Call Option");
										else if(fex.getE01WKFSBT().equals("WC")) out.print("Write Call Option");
										else if(fex.getE01WKFSBT().equals("BP")) out.print("Buy a Put");
										else if(fex.getE01WKFSBT().equals("WP")) out.print("Write a Put");
						                else out.print("No Action Taken");%> </td>
			<td nowrap><div align="right">Concepto : </div></td>
            <td nowrap > <%=fex.getE01WKFBRC()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">C&oacute;digo de Moneda :</div>
            </td>
            <td nowrap width="38%" > <%= fex.getE01WKFCCY().trim()%> </td>
            <td nowrap width="16%" > 
              <div align="right">Valor Nominal :</div>
            </td>
            <td nowrap width="23%" > 
              <div align="left"> <%= Util.fcolorCCY(fex.getE01WKFSOA())%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Tipo de Opci&oacute;n :</div>
            </td>
            <td nowrap width="38%" > <% if(fex.getE01WKFOPT().equals("1")) out.print("Americana");
						                else out.print("Europea");%> </td>
            <td nowrap width="16%" > 
              <div align="right">Strike Price : </div>
            </td>
            <td nowrap width="23%" > 
              <div align="left"> <%= Util.fcolorCCY(fex.getE01WKFEXR())%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Fecha del Contrato :</div>
            </td>
            <td nowrap width="38%" > <%= Util.formatDate(fex.getE01WKFVD1(),fex.getE01WKFVD2(),fex.getE01WKFVD3())%> 
            </td>
            <td nowrap width="16%" > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left"> <%= Util.formatDate(fex.getE01WKFMA1(),fex.getE01WKFMA2(),fex.getE01WKFMA3())%> 
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap width="38%" > <%= fex.getE01WKFTRM().trim()%> - <% if(fex.getE01WKFTRC().equals("M")) out.print("Month(s)");
																		  else if(fex.getE01WKFTRC().equals("D")) out.print("Day(s)");
						                								  else out.print("Year(s)");%> 
            </td>
            <td nowrap width="16%" > 
              <div align="right">Referencia Spot :</div>
            </td>
            <td nowrap width="23%" > 
              <div align="left"> <%= fex.getE01WKFSPR()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <div align="left"> </div>
            </td>
            <td nowrap > 
              <div align="right">Precio Opci&oacute;n :</div>
            </td>
            <td nowrap > 
              <div align="left"> <%= Util.fcolorCCY(fex.getE01WKFBRF())%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="3" ><%= fex.getE01WKFDID()%> - <%= fex.getD01FEGDSC()%> 
              <input type="hidden" name="E01WKFCCY"  value="<%= fex.getE01WKFCCY()%>" >
            </td>
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
       </table>   
       
       <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
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
          <tr id="trclear"> 
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
          <tr id="trdark"> 
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
              <div align="right">Posici&oacute;n D&iacute;a Anterior :</div>
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Foreign Exchange Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0140DSMessage"  scope="session" />
<jsp:useBean id= "currClient" class= "datapro.eibs.beans.ESD080001Message"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }


</SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(opm_bo_opt);

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
<h3 align="center"> Opciones de Moneda</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_basic_opt.jsp, EFE0140B"> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140" >
<%
String ogen = "";
if (fex.getE01FESTIN().equals("T")) {
	ogen = "Tesorería";
	currClient.setE01CUSCUN(fex.getE01WKFCUN());
} else if (fex.getE01FESTIN().equals("F")) {
	ogen = "Fideicomiso";
	currClient.setE01CUSCUN(fex.getE01WKFCUN());
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
            <td nowrap  width="85%" > 
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
            <td nowrap >Fecha de Pacto :<%= Util.formatDate(fex.getE01WKFDD1(),fex.getE01WKFDD2(),fex.getE01WKFDD3())%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Tipo de Operaci&oacute;n :</div>
            </td>
            <td nowrap> <% if(fex.getE01WKFSBT().equals("BC")) out.print("Buy Call Option");
							else if(fex.getE01WKFSBT().equals("WC")) out.print("Write Call Option");
							else if(fex.getE01WKFSBT().equals("BP")) out.print("Buy a Put");
							else if(fex.getE01WKFSBT().equals("WP")) out.print("Write a Put");
						    else out.print("No Action Taken");%> </td>
			<td nowrap><div align="right">Concepto : </div></td>
            <td nowrap > <%=fex.getE01WKFBRC()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Moneda Primaria :</div>
            </td>
            <td nowrap width="38%" > <%= fex.getE01WKFCCY().trim()%> : <%= Util.fcolorCCY(fex.getE01WKFSOA())%></td>
            <td nowrap width="16%" > 
              <div align="right">Tipo de Opci&oacute;n :</div>
            </td>
            <td nowrap width="23%" > 
              <div align="left"><% if(fex.getE01WKFOPT().equals("1")) out.print("Americana");
						                else out.print("Europea");%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Moneda Base :</div>
            </td>
            <td nowrap width="38%" ><%= fex.getE01WKFDCY().trim()%> : <%= Util.fcolorCCY(fex.getE01WKFDOA())%> 
            </td>
            <td nowrap width="16%" > 
              <div align="right">Fecha del Contrato :</div>
            </td>
            <td nowrap width="23%" > 
              <div align="left"><%= Util.formatDate(fex.getE01WKFVD1(),fex.getE01WKFVD2(),fex.getE01WKFVD3())%> 
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap width="38%" ><%= fex.getE01WKFTRM().trim()%> - <% if(fex.getE01WKFTRC().equals("M")) out.print("Mes(s)");
																		  else if(fex.getE01WKFTRC().equals("D")) out.print("Dia(s)");
						                								  else out.print("Año(s)");%> 
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
              <div align="right">Referencia Spot :</div>
            </td>
            <td nowrap width="38%" ><%= fex.getE01WKFSPR()%> </td>
            <td nowrap width="16%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="23%" > 
              <div align="left"> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Strike Price :</div>
            </td>
            <td nowrap > 
              <div align="left"><%= Util.fcolorCCY(fex.getE01WKFEXR())%> </div>
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
            <td nowrap colspan="3" ><%= fex.getE01WKFDID()%> - <%= fex.getD01FEGDSC().trim()%> 
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
           <% if(fex.getH01FLGWK3().equals("N")){%>
             <td nowrap colspan="2" > 
              <input type="text" name="E01WKFCLS" size="5" maxlength="4" value="<%= fex.getE01WKFCLS()%>">
              <a href="javascript:GetClassFex('E01WKFCLS','OPT','<%= fex.getE01WKFCCY()%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
             </td>
            <%} else {%>
             <td nowrap colspan="2" > <%= fex.getE01WKFCLS()%> </td>
           <%}%>
          
            <td nowrap colspan="2" > 
              <div align="right">Tipo de Reevaluaci&oacute;n :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01WKFRVF" size="3" maxlength="1" value="<%= fex.getE01WKFRVF()%>" 
			  >
              <a href="javascript:GetCode('E01WKFRVF','STATIC_fe_bo_rev.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
            </td>
            <td nowrap >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Confirmaci&oacute;n :</div>
            </td>
            <td nowrap colspan="2" > 
              <input type="text" name="E01WKFNTF" size="3" maxlength="1" value="<%= fex.getE01WKFNTF()%>" 
			  >
              <a href="javascript:GetCode('E01WKFNTF','STATIC_fe_bo_not.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
            </td>
            <td nowrap colspan="2" > 
              <div align="right">Forma de Pago :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01WKFBPV" size="3" maxlength="1" value="<%= fex.getE01WKFBPV()%>" 
			  >
              <a href="javascript:GetCode('E01WKFBPV','STATIC_fe_bo_pay.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></a> 
            </td>
            <td nowrap width="17%" >&nbsp;</td>
          </tr>
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
                <input type="text" name="E01WKFOBK" size="2" maxlength="2" value="<%= fex.getE01WKFOBK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01WKFOBR" size="3" maxlength="3"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01WKFOBK.value,'','','','')" value="<%= fex.getE01WKFOBR()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01WKFOCY" size="3" maxlength="3" value="<%= fex.getE01WKFOCY()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01WKFOBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01WKFOGL" size="17" maxlength="16" value="<%= fex.getE01WKFOGL()%>" 
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01WKFOBK.value,document.forms[0].E01WKFOCY.value,'','','')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01WKFOAC" size="13" maxlength="12" value="<%= fex.getE01WKFOAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01WKFOBK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01WKFOCC" size="12" maxlength="9" value="<%= fex.getE01WKFOCC()%>"
                oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01WKFOBK.value,'','','','')" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Cuenta Vostro :</div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01WKFCBK" size="2" maxlength="2" value="<%= fex.getE01WKFCBK()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01WKFCBR" size="3" maxlength="3" value="<%= fex.getE01WKFCBR()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01WKFCBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01WKFCCU" size="3" maxlength="3" value="<%= fex.getE01WKFCCU()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01WKFCBK.value,'','','','')">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01WKFCGL" size="17" maxlength="16" value="<%= fex.getE01WKFCGL()%>"
				oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01WKFCBK.value,document.forms[0].E01WKFCCU.value,'','','')"  >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01WKFCAC" size="13" maxlength="12" value="<%= fex.getE01WKFCAC()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01WKFCBK.value,'','','','RT')" >
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01WKFCCC" size="12" maxlength="9" value="<%= fex.getE01WKFCCC()%>"
                oncontextmenu="showPopUp(costcenterHelp,this.name,document.forms[0].E01WKFCBK.value,'','','','')" >
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
              <div align="right">Monto L&iacute;mite Final:</div>
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
  <br><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
        </div>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <BR>
  </form>
</body>
</html>

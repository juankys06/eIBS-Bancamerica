<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Foreign Exchange Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0160DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(opi_bo_opt);

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
<h3 align="center"> Consulta - Opciones de Moneda </h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160" >
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
                <input type="hidden" name="D01FEMCP1"  value="<%= fex.getD01FEMCP1()%>" >
                <%= fex.getD01FEMCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap width="85%" > 
              <input type="hidden" name="D01FEMCP2"  value="<%= fex.getD01FEMCP2()%>" readonly>
              <%= fex.getD01FEMCP2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="85%" ><font face="Arial"><font size="2"> 
              <input type="hidden" name="D01FEMCP3"  value="<%= fex.getD01FEMCP3()%>" readonly>
              </font></font><%= fex.getD01FEMCP3()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>N&uacute;mero de Cuenta :</b></div>
            </td>
            <td nowrap width="85%" ><font face="Arial"><font size="2"> 
              <input type="hidden" name="E01FEMACC"  value="<%= fex.getD01FEMCP3()%>" readonly>
              </font></font><%= fex.getE01FEMACC()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica<b> 
    <input type=HIDDEN name="SCREEN" value="12">
    </b></h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap > 
              <div align="left"><%= fex.getE01FEMREF()%></div>
            </td>
            <td nowrap >&nbsp;</td>
            <td nowrap >Fecha Pacto :<%= Util.formatDate(fex.getE01FEMDD1(),fex.getE01FEMDD2(),fex.getE01FEMDD3())%></td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Tipo de Operaci&oacute;n :</div>
            </td>
            <td nowrap> <% if(fex.getE01FEMSBT().equals("BC")) out.print("Buy Call Option");
										else if(fex.getE01FEMSBT().equals("WC")) out.print("Write Call Option");
										else if(fex.getE01FEMSBT().equals("BP")) out.print("Buy a Put");
										else if(fex.getE01FEMSBT().equals("WP")) out.print("Write a Put");
						                else out.print("No Action Taken");%> 
			</td>
			<td nowrap><div align="right">Concepto : </div></td>
            <td nowrap > <%=fex.getE01FEMBRC()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right"> Moneda Primaria :</div>
            </td>
            <td nowrap width="38%" > <%= fex.getE01FEMCCY().trim()%> </td>
            <td nowrap width="16%" > 
              <div align="right">Moneda Base :</div>
            </td>
            <td nowrap width="23%" > 
              <div align="left"><%= fex.getE01FEMDCY().trim()%> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Monto Original Mda. Primaria :</div>
            </td>
            <td nowrap width="38%" ><%= Util.fcolorCCY(fex.getE01FEMSOA())%> </td>
            <td nowrap width="16%" > 
              <div align="right">Monto Original Mda. Base :</div>
            </td>
            <td nowrap width="23%" > 
              <div align="left"><%= Util.fcolorCCY(fex.getE01FEMDOA())%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">Balance Moneda Primaria :</div>
            </td>
            <td nowrap width="38%" > 
              <div align="left"><%= Util.fcolorCCY(fex.getE01FEMSAM())%></div>
            </td>
            <td nowrap width="16%" >
              <div align="right">Balance Moneda Base :</div>
            </td>
            <td nowrap width="23%">
              <div align="left"><%= Util.fcolorCCY(fex.getE01FEMDAM())%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Tipo de Opci&oacute;n :</div>
            </td>
            <td nowrap width="38%" > 
              <div align="left"><% if(fex.getE01FEMOPT().equals("1")) out.print("Americana");
						                else out.print("Europea");%></div>
            </td>
            <td nowrap width="16%" >
              <div align="right">Fecha del Contrato :</div>
            </td>
            <td nowrap width="23%">
              <div align="left"><%= Util.formatDate(fex.getE01FEMVD1(),fex.getE01FEMVD2(),fex.getE01FEMVD3())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="23%" > 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap width="38%" ><%= fex.getE01FEMTRM().trim()%> - <% if(fex.getE01FEMTRC().equals("M")) out.print("Mes(s)");
																		  else if(fex.getE01FEMTRC().equals("D")) out.print("Dia(s)");
						                								  else out.print("Año(s)");%> 
            </td>
            <td nowrap width="16%" > 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="23%"> 
              <div align="left"> <%= Util.formatDate(fex.getE01FEMMA1(),fex.getE01FEMMA2(),fex.getE01FEMMA3())%> 
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Referencia Spot :</div>
            </td>
            <td nowrap width="38%" ><%= fex.getE01FEMSPR()%> </td>
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
              <div align="left"><%= Util.fcolorCCY(fex.getE01FEMEXR())%> </div>
            </td>
            <td nowrap > 
              <div align="right">Precio Opci&oacute;n :</div>
            </td>
            <td nowrap > 
              <div align="left"> <%= Util.fcolorCCY(fex.getE01FEMBRF())%> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="23%" > 
              <div align="right">Tesorero :</div>
            </td>
            <td nowrap colspan="3" ><%= fex.getE01FEMDID()%> - <%= fex.getD01USRDSC().trim()%> 
              <input type="hidden" name="E01FEMCCY"  value="<%= fex.getE01FEMCCY()%>" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n Adicional </h4>
  <table  class="tableinfo" width="474" >
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Clasificaci&oacute;n :</div>
            </td>
            <td nowrap colspan="4" > <%= fex.getE01FEMCLS()%> - <%= fex.getD01FEGDSC()%></td>
            <td nowrap > 
              <div align="right">Tipo de Reevaluaci&oacute;n :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"><% if(fex.getE01FEMRVF().equals("3")) out.print("Acumulado Diario");
				 else if(fex.getE01FEMRVF().equals("1")) out.print("Tasa Spot Diaria");
				 else if(fex.getE01FEMRVF().equals("2")) out.print("Tasa Futura");
				 else out.print("Ninguna");%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Confirmaci&oacute;n :</div>
            </td>
            <td nowrap colspan="2" > <% if(fex.getE01FEMNTF().equals("P")) out.print("Imprimir");
				 else if(fex.getE01FEMNTF().equals("S")) out.print("Via Swift");
				 else if(fex.getE01FEMNTF().equals("H")) out.print("Via Chip");
				 else if(fex.getE01FEMNTF().equals("T")) out.print("Via Telex");
				 else if(fex.getE01FEMNTF().equals("F")) out.print("Via Fax");
				 else out.print("No Notificar");%> </td>
            <td nowrap colspan="2" > 
              <div align="right"></div>
            </td>
            <td nowrap > 
              <div align="right">Forma de Pago :</div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left"><% if(fex.getE01FEMBPV().equals("F")) out.print("Via Fed");
				 else if(fex.getE01FEMBPV().equals("1")) out.print("Swift MT-103");
				 else if(fex.getE01FEMBPV().equals("2")) out.print("Swift MT-200");
				 else if(fex.getE01FEMBPV().equals("3")) out.print("Swift MT-202");
				 else if(fex.getE01FEMBPV().equals("T")) out.print("Telex");
				 else out.print("Ninguna");%></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="center"></div>
            </td>
            <td nowrap > 
              <h4 align="center"><b>Banco</b></h4>
            </td>
            <td nowrap> 
              <h4 align="center"><b>Sucursal</b></h4>
            </td>
            <td nowrap > 
              <h4 align="center"><b>Moneda</b></h4>
            </td>
            <td nowrap > 
              <h4 align="center"><b>Cuenta Contable</b></h4>
            </td>
            <td nowrap > 
              <h4 align="center"><b>Referencia</b></h4>
            </td>
            <td nowrap > 
              <h4 align="center"><b>Centro de Costo</b></h4>
            </td>
            <td nowrap >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Cuenta Nostro :</div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMOBK()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMOBR()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMOCY()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMOGL()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMOAC()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMOCC()%> </div>
            </td>
            <td nowrap ><%= fex.getD01NACDSC()%></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Cuenta Vostro :</div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMCBK()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMCBR()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMCCU()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMCGL()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMCAC()%> </div>
            </td>
            <td nowrap > 
              <div align="center"> <%= fex.getE01FEMCCC()%> </div>
            </td>
            <td nowrap ><%= fex.getD01VACDSC()%></td>
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

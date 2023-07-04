<html>
<head>
<title>Información General</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lnGenInf" class="datapro.eibs.beans.EDL015005Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(ln_a_opt);

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
  out.println("<SCRIPT> initMenu(); </SCRIPT>");
%> 
<h3 align="center">Informaci&oacute;n General <%= lnGenInf.getE05DSCPRO().trim()%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_ap_gen_inf.jsp,EDL0140"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150" >
  <INPUT TYPE=HIDDEN NAME="E05DEABNK"  value="<%= lnGenInf.getE05DEABNK().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="E05DEACUN" size="9" maxlength="9" value="<%= lnGenInf.getE05DEACUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E05CUSNA1" size="45" maxlength="45" value="<%= lnGenInf.getE05CUSNA1().trim()%>" >
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E05DEAACC" size="12" maxlength="12" value="<%= lnGenInf.getE05DEAACC().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E05DEACCY2" size="3" maxlength="3" value="<%= lnGenInf.getE05DEACCY().trim()%>" >
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E05DEAPRO" size="4" maxlength="4" value="<%= lnGenInf.getE05DEAPRO().trim()%>" >
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n General</h4>
      
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%" > 
              <div align="right">Tipo de Inter&eacute;s :</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEAICT().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAICT" size="30" maxlength="30" 
				  value="<% if (lnGenInf.getE05DEAICT().equals("S")) out.print("Al Vencimiento Calendario");
							else if (lnGenInf.getE05DEAICT().equals("M")) out.print("Al Vencimiento Comercial");
							else if (lnGenInf.getE05DEAICT().equals("P")) out.print("Prepagados Calendario");
							else if (lnGenInf.getE05DEAICT().equals("A")) out.print("Prepagados Comerciales");
							else if (lnGenInf.getE05DEAICT().equals("D")) out.print("Descontados Calendario");
							else if (lnGenInf.getE05DEAICT().equals("T")) out.print("Descontados Comerciales");
							else if (lnGenInf.getE05DEAICT().equals("R")) out.print("Capitalizados");
							else if (lnGenInf.getE05DEAICT().equals("1")) out.print("Al Vencimiento Calendario");
							else if (lnGenInf.getE05DEAICT().equals("2")) out.print("Al Vencimiento Comercial");
							else if (lnGenInf.getE05DEAICT().equals("3")) out.print("Prepagados Calendario");
							else if (lnGenInf.getE05DEAICT().equals("4")) out.print("Prepagados Comerciales");
							else if (lnGenInf.getE05DEAICT().equals("5")) out.print("Descontados Calendario");
							else if (lnGenInf.getE05DEAICT().equals("6")) out.print("Descontados Comerciales");
							else if (lnGenInf.getE05DEAICT().equals("7")) out.print("Capitalizados");
							else if (lnGenInf.getE05DEAICT().equals("8")) out.print("Regla 78");
							else out.print("");%>" 
				>
            </td>
            <td nowrap width="24%" > 
              <div align="right">Comisionista :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEABRK().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEABRK" size="4" maxlength="3" value="<%= lnGenInf.getE05DEABRK().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              <div align="right">Per&iacute;odo Base :</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" name="E05DEABAS" size="4" maxlength="3" <% if (lnGenInf.getF05DEABAS().equals("Y")) out.print("id=\"txtchanged\""); %> value="<%= lnGenInf.getE05DEABAS().trim()%>" readonly>
            </td>
            <td nowrap width="24%" > 
              <div align="right">Tasa del Comisionista :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEABCP().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEABCP" size="10" maxlength="9" value="<%= lnGenInf.getE05DEABCP().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%" > 
              <div align="right">C&aacute;lculo de Inter&eacute;s Normal :</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEAIFL().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAIFL" size="30" maxlength="30" 
				  value="<% if (lnGenInf.getE05DEAIFL().equals("1")) out.print("Capital Vigente");
							else if (lnGenInf.getE05DEAIFL().equals("2")) out.print("Capital Original");
							else if (lnGenInf.getE05DEAIFL().equals("3")) out.print("Capital Vigente menos Capital Vencido");
							else if (lnGenInf.getE05DEAIFL().equals("N")) out.print("No Calcula Intereses");
							else out.print("");%>" 
				>
            </td>
            <td nowrap width="24%" > 
              <div align="right">Retenci&oacute;n de Impuestos :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEAWHF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAWHF" size="30" maxlength="30" 
				  value="<% if (lnGenInf.getE05DEAWHF().equals("1")) out.print("Retencion sobre Intereses ISR");
							else if (lnGenInf.getE05DEAWHF().equals("2")) out.print("Cobre del IVA");
							else if (lnGenInf.getE05DEAWHF().equals("3")) out.print("IVA mas ISR");
							else if (lnGenInf.getE05DEAWHF().equals("4")) out.print("IVA solo en Comisiones");
							else if (lnGenInf.getE05DEAWHF().equals("5")) out.print("IVA solo en Intereses");
							else if (lnGenInf.getE05DEAWHF().equals("6")) out.print("Debito bancario IDB");
							else if (lnGenInf.getE05DEAWHF().equals("7")) out.print("IDB mas ISR");
							else if (lnGenInf.getE05DEAWHF().equals("8")) out.print("IDB mas IVA");
							else if (lnGenInf.getE05DEAWHF().equals("9")) out.print("Todo Tipo de Impuestos");
							else if (lnGenInf.getE05DEAWHF().equals("N")) out.print("No Calcula Impuestos");
							else if (lnGenInf.getE05DEAWHF().equals("F")) out.print("Cobro del FECI");
							else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              <div align="right">C&aacute;lculo de Inter&eacute;s Mora :</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEAPCL().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAPCL" size="30" maxlength="30" 
				  value="<% if (lnGenInf.getE05DEAPCL().equals("1")) out.print("Sobre Capital Vencido");
							else if (lnGenInf.getE05DEAPCL().equals("2")) out.print("Sobre Capital Original");
							else if (lnGenInf.getE05DEAPCL().equals("3")) out.print("Sobre Capital Vigente");
							else if (lnGenInf.getE05DEAPCL().equals("4")) out.print("Sobre Capital Vencido mas Intereses Vencido");
							else if (lnGenInf.getE05DEAPCL().equals("N")) out.print("No Calcula Intereses");
							else out.print("");%>" 
				>
            </td>
            <td nowrap width="24%" > 
              <div align="right">Vencimiento Feriados :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEAHFQ().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAHFQ" size="30" maxlength="30" 
				  value="<% if (lnGenInf.getE05DEAHFQ().equals("1")) out.print("Acepta Dias Feriados");
							else if (lnGenInf.getE05DEAHFQ().equals("2")) out.print("No Acepta Dias Feriados");
							else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%" > 
              <div align="right">Per&iacute;odo de Gracia :</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEAGPD().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAGPD" size="3" maxlength="2" value="<%= lnGenInf.getE05DEAGPD().trim()%>" >
            </td>
            <td nowrap width="24%" > 
              <div align="right">Direcci&oacute;n de Correos :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEAMLA().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAMLA" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAMLA().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              <div align="right">Tabla Cargos :</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEATLN().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEATLN" size="2" maxlength="2" value="<%= lnGenInf.getE05DEATLN().trim()%>">
            </td>
            <td nowrap width="24%" > 
              <div align="right">C&oacute;digo de Obligaci&oacute;n :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEAOBL().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAOBL" size="3" maxlength="2" value="<%= lnGenInf.getE05DEAOBL().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%" > 
              <div align="right">Tabla Tasas :</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEARTB().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEARTB" size="2" maxlength="1" value="<%= lnGenInf.getE05DEARTB().trim()%>">
            </td>
            <td nowrap width="24%" > 
              <div align="right">C&oacute;digo de Usuario :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEARRC().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEARRC" size="3" maxlength="2" value="<%= lnGenInf.getE05DEARRC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              <div align="right">Clase de Cr&eacute;dito :</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEACLF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEACLF" size="2" maxlength="1" value="<%= lnGenInf.getE05DEACLF().trim()%>">
            </td>
            <td nowrap width="24%" > 
              <div align="right">Participaciones :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEAPAR().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAPAR" size="3" maxlength="2" 
				  value="<% if (lnGenInf.getE05DEAPAR().equals("Y")) out.print("Si");
							else if (lnGenInf.getE05DEAPAR().equals("N")) out.print("No");
							else out.print("");%>" 
				>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="30%" > 
              <div align="right">Pr&eacute;stamo a Demanda :</div>
            </td>
            <td nowrap width="19%" > 
              <input type="text" readonly <% if (lnGenInf.getF05DEALNC().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEALNC" size="3" maxlength="2" 
				  value="<% if (lnGenInf.getE05DEALNC().equals("Y")) out.print("Si");
							else if (lnGenInf.getE05DEALNC().equals("N")) out.print("No");
							else out.print("");%>" 
				>
            </td>
            <td nowrap width="24%" > 
              <div align="right">Cuenta :</div>
            </td>
            <td nowrap width="27%">
              <input type="text" readonly <% if (lnGenInf.getF05DEAPAC().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAPAC" size="12" maxlength="12" value="<%= lnGenInf.getE05DEAPAC().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="19%" >&nbsp; </td>
            <td nowrap width="24%" > 
              <div align="right"> Porciento de Participaciones :</div>
            </td>
            <td nowrap width="27%">
              <input type="text" readonly <% if (lnGenInf.getF05DEACCF().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEACCF" size="4" maxlength="3" value="<%= lnGenInf.getE05DEACCF().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Prioridad de Pagos </h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"  > 
              <div align="center">
                <input type="text" readonly <% if (lnGenInf.getF05DEAPP1().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAPP1" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP1().trim()%>">
              </div>
            </td>
            <td nowrap width="14%"  > 
              <div align="center"> 
                <input type="text" readonly <% if (lnGenInf.getF05DEAPP2().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAPP2" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" readonly <% if (lnGenInf.getF05DEAPP3().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAPP3" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP3().trim()%>">
              </div>
            </td>
            <td nowrap width="22%"  > 
              <div align="center"> 
                <input type="text" readonly <% if (lnGenInf.getF05DEAPP4().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAPP4" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP4().trim()%>">
              </div>
            </td>
            <td nowrap width="17%" > 
              <div align="center"> 
                <input type="text" readonly <% if (lnGenInf.getF05DEAPP5().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAPP5" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP5().trim()%>">
              </div>
            </td>
            <td nowrap width="15%"  > 
              <div align="center"> 
                <input type="text" readonly <% if (lnGenInf.getF05DEAPP6().equals("Y")) out.print("id=\"txtchanged\""); %> name="E05DEAPP6" size="2" maxlength="1" value="<%= lnGenInf.getE05DEAPP6().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="center">Principal</div>
            </td>
            <td nowrap width="14%" > 
              <div align="center">Intereses</div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">Mora</div>
            </td>
            <td nowrap width="22%" > 
              <div align="center">Impuestos / Comisiones</div>
            </td>
            <td nowrap width="17%" > 
              <div align="center">Deducciones</div>
            </td>
            <td nowrap width="15%" > 
              <div align="center">I.V.A.</div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
    
  </form>
</body>
</html>

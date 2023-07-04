<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Evaluación Credit Scoring</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id="Edp088601" class="datapro.eibs.beans.EDP088601Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</head>

<body onload=javascript:init()>
<script language="JavaScript">
function init(){
 // texto observaciones
 document.forms[0].E01EVAOBS.value = "<%=Edp088601.getE01EVAOBS().trim()%>";
alert(document.forms[0].E01EVAOBS.value);
}

function goUpdVal(){
document.forms[0].H01OPECOD.value="0005";
document.forms[0].SCREEN.value=100;
document.forms[0].submit();	 
}

function limitText(limitField, limitNum) {
	if (limitField.value.length > limitNum + 1) { 
		if (document.forms[0].LAN.value == 'S') {
			alert('Límite de texto excedido');
		} else {
			alert('Your input has been truncated');
		}	
	}	
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	}
}


</script>


<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>

<H3 align="center">Resumen Evaluación Credit Scoring<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="evaluation_credit_scoring.jsp, EDP0886"></H3>
<P align="center">
</P>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0886" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <INPUT TYPE=HIDDEN NAME="H01OPECOD" VALUE="">
  <input type=HIDDEN name="opt">

<h4 align="center"> 
<br>
    <tr bordercolor="#FFFFFF"> 
      Cliente : 
	<INPUT type="text" name="DSC" size="10" maxlength="9" value="<%= userPO.getCusNum().trim()%>"  >
	<INPUT type="text" name="DSC1" size="35" maxlength="35" value="<%= userPO.getCusName().trim()%>"  >
	</tr>
</h4> 

  <h4>Datos Personales</h4>
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<tr id="trdark">
				<td	nowrap width="25%">
					<div align="right">Nombres :</div>
				</td>
				<td nowrap align="left" width="25%">
					<INPUT type="text" name="E01CUSNA1" size="40" maxlength="35" value="<%= Edp088601.getE01CUSNA1().trim()%>" readonly > 
				</td>
				<td nowrap width="25%">
					<DIV align="right">Identificación :</DIV>
				</TD>
				<td nowrap align="left" width="25%">
					<INPUT type="text" name="E01CUSIDN" size="17" maxlength="15" value="<%= Edp088601.getE01CUSIDN().trim()%>" readonly  >
				</td>
			</tr>

			<tr id="trclear">
				<td nowrap width="25%">
					<DIV align="right">Edad :</DIV>
				</td>
				<td nowrap align="left" width="25%">
					<INPUT type="text" name="E01SUMAGE" size="7" maxlength="6" value="<%= Edp088601.getE01SUMAGE().trim()%>" readonly  > 
				</td>
				<td nowrap width="25%">
					<DIV align="right">Genero : </DIV>
				</TD>
				<td nowrap align="left" width="25%">
					<INPUT type="text" name="E01SUMGEN" size="10" maxlength="9" value="<%= Edp088601.getE01SUMGEN().trim()%>" readonly  >
				</td>
			</tr>
			<TR id="trdark">
				<td nowrap width="25%">
					<DIV align="right">Cliente desde : </DIV>
				</TD>
				<td nowrap align="left" width="25%">
					<INPUT type="text" name="E01SUMDDF" size="3" maxlength="2" value="<%= Edp088601.getE01SUMDDF().trim()%>" readonly  >/
					<INPUT type="text" name="E01SUMMMF" size="3" maxlength="2" value="<%= Edp088601.getE01SUMMMF().trim()%>" readonly  >/
					<INPUT type="text" name="E01SUMYYF" size="5" maxlength="4" value="<%= Edp088601.getE01SUMYYF().trim()%>" readonly  >
				</td>
				<td nowrap width="25%">
				</TD>
				<td nowrap align="left" width="25%">
				</td>
			</tr>
		</TABLE>
		</td>
    </tr>
  </table>

  <h4>Datos Solicitud</h4>
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<tr id="trdark">
				<td	nowrap width="25%">
					<div align="right">Numero de Solicitud :</div>
				</td>
				<td nowrap align="left" width="25%">
					<INPUT type="text" name="E01PLPNPR" size="13" maxlength="12" value="<%= Edp088601.getE01PLPNPR().trim()%>" readonly > 
				</td>
				<td nowrap width="25%">
					<DIV align="right">Fecha de Solicitud :</DIV>
				</TD>
				<td nowrap align="left" width="25%">
					<INPUT type="text" name="E01EVAFAD" size="3" maxlength="2"  value="<%= Edp088601.getE01EVAFAD().trim()%>" readonly  >/
					<INPUT type="text" name="E01EVAFAM" size="3" maxlength="2"  value="<%= Edp088601.getE01EVAFAM().trim()%>" readonly  >/
					<INPUT type="text" name="E01EVAFAY" size="5" maxlength="4"  value="<%= Edp088601.getE01EVAFAY().trim()%>" readonly  >
				</td>
			</tr>

			<tr id="trclear">
				<td nowrap width="25%">
					<DIV align="right">Oficina Receptora :</DIV>
				</td>
				<td nowrap align="left" width="25%">
					<INPUT type="text" name="E01PLPBRN" size="5" maxlength="4" value="<%= Edp088601.getE01PLPBRN().trim()%>" readonly > 
					<INPUT type="text" name="E01PLPBRD" size="20" maxlength="20" value="<%= Edp088601.getE01PLPBRD().trim()%>" readonly  > 
				</td>
				<td nowrap width="25%">
					<DIV align="right">Ejecutivo de Negocios : </DIV>
				</TD>
				<td nowrap align="left" width="25%">
					<INPUT type="text" name="E01PLPEJE" size="5" maxlength="4" value="<%= Edp088601.getE01PLPEJE().trim()%>" readonly  >
					<INPUT type="text" name="E01PLPEJD" size="20" maxlength="20" value="<%= Edp088601.getE01PLPEJD().trim()%>" readonly  > 
				</td>
			</tr>
		</TABLE>
		</td>
    </tr>
  </table>

  <h4>Datos de la Operación</h4>
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Credito Solicitado - Producto :</div>
				</td>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01PLTPRO" size="5" maxlength="4" value="<%= Edp088601.getE01PLTPRO().trim()%>" readonly  > 
					<INPUT type="text" name="E01PLTPRD" size="30" maxlength="30" value="<%= Edp088601.getE01PLTPRD().trim()%>" readonly   >
				</td>
			</tr>

			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Monto Solicitado :</DIV>
				</TD>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01PLTAMN" size="17" maxlength="15" value="<%= Edp088601.getE01PLTAMN().trim()%>" readonly  >
				</td>
			</tr>

			<tr id="trdark">
				<td nowrap width="33%">
					<DIV align="right">Plazo :</DIV>
				</td>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01PLTPLZ" size="7" maxlength="6" value="<%= Edp088601.getE01PLTPLZ().trim()%>" readonly  > 
					<INPUT type="text" name="E01PLTPLD" size="30" maxlength="30" value="<%= Edp088601.getE01PLTPLD().trim()%>" readonly   >
				</td>
			</tr>

			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Tasa de Interés : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01PLTRTE" size="10" maxlength="9" value="<%= Edp088601.getE01PLTRTE().trim()%>" readonly  >
				</td>
			</tr>
			<TR id="trdark">
				<td nowrap width="33%">
					<DIV align="right">Garantia : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01PLTCOD" size="40" maxlength="40" value="<%= Edp088601.getE01PLTCOD().trim()%>" readonly  >
				</td>
			</tr>
		</TABLE>
		</td>
    </tr>
  </table>

  <h4>Datos Financieros</h4>
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Ingresos Mensuales :</div>
				</td>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01EVAING" size="17" maxlength="17" value="<%= Edp088601.getE01EVAING().trim()%>" readonly  > 
				</td>
			</tr>
			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Deuda Financiera Mensual :</DIV>
				</TD>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01DEBMON" size="17" maxlength="16" value="<%= Edp088601.getE01DEBMON().trim()%>" readonly  >
				</td>
			</tr>
			<tr id="trdark">
				<td nowrap width="33%">
					<DIV align="right">Endeudamiento Actual :</DIV>
				</td>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01DEBACT" size="17" maxlength="16" value="<%= Edp088601.getE01DEBACT().trim()%>" readonly  > 
				</td>
			</tr>
		</TABLE>
		</td>
    </tr>
  </table>

  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="33%">Solicitud de Credito</th>
            <th align=CENTER nowrap width="33%">Default</th>
            <th align=CENTER nowrap width="33%">Politicas</th>
           </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="33%">
            Máximo a Otorgar   :</td>
            <td NOWRAP  align=CENTER width="33%"> 
                <input type=TEXT name="E01MAXODF" size=17 maxlength=17 value="<%= Edp088601.getE01MAXODF().trim()%>" readonly >
            </td>
            <td NOWRAP  align=CENTER width="33%"> 
                <input type=TEXT name="E01MAXOPL" size=17 maxlength=17 value="<%= Edp088601.getE01MAXOPL().trim()%>" readonly >
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="33%">
            Mínimo a Otorgar :</td>
            <td NOWRAP  align=CENTER width="33%"> 
                <input type=TEXT name="E01MINODF" size=17 maxlength=17 value="<%= Edp088601.getE01MINODF().trim()%>" readonly >
            </td>
            <td NOWRAP  align=CENTER width="33%"> 
                <input type=TEXT name="E01MINOPL" size=17 maxlength=17 value="<%= Edp088601.getE01MINOPL().trim()%>" readonly >
            </td>
          </tr>
 
         </table>
  </table>


  <h4>Resultados del Credit Scoring</h4>
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<tr id="trdark">
				<td	nowrap width="50%">
					<div align="right">Total Puntuacion del Credit Scoring :</div>
				</td>
				<td nowrap align="right" width="50%">
					<INPUT type="text" name="E01TOTPNT" size="10" maxlength="10" value="<%= Edp088601.getE01TOTPNT().trim()%>" readonly  > 
				</td>
			</tr>
			<TR id="trclear">
				<td nowrap width="50%">
					<DIV align="right">Calificacion de Riesgo :</DIV>
				</TD>
				<td nowrap align="right" width="50%">
					<INPUT type="text" name="E01CALRIS" size="5" maxlength="4" value="<%= Edp088601.getE01CALRIS().trim()%>" readonly  >
				</td>
			</tr>
			<TR id="trdark">
				<td nowrap width="50%">
					<DIV align="right">Probabilidad de Incumplimiento :</DIV>
				</TD>
				<td nowrap align="right" width="50%">
					<INPUT type="text" name="E01FAIPRB" size="7" maxlength="6" value="<%= Edp088601.getE01FAIPRB().trim()%>" readonly  >
				%</td>
			</tr>
			<TR id="trclear">
				<td nowrap width="50%">
					<DIV align="right">Provision Especifica :</DIV>
				</TD>
				<td nowrap align="right" width="50%">
					<INPUT type="text" name="E01SPEPRO" size="17" maxlength="16" value="<%= Edp088601.getE01SPEPRO().trim()%>" readonly  >
				</td>
			</tr>
			<TR id="trdark">
				<td nowrap width="50%">
					<DIV align="right">Recomendacion por Credit Scoring :</DIV>
				</TD>
				<td nowrap align="right" width="50%">
					<INPUT type="text" name="E01SCOREC" size="17" maxlength="16" value="<%= Edp088601.getE01SCOREC().trim()%>" readonly  >
				</td>
			</tr>
			<TR id="trclear">
				<td nowrap width="50%">
					<DIV align="right">Monto Maximo a Otorgar por Ingresos :</DIV>
				</TD>
				<td nowrap align="right" width="50%">
					<INPUT type="text" name="E01AMNMXI" size="17" maxlength="16" value="<%= Edp088601.getE01AMNMXI().trim()%>" readonly  >
				</td>
			</tr>
			<TR id="trdark">
				<td nowrap width="50%">
					<DIV align="right">Monto Recomendado por Credit Scoring :</DIV>
				</TD>
				<td nowrap align="right" width="50%">
					<INPUT type="text" name="E01AMNMXC" size="17" maxlength="16" value="<%= Edp088601.getE01AMNMXC().trim()%>" readonly  >
				</td>
			</tr>
		</TABLE>
		</td>
    </tr>
  </table>


<h4>Observaciones/Comentarios</h4>
<table class="tableinfo">
	<tr id="trdark">
		<td width="100%" align="center">
		<TEXTAREA name="E01EVAOBS" rows="10" value="<%= Edp088601.getE01EVAOBS().trim()%>" 
			cols="80" onKeyDown="limitText(this.form.E01EVAOBS,800);"
			onKeyUp="limitText(this.form.E01EVAOBS,800);"
			<%if (userPO.getPurpose().equals("INQUIRY")) {out.print("readonly"); } %>
		>
		</TEXTAREA></td>
	</tr>
</table>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="50%">Analista de Credito</th>
            <th align=CENTER nowrap width="50%">Gerente de Credito</th>
           </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="33%"> 
                <input type=TEXT name="E01IDANAL" size=17 maxlength=17 value="<%= Edp088601.getE01IDANAL().trim()%>"
         			<%if (userPO.getPurpose().equals("INQUIRY")) {out.print("readonly"); } %>
                  >
                <input type=TEXT name="E01DSANAL" size=40 maxlength=35 value="<%= Edp088601.getE01DSANAL().trim()%>" readonly >
			<%if (!userPO.getPurpose().equals("INQUIRY")) { %>
<%--
	           <a href="javascript:GetUser('E01IDANAL','E01DSANAL',document.forms[0].E01IDANAL.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"></a> 
--%>
				<A href="javascript:GetCodeDescCNOFC('E01IDANAL','E01DSANAL','15')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
			<% }; %>
            </td>
            <td NOWRAP  align=CENTER width="33%"> 
                <input type=TEXT name="E01IDMANA" size=17 maxlength=17 value="<%= Edp088601.getE01IDMANA().trim()%>" 
					<%if (userPO.getPurpose().equals("INQUIRY")) {out.print("readonly"); } %>
                 >
                <input type=TEXT name="E01DSMANA" size=40 maxlength=35 value="<%= Edp088601.getE01DSMANA().trim()%>"  >
			<%if (!userPO.getPurpose().equals("INQUIRY")) { %>
<%--
	           <a href="javascript:GetUser('E01IDMANA','E01DSMANA',document.forms[0].E01IDMANA.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"></a> 
--%>
				<A href="javascript:GetCodeDescCNOFC('E01IDMANA','E01DSMANA','15')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
			<% }; %>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=LEFT width="33%">. 
            </td>
            <td NOWRAP  align=RIGHT width="33%">.
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=LEFT width="33%">. 
            </td>
            <td NOWRAP  align=RIGHT width="33%">.
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=LEFT width="33%">. 
            </td>
            <td NOWRAP  align=RIGHT width="33%">. 
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=LEFT width="33%">. 
            </td>
            <td NOWRAP  align=RIGHT width="33%">. 
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=LEFT width="33%">. 
            </td>
            <td NOWRAP  align=RIGHT width="33%">. 
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=LEFT width="33%">. 
            </td>
            <td NOWRAP  align=RIGHT width="33%">. 
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="33%">________________________
            </td>
            <td NOWRAP  align=CENTER width="33%">________________________
            </td>
          </tr>
 
         </table>
		</td>
    </tr>
  </table>

<BR>
  <table class="tbenter" width=100% align=center>
    <tr> 
	<%if (!userPO.getPurpose().equals("INQUIRY")) { %>

    <td class=TDBKG width="20%">
        <div align="center"><a href="javascript:goUpdVal()"><b>Enviar</b></a></div> 
    </td> 
	<% }; %>
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
<script language="JavaScript">
	function init(){
		// texto observaciones
 		document.forms[0].E01EVAOBS.value = "<%=Edp088601.getE01EVAOBS().trim()%>";
	}

function limitText(limitField, limitNum) {
	if (limitField.value.length > limitNum + 1) { 
		if (document.forms[0].LAN.value == 'S') {
			alert('Límite de texto excedido');
		} else {
			alert('Your input has been truncated');
		}	
	}	
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	}
}

//	init();
</script>
</form>
</body>
</html>
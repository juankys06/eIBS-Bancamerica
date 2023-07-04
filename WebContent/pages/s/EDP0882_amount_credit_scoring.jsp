<html>
<head>
<title>Montos Evaluación Credit Scoring</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "Edp088202" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 
<jsp:useBean id= "trans2" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 
<jsp:useBean id="Edp088201" class="datapro.eibs.beans.EDP088201Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

function goCancel(fmt) {

document.forms[0].SCREEN.value="200";
document.forms[0].submit(); 
	  		  
}

</script>

</head>
<body >

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>

<H3 align="center">Montos Evaluación Credit Scoring<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="amount_credit_scoring.jsp, Edp088201"></H3>
<P align="center">
</P>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEdp088201" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  <input type=HIDDEN name="opt">
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="">

<h4 align="center"> 
<br>
    <tr bordercolor="#FFFFFF"> 
      Cliente : 
	<INPUT type="text" name="DSC" size="10" maxlength="9" value="<%= userPO.getCusNum().trim()%>"  >
	<INPUT type="text" name="DSC1" size="35" maxlength="35" value="<%= userPO.getCusName().trim()%>"  >
	</tr>
  </h4> 

  <h4>Cálculos Financieros del Crédito</h4>
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<tr id="trclear">
				<td	nowrap width="33%">
					<div align="right">Numero de Solicitud (Propuesta) :</div>
				</td>
				<td nowrap align="left" width="67%">
					<INPUT type="text" name="E01PLPNPR" size="12" maxlength="12" value="<%=Edp088201.getE01PLPNPR().trim()%>" readonly>
				</td>
			</tr>
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Credito Solicitado - Producto :</div>
				</td>
				<td nowrap align="left" width="67%">
		            <input type=TEXT name="E01PLTPRO" size="6" maxlength="4" value="<%=Edp088201.getE01PLTPRO().trim()%>" readonly>
		            <input type=TEXT name="E01PLTPRD" size="30" maxlength="30" value="<%=Edp088201.getE01PLTPRD().trim()%>" readonly>
				</td>
			</tr>

			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Monto Solicitado :</DIV>
				</TD>
				<td nowrap align="left" width="67%">
				    <INPUT type="text" name="E01PLTAMN" size="20" maxlength="20" value="<%= Edp088201.getE01PLTAMN().trim()%>" readonly >
				</td>
			</tr>

			<tr id="trdark">
				<td nowrap width="33%">
					<DIV align="right">Plazo :</DIV>
				</td>
				<td nowrap align="left" width="67%">
        	          <INPUT type="text" name="E01PLTPLZ" size="5" maxlength="4" value="<%= Edp088201.getE01PLTPLZ().trim()%>" readonly>
        	          <INPUT type="text" name="E01PLTPLD" size="16" maxlength="15" value="<%= Edp088201.getE01PLTPLD().trim()%>" readonly>
				</td>
			</tr>

			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Tasa de Interés Activa Anual : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
				   <INPUT type="TEXT" name="E01PLTRTE" size="12" maxlength="10" value="<%= Edp088201.getE01PLTRTE().trim()%>"	readonly>
				</td>
			</tr>
			<TR id="trdark">
				<td nowrap width="33%">
					<DIV align="right">Tasa de Interés Activa Anual con Cap. Mensual : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
				   <INPUT type="TEXT" name="E01PLTRTM" size="12" maxlength="10" value="<%= Edp088201.getE01PLTRTM().trim()%>"	readonly>
				</td>
			</tr>
			<TR id="trclear">
				<td nowrap width="33%">
					<DIV align="right">Capital + Intereses : </DIV>
				</TD>
				<td nowrap align="left" width="67%">
				    <INPUT type="text" name="E01PAYAMN" size="20" maxlength="20" value="<%= Edp088201.getE01PAYAMN().trim()%>" readonly >
				</td>
			</tr>
		</TABLE>
		</td>
    </tr>
  </table>

<h4>Apalancamiento Actual del Solicitante con la Institución</h4>
  
<table  class="tableinfo">
  <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
  <table  id=mainTable class="tableinfo">
    <tr > 
     <td NOWRAP > 
   <TABLE id="headTable1" NOWRAP>
          <tr id="trdark"> 
            <th align=CENTER nowrap width="20%">Producto</th>
            <th align=CENTER nowrap width="15%"> <div align="center">Monto</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Tasa Anual </div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Tasa Mensual </div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Plazo</div> </th>
            <th align=CENTER nowrap width="15%"> <div align="center">Pago</div> </th>
	      </tr>
	</TABLE>
   <div id="dataDiv1" class="scbarcolor" NOWRAP>

    <%
		int i = 0;
    %> 

    <table id="dataTable1" NOWRAP> 

          <%
	 			Edp088202.initRow();
	 			boolean firstTime = true;
				String Chk1 = "";
                while (Edp088202.getNextRow()) {
					if (firstTime) {
						Chk1 = "checked";
						firstTime = false;
					}else{
						Chk1 = "";
					}
				i++;
		 %>
		<% if (Edp088202.getRecord(0).equals("1")) { %>		
		<% if (!Edp088202.getRecord(7).equals("")) { %>		
          <tr> 
	      	<td align="center" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(1)%></DIV>
      		</td>
	      	<td align="right" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(2)%></DIV>
      		</td>
	      	<td align="right" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(3)%></DIV>
      		</td>
	      	<td align="right" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(4)%></DIV>
      		</td>
	      	<td align="right" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(5)%></DIV>
      		</td>
	      	<td align="right" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(6)%></DIV>
      		</td>
          </tr>
	        <% }else { %>
	      <tr id="trdark"> 
	      	<th align="center" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(1)%></DIV>
      		</TH>
			<TH ALIGN=right NOWRAP>
				<DIV NOWRAP><%=Edp088202.getRecord(2)%></DIV>
			</TH>
			<TH ALIGN=right NOWRAP>
			</TH>
			<TH ALIGN=right NOWRAP>
			</TH>
			<TH ALIGN=right NOWRAP>
			</TH>
			<TH ALIGN=right NOWRAP>
				<DIV NOWRAP><%=Edp088202.getRecord(6)%></DIV>
			</TH>
          </tr>
            <% }; %>

    <%
          };
          }
    %> 
  </table>
  </div>

  </TABLE>

</table>

<h4>Apalancamiento Actual del Solicitante con Otras Instituciones</h4>
  
<table  class="tableinfo">
  <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
  <table  id=mainTable class="tableinfo">
    <tr > 
     <td NOWRAP > 
   <TABLE id="headTable2" NOWRAP>
          <tr id="trdark"> 
            <th align=CENTER nowrap width="20%">Producto</th>
            <th align=CENTER nowrap width="15%"> <div align="center">Monto</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Tasa Anual </div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Tasa Mensual </div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Plazo</div> </th>
            <th align=CENTER nowrap width="15%"> <div align="center">Pago</div> </th>
	      </tr>
	</TABLE>
   <div id="dataDiv2" class="scbarcolor" NOWRAP>

    <%
		i = 0;
    %> 

    <table id="dataTable2" NOWRAP> 

          <%
	 			Edp088202.initRow();
	 			firstTime = true;
				Chk1 = "";
                while (Edp088202.getNextRow()) {
					if (firstTime) {
						Chk1 = "checked";
						firstTime = false;
					}else{
						Chk1 = "";
					}
				i++;
		 %>
		<% if (Edp088202.getRecord(0).equals("2")) { %>		
		<% if (!Edp088202.getRecord(7).equals("")) { %>		
          <tr> 
	      	<td align="center" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(1)%></DIV>
      		</td>
	      	<td align="right" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(2)%></DIV>
      		</td>
	      	<td align="right" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(3)%></DIV>
      		</td>
	      	<td align="right" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(4)%></DIV>
      		</td>
	      	<td align="right" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(5)%></DIV>
      		</td>
	      	<td align="right" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(6)%></DIV>
      		</td>
          </tr>
	        <% }else { %>
	      <tr id="trdark"> 
	      	<th align="center" nowrap >  
				<DIV NOWRAP><%=Edp088202.getRecord(1)%></DIV>
      		</TH>
			<TH ALIGN=right NOWRAP>
				<DIV NOWRAP><%=Edp088202.getRecord(2)%></DIV>
			</TH>
			<TH ALIGN=right NOWRAP>
			</TH>
			<TH ALIGN=right NOWRAP>
			</TH>
			<TH ALIGN=right NOWRAP>
			</TH>
			<TH ALIGN=right NOWRAP>
				<DIV NOWRAP><%=Edp088202.getRecord(6)%></DIV>
			</TH>
          </tr>
            <% }; %>

    <%
          };
          }
    %> 
  </table>
  </div>

  </TABLE>

</table>

  <h4>Capacidad de Endeudamiento: 
  </h4>

<TABLE id="tbenter" align="center" style="width: 100%" border="0">
  <tr id="trdark"> 
  <TH ALIGN=right NOWRAP>
  Ingresos Mensuales : 
  <INPUT type="text" name="E01MONINC" size="20" maxlength="20" value="<%= Edp088201.getE01MONINC().trim()%>" readonly >
  </TH>
  <TH ALIGN=right NOWRAP>
  Servicio de Deuda Actual : 
  <INPUT type="text" name="E01SERDEB" size="20" maxlength="20" value="<%= Edp088201.getE01SERDEB().trim()%>" readonly >
  </tr>
</table>

  <BR>
  <h4>Máxima Default  
  </h4>
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Máximo para Servicios de Deuda :</div>
				</td>
				<td nowrap align="left" width="10%">
				   <INPUT type="TEXT" name="E01MXDPER" size="12" maxlength="10" value="<%= Edp088201.getE01MXDPER().trim()%>"	readonly>%
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MXDAMN" size="20" maxlength="20" value="<%= Edp088201.getE01MXDAMN().trim()%>" readonly >
				</td>
			</tr>
			<tr id="trclear">
				<td	nowrap width="33%">
					<div align="right">Ingreso Mensual Disponible para Deuda :</div>
				</td>
				<td nowrap align="left" width="10%">
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MXDIMD" size="20" maxlength="20" value="<%= Edp088201.getE01MXDIMD().trim()%>" readonly >
				</td>
			</tr>
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Endeudamiento que puede solicitar :</div>
				</td>
				<td nowrap align="left" width="10%">
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MXDDEB" size="20" maxlength="20" value="<%= Edp088201.getE01MXDDEB().trim()%>" readonly >
				</td>
			</tr>

		</TABLE>
		</td>
    </tr>
  </table>

  <h4>Mínima Default</h4>
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Máximo para Servicios de Deuda :</div>
				</td>
				<td nowrap align="left" width="10%">
				   <INPUT type="TEXT" name="E01MNDPER" size="12" maxlength="10" value="<%= Edp088201.getE01MNDPER().trim()%>"	readonly>%
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MNDAMN" size="20" maxlength="20" value="<%= Edp088201.getE01MNDAMN().trim()%>" readonly >
				</td>
			</tr>
			<tr id="trclear">
				<td	nowrap width="33%">
					<div align="right">Ingreso Mensual Disponible para Deuda :</div>
				</td>
				<td nowrap align="left" width="10%">
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MNDIMD" size="20" maxlength="20" value="<%= Edp088201.getE01MNDIMD().trim()%>" readonly >
				</td>
			</tr>
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Endeudamiento que puede solicitar :</div>
				</td>
				<td nowrap align="left" width="10%">
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MNDDEB" size="20" maxlength="20" value="<%= Edp088201.getE01MNDDEB().trim()%>" readonly >
				</td>
			</tr>

		</TABLE>
		</td>
    </tr>
  </table>



  <h4>Máxima Políticas</h4>
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Máximo para Servicios de Deuda :</div>
				</td>
				<td nowrap align="left" width="10%">
				   <INPUT type="TEXT" name="E01MXPPER" size="12" maxlength="10" value="<%= Edp088201.getE01MXPPER().trim()%>"	readonly>%
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MXPAMN" size="20" maxlength="20" value="<%= Edp088201.getE01MXPAMN().trim()%>" readonly >
				</td>
			</tr>
			<tr id="trclear">
				<td	nowrap width="33%">
					<div align="right">Ingreso Mensual Disponible para Deuda :</div>
				</td>
				<td nowrap align="left" width="10%">
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MXPIMD" size="20" maxlength="20" value="<%= Edp088201.getE01MXPIMD().trim()%>" readonly >
				</td>
			</tr>
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Endeudamiento que puede solicitar :</div>
				</td>
				<td nowrap align="left" width="10%">
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MXPDEB" size="20" maxlength="20" value="<%= Edp088201.getE01MXPDEB().trim()%>" readonly >
				</td>
			</tr>

		</TABLE>
		</td>
    </tr>
  </table>

  <h4>Mínima Políticas</h4>
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Máximo para Servicios de Deuda :</div>
				</td>
				<td nowrap align="left" width="10%">
				   <INPUT type="TEXT" name="E01MNPPER" size="12" maxlength="10" value="<%= Edp088201.getE01MNPPER().trim()%>"	readonly>%
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MNPAMN" size="20" maxlength="20" value="<%= Edp088201.getE01MNPAMN().trim()%>" readonly >
				</td>
			</tr>
			<tr id="trclear">
				<td	nowrap width="33%">
					<div align="right">Ingreso Mensual Disponible para Deuda :</div>
				</td>
				<td nowrap align="left" width="10%">
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MNPIMD" size="20" maxlength="20" value="<%= Edp088201.getE01MNPIMD().trim()%>" readonly >
				</td>
			</tr>
			<tr id="trdark">
				<td	nowrap width="33%">
					<div align="right">Endeudamiento que puede solicitar :</div>
				</td>
				<td nowrap align="left" width="10%">
				</td>
				<td nowrap align="left" width="57%">
				   <INPUT type="text" name="E01MNPDEB" size="20" maxlength="20" value="<%= Edp088201.getE01MNPDEB().trim()%>" readonly >
				</td>
			</tr>

		</TABLE>
		</td>
    </tr>
  </table>

<br>
<br>

  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="40%">Montos a Otorgar Con Garantia Real</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Default</div>
            </th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Politicas</div>
            </th>
            
           </tr>
          <tr id="trclear"> 
            <td NOWRAP  align=RIGHT width="40%"> 
            Máximo a Otorgar 
            </td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PMXDFC" size="12" maxlength="10" value="<%= Edp088201.getE01PMXDFC().trim()%>"	readonly>%
			</td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PMXPLC" size="12" maxlength="10" value="<%= Edp088201.getE01PMXPLC().trim()%>"	readonly>%
			</td>
         </tr>
          <tr id="trdark"> 
            <td NOWRAP  align=RIGHT width="40%"> 
            Minimo a Otorgar 
            </td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PMNDFC" size="12" maxlength="10" value="<%= Edp088201.getE01PMNDFC().trim()%>"	readonly>%
			</td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PMNPLC" size="12" maxlength="10" value="<%= Edp088201.getE01PMNPLC().trim()%>"	readonly>%
			</td>
         </tr>
        </table>
  </table>
<br>
<br>

  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="40%">Montos a Otorgar Sin Garantia Real</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Default</div>
            </th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Politicas</div>
            </th>
            
           </tr>
          <tr id="trclear"> 
            <td NOWRAP  align=RIGHT width="40%"> 
            Máximo a Otorgar 
            </td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PMXDFS" size="12" maxlength="10" value="<%= Edp088201.getE01PMXDFS().trim()%>"	readonly>%
			</td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PMXPLS" size="12" maxlength="10" value="<%= Edp088201.getE01PMXPLS().trim()%>"	readonly>%
			</td>
         </tr>
          <tr id="trdark"> 
            <td NOWRAP  align=RIGHT width="40%"> 
            Minimo a Otorgar 
            </td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PMNDFS" size="12" maxlength="10" value="<%= Edp088201.getE01PMNDFS().trim()%>"	readonly>%
			</td>
			<td nowrap align="CENTER" width="20%">
 		    <INPUT type="TEXT" name="E01PMNPLS" size="12" maxlength="10" value="<%= Edp088201.getE01PMNPLS().trim()%>"	readonly>%
			</td>
         </tr>
        </table>
  </table>
<br>
<br>
  
<div align="center"> 
		<TD ALIGN=CENTER width="24%" class=tdbkg><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a>

</div>
 <SCRIPT language="JavaScript">

     function resizeDoc() {
       	divResize();
     	adjustEquTables(headTable1, dataTable1, dataDiv1,1,false);
     	adjustEquTables(headTable2, dataTable2, dataDiv2,1,false);
      }
     resizeDoc();
     window.onresize=resizeDoc;

</SCRIPT>


</form>
</body>
</html>
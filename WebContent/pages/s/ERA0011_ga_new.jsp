<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Información  Básica de Garantías</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="gaMant" class= "datapro.eibs.beans.ERA001101Message"  scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

 
<SCRIPT Language="Javascript">

function hidePoliza(value){
 if (value) {
   Poliza.style.display="";}
 else{
   Poliza.style.display="none"; }
}

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
   
%> 
<h3 align="center">Garant&iacute;as - Informaci&oacute;n B&aacute;sica
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ga_new.jsp,ERA0011"></h3> 
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011" >

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">
   
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01ROCCUN" readonly size="9" maxlength="9" value="<%= gaMant.getE01ROCCUN().trim()%>">
                <a href="javascript:GetCustomerDescId('E01ROCCUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3"> 
              <div align="left">
                <input type="text" name="E01CUSNA1" readonly size="45" maxlength="45" value="<%= gaMant.getE01CUSNA1().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Tipo Garantía :</b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01ROCTYP" readonly size="2" maxlength="2" value="<%= gaMant.getE01ROCTYP().trim()%>">
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Tipo Bien :</b> </div>
            </td>
            <td nowrap colspan="3"> 
              <div align="left">
                <input type="text" name="E01ROCUC9" readonly size="4" maxlength="4" value="<%= gaMant.getE01ROCUC9().trim()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Informaci&oacute;n B&aacute;sica</h4>
      
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Banco /Sucursal /Moneda :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01ROCBNK" size="2" maxlength="2" value="<%= gaMant.getE01ROCBNK().trim()%>" >
              / 
              <input type="text" name="E01ROCBRN" size="4" maxlength="3" value="<%= gaMant.getE01ROCBRN().trim()%>" >
              <a href="javascript:GetBranch('E01ROCBRN',document.forms[0].E01ROCBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="middle" border="0" ></a> 
              / 
              <input type="text" name="E01ROCCCY" size="4" maxlength="3" value="<%= gaMant.getE01ROCCCY().trim()%>" >
              <a href="javascript:GetCurrency('E01ROCCCY',document.forms[0].E01ROCBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
            </td>
                         
          </tr>
          <tr id="trclear">
           <td nowrap > 
              <div align="right">No Cta Contable :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01ROCGLN" size="16" maxlength="16" value="<%= gaMant.getE01ROCGLN().trim()%>" >
              <a href="javascript:GetLedger('E01ROCGLN',document.forms[0].E01ROCBNK.value,document.forms[0].E01ROCCCY.value,'91')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Nombre Propietario :</div>
            </td>
            <td nowrap colspan=3>
              <INPUT type="text" name="E01ROCOWN" size="4" maxlength="4" value="<%= gaMant.getE01ROCOWN().trim()%>"> / 
              <INPUT type="text" name="E01CUSNAM" size="35" maxlength="35" value="<%= gaMant.getE01CUSNAM().trim()%>">
              <A href="javascript:GetCustomerDescId('E01ROCOWN','E01CUSNAM','')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
             </td>            
          </tr>
          <tr id="trclear"> 
            <TD>
              <div align="right">R.U.T Propietario :</div>
            </td>
            <td nowrap colspan=3>
              <INPUT type="text" name="E01ROCRF5" size="13" maxlength="12" value="<%= gaMant.getE01ROCRF5().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Observaciones :</div>
            </td>
            <td nowrap colspan=3> 
              <input type="text" name="E01ROCSP3" size="58" maxlength="56" value="<%= gaMant.getE01ROCSP3().trim()%>" >
             </td>            
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan=3> 
              <input type="text" name="E01ROCSP4" size="58" maxlength="56" value="<%= gaMant.getE01ROCSP4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Estado del Bien :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01ROCRF5" size="13" maxlength="12" value="<%= gaMant.getE01ROCRF5().trim()%>">
            </td>
            <td nowrap > 
              <div align="right">Valor Ajustado :</div>
            </td>
            <td nowrap >
              <INPUT type="text" name="E01ROCAM1" size="17" maxlength="17" value="<%= gaMant.getE01ROCAM1().trim()%>" onkeypress="enterSignDecimal()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Limitada a Operación :</div>
            </td>
            <td nowrap>
            	<INPUT type="text" name="E01ROCUC2" size="5" maxlength="4" value="<%= gaMant.getE01ROCUC2().trim()%>">
            	<a href="javascript:GetCodeCNOFC('E01ROCUC2','2J')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a> 
            </td>
            <td nowrap> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap><INPUT type="text" name="E01ROCCCY" size="6" maxlength="4" value="<%= gaMant.getE01ROCCCY().trim()%>">
              <a href="javascript:GetCurrency('E01ROCCCY',document.forms[0].E01ROCBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
            </td>
            </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Valor :</div>
            </td>
            <td nowrap><INPUT type="text" name="E01ROCRF5" size="13" maxlength="12" value="<%= gaMant.getE01ROCRF5().trim()%>"></td>
            <td nowrap> 
              <div align="right">Estado Cliente : </div>
            </td>
            <td nowrap><INPUT type="text" name="E01ROCRF5" size="13" maxlength="12" value="<%= gaMant.getE01ROCRF5().trim()%>"></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">No. Referencia :</div>
            </td>
            <td nowrap>
             <input type="text" name="E01ROCREF" size="12" maxlength="12" value="<%= gaMant.getE01ROCREF().trim()%>" >
            </td>
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap></td>
          </tr>
          <% if (!gaMant.getE01ROCTYP().equals("05")) { %>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Grado :</div>
            </td>
            <td nowrap <% if (!gaMant.getE01ROCTYP().equals("04")) out.print("colspan=3"); %>>
              <INPUT type="text" name="E01ROCUC1" size="5" maxlength="4" value="<%= gaMant.getE01ROCUC1().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01ROCUC1','2I')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a> 
            </td>
            <% if (gaMant.getE01ROCTYP().equals("04")) {%>
            <td nowrap> 
              <div align="right">Seguro</div>
            </td>
            <td nowrap>
              <input type="hidden" name="E01ROCINF" value="<%= gaMant.getE01ROCINF()%>">
              <input type="radio" name="CE01ROCINF" value="Y" onClick="document.forms[0].E01ROCINF.value='Y'; hidePoliza(true);"
			  <%if(gaMant.getE01ROCINF().equals("Y")) out.print("checked");%>>
              Sí 
              <input type="radio" name="CE01ROCINF" value="N" onClick="document.forms[0].E01ROCINF.value='N'; hidePoliza(false);"
			  <%if(gaMant.getE01ROCINF().equals("N")) out.print("checked");%>>
              No
            </td>
            <% } %>
          </tr>
          <% } %>
         </table>
      </td>
    </tr>
  </table>
  <% if (gaMant.getE01ROCTYP().equals("05")) { %>
   <h4>Datos Fiador</h4>
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">R.U.T:</div>
            </td>
            <td nowrap >
              <INPUT type="text" name="E01ROCUC" size="6" maxlength="4" value="<%= gaMant.getE01ROCUC3().trim()%>">
            </td>
            <td nowrap> 
              <div align="left">Nombre</div>
            </td>
            <td nowrap ><INPUT type="text" name="E01ROCUC" size="6" maxlength="4" value="<%= gaMant.getE01ROCUC3().trim()%>"><A href="javascript:GetCodeCNOFC('E01ROCUC3','2N')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A></td>
          </tr>         
        </table>
      </td>
    </tr>
  </table>
  <% }%>
  <% if (gaMant.getE01ROCTYP().equals("01") || gaMant.getE01ROCTYP().equals("02") ) { %>
   <h4>Datos Propiedad</h4>   
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Descripción : </div>
            </td>
            <td nowrap ><INPUT type="text" name="E01ROCDES" size="36" maxlength="35" value="<%= gaMant.getE01ROCDES().trim()%>"> </td>
            <td nowrap> 
              <div align="right">Propiedad : </div>
            </td>
            <td nowrap ><INPUT type="text" name="E01ROCF6A" size="6" maxlength="4" value="<%= gaMant.getE01RCRF6A().trim()%>">(m2) </td>
          </tr>
   		  <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Ubicación : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCSP2" size="56" maxlength="56" value="<%= gaMant.getE01ROCSP2().trim()%>">
            </td>
            <td nowrap> 
              <div align="right">Comuna : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCUC8" size="7" maxlength="6" value="<%= gaMant.getE01ROCUC8().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tipo Construcción : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCUC3" size="6" maxlength="4" value="<%= gaMant.getE01ROCUC3().trim()%>">
             <A href="javascript:GetCodeCNOFC('E01ROCUC3','2N')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></A>
            </td>
            <td nowrap> 
              <div align="right">Terreno : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCF6C" size="4" maxlength="3" value="<%= gaMant.getE01RCRF6C().trim()%>">
             UM<INPUT type="text" name="E01ROCF6D" size="6" maxlength="5" value="<%= gaMant.getE01RCRF6D().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Rol Propiedad : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCRF7" size="11" maxlength="10" value="<%= gaMant.getE01ROCRF7().trim()%>">
            </td>
            <td nowrap> 
              <div align="right"></div>
            </td>
            <td nowrap >
            </td>
          </tr>  
        </table>
      </td>
    </tr>
  </table>
   
  <% } else if (gaMant.getE01ROCTYP().equals("03")) {%>
    <h4>Datos Propiedad</h4>
   
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Descripción : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCDES" size="36" maxlength="35" value="<%= gaMant.getE01ROCDES().trim()%>">
            </td>
           </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Rol Propiedad : </div>
            </td>
            <td nowrap colspan="3"> 
              <INPUT type="text" name="E01ROCRF7" size="11" maxlength="10" value="<%= gaMant.getE01ROCRF7().trim()%>">
            </td>
          </tr>
		  <%if(currUser.getE01INT().equals("18")){%>          
         <tr id="trdark">
            <td nowrap> 
              <div align="right">Comuna : </div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCUC8" size="7" maxlength="6" value="<%= gaMant.getE01ROCUC8().trim()%>">
            </td>
          </tr>
		  <%}%>             
   </table>
      </td>
    </tr>
  </table>
  <% } %>
  <% if (!gaMant.getE01ROCTYP().equals("04")) { %>
   <h4>Datos Notaria / Conservador</h4>
    <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha Ecritura :</div>
            </td>
            <td nowrap >
              <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">
              <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">
              <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">            
            </td>
            <td nowrap> 
              <div align="right">Notaria :</div>
            </td>
            <td nowrap> 
              <INPUT type="text" name="E01ROCRF4" size=20 maxlength=20 value="<%= gaMant.getE01ROCRF4().trim() %>">                        
            </td>
          </tr>


        <% if (!gaMant.getE01ROCTYP().equals("05")) { %>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Folio de Constitución :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E01ROCFR2" size="20" maxlength="20" value="<%= gaMant.getE01ROCRF2().trim()%>"> 
            </td>
            <td nowrap > 
              <div align="right">Número Constitución :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E01RCRF6" size="3" maxlength="2" value="<%= gaMant.getE01RCRF6A().trim()%>"> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Año Constitución :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E01RCRF6" size="3" maxlength="2" value="<%= gaMant.getE01RCRF6A().trim()%>"> 
            </td>
            <td nowrap > 
              <div align="right">Conserv. Constitución :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01ROCUC7" size="6" maxlength="4" value="<%= gaMant.getE01ROCUC7().trim()%>" >
              <a href="javascript:GetCodeCNOFC('E01ROCUC7','2K')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Folio de Prohibición :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E01RCRF6" size="3" maxlength="2" value="<%= gaMant.getE01RCRF6A().trim()%>"> 
            </td>
            <td nowrap > 
              <div align="right">Número Prohibición :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E01RCRF6" size="3" maxlength="2" value="<%= gaMant.getE01RCRF6A().trim()%>"> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Año Prohibición :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E01RCRF6" size="3" maxlength="2" value="<%= gaMant.getE01RCRF6A().trim()%>"> 
            </td>
            <td nowrap > 
              <div align="right">Conserv. Prohibición :</div>
            </td>
            <td nowrap >               
              <INPUT type="text" name="E01RCRF6" size="3" maxlength="2" value="<%= gaMant.getE01RCRF6A().trim()%>"> 
            </td>
          </tr>
		  <TR id="trclear">
				<TD nowrap align="right">Seguro :</TD>
				<TD nowrap colspan="3"><INPUT type="radio" name="E01ROCINF" value="Y" onclick="hidePoliza(false);"
					<%if(gaMant.getE01ROCINF().equals("Y")) out.print("checked");%>> Sí
				<INPUT type="radio" name="E01ROCINF" value="N" onclick="hidePoliza(true);"
					<%if(!gaMant.getE01ROCINF().equals("Y")) out.print("checked");%>>
				No</TD>
				<TD nowrap align="right">Rollo :</TD>
				<TD nowrap>
					<INPUT type="text" name="E01ROCSP1" size="30" maxlength="28" value="<%= gaMant.getE01ROCSP1().trim()%>">
				</TD>
		  </TR>
		  <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Finca :</div>
            </td>
				<td nowrap colspan="3">
					<INPUT type="text" name="E01ROCSP5" size="56" maxlength="56" value="<%= gaMant.getE01ROCSP5().trim()%>">
				</td>
			<td nowrap > 
              <div align="right">Documento (Imagen) :</div>
            </td>
            <td nowrap>               
            	<INPUT type="text" name="E01ROCSP6" size="30" maxlength="28" value="<%= gaMant.getE01ROCSP6().trim()%>">
			</td>
          </tr>
		<% }%>
		
         <%if( gaMant.getH01SCRCOD().equals("07")){%> 
			<tr id="trclear">
            <td nowrap> 
              <div align="right">Calle : </div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" name="E01NA2"  size="37" maxlength="35" value="<%=gaMant.getE01NA2().trim()%>">
           	</td>
				<TD nowrap align="right">Provincia : </TD>
				<TD nowrap><INPUT type="text" name="D01STE" size="37" maxlength="35"
					value="<%= gaMant.getD01STE().trim()%>"> <A
					href="javascript:GetCodeDescCNOFC('E01STE','D01STE','PV')"> <IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="bottom" border="0" style="cursor:hand"></A></TD>
			</tr>
           <tr id="trdark">
            <td nowrap> 
              <div align="right">Residencial/edificio : </div>
            </td>
				<td nowrap colspan="3"><input type="text" name="E01NA3" size="37"
					maxlength="35" value="<%= gaMant.getE01NA3().trim()%>"></td>
				<TD nowrap align="right">Distrito : </TD>
				<TD nowrap><INPUT type="text" name="D01TID" size="37" maxlength="35"
					value="<%= gaMant.getD01TID().trim()%>"> <IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="bottom" border="0" style="cursor:hand"
					onclick="javascript:GetCodeDescCNOFC('E01TID','D01TID','PI')"></TD>
			</tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">No. Casa/Apto :</div>
            </td>
				<td nowrap colspan="3"><input type="text" name="E01NA4" size="37"
					maxlength="35" value="<%= gaMant.getE01NA4().trim()%>"></td>
				<TD nowrap align="right">Corregimiento : </TD>
				<TD nowrap><INPUT type="text" name="D01PID" size="37" maxlength="35"
					value="<%= gaMant.getD01PID().trim()%>"> <A
					href="javascript:GetCodeDescCNOFC('E01PID','D01PID','PE')"> <IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="bottom" border="0" style="cursor:hand"></A></TD>
			</tr>
           <tr id="trdark">
            <td nowrap> 
              <div align="right">Apartado Postal : </div>
            </td>
				<td nowrap colspan="3"><input type="hidden" name="E01STE" size="6"
					maxlength="4" value="<%= gaMant.getE01STE().trim()%>"> <A
					href="javascript:GetCodeDescCNOFC('E01STE','D01STE','PV')"> </A><INPUT
					type="text" name="E01POB" size="11" maxlength="10"
					value="<%= gaMant.getE01POB().trim()%>"></td>
				<TD nowrap align="right">País : </TD>
				<TD nowrap><INPUT type="text" name="E01CTR" size="21" maxlength="20"
					value="<%= gaMant.getE01CTR().trim()%>"></TD>
			</tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"></div>
            </td>
				<td nowrap colspan="3"><input type="hidden" name="E01TID" size="6"
					maxlength="4" value="<%= gaMant.getE01TID().trim()%>"> </td>
				<TD nowrap align="right">Código Postal : </TD>
				<TD nowrap><%System.out.println( "Cod Postal=" + gaMant.getE01ZPC().trim() ) ; %>
				<INPUT type="hidden" name="E01ZPC" size="17" maxlength="15"
					value='<%= (gaMant.getE01ZPC() != null && !gaMant.getE01ZPC().trim().equals("") && gaMant.getE01ZPC().trim().length() > 4 )
              	?gaMant.getE01ZPC().substring(0,3):""%>'>
				<INPUT type="text" name="D01ZPC" size="17" maxlength="15"
					value='<%= (gaMant.getE01ZPC() != null && !gaMant.getE01ZPC().trim().equals("") && gaMant.getE01ZPC().trim().length() > 5 )
              	?gaMant.getE01ZPC().substring(4):""%>'>
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="bottom" border="0" style="cursor:hand"
					onclick="javascript:GetCodeDescCNOFC('E01ZPC','D01ZPC' ,'59')"></TD>
			</tr>

			<%} else {%>
 
            <tr id="trclear"> 
              <td nowrap> 
                <div align="right">Dirección Principal :</div>
              </td>
				<td nowrap colspan="3"><input type="text" name="E01NA2" size="36"
					maxlength="35" value="<%= gaMant.getE01NA2().trim()%>"></td>
				<TD nowrap align="right">Ciudad : </TD>
				<TD nowrap><INPUT type="text" name="E01CTY" size="31" maxlength="30"
					value="<%= gaMant.getE01CTY().trim()%>"></TD>
			</tr>
            <tr id="trdark"> 
              <td nowrap> 
                <div align="right"></div>
              </td>
				<td nowrap colspan="3"><input type="text" name="E01NA3" size="36"
					maxlength="35" value="<%= gaMant.getE01NA3().trim()%>"></td>
				<TD nowrap align="right">Estado : </TD>
				<TD nowrap><INPUT type="text" name="E01STE" size="5" maxlength="4"
					value="<%= gaMant.getE01STE().trim()%>"> <A
					href="javascript:GetCodeCNOFC('E01STE','27')"> <IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda"
					align="bottom" border="0"></A></TD>
			</tr>
            <tr id="trclear"> 
              <td nowrap> 
                <div align="right"></div>
              </td>
				<td nowrap colspan="3"><input type="text" name="E01NA4" size="36"
					maxlength="35" value="<%= gaMant.getE01NA4().trim()%>"></td>
				<TD nowrap align="right">País : </TD>
				<TD nowrap><INPUT type="text" name="E01CTR" size="21"
					maxlength="20" value="<%= gaMant.getE01CTR().trim()%>"></TD>
			</tr>

            <tr id="trdark"> 
              <td nowrap> 
                <div align="right">Apartado Postal : </div>
              </td>
				<td nowrap colspan="3"><INPUT type="text" name="E01POB" size="11"
					maxlength="10" value="<%= gaMant.getE01POB().trim()%>"></td>
				<TD nowrap align="right">Código Postal : </TD>
				<TD nowrap><INPUT type="text" name="E01ZPC" size="16"
					maxlength="15" value="<%= gaMant.getE01ZPC().trim()%>"></TD>
			</tr>

         <%} %>   


         <% if (gaMant.getE01ROCTYP().equals("03") && currUser.getE01INT().equals("18")) { %>
           <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha Pub. Diaria :</div>
            </td>
            <td nowrap >               
             <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">
             <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">
             <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">
            </td>
            <td nowrap > 
              <div align="right">Fecha Notificación :</div>
            </td>
            <td nowrap >               
             <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">
             <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">
             <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">
            </td>
          </tr>
         <% }%>
   </table>
      </td>
    </tr>
  </table>
  <% }%>
  
  <% if (gaMant.getE01ROCTYP().equals("04")) { %>
   <h4>Individualización Mercaderia</h4>
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">No. Documento :</div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCUC" value="<%= 1 %>">
            </td>
            <td nowrap >
              <div align="right">Banco/Almacen :</div>
            </td>
            <td nowrap >
              <INPUT type="text" name="E01ROCUC6" value="<%= gaMant.getE01ROCUC6()%>">
             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha Vencimiento :</div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">
             <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">
             <INPUT type="text" name="E01ROCUC" size=2 maxlength=2 value="<%= 1 %>">
            </td>
            <td nowrap >
              <div align="right"></div>
            </td>
            <td nowrap >
            </td>
          </tr>
   </table>
      </td>
    </tr>
  </table>
  <% }%>
  
  <% if (!gaMant.getE01ROCTYP().equals("05")) { %>
  <div id="Poliza" style="display:none">
   <h4>Datos Seguro</h4>
   <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tipo de Seguro :</div>
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01ROCTS1" value="1" <% if (gaMant.getE01ROCTS1().equals("1")) out.print("checked"); %> >Incendio
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01ROCTS2" value="1" <% if (gaMant.getE01ROCTS2().equals("1")) out.print("checked"); %> >Vehiculo
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01ROCTS3" value="1" <% if (gaMant.getE01ROCTS3().equals("1")) out.print("checked"); %> >Transporte
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01ROCTS4" value="1" <% if (gaMant.getE01ROCTS4().equals("1")) out.print("checked"); %> >Adicional
            </td>
            <td nowrap >
             <INPUT type="checkbox" name="E01ROCTS5" value="1" <% if (gaMant.getE01ROCTS5().equals("1")) out.print("checked"); %> >Otros
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Poliza :</div>
            </td>
            <td nowrap colspan=2>
             <INPUT type="text" name="E01ROCREF" value="<%= gaMant.getE01ROCREF()%>">
            </td>
            <td nowrap >
              <div align="right">Corredora :</div>
            </td>
            <td nowrap colspan=2>
             <INPUT type="text" name="E01ROCCRR" size="5" maxlength="4" value="<%= gaMant.getE01ROCCRR().trim()%>">
             <a href="javascript:GetCodeCNOFC('E01ROCCRR','2M')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"  ></a>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Compañia :</div>
            </td>
            <td nowrap colspan=2>
             <INPUT type="text" name="E01ROCICN" size="7" maxlength="6" value="<%= gaMant.getE01ROCICN()%>">
            </td>
            <td nowrap >
              <div align="right"></div>
            </td>
            <td nowrap colspan=2>
             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap colspan=2>
             <input type="text" name="E01ROCICY" size="4" maxlength="3" value="<%= gaMant.getE01ROCICY().trim()%>" >
                <a href="javascript:GetCurrency('E01ROCICY',document.forms[0].E01ROCBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a> 
            </td>
            <td nowrap >
              <div align="right">Valor :</div>
            </td>
            <td nowrap colspan=2>
              <INPUT type="text" name="E01ROCIPA" size=15 maxlength=15 value="<%= gaMant.getE01ROCIPA()%>">
             </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha Vto. :</div>
            </td>
            <td nowrap colspan=2>
             <INPUT type="text" name="E01ROCMD1" size=2 maxlength=2 value="<%= gaMant.getE01ROCMD1()%>">
             <INPUT type="text" name="E01ROCMD2" size=2 maxlength=2 value="<%= gaMant.getE01ROCMD2()%>">
             <INPUT type="text" name="E01ROCMD3" size=2 maxlength=2 value="<%= gaMant.getE01ROCMD3()%>">
            </td>
            <td nowrap> 
                <div align="right">Banco/Endosa:</div>
            </td>
            <td nowrap colspan=2>
                <input type="radio" name="E01ROCEDO" value="1" <%if(gaMant.getE01ROCEDO().equals("1")) out.print("checked");%>>
                Sí 
                <input type="radio" name="E01ROCEDO" value="2" <%if(gaMant.getE01ROCEDO().equals("2")) out.print("checked");%>>
                No
            </td>
          </tr>
   		</table>
      </td>
    </tr>
  </table>
  </div>
  <h4>Datos de la Tasación</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">          
   		  <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCCCY" size=3 maxlength=3 value="<%= gaMant.getE01ROCCCY()%>">
             <a href="javascript:GetCurrency('E01ROCCCY',document.forms[0].E01ROCBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a> 
            </td>
            <td nowrap >
              <div align="right">Valor :</div>
            </td>
            <td nowrap >
              <INPUT type="text" name="E01ROCMPU" size=15 maxlength=15 value="<%= gaMant.getE01ROCMPU()%>">
             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha Tasación :</div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCAP1" size=2 maxlength=2 value="<%= gaMant.getE01ROCAP1()%>">
             <INPUT type="text" name="E01ROCAP2" size=2 maxlength=2 value="<%= gaMant.getE01ROCAP2()%>">
             <INPUT type="text" name="E01ROCAP3" size=2 maxlength=2 value="<%= gaMant.getE01ROCAP3()%>">
            </td>
            <td nowrap >
              <div align="right">Fecha Vto. :</div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E01ROCMT1" size=2 maxlength=2 value="<%= gaMant.getE01ROCMT1()%>">
             <INPUT type="text" name="E01ROCMT2" size=2 maxlength=2 value="<%= gaMant.getE01ROCMT2()%>">
             <INPUT type="text" name="E01ROCMT3" size=2 maxlength=2 value="<%= gaMant.getE01ROCMT3()%>">
            </td>
          </tr>
   		</table>
      </td>
    </tr>
  </table>
  <% }%>
 
  <p align="center"> 
    		<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  <SCRIPT Language="Javascript">
	<% if (!gaMant.getE01ROCTYP().equals("05")) {
	     if(gaMant.getE01ROCINF().equals("Y")) { %>
 	       hidePoliza(true);
	    <%} else { %>
 	    hidePoliza(false);
	<%    }
	}%>
  </SCRIPT> 
  </FORM>
 </BODY>
 </html>
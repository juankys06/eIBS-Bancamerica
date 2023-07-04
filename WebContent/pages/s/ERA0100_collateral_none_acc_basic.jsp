<html>
<head>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "collBasic" class= "datapro.eibs.beans.ERA010001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(colla_i_opt); 
   
 function  hidePoliza(value){
 if (value) {
   Poliza.style.display="";}
 else{
   Poliza.style.display="none"; }
}
   
</SCRIPT>

</head>

<body>

 <%@ page import = "datapro.eibs.master.Util" %>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<SCRIPT> initMenu(); </SCRIPT>

<h3 align="center">Consulta de Garantías de no Depósito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="collateral_none_acc_basic.jsp, ERA0100" width="32" height="32"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0100" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="BNKNUM" VALUE="<%= collBasic.getE01ROCBNK().trim()%>">
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente : </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="CUSNA1" size="45" readonly maxlength="45" value="<%= userPO.getCusName().trim() %>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Referencia : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="ACCNUM" size="12" readonly maxlength="12" value="<%= userPO.getIdentifier().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="PROCCY" size="4" readonly maxlength="4" value="<%= userPO.getCurrency().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Tipo de Garant&iacute;a : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="PROCOD" size="4" readonly maxlength="4" value="<%= userPO.getType().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<h4>Sumario</h4>
    <table class="tableinfo">
      <tr > 
        <td >
          <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          	<tr id="trclear"> 
            	<td width=20%><div align="right">Banco/Sucursal : </div></td>
            	<td width=30% NOWRAP> 
              		<div align="left"> 
                		<input type="text" name="E01ROCBNK" readonly size="4" maxlength="2" value="<%= collBasic.getE01ROCBNK().trim()%>">
                		<input type="text" name="E01ROCBRN" readonly size="5" maxlength="3" value="<%= collBasic.getE01ROCBRN().trim()%>">
              		</div>
            	</td>
          	</tr>
            <tr id="trdark"> 
            	<td width=20%><div align="right">Cuenta Contable : </div></td>
            	<td width=30% nowrap> 
              		<div align="left">
                		<input type="text" name="E01ROCGLN" readonly size="18" maxlength="16" value="<%= collBasic.getE01ROCGLN().trim()%>">
              		</div>
            	</td>
          	</tr>
            <tr id="trclear"> 
              	<td width=25%><div align="right">Valor Tasado : </div></td>
            	<td width=22%> 
              		<div align="left">
                  		<input type="text" id="txtright" name="E01ROCAPA" readonly size="17" maxlength="15" value="<%= collBasic.getE01ROCAPA().trim()%>">
                	</div>
              	</td>
            </tr>
            <tr id="trdark"> 
              	<td width=25%><div align="right">Valor Elegible : </div></td>
            	<td width=22%> 
              		<div align="left">
                		<input type="text" id="txtright" name="E01ROCLIV" readonly size="17" maxlength="15" value="<%= collBasic.getE01ROCLIV().trim()%>">
              		</div>
              	</td>
            </tr>
            <tr id="trclear"> 
              	<td width=25%><div align="right">Valor Inelegible : </div></td>
            	<td width=22%> 
              		<div align="left">
                		<input type="text" id="txtright" name="E01ROCINA" readonly size="17" maxlength="15" value="<%= collBasic.getE01ROCINA().trim()%>">
              		</div>
              	</td>
            </tr>
            <tr id="trdark"> 
              	<td width=25%><div align="right">Valor Residual : </div></td>
           		<td width=22%> 
              		<div align="left">
                		<input type="text" id="txtright" name="E01ROCFAA" readonly size="17" maxlength="15" value="<%= collBasic.getE01ROCFAA().trim()%>">
              		</div>
              	</td>
            </tr>
            <tr id="trclear"> 
              	<td width=25%><div align="right">Reajuste de Valor : </div></td>
           		<td width=22%> 
              		<div align="left">
                		<input type="text" id="txtright" name="E01ROCAM1" readonly size="17" maxlength="15" value="<%= collBasic.getE01ROCAM1().trim()%>">
              		</div>
              	</td>
            </tr>  	
            <tr id="trdark"> 
              	<td width=25%><div align="right">Valor en Libros : </div></td>
            	<td width=22%> 
              		<div align="left">
                		<input type="text" id="txtright" name="E01ROCCOP" readonly size="17" maxlength="15" value="<%= collBasic.getE01ROCCOP().trim()%>">
              		</div>
              	</td>
            </tr>
            <tr id="trclear"> 
              	<td width=25%><div align="right">Valor Utilizado : </div></td>
            	<td width=22%> 
              		<div align="left">
                		<input type="text" id="txtright" name="E01ROCUSE" readonly size="17" maxlength="15" value="<%= collBasic.getE01ROCUSE().trim()%>">
              		</div>
              	</td>
            </tr>
            <tr id="trdark"> 
              	<td width=25%><div align="right">Valor Disponible : </div></td>
            	<td width=22%> 
              		<div align="left">
                		<input type="text" id="txtright" name="E01ROCBAL" readonly size="17" maxlength="15" value="<%= collBasic.getE01ROCBAL().trim()%>">
              		</div>
              	</td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
<BR>
<div id="Poliza">
<h4>Poliza de Seguro de Garantía</h4>
<table class="tableinfo" >
    <tr > 
      <td > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td width=22%> 
              <div align="right">Aseguradora : </div>
            </td>
            <td width=23%> 
              <div align="left"> 
                  <input type="text" name="E01ROCICM" readonly size="37" maxlength="35" value="<%= collBasic.getE01ROCICM().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">N&uacute;mero : </div>
            </td>
            <td width=26% nowrap> 
              <div align="left"> 
                  <input type="text" name="E01ROCICN" readonly size="7" maxlength="5" value="<%= collBasic.getE01ROCICN().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=22%> 
              <div align="right">Descripci&oacute;n : </div>
            </td>
            <td width=23%> 
              <div align="left"> 
                  <input type="text" name="E01ROCIPD" readonly size="32" maxlength="30" value="<%= collBasic.getE01ROCIPD().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">Poliza : </div>
            </td>
            <td width=26% nowrap> 
              <div align="left"> 
                  <input type="text" name="E01ROCCIN" readonly size="41" maxlength="40" value="<%= collBasic.getE01ROCCIN().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=22%> 
              <div align="right">Moneda : </div>
            </td>
            <td width=23%> 
              <div align="left"> 
                  <input type="text" name="E01ROCICY" readonly size="5" maxlength="3" value="<%= collBasic.getE01ROCICY().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">Fecha de Emisi&oacute;n : </div>
            </td>
            <td width=26% nowrap> 
              <div align="left"> 
                  <input type="text" name="E01ROCEM1" readonly size="4" maxlength="2" value="<%= collBasic.getE01ROCEM1().trim()%>">
                  <input type="text" name="E01ROCEM2" readonly size="4" maxlength="2" value="<%= collBasic.getE01ROCEM2().trim()%>">
                  <input type="text" name="E01ROCEM3" readonly size="4" maxlength="2" value="<%= collBasic.getE01ROCEM3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=22%> 
              <div align="right">Valor Poliza : </div>
            </td>
            <td width=23%> 
              <div align="left"> 
                  <input type="text" id="txtright" name="E01ROCIPA" readonly size="17" maxlength="15" value="<%= collBasic.getE01ROCIPA().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">Fecha de Vencimiento : </div>
            </td>
            <td width=26% nowrap> 
              <div align="left"> 
                  <input type="text" name="E01ROCMD1" readonly size="4" maxlength="2" value="<%= collBasic.getE01ROCMD1().trim()%>">
                  <input type="text" name="E01ROCMD2" readonly size="4" maxlength="2" value="<%= collBasic.getE01ROCMD2().trim()%>">
                  <input type="text" name="E01ROCMD3" readonly size="4" maxlength="2" value="<%= collBasic.getE01ROCMD3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=22%> 
              <div align="right">Excepci&oacute;n : </div>
            </td>
            <td width=23%> 
              <div align="left"> 
                  <input type="text" name="E01ROCRGK" readonly size="3" maxlength="1" value="<%= collBasic.getE01ROCRGK().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">Aviso de Vencimiento : </div>
            </td>
            <td width=26%> 
              <div align="left"> 
                  <input type="text" name="E01ROCCLF" readonly size="3" maxlength="1" value="<%= collBasic.getE01ROCCLF().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=22%> 
              <div align="right">Emitido por : </div>
            </td>
            <td width=23%> 
              <div align="left"> 
                  <input type="text" name="E01ROCEMB" readonly size="6" maxlength="4" value="<%= collBasic.getE01ROCEMB().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">C&oacute;digo de Usuario : </div>
            </td>
            <td width=26% nowrap> 
              <div align="left"> 
                  <input type="text" name="E01ROCUSC" readonly size="6" maxlength="4" value="<%= collBasic.getE01ROCUSC().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 </div>
  <SCRIPT Language="Javascript">
	<% if(collBasic.getE01ROCINF().equals("Y")){ %>
 	 hidePoliza(true);
	<%} else { %>
 	 hidePoliza(false);
	<%}%>
</SCRIPT>

  <p>&nbsp;</p>
</form>
</body>
</html>


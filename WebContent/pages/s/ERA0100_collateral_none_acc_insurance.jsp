<html>
<head>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "collIns" class= "datapro.eibs.beans.ERA010003Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
function setOptMenu(purpose) {
	if (purpose == "INQUIRY") {
    	builtNewMenu(colla_i_opt);
	} else {
		if (purpose == "APPROVAL_INQ") {
    		builtNewMenu(colla_A_opt);
    	} else {	
    		builtNewMenu(colla_M_opt);
    	}
    }		
    initMenu();
}
</SCRIPT>

</head>
<body>

 <%@ page import = "datapro.eibs.master.Util" %>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<SCRIPT Language="Javascript">
	setOptMenu("<%= userPO.getPurpose() %>");
</SCRIPT>

<h3 align="center">Consulta Poliza de Seguro de Garantías<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="collateral_none_acc_insurance.jsp, ERA0100"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0100" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <input type=HIDDEN name="BNKNUM" value="<%= collIns.getE03ROCBNK().trim()%>">
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
                <input type="text" name="CUSNA1" size="45" readonly maxlength="45" value="<%= userPO.getCusName().trim()%>" >
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
  <h4>&nbsp;</h4>
  <table class="tableinfo">
      <tr > 
        <td >
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td width=22%> 
              <div align="right">Aseguradora : </div>
            </td>
            <td width=23%> 
              <div align="left">
                <input type="text" name="E03ROCICM" readonly size="37" maxlength="35" value="<%= collIns.getE03ROCICM().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">N&uacute;mero : </div>
            </td>
            <td width=26% nowrap> 
              <div align="left"> 
                <input type="text" name="E03ROCICN" readonly size="7" maxlength="5" value="<%= collIns.getE03ROCICN().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=22%> 
              <div align="right">Descripci&oacute;n : </div>
            </td>
            <td width=23%> 
              <div align="left">
                <input type="text" name="E03ROCIPD" readonly size="32" maxlength="30" value="<%= collIns.getE03ROCIPD().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">Poliza : </div>
            </td>
            <td width=26% nowrap> 
              <div align="left">
                <input type="text" name="E03ROCCIN" readonly size="41" maxlength="40" value="<%= collIns.getE03ROCCIN().trim()%>">S
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=22%> 
              <div align="right">Moneda : </div>
            </td>
            <td width=23%> 
              <div align="left">
                <input type="text" name="E03ROCICY" readonly size="5" maxlength="3" value="<%= collIns.getE03ROCICY().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">Fecha de Emisi&oacute;n : </div>
            </td>
            <td width=26% nowrap> 
              <div align="left">
                <input type="text" name="E03ROCEM1" readonly size="4" maxlength="2" value="<%= collIns.getE03ROCEM1().trim()%>">
                <input type="text" name="E03ROCEM2" readonly size="4" maxlength="2" value="<%= collIns.getE03ROCEM2().trim()%>">
                <input type="text" name="E03ROCEM3" readonly size="4" maxlength="2" value="<%= collIns.getE03ROCEM3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=22%> 
              <div align="right">Valor Poliza : </div>
            </td>
            <td width=23%> 
              <div align="left"> 
                <input type="text" id="txtright" name="E03ROCIPA" readonly size="17" maxlength="15" value="<%= collIns.getE03ROCIPA().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">Fecha de Vencimiento : </div>
            </td>
            <td width=26% nowrap> 
              <div align="left"> 
                <input type="text" name="E03ROCMD1" readonly size="4" maxlength="2" value="<%= collIns.getE03ROCMD1().trim()%>">
                <input type="text" name="E03ROCMD2" readonly size="4" maxlength="2" value="<%= collIns.getE03ROCMD2().trim()%>">
                <input type="text" name="E03ROCMD3" readonly size="4" maxlength="2" value="<%= collIns.getE03ROCMD3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width=22%> 
              <div align="right">Excepci&oacute;n : </div>
            </td>
            <td width=23%> 
              <div align="left">
                <input type="text" name="E03ROCRGK" readonly size="3" maxlength="1" value="<%= collIns.getE03ROCRGK().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">Aviso de Vencimiento : </div>
            </td>
            <td width=26%> 
              <div align="left"> 
                <input type="text" name="E03ROCCLF" readonly size="3" maxlength="1" value="<%= collIns.getE03ROCCLF().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width=22%> 
              <div align="right">Emitido por : </div>
            </td>
            <td width=23%> 
              <div align="left">
                <input type="text" name="E03ROCEMB" readonly size="6" maxlength="4" value="<%= collIns.getE03ROCEMB().trim()%>">
              </div>
            </td>
            <td width=29%> 
              <div align="right">C&oacute;digo de Usuario : </div>
            </td>
            <td width=26% nowrap> 
              <div align="left"> 
                <input type="text" name="E03ROCUSC" readonly size="6" maxlength="4" value="<%= collIns.getE03ROCUSC().trim()%>">
              </div>
            </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>

  
</form>
</body>
</html>


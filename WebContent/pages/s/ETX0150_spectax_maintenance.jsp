<%@ page import ="datapro.eibs.master.Util" %>
<html> 
<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.ETX015001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>

<script language="JavaScript">

function goConfirm() {

		
	
      ok = confirm("Esta seguro que desea registrar entrega de documento?");
      
	  if (ok) 
	       {
	       document.forms[0].submit();
	        
	       }  
	  
		  
}

function goCancel(accnum) {

document.forms[0].SCREEN.value=100;
document.forms[0].ACCNUM.value=accnum;
document.forms[0].submit(); 
	  		  
}

</script>

<H3 align="center">Control de Pago de Iva Especial<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="spectax_maintenance.jsp, ETX0150"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSETX0150" >
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
 <INPUT TYPE=HIDDEN NAME="ACCNUM" VALUE="">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
              <input type="text" name="E01TXCACC" size="13" maxlength="12"  value="<%= brnDetails.getE01TXCACC().trim()%>" readonly >
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CUSNA1" size="36" maxlength="35"  value="<%= brnDetails.getE01CUSNA1().trim()%>" readonly >
              </div>
            </td>
            
            <td nowrap width="16%" > 
              <div align="right"><b>Fecha Transacci&oacute;n:</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01TXCPDD" size="30"  maxlength="30" value="<%= Util.formatDate(brnDetails.getE01TXCPDD(),brnDetails.getE01TXCPDM(),brnDetails.getE01TXCPDY())+'-'+Util.formatTime(brnDetails.getE01TXCPDT())%>" readonly>
                </font></font></font></div>
            </td>
                        
            </tr>
            <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Usuario Ult.Entrega :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
              <input type="text" name="E01TXCDUS" size="11" maxlength="10"  value="<%= brnDetails.getE01TXCDUS().trim()%>" readonly >
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
              </div>
            </td>
            
            <td nowrap width="16%" > 
              <div align="right"><b>Fecha Ult.Entrega:</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01TXCDDD" size="30"  maxlength="30" value="<%= Util.formatDate(brnDetails.getE01TXCDDD(),brnDetails.getE01TXCDDM(),brnDetails.getE01TXCDDY())+'-'+Util.formatTime(brnDetails.getE01TXCDTI())%>" readonly>
                </font></font></font></div>
            </td>
                        
            </tr>
            
            
            
            
            
            
            
            
        </table>
      </td>
    </tr>
  </table>
  <h4>Detalle del Movimiento</h4>
<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Iva Cobrado :</div>
            </td>
            <td nowrap width="38%"> 
              <input type="text" name="E01TXCAMT" maxlength="20" size="20" value="<%= brnDetails.getE01TXCAMT().trim()%>" readonly >
            </td>
            <td nowrap width="16%"> 
              <div align="right">Iva Pendiente:</div>
            </td>
            <td nowrap width="38%"> 
              <input type="text" name="E01TXCPND" maxlength="20" size="20" value="<%= brnDetails.getE01TXCPND().trim()%>" readonly >
            </td>
            
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Cuenta de Pago:</div>
            </td>
            <td nowrap height="23" width="38%"> 
            <input type="text" name="E01TXCPBK" size="3" maxlength="2" value="<%= brnDetails.getE01TXCPBK().trim()%>" readonly> 
            &nbsp;
            <INPUT type="text" name="E01TXCPBR" size="4" maxlength="3" value="<%= brnDetails.getE01TXCPBR().trim()%>" readonly>
            &nbsp;
		    <INPUT type="text" name="E01TXCPCY" size="4" maxlength="3" value="<%= brnDetails.getE01TXCPCY().trim()%>" readonly>
            &nbsp;
		    <INPUT type="text" name="E01TXCPGL" size="17" maxlength="16" value="<%= brnDetails.getE01TXCPGL().trim()%>" readonly>
		    &nbsp;
		    <INPUT type="text" name="E01TXCPAC" size="13" maxlength="12" value="<%= brnDetails.getE01TXCPAC().trim()%>" readonly>
		    </td>
            <td nowrap height="23" width="16%"> 
              <div align="right">Tipo de Iva </div>
            </td>
            <td nowrap height="23" width="38%"> 
            <INPUT type="text" name="E01TXCPGL" size="4" maxlength="4" value="<%= brnDetails.getE01TXCTTP().trim()%>" readonly>
              
            </td>
                        
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Fecha L&iacute;mite:</div>
            </td>
            <td nowrap height="19" width="38%"> 
              <input type="text" name="E01TXBFTY" maxlength="10" size="10" value="<%= Util.formatDate(brnDetails.getE01TXCLMD(),brnDetails.getE01TXCLMM(),brnDetails.getE01TXCLMY())%>" readonly>
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">N&uacute;mero Referencia</div>
            </td>
            <td nowrap height="19" width="38%"> 
              <input type="text" name="E01TXCREF" maxlength="21" size="20" value="<%= brnDetails.getE01TXCREF().trim()%>" >
             </td>
            
          </tr>
                 
          
          </table>
      </td>
    </tr>
  </table>
  
          <div align="center"> 
            <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onClick="goConfirm()"> 
            <input id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onClick="goCancel(<%= brnDetails.getE01TXCACC().trim()%>)"> 
          </div>
          
          <script language="JavaScript">
  		  document.forms[0].E01TXCREF.focus();
  		  </script>
  </form>
</body>
</html>

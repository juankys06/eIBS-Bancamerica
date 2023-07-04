<%@ page import ="datapro.eibs.master.Util" %>
<html> 
<head>
<title>Timbres Fiscales por Recaudador</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.ETX016001Message"  scope="session" />

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

		
	
      ok = confirm("Esta seguro que desea registrar este numero de Timbre?");
      
	  if (ok) 
	       {
	       document.forms[0].submit();
	        
	       }  
	  
		  
}

function goCancel(accnum) {

document.forms[0].SCREEN.value=100;
document.forms[0].E01T42CDE.value=accnum;
document.forms[0].submit(); 
	  		  
}

</script>

<H3 align="center">Timbres Fiscales por Recaudador<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="spectax_maintenance.jsp, ETX0160"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSETX0160" >
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
 <INPUT TYPE=HIDDEN NAME="ACCNUM" VALUE="">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Recaudador :</b></div>
            </td>
            <td nowrap width="40%" > 
              <div align="left"> 
              <input type="text" name="E01T42CDE" size="5" maxlength="5"  value="<%= brnDetails.getE01T42CDE().trim()%>" readonly >
			  <input type="text" name="E01T42NME" size="35" maxlength="35"  value="<%= brnDetails.getE01T42NME().trim()%>" readonly >              
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
              
              </div>
            </td>
            
            <td nowrap width="16%" > 
              <div align="right"><b></b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
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
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Total a Reportar:</div>
            </td>
            <td nowrap height="23" width="38%"> 
            <input type="text" name="E01T42AMT" size="20" maxlength="20" value="<%= brnDetails.getE01T42AMT().trim()%>" readonly> 
            &nbsp;
            
            &nbsp;
		    
            &nbsp;
		    
		    &nbsp;
		    </td>
            <td nowrap height="23" width="16%"> 
              <div align="right">Cantidad </div>
            </td>
            <td nowrap height="23" width="38%"> 
            <INPUT type="text" name="E01T42QTY" size="9" maxlength="9" value="<%= brnDetails.getE01T42QTY().trim()%>" readonly>
              
            </td>
                        
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Numero Timbre:</div>
            </td>
            <td nowrap height="19" width="38%"> 
              <input type="text" name="E01T42NUM" maxlength="15" size="16" value="<%= Util.formatCell(brnDetails.getE01T42NUM())%>" >
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right"></div>
            </td>
            <td nowrap height="19" width="38%"> 
             </td>
            
          </tr>
                 
          
          </table>
      </td>
    </tr>
  </table>
  
          <div align="center"> 
            <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onClick="goConfirm()"> 
            <input id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onClick="goCancel(<%= brnDetails.getE01T42CDE().trim()%>)"> 
          </div>
          
          <script language="JavaScript">
  		  document.forms[0].E01T42NUM.focus();
  		  </script>
  </form>
</body>
</html>

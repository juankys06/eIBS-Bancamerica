<html>
<head>
<title>Productos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.ESD094001Message"  scope="session" />

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

		
	
      ok = confirm("Esta seguro que desea crear/modificar este registro?");
      
	  if (ok) 
	       {
	       document.forms[0].SCREEN.value="600";
	       document.forms[0].submit();
	        
	       }  
	  
		  
}

function goCancel(apl, bnk, acc, prod) {

document.forms[0].SCREEN.value="100";
document.forms[0].E01CHGAPL.value=apl;
document.forms[0].E01CHGBNK.value=bnk;
document.forms[0].E01CHGACC.value=acc;
document.forms[0].E01CHGPRC.value=prod;
document.forms[0].submit(); 
	  		  
}

</script>

<H3 align="center">Cambio de Productos <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="branch_maintenance.jsp, ESD0940"></H3>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0940" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
      </td>
    </tr>
  </table>
  <h4>Producto a cambiar</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Banco:</div>
            </td>
            <td nowrap width="55%"> 
				<INPUT type="text" name="E01CHGBNK" maxlength="3" size="2" onkeypress="enterInteger()" value="<%= brnDetails.getE01CHGBNK().trim()%>" readonly >
			</td>
			<TD width="14"></td>
            <td nowrap width="16%"></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Cuenta:</div>
            </td>
            <td nowrap height="23" colspan="1" width="372"> 
			<INPUT type="text" name="E01CHGACC" size="17" maxlength="16"
					value="<%= brnDetails.getE01CHGACC().trim()%>" readonly >
					<A
					href="javascript:GetAccByClient('E01CHGACC',document.forms[0].E01CHGBNK.value,'','','')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
					align="absbottom" border="0">
					</A>
			</td>
            <td nowrap height="23" colspan="1" width="14"> 
              
            </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Producto:</div>
            </td>
            <td nowrap height="19" colspan="3">
            
          <INPUT type="text" name="E01CHGPRC" size="5" maxlength="4"
					value="<%= brnDetails.getE01CHGPRC().trim()%>"><A
					href="javascript:GetProduct('E01CHGPRC',document.forms[0].E01CHGAPL.value,document.forms[0].E01CHGBNK.value)"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda"
					align="absbottom" border="0"></A></tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right"> </div>
            </td>
            <td nowrap height="19" width="55%">
            
			</td>
            <td nowrap height="19" width="2%">
            </td>
            <td nowrap width="28%" height="19">
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right"></div>
            </td>
            <td nowrap height="19" width="55%">
            <INPUT type="hidden" name="E01CHGAPL" maxlength="2" size="2"
					value="<%= brnDetails.getE01CHGAPL().trim()%>" readonly>
			</td>
          </tr>
 		 <tr id="trdark"> 
            <td nowrap width="16%" height="19"></td>
            <td nowrap height="19" width="55%">
            <a href="javascript:GetCorrespondentDescId('E01TXBCOR','E01REQCON','')"></a>		
			</td>
			<td nowrap height="19" width="2%"> 
			<div align="right"> </div>
			</td>
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>	
         </tr>
       
          
          </table>
      </td>
    </tr>
  </table>
  
          <div align="center"> 
	        <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm()"> 
            <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader9().trim() %>','<%= userPO.getHeader10().trim() %>','<%= userPO.getHeader11().trim() %>','<%= userPO.getHeader12().trim() %>','<%= userPO.getHeader15().trim() %>')">
	</div>
          
          <script language="JavaScript">
  		  document.forms[0].E01CHGPRC.focus();
  		  document.forms[0].E01CHGPRC.select();
  		  </script>
          
  </form>
</body>
</html>

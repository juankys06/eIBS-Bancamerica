<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Loans Profile</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="prd" class="datapro.eibs.beans.ESD070001Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<SCRIPT Language="Javascript">
	
	builtNewMenu(prd_loan_opt);
    initMenu();
	
    function showSelClient() {
    
       if (document.forms[0].SELTYP[0].checked) 
       	SELECTIONTABLE.rows[SELECTIONTABLE.rows.length -1].style.display="none";
       else	
        SELECTIONTABLE.rows[SELECTIONTABLE.rows.length -1].style.display="";
        
       document.all.SELFRAME.src = "<%=request.getContextPath()%>/pages/s/EWD0005_client_help_blank.jsp";
    }
  
    function checkValues() {
       if (trim(document.forms[0].E01DLSBNK.value).length < 1) {
              alert("Teclee un código de Banco válido");
              return false; 
       }
       if (trim(document.forms[0].E01DLSTYP.value).length < 1) {
              alert("Teclee un código de Producto válido");
              return false; 
       }
       if (document.forms[0].SELTYP[1].checked) {
       	  if (trim(document.forms[0].E01DLSTLN.value).length < 1) {
              alert("Teclee un código de Tabla válido");
              return false; 
       	  }
          if (document.forms[0].E01DLSCUN.value.length < 1) {
              alert("Teclee un código de Cliente válido");
              return false; 
          }
       }
       return true;
    }
    
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }   
   
 
%> 
</head>
<body>
<h3 align="center">Mantenimiento Tabla de Parametros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="Loans_parameters_enter.jsp"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0510" onsubmit="return(checkValues())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="1">
  
  <table class="tbenter">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbenter">
          <tr> 
            <td nowrap width="45%" align=right> 
              <b>Tarifas por :</b>
            </td>
            <td nowrap> 
               <input type="radio" name="SELTYP" value="1" checked onClick="showSelClient()">Producto
               <input type="radio" name="SELTYP" value="2" onClick="showSelClient()">Cliente
            </td>
          </tr>
  		</table>
      </td>
    </tr>
  </table>
 
  <table class="tbenter">
    <tr> 
      <td nowrap> 
        <table id="SELECTIONTABLE" cellspacing="0" cellpadding="2" width="100%" border="0" class="tbenter">
          <tr> 
            <td nowrap width="45%" align=right> 
              <b>C&oacute;digo Banco :</b>
            </td>
            <td nowrap> 
               <input type="text" name="E01DLSBNK" size="5" maxlength="2" value="<%=prd.getE01APCBNK()%>" readonly onkeypress="enterInteger()">
            </td>
          </tr>
          <tr> 
            <td nowrap align=right> 
              <b>Tipo Producto :</b>
            </td>
            <td nowrap > 
               <input type="text" name="E01DLSTYP" size="5" maxlength="4" value="<%=prd.getE01APCTYP()%>" readonly>
               <!--<input type="hidden" name="DESPRD" value="">
               <a href="javascript:GetCodeDescCNOFC('E01DLSTYP','DESPRD','04')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>-->
          </tr>
          <tr> 
            <td nowrap align=right> 
              <b>C&oacute;digo de Tabla :</b>
            </td>
            <td nowrap> 
               <input type="text" name="E01DLSTLN" size="5" maxlength="2" value="">
               <a href="javascript:GetLoanTable('E01DLSTLN',document.forms[0].E01DLSTYP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
            </td>
          </tr>
          <tr> 
            <td nowrap align=right>
              <b>Cliente :</b>               
            </td>
            <td nowrap> 
               <input type="text" name="E01DLSCUN" size="10" maxlength="9" value="" onkeypress="enterInteger()">          
               <a href="javascript:GetCustomer('E01DLSCUN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>               
            </td>
          </tr>             
        </table>
      </td>
    </tr>
  </table>
  
  <p align="center"> 
      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  
  <IFRAME NAME=SELFRAME SCROLLING=YES FRAMEBORDER=0 ALLOWTRANSPARENCY=TRUE WIDTH=100% HEIGHT=100% SRC="<%=request.getContextPath()%>/pages/s/EWD0005_client_help_blank.jsp"></IFRAME>
  
  </form>
  <SCRIPT Language="Javascript">
    showSelClient();
  </SCRIPT>
</body>
</html>

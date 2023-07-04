<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Loans Profile</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<SCRIPT Language="Javascript">

    function showSelType() {        
       document.all.SELFRAME.src = "<%=request.getContextPath()%>/pages/s/EWD0005_client_help_blank.jsp";
    }
  
    function checkValues() {
       
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
<h3 align="center">Tabla de Seguros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="Loans_insurance_enter.jsp"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.parameters.loans.servlet.JSInsurance" onsubmit="return(checkValues())" target="SELFRAME">
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
  
  <table class="tbenter">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbenter">
          <tr> 
            <td nowrap width="45%" align=right> 
              <b>Tipo de Seguros :</b>
            </td>
            <td nowrap> 
               <input type="radio" name="SELTYP" value="" checked onClick="showSelType()">Autos
               <input type="radio" name="SELTYP" value="V" onClick="showSelType()">Vida
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
            <td width="45%" nowrap align=right> 
              <b>C&oacute;digo de Tabla :</b>
            </td>
            <td nowrap> 
               <input type="text" name="SELTBL" size="5" maxlength="2" value="">
            </td>
          </tr>
                       
        </table>
      </td>
    </tr>
  </table>
  
  <p align="center"> 
      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  
  <IFRAME NAME=SELFRAME SCROLLING=NO FRAMEBORDER=0 ALLOWTRANSPARENCY=TRUE WIDTH=100% HEIGHT=100% SRC="<%=request.getContextPath()%>/pages/s/EWD0005_client_help_blank.jsp"></IFRAME>
  
  </form>
  <SCRIPT Language="Javascript">
    showSelType();
  </SCRIPT>
</body>
</html>

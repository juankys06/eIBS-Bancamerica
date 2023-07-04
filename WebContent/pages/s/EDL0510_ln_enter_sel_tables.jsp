<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>



<META name="GENERATOR" content="IBM WebSphere Studio">
</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
    }
%> 
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL0510" >
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
 	<INPUT TYPE=HIDDEN NAME="SEARCHC" VALUE="">


  <h3 align="center">Tablas de Estructuras de Prestamos</h3>
  <br><br><br><br><br><br>
  <table class="tableinfo">
    <tr> 
      <td valign="middle" align="center" height=33 width="49%"> 
        <div align="right">Banco : </div>
      </td>
      <td valign="middle" align="center" height=33 width="51%"> 
        <div align="left"> 
          <input type="text" name="E01DLSBNK"  size=3 maxlength="2">
        </div>
      </td>
    </tr>
    <tr > 
      <td width="49%" nowrap> 
        <div align="right">Tipo de Producto : </div>
      </td>
      <td width="51%" nowrap> 
        <input type="text" name="E01DLSTYP"  size=5 maxlength="4" onKeyPress="document.forms[0].SEARCHC.value='I'">
         <a href="javascript:GetProductRates('E01DLSTYP','10')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
      </td>
    </tr>
    <tr > 
      <td width="49%" nowrap> 
        <div align="right">Cliente : </div>
      </td>
      <td width="51%" nowrap> 
        <input type="text" name="E01DLSCUN"  size=12 maxlength="9" 
					  >
        <a href="javascript:GetCustomer('E01DLSCUN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
          <tr bgcolor="#FFFFFF"> 
            <td width="33%"> 
              <div align="center"> 
                <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
              </div>
            </td>
          </tr>
          <tr bgcolor="#FFFFFF"> 
            <td> 
              <div align="center"> </div>
            </td>
          </tr>
        </table>
              
</form>
</body>
</html>

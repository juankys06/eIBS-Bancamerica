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
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDD0660" >
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
 	<INPUT TYPE=HIDDEN NAME="SEARCHC" VALUE="">


<h3 align="center">Estructura de Cargos de Pagos y Recibos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="enter_param.jsp, EDD0660"></h3>
<hr size="4">
  <br><br><br><br><br><br>
  <table class="tableinfo">
    <tr> 
      <td valign="middle" align="center" height=33 width="49%"> 
        <div align="right">Banco : </div>
      </td>
      <td valign="middle" align="center" height=33 width="51%"> 
        <div align="left"> 
          <input type="text" name="E01PRFBNK"  size=3 maxlength="2">
        </div>
      </td>
    </tr>
  </table>
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
          <tr bgcolor="#FFFFFF"> 
            <td width="33%"> 
              <div align="center"> 
                <input id="EIBSBTN" type=submit name="Submit" value="Ingresar">
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

<html>
<head>
<title>Instrucciones Especiales</title>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "msgLC" class= "datapro.eibs.beans.ESD000005Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </script>

<script Language="Javascript">

	builtNewMenu(lc_opening);

</script>

</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   out.println("<SCRIPT> initMenu(); </SCRIPT>");

%> 
<h3 align="center">Instrucciones Especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="lc_special_inst.jsp, ELC0500"></h3>
<hr size="4">
 <form METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500" >
  <input TYPE=HIDDEN NAME="SCREEN" VALUE="104">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Numero de Carta de Credito :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E05ACC" size="14" maxlength="12" value="<%= msgLC.getE05ACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>"><br>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp; </p>
  <table class="tableinfo">
    <tr  > 
      <td> 
        <p align="center"> 
          <input type="text" name="E15DSC" size="80" maxlength="80" value="<%= msgLC.getE15DSC().trim()%>"><br>
          <input type="text" name="E25DSC" size="80" maxlength="80" value="<%= msgLC.getE25DSC().trim()%>"><br>
          <input type="text" name="E35DSC" size="80" maxlength="80" value="<%= msgLC.getE35DSC().trim()%>"><br>
          <input type="text" name="E45DSC" size="80" maxlength="80" value="<%= msgLC.getE45DSC().trim()%>"><br>
          <input type="text" name="E55DSC" size="80" maxlength="80" value="<%= msgLC.getE55DSC().trim()%>"><br>
          <input type="text" name="E65DSC" size="80" maxlength="80" value="<%= msgLC.getE65DSC().trim()%>"><br>
          <input type="text" name="E75DSC" size="80" maxlength="80" value="<%= msgLC.getE75DSC().trim()%>"><br>
          <input type="text" name="E85DSC" size="80" maxlength="80" value="<%= msgLC.getE85DSC().trim()%>"><br>
          <input type="text" name="E95DSC" size="80" maxlength="80" value="<%= msgLC.getE95DSC().trim()%>"><br>
          <input type="text" name="E05DSC" size="80" maxlength="80" value="<%= msgLC.getE05DSC().trim()%>"><br>
          <input type="text" name="EA5DSC" size="80" maxlength="80" value="<%= msgLC.getEA5DSC().trim()%>"><br>
          <input type="text" name="EB5DSC" size="80" maxlength="80" value="<%= msgLC.getEB5DSC().trim()%>"><br>
          <input type="text" name="EC5DSC" size="80" maxlength="80" value="<%= msgLC.getEC5DSC().trim()%>"><br>
          <input type="text" name="ED5DSC" size="80" maxlength="80" value="<%= msgLC.getED5DSC().trim()%>"><br>
          <input type="text" name="EE5DSC" size="80" maxlength="80" value="<%= msgLC.getEE5DSC().trim()%>"><br>
          <input type="text" name="EF5DSC" size="80" maxlength="80" value="<%= msgLC.getEF5DSC().trim()%>"><br>
          <input type="text" name="EG5DSC" size="80" maxlength="80" value="<%= msgLC.getEG5DSC().trim()%>"><br>
          <input type="text" name="EH5DSC" size="80" maxlength="80" value="<%= msgLC.getEH5DSC().trim()%>"><br>
          <input type="text" name="EI5DSC" size="80" maxlength="80" value="<%= msgLC.getEI5DSC().trim()%>"><br>
          <input type="text" name="EJ5DSC" size="80" maxlength="80" value="<%= msgLC.getEJ5DSC().trim()%>"><br>
        </p>
      </td>
    </tr>
  </table>
    <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  </form>
</body>
</html>

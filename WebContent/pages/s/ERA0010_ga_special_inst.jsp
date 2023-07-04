<html>
<head>
<title>Instrucciones Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "gaInst" class= "datapro.eibs.beans.ESD000005Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(colla_M_opt);


</SCRIPT>

</head>

<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");

%> 
<h3 align="center">Instrucciones Especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_special_inst,EDL0130"></h3>
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0010" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="12">
  </p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01ROCCUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p></p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <p align="center"> 
          <input type="text" name="E15DSC" size="80" maxlength="80" value="<%= gaInst.getE15DSC().trim()%>"><br>
          <input type="text" name="E25DSC" size="80" maxlength="80" value="<%= gaInst.getE25DSC().trim()%>"><br>
          <input type="text" name="E35DSC" size="80" maxlength="80" value="<%= gaInst.getE35DSC().trim()%>"><br>
          <input type="text" name="E45DSC" size="80" maxlength="80" value="<%= gaInst.getE45DSC().trim()%>"><br>
          <input type="text" name="E55DSC" size="80" maxlength="80" value="<%= gaInst.getE55DSC().trim()%>"><br>
          <input type="text" name="E65DSC" size="80" maxlength="80" value="<%= gaInst.getE65DSC().trim()%>"><br>
          <input type="text" name="E75DSC" size="80" maxlength="80" value="<%= gaInst.getE75DSC().trim()%>"><br>
          <input type="text" name="E85DSC" size="80" maxlength="80" value="<%= gaInst.getE85DSC().trim()%>"><br>
          <input type="text" name="E95DSC" size="80" maxlength="80" value="<%= gaInst.getE95DSC().trim()%>"><br>
          <input type="text" name="E05DSC" size="80" maxlength="80" value="<%= gaInst.getE05DSC().trim()%>"><br>
          <input type="text" name="EA5DSC" size="80" maxlength="80" value="<%= gaInst.getEA5DSC().trim()%>"><br>
          <input type="text" name="EB5DSC" size="80" maxlength="80" value="<%= gaInst.getEB5DSC().trim()%>"><br>
          <input type="text" name="EC5DSC" size="80" maxlength="80" value="<%= gaInst.getEC5DSC().trim()%>"><br>
          <input type="text" name="ED5DSC" size="80" maxlength="80" value="<%= gaInst.getED5DSC().trim()%>"><br>
          <input type="text" name="EE5DSC" size="80" maxlength="80" value="<%= gaInst.getEE5DSC().trim()%>"><br>
          <input type="text" name="EF5DSC" size="80" maxlength="80" value="<%= gaInst.getEF5DSC().trim()%>"><br>
          <input type="text" name="EG5DSC" size="80" maxlength="80" value="<%= gaInst.getEG5DSC().trim()%>"><br>
          <input type="text" name="EH5DSC" size="80" maxlength="80" value="<%= gaInst.getEH5DSC().trim()%>"><br>
          <input type="text" name="EI5DSC" size="80" maxlength="80" value="<%= gaInst.getEI5DSC().trim()%>"><br>
          <input type="text" name="EJ5DSC" size="80" maxlength="80" value="<%= gaInst.getEJ5DSC().trim()%>"><br>
        </p>
      </td>
    </tr>
  </table>
  <% 
    // This goes at the end of the page
  if ( (userPO.getPurpose().equals("NEW")) || (userPO.getPurpose().equals("MAINTENANCE")) ) { %>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>			
  <%	 }
 	 %> 
  <div align="center"> </div>
</form>
</body>
</html>

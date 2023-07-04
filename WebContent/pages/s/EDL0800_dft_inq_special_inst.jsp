<html>
<head>
<title>Instrucciones Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "lnInst" class= "datapro.eibs.beans.ESD000005Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
	builtNewMenu(dft_i_opt);
	initMenu();
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
<h3 align="center">Instrucciones Especiales <%= userPO.getHeader4().trim()%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="dft_special_inst,EDL0800"></h3>
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800" >
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
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E05ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp; </p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <p align="center"> 
          <input type="text" name="E15DSC" size="80" maxlength="80" readonly value="<%= lnInst.getE15DSC().trim()%>"><br>
          <input type="text" name="E25DSC" size="80" maxlength="80" readonly value="<%= lnInst.getE25DSC().trim()%>"><br>
          <input type="text" name="E35DSC" size="80" maxlength="80" readonly value="<%= lnInst.getE35DSC().trim()%>"><br>
          <input type="text" name="E45DSC" size="80" maxlength="80" readonly value="<%= lnInst.getE45DSC().trim()%>"><br>
          <input type="text" name="E55DSC" size="80" maxlength="80" readonly value="<%= lnInst.getE55DSC().trim()%>"><br>
          <input type="text" name="E65DSC" size="80" maxlength="80" readonly value="<%= lnInst.getE65DSC().trim()%>"><br>
          <input type="text" name="E75DSC" size="80" maxlength="80" readonly value="<%= lnInst.getE75DSC().trim()%>"><br>
          <input type="text" name="E85DSC" size="80" maxlength="80" readonly value="<%= lnInst.getE85DSC().trim()%>"><br>
          <input type="text" name="E95DSC" size="80" maxlength="80" readonly value="<%= lnInst.getE95DSC().trim()%>"><br>
          <input type="text" name="E05DSC" size="80" maxlength="80" readonly value="<%= lnInst.getE05DSC().trim()%>"><br>
          <input type="text" name="EA5DSC" size="80" maxlength="80" readonly value="<%= lnInst.getEA5DSC().trim()%>"><br>
          <input type="text" name="EB5DSC" size="80" maxlength="80" readonly value="<%= lnInst.getEB5DSC().trim()%>"><br>
          <input type="text" name="EC5DSC" size="80" maxlength="80" readonly value="<%= lnInst.getEC5DSC().trim()%>"><br>
          <input type="text" name="ED5DSC" size="80" maxlength="80" readonly value="<%= lnInst.getED5DSC().trim()%>"><br>
          <input type="text" name="EE5DSC" size="80" maxlength="80" readonly value="<%= lnInst.getEE5DSC().trim()%>"><br>
          <input type="text" name="EF5DSC" size="80" maxlength="80" readonly value="<%= lnInst.getEF5DSC().trim()%>"><br>
          <input type="text" name="EG5DSC" size="80" maxlength="80" readonly value="<%= lnInst.getEG5DSC().trim()%>"><br>
          <input type="text" name="EH5DSC" size="80" maxlength="80" readonly value="<%= lnInst.getEH5DSC().trim()%>"><br>
          <input type="text" name="EI5DSC" size="80" maxlength="80" readonly value="<%= lnInst.getEI5DSC().trim()%>"><br>
          <input type="text" name="EJ5DSC" size="80" maxlength="80" readonly value="<%= lnInst.getEJ5DSC().trim()%>"><br>
        </p>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>
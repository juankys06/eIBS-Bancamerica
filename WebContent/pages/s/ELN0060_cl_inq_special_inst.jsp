<html>
<head>
<title>Special Instruction</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "clInst" class= "datapro.eibs.beans.ESD000005Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(cl_i_opt);

</SCRIPT>

</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<SCRIPT> initMenu(); </SCRIPT>

<h3 align="center">Instrucciones Especiales  
  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cl_inq_special_inst.jsp, ELN0060"></h3>
<hr size="4">
<p>&nbsp;</p>

 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="36">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="CUSNA1" size="45" readonly maxlength="45" value="<%= userPO.getCusName().trim()%>" >
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Tipo de Linea :</b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="PROCOD" size="4" readonly maxlength="4" value="<%= userPO.getCreditLineType().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>N&uacute;mero de Linea :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="ACCNUM" size="12" readonly maxlength="12" value="<%= userPO.getCreditLineNum().trim()%>" >
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right"><b>Oficial :</b></div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="PROCCY" size="4" readonly maxlength="4" value="<%= userPO.getCurrency().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Instrucciones Especiales</h4>
 <table class="tableinfo">
    <tr id="trdark" > 
      <td nowrap> 
        <p align="center"> 
          <input type="text" readonly name="E15DSC" size="81" maxlength="80" value="<%= clInst.getE15DSC().trim()%>"><br>
          <input type="text" readonly name="E25DSC" size="81" maxlength="80" value="<%= clInst.getE25DSC().trim()%>"><br>
          <input type="text" readonly name="E35DSC" size="81" maxlength="80" value="<%= clInst.getE35DSC().trim()%>"><br>
          <input type="text" readonly name="E45DSC" size="81" maxlength="80" value="<%= clInst.getE45DSC().trim()%>"><br>
          <input type="text" readonly name="E55DSC" size="81" maxlength="80" value="<%= clInst.getE55DSC().trim()%>"><br>
          <input type="text" readonly name="E65DSC" size="81" maxlength="80" value="<%= clInst.getE65DSC().trim()%>"><br>
          <input type="text" readonly name="E75DSC" size="81" maxlength="80" value="<%= clInst.getE75DSC().trim()%>"><br>
          <input type="text" readonly name="E85DSC" size="81" maxlength="80" value="<%= clInst.getE85DSC().trim()%>"><br>
          <input type="text" readonly name="E95DSC" size="81" maxlength="80" value="<%= clInst.getE95DSC().trim()%>"><br>
          <input type="text" readonly name="E05DSC" size="81" maxlength="80" value="<%= clInst.getE05DSC().trim()%>"><br>
          <input type="text" readonly name="EA5DSC" size="81" maxlength="80" value="<%= clInst.getEA5DSC().trim()%>"><br>
          <input type="text" readonly name="EB5DSC" size="81" maxlength="80" value="<%= clInst.getEB5DSC().trim()%>"><br>
          <input type="text" readonly name="EC5DSC" size="81" maxlength="80" value="<%= clInst.getEC5DSC().trim()%>"><br>
          <input type="text" readonly name="ED5DSC" size="81" maxlength="80" value="<%= clInst.getED5DSC().trim()%>"><br>
          <input type="text" readonly name="EE5DSC" size="81" maxlength="80" value="<%= clInst.getEE5DSC().trim()%>"><br>
          <input type="text" readonly name="EF5DSC" size="81" maxlength="80" value="<%= clInst.getEF5DSC().trim()%>"><br>
          <input type="text" readonly name="EG5DSC" size="81" maxlength="80" value="<%= clInst.getEG5DSC().trim()%>"><br>
          <input type="text" readonly name="EH5DSC" size="81" maxlength="80" value="<%= clInst.getEH5DSC().trim()%>"><br>
          <input type="text" readonly name="EI5DSC" size="81" maxlength="80" value="<%= clInst.getEI5DSC().trim()%>"><br>
          <input type="text" readonly name="EJ5DSC" size="81" maxlength="80" value="<%= clInst.getEJ5DSC().trim()%>"><br>
        </p>
      </td>
    </tr>
  </table>
  
</form>
</body>
</html>

<html>
<head>
<title>Instrucciones Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "collInst" class= "datapro.eibs.beans.ESD000005Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   builtNewMenu(coll_i_opt);


</SCRIPT>
</head>

<body nowrap>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
     out.println("<SCRIPT> initMenu(); </SCRIPT>");
%> 
<h3 align="center">Instrucciones Especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="coll_inq_special_inst,EDC0100"></h3>
<hr size="4">
 <FORM >
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
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Oficial :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
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
  <table cellspacing=0 cellpadding=2 width="100%" border="0" bordercolor="#000000">
    <tr bordercolor="#FFFFFF" > 
      <td nowrap> 
        <p align="center"> 
          <input type="text" name="E15DSC" size="80" maxlength="80" value="<%= collInst.getE15DSC().trim()%>" readonly><br>
          <input type="text" name="E25DSC" size="80" maxlength="80" value="<%= collInst.getE25DSC().trim()%>" readonly><br>
          <input type="text" name="E35DSC" size="80" maxlength="80" value="<%= collInst.getE35DSC().trim()%>" readonly><br>
          <input type="text" name="E45DSC" size="80" maxlength="80" value="<%= collInst.getE45DSC().trim()%>" readonly><br>
          <input type="text" name="E55DSC" size="80" maxlength="80" value="<%= collInst.getE55DSC().trim()%>" readonly><br>
          <input type="text" name="E65DSC" size="80" maxlength="80" value="<%= collInst.getE65DSC().trim()%>" readonly><br>
          <input type="text" name="E75DSC" size="80" maxlength="80" value="<%= collInst.getE75DSC().trim()%>" readonly><br>
          <input type="text" name="E85DSC" size="80" maxlength="80" value="<%= collInst.getE85DSC().trim()%>" readonly><br>
          <input type="text" name="E95DSC" size="80" maxlength="80" value="<%= collInst.getE95DSC().trim()%>" readonly><br>
          <input type="text" name="E05DSC" size="80" maxlength="80" value="<%= collInst.getE05DSC().trim()%>" readonly><br>
          <input type="text" name="EA5DSC" size="80" maxlength="80" value="<%= collInst.getEA5DSC().trim()%>" readonly><br>
          <input type="text" name="EB5DSC" size="80" maxlength="80" value="<%= collInst.getEB5DSC().trim()%>" readonly><br>
          <input type="text" name="EC5DSC" size="80" maxlength="80" value="<%= collInst.getEC5DSC().trim()%>" readonly><br>
          <input type="text" name="ED5DSC" size="80" maxlength="80" value="<%= collInst.getED5DSC().trim()%>" readonly><br>
          <input type="text" name="EE5SC" size="80" maxlength="80" value="<%= collInst.getEE5DSC().trim()%>"  readonly><br>
          <input type="text" name="EF5DSC" size="80" maxlength="80" value="<%= collInst.getEF5DSC().trim()%>" readonly><br>
          <input type="text" name="EG5DSC" size="80" maxlength="80" value="<%= collInst.getEG5DSC().trim()%>" readonly><br>
          <input type="text" name="EH5DSC" size="80" maxlength="80" value="<%= collInst.getEH5DSC().trim()%>" readonly><br>
          <input type="text" name="EI5DSC" size="80" maxlength="80" value="<%= collInst.getEI5DSC().trim()%>" readonly><br>
          <input type="text" name="EJ5DSC" size="80" maxlength="80" value="<%= collInst.getEJ5DSC().trim()%>" readonly><br>
        </p>
        
      </td>
    </tr>
  </table>
  <div align="center"> </div>
  </td>
    </tr>
  </table>
</form>
</body>
</html>

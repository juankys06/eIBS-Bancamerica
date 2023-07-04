<html>
<head>
<title>Instrucciones Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "rtInst" class= "datapro.eibs.beans.ESD000005Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
%>

function history(type,code) {
	pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.general.JSEWD0701?SCREEN=1&TYPE=" + type + "&CODE=" + code;
	CenterWindow(pg,600,500,2);
}


</SCRIPT>

</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   out.println("<SCRIPT> initMenu(); </SCRIPT>");

%> 
<h3 align="center">Instrucciones Especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_special_inst,EDD0000"></h3>
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="37">
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
  <tr>	
	<td> 
        <div align="center">  
          <input id="EIBSBTN_LARGE" type=button name="Button" value="Ver Historia de Instrucciones" onClick="javascript:history('1','<%= userPO.getIdentifier().trim()%>')">
        </div>
      </td>
  </tr>
  </table>
  <p>&nbsp; </p>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF" > 
      <td> 
        <p align="center"> 
          <input type="text" name="E15DSC" size="80" maxlength="80" value="<%= rtInst.getE15DSC().trim()%>" readonly>
          <input type="text" name="E25DSC" size="80" maxlength="80" value="<%= rtInst.getE25DSC().trim()%>" readonly>
          <input type="text" name="E35DSC" size="80" maxlength="80" value="<%= rtInst.getE35DSC().trim()%>" readonly>
          <input type="text" name="E45DSC" size="80" maxlength="80" value="<%= rtInst.getE45DSC().trim()%>" readonly>
          <input type="text" name="E55DSC" size="80" maxlength="80" value="<%= rtInst.getE55DSC().trim()%>" readonly>
          <input type="text" name="E65DSC" size="80" maxlength="80" value="<%= rtInst.getE65DSC().trim()%>" readonly>
          <input type="text" name="E75DSC" size="80" maxlength="80" value="<%= rtInst.getE75DSC().trim()%>" readonly>
          <input type="text" name="E85DSC" size="80" maxlength="80" value="<%= rtInst.getE85DSC().trim()%>" readonly>
          <input type="text" name="E95DSC" size="80" maxlength="80" value="<%= rtInst.getE95DSC().trim()%>" readonly>
          <input type="text" name="E05DSC" size="80" maxlength="80" value="<%= rtInst.getE05DSC().trim()%>" readonly>
          <input type="text" name="EA5DSC" size="80" maxlength="80" value="<%= rtInst.getEA5DSC().trim()%>" readonly>
          <input type="text" name="EB5DSC" size="80" maxlength="80" value="<%= rtInst.getEB5DSC().trim()%>" readonly>
          <input type="text" name="EC5DSC" size="80" maxlength="80" value="<%= rtInst.getEC5DSC().trim()%>" readonly>
          <input type="text" name="ED5DSC" size="80" maxlength="80" value="<%= rtInst.getED5DSC().trim()%>" readonly>
          <input type="text" name="EE5DSC" size="80" maxlength="80" value="<%= rtInst.getEE5DSC().trim()%>"  readonly>
          <input type="text" name="EF5DSC" size="80" maxlength="80" value="<%= rtInst.getEF5DSC().trim()%>" readonly>
          <input type="text" name="EG5DSC" size="80" maxlength="80" value="<%= rtInst.getEG5DSC().trim()%>" readonly>
          <input type="text" name="EH5DSC" size="80" maxlength="80" value="<%= rtInst.getEH5DSC().trim()%>" readonly>
          <input type="text" name="EI5DSC" size="80" maxlength="80" value="<%= rtInst.getEI5DSC().trim()%>" readonly>
          <input type="text" name="EJ5DSC" size="80" maxlength="80" value="<%= rtInst.getEJ5DSC().trim()%>" readonly>
        </p>
      </td>
    </tr>
  </table>
  <div align="center"> </div>
</form>
</body>
</html>

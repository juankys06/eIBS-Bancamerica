<%@ page import = "datapro.eibs.beans.EDL111201Message" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Banesco - Abonos Masivos, opcion manual</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">


<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.js"> </SCRIPT>


<jsp:useBean id= "extList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<%@ page import = "datapro.eibs.beans.EDL111201Message" %>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>

<body nowrap>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 	int row;
	try {
		row = Integer.parseInt(request.getParameter("ROW"));
	} 
	catch (Exception e) {
		row = 0;
	}
	extList.setCurrentRow(row);
	EDL111201Message msgLN = (EDL111201Message) extList.getRecord();
%>


<SCRIPT LANGUAGE="JavaScript">

 builtHPopUp();

 function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
 }
 
</SCRIPT>

<h3 align="center">Abonos Masivos, Proceso Manual - Eliminación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_delete_sch.jsp, EDL1112"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL1112" id="form1">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="OPT" VALUE="4">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%=row%>">  
  <INPUT TYPE=HIDDEN NAME="E01DLSSEQ" VALUE="99">            
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Préstamo : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01DEAACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Cliente : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01CUSNA1" size="48" maxlength="45" value="<%= userPO.getCusName().trim()%>" readonly>
              </div>
            </td>

          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información de Pago</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0" id="INFOCHG">

          <tr id="trdark"> 
            <td width="30%"> 
              <div align="right">Valor Recibido :</div>
            </td>
            <td > 
              <input type="text" readonly name="E01DLCVA1" size="17" maxlength="15" value="<%= msgLN.getE01DLCVA1().trim()%>" onkeypress="enterDecimal()">
            </td>
          </tr>
			<tr id="trclear"> 
            <td width="30%"> 
              <div align="right">Valor de Pago :</div>
            </td>
            <td > 
              <input type="text" readonly name="E01DLCVA2" size="17" maxlength="15" value="<%= msgLN.getE01DLCVA2().trim()%>" onkeypress="enterDecimal()">
            </td>
          </tr>

        </table>                       
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

<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>
<jsp:useBean id= "msgPR" class= "datapro.eibs.beans.EPR012001Message"  scope="session"/>

</head>
<body>
  <h3 align="center">Aprobación Masiva de Transferencias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_approval_enter.jsp, EPR1080"> 
  </h3>
  <hr size="4"> 
 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
    }
%>
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="<%= request.getParameter("SCREEN")%>">
  <INPUT TYPE=HIDDEN NAME="ACCNUM" VALUE="<%= request.getParameter("ACCNUM")%>">
  <INPUT TYPE=HIDDEN NAME="approv" VALUE="<%= request.getParameter("approv")%>">

  <table id="tbenter" align="center" style="width:85%" border="1">
    <tr> 
      <td> 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr class="trdark">
            <td width="22%" nowrap> 
              <div align="right">Fecha :</div>
            </td>
            <td nowrap width="60%"> 
				<input type="text" name="E01CUTDTD" size="3" maxlength="2" onKeypress="enterInteger()">
				<input type="text" name="E01CUTDTM" size="3" maxlength="2" onKeypress="enterInteger()">
				<input type="text" name="E01CUTDTY" size="6" maxlength="4" onKeypress="enterInteger()">
				<a href="javascript:DatePicker(document.forms[0].E01CUTDTD,document.forms[0].E01CUTDTM,document.forms[0].E01CUTDTY)">
				<img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
            </td>
          </tr>
          <tr class="trdark">
            <td width="22%" nowrap> 
              <div align="right">Hora :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="E01CUTTIM" size="8" maxlength="6"  onKeypress="enterInteger()" >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
        <p>&nbsp;</p>
  <p><BR>
  </p>
  <p align="center">&nbsp; </p>
      
</form>
</body>
</html>

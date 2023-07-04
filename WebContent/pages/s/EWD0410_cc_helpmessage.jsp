<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Credit Card</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0410Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers

function enter(code) {
var form = top.opener.document.forms[0];
 form[top.opener.fieldName].value = code;
 top.close();
 }
 
//-->
</script>
</head>
<body>
<%
	if ( EWD0410Help.getNoResult() ) {
 %>
   		
<h4 align="center">Credit Card</h4>
<TABLE class="tbenter" width=100% height=100%>
   <TR>
      <TD> 
      	<div align="center"> <b> There is no match for your search criteria </b></div>
      </TD>
    </TR>
</TABLE>
<%  
	}
	else {
%>	  
<TABLE class="tableinfo" align="center" style="width:'95%'">
  <TR id="trdark"> 
    <th >Code </th>
    <th> 
      <div align="left">Description </div>
    </th>
  </tr>
  <%
                EWD0410Help.initRow();
                while (EWD0410Help.getNextRow()) {
                    if (EWD0410Help.getFlag().equals("")) {
                    		out.println(EWD0410Help.getRecord());
                    }
                }
    %> 
</TABLE>
<%
   }  
%>
</body>
</html>
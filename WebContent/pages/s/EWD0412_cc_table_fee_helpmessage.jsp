<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Table Fees</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ewd0412Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>
<script language="javascript">
//<!-- Hide from old browsers
function enter(code) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++){
var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName){
  		top.opener.document.forms[0].elements[n].value = code;
  		break;
	}
 }
top.close();
 }
//-->
</script>
</head>
<body>

<%
	if ( ewd0412Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
      <div align="center"> <b> There is no match for your search 
        criteria </b></div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>
      
    <TABLE class="tableinfo" align="center" style="width:'95%'">
    <TR id="trdark"> 
			     <th width="20%">Structure Number</th>
			     <th width="10%">Bank</th>
				  <th width="20%">Account Type</th>
			     <th width="40%">Description</th>
			  </TR>

    <%
                ewd0412Help.initRow();
                while (ewd0412Help.getNextRow()) {
                    if (ewd0412Help.getFlag().equals("")) {
                    		out.println(ewd0412Help.getRecord());
                    }
                }
    %> 

  </TABLE>

<%
   }
%>
</body>
</html>
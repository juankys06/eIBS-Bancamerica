<html>
<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="Javascript">

function fsubmit() {
 if (document.getElementById("RTYPE").value == "R") {
	if (document.getElementById("ENT").value == "") {
	    alert("You must specify an Entity ID or Select All Entities");
	    document.getElementById("ENT").focus();
		return false;
	} else {
		return true;
	}
  }
}

function updatebox() {
	if (document.getElementById("ALL").checked) {
		document.getElementById("ENT").value = "*";
	} else {
		document.getElementById("ENT").value = "";
	} 
}

function GetDCIBSUsers(name)
{
	page = webapp + "/servlet/com.datapro.eibs.internet.JSLogTransSearch?SCREEN=" + 3;
	fieldName=name;
	CenterWindow(page,630,230,1);

}

</SCRIPT>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "lstEntities" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "lstTransactions" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<body>




<%
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }

%>


<H3 align="center">Historical Access Log <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="DCIBS_log_transaction.jsp, DCIBS"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSLogTransSearch" onsubmit="return fsubmit()">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="RTYPE" VALUE="2">
  
  <p>Transaction List
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF">
      <td nowrap>
        <table cellspacing="0" cellpadding="3" width="100%" border="0" class="tbhead">
          <tr id="trdark">
            <td nowrap width="30%" >
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;<b>Entity ID :</b></div>
            </td>
            <td nowrap colspan="" width="30%">
            	<div align="left">
              		<input type="text" id="ENT" name="ENTITY" size="16" maxlength="15" value="">
              		<a href="javascript:GetDCIBSUsers('ENTITY')">
              		<img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" align="bottom" border="0" ></a>
              	</div>
            </td>
            <td nowrap align="left" width="30%">&nbsp;&nbsp;&nbsp;
          		<input type="checkbox" id="ALL" name="ALL_ENTITY" value="" onclick="updatebox()" >All Entities
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap height="23" width="30%">
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Transaction Type:</div>
            </td>
            <td nowrap colspan="2" width="70%">
            	<div align="left">
          			<SELECT NAME="TRANSCODE" class=inputs >
            			<OPTION VALUE="00000">All</OPTION>
            			<%
						lstTransactions.initRow();
						while(lstTransactions.getNextRow()){
  							com.datapro.eibs.internet.databeans.DC_MENU logtransdatabean = new com.datapro.eibs.internet.databeans.DC_MENU();
  							logtransdatabean = (com.datapro.eibs.internet.databeans.DC_MENU) lstTransactions.getRecord();
						%>
            			<OPTION VALUE="<%= logtransdatabean.getTRANSCODE() %>"> <%= logtransdatabean.getNAME().trim() %></OPTION>
            			<%
						}
						%>
          			</SELECT>
              	</div>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap align="left" colspan="3" width="100%">&nbsp;&nbsp;&nbsp;
              <input type="radio" name="SEARCH" value="D" >Selection By Date
            Range</td>
          </tr>
          <tr id="trclear" >
            <td nowrap align="left" width="30%">&nbsp;</td>
			<td nowrap align="left">From :
				<input type="text" name="FD1" size="2" maxlength="2" value=<%= datapro.eibs.master.Util.getMonth() %> onkeypress="enterInteger()">
				<input type="text" name="FD2" size="2" maxlength="2" value="01" onkeypress="enterInteger()">
				<input type="text" name="FD3" size="2" maxlength="2" value="<%= datapro.eibs.master.Util.getYear() %>" onkeypress="enterInteger()">
				<a href="javascript:DatePicker(document.forms[0].FD1,document.forms[0].FD2,document.forms[0].FD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="Help" align="middle" border="0"></a>
			</td>
	        <td align="left">&nbsp;To :
				<input type="text" name="ED1" size="2" maxlength="2" value="<%= datapro.eibs.master.Util.getMonth() %>" onkeypress="enterInteger()">
				<input type="text" name="ED2" size="2" maxlength="2" value="<%= datapro.eibs.master.Util.getDay() %>" onkeypress="enterInteger()">
				<input type="text" name="ED3" size="2" maxlength="2" value="<%= datapro.eibs.master.Util.getYear() %>" onkeypress="enterInteger()">
				<a href="javascript:DatePicker(document.forms[0].ED1,document.forms[0].ED2,document.forms[0].ED3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="Help" align="middle" border="0"></a>
	        </td>
          </tr>
          <tr id="trdark">
            <td nowrap align="left" colspan="3" width="100%">&nbsp;&nbsp;&nbsp;</td>
          </tr>
          <tr id="trclear">
            <td nowrap align="left" colspan="3" width="100%">&nbsp;&nbsp;&nbsp;
              <input type="radio" name="SEARCH" value="A" CHECKED >Select All
            Dates</td>
          </tr>
          <tr id="trdark">
            <td nowrap align="left" colspan="3" width="100%">&nbsp;&nbsp;&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 <br>
   <p align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Submit" onclick="javascript:document.forms[0].RTYPE.value='R'">
  </p>
 <br>  
  <p> Transaction Graph 
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF">
      <td nowrap>
        <table cellspacing="0" cellpadding="3" width="100%" border="0" class="tbhead">
           <tr id="trdark">
            <td nowrap height="23" width="30%">
              <div align="left">&nbsp;&nbsp;&nbsp;&nbsp;Transaction Type:</div>
            </td>
            <td nowrap colspan="3" width="70%">
            	<div align="left">
          			<SELECT NAME="GTRANSCODE" class=inputs >
            			<OPTION VALUE="00000">All</OPTION>
            			<%
						lstTransactions.initRow();
						while(lstTransactions.getNextRow()){
  							com.datapro.eibs.internet.databeans.DC_MENU logtransdatabean = new com.datapro.eibs.internet.databeans.DC_MENU();
  							logtransdatabean = (com.datapro.eibs.internet.databeans.DC_MENU) lstTransactions.getRecord();
						%>
            			<OPTION VALUE="<%= logtransdatabean.getTRANSCODE() %>"> <%= logtransdatabean.getNAME().trim() %></OPTION>
            			<%
						}
						%>
          			</SELECT>
              	</div>
            </td>
          </tr>
          
        </table>  
      </td>
    </tr>
 </table>                  

  <p align="center">
    <input id="EIBSBTN" type=submit name="Submit" value="Submit" onclick="javascript:document.forms[0].RTYPE.value='G'">
  </p>

  </form>
</body>
</html>

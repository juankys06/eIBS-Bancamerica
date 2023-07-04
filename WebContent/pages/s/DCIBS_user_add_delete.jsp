<!DOCTYPE HTML PUBLIC "-/W3C/DTD HTML 3.2 FINAL/EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META NAME="Author" CONTENT="Datapro">
<META NAME="Generator" CONTENT="NetObjects Fusion 4.0.1 for Windows">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<TITLE></TITLE>

<jsp:useBean id="usersdatabean" class="com.datapro.eibs.internet.databeans.DC_USRPROFILE" scope="session"/>
<jsp:useBean id="grpitmlistbean" class="com.datapro.eibs.internet.beans.JBGroupItemsList" scope="session"/>
<jsp:useBean id="menulistbean" class="com.datapro.eibs.internet.beans.JBMenu" scope="session"/>



<SCRIPT SRC="<%= request.getContextPath() %>/pages/javascripts/dibs.jsp"> </SCRIPT>
<script>
function doPrint(){
  if (!window.print) {
    var msg =       "Dear Customer:\n";
        msg = msg + "to use the print boton,\n";
        msg = msg + "please upgrade your browser.";
    alert(msg);

   return;
  }

  window.focus();
  window.print();

  return;
}
</script>
</HEAD>

<BODY>
<H3 align="center">DCIBS Entity User Inquiry <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="DCIBS_user_add_delete.jsp, DCIBS"></H3>
<hr size="4">
<FORM>

<BR>

<table border=0 cellspacing=0 cellpadding=0 width=90% class="tableinfo">
  <tr> 
    <td width="12"><img src="<%=request.getContextPath()%>/images/ball.gif" vspace="6"></td>
    <td id="sectbody2">Personal Information</td>
  </tr>
</table>

<table width="90%" class="tableinfo">
  <TR id="trdark"> 
    <TD width=200 ALIGN=RIGHT>Entity ID&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="10" name="USERID" readonly value="<%= usersdatabean.getENTITYID().trim() %>" onKeypress="event.returnValue = validCharactersUser()">
  </TR>
  <TR id="trclear"> 
    <TD width=200 ALIGN=RIGHT>User Id&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="10" name="USERID" readonly value="<%= usersdatabean.getUSERID().trim() %>" onKeypress="event.returnValue = validCharactersUser()">
  </TR>
  <TR id="trdark"> 
    <TD width=200 ALIGN=RIGHT>First Name&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="30" name="FIRSTNAME" readonly  value="<%= usersdatabean.getFIRSTNAME().trim() %>"></TD>
  </TR>
  <TR id="trclear"> 
    <TD width=200 ALIGN=RIGHT>Last Name&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="30" name="LASTNAME"  readonly value="<%= usersdatabean.getLASTNAME().trim() %>"></TD>
  </TR>
  <TR id="trdark"> 
    <TD width=200 ALIGN=RIGHT>Position&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="<%= usersdatabean.getTITLE().trim() %>"></TD>
  </TR>
  <TR id="trclear"> 
    <TD width=200 ALIGN=RIGHT>e-Mail&nbsp;</TD>
    <TD><INPUT size="35" type="text" maxlength="35" name="EMAIL" readonly  value="<%= usersdatabean.getEMAIL().trim() %>" onblur="isValidEmail(document.forms[0].EMAIL.value)"></TD>
  </TR>
  <TR id="trdark"> 
    <TD width=200 ALIGN=RIGHT>Role&nbsp;</TD>
    <TD><INPUT type="radio" name="ROLE" <%if (usersdatabean.getROLE().equals("0")) { out.print("checked"); }%> disabled value="0">OPERATOR 
              <INPUT type="radio" name="ROLE" <%if (usersdatabean.getROLE().equals("1")) { out.print("checked"); }%> disabled value="1">SUPERVISOR 
              <INPUT type="radio" name="ROLE" <%if (usersdatabean.getROLE().equals("2")) { out.print("checked"); }%> disabled value="2">BOTH</TD>
  </TR>
 </TABLE>

<br>
<table border=0 cellspacing=0 cellpadding=0 width=90% class="tableinfo">
  <tr> 
    <td width="12"><img src="<%=request.getContextPath()%>/images/ball.gif" vspace="6"></td>
    <td id="sectbody2">Menu Options</td>
  </tr>
</table>
<table border=1 bordercolor="#6684A3" style="border-collapse: collapse" CELLSPACING=0 CELLPADDING=0 width="90%" class="tableinfo">
<%
  menulistbean.initRow();
  while (menulistbean.getNextRow()) {
    boolean isItmInGrp = grpitmlistbean.isHere(menulistbean.getMMID(), menulistbean.getCMID());
    if (menulistbean.getCMID() == 0) {
%>
    <TR > 
      <TD width=40%><INPUT type="checkbox" disabled name="CHK_<%= menulistbean.getMMID() + "" %>_<%= menulistbean.getCMID() + "" %>" <%if (isItmInGrp) { out.print("checked"); } //else {if (String.valueOf(menulistbean.getCMID()).equals("0")) { out.print("disabled"); }}%> >
          <b><%= menulistbean.getNAME() %></b>
      </TD>
      <%
    if (menulistbean.getFLAG().equals("Y")) {
%>
      <TD width=30% align="right">Approval Number &nbsp;&nbsp;</TD>
      <TD width=30%> 
        <SELECT size="1" name="<%= menulistbean.getCHK() %>" disabled>
          <%
       if (isItmInGrp) {
%>
          <OPTION value="2" <%if (grpitmlistbean.getAPPLEVEL() == 2) { out.print("selected"); }%>>1</OPTION>
          <OPTION value="3" <%if (grpitmlistbean.getAPPLEVEL() == 3) { out.print("selected"); }%>>2</OPTION>
          <%
       }
       else {
%>
          <OPTION value="2">1</OPTION>
          <OPTION value="3">2</OPTION>
          <%
       }
%>
        </SELECT>
      </TD>
      <%
    }
    else {
%>
      <%
    }
%>
    </TR>
    <%
    }
    else {
%>
    <TR > 
      <TD width=40%> 
        <INPUT type="checkbox" disabled name="CHK_<%= menulistbean.getMMID() + "" %>_<%= menulistbean.getCMID() + "" %>" <%if (isItmInGrp) { out.print("checked"); }%> onclick="doCheck(this)">
        <%= menulistbean.getNAME() %></TD>
      <%
    if (menulistbean.getFLAG().equals("Y")) {
%>
      <TD width="30%" align="right">Limit&nbsp;&nbsp;</TD>
      <TD width="30%"> 
        <%
       if (isItmInGrp) {
%>
        <INPUT size="20" type="text" maxlength="9" name="<%= menulistbean.getCHK() %>" readonly value="<%=grpitmlistbean.getLMTAMNT() + "" %>"  >
        <%
       }
       else {
%>
        <INPUT size="20" type="text" maxlength="9" name="<%= menulistbean.getCHK() %>" readonly value="0,00" >
        <%
       }
%>
      </TD>
      <%
    }
    else {
%>
      <%
    }
%>
    </TR>
    <%
    }
  }
%>
  </TABLE>  


<BR>

<table border=0 cellspacing=0 cellpadding=0 width=90% class="tableinfo">
  <tr> 
    <td width="12"><img src="<%=request.getContextPath()%>/images/ball.gif" vspace="6"></td>
    <td id="sectbody2">ACCOUNTS</td>
  </tr>
</table>

<table  width="90%" class="tableinfo">
  <TR id="rowlight">
    <TD width=200 ALIGN=right>Include/Exclude&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="
<%if (usersdatabean.getINCEXC().trim().equals("N")) { out.print("ALL"); }%>
<%if (usersdatabean.getINCEXC().trim().equals("I")) { out.print("INCLUDE"); }%>
<%if (usersdatabean.getINCEXC().trim().equals("E")) { out.print("EXCLUDE"); }%>
  "></TD>
  </TR> 
  <TR id="trdark">
    <TD align="right">Account&nbsp;1&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="<%= usersdatabean.getEXACC01().trim() %>"></TD>
  </TR>
  <TR id="trclear">
    <TD align="right">Account&nbsp;2&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="<%= usersdatabean.getEXACC02().trim() %>"></TD>
  </TR>
  <TR id="trdark">
    <TD align="right">Account&nbsp;3&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="<%= usersdatabean.getEXACC03().trim() %>"></TD>
  </TR>
  <TR id="trclear">
    <TD align="right">Account&nbsp;4&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="<%= usersdatabean.getEXACC04().trim() %>"></TD>
  </TR>
  <TR id="trdark">
    <TD align="right">Account&nbsp;5&nbsp;</TD>
   <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="<%= usersdatabean.getEXACC05().trim() %>"></TD>
  </TR>  <TR id="trclear">
    <TD align="right">Account&nbsp;6&nbsp;</TD>
   <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="<%= usersdatabean.getEXACC06().trim() %>"></TD>
  </TR>
  <TR id="trdark">
    <TD align="right">Account&nbsp;7&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="<%= usersdatabean.getEXACC07().trim() %>"></TD>
  </TR>
  <TR id="trclear">
    <TD align="right">Account&nbsp;8&nbsp;</TD>
   <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="<%= usersdatabean.getEXACC08().trim() %>"></TD>
  </TR>
  <TR id="trdark">
    <TD align="right">Account&nbsp;9&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="<%= usersdatabean.getEXACC09().trim() %>"></TD>
  </TR>
  <TR id="trclear">
    <TD align="right">Account&nbsp;10&nbsp;</TD>
    <TD><INPUT size="30" type="text" maxlength="30" name="TITLE" readonly  value="<%= usersdatabean.getEXACC10().trim() %>"></TD>
  </TR>
</TABLE>

<BR>

<table border=0 CELLSPACING=0 CELLPADDING=0 WIDTH=90%>
  <TR>
    <TD width=50% align=center><input id="EIBSBTN" type=button value=PRINT  onClick="doPrint()"></TD>
	<TD width=50% align=center><input id="EIBSBTN" type=button value="RETURN" onClick="window.location.href='DCIBS_user_add_delete_list.jsp'"></TD>
  </TR>
</TABLE>




</FORM>
</BODY>
</HTML>

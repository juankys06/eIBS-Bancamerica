<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Corporate User</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="userDetail" class="datapro.eibs.beans.ESS204002Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<style TYPE="text/css">
.textchanged
{
	font-family: "Verdana, Arial, Helvetica, sans-serif";
	font-size:8pt;
	color:#01016E;
	background-color:#FFFF80;
}
</style>
</head>

<body bgcolor="#FFFFFF">
<%
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0") ;
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 %>
<SCRIPT language="JavaScript">
  function Action(op){
     document.forms[0].ACTION.value = op;
     document.forms[0].submit();
  }
</SCRIPT>

<h3 align="center">Approve Internet Banking  User<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="User_Details, ESS2040" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2040" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="X">
  <% if(userDetail.getH02FLGWK3().trim().equals("Y")){ %>
    <h4><FONT color="GREEN">General Information for New User</FONT></h4>
  <% }else{%>
    <h4><FONT color="RED">General Information for User Change</FONT></h4>
  <% }%>

  <table class="tableinfo">
      <tr >
        <td nowrap>

        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">User ID :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY  type="text" READONLY ID="MANDATORY" name="USERID" size="35" maxlength="10" READONLY value="<%= userDetail.getE02EUSUSR().trim()%>"></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">User Password :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY class="<%= userDetail.getF02EUSACP().equals("Y")?"textchanged":"" %>"  name="E02EUSACP" size="35" maxlength="10" value=""></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">User Status :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSSTS" class="<%= userDetail.getF02EUSSTS().equals("Y")?"textchanged":"" %>" >
             <OPTION VALUE="1" <%if (userDetail.getE02EUSSTS().equals("1")) {out.print("selected"); }%>>ACTIVE</OPTION>
             <OPTION VALUE="2" <%if (userDetail.getE02EUSSTS().equals("2")) {out.print("selected"); }%>>INACTIVE</OPTION>
             <OPTION VALUE="3" <%if (userDetail.getE02EUSSTS().equals("3")) {out.print("selected"); }%>>SUSPENDED</OPTION>
             <OPTION VALUE="4" <%if (userDetail.getE02EUSSTS().equals("4")) {out.print("selected"); }%>>PENDING ACTIVATION</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Customer Number :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY class="<%= userDetail.getF02EUSCUN().equals("Y")?"textchanged":"" %>" type="text" name="E02EUSCUN" size="35" maxlength="9" value="<%= userDetail.getE02EUSCUN().trim()%>">
                                    </div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Primary Account Number :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY class="<%= userDetail.getF02EUSACC().equals("Y")?"textchanged":"" %>" type="text" ID="MANDATORY" name="E02EUSACC" size="35" maxlength="9" value="<%= userDetail.getE02EUSACC().trim()%>" onkeypress="enterInteger()">
                                    </div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Maximum Acumulated Type :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT DISABLED  NAME="E02EUSMXT" class="<%= userDetail.getF02EUSMXT().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="1" <%if (userDetail.getE02EUSMXT().equals("1")) {out.print("selected"); }%>>DAILY MAXIMUN AMOUNT PERMITTED</OPTION>
             <OPTION VALUE="2" <%if (userDetail.getE02EUSMXT().equals("2")) {out.print("selected"); }%>>WEEKLY MAXMIMUN AMOUNT PERMITTED</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Internal Transfers Permitted :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT DISABLED  NAME="E02EUSTRA" DISABLED class="<%= userDetail.getF02EUSTRA().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="Y" <%if (userDetail.getE02EUSTRA().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             <OPTION VALUE="N" <%if (userDetail.getE02EUSTRA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Third Party Transfers Permitted :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSTPT" class="<%= userDetail.getF02EUSTPT().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="Y" <%if (userDetail.getE02EUSTPT().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             <OPTION VALUE="N" <%if (userDetail.getE02EUSTPT().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">External Transfers Permitted :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSEXT" class="<%= userDetail.getF02EUSEXT().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="Y" <%if (userDetail.getE02EUSEXT().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             <OPTION VALUE="N" <%if (userDetail.getE02EUSEXT().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Bill Payable Permitted :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSBPA" class="<%= userDetail.getF02EUSBPA().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="Y" <%if (userDetail.getE02EUSBPA().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             <OPTION VALUE="N" <%if (userDetail.getE02EUSBPA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">L/C Permitted :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSLCA" class="<%= userDetail.getF02EUSLCA().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="Y" <%if (userDetail.getE02EUSLCA().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             <OPTION VALUE="N" <%if (userDetail.getE02EUSLCA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
           <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Loans Payable Permitted :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSLNA" class="<%= userDetail.getF02EUSLNA().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="Y" <%if (userDetail.getE02EUSLNA().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             <OPTION VALUE="N" <%if (userDetail.getE02EUSLNA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Credit Card Payable Permitted :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSCCP" class="<%= userDetail.getF02EUSCCP().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="Y" <%if (userDetail.getE02EUSCCP().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             <OPTION VALUE="N" <%if (userDetail.getE02EUSCCP().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Internal Transfers Limit :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY class="<%= userDetail.getF02EUSMAX().equals("Y")?"textchanged":"" %>" type="text" name="E02EUSMAX" size="35" maxlength="13" value="<%= userDetail.getE02EUSMAX().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Internal Transfers Acumulated Limit :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY class="<%= userDetail.getF02EUSAMX().equals("Y")?"textchanged":"" %>" type="text" name="E02EUSAMX" size="35" maxlength="13" value="<%= userDetail.getE02EUSAMX().trim()%>"></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Third Party Transfers Limit :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY class="<%= userDetail.getF02EUSTTL().equals("Y")?"textchanged":"" %>" type="text" name="E02EUSTTL" size="35" maxlength="13" value="<%= userDetail.getE02EUSTTL().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Third Party Transfers Acumulated Limit :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY class="<%= userDetail.getF02EUSTAL().equals("Y")?"textchanged":"" %>" type="text" name="E02EUSTAL" size="35" maxlength="13" value="<%= userDetail.getE02EUSTAL().trim()%>"></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">External Transfers Limit :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY class="<%= userDetail.getF02EUSETL().equals("Y")?"textchanged":"" %>" type="text" name="E02EUSETL" size="35" maxlength="13" value="<%= userDetail.getE02EUSETL().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">External Transfers Acumulated Limit :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY class="<%= userDetail.getF02EUSEAL().equals("Y")?"textchanged":"" %>" type="text" name="E02EUSEAL" size="35" maxlength="13" value="<%= userDetail.getE02EUSEAL().trim()%>"></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Person Responsible :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY class="<%= userDetail.getF02EUSCON().equals("Y")?"textchanged":"" %>" type="text" name="E02EUSCON" size="35" maxlength="30" value="<%= userDetail.getE02EUSCON().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">E-Mail Address :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY class="<%= userDetail.getF02EUSIAD().equals("Y")?"textchanged":"" %>" type="text" name="E02EUSIAD" size="35" maxlength="45" value="<%= userDetail.getE02EUSIAD().trim()%>"></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Confirmation Type :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSCFM" class="<%= userDetail.getF02EUSCFM().equals("Y")?"textchanged":"" %>" >
             <OPTION VALUE="1" <%if (userDetail.getE02EUSCFM().equals("1")) {out.print("selected"); }%>>NO CONFIRMATION REQUIRED</OPTION>
             <OPTION VALUE="2" <%if (userDetail.getE02EUSCFM().equals("2")) {out.print("selected"); }%>>CALL-BACK REQUIRED</OPTION>
             <OPTION VALUE="3" <%if (userDetail.getE02EUSCFM().equals("3")) {out.print("selected"); }%>>INTERNET ADVICE TO CUSTOMER</OPTION>
             <OPTION VALUE="4" <%if (userDetail.getE02EUSCFM().equals("4")) {out.print("selected"); }%>>E-MAIL ADVICE TO CUSTOMER</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Include/Exclude Accounts :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSIEF" class="<%= userDetail.getF02EUSIEF().equals("Y")?"textchanged":"" %>" >
             <OPTION VALUE=" " <%if (userDetail.getE02EUSIEF().equals(""))  {out.print("selected"); }%>>NO</OPTION>
             <OPTION VALUE="I" <%if (userDetail.getE02EUSIEF().equals("I")) {out.print("selected"); }%>>INCLUDE</OPTION>
             <OPTION VALUE="E" <%if (userDetail.getE02EUSIEF().equals("E")) {out.print("selected"); }%>>EXCLUDE</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">ACCESS ACT #1 :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY  type="text" class="<%= userDetail.getF02EUSA01().equals("Y")?"textchanged":"" %>" name="E02EUSA01" size="35" maxlength="9" value="<%= userDetail.getE02EUSA01().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">ACCESS ACT #2 :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY  type="text" class="<%= userDetail.getF02EUSA02().equals("Y")?"textchanged":"" %>" name="E02EUSA02" size="35" maxlength="9" value="<%= userDetail.getE02EUSA02().trim()%>"></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">ACCESS ACT #3 :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY  type="text" class="<%= userDetail.getF02EUSA03().equals("Y")?"textchanged":"" %>" name="E02EUSA03" size="35" maxlength="9" value="<%= userDetail.getE02EUSA03().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">ACCESS ACT #4 :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY  type="text" class="<%= userDetail.getF02EUSA04().equals("Y")?"textchanged":"" %>" name="E02EUSA04" size="35" maxlength="9" value="<%= userDetail.getE02EUSA04().trim()%>"></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">ACCESS ACT #5 :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY  type="text" class="<%= userDetail.getF02EUSA05().equals("Y")?"textchanged":"" %>" name="E02EUSA05" size="35" maxlength="9" value="<%= userDetail.getE02EUSA05().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">ACCESS ACT #6 :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY  type="text" class="<%= userDetail.getF02EUSA06().equals("Y")?"textchanged":"" %>" name="E02EUSA06" size="35" maxlength="9" value="<%= userDetail.getE02EUSA06().trim()%>"></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">ACCESS ACT #7 :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY  type="text" class="<%= userDetail.getF02EUSA07().equals("Y")?"textchanged":"" %>" name="E02EUSA07" size="35" maxlength="9" value="<%= userDetail.getE02EUSA07().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">ACCESS ACT #8 :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY  type="text" class="<%= userDetail.getF02EUSA08().equals("Y")?"textchanged":"" %>" name="E02EUSA08" size="35" maxlength="9" value="<%= userDetail.getE02EUSA08().trim()%>"></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">ACCESS ACT #9 :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY  type="text" class="<%= userDetail.getF02EUSA09().equals("Y")?"textchanged":"" %>" name="E02EUSA09" size="35" maxlength="9" value="<%= userDetail.getE02EUSA09().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">ACCESS ACT #10 :</div></td>
            <td nowrap width="60%" ><div align="left"> <INPUT READONLY  type="text" class="<%= userDetail.getF02EUSA10().equals("Y")?"textchanged":"" %>" name="E02EUSA10" size="35" maxlength="9" value="<%= userDetail.getE02EUSA10().trim()%>"></div></td>
          </tr>
        </table>
        </td>
      </tr>
    </table>

  <table border="0" width="95%">
    <tr align="center">
       <td><INPUT id="EIBSBTN" type=BUTTON name="Submit" value="Reject" onclick="JavaScript:Action('N')"></td>
       <td><INPUT id="EIBSBTN" type=BUTTON name="Submit" value="Approve" onclick="JavaScript:Action('Y')"></td>
    </tr>
  </table>	
</form>
</body>
</html>

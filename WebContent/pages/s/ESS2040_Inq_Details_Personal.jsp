<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Corporate User</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />
<jsp:useBean id="userDetail" class="datapro.eibs.beans.ESS204002Message" scope="session" />

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
<SCRIPT language="JavaScript">
  function Action(op){
     document.forms[0].ACTION.value = op;
     document.forms[0].submit();
  }
</SCRIPT>
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


<h3 align="center">Internet Banking Personal User<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cliente_corporate_new_data, ESS2000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2040" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="X">
  <table class="tableinfo">
      <tr >
        <td nowrap>

        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">User ID :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="text" READONLY ID="MANDATORY" name="E02EUSUSR" class="<%= userDetail.getF02EUSUSR().equals("Y")?"textchanged":"" %>" size="35" maxlength="10" READONLY value="<%= userDetail.getE02EUSUSR().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">User Password :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="password" name="E02EUSACP" class="<%= userDetail.getF02EUSACP().equals("Y")?"textchanged":"" %>" size="35" maxlength="10" value="<%= userDetail.getE02EUSACP().trim()%>"></div></td>
          </tr>
          <!-- tr id="trdark">
            <td nowrap width="40%" ><div align="right">User Status :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSSTS">
             <OPTION VALUE="1" <%if (userDetail.getE02EUSSTS().equals("1")) {out.print("selected"); }%>>ACTIVE</OPTION>
             <OPTION VALUE="2" <%if (userDetail.getE02EUSSTS().equals("2")) {out.print("selected"); }%>>INACTIVE</OPTION>
             <OPTION VALUE="3" <%if (userDetail.getE02EUSSTS().equals("3")) {out.print("selected"); }%>>SUSPENDED</OPTION>
             <OPTION VALUE="4" <%if (userDetail.getE02EUSSTS().equals("4")) {out.print("selected"); }%>>PENDING ACTIVATION</OPTION>
             </SELECT></div></td>
          </tr -->
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Customer Number :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="text" ID="MANDATORY" name="E02EUSCUN" class="<%= userDetail.getF02EUSCUN().equals("Y")?"textchanged":"" %>" size="35" maxlength="9" value="<%= userDetail.getE02EUSCUN().trim()%>">
                                    </div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Primary Account Number :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="text" ID="MANDATORY" name="E02EUSACC" class="<%= userDetail.getF02EUSACC().equals("Y")?"textchanged":"" %>" size="35" maxlength="9" value="<%= userDetail.getE02EUSACC().trim()%>" onkeypress="enterInteger()">
                                    </div></td>
          </tr>
			<TR>
				<TD nowrap width="40%" colspan="2" align="center">
				   <TABLE width="70%">
				      <TR>
				         <TD colspan="4" align="center" >TRANSACTIONS PERMISSION</TD>
				      </TR>
					  <tr align="center" id="trdark">
					     <TD>Transaction</TD>
					     <TD>Permitted</TD>
					     <TD>Transaction Limit</TD>
					     <TD>Acumulated Limit</TD>
					  </tr>
					  <tr align="center">
					     <TD align="right">Internal Transfer :</TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSTRA" class="<%= userDetail.getF02EUSTRA().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSTRA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSTRA().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD><input readonly  type="text" name="E02EUSMAX" class="<%= userDetail.getF02EUSMAX().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSMAX().trim()%>"></TD>
					     <TD><input readonly  type="text" name="E02EUSAMX" class="<%= userDetail.getF02EUSAMX().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSAMX().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Third Party Transfer :</TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSTPT" class="<%= userDetail.getF02EUSTPT().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSTPT().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSTPT().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD><input readonly  type="text" name="E02EUSTTL" class="<%= userDetail.getF02EUSTTL().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSTTL().trim()%>"></TD>
					     <TD><input readonly  type="text" name="E02EUSTAL" class="<%= userDetail.getF02EUSTAL().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSTAL().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">External Transfer :</TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSEXT" class="<%= userDetail.getF02EUSEXT().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSEXT().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSEXT().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD><input readonly  type="text" name="E02EUSETL" class="<%= userDetail.getF02EUSETL().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSETL().trim()%>"></TD>
					     <TD><input readonly  type="text" name="E02EUSEAL" class="<%= userDetail.getF02EUSEAL().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSEAL().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Bill Payable Permitted :</TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSBPA" class="<%= userDetail.getF02EUSBPA().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSBPA().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSBPA().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD><input readonly  type="text" name="E02EUSBPL" class="<%= userDetail.getF02EUSBPL().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSBPL().trim()%>"></TD>
					     <TD><input readonly  type="text" name="E02EUSBAA" class="<%= userDetail.getF02EUSBAA().equals("Y")?"textchanged":"" %>"  size="25" maxlength="13" value="<%= userDetail.getE02EUSBAA().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Letter of Credits Permitted :</TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSLCA" class="<%= userDetail.getF02EUSLCA().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSLCA().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSLCA().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD><input readonly  type="text" name="E02EUSLCL" class="<%= userDetail.getF02EUSLCL().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSLCL().trim()%>"></TD>
					     <TD><input readonly  type="text" name="E02EUSLAA" class="<%= userDetail.getF02EUSLAA().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSLAA().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Loans Payable Permitted : </TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSLNA" class="<%= userDetail.getF02EUSLNA().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSLNA().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSLNA().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD></TD>
					     <TD></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Credit Card Payable Permitted : </TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSCCP" class="<%= userDetail.getF02EUSCCP().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSCCP().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSCCP().equals("Y")) {out.print("selected"); }%>>YES</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD></TD>
					     <TD></TD>
					  </tr>					  
				   </TABLE>
				</TD>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Maximum Acumulated Type :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT DISABLED  NAME="E02EUSMXT" class="<%= userDetail.getF02EUSMXT().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="1" <%if (userDetail.getE02EUSMXT().equals("1")) {out.print("selected"); }%>>DAILY MAXIMUN AMOUNT PERMITTED</OPTION>
             <OPTION VALUE="2" <%if (userDetail.getE02EUSMXT().equals("2")) {out.print("selected"); }%>>WEEKLY MAXMIMUN AMOUNT PERMITTED</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Operational Password Required :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT DISABLED  NAME="E02EUSOPR" class="<%= userDetail.getF02EUSOPR().equals("Y")?"textchanged":"" %>">
                                                        <OPTION VALUE="0" <%if (userDetail.getE02EUSOPR().equals("0")) {out.print("selected"); }%>>NO</OPTION>
             					 						<OPTION VALUE="1" <%if (userDetail.getE02EUSOPR().equals("1")) {out.print("selected"); }%>>YES</OPTION>
             				 							 </SELECT>             			               			
	 			</div>				 
            </td>
          </tr>  
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Operational Password :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="PASSWORD" name="E02EUSOPP" class="<%= userDetail.getF02EUSOPP().equals("Y")?"textchanged":"" %>" size="15" maxlength="10" value="<%= userDetail.getE02EUSOPP() %>"></div></td>
          </tr>                  
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Person Responsible :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="text" name="E02EUSCON" class="<%= userDetail.getF02EUSCON().equals("Y")?"textchanged":"" %>" size="35" maxlength="30" value="<%= userDetail.getE02EUSCON().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">E-Mail Address :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="text" ID="MANDATORY" name="E02EUSIAD" class="<%= userDetail.getF02EUSIAD().equals("Y")?"textchanged":"" %>" size="50" maxlength="40" value="<%= userDetail.getE02EUSIAD().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Confirmation Type :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSCFM" class="<%= userDetail.getF02EUSCFM().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="1" <%if (userDetail.getE02EUSCFM().equals("1")) {out.print("selected"); }%>>NO CONFIRMATION REQUIRED</OPTION>
             <OPTION VALUE="2" <%if (userDetail.getE02EUSCFM().equals("2")) {out.print("selected"); }%>>CALL-BACK REQUIRED</OPTION>
             <OPTION VALUE="3" <%if (userDetail.getE02EUSCFM().equals("3")) {out.print("selected"); }%>>INTERNET ADVICE TO CUSTOMER</OPTION>
             <OPTION VALUE="4" <%if (userDetail.getE02EUSCFM().equals("4")) {out.print("selected"); }%>>E-MAIL ADVICE TO CUSTOMER</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Include/Exclude Accounts :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSIEF" class="<%= userDetail.getF02EUSIEF().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE=" " <%if (userDetail.getE02EUSIEF().equals(""))  {out.print("selected"); }%>>INCLUDE ALL</OPTION>
             <OPTION VALUE="I" <%if (userDetail.getE02EUSIEF().equals("I")) {out.print("selected"); }%>>INCLUDE NEXT ACCOUNTS</OPTION>
             <OPTION VALUE="E" <%if (userDetail.getE02EUSIEF().equals("E")) {out.print("selected"); }%>>EXCLUDE NEXT ACCOUNTS</OPTION>
             </SELECT></div></td>
          </tr>
          <TR>
           <TD colspan="2" align="CENTER">
             <TABLE width="80%">
               <TR align="CENTER">
                  <TD><input readonly  type="text" name="E02EUSA01" size="25" maxlength="9" value="<%= userDetail.getE02EUSA01().trim()%>" class="<%= userDetail.getF02EUSA01().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA02" size="25" maxlength="9" value="<%= userDetail.getE02EUSA02().trim()%>" class="<%= userDetail.getF02EUSA02().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA03" size="25" maxlength="9" value="<%= userDetail.getE02EUSA03().trim()%>" class="<%= userDetail.getF02EUSA03().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA04" size="25" maxlength="9" value="<%= userDetail.getE02EUSA04().trim()%>" class="<%= userDetail.getF02EUSA04().equals("Y")?"textchanged":"" %>"></TD>
               </TR>
               <TR align="CENTER">
                  <TD><input readonly  type="text" name="E02EUSA05" size="25" maxlength="9" value="<%= userDetail.getE02EUSA05().trim()%>" class="<%= userDetail.getF02EUSA05().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA06" size="25" maxlength="9" value="<%= userDetail.getE02EUSA06().trim()%>" class="<%= userDetail.getF02EUSA06().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA07" size="25" maxlength="9" value="<%= userDetail.getE02EUSA07().trim()%>" class="<%= userDetail.getF02EUSA07().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA08" size="25" maxlength="9" value="<%= userDetail.getE02EUSA08().trim()%>" class="<%= userDetail.getF02EUSA08().equals("Y")?"textchanged":"" %>"></TD>
               </TR>
               <TR align="CENTER">
                  <TD><input readonly  type="text" name="E02EUSA09" size="25" maxlength="9" value="<%= userDetail.getE02EUSA09().trim()%>"  class="<%= userDetail.getF02EUSA09().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA10" size="25" maxlength="9" value="<%= userDetail.getE02EUSA10().trim()%>"  class="<%= userDetail.getF02EUSA10().equals("Y")?"textchanged":"" %>"></TD>
                  <TD></TD>
                  <TD></TD>
               </TR>
             </TABLE>
           </TD>
          </TR>
        </table>
        </td>
      </tr>
    </table>
  <table border="0" width="95%">
    <tr align="center">
       <td><INPUT id="EIBSBTN" type=BUTTON name="Submit" value="Accept" onclick="JavaScript:window.close()"></td>
    </tr>
  </table>
</form>
</body>
</html>

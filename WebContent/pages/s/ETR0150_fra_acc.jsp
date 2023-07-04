<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Forward Rate Agreements</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="fra" class="datapro.eibs.beans.ETR0150DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
    builtNewMenu(fra_app_opt);
 </SCRIPT>


</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> 
<h3 align="center"> Forward Rates Agreements - Maintenance</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSETR0150" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <table class="tableinfo" width="315">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"><b>Counterparty :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="hidden" name="D01FRACP1"  value="<%= fra.getD01FRACP1()%>" readonly>
                <%= fra.getD01FRACP1()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"><b>Location :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="hidden" name="D01FRACP2"  value="<%= fra.getD01FRACP2()%>" readonly>
                <%= fra.getD01FRACP2()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap >&nbsp;</td>
            <td nowrap colspan="3" > 
              <input type="hidden" name="D01FRACP3"  value="<%= fra.getD01FRACP3()%>" readonly>
              <%= fra.getD01FRACP3()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"><b>Account Number :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="hidden" name="E01FRAACC"  value="<%= fra.getE01FRAACC()%>" readonly>
              <%= fra.getE01FRAACC()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Basic Information</h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Contract Type :</div>
            </td>
            <td nowrap ><%= fra.getE01FRAITP().trim()%></td>
            <td nowrap > 
              <div align="right">Action Taken :</div>
            </td>
            <td nowrap colspan="2">
              <% if(fra.getE01FRASBT().equals("PU")) out.print("Purchase");
						else out.print("Sale");%>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Currency :</div>
            </td>
            <td nowrap ><%= fra.getE01FRACCY().trim()%></td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Notional Amount :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fra.getE01FRAOAM())%> </td>
            <td nowrap > 
              <div align="right">Contract Rate : </div>
            </td>
            <td nowrap colspan="2"><%= fra.getE01FRARTE().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Trade Date :</div>
            </td>
            <td nowrap ><%= Util.formatDate(fra.getE01FRADD1(),fra.getE01FRADD2(),fra.getE01FRADD3())%> 
            </td>
            <td nowrap align="right">Value Date :</td>
            <td nowrap colspan="2"><%= Util.formatDate(fra.getE01FRAVD1(),fra.getE01FRAVD2(),fra.getE01FRAVD3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"> </div>
            </td>
            <td nowrap >&nbsp; </td>
            <td nowrap > 
              <div align="right">Maturity Date :</div>
            </td>
            <td nowrap colspan="2"> <%= Util.formatDate(fra.getE01FRAMA1(),fra.getE01FRAMA2(),fra.getE01FRAMA3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Notes :</div>
            </td>
            <td nowrap colspan="4" ><%= fra.getE01FRAOT1().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="4" ><%= fra.getE01FRAOT2().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Dealer :</div>
            </td>
            <td nowrap colspan="4" ><%= fra.getH01USERID().trim()%> - <%= fra.getD01USRDSC()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Additional Information</h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Product :</div>
            </td>
            <td nowrap > <%= fra.getE01FRAPRO()%> - <%= fra.getD01APCDSC()%> 
            </td>
            <td nowrap >&nbsp;</td>
            <td nowrap colspan="2">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Payment Via :</div>
            </td>
            <td nowrap > <% if(fra.getE01FRAPVI().equals("F")) out.print("Payment Via Fed");
				 else if(fra.getE01FRAPVI().equals("1")) out.print("Payment Via Swift MT-100");
				 else if(fra.getE01FRAPVI().equals("2")) out.print("Payment Via Swift MT - 200");
				 else if(fra.getE01FRAPVI().equals("3")) out.print("Payment Via Swift MT- 202");
				 else if(fra.getE01FRAPVI().equals("T")) out.print("Payment Via Telex");
				 else out.print("None");%> </td>
            <td nowrap > 
              <div align="right">Confirmation Type :</div>
            </td>
            <td nowrap colspan="2"> <% if(fra.getE01FRACFT().equals("P")) out.print("Print Notification");
				 else if(fra.getE01FRACFT().equals("S")) out.print("Send Notification Via Swift");
				 else if(fra.getE01FRACFT().equals("H")) out.print("Send Notification Via Chip");
				 else if(fra.getE01FRACFT().equals("T")) out.print("Send Notification Via Telex");
				 else if(fra.getE01FRACFT().equals("F")) out.print("Send Notification Via Fax");
				 else out.print("No Notification");%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4 align="left">Limits</h4>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap  colspan="2"> 
              <h5 align="center">&nbsp;</h5>
            </td>
            <td nowrap width="21%"> 
              <h5 align="center">Limit Amount</h5>
            </td>
            <td nowrap width="23%"> 
              <h5 align="center"><b>Available Limit</b> </h5>
            </td>
            <td nowrap width="34%"> 
              <h5 align="center">Ending Limit Amount</h5>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  colspan="2"> 
              <div align="right">Line of Credit :</div>
            </td>
            <td nowrap width="21%" > 
              <div align="center"><%= fra.getD01LIMAMT()%></div>
            </td>
            <td nowrap width="23%" > 
              <div align="center"><%= fra.getD01LIMAVL()%></div>
            </td>
            <td nowrap width="34%" > 
              <div align="center"><%= fra.getD01LIMEND()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
</form>
</body>
</html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Forward Rate Agreements</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="fra" class="datapro.eibs.beans.ETR0130DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>


</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<h3 align="center"> Forward Rates Agreements <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_fra_basic.jsp,ETR0170"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSETR0130" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
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
                <input type="hidden" name="D01WFRCP1"  value="<%= fra.getD01WFRCP1()%>" readonly>
                <%= fra.getD01WFRCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"><b>Location :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="hidden" name="D01WFRCP2"  value="<%= fra.getD01WFRCP2()%>" readonly>
                <%= fra.getD01WFRCP2()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap >&nbsp;</td>
            <td nowrap colspan="3" > 
              <input type="hidden" name="D01WFRCP3"  value="<%= fra.getD01WFRCP3()%>" readonly>
              <%= fra.getD01WFRCP3()%> </td>
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
            <td nowrap ><%= fra.getE01FRATYP().trim()%></td>
            <td nowrap > 
              <div align="right">Action Taken :</div>
            </td>
            <td nowrap>
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
              <div align="right">Line of Credit :</div>
            </td>
            <td nowrap>
			  <%= fra.getE01WFRLCU().trim()%>
			  &nbsp;/&nbsp;
              <%= fra.getE01WFRCMM().trim()%>			
            
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Notional Amount :</div>
            </td>
            <td nowrap ><%= Util.fcolorCCY(fra.getE01FRAOAM())%> </td>
            <td nowrap > 
              <div align="right">Contract Rate : </div>
            </td>
            <td nowrap><%= fra.getE01FRARTE().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Trade Date :</div>
            </td>
            <td nowrap ><%= Util.formatDate(fra.getE01FRADD1(),fra.getE01FRADD2(),fra.getE01FRADD3())%> 
            </td>
            <td nowrap align="right">Value Date :</td>
            <td nowrap><%= Util.formatDate(fra.getE01FRAVD1(),fra.getE01FRAVD2(),fra.getE01FRAVD3())%> 
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
            <td nowrap> <%= Util.formatDate(fra.getE01FRAMA1(),fra.getE01FRAMA2(),fra.getE01FRAMA3())%> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Notes :</div>
            </td>
            <td nowrap colspan="3" ><%= fra.getE01FRAOT1().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" ><%= fra.getE01FRAOT2().trim()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Dealer :</div>
            </td>
            <td nowrap colspan="3" ><%= fra.getH01USERID().trim()%> - <%= fra.getD01USRDSC()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Product :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><%= fra.getE01FRAPRO()%> - <%= fra.getD01APCDSC()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Payment Via :</div>
            </td>
            <td nowrap >
              <div align="left"><% if(fra.getE01FRAPVI().equals("F")) out.print("Payment Via Fed");
				 else if(fra.getE01FRAPVI().equals("1")) out.print("Payment Via Swift MT-100");
				 else if(fra.getE01FRAPVI().equals("2")) out.print("Payment Via Swift MT - 200");
				 else if(fra.getE01FRAPVI().equals("3")) out.print("Payment Via Swift MT- 202");
				 else if(fra.getE01FRAPVI().equals("T")) out.print("Payment Via Telex");
				 else out.print("None");%></div>
            </td>
            <td nowrap >
              <div align="right">Confirmation Type :</div>
            </td>
            <td nowrap ><% if(fra.getE01FRACFT().equals("P")) out.print("Print Notification");
				 else if(fra.getE01FRACFT().equals("S")) out.print("Send Notification Via Swift");
				 else if(fra.getE01FRACFT().equals("H")) out.print("Send Notification Via Chip");
				 else if(fra.getE01FRACFT().equals("T")) out.print("Send Notification Via Telex");
				 else if(fra.getE01FRACFT().equals("F")) out.print("Send Notification Via Fax");
				 else if(fra.getE01FRACFT().equals("C")) out.print("Send Notification Via Swift CLS");
				 else out.print("No Notification");%></td>
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
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Market Final Price Rate :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FRALRT" size="11" maxlength="9" value="<%= fra.getE01FRALRT()%>" 
			  >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Submit">
        </div>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <BR>
  </form>
</body>
</html>

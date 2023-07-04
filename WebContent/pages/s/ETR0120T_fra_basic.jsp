<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Forward Rate Agreements</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="fra" class="datapro.eibs.beans.ETR0120DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
<h3 align="center"> Forward Rates Agreements - Inquiry<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fra_basic.jsp,ETR0120T"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSETR0120" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <table class="tableinfo" width="313">
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
                <%= fra.getD01WFRCP2()%>  </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap >
              <input type="hidden" name="E01WFRCMM"  value="<%= fra.getE01WFRCMM()%>" readonly>
            </td>
            <td nowrap colspan="3" > 
              
              <input type="hidden" name="D01WFRCP3"  value="<%= fra.getD01WFRCP3()%>" readonly>
              <%= fra.getD01WFRCP3()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0  border="0" width="100%">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Contract Type :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="FIRST" size="2" maxlength="2" value="<%= userPO.getHeader20()%>" 
			  >
              X 
              <input type="text" readonly  name="SEC" size="2" maxlength="2" value="<%= userPO.getHeader21()%>" 
			 >
            </td>
            <td nowrap > 
              <div align="right">
                <input type="hidden" name="E01WFRSBT" value="<%= fra.getE01WFRSBT()%>">
                <input type="hidden" name="E01WFRHEF" value="<%= fra.getE01WFRHEF()%>">
              </div>
            </td>
            <td nowrap >
              <input type="radio" name="CE01WFRSBT" value="PU" onClick="document.forms[0].E01WFRSBT.value='PU'"
			  <%if(fra.getE01WFRSBT().equals("PU")) out.print("checked");%> disabled>
              Purchase 
              <input type="radio" name="CE01WFRSBT" value="SL" onClick="document.forms[0].E01WFRSBT.value='SL'"
			  <%if(fra.getE01WFRSBT().equals("SL")) out.print("checked");%> disabled>
              Sale </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Currency :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01WFRCCY" size="4" maxlength="3" value="<%= fra.getE01WFRCCY().trim()%>" >
            </td>
            <td nowrap > 
              <div align="right"> </div>
            </td>
            <td nowrap >&nbsp; </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Notional Amount :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01WFROAM" size="15" maxlength="13" value="<%= fra.getE01WFROAM()%>" 
			  onKeyPress="enterDecimal()">
            </td>
            <td nowrap > 
              <div align="right">Contract Rate : </div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01WFRRTE" size="10" maxlength="9" value="<%= fra.getE01WFRRTE().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Trade Date :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01WFRDD1" size="3" maxlength="2" value="<%= fra.getE01WFRDD1().trim()%>" 
			  >
              <input type="text" readonly  name="E01WFRDD2" size="3" maxlength="2" value="<%= fra.getE01WFRDD2().trim()%>" 
			  >
              <input type="text" readonly  name="E01WFRDD3" size="3" maxlength="2" value="<%= fra.getE01WFRDD3().trim()%>" 
			  >
            </td>
            <td nowrap > 
              <div align="right">Value Date :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01WFRVD1" size="3" maxlength="2" value="<%= fra.getE01WFRVD1().trim()%>" 
			  >
              <input type="text" readonly  name="E01WFRVD2" size="3" maxlength="2" value="<%= fra.getE01WFRVD2().trim()%>" 
			  >
              <input type="text" readonly  name="E01WFRVD3" size="3" maxlength="2" value="<%= fra.getE01WFRVD3().trim()%>" 
			  >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap >&nbsp;</td>
            <td nowrap > 
              <div align="right">Maturity Date : </div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E01WFRMA1" size="3" maxlength="2" value="<%= fra.getE01WFRMA1().trim()%>" 
			  >
              <input type="text" readonly  name="E01WFRMA2" size="3" maxlength="2" value="<%= fra.getE01WFRMA2().trim()%>" 
			  >
              <input type="text" readonly  name="E01WFRMA3" size="3" maxlength="2" value="<%= fra.getE01WFRMA3().trim()%>" 
			  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Notes :</div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01WFROT1" size="70" maxlength="60" value="<%= fra.getE01WFROT1().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" > 
              <input type="text" readonly  name="E01WFROT2" size="70" maxlength="60" value="<%= fra.getE01WFROT2().trim()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Dealer :</div>
            </td>
            <td nowrap colspan="3" ><%= fra.getH01USERID().trim()%> - <%= fra.getD01USRDSC()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <br><table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%" >
          <tr id="trdark"> 
            <td nowrap  colspan="2"> 
              <div align="center"><b></b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Limit Amount </b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Available Limit </b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Ending Limit Amount </b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap  colspan="2"> 
              <div align="right"><b>Line of Credit</b></div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fra.getD01LIMAMT())%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fra.getD01LIMAVL())%></div>
            </td>
            <td nowrap > 
              <div align="center"><%= Util.fcolorCCY(fra.getD01LIMEND())%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  </form>
</body>
</html>

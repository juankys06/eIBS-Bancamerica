<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Treasury Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="deal" class="datapro.eibs.beans.EDL0120DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

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
<h3 align="center">Dealer Slip - Securities</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120" >
<%
String ogen = "";
if (deal.getE01DLSSTS().equals("T")) {
	ogen = "Tesorería";
} else if (deal.getE01DLSSTS().equals("F")) {
	ogen = "Fideicomiso";
}  else if (deal.getE01DLSSTS().equals("E")) {
	ogen = "FEM";
}  else if (deal.getE01DLSSTS().equals("R")) {
	ogen = "Terceros";
}
%>
  <table class="tableinfo" width="100%" >
    <tr id="trclear"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Counterparty :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="hidden" name="D01DLSCP1"  value="<%= deal.getD01DLSCP1()%>" >
                <%= deal.getD01DLSCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Location :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="D01DLSCP2"  value="<%= deal.getD01DLSCP2()%>" >
              <%= deal.getD01DLSCP2()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right">
                <input type=HIDDEN name="SCREEN" value="6">
              </div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="D01DLSCP3"  value="<%= deal.getD01DLSCP3()%>" >
              <%= deal.getD01DLSCP3()%> </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="E01DLSSTS"  value="<%= deal.getE01DLSSTS()%>" >
              <%= ogen%> </td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo" width="736">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap colspan="5" > 
              <div align="right">Date :<%= Util.formatDate(deal.getE01DLSDD1(),deal.getE01DLSDD2(),deal.getE01DLSDD3())%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Broker :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLSBRK" size="4" maxlength="3" value="<%= deal.getE01DLSBRK()%>">
              <input type="text" name="D01FEBNM1" size="15" maxlength="15" value="<%= deal.getD01FEBNM1()%>">
              <a href="javascript:GetBrokerT('E01DLSBRK','D01FEBNM1')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap width="18%" > 
              <div align="right"> 
                <input type="hidden" name="E01DLSSBT" value="<%= deal.getE01DLSSBT()%>">
              </div>
            </td>
            <td nowrap width="20%" > 
              <input type="radio" name="CE01DLSSBT" value="PU" onClick="document.forms[0].E01DLSSBT.value='PU'"
			  <%if(deal.getE01DLSSBT().equals("PU")) out.print("checked");%>>
              Buy</td>
            <td nowrap rowspan="2" width="18%" > 
              <div align="center"> </div>
              <div align="center"> 
                <h5>Deal Number<br>
                  <input type="text" name="E01DLSHEM" size="12" maxlength="9" onKeyPress="enterInteger()" value="<%= deal.getE01DLSHEM() %>">
                  <a href="javascript:GetAccount('E01DLSHEM','','IN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></h5>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="23%" >&nbsp; </td>
            <td nowrap width="18%" >&nbsp;</td>
            <td nowrap width="20%"> 
              <input type="radio" name="CE01DLSSBT" value="AD" onClick="document.forms[0].E01DLSSBT.value='AD'"
			  <%if(deal.getE01DLSSBT().equals("AD")) out.print("checked");%>>
              Sell</td>
          </tr>
           <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Currency :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLSCCY" size="4" maxlength="3" value="<%= deal.getE01DLSCCY().trim()%>" >
              <a href="javascript:GetCurrency('E01DLSCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap align="right" width="18%">
            	<div align="right">Trade Date :</div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" name="E01DLSDD1" size="3" maxlength="2" value="<%= deal.getE01DLSDD1().trim()%>" 
			  >
              <input type="text" name="E01DLSDD2" size="3" maxlength="2" value="<%= deal.getE01DLSDD2().trim()%>" 
			  >
              <input type="text" name="E01DLSDD3" size="3" maxlength="2" value="<%= deal.getE01DLSDD3().trim()%>" 
			  >
              <a href="javascript:DatePicker(document.forms[0].E01DLSDD1,document.forms[0].E01DLSDD2,document.forms[0].E01DLSDD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>            
            <td nowrap width="18%"> 
              <input type="hidden" name="E01DLSCMM"  value="<%= deal.getE01DLSCMM()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Par Value : </div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLSAMN" size="15" maxlength="13" value="<%= deal.getE01DLSAMN()%>" 
			  onKeyPress="enterDecimal()">
            </td>
            <td nowrap align="right" width="18%"> 
              <div align="right">Settlement Date :</div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" name="E01DLSVD1" size="3" maxlength="2" value="<%= deal.getE01DLSVD1().trim()%>" 
			  >
              <input type="text" name="E01DLSVD2" size="3" maxlength="2" value="<%= deal.getE01DLSVD2().trim()%>" 
			  >
              <input type="text" name="E01DLSVD3" size="3" maxlength="2" value="<%= deal.getE01DLSVD3().trim()%>" 
			  >
              <a href="javascript:DatePicker(document.forms[0].E01DLSVD1,document.forms[0].E01DLSVD2,document.forms[0].E01DLSVD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
            </td> 
            <td nowrap width="18%">&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Price :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLSAM1" size="15" maxlength="13" value="<%= deal.getE01DLSAM1()%>" 
			  onKeyPress="enterDecimal()">
            </td>
            <td nowrap align="right" width="18%"> 
              <div align="right">Payment Via :</div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" name="E01DLSRA2" size="11" maxlength="11" value="<%= deal.getE01DLSRA2().trim()%>" >
            </td>
            <td nowrap width="18%">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">CUSIP :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLSTHR" size="15" maxlength="15" value="<%= deal.getE01DLSTHR()%>" >
            </td>
            <td nowrap width="21%" > 
              <div align="right">ISIN :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLSTHR" size="15" maxlength="15" value="<%= deal.getE01DLSTHR()%>" >
            </td>
            <td nowrap width="18%">&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Instrument Description :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLSTHR" size="15" maxlength="15" value="<%= deal.getE01DLSTHR()%>" >
            </td>
            <td nowrap align="right" width="18%">&nbsp;</td>
            <td nowrap width="20%">&nbsp;</td> 
            <td nowrap width="18%">&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Notes :</div>
            </td>
            <td nowrap colspan="4" > 
              <input type="text" name="E01DLSOT1" size="70" maxlength="60" value="<%= deal.getE01DLSOT1().trim()%>" 
			  >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="4" > 
              <input type="text" name="E01DLSOT2" size="70" maxlength="60" value="<%= deal.getE01DLSOT2().trim()%>" 
			  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Dealer :</div>
            </td>
            <td nowrap colspan="4" ><%= deal.getE01DLSDID().trim()%> - <%= deal.getD01USRDSC().trim()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table width="100%">
          <tr id="trdark"> 
            <td nowrap>&nbsp;</td>
            <td nowrap  colspan="2"> 
              <div align="center"><b>Limit Amount </b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Available Limit</b></div>
            </td>
            <td nowrap > 
              <div align="center"><b>Ending Limit Amount </b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap>Credit Lines</td>
            <td nowrap  colspan="2"> 
              <div align="right"><%= Util.fcolorCCY(deal.getD01LIMAMT())%>:</div>
            </td>
            <td nowrap > 
              <div align="right"><%= Util.fcolorCCY(deal.getD01LIMAVL())%></div>
            </td>
            <td nowrap > 
              <div align="right"><b><%= Util.fcolorCCY(deal.getD01LIMEND())%></b></div>
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
          <input type="checkbox" name="H01FLGWK1" value="1" >
          Accept with Warnings </div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
       <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Submit">
  </div>
      </td>
    </tr>
  </table>
  
  </form>
</body>
</html>

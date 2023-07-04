<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page  import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Account Summary For Customer Number
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "listRep" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "totalsRep" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"   scope="session" />


</HEAD>


<BODY>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>



<FORM>
  <%
	if ( listRep.getNoResult() ) {
%> 
  <table class="tbenter" width=100% height=100%>
    <tr> 
      <td> 
        <h3>No hay resultados para su criterio de búsqueda</h3>
      </td>
    </tr>
  </table>
  <%
	}
	else {
%>
  <h3 align="center">Resumen de Cuentas por Número de Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="rp_sum_for_cust.jsp, ECIF0100P" width="35" height="35"></h3>
  <hr size="4">
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="24%" > 
              <div align="right"><b>Reporte preparado para : </b></div>
            </td>
            <td nowrap width="76%" > 
              <div align="left"> <%= userPO.getHeader1().trim()%> - <%= userPO.getHeader2().trim()%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table  class="tableinfo" width="100%">
    <tr>
      <td>
        <table width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap colspan="12"> 
              <div align="left">Requested Customer Number : <%= userPO.getHeader3().trim()%></div>
            </th>
          </tr>
          <tr id="trdark"> 
            <th align=CENTER nowrap width="3%">Loan</th>
            <th align=CENTER nowrap width="6%">Account</th>
            <th align=CENTER nowrap width="4%"> 
              <div align="center">Term</div>
            </th>
            <th align=CENTER nowrap width="7%"> 
              <div align="center">Short Name</div>
            </th>
            <th align=CENTER nowrap width="10%">Current Balance <br>
              (Native Currency)</th>
            <th align=CENTER nowrap width="6%">Conv. Rate</th>
            <th align=CENTER nowrap width="10%">Current Balance <br>
              (Base Currency)</th>
            <th align=CENTER nowrap width="12%">Int. Rate</th>
            <th align=CENTER nowrap width="10%">Maturity <br>
              Date </th>
            <th align=CENTER nowrap width="10%"> Date Account <br>
              Created </th>
            <th align=CENTER nowrap width="9%">Date Client <br>
              Established</th>
            <th align=CENTER nowrap width="13%">Product <br>
               Code </th>
          </tr>
          <%
                listRep.initRow();
		  String prevFlag = "0";
		  int i = 0;
                while (listRep.getNextRow()) {
                    if (listRep.getFlag().equals(prevFlag)) {
                    		out.println(listRep.getRecord());
                    }
			else {
				prevFlag = listRep.getFlag();
				totalsRep.setCurrentRow(i);
				i++;
              %> 
          <tr id="trdark"> 
            <td colspan="3"><b>Total for Loan : <%= totalsRep.getRecord(3)%></b></td>
            <td colspan="2" > 
              <div align="right"><b>Count : <%= totalsRep.getRecord(2) %></b></div>
            </td>
            <td colspan="2"> 
              <div align="right"><b>$ <%= Util.fcolorCCY(totalsRep.getRecord(1)) %></b></div>
            </td>
            <td colspan="5">&nbsp; </td>
          </tr>
          <%
                    		out.println(listRep.getRecord());
			}
                }
		  totalsRep.setCurrentRow(i);
                if (totalsRep.getRecord(0).equals("*")) {
              %> 
          <tr id="trdark"> 
            <td colspan="3"><b>Total for Loan : <%= totalsRep.getRecord(3) %></b></td>
            <td colspan="2"> 
              <div align="right"><b>Count : <%= totalsRep.getRecord(2) %></b></div>
            </td>
            <td colspan="2"> 
              <div align="right"><b>$ <%= Util.fcolorCCY(totalsRep.getRecord(1)) %></b></div>
            </td>
            <td colspan="5">&nbsp;</td>
          </tr>
          <%
		  }
	}
              %> 
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <BR>
  <div align="center"> </div>

</FORM>

</BODY>
</HTML>

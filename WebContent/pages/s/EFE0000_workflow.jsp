<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Treasury WorkFlow</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0120DSMessage"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

   

   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }


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



%> 
<h3 align="center"> Treasury WorkFlow </h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B" >
<center>
  <table  class="tableinfo" >
    <tr > 
      <td nowrap> 
          <table cellpadding=2 cellspacing=0 width="100%" border="0">
            <tr id="trclear"> 
              <td nowrap > 
                <div align="center"></div>
              </td>
              <td nowrap > 
                <div align="center"> <b>I. Dealer Slip</b> </div>
              </td>
              <td nowrap > 
                <div align="center"></div>
              </td>
              <td nowrap width="80" > 
                <div align="center"><font color="red">--- </font></div>
              </td>
              <td nowrap >Deleted</td>
            </tr>
          </table>
      </td>
    </tr>
  </table>
    <p><font color="green"><b>|</b></font></p>
    <table  class="tableinfo" >
      <tr > 
        <td nowrap> 
          <table cellpadding=2 cellspacing=0 width="100%" border="0">
            <tr id="trclear"> 
              <td nowrap > 
                <div align="center"></div>
              </td>
              <td nowrap > 
                <div align="center"> <b>II. Back Office</b></div>
              </td>
              <td nowrap > 
                <div align="center"></div>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <p><font color="green"><b>|</b></font></p>
    <table  class="tableinfo" >
      <tr > 
        <td nowrap> 
          <table cellpadding=2 cellspacing=0 width="100%" border="0">
            <tr id="trclear"> 
              <td nowrap rowspan="2" > 
                <div align="center"></div>
              </td>
              <td nowrap rowspan="2" colspan="2" > 
                <div align="center"><font color="red"><b>----------------------</b> 
                  </font></div>
                <font color="red"><b>|</b></font></td>
              <td nowrap rowspan="2" > 
                <div align="center"> <b>III . Approved</b></div>
              </td>
              <td nowrap rowspan="2" > 
                <div align="center"></div>
              </td>
              <td nowrap colspan="2" rowspan="2" > 
                <div align="center"><font color="red"><b>----------------------</b></font></div>
                <div align="right"><font color="red"><b>|</b></font></div>
              </td>
            </tr>
            <tr id="trclear"> </tr>
            <tr id="trclear"> 
              <td nowrap >&nbsp;</td>
              <td nowrap colspan="2" > 
                <div align="left">Rejected</div>
              </td>
              <td nowrap >&nbsp;</td>
              <td nowrap >&nbsp;</td>
              <td nowrap colspan="2" > 
                <div align="right">Deleted</div>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <p><font color="green"><b>|</b></font></p>
    <table  class="tableinfo" >
      <tr > 
        <td nowrap> 
          <table cellpadding=2 cellspacing=0 width="100%" border="0">
            <tr id="trclear"> 
              <td nowrap > 
                <div align="center"></div>
              </td>
              <td nowrap > 
                <div align="center"> <b>IV. Wires</b></div>
              </td>
              <td nowrap > 
                <div align="center"></div>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <p><font color="green"><b>|</b></font></p>
    <table  class="tableinfo" >
      <tr > 
        <td nowrap> 
          <table cellpadding=2 cellspacing=0 width="100%" border="0">
            <tr id="trclear"> 
              <td nowrap > 
                <div align="center"></div>
              </td>
              <td nowrap > 
                <div align="center"> <b>V. Maintenance</b></div>
              </td>
              <td nowrap > 
                <div align="center"></div>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </center>
</form>
</body>
</html>

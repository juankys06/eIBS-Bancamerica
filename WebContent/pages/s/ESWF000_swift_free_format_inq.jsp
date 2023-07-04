<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Swift Free Format Inquiry</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="swff" class="datapro.eibs.beans.ESWF01002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
    builtNewMenu(free_a_opt);
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
<h3 align="center"> SWIFT - Formatos Libres<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="swift_free_format_inq.jsp,ESWF000"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0000" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="2">
  </p>
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="27%" > 
              <div align="right">Identificaci&oacute;n Swift :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="ESW2SWI" size="15" maxlength="15" value="<%= swff.getESW2SWI()%>" readonly>
            </td>
            <td nowrap width="26%" > 
              <div align="right">Identificaci&oacute;n de Usuario :</div>
            </td>
            <td nowrap > 
              <input type="text" name="ESW2USR" size="11" maxlength="11" value="<%= swff.getESW2USR()%>"  readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" > 
              <div align="right">N&uacute;mero de Referencia:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="ESW2REF" size="15" maxlength="15" value="<%= swff.getESW2REF()%>" readonly>
            </td>
            <td nowrap width="26%" > 
              <div align="right">Formato Swift : </div>
            </td>
            <td nowrap >
            <INPUT type="text" name="ESW2FMT" size="15" maxlength="15" value="<%= swff.getESW2FMT()%>" readonly></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4" > 
              <div align="center">Comentarios</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F01" size="80" maxlength="50" value="<%= swff.getESW2F01()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F02" size="80" maxlength="50" value="<%= swff.getESW2F02()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F03" size="80" maxlength="50" value="<%= swff.getESW2F03()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F04" size="80" maxlength="50" value="<%= swff.getESW2F04()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F05" size="80" maxlength="50" value="<%= swff.getESW2F05()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F06" size="80" maxlength="50" value="<%= swff.getESW2F06()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F07" size="80" maxlength="50" value="<%= swff.getESW2F07()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F08" size="80" maxlength="50" value="<%= swff.getESW2F08()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F09" size="80" maxlength="50" value="<%= swff.getESW2F09()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F10" size="80" maxlength="50" value="<%= swff.getESW2F10()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F11" size="80" maxlength="50" value="<%= swff.getESW2F11()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F12" size="80" maxlength="50" value="<%= swff.getESW2F12()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F13" size="80" maxlength="50" value="<%= swff.getESW2F13()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F14" size="80" maxlength="50" value="<%= swff.getESW2F14()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F15" size="80" maxlength="50" value="<%= swff.getESW2F15()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F16" size="80" maxlength="50" value="<%= swff.getESW2F16()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F17" size="80" maxlength="50" value="<%= swff.getESW2F17()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F18" size="80" maxlength="50" value="<%= swff.getESW2F18()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F19" size="80" maxlength="50" value="<%= swff.getESW2F19()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F20" size="80" maxlength="50" value="<%= swff.getESW2F20()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F21" size="80" maxlength="50" value="<%= swff.getESW2F21()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F22" size="80" maxlength="50" value="<%= swff.getESW2F22()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F23" size="80" maxlength="50" value="<%= swff.getESW2F23()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F24" size="80" maxlength="50" value="<%= swff.getESW2F24()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F25" size="80" maxlength="50" value="<%= swff.getESW2F25()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F26" size="80" maxlength="50" value="<%= swff.getESW2F26()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F27" size="80" maxlength="50" value="<%= swff.getESW2F27()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F28" size="80" maxlength="50" value="<%= swff.getESW2F28()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F29" size="80" maxlength="50" value="<%= swff.getESW2F29()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F30" size="80" maxlength="50" value="<%= swff.getESW2F30()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F31" size="80" maxlength="50" value="<%= swff.getESW2F31()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F32" size="80" maxlength="50" value="<%= swff.getESW2F32()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F33" size="80" maxlength="50" value="<%= swff.getESW2F33()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F34" size="80" maxlength="50" value="<%= swff.getESW2F34()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" name="ESW2F35" size="80" maxlength="50" value="<%= swff.getESW2F35()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Templates</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="swff" class="datapro.eibs.beans.ESWF003001Message"  scope="session" />

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
<h3 align="center"> Plantillas Formatos Libres SWIFT <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inq_swift_free_format.jsp,ESWF300"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0300" >

    <input type=HIDDEN name="SCREEN" value="5">
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="27%" > 
              <div align="right">Plantilla :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" readonly  name="ESWFTPN" size="15" maxlength="15" value="<%= swff.getESWFTPN()%>" >
            </td>
            <td nowrap width="26%" > 
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="ESWF00" size="55" maxlength="50" value="<%= swff.getESWF00()%>" >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="27%" > 
              <div align="right">Formato Swift :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="ESWFFMT" size="4" maxlength="3" value="<%= swff.getESWFFMT()%>" readonly>
            </td>
            <td nowrap width="26%" > 
              <div align="right">Identificaci&oacute;n Swift :</div>
            </td>
            <td nowrap >
              <input type="text" name="ESWFSWI" size="13" maxlength="12" value="<%= swff.getESWFSWI()%>" readonly >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4" > 
              <div align="center"><b>Comentarios</b> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF01" size="55" maxlength="50" value="<%= swff.getESWF01()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF02" size="55" maxlength="50" value="<%= swff.getESWF02()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF03" size="55" maxlength="50" value="<%= swff.getESWF03()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF04" size="55" maxlength="50" value="<%= swff.getESWF04()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF05" size="55" maxlength="50" value="<%= swff.getESWF05()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF06" size="55" maxlength="50" value="<%= swff.getESWF06()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF07" size="55" maxlength="50" value="<%= swff.getESWF07()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF08" size="55" maxlength="50" value="<%= swff.getESWF08()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF09" size="55" maxlength="50" value="<%= swff.getESWF09()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF10" size="55" maxlength="50" value="<%= swff.getESWF10()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF11" size="55" maxlength="50" value="<%= swff.getESWF11()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF12" size="55" maxlength="50" value="<%= swff.getESWF12()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF13" size="55" maxlength="50" value="<%= swff.getESWF13()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF14" size="55" maxlength="50" value="<%= swff.getESWF14()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF15" size="55" maxlength="50" value="<%= swff.getESWF15()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF16" size="55" maxlength="50" value="<%= swff.getESWF16()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF17" size="55" maxlength="50" value="<%= swff.getESWF17()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF18" size="55" maxlength="50" value="<%= swff.getESWF18()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF19" size="55" maxlength="50" value="<%= swff.getESWF19()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF20" size="55" maxlength="50" value="<%= swff.getESWF20()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF21" size="55" maxlength="50" value="<%= swff.getESWF21()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF22" size="55" maxlength="50" value="<%= swff.getESWF22()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF23" size="55" maxlength="50" value="<%= swff.getESWF23()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF24" size="55" maxlength="50" value="<%= swff.getESWF24()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF25" size="55" maxlength="50" value="<%= swff.getESWF25()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF26" size="55" maxlength="50" value="<%= swff.getESWF26()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF27" size="55" maxlength="50" value="<%= swff.getESWF27()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF28" size="55" maxlength="50" value="<%= swff.getESWF28()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF29" size="55" maxlength="50" value="<%= swff.getESWF29()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF30" size="55" maxlength="50" value="<%= swff.getESWF30()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF31" size="55" maxlength="50" value="<%= swff.getESWF31()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF32" size="55" maxlength="50" value="<%= swff.getESWF32()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF33" size="55" maxlength="50" value="<%= swff.getESWF33()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap colspan="4" >
              <div align="center">
                <input type="text" readonly  name="ESWF34" size="55" maxlength="50" value="<%= swff.getESWF34()%>" >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="center"> 
                <input type="text" readonly  name="ESWF35" size="55" maxlength="50" value="<%= swff.getESWF35()%>" >
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"></div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=submit name="Submit" value="Copiar">
        </div>
      </td>
    </tr>
  </table>
</form>
</body>
</html>

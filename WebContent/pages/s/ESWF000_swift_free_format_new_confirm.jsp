<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Swift Free Format Confirmation</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Refresh" CONTENT="5;url='<%=request.getContextPath()%>/pages/background.jsp'">

<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="swff" class="datapro.eibs.beans.ESWF00001Message"  scope="session" />

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
<h3 align="center"> SWIFT - Confirmaci&oacute;n Formatos Libres <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="swift_free_format_new_confirm.jsp, ESWF000"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120P" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="14">
  </p>
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="42%" > 
              <div align="right">Identificaci&oacute;n Swift :</div>
            </td>
            <td nowrap width="7%" > <%= swff.getESWFSWI()%> </td>
            <td nowrap width="39%" > 
              <div align="right">Identificaci&oacute;n de Usuario :</div>
            </td>
            <td nowrap width="12%" > <%= swff.getH01USR()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="42%" > 
              <div align="right">N&uacute;mero de Referencia:</div>
            </td>
            <td nowrap width="7%" > <%= swff.getESWFREF()%> </td>
            <td nowrap width="39%" > 
              <div align="right">Referencia Relacionada : </div>
            </td>
            <td nowrap width="12%" > <%= swff.getESWFRLR()%> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="42%" > 
              <div align="right">Formato :</div>
            </td>
            <td nowrap width="7%" > <%= swff.getESWFFMT()%> </td>
            <td nowrap width="39%" > 
              <div align="right">Banco: </div>
            </td>
            <td nowrap width="12%" > <%= swff.getESWFBNK()%> </td>
          </tr>
          <tr id="trclear">
            <td nowrap colspan="4" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap colspan="4" > 
              <div align="center">Comentarios</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap colspan="4" > 
              <div align="left">
              <%= Util.concatBR(new String[] {swff.getESWF01(),swff.getESWF02(),swff.getESWF03(),
              								  swff.getESWF04(),swff.getESWF05(),swff.getESWF06(),
              								  swff.getESWF07(),swff.getESWF08(),swff.getESWF09(),
              								  swff.getESWF10(),swff.getESWF11(),swff.getESWF12(),
              								  swff.getESWF13(),swff.getESWF14(),swff.getESWF15(),
              								  swff.getESWF16(),swff.getESWF17(),swff.getESWF18(),
              								  swff.getESWF19(),swff.getESWF20(),swff.getESWF21(),
              								  swff.getESWF22(),swff.getESWF23(),swff.getESWF24(),
              								  swff.getESWF25(),swff.getESWF26(),swff.getESWF27(),
              								  swff.getESWF28(),swff.getESWF29(),swff.getESWF30(),
              								  swff.getESWF31(),swff.getESWF32(),swff.getESWF33(),
              								  swff.getESWF34(),swff.getESWF35()}) %>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <BR>
  </form>
</body>
</html>

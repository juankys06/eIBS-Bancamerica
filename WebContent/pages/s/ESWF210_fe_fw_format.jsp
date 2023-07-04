<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Back Office</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="swift" class="datapro.eibs.beans.ESWF10003Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
    builtNewMenu(fw_bo_opt);
 </SCRIPT>

</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
 out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> 
<h3 align="center">MT 210 - Notificaci&oacute;n de Recibo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_fw_format.jsp, ESWF210"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M" >
  <table class="tableinfo" width="100%" >
    <tr id="trclear"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> <%= swift.getE03SWFNA1()%> </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > <%= swift.getE03SWFNA2()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" width="85%" > <%= swift.getE03SWFNA3()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n B&aacute;sica </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">N&uacute;mero de Referencia 20 :</div>
            </td>
            <td nowrap ><%= swift.getE03SWFREF()%></td>
            <td nowrap > 
              <div align="right">Referencia Relativa 21 :</div>
            </td>
            <td nowrap ><%= swift.getE03SWFRLR()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right">Monto 32 B :</div>
            </td>
            <td nowrap width="23%" ><%= Util.fcolorCCY(swift.getE03SWFPRI())%> 
              - <%= swift.getE03SWFCCY().trim()%></td>
            <td nowrap width="18%" > 
              <div align="right">N&uacute;mero de Cuenta :</div>
            </td>
            <td nowrap width="20%" ><%= swift.getE03SWFACC()%> </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right">Identificaci&oacute;n Enviador :</div>
            </td>
            <td nowrap width="23%" ><%= swift.getE03SWFSID()%> </td>
            <td nowrap width="18%" > 
              <div align="right">Identificaci&oacute;n Recibidor :</div>
            </td>
            <td nowrap width="20%"><%= swift.getE03SWFRID()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Informaci&oacute;n General </h4>
  <table  class="tableinfo" width="736">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Intermediario 56 :</div>
            </td>
            <td nowrap > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAIO" size="4" maxlength="2" value="<%= swift.getE03SWFAIO()%>" 
			  >
                <input type="text" readonly  name="E03SWFAI1" size="40" maxlength="35" value="<%= swift.getE03SWFAI1()%>" 
			  >
              </div>
            </td>
            <td nowrap > 
              <div align="right">Banco Ordenante 52 :</div>
            </td>
            <td nowrap > 
              <input type="text" readonly  name="E03SWFAD1" size="40" maxlength="35" value="<%= swift.getE03SWFAD1()%>" 
			  >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="23%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAI2" size="40" maxlength="35" value="<%= swift.getE03SWFAI2()%>" 
			  >
              </div>
            </td>
            <td nowrap width="18%" > 
              <div align="right">Fecha Valor 30 V :</div>
            </td>
            <td nowrap width="20%" ><%= Util.formatDate(swift.getE03SWFVD1(),swift.getE03SWFVD2(),swift.getE03SWFVD3())%> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="23%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAI3" size="40" maxlength="35" value="<%= swift.getE03SWFAI3()%>" 
			  >
              </div>
            </td>
            <td nowrap width="18%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="20%" >&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="21%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="23%" > 
              <div align="right"> 
                <input type="text" readonly  name="E03SWFAI4" size="40" maxlength="35" value="<%= swift.getE03SWFAI4()%>" 
			  >
              </div>
            </td>
            <td nowrap width="18%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="20%" >&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
          
  </form>
</body>
</html>

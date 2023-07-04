<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Pagos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">




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
<h3 align="center"> Procesar Archivo de Pago<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="payments_basic.jsp,EPR0015"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0015" >
  <h4>
    <input type="hidden" name="SCREEN" value="800">
  </h4>
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">No. de Acuerdo :</div>
            </td>
            <td nowrap > 
              <input type="text" name="AGREEMENT" size="12" maxlength="9">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Folder : </div>
            </td>
            <td nowrap > 
              <input type="text" name="LIBRARY" size="15" maxlength="12" value="">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Archivo :</div>
            </td>
            <td nowrap >
              <input type="text" name="FILE" size="15" maxlength="12" value="PRCPF">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tasa FX  :</div>
            </td>
            <td nowrap >
              <input type="text" name="E01PRCEXR" size="13" maxlength="11" value="">
            </td>
          </tr>  
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Cuenta Contable de Contrapartida :</div>
            </td>
            <td nowrap >
              <input type="text" name="E01PRCOSA" size="13" maxlength="12" value="">
              <a href="javascript:GetLedger('E01PRCOSA','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
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
          <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
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

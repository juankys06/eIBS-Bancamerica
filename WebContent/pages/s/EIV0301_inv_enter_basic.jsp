<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Investments Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="inv" class="datapro.eibs.beans.EIV030101Message"  scope="session" />

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
<h3 align="center"> Portafolio - Orden de Compra / Venta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_enter_basic.jsp, EIV0301"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIV0301" >
  <p>
    <input type=HIDDEN name="SCREEN" value="200">
  </p>
  <p>&nbsp; </p>
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="11%" > 
              <div align="right">Cliente :</div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01IVSCUN"  size = "12" value="<%= inv.getE01IVSCUN()%>"  maxlength="12">
                <a href="javascript:GetCustomerDescId('E01IVSCUN','E01IVSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
                <input type="text" name="E01IVSNA1"  size = "35" value="<%= inv.getE01IVSNA1()%>"  maxlength="35">
              </div>
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="11%" > 
              <div align="right">Clase de Documento :</div>
            </td>
            <td nowrap width="29%" > 
              
              <input type="hidden" name="E01IVSCLS" value="<%= inv.getE01IVSCLS()%>">
              <input type="radio" name="CE01IVSCLS" value="F" onClick="document.forms[0].E01IVSCLS.value='F'"
			  <%if(inv.getE01IVSTRN().equals("F")) out.print("checked");%> >
              Fijo 
              <input type="radio" name="CE01IVSCLS" value="V" onClick="document.forms[0].E01IVSCLS.value='V'"
			  <%if(inv.getE01IVSTRN().equals("V")) out.print("checked");%> >
              Variable 
              <input type="radio" name="CE01IVSCLS" value="C" onClick="document.forms[0].E01IVSCLS.value='C'"
			  <%if(inv.getE01IVSTRN().equals("C")) out.print("checked");%> >
              Combinado</td>
            <td nowrap width="20%" > 
              <div align="right">Tipo de Operaci&oacute;n :</div>
            </td>
            <td nowrap width="40%" > 
              <div align="left"> 
                <input type="hidden" name="E01IVSTRN" value="<%= inv.getE01IVSTRN()%>">
                <input type="radio" name="CE01IVSTRN" value="1" onClick="document.forms[0].E01IVSTRN.value='1'"
			  <%if(inv.getE01IVSTRN().equals("1")) out.print("checked");%> >
                Compra 
                <input type="radio" name="CE01IVSTRN" value="2" onClick="document.forms[0].E01IVSTRN.value='2'"
			  <%if(inv.getE01IVSTRN().equals("2")) out.print("checked");%> >
                Venta</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="11%" > 
              <div align="right">Broker :</div>
            </td>
            <td nowrap width="29%" > 
              <input type="text" name="E01IVSBRK" size="4" maxlength="3" value="<%= inv.getE01IVSBRK()%>" >
              <input type="text" name="E01IVSBRD" size="15" maxlength="15" value="<%= inv.getE01IVSBRD()%>" >
              <a href="javascript:GetBrokerT('E01IVSBRK','E01IVSBRD')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
            <td nowrap width="20%" > 
              <div align="right">N&uacute;mero Portafolio :</div>
            </td>
            <td nowrap width="40%" > 
              <input type="text" name="E01IVSPRP" size="12" maxlength="12" value="<%= inv.getE01IVSPRP()%>" >
              <a href="javascript:GetPortfolioNum('E01IVSPRP','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  
  <br><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
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

  </form>
</body>
</html>

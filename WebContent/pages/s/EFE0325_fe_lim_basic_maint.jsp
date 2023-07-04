<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Customer Settlement Limits</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0325DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

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
<h3 align="center"> L&iacute;mites Establecidos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_lim_basic.jsp,EFE0325"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0325" >
  <input type=HIDDEN name="SCREEN" value="4">
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="hidden" name="E01FEOCUN"  value="<%= fex.getE01FEOCUN()%>" >
                <%= fex.getE01FEOCUN()%>- 
                <input type="hidden" name="D01FEOCP1"  value="<%= fex.getD01FEOCP1()%>" >
                <%= fex.getD01FEOCP1()%> </div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="D01FEOCP2"  value="<%= fex.getD01FEOCP2()%>" >
              <%= fex.getD01FEOCP2()%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="D01FEOCP3"  value="<%= fex.getD01FEOCP3()%>" >
              <%= fex.getD01FEOCP3()%></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">L&iacute;mite de Moneda :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FEOCCY" size="4" maxlength="3" value="<%= fex.getE01FEOCCY().trim()%>" >
              <a href="javascript:GetCurrency('E01FEOCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
              <input type="text" name="E01FEOLIM" size="15" maxlength="13" value="<%= fex.getE01FEOLIM()%>" 
			  onKeyPress="enterDecimal()">
            </td>
            <td nowrap > 
              <div align="right">Monto Utilizado :</div>
            </td>
            <td nowrap colspan="2" > 
              <input type="text" name="E01FEOUTI" size="11" maxlength="11" value="<%= fex.getE01FEOUTI()%>" 
			  readonly >
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Fecha Efectiva :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FEODT1" size="3" maxlength="2" value="<%= fex.getE01FEODT1().trim()%>" 
			  readonly >
              <input type="text" name="E01FEODT2" size="3" maxlength="2" value="<%= fex.getE01FEODT2().trim()%>" 
			  readonly >
              <input type="text" name="E01FEODT3" size="3" maxlength="2" value="<%= fex.getE01FEODT3().trim()%>" 
			   readonly >
            </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2">&nbsp; </td>
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
          <input type="checkbox" name="H01FLGWK1" value="1" <% if(fex.getH01FLGWK1().equals("1")){ out.print("checked");} %>>
          Aceptar con Errores</div>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
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

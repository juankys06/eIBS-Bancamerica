<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Foreign Exchange Module</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="fex" class="datapro.eibs.beans.EFE0120DSMessage"  scope="session" />

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
<h3 align="center"> Dep&oacute;sitos <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_deposits.jsp,EFE0120P"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120P" >
<%
String ogen = "";
if (fex.getE01FESTIN().equals("T")) {
	ogen = "Tesorería";
} else if (fex.getE01FESTIN().equals("F")) {
	ogen = "Fideicomiso";
}  else if (fex.getE01FESTIN().equals("E")) {
	ogen = "FEM";
}  else if (fex.getE01FESTIN().equals("R")) {
	ogen = "Terceros";
}
%>
  <table class="tableinfo" width="100%" >
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"><b>Contraparte :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" > 
              <div align="left"> 
                <input type="hidden" name="D01FESCP1"  value="<%= fex.getD01FESCP1()%>" readonly>
                <%= fex.getD01FESCP1()%> Datapro, Inc.</div>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Localizaci&oacute;n :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="D01FESCP2"  value="<%= fex.getD01FESCP2()%>" readonly>
              <%= fex.getD01FESCP2()%>1300 Brickell Bay Dr</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="3" width="85%" ><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font face="Arial"><font size="2"> 
              </font></font></font></font></font><font size="2">
              <input type="hidden" name="D01FESCP3"  value="<%= fex.getD01FESCP3()%>" readonly>
              <%= fex.getD01FESCP3()%> Miami, Fl, 33131</font></font></font></font></font></td>
          </tr>
          <tr id="trclear">
            <td nowrap width="15%" >
              <div align="right"><b>Orden Generada :</b></div>
            </td>
            <td nowrap colspan="3" width="85%" >
              <input type="hidden" name="E01FESTIN"  value="<%= fex.getE01FESTIN()%>" readonly>
              <%= ogen%></td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p><table  class="tableinfo" width="545">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > </td>
            <td nowrap >&nbsp;</td>
            <td nowrap >
              <div align="right">Fecha :<%= Util.formatDate(fex.getE01FESDD1(),fex.getE01FESDD2(),fex.getE01FESDD3())%>04/02/02</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Agente de Bolsa :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FESBRK" size="4" maxlength="3" value="<%= fex.getE01FESBRK()%>">
              <input type="text" name="D01FEBNM1" size="15" maxlength="15" value="<%= fex.getD01FEBNM1()%>">
              <a href="javascript:GetBrokerT('E01FESBRK','D01FEBNM1')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
            </td>
                        <td nowrap align="right"> 
              <input type=HIDDEN name="SCREEN" value="2">
            <INPUT type="radio" name="CE01FESSBT" value="PU" onclick="document.forms[0].E01FESSBT.value='PU'"></td>
                        <td nowrap > 
              <input type="hidden" name="E01FESSBT" value="<%= fex.getE01FESSBT()%>">
              Fecha Inversi&oacute;n Nocturna</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"> Moneda :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01FESCCY" size="4" maxlength="3" value="<%= fex.getE01FESCCY().trim()%>" >
              <a href="javascript:GetCurrency('E01FESCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
              <input type="text" name="E01FESAMN" size="15" maxlength="13" value="<%= fex.getE01FESAMN()%>" 
			  onKeyPress="enterDecimal()">
            </td>
            <td nowrap > 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap>
            <INPUT type="text" name="E01FESVD1" size="3" maxlength="2" value="<%= fex.getE01FESVD1().trim()%>">
              <INPUT type="text" name="E01FESVD2" size="3" maxlength="2" value="<%= fex.getE01FESVD2().trim()%>">
              <INPUT type="text" name="E01FESVD3" size="3" maxlength="2" value="<%= fex.getE01FESVD3().trim()%>"></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tasa :</div>
            </td>
            <td nowrap >
              <input type="text" name="E01FESCCY2" size="11" maxlength="3" value="<%= fex.getE01FESCCY().trim()%>" >
            </td>
            <td nowrap align="right">Fecha de Vencimiento :</td>
            <td nowrap> 
              <input type="text" name="E01FESVD1" size="3" maxlength="2" value="<%= fex.getE01FESVD1().trim()%>" 
			  >
              <input type="text" name="E01FESVD2" size="3" maxlength="2" value="<%= fex.getE01FESVD2().trim()%>" 
			  >
              <input type="text" name="E01FESVD3" size="3" maxlength="2" value="<%= fex.getE01FESVD3().trim()%>" 
			  >
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
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
          <input type=image class="imgfilter" name="Submit" src="<%=request.getContextPath()%>/images/s/bt_submit.gif" onMouseDown="this.className='imgfilterpress' "
onMouseUp="this.className='imgfilter' ">
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

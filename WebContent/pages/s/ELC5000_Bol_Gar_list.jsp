<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "collList" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
function goCollBasic(ref) {
	document.forms[0].E01LCMACC.value = ref;
	document.forms[0].submit();
}
</script>


<body>

<%@ page import = "datapro.eibs.master.Util" %>

<h3 align="center">Lista de Boletas del Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="Bol_Gar_list,ELC5000"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="7">
<!--INPUT TYPE=HIDDEN NAME="CUSNUM" VALUE="<%= userPO.getCusNum() %>"-->
<INPUT TYPE=HIDDEN NAME="E01LCMACC" VALUE="">

<%
	if ( collList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD>
        <div align="center">
        		<font size="3"><b>
        				No hay resultados que correspondan a su criterio de b·squeda
        		</b></font>
        	</div>
      </TD></TR>
   		</TABLE>
  <%
	}
	else {
%>
  <table class="tableinfo">
    <tr >
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark">
            <td nowrap width="10%" >
              <div align="left"><b>Cliente : </b></div>
            </td>
            <td nowrap>
              <div align="left">
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" readonly>
              </div>
            </td>
            <td nowrap >
              <div align="left">
                <input type="text" name="CUSCUN" size="60" value="<%= userPO.getCusName().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

<br>
<br>

  <TABLE id="garTable" class="tableinfo">
    <TR id ="trdark">
      <TH ALIGN=CENTER>Boleta</TH>
      <TH ALIGN=CENTER>Estado</TH>
      <TH ALIGN=CENTER>Moneda</TH>
      <TH ALIGN=CENTER>Monto</TH>
      <TH ALIGN=CENTER>Beneficiario</TH>
      <TH ALIGN=CENTER></TH>
      <TH ALIGN=CENTER>Vencimiento</TH>
      <TH ALIGN=CENTER>Prorroga</TH>
    </TR>
    <%
                collList.initRow();
                while (collList.getNextRow()) {
                    if (collList.getFlag().equals("")) {
                    		out.println(collList.getRecord());
                    }
                }
    %>
  </TABLE>

<%
  }
%>
</FORM>
</BODY>
</HTML>

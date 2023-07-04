<HTML>
<HEAD>
<TITLE>
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "collIns" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function setOptMenu(purpose) {
	if (purpose == "INQUIRY") {
    	builtNewMenu(colla_i_opt);
	} else {
		if (purpose == "APPROVAL_INQ") {
    		builtNewMenu(colla_A_opt);
    	} else {	
    		builtNewMenu(colla_M_opt);
    	}
    }		
    initMenu();
}

</script>

</HEAD>

<body>

 <%@ page import = "datapro.eibs.master.Util" %>


<SCRIPT Language="Javascript">
	setOptMenu("<%= userPO.getPurpose() %>");
</SCRIPT>


<h3 align="center">Detalle de Polizas de Seguro<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="collateral_none_acc_poliza_detail.jsp,ERA0100"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSERA0100" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
<input type=HIDDEN name="BNKNUM" value="<%= userPO.getBank()%>">

<%
	if ( collIns.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center">
        		<font size="3"><b>
        				No hay resultados que correspondan a su criterio de búsqueda 
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
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente : </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="CUSNA1" size="45" readonly maxlength="45" value="<%= userPO.getCusName().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Referencia : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="ACCNUM" size="12" readonly maxlength="12" value="<%= userPO.getIdentifier().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="PROCCY" size="4" readonly maxlength="4" value="<%= userPO.getCurrency().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Tipo de Garant&iacute;a : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="PROCOD" size="4" readonly maxlength="4" value="<%= userPO.getType().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<h4></h4>
  
  <TABLE class="tableinfo">
    <TR id="trdark"> 
        <TH ALIGN=CENTER NOWRAP>Compa&ntilde;&iacute;a</TH>
        <TH ALIGN=CENTER NOWRAP>Nombre</TH>
        <TH ALIGN=CENTER NOWRAP>N&uacute;mero de P&oacute;liza</TH>
        <TH ALIGN=CENTER NOWRAP>Moneda</TH>
        <TH ALIGN=CENTER NOWRAP>Monto</TH>
    </TR>
    <%
                collIns.initRow();
                while (collIns.getNextRow()) {
                    if (collIns.getFlag().equals("")) {
                    		out.println(collIns.getRecord());
                    }
                }
    %> 
  </TABLE>
  <BR>
  
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
<%
        if ( collIns.getShowPrev() ) {
      			int pos = collIns.getFirstRec() - 51;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1&Pos=" + pos + "\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%>
	</TD>
 	<TD WIDTH="50%" ALIGN=RIGHT>
<%        
        if ( collIns.getShowNext() ) {
      			int pos = collIns.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1&Pos=" + pos + "\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
        }
%> 
	</TD>
 	</TR>
 	</TABLE>
<%       
  }
%>

</FORM>

</BODY>
</HTML>

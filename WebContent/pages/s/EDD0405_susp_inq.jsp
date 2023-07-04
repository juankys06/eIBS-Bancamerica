<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Suspensiones de Pago
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "susp" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
<%
if (userPO.getPurpose().equals("INQUIRY")){
%>

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
%>

<%
}
%>
</SCRIPT>

</head>

<body>

<%
   if (userPO.getPurpose().equals("INQUIRY")){ 
   out.println("<SCRIPT> initMenu(); </SCRIPT>");}
%> 


<h3 align="center">Suspensiones de Pagos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="susp_inq.jsp,EDD0405"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0405" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">

<%
	if ( susp.getNoResult() ) {
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
    <tr> 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" >
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%= userPO.getHeader2().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="CUSNA1" size="45" readonly maxlength="45" value="<%= userPO.getHeader3().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%">
              <div align="right"><b>Cuenta :</b></div>
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
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left">
                <input type="text" name="PROCOD" size="4" readonly maxlength="4" value="<%= userPO.getHeader1().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br> 
  
  <TABLE class="tableinfo">
    
     <TR id=trdark> 
      <TH ALIGN=CENTER colspan="2">No de Cheques</TH>
      <TH ALIGN=CENTER colspan="3">Procesado</TH>
      <TH ALIGN=CENTER rowspan="2">Estado</TH>
      <TH ALIGN=CENTER colspan="3">Aclarado</TH>
      <TH ALIGN=CENTER rowspan="2">Monto</TH>
      <TH ALIGN=CENTER rowspan="2">Comentarios</TH>
     </TR>
     <TR id=trdark> 
      <TH ALIGN=CENTER>Desde</TH>
      <TH ALIGN=CENTER>Hasta</TH>
      <TH ALIGN=CENTER>Fecha</TH>
      <TH ALIGN=CENTER>Hora</TH>
      <TH ALIGN=CENTER>Usuario</TH>
      <TH ALIGN=CENTER>Fecha</TH>
      <TH ALIGN=CENTER>Hora</TH>
      <TH ALIGN=CENTER>Usuario</TH>
    </TR>
    <%
                susp.initRow();
                while (susp.getNextRow()) {
                    datapro.eibs.beans.EDD0405DSMessage msgList = (datapro.eibs.beans.EDD0405DSMessage) susp.getRecord();
     %>  
    <TR>
		<TD NOWRAP><%=Util.formatCell(msgList.getE01STPFCK())%></TD>
		<TD NOWRAP><%=Util.formatCell(msgList.getE01STPTCK())%></TD>
		<TD NOWRAP ALIGN=CENTER><%=Util.formatDate(msgList.getE01STPDT1(), msgList.getE01STPDT2(), msgList.getE01STPDT3())%></TD>
		<TD NOWRAP ALIGN=CENTER><%=Util.formatTime(msgList.getE01STPTIM())%></TD>
		<TD NOWRAP><%=Util.formatCell(msgList.getE01STPUSR())%></TD>
		<TD NOWRAP><%=Util.formatCell(msgList.getE01STPSTS())%></TD>
		<TD NOWRAP ALIGN=CENTER><%=Util.formatDate(msgList.getE01STPRD1(), msgList.getE01STPRD2(), msgList.getE01STPRD3())%></TD>
		<TD NOWRAP ALIGN=CENTER><%=Util.formatTime(msgList.getE01STPRTM())%></TD>
		<TD NOWRAP><%=Util.formatCell(msgList.getE01STPRUS())%></TD>
		<TD NOWRAP ALIGN=RIGHT><%=Util.fcolorCCY(msgList.getE01STPAMT())%></TD>
		<TD NOWRAP><%=Util.formatCell(msgList.getE01STPRMK())%></TD>
		
	</TR>            
      <%            
                }
    %> 
  </TABLE>

<%
  }
%>

</FORM>

</BODY>
</HTML>

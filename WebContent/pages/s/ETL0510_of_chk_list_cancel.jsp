<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util"%>
<HTML>
<HEAD>
<TITLE>
Others Documents
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>



<jsp:useBean id= "dvList" class= "datapro.eibs.beans.JBList"  scope="session"/>



<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>


<BODY>


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<FORM>
  <h3 align="center">Cheques de Gerencia - Cancelacion<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="of_chk_list.jsp, ETL0510"> 
  </h3>
  <hr size="4">
<%
	if ( dvList.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
        <div align="center"> <b>No hay resultados para su criterio de busqueda</b> </div>
      </TD></TR>
   		</TABLE>
  <%
	}
	else {
%> 
 
  <BR>
  <TABLE class="tableinfo">
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="10%">Referencia</TH>
      <TH ALIGN=CENTER  nowrap width="8%">Moneda</TH>
      <TH ALIGN=CENTER  nowrap width="7%">Sucursal</TH>
      <TH ALIGN=CENTER  nowrap width="9%">Monto</TH>
      <TH ALIGN=CENTER  nowrap width="5%"> 
        <div align="right">Status</div>
      </TH>
      <TH ALIGN=CENTER  nowrap width="6%"> 
        <div align="center">Emitido</div>
      </TH>
      <TH ALIGN=CENTER  nowrap width="55%"> 
        <div align="center">Beneficiario</div>
      </TH>
    </TR>
    <%
                dvList.initRow();
                while (dvList.getNextRow()) {
                    if (dvList.getFlag().equals("")) {
                    		out.println(dvList.getRecord());
                    }
                }
              %> 
  </TABLE>
  <BR>
  
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>

<%
        if ( dvList.getShowPrev() ) {
      			int pos = dvList.getFirstRec() - 51;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSETL0510?SCREEN=11&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
 %>
 </TD>
 <TD WIDTH="50%" ALIGN=RIGHT>
 <%       
        if ( dvList.getShowNext() ) {
      			int pos = dvList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSETL0510?SCREEN=11&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
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

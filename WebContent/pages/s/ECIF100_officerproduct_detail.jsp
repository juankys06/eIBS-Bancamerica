<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
 
<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Detalle de Productos por Ejecutivo
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "prodList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPOLevel" class= "datapro.eibs.beans.UserPos"  scope="session" />

<BODY>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<%if ( prodList.getNoResult() ) {%>
	<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font> 
      </div>
      </TD></TR>
	</TABLE>
<%}	else {
      String officer="";
	  String product="";
	  String name="";
	  String descri="";
	  String clase="";
      String param="";
      try {
		  officer= request.getParameter("Officer").trim();
		  name= request.getParameter("Name").trim();
		  product= request.getParameter("Product").trim();
		  descri= request.getParameter("Descri").trim();
		  clase= request.getParameter("Clase").trim();
      }
      catch(Exception e){
      }
%>
  <h3 align="center">Detalle de Productos por Ejecutivo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="officerproduct_detail.jsp,ECIF100"></h3> 
  <hr size="4">
  <br>
  <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark">
            <td nowrap width="25%" > 
              <div align="right"><b>Ejecutivo : </b></div>
            </td>
            <td nowrap align="left" width="75%" > 
              <input type="text" name="OFFICER" size="5" maxlength="4" value="<%= officer%>" readonly>
              <input type="text" name="NAME" size="40" maxlength="35" value="<%= name%>" readonly>
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="25%" > 
              <div align="right"><b>Producto: </b></div>
            </td>
            <td nowrap align="left" width="75%" > 
   			  <input type="text" name="PRODUCT" size="5" maxlength="4" value="<%= product%>" readonly>
   			  <input type="text" name="DESCRI" size="40" maxlength="35" value="<%= descri%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <TABLE class="tableinfo">
    <TR id=trdark> 
      <TH ALIGN=CENTER nowrap>Aplicacion</TH>
      <TH ALIGN=CENTER nowrap>Codigo de<BR>Cliente</TH>
      <TH ALIGN=CENTER nowrap>Nombre</TH>
      <TH ALIGN=CENTER nowrap>Cuenta</TH>
      <TH ALIGN=CENTER nowrap>Principal</TH>
      <TH ALIGN=CENTER nowrap>Interes</TH>
	  <TH ALIGN=CENTER nowrap>Mora</TH>
	  <TH ALIGN=CENTER nowrap>Pignoraciones</TH>
	  <TH ALIGN=CENTER nowrap>Grupo</TH>
   </TR>
   <%
	prodList.initRow();
	while (prodList.getNextRow()) {
	    if (prodList.getFlag().equals("")) {
	         out.println(prodList.getRecord());
	    }
	}
   %> 
  </TABLE>
  
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  
  <%
    if ( prodList.getShowPrev() ) {
  			int pos = prodList.getFirstRec() - 21;
  			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSECIF100?SCREEN=1&Pos=" + pos + "&OFFICER=" + officer + "&NAME=" + name + "&PRODUCT=" + product + "&DESCRI=" + descri + "&CLASE=" + clase +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
    }
  %>
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>
 
  <%      
    if ( prodList.getShowNext() ) {
  			int pos = prodList.getLastRec();
  			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSECIF100?SCREEN=1&Pos=" + pos + "&OFFICER=" + officer + "&NAME=" + name + "&PRODUCT=" + product + "&DESCRI=" + descri + "&CLASE=" + clase +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
    }
  %> 
 </TD>
 </TR>
 </TABLE>
<%}%>
</FORM>

 </BODY>
</HTML>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page  import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Operations to be Collaterized
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session" />

<jsp:useBean id= "asList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"   scope="session" />

<BODY>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
	 error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  
%> 

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0005">
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
  <%
	if ( asList.getNoResult() ) {
%> 
  <table class="tbenter" width=100% height=100%>
    <tr> 
      <td> 
        <div align="center"> <font size="3"><b>No hay resultados para su criterio de busqueda</b></font> </div>
      </td>
    </tr>
  </table>
  <%
	}
	else {
%>
  <h3 align="center">Operaciones con Garantias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ga_assets_list_fp,ERA005"></h3>
  <hr size="4">
  <BR>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="30%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="18%" > 
              <div align="left"> <%= userPO.getHeader2().trim()%> </div>
            </td>
            <td nowrap width="11%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap width="41%" > 
              <div align="left"> <%= userPO.getHeader3().trim()%> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <table class="tableinfo">
    <tr id="trdark"> 
      <th align=CENTER width="114" nowrap>Cuenta</th>
      <th align=CENTER width="88"  nowrap>Tipo de Cuenta</th>
      <th align=CENTER width="80"  nowrap>Moneda</th>
      <th align=CENTER width="208" nowrap> <div align="center">Valor de la Operación </div></th>
      <th align=CENTER width="196" nowrap> <div align="center">Monto Garantizado</div></th>
      <th align=CENTER width="237" nowrap> <div align="center">Monto no Garantizado</div></th>
    </tr>
    <%
                asList.initRow();
                while (asList.getNextRow()) {
                    if (asList.getFlag().equals("")) {
                    		out.println(asList.getRecord());
                    }
                }
              %> 
  </table>
  <BR>
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
        if ( asList.getShowPrev() ) {
      			int pos = asList.getFirstRec() - 51;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSERA0005?SCREEN=5&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
   %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <% 
        if ( asList.getShowNext() ) {
      			int pos = asList.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.client.JSERA0005?SCREEN=5&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
        }
  %> 
   </TD>
 	</TR>
 	</TABLE>
<BR>
<p align="center"> 
		   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">         
        </p>

  <%
  }
%> 
</FORM>

</BODY>
</HTML>

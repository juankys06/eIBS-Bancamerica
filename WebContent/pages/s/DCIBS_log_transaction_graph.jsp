<html>

<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "paramters" class= "com.datapro.eibs.internet.databeans.DC_SEARCH"  scope="session" />
<jsp:useBean id= "lstTransactions" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>


<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">DCIBS Transactions Graph<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="DCIBS_log_transaction_list.jsp, DCIBS"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSLogTransSearch" >
  	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
  

  <table class="tableinfo" width="90%" height="75%">
    <tr> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0" height="100%">
          <tr id="tbenter">
            <td nowrap colspan=2> 
              <div align="center">
              	<APPLET name="history" code="VerticalBar3D.class" width="90%" height="90%" align="absmiddle" archive="3DGraphs.zip" codebase="<%=request.getContextPath()%>/pages/s/">
                <PARAM name="title_color" value="7,52,96">                     
                <!-- PARAM name= "show_legend_on_right" -->
                <PARAM name="aspect_fudge" value="1.5">
                <PARAM name="transparency" value=".8">
                <PARAM name="divisions_number" value="5">
                <PARAM name="show_values_on_top_of_bars" >
                <PARAM name="show_description_on_x_axis" >
                <PARAM name="set_legend_off">
                <PARAM name="x_axis_description" value="Months">

                  <% lstTransactions.getFirstRec();
                     com.datapro.eibs.internet.databeans.DC_LOG xlog = new com.datapro.eibs.internet.databeans.DC_LOG();
                     int xi=0;
                     while(lstTransactions.getNextRow()){
                        xlog = (com.datapro.eibs.internet.databeans.DC_LOG) lstTransactions.getRecord();
                        xi++;                        
                        if(xi>12) break;                           
                   %>
                  <param name=description_<%=xi%> value="<%= xlog.getCREDITACC() + "/" + xlog.getDEBITACC()  %>">
                  <param name=val_<%=xi%> value="<%= xlog.getAMOUNT() %>">
<%
                     }
              	%>
                <PARAM name="title" value="Internet Transactions Type : <%= xlog.getTRANSCODE() %>" >
 	                </APPLET>
              	 </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

	<table class="tbenter" width=100% align=center>
		<tr> 
	        <td><div align="center"><input id="EIBSBTN" type=submit name="Return" value="Return"></div></td>
	    </tr>
	</table>
  
</form>
</body>
</html>

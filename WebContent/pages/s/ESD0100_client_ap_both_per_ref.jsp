<html>
<head>
<title>Personal References</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id= "perRef" type= "datapro.eibs.beans.ESD000004Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 


<SCRIPT Language="Javascript">

     <% 
   if ( userPO.getOption().equals("CLIENT_P") ) {
   %>
		builtNewMenu(client_ap_personal_opt);
  <%      
   }
   else
   {
   %>
		builtNewMenu(client_ap_corp_opt);
   <%
   }
   %>

</SCRIPT>

<body bgcolor="#FFFFFF">


<% 
 if ( !error.getERRNUM().equals("0")  ) {
   	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%> 
<h3 align="center">Referencias Personales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_ap_both_per_ref, ESD0100"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="44">
   

<table cclass="tableinfo"">
  <tr > 
    <td nowrap> 
      <table cellspacing="0" cellpadding="2" width="100%" class="tbhead"   align="center">
        <tr>
             <td nowrap width="10%" align="right"> Cliente: 
               </td>
          <td nowrap width="12%" align="left">
      			<%= userPO.getHeader1()%>
          </td>
            <td nowrap width="6%" align="right">ID:  
            </td>
          <td nowrap width="14%" align="left">
      			<%= userPO.getHeader2()%>
          </td>
            <td nowrap width="8%" align="right"> Nombre: 
               </td>
          <td nowrap width="50%"align="left">
      			<%= userPO.getHeader3()%>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<BR>

<jsp:include page="ESD0004_entities_template.jsp" flush="true">
	<jsp:param name="messageName" value="perRef" />
	<jsp:param name="approval" value="true" />
</jsp:include>	
 

  <p><% 
    if ( (userPO.getPurpose().equals("NEW")) || (userPO.getPurpose().equals("MAINTENANCE")) ) {
		out.println("<p>");
		out.println("  <center>");
		out.println("<input class=\"imgfilter\" type=image name=\"Submit\" src=\""+request.getContextPath()+"/images/s/bt_enviar.gif\" onMouseDown=\"this.className=''\" onMouseUp=\" this.className='imgfilter'\" >");
		out.println("  </center>");
		out.println("<p>");
 	 }
 	 %> </p>
</form>
</body>
</html>

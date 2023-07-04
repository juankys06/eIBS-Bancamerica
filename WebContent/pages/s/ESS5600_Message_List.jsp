<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Affiliation Request</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ESS5601List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
<!--

 function goSubmit(Op) {
 	document.forms[0].SCREEN.value = Op;
    document.forms[0].submit();
  }
  
</script>

</HEAD>
<BODY>

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS5600">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="110">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <h3 align="center">Mensajes de Mercadeo a Usuarios de Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="Affiliation_List.jsp,ESS2060"> 
  </h3>
<hr size="4">

 <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER width="33%" class=TDBKG> <a href="javascript:goSubmit('120')">Nuevo</a> </TD>
      <TD ALIGN=CENTER width="33%" class=TDBKG> <a href="javascript:goSubmit('110')">Modificar</a></TD>
      <TD ALIGN=CENTER width="33%" class=TDBKG> <a href="javascript:goSubmit('115')">Eliminar</a></TD>
    </TR>
  </TABLE>

 <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark">  
		      <TH ALIGN=CENTER width=20%>Selección</TH>
		      <TH ALIGN=CENTER width=50%>DescripcióN</TH>
		      <TH ALIGN=CENTER width=10%>Fecha Inicio</TH>
		      <TH ALIGN=CENTER width=10%>Fecha Final</TH>
		  </TR>
       <%
       	  int i = 0;
          ESS5601List.initRow();               
          while (ESS5601List.getNextRow()) {
             datapro.eibs.beans.ESS560001Message objList = (datapro.eibs.beans.ESS560001Message) ESS5601List.getRecord();	
       %>  
          <tr>
             <td align="center">
                <input type="radio" name="IMDUNQ" value ="<%= objList.getIMDUNQ() %>" checked>
             <td><%= objList.getIMDDSC()  %></td>
             <td align="center"><%= objList.getIMDSTDD() + "/" + objList.getIMDSTMM() + "/" + objList.getIMDSTYY() %></td>
             <td align="center"><%= objList.getIMDENDD() + "/" + objList.getIMDENDM() + "/" + objList.getIMDENDY() %></td>             
          </tr>
        <% } %>  
 		</table>
  </table>
</FORM>

</BODY>
</HTML>

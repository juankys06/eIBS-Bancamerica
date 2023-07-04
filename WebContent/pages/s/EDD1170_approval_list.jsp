<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Lista de Cuentas a Aprobar
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
  var reason = '';
  var accOfac = '';
  var accWarn = '';

   function goAction(op) {
   
     document.forms[0].action.value = op;  
     document.forms[0].submit();

 }
  
 
function getParams(convm) {

    document.forms[0].CONVM.value = convm;
   
}
	
</script>

</HEAD>

<BODY>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } else {
   if (userPO.getThereIsMsg()) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showReceipt('"+userPO.getOption()+"')");
     out.println("</SCRIPT>");     
   }
 }
%>
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.misc.JSEDD1170" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="CONVM" VALUE="">
<INPUT TYPE=HIDDEN NAME="action" VALUE="A">
<INPUT TYPE=HIDDEN NAME="reason" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <h3 align="center"> Aprobación de Cierre Masivo de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,EDD1170"> 
  </h3>
<hr size="4">
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="33%"> 
        <div align="center" ><a href="javascript:goAction('A')"><b>Aprobar</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction('D')"><b>Borrar</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Tipo de <br> Producto</div>
            </th>
            <th align=CENTER nowrap width="30%"> 
              <div align="center">Tipo de <br> Convenio</div>
            </th>
            <th align=CENTER nowrap width="10%">Agencia</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Cuenta <br> Contable</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Centro <br> Costo</div>
            </th>

          </tr>
          <%
                appList.initRow();
				int k=1;
                while (appList.getNextRow()) {
                 
                  out.println(appList.getRecord());
                  k++;   
                }
              %>
        </table>
    </table>
     
  <SCRIPT language="JavaScript">

   
  showChecked("ACCNUM");
  
     
</SCRIPT>


</FORM>

</BODY>
</HTML>

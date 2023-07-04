 <%@ page import = "datapro.eibs.master.Util" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "noneCollList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">

function goAction(op) {
 var delok= false;

 if (op == 1) {
 	document.forms[0].SCREEN.value = '300';
    document.forms[0].submit();
 }
 if (op == 2) {
	delok = confirm("Esta seguro que desea borrar la Garantía seleccionada"); 
 	if ( delok) {
		document.forms[0].SCREEN.value = '600';
		document.forms[0].submit()
 	}
 }      

}

function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";

}

function goCollBasic(ref) {
	document.forms[0].REF.value = ref;
	document.forms[0].submit();
}

</script>
</HEAD>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/delete_over.gif','<%=request.getContextPath()%>/images/s/nueva_over.gif')">


<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
<INPUT TYPE=HIDDEN NAME="CUSNUM" VALUE="<%= userPO.getCusNum() %>">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

<h3 align="center"> Lista de Garantías de No-Dep&oacute;sito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ga_acc_list.jsp,ERA0011"> 
</h3>
<hr size="4">
  
  
<TABLE class="tbenter">
  <TR> 
    <TD ALIGN=CENTER> <a href="javascript:goAction('1')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/nueva_over.gif',1)"><img name="Image1" alt="Nuevo" border="0" src="<%=request.getContextPath()%>/images/s/nueva.gif" ></a> 
    </TD>
 <%
	if ( !(noneCollList.getNoResult() )) {
 %>
    <TD ALIGN=CENTER><a href="javascript:goGaBasic()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image11','','<%=request.getContextPath()%>/images/s/maintenance_over.gif',1)"><img name="Image11" alt="Modificar" border="0" src="<%=request.getContextPath()%>/images/s/maintenance.gif" ></a>
    </TD>
    <TD ALIGN=CENTER> <a href="javascript:goAction('2')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image3" alt="Borrar" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a> 
    </TD>
 <% } %>
    <TD ALIGN=CENTER> <a href="javascript:goExit()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" alt="Salir" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a> 
    </TD>
  </TR>
</TABLE>

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
        </table>
      </td>
    </tr>
  </table>
<h4></h4>
<%
	if ( noneCollList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% >
   		<TR>
      <TD> 
        <div align="center">
        		
          <p>&nbsp;</p>
          <p><font size="3"><b>No hay resultados que correspondan a su criterio 
            de búsqueda </b></font> </p>
          </div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>  

  <TABLE class="tableinfo">
    <TR id="trdark"> 
      <TH ALIGN=CENTER width="1%">&nbsp;</TH>
      <TH ALIGN=CENTER width="20%">N&uacute;mero de Referencia</TH>
      <TH ALIGN=CENTER width="5%">Tipo</TH>
      <TH ALIGN=CENTER width="20%">Fecha de Vencimiento</TH>
      <TH ALIGN=CENTER width="5%">MDA</TH>
      <TH ALIGN=CENTER width="13%">Balance Neto</TH>
      <TH ALIGN=CENTER width="18%">Garant&iacute;as Utilizadas</TH>
      <TH ALIGN=CENTER width="18%">Balance Disponible</TH>
    </TR>
    <%
                noneCollList.initRow();
                while (noneCollList.getNextRow()) {
                    if (noneCollList.getFlag().equals("")) {
                    		out.println(noneCollList.getRecord());
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

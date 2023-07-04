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


<jsp:useBean id= "noneCollList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">

function goAction(op) {
 var delok= false;

 if (op == 1 || op == 2) {
 	//document.forms[0].SCREEN.value = '300';
    //document.forms[0].submit();
    if (op == 1)
      page = webapp + "/servlet/datapro.eibs.client.JSERA0010?SCREEN=300";
	else
	  page = webapp + "/servlet/datapro.eibs.client.JSERA0010?SCREEN=500&ROW=" + document.forms[0].ACTROW.value;
	
	CenterWindow(page,600,500,2);
 }
 else {
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

function setRow(actrow) {
	document.forms[0].ACTROW.value = actrow;
}

</script>
</HEAD>

<% 
 boolean firstTime = true;
 String chk = "";
 int row;
 try {
	  	row = Integer.parseInt(request.getParameter("ROW"));
	  	firstTime = false;
	} 
 catch (Exception e) {
		row = 0;
		firstTime = true;		
	}
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY>


<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0010">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
<INPUT TYPE=HIDDEN NAME="CUSNUM" VALUE="<%= userPO.getCusNum() %>">
<INPUT TYPE=HIDDEN NAME="ACTROW" VALUE="<%= row %>">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

<h3 align="center"> Lista de Garantías de No-Dep&oacute;sito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ga_acc_list.jsp,ERA0010"> 
</h3>
<hr size="4">
  
  
<TABLE class="tbenter">
  <TR> 
    <TD class=TDBKG> <a href="javascript:goAction('1')">Nueva</a> 
    </TD>
 <%
	if ( !(noneCollList.getNoResult() )) {
 %>
    <TD class=TDBKG><a href="javascript:goAction(2)">Modificar</a>
    </TD>
    <TD class=TDBKG> <a href="javascript:goAction('3')">Eliminar</a> 
    </TD>
 <% } %>
    <TD class=TDBKG> <a href="javascript:goExit()">Salir</a> 
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
                     if (firstTime) {
						firstTime = false;
						chk = "checked";
				  	 }
				  	 else {
						if (noneCollList.getCurrentRow() == row )
							chk = "checked";
						else 
							chk = "";
				  	}
                    datapro.eibs.beans.ERA010004Message msgList = (datapro.eibs.beans.ERA010004Message) noneCollList.getRecord();
    %>              
    			  <TR>
					<TD NOWRAP><input type="radio" name="REFNUM" value="<%= msgList.getE04ROCREF()%>" <%=chk%> onClick="setRow('<%=noneCollList.getCurrentRow()%>')"></TD>
					<TD NOWRAP ALIGN=LEFT><%=Util.formatCell(msgList.getE04ROCREF())%></TD>
					<TD NOWRAP><%=Util.formatCell(msgList.getE04ROCTYP())%></TD>
					<TD NOWRAP><%=Util.formatDate(msgList.getE04ROCMT1(), msgList.getE04ROCMT2(), msgList.getE04ROCMT3())%></TD>
					<TD NOWRAP><%=Util.formatCell(msgList.getE04ROCCCY())%></TD>
					<TD NOWRAP ALIGN=RIGHT><%=Util.fcolorCCY(msgList.getE04ROCNBL())%></TD>
					<TD NOWRAP ALIGN=RIGHT><%=Util.fcolorCCY(msgList.getE04ROCCOP())%></TD>
					<TD NOWRAP ALIGN=RIGHT><%=Util.fcolorCCY(msgList.getE04DISPON())%></TD>
				   </TR>
      
    <%            }
    %> 
  </TABLE>
  
  <%
  }
 %>


</FORM>

</BODY>
</HTML>

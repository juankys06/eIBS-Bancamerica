<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista Formatos por Cliente</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "EDP073001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="JavaScript">



function goAction(op) {

	if (op == "1") {
//		document.forms[0].E01IFMCUN.value = 0;
		document.forms[0].E01IFMCFO.value = "<%=userPO.getHeader3()%>";
		document.forms[0].TITULO.value = "<%=userPO.getHeader20()%>";
		document.forms[0].E01IFMFEY.value = "<%=userPO.getHeader6()%>";
		document.forms[0].E01IFMFEM.value = "<%=userPO.getHeader7()%>";
		document.forms[0].E01IFMFED.value = "<%=userPO.getHeader8()%>";
		document.forms[0].opt.value = op;
		document.forms[0].action.value = op;
		document.forms[0].submit();
	  
	} 
	if (op == "2") {
	       document.forms[0].opt.value = op;
	       document.forms[0].submit();
	  
	}
	if (op == "3") {
      ok = confirm("Esta seguro que desea eliminar el registro seleccionado?");
	  if (ok) 
	       {
	       document.forms[0].SCREEN.value="200";
	       document.forms[0].opt.value = op;
	       document.forms[0].submit();
	       }  
	}
	if (op == "4") {
	       document.forms[0].opt.value = op;
	       document.forms[0].submit();
	  
	}
}


function getParams(currrow,CUN,CFO,DSC,FEY,FEM,FED) {
	document.forms[0].CURRENTROW.value = currrow;
	document.forms[0].E01IFMCUN.value = CUN;
	document.forms[0].E01IFMCFO.value = CFO;
	document.forms[0].TITULO.value = CFO + "-" + DSC;
	document.forms[0].E01IFMFEY.value = FEY;
	document.forms[0].E01IFMFEM.value = FEM;
	document.forms[0].E01IFMFED.value = FED;
}


</SCRIPT>  
</head>
<BODY>
<h3 align="center">Estados Financieros por Cliente<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_list.jsp, EDP0730"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="400">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="E01IFMCUN" value="<%=userPO.getHeader1()%>" >
    <input type=HIDDEN name="E01IFMCFO" value="">
    <input type=HIDDEN name="TITULO" value="">
    <input type=HIDDEN name="E01IFMFEY" value="">
    <input type=HIDDEN name="E01IFMFEM" value="">
    <input type=HIDDEN name="E01IFMFED" value="">
    <input type=HIDDEN name="opt">
    <input type=HIDDEN name="action">
  </p>
  <p> 
    <%
	if ( EDP073001Help.getNoResult() ) {
 %>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
            <tr> 
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="javascript:goAction(1)"><b>Nuevo</b></a></div>
              </td>
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
            </tr>
          </table>
          <p>&nbsp;</p>
        </div>
	  </TD>
	</TR>
    </TABLE>
  <%  
		}
	else {
%> <% 

String chk = "";

 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
  <p> 
  <table class="tbenter" width=100% align=center>
    <tr> 
	<% if(userPO.getOption().equals(" ")) { %> 
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction(1)"><b>Nuevo</b></a></div>
     </td>
	<%};%> 
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction(4)"><b>Consulta</b></a></div>
     </td>
	<% if(userPO.getOption().equals(" ")) { %> 
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Modificar</b></a></div>
     </td>
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction(3)"><b>Eliminar</b></a></div>
     </td>
	<%};%> 
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  <br>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="10%"><div align="center">Id. Cliente</div></th>
            <th align=CENTER nowrap width="25%"><div align="center">Nombre</div></th>
            <th align=CENTER nowrap width="25%"><div align="center">Formato</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Fecha Inic.</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Fecha Fin</div></th>
            <th align=CENTER nowrap width="5%"><div align="center">Revisado</div></th>
            <th align=CENTER nowrap width="5%"><div align="center">Vencido</div></th>
           </tr>
          <%
                EDP073001Help.initRow();
                chk = "checked";
                while (EDP073001Help.getNextRow()) {
                 
                  datapro.eibs.beans.EDP073001Message msg0730 = (datapro.eibs.beans.EDP073001Message) EDP073001Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= EDP073001Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value,'<%= msg0730.getE01IFMCUN() %>','<%= msg0730.getE01IFMCFO() %>','<%= msg0730.getE01IFMDSC() %>','<%= msg0730.getE01IFMFEY() %>','<%= msg0730.getE01IFMFEM() %>','<%= msg0730.getE01IFMFED() %>');">
            </td>
			<td NOWRAP  align=CENTER width=\"10%\"><%= msg0730.getE01IFMCUN() %></td>
            <td NOWRAP  align=LEFT width=\"25%\"><%= msg0730.getE01IFMNA1() %></td>
			<td NOWRAP  align=CENTER width=\"25%\"><%= msg0730.getE01IFMCFO() %>-<%= msg0730.getE01IFMDSC() %></td>
			<td NOWRAP  align=CENTER width=\"10%\"><%= msg0730.getE01IFMFD2() %>/<%= msg0730.getE01IFMFM2() %>/<%= msg0730.getE01IFMFY2() %></td>
			<td NOWRAP  align=CENTER width=\"10%\"><%= msg0730.getE01IFMFED() %>/<%= msg0730.getE01IFMFEM() %>/<%= msg0730.getE01IFMFEY() %></td>
			<td NOWRAP  align=CENTER width=\"5%\"><% if (msg0730.getE01DPBREV().equals("1")) {out.print("SI");} else {out.print("NO");} %></td>
			<td NOWRAP  align=CENTER width=\"5%\"><% if (msg0730.getE01DPBVEN().equals("1")) {out.print("SI");} else {out.print("NO");} %></td>
         </tr>
          <%
              				chk = "";     
                }
              %>
        </table>
  </table>
  <BR>
  <TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
        if ( EDP073001Help.getShowPrev() ) {
		int pos = EDP073001Help.getFirstRec() - 20;
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=200&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
  %>
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>
  <%
        if ( EDP073001Help.getShowNext() ) {
		int pos = EDP073001Help.getLastRec();
   			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=200&Pos=" + pos +"\"><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");
        }
  %> 
 </TD>
 </TR>
 </TABLE>

  <SCRIPT language="JavaScript">
 showChecked("CURRCODE");
</SCRIPT>
<%}%>
</form>
</body>
</html>
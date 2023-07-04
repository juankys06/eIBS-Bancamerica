<html>

<head><title>FX Rate Tolerance by User-Currency</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "EFE0007List" class= "datapro.eibs.beans.JBList"   scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="JavaScript">
  function goAction(op) {

	if(document.forms[0].E01FEUUSR.value == '' && (op==2 || op==4)){
    	alert("Un Usuario debe ser seleccionado"); 
        return; 
    }
    if (op == 4) {
		if (!confirm("Desea borrar este registro?")) {
			return;
		}
	}	
 	document.forms[0].SCREEN.value = op;
	document.forms[0].submit();
  }

  function getKey(User, Currency) {
    document.forms[0].E01FEUUSR.value = User;
	document.forms[0].E01FEUCCY.value = Currency;
  }

</SCRIPT>  

</head>

<body>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0007">
<input type=HIDDEN name="totalRow" value="0">
<input type=HIDDEN name="E01FEUUSR" value="">
<input type=HIDDEN name="E01FEUCCY" value="">
<input type=HIDDEN NAME="SCREEN" value="1">

  <h3 align="center">Tolerancia de Tasas de Cambio para Usuarios / Moneda<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="fe_user_currency_list.jsp, EFE0007">
  </h3>
	<hr size="4">
  
  <%
	if ( EFE0007List.getNoResult() ) {
  %>
  <table class="tbenter" width="100%">
    <tr> 
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction(10)"><b>Nuevo</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </TD>
    </tr>
  </table>
  <TABLE class="tbenter" width=100% height=100%>
  	<TR>
   		<TD align="center"><b> No hay usuarios registrados para Tolerancia por moneda </b></TD>
   	</TR>
  </TABLE>
<%  
		}
	else {
	
	if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
%>	
 
  <table class="tbenter" width="100%">
    <tr> 
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction(10)"><b>Nuevo</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction(2)"><b>Actualizar</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction(4)"><b>Borrar</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </TD>
    </tr>
  </table>
  
<TABLE  id="mainTable" class="tableinfo">
<TR > 
    <TD NOWRAP valign="top" width="100%" >
<TABLE id="headTable" width="100%" >
  <TR id="trdark"> 
    <th ALIGN=CENTER NOWRAP> </th>
    <th ALIGN=CENTER NOWRAP>Usuario</th>
    <th ALIGN=CENTER NOWRAP>Nombre</th>
    <th ALIGN=CENTER NOWRAP>Moneda</th>
    <th ALIGN=CENTER NOWRAP>Descripción</th>
    <th ALIGN=CENTER NOWRAP>Porcentaje</th>
  </tr>
  <TR id=trdark>
	<td NOWRAP align="center"></td>
	<td NOWRAP align="center">
		<INPUT type="text" name="FUSER" size="11" maxlength="10" value="<%= userPO.getHeader1() %>">
				<IMG src="<%=request.getContextPath()%>/images/ico5.gif" onclick="goSearch('C')" width="15" height="11" 
					ALT="Posicionarse en...">
	</td>
	<td NOWRAP align="center"></td>
	<td NOWRAP align="center"></td>
	<TD nowrap align="center"></td>
	<TD nowrap align="center"></TD>
  </TR>
  
  </table>
  <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" >
  <%
                EFE0007List.initRow();
				int k = 1;
                while (EFE0007List.getNextRow()) {
                    if (EFE0007List.getFlag().equals("")) {
                    		out.println(EFE0007List.getRecord());
							k ++;
                    }
                }
    %> 
</TABLE>

</div>
</td>
</tr>
</table>
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
        if ( EFE0007List.getShowPrev() ) {
      			int pos = EFE0007List.getFirstRec() - 21;
				String cun = userPO.getIdentifier();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.forex.JSEFE0007?Pos=" + pos + "&SCREEN=1><IMG border=\"0\" src=\""+request.getContextPath()+"/images/e/previous_records.gif\" ></A>");
        }
   %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <% 
        if ( EFE0007List.getShowNext() ) {
      			int pos = EFE0007List.getLastRec();
				String cun = userPO.getIdentifier();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.forex.JSEFE0007?Pos=" + pos + "&SCREEN=1><IMG border=\"0\" src=\""+request.getContextPath()+"/images/e/next_records.gif\" ></A>");
        }
  %> 
   </TD>
 	</TR>
 	</TABLE>

<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(false);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     resizeDoc();
     window.onresize=resizeDoc;
</SCRIPT>
<%
   }  
%>

</form>
</body>
</html>


<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Tabla de Puntuación y Niveles de Riesgo</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "trans" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "transData" class= "datapro.eibs.beans.DataTransaction"  scope="session" /> 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="JavaScript">

function goAction(option) {
  var okdel = false;
  document.forms[0].opt.value = option;
  document.forms[0].SCREEN.value="2";

  switch (option) {
  
	 case  1: 
	     okdel = confirm("Esta opcion borrara las transacciones seleccionadas");
    	  if ( okdel ) { document.forms[0].submit();	}
	      break;
	 case  2: 
		  document.forms[0].SCREEN.value="200";
    	  document.forms[0].submit();
	      break;
	 case  7: 
		  document.forms[0].SCREEN.value="2";
    	  document.forms[0].submit();
	      break;
	}
 }
function getParams(currrow,cacti,dacti) {

	document.forms[0].CURRENTROW.value = currrow;
}



</SCRIPT>  
</head>
<BODY>
<%
	 trans.initRow();
    int blank_row = Integer.parseInt(transData.getTrNum());
    int max_row = 9999;
    int row;
    int total_row;
    try {
      row = Integer.parseInt(request.getParameter("ROW"));
    }
    catch (Exception e) {
      row = 0;
    }
    if ( (row == 0) || (row < trans.getLastRow()) ) {
      total_row = trans.getLastRow() + 1 + blank_row;
    }
    else {
		total_row = row;       
    }
    
%> 
<h3 align="center">Tabla de Puntuación y Niveles de Riesgo</h3>
<h3 align="center">
<% if (userPO.getHeader2().trim().equals("1")) {;
%>
<% }; %>
<% if (userPO.getHeader2().trim().equals("2")) {;
%>
<% }; %>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="risk_levels,EDP0860"></h3>

<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0860" >
  <p> 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <input type=HIDDEN name="opt">
    <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="<%= total_row %>">
  </p>
  <p> 
<% 
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
    <td class=TDBKG width="20%">
        <div align="center"><a href="javascript:goAction(7)"><b>Enviar</b></a></div> 
    </td> 
	 <td class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction(1)"><b>Borrar Selección</b></a></div>
      </td>
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  
  
<TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER NOWRAP>
    <tr > 
    <TD NOWRAP>
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="5%">&nbsp;</th>
            <th align=CENTER nowrap width="10%"> <div align="center">Código</div> </th>
            <th align=CENTER nowrap width="30%">Descripción</th>
            <th align=CENTER nowrap width="10%"> <div align="center">Nombre Corto</div> </th>
            <th align=CENTER nowrap width="15%"> <div align="center">Puntos <BR> Iniciales</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Porcentaje <BR> Minimo</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Porcentaje <BR> Maximo</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Probabilidad <BR> Incumplimiento</div> </th>
	      </tr>
	</TABLE>

   <div id="dataDiv1" class="scbarcolor" NOWRAP>
    <%
		int i = 0;
		int iw = 5;
    %> 

    <table id="dataTable" NOWRAP> 

    <%
				trans.initRow();
	 			boolean firstTime = true;
                while (trans.getNextRow()) {
					if (firstTime) {
						firstTime = false;
						iw = iw-5;
					}
	      %> 
    <tr id="trclear"> 
      <td align="center" nowrap > 
        <input type="checkbox" name="TRANSROW_<%= trans.getCurrentRow() %>" value="checked">
      </td>
      <td align="center" nowrap >  
          <input type="text" name="E01PNRCCL_<%= trans.getCurrentRow() %>" size="5" maxlength="4"  value="<%= trans.getRecord(1) %>" > 
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01PNRDSC_<%= trans.getCurrentRow() %>" size="36" maxlength="35"  value="<%= trans.getRecord(2) %>"  >
      </td>												
      <td align="center" nowrap > 
          <input type="text" name="E01PNRSNM_<%= trans.getCurrentRow() %>" size="16" maxlength="15" value="<%= trans.getRecord(3) %>" >
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01PNRPTS_<%= trans.getCurrentRow() %>" size="17" maxlength="17" onKeypress="enterSignDecimal()" value="<%= trans.getRecord(4) %>" >
      </td>
      <td align="center" nowrap> 
          <input type="text" name="E01PNRPMN_<%= trans.getCurrentRow() %>" size="10" maxlength="10" onKeypress="enterDecimal()" value="<%= trans.getRecord(5) %>" >
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01PNRPMX_<%= trans.getCurrentRow() %>" size="10" maxlength="10" onKeypress="enterDecimal()" value="<%= trans.getRecord(6) %>" >
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01PNRPRI_<%= trans.getCurrentRow() %>" size="10" maxlength="10" onKeypress="enterDecimal()" value="<%= trans.getRecord(7) %>" >
      </td>
    </tr>
    <%
                }
			for (i=trans.getRow(); i < trans.getCurrentRow() + (iw+5); i++) {
    %> 
    <tr id="trclear"> 
      <td align="center" nowrap  > 
        <input type="checkbox" name="TRANSROW_<%= i %>" value="checked">
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01PNRCCL_<%= i %>" size="5" maxlength="4"  >
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01PNRDSC_<%= i %>" size="36" maxlength="35" >
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01PNRSNM_<%= i %>" size="16" maxlength="15" value="" >
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01PNRPTS_<%= i %>" size="17" maxlength="17" value="" onKeypress="enterSignDecimal()" >
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01PNRPMN_<%= i %>" size="10" maxlength="10" value="" onKeypress="enterDecimal()" >
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01PNRPMX_<%= i %>" size="10" maxlength="10" value="" onKeypress="enterDecimal()" >
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01PNRPRI_<%= i %>" size="10" maxlength="10" value="" onKeypress="enterDecimal()" >
      </td>
    </tr>
    <%
      }
    %> 

 
  </table>
  <input type="HIDDEN" name="RECNUM" value="<%=i%>">
  </div>
  </TD>
  </TR>
  </table>
  <SCRIPT language="JavaScript">

     function resizeDoc() {
       	divResize();
     	adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     resizeDoc();
     window.onresize=resizeDoc;

</SCRIPT>
</form>
</body>
</html>
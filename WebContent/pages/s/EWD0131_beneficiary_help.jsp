<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Lista de Beneficiarios</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="javascript">

function enter(customer,acc,bad1,bad2,bad3,bkid,bka1,bka2,bka3,bka4,ibid,iba1,iba2,iba3,iba4) {
	var form = top.opener.document.forms[0];
//	form[top.opener.fieldName].value = customer;
  	form[top.opener.fieldAux1].value = acc;
  	form[top.opener.fieldAux2].value = bad1;
  	form[top.opener.fieldAux3].value = bad2;
  	form[top.opener.fieldAux4].value = bad3;
  	form[top.opener.fieldAux5].value = bkid;
   	form[top.opener.fieldAux6].value = bka1;
   	form[top.opener.fieldAux7].value = bka2;
   	form[top.opener.fieldAux8].value = bka3;
   	form[top.opener.fieldAux9].value = bka4;
   	form[top.opener.fieldAux10].value = ibid;
   	form[top.opener.fieldAux11].value = iba1;
   	form[top.opener.fieldAux12].value = iba2;
   	form[top.opener.fieldAux13].value = iba3;
   	form[top.opener.fieldAux14].value = iba4;
	top.close();
}

</script>

<jsp:useBean id= "EWD0131Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

</head>
<body>
<form>

  <h3 align="center">Lista de Beneficiarios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="beneficiary_help.jsp,EWD0131"> 
  </h3>
<hr size="4">
  <input type=HIDDEN name="totalRow" value="0">
  <%
	if ( EWD0131Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center"> <b> No hay resultados que correspondan con su criterio de búsqueda</b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
<p>&nbsp;</p>
<TABLE  id="mainTable" class="tableinfo">
<TR > 
    <TD NOWRAP valign="top" width="100%" >
<TABLE id="headTable" width="100%" >
  <TR id="trdark"> 
            <th>Cuenta</th>
            <th>Nombre</th>
            <th>Dirección 1</th>
            <th>Dirección 2</th>
            <th>Dirección 3</th>
            <th>Código<br>Banco<br>Beneficiario</th>
            <th>Dirección 1 - Banco Beneficiario</th>
            <th>Dirección 2<br>Banco Beneficiario</th>
            <th>Dirección 3<br>Banco Beneficiario</th>
            <th>Dirección 4<br>Banco Beneficiario</th>
            <th>Código<br>Banco<br>Intermediario</th>
            <th>Dirección 1<br>Banco Intermediario</th>
            <th>Dirección 2<br>Banco Intermediario</th>
            <th>Dirección 3<br>Banco Intermediario</th>
            <th>Dirección 4<br>Banco Intermediario</th>
  </tr>
  </table>
  <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" >
  <%
                EWD0131Help.initRow();
				int k = 1;
                while (EWD0131Help.getNextRow()) {
                    if (EWD0131Help.getFlag().equals("")) {
                    		out.println(EWD0131Help.getRecord());
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
        if ( EWD0131Help.getShowPrev() ) {
      			int pos = EWD0131Help.getFirstRec() - 21;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0131?Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
   %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <% 
        if ( EWD0131Help.getShowNext() ) {
      			int pos = EWD0131Help.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0131?Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
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


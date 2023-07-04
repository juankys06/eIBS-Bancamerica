<HTML>

<%@ page import = "datapro.eibs.master.Util" %><META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers
function enter(code) {
var form = top.opener.document.forms[0];

	   form[top.opener.fieldName].value = code;
  		
top.close();
 }
//-->
</script>
</head>

<jsp:useBean id= "bankIdHelp" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<%
	if ( bankIdHelp.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <b> No hay resultados para su criterio de busqueda</b> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
 <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER style="width:'95%'">
 <TR> 
    <TD NOWRAP valign="top" width="100%" >
  <TABLE id="headTable" >
  <TR id="trdark">
 	  <th nowrap>Identificacion</th> 
      <th nowrap>Nombre</th>
      <th nowrap>Pais</th>
	  <th nowrap>Estado</th>
	  <th nowrap>Ciudad</th>
	 </TR>
    </TABLE>
  
    <div id="dataDiv1" class="scbarcolor" >
     <TABLE id="dataTable">	
    		<%
                String NameSearch = (String)request.getAttribute("NameSearch");
                bankIdHelp.initRow();
				int i=0;
                while (bankIdHelp.getNextRow()) {
                    if (bankIdHelp.getFlag().equals("")) {
                    		out.println(bankIdHelp.getRecord());
					i++;
                    }
                }
              %> 
     </TABLE>
  </div>
   </TD>
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= i %>";
     divResize(false);
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);     
</SCRIPT>
<%      
  }
%> 
</FORM>

</BODY>
</HTML>


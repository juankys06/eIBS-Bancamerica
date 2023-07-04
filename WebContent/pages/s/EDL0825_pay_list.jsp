<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Letras</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "docList" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

</head>
<body >

<%
	if ( docList.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con 
        su criterio de b&uacute;squeda</b></font> </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
    docList.initRow();
	docList.setCurrentRow(0);
%>
    

<SCRIPT Language="Javascript">

function ShowPay(Loan,ID,Doc,Type,radio,row){
 radioClick(radio,row);
 var Params = "?SCREEN=3&Loan="+ Loan+"&ID=" + ID + "&Doc=" + Doc + "&Type=" + Type;
 page= webapp + "/servlet/datapro.eibs.products.JSEDL0825"+Params;

 CenterWindow(page,530,460,2);           
}

  function setInfo(idxRow){
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   }

</SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>


<h3 align="center">Listado Documentos a Pagar<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pay_list,EDL0825"></h3>
<hr size="4">  
    
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0825">

    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="">
    <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="<%= docList.getRow() %>">
  
      
      
  <table class="tableinfo" id="trdark">
    <tr id="trdark">
      <th align="center" nowrap colspan="2">
      <% if (request.getParameter("OPT").equals("LN")) { out.println("Cuenta");}
        		 else { out.println("Aceptante");} %>
      </th>
    </tr>
    <tr id="trclear">
      <td align="center" nowrap width="50%">
       <% if (request.getParameter("OPT").equals("LN")) { out.print("<b>Número : </b>"+docList.getRecord(7));}
        		 else { out.print("<b>Identificación : </b>"+docList.getRecord(1));} %>
      </td>
      
      <td align="center" nowrap width="50%">
       <% if (request.getParameter("OPT").equals("LN")) { out.println("<b>Cliente : </b>"+docList.getRecord(9));}
        		 else { out.println("<b>Nombre : </b>"+docList.getRecord(2));} %>
      </td>
    </tr>   
  </table>
  <BR>
  <TABLE  id="mainTable" class="tableinfo">
  <TR> 
    <TD NOWRAP>
   <TABLE id="headTable" NOWRAP>
    <TR id="trdark"> 
      <th nowrap>&nbsp;</th>
      <th align="center" nowrap >No. Doc.</th>
      <th align="center" nowrap >Cuenta</th>
      <th align="center" nowrap ><% if (request.getParameter("OPT").equals("LN")) { out.println("Aceptante");}
        		 else { out.println("Cliente");} %></th>
      <th align="center" nowrap >Monto</th>
    </tr>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" NOWRAP>
    <table id="dataTable" NOWRAP> 
    <%
	 	docList.initRow();
        while (docList.getNextRow()) {
	      %> 
    <tr> 
      <td align="center" nowrap > 
        <input type="radio" name="ROW" value="<%= docList.getCurrentRow() %>" onClick="setInfo(<%= docList.getCurrentRow() %>)" <% if (docList.getCurrentRow() == 0) out.print("checked");%>>
      </td>
      <td align="center" nowrap >  
          <a href="javascript:ShowPay('<%= docList.getRecord(7) %>','<%= docList.getRecord(1) %>','<%= docList.getRecord(0) %>','<%= docList.getRecord(6) %>','ROW',<%= docList.getCurrentRow() %>)"><%= docList.getRecord(0) %></a> 
      </td>
      <td align="center" nowrap > 
		  <a href="javascript:ShowPay('<%= docList.getRecord(7) %>','<%= docList.getRecord(1) %>','<%= docList.getRecord(0) %>','<%= docList.getRecord(6) %>','ROW',<%= docList.getCurrentRow() %>)"><%= docList.getRecord(7) %></a>
      </td>												
      <td align="center" nowrap > 
		  <a href="javascript:ShowPay('<%= docList.getRecord(7) %>','<%= docList.getRecord(1) %>','<%= docList.getRecord(0) %>','<%= docList.getRecord(6) %>','ROW',<%= docList.getCurrentRow() %>)">
          <%if (request.getParameter("OPT").equals("LN")) { out.println(docList.getRecord(2));}
        		 else { out.println(docList.getRecord(9));} %></a>
      </td>
      <td align="center" nowrap > 
		  <a href="javascript:ShowPay('<%= docList.getRecord(7) %>','<%= docList.getRecord(1) %>','<%= docList.getRecord(0) %>','<%= docList.getRecord(6) %>','ROW',<%= docList.getCurrentRow() %>)"><%= docList.getRecord(3) %></a>
      </td>
    </tr>
    <%
	 }
	%>
  </table>
  </div>
   
  </TD>
  </TR>	
</TABLE>

  <BR>
  
    <BR>
  <SCRIPT language="JavaScript">
     function resizeDoc() {
       divResize();
     adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     showChecked("ROW");
     resizeDoc();
     window.onresize=resizeDoc;
     
</SCRIPT>     
</form>
<%
	}
%>
</body>
</html>

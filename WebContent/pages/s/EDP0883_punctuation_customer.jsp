<html>
<head>
<title>Calificación Puntuación Credit Scoring por Cliente</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "trans1" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function getParams2(p1,p2,p3,p4) {
	sec = p4; 
	eval("document.forms[0].PTSSEQ_"+sec+".value = 1");
}

function goUpdVal(){
	document.forms[0].SCREEN.value=300;
	document.forms[0].submit();	 
}

</script>

</head>
<body >
<%
	 trans1.initRow();
    int max_row = 9999;
    int row;
    int total_row;
    try {
      row = Integer.parseInt(request.getParameter("ROW"));
    }
    catch (Exception e) {
      row = 0;
    }
    if ( (row == 0) || (row < trans1.getLastRow()) ) {
    }
    else {
		total_row = row;       
    }
%> 

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>

<h3 align="center">Calificación Puntuación Credit Scoring por Cliente</h3>

<h3 align="center">
<% if (userPO.getHeader2().trim().equals("1")) {;
%>
<% }; %>
<% if (userPO.getHeader2().trim().equals("2")) {;
%>
<% }; %>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="punctuation_customer.jsp,EDP0883"></h3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0883" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
  <input type=HIDDEN name="opt">
  <h4 align="center"> 
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="">
    <tr bordercolor="#FFFFFF"> 
      Cliente : 
	<INPUT type="text" name="DSC" size="10" maxlength="9" value="<%= userPO.getCusNum().trim()%>" readonly >
	<INPUT type="text" name="DSC1" size="35" maxlength="35" value="<%= userPO.getCusName().trim()%>" readonly>
	</tr>
  </h4> 

<h4>Asignar Calificación :</h4>
  
<table id="mainTable"  class="tableinfo">
  <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
		
	<TABLE id="tbenter" align="center" style="width: 100%" border="0">
    <TD NOWRAP>
      <td NOWRAP valign="top" > 
        <table id="headTable1" >
          <tr id="trdark"> 
            <th align=CENTER nowrap >Código</th>
            <th align=CENTER nowrap > <div align="center">Descripción Módulo</div> </th>
<%--
            <th align=CENTER nowrap > <div align="center">Puntos</div> </th>
--%>
            <th align=CENTER nowrap > <div align="center">Sel.</div> </th>
            <th align=CENTER nowrap > <div align="center">Porcentaje</div> </th>
<%--
            <th align=CENTER nowrap > <div align="center">Puntos</div> </th>
--%>
            <th align=CENTER nowrap > <div align="center">Calificación</div> </th>
	      </tr>
	</TABLE>
   <div id="dataDiv1" class="scbarcolor" NOWRAP>

    <%
		int i = 0;
		int j = 0;
    %> 

    <table id="dataTable1" NOWRAP> 

          <%
	 			trans1.initRow();
	 			boolean firstTime = true;
				String Chk1 = "";
                while (trans1.getNextRow()) {
					if (firstTime) {
						Chk1 = "checked";
						firstTime = false;
					}else{
						Chk1 = "";
					}
				i++;
		 %>
		<% if (!trans1.getRecord(1).equals("")) { %>		
		<% if (trans1.getRecord(9).equals("H")) { %>		
          <tr id="trdark"> 
	      	<th align="left" nowrap >  
				<DIV NOWRAP><%=trans1.getRecord(1)%></DIV>
      		</th>
	      	<th align="left" nowrap >  
				<DIV NOWRAP><%=trans1.getRecord(2)%></DIV>
      		</th>
<%--
	      	<th align="left" nowrap >  
				<DIV NOWRAP></DIV>
      		</th>
--%>
	      	<th align="left" nowrap >  
				<DIV NOWRAP></DIV>
      		</th>
	      	<th align="left" nowrap >  
				<DIV NOWRAP><%=trans1.getRecord(6)%></DIV>
      		</th>
<%--
	      	<th align="left" nowrap >  
				<DIV NOWRAP><%=trans1.getRecord(7)%></DIV>
      		</th>
--%>
	      	<th align="left" nowrap >  
				<DIV NOWRAP><%=trans1.getRecord(8)%></DIV>
      		</th>
          </tr>
	        <% j++; }else { %>
          <tr> 
    	      <input type="hidden" name="MODCOD_<%= trans1.getCurrentRow() %>" value="<%= trans1.getRecord(1) %>"> 
    	      <input type="hidden" name="MODCOD_<%= trans1.getCurrentRow() %>" value="<%= trans1.getRecord(1) %>"> 
	      	<td align="center" nowrap >  
    	      <input type="text" name="E01PTSSEQ_<%= trans1.getCurrentRow() %>"  size="4" maxlength="4"  value="<%= trans1.getRecord(3) %>"  readonly > 
      		</td>
	      	<td align="center" nowrap >  
    	      <input type="text" name="E01PTSDSC_<%= trans1.getCurrentRow() %>"  size="50" maxlength="50"  value="<%= trans1.getRecord(2) %>" readonly > 
      		</td>
<%--
	      	<td align="center" nowrap >  
    	      <input type="text" name="E01PTSPTS_<%= trans1.getCurrentRow() %>"  size="10" maxlength="9"  value="<%= trans1.getRecord(4) %>" readonly > 
      		</td>
--%>
		    <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE2<%=j%>" <% if(trans1.getRecord(5).equals("1")){out.print("checked value=1");}else{out.print("value=0");}%> onClick="getParams2(this.value,'<%= trans1.getRecord(3) %>','<%= trans1.getRecord(4) %>',<%= trans1.getCurrentRow() %>)"
			<% if(userPO.getPurpose().equals("INQUIRY")) {out.print("disabled") ;}%>

              >
<%--
    	      <input type="hidden" name="PTSSEQ_<%= trans1.getCurrentRow() %>" <% if(trans1.getRecord(5).equals("1")){out.print("value=1");}else{out.print("value=0");}%> > 
--%>
    	      <input type="hidden" name="E01PTSPTS_<%= trans1.getCurrentRow() %>" value="<%= trans1.getRecord(4) %>"> 
    	      <input type="hidden" name="PTSSEQ_<%= trans1.getCurrentRow() %>" > 
		    </td>
	      	<td align="center" nowrap >  
      		</td>
	      	<td align="center" nowrap >  
      		</td>
<%--
	      	<td align="center" nowrap >  
      		</td>
--%>
	      	<td align="center" nowrap >  
      		</td>
          </tr>
            <% } %>
	        <% }else { %>
          <tr> 
			<TD ALIGN=CENTER NOWRAP>
			</TD>
			<TD ALIGN=CENTER NOWRAP>
			</TD>
			<TH ALIGN=CENTER NOWRAP>
				<DIV NOWRAP><%=trans1.getRecord(2)%></DIV>
			</TH>
<%--
			<TD ALIGN=CENTER NOWRAP>
			</TD>
--%>

			<TH ALIGN=LEFT NOWRAP>
				<DIV NOWRAP><%=trans1.getRecord(6)%></DIV>
			</TH>
<%--
			<TH ALIGN=LEFT NOWRAP>
				<DIV NOWRAP><%=trans1.getRecord(7)%></DIV>
			</TH>
--%>
			<TH ALIGN=LEFT NOWRAP>
				<DIV NOWRAP><%=trans1.getRecord(8)%></DIV>
			</TH>
          </tr>
            <% } %>

    <%
                }
    %> 

  </table>
  <input type="HIDDEN" name="RECNUM" value="<%=i%>">
  </div>

  </TABLE>
  </td>
  </tr>
</table>

  <table class="tbenter" width=100% align=center>
    <tr> 
	<% if(!userPO.getPurpose().equals("INQUIRY")) {%>
    <td class=TDBKG width="20%">
        <div align="center"><a href="javascript:goUpdVal()"><b>Enviar</b></a></div> 
    </td> 
	<% }; %>
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  
  <SCRIPT language="JavaScript">

     function resizeDoc() {
    	divResize();
	    var dataTab1 = document.all["dataTable1"];
     	adjustEquTables(headTable1, dataTab1, dataDiv1,1,false);
      }
     resizeDoc();
     window.onresize=resizeDoc;

</SCRIPT>
</form>
</body>
</html>
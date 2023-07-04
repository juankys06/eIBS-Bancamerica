<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Asignación de Puntos por Módulo</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "trans" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "transData" class= "datapro.eibs.beans.DataTransaction"  scope="session" /> 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
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

<script language="JavaScript">

function getParams(currrow,dsc) {

	document.forms[0].E01MODCOD.value = currrow;
	document.forms[0].E01MODDSC.value = dsc;
}

function submitThis(option) {
 if (document.forms[0].E01MODCOD.value !="") {
	document.forms[0].SCREEN.value="300";
	document.forms[0].submit();
 }
}


</SCRIPT>  
<h3 align="center">Asignación de Puntos por Módulo de Clasificación</h3>

<h3 align="center">
<% if (userPO.getHeader2().trim().equals("1")) {;
%>
Corporación
<% }; %>
<% if (userPO.getHeader2().trim().equals("2")) {;
%>
Persona Natural
<% }; %>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="modules_list,EDP0840"></h3>

<hr size="4">

 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0840" >
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0840" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="E01MODCOD" VALUE=""> 
    <INPUT TYPE=HIDDEN NAME="E01MODDSC" VALUE=""> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
    <input type=HIDDEN name="opt">
    <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="<%= total_row %>">
    <INPUT TYPE=HIDDEN NAME="E01MODLGT" VALUE="<%= userPO.getHeader2().trim() %>">
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
  <table class="tbenter" width=100% align=center>
    <tr> 
    <td class=TDBKG width="20%">
        <div align="center">
        <a href="javascript:submitThis(7)"><b>Enviar</b></a>
        </div> 
    </td> 
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  
  
  <table  id=mainTable class="tableinfo">
    <tr > 
     <td NOWRAP > 
   <TABLE id="headTable" NOWRAP>
          <tr id="trdark"> 
            <th align=CENTER nowrap >&nbsp;</th>
            <th align=CENTER nowrap > <div align="center">Código</div> </th>
            <th align=CENTER nowrap >Descripción</th>
            <th align=CENTER nowrap > <div align="center">Porcentaje
              <input type=TEXT name="T01MODPER" size=10 maxlength=9 value="<%= userPO.getHeader1().trim() %>" readonly >
            </div> </th>
           </tr>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" NOWRAP>

    <table id="dataTable" NOWRAP> 

          <%
	 			trans.initRow();
                while (trans.getNextRow()) {
		 %>
          <tr> 
	        <td align="center" nowrap >
                <input type="radio" name="CURRROW" value="<%= trans.getRecord(1) %>" <%=chk%> onClick="getParams(this.value,'<%= trans.getRecord(2) %>')">
            </td>
            
			<TD ALIGN=CENTER NOWRAP>
				<a href="javascript:radioClick('CURRROW',<%= trans.getCurrentRow() %>)" >
				<DIV NOWRAP><%=trans.getRecord(1)%></DIV>
				</a>
			</TD>
			<TD ALIGN=CENTER NOWRAP>
				<a href="javascript:radioClick('CURRROW',<%= trans.getCurrentRow() %>)" >
				<DIV NOWRAP><%=trans.getRecord(2)%></DIV>
				</a>
			</TD>
			<TD ALIGN=LEFT NOWRAP>
				<a href="javascript:radioClick('CURRROW',<%= trans.getCurrentRow() %>)" >
				<DIV NOWRAP><%=trans.getRecord(3)%></DIV>
				</a>
			</TD>
          </tr>
    <%
                }
    %> 
         </table>
  </TD>
  </TR>	
  </table>
  <BR>


 <SCRIPT language="JavaScript">

     function resizeDoc() {
       	divResize();
     	adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     resizeDoc();
     window.onresize=resizeDoc;
	 showChecked("CURRROW");

</SCRIPT>
</form>
</body>
</html>
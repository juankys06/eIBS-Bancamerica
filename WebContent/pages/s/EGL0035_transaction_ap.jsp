<html>
<head>
<title>Transacciones</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "trans" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" /> 
<jsp:useBean id= "transData" class= "datapro.eibs.beans.DataTransaction"  scope="session" /> 

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

</head>
<body>
<%
	 trans.initRow();
    int blank_row = Integer.parseInt(transData.getTrNum());
    int max_row = 361;
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

<SCRIPT LANGUAGE="JavaScript">
<%
  if ( !userPO.getOption().equals("TRANSACTION") ){
 
    if (userPO.getOption().equals("CD")){
%>
	 builtNewMenu(cd_a_opt);
<%   
   }
   else if (userPO.getOption().equals("LN")){
   %>
	 builtNewMenu(ln_a_opt);
  <%   
   } 
   else if (userPO.getOption().equals("OCK")){
    %>		
      builtNewMenu(of_a_opt);
  <%	
   } 
	else if (userPO.getOption().equals("PR")){
    %>		
      builtNewMenu(pr_a_opt);
  <%	
   }
	else if (userPO.getOption().equals("LN_TRANSACTION")){
    %>		
      builtNewMenu(ln_t_a_opt);
  <%	
   }
   %>
   initMenu();
 <%  
}
%>

</SCRIPT>
<SCRIPT>

function checkRowValue() {
  var r = <%= total_row %> + parseInt(document.forms[0].TEMP_ROW.value);
  if (r > <%= max_row %>) {
    alert("Registers out of range, maximum number is 361");
  }
  else {
    document.forms[0].ROW.value = r + "";
  }
}

function setCurrentRow(row) {
var form = document.forms[0];
   form["CURRENTROW"].value = row;
   if ( (form["E01WRKBNK_"+ row].value =="") && (form["E01WRKBRN_"+ row].value =="") && (form["E01WRKCCY_"+ row].value =="")
        && (form["E01WRKGLN_"+ row].value =="") && (form["E01WRKCCN_"+ row].value =="") && (form["E01WRKACC_"+ row].value =="")
        && (form["E01WRKCDE_"+ row].value =="") && (form["E01WRKAMT_"+ row].value =="") && (form["E01WRKDCC_"+ row].value =="")) {
    form["E01WRKTDS_"+ row].value = form["E01WRKTDS"].value;
    form["E01WRKVD1_"+ row].value = form["E01WRKVD1"].value;
    form["E01WRKVD2_"+ row].value = form["E01WRKVD2"].value;
    form["E01WRKVD3_"+ row].value = form["E01WRKVD3"].value;
  }
}

function GetInfo(currentrow){
if ( currentrow !== "" ) { setCurrentRow(currentrow);}
var winH = (currentrow == "") ? 270 : 325 ;

	pg= prefix +language + "EGL0035_transaction_info.jsp?CurrRow="+ currentrow;

	CenterNamedWindow(pg,'Information',400,winH,1);          
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

<h3 align="center">Transacciones <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="transaction_ap,EGL0035"></h3>
<hr size="4">
  
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL0035">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="">
    <input type=HIDDEN name="OPTHELP" value="">
     <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="<%= total_row %>">
  </p>
  
   
  <BR>
        
  <table class="tableinfo" id="trdark">
    <tr id="trdark"> 
      <td align="right"  nowrap width="21%"  >
       <% if (userPO.getOption().equals("LN")) { out.println("Cuenta : ");}
        		 else if (userPO.getOption().equals("CD")) { out.println("Certificado : ");}
        		 else if (userPO.getOption().equals("OCK")) { out.println("Cheque : ");}
        												    //if ( userPO.getHeader19().equals("1")) {out.println("Cheque No. : ");}
        												    //else {out.println("");}
        												    //}
        		else if (userPO.getOption().equals("TRANSACTION")) { out.println("Lote : ");}%>
      </td>
      <td align="right"  nowrap width="21%"  > 
        <div align="left"> 
          <% if (!userPO.getOption().equals("TRANSACTION")) { %> 
             <input type="text" name="E01NUMACC" size="17" maxlength="15" readonly value = "<%= transData.getAccNum() %>">
          <% } else {%>
             <input type="text" name="E01BTHNUM" size="6" maxlength="5" readonly value = "<%= transData.getBthnum() %>">
          <% } %>
         </div>
      </td>
      <td align="left" nowrap width="20%"  > 
        <div align="right">
           <% if (userPO.getOption().equals("TRANSACTION")) { %> 
             Originado : 
           <% } %>  
        </div>
      </td>
      <td align="right" nowrap width="33%"  > 
        <div align="left"> 
          <% if (userPO.getOption().equals("TRANSACTION")) { %> 
            <input type="text" name="E01WRKOBK" size="4" maxlength="2" readonly value = "<%= transData.getBank() %>">
            <input type="text" name="E01WRKOBR" size="5" maxlength="3" readonly value = "<%= transData.getBranch() %>">
          <% } %>
        </div>
      </td>
      <td align="right" nowrap width="33%"  > Total Debitos  : </td>
      <td align="left" nowrap colspan="3"  width="26%"> 
        <input type="text" name="DEBIT" size="14" maxlength="13" id="txtright" readonly value = "<%= transData.getDebitAmt() %>">
      </td>
    </tr>
    <tr id="trdark"> 
      <td align="right" nowrap width="21%" >Total Transacciones  :</td>
      <td align="right" nowrap width="21%"  colspan="3"> 
        <div align="left"> 
          <input type="text" name="ROW" size="5" maxlength="5" id="txtright" readonly value="<%= total_row %>">
        </div>
      </td>
      <td align="right" nowrap width="33%"  > Total Creditos 
        : </td>
      <td align="left" nowrap colspan="3"  width="26%"> 
        <input type="text" name="CREDIT" size="14" maxlength="13" id="txtright" readonly value = "<%= transData.getCreditAmt() %>">
      </td>
    </tr>
   
  </table>
  <BR>
  <table class="tableinfo">
    <tr  id="trclear">
      <td align="right" width="17%"> 
        Descripcion : 
      </td>
      <td align="left" width="40%" nowrap> 
        <input type="text" name="E01WRKTDS" size="30" maxlength="300" readonly value = "<%= transData.getDescription() %>">
        <a href="javascript:GetInfo('')"><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="More Information" align="middle" border="0" ></a> 
      </td>
      <td align="right"width="14%"> 
        Fecha Valor : 
      </td>
      <td align="left" width="29%" nowrap> 
        <input type="text" name="E01WRKVD2" size="2" maxlength="2" readonly value = "<%= transData.getDate2() %>">
        <input type="text" name="E01WRKVD1" size="2" maxlength="2" readonly value = "<%= transData.getDate1() %>">
        <input type="text" name="E01WRKVD3" size="2" maxlength="2" readonly value = "<%= transData.getDate3() %>">
      </td>
    </tr>
  </table>
  <BR>
  
  <TABLE  id="mainTable" class="tableinfo"  >
 <TR> 
    <TD NOWRAP>
   <TABLE id="headTable" >
    <TR id="trdark">
      <th align="center" nowrap >Bnc</th>
      <th align="center" nowrap >Suc</th>
      <th align="center" nowrap >Mda</th>
      <th align="center" nowrap >Cuenta<br>Contable</th>
      <th align="center" nowrap >Centro<br>Costo</th>
      <th align="center" nowrap >Número<br>Cuenta </th>
      <th align="center" nowrap >TR/CD</th>
      <th align="center" nowrap >Monto</th>
      <th align="center" nowrap > D/C</th>
      <th nowrap >&nbsp;</th>
    </tr>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
    <%
	 				 trans.initRow();
                while (trans.getNextRow()) {
	      %> 
    <tr id="trclear"> 
       <td align="center" nowrap >  
          <input type="text" name="E01WRKBNK_<%= trans.getCurrentRow() %>" size="2" maxlength="2"  readonly value="<%= trans.getRecord(0) %>" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" > 
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKBRN_<%= trans.getCurrentRow() %>" size="3" maxlength="3" readonly value="<%= trans.getRecord(1) %>" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" >
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKCCY_<%= trans.getCurrentRow() %>" size="3" maxlength="3" readonly value="<%= trans.getRecord(2) %>" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" id="txtright">
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKGLN_<%= trans.getCurrentRow() %>" size="17" maxlength="13" readonly value="<%= trans.getRecord(3) %>"  onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" id="txtright">
      </td>
      <td align="center" nowrap  > 
          <input type="text" name="E01WRKCCN_<%= trans.getCurrentRow() %>" size="8" maxlength="8" readonly value="<%= trans.getRecord(4) %>"   onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" id="txtright">
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKACC_<%= trans.getCurrentRow() %>" size="17" maxlength="13" readonly value="<%= trans.getRecord(5) %>"  onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" id="txtright">
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKCDE_<%= trans.getCurrentRow() %>" size="2" maxlength="2" readonly value="<%= trans.getRecord(6) %>"  onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" id="txtright">
      </td>
      <td align="center" nowrap > 
          <input type="text" name="E01WRKAMT_<%= trans.getCurrentRow() %>" size="17" maxlength="13" readonly value="<%= trans.getRecord(7) %>"  onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" id="txtright">
      </td>
      <td align="center" nowrap  > 
          <input type="text" name="E01WRKDCC_<%= trans.getCurrentRow() %>" size="1" maxlength="1" readonly value="<%= trans.getRecord(8) %>" onFocus="setCurrentRow('<%= trans.getCurrentRow() %>')" id="txtright">
      </td>
      <td align="left" nowrap > <a href="javascript:GetInfo('<%= trans.getCurrentRow()%>')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="More Information" align="middle" border="0" ></a> 
        <input type= Hidden name="E01WRKTDS_<%= trans.getCurrentRow()%>" size="37" maxlength="300" value="<%= trans.getRecord(9)+trans.getRecord(15)+trans.getRecord(16)+
                                                                                                              trans.getRecord(17)+trans.getRecord(18)+trans.getRecord(19)+
																											  trans.getRecord(20)+trans.getRecord(21)+trans.getRecord(22)+
																											  trans.getRecord(23)%>">
        <input type= Hidden name="E01WRKVD1_<%= trans.getCurrentRow()%>" size="2" maxlength="2" value = "<%= trans.getRecord(10) %>">
        <input type= Hidden name="E01WRKVD2_<%= trans.getCurrentRow()%>" size="2" maxlength="2" value = "<%= trans.getRecord(11) %>">
        <input type= Hidden name="E01WRKVD3_<%= trans.getCurrentRow()%>" size="2" maxlength="2" value = "<%= trans.getRecord(12) %>">
        <input type= Hidden name="E01WRKEXR_<%= trans.getCurrentRow()%>" size="12" maxlength="11" value = "<%= trans.getRecord(13) %>">
        <input type= Hidden name="E01WRKCUN_<%= trans.getCurrentRow()%>" size="10" maxlength="9" value = "<%= trans.getRecord(26) %>">
        <input type= Hidden name="DUPLIC_<%= trans.getCurrentRow()%>" size="2" maxlength="2" value = "<%= trans.getRecord(24) %>">
      </td>
    </tr>
   	<tr id="trdark"> 
		<td nowrap colspan="3">
      	</td>
		<td nowrap colspan="7"> 
        	<%= trans.getRecord(40).trim()%> 
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
<SCRIPT language="JavaScript">
     function resizeDoc() {
       divResize();
     adjustEquTables(headTable, dataTable, dataDiv1,0,false);
      }
     resizeDoc();
     window.onresize=resizeDoc;
     
</SCRIPT>     
</form>
</body>
</html>

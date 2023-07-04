<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Tabla de Sub-Módulos de Clasificación</title>
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

function getParams(currrow,cacti,dacti) {
	document.forms[0].CURRENTROW.value = currrow;
}

function submitThis(option) {
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
	 case  4: 
	    okdel = confirm("Esta opcion borrara todo el lote de transacciones");
    	if ( okdel ) { document.forms[0].submit();}
	      break;
	 case  7: 
		  document.forms[0].SCREEN.value="2";
    	  document.forms[0].submit();
	      break;
	}
 }
 

function checkRowValue() {
  var r = <%= total_row %> + parseInt(document.forms[0].TEMP_ROW.value);
  if (r > <%= max_row %>) {
    alert("Valor de registros fuera de rango numero maximo es 9999");
  }
  else {
    document.forms[0].ROW.value = r + "";
  }
}

</SCRIPT>  

<h3 align="center">Tabla de Sub-Módulos de Clasificación</h3>
<h3 align="center">
<% if (userPO.getHeader2().trim().equals("1")) {;
%>
Corporación
<% }; %>
<% if (userPO.getHeader2().trim().equals("2")) {;
%>
Persona Natural
<% }; %>
<br>
<br>
<%= userPO.getHeader4() %>

<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="submodules_list,EDP0840"></h3>

<HR size="4" width="100%" align="right">

<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0840" >

  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
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
  <p> 
  
  <table class="tbenter" width=100% align=center>
    <tr> 
    <td class=TDBKG width="20%">
        <div align="center">
        <a href="javascript:submitThis(7)"><b>Enviar</b></a>
        </div> 
    </td> 
	 <td class=TDBKG width="20%"> 
        <a href="javascript:submitThis(1)">Borrar Selección</a>
      </td>
	 <td class=TDBKG width="20%"> 
        <a href="javascript:submitThis(2)">Regresar</a>
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
            <th align=CENTER nowrap > <div align="center">Puntos
            </div> </th>
            <th align=CENTER nowrap > <div align="center">Valor Mínimo
            </div> </th>
            <th align=CENTER nowrap > <div align="center">Valor Máximo
            </div> </th>
            <th align=CENTER nowrap > <div align="center">Valor Fijo
            </div> </th>
           </tr>
    </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" NOWRAP>
    <%
		int i = 0;
		int iw = 10;
    %> 

    <table id="dataTable" NOWRAP> 

          <%
	 			trans.initRow();
	 			boolean firstTime = true;
                while (trans.getNextRow()) {
					if (firstTime) {
						firstTime = false;
						iw = iw-10;
					}
		 %>
          <tr> 
	        <td align="center" nowrap > 
		        <input type="checkbox" name="TRANSROW_<%= trans.getCurrentRow() %>"  value="checked">
            </td>
	      	<td align="center" nowrap >  
    	      <input type="text" name="E01PTSSEQ_<%= trans.getCurrentRow() %>"  size="4" maxlength="4"  value="<%= trans.getRecord(1) %>" onkeypress=enterDecimal() readonly> 
      		</td>
	      	<td align="center" nowrap >  
    	      <input type="text" name="E01PTSDSC_<%= trans.getCurrentRow() %>"  size="52" maxlength="50"  value="<%= trans.getRecord(2) %>"  > 
      		</td>
	      	<td align="center" nowrap >  
    	      <input type="text" name="E01PTSPTS_<%= trans.getCurrentRow() %>"  size="10" maxlength="9"  value="<%= trans.getRecord(3) %>" onkeypress=enterSignDecimal(2)>
      		</td>
	      	<td align="center" nowrap >  
    	      <input type="text" name="E01PTSVMN_<%= trans.getCurrentRow() %>"  size="17" maxlength="17"  value="<%= trans.getRecord(4) %>" onkeypress=enterSignDecimal(2)>
      		</td>
	      	<td align="center" nowrap >  
    	      <input type="text" name="E01PTSVMX_<%= trans.getCurrentRow() %>"  size="17" maxlength="17"  value="<%= trans.getRecord(5) %>" onkeypress=enterSignDecimal(2)>
      		</td>
	      	<td align="center" nowrap >  
    	      <input type="text" name="E01PTSVAL_<%= trans.getCurrentRow() %>"  size="16" maxlength="15"  value="<%= trans.getRecord(6) %>" >
      		</td>
          </tr>
    <%
                }
			for ( i=trans.getRow(); i < trans.getCurrentRow() + (iw+5) ; i++) {
    %> 
          <tr> 
		    <td align="center" nowrap  > 
		        <input type="checkbox" name="TRANSROW_<%= i %>"  value="checked">
            </td>
	      	<td align="center" nowrap  >  
    	      <input type="text" name="E01PTSSEQ_<%= i %>" value="<%= i+1 %>" size="4" maxlength="4" onkeypress=enterDecimal() readonly > 
      		</td>
	      	<td align="center" nowrap  >  
    	      <input type="text" name="E01PTSDSC_<%= i %>" size="52" maxlength="50" > 
      		</td>
	      	<td align="center" nowrap  >  
    	      <input type="text" name="E01PTSPTS_<%= i %>"  size="10" maxlength="9" onkeypress=enterSignDecimal(2)>
      		</td>
	      	<td align="center" nowrap  >  
    	      <input type="text" name="E01PTSVMN_<%= i %>"  size="17" maxlength="17" onkeypress=enterSignDecimal(2)>
      		</td>
	      	<td align="center" nowrap  >  
    	      <input type="text" name="E01PTSVMX_<%= i %>"  size="17" maxlength="17" onkeypress=enterSignDecimal(2)>
      		</td>
	      	<td align="center" nowrap  >  
    	      <input type="text" name="E01PTSVAL_<%= i %>"  size="16" maxlength="15" >
      		</td>
          </tr>
    <%
                }
    %> 
         </table>
    <input type="HIDDEN" name="RECNUM" value="<%=i%>">
  </TD>
  </TR>	
  </table>
  <BR>
  <table class="tbenter" width="100%" >
    <tr> 
      <td align="right" nowrap > 
	    <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= i+1 %>">
      </td>
    </tr>
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
<html>
<head>
<title>Listado Detalles de la Garantía</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "collList" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


</head>
<body nowrap >
<%
	 collList.initRow();
   // int blank_row = Integer.parseInt(collList.getIniRow());
    int max_row = 15;
    int row;
    int total_row;
	 int seq = 0;
	 int start = 0;
    try {
      row = Integer.parseInt(request.getParameter("ROW"));
    }
    catch (Exception e) {
      row = 0;
    }
    if ( (row == 0) || (row < collList.getLastRow()) ) {
      total_row = collList.getLastRow() + 1;//+ blank_row;
    }
    else {
		total_row = row;       
    }
	 if ( collList.getNoResult() ) {
   	total_row =  1;
  	   row =  1;
	 }
%> 

<SCRIPT LANGUAGE="JavaScript">
builtNewMenu(colla_M_opt);
initMenu();

</SCRIPT>

<SCRIPT>

function checkRowValue() {
  var r = <%= total_row %> + parseInt(document.forms[0].TEMP_ROW.value);
   if (r > <%= max_row %>) {
    alert("Valor de registros fuera de rango numero maximo es 15");
  }
  else {
    document.forms[0].ROW.value = r + "";
  }
}

function submitThis(option) {
 var delok= false;
   if ( option==1 && parseInt(document.forms[0].TEMP_ROW.value)==0) {
      alert('El Numero de Secuencia a adicionar debe ser mayor que 0');
	}
	else {
          if (option == 3) {
	 	     			delok = confirm("Esta seguro que desea borrar el Detalle de la Garantía seleccionado"); 
	 	      	if ( delok) {
	 	      	document.forms[0].SCREEN.value = '800';
	 	      	document.forms[0].submit()
	 	      	 }
	 	    }
			 else{	    
					document.forms[0].opt.value = option;
					document.forms[0].submit(); 
			}		
 	}
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

 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0010">
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="14">
    <INPUT TYPE=HIDDEN NAME="opt" VALUE="2">
    <INPUT TYPE=HIDDEN NAME="ROW" VALUE="">
  
  <h3 align="center">Listado de Detalles de Garant&iacute;as<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ERA0010_ga_list_detail.jsp" width="35" height="35" ></h3>
  <hr size="4">
  <p align="left"></p>
 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="7%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E01ROCCUN" size="9" maxlength="9" value="<%=userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="15%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap width="37%" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01RODCUN" size="45" maxlength="45" value="<%= userPO.getCusName().trim()%>" >
                </font></font></font></div>
            </td>
            <td nowrap width="11%" > 
              <div align="right">Referencia:</div>
            </td>
            <td nowrap width="21%" > 
              <input type="text" name="E02RODREF" size="9" maxlength="9" value="<%= userPO.getIdentifier().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <BR>
  <table class="tableinfo" >
    <tr id="trdark"> 
      <th nowrap width="3%" >&nbsp;</th>
      <th align="center" nowrap >No Sec</th>
      <th align="center" nowrap > No Registro<br>
        Mercantil</th>
      <th align="center" nowrap > Descripcion Garantia</th>
      <th align="center" nowrap > Monto </th>
      <th align="center" nowrap > Fecha de Vcto</th>
    </tr>
   		<%
   			if ( !collList.getNoResult() ) {
   				 collList.initRow();
                while (collList.getNextRow()) {
	      %> 
    <tr id="trclear"> 
      <td align="center" nowrap width="3%"> 
        <input type="radio" name="COLLROW" value="<%= collList.getRecord(0) %>">
      </td>
      <td align="center" nowrap > 
        <input type="text"  name="E02RODSEQ_<%= collList.getCurrentRow() %>" size="2" maxlength="2"   readonly value="<%= collList.getRecord(0) %>" id="txtright">
      </td>
      <td align="center" nowrap > 
        <input type="text" name="E02RODNBR_<%= collList.getCurrentRow() %>" size="4" maxlength="4" onKeypress="enterInteger()" value="<%= collList.getRecord(1) %>" >
      </td>
      <td align="center" nowrap> 
        <input type="text" name="E02RODDSC_<%= collList.getCurrentRow() %>" size="35" maxlength="35" value="<%= collList.getRecord(2) %>" >
      </td>
      <td align="center" nowrap > 
        <input type="text" name="E02RODAMT_<%= collList.getCurrentRow() %>" size="17" maxlength="17" value="<%= collList.getRecord(3) %>" onKeypress="enterDecimal()" >
      </td>
      <td align="center" nowrap> 
        <input type="text" name="E02RODMD1_<%= collList.getCurrentRow() %>" size="2" maxlength="2" value="<%= collList.getRecord(4) %>">
        <input type="text" name="E02RODMD2_<%= collList.getCurrentRow() %>" size="2" maxlength="2" value="<%= collList.getRecord(5) %>" >
        <input type="text" name="E02RODMD3_<%= collList.getCurrentRow() %>" size="2" maxlength="2" value="<%= collList.getRecord(6) %>">
      </td>
    </tr>
    <%
             }
             collList.setCurrentRow(collList.getLastRow());
             seq= Integer.parseInt(collList.getRecord(0));     
             start=collList.getRow();
       }
       for (int i=start; i < total_row; i++) {
	          seq++;
    %> 
    <tr id="trclear"> 
      <td align="center" nowrap width="3%" > 
        <input type="radio" name="COLLROW" value="<%=seq %>">
      </td>
      <td align="left" nowrap width="6%" > 
        <div align="center">
          <input type="text" name="E02RODSEQ_<%= i %>" size="2" maxlength="2"   readonly  id="txtright" value="<%=seq %>" >
        </div>
      </td>
      <td align="left" nowrap width="9%" > 
        <div align="center">
          <input type="text" name="E02RODNBR_<%= i %>" size="4" maxlength="4" onKeypress="enterInteger()" id="txtleft" value="">
        </div>
      </td>
      <td align="left" nowrap width="28%" > 
        <div align="center">
          <input type="text" name="E02RODDSC_<%= i %>" size="35" maxlength="35" value="" id="txtleft" >
        </div>
      </td>
      <td align="left" nowrap width="14%"> 
        <div align="center">
          <input type="text" name="E02RODAMT_<%= i %>" size="17" maxlength="17" value="" id="txtleft"  onKeypress="enterDecimal()">
        </div>
      </td>
      <td align="left" nowrap width="13%"> 
        <div align="center">
          <input type="text" name="E02RODMD1_<%= i %>" size="2" maxlength="2" value="" id="txtleft">
          <input type="text" name="E02RODMD2_<%= i %>" size="2" maxlength="2" value="" id="txtleft">
          <input type="text" name="E02RODMD3_<%= i %>" size="2" maxlength="2" value="" id="txtleft">
        </div>
      </td>
    </tr>
    <%
        }
     %> 
  </table>
  <BR>
  <table  class="tbenter">
    <tr > 
      <td width="37%" height="52" nowrap> 
        <div align="right">Cantidad de Registros a A&ntilde;adir : 
          <input type="text" name="TEMP_ROW" size="4" maxlength="3" value="0" onKeyPress="enterInteger()" onBlur="checkRowValue()">
        </div>
      </td>
      <td width="10%" height="52" nowrap><a href="javascript:submitThis(1)"><img src="<%=request.getContextPath()%>/images/s/applicar_on.gif" alt="aplicar" align="absmiddle" border="0" ></a></td>
      <td  width="44%" height="52" nowrap> 
        <div align="right">Elimina Registro Seleccionado: </div>
      </td>
      <td  width="9%" height="52" nowrap><a href="javascript:submitThis(3)"><img src="<%=request.getContextPath()%>/images/s/delete_over.gif" alt=" borrar" align="absmiddle" border="0" ></a></td>
    </tr>
  </table>
    
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
  </form>
</body>
</html>

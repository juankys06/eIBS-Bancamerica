<HTML>
<HEAD>
<TITLE>
Lista de Cheques a Imprimir
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<jsp:useBean id= "dvList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">

function enter(accnum , currency) {
   document.forms[0].SCREEN.value = "200";
   document.forms[0].E01OFMCKN.value = accnum;
   document.forms[0].E01OFMCCY.value = currency;
   document.forms[0].submit();
}

function changeChecked(field){

	for (i = 0; i < field.length; i++)
		field[i].checked = document.forms[0].hcheck.checked ;
}

function submitForm(){

	var anyChecked = false ;
	var checks = document.forms[0].ACCNUM ;

	if( typeof(checks.length)=="undefined" ){ //If true there is only one item in the list
		if( checks.checked == true ){
			anyChecked = true ;
		}
	}else{ //There is more than one item in the list, in this case the items must be accessed by an array
		for(i=0 ; i < checks.length ; i++ ){
			if( checks[i].checked == true ){
				anyChecked = true ;
				break ;
			}
		}
	
	}
	
	
	if( ! anyChecked ){
		alert("No existe ningun cheque seleccionado.");
		return false ;
	}else{
	    document.forms[0].SCREEN.value = "500";
		document.forms[0].submit();
		return true;
	}
}


</script>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY onload="">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115P" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">
<INPUT TYPE=HIDDEN NAME="E01OFMCCY" VALUE="">
<INPUT TYPE=HIDDEN NAME="E01OFMCKN" VALUE="">


  <h3 align="center"> Impresi&oacute;n de Cheques de Gerencia   <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="approval_list.jsp,EOF0115"> 
  </h3>
<hr size="4">

<%
	if ( dvList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center">
        		<font size="3"><b>
        				No hay resultados que correspondan a su criterio de búsqueda 
        		</b></font>
        	</div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>
  
  <TABLE class="tbenter" width="50%">
    <TR> 
      <TD ALIGN=CENTER >
			<input id="EIBSBTN" type=button name="Submit" value="Imprimir" onclick="return submitForm();" >
	  </TD>
      <TD ALIGN=CENTER >&nbsp;</TD>
      <TD ALIGN=CENTER >
      	<input id="EIBSBTN" type="button" name="button" value="Salir" onClick="javascript:location.href='<%=request.getContextPath()%>/pages/background.jsp'">
      </TD>
    </TR>
  </TABLE>
        
  
  <table class="tableinfo">
    <tr id="trdark">
      <th align=CENTER  nowrap width="1%">
      	<INPUT type="checkbox" name="hcheck"
      		onclick="javascript:changeChecked(document.forms[0].ACCNUM)" />
      </th>
      <th align=CENTER  nowrap width="14%">Referencia</th>
      <th align=CENTER  nowrap width="6%">Moneda</th>
      <th align=CENTER  nowrap width="6%">Sucursal</th>
      <th align=CENTER  nowrap width="7%">Monto</th>
      <th align=CENTER  nowrap width="5%"> 
        <div align="right">Estado</div>
      </th>
      <th align=CENTER  nowrap width="6%"> 
        <div align="center">Emitido</div>
      </th>
      <th align=CENTER  nowrap width="55%"> 
        <div align="center">Beneficiario</div>
      </th>
    </tr>
    <%
                dvList.initRow();
                while (dvList.getNextRow()) {
                    if (dvList.getFlag().equals("")) {
                    		out.println(dvList.getRecord());
                    }
                }
              %> 
  </table>
  <%
  }
%>

</FORM>

</BODY>
</HTML>

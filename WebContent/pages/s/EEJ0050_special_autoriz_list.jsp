<html> 
<head>
<title>Lista de Autorizaciones Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "autList" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
var reason = '';

function callPosition() {
var customer = document.forms[0].Cun.value;    

    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=5&CUSTOMER="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);
}





function goAction(option) {

	if (option == "A") {
    	document.forms[0].SCREEN.value = "200";
    	document.forms[0].option.value = option;	
  	} else {
  		if (option == "D") {
    	document.forms[0].SCREEN.value = "100";
    	document.forms[0].option.value = option;
    	//
  	    	} else {
  			if (option == "M") {
    		document.forms[0].SCREEN.value = "110";
    		document.forms[0].option.value = option;
    	//
	    	} else {
  			if (option == "H") {
    		document.forms[0].SCREEN.value = "300";
    		document.forms[0].option.value = option;
    		    	} else {
  			if (option == "P") {
  			document.forms[0].option.value = option;
  			callPosition();
    		} else {
  				if (option == "R") {
  					document.forms[0].SCREEN.value = "200";
    				document.forms[0].option.value = option;
  		
  	   				var len = reason.length;
  	   
  	   				if (len > 105) {
  	      			document.forms[0].E01EJARM1.value = reason.substring(0,35);
  	     			document.forms[0].E01EJARM2.value = reason.substring(35,70);
  	     			document.forms[0].E01EJARM3.value = reason.substring(71,105);
  	   				} else if (len > 70) {
  	      			document.forms[0].E01EJARM1.value = reason.substring(0,35);
  	      			document.forms[0].E01EJARM2.value = reason.substring(35,70);
  	      			document.forms[0].E01EJARM3.value = reason.substring(71,len);
  	      			} else if (len > 35) {
  	      			document.forms[0].E01EJARM1.value = reason.substring(0,35);
  	      			document.forms[0].E01EJARM2.value = reason.substring(35,len);
  	   				} else {
  	      			document.forms[0].E01EJARM1.value = reason;
  	      			}
  	      		}
  	      	}
  	    }
  	}
	}
	}
	
	if (option != "P")
	{
	document.forms[0].submit();
	}
}

function setParameters(sup,ref,cun) {
    document.forms[0].Sup.value = sup;
    document.forms[0].Ref.value = ref;
    document.forms[0].Cun.value = cun;
}

</script>  

</head>

<body>
<h3 align="center">Aprobaci&oacute;n de Autorizaciones Especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="special_autoriz_list.jsp, EEJ0050"></h3>
<hr size="4">
<form name="form1" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEEJ0050" >
  <input type=hidden name="SCREEN" value="100">
  <input type=hidden name="option" value="">
  <input type=hidden name="totalRow" value="0">
  <input type=hidden name="Row" value="0">
  <input type=hidden name="Sup" value="<%= userPO.getHeader10()%>">
  <input type=hidden name="Ref" value="<%= userPO.getHeader11()%>">
  <input type=hidden name="Cun" value="<%= userPO.getHeader12()%>">
  <input type=hidden name="E01EJARM1" value="">
  <input type=hidden name="E01EJARM2" value="">
  <input type=hidden name="E01EJARM3" value=""> 
 
  <%if ( autList.getNoResult() ) {%>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su criterio de busqueda</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
              <td class=TDBKG>
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salida</b></a></div>
              </td>
           </tr>
         </table>
	  </div>

	  </TD>
	</TR>
    </TABLE>
  <%} else { 
	  if ( !error.getERRNUM().equals("0")  ) {
	     error.setERRNUM("0");
	     out.println("<SCRIPT Language=\"Javascript\">");
	     out.println("       showErrors()");
	     out.println("</SCRIPT>");
	     }
  %> 
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="15%"> 
        <div align="center"><a href="javascript:goAction('D')"><b>Detalle</b></a></div>
      </td>
      <td class=TDBKG width="15%"> 
        <div align="center"><a href="javascript:goAction('M')"><b>Cambiar Tasa</b></a></div>
      </td>
      
      <td class=TDBKG width="15%"> 
        <div align="center"><a href="javascript:goAction('H')"><b>Historia</b></a></div>
      </td>
      <td class=TDBKG width="15%"> 
        <div align="center"><a href="javascript:goAction('A')"><b>Aprobar</b></a></div>
      </td>
      <td class=TDBKG width="15%"> 
        <div align="center"><a href="javascript:enterReason('R')"><b>Rechazar</b></a></div>
      </td>
      <td class=TDBKG width="15%"> 
      <div align="center"><a href="javascript:goAction('P')"><b>Posicion del Cliente</b></a></div>
      </td>
      
      <td class=TDBKG width="15%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salida</b></a></div>
      </td>
    </tr>
  </table>
  <br>
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="5%">&nbsp;</th>
            <th align=CENTER nowrap>Ejecutivo<BR>Solicita</th>
            <th align=CENTER nowrap> 
              <div align="center">Referencia</div>
            </th>
            <th align=CENTER nowrap> 
            <div align="center">Tasa<BR>Solicitada</div>
            </th>
            <th align=CENTER nowrap> 
              <div align="center">Cliente</div>
            </th>
            <th align=CENTER nowrap> 
              <div align="center">Producto</div>
            </th>
            <th align=CENTER nowrap> 
              <div align="center">Operaci&oacute;n</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Porcentaje<BR>Aprobado</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Valor<BR>Aprobado</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Status</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Tipo de<BR>Aprobaci&oacute;n</div>
            </th>
          </tr>
          <%
                autList.initRow();
				int k=1;
                while (autList.getNextRow()) {
                 
                  out.println(autList.getRecord());
                  k++;   
                }
              %>
        </table>
    </table>
  <%}%>
</form>
</body>
</html>

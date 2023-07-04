<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<html>
<head><title>Tesoreria </title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<jsp:useBean id= "EWD0334Help" class= "datapro.eibs.beans.JBList"   scope="session"/>

<SCRIPT language="JavaScript">
function showInqPlp(ref)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=400&E01DEAACC=" + ref;
	CenterWindow(page,560,500,2);
}

  function goAction(op) {

     var formLength= document.forms[0].elements.length;
     var ok = false;
	 var acc = "";
	 var pg = "";
     for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ACC") 
      	{
				if (document.forms[0].elements[n].checked == true) {
					acc = document.forms[0].elements[n].value;
        			ok = true;
        			break;
				}
      	}
      }
      if ( ok ) {
      	document.forms[0].ACCOUNT.value = acc;	
      	document.forms[0].submit();
      
     }
     else {
			alert("Por favor, seleccione una cuenta para continuar.");	   
     }

  }
</SCRIPT>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130">

  <h3 align="center">Cancelación - Renovación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cancel_renov_list, EWD0334"> 
  </h3>
<hr size="4">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="ACCOUNT" value="">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="59">
  <%
	if ( EWD0334Help.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        <div align="center"> <b> No hay resultados que correspondan con su criterio 
          de b&uacute;squeda</b></div>
      </TD></TR>
   		</TABLE>
<%  
		}
	else {
%>	
   
 <TABLE class="tbenter" width="100%">
    <TR> 
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goAction()" ><b>Cancelar/<br>Renovar</b></a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="<%=request.getContextPath()%>/pages/background.jsp" ><b>Salir</b></a> 
      </TD>
    </TR>
  </TABLE>
  <center>
<TABLE  id="mainTable" class="tableinfo">
<TR > 
    <TD NOWRAP valign="top"  >
        <TABLE id="headTable"  >
          <TR id="trdark"> 
          	<th align=CENTER nowrap></th>
            <th align=CENTER nowrap>N&uacute;mero de <BR>
              Cuenta</th>
            <th align=CENTER nowrap>Contraparte</th>
            <th align=CENTER nowrap>C&oacute;digo de <BR>
              Producto</th>
            <th align=CENTER nowrap>Tipo</th>
            <th align=CENTER nowrap> Monto</th>
            <th align=CENTER nowrap>Fecha<br>
              Valor</th>
            <th align=CENTER nowrap>Estado</th>
            <th align=CENTER nowrap>Fecha<br>Vencimiento</th>
          </tr>
        </table>
  		<div id="dataDiv1" class="scbarcolor" >
    		<table id="dataTable" align="center">
  <%
                EWD0334Help.initRow();
				int k = 0;
                while (EWD0334Help.getNextRow()) {
                    if (EWD0334Help.getFlag().equals("")) {
                    		out.println(EWD0334Help.getRecord());
							k ++;
                    }
                }
    %> 
			</TABLE>

		</div>
		</td>
	</tr>
</table>
 </center>
<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize();
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


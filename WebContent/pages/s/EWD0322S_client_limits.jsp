<html>
<head>
<title>Customer Limits</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "limPos" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
  function goAction(op) {

		
		  if(document.forms[0].date1.value == '' && op==2){
             alert("Un cliente válido debe ser seleccionado"); 
             return; 
          }
		  if(document.forms[0].date1.value == '' && op==3){
             alert("Un cliente válido debe ser seleccionado"); 
             return; 
          }

        if(op==1){
		 document.forms[0].opt.value = 1;
        }  
		else if(op==2){
		 document.forms[0].opt.value = 2;
        }  
		if(op==3){
		 document.forms[0].opt.value = 3;
        }  
		document.forms[0].submit();

  }

</SCRIPT>  

<script language="JavaScript">
  function getValor(Customer,Date1,Date2,Date3) {

    document.forms[0].cust.value = Customer;
	document.forms[0].date1.value = Date1;
	document.forms[0].date2.value = Date2;
    document.forms[0].date3.value = Date3;
  }

</SCRIPT>  

<script language="JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v3.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
</head>

<BODY onLoad="MM_preloadImages('<%=request.getContextPath()%>/images/s/new_over.gif','<%=request.getContextPath()%>/images/s/maintenance_over.gif','<%=request.getContextPath()%>/images/s/exit_over.gif','<%=request.getContextPath()%>/images/s/delete_over.gif')">
<h3 align="center">Mantenimiento de Límites de Clientes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EWD0322S_client_limits.jsp, EWD0322S"></h3>
<hr size="4">
  <form>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
    
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
  <INPUT TYPE=HIDDEN NAME="opt">
  <input type=HIDDEN name="cust" value="<%= userPO.getCusNum()%>" >
  <input type=HIDDEN name="date1">
  <input type=HIDDEN name="date2">
  <input type=HIDDEN name="date3">
  <%
	if ( limPos.getNoResult() ) {
 %> 
  <TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        
      <div align="center"> 
          <p><font size="3"><b> No hay resultados que correspondan 
          con su criterio de b&uacute;squeda </b></font></p>
          <table class="tbenter" width="100%">
            <tr> 
              <td align=CENTER> <a href="javascript:goAction(1)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image11','','<%=request.getContextPath()%>/images/s/new_over.gif',1)"><img name="Image11" border="0" src="<%=request.getContextPath()%>/images/s/new.gif" ></a></td>
              <td align=CENTER> <a href="<%=request.getContextPath()%>/pages/background.jsp" onClick="top.close()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image52','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image52" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a></td>
            </tr>
          </table>
          <p>&nbsp; </p>
        </div>
      </TD></TR>
   		</TABLE>
  <%   		
	}
	else {
%> 
  <p>
<h4>L&iacute;mites Establecidos</h4>
  <table class="tbenter" width="100%">
    <tr> 
      <td align=CENTER> <a href="javascript:goAction(1)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/new_over.gif',1)"><img name="Image1" border="0" src="<%=request.getContextPath()%>/images/s/new.gif" ></a></td>
      <td align=CENTER> <a href="javascript:goAction(2)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/maintenance_over.gif',1)"><img name="Image2" border="0" src="<%=request.getContextPath()%>/images/s/maintenance.gif" ></a> 
      </td>
      <td align=CENTER><a href="javascript:goAction(3)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image51','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image51" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a></td>
      <td align=CENTER> <a href="<%=request.getContextPath()%>/pages/background.jsp" onClick="top.close()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a></td>
    </tr>
  </table>
  <center>
    <table class="tableinfo">
      <tr id="trclear"> 
        <th align=CENTER nowrap >&nbsp;</th>
        <th align=CENTER nowrap > 
          <div align="center">Cliente</div>
        </th>
        <th align=CENTER nowrap > 
          <div align="center">Fecha<br>Valor</div>
        </th>
        <th align=CENTER nowrap > 
          <div align="center">Moneda</div>
        </th>
        <th align=CENTER nowrap > 
          <div align="center">Monto<BR>
		Aprobado</div>
        </th>
        <th align=CENTER nowrap > 
          <div align="center">Monto<BR>
		Usado</div>
        </th>
        <th align=CENTER nowrap > 
          <div align="center">Monto<BR>
		Disponible</div>
        </th>
      </tr>
      <tr> <%
                limPos.initRow();
                while (limPos.getNextRow()) {
                 
                    		out.println(limPos.getRecord());
                    
                }
              %> </tr>
    </table>
  </center>
  <%
     }   
  %> 
 
</form>
</body>
</html>

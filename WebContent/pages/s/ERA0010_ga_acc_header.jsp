<HTML>
<HEAD>
<TITLE>
Listado de Garantías
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</HEAD>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
 function goAction(op) {
 var delok= false;

  	with  ( parent.results ){  
    	    	if (op == 1) {
    	    	    document.forms[0].SCREEN.value = '300';
          		document.forms[0].submit();
	 	     }
	 	      if (op == 2) {
	 	     			delok = confirm("Esta seguro que desea borrar la Garantía seleccionada"); 
	 	      	if ( delok) {
	 	      	document.forms[0].SCREEN.value = '600';
	 	      	document.forms[0].submit()
	 	      	 }
	 	    }      
  	}
 }
  function goExit(){
  parent.window.location.href="<%=request.getContextPath()%>/pages/background.jsp";

  }
</script>

<BODY onload="MM_preloadImages('<%=request.getContextPath()%>/images/s/approve_over.gif','<%=request.getContextPath()%>/images/s/delete_over.gif','<%=request.getContextPath()%>/images/s/nueva_over.gif')">
<h3 align="center"> Lista de Garantías de No-Dep&oacute;sito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ga_acc_list.jsp,ERA0010"> 
</h3>
<hr size="4">
  
  
<TABLE class="tbenter">
  <TR> 
    <TD ALIGN=CENTER width="25%"> <a href="javascript:goAction('1')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/nueva_over.gif',1)"><img name="Image1" alt="Nuevo" border="0" src="<%=request.getContextPath()%>/images/s/nueva.gif" ></a> 
    </TD>
    <TD ALIGN=CENTER width="25%"><a href="javascript:goGaBasic()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image11','','<%=request.getContextPath()%>/images/s/maintenance_over.gif',1)"><img name="Image11" alt="Modificar" border="0" src="<%=request.getContextPath()%>/images/s/maintenance.gif" ></a>
    </TD>
    <TD ALIGN=CENTER width="25%"> <a href="javascript:goAction('2')" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image3','','<%=request.getContextPath()%>/images/s/delete_over.gif',1)"><img name="Image3" alt="Borrar" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a> 
    </TD>
    <TD ALIGN=CENTER width="25%"> <a href="javascript:goExit()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)"><img name="Image5" alt="Salir" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a> 
    </TD>
  </TR>
</TABLE>
        
 </BODY>
</HTML>

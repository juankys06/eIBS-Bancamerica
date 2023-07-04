<html> 
<head> 
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" 	scope="session" />
<jsp:useBean id="balance" class="datapro.eibs.beans.EGL075001Message" scope="session" />


<script language="JavaScript">
function enter(){
	  document.forms[0].submit();
	 }
</script>
<SCRIPT language="JavaScript">

document.onkeyup="";
 
function typeClick(fBanco,fSucursal,fClase,fMoneda,fTipo,fDescripcion,
                   fCentroCosto,fCodigoContable, fNivel){
    
    showdiv("fieldArea");
      
    if (fBanco)
      showdiv("banco");
    else
      hidediv("banco");
      
    if (fSucursal)
      showdiv("sucursal");
    else
      hidediv("sucursal");                  
   
    if (fClase )
      showdiv("clase");
    else
      hidediv("clase");
     
    if (fMoneda)
      showdiv("moneda");
    else
      hidediv("moneda"); 
   
    if (fTipo )
      showdiv("tipo");
    else
      hidediv("tipo");
      
    if (fDescripcion )
      showdiv("descripcion");
    else
      hidediv("descripcion"); 
     
    if (fCentroCosto )
      showdiv("centroCosto");
    else
      hidediv("centroCosto");  
      
    if (fCodigoContable)
      showdiv("codigoContable");
    else
      hidediv("codigoContable");
      
    if (fNivel)
      showdiv("nivel");
    else
      hidediv("nivel");        
    
}

function hidediv(id) {
	//safe function to hide an element with a specified id
	if (document.getElementById) { // DOM3 = IE5, NS6
		document.getElementById(id).style.display = 'none';
	}
	else {
		if (document.layers) { // Netscape 4
			document.id.display = 'none';
		}
		else { // IE 4
			document.all.id.style.display = 'none';
		}
	}
}

function showdiv(id) {
	//safe function to show an element with a specified id
		  
	if (document.getElementById) { // DOM3 = IE5, NS6
		document.getElementById(id).style.display = 'block';
	}
	else {
		if (document.layers) { // Netscape 4
			document.id.display = 'block';
		}
		else { // IE 4
			document.all.id.style.display = 'block';
		}
	}
}
/*
function enter(firsTime){
 
 
 //var NameSearch = document.forms[0].NameSearch.value;
 
 var Type ="A";
 	
	for(var i = 0; i < document.forms[0].Type.length; i++)
	{
		if (document.forms[0].Type[i].checked)
			Type = document.forms[0].Type[i].value;
	}
		
	parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.accounting.JSEGL0750";	
 }
*/
</SCRIPT>
</head>
<body>

 
 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
    }
%>
<!-- <FORM Action="javascript:enter(document.forms[0].NameSearch.value)"> -->
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.accounting.JSEGL0750" >
 
 
 
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  
  <h3 align="center">Consulta de Balances<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inq_enter1.jsp,EGL0750"> 
  </h3>
  <hr size="4">
<p></p>


   <table  id="display" class="TABLEINFO" align="center" >
     <tr>
     
      <td width="35%">
    <div id="searchBox" >
      <div class="ROW" align="left" >
	    Buscar Por
	  </div>
      <div class="ROW" align="left" >
	    <input type="radio" name="Type" value="A" onclick="typeClick(true,true,false,true,false,false,false,false,false)" checked>Banco/Sucursal/Moneda
	  </div>	
	  <div class="ROW" align="left" >
		<input type="radio" name="Type" value="B" onclick="typeClick(true,true,true,false,false,false,false,false,false)">Banco/Sucursal/Clase
	  </div>
	  <div class="ROW" align="left" >
		<input type="radio" name="Type" value="C" onclick="typeClick(true,true,true,true,false,false,false,false,false)">Banco/Sucursal/Clase/Moneda
	  </div>
	  <div class="ROW" align="left" >
		<input type="radio" name="Type" value="D" onclick="typeClick(true,true,true,false,true,false,false,false,false)">Banco/Sucursal/Clase/Tipo
	  </div>
	  <div class="ROW" align="left" >
		  	<input type="radio" name="Type" value="E" onclick="typeClick(true,true,true,true,true,false,false,false,false)">Banco/Sucursal/Clase/Moneda/Tipo
	  </div>
	  <div  class="ROW"align="left" >
		  	<input type="radio" name="Type" value="F" onclick="typeClick(true,true,false,false,false,true,false,false,false)">Banco/Sucursal/Descripcion
	  </div>
	  <div class="ROW" align="left" >
		  	<input type="radio" name="Type" value="G" onclick="typeClick(true,true,false,false,false,false,true,false,false)">Banco/Sucursal/Centro de Costo
	  </div>
	  <div class="ROW" align="left" >
		  	<input type="radio" name="Type" value="H" onclick="typeClick(true,true,false,false,false,false,false,true,false)">Banco/Sucursal/Codigo Contable
	  </div>
	  <div class="ROW" align="left" >
		  	<input type="radio" name="Type" value="I" onclick="typeClick(true,true,false,true,false,false,false,false,true)">Banco/Sucursal/Moneda/Nivel
	  </div>
	  <div  class="ROW" align="left" >
		  	<input type="radio" name="Type" value="J" onclick="typeClick(true,true,true,true,false,false,false,false,true)">Banco/Sucursal/Moneda/Clase/Nivel
	  </div>
	</div>
	</td>
	
	<td width="35%" align="left">

     <div id="fieldArea" class="GROUP"  >
         
    <div id="banco">
      <span style="width:100px; " >
        Banco:
       </span>
       <input type="text" name="E01GLBBNK" size="3" maxlength="2">
     </div>
    
    <div id="sucursal">
      <span style="width:100px; " >
        Sucursal:
       </span>
        <input type="text" name="E01GLBBRN" size="4" maxlength="3" > 
          <a href="javascript:GetBranch('E01GLBBRN','')">
             <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"  >
          </a>
     </div>
     
     <div id="moneda"> 
       <span style="width:100px; " >
         Moneda:
       </span>     
         <input type="text" name="E01GLBCCY" size="4" maxlength="3" >
         <a href="javascript:GetCurrency('E01GLBCCY','')">
           <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" >
         </a>
     </div>
     
     <div id="clase" style="display:none">  
      <span style="width:100px; " >
        Clase:
       </span>   
	      <input type="text" name="E01GLBCLS" size="3" maxlength="2"  >
          <a href="javascript:GetCodeDescCNOFC('E01GLBCLS','','01')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0">
          </a> 
     </div>
     
     <div id="tipo" style="display:none">  
       <span style="width:100px; " >
         Tipo:
       </span>     
         <input type="text" name="E01GLBATY" size="5" maxlength="4"  >
          <a href="javascript:GetCodeDescCNOFC('E01GLBATY','','04')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0">
          </a>
     </div>
     
     <div id="descripcion" style="display:none">
       <span style="width:100px; " >
        Descripci&oacute;n:
       </span>
      <input type="text" name="E01GLBDSC" size="30" maxlength="30"  >
     </div>
     
     <div id="centroCosto" style="display:none">
     <span style="width:100px; " >
        Centro de Costo:
       </span>
          <input type="text" name="E01GLBCCN" size="7" maxlength="6"  >
          <a href="javascript:GetCostCenter('E01GLBCCN','01')">
             <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0" >
          </a>
     </div>
     
     <div id="codigoContable" style="display:none"> 
     <span style="width:100px; " >
        C&oacute;digo Contable:
       </span>     
         <input type="text" name="E01GLBGLN" size="17" maxlength="16"  >
          <a href="javascript:GetLedger('E01GLBGLN',document.forms[0].E01GLBBNK.value,document.forms[0].E01GLBCCY.value,'')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" >
          </a> 
      </div>
      
      <div id="nivel" style="display:none"> 
      <span style="width:100px; " >
        Nivel:
       </span>  
          <select name="E01GLBNIV">
				<option value=" " <%if (balance.getE01GLBNIV().equals(" ")) out.print("selected"); %>>Ninguno</option>
                <option value="1" <%if (balance.getE01GLBNIV().equals("1")) out.print("selected"); %>>Nivel 1</option>
                <option value="2" <%if (balance.getE01GLBNIV().equals("2")) out.print("selected"); %>>Nivel 2</option>
                <option value="3" <%if (balance.getE01GLBNIV().equals("3")) out.print("selected"); %>>Nivel 3</option>
                <option value="4" <%if (balance.getE01GLBNIV().equals("4")) out.print("selected"); %>>Nivel 4</option>
                <option value="5" <%if (balance.getE01GLBNIV().equals("5")) out.print("selected"); %>>Nivel 5</option>
                <option value="6" <%if (balance.getE01GLBNIV().equals("6")) out.print("selected"); %>>Nivel 6</option>
                <option value="7" <%if (balance.getE01GLBNIV().equals("7")) out.print("selected"); %>>Nivel 7</option>
                <option value="8" <%if (balance.getE01GLBNIV().equals("8")) out.print("selected"); %>>Nivel 8</option>
          </select>
      </div>    
   </div>
  </td>
  <td></td>
  </tr> 
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>

<%--  
  <SCRIPT Language="Javascript">;

     function resizeDoc() {
       for(var i = 0; i < document.forms[0].Type.length; i++)
		{
		if (document.forms[0].Type[i].checked)
			document.forms[0].Type[i].click();
		}
     }
     
     window.onresize=resizeDoc;     
     enter(true);
     
</SCRIPT>   
--%> 
</form>
</body>
</html>

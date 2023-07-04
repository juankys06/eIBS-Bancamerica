<html>
<head>
<title>Esquema de Pagos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "pmntPlus" class= "datapro.eibs.beans.JBListRec"  scope="session" />

</head>
<body nowrap>
<%
	int row;  
	if (pmntPlus.getNoResult()) {    
      row = 0;
    }
    else {
      row = pmntPlus.getLastRow() + 1;         
    }
%> 

<SCRIPT Language="JavaScript">

function checkRowValue() {
  if (trim(document.forms[0].TEMP_ROW.value) !="") {
   var r = parseInt(document.forms[0].TEMP_ROW.value);
   if (r > 30 ) {
    	alert("Valor fuera de limites, el numero maximo es 30.");
   } else if (r == 0) {
   		document.forms[0].TEMP_ROW.value = 1;
   }
  } else document.forms[0].TEMP_ROW.value = 1;
}

function MoveElements(posIni,posEnd){   
  try {
    document.forms[0].elements("DLPPNU_" + posEnd).value = document.forms[0].elements("DLPPNU_" + posIni).value;
    document.forms[0].elements("DLPPRI_" + posEnd).value = document.forms[0].elements("DLPPRI_" + posIni).value;
  } catch(e){}      
 }
 
 //Rename the fields de una specific table dependiendo del flag. If the field's sufix is different  a la row donde esta,
 // then se renombra
 function UpdateTable(tableName,chkboxName,noRow){  
   var maxRow;
   var restRow;
    try {
        var myTable = document.all[tableName];
     	maxRow = myTable.rows.length;
     	restRow = maxRow - noRow;
     	for (i= restRow; i < maxRow;i++) {
     	  myTable.deleteRow(restRow);
      	}     	
     	if (restRow > 1) {
     	   var myChkB = document.forms[0][chkboxName];     	   
     	   if (myChkB.length == null) {
     	   		myChkB.checked = false;
     	   }
     	   else{
     	   	  for (j=0; j < myChkB.length ;j++) {     	      	 
     	      	myChkB[j].checked = false;
     	   	  }
     	   }
     	}     	
    }
    catch(e) {
    }
  }
  
  function DeleteRows(tableName,chkboxName){  
   var maxrow;
   var noRow = 0;
   var firstTime = true;
   var okdel=false;
    
    try {
        var myTable = document.all[tableName];
     	maxrow = myTable.rows.length;
     	if (maxrow > 1) {
     	   var myChkB = document.forms[0][chkboxName];
     	   if (myChkB.length == null) {
     	   		if (myChkB.checked){
     	      	 		if (firstTime) {
     	      				okdel = confirm("Todos los pagos seleccionados se borraran");
    						if ( !okdel ) return(noRow);
     	      	 		}
     	         		myTable.deleteRow(1);
     	         		return(noRow);
     	      		}
     	   } 
     	   else {     	   
     	   			     	      
     	      		for (i=0; i < myChkB.length; i++) {     	   			
     	      			if (myChkB[i].checked){
     	      	 			if (firstTime) {
     	      					okdel = confirm("Todos los pagos seleccionados se borraran");
    							if ( !okdel ) break;
    							firstTime = false;
    						}
    						noRow++;   	         		     	         		
     	         		} else {
     	         	    	if (noRow > 0) {
     	         	    	     var row = i+1;     	         	     
     	         		 		 MoveElements(row,row - noRow);     	         		 
     	         			} 
     	         		}	 
     	      		}
     	      		return(noRow);
     	      		
     	      		
     	   }
     	} else return(noRow);
    }
    catch(e) {
       return(noRow);
    }
  }
  
 //Esta funciton se llama en el click del button de borrado
  function DeleteRowSel(tableName,chkboxName){
    var noRow = DeleteRows(tableName,chkboxName);  
    if ( noRow > 0 ){
    	UpdateTable(tableName,chkboxName,noRow);    
    	document.forms[0].ROW.value = "" + document.all[tableName].rows.length -1;
    }
  }
  
 //adiciona una row a una tabla  
  function AddRow(tableName,nRow){  
   var maxcell =6;
   var idxrow;
   var okdel=false;
   var sufix="";
    try {
      var myTable = document.all[tableName];
      if (myTable.rows.length < 31) {
        myTable.insertRow();
     	idxrow = myTable.rows.length -1;
     	sufix="" + idxrow;
     	
     	for(i=0; i<maxcell; i++) {
          myTable.rows[idxrow].insertCell();
          myTable.rows[idxrow].cells[i].align = "center";
          
       	}       	
       	myTable.rows[idxrow].cells[0].innerHTML = "<input type='checkbox' name='ROWSEL' value='" + sufix + "'>";
      	
      	myTable.rows[idxrow].cells[1].innerHTML = "<input type='text' name='DLPPNU_" + sufix + "' id='MANDATORY' size='4' maxlength='3'  value='" + idxrow + "'>";
         
        myTable.rows[idxrow].cells[2].innerHTML = "<input type='text' name='DLPPRI_" + sufix + "' id='MANDATORY' size='17' maxlength='15' value='' style ='text-align: right' onKeypress='enterDecimal()'>";

     	myTable.refresh();
        document.forms[0]["DLPPRI_" + sufix ].focus();
        if (nRow > 1) AddRow(tableName,nRow -1); else document.forms[0].ROW.value = "" + idxrow;
        
      }      
    }
    catch(e) {
    }
  }
 
 function delLeftChar(value,char){
    
  var result = value;
  var posi = value.indexOf(char);
  if(posi > -1 && value.length > 1){
	  while(posi == 0){
		  result = value.substring(1);
	      posi = result.indexOf(char);
	      value = result;
	  }    
  }
  
  return(result);

 }

 function checkAutomaticFields() {
  var coll= document.forms[0].elements["AUTOMATICINT"];
  var elem = null;
  var v;
  if (coll != null) {
    var collength = coll.length;
    if (collength > 1) {
      for (i =0; i< collength; i++) {
		 try {
		    if (trim(coll[i].value) == "") v = 0;
		    else {
		       v = parseInt(delLeftChar(coll[i].value,'0'));
		       coll[i].value = "" + v;	
		    }
		 	if (v <= 0) {
	          elem = coll[i];
	          break;
		 	}
		 }
		 catch (e) {
           elem = coll[i];
	       break;
		 }
      }
    } else {
		 try {
		    if (trim(coll.value) == "") v = 0;
		    else {
		       v = parseInt(delLeftChar(coll.value,'0'));
		       coll.value = "" + v;	
		    } 	
		 	if (v <= 0) {
	          elem = coll;
		 	}
		 }
		 catch (e) {
           elem = coll;
		 }
    }
    if ( elem == null) return true;
    else {
      alert("Los campos de Generacion Automatica deben poseer un valor valido.");
      elem.focus();
      return false;
    }
  } else return true;

}
 
function checkMandatoryFields() {
  var coll= document.forms[0].elements["MANDATORY"];
  var elem = null;
  var v;
  if (coll != null) {
    var collength = coll.length;
    if (collength > 1) {
      for (i =0; i< collength; i++) {
		 try {
		    if (trim(coll[i].value) == "") {
	          elem = coll[i];
	          break;
		 	}
		 }
		 catch (e) {
           elem = coll[i];
	       break;
		 }
      }
    } else {
		 try {
		    if (trim(coll.value) == "") {
	          elem = coll;
		 	}
		 }
		 catch (e) {
           elem = coll;
		 }
    }
    if ( elem == null) return true;
    else {
      alert("Los Fechas y Montos de los pagos deben poseer un valor valido.");
      elem.focus();
      return false;
    }
  } else return true;

}

 function delRowsFrom(tablename,noRow){
   var myTable = document.all["DINTABLE"];
   var maxRows = myTable.rows.length;   
   for (j=noRow; j < maxRows;j++) {
       myTable.deleteRow(noRow);
   }
   myTable.refresh(); 
 }
 
</SCRIPT>

<h3 align="center"> Pagos Irregulares</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150" onsubmit="return(checkMandatoryFields())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="509">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">  
  <table class="tbenter" >
    <tr>
      <td class="TDBKG"><a href="javascript:DeleteRowSel('DINTABLE','ROWSEL')">Eliminar</a></td>    
      <td > 
        <div align="right">Cantidad de Registros a A&ntilde;adir : 
          <input type="text" name="TEMP_ROW" size="4" maxlength="3" value="1" onBlur="checkRowValue()" onKeypress="enterInteger()">
        </div>
      </td>
      <td class="TDBKG" width="2%"><a href="javascript:AddRow('DINTABLE',document.forms[0].TEMP_ROW.value)">A&ntilde;adir</a></td>
    </tr>
  </table>
  <table class="tableinfo" id=DINTABLE>
    <tr id="trdark"> 
      <td align=center width="1%">
      </td>
      <td nowrap> 
        <div align="center">N&uacute;mero</div>
      </td>
      <td nowrap> 
        <div align="center">Principal</div>
      </td>
    </tr>
    <%
	 	pmntPlus.initRow();
        while (pmntPlus.getNextRow()) {
        int actrow = pmntPlus.getCurrentRow() + 1;
	%> 
    <tr id="trclear">       
      <td align=center>
    	<input type="checkbox" name="ROWSEL" value="<%= actrow %>">
      </td>
      <td nowrap> 
        <div align="center"> 
          <input type="text" name="DLPPNU_<%= actrow %>" id="MANDATORY" size="4" maxlength="3"  value="<%= pmntPlus.getRecord(0) %>" >
        </div>
      </td>
      <td nowrap> 
        <div align="center"> 
          <input type="text" name="DLPPRI_<%= actrow %>" id="MANDATORY" style ="text-align: right" size="17" maxlength="15" value="<%= pmntPlus.getRecord(1) %>" onKeypress="enterDecimal()">
        </div>
      </td>
    </tr>
    <%
       }
    %> 
    
  </table>
   
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  
  </form>
</body>
</html>

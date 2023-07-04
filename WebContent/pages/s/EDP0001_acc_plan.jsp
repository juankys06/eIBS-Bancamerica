<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Format Code List
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "planList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "plan" class= "datapro.eibs.beans.EDP000101Message"  scope="session" /> 

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/graphical.jsp"> </SCRIPT>

<script language="javascript">

function closeHiddenDivNew(){
	setVisibility(document.getElementById("hiddenDivNew"), "hidden");
}

function showHiddenDivNew() {
 	
	var hiddenDivNew = document.getElementById("hiddenDivNew");
	var opTable = document.getElementById("opTable");

	 var y= opTable.offsetTop + 10;
     var x= opTable.offsetLeft + (hiddenDivNew.clientWidth /2);
     
	//cancelBub(evt);

	moveElement(hiddenDivNew, y, x);
	setVisibility(hiddenDivNew, "visible");
	 
	 //document.forms[0].RUTNUM.focus();
}

document.onclick= closeHiddenDivNew;
 
 function goAction(op) {
   document.forms[0].opt.value = op;
   showChecked("PLANCOD");
   switch (op) {
    case 1 :{
            document.forms[0].E01DPGCTA.value="";
   			document.forms[0].E01DPGCOD.value="";
   			document.forms[0].E01DPGDSC.value="";
   			document.forms[0].E01DPGOPE.value="";
   			document.forms[0].E01DPGCF1.value="";
   			document.forms[0].E01DPGCF2.value="";
   			document.forms[0].E01DPGCF3.value="";
   			document.forms[0].E01DPGCF4.value="";
   			document.forms[0].E01DPGCF5.value="";
   			document.forms[0].E01DPGCTA.readOnly=false;
    		showHiddenDivNew();
    		document.forms[0].E01DPGCTA.focus();
            break;
            }
    case 2 :{
    		showHiddenDivNew();
    		document.forms[0].E01DPGCTA.readOnly=true;
    		document.forms[0].E01DPGDSC.focus();
            break;
            }
    case 3 :{
    		var okdel = confirm("Esta opcion borrará la Cuenta : "+ document.forms[0].E01DPGCTA.value);
    		if ( okdel ) document.forms[0].submit();
            break;
            }
    case 4 :{
            window.location.href="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDP0035?SCREEN=100&FMTCOD=" + document.forms[0].E01DPGTIT.value;
            break;
            }
   }  
   
 }
 
 function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";

  }
 
 function setPlanInfo(idxRow){   
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].E01DPGCTA.value=trim(dataTable.rows[idxRow].cells[1].innerText);
   document.forms[0].E01DPGCOD.value=trim(dataTable.rows[idxRow].cells[2].innerText);
   document.forms[0].E01DPGDSC.value=trim(dataTable.rows[idxRow].cells[3].innerText);
   document.forms[0].E01DPGOPE.value=trim(dataTable.rows[idxRow].cells[4].innerText);
   document.forms[0].E01DPGCF1.value=trim(dataTable.rows[idxRow].cells[5].innerText);
   document.forms[0].E01DPGCF2.value=trim(dataTable.rows[idxRow].cells[6].innerText);
   document.forms[0].E01DPGCF3.value=trim(dataTable.rows[idxRow].cells[7].innerText);
   document.forms[0].E01DPGCF4.value=trim(dataTable.rows[idxRow].cells[8].innerText);
   document.forms[0].E01DPGCF5.value=trim(dataTable.rows[idxRow].cells[8].innerText);
   document.forms[0].ROW.value=idxRow;
  } 
 
</script>

</HEAD>
<BODY>

 <h3 align="center">Plan de Cuentas de Compañias<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="acc_plan.jsp,EPD0001"> 
 </h3>
 <hr size="4">
 <FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDP0001" >

 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
 <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
 <INPUT TYPE=HIDDEN NAME="ROW" VALUE="">
 
<div id="hiddenDivNew" class="hiddenDiv">
 <TABLE id=tbhelp cellpadding=4 cellspacing=2 style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<TR>
	  <TD ALIGN=center colspan=6>
          <b>Plan</b>  
      </TD>
   </TR>
	<TR>
	  <TD ALIGN=right nowrap>
          <b>Cuenta :</b>  
      </TD>
      <TD ALIGN=Left nowrap colspan=5>
	     <input type="text" maxlength=17 size=17 name="E01DPGCTA" value="">  
	  </TD>
   </TR>
   <TR>
	  <TD ALIGN=right nowrap>
          <b>Codigo Agrupación:</b>  
      </TD>
      <TD ALIGN=Left nowrap colspan=5>
	     <input type="text" maxlength=4 size=4 name="E01DPGCOD" value="">  
	     <a href="javascript:GetCodeCNOFC('E01DPGCOD','06')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a> 
      </TD>
   </TR>
   <TR>
	  <TD ALIGN=right nowrap>
          <b>Descripción:</b>  
      </TD>
      <TD ALIGN=Left nowrap colspan=5>
	     <input type="text" maxlength=35 size=36 name="E01DPGDSC" value="">  
	 </TD>
   </TR>
   <TR>
	  <TD ALIGN=right nowrap>
          <b>Operando:</b>  
      </TD>
      <TD ALIGN=Left nowrap colspan=5>
	     <select name="E01DPGOPE">
	        <option value="" ></option>
            <option value="+" >Suma</option>
            <option value="-" >Resta</option>
            <option value="S" >Subtotal</option>
         </select>  
	 </TD>
   </TR>
   <TR>
	  <TD ALIGN=right nowrap><b>Cod. Formulas:</b>  
      </TD>
      <TD ALIGN=center nowrap>
        <a href="javascript:GetCode('E01DPGCF1','STATIC_acc_formula.jsp')"><b>1</b></a>
      </TD>
      <TD ALIGN=center nowrap> 
      	<a href="javascript:GetCode('E01DPGCF2','STATIC_acc_formula.jsp')"><B>2</B></a>
      </TD>
      <TD ALIGN=center nowrap> 
      	<a href="javascript:GetCode('E01DPGCF3','STATIC_acc_formula.jsp')"><B>3</B></a>
      </TD>
      <TD ALIGN=center nowrap> 
      	<a href="javascript:GetCode('E01DPGCF4','STATIC_acc_formula.jsp')"><B>4</B></a>
      </TD>
      <TD ALIGN=center nowrap> 
      	<a href="javascript:GetCode('E01DPGCF5','STATIC_acc_formula.jsp')"><B>5</B></a>
      </TD>
   </TR>
   <TR>
	  <TD ALIGN=right nowrap>
          <b></b>  
      </TD>
      <TD ALIGN=Left nowrap>
          <input type="text" name="E01DPGCF1" size="2" maxlength="2" onKeypress="enterInteger()" value="">       
      </TD>
      <TD ALIGN=Left nowrap>
          <input type="text" name="E01DPGCF2" size="2" maxlength="2" onKeypress="enterInteger()" value="">      
      </TD>
      <TD ALIGN=Left nowrap>
          <input type="text" name="E01DPGCF3" size="2" maxlength="2" onKeypress="enterInteger()" value="">       
      </TD>
      <TD ALIGN=Left nowrap>
          <input type="text" name="E01DPGCF4" size="2" maxlength="2" onKeypress="enterInteger()" value="">      
      </TD>
      <TD ALIGN=Left nowrap>
          <input type="text" name="E01DPGCF5" size="2" maxlength="2" onKeypress="enterInteger()" value="">      	 
      </TD>
   </TR>
   <TR>
	  <TD ALIGN=center colspan=6>
	    <p> </p>
	  </TD>
   </TR>
   <TR>
	  <TD ALIGN=center colspan=6>
	     <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
      </TD>
   </TR>
 </TABLE>
</div>
  <table class="tableinfo">
    <tr id="trdark"> 
      <td align="right"  >
         Formato Balance
      </td>
      <td nowrap > 
         <input type="text" name="E01DPGTIT" size="2" readonly value = "<%= userPO.getHeader1() %>">
         <input type="text" name="FMTDSC" size="36" readonly value = "<%= userPO.getHeader2() %>">
      </td>   
      <td align="right" nowrap >Total Entradas :</td>
      <td nowrap > 
         <input type="text" name="ROW" size="5" maxlength="5" id="txtright" readonly value="<%if (planList.getLastRec()>0) out.print(planList.getLastRec()+1); else out.print(""+planList.getLastRec()); %>">
      </td>
    </tr>   
  </table>  
  <% if (planList.getNoResult()) { %>
   <TABLE id=opTable class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="30%" class=TDBKG>
  		 <a href="javascript:goAction(1)">Nueva</a>
      </TD>
      <TD ALIGN=CENTER width="30%" class=TDBKG>
         <a href="javascript:goAction(4)">Formato</a>
      </TD>
      <TD ALIGN=CENTER width="30%" class=TDBKG>
  		 <a href="javascript:goExit()">Salir</a>
      </TD>
    </TR>
   </TABLE>
   <TABLE class="tbenter" height="60%">
   <TR>
      <TD ALIGN=CENTER VALIGN=MIDDLE>
  		 <H4 style="text-align: center">No existe ningun Plan creado para esta formato. <br> Seleccione la opción NUEVA si desea crear un nuevo Plan</h4>
      </TD>
    </TR>
   </TABLE>
  <% } else {%> 
   <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
   <TABLE id=opTable class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
  		 <a href="javascript:goAction(1)">Nueva</a>
      </TD>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
  		 <a href="javascript:goAction(2)">Mantenimiento</a>
      </TD>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
         <a href="javascript:goAction(3)">Eliminar</a>
      </TD>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
         <a href="javascript:goAction(4)">Formato</a>
      </TD>
      <TD ALIGN=CENTER width="20%" class=TDBKG>
  		 <a href="javascript:goExit()">Salir</a>
      </TD>
    </TR>
  </TABLE>
  
 <TABLE  id="mainTable" class="tableinfo"  > 
 
 <TR > 
  <TD NOWRAP valign="top" width="100%">
   <TABLE id="headTable" >
    <TR id="trdark">  
      <TH ALIGN=CENTER rowspan="2" nowrap></TH>
      <TH ALIGN=CENTER rowspan="2" nowrap>Cuenta</TH>     
      <TH ALIGN=CENTER rowspan="2" nowrap>Grupo</TH>            
      <TH ALIGN=CENTER rowspan="2" nowrap>Descripcion</TH>      
      <TH ALIGN=CENTER rowspan="2" nowrap>Operando</TH>
      <TH ALIGN=CENTER colspan="5" >Codigos de Formula</TH>            
    </TR>
    <TR id="trdark">  
      <TH ALIGN=CENTER nowrap>1</TH>
      <TH ALIGN=CENTER nowrap>2</TH>
      <TH ALIGN=CENTER nowrap>3</TH>
      <TH ALIGN=CENTER nowrap>4</TH>
      <TH ALIGN=CENTER nowrap>5</TH>
    </TR>
   </TABLE>
  
   <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
    <table id="dataTable">
    <%
                planList.initRow();
                int k=1;
                while (planList.getNextRow()) {
                    if (planList.getFlag().equals("")) {
                    		out.println(planList.getRecord());
                    k++;
                    }        
                }                 
    %> 
   </table>
   </div>
   
  </TD>   
  </TR>	
</TABLE>

<SCRIPT language="JavaScript">
  document.forms[0].totalRow.value="<%= k %>";
  function resizeDoc() {
     divResize(false);
     adjustDifTables(
     	document.getElementById("headTable"), 
     	document.getElementById("dataTable"), 
     	document.getElementById("dataDiv1"),5,1);
  }
  showChecked("PLANCOD");
  resizeDoc();
  window.onresize=resizeDoc;
  
</SCRIPT>
<% } %>
<SCRIPT language="JavaScript">
   	document.getElementById("hiddenDivNew").onclick=cancelBub;
</SCRIPT>

<%  	
    int row=0;
    try {
      row = Integer.parseInt(request.getParameter("ROW"));
    }
    catch (Exception e) {
      row = -1;
    }    
 
   if ( !error.getERRNUM().equals("0")) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
%>
  <SCRIPT language="JavaScript">
     <% if (row > -1) out.println("radioClick(\"PLANCOD\","+ row + ");"); %> 
     document.forms[0].E01DPGCTA.value="<%= plan.getE01DPGCTA() %>";
     document.forms[0].E01DPGCOD.value="<%= plan.getE01DPGCOD() %>";
     document.forms[0].E01DPGDSC.value="<%= plan.getE01DPGDSC() %>";
     document.forms[0].E01DPGOPE.value="<%= plan.getE01DPGOPE() %>";
     document.forms[0].E01DPGCF1.value="<%= plan.getE01DPGCF1() %>";
     document.forms[0].E01DPGCF2.value="<%= plan.getE01DPGCF2() %>";
     document.forms[0].E01DPGCF3.value="<%= plan.getE01DPGCF3() %>";
     document.forms[0].E01DPGCF4.value="<%= plan.getE01DPGCF4() %>";
     document.forms[0].E01DPGCF5.value="<%= plan.getE01DPGCF5() %>";
     showHiddenDivNew();
  </SCRIPT>
<%     
 }
%>
</FORM>

</BODY>
</HTML>

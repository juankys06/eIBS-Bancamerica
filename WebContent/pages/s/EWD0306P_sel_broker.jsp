<html>
<head>
<title>Agentes de Bolsa</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0306Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

function closeHiddenDivNew(){
	setVisibility(document.getElementById("hiddenDivNew"), "hidden");
}

function showHiddenDivNew(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var hiddenDivNew = document.getElementById("hiddenDivNew");

	var y=evt.clientY + document.body.scrollTop;
	var x=evt.clientX + document.body.scrollLeft;
     
	cancelBub(evt);

	moveElement(hiddenDivNew, y, x);
	setVisibility(hiddenDivNew, "visible");
	
	document.forms[0].CODEN.focus();
}

function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}


function getParams(code) {

    document.forms[0].CODE.value = code;
    
}

document.onclick= closeHiddenDivNew;

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Tabla de Agentes de Bolsa<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sel_broker.jsp,EWD0306P"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0306P" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="CODE" value="">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">

<div id="hiddenDivNew" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Código :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
          <input type="text" name="CODEN" size="4" maxlength="3">
          <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom"> 
        </div>
      </td>
    </tr>
   <tr id="trclear">
   
   <TD ALIGN=center nowrap colspan="2">
	     <input id="EIBSBTN" type=submit name="Submit" value="Enviar"> 
   </TD>
   
   </tr>
 </TABLE>
</div>

  <%
	if ( EWD0306Help.getNoResult() ) {
 %> 
  <TABLE class="tbenter" width=100% height=50%>
    <TR>
      <TD> 
      <div align="center"> 
          <p><b> No hay resultados opara su criterio de búsqueda</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
              <td class=TDBKG>
                <div id="eibsNew" align="center" style="cursor:pointer"><a><b>Nuevo</b></a></div>
              </td>
              <td class=TDBKG>
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
           </tr>
         </table>
	  </div>

	  </TD>
	</TR>
    </TABLE>
	
  <%  
		}
	else {
%> <% 
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
      <td class=TDBKG> 
		<div id="eibsNew" align="center" style="cursor:pointer"><a><b>Nuevo</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(4)"><b>Consulta</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(3)"><b>Borrar</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  
  <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
        <table id="headTable" width="100%" >
          <tr id="trdark"> 
            <th align=CENTER nowrap >&nbsp;</th>
            <th align=CENTER nowrap > 
              <div align="center">Código</div>
            </th>
            <th align=CENTER nowrap >Nombre</th>
            <th align=CENTER nowrap > 
              <div align="center">Telefono </div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Ciudad</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Estado</div>
            </th>
          </tr>
        </table>
     <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
		<%
                EWD0306Help.initRow();
				int k=1;
                while (EWD0306Help.getNextRow()) {
                 
                  out.println(EWD0306Help.getRecord());
                  k++;   
                }
              %> 
    </table>
   </div>
</Table>
<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
       divResize(false);
     adjustEquTables(
     	document.getElementById("headTable"),
		document.getElementById("dataTable"),
		document.getElementById("dataDiv1"), 1,false);
      }

     resizeDoc();
    
     window.onresize=resizeDoc;
     
     try {
	     document.forms[0].index[0].click();
	 } catch (e) {
	     document.forms[0].index.click();
	 }
     
</SCRIPT>

<%}%>

<SCRIPT language="JavaScript">
 	document.getElementById("hiddenDivNew").onclick=cancelBub;
	document.getElementById("eibsNew").onclick=showHiddenDivNew;
     
</SCRIPT>
  </form>

</body>
</html>

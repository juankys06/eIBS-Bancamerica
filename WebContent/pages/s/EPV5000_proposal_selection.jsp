<html>
<head>
<title>TABLAS DE SERVICIOS</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EPV5000Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

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
	 
}

function goAction(op) {

	document.forms[0].opt.value = op;
	if (op == 1) document.forms[0].SCREEN.value="200"; 
	document.forms[0].submit();
  
}


function getParams(propnum,cusnum) {

    document.forms[0].PROPNUM.value = propnum;
	document.forms[0].CUSNUM.value = cusnum;
	
    
}


document.onclick= closeHiddenDivNew;

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Manejo de Propuestas</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.credprop.JSEPV5000" >
  <input type=HIDDEN name="SCREEN" value="700">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="PROPNUM" value="">
  <input type=HIDDEN name="CUSNUM" value="">
  
<div id="hiddenDivNew" class="hiddenDiv">
    <table id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
      
      
      <tr > 
        <td> 
          <div align="right">Identificación del Cliente :</div>
        </td>
        <td> 
          <div align="left"> 
            <input type="text" name="E01PVMIDN"  size="17" maxlength="15" value="<%= userPO.getCusNum()%>">
            <a href="javascript:GetCustomerDescId('CUSNUM','','E01PVMIDN')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
          </div>
        </td>
      </tr>
      <tr > 
        <td align=center nowrap colspan="2"> 
          <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="javascript:goAction(1)">
        </td>
      </tr>
    </table>
  </div>
  <%
	if ( EPV5000Help.getNoResult() ) {
 %>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=60% align=center>
           <tr>
              <td class=TDBKG width=20%>
                <div id="eibsNew" align="center" style="cursor:pointer"><a><b>Nueva</b></a></div>
              </td>
              <td class=TDBKG width=20%>
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
           
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="33%"> 
      	<div id="eibsNew" align="center" style="cursor:pointer"><a><b>Nueva</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">No. Propuesta</div>
            </th>
            <th align=CENTER nowrap width="40%">Cliente</th>
            <th align=CENTER nowrap width="40%"> 
              <div align="center">Estado</div>
            </th>
            <th align=CENTER nowrap width="40%">Descripción</th>
            <th align=CENTER nowrap width="40%"> 
              <div align="center">Fecha de <br>
                Propuesta</div>
            </th>
            <th align=CENTER nowrap width="40%"> 
              <div align="center">Vendedor</div>
            </th>
          </tr>
          <%
                EPV5000Help.initRow();
				int k=1;
                while (EPV5000Help.getNextRow()) {
                 
                  out.println(EPV5000Help.getRecord());
                  k++;   
                }
              %>
        </table>
    </table>
     
  <%}%>

<SCRIPT language="JavaScript">
  
  showChecked("ACCNUM");
  
 	document.getElementById("hiddenDivNew").onclick=cancelBub;
	document.getElementById("eibsNew").onclick=showHiddenDivNew;
  
</SCRIPT>
</form>

</body>
</html>

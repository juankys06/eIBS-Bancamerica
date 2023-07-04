<html>
<head>
<title>TABLAS DE DOCUMENTOS</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDI010Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/graphical.jsp"> </SCRIPT>
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
	document.forms[0].submit();
  
}


function getParams(code) {

    document.forms[0].TABLE.value = code;
    
}

document.onclick= closeHiddenDivNew;

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Tablas de Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sel_tables, EDI010" ></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDI010" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  
<input type=HIDDEN name="TABLE" >
 <div id="hiddenDivNew" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Número de Tabla :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
         <input type="text" name="TABLEN" size="4" maxlength="2">       
        </div>
      </td>
    </tr>
	<tr id="trclear"> 
      <td align=CENTER width="18%"> 
        <div align="right">Descripción :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
         <input type="text" name="DESCRIPTION" size="35" maxlength="35">       
        </div>
      </td>
    </tr>

	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Código :</div>
      </td>
      <td align=CENTER width="34%"> 
          <div align="left"> 
            <select name="CODE">
			  <option value=""></option>
              <option value="A">Cuenta</option>
              <option value="C">Cliente Corporativo</option>
              <option value="P">Cliente Personal</option>
              <option value="S">Propuesta de Credito</option>
            </select>
          </div>
      </td>
    </tr>

   <tr id="trclear">
   
   <TD ALIGN=center nowrap colspan="2">
	     <input id="EIBSBTN" type=button name="Submit" value="Submit" onClick="javascript:goAction(1)"> 
   </TD>
   
   </tr>
 </TABLE>
</div>




  <%
	if ( EDI010Help.getNoResult() ) {
 %> 
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
            <td class=TDBKG>
                <div id="eibsNew" align="center" style="cursor:pointer"><a><b>Nueva</b></a></div>
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
              <div align="center">Tabla</div>
            </th>
            <th align=CENTER nowrap width="30%"> 
              <div align="center">C&oacute;digo</div>
            </th>
            <th align=CENTER nowrap width="40%"> 
              <div align="center">Descripci&oacute;n</div>
            </th>
          </tr>
          		<%
                EDI010Help.initRow();
				int k=1;
                while (EDI010Help.getNextRow()) {
                 
                  out.println(EDI010Help.getRecord());
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

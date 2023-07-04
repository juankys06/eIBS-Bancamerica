<html>
<head>
<title>Esquema de Pagos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "invList" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

function closeHiddenDivOpt(){
	setVisibility(document.getElementById("hiddenDivOpt"), "hidden");
}

function showHiddenDivOpt(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var hiddenDivOpt = document.getElementById("hiddenDivOpt");

	var y=evt.clientY + document.body.scrollTop;
	var x=evt.clientX + document.body.scrollLeft;
     
	cancelBub(evt);

	moveElement(hiddenDivOpt, y, x);
	setVisibility(hiddenDivOpt, "visible");
	 document.forms[0].TABLEN.focus();
}

function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}


function getParams(code,date1,date2,date3,payment) {

	document.forms[0].CODE.value = code;
    document.forms[0].DATE1.value = date1;
    document.forms[0].DATE2.value = date2;
    document.forms[0].DATE3.value = date3;
    document.forms[0].PAYMENT.value = payment;
    
}

document.onclick= closeHiddenDivOpt;

</SCRIPT>  

</head>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%>
<BODY>
<h3 align="center">Esquema de Pagos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sel_instruments.jsp, EWD0308"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0070" >
  <input type=HIDDEN name="SCREEN" value="1000">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CODE" value="<%= userPO.getIdentifier()%>">
  <input type=HIDDEN name="DATE1">
  <input type=HIDDEN name="DATE2">
  <input type=HIDDEN name="DATE3">
  <input type=HIDDEN name="PAYMENT">

<div id="hiddenDivOpt" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Fecha de Comienzo :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
          <input type="text" name="DATES1" size="3" maxlength="2"> 
		  <input type="text" name="DATES2" size="3" maxlength="2">
		  <input type="text" name="DATES3" size="3" maxlength="2">
	   </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td align=CENTER width="18%"> 
        <div align="right">Frecuencia de Pago :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left">
		   <input type="text" name="FRECPER" size="3" maxlength="3"> 
           <select name="FRECCOD">
                <option value="D">Días </option>
                <option value="M">Meses</option>
                <option value="Y">Años</option>
          </select>
 
        </div>
      </td>
    </tr>
	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Tipo de Pago :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
              <select name="PTYPE">
                <option value="I">Interes </option>
                <option value="C">Capital</option>
				<option value="D">Dividendos</option>
          </select>      
		 </div>
      </td>
    </tr>
	<tr id="trclear"> 
      <td align=CENTER width="18%"> 
        <div align="right">Monto a Pagar :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left">
			  <input type="text" name="PAMOUNT" size="17" maxlength="15" onKeyPress="enterDecimal()"> 
              <select name="PAYTYPE">
                <option value="%">Porciento</option>
                <option value="F">Fijo</option>
          </select>      
		 </div>
      </td>
    </tr>
   <tr id="trclear">
   
   <TD ALIGN=center nowrap colspan="2">
	     <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="goAction(5);"> 
   </TD>
   
   </tr>
 </TABLE>
</div>

 <h4>Información Básica</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right">Código de Instrumento :</div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> <%= userPO.getIdentifier().trim()%> </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right">Descripción :</div>
            </td>
            <td nowrap > 
              <div align="left"> <%= userPO.getHeader1()%> </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right">Tipo de Instrumento :</div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> <%= userPO.getHeader2()%> </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> <%= userPO.getHeader3()%> </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table> 

  <%
	if ( invList.getNoResult() ) {
 %>
<TABLE class="tbenter" width="100%" height="50%" >
    <tr>
      <TD > 
        <div align="center"> 
          <p><b>No existen pagos definidos, elija una de las opciones siguientes </b></p>
          <table  class="tbenter" width=100% align=center>
            <tr > 
              <td class=TDBKG width="29%" > 
                <div align="center" ><a href="javascript:goAction(1)"><b>Nuevo</b></a></div>
              </td>
              <td class=TDBKG width="34%" > 
                <div id="eibsOption1" align="center" style="cursor:pointer"><a><b>Cálculo <BR>Autom&aacute;tico</b></a></div>
      		 </td>
              <td class=TDBKG width="37%" > 
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

          
  <table class="tbenter" align=center>
    <tr> 
      <td class=TDBKG > 
        <div align="center" ><a href="javascript:goAction(1)"><b>Nuevo</b></a></div>
      </td>
      <td class=TDBKG > 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG > 
        <div id="eibsOption1" align="center" style="cursor:pointer" ><a><b>Cálculo<BR>Automático</b></a></div>
      </td>
      <td class=TDBKG > 
        <div align="center"><a href="javascript:goAction(3)"><b>Borrar</b></a></div>
      </td>
      <td class=TDBKG > 
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
              <div align="center">Tipo de<br> Pago</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Monto a <br> Pagar %</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Fecha<br> Declarada</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Fecha de<br> Grabación</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Fecha de <br> Pago</div>
            </th>
            <th align=CENTER nowrap >Estado</th>
            
          </tr>
        </table>
     <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
		<%
                invList.initRow();
				int k=1;
                while (invList.getNextRow()) {
                 
                  out.println(invList.getRecord());
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

<p>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
    <tr bgcolor="#FFFFFF"> 
      <td width="33%"> 
        <div align="center"> 
          <input id="EIBSBTN" type=button name="Submit2" value="Enviar" onClick="goAction(10)">
        </div>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF"> 
      <td> 
        <div align="center"> </div>
      </td>
    </tr>
  </table>
  </form>
  
<SCRIPT language="JavaScript">
 	document.getElementById("hiddenDivOpt").onclick=cancelBub;
	document.getElementById("eibsOption1").onclick=showHiddenDivOpt;
     
</SCRIPT>

</body>
</html>

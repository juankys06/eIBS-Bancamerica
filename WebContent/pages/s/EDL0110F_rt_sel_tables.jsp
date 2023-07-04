<html>
<head>
<title>TABLAS DE SERVICIOS</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDL0110FHelp" class= "datapro.eibs.beans.JBList"  scope="session" />
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


function getParams(tar,des1,type1,rtPry1,rtSec1,pryMonth1,pryDay1,pryYear1)
{

	document.forms[0].TABLEN.value = tar;
	document.forms[0].DSC.value = des1;
	document.forms[0].TYPE.value = type1;
	
	document.forms[0].RT_PRY.value = rtPry1;
	document.forms[0].RT_SEC.value = rtSec1;
		
	document.forms[0].PRY_MONTH.value = pryMonth1;
	document.forms[0].PRY_DAY.value = pryDay1;
	document.forms[0].PRY_YEAR.value = pryYear1;
	
}

document.onclick= closeHiddenDivNew;

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Tasas Flotantes</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDL0110F" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  
  <input type=HIDDEN name="TABLEN" value="">
  <input type=HIDDEN name="DSC" value="">
  <input type=HIDDEN name="TYPE" value="">
  
  <input type=HIDDEN name="RT_PRY" value="">
  <input type=HIDDEN name="RT_SEC" value="">
  <input type=HIDDEN name="PRY_MONTH" value="">
  <input type=HIDDEN name="PRY_DAY" value="">
  <input type=HIDDEN name="PRY_YEAR" value="">

  
  
 <div id="hiddenDivNew" class="hiddenDiv">
    <table id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
      <tr id="trdark"> 
        <td align=CENTER width="18%"> 
          <div align="right">Número de Tabla :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
            <input type="text" name="TABLE" size="4" maxlength="2">
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
      
      <tr id="trclear"> 
        <td align=center nowrap colspan="2"> 
          <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="javascript:goAction(1)">
        </td>
      </tr>
    </table>
  </div>
  <%
	if ( EDL0110FHelp.getNoResult() ) {
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
 
          <tr id="trdark"> 
            <th align=CENTER   nowrap>&nbsp;</th>
            <th align=CENTER  nowrap> 
              <div align="center">Tabla</div>
            </th>
            <th align=CENTER   nowrap> 
              <div align="center">Descripci&oacute;n</div>
            </th>
            <th align=CENTER   nowrap>Tipo</th>
            
            <th align=CENTER    nowrap> 
              <div align="center">Tasa<br>Primaria</div>
            </th>
            <th align=CENTER   nowrap> 
              <div align="center">Tasa<br>Secundaria</div>
            </th> 
            <th align=CENTER   nowrap> 
               <div align="center">Fecha</div>
            </th>        
          </tr>
          <%
                EDL0110FHelp.initRow();
				int k=1;
                while (EDL0110FHelp.getNextRow()) {
                 
                  out.println(EDL0110FHelp.getRecord());
                  k++;   
                }
              %>
   
</TABLE>
     
  <%}%>

<SCRIPT language="JavaScript">
  
  showChecked("TBL");
  
 	document.getElementById("hiddenDivNew").onclick=cancelBub;
	document.getElementById("eibsNew").onclick=showHiddenDivNew;
  
</SCRIPT>
</form>

</body>
</html>

<html>
<head>
<title>Paying and Receiving Fee Structure</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDD0660Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function closeEnter(){
   	  enterFee.filters[0].apply();
      enterFee.style.visibility="hidden";
      enterFee.filters[0].Play();
}

function ShowEnterCod() {	 
	 var y=event.clientY + document.body.scrollTop;
     var x=event.clientX + document.body.scrollLeft;
	 cancelBub();
	 eval('enterFee.style.pixelTop=' + y);
     eval('enterFee.style.pixelLeft=' + x);
	 enterFee.filters[0].apply();
     enterFee.style.visibility="visible";
     enterFee.filters[0].Play();
	 
}


function goAction(op,bnew) {

	if (bnew == true) {
		document.forms[0].TBL.value = document.forms[0].TBL1.value;
		document.forms[0].CUN.value = document.forms[0].CUN1.value;
	}
	
	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}


function getParams(bnk, tbl, cun) {

	document.forms[0].BNK.value = bnk;
	document.forms[0].TBL.value = tbl;
    document.forms[0].CUN.value = cun;
    
}

function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Estructura de Cargos de Pagos y Recibos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fee_list.jsp, EDD0660"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDD0660" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="BNK" value="<%=userPO.getBank()%>">
  <input type=HIDDEN name="TBL" value="">
  <input type=HIDDEN name="CUN" value="">
  
  <div id="enterFee" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onClick="cancelBub()"> 
    <table id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
      <tr id="trdark"> 
        <td align=CENTER width="18%"> 
          <div align="right">Estructura de Cargo :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
            <input type="text" name="TBL1" size="4" maxlength="2" value="">
          </div>
        </td>
      </tr>
	  <tr id="trclear"> 
        <td align=CENTER width="18%"> 
          <div align="right">Cliente :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
            <input type="text" name="CUN1" size="10" maxlength="9" value="0">
			<A href="javascript:GetCustomer('CUN1')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" border="0" ></A>
          </div>
        </td>
      </tr>
       <tr id="trdark"> 
        <td align=center nowrap colspan="2"> 
          <input id="EIBSBTN" type=button name="Submit" value="Ingresar" onClick="javascript:goAction(1,true)">
        </td>
      </tr>
    </table>
  </div>

  <%
	if ( EDD0660Help.getNoResult() ) {
 %>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay registros para su criterio de búsqueda</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
           <td class=TDBKG>
                <div align="center" style="cursor:hand" onClick="ShowEnterCod()"><a><b>Nuevo</b></a></div>
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
        <div align="center" style="cursor:hand" onClick="ShowEnterCod()"><a><b>Nuevo</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction(2, false)"><b>Mantenimiento</b></a></div>
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
            <th align=CENTER nowrap width="10%">Banco</th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Estructura</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Moneda</div>
            </th>
            <th align=CENTER nowrap width="10%">Cliente</th>
            <th align=CENTER nowrap width="50%">Nombre</th>
          </tr>
          <%
                EDD0660Help.initRow();
				int k=1;
                while (EDD0660Help.getNextRow()) {
                 
                  out.println(EDD0660Help.getRecord());
                  k++;   
                }
              %>
        </table>
    </table>
     
  <%}%>

<SCRIPT language="JavaScript">
  
  showChecked("ACCNUM");
  
</SCRIPT>
</form>

</body>
</html>

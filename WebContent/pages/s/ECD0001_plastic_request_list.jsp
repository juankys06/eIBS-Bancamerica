<html>
<head>
<title>Solicitud de Plastico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0001DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 

<script language="JavaScript">

function goAction(op) {
	document.forms[0].opt.value = op;
	if (op == 4){
		if(confirm("Esta seguro que desea eliminar este registro?")){
			document.forms[0].submit();
		} 
	} else {
		document.forms[0].submit();
	}
}	

function closeEnter(){
   	  setVisibility(document.getElementById("enterData"),"hidden");
}

function showEnterData(evt) {	 
     evt = (evt)? evt: ((window.event) ? window.event : "");
	 document.forms[0].E01CDRTPL.value = "";
 	 document.forms[0].E01CDRQTY.value = "";
 	 document.forms[0].E01CDRVEN.value = "";
 	 document.forms[0].E01CDRMOX.value = "";
 	 document.forms[0].E01CDRYEX.value = "";
 	 var enterData= document.getElementById("enterData");
	 var y=evt.clientY + document.body.scrollTop;
     var x=evt.clientX + document.body.scrollLeft;
	 cancelBub(evt);
	 eval('enterData.style.pixelTop=' + y);
     eval('enterData.style.pixelLeft=' + x);
	 moveElement(enterData, y, x);
	 setVisibility(enterData,"visible");
}





</SCRIPT>  

</head>

<BODY>
<h3 align="center">Solicitud de Plástico</h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0001" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CURRENTROW" value="">
  
<!--
  <div id="enterData" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onClick="cancelBub()"> 
-->
  <div id="enterData" class="hiddenDiv">
    <table id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
      <tr id="trdark"> 
        <td align=CENTER width="18%"> 
          <div align="right">Tipo de Plástico :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
            <input type="text" name="E01CDRTPL"  size="3" maxlength="2" value="<%= msgCD.getE01CDRTPL() %>" >
            <a href="javascript:GetDescATMCard('E01CDRTPL')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
          </div>
        </td>
      </tr>
      <tr id="trdark"> 
        <td align=CENTER width="18%"> 
          <div align="right">Cantidad :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
            <input type="text" name="E01CDRQTY"  size="8" maxlength="7" value="<%= msgCD.getE01CDRQTY() %>">
          </div>
        </td>
      </tr>
      <tr id="trdark"> 
        <td align=CENTER width="18%"> 
          <div align="right">Fecha de Vencimiento :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
            <input type="text" name="E01CDRMOX"  size="3" maxlength="2" value="<%= msgCD.getE01CDRMOX() %>" > / 
            <input type="text" name="E01CDRYEX"  size="3" maxlength="2" value="<%= msgCD.getE01CDRYEX() %>" >
            (Mes/A&ntilde;o) 
          </div>
        </td>
      </tr>

      
      <tr id="trdark"> 
        <td align=CENTER width="18%"> 
          <div align="right">Proveedor :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
            <input type="text" name="E01CDRVEN"  size="12" maxlength="10" value="<%= msgCD.getE01CDRVEN() %>">
            <a href="javascript:GetVendor('E01CDRVEN','1')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
          </div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td align=center nowrap colspan="2"> 
          <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="javascript:goAction(1);">
        </td>
      </tr>
    </table>
  </div>
  <%
	if ( appList.getNoResult() ) {
 %>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
            <td class=TDBKG>
                <div align="center" style="cursor:hand" onClick="showEnterData()"><a><b>Nueva</b></a></div>
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
        <div align="center" style="cursor:hand" onClick="showEnterData()"><a><b>Nueva</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(3)"><b>Recepción</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(5)"><b>Consulta</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(4)"><b>Eliminar</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(6)"><b>Validar</b></a></div>
      </td>
      <td class=TDBKG> 
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
            <th align=CENTER nowrap width="20%"><div align="center"># Solicitud</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Tipo de Plástico</div></th>
            <th align=CENTER nowrap width="30%">Cantidad</th>
            <th align=CENTER nowrap width="30%">Cantidad <br>Recibida</th>
            <th align=CENTER nowrap width="20%">Fecha de <br>Solicitud</th>
            <th align=CENTER nowrap width="10%"><div align="center">Usuario<BR>Solicita</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Usuario<BR>Recibe</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Proveedor</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Estado</div></th>
          </tr>
     	<%
        appList.initRow(); 
		boolean firstTime = true;
		String chk = "";
        while (appList.getNextRow()) {
			if (firstTime) {
				firstTime = false;
				chk = "checked";
			} else {
				chk = "";
			}
           datapro.eibs.beans.ECD0001DSMessage msgPart = (datapro.eibs.beans.ECD0001DSMessage) appList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= appList.getCurrentRow()%>" <%=chk%> 
				onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRNUM())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRNPL())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRQTY())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRREQ())%></TD>
		  	<TD NOWRAP ALIGN="CENTER"><%= Util.formatDate(msgPart.getE01CDRDAY(),msgPart.getE01CDRMON(),msgPart.getE01CDRYEA()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRUSR())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRRUS())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRNAM())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CDRSTD())%></TD>
		</TR>    		
    	<%}%>    
        </table>
    </table>
     
  <%}%>

<SCRIPT language="JavaScript">  
  showChecked("ROW"); 
  document.onclick=closeEnter;
  document.getElementById("enterData").onclick=cancelBub;
</SCRIPT>
</form>

</body>
</html>

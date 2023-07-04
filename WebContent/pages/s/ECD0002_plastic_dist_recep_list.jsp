<html> 
<head>
<title>Solicitud de Plastico</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "msgCD" class= "datapro.eibs.beans.ECD0002DSMessage"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

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
   	  enterData.filters[0].apply();
      enterData.style.visibility="hidden";
      enterData.filters[0].Play();
}

function ShowEnterData() {	 
	 var y=event.clientY + document.body.scrollTop;
     var x=event.clientX + document.body.scrollLeft;
	 cancelBub();
	 eval('enterData.style.pixelTop=' + y);
     eval('enterData.style.pixelLeft=' + x);
	 enterData.filters[0].apply();
     enterData.style.visibility="visible";
     enterData.filters[0].Play();	 
}

function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;

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
<h3 align="center">Distribución y Recepción de Plástico
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="plastic_dist_recep_list.jsp, ECD0002"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0002" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CURRENTROW" value="">
  
  <div id="enterData" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onClick="cancelBub()"> 
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
            <input type="text" name="E02CDRTPL"  size="3" maxlength="2" value="" >
            <a href="javascript:GetDescATMCard('E02CDRTPL')">
            <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
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
 				<div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
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
%> 
  <p> 

          
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG> 
 		<div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(2)"><b>Recepción</b></a></div>
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
            <th align=CENTER nowrap width="30%">Origen</th>
            <th align=CENTER nowrap width="30%">Destino</th>
            <th align=CENTER nowrap width="20%">Fecha <br>Enviada</th>
            <th align=CENTER nowrap width="10%"><div align="center">Cantidad <br>Enviada</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Fecha <br>Recibida</div></th>
            <th align=CENTER nowrap width="10%"><div align="center">Cantidad <br>Recibida</div></th>
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
           datapro.eibs.beans.ECD0002DSMessage msgPart = (datapro.eibs.beans.ECD0002DSMessage) appList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= appList.getCurrentRow()%>" <%=chk%> 
				onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDRNUM())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDRNPL())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDRBRN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDRBRD())%></TD>
		  	<TD NOWRAP ALIGN="CENTER"><%= Util.formatDate(msgPart.getE02CDRDAY(),msgPart.getE02CDRMON(),msgPart.getE02CDRYEA()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDRQTY())%></TD>
		  	<TD NOWRAP ALIGN="CENTER"><%= Util.formatDate(msgPart.getE02CDRDAR(),msgPart.getE02CDRMOR(),msgPart.getE02CDRYER()) %></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDRREQ())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE02CDRSTD())%></TD>
		</TR>    		
    	<%}%>    
        </table>
    </table>
     
  <%}%>

<SCRIPT language="JavaScript">  
  showChecked("ROW");  
</SCRIPT>
</form>

</body>
</html>

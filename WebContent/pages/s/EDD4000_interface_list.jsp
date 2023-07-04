<html>
<head>
<title>Maestro de Interfaces</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDD400001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">



function goAction(op) {

	document.forms[0].opt.value = op;
	if (op == 1) {
		var y=event.clientY + document.body.scrollTop;
		var x=event.clientX + document.body.scrollLeft;
		ShowNew(x,y);
	} else {
		document.forms[0].submit();
	}
}


function getParams(bank,branch) {

    document.forms[0].BANK.value = account;
    document.forms[0].BRANCH.value = branch;
}

function closeEnter(){
   	enterNew.filters[0].apply();
    enterNew.style.visibility="hidden";
    enterNew.filters[0].Play();
    document.forms[0].E01DDCCIA.value="";
}

function ShowNew(x,y) {	 
	cancelBub();
	eval('enterNew.style.pixelTop=' + y);
	eval('enterNew.style.pixelLeft=' + x);
	enterNew.filters[0].apply();
    enterNew.style.visibility="visible";
    enterNew.filters[0].Play();
	document.forms[0].E01DDCCIA.focus();
}

function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;
</SCRIPT>  

</head>

<BODY>
<h3 align="center">Maestro de Compañias (Pensiones/Nómina/FMH) <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="interface_list.jsp, EDD4000"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDD4000" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="BANK">
    <input type=HIDDEN name="BRANCH">
   
    <input type=HIDDEN name="opt">
  </p>
<div id="enterNew" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onclick="cancelBub()">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<tr id="trdark"> 
      <td align=CENTER width="25%"> 
        <div align="right">Interface :</div>
      </td>
      <td align=CENTER width="75%"> 
        <div align="left"> 
          <input type="text" name="E01DDCCIA" size="4" maxlength="3">
          <a href="javascript:GetCodeDescCNOFC('E01DDCCIA','','BT')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
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
  <p> 
    <%
	if ( EDD400001Help.getNoResult() ) {
 %>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
            <tr> 
              <td class=TDBKG width="30%"> 
                <div align="center" style="cursor:hand" onClick="goAction('1')"><b>Nueva</b></div>
              </td>
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
            </tr>
          </table>
          <p>&nbsp;</p>
          
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
      <td class=TDBKG width="30%"> 
        <div align="center" style="cursor:hand" onClick="goAction('1')"><b>Nueva</b></div>
      </td>
		<td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
	  <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(3)"><b>Borrado</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Proceso</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Descripci&oacute;n</div>
            </th>
          </tr>
          <%
                boolean firstTime = true;
				String chk = "";
                EDD400001Help.initRow();
                while (EDD400001Help.getNextRow()) {
					if (firstTime) {
						firstTime = false;
						chk = "checked";
					} else {
						chk = "";
					}
                 
                  datapro.eibs.beans.EDD400001Message msgList = (datapro.eibs.beans.EDD400001Message) EDD400001Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= EDD400001Help.getCurrentRow() %>" <%=chk%> >
            </td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DDCCIA() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01CIADSC() %></td>
          </tr>
          <%
                }
              %>
        </table>
  </table>
     
  <SCRIPT language="JavaScript">
 showChecked("CURRCODE");
     
</SCRIPT>

<%}%>


  </form>

</body>
</html>

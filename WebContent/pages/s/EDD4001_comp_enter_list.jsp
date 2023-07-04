<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Mantenimiento de Pagos Automáticos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDD400101Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">



function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}

function getParameters(tablecode, processcode) {

	document.forms[0].TABLECODE.value = tablecode;
	document.forms[0].PROCESSCODE.value = processcode;  
}





function closeEnter(){
   	  enterACC.filters[0].apply();
      enterACC.style.visibility="hidden";
      enterACC.filters[0].Play();
}

function ShowEnterCod() {	 
	 var y=event.clientY + document.body.scrollTop;
     var x=event.clientX + document.body.scrollLeft;
	 cancelBub();
	 eval('enterACC.style.pixelTop=' + y);
     eval('enterACC.style.pixelLeft=' + x);
	 enterACC.filters[0].apply();
     enterACC.style.visibility="visible";
     enterACC.filters[0].Play();
	 
}

function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Mantenimiento de Pagos Automáticos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="comp_enter_list.jsp, EDD4001"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEDD4001" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="400">
    <input type=HIDDEN name="totalRow" value="0">
	
   
    <input type=HIDDEN name="opt">
  </p>
  <div id="enterACC" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onclick="cancelBub()">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<tr id="trclear"> 
      <td align=CENTER width="18%"> 
        <div align="right">Código de Compañia :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
         <input type="text" name="TABLECODE" size="5" maxlength="4" > 
         <a href="javascript:GetCodeDescCNOFC('TABLECODE','','BT')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>             
        </div>
      </td>
    </tr>
	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Tipo de Proceso :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
         <input type="text" name="PROCESSCODE" size="5" maxlength="4" > 
         <a href="javascript:GetCodeDescCNOFC('PROCESSCODE','','PT')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a>             
        </div>
      </td>
    </tr>

   <tr id="trclear">
   
   <TD ALIGN=center nowrap colspan="2">
	     <input id="EIBSBTN" type=button name="Submit" value="Submit" onClick="goAction(2)"> 
   </TD>
   
   </tr>
 </TABLE>
</div>
  <p> 
    <%
	if ( EDD400101Help.getNoResult() ) {
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
          <p>&nbsp;</p>

          
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="30%"> 
        <div align="center" style="cursor:hand" onClick="ShowEnterCod()"><a><b>Nueva</b></a></div>
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
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
        <div align="center" style="cursor:hand" onClick="ShowEnterCod()"><a><b>B&uacute;squeda<BR>
          Directa</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER style="width:'95%'">
 			   <TR> 
    			     <TD NOWRAP width="100%" >
  				<TABLE id="headTable" >
  				   <TR id="trdark">  
            		<th align=CENTER nowrap width="10%">&nbsp;</th>
            		<th align=CENTER nowrap width="10%">Compañia</th>
            		<th align=CENTER nowrap width="35%"> Descripción </th>
            		<th align=CENTER nowrap width="10%">Tipo de Proceso</th>
            		<th align=CENTER nowrap width="35%"> Descripción</th>
          			   </TR>
       			</TABLE>
  
   			    <div id="dataDiv1" class="scbarcolor">
    				<table id="dataTable" > 
          <%
                EDD400101Help.initRow();
				boolean firstTime = true;
				String chk = "";
        		while (EDD400101Help.getNextRow()) {
					if (firstTime) {
						firstTime = false;
						chk = "checked";
					} else {
						chk = "";
					}
                  datapro.eibs.beans.EDD400101Message msgList = (datapro.eibs.beans.EDD400101Message) EDD400101Help.getRecord();
		 %>
		 	<tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= msgList.getE01PAYCIA() %> "  <%=chk%> 
				onClick="javascript:getParameters('<%= msgList.getE01PAYCIA()%>','<%= msgList.getE01PAYCDE()%>');">
            </td>
            <td NOWRAP  align=CENTER width="10%"><%= msgList.getE01PAYCIA() %></td>
            <td NOWRAP  align=LEFT width="35%"><%= msgList.getD01PAYCIA() %></td>
            <td NOWRAP  align=CENTER width="10%"><%= msgList.getE01PAYCDE() %></td>
            <td NOWRAP  align=LEFT width="35%"><%= msgList.getD01PAYCDE() %></td>
          </tr>
          <%
                }
              %>
			 </table>
   			</div>
   			</TD>
   		      </TR>	
		    </TABLE>

	  
    				 
  <SCRIPT language="JavaScript">
 showChecked("CURRCODE");
  </SCRIPT>
  <SCRIPT language="JavaScript">
			
			function resizeDoc() {
      		 	divResize();
     		    adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      		}
	 		resizeDoc();   			
     		window.onresize=resizeDoc;        
     </SCRIPT>
  
     


<%}%>


  </form>

</body>
</html>

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Codigos de Referencia del Sistema</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ESD003001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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

function getParameters(tablecode) {

	document.forms[0].TABLECODE.value = tablecode;
  
}

function closeHiddenDivSearch(){
	setVisibility(document.getElementById("hiddenDivSearch"), "hidden");
}

function showHiddenDivSearch(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var hiddenDivSearch = document.getElementById("hiddenDivSearch");

	var y=evt.clientY + document.body.scrollTop;
	var x=evt.clientX + document.body.scrollLeft;
     
	cancelBub(evt);

	moveElement(hiddenDivSearch, y, x);
	setVisibility(hiddenDivSearch, "visible");
	 
}

document.onclick= closeHiddenDivSearch;


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Tablas de Codigos de Referencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cnof_enter_list.jsp, ESD0030"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0030" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="400">
    <input type=HIDDEN name="totalRow" value="0">
	
   
    <input type=HIDDEN name="opt">
  </p>
<div id="hiddenDivSearch" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Código de Referencia :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
         <input type="text" name="TABLECODE" size="7" maxlength="6" > 
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
	if ( ESD003001Help.getNoResult() ) {
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
		<div id="eibsSearch" align="center" style="cursor:pointer"><a><b>B&uacute;squeda<BR>Directa</b></a></div>
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
            		<th align=CENTER nowrap width="5%">&nbsp;</th>
            		<th align=CENTER nowrap width="20%">C&oacute;digo</th>
            		<th align=CENTER nowrap width="75%"> Descripci&oacute;n </th>
          			   </TR>
       			</TABLE>
  
   			    <div id="dataDiv1" class="scbarcolor">
    				<table id="dataTable" > 
          <%
                ESD003001Help.initRow();
				boolean firstTime = true;
				String chk = "";
        		while (ESD003001Help.getNextRow()) {
					if (firstTime) {
						firstTime = false;
						chk = "checked";
					} else {
						chk = "";
					}
                  datapro.eibs.beans.ESD003001Message msgList = (datapro.eibs.beans.ESD003001Message) ESD003001Help.getRecord();
		 %>
		 	<tr> 
            <td NOWRAP  align=CENTER width="5%"> 
              <input type="radio" name="CURRCODE" value="<%= msgList.getE01CNOTCN() %> "  <%=chk%> 
				onClick="javascript:getParameters('<%= msgList.getE01CNOTCN() %>');">
            </td>
            <td NOWRAP  align=CENTER width="20%"><%= msgList.getE01CNOTCN() %></td>
            <td NOWRAP  align=LEFT width="75%"><%= msgList.getE01CNODCN() %></td>
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
		
			function resizeDoc() {
      		 	divResize();
     		    adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      		}
	 		resizeDoc();   			
     		window.onresize=resizeDoc;        
     </SCRIPT>
  
     


<%}%>
  <SCRIPT language="JavaScript">
	 	document.getElementById("hiddenDivSearch").onclick=cancelBub;
		if(document.getElementById("eibsSearch"))
			document.getElementById("eibsSearch").onclick=showHiddenDivSearch; 
     </SCRIPT>

  </form>

</body>
</html>

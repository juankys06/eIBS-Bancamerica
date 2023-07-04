<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">

//<!-- Hide from old browsers
 
 function closeEnter(){
   	  enterNew.filters[0].apply();
      enterNew.style.visibility="hidden";
      enterNew.filters[0].Play();
 }
 
 function ShowEnterCod() {	 

	 var y=event.clientY + document.body.scrollTop;
     var x=event.clientX + document.body.scrollLeft;
	 cancelBub();
	 eval('enterNew.style.pixelTop=' + y);
     eval('enterNew.style.pixelLeft=' + x);
	 enterNew.filters[0].apply();
     enterNew.style.visibility="visible";
     enterNew.filters[0].Play();
}
 
 
  
   function goAction(op) {

    document.forms[0].opt.value = op;
    if (op == 1) { //new
          document.forms[0].SCREEN.value = 1; 
          document.forms[0].submit();
    } else {
      	   var formLength= document.forms[0].elements.length;
           var ok = false;
		   var row = "";
           for(n=0;n<formLength;n++)
           {
            	var elementName= document.forms[0].elements[n].name;
            	if(elementName == "ROW") 
            	{
					if (document.forms[0].elements[n].checked == true) {
               			ok = true;
						row =  document.forms[0].elements[n].value;
              			break;
					}
            	}
            }
      	 if ( ok ) {
      	 	if (op == 5) { //inquiry
    			
    			page= prefix +language + "EDEN020_our_denial_inq.jsp?ROW=" + row;
    			CenterWindow(page,700,500,1);
      	 	
      	 	} else if (op == 3) {//delete
      	 		
      	 		if (confirm('¿Está seguro de que desea eliminar este elemento?')) {
      	 			document.forms[0].SCREEN.value = 3;
      	 			document.forms[0].submit();
      	 		}
      	 	
      	 	} else
			   document.forms[0].submit();
           }
           else {
      			alert("Se debe seleccionar un nombre válido antes de realizar esta operación.");	   
           }
	 }
	 
  }
  
  
  function enter(row) {
          var op =document.forms[0].OPT.value;
         
          if (op == 5) { //inquiry	
    			page= prefix +language + "EDEN020_our_denial_inq.jsp?ROW=" + row;
    			CenterWindow(page,700,500,1);
      	 	
      	  } else  if (op == 3) {//delete
      	 		
      	 		if (confirm('¿Está seguro de que desea eliminar este elemento?')) {
      	 			document.forms[0].SCREEN.value = 3;   
      	 			document.forms[0].submit();   	 			
      	 	     }
      	  }	else
		     document.forms[0].submit();
		          
  }
 
//-->

function PrintPreviewPDF(nam) {
  CenterWindow('<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDEN020?SCREEN=700&RWDNAM=' + nam ,600,500,4);
}

function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;
</script>
<script type="text/javascript"> // this disables ENTER key

function stopRKey(evt) { 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
} 

document.onkeypress = stopRKey; 

</script>
<TITLE></TITLE>
</head>
<jsp:useBean id= "shrList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>
 
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDEN020" target="_parent" >
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
 <INPUT TYPE=HIDDEN NAME="OPT" VALUE="<%= userPO.getAccOpt() %>">
 
 <div id="enterNew" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onClick="cancelBub()"> 
    <table id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
      <tr id="trdark"> 
        <td align=CENTER width="20%"> 
          <div align="right">Nombre :</div>
        </td>
        <td align=CENTER width="80%"> 
          <div align="left"> 
            <input type="text" name="NEWNAME" size="60" maxlength="60" value="" >
          </div>
        </td>
      </tr>
       <tr id="trclear"> 
        <td align=center nowrap colspan="2"> 
          <input id="EIBSBTN" type=submit name="Submit1" value="Enviar" onClick="javascript:goAction(1)">
        </td>
      </tr>
    </table>
  </div>
 
<%
	if ( shrList.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100%>
 	  <TR>
         <TD> 
		   <div align="center"><b>No hay datos que correspondan con su criterio de búsqueda</b></div>
      	 </TD>
      </TR>
   	</TABLE>
<%
	} else {
%>
	 
  <TABLE class="tableinfo" align="center" style="width:'95%'">
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="10%"></TH>	
      <TH ALIGN=CENTER  nowrap width="90%">Nombre</TH>
    </TR>
    <%
                String E01DOAME = userPO.getIdentifier();
 		        int row;
				try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
    
                shrList.initRow();               
                while (shrList.getNextRow()) {
                    EDEN02001Message msgHelp = (EDEN02001Message) shrList.getRecord();			 
                    
       %>             
                    
          <TR>
		     <TD ALIGN=CENTER NOWRAP> 
		        <INPUT TYPE="radio" NAME="ROW" VALUE="<%= shrList.getCurrentRow() %>" <% if (shrList.getCurrentRow() == row) out.print("checked"); %> >
				
		     </TD>
		  	<td NOWRAP >
				<a href="javascript:enter('<%=shrList.getCurrentRow()%>')"><%=Util.formatCell(msgHelp.getE01DOAME())%></a>
		  	</td>

		</TR>
        <%        }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( shrList.getShowPrev() ) {
      			int pos =shrList.getFirstRec() - 21;
      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.tools.JSEDEN020?SCREEN=200&E01DOAME=" + E01DOAME + "&FromRecord=" + pos +"\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( shrList.getShowNext() ) {
      			int pos = shrList.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.tools.JSEDEN020?SCREEN=200&E01DOAME=" + E01DOAME + "&FromRecord=" + pos + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

        }
%> </TD>
	 </TR>
	 </TABLE>
<%      
  }
%> 
</FORM>

</BODY>
</HTML>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Garantías
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "chkList" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" /> 
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<script language="JavaScript">

  function goAction(op) {

    document.forms[0].opt.value = op;
    
    if (op == 3) {
//		Ask for confirmation in order to delete
		if (!confirm("Esta seguro que desea eliminar este Cheque?")) return;
	}
	document.forms[0].submit();		 
  }

 function setInfo(idxRow){
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].actRow.value = idxRow;   
  }
   
</script>
</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 
<h3 align="center">Listado de Cheques
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_protec_list.jsp,ECH0335">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0335">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
<INPUT TYPE=HIDDEN NAME="actRow" VALUE="">

<%
	if ( chkList.getNoResult() ) {

       String action = "";
	   try {
		action = userPO.getHeader20();
		userPO.setHeader20("");
       }
       catch (Exception e) {
       	action = "";
       }
       String pagename = "";
       try {
       	pagename = userPO.getHeader21();
       	userPO.setHeader21("");
       }
       catch (Exception e) {
       	pagename = "";
       }
               
       if ( action.equals("DO_MAINT") || action.equals("DO_NEW")) {
%>
       	<SCRIPT Language="Javascript">
<% 
            if ( !pagename.equals("") ) {
%>
	   			CenterWindow('<%= pagename %>',400,300,1);
<% 
             }
%>
         </SCRIPT>
<% 
		}
%> 

   <TABLE class="tbenter" width=100% height=90%>
   	<TR>
      <TD> 
        <div align="center"> 
          <table class="tableinfo">
            <tr > 
              <td nowrap> 
                <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
                  <tr id="trdark">
                    <td nowrap> 
                      <div align="right"><b>Cuenta :</b></div>
                    </td>
                    <td nowrap> 
                      <%= userPO.getIdentifier().trim()%>
                    </td> 
                    <td nowrap > 
                      <div align="right"><b>Cliente :</b></div>
                    </td>
                    <td nowrap> 
                      <div align="left"> 
                        <%=userPO.getCusNum().trim()%>
                      </div>
                    </td>
                    <td nowrap> 
                      <div align="right"><b>Nombre :</b> </div>
                    </td>
                    <td nowrap> 
                      <div align="left">
                        <%= userPO.getCusName().trim()%>
                      </div>
                    </td>                    
                  </tr>
                </table>
              </td>
            </tr>
          </table>
          <p>&nbsp;</p>
          <p><b>La cuenta seleccionada a&uacute;n no posee Cheques 
            asignados, por favor elija una de las siguientes opciones</b></p>
          <table class="tbenter" width="100%">
            <tr> 
              <td id=TDBKG> <a href="javascript:goAction(1)">Nuevo</a></td>
              <td id=TDBKG> <a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a></td>
            </tr>
          </table>
        </div>
      </TD>
     </TR>
   </TABLE>
  <%   		
	}
	else {
 
      	String action = "";
		try {
			action = userPO.getHeader20();
			userPO.setHeader20("");
        }
        catch (Exception e) {
       		action = "";
        }
        String pagename = "";
        try {
       	 	pagename = userPO.getHeader21();
       	 	userPO.setHeader21("");
        }
        catch (Exception e) {
       		pagename = "";
       	}
               
       if ( action.equals("DO_MAINT") || action.equals("DO_NEW")) {
%>
       	<SCRIPT Language="Javascript">
<% 
          if ( !pagename.equals("") ) {
%>
	   		CenterWindow('<%= pagename %>',620,500,2);
<% 
           }
%>
        </SCRIPT>
<% 
	}
%> 

  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark">
			<td nowrap width="16%" > 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%" > 
               <%= userPO.getIdentifier().trim()%>
            </td>
            <td nowrap width="11%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="10%" > 
              <div align="left"> 
                <%=userPO.getCusNum().trim()%>
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap width="42%" > 
              <div align="left">
                <%= userPO.getCusName().trim()%>
              </div>
            </td>            
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <table class="tbenter" width="100%">
    <tr>     
      <td class=TDBKG> <a href="javascript:goAction(1)">Nuevo</a></td>
      <td class=TDBKG> <a href="javascript:goAction(2)">Mantenimiento</a></td>
      <td class=TDBKG> <a href="javascript:goAction(3)">Borrar</a></td>
      <td class=TDBKG> <a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a></td>    
    </tr>
  </table>
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top">
        <TABLE id="headTable">
          <TR id="trdark"> 
            <TH ALIGN=CENTER NOWRAP></TH>
            <TH ALIGN=CENTER NOWRAP>No. Cheque</TH>
            <TH ALIGN=CENTER NOWRAP>Monto</TH>            
            <TH ALIGN=CENTER NOWRAP>Beneficiario</TH>
          </TR>
        </TABLE>
        
    <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
        <table id="dataTable"  NOWRAP>
    <%
         int row;
		 try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
         chkList.initRow();
         int k=1;
         while (chkList.getNextRow()) {
     %> 
            <TR> 
              <TD ALIGN=CENTER NOWRAP> 
                <INPUT TYPE="radio" NAME="ROW" VALUE="<%= chkList.getCurrentRow() %>" <% if (chkList.getCurrentRow() == row) out.print("checked");%> onClick="setInfo(<%= chkList.getCurrentRow() %>)">
              </TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(chkList.getRecord(0)) %></TD>
              <TD ALIGN=RIGHT NOWRAP><%= Util.fcolorCCY(chkList.getRecord(1)) %></TD>
              <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(chkList.getRecord(2)) %></TD>
            </TR>
      <%
              k++;
         }        
    %> 
          </table>
   </div>
   </TD>
  </TR>	
</TABLE>
      
<SCRIPT language="JavaScript">
  document.forms[0].totalRow.value="<%= k %>";
  function resizeDoc() {
       divResize();
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
  }
  showChecked("ROW");
  resizeDoc();
  window.onresize=resizeDoc;
     
</SCRIPT>


  <%
  }
%> 
</FORM>

</BODY>
</HTML>

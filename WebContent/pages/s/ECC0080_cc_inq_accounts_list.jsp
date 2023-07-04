<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Assign Accounts to Cards
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<SCRIPT Language="Javascript">
	builtNewMenu(dc_i_opt);
	initMenu();  
</SCRIPT>

<script language="JavaScript">
<!--
//-->
  function getAccountInfo(idx) {
  	document.forms[0].ROW.value = idx;
  	document.forms[0].submit();
  }

//-->

</script>
</head>

<body >
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 
<h3 align="center">Cuentas Asignadas a tarjeta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_inq_accounts_list.jsp, ECC0080"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0080I" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">

<% 
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
               
       if ( action.equals("DO_INQUIRY")) {
              if ( !pagename.equals("") ) {
%>
       	<SCRIPT Language="Javascript">
	   		CenterWindow('<%= pagename %>',620,500,2);
       	</SCRIPT>
<% 
              }
	}
%> 
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0"> 
   	      <table class="tableinfo">
            <tr > 
              <td nowrap> 
                <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
                  <tr id="trdark"> 
                    <td nowrap width="16%" > 
                      <div align="right"><b>Cliente :</b></div>
                    </td>
                    <td nowrap width="20%" > 
                      <div align="left"><b> 
                        <input type="text" readonly name="E01CARCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" >
                        </b> </div>
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right"><b>Nombre :</b> </div>
                    </td>
                    <td nowrap > 
                      <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                        <input type="text" name="E01CARNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
                        </font></font></font></div>
                    </td>
                  </tr>
                  <tr id="trdark"> 
                    <td nowrap width="16%" > 
                      <div align="right">Estado</div>
                    </td>
                    <td nowrap width="20%" > 
			           <input type="text" readonly name="E01CCRSTA" size="12" maxlength="10" 
			           	value="<% 
			           	if 		(userPO.getHeader2().equals("I")) out.print("Inactiva");
						else if	(userPO.getHeader2().equals("A")) out.print("Activa");
			           	else out.print("");	%>" >	
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right">Causal de Estado :</div>
                    </td>
                    <td nowrap > 
                      <div align="left">
                      	<input type="text" readonly name="E01CCRDSC" size="17" maxlength="15" value="<%= userPO.getHeader3()%>" >
                      </div>
                    </td>
                  </tr>         
                  <tr id="trdark"> 
                    <td nowrap width="16%" > 
                      <div align="right"></div>
                    </td>
                    <td nowrap > 
                      <div align="left">
                      </div>
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right">Numero de tarjeta :</div>
                    </td>
                    <td nowrap > 
                      <div align="left">
                      	<input type="text" readonly name="E01CCANUM" size="30" maxlength="30" value="<%= userPO.getHeader1().trim()%>" >
                      </div>
                    </td>
                  </tr>         
                </table>
              </td>
            </tr>
          </table>      
<%
	if ( appList.getNoResult() ) {
 %>
         <div align="center">
          <p>&nbsp;</p>
          <p><font size="3"><b>Esta tarjeta no tiene cuentas</b></font></p>
          <p>&nbsp; </p>
        </div>
      </TD></TR>
   		</TABLE>
  <%   		
	}
	else {
%>

  <p>&nbsp;</p>
  <center>
    <table  id="mainTable" class="tableinfo" >
      <tr > 
        <td NOWRAP valign="top"> 
          <table id="headTable">
            <tr id="trdark"> 
              <TH align=CENTER nowrap>Cuenta</TH>
              <TH align=CENTER nowrap>Tipo</TH>
              <TH align=CENTER nowrap>Numero Cliente</TH>
              <TH align=CENTER nowrap>Nombre Cliente</TH>
              <TH align=CENTER nowrap>Prim/Sec</TH>
            </tr>
          </table>
          <div id="dataDiv1" class="scbarcolor" > 
			   <table id="dataTable">
			    <%
	              	int row;
			  		try {
			  			row = Integer.parseInt(request.getParameter("ROW"));
			  		}catch(Exception e){
			  			row = 0;
			  		}
	               	appList.initRow();
		        	int k=1;
		            while (appList.getNextRow()) {
			     %> 
			    <TR> 
			      <TD ALIGN=LEFT NOWRAP>
			      	<a href="javascript:getAccountInfo('<%= appList.getCurrentRow() %>')"><%= appList.getRecord(4) %></a>
			      </TD>
			      <TD ALIGN=LEFT NOWRAP>
			      	<a href="javascript:getAccountInfo('<%= appList.getCurrentRow() %>')"><%= Util.formatCell(appList.getRecord(3)) %></a>
			      </TD>
			      <TD ALIGN=LEFT NOWRAP>
			      	<a href="javascript:getAccountInfo('<%= appList.getCurrentRow() %>')"><%= Util.formatCell(appList.getRecord(15)) %></a>
			      </TD>
			      <TD ALIGN=LEFT NOWRAP>
			      	<a href="javascript:getAccountInfo('<%= appList.getCurrentRow() %>')"><%= Util.formatCell(appList.getRecord(16)) %></a>
			      </TD>
			      <TD ALIGN=LEFT NOWRAP>
			      	<a href="javascript:getAccountInfo('<%= appList.getCurrentRow() %>')"><%= Util.formatCell(appList.getRecord(17)) %></a>
			      </TD>
			    </TR>
			    <%
			    		k++;
		            }
			    %> 
    			<INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= appList.getCurrentRow() %>">
			  </table>            
          </div>
        </td>
       </tr>   
    </table>
  </center>
      
<SCRIPT language="JavaScript">
  function resizeDoc() {
  	   document.forms[0].totalRow.value="<%= k %>";
       divResize();
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
  }
  resizeDoc();
  window.onresize=resizeDoc;
     
</SCRIPT>
  <%
  }
%> 
</FORM>

</BODY>
</HTML>


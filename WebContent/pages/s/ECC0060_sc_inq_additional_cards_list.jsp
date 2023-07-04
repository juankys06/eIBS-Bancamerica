<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 

<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Tarjetas adicionales
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
<!--
//-->
  
  function getCardInfo(idx) {
  	document.forms[0].E01CCRNUM.value = idx;
  	document.forms[0].submit();
  }

//-->

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
<h3 align="center">Tarjetas de <% if (userPO.getHeader2().equals("D")) out.print("Débito"); else out.print("Crédito"); %>
<BR>Cambio de Status<BR>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_inq_additional_cards_list.jsp, ECC0060"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="<%=request.getParameter("opt")%>">
<INPUT TYPE=HIDDEN NAME="E01CCRNUM" VALUE="">
<INPUT TYPE=HIDDEN NAME="search" VALUE="C">


<% 
    String action = "";
	try {
		action = userPO.getHeader20();
		userPO.setHeader20("");
    } catch (Exception e) {
       	action = "";
    }
    String pagename = "";
    try {
       	pagename = userPO.getHeader21();
       	userPO.setHeader21("");
    } catch (Exception e) {
       	pagename = "";
    }
               
    if ( action.equals("DO_INQUIRY")) {
              if ( !pagename.equals("") ) {
%>
       	<SCRIPT Language="Javascript">
			var win = window.opener;
			if (win == undefined) win = window;
			win.location.href="<%=pagename %>";
	   		//CenterWindow('<%= pagename %>',620,500,2);
       	</SCRIPT>
<% 
              }
	}
%> 

   	<TABLE class="tbenter" width=100%>
   	<TR>
      <TD> 
        <div align="center">
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
                        <input type="text" readonly name="E01CCRCUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" >
                        </b> </div>
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right"><b>Nombre :</b> </div>
                    </td>
                    <td nowrap > 
                      <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                        <input type="text" name="E01CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
                        </font></font></font></div>
                    </td>
                  </tr>
                  <tr id="trdark"> 
                    <td nowrap width="16%"> 
                      <div align="right"><b>Cuenta :</b></div>
                    </td>
                    <td nowrap width="20%"> 
                      <div align="left"> 
                        <input type="text" name="E01CCRCRA" size="12" maxlength="12" value="<%= userPO.getAccNum().trim()%>" readonly>
                      </div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="right"><b>Tipo : </b></div>
                    </td>
                    <td nowrap width="16%"> 
                      <div align="left"><b> 
                        <input type="text" name="E01TARTYP" size="15" maxlength="14" value="<% if (userPO.getHeader2().equals("D")) out.print("Débito"); else out.print("Crédito");%>" readonly>
                        </b> </div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>      
 		      <p>&nbsp;</p>
 <%
	if (appList.getNoResult()){
 %> 
          <p><font size="3"><b>Esta cuenta no tiene tarjetas</b></font></p>
<%   		
	} else {
 %>  
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0"> 
      			          
          <p>&nbsp; </p>
        </div>
      </TD>
      </TR>
   	</TABLE>  
  
  <center>
    <table  id="mainTable" class="tableinfo" >
      <tr > 
        <td NOWRAP valign="top"> 
          <table id="headTable">
            <tr id="trdark"> 
              <TH align=CENTER nowrap> 
                Numero Tarjeta</TH>
              <TH align=CENTER nowrap>Cuenta</TH>
              <TH align=CENTER nowrap>P/S</TH>
              <TH align=CENTER nowrap>Fecha Emision</TH>
              <TH align=CENTER nowrap>Fecha
              Expiracion</TH>
              <TH align=CENTER nowrap>Estado</TH>              
            </tr>
          </table>
          <div id="dataDiv1" class="scbarcolor" > 
			   <table id="dataTable">
     	<%
        appList.initRow(); 
		int indexRow = 0;
		String acct = "" ;

        while (appList.getNextRow()) {
            datapro.eibs.beans.ECC006001Message msgPart = (datapro.eibs.beans.ECC006001Message) appList.getRecord();
     	%>
        <TR>
	      <TD ALIGN=LEFT NOWRAP>
	      	<a href="javascript:getCardInfo('<%= msgPart.getE01CCRNUM() %>')"><%= msgPart.getE01CCRNUM() %></a>
	      </TD>
	      <TD ALIGN=CENTER NOWRAP>
	      	<a href="javascript:getCardInfo('<%= msgPart.getE01CCRNUM() %>')"><%= Util.formatCell(msgPart.getE01CCRCRA()) %></a>
	      </TD>
	      <TD ALIGN=CENTER NOWRAP>
	      	<%= Util.formatCell(msgPart.getE01CCRTCL()) %>
	      </TD>
		  <TD ALIGN=CENTER NOWRAP><%= Util.formatDate(msgPart.getE01CCRISM(),msgPart.getE01CCRISD(),msgPart.getE01CCRISY()) %></TD>
	      <TD ALIGN=CENTER NOWRAP><%= Util.formatDate(msgPart.getE01CCREXM(),msgPart.getE01CCREXD(),msgPart.getE01CCREXY()) %></TD>
	      <TD ALIGN=CENTER NOWRAP><%= msgPart.getE01CCRDSC() %></TD>
		</TR>    		
    	<%	
    		if( msgPart.getE01CCRTCL().equals("P")  ){
    			acct = msgPart.getE01CCRCRA() ;
    		}
    	
    		indexRow++;
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
  	   document.forms[0].totalRow.value="<%= indexRow %>";
       divResize();
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
  }
  showChecked("ROW");
  resizeDoc();
  window.onresize=resizeDoc;

  if( "<%=acct %>"  != "" ){
  	document.forms[0].E01CCRCRA.value = <%=acct %> ; 
  }
     
</SCRIPT>
  <%
  }
%> 
</FORM>

</BODY>
</HTML>


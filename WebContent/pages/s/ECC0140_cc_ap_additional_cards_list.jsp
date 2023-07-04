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
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
 
	builtNewMenu(cc_a_opt);
 
</SCRIPT>

<script language="JavaScript">
<!--
//-->
  function goAction(op) {

    document.forms[0].opt.value = op;
    if (op == 1) {
          document.forms[0].submit();
	 }
	 else {
      	 var formLength= document.forms[0].elements.length;
           var ok = false;
		   var en = "";
           for(n=0;n<formLength;n++)
           {
            	var elementName= document.forms[0].elements[n].name;
            	if(elementName == "ROW") 
            	{
					if (document.forms[0].elements[n].checked == true) {
               			ok = true;
						en = 'NUM_' + document.forms[0].elements[n].value;
              			break;
					}
            	}
            }
      	 if ( ok ) {
			document.forms[0].submit();
           }
           else {
      			alert("Debe seleccionar una tarjeta válida");	   
           }
	 }
	 
  }

//-->

</script>
</head>

<body onLoad="MM_preloadImages('<%=request.getContextPath()%>/images/s/nueva_over.gif','<%=request.getContextPath()%>/images/s/modificar_over.gif','<%=request.getContextPath()%>/images/s/exit_over.gif','<%=request.getContextPath()%>/images/s/delete_over.gif')">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> 
<h3 align="center">Tarjetas de <% if (userPO.getHeader2().equals("D")) out.print("Débito"); else out.print("Crédito");%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_ap_additional_cards_list.jsp , ECC0140"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060A" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="14">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="2">

<%
	if ( appList.getNoResult() ) {
 %>
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
               
       if ( action.equals("DO_MAINT") || action.equals("DO_NEW")) {
              if ( !pagename.equals("") ) {
%>
       	<SCRIPT Language="Javascript">
	   		CenterWindow('<%= pagename %>',620,500,2);
       	</SCRIPT>
<% 
              }
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
                        <input type="text" name="E01TARTYP" size="15" maxlength="14" value="<% if (userPO.getHeader2().equals("D")) out.print("Debit"); else out.print("Credit");%>" readonly>
                        </b> </div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>      

          <p>&nbsp;</p>
          <p><font size="3"><b>Esta cuenta no tiene tarjetas</b></font></p>
          <p>&nbsp; </p>
        </div>
      </TD></TR>
   		</TABLE>
  <%   		
	}
	else {
%> <% 
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
                        <input type="text" readonly name="E01PRICUN" size="9" maxlength="9" value="<%= userPO.getCusNum().trim()%>" >
                        </b> </div>
                    </td>
                    <td nowrap width="16%" > 
                      <div align="right"><b>Nombre :</b> </div>
                    </td>
                    <td nowrap > 
                      <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                        <input type="text" name="E01PRINA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
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
                        <input type="text" name="E01TARTYP" size="15" maxlength="14" value="<% if (userPO.getHeader2().equals("D")) out.print("Debito"); else out.print("Credito");%>" readonly>
                        </b> </div>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>      

  <p>&nbsp;</p>
  <table class="tbenter" width="100%">
    <tr> 
      <td align=CENTER> <a href="javascript:goAction(2)" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/INQUIRY_OVER.gif',1)"><img name="Image2" border="0" src="<%=request.getContextPath()%>/images/s/INQUIRY.gif" ></a></td>
    </tr>
  </table>
  <center>
    <table  id="mainTable" class="tableinfo" >
      <tr > 
        <td NOWRAP valign="top"> 
          <table id="headTable">
            <tr id="trdark"> 
              <TH align=CENTER nowrap>&nbsp;</TH>
              <TH align=CENTER nowrap> 
                Numero
              Tarjeta</TH>
              <TH align=CENTER nowrap> 
               Cliente
              </TH>
              <TH align=CENTER nowrap> 
               Fecha
              emision</TH>
              <TH align=CENTER nowrap>Fecha expiracion</TH>
            </tr>
          </table>
          <div id="dataDiv1" class="scbarcolor" > 
			   <table id="dataTable">
		     	<%
				int row;
				try{
					row = Integer.parseInt(request.getParameter("ROW"));
				}catch(Exception e){
					row = 0;
				}
		        appList.initRow(); 
				int indexRow = 0;

				String chk = "";
		        while (appList.getNextRow()) {
					if (appList.getCurrentRow() == row) {
						chk = "checked";
					} else {
						chk = "";
					}
        		    datapro.eibs.beans.ECC006001Message msgPart = (datapro.eibs.beans.ECC006001Message) appList.getRecord();
		     	%>               
		        <TR>
			      <TD ALIGN=CENTER NOWRAP> 
				    <INPUT TYPE="radio" NAME="ROW" VALUE="<%= appList.getCurrentRow() %>" <%=chk%> >
					<INPUT TYPE="hidden" NAME="NUM_<%= appList.getCurrentRow() %>" VALUE="<%= msgPart.getE01CCRNUM() %>">
			      </TD>
	    		  <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(msgPart.getE01CCRNUM()) %></TD>
			      <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(msgPart.getE01SECNA1()) %></TD>
				  <TD ALIGN=CENTER NOWRAP><%= Util.formatDate(msgPart.getE01CCRISM(),msgPart.getE01CCRISD(),msgPart.getE01CCRISY()) %></TD>
	    		  <TD ALIGN=CENTER NOWRAP><%= Util.formatDate(msgPart.getE01CCREXM(),msgPart.getE01CCREXD(),msgPart.getE01CCREXY()) %></TD>
				</TR>    		
		    	<%	indexRow++;
    			}
		    	%>   
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
     
</SCRIPT>
  <%
  }
%> 
</FORM>

</BODY>
</HTML>


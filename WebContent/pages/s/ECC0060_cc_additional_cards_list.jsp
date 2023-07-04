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
  function goAction(op) {

    document.forms[0].opt.value = op;
    if (op == 1) {
    	document.forms[0].submit();
	} else {
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
			if (op == 3){
			 	if(confirm("Está seguro que desea borrar esta cuenta?")){
					document.forms[0].submit();
				} 
			} else {
				document.forms[0].submit();
			}
        } else {
      		alert("Debe seleccionar una tarjeta válida");	   
		}
	 }
	 
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
<h3 align="center">
<% 
	String opt = request.getParameter("opt");
	if (opt == null) opt = "";

	if (userPO.getHeader2().equals("D")) 
		out.print("Tarjetas de Débito"); 
	else out.print("Tarjetas de Crédito");
%>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_additional_cards_list.jsp , ECC0060"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060" id="form1" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="<%= opt %>">

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
               
    if ( action.equals("DO_MAINT") || action.equals("DO_NEW")) {
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
                      <div align="right"><b>Identificaci&oacute;n :</b></div>
                    </td>
                    <td nowrap width="20%"> 
                      <div align="left"> 
                        <input type="text" name="E01CCRCID" size="15" maxlength="15" value="<%= userPO.getID() %>" readonly>
                      </div>
                    </td>
                    <td nowrap width="16%"> 
                    </td>
                    <td nowrap width="16%"> 
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
			if (!opt.equals("4")) {
 		%> 
          <p><font size="3"><b>Esta cuenta no tiene tarjetas, Por favor seleccione una de las siguientes opciones:</b></font></p>
          <table class="tbenter" width="100%">
            <tr> 
		      <TD class=TDBKG> 
		        <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
		      </TD>
		      <TD class=TDBKG> 
		        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
		      </TD>            
            </tr>
          </table>
		<%   		
			} else {
		%> 
         <p><font size="3"><b>Esta cuenta no tiene tarjetas</b></font></p>
		<%   		
			}
		%>           			          
 <%   		
	} else {
 %>  
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0"> 
  <p>&nbsp;</p>
       	<%  
			if (!opt.equals("4")) {
		%> 
			<table class="tbenter" width="100%">
			    <tr> 
			      <%--  
			      <TD class=TDBKG> 
			        <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
		    	  </TD>
			      <TD class=TDBKG> 
			        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
			      </TD>
				  --%>
			      <TD class=TDBKG> 
			        <div align="center"><a href="javascript:goAction(5)"><b>Asignar PIN</b></a></div>
			      </TD>
			      <TD class=TDBKG> 
			        <div align="center"><a href="javascript:goAction(6)"><b>Adicionar</b></a></div>
			      </TD>
			      <TD class=TDBKG> 
			        <div align="center"><a href="javascript:goAction('3')"><b>Eliminar</b></a></div>
			      </TD>
			      <TD class=TDBKG> 
		    	    <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
			      </TD>     
			    </tr>
			</table>
		<%   		
			} else {
		%> 
			<table class="tbenter" width="100%">
			    <tr> 
			      <TD class=TDBKG> 
			        <div align="center"><a href="javascript:goAction(4)"><b>Cambiar Estado</b></a></div>
		    	  </TD>
			      <TD class=TDBKG> 
		    	    <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
			      </TD>     
			    </tr>
			</table>
		<%   		
			}
		%>           			          
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
              <TH align=CENTER nowrap>&nbsp;</TH>
              <TH align=CENTER nowrap> 
                Numero Tarjeta</TH>
              <TH align=CENTER nowrap>Cuenta</TH>
              <TH align=CENTER nowrap>Fecha Emision</TH>
              <TH align=CENTER nowrap>Fecha
              Expiracion</TH>
              <TH align=CENTER nowrap>Estado</TH> 
              <TH align=CENTER nowrap>Prim/Sec</TH>             
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
		String custId = "" ;
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
	      <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(msgPart.getE01CCRCRA()) %></TD>
		  <TD ALIGN=CENTER NOWRAP><%= Util.formatDate(msgPart.getE01CCRISM(),msgPart.getE01CCRISD(),msgPart.getE01CCRISY()) %></TD>
	      <TD ALIGN=CENTER NOWRAP><%= Util.formatDate(msgPart.getE01CCREXM(),msgPart.getE01CCREXD(),msgPart.getE01CCREXY()).substring(0,3) + Util.formatDate(msgPart.getE01CCREXM(),msgPart.getE01CCREXD(),msgPart.getE01CCREXY()).substring(6) %></TD>
	      <TD ALIGN=CENTER NOWRAP><%= msgPart.getE01CCRDSC() %></TD>
	      <TD ALIGN=CENTER NOWRAP><%= msgPart.getE01CCRTCL() %></TD>
		</TR>    		
    	<%	
    		custId = msgPart.getE01CCRCID();
    		indexRow++;
    	}
    	%> 
			  </table>            
			  <SCRIPT> form1.E01CCRCID.value = "<%=custId%>"  </SCRIPT>
			  
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


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Promedios de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "datapro.eibs.master.Util,datapro.eibs.beans.*" %>

<jsp:useBean id="listAcc" class="datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id="userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

	function showInfo(idx){
	    var index = document.forms[0].ACCIDX.value;
		document.all["dataOpt"+index].style.display="none";
		document.all["dataOpt"+idx].style.display="";
		document.forms[0].ACCIDX.value = idx;
		document.forms[0].submit();
	}

	builtNewMenu(cr_i_opt);
	initMenu();
	
</SCRIPT>

</head>

<body>
<h3 align="center">Promedios de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="averages_rt.jsp,ECIF250"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF250" target="AVEFRAME">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="ACCIDX" VALUE="0">
  
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" class="tbhead" align="center">
          <tr id=trdark> 
            <td nowrap width="9%" align="right"> Cliente : </td>
            <td nowrap width="15%" align="left"> <%= userPO.getCusNum()%> </td>
            <td nowrap width="11%" align="right"> 
              <div align="right">RUT : </div>
            </td>
            <td nowrap width="10%" align="left"> <%= userPO.getID()%> </td>
            <td nowrap width="14%" align="right"> 
              <div align="right">Nombre: </div>
            </td>
            <td nowrap width="41%"align="left"> <%= userPO.getCusName()%> </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
   <BR>
  <% if (listAcc.getNoResult()) {%>
      <h3 align=center>No existen cuentas para este cliente</h3>
  <% } else { %>
 
  <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" valign=top>
              <div align="right"><b>Cuenta(s) :</b></div>
            </td>
            <td nowrap width="20%" valign=top> 
              <select name="accList" style="width:150" onchange="showInfo(this.selectedIndex)">  	       		
              	<%
              	String sel = "selected";
              	listAcc.initRow();
   		  		while (listAcc.getNextRow()) {
          			ECIF25002Message accBean = (ECIF25002Message) listAcc.getRecord();
          			out.print("<option value=\""+ accBean.getE02ACCNUM()+"\" "+ sel + ">"+ accBean.getE02ACCNUM() +"</option>");    	                    
          			if ( sel.equals("selected")) sel = "";
          		}	
          		%>
              </select> 
            </td>
            <td nowrap> 
               <%             
          		String view = "";
          		listAcc.initRow();
   		  		while (listAcc.getNextRow()) {
          			ECIF25002Message accBean = (ECIF25002Message) listAcc.getRecord();
          		%>
          		<div id="dataOpt<%=listAcc.getCurrentRow()%>" <%=view%>>
          			<table cellspacing="0" cellpadding="2" width="100%" border="0">
          				<tr id=trdark> 
				          <td nowrap >
				              <div align="right"><b>Saldo Disponible :</b> </div>
				          </td>
				          <td nowrap > 
				              <input type="text" name="E02AVALBL" size="15" readonly maxlength="15" value="<%= Util.formatCCY(accBean.getE02AVALBL()) %>" >
				          </td>
				        </tr>
				        <tr id=trclear> 
				           <td nowrap width="16%">
				              <div align="right"><b>Ultimo Deposito :</b> </div>             
				            </td>
				            <td nowrap width="20%"> 
				              <div align="left">
				                <input type="text" name="E02ACMLDA" size="15" readonly maxlength="15" value="<%= Util.formatCCY(accBean.getE02ACMLDA()) %>" >
				              </div>
				            </td>
				         </tr>
				         <tr id=trdark> 
				            <td nowrap width="16%">
				              <div align="right"><b>Fecha Apertura :</b> </div>             
				            </td>
				            <td nowrap width="20%"> 
				              <div align="left">
				                <input type="text" name="E02OPNDT1" size="3" readonly maxlength="2" value="<%= accBean.getE02OPNDT1()%>" >
				              	<input type="text" name="E02OPNDT2" size="3" readonly maxlength="2" value="<%= accBean.getE02OPNDT2()%>" >
				              	<input type="text" name="E02OPNDT3" size="3" readonly maxlength="2" value="<%= accBean.getE02OPNDT3()%>" >
				              </div>
				            </td>
				         </tr>
				         <tr id=trclear> 
				            <td nowrap width="16%">
				              <div align="right"><b>Estado :</b> </div>             
				            </td>
				            <td nowrap width="20%"> 
				              <div align="left">
				                <input type="text" name="E02DSCAST" size="23" readonly maxlength="20" value="<%= accBean.getE02DSCAST() %>" >
				              </div>
				            </td>
				         </tr>
				         <tr id=trdark> 
				            <td nowrap width="16%">
				              <div align="right"><b>Fecha Cambio de Estado :</b> </div>             
				            </td>
				            <td nowrap width="20%"> 
				              <div align="left">
				                <input type="text" name="E02LSCST1" size="3" readonly maxlength="2" value="<%= accBean.getE02LSCST1()%>" >
				              	<input type="text" name="E02LSCST2" size="3" readonly maxlength="2" value="<%= accBean.getE02LSCST2()%>" >
				              	<input type="text" name="E02LSCST3" size="3" readonly maxlength="2" value="<%= accBean.getE02LSCST3()%>" >
				              </div>
				            </td>
				          </tr>
				        </table>   
          		</div>	
          		<%
          		    
          		    if ( view.equals("")) view = "style=\"display:none\"";
          		 }         
                %>            
            </td>              
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <IFRAME NAME=AVEFRAME SCROLLING=NO FRAMEBORDER=0 ALLOWTRANSPARENCY=TRUE WIDTH=100% HEIGHT=490 SRC="<%=request.getContextPath()%>/pages/s/EWD0005_client_help_blank.jsp"></IFRAME>  
  <SCRIPT Language="Javascript">
    showInfo(document.forms[0].accList.selectedIndex);
  </SCRIPT>
  <% } %>
  </form>
</body>
</html>

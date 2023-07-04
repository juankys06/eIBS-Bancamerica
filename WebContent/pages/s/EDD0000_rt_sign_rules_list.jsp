<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>

<HTML>
<HEAD>
<TITLE>
Approval List Account
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
 
<jsp:useBean id= "rulesList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT language="Javascript">

	builtNewMenu(rt_m_opt);
	
	function goAction(opt) {
     	
       document.forms[0].action.value = opt;            
       if (opt == "3") { //Delete
     	 ok = confirm("Esta seguro que desea eliminar el registro seleccionado?");
	  	 if (ok) document.forms[0].submit();
	   } else  {
	  		if (opt == "1") { //New
	  			page= prefix + language + "EDD0000_rt_sign_rules_maint.jsp"; 
	  		} else { // Maintenance
	  			page= prefix + language + "EDD0000_rt_sign_rules_maint.jsp?ROW=" + document.forms[0].selRow.value;
	  		}
	  		CenterWindow(page,450,520,1);
  	   }        	   
	}
	
	function setInfo(idx) {
	   document.forms[0].selRow.value = idx;
	}
    
    initMenu();
    
</SCRIPT>

</HEAD>

<BODY>


 <h3 align="center">
  Listado de Reglas de Firma 
 <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_sign_rules_list.jsp,EDD0000"> 
    </h3>
   <hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000F" >

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="action" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="selRow" VALUE="0">   


  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E02NA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E04CUN" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
<%
	if ( rulesList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=60%>
   	<TR>
      <TD> 
        <div align="center">           
          <p>&nbsp;</p>
          <p><b>No existen reglas asociadas a esta cuenta,
             por favor elija una de las siguientes opciones</b></p>
          <table class="tbenter" width="100%">
            <tr> 
              <td class=TDBKG> <a href="javascript:goAction(1)">Nuevo</a></td>
              <td class=TDBKG> <a href="javascript:checkClose()">Salir</a></td>
            </tr>
          </table>
        </div>
      </TD>
     </TR>
   		
<%   		
	}
	else {
	
	boolean firstTime = true;
 	String chk = "";
%>

  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
    
  <table class="tbenter" width="100%">
    <tr>     
      <td class=TDBKG><a href="javascript:goAction(1)">Nuevo</a></td>
      <td class=TDBKG><a href="javascript:goAction(2)">Mantenimiento</a></td>
      <td class=TDBKG><a href="javascript:goAction(3)">Borrar</a></td>    
    </tr>
  </table>
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top">
        <TABLE id="headTable">
          <TR id="trdark"> 
            <TH ALIGN=CENTER nowrap></TH>
      		<TH ALIGN=CENTER nowrap>Regla</TH>
      		<TH ALIGN=CENTER nowrap>Descripcion /Categoria(s)</TH>
      		<TH ALIGN=CENTER nowrap>Monto</TH>
      		<TH ALIGN=CENTER nowrap>Moneda</TH>
      		<TH ALIGN=CENTER nowrap>Valida Desde</TH>
      		<TH ALIGN=CENTER nowrap>Valida Hasta</TH>
      		<TH ALIGN=CENTER nowrap>Status</TH>
          </TR>
        </TABLE>
        
    <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
        <table id="dataTable" nowrap>
    <%
         rulesList.initRow();
         int k=1;
         while (rulesList.getNextRow()) {
            if (firstTime) {
						firstTime = false;
						chk = "checked";
				  }
				  else {
							chk = "";
				  }
            datapro.eibs.beans.DataSignRule dsr = (datapro.eibs.beans.DataSignRule) rulesList.getRecord();
     %> 
            <TR> 
              <TD ALIGN=CENTER NOWRAP> 
                <INPUT TYPE="radio" NAME="ROW" VALUE="<%= rulesList.getCurrentRow() %>" <%=chk%> onclick="setInfo('<%= rulesList.getCurrentRow()%>')">
              </TD>
              <TD ALIGN=CENTER NOWRAP><%= rulesList.getCurrentRow() + 1 %></TD>
              <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(dsr.getSigRule()) %></TD>
              <TD ALIGN=RIGHT NOWRAP><% if (dsr.getAmount().equals("0.00") || dsr.getAmount().equals("0")) out.print("Sin Limite"); else out.print(Util.formatCCY(dsr.getAmount())); %></TD>
              <TD ALIGN=CENTER NOWRAP><%= dsr.getCCYCode() %></TD>
              <TD ALIGN=CENTER NOWRAP><%= dsr.getDayFrom() + "/" + dsr.getMonthFrom() + "/" + dsr.getYearFrom() %></TD>
              <TD ALIGN=CENTER NOWRAP><%= dsr.getDayTo() + "/" + dsr.getMonthTo() + "/" + dsr.getYearTo() %></TD>
              <TD ALIGN=CENTER NOWRAP><% if (dsr.getStatus().equals("0")) out.print("Inactiva"); else out.print("Activa"); %></TD>
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
  
 <%                
 String error = request.getParameter("ERROR");
   if ( error != null ) {    
   out.println("alert(\" Error de actualizacion. Por favor contacte con el Administardor de sistema\")");      
 	}
 %>
     
</SCRIPT>


  <%
   
  }
%> 

</FORM>
</BODY>
</HTML>

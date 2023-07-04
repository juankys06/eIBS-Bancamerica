<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>

<HTML>
<HEAD>
<TITLE>
Signers List
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
 
<jsp:useBean id= "signersList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_m_opt);
<%   
}
if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_m_opt);
<%   
}
if ( userPO.getOption().equals("CD") ) {
%>
	builtNewMenu(cd_m_opt);
<%   
}
%>

	
	function goAction(opt) {
     	
       document.forms[0].OPTION.value = opt;            
       if (opt == "9") { //Delete
       
     	 ok = confirm("Esta seguro que desea eliminar el registro seleccionado?");
	  	 if (ok) document.forms[0].submit();
	  	 
	   } else  {
	   
	        if ((opt == "1") && (document.forms[0].totalRow.value == "99") ) {
	           alert("El máximo número de firmantes es 99");
	           return;
	   		}

	        if ((opt == "1") && (document.forms[0].totalRow.value != "99") ) {
		        page= prefix + language + "EDD0000_rt_signers.jsp?OPTION=" + opt;
		  		CenterWindow(page,450,600,1);
	   		}
	   		
	  		if (opt == "2") { //Maint	
		        page= prefix + language + "EDD0000_rt_signers.jsp?OPTION=" + opt;
	  			page= page + "&ROW=" + document.forms[0].selRow.value;
		  		CenterWindow(page,450,600,1);
	  		}
	  		if (opt == "3") { //Image Sign
				var CUN = document.forms[0].CUN.value ;    
				var NAM = document.forms[0].NAM.value ;    
				var CUN2 = document.forms[0].E02CUN.value ;    
				var NAM3 = document.forms[0].E02NA1.value ;    
    			page = "<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=1&Type=C&CUN="+CUN+"&NAM="+NAM+"&E02CUN="+CUN2+"&E02NA1="+NAM3;
    			CenterNamedWindow(page,'Information',700,600,2);
	  		}
	  		if (opt == "4") { //Condiciones especiales de firmas	
				document.forms[0].action = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5700"
				document.forms[0].SCREEN.value="19";
				document.forms[0].submit();		  	       	       
	  		}
  	   }        	   
	}
	

	function setInfo(idx,cun,nam) {
	   document.forms[0].selRow.value = idx;
	   document.forms[0].CUN.value = cun;
	   document.forms[0].NAM.value = nam;
	}

	
	function callDocC() {
		var CUN = document.forms[0].CUN.value ;    
		var NAM = document.forms[0].NAM.value ;    
		var CUN2 = document.forms[0].E02CUN.value ;    
		var NAM3 = document.forms[0].E02NA1.value ;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=1&Type=C&CUN="+CUN+"&NAM="+NAM+"&E02CUN="+CUN2+"&E02NA1="+NAM3;
    	CenterNamedWindow(page,'Information',700,600,2);
	}
	
	
	function callAllImg() {

	var ind= document.forms[0].IND.value  ;
	var list= "" ;
	var names= "" ;
	
	for (i = 1; i <= ind; i++) { 
		if (i == 1) {
		list = eval("document.forms[0].E01RCN"+i+".value");
		names = eval("document.forms[0].E01RCN"+i+".value")+"="+eval("document.forms[0].E01MA1"+i+".value");
		} else {
		list = list +","+ eval("document.forms[0].E01RCN"+i+".value");
		names = names+"_"+eval("document.forms[0].E01RCN"+i+".value")+"="+eval("document.forms[0].E01MA1"+i+".value");
		}
	}
		var CUN = document.forms[0].CUN.value ;    
		var NAM = document.forms[0].NAM.value ;    
		var CUN2 = document.forms[0].E02CUN.value ;    
		var NAM3 = document.forms[0].E02NA1.value ;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=6&Type=C&CUN="+CUN+"&NAM="+NAM+"&E02CUN="+CUN2+"&E02NA1="+NAM3+"&LIST="+list+"&NAMES="+names;
    	CenterNamedWindow(page,'Information',700,600,2);
	}
    
    initMenu();
    
</SCRIPT>

</HEAD>

<BODY>


 <h3 align="center">
  Listado de Firmantes 
 <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_signers_list.jsp, EDD0000"> 
    </h3>
   <hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500" >

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="20">
<INPUT TYPE=HIDDEN NAME="OPTION" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="selRow" VALUE="0">   
<INPUT TYPE=HIDDEN NAME="CUN" VALUE="<%= userPO.getHeader2().trim()%>">
<INPUT TYPE=HIDDEN NAME="NAM" VALUE="<%= userPO.getHeader3().trim()%>">
<INPUT TYPE=HIDDEN NAME="LIST" VALUE="">   
<INPUT TYPE=HIDDEN NAME="NAMES" VALUE="">   


  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
  <% if (!userPO.getHeader2().equals("0")) {%> 
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
  <% } %> 
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
	if ( signersList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=60%>
   	<TR>
      <TD> 
        <div align="center">           
          <p>&nbsp;</p>
          <p><b>No existen Firmantes asociados a esta cuenta,
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
    
  <table class="tbenter" width="100%">
    <tr>     
      <td class=TDBKG><a href="javascript:goAction(1)">Nuevo</a></td>
      <td class=TDBKG><a href="javascript:goAction(2)">Mantenimiento</a></td>
      <%--  <td class=TDBKG><a href="javascript:goAction(3)">Imagen Firma</a></td> --%>
      <%--  <td class=TDBKG><a href="javascript:goAction(4)">Condic. Especiales Firmas</a></td> --%>
      <td class=TDBKG><a href="javascript:goAction(9)">Borrar</a></td>    
    </tr>
  </table>
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top">
        <TABLE id="headTable">
          <TR id="trdark"> 
            <TH ALIGN=CENTER nowrap></TH>
      		<TH ALIGN=CENTER NOWRAP>N&uacute;mero</TH>
            <TH ALIGN=CENTER NOWRAP>Cliente</TH>
            <TH ALIGN=CENTER NOWRAP>Acci&oacute;n</TH>
          </TR>
        </TABLE>
        
    <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
        <table id="dataTable" nowrap>
    <%
         signersList.initRow();
         int k=1;
         while (signersList.getNextRow()) {
         
         	datapro.eibs.beans.EDD550001Message dds = (datapro.eibs.beans.EDD550001Message) signersList.getRecord();
     		
     		if (dds.getE01RTY().equals("D")) continue;
     		
            if (firstTime) {
						firstTime = false;
						chk = "checked";
				  }
				  else {
							chk = "";
				  }
		    
            
     		
     %> 
            <TR> 
              <TD ALIGN=CENTER NOWRAP> 
                <INPUT TYPE="radio" NAME="ROW" VALUE="<%= signersList.getCurrentRow() %>" <%=chk%> onclick="setInfo('<%= signersList.getCurrentRow()%>','<%= dds.getE01RCN()%>','<%= dds.getE01MA1()%>')">
              </TD>
              <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(dds.getE01RCN()) %></TD>
              <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(dds.getE01MA1()) %></TD>
              <TD ALIGN=CENTER NOWRAP><% if (dds.getE01RTY().equals("D")) out.print("Borrado");
                else if (dds.getE01RTY().equals("N")) out.print("Nuevo");
                else if (dds.getE01RTY().equals("M")) out.print("Modificado");%></TD>
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

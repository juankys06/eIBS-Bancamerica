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
	builtNewMenu(rt_i_opt_f);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt_f);
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
	   		
	        page= prefix + language + "EDD0000_rt_condit_signers_maint.jsp?OPTION=" + opt + "&CCY=" + document.forms[0].E01DEACCY.value;
	  		if (opt == "2") { //Maint	
	  			page= page + "&ROW=" + document.forms[0].selRow.value ;
	  		}
	  		
	  		CenterWindow(page,600,550,1);
  	   }        	   
	}
	

	function setInfo(idx,sqn,nam,cs1,cs2,cs3,ccy) {
	   document.forms[0].selRow.value = idx;
	   document.forms[0].SQN.value = sqn;
	   document.forms[0].NAM.value = nam;
	   document.forms[0].E01ACC.value = document.forms[0].E01CUN.value;
	   document.forms[0].E01CS1.value = cs1;
	   document.forms[0].E01CS2.value = cs2;
	   document.forms[0].E01CS3.value = cs3;
	   document.forms[0].E01CCY.value = ccy;
	}
    
    initMenu();
    
</SCRIPT>

</HEAD>

<BODY>


 <h3 align="center">
  Consulta Condiciones Especiales de Firmantes 
 <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_inq_condit_signers_list.jsp, EDD0000"> 
    </h3>
   <hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5700" >

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="20">
<INPUT TYPE=HIDDEN NAME="OPTION" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="selRow" VALUE="0">   
<INPUT TYPE=HIDDEN NAME="CUN" VALUE="<%= userPO.getHeader2().trim()%>">
<INPUT TYPE=HIDDEN NAME="NAM" VALUE="<%= userPO.getHeader3().trim()%>">
<INPUT TYPE=HIDDEN NAME="SQN" VALUE="">   
<INPUT TYPE=HIDDEN NAME="LIST" VALUE="">   
<INPUT TYPE=HIDDEN NAME="NAMES" VALUE="">   
<INPUT TYPE=HIDDEN NAME="E01ACC" VALUE="">   
<INPUT TYPE=HIDDEN NAME="E01CS1" VALUE="">   
<INPUT TYPE=HIDDEN NAME="E01CS2" VALUE="">   
<INPUT TYPE=HIDDEN NAME="E01CS3" VALUE="">   
<INPUT TYPE=HIDDEN NAME="E01CCY" VALUE="">   

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
                <input type="text" name="E01CUN" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
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
          <p><b>No existen Condiciones de Firmantes asociados a esta cuenta,
             por favor elija una de las siguientes opciones</b></p>
          <table class="tbenter" width="100%">
          </table>
        </div>
      </TD>
     </TR>
</TABLE> 		
<%   		
	}
	else {
	
	boolean firstTime = true;
 	String chk = "";
%>
    
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top">
        <TABLE id="headTable">
          <TR id="trdark"> 
      		<TH ALIGN=CENTER NOWRAP>#Cond.</TH>
      		<TH ALIGN=CENTER NOWRAP>Nombre Firmante</TH>
            <TH ALIGN=CENTER NOWRAP>Categoria <BR>de Firma</TH>
            <TH ALIGN=CENTER NOWRAP>Tipo de Firma</TH>
      		<TH ALIGN=CENTER NOWRAP>Nombre Firmante</TH>
            <TH ALIGN=CENTER NOWRAP>Categoria <BR>de Firma</TH>
      		<TH ALIGN=CENTER NOWRAP>Nombre Firmante</TH>
            <TH ALIGN=CENTER NOWRAP>Categoria <BR>de Firma</TH>
            <TH ALIGN=CENTER NOWRAP>Monto Limite</TH>
<%--
            <TH ALIGN=CENTER NOWRAP>Acción</TH>
--%>
          </TR>
        </TABLE>
        
    <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
        <table id="dataTable" nowrap>
    <%
         signersList.initRow();
         int k=1;
         while (signersList.getNextRow()) {
         
         	datapro.eibs.beans.EDD570001Message dds = (datapro.eibs.beans.EDD570001Message) signersList.getRecord();
     		
     		if (dds.getH01WK1().equals("D")) continue;
     		
            if (firstTime) {
						firstTime = false;
						chk = "checked";
				  }
				  else {
							chk = "";
				  }
		    
            
     		
     %> 
            <TR> 
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(dds.getE01SQN()) %></TD>
              <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(dds.getE01NM1()) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(dds.getE01CS1()) %></TD>
              <TD ALIGN=CENTER NOWRAP><% if (dds.getE01TSG().equals("1")) out.print("Individual"); %>
				<% if (dds.getE01TSG().equals("2")) out.print("Mancomunada"); %>
				<% if (dds.getE01TSG().equals("3")) out.print("Indistinta"); %>
              </TD>
              <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(dds.getE01NM2()) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(dds.getE01CS2()) %></TD>
              <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(dds.getE01NM3()) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(dds.getE01CS3()) %></TD>
              <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(dds.getE01AMN()) %></TD>
<%--
              <TD ALIGN=CENTER NOWRAP><% if (dds.getE01FL1().equals("9")) out.print("Borrado");
                else if (dds.getE01FL1().equals("1")) out.print("Nuevo");
                else if (dds.getE01FL1().equals("2")) out.print("Modificado");%></TD>
--%>
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

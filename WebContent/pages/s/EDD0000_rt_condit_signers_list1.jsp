<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>

<HTML>
<HEAD>
<TITLE>
Condiciones Especiales de Firmantes
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "signersList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "rtCond" class= "datapro.eibs.beans.EDD570001Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<%
if (!error.getERRNUM().equals("0")) {
      error.setERRNUM("0");
%>
	<SCRIPT Language="Javascript">
		showErrors();
	</SCRIPT>
<%}else{%>
	<SCRIPT Language="Javascript">
	</SCRIPT>
<%}%>


<SCRIPT Language="Javascript">

<%
if (!userPO.getPurpose().equals("APPROVAL_INQ") ) {
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_m_opt_f);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_m_opt_f);
<%   
}
}else{
%>
	builtNewMenu(rt_a_opt);
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
	   document.forms[0].E01SG1.value = "";
	   document.forms[0].E01NM1.value = "";
	   document.forms[0].E01CS1.value = "";
	   document.forms[0].E01TSG.value = "";
	   document.forms[0].E01SG2.value = "";
	   document.forms[0].E01NM2.value = "";
	   document.forms[0].E01CS2.value = "";
	   document.forms[0].E01SG3.value = "";
	   document.forms[0].E01NM3.value = "";
	   document.forms[0].E01CS3.value = "";
	   document.forms[0].E01AMN.value = "";
	   document.forms[0].E01CCYW.value = "";
	   lastrec = document.forms[0].totalRow.value;
	   document.forms[0].E01SQN.value = "";

       if (opt == "1") { //new

<%
	     String pos = "";
	     pos = (signersList.getNoResult()) ? "01" : "" + (signersList.getLastRec() + 2);
	   	 if (pos.length() == 1) pos = "0" + pos;
	   	 rtCond.setE01SQN(pos);
	 	 rtCond.setE01ACC(userPO.getIdentifier().trim());
 	 	 rtCond.setH01WK1("N");
     	 rtCond.setE01FL1("1");

%>
	   document.forms[0].E01SQN.value = <%= rtCond.getE01SQN() %>;
	 }



  	   }        	   
	}
	

	function setInfo(idx,sqn,sg1,nam1,cs1,tsg,sg2,nam2,cs2,sg3,nam3,cs3,amn,ccy) {
	   document.forms[0].selRow.value = idx;
	   document.forms[0].SQN.value = sqn;
	   document.forms[0].NAM.value = nam1;
	   document.forms[0].E01ACC.value = document.forms[0].E01CUN.value;
	   document.forms[0].E01SG1.value = sg1;
	   document.forms[0].E01NM1.value = nam1;
	   document.forms[0].E01CS1.value = cs1;
	   document.forms[0].E01TSG.value = tsg;
	   document.forms[0].E01SG2.value = sg2;
	   document.forms[0].E01NM2.value = nam2;
	   document.forms[0].E01CS2.value = cs2;
	   document.forms[0].E01SG3.value = sg3;
	   document.forms[0].E01NM3.value = nam3;
	   document.forms[0].E01CS3.value = cs3;
	   document.forms[0].E01AMN.value = amn;
	   document.forms[0].E01CCYW.value = ccy;
	   document.forms[0].E01SQN.value = sqn;
	}

 function goUpdVal(){
	document.forms[0].SCREEN.value=20;
	document.forms[0].H01WK1.value="M";
    document.forms[0].E01ACC.value = document.forms[0].E01CUN.value;
	document.forms[0].submit();	 

 }

	function inzRec() {
	   document.forms[0].E01SG1.value = "";
	   document.forms[0].E01NM1.value = "";
	   document.forms[0].E01CS1.value = "";
	   document.forms[0].E01TSG.value = "";
	   document.forms[0].E01SG2.value = "";
	   document.forms[0].E01NM2.value = "";
	   document.forms[0].E01CS2.value = "";
	   document.forms[0].E01SG3.value = "";
	   document.forms[0].E01NM3.value = "";
	   document.forms[0].E01CS3.value = "";
	   document.forms[0].E01AMN.value = "";
	   document.forms[0].E01CCYW.value = "";
	   document.forms[0].E01SQN.value = "";
 } 
    
 initMenu();
    
</SCRIPT>

</HEAD>

<BODY>


 <h3 align="center">
  Condiciones Especiales de Firmantes 
 <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_condit_signers_list1.jsp, EDD0000"> 
    </h3>
   <hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5700" >

<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="20">
<INPUT TYPE=HIDDEN NAME="OPTION" VALUE="2">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="selRow" VALUE="0">   
<INPUT TYPE=HIDDEN NAME="CUN" VALUE="<%= userPO.getHeader2().trim()%>">
<INPUT TYPE=HIDDEN NAME="NAM" VALUE="<%= userPO.getHeader3().trim()%>">
<INPUT TYPE=HIDDEN NAME="SQN" VALUE="">   
<INPUT TYPE=HIDDEN NAME="LIST" VALUE="">   
<INPUT TYPE=HIDDEN NAME="NAMES" VALUE="">   
<INPUT TYPE=HIDDEN NAME="E01ACC" VALUE="">   
<INPUT TYPE=HIDDEN NAME="E01CS1W" VALUE="">   
<INPUT TYPE=HIDDEN NAME="E01CS2W" VALUE="">   
<INPUT TYPE=HIDDEN NAME="E01CS3W" VALUE="">   
<INPUT TYPE=HIDDEN NAME="E01CCYW" VALUE="">   
<INPUT TYPE=HIDDEN NAME="H01WK1" VALUE="">   

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
	boolean firstTime = true;
 	String chk = "";
%>
    
  <table class="tbenter" width="100%">
<%
	if (!userPO.getPurpose().equals("APPROVAL_INQ") ) {
%>
    <tr>     
      <td class=TDBKG><a href="javascript:goAction(1)">Nuevo</a></td>
<%--
      <td class=TDBKG><a href="javascript:goAction(2)">Mantenimiento</a></td>
--%>
      <td class=TDBKG><a href="javascript:goAction(9)">Borrar</a></td>    
    </tr>
<%
	}
%>
  </table>
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top">
        <TABLE id="headTable" width="100%">
          <TR id="trdark"> 
            <TH ALIGN=CENTER nowrap></TH>
      		<TH ALIGN=CENTER NOWRAP>#Cond.</TH>
      		<TH ALIGN=CENTER NOWRAP>Nombre Firmante</TH>
            <TH ALIGN=CENTER NOWRAP>Categoria <BR>de Firma</TH>
            <TH ALIGN=CENTER NOWRAP>Tipo de Firma</TH>
      		<TH ALIGN=CENTER NOWRAP>Nombre Firmante</TH>
            <TH ALIGN=CENTER NOWRAP>Categoria <BR>de Firma</TH>
      		<TH ALIGN=CENTER NOWRAP>Nombre Firmante</TH>
            <TH ALIGN=CENTER NOWRAP>Categoria <BR>de Firma</TH>
            <TH ALIGN=CENTER NOWRAP>Monto Limite</TH>
<%
	if (!userPO.getPurpose().equals("INQUIRY") ) {
%>
            <TH ALIGN=CENTER NOWRAP>Acción</TH>
<%
	}
%>
          </TR>
        </TABLE>
        
    <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
        <table id="dataTable" nowrap width="100%">
    <%
         signersList.initRow();
         int k=0;
         int ind = 0;
         int ind2 =0;
         while (signersList.getNextRow()) {
         
         	datapro.eibs.beans.EDD570001Message dds = (datapro.eibs.beans.EDD570001Message) signersList.getRecord();
     		
     		if (dds.getH01WK1().equals("D")) continue;
     		
            if (firstTime) {
						firstTime = false;
//						chk = "checked";
				  }
				  else {
							chk = "";
				  }
		    
            
     		
     %> 
		 <% ind  = ind + 1; %>
		 <% ind2 = ind % 2; %>
            <TR id="<% if (ind2==0) {out.print("trdark");} else {out.print("trclear");} %>">  
              <TD ALIGN=CENTER NOWRAP> 
<%
	if (!userPO.getPurpose().equals("APPROVAL_INQ") ) {
%>
                <INPUT TYPE="radio" NAME="ROW" VALUE="<%= signersList.getCurrentRow() %>" <%=chk%> onclick="setInfo('<%= signersList.getCurrentRow()%>','<%= dds.getE01SQN()%>','<%= dds.getE01SG1()%>','<%= dds.getE01NM1()%>','<%= dds.getE01CS1()%>','<%= dds.getE01TSG()%>','<%= dds.getE01SG2()%>','<%= dds.getE01NM2()%>','<%= dds.getE01CS2()%>','<%= dds.getE01SG3()%>','<%= dds.getE01NM3()%>','<%= dds.getE01CS3()%>','<%= dds.getE01AMN()%>','<%= dds.getE01CCY()%>')">
<%
	}
%>
              </TD>
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
<%
	if (!userPO.getPurpose().equals("INQUIRY") ) {
%>
              <TD ALIGN=CENTER NOWRAP><% if (dds.getE01FL1().equals("9")) out.print("Borrado");
                else if (dds.getE01FL1().equals("1")) out.print("Nuevo");
                else if (dds.getE01FL1().equals("2")) out.print("Modificado");%></TD>
<%
	}
%>
             </TR>
      <%
              k++;
         }        
    %> 
<%
	if (!userPO.getPurpose().equals("APPROVAL_INQ") ) {
%>
            <TR> 
              <TD ALIGN=CENTER NOWRAP> 
              </TD>
              <TD ALIGN=CENTER NOWRAP>
              	<INPUT type="text" name="E01SQN" size="1" maxlength="2" value="<%=rtCond.getE01SQN()%>" readonly>
			  </TD>
              <TD ALIGN=LEFT NOWRAP>
					<INPUT type="text" name="E01SG1" size="9" maxlength="9" value="<%=rtCond.getE01SG1()%>">
				   <a href="javascript:GetCustomerDetails('E01SG1','E01NM1','','','','','','','','','','','','','','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>
				   <br>
					<INPUT type="text" name="E01NM1" size="30" maxlength="30" value="<%=rtCond.getE01NM1()%>" readonly>
			  </TD>
              <TD ALIGN=CENTER NOWRAP>
              <input type="text" name="E01CS1" maxlength="1" size="2" value="<%= rtCond.getE01CS1()%>">
              <a href="javascript:GetCodeCNOFC('E01CS1','FI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
			  </TD>
              <TD ALIGN=CENTER NOWRAP>
              <select name="E01TSG">
                <option value=" " <% if (!(rtCond.getE01TSG().equals("1") || rtCond.getE01TSG().equals("2") || rtCond.getE01TSG().equals("3"))) out.print("selected"); %>></option>
                <option value="1" <% if (rtCond.getE01TSG().equals("1")) out.print("selected"); %>>Firma Individual</option>
                <option value="2" <% if (rtCond.getE01TSG().equals("2")) out.print("selected"); %>>Firma Mancomunada</option>
				<option value="3" <% if (rtCond.getE01TSG().equals("3")) out.print("selected"); %>>Firma Indistinta</option>
			  </select>
              </TD>
              <TD ALIGN=LEFT NOWRAP>
					<INPUT type="text" name="E01SG2" size="9" maxlength="9" value="<%=rtCond.getE01SG2()%>">
				   <a href="javascript:GetCustomerDetails('E01SG2','E01NM2','','','','','','','','','','','','','','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>
				   <br>
					<INPUT type="text" name="E01NM2" size="30" maxlength="30" value="<%=rtCond.getE01NM2()%>" readonly>
			  </TD>
              <TD ALIGN=CENTER NOWRAP>
              <input type="text" name="E01CS2" maxlength="1" size="2" value="<%= rtCond.getE01CS2()%>">
              <a href="javascript:GetCodeCNOFC('E01CS2','FI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
			  </TD>
              <TD ALIGN=LEFT NOWRAP>
					<INPUT type="text" name="E01SG3" size="9" maxlength="9" value="<%=rtCond.getE01SG3()%>">
				   <a href="javascript:GetCustomerDetails('E01SG3','E01NM3','','','','','','','','','','','','','','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>
				   <br>
					<INPUT type="text" name="E01NM3" size="30" maxlength="30" value="<%=rtCond.getE01NM3()%>" readonly>
			  </TD>
              <TD ALIGN=CENTER NOWRAP>
              <input type="text" name="E01CS3" maxlength="1" size="2" value="<%= rtCond.getE01CS3()%>">
              <a href="javascript:GetCodeCNOFC('E01CS3','FI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
			  </TD>
              <TD ALIGN=LEFT NOWRAP>
					<INPUT type="text" name="E01AMN" size="15" maxlength="15" value="<%=rtCond.getE01AMN()%>">
              </TD>
              <TD ALIGN=CENTER NOWRAP>
				<INPUT id="EIBSBTN0" type="submit" name="Submit0" value="Enviar" onclick="goUpdVal()">
              </TD>
             </TR>
    <%
         }else{        
	%>
	<INPUT TYPE=HIDDEN NAME="E01CS1" VALUE="">   
	<INPUT TYPE=HIDDEN NAME="E01CS2" VALUE="">   
	<INPUT TYPE=HIDDEN NAME="E01CS3" VALUE="">   
	<INPUT TYPE=HIDDEN NAME="E01SG1" VALUE="">   
	<INPUT TYPE=HIDDEN NAME="E01SG2" VALUE="">   
	<INPUT TYPE=HIDDEN NAME="E01SG3" VALUE="">   
	<INPUT TYPE=HIDDEN NAME="E01NM1" VALUE="">   
	<INPUT TYPE=HIDDEN NAME="E01NM2" VALUE="">   
	<INPUT TYPE=HIDDEN NAME="E01NM3" VALUE="">   
	<INPUT TYPE=HIDDEN NAME="E01AMN" VALUE="">   
	<INPUT TYPE=HIDDEN NAME="E01SQN" VALUE="">   
	<INPUT TYPE=HIDDEN NAME="E01TSG" VALUE="">   
	<%
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
//  showChecked("ROW");
  resizeDoc();
  window.onresize=resizeDoc;
 <%                
 String errorw = request.getParameter("ERROR");
   if ( errorw != null ) {    
   out.println("alert(\" Error de actualizacion. Por favor contacte con el Administardor de sistema\")");      
 	}
 %>
     
</SCRIPT>
</FORM>
</BODY>
</HTML>

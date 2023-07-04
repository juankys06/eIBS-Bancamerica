<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Producto
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="msgRT" class="datapro.eibs.beans.EDP072001Message"  scope="session" />
<jsp:useBean id="msgList" class="datapro.eibs.beans.EDP072101Message"  scope="session" />
<jsp:useBean id="msgRTC" class="datapro.eibs.beans.EDP072201Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<%
if (!error.getERRNUM().equals("0")) {
      error.setERRNUM("0");
%>
	<SCRIPT Language="Javascript">
		showErrors();
		</SCRIPT>
<%}%>


<SCRIPT language="JavaScript">

builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
}
function goCancel(opt) {
	var op = opt;  	  
	document.forms[0].opt.value = op;
	document.forms[0].SCREEN.value="470"; 
	document.forms[0].submit(); 
}


</SCRIPT>

</HEAD>

<body>

<h3 align="center">Detalle del Producto<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="proposals_product_enter_maint.jsp,EDP0720"></h3>



<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0720">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="740">
  <INPUT TYPE=HIDDEN NAME="E01PLTACD" VALUE="">
  <INPUT type=HIDDEN name="E01PLPPTY" value="<%= msgRT.getE01PLPPTY().trim()%>">
  <INPUT type=HIDDEN name="E01PLTPTY" value="<%= msgRT.getE01PLPPTY().trim()%>">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="<%= userPO.getHeader16().trim()%>">  
  <INPUT TYPE=HIDDEN NAME="E01PLPCN2" value="<%= msgRT.getE01PLPCN2().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01PLPCN3" value="<%= msgRT.getE01PLPCN3().trim()%>">

<hr size="4">

   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type=TEXT name="E01CUSCUN" size=10 maxlength=9  value="<%= msgRT.getE01CUSCUN().trim()%>" readonly>
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
   		<input type="text" name="E01CUSNA1" size="27" maxlength="4" value="<%= msgRT.getE01CUSNA1().trim()%>" readonly>
      </td>
    </tr> 
    
    <tr id="trdark"> 
      <td align="right"  >
         <b>Nro. Propuesta :</b>
      </td>
      <td nowrap > 
		<INPUT type="text" name="E01PLPNPR" size="12" maxlength="12" value="<%= msgRT.getE01PLPNPR().trim()%>" readonly>
      </td>
   
      <td align="right"></td> 
      <td nowrap colspan=3></td>
    </tr>
        
  </table>
   
  <table class="tableinfo">  
      <tr >      
       <td>
		<table cellspacing="0" cellpadding="0" width="100%" border="0" id="opTable">	
		
			<TR id="trclear">
			   
			   <TD width="15%" align="right">
                    <DIV >Producto:</DIV>
				</TD>
		        <td nowrap width=20% align="left"> 
		            <input type=TEXT name="E01PLTPRO" size="6" maxlength="4" readonly value="<%= msgList.getE01PLTPRO().trim()%>">
			        <a href="javascript:GetProposalsProducts('E01PLTPRO','E01PLTTYP','E01PLTACD','')">
			        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
		        </td>
				
				<TD width="20%" align="left">  
				  Tipo: <INPUT type="text" name="E01PLTTYP" size="5" maxlength="4" value="" readonly value="<%= msgList.getE01PLTTYP().trim()%>">	  
	            </TD>		

				<td nowrap width="20%" align="left">
			          Ruta: <input type="text" name="E01PLTCN1" size="5" maxlength="4" readonly>
			              <a href="javascript:GetCodeCNOFC('E01PLTCN1','48')">
			              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
				</td>


		    </TR>
		  </table>
		</td>
	  </tr>  		 
  </table>
  <% if(userPO.getType().equals("9")){ %>
  <table class="tableinfo">  
      <tr >      
       <td width="1126">
		<table cellspacing="0" cellpadding="0" width="100%" border="0" id="opTable">	
		
			<TR id="trclear">
			   
			   <TD width="15%" align="right">
                    <DIV >Referencia Renovacion:</DIV>
				</TD> 
				       
				<TD width="20%" align="left">
				    <INPUT type="text" name="E01PLTRRE" size="13" maxlength="12" onkeypress=enterInteger() >   
					<a href="javascript:GetAccount('E01PLTRRE','','10',' ')">
					 	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" >
					</a>  
				 </TD>  
				
		   		 <TD  width="45%" align="left">
		   		 </TD>
		   		  
		    </TR>
		  </table>
		</td>
	  </tr>  		 
  </table>
  <% } %>

  
<div align="center"> 
	<% if (!msgRT.getH01FLGWK3().equals("3")) { %>
    <INPUT id="EIBSBTN" type="submit" name="Submit" value="Enviar" >
	<% } %>
	<INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader16() %>')">
</div>
          
</form>

</BODY>
</HTML>

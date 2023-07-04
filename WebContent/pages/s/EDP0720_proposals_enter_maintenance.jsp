<%@ page import ="datapro.eibs.master.Util" %> 
<html>
<head>
<title>Propuesta de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="EDP072101Help" class="datapro.eibs.beans.JBObjList" scope="session" />
<jsp:useBean id="EDP072601Help" class="datapro.eibs.beans.JBObjList" scope="session" />

<jsp:useBean id="msgRT" class="datapro.eibs.beans.EDP072001Message"  scope="session" />
<jsp:useBean id="msgRTC" class="datapro.eibs.beans.EDP072201Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
       builtNewMenu(pc_optionHeader);  
       
       
	function goCancel() {
	document.forms[0].SCREEN.value="200";
	document.forms[0].submit(); 
	  		  
}

</SCRIPT>



<%
if (!error.getERRNUM().equals("0")) {
      error.setERRNUM("0");
%>
	<SCRIPT Language="Javascript">
		showErrors();
		</SCRIPT>
<%}
 out.println("<SCRIPT> initMenu(); </SCRIPT>");

%>

</head>
<body >
<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }   
%>

<H3 align="center">Nueva Propuesta de Cr&eacute;dito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="proposals_enter_maintenance.jsp, EDP0720">
</H3>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0720" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="302">

  <input type=HIDDEN name="E01PLPBNK" value="<%= currUser.getE01UBK().trim()%>"> 
  <input type=HIDDEN name="E01PLPBRN" value="<%= currUser.getE01UBR().trim()%>"> 

  <input type=HIDDEN name="E01DPPPXX" value="0001"> 
  <input type=HIDDEN name="E01PLPPTY" value="1"> 
  <input type=HIDDEN name="E01DPPDXX" value="APERTURA PROPUESTA"> 
<%--

  <h4>Propuesta</h4>

 <table class="tableinfo">
    <tr > 
      	<td>
			<table cellspacing="0" cellpadding="4" width="100%" border="0">
				<tr id="trdark"> 
		            <td width="10%"> 
		              <div align="right">Tipo de Propuesta</div>
		            </td>
		            
		            <td width="20%">
		  				  <input type="radio" name="E01PLPPTY" value="1">Solicitud 
		              	  <input type="radio" name="E01PLPPTY" value="7">Otras Operaciones
		            </td>
		            <td width="50%" align="left">Destino
	   				<input type="text" name="E01PLPCN2" size="5" maxlength="4" value="<%= msgRT.getE01PLPCN2().trim()%>" 
					<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
   					>
       		
	   				<% if(!userPO.getHeader16().equals("5")) {%>
					<A href="javascript:GetCodeDescCNOFC('E01PLPCN2','E01PLPRE2','PD')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
					</A>
					<% } %>
					<INPUT type="text" name="E01PLPRE2" size="15" maxlength="15" value="<%= msgRT.getE01PLPRE2().trim()%>" readonly> 
	   				<input type="text" name="E01PLPCN3" size="5" maxlength="4" value="<%= msgRT.getE01PLPCN3().trim()%>" 
					<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
   					>
       		
	   				<% if(!userPO.getHeader16().equals("5")) {%>
					<A href="javascript:GetCodeDescCNOFC('E01PLPCN3','E01PLPRE3','PD')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
					</A>
					<% } %>
					<INPUT type="text" name="E01PLPRE3" size="15" maxlength="15" value="<%= msgRT.getE01PLPRE3().trim()%>" readonly> 
				    </td>
	    	      </tr>
			</table>
		</td>
    </tr>
 </table>
--%>

   <h4>Datos del Cliente</h4>
 <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="15%"> 
              <div align="right">N&uacute;mero Cliente :</div>
            </td>
            <td width="30%">
	            <input type=TEXT name="E01CUSCUN" size=10 maxlength=9 value="" onKeypress="enterInteger()" >
        		<a href="javascript:GetCustomerDescId('E01CUSCUN','E01CUSNA1','')">
        		<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
            </td>
            <td width="60%"> 
              Nombre Cliente :
               		<input type="text" name="E01CUSNA1" size="35" maxlength="40" value="" readonly>
            </td>
          </tr>

          <tr id="trdark"> 
            <td width="15%"> 
              <div align="right">Sujeto de Crédito :</div>
            </td>
            <td width="30%">
	   				<input type="text" name="E01PLPCN2" size="5" maxlength="4" value="<%= msgRT.getE01PLPCN2().trim()%>" 
					<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
   					>
       		
	   				<% if(!userPO.getHeader16().equals("5")) {%>
					<A href="javascript:GetCodeDescCNOFC('E01PLPCN2','E01PLPRE2','PD')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
					</A>
					<% } %>
					<INPUT type="text" name="E01PLPRE2" size="15" maxlength="15" value="<%= msgRT.getE01PLPRE2().trim()%>" readonly> 
		    </td>
            <td width="60%"> 
              Destino de Crédito :
	   				<input type="text" name="E01PLPCN3" size="5" maxlength="4" value="<%= msgRT.getE01PLPCN3().trim()%>" 
					<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
   					>
       		
	   				<% if(!userPO.getHeader16().equals("5")) {%>
					<A href="javascript:GetCodeDescCNOFC('E01PLPCN3','E01PLPRE3','38')">
					<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
					</A>
					<% } %>
					<INPUT type="text" name="E01PLPRE3" size="15" maxlength="15" value="<%= msgRT.getE01PLPRE3().trim()%>" readonly> 
		    </td>
          </tr>
         
        </table>
      </td>
    </tr>
  </table>
  
  <p>
<div align="center"> 
    <INPUT id="EIBSBTN" type="submit" name="Submit" value="Enviar" >
	<INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel()">
</div>
        
          
  </form>
</body>
</html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Informacion Basica</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<SCRIPT Language="Javascript">

//  Process according with user selection
 function goAction(op) {
	
   	switch (op){
	// Validate & Write 
  	case 1:  {
    	document.forms[0].APPROVAL.value = 'N';
       	break;
        }
	// Validate and Approve
	case 2:  {
 		document.forms[0].APPROVAL.value = 'Y';
       	break;
		}
	}
	document.forms[0].submit();
 }

function protectFields(updtyp) {
		document.getElementById("E01SHN").disabled=true;  
		document.getElementById("E01NA1").disabled=true; 
 
} 
</SCRIPT>

 
<jsp:useBean id= "client" class= "datapro.eibs.beans.ESD008501Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<%
 if ( !userPO.getPurpose().equals("NEW") ) {
%>

   <SCRIPT Language="Javascript">
     builtNewMenu(client_personal_opt);
   </SCRIPT>

<%
}
%>

</head>

<body bgcolor="#FFFFFF">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 }
%>

<h3 align="center">Informaci&oacute;n  Personal<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_personal_basic, ESD0085"  ></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0085" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="APPROVAL" VALUE="N">
  <input type="hidden" name="E01LGT" size="15" maxlength="10" value="<%= client.getE01LGT().trim()%>">
  <h4> Nombre y Apellidos</h4>
    <div align="left">
    <table class="tableinfo" >
      <tr > 
          <td nowrap > 
            <div align="center"> 
              
            <table cellspacing="0" cellpadding="2" width="100%" border="0">
              <tr id="trdark"> 
                <td nowrap width="25%"> 
                  <div align="right">Identificación . :</div>
                </td>
                <td nowrap colspan="4"> 
                  <input type="text" name="E01LN3" size="30" value="<%= client.getE01LN3().trim()%>" readonly>
                </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="25%"> 
                  <div align="right">Primer Nombre :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" name="E01FNA" size="35" maxlength="30" value="<%= client.getE01FNA().trim() %>"
                  <% if (client.getE01GEC().equals("DR")) { %>
                   
                   <% } %> >
                  <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0" > 
                </td>
              </tr>
              <tr id="trdark"> 
                <td nowrap width="25%" > 
                  <div align="right">Segundo Nombre :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" name="E01FN2" size="35" maxlength="30" value="<%= client.getE01FN2().trim()%>"
                  <% if (client.getE01GEC().equals("DR")) { %>
                   
                   <% } %> >
                </td>
              </tr>
              <tr id="trclear"> 
                <td nowrap width="25%"> 
                  <div align="right">Primer Apellido :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" name="E01LN1" size="35" maxlength="30" value="<%= client.getE01LN1().trim()%>"
                  <% if (client.getE01GEC().equals("DR")) { %>
                   
                   <% } %> >
                  <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"  > 
                </td>
              </tr>
              <tr id="trdark"> 
                <td nowrap width="25%"> 
                  <div align="right">Segundo Apellido :</div>
                </td>
                <td nowrap colspan=3> 
                  <input type="text" name="E01LN2" size="35" maxlength="30" value="<%= client.getE01LN2().trim()%>"
                  <% if (client.getE01GEC().equals("DR")) { %>
                    
                   <% } %> >
                </td>
              </tr>
            </table>
            
          </div>
          </td>
        </tr>
      </table>
   </div>
   
   <% System.out.println("Bloque Direccion H01SCR="+client.getH01SCR()); %>  
    <%if( client.getH01SCR().equals("03")){%>
	<%} else if( client.getH01SCR().equals("06")){%>
	
	<%} else if( client.getH01SCR().equals("07")){ %>
	<%} else if( client.getH01SCR().equals("21")){%>
	
	<%} else {%>
	<%} %>   
    
    <% System.out.println("Bloque Identificacion H01SCR="+client.getH01SCR()); %>  
    <%if( client.getH01SCR().equals("07")){%>
	<%} else if( client.getH01SCR().equals("21")){%>
	<%} else {%>
	<%} %>   
	 <%--
  <h4>Identificaci&oacute;n</h4>
    
  <table class="tableinfo" >
    <tr > 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="15%"> 
              <div align="right">Ident. No. :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="E01IDN" size="20" maxlength="15" value="<%= client.getE01IDN().trim()%>" >
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >  	     		
            </td>
            <td nowrap width="11%"> 
              <div align="right">Tipo :</div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" name="E01TID" size="8" maxlength="4" value="<%= client.getE01TID().trim()%>">
              <a href="javascript:GetCodeAuxCNOFC('E01TID','34','<%=client.getH01SCR()%>_2' )"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0"></a> 
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >  	     		
            </td>
            <td nowrap width="11%"> 
              <div align="right">Pa&iacute;s :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" name="E01PID" size="8" maxlength="4" value="<%= client.getE01PID().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01PID','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
               <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" >  	     		
             </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="15%"> 
              <div align="right">Ident. No. :</div>
            </td>
            <td nowrap width="18%"> 
              <input type="text" name="E01IDF" size="20" maxlength="15" value="<%= client.getE01IDF().trim()%>">
            </td>
            <td nowrap width="11%"> 
              <div align="right">Tipo :</div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" name="E01TIF" size="8" maxlength="4" value="<%= client.getE01TIF().trim()%>">
              <a href="javascript:GetCodeAuxCNOFC('E01TIF','34','<%=client.getH01SCR()%>_2')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
            <td nowrap width="11%"> 
              <div align="right">Pa&iacute;s :</div>
            </td>
            <td nowrap width="25%"> 
              <input type="text" name="E01PIF" size="8" maxlength="4" value="<%= client.getE01PIF().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01PIF','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="15%" > 
              <div align="right">Ident. No. :</div>
            </td>
            <td nowrap width="18%" > 
              <input type="text" name="E01ID3" size="20" maxlength="15" value="<%= client.getE01ID3().trim()%>">
            </td>
            <td nowrap width="11%" > 
              <div align="right"> Tipo :</div>
            </td>
            <td nowrap width="20%" > 
              <input type="text" name="E01TI3" size="8" maxlength="4" value="<%= client.getE01TI3().trim()%>">
              <a href="javascript:GetCodeAuxCNOFC('E01TI3','34','<%=client.getH01SCR()%>_2')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
            <td nowrap width="11%" > 
              <div align="right">Pa&iacute;s :</div>
            </td>
            <td nowrap width="25%" > 
              <input type="text" name="E01PI3" size="8" maxlength="4" value="<%= client.getE01PI3().trim()%>">
              <a href="javascript:GetCodeCNOFC('E01PI3','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="bottom" border="0" ></a> 
            </td>
          </tr>         

        </table>
      </td>
    </tr>
  </table>
            --%>
  <h4></h4><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF"><tr bgcolor="#FFFFFF"><td width="33%"></td>
    	</tr>
  	</table>

<table width="100%">		
  	<tr>
		<% if (client.getE01SFR().equals("Y")) {%>
  		<td width="50%">
		<%} else { %>
		<td width="100%">
		<% } %>

  		  <div align="center"> 
     		<input id="EIBSBTN" type="button" name="Submit" value="Enviar" onClick="javascript:goAction(1);">
     	  </div>	
  		</td>
		<% if (client.getE01SFR().equals("Y")) {%>
		<td width="50%">
  		  		<div align="center">
				<input id="EIBSBTN" type="button" name="Submit2" value="Crear" onClick="javascript:goAction(2);">
     	  	 	</div>	
  		</td>
		<%} %>
  	</tr>	
</table>	
	<SCRIPT language="JavaScript">
		protectFields('<%= userPO.getPurpose().trim()%>');	
	</SCRIPT>

</form>
</body>
</html>


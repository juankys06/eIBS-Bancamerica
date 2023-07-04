<html>
<head>
<title>Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "invAcc" class= "datapro.eibs.beans.EIE001101Message"   scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"   scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
	 builtNewMenu(inv_port_opt);
	 
	 function switchVal(obj) {
	   if (obj.checked) obj.value="Y"; else obj.value="N";
	 }
</SCRIPT>

</head>

<body >

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  
    out.println("<SCRIPT> initMenu(); </SCRIPT>"); 
%>

<h3 align="center">Cuentas Transaccionales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="inv_port_acc, EIE0010"></h3>
<hr size="4">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0010" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">  

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01PCACUN" size="9" maxlength="9" value="<%= invAcc.getE01PCACUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= invAcc.getE01CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Portafolio :</b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01PCANUM" size="12" maxlength="12" value="<%= invAcc.getE01PCANUM().trim()%>" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Descripción : </b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01PRFDSC" size="35" maxlength="35" value="<%= invAcc.getE01PRFDSC().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Cuentas Transaccionales</h4>
  <table class="tableinfo">
    <tr>  
      <td> 
        <table cellspacing="2" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
            <th nowrap> 
              <div align="center">Cuenta</div>
            </th>
            <th nowrap> 
              <div align="center">Moneda</div>
            </th>
            <th nowrap> 
              <div align="center">Saldo</div>
            </th>
            <th width="1%" nowrap> 
              <div align="center">Borrar</div>
            </th>
          </tr>
<%  
  String name;
  for ( int i=1; i<=10; i++ ) {
    if ( i == 10 ) name = "0"; else name = i + "";       
%>  
          <tr id="trclear"> 
            <td nowrap align="center">
             <% if(invAcc.getField("E01PCAAC" + name).getString().trim().equals("0") || invAcc.getField("E01ERRFL" + name).getString().trim().equals("Y")) { %>  
			 <a href="javascript:showRetAccountInq(document.forms[0].E01PCAAC<%= name %>.value)"><img src="<%=request.getContextPath()%>/images/1aquire.gif" alt="click for account inquiry" align="absbottom" border="0" > </a>
			  <a href="javascript:showRetAccountHolders(document.forms[0].E01PCAAC<%= name %>.value)"><img src="<%=request.getContextPath()%>/images/accountholder.gif" alt="click for account holders" align="absbottom" border="0" > </a>
              <input type="text" name="E01PCAAC<%= name %>" maxlength="12" size="12" value="<%= invAcc.getField("E01PCAAC" + name).getString().trim()%>">
              <a href="javascript:GetAccount('E01PCAAC<%= name %>','','RA','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"></a> 
              <% } else { %> <a href="javascript:showRetAccountInq(document.forms[0].E01PCAAC<%= name %>.value)"><img src="<%=request.getContextPath()%>/images/1aquire.gif" alt="click for account inquiry" align="absbottom" border="0" > 
              </a> <a href="javascript:showRetAccountHolders(document.forms[0].E01PCAAC<%= name %>.value)"><img src="<%=request.getContextPath()%>/images/accountholder.gif" alt="click for account holders" align="absbottom" border="0" ></a> 
              <input type="text" name="E01PCAAC<%= name %>" maxlength="12" size="12" value="<%= invAcc.getField("E01PCAAC" + name).getString().trim()%>" readonly>            
             <% } %>
            </td>
            <td nowrap align="center"> 
              <input type="text" name="E01PCACY<%= name %>" maxlength="3" size="3" value="<%= invAcc.getField("E01PCACY" + name).getString().trim()%>" readonly>           
            </td>
            <td nowrap align="center"> 
              <input type="text" id="txtright" name="E01MNBAL<%= name %>" maxlength="17" size="17" value="<%= invAcc.getField("E01MNBAL" + name).getString()%>" readonly>
            </td>
            <td nowrap align="center">
             <% if(!(invAcc.getField("E01PCAAC" + name).getString().trim().equals("0") || invAcc.getField("E01ERRFL" + name).getString().trim().equals("Y"))) { %>                        
                <input type="checkbox" name="E01PCADL<%= name %>" onClick="switchVal(this)" value="<%= invAcc.getField("E01PCADL" + name).getString().trim()%>" <% if(invAcc.getField("E01PCADL" + name).getString().trim().equals("Y")) out.print("checked");%> >             
             <% } %>
            </td>
          </tr>                    
<%
  }
%>		  
        </table>        
      </td>
    </tr>
  </table>
  
 <p align="center"> 
	<input id="EIBSBTN" type=submit name="Submit" value="Enviar">
 </p>
</form>
</body>
</html>

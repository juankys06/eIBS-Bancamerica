<html>
<head>
<title>Pasivos</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "liabilities" class= "datapro.eibs.beans.ESD000004Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<SCRIPT Language="Javascript">

   builtNewMenu(client_ap_corp_opt);

</SCRIPT>

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
<h3 align="center">Pasivos del Cliente <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_ap_corp_liabilities, ESD0100"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080">
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="20">
  
<p>
<table class="tableinfo">
  <tr > 
    <td nowrap> 
      <table cellspacing="0" cellpadding="2" width="100%"  class="tbhead"  align="center">
        <tr>
             <td nowrap width="10%" align="right"> Cliente: 
               </td>
          <td nowrap width="12%" align="left">
      			<%= userPO.getHeader1()%>
          </td>
            <td nowrap width="6%" align="right">ID:  
            </td>
          <td nowrap width="14%" align="left">
      			<%= userPO.getHeader2()%>
          </td>
            <td nowrap width="8%" align="right"> Nombre: 
               </td>
          <td nowrap width="50%"align="left">
      			<%= userPO.getHeader3()%>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</p>
<div id="dataDiv" align="center" style="height:351; overflow-y:scroll; width:90%; padding-left:50; padding-right:5" class="scbarcolor">
<%
  int bene_amount = 10;
  String name;
  for ( int i=1; i<=bene_amount; i++ ) {
    if ( i == 10 ) {
      name = "A"; 
    }
    else {
      name = i + "";
    }
    
    out.println("<table class=\"tbenter\" border=\"0\" width=\"100%\">");
    out.println("<tr>");
    
    switch ( i ) {
        case 1 :     
%> 
 <td align="left"><h4>Primero</h4></td>
<%          
           break;
        case 2 : 
%> 
 <td align="left"><h4>Segundo</h4></td>
<%          
           break;
        case 3 : 
%> 
<td align="left"><h4>Tercero</h4></td>
<%          
           break;
        case 4 : 
%> 
<td align="left"><h4>Cuarto</h4></td>
<%          
           break;
        case 5 : 
%> 
<td align="left"><h4>Quinto</h4></td>
<%          
           break;
        case 6 : 
%> 
<td align="left"><h4>Sexto</h4></td>
<%          
           break;
        case 7 : 
%> 
<td align="left"><h4>Séptimo</h4></td>
<%          
           break;
        case 8 : 
%> 
<td align="left"><h4>Octavo</h4></td>
<%          
           break;
        case 9 : 
%> 
<td align="left"><h4>Noveno</h4></td>
<%          
           break;
        case 10 : 
%> 
<td align="left"><h4>Décimo</h4></td>
<%          
           break;
     }
  out.println("<td width=\"30%\" align=\"right\">");
  out.println("<h4>");
  out.print("<A href=\"javascript:go(1)\">1</A>,<A href=\"javascript:go(2)\">2</A>,<A href=\"javascript:go(3)\">3</A>,<A href=\"javascript:go(4)\">4</A>,<A href=\"javascript:go(5)\">5</A>,");
  out.print("<A href=\"javascript:go(6)\">6</A>,<A href=\"javascript:go(7)\">7</A>,<A href=\"javascript:go(8)\">8</A>,<A href=\"javascript:go(9)\">9</A>,<A href=\"javascript:go(10)\">10</A>");
   out.println("</h4>"); 
  out.println("</td>");   
  out.println("</tr>");
  out.println("</table>"); 
%>

  <table id="mainTable<%= name %>" class="tableinfo" width="100%">
    <tr bgcolor="#FFFFFF" bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" name="TB_BENEFICIARY_CORP" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF" align="center">
          <tr id="trdark"> 
            <td nowrap width="13%"  > 
              <div align="right">Acreedor :</div>
            </td>
            <td nowrap width="23%"  >  
              <input type="text" readonly name="E<%= name %>4MA1" maxlength="45" size="46" value="<%= liabilities.getField("E" + name + "4MA1").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="13%" > 
              <div align="right">Tipo de Deuda :</div>
            </td>
            <td nowrap >  
              <input type="text" readonly name="E<%= name %>4MA2" maxlength="35" size="36" value="<%= liabilities.getField("E" + name + "4MA2").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="13%"> 
              <div align="right">Referencia :</div>
            </td>
            <td nowrap width="23%" >  
              <input type="text" readonly name="E<%= name %>4MA3" maxlength="35" size="36" value="<%= liabilities.getField("E" + name + "4MA3").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="13%"> 
              <div align="right">Valor del Pasivo :</div>
            </td>
            <td nowrap width="23%" >  
              <input type="text" readonly name="E<%= name %>4AM1" maxlength="15" size="16" value="<%= liabilities.getField("E" + name + "4AM1").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="13%" > 
              <div align="right">Fecha del Pasivo :</div>
            </td>
            <td nowrap width="23%" >  
              <input type="text" readonly name="E<%= name %>4DT1" size="2" maxlength="2" value="<%= liabilities.getField("E" + name + "4DT1").getString().trim()%>">
             
              <input type="text" readonly name="E<%= name %>4DT2" size="2" maxlength="2" value="<%= liabilities.getField("E" + name + "4DT2").getString().trim()%>">
             
              <input type="text" readonly name="E<%= name %>4DT3" size="2" maxlength="2" value="<%= liabilities.getField("E" + name + "4DT3").getString().trim()%>">
              </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
   <%
  }
%>
</div>
 <SCRIPT Language="Javascript">
   dataDiv.style.height=  mainTable1.offsetTop + mainTable1.offsetHeight +"";
</SCRIPT>

  <p><% 
    // This goes at the end of the page
    if ( (userPO.getPurpose().equals("NEW")) || (userPO.getPurpose().equals("MAINTENANCE")) ) {
		out.println("<p>");
		out.println("  <center>");
		out.println("<input class=\"imgfilter\" type=image name=\"Submit\" src=\""+request.getContextPath()+"/images/s/bt_enviar.gif\" onMouseDown=\"this.className=''\" onMouseUp=\" this.className='imgfilter'\" >");
		out.println("  </center>");
		out.println("<p>");
 	 }
 	 %> </p>
  </form>
</body>
</html>

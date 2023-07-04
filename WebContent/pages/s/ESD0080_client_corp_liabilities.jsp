<html>
<head>
<title>Liabilities</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 


<SCRIPT Language="Javascript">

   builtNewMenu(client_corp_opt);

</SCRIPT>

</head>


<jsp:useBean id= "liabilities" class= "datapro.eibs.beans.ESD000004Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

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
<h3 align="center">Pasivos del Cliente <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_corp_liabilities, ESD0080"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080">

    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="20">

<table class="tableinfo">
  <tr > 
    <td> 
      <table cellspacing="0" cellpadding="2" class="tbhead" width="100%"   align="center">
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

<BR>
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
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" name="TB_BENEFICIARY_CORP" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Acreedor :</div>
            </td>
            <td width="23%" nowrap>  
              <input type="text" name="E<%= name %>4MA1" maxlength="45" size="46" value="<%= liabilities.getField("E" + name + "4MA1").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Tipo de Deuda :</div>
            </td>
            <td nowrap>  
              <input type="text" name="E<%= name %>4MA2" maxlength="35" size="36" value="<%= liabilities.getField("E" + name + "4MA2").getString().trim()%>">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Referencia :</div>
            </td>
            <td width="23%" nowrap>  
              <input type="text" name="E<%= name %>4MA3" maxlength="35" size="36" value="<%= liabilities.getField("E" + name + "4MA3").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Valor del Pasivo :</div>
            </td>
            <td width="23%" nowrap>  
              <input type="text" name="E<%= name %>4AM1" maxlength="15" size="16" value="<%= liabilities.getField("E" + name + "4AM1").getString().trim()%>" onKeypress="enterDecimal()">
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Fecha del Pasivo :</div>
            </td>
            <td width="23%" nowrap>  
              <input type="text" name="E<%= name %>4DT1" size="2" maxlength="2" value="<%= liabilities.getField("E" + name + "4DT1").getString().trim()%>">
             
              <input type="text" name="E<%= name %>4DT2" size="2" maxlength="2" value="<%= liabilities.getField("E" + name + "4DT2").getString().trim()%>">
             
              <input type="text" name="E<%= name %>4DT3" size="2" maxlength="2" value="<%= liabilities.getField("E" + name + "4DT3").getString().trim()%>">
              </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="34%">
              <div align="right">Tipo de Pasivo :</div>
            </td>
            <td nowrap width="66%" >
              <div align="left"> 
				<select name="E<%= name %>4FL1"> 
                  <option value="" <%if (liabilities.getField("E" + name + "4FL1").equals("")) {out.print("selected"); }%>></option>    
                  <option value="1" <%if (liabilities.getField("E" + name + "4FL1").equals("1")) {out.print("selected"); }%>>Circulante</option>
                  <option value="2" <%if (liabilities.getField("E" + name + "4FL1").equals("2")) { out.print("selected"); }%>>Largo Plazo</option>
                  <option value="3" <%if (liabilities.getField("E" + name + "4FL1").equals("3")) { out.print("selected"); }%>>Patrimonio</option>
                </select>
              </div>
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
 
  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</p>
  </form>
</body>
</html>

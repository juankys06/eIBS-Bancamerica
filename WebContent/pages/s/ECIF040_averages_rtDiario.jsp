<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="org.apache.poi.hssf.record.PageBreakRecord.Break"%>
<html>
<head>
<title>Promedios de Cuentas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "aveBean" class= "datapro.eibs.beans.ECIF04003Message"  scope="session" />
<jsp:useBean id= "header" class= "datapro.eibs.beans.ECIF04003Message"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function showGraph()
{
	var pg= "<%=request.getContextPath()%>/pages/s/ECIF040_averages_rt_graph.jsp";
	CenterNamedWindow(pg,'graph',700,560,2);
}

<%
if (userPO.getPurpose().equals("INQUIRY")){
%>

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
else if (userPO.getOption().equals("CD")){
%>
	builtNewMenu(cd_i_opt);
<%   
}
else if (userPO.getOption().equals("LN")){
%>

    <%
     if ( userPO.getHeader23().equals("G") ||  userPO.getHeader23().equals("V")){
    %>
	builtNewMenu(ln_i_1_opt);
   <%   
   }
    else  {
   %>
	builtNewMenu(ln_i_2_opt);
   <%   
   }
   %>

<%
}
%>
<%
}
%>
</SCRIPT>

</head>

<body>
<%@ page import = "datapro.eibs.master.Util" %>

<%
   if (userPO.getPurpose().equals("INQUIRY")){ 
   out.println("<SCRIPT> initMenu(); </SCRIPT>");}
%> 



<h3 align="center">Promedios de Cuenta Diario  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="averages_rt.jsp,ECIF040"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECIF040" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <%if(!header.getE03CUNLET().equals("")){ %>
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
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%=header.getE03CUSLET().trim() %>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="CUSNA1" size="45" readonly maxlength="45" value="<%=header.getE03CUNLET().trim() %>" >
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%">
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="ACCNUM" size="12" readonly maxlength="12" value="<%=header.getE03ACCLET().trim()  %>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left">
                <input type="text" name="PROCCY" size="4" readonly maxlength="4" value="<%= header.getE03CCYLET().trim()  %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left">
                <input type="text" name="PROCOD" size="4" readonly maxlength="4" value="<%=header.getE03PROLET().trim()  %>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Promedios</h4>

  <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark">
            <td width="50%"> 
              <div align="center"><b>Mes:  <%=header.getE03MTHLET().trim()  %>  Año:  <%=header.getE03YARLET().trim()  %> </b></div>
            </td>
            
          </tr>
        </table>
		
        <table cellspacing=0 cellpadding=2 width="100%" border="1">
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="center">Dia</div>
            </td>
            <td nowrap width="38%"> 
              <div align="center">Promedio Mensual</div>
            </td>
            <td nowrap width="28%"> 
              <div align="center">Saldo en Libros</div>
            </td>
              <td nowrap width="28%"> 
              <div align="center">Saldo Neto</div>
            </td>
          </tr>
          <%
        	String reci[]= new String[31];
        	reci[0]=header.getE03STRL01();
        	reci[1]=header.getE03STRL02();
        	reci[2]=header.getE03STRL03();
        	reci[3]=header.getE03STRL04();
        	reci[4]=header.getE03STRL05();
        	reci[5]=header.getE03STRL06();
        	reci[6]=header.getE03STRL07();
        	reci[7]=header.getE03STRL08();
        	reci[8]=header.getE03STRL09();
        	reci[9]=header.getE03STRL10();
        	reci[10]=header.getE03STRL11();
        	reci[11]=header.getE03STRL12();
        	reci[12]=header.getE03STRL13();
        	reci[13]=header.getE03STRL14();
        	reci[14]=header.getE03STRL15();
        	reci[15]=header.getE03STRL16();
        	reci[16]=header.getE03STRL17();
        	reci[17]=header.getE03STRL18();
        	reci[18]=header.getE03STRL19();
        	reci[19]=header.getE03STRL20();
        	reci[20]=header.getE03STRL21();
        	reci[21]=header.getE03STRL22();
        	reci[22]=header.getE03STRL23();
        	reci[23]=header.getE03STRL24();
        	reci[24]=header.getE03STRL25();
        	reci[25]=header.getE03STRL26();
        	reci[26]=header.getE03STRL27();
        	reci[27]=header.getE03STRL28();
        	reci[28]=header.getE03STRL29();
        	reci[29]=header.getE03STRL30();
        	reci[30]=header.getE03STRL31();
        	
          	         	
          int i=0;
          while(i<31){    //Ciclo para repetir por los dias del mes 
        	String rec=reci[i];
          	if(!rec.equals("")){         		
          	          	
          	%>
          
            <tr> 
            <td  id="trdark" nowrap width="20%" height="19"> 
              <div align="right"> <%= rec.substring(0,2) %></div>
            </td>
            <td  id="trclear" nowrap width="20%" height="19"> 
              <div align="right"><%= Float.parseFloat(rec.substring(3,21))/100 %> </div>
            </td>
            <td  id="trclear" nowrap width="20%" height="19"> 
              <div align="right"><%= Float.parseFloat(rec.substring(22,40))/100 %> </div>
            </td>
            <td id="trclear" nowrap width="20%" height="19"> 
              <div align="right"><%= Float.parseFloat(rec.substring(42,59))/100 %> </div>
            </td>                     
           </tr>
          <%
      			
          	}   
          	i++;
          }   //fin del while %>
        
           
					

						</table>
      </td>
    </tr>
  </table>
  <%} else{%>
  <td> 
            <div align="center"><font face="Tahoma, Arial, Garamond, Futura Lt BT, Futura XBlk BT, Futura Md BT, FuturaBlack BT" size="3" color="#0078BD"><b> El Promedio Diario Es Solo Para Cuentas Del Tipo Pasivo</b></font></div>
          </td>
   <%} %>       
  </form>
	<td width="50%">
		<div align="center">
		</div>
	</td>

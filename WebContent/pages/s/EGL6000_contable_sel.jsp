<html>
<head>       
<title>Ambient selection Countable Previous Month</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgM" class= "datapro.eibs.beans.EGL600001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 


	int NEW = 0;
	try { NEW = Integer.parseInt(request.getParameter("NEW"));} catch (Exception e) {}
	if (NEW == 1) {
	msgM.destroy();
	}

   	if (msgM == null) msgM = new datapro.eibs.beans.EGL600001Message();
   	   
%>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
      
 %>
     <SCRIPT Language="Javascript">
            showErrors(); 
     </SCRIPT>
 <%
 }
%>
</head>
<body >

<H3 align="center">Seleccion de Ambiente Contable Mes Anterior<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="products,EGL6000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEGL6000">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="0002">
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">        
     	<tr id=trdark> 	      
	      <td> 
	        <div align="center">Borrar Libreria : </div>
	      </td>
          <td nowrap width="18%"> 
            <input type="radio" name="E01CONLIB" value="Y" onClick="document.forms[0].E01CONLIB.value='Y'" <%if(msgM.getE01CONLIB().equals("Y")) out.print("checked");%>>Si
            <input type="radio" name="E01CONLIB" value="N" onClick="document.forms[0].E01CONLIB.value='N'" <%if(msgM.getE01CONLIB().equals("N")) out.print("checked");%>>No 
          </td>	      
     	</tr> 
     	<tr id=trdark> 	      
	      <td> 
	        <div align="center">Mes a Procesar : </div>
	      </td>
	      <td nowrap width="56%"> 
              <div align="left"> 
			    <select name="E01CONMES">
				  <option value="01" <% if (msgM.getE01CONMES().equals("01")) out.print("selected"); %>>ENERO</option>
				  <option value="02" <% if (msgM.getE01CONMES().equals("02")) out.print("selected"); %>>FEBRERO</option>
				  <option value="03" <% if (msgM.getE01CONMES().equals("03")) out.print("selected"); %>>MARZO</option>
				  <option value="04" <% if (msgM.getE01CONMES().equals("04")) out.print("selected"); %>>ABRIL</option>				  
				  <option value="05" <% if (msgM.getE01CONMES().equals("05")) out.print("selected"); %>>MAYO</option>				  
				  <option value="06" <% if (msgM.getE01CONMES().equals("06")) out.print("selected"); %>>JUNIO</option>				  
				  <option value="07" <% if (msgM.getE01CONMES().equals("07")) out.print("selected"); %>>JULIO</option>				  
				  <option value="08" <% if (msgM.getE01CONMES().equals("08")) out.print("selected"); %>>AGOSTO</option>				  
				  <option value="09" <% if (msgM.getE01CONMES().equals("09")) out.print("selected"); %>>SETIEMBRE</option>				  
				  <option value="10" <% if (msgM.getE01CONMES().equals("10")) out.print("selected"); %>>OCTUBRE</option>				  
				  <option value="11" <% if (msgM.getE01CONMES().equals("11")) out.print("selected"); %>>NOVIEMBRE</option>				  				  
				</select>
              </div>
            </td>
               
     	</tr>  		 		
     </table>
  	</td>      
   </tr>     
   </table>
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Procesar">
  </p>
</form>
</body>
</html>

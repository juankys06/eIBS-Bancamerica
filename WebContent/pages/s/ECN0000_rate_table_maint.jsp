<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Tabla de Renovaci&oacute;n de Certificados de Dep&oacute;sitos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "tbRate" class= "datapro.eibs.beans.ECN000003Message"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

 function goAction(op) {
   var ok = true;
   if (op == "C") {
   	  ok = confirm("Desea cambiar los valores de Plazo o Montos");
   }
   if (ok) {
   	document.forms[0].OPT.value = op;
   	document.forms[0].submit(); 
   }	 
 }

</SCRIPT>
</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
	 error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">Tasas de Interes por Plazo vs. Monto<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rate_table_maint.jsp,ECN0000"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSECN0000">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="OPT" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E03CDRSFL" VALUE="<%= tbRate.getE03CDRSFL()%>">      

<TABLE class="tableinfo">
    <TR>
    <TD>
		<TABLE width="102%">
			<tr>
				<td>
				<div align="right">N&uacute;mero de Tabla :</div>
				</td>
				<td><input type="text" name="E03CDRRTB" size="2" maxlength="1"
					value="<%= tbRate.getE03CDRRTB()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>></td>
				<td>
				<div align="right">Nombre de Tabla :</div>
				</td>
				<td width="239"><input type="text" name="E03CDRDSC" size="36"
					maxlength="36" value="<%= tbRate.getE03CDRDSC()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>></td>
			</tr>
			<tr>
				<td>
				<div align="right">Fecha Aplicaci&oacute;n :</div>
				</td>
				<td><input type="text" name="E03CDRDT1" size="3" maxlength="2"
					onKeypress="enterInteger()" value="<%= tbRate.getE03CDRDT1()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>> <input
					type="text" name="E03CDRDT2" size="3" maxlength="2"
					onKeypress="enterInteger()" value="<%= tbRate.getE03CDRDT2()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>> <input
					type="text" name="E03CDRDT3" size="3" maxlength="2"
					onKeypress="enterInteger()" value="<%= tbRate.getE03CDRDT3()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>></td>

				<td>
				<div align="right">Moneda :</div>
				</td>
				<td width="239"><input type="text" name="E03CDRCCY" size="3"
					maxlength="3" value="<%= tbRate.getE03CDRCCY()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>>Expresados en cientos
				</td>
			</tr>
		</TABLE>
		</TD>
  </TR>
  </TABLE>	  
<h4>Datos B&aacute;sicos</h4>	  
<TABLE class="tableinfo">
    <TR>
    <TD> 
   <TABLE width=100%>	  
	  <tr id=trdark>
	    <td width=60% align=center><b>Datos</b></td>
	    <td width=40% align=center><b>Penalidad x Precancelaci&oacute;n</b></td>
	  </tr>
	  <tr> 
	    <td valign=top>
	    	 <TABLE>	  
	  			<tr>
	  				<td nowrap> 
			          <div align="right">Tasa M&iacute;nima : </div>
			        </td>
			        <td>
			        	<input type="text" name="E03CDRMIR" size="10" maxlength="10" value="<%= tbRate.getE03CDRMIR()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>>
			        </td>      
				  	<td nowrap> 
			          <div align="right">Tasa M&aacute;xima : </div>
			        </td>
				  	<td>
				  	   <input type="text" name="E03CDRMXR" size="10" maxlength="10" value="<%= tbRate.getE03CDRMXR()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>>
				  	</td>
	  			</tr>
	  			<tr>
	  				<td nowrap> 
			          <div align="right">Periodo Base : </div>
			        </td>
			        <td>
			        	<input type="text" name="E03CDRB01" size="10" maxlength="10" value="<%= tbRate.getE03CDRB01()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>>
			        </td>      
				  	<td nowrap> 
			          <div align="right">Puntos Base : </div>
			        </td>
				  	<td>
				  	   <input type="text" name="E03CDRBSE" size="10" maxlength="10" value="<%= tbRate.getE03CDRBSE()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>>
				  	</td>
	  			</tr>
	  			<tr>
	  				<td nowrap> 
			          <div align="right">Asignacion de Tasa : </div>
			        </td>
			        <td>
			        	<input type="text" name="E03CDRB01" size="10" maxlength="10" value="<%= tbRate.getE03CDRB01()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>>
			        </td>      
				  	<td nowrap> 
			          <div align="right">Puntos Base : </div>
			        </td>
				  	<td>
				  	   <input type="text" name="E03CDRBSE" size="10" maxlength="10" value="<%= tbRate.getE03CDRBSE()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>>
				  	</td>
	  			</tr>
	  			<tr>
	  				<td nowrap> 
			          <div align="right">Tasa Flotante : </div>
			        </td>
			        <td>
			        	<input type="text" name="E03CDRFTB" size="2" maxlength="2" value="<%= tbRate.getE03CDRFTB()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>>
                		<a href="javascript:GetFloating('E03CDRFTB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Tabla de Tasas Flotantes" align="absmiddle" border="0" ></a> 
			            <SELECT name="E03CDRFTY">
                  			<option value=" " <% if (!(tbRate.getE03CDRFTY().equals("FP") || tbRate.getE03CDRFTY().equals("FS"))) out.print("selected"); %>></option>
                  			<option value="FP" <%if (tbRate.getE03CDRFTY().equals("FP")) { out.print("selected"); }%>>FP</option>
                  			<option value="FS" <%if (tbRate.getE03CDRFTY().equals("FS")) { out.print("selected"); }%>>FS</option>
						</SELECT>
			        </td>      
				  	<td nowrap> 
			          <div align="right">Asignacion de Tasa : </div>
			        </td>
				  	<td>
				  	    <SELECT name="H03FLGWK1">
				  	        <option value="R" <%if (tbRate.getH03FLGWK1().equals("R")) { out.print("selected"); }%>>Por Rangos</option>
                  			<option value="A" <%if (tbRate.getH03FLGWK1().equals("A")) { out.print("selected"); }%>>Por Aproximacion</option>
						</SELECT>
				  	</td>
	  			</tr>
	  		  </TABLE>
	    </td>
	    <td valign=top>
	    	 <TABLE>	  
	  			<tr>
	  				<td> 
			          <div align="right">Dias : </div>
			        </td>
			        <td>
			        	<input type="text" name="E03CDRDPE" size="4" maxlength="3" value="<%= tbRate.getE03CDRDPE()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>>
			        </td>      
				  	<td > 
			          <div align="right">% : </div>
			        </td>
				  	<td>
				  	   <input type="text" name="E03CDRPRT" size="5" maxlength="5" value="<%= tbRate.getE03CDRPRT()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>>
				  	</td>
	  			</tr>
	  	     </TABLE>	
	    </td>
	  </tr>  
  </TABLE>
  </TD>  
  </TR>  
  </TABLE>
 <br> 
  <TABLE class="tableinfo">
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap width=3%></TH> 
      <TH ALIGN=CENTER  nowrap colspan=7>Montos Hasta:</TH>   
    </TR>
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap >Plazo</TH>
      <%
      String name1= "";
      String name2= ""; 
      for(int i=1; i<8;i++) {
        name1= ""+i;
      %> 
      <TH ALIGN=CENTER  nowrap>
        <% if (userPO.getHeader1().equals("")) {%>
            <%= tbRate.getField("E03CDUA"+name1).getString().trim()%>
            <input type="hidden" name="E03CDUA<%=i%>" value="<%= tbRate.getField("E03CDUA"+name1).getString().trim()%>">
        <% } else {%>
      		<input type="input" name="E03CDUA<%=i%>" value="<%= tbRate.getField("E03CDUA"+name1).getString().trim()%>" size=12 maxlength=12 <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}else{%>onkeypress="enterInteger()"<%}%>>
      	<% } %>
      </TH> 
      <% } %>  
    </TR>
      <%
       for(int i=1;i<16; i++) { 
         name2=(i<10)? "0"+i: ""+i;                               
       %>             
                    
        <TR>
          <td align="center" id=trdark>
           <% if (userPO.getHeader1().equals("")) {%>
            <%= tbRate.getField("E03CDD"+name2).getString().trim()%>
          	<input type="hidden" name="E03CDD<%=name2%>" size="5" maxlength="4" value="<%= tbRate.getField("E03CDD"+name2).getString().trim()%>">      	
		   <% } else {%>
		    <input type="input" name="E03CDD<%=name2%>" size="5" maxlength="4" value="<%= tbRate.getField("E03CDD"+name2).getString().trim()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}else{%>onkeypress="enterInteger()"<%}%>>      			   
		   <% } %>
		  </td>
      	  <% 
      		for(int j=1; j<8;j++) {
      		  name1= ""+ j + name2;
     	  %>
          <td NOWRAP align=center>
          	<input type="input" name="E03CL<%=name1%>" size="10" maxlength="10" value="<%= tbRate.getField("E03CL"+name1).getString().trim()%>" <%if (userPO.getPurpose().equals("READONLY")) {%>readonly<%}%>>      	
		  </td>		  
		  <% } %>
		</TR>
        <%}%> 
  </TABLE>
  <br>
  <TABLE class=tbenter>
  <%if (!userPO.getPurpose().equals("READONLY")) {%>
  <tr>
  	<td align="center">         
	      <input id="EIBSBTN" type=button name="Submit" value="Enviar" onclick="goAction('S')">
  	</td>
  	<td align="center">         
	      <input id="EIBSBTN" type=button name="Submit" value="Variar Ter." onclick="goAction('C')">
    </td>
  </tr>
  <%}%>
  </TABLE>
  <%if (!userPO.getHeader1().equals("")) {%>
    <SCRIPT Language="javascript">
   		document.forms[0].E03CDUA1.focus();
	    document.forms[0].E03CDUA1.select(); 	
    </SCRIPT>
  <%}%>
</form>
</body>
</html>

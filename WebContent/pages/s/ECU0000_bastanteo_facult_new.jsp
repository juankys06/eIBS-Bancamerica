<html> 
<head>
<title>Creacion de Facultades Limitadas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgFac" class= "datapro.eibs.beans.ECU000007Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<% 
   if (msgFac == null) msgFac = new datapro.eibs.beans.ECU000007Message();   
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

<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "C") { 
    	document.forms[0].SCREEN.value = 70;   
		document.forms[0].submit();
  	} 
  }
  
</SCRIPT>

</head>
<body>

<H3 align="center">Sistema de Bastanteo - Creacion de Facultades Limitadas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_facult_new,ECU0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="74">
  <table class="tableinfo">
   <tr>
   	  <td>
   		<TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">C&oacute;digo de Cliente :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E07CULCUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E07CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
				</TD>
			</TR>
			<% 
 				if (!userPO.getHeader3().equals("Y")  ) {
			%>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Integrante :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E07CULMAN" size="3" maxlength="2" value="<%= userPO.getHeader18() %>" readonly>
					<INPUT type="text" name="E07CULMA1" size="40" maxlength="35" value="<%= userPO.getHeader19() %>" readonly> 
				</TD>
			</TR>
			<% } else { %>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Apoderado :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="hidden" name="E07CULMAN" size="3" maxlength="2" value="01" readonly>
					<INPUT type="text" name="E07CULMA1" size="40" maxlength="35" value="<%= userPO.getHeader19() %>" readonly> 
				</TD>
			</TR>
			<% } %>
			<% 
 				if (!userPO.getHeader3().equals("Y")  ) {
			%>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Cargo :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E07CUMUC5" size="5" maxlength="4" value="<%= userPO.getHeader20() %>" readonly>
					<INPUT type="text" name="E07CUMUCN" size="40" maxlength="35" value="<%= userPO.getHeader21() %>" readonly> 
				</TD>
			</TR>
			<% } %>
 		</TABLE>
 	  </td>
   </tr>
   </table>
  <h4></h4>
  <table class="tableinfo">
   <tr>
   	  <td>
    	<table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">Facultad 1: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CULFAC" size="5" maxlength="4" value="<%= msgFac.getE07CULFAC() %>">
      	    <input type="text" name="E07CULFAN" size="40" maxlength="35" value="<%= msgFac.getE07CULFAN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E07CULFAC','E07CULFAN','YF')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
    	<tr id="trclear"> 
          <td> 
             <div align="right">Tiene Condiciones: </div>
          </td>
          <td nowrap >  
             <input type="radio" name="E07CULCON" value="Y" <%if (msgFac.getE07CULCON().equals("Y")) out.print("checked"); %>>
              S&iacute; 
             <input type="radio" name="E07CULCON" value="N" <%if (msgFac.getE07CULCON().equals("N")) out.print("checked"); %>>
              No 
          </td>
        </tr>
        <% 
 			if (!userPO.getHeader3().equals("Y")  ) {
		%>
     	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CULCFI" >
                <OPTION value="0" <% if (msgFac.getE07CULCFI().equals("0")) out.print("SELECTED");%>>No Aplica</OPTION>
                <OPTION value="1" <% if (msgFac.getE07CULCFI().equals("1")) out.print("SELECTED");%>>Firma Individual</OPTION>
                <OPTION value="2" <% if (msgFac.getE07CULCFI().equals("2")) out.print("SELECTED");%>>Firmas Conjuntas Indistintas</OPTION>
                <OPTION value="3" <% if (msgFac.getE07CULCFI().equals("3")) out.print("SELECTED");%>>Firmas Conjuntas Específicas</OPTION>
            </select>
          </td>     
      	</tr>
    	<tr id=trclear> 
		  <td> 
		     <div align="right">N&uacute;mero de Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CULNFI" size="4" maxlength="3" value="<%= msgFac.getE07CULNFI() %>">
	      </td>     
      	</tr>
      	<% } %>
      	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Autorizaciones: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CULAUT" >
                <OPTION value="1" <% if (msgFac.getE07CULAUT().equals("1")) out.print("SELECTED");%>>No Requiere Ningun Tipo de Autorizacion</OPTION>
                <OPTION value="2" <% if (msgFac.getE07CULAUT().equals("2")) out.print("SELECTED");%>>Requiere Autorizacion de la Junta Directiva</OPTION>
                <OPTION value="3" <% if (msgFac.getE07CULAUT().equals("3")) out.print("SELECTED");%>>Requiere Autorizacion de la Asamblea de Accionistas</OPTION>
                <OPTION value="4" <% if (msgFac.getE07CULAUT().equals("4")) out.print("SELECTED");%>>Requiere Autorizacion Especial</OPTION>
            </select>
          </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Monto Autorizado: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CULAMT" size="20" maxlength="15" value="<%= msgFac.getE07CULAMT() %>">
	      </td>     
      	</tr>
     </table>
     
         </td>
   </tr>

  </table>
     
 <h4></h4>
 
  <table class="tableinfo">
   <tr>
   	  <td>
     
     
     <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">Facultad 2: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU1FAC" size="5" maxlength="4" value="<%= msgFac.getE07CU1FAC() %>">
      	    <input type="text" name="E07CU1FAN" size="40" maxlength="35" value="<%= msgFac.getE07CU1FAN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E07CU1FAC','E07CU1FAN','YF')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
    	<tr id="trclear"> 
          <td> 
             <div align="right">Tiene Condiciones: </div>
          </td>
          <td nowrap >  
             <input type="radio" name="E07CU1CON" value="Y" <%if (msgFac.getE07CU1CON().equals("Y")) out.print("checked"); %>>
              S&iacute; 
             <input type="radio" name="E07CU1CON" value="N" <%if (msgFac.getE07CU1CON().equals("N")) out.print("checked"); %>>
              No 
          </td>
        </tr>
        <% 
 			if (!userPO.getHeader3().equals("Y")  ) {
		%>
     	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CU1CFI" >
                <OPTION value="0" <% if (msgFac.getE07CU1CFI().equals("0")) out.print("SELECTED");%>>No Aplica</OPTION>
                <OPTION value="1" <% if (msgFac.getE07CU1CFI().equals("1")) out.print("SELECTED");%>>Firma Individual</OPTION>
                <OPTION value="2" <% if (msgFac.getE07CU1CFI().equals("2")) out.print("SELECTED");%>>Firmas Conjuntas Indistintas</OPTION>
                <OPTION value="3" <% if (msgFac.getE07CU1CFI().equals("3")) out.print("SELECTED");%>>Firmas Conjuntas Específicas</OPTION>
            </select>
          </td>     
      	</tr>
    	<tr id=trclear> 
		  <td> 
		     <div align="right">N&uacute;mero de Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU1NFI" size="4" maxlength="3" value="<%= msgFac.getE07CU1NFI() %>">
	      </td>     
      	</tr>
      	<% } %>
      	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Autorizaciones: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CU1AUT" >
                <OPTION value="1" <% if (msgFac.getE07CU1AUT().equals("1")) out.print("SELECTED");%>>No Requiere Ningun Tipo de Autorizacion</OPTION>
                <OPTION value="2" <% if (msgFac.getE07CU1AUT().equals("2")) out.print("SELECTED");%>>Requiere Autorizacion de la Junta Directiva</OPTION>
                <OPTION value="3" <% if (msgFac.getE07CU1AUT().equals("3")) out.print("SELECTED");%>>Requiere Autorizacion de la Asamblea de Accionistas</OPTION>
                <OPTION value="4" <% if (msgFac.getE07CU1AUT().equals("4")) out.print("SELECTED");%>>Requiere Autorizacion Especial</OPTION>
            </select>
          </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Monto Autorizado: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU1AMT" size="20" maxlength="15" value="<%= msgFac.getE07CU1AMT() %>">
	      </td>     
      	</tr>
     </table>

    </td>
   </tr>

  </table>

<h4></h4>


  <table class="tableinfo">
   <tr>
   	  <td>

     
<table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">Facultad 3: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU2FAC" size="5" maxlength="4" value="<%= msgFac.getE07CU2FAC() %>">
      	    <input type="text" name="E07CU2FAN" size="40" maxlength="35" value="<%= msgFac.getE07CU2FAN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E07CU2FAC','E07CU2FAN','YF')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
    	<tr id="trclear"> 
          <td> 
             <div align="right">Tiene Condiciones: </div>
          </td>
          <td nowrap >  
             <input type="radio" name="E07CU2CON" value="Y" <%if (msgFac.getE07CU2CON().equals("Y")) out.print("checked"); %>>
              S&iacute; 
             <input type="radio" name="E07CU2CON" value="N" <%if (msgFac.getE07CU2CON().equals("N")) out.print("checked"); %>>
              No 
          </td>
        </tr>
        <% 
 			if (!userPO.getHeader3().equals("Y")  ) {
		%>
     	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CU2CFI" >
                <OPTION value="0" <% if (msgFac.getE07CU2CFI().equals("0")) out.print("SELECTED");%>>No Aplica</OPTION>
                <OPTION value="1" <% if (msgFac.getE07CU2CFI().equals("1")) out.print("SELECTED");%>>Firma Individual</OPTION>
                <OPTION value="2" <% if (msgFac.getE07CU2CFI().equals("2")) out.print("SELECTED");%>>Firmas Conjuntas Indistintas</OPTION>
                <OPTION value="3" <% if (msgFac.getE07CU2CFI().equals("3")) out.print("SELECTED");%>>Firmas Conjuntas Específicas</OPTION>
            </select>
          </td>     
      	</tr>
    	<tr id=trclear> 
		  <td> 
		     <div align="right">N&uacute;mero de Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU2NFI" size="4" maxlength="3" value="<%= msgFac.getE07CU2NFI() %>">
	      </td>     
      	</tr>
      	<% } %>
      	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Autorizaciones: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CU2AUT" >
                <OPTION value="1" <% if (msgFac.getE07CU2AUT().equals("1")) out.print("SELECTED");%>>No Requiere Ningun Tipo de Autorizacion</OPTION>
                <OPTION value="2" <% if (msgFac.getE07CU2AUT().equals("2")) out.print("SELECTED");%>>Requiere Autorizacion de la Junta Directiva</OPTION>
                <OPTION value="3" <% if (msgFac.getE07CU2AUT().equals("3")) out.print("SELECTED");%>>Requiere Autorizacion de la Asamblea de Accionistas</OPTION>
                <OPTION value="4" <% if (msgFac.getE07CU2AUT().equals("4")) out.print("SELECTED");%>>Requiere Autorizacion Especial</OPTION>
            </select>
          </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Monto Autorizado: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU2AMT" size="20" maxlength="15" value="<%= msgFac.getE07CU2AMT() %>">
	      </td>     
      	</tr>
     </table>

    </td>
   </tr>

  </table>

<h4></h4>

  <table class="tableinfo">
   <tr>
   	  <td>

<table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">Facultad 4: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU3FAC" size="5" maxlength="4" value="<%= msgFac.getE07CU3FAC() %>">
      	    <input type="text" name="E07CU3FAN" size="40" maxlength="35" value="<%= msgFac.getE07CU3FAN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E07CU3FAC','E07CU3FAN','YF')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
    	<tr id="trclear"> 
          <td> 
             <div align="right">Tiene Condiciones: </div>
          </td>
          <td nowrap >  
             <input type="radio" name="E07CU3CON" value="Y" <%if (msgFac.getE07CU3CON().equals("Y")) out.print("checked"); %>>
              S&iacute; 
             <input type="radio" name="E07CU3CON" value="N" <%if (msgFac.getE07CU3CON().equals("N")) out.print("checked"); %>>
              No 
          </td>
        </tr>
        <% 
 			if (!userPO.getHeader3().equals("Y")  ) {
		%>
     	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CU3CFI" >
                <OPTION value="0" <% if (msgFac.getE07CU3CFI().equals("0")) out.print("SELECTED");%>>No Aplica</OPTION>
                <OPTION value="1" <% if (msgFac.getE07CU3CFI().equals("1")) out.print("SELECTED");%>>Firma Individual</OPTION>
                <OPTION value="2" <% if (msgFac.getE07CU3CFI().equals("2")) out.print("SELECTED");%>>Firmas Conjuntas Indistintas</OPTION>
                <OPTION value="3" <% if (msgFac.getE07CU3CFI().equals("3")) out.print("SELECTED");%>>Firmas Conjuntas Específicas</OPTION>
            </select>
          </td>     
      	</tr>
    	<tr id=trclear> 
		  <td> 
		     <div align="right">N&uacute;mero de Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU3NFI" size="4" maxlength="3" value="<%= msgFac.getE07CU3NFI() %>">
	      </td>     
      	</tr>
      	<% } %>
      	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Autorizaciones: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CU3AUT" >
                <OPTION value="1" <% if (msgFac.getE07CU3AUT().equals("1")) out.print("SELECTED");%>>No Requiere Ningun Tipo de Autorizacion</OPTION>
                <OPTION value="2" <% if (msgFac.getE07CU3AUT().equals("2")) out.print("SELECTED");%>>Requiere Autorizacion de la Junta Directiva</OPTION>
                <OPTION value="3" <% if (msgFac.getE07CU3AUT().equals("3")) out.print("SELECTED");%>>Requiere Autorizacion de la Asamblea de Accionistas</OPTION>
                <OPTION value="4" <% if (msgFac.getE07CU3AUT().equals("4")) out.print("SELECTED");%>>Requiere Autorizacion Especial</OPTION>
            </select>
          </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Monto Autorizado: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU3AMT" size="20" maxlength="15" value="<%= msgFac.getE07CU3AMT() %>">
	      </td>     
      	</tr>
     </table>

    </td>
   </tr>

  </table>

<h4></h4>

  <table class="tableinfo">
   <tr>
   	  <td>

<table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">Facultad 5: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU4FAC" size="5" maxlength="4" value="<%= msgFac.getE07CU4FAC() %>">
      	    <input type="text" name="E07CU4FAN" size="40" maxlength="35" value="<%= msgFac.getE07CU4FAN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E07CU4FAC','E07CU4FAN','YF')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  </td>     
      	</tr>
    	<tr id="trclear"> 
          <td> 
             <div align="right">Tiene Condiciones: </div>
          </td>
          <td nowrap >  
             <input type="radio" name="E07CU4CON" value="Y" <%if (msgFac.getE07CU4CON().equals("Y")) out.print("checked"); %>>
              S&iacute; 
             <input type="radio" name="E07CU4CON" value="N" <%if (msgFac.getE07CU4CON().equals("N")) out.print("checked"); %>>
              No 
          </td>
        </tr>
        <% 
 			if (!userPO.getHeader3().equals("Y")  ) {
		%>
     	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CU4CFI" >
                <OPTION value="0" <% if (msgFac.getE07CU4CFI().equals("0")) out.print("SELECTED");%>>No Aplica</OPTION>
                <OPTION value="1" <% if (msgFac.getE07CU4CFI().equals("1")) out.print("SELECTED");%>>Firma Individual</OPTION>
                <OPTION value="2" <% if (msgFac.getE07CU4CFI().equals("2")) out.print("SELECTED");%>>Firmas Conjuntas Indistintas</OPTION>
                <OPTION value="3" <% if (msgFac.getE07CU4CFI().equals("3")) out.print("SELECTED");%>>Firmas Conjuntas Específicas</OPTION>
            </select>
          </td>     
      	</tr>
    	<tr id=trclear> 
		  <td> 
		     <div align="right">N&uacute;mero de Firmas: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU4NFI" size="4" maxlength="3" value="<%= msgFac.getE07CU4NFI() %>">
	      </td>     
      	</tr>
      	<% } %>
      	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">En Cuanto a Autorizaciones: </div>        
		  </td>
      	  <td nowrap> 
      	    <select name="E07CU4AUT" >
                <OPTION value="1" <% if (msgFac.getE07CU4AUT().equals("1")) out.print("SELECTED");%>>No Requiere Ningun Tipo de Autorizacion</OPTION>
                <OPTION value="2" <% if (msgFac.getE07CU4AUT().equals("2")) out.print("SELECTED");%>>Requiere Autorizacion de la Junta Directiva</OPTION>
                <OPTION value="3" <% if (msgFac.getE07CU4AUT().equals("3")) out.print("SELECTED");%>>Requiere Autorizacion de la Asamblea de Accionistas</OPTION>
                <OPTION value="4" <% if (msgFac.getE07CU4AUT().equals("4")) out.print("SELECTED");%>>Requiere Autorizacion Especial</OPTION>
            </select>
          </td>     
      	</tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Monto Autorizado: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E07CU4AMT" size="20" maxlength="15" value="<%= msgFac.getE07CU4AMT() %>">
	      </td>     
      	</tr>
     </table>

     
     
     
    </td>
   </tr>

  </table>
  <h4>Observaciones</h4>
 
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB1" size="90" maxlength="80" value="<%= msgFac.getE07CULOB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB2" size="90" maxlength="80" value="<%= msgFac.getE07CULOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB3" size="90" maxlength="80" value="<%= msgFac.getE07CULOB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB4" size="90" maxlength="80" value="<%= msgFac.getE07CULOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E07CULOB5" size="90" maxlength="80" value="<%= msgFac.getE07CULOB5().trim()%>">
            </td>
          </tr>
        </table>
        </td>
      </tr>
   </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
    <input id="EIBSBTN" type="button" name="Submit" value="Cancelar" onclick="goAction('C')">
  </p>
  
</form>
</body>
</html>

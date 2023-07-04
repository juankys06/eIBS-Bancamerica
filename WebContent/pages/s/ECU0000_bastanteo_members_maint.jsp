<html> 
<head>
<title>Mantenimiento de Integrantes de la Administraci&oacute;n</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgMTMem" class= "datapro.eibs.beans.ECU000005Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>

<SCRIPT Language="Javascript">
    builtNewMenu(bastanteo_menu_2);
</SCRIPT>

<SCRIPT language="javascript">
  
  function goAction(opt) {
    
    if (opt == "C") { 
    	document.forms[0].SCREEN.value = 50;   
		document.forms[0].submit();
  	} 
  }
  
</SCRIPT>

</head>
<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
 	 out.println("<SCRIPT> initMenu();  </SCRIPT>");
%>

<H3 align="center">Sistema de Bastanteo - Mantenimiento de Integrantes de la Administraci&oacute;n<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_members_maint,ECU0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="53">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <TABLE cellspacing=0 cellpadding=2 width="100%" border="0">    
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">C&oacute;digo de Cliente :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E05CUMCUN" size="10" maxlength="9" value="<%= userPO.getHeader16() %>" readonly> 
				</TD>
			</TR>
			<TR>
				<TD nowrap width="40%">
					<DIV align="right">Nombre o Denominaci&oacute;n Social :</DIV>
				</TD>
				<TD nowrap width="60%">
					<INPUT type="text" name="E05CUSNA1" size="40" maxlength="35" value="<%= userPO.getHeader17() %>" readonly>  
				</TD>
			</TR>
			<TR> 
	      		<TD nowrap width="40%">
					<DIV align="right">Secuencia del Integrante: </div>
		  		</TD>
		  		<TD nowrap width="60%">
					<INPUT type="text" name="E05CUMMAN" readonly size="3" maxlength="2" value="<%= msgMTMem.getE05CUMMAN() %>"> 
		  		</TD>
     		</TR>
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
	      <td nowrap >
				<div align="right">Nombre: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E05CUMMA1" size="40" maxlength="35" value="<%= msgMTMem.getE05CUMMA1() %>"> 
		  </td>
     	</tr>
     	<tr id=trdark> 
            <td nowrap width="39%"> 
              <div align="right">N&uacute;mero de Identificaci&oacute;n :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E05CUMBNI" size="16" maxlength="15"  value="<%= msgMTMem.getE05CUMBNI().trim()%>">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
            </td>
        </tr>
     	<tr id=trclear>
     		<td nowrap width="39%"> 
              <div align="right">Tipo de Identificaci&oacute;n :</div>
            </td>
     		<td>
              <input type="text" name="E05CUMTID" size="5" maxlength="4" value="<%= msgMTMem.getE05CUMTID().trim()%>">
              <input type="text" name="E05CUMTIN" size="40" maxlength="35" readonly value="<%= msgMTMem.getE05CUMTIN().trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E05CUMTID','E05CUMTIN','34')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
            </td>
        </tr>
        <tr id=trdark>
        	<td nowrap width="39%"> 
              <div align="right">Pa&iacute;s :</div>
            </td>
     		<td>
              <input type="text" name="E05CUMPID" size="5" maxlength="4" value="<%= msgMTMem.getE05CUMPID().trim()%>">
              <input type="text" name="E05CUMPIN" size="40" maxlength="35" readonly value="<%= msgMTMem.getE05CUMPIN().trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E05CUMPID','E05CUMPIN','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
            </td>
        </tr>
     	<tr id=trdark> 
		  <td nowrap> 
		     <div align="right">Cargo: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E05CUMUC5" size="5" maxlength="4" value="<%= msgMTMem.getE05CUMUC5() %>">
      	    <input type="text" name="E05CUMUCN" size="40" maxlength="35" value="<%= msgMTMem.getE05CUMUCN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E05CUMUC5','E05CUMUCN','YC')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
    	<tr id=trclear> 
		  <td> 
		     <div align="right">Fecha de Designaci&oacute;n: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E05CUMDD1" size="2" maxlength="2" value="<%= msgMTMem.getE05CUMDD1() %>">
	      	<input type="text" name="E05CUMDD2" size="2" maxlength="2" value="<%= msgMTMem.getE05CUMDD2() %>">
	      	<input type="text" name="E05CUMDD3" size="2" maxlength="2" value="<%= msgMTMem.getE05CUMDD3() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E05CUMDD1,document.forms[0].E05CUMDD2,document.forms[0].E05CUMDD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
	      	&nbsp;T&eacute;rmino&nbsp;
	      	<input type="text" name="E05MBMTRM" size="4" maxlength="4" value="<%= msgMTMem.getE05MBMTRM().trim()%>">
              <select name="E05MBRTRC">
                <option value="D" <% if(msgMTMem.getE05MBRTRC().equals("D")) out.print("selected");%>>D&iacute;a(s)</option>
                <option value="M" <% if(msgMTMem.getE05MBRTRC().equals("M")) out.print("selected");%>>Mes(es)</option>
                <option value="Y" <% if(msgMTMem.getE05MBRTRC().equals("Y")) out.print("selected");%>>A&ntilde;o(s)</option>
              </select>
	      </td>     
      	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Vencimiento: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E05CUMD21" size="2" maxlength="2" value="<%= msgMTMem.getE05CUMD21() %>">
	      	<input type="text" name="E05CUMD22" size="2" maxlength="2" value="<%= msgMTMem.getE05CUMD22() %>">
	      	<input type="text" name="E05CUMD23" size="2" maxlength="2" value="<%= msgMTMem.getE05CUMD23() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E05CUMD21,document.forms[0].E05CUMD22,document.forms[0].E05CUMD23)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
      	  </td>     
      	</tr>
     	<tr id=trclear> 
          <td> 
             <div align="right">Tiene Reeleci&oacute;n: </div>
          </td>
          <td nowrap >  
             <input type="radio" name="E05CUMFL1" value="Y" <%if (msgMTMem.getE05CUMFL1().equals("Y")) out.print("checked"); %>>
              S&iacute; 
             <input type="radio" name="E05CUMFL1" value="N" <%if (msgMTMem.getE05CUMFL1().equals("N")) out.print("checked"); %>>
              No 
          </td>
        </tr>
        <tr id=trdark> 
          <td> 
             <div align="right">Facultades del Cargo: </div>
          </td>
          <td nowrap >  
             <input type="radio" name="E05CUMFL2" value="A" <%if (msgMTMem.getE05CUMFL2().equals("A")) out.print("checked"); %>>
              Algunas 
             <input type="radio" name="E05CUMFL2" value="T" <%if (msgMTMem.getE05CUMFL2().equals("T")) out.print("checked"); %>>
              Todas
             <input type="radio" name="E05CUMFL2" value="N" <%if (msgMTMem.getE05CUMFL2().equals("N")) out.print("checked"); %>>
              Ninguna  
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
              <input type="text" name="E05CUMOB1" size="90" maxlength="80" value="<%= msgMTMem.getE05CUMOB1().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMOB2" size="90" maxlength="80" value="<%= msgMTMem.getE05CUMOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMOB3" size="90" maxlength="80" value="<%= msgMTMem.getE05CUMOB3().trim()%>">
            </td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMOB4" size="90" maxlength="80" value="<%= msgMTMem.getE05CUMOB4().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMOB5" size="90" maxlength="80" value="<%= msgMTMem.getE05CUMOB5().trim()%>">
            </td>
          </tr>
        </table>
      </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
    <input id="EIBSBTN" type="button" name="Submit" value="Cancelar" onclick="goAction('C')">
  </p>
  
</form>
</body>
</html>

<html> 
<head>
<title>Mantenimiento del Apoderado</title>
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

<H3 align="center">Sistema de Bastanteo - Mantenimiento del Propietario/Apoderado<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bastanteo_attorney_maint,ECU0000"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="53">
  <INPUT type=hidden name="E05CUMMAN" value="01">
  
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
				<div align="right">Nombre del Propietario: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E05CUMMA3" size="40" maxlength="35" value="<%= msgMTMem.getE05CUMMA3() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
            <td nowrap width="39%"> 
              <div align="right">N&uacute;mero de Identificaci&oacute;n del Propietario:</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E05CUMCTR" size="16" maxlength="15" value="<%= msgMTMem.getE05CUMCTR().trim()%>">
            </td>
        </tr>
     	<tr id=trdark>
     		<td nowrap width="39%"> 
              <div align="right">Tipo de Identificaci&oacute;n del Propietario:</div>
            </td>
     		<td>
              <input type="hidden" name="E05CUMINC" size="5" maxlength="4" value="<%= msgMTMem.getE05CUMINC().trim()%>">
              <input type="text" name="E05CUMINN" size="40" maxlength="35" readonly value="<%= msgMTMem.getE05CUMINN().trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E05CUMINC','E05CUMINN','34')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
            </td>
        </tr>
        <tr id=trclear>
        	<td nowrap width="39%"> 
              <div align="right">Pa&iacute;s del Propietario:</div>
            </td>
     		<td>
              <input type="hidden" name="E05CUMBNC" size="5" maxlength="4" value="<%= msgMTMem.getE05CUMBNC().trim()%>">
              <input type="text" name="E05CUMBNN" size="40" maxlength="35" readonly value="<%= msgMTMem.getE05CUMBNN().trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E05CUMBNC','E05CUMBNN','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
            </td>
        </tr>
    
    
     	<tr id=trdark> 
	      <td nowrap >
				<div align="right">Nombre del Apoderado: </div>
		  </td>
		  <td nowrap>
				<input type="text" name="E05CUMMA1" size="40" maxlength="35" value="<%= msgMTMem.getE05CUMMA1() %>"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
            <td nowrap width="39%"> 
              <div align="right">N&uacute;mero de Identificaci&oacute;n del Apoderado:</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E05CUMBNI" size="16" maxlength="15" value="<%= msgMTMem.getE05CUMBNI().trim()%>">
            </td>
        </tr>
     	<tr id=trdark>
     		<td nowrap width="39%"> 
              <div align="right">Tipo de Identificaci&oacute;n del Apoderado:</div>
            </td>
     		<td>
              <input type="hidden" name="E05CUMTID" size="5" maxlength="4" value="<%= msgMTMem.getE05CUMTID().trim()%>">
              <input type="text" name="E05CUMTIN" size="40" maxlength="35" readonly value="<%= msgMTMem.getE05CUMTIN().trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E05CUMTID','E05CUMTIN','34')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
            </td>
        </tr>
        <tr id=trclear>
        	<td nowrap width="39%"> 
              <div align="right">Pa&iacute;s del Apoderado:</div>
            </td>
     		<td>
              <input type="hidden" name="E05CUMPID" size="5" maxlength="4" value="<%= msgMTMem.getE05CUMPID().trim()%>">
              <input type="text" name="E05CUMPIN" size="40" maxlength="35" readonly value="<%= msgMTMem.getE05CUMPIN().trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E05CUMPID','E05CUMPIN','03')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
            </td>
        </tr>
    	<tr id=trdark> 
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
     	<tr id=trclear> 
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
     	<tr id=trdark> 
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
        <tr id=trclear> 
          <td> 
             <div align="right">Facultades: </div>
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
  
    <h4>Datos del Poder</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
          <td> 
		     <div align="right">Registro o Notar&iacute;a: </div>        
		  </td>
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMCTY" size="30" maxlength="30" value="<%= msgMTMem.getE05CUMCTY().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
          <td> 
		     <div align="right">Circunscripci&oacute;n: </div>        
		  </td>
          
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMMA2" size="35" maxlength="35" value="<%= msgMTMem.getE05CUMMA2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
          <td> 
		     <div align="right">N&uacute;mero de Documento: </div>        
		  </td>

            <td nowrap width="100%"> 
              <input type="text" name="E05CUMNST" size="9" maxlength="7" value="<%= msgMTMem.getE05CUMNST().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
          <td> 
		     <div align="right">Tomo: </div>        
		  </td>

            <td nowrap width="100%"> 
              <input type="text" name="E05CUMPOB" size="10" maxlength="8" value="<%= msgMTMem.getE05CUMPOB().trim()%>">
            </td>
          </tr>
        <tr id=trdark> 
		  <td> 
		     <div align="right">Fecha de Tomo: </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E05REGRDD" size="2" maxlength="2" value="<%= msgMTMem.getE05REGRDD() %>">
	      	<input type="text" name="E05REGRMM" size="2" maxlength="2" value="<%= msgMTMem.getE05REGRMM() %>">
	      	<input type="text" name="E05REGRYY" size="2" maxlength="2" value="<%= msgMTMem.getE05REGRYY() %>">
	      	<a href="javascript:DatePicker(document.forms[0].E05REGRDD,document.forms[0].E05REGRMM,document.forms[0].E05REGRYY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
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
          <tr id="trclear"> 
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMOB2" size="90" maxlength="80" value="<%= msgMTMem.getE05CUMOB2().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="100%"> 
              <input type="text" name="E05CUMOB3" size="90" maxlength="80" value="<%= msgMTMem.getE05CUMOB3().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
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

<html>
<head>
<title>Codigos Especiales</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "msgLC" class= "datapro.eibs.beans.ELC050006Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </script>

<script language="Javascript">

builtNewMenu(lc_opening);
initMenu();
</script>
</head>
<body bgcolor="#FFFFFF">
<% if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

	String flgrd = msgLC.getH06SCRCOD();
	if( flgrd==null ) flgrd = "";
	if(!flgrd.equals(""))
     	flgrd = "readonly";
%>

<h3 align="center">Documentos Requeridos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_documents.jsp,0500"></h3>
<hr size="4"> <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500">
 <input type=HIDDEN name="SCREEN" value="602"> 
 <input type=HIDDEN name="H06FLGWK3" value="<%= msgLC.getH06FLGWK3()%>"> 
 
  <table cellspacing="0" cellpadding="2" width="100%" border="1" bordercolor="#000000">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tbody><tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E06LCMCUN" size="9" maxlength="9" readonly value="<%=msgLC.getE06LCMCUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3"> 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E06CUSNA1" size="45" maxlength="45" readonly value="<%=msgLC.getE06CUSNA1().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E06LCMACC" size="12" maxlength="12" value="<%=msgLC.getE06LCMACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td><td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E06LCMPRO" size="4" maxlength="4" readonly value="<%=msgLC.getE06LCMPRO().trim()%>">
                </b> </div>
            </td>
            
            <td nowrap width="16%" align="right"><b>Descripcion de Producto :</b></td><td nowrap width="16%" align="left">
                  <b>
                  <input type="text" name="E02DSCPRO" size="40" maxlength="35" value="<%=userPO.getHeader14()%>" readonly>
                  </b></td>
            
          </tr>
        </tbody></table>
      </td>
    </tr>
  </table>
  <h4>Documentos</h4> 
  <table cellpadding=2 cellspacing=0 width="100%" border="1" bordercolor="#000000" bgcolor="#FFFFFF" >
    <tr bordercolor="#FFFFFF"> 
      <td nowrap > 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%" align="center">
              <a href="javascript:GetCodeDescCNOFC('E02OFC','D02OFC','15')"></a>Cod. Documento</td>
				<td nowrap width="21%" align="center">Descripcion 
            </td>
				<td nowrap width="5%" align="center">Originales</td>
				<td nowrap width="36%">Copias</td>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="30%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD1" size="5" maxlength="4" value="<%= msgLC.getE06LCMDD1()%>" readonly>              
              <% if( !flgrd.trim().equals("readonly") ) {%> <a href="javascript:GetCodeDescCNOFC('E06LCMDD1','E06LCMDS1','08')">
              		<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a><%}%> 
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS1" size="40" readonly	maxlength="35" value="<%= msgLC.getE06LCMDS1() %>" ></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO1" size="2" <%= flgrd %>	maxlength="1" value="<%= msgLC.getE06LCMDO1()%>"></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC1" size="2"	<%= flgrd %> maxlength="1" value="<%= msgLC.getE06LCMDC1()%>"></td>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD2" size="5" maxlength="4" value="<%= msgLC.getE06LCMDD2()%>" readonly>              
              <% if( !flgrd.trim().equals("readonly") ) {%> <a href="javascript:GetCodeDescCNOFC('E06LCMDD2','E06LCMDS2','08')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a><%}%> 
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS2" size="40"	maxlength="35" value="<%= msgLC.getE06LCMDS2()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO2" size="2"	<%= flgrd %>	maxlength="1" value="<%= msgLC.getE06LCMDO2()%>"></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC2" size="2"	<%= flgrd %>	maxlength="1" value="<%= msgLC.getE06LCMDC2()%>"></td>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="3%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD3" size="5" maxlength="4" value="<%= msgLC.getE06LCMDD3()%>" readonly>              
              <% if( !flgrd.trim().equals("readonly") ) {%> <a href="javascript:GetCodeDescCNOFC('E06LCMDD3','E06LCMDS3','08')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a><%}%> 
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS3" size="40"		maxlength="35" value="<%= msgLC.getE06LCMDS3()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO3" size="2"	<%= flgrd %>	maxlength="1" value="<%= msgLC.getE06LCMDO3()%>"></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC3"	<%= flgrd %>	size="2" maxlength="1" value="<%= msgLC.getE06LCMDC3()%>"></td>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD4" size="5" maxlength="4" value="<%= msgLC.getE06LCMDD4()%>" readonly>             
              <% if( !flgrd.trim().equals("readonly") ) {%> <a href="javascript:GetCodeDescCNOFC('E06LCMDD4','E06LCMDS4','08')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a><%}%> 
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS4" size="40"		maxlength="35" value="<%= msgLC.getE06LCMDS4()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO4" size="2" <%= flgrd %>	maxlength="1" value="<%= msgLC.getE06LCMDO4()%>"></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC4"	<%= flgrd %>	size="2" maxlength="1" value="<%= msgLC.getE06LCMDC4()%>"></td>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="30%"><div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD5" size="5" maxlength="4" value="<%= msgLC.getE06LCMDD5()%>" readonly>       
              <% if( !flgrd.trim().equals("readonly") ) {%> <a href="javascript:GetCodeDescCNOFC('E06LCMDD5','E06LCMDS5','08')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a><%}%> 
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS5" size="40"		maxlength="35" value="<%= msgLC.getE06LCMDS5()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO5" size="2"	<%= flgrd %>	maxlength="1" value="<%= msgLC.getE06LCMDO5()%>"></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC5"	<%= flgrd %>	size="2" maxlength="1" value="<%= msgLC.getE06LCMDC5()%>"></td>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD6" size="5" maxlength="4" value="<%= msgLC.getE06LCMDD6()%>" readonly>             
              <% if( !flgrd.trim().equals("readonly") ) {%> <a href="javascript:GetCodeDescCNOFC('E06LCMDD6','E06LCMDS6','08')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a><%}%> 
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS6" size="40"	maxlength="35" value="<%= msgLC.getE06LCMDS6()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO6" size="2" <%= flgrd %>	maxlength="1" value="<%= msgLC.getE06LCMDO6()%>"></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC6"	size="2" <%= flgrd %> maxlength="1" value="<%= msgLC.getE06LCMDC6()%>"></td>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD7" size="5" maxlength="4" value="<%= msgLC.getE06LCMDD7()%>" readonly>              
              <% if( !flgrd.trim().equals("readonly") ) {%> <a href="javascript:GetCodeDescCNOFC('E06LCMDD7','E06LCMDS7','08')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a><%}%> 
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS7" size="40"	maxlength="35" value="<%= msgLC.getE06LCMDS7()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO7" size="2" <%= flgrd %>	maxlength="1" value="<%= msgLC.getE06LCMDO7()%>"></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC7"	size="2" <%= flgrd %> maxlength="1" value="<%= msgLC.getE06LCMDC7()%>"></td>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="30%"><div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD8" size="5" maxlength="4" value="<%= msgLC.getE06LCMDD8()%>" readonly>            
              <% if( !flgrd.trim().equals("readonly") ) {%> <a href="javascript:GetCodeDescCNOFC('E06LCMDD8','E06LCMDS8','08')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0"></a><%}%> 
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS8" size="40"	maxlength="35" value="<%= msgLC.getE06LCMDS8()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO8" size="2" <%= flgrd %>	maxlength="1" value="<%= msgLC.getE06LCMDO8()%>"></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC8"	size="2" <%= flgrd %> maxlength="1" value="<%= msgLC.getE06LCMDC8()%>"></td>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%">             
                <input type="text" name="E06LCMDD9" size="5" maxlength="4"	value="<%= msgLC.getE06LCMDD9()%>" readonly> 
                <% if( !flgrd.trim().equals("readonly") ) {%> <a href="javascript:GetCodeDescCNOFC('E06LCMDD9','E06LCMDS9','08')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."	align="bottom" border="0"></a><%}%>
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS9" size="40"	maxlength="35" value="<%= msgLC.getE06LCMDS9()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO9" size="2" <%= flgrd %>	maxlength="1" value="<%= msgLC.getE06LCMDO9()%>"></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC9"	size="2" <%= flgrd %> maxlength="1" value="<%= msgLC.getE06LCMDC9()%>"></td>
			</tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type="submit" name="Submit" value="Enviar">
  </p>
</form>
</body>
</html>


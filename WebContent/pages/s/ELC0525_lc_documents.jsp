<html>
<head>
<title>Codigos Especiales</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "msg050006" class= "datapro.eibs.beans.ELC050006Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </script>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </script>


<script language="Javascript">
builtNewMenu(lc_apr_cc_new);
initMenu();
</script>
</head>
<body bgcolor="#FFFFFF">
<% if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

 String flgrd = msg050006.getH06SCRCOD();
 if( flgrd==null ) {
    flgrd = "";
 }else{
    if(!flgrd.equals("0")){
     	//flgrd = "readonly";
    }else flgrd = "";
 }
%>

<h3 align="center">Documentos Requeridos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="lc_documents.jsp,0525"></h3>
<hr size="4"> <form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500">
 <input type=HIDDEN name="SCREEN" value="602"> 
 <input type=HIDDEN name="H06FLGWK3" value="<%= msg050006.getH06FLGWK3()%>"> 
 
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
                <input type="text" name="E06LCMCUN" size="9" maxlength="9" readonly value="<%=msg050006.getE06LCMCUN().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3"> 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E06CUSNA1" size="45" maxlength="45" readonly value="<%=msg050006.getE06CUSNA1().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E06LCMACC" size="12" maxlength="12" value="<%=msg050006.getE06LCMACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td><td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E06LCMPRO" size="4" maxlength="4" readonly value="<%=msg050006.getE06LCMPRO().trim()%>">
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
              <input type="text" name="E06LCMDD1" size="5" maxlength="4" value="<%= msg050006.getE06LCMDD1()%>" readonly>              
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS1" size="40" readonly	maxlength="35" value="<%= msg050006.getE06LCMDS1() %>" ></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO1" size="2" <%= flgrd %>	maxlength="1" value="<%= msg050006.getE06LCMDO1()%>" readonly></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC1" size="2"	<%= flgrd %> maxlength="1" value="<%= msg050006.getE06LCMDC1()%>" readonly></td>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD2" size="5" maxlength="4" value="<%= msg050006.getE06LCMDD2()%>" readonly>              
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS2" size="40"	maxlength="35" value="<%= msg050006.getE06LCMDS2()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO2" size="2"	<%= flgrd %>	maxlength="1" value="<%= msg050006.getE06LCMDO2()%>" readonly></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC2" size="2"	<%= flgrd %>	maxlength="1" value="<%= msg050006.getE06LCMDC2()%>" readonly></td>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="3%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD3" size="5" maxlength="4" value="<%= msg050006.getE06LCMDD3()%>" readonly>              
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS3" size="40"		maxlength="35" value="<%= msg050006.getE06LCMDS3()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO3" size="2"	<%= flgrd %>	maxlength="1" value="<%= msg050006.getE06LCMDO3()%>" readonly></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC3"	<%= flgrd %>	size="2" maxlength="1" value="<%= msg050006.getE06LCMDC3()%>" readonly></td>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD4" size="5" maxlength="4" value="<%= msg050006.getE06LCMDD4()%>" readonly>             
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS4" size="40"		maxlength="35" value="<%= msg050006.getE06LCMDS4()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO4" size="2" <%= flgrd %>	maxlength="1" value="<%= msg050006.getE06LCMDO4()%>" readonly></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC4"	<%= flgrd %>	size="2" maxlength="1" value="<%= msg050006.getE06LCMDC4()%>" readonly></td>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="30%"><div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD5" size="5" maxlength="4" value="<%= msg050006.getE06LCMDD5()%>" readonly>       
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS5" size="40"		maxlength="35" value="<%= msg050006.getE06LCMDS5()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO5" size="2"	<%= flgrd %>	maxlength="1" value="<%= msg050006.getE06LCMDO5()%>" readonly></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC5"	<%= flgrd %>	size="2" maxlength="1" value="<%= msg050006.getE06LCMDC5()%>" readonly></td>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD6" size="5" maxlength="4" value="<%= msg050006.getE06LCMDD6()%>" readonly>             
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS6" size="40"	maxlength="35" value="<%= msg050006.getE06LCMDS6()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO6" size="2" <%= flgrd %>	maxlength="1" value="<%= msg050006.getE06LCMDO6()%>" readonly></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC6"	size="2" <%= flgrd %> maxlength="1" value="<%= msg050006.getE06LCMDC6()%>" readonly></td>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD7" size="5" maxlength="4" value="<%= msg050006.getE06LCMDD7()%>" readonly>              
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS7" size="40"	maxlength="35" value="<%= msg050006.getE06LCMDS7()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO7" size="2" <%= flgrd %>	maxlength="1" value="<%= msg050006.getE06LCMDO7()%>" readonly></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC7"	size="2" <%= flgrd %> maxlength="1" value="<%= msg050006.getE06LCMDC7()%>" readonly></td>
			</tr>
          <tr id="trdark"> 
            <td nowrap width="30%"><div align="right"></div>
            </td>
            <td nowrap width="8%"> 
              <input type="text" name="E06LCMDD8" size="5" maxlength="4" value="<%= msg050006.getE06LCMDD8()%>" readonly>            
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS8" size="40"	maxlength="35" value="<%= msg050006.getE06LCMDS8()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO8" size="2" <%= flgrd %>	maxlength="1" value="<%= msg050006.getE06LCMDO8()%>" readonly></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC8"	size="2" <%= flgrd %> maxlength="1" value="<%= msg050006.getE06LCMDC8()%>" readonly></td>
			</tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="8%">             
                <input type="text" name="E06LCMDD9" size="5" maxlength="4"	value="<%= msg050006.getE06LCMDD9()%>" readonly> 
            </td>
				<td nowrap width="21%"><input type="text" name="E06LCMDS9" size="40"	maxlength="35" value="<%= msg050006.getE06LCMDS9()%>" readonly></td>
				<td nowrap width="5%" align="center"><input type="text" name="E06LCMDO9" size="2" <%= flgrd %>	maxlength="1" value="<%= msg050006.getE06LCMDO9()%>" readonly></td>
				<td nowrap width="36%"><input type="text" name="E06LCMDC9"	size="2" <%= flgrd %> maxlength="1" value="<%= msg050006.getE06LCMDC9()%>" readonly></td>
			</tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>


<html>
<head>
<title>Codigos Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
</head>

<jsp:useBean id= "official" class= "datapro.eibs.beans.ESD085001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

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
}else if ( userPO.getOption().equals("CD") ) {
%>
	builtNewMenu(cd_i_opt);
<%   
}else if ( userPO.getOption().equals("CL_TYPE") || userPO.getOption().equals("CL_CUSTOMER")) {
%>
	builtNewMenu(cl_i_opt);
<%
}else if ( userPO.getOption().equals("LC") ) {
%>
	builtNewMenu(lc_i_opt);
<%
}else if ( userPO.getOption().equals("DV") ) {
%>
	builtNewMenu(coll_i_opt);
<%    
}else if ( userPO.getOption().equals("LN") ) {
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
}else  if ( userPO.getOption().equals("CLIENT_P") ) {
%>
	builtNewMenu(client_inq_personal_opt);
<%      
}else  if  ( userPO.getOption().equals("CLIENT_C") ){
%>
	builtNewMenu(client_inq_corp_opt);
<%
}
%>

 initMenu();
</SCRIPT>

<body bgcolor="#FFFFFF">

<h3 align="center">Datos del Oficial<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="official_inq.jsp,ESD0850"></h3> 
<hr size="4">
 <FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0850" >
  
  
  <h4>Información Básica</h4>
  <table  class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Código:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E01OFCCOD" size="15" value="<%= official.getE01OFCCOD() %>" readonly>
            </td>
          </tr>
          <tr> 
            <td nowrap width="30%"> 
              <div align="right">Nombre:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E01OFCNME" size="35" value="<%= official.getE01OFCNME().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="30%"> 
              <div align="right">Identificación:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E01OFCIDN" size="15" value="<%= official.getE01OFCIDN().trim()%>" readonly>
            </td> 
          </tr>
          <tr>
            <td nowrap width="30%"> 
              <div align="right">Telefono:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E01OFCPHN" size="15" value="<%= official.getE01OFCPHN().trim()%>" readonly>
            </td> 
            
          </tr>
          <tr id="trdark">
            <td nowrap width="30%"> 
              <div align="right">Extensión:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E01OFCPXT" size="10" value="<%= official.getE01OFCPXT().trim()%>" readonly>
            </td> 
            
          </tr>
          <tr> 
            <td nowrap width="30%"> 
              <div align="right">eMail:</div>
            </td>
            <td nowrap width="70%"> 
              <a href="mailto:<%= official.getE01OFCEML().trim()%>"><%= official.getE01OFCEML().trim()%></a>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Otros Datos</h4>
  <table  class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Banco:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E01OFCBNK" size="4" value="<%= official.getE01OFCBNK() %>" readonly>
            </td>
          </tr>
          <tr> 
            <td nowrap width="30%"> 
              <div align="right">Oficina Contabilidad:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E01OFCBRN" size="3" value="<%= official.getE01OFCBRN().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="30%"> 
              <div align="right">Centro Costo:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E01OFCCCN" size="7" value="<%= official.getE01OFCCCN().trim()%>" readonly>
            </td> 
          </tr>
          <tr>
            <td nowrap width="30%"> 
              <div align="right">Lugar de Trabajo:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E01OFCAOF" size="4" value="<%= official.getE01OFCAOF().trim()%>" readonly>
              <input type="text" name="E01DSCAOF" size="35" value="<%= official.getE01DSCAOF().trim()%>" readonly>
            </td> 
            
          </tr>
          <tr id="trdark">
            <td nowrap width="30%"> 
              <div align="right">Usuario Nivel Sup.:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E01OFCROT" size="10" value="<%= official.getE01OFCROT().trim()%>" readonly>
            </td> 
            
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>


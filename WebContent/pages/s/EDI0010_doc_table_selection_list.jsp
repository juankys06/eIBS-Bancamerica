<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Lista de Tablas
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "tblList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT language="javascript">

<%
if ( userPO.getOption().equals("RT") ) {
%>
	 builtNewMenu(rt_m_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	 builtNewMenu(sv_m_opt);
<%   
}
else if (userPO.getOption().equals("CD")){
%>
	 builtNewMenu(cd_m_opt);
<%   
}
else if (userPO.getOption().equals("LN")){
%>
	 builtNewMenu(ln_m_opt);
   <%   
 }
else if ( userPO.getOption().equals("CLIENT_P") ) {
   %>
		 builtNewMenu(client_personal_opt);
  <%      
 }
else if ( userPO.getOption().equals("CLIENT_C") ) {
   %>
		 builtNewMenu(client_corp_opt);
  <%      
 }
else if ( userPO.getOption().equals("LC") ) {
   %>
		 builtNewMenu(lc_i_opt);
  <%      
 }
else if ( userPO.getOption().equals("DV") ) {
   %>
		 builtNewMenu(coll_i_opt);
   <%
   }
   %>

</SCRIPT>

</HEAD>

<BODY >


<SCRIPT Language="Javascript"> 
     initMenu();
</SCRIPT>

<h3 align="center">Documentos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="doc_table_selection_list.jsp, EDI0010"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010" >
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1">
<INPUT TYPE=HIDDEN NAME="Type" VALUE="<%= userPO.getHeader22()%>">
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E02CUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E02NA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>
<%
if (userPO.getOption().equals("RT")||userPO.getOption().equals("SV") ) {
%>

          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E04CUN" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
<%
}
%>

  </table>

<%
if ( tblList.getNoResult() ) {
%>
  
  <TABLE class="tbenter" width=100% height=100%>
    <TR>
      <TD> 
        <div align="center">
          <font size="3"><b> No hay resultado que corresponda con su criterio de busqueda</b></font>
        </div>
      </TD>
    </TR>
  </TABLE>

<%   		
}
else {
%>
<h4>Selección de Tabla</h4>
<TABLE  id="mainTable" class="tableinfo"  NOWRAP>
    <TR id="trdark"> 
      <TH ALIGN=CENTER NOWRAP></TH>
      <TH ALIGN=CENTER NOWRAP>Tabla</TH>
      <TH ALIGN=CENTER NOWRAP>Descripción</TH>
    </TR>
  
    <%
                tblList.initRow();
                while (tblList.getNextRow()) {
                    if (tblList.getFlag().equals("")) {
                       out.println(tblList.getRecord());
                    }
                }
    %> 
    
</TABLE>

  <p>
    <div align="center"> 
      <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
    </div>
  </p>
      
<%
}
%>

</FORM>

</BODY>
</HTML>

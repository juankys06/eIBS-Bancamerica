<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Confirmacion</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<meta http-equiv="Refresh" CONTENT="7;url='<%=request.getContextPath()%>/pages/background.jsp'">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "chkList" class= "datapro.eibs.beans.JBListRec"  scope="session" /> 

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<body>


<h3 align="center">Confirmación de alta de movimientos</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_protect_confirm, ECH0335A"> 
<hr size="4">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark">
			<td nowrap width="16%" > 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%" > 
               <%= userPO.getIdentifier().trim()%>
            </td>
            <td nowrap width="11%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="10%" > 
              <div align="left"> 
                <%=userPO.getCusNum().trim()%>
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap width="42%" > 
              <div align="left">
                <%= userPO.getCusName().trim()%>
              </div>
            </td>            
          </tr>
        </table>
      </td>
    </tr>
  </table>
 
 <% if (true) { %> 
  
 <div align=center>
   <h4>
   Los movimientos se han dado de alta, con la(s) siguiente(s) exepcion(es).
   </h4>
 </div>
 
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top">
        <TABLE id="headTable">
          <TR id="trdark"> 
            <TH ALIGN=CENTER NOWRAP>No. Mov.</TH>
            <TH ALIGN=CENTER NOWRAP>No. Cuenta</TH>
            <TH ALIGN=CENTER NOWRAP>No. Documento</TH>            
            <TH ALIGN=CENTER NOWRAP>Importe</TH>
            <TH ALIGN=CENTER NOWRAP>Status</TH>
          </TR>
        </TABLE>
        
    <div id="dataDiv1" class="scbarcolor" style="padding:0" NOWRAP>
        <table id="dataTable"  NOWRAP>
    <%
         int row;
		 try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
         chkList.initRow();
         int k=1;
         while (chkList.getNextRow()) {
     %> 
            <TR> 
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(chkList.getRecord(4)) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(chkList.getRecord(3)) %></TD>
              <TD ALIGN=CENTER NOWRAP><%= Util.formatCell(chkList.getRecord(0)) %></TD>
              <TD ALIGN=RIGHT NOWRAP><%= Util.fcolorCCY(chkList.getRecord(1)) %></TD>
              <TD ALIGN=LEFT NOWRAP><%= Util.formatCell(chkList.getRecord(2)) %></TD>
            </TR>
      <%
              k++;
         }        
    %> 
          </table>
   </div>
   </TD>
  </TR>	
</TABLE>

<% } else { %>
      
 <div align=center>
   <h4>
   Los movimientos se han dado de alta.
   </h4>
 </div>
 

<% } %>
      
</form>
</body>
</html>

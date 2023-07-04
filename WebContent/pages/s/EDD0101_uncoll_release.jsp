<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.Util" %>
<title>Uncollected Release</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT  language="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="uncollRLS" class="datapro.eibs.beans.EDD010101Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT language="javascript"> 
  function confirmAct() {
    var ok = true;
    ok = confirm("Esta seguro de la operación a realizar ?");
    return ok;
  }
</SCRIPT>
</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) 
 {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }


%> 

<H3 align="center">Liberaci&oacute;n de Diferidos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="uncoll_release.jsp, EDD0101"></H3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.cleaning.JSEDD0101" onsubmit="return(confirmAct())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="200">
  <INPUT TYPE=HIDDEN NAME="E01ACTION" value="A">
  
  <h4>Ultima Liberaci&oacute;n</h4> 
  <table  class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          
          <tr id="trdark"> 
            <th align=center><div>Usuario</div>
            </th>
            <th align=center><div>Fecha</div>
            </th>
            <th align=center><div>Hora</div>
            </th>
          </tr>
          <tr id="trclear"> 
            
            <td align=center>
              <INPUT type="text" id="txtlabel" name="E01USERLI" value="<%= Util.formatCell(uncollRLS.getE01USERLI().trim())%>">                     
              </td>
            <td align=center>
              <INPUT type="text" id="txtlabel" name="E01DATELI" value="<%= Util.formatDate(uncollRLS.getE01DATELI().trim())%>">                     
             </td>
            <td align=center>
              <INPUT type="text" id="txtlabel" name="E01TIMELI" value="<%= Util.formatTime(uncollRLS.getE01TIMELI().trim())%>">                              
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>
 <h4>Fecha Proceso</h4> 
  <table  class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          
          
          <tr id="trdark"> 
            
            <td align=right>Fecha :
            </td>  
            <td align=center>
              <INPUT type="text" readonly name="E01CNTFE1" value="<%= uncollRLS.getE01CNTFE1().trim()%>" size=3>                     
              <INPUT type="text" readonly name="E01CNTFE2" value="<%= uncollRLS.getE01CNTFE2().trim()%>" size=3>                     
              <INPUT type="text" readonly name="E01CNTFE3" value="<%= uncollRLS.getE01CNTFE3().trim()%>" size=3>                     
             </td>            
          </tr>

        </table>
      </td>
    </tr>
  </table> 
  
 <p align="center"> 
   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
 </p>
</form>
   
</body>
</html>

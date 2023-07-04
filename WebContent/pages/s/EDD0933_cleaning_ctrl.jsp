<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.Util" %>
<title>Camara</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<SCRIPT language="Javascript1.1" SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="cleaningCNTRL" class="datapro.eibs.beans.EDD093301Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT language="javascript">
  function checkProc(){
   var status = "";
    if (document.forms[0].E01TIPOCA[0].checked) { //in cleaning
       status = document.forms[0].E01STSCAMI.value;
    } else { //out cleaning
       status = document.forms[0].E01STSCAMO.value;
    }
    if (status == "1") {
           document.forms[0].E01PROCESO.value = "2";
           document.forms[0].Submit.value = "Abrir";
    } else {
       	   document.forms[0].E01PROCESO.value = "1";
           document.forms[0].Submit.value = "Cerrar";
    }
  }
  
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

<H3 align="center">Cierre/Reapertura Procesos de Camara<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cleaning_ctrl.jsp, EDD0933"></H3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.cleaning.JSEDD0933" onsubmit="return(confirmAct())">
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="200">
  <INPUT TYPE=HIDDEN NAME="E01PROCESO" value="">  
  <table  class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          
          <tr id="trdark"> 
            <th align=center><div>Tipo  Camara</div>
            </th>
            <th align=center><div>Estado Actual</div>
            </th>
            <th align=center><div>Fecha</div>
            </th>
            <th align=center><div>Hora</div>
            </th>
            <th align=center><div>Usuario</div>
            </th>
          </tr>
          <tr id="trclear"> 
            <td align=center>
              <INPUT type="radio" name="E01TIPOCA" value="1" <% if (!cleaningCNTRL.getE01TIPOCA().equals("2")) out.print("checked");%> onClick="checkProc()">Entrante
            </td>
            <td align=center>
              <INPUT type="hidden" name="E01STSCAMI" value="<%= cleaningCNTRL.getE01STSCAMI().trim()%>">                     
              <INPUT type="text" id="txtlabel" value="<% if (cleaningCNTRL.getE01STSCAMI().equals("1")) out.print("CERRADA"); else out.print("ABIERTA");%>">         
            </td>
            <td align=center>
              <INPUT type="hidden" name="E01STSFECI" value="<%= cleaningCNTRL.getE01STSFECI().trim()%>">                     
              <%= Util.formatDate(cleaningCNTRL.getE01STSFECI())%>         
            </td>
            <td align=center>
              <INPUT type="hidden" name="E01STSTIMI" value="<%= cleaningCNTRL.getE01STSTIMI().trim()%>">                     
              <%= Util.formatTime(cleaningCNTRL.getE01STSTIMI())%>         
            </td>
            <td align=center>
              <%= cleaningCNTRL.getE01STSUSRI().trim()%>                     
            </td>
          </tr>
          <tr id="trclear"> 
            <td align=center>
              <INPUT type="radio" name="E01TIPOCA" value="2" <% if (cleaningCNTRL.getE01TIPOCA().equals("2")) out.print("checked");%> onClick="checkProc()">Saliente
            </td>
            <td align=center>
              <INPUT type="hidden" name="E01STSCAMO" value="<%= cleaningCNTRL.getE01STSCAMO().trim()%>">                     
              <INPUT type="text" id="txtlabel" value="<% if (cleaningCNTRL.getE01STSCAMO().equals("1")) out.print("CERRADA"); else out.print("ABIERTA");%>">         
            </td>
            <td align=center>
              <INPUT type="hidden" name="E01STSFECO" value="<%= cleaningCNTRL.getE01STSFECO().trim()%>">                     
              <%= Util.formatDate(cleaningCNTRL.getE01STSFECO())%>         
            </td>
            <td align=center>
              <INPUT type="hidden" name="E01STSTIMO" value="<%= cleaningCNTRL.getE01STSTIMO().trim()%>">                     
              <%= Util.formatTime(cleaningCNTRL.getE01STSTIMO())%>         
            </td>
            <td align=center>
              <%= cleaningCNTRL.getE01STSUSRO().trim()%>                     
            </td>
          </tr>
          <tr id="trdark"> 
            <td><div align="right">Motivo</div></td>
            <td colspan=4>
			<input size="50" type="text" name="E01MOTIVO" maxlength="50"  value="<%= cleaningCNTRL.getE01MOTIVO().trim()%>">
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
<SCRIPT language="javascript">
   checkProc();
</SCRIPT>   
</body>
</html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id= "msgPart" class= "datapro.eibs.beans.EDEN02001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<H3 align="center">Lista Propia<br>Copia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="our_denial_maint.jsp, EDEN020"></H3>
<hr size="4">
<form METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDEN020">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="<%= userPO.getAccOpt() %>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" >
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap width="75%"  > 
              <div align="left"> 
                <input type="text" name="E01DOAME" size="80"  value="<%= msgPart.getE01DOAME().trim()%>">
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  >
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right"><b>Dirección :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="E01DOAD1" size="70" maxlength="60"  value="<%= msgPart.getE01DOAD1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="E01DOAD2" size="70" maxlength="60"  value="<%= msgPart.getE01DOAD2().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="75%"  > 
              <div align="left"> 
                <input type="text" name="E01DOAD3" size="70" maxlength="60"  value="<%= msgPart.getE01DOAD3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Estado :</b></div>
            </td>
            <td nowrap width="75%"> 
              <div align="left"> 
                <input type="text" name="E01DOSTA" size="3" maxlength="2" value="<%= msgPart.getE01DOSTA().trim()%>" >
                <a href="javascript:GetCodeCNOFC('E01DOSTA','PV')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a>
              </div>
            </td>
		  </tr>
          <tr id="trclear">             
           <td nowrap width="25%"> 
              <div align="right"><b>País :</b></div>
            </td>
            <td nowrap width="75%"> 
              <div align="left"> 
                <input type="text" name="E01DOCCD" size="4" maxlength="3" value="<%= msgPart.getE01DOCCD().trim()%>" >
                <a href="javascript:GetCountryCodeOfac('E01DOCCD')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  >
              </div>
            </td>
           </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Fecha Inicial:</b></div>
            </td>
            <td nowrap width="75%"> 
              <div align="left"> 
                <input type="text" name="E01DODT11" size="3" maxlength="2" value="<%= msgPart.getE01DODT11().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E01DODT12" size="3" maxlength="2" value="<%= msgPart.getE01DODT12().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E01DODT13" size="3" maxlength="2" value="<%= msgPart.getE01DODT13().trim()%>" onKeypress="enterInteger()">
                <a href="javascript:DatePicker(document.forms[0].E01DODT11,document.forms[0].E01DODT12,document.forms[0].E01DODT13)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  >
                &nbsp;(AA/MM/DD)
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Primera Actualización :</b></div>
            </td>
            <td nowrap width="75%"> 
              <div align="left"> 
                <input type="text" name="E01DODT21" size="3" maxlength="2" value="<%= msgPart.getE01DODT21().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E01DODT22" size="3" maxlength="2" value="<%= msgPart.getE01DODT22().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E01DODT23" size="3" maxlength="2" value="<%= msgPart.getE01DODT23().trim()%>" onKeypress="enterInteger()">
                <a href="javascript:DatePicker(document.forms[0].E01DODT21,document.forms[0].E01DODT22,document.forms[0].E01DODT23)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                &nbsp;(AA/MM/DD)
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Segunda Actualización :</b></div>
            </td>
            <td nowrap width="75%"> 
              <div align="left"> 
                <input type="text" name="E01DODT31" size="3" maxlength="2" value="<%= msgPart.getE01DODT31().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E01DODT32" size="3" maxlength="2" value="<%= msgPart.getE01DODT32().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E01DODT33" size="3" maxlength="2" value="<%= msgPart.getE01DODT33().trim()%>" onKeypress="enterInteger()">
                <a href="javascript:DatePicker(document.forms[0].E01DODT31,document.forms[0].E01DODT32,document.forms[0].E01DODT33)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                &nbsp;(AA/MM/DD)
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Tipo :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="E01DOCTY" size="6" maxlength="5" value="<%= msgPart.getE01DOCTY().trim()%>" >
                <a href="javascript:GetDenialCodeOfac('E01DOCTY')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
                <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  >
              </div>
            </td>
           </tr>    
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Fuente :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="E01DOFL2" size="9" maxlength="8" value="<%= msgPart.getE01DOFL2().trim()%>" >
                <input type="text" name="E01DOFL6" size="13" maxlength="12" value="<%= msgPart.getE01DOFL6().trim()%>" >
              </div>
            </td>
           </tr>                                                 
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Comentarios :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="E01DORMK" size="80" value="<%= msgPart.getE01DORMK().trim()%>" >
              </div>
            </td>
           </tr>                     
        </table>
      </td>
    </tr>
  </table>
   <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
  
</form>
</body>
</html>

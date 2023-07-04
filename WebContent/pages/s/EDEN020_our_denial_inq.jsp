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

<jsp:useBean id= "shrList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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

<H3 align="center">Consulta Lista Propia<br>Consulta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="our_denial_inq.jsp, EDEN020"></H3>
<hr size="4">
<form>
 <%
 	int row;
	try {
		row = Integer.parseInt(request.getParameter("ROW"));
	} catch(Exception e){
		row = 0;
	}
	shrList.setCurrentRow(row);
	EDEN02001Message msgHelp = (EDEN02001Message) shrList.getRecord();	
 %>
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
                <input type="text" name="E01DOAME" size="80" readonly value="<%= msgHelp.getE01DOAME().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right"><b>Dirección :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="E01DOAD1" size="70" maxlength="60" readonly value="<%= msgHelp.getE01DOAD1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="E01DOAD2" size="70" maxlength="60" readonly value="<%= msgHelp.getE01DOAD2().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="75%"  > 
              <div align="left"> 
                <input type="text" name="E01DOAD3" size="70" maxlength="60" readonly value="<%= msgHelp.getE01DOAD3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Estado :</b></div>
            </td>
            <td nowrap width="75%"> 
              <div align="left"> 
                <input type="text" name="E01DOSTA" size="3" maxlength="2" value="<%= msgHelp.getE01DOSTA().trim()%>" readonly>
              </div>
            </td>
		  </tr>
          <tr id="trclear">             
           <td nowrap width="25%"> 
              <div align="right"><b>País :</b></div>
            </td>
            <td nowrap width="75%"> 
              <div align="left"> 
                <input type="text" name="E01DOCCD" size="4" maxlength="3" value="<%= msgHelp.getE01DOCCD().trim()%>" readonly>
              </div>
            </td>
           </tr>
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Fecha Inicial :</b></div>
            </td>
            <td nowrap width="75%"> 
              <div align="left"> 
                <input type="text" name="E01DODT11" size="3" value="<%= msgHelp.getE01DODT11().trim()%>" readonly>
                <input type="text" name="E01DODT12" size="3" value="<%= msgHelp.getE01DODT12().trim()%>" readonly>
                <input type="text" name="E01DODT13" size="3" value="<%= msgHelp.getE01DODT13().trim()%>" readonly>
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
                <input type="text" name="E01DODT21" size="3" value="<%= msgHelp.getE01DODT21().trim()%>" readonly>
                <input type="text" name="E01DODT22" size="3" value="<%= msgHelp.getE01DODT22().trim()%>" readonly>
                <input type="text" name="E01DODT23" size="3" value="<%= msgHelp.getE01DODT23().trim()%>" readonly>
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
                <input type="text" name="E01DODT31" size="3" value="<%= msgHelp.getE01DODT31().trim()%>" readonly>
                <input type="text" name="E01DODT32" size="3" value="<%= msgHelp.getE01DODT32().trim()%>" readonly>
                <input type="text" name="E01DODT33" size="3" value="<%= msgHelp.getE01DODT33().trim()%>" readonly>
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
                <input type="text" name="E01DOCTY" size="6" maxlength="5" value="<%= msgHelp.getE01DOCTY().trim()%>" readonly>
              </div>
            </td>
           </tr>    
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Fuente :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="E01DOFL2" size="9" maxlength="8" value="<%= msgHelp.getE01DOFL2().trim()%>" readonly>
                <input type="text" name="E01DOFL6" size="13" maxlength="12" value="<%= msgHelp.getE01DOFL6().trim()%>" readonly>
              </div>
            </td>
           </tr>                                                 
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Comentario :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="E01DORMK" size="80" value="<%= msgHelp.getE01DORMK().trim()%>" readonly>
              </div>
            </td>
           </tr>                     
        </table>
      </td>
    </tr>
  </table>
  
</form>
</body>
</html>

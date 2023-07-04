<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Consulta OFAC</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id= "shrList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"   scope="session"/>

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

<%	// Added By Henry (12/06/2006) Valid Only for Bradesco Luxembourg, word OFAC replaced by "World Check"
if (currUser.getH01WK3().equals("4") ) {	
%>
<H3 align="center">Búsqueda de Cheques Globales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ofac_inq.jsp, EDEN000"></H3>
<% } else { %>
<H3 align="center">Oficina de Control de Activos Extranjeros (OFAC)<br>Consulta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ofac_inq.jsp, EDEN000"></H3>
<% } %>

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
	EDEN011DSMessage msgHelp = (EDEN011DSMessage) shrList.getRecord();	
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
                <input type="text" name="SWDNAM" size="80" readonly value="<%= msgHelp.getSWDNAM().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Identificación :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="SWDCOU" size="6" maxlength="6" value="<%= msgHelp.getSWDCOU().trim()%>" readonly>
              </div>
            </td>
           </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right"><b>Localidad :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="SWDAD1" size="70" maxlength="60" readonly value="<%= msgHelp.getSWDAD1().trim()%>">
              </div>
            </td>
          </tr>
			<TR>
				<TD nowrap width="25%" align="right"><B>Estado :</B></TD>
				<TD nowrap width="75%">
				<DIV align="left"><INPUT type="text" name="SWDSTA" size="3"
					maxlength="2" value="<%= msgHelp.getSWDSTA().trim()%>" readonly></DIV>
				</TD>
			</TR>
			<TR>
				<TD nowrap width="25%">
				<DIV align="right"><B>País :</B></DIV>
				</TD>
				<TD nowrap width="75%"><INPUT type="text" name="SWDCCD" size="4"
					maxlength="3" value="<%= msgHelp.getSWDCCD().trim()%>" readonly>
                <INPUT type="text" name="SWDCDS" size="35"
					maxlength="30" value="<%= msgHelp.getSWDCDS().trim()%>" readonly></TD>
			</TR>
			<TR>
				<TD nowrap width="25%" align="right">
				<DIV align="right"><B>Información Adicional :</B></DIV></TD>
				<TD nowrap width="75%"><INPUT type="text" name="SWDAD2" size="70"
					maxlength="60" readonly value="<%= msgHelp.getSWDAD2().trim()%>"></TD>
			</TR>
			<tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left">
              <INPUT type="text" name="SWDAD3" size="70" maxlength="60"
					readonly value="<%= msgHelp.getSWDAD3().trim()%>"></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="75%"  > 
              <div align="left">
              <INPUT type="text" name="SWDREM" size="80"
					value="<%= msgHelp.getSWDREM().trim()%>" readonly></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"><B>Fecha de Entrada :</B></div>
				</td>
            <td nowrap width="75%">
				<INPUT type="text" name="SWDDTE" size="10"
					value="<%= msgHelp.getSWDDTE().trim()%>" readonly>
            </td>
		  </tr>
          <tr id="trdark">             
           <td nowrap width="25%"><DIV align="right"><B></B></DIV>
            </td>
            <td nowrap width="75%"> 
              <div align="left"> 
                
              </div>
            </td>
           </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"><b></b></div>
            </td>
            <td nowrap width="75%"> 
              <div align="left">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
           <td nowrap width="25%"> 
              <div align="right"><b>Volumen/Página :</b></div>
            </td>
            <td nowrap width="75%"> 
              <div align="left"> 
                <input type="text" name="SWDVOL" size="4" maxlength="3" value="<%= msgHelp.getSWDVOL().trim()%>" readonly>/
                <input type="text" name="SWDPAG" size="8" maxlength="7" value="<%= msgHelp.getSWDPAG().trim()%>" readonly>
              </div>
            </td>
           </tr>      
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right"><b>Tipo :</b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left"> 
                <input type="text" name="SWDTYP" size="6" maxlength="5" value="<%= msgHelp.getSWDTYP().trim()%>" readonly>
                <input type="text" name="SWDTDS" size="35" value="<%= msgHelp.getSWDTDS().trim()%>" readonly>
              </div>
            </td>
           </tr>                     
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right"><b></b></div>
            </td>
            <td nowrap width="75%" > 
              <div align="left">
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

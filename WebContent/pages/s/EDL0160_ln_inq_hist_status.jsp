<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Historic Status
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "lnStatus" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
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
%>
initMenu();
</SCRIPT>


</head>

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%> 
<h3 align="center">Hist&oacute;rico del Estado del Pr&eacute;stamo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_inq_hist_status.jsp,EDL0160"> 
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="14%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="9%" > 
              <div align="left"> 
                <input type="text" name="E02CUN2" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="12%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap ><b> 
              <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
              </b></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="14%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="9%"> 
              <div align="left"> 
                <input type="text" name="E02ACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="12%"> 
              <div align="right"><b>Oficial :</b></div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02NA122" size="30" maxlength="30" readonly value="<%= userPO.getOfficer().trim()%>">
                </b> </div>
            </td>
            <td nowrap width="11%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<%
	if ( lnStatus.getNoResult() ) {
%> 

  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD> 
        <div align="center"> 
          
          <p>&nbsp;</p>
          <p><b>Este pr&eacute;stamo no posee Hist&oacute;rico de Estados</b></p>
        </div>
      </TD>
     </TR>
   </TABLE>
  <%   		
	}
	else {
%> 

  <h4>Hist&oacute;rico</h4>
  
 <TABLE  id="mainTable" class="tableinfo">
 <TR> 
   <TD NOWRAP valign="top">
        <TABLE id="headTable" width="100%">
          <TR id="trdark"> 
            <TH ALIGN=CENTER NOWRAP colspan=2>Estados</TH>
            <TH ALIGN=CENTER NOWRAP rowspan=2>Fecha</TH>
            <TH ALIGN=CENTER NOWRAP rowspan=2>Hora</TH>
            <TH ALIGN=CENTER NOWRAP rowspan=2>Usuario</TH>
            <TH ALIGN=CENTER NOWRAP rowspan=2>Oficial</TH>
          </TR>
          <TR id="trdark"> 
            <TH ALIGN=CENTER NOWRAP>Anterior</TH>
            <TH ALIGN=CENTER NOWRAP>Nuevo</TH>
          </TR>
        
    <%    
         lnStatus.initRow();
         String OldSts = "";
         String NewSts = "";
         while (lnStatus.getNextRow()) { 
            datapro.eibs.beans.EDL013503Message msgStatus = (datapro.eibs.beans.EDL013503Message) lnStatus.getRecord();                   
            if (msgStatus.getE03DLSOST().equals("1")) OldSts = "VIGENTE";
            else if (msgStatus.getE03DLSOST().equals("2")) OldSts = "VENCIDO";
            else if (msgStatus.getE03DLSOST().equals("3")) OldSts = "CASTIGADO";
            else if (msgStatus.getE03DLSOST().equals("4")) OldSts = "CASTIGADO NO INFORMADO";
            if (msgStatus.getE03DLSNST().equals("1")) NewSts = "VIGENTE";
            else if (msgStatus.getE03DLSNST().equals("2")) NewSts = "VENCIDO";
            else if (msgStatus.getE03DLSNST().equals("3")) NewSts = "CASTIGADO";
            else if (msgStatus.getE03DLSNST().equals("4")) NewSts = "CASTIGADO NO INFORMADO";
     %>  
            <TR>               
              <td align=center ><%= OldSts %></td>            	
         	  <td align=center ><%= NewSts %></td>
         	  <td align=center nowrap><%=Util.formatDate(msgStatus.getE03DLSDT1(),msgStatus.getE03DLSDT2(),msgStatus.getE03DLSDT3()) %></td>
    		  <td align=center nowrap><%=Util.formatTime(msgStatus.getE03DLSTIM()) %></td>
    		  <td align=center nowrap><%=msgStatus.getE03DLSUSR() %></td>            	
         	  <td align=center nowrap><%=msgStatus.getE03DLSOFC() %> - <%=msgStatus.getE03DSCOFC() %></td>
    		</TR>    		 
      <%
              
         }        
    %> 
          </TABLE>
   </div>
   </TD>
  </TR>	
</TABLE>

  <%
  }
%> 
</FORM>

</BODY>
</HTML>

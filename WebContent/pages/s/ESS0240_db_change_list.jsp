<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Historial de Mantenimiento</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "dbList" 	class= "datapro.eibs.beans.JBObjList"  			scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  			scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  	scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
<%
	// show option menu
	if (userPO.getOption().equals("LN")) {
		if (userPO.getPurpose().equals("INQUIRY")) {
			if ( userPO.getHeader23().equals("G") ||  userPO.getHeader23().equals("V")){
%>
				builtNewMenu(ln_i_1_opt);
<%   
			} else  {
%>
				builtNewMenu(ln_i_2_opt);
<%   
			}
		} else { %>
 			builtNewMenu(ln_a_opt);
<% 		
		}
	} else if (userPO.getOption().equals("LN_PAYMENTCOND")) {
%>
		builtNewMenu(pm_cnd_opt);

<% 		
	} else if (userPO.getOption().equals("CD")) {
		if (userPO.getPurpose().equals("INQUIRY")) {
%>
			builtNewMenu(cd_i_opt);
<% 		
		} else {
%>
			builtNewMenu(cd_a_opt);

<% 		
	}
	} else if (userPO.getOption().equals("LC")) {
		if (userPO.getPurpose().equals("INQUIRY")) {
%>
			builtNewMenu(lc_i_opt);
<% 		
		} else {
%>
			builtNewMenu(lc_i_opt);


<% 		
	}
	} else if (userPO.getOption().equals("DV")) {
		if (userPO.getPurpose().equals("INQUIRY")) {
%>
			builtNewMenu(coll_i_opt);
<% 		
		} else {
%>
			builtNewMenu(coll_i_opt);

<% 		

		}
	} else if (userPO.getOption().equals("RT")) {
		if (userPO.getPurpose().equals("INQUIRY")) {
%>
			builtNewMenu(rt_i_opt);
<%
		} else {
%>		
			builtNewMenu(rt_a_opt);
<%
	 	}
	} else if (userPO.getOption().equals("SV")) {
		if (userPO.getPurpose().equals("INQUIRY")) {
%>
			builtNewMenu(sv_i_opt);
<%		
		} else {
%>
			builtNewMenu(sv_a_opt);		
<% 		
		}
	} else if (userPO.getOption().equals("CLIENT_C")) {
		if (userPO.getPurpose().equals("INQUIRY")) {
%>
			builtNewMenu(client_inq_corp_opt);
<%		
		} else {
%>
			builtNewMenu(client_ap_corp_opt);		
<% 		
		}
	} else if (userPO.getOption().equals("CLIENT_P")) {
		if (userPO.getPurpose().equals("INQUIRY")) {
%>
			builtNewMenu(client_inq_personal_opt);
<%		
		} else {			
%>
			builtNewMenu(client_ap_personal_opt);								
<% 		
		}
	} 
%>

</SCRIPT>
</head>

<BODY>
<%
	// show option menu
	if (userPO.getOption().equals("LN") 
		|| userPO.getOption().equals("LN_PAYMENTCOND")
		|| userPO.getOption().equals("CD")
		|| userPO.getOption().equals("DV")
		|| userPO.getOption().equals("LC")
		|| userPO.getOption().equals("RT")
		|| userPO.getOption().equals("SV")
		|| userPO.getOption().equals("CLIENT_C")
		|| userPO.getOption().equals("CLIENT_P")) {
	
 		out.println("<SCRIPT> initMenu();  </SCRIPT>");
	}
%>

<h3 align="center">Historial de Mantenimiento<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="db_change_list.jsp, ESS0240"></h3>
<hr size="4">
<FORM  >
<% if (userPO.getOption().equals("CLIENT_P") || userPO.getOption().equals("CLIENT_C")) {%>
	<table class="tableinfo">
	  <tr > 
	    <td> 
	      <table cellspacing="0" cellpadding="2" width="100%" class="tbhead" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">
	        <tr>
	             
	            <td nowrap width="10%" align="right"> Cliente: </td>
	          <td nowrap width="12%" align="left">
	      			<%= userPO.getHeader1()%>
	          </td>
	            <td nowrap width="6%" align="right">ID:  
	            </td>
	          <td nowrap width="14%" align="left">
	      			<%= userPO.getHeader2()%>
	          </td>
	            <td nowrap width="8%" align="right"> Nombre: </td>
	          <td nowrap width="50%"align="left">
	      			<%= userPO.getHeader3()%>
	          </td>
	        </tr>
	      </table>
	    </td>
	  </tr>
	</table>
<%} else {%>
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
            <td nowrap colspan="3"> 
              <div align="left"> 
                <input type="text" name="E02NA12" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
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
              <div align="right">Producto :</div>
            </td>
            <td nowrap width="33%"> 
              <div align="left"><b> 
                <input type="text" name="E02PRO2" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
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
<%}%>  
  <p>
  <%if ( dbList.getNoResult() ) {%>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su criterio de búsqueda</b></p>
          <p>&nbsp;</p>
        </div>
	  </TD>
	</TR>
    </TABLE>
	<%} else {%> 
  <p> 
  <br>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="30%"> 
              <div align="center">Descripción Campo</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Antes</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Después</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Usuario</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Fecha</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Hora</div>
            </th>
          </tr>
          <%
          dbList.initRow();
          boolean firstTime = true;
          String chk = "";
          while (dbList.getNextRow()) {
                
                datapro.eibs.beans.ESS024002Message msgList = (datapro.eibs.beans.ESS024002Message) dbList.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=LEFT width=\"20%\"><%= msgList.getE02FLDDSC() %></td>
            <td NOWRAP  align=CENTER   width=\"20%\"><%= msgList.getE02VALBEF() %></td>
            <td NOWRAP  align=CENTER width=\"20%\"><%= msgList.getE02VALAFT() %></td>
            <td NOWRAP  align=LEFT  width=\"20%\"><%= msgList.getE02USERID() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= Util.formatDate(msgList.getE02DATER1(), msgList.getE02DATER2(), msgList.getE02DATER3()) %></td>
            <td NOWRAP  align=RIGHT  width=\"10%\"><%= Util.formatTime(msgList.getE02VALTIM()) %></td>
          </tr>
          <%}%>
        </table>
  </table>
     
<%}%>
</form>
</body>
</html>

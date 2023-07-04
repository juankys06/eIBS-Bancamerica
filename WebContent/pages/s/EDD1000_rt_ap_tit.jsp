<html>
<head>
<title>Titulares</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "rtTit" class= "datapro.eibs.beans.ESD000006Message"  scope="session" />
<jsp:useBean id="cnofcList" class="datapro.eibs.beans.JBObjList" scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
	if(userPO.getOption().equals("RT")){
%>
	builtNewMenu(rt_a_opt);
<%
	}
	else if(userPO.getOption().equals("SV")){
%>
	builtNewMenu(sv_a_opt);
<%
	} 
%>

</SCRIPT>

</head>


<body bgcolor="#FFFFFF">


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors();");
     out.println("</SCRIPT>");
 }
   out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>

<h3 align="center">Informaci&oacute;n de Titulares<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_ap_tit.jsp,EDD1000"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="18">
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
                <input type="text" readonly name="E04DEACUN" size="9" maxlength="9"  value="<%= userPO.getHeader2().trim() %>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" readonly name="E04CUSNA1" size="45" maxlength="45" value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E06ACC" size="12" maxlength="12" value="<%= rtTit.getE06ACC().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" >
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left">
                <input type="text" readonly name="E04DEAPRO" size="5" maxlength="4"value="<%= userPO.getHeader1().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Titulares</h4>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="center">N&uacute;mero</div>
            </td>
            <td nowrap> 
              <div align="center">Primero y Segundo Apellido</div>
            </td>
            <td nowrap> 
              <div align="center">Identificaci&oacute;n</div>
            </td>
            <td nowrap> 
              <div align="center">Tipo Relaci&oacute;n</div>
            </td>
            <td nowrap> 
              <div align="center">% Relaci&oacute;n</div>
            </td>
          </tr>
          <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
          <tr id="trclear"> 
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E06CU<%= name %>" size="9" maxlength="9" value="<%= rtTit.getField("E06CU"+name).getString().trim()%>" 
				readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E06MA<%= name %>" size="40" maxlength="45" value="<%= rtTit.getField("E06MA"+name).getString().trim()%>" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
	          	  <%if(!(rtTit.getField("H06SCR").equals("07"))){%> 
	              <input type="text" name="E06NM<%= name %>" maxlength="30" size="31" value="<%= rtTit.getField("E06NM"+name).getString().trim()%>" readonly>
	          	  <%} else {%>
	              <input type="text" name="E06ID<%= name %>" maxlength="15" size="15" value="<%= rtTit.getField("E06ID"+name).getString().trim()%>" readonly>
	          	  <%}%>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E06TY<%= name %>" size="20"  maxlength="15" 
				  value="<%	cnofcList.initRow();
							while(cnofcList.getNextRow()){ 
	 							datapro.eibs.beans.EWD0002DSMessage cnofcDetail = (datapro.eibs.beans.EWD0002DSMessage)cnofcList.getRecord(); 
     							if (cnofcDetail.getEWDCOD().trim().equals(rtTit.getField("E06TY"+name).getString().trim()) &&
									!cnofcDetail.getEWDCOD().trim().equals("")) {
									out.print(cnofcDetail.getEWDDSC().trim());
									break;
								}
							}
						%>" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E06PR<%= name %>" size="6" maxlength="5" value="<%= rtTit.getField("E06PR"+name).getString().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <%
    		}
    		%> 
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>

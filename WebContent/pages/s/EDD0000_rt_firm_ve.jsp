<html>
<head>
<title>Firmantes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "rtFirm" class= "datapro.eibs.beans.ESD000004Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

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
%>

</SCRIPT>

</head>

<body bgcolor="#FFFFFF">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 
%>

<h3 align="center">Firmantes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_firm_ve.jsp, EDD0000"></h3>
<hr size="4">
<FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="20">  
  <INPUT TYPE=HIDDEN NAME="COUNTRY" VALUE="VE">  
<p>
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
                <input type="text" name="E02CUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E02NA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E04CUN" size="12" maxlength="12" value="<%= rtFirm.getE04CUN().trim()%>" readonly>
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
  </table>
  <div id="dataDiv" align="center" style="height:351; overflow-y:scroll; width:90%; padding-left:50; padding-right:10" class="scbarcolor">
<%
  rtFirm.setH04SCR("03");
  int bene_amount = 4;
  String name;
  for ( int i=5; i<=bene_amount+3; i++ ) {
    if ( i == 10 ) {
      name = "A"; 
    }
    else {
      name = i + "";
    }
    
    out.println("<table class=\"tbenter\" border=\"0\" width=\"100%\">");
    out.println("<tr>");
    
    switch ( i ) {
        case 5 : 
%> 
<td align="left"><h4>Quinto</h4></td>
<%          
           break;
        case 6 : 
%> 
<td align="left"><h4>Sexto</h4></td>
<%          
           break;
        case 7 : 
%> 
<td align="left"><h4>Séptimo</h4></td>
<%          
           break;
     }
  out.println("<td width=\"30%\" align=\"right\">");
  out.println("<h4>");
  out.print("<A href=\"javascript:go(5)\">5</A>,<A href=\"javascript:go(6)\">6</A>,<A href=\"javascript:go(7)\">7</A>");
   out.println("</h4>"); 
  out.println("</td>");   
  out.println("</tr>");
  out.println("</table>"); 
%>

  <table id="mainTable<%= name %>" class="tableinfo">
    <tr bgcolor="#FFFFFF" bordercolor="#FFFFFF"> 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" name="TB_rtFirmFICIARY_CORP" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">
        <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Número de Cliente : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E<%= name %>4RCN" maxlength="12" size="12" value="<%= rtFirm.getField("E" + name + "4RCN").getString().trim()%>">
			  <a href="javascript:GetCustomerDetails('E<%= name %>4RCN','E<%= name %>4MA1','E<%= name %>4NME','','','E<%= name %>4MA2','E<%= name %>4MA3','','E<%= name %>4CTY','E<%= name %>4STE','E<%= name %>4CTR','E<%= name %>4POB','E<%= name %>4ZPC','','','','','E<%= name %>4HPN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
			  <input type="hidden" name="E<%= name %>4CTY" value="">
            </td>
          </tr>

	     <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Nombre Legal : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E<%= name %>4MA1" maxlength="45" size="46" value="<%= rtFirm.getField("E" + name + "4MA1").getString().trim()%>">
            </td>
          </tr>

          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Direcci&oacute;n : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4MA2" maxlength="35" size="36" value="<%= rtFirm.getField("E" + name + "4MA2").getString().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right"></div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4MA3" maxlength="35" size="36" value="<%= rtFirm.getField("E" + name + "4MA3").getString().trim()%>">
            </td>
          </tr>
          <%if(currUser.getE01INT().equals("18")){%> 
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Ciudad : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4TID" maxlength="5" size="4" value="<%= rtFirm.getField("E" + name + "4TID").getString().trim()%>">
			  <a href="javascript:GetCodeCNOFC('E<%= name %>4TID','84')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>	
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Comuna : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4PID" maxlength="4" size="5" value="<%= rtFirm.getField("E" + name + "4PID").getString().trim()%>">
              <a href="javascript:GetCodeCNOFC('E<%= name %>4PID','80')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>	
            </td>
          </tr>
          <%} else if(currUser.getE01INT().equals("06")){%> 
         
         <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Departamento :</div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4TID" maxlength="4" size="5" value="<%= rtFirm.getField("E" + name + "4TID").getString().trim()%>" >
               <a href="javascript:GetCodeCNOFC('E<%= name %>4TID','GD')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>
            </td>
          </tr>
         <tr> 
            <td width="13%" nowrap> 
              <div align="right">Municipio :</div>
            </td>
           <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4BNI" maxlength="6" size="7" value="<%= rtFirm.getField("E" + name + "4BNI").getString().trim()%>" >
              <a href="javascript:GetCodeCNOFC('E<%= name %>4BNI','GM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a> 	
            </td>
          </tr>
          <%} else {%>
		  <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Ciudad : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4CTY" maxlength="30" size="31" value="<%= rtFirm.getField("E" + name + "4CTY").getString().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Estado : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4STE" maxlength="4" size="5" value="<%= rtFirm.getField("E" + name + "4STE").getString().trim()%>">
              <a href="javascript:GetCodeCNOFC('E<%= name %>4STE','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>	
            </td>
          </tr>
		<%}%>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Pa&iacute;s : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4CTR" maxlength="20" size="21" value="<%= rtFirm.getField("E" + name + "4CTR").getString().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Apartado Postal : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4POB" maxlength="10" size="11" value="<%= rtFirm.getField("E" + name + "4POB").getString().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">C&oacute;digo Postal : </div>
            </td>
            <td width="23%" nowrap>
              <input type="text" name="E<%= name %>4ZPC" maxlength="15" size="16" value="<%= rtFirm.getField("E" + name + "4ZPC").getString().trim()%>">
            </td>
          </tr>

           	   <%-- System.out.println("H04SCR="+rtFirm.getH04SCR()); %> 
	   <% System.out.println("index="+name); %> 
		<%	
		 if( rtFirm.getH04SCR().equals("07")){%> 	
		 <tr>
		 	<td colspan="2">
			<jsp:include page="ESD0080_address_template_basic_banesco_panama.jsp" flush="true">
				<jsp:param name="suffix" value='<%="E"+name+"4"%>' />
				<jsp:param name="title" value="Dirección" />
				<jsp:param name="messageName" value="rtFirm" />
				<jsp:param name="readOnly" value="false" />
				<jsp:param name="basic" value="false" />
			</jsp:include>
			<td>
		 </tr>
		<%} else if( rtFirm.getH04SCR().equals("03")) {%> 

           <jsp:include page="ESD0080_address_template_generic_venezuela.jsp"  flush="true">
	          <jsp:param name="index" value="<%=name%>" />
	          <jsp:param name="title" value="Direccion" />
	          <jsp:param name="messageName" value="rtFirm" />
              <jsp:param name="addressType" value="S" />
              <jsp:param name="readOnly"  value="false" />
           </jsp:include>
           
         <%} else { %>

           <jsp:include page="ESD0080_address2_template_basic_generic.jsp"  flush="true">
	          <jsp:param name="index" value="<%=name%>" />
	          <jsp:param name="title" value="Direccion" />
	          <jsp:param name="messageName" value="rtFirm" />
              <jsp:param name="addressType" value="S" />
              <jsp:param name="readOnly"  value="false" />
           </jsp:include>

		<%} --%>   
           
          <%if(!(currUser.getE01INT().equals("06"))){%> 
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Identificaci&oacute;n : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4NME" maxlength="30" size="31" value="<%= rtFirm.getField("E" + name + "4NME").getString().trim()%>">
            </td>
          </tr>
          <%}%>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Tel&eacute;fono : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4HPN" maxlength="11" size="12" value="<%= rtFirm.getField("E" + name + "4HPN").getString().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Tipo de Firma: </div>
            </td>
            <td width="23%" nowrap> 
              <select name="E<%= name %>4FL1">
                <option value=" " <% if (!(rtFirm.getField("E" + name + "4FL1").getString().equals("1") || rtFirm.getField("E" + name + "4FL1").getString().equals("2") || rtFirm.getField("E" + name + "4FL1").getString().equals("3"))) out.print("selected"); %>></option>
                <option value="1" <% if (rtFirm.getField("E" + name + "4FL1").getString().equals("1")) out.print("selected"); %>>Firma Indistinta</option>
                <option value="2" <% if (rtFirm.getField("E" + name + "4FL1").getString().equals("2")) out.print("selected"); %>>Firma Mancomunada</option>
				<option value="3" <% if (rtFirm.getField("E" + name + "4FL1").getString().equals("3")) out.print("selected"); %>>Firma Individual</option>
				</select>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Categoría de Firma : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4FL3" maxlength="1" size="2" value="<%= rtFirm.getField("E" + name + "4FL3").getString().trim()%>">
              <a href="javascript:GetCodeCNOFC('E<%= name %>4FL3','FI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>	
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td width="13%" > 
              <div align="right">Monto L&iacute;mite: </div>
            </td>
            <td width="23%" nowrap > 
              <input type="text" name="E<%= name %>4AM1" value="<%= rtFirm.getField("E" + name + "4AM1").getString().trim()%>" size="15" maxlength="15">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Comentarios: </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="D<%= name %>4CIT" size="35" maxlength="35" value="<%= rtFirm.getField("D" + name + "4CIT").getString().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
<%
  }
%>
</div>
<SCRIPT Language="Javascript">
   dataDiv.style.height=  mainTable5.offsetTop + mainTable5.offsetHeight +"";
</SCRIPT>
  
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
</form>
</body>
</html>

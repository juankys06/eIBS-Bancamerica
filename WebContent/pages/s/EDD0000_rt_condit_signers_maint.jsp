<html>
<head>
<title>Condiciones Especiales Firmantes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "signersList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "rtCond" class= "datapro.eibs.beans.EDD570001Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
<% 
 boolean newSigner = true;
 int row = 0;
 String opt = request.getParameter("OPTION").trim();
 String ccy = request.getParameter("CCY").trim();
 
 if ( error.getERRNUM().equals("0")  ) {
 
	 if (opt.equals("1")) { //new
	     String pos = "";
	     rtCond = new datapro.eibs.beans.EDD570001Message();
	     pos = (signersList.getNoResult()) ? "01" : "" + (signersList.getLastRec() + 2);
     	 if (pos.length() == 1) pos = "0" + pos;
     	 rtCond.setE01SQN(pos);
     	 rtCond.setE01ACC(userPO.getIdentifier().trim());
     	 rtCond.setH01WK1("N");
     	 rtCond.setE01FL1("1");
     	 rtCond.setE01CCY(ccy);
	 	 
	 } else {
	 
	    try {		  	
		  	row = Integer.parseInt(request.getParameter("ROW")); 
		  	signersList.setCurrentRow(row);
	        rtCond = (datapro.eibs.beans.EDD570001Message ) signersList.getRecord(); 
	        newSigner = false;
	     	rtCond.setE01FL1(opt);
		 } 
		 catch (Exception e) {
				row = 0;	    
		 }
	 }
 	//datapro.eibs.beans.EDD570001Message rtCond = new datapro.eibs.beans.EDD570001Message();

 } else {
     error.setERRNUM("0");
     out.println("       showErrors()");
 }
 
%>

</SCRIPT>

</head>

<body>
<h3 align="center">Condiciones Especiales de Firmantes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_condit_signers_maint.jsp, EDD0000"></h3>
<hr size="4">
<FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5700" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="20">  
  <INPUT TYPE=HIDDEN NAME="OPTION" VALUE="<%= opt %>">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  <INPUT TYPE=HIDDEN NAME="E01ACC" VALUE="<%= rtCond.getE01ACC() %>">
  <INPUT TYPE=HIDDEN NAME="E01SQN" VALUE="<%= rtCond.getE01SQN() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUN" VALUE="<%= rtCond.getE01CUN() %>">
  <INPUT TYPE=HIDDEN NAME="H01WK1" VALUE="<%= rtCond.getH01WK1() %>">
  <INPUT TYPE=HIDDEN NAME="E01FL1" VALUE="<%= rtCond.getE01FL1() %>">
  <INPUT TYPE=HIDDEN NAME="action" VALUE="<%= rtCond.getE01FL1() %>">
  <INPUT TYPE=HIDDEN NAME="E01CCY" VALUE="<%= rtCond.getE01CCY() %>">
  <INPUT TYPE=HIDDEN NAME="E01FDM" VALUE="<%= rtCond.getE01FDM() %>">
  <INPUT TYPE=HIDDEN NAME="E01FDD" VALUE="<%= rtCond.getE01FDD() %>">
  <INPUT TYPE=HIDDEN NAME="E01FDY" VALUE="<%= rtCond.getE01FDY() %>">
  <INPUT TYPE=HIDDEN NAME="E01TDM" VALUE="<%= rtCond.getE01TDM() %>">
  <INPUT TYPE=HIDDEN NAME="E01TDD" VALUE="<%= rtCond.getE01TDD() %>">
  <INPUT TYPE=HIDDEN NAME="E01TDY" VALUE="<%= rtCond.getE01TDY() %>">
  <INPUT TYPE=HIDDEN NAME="E01BRN" VALUE="<%= rtCond.getE01BRN() %>">
<%--
  <INPUT TYPE=HIDDEN NAME="E01STS" VALUE="<%= rtCond.getE01STS() %>">
--%>
  <INPUT TYPE=HIDDEN NAME="E01STS" VALUE="0">
<%--
  <INPUT TYPE=HIDDEN NAME="E01DOC" VALUE="<%= rtCond.getE01DOC() %>">
--%>
  <INPUT TYPE=HIDDEN NAME="E01DOC" VALUE="0">
  
  <table id="mainTable" class="tableinfo">
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Tipo de Firma: </div>
            </td>
            <td width="23%" nowrap> 
              <select name="E01TSG">
                <option value=" " <% if (!(rtCond.getE01TSG().equals("1") || rtCond.getE01TSG().equals("2") || rtCond.getE01TSG().equals("3"))) out.print("selected"); %>></option>
                <option value="1" <% if (rtCond.getE01TSG().equals("1")) out.print("selected"); %>>Firma Individual</option>
                <option value="2" <% if (rtCond.getE01TSG().equals("2")) out.print("selected"); %>>Firma Mancomunada</option>
				<option value="3" <% if (rtCond.getE01TSG().equals("3")) out.print("selected"); %>>Firma Indistinta</option>
			  </select>
            </td>
          </tr>         
    	<tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Número de Cliente : </div>
            </td>
            <td nowrap> 
            <% if (opt.equals("1")||opt.equals("2")) { //new %>
               <input type="text" name="E01SG1" maxlength="12" size="12" value="<%= rtCond.getE01SG1()%>">
			   <a href="javascript:GetCustomerDetails('E01SG1','E01NM1','','','','','','','','','','','','','','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>
            <% } else {%>
               <input type="text" name="E01SG1" maxlength="12" size="12" value="<%= rtCond.getE01SG1()%>" readonly>
            <% }%>
              <input type="text" name="E01NM1" maxlength="45" size="46" value="<%= rtCond.getE01NM1()%>" readonly>
            </td>
          </tr>

          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Categoría de Firma : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E01CS1" maxlength="1" size="2" value="<%= rtCond.getE01CS1()%>">
              <a href="javascript:GetCodeCNOFC('E01CS1','FI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>	
            </td>
          </tr>
    	<tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Número de Cliente : </div>
            </td>
            <td nowrap> 
            <% if (opt.equals("1")||opt.equals("2")) { //new %>
               <input type="text" name="E01SG2" maxlength="12" size="12" value="<%= rtCond.getE01SG2()%>">
			   <a href="javascript:GetCustomerDetails('E01SG2','E01NM2','','','','','','','','','','','','','','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>
            <% } else {%>
               <input type="text" name="E01SG2" maxlength="12" size="12" value="<%= rtCond.getE01SG2()%>" readonly>
            <% }%>
              <input type="text" name="E01NM2" maxlength="45" size="46" value="<%= rtCond.getE01NM2()%>" readonly>
            </td>
          </tr>

          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Categoría de Firma : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E01CS2" maxlength="1" size="2" value="<%= rtCond.getE01CS2()%>">
              <a href="javascript:GetCodeCNOFC('E01CS2','FI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>	
            </td>
          </tr>
    	<tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Número de Cliente : </div>
            </td>
            <td nowrap> 
            <% if (opt.equals("1")||opt.equals("2")) { //new %>
               <input type="text" name="E01SG3" maxlength="12" size="12" value="<%= rtCond.getE01SG3()%>">
			   <a href="javascript:GetCustomerDetails('E01SG3','E01NM3','','','','','','','','','','','','','','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>
            <% } else {%>
               <input type="text" name="E01SG3" maxlength="12" size="12" value="<%= rtCond.getE01SG3()%>" readonly>
            <% }%>
              <input type="text" name="E01NM3" maxlength="45" size="46" value="<%= rtCond.getE01NM3()%>" readonly>
            </td>
          </tr>

          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Categoría de Firma : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E01CS3" maxlength="1" size="2" value="<%= rtCond.getE01CS3()%>">
              <a href="javascript:GetCodeCNOFC('E01CS3','FI')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></a>	
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" > 
              <div align="right">Monto L&iacute;mite: </div>
            </td>
            <td width="23%" nowrap > 
              <input type="text" name="E01AMN" value="<%= rtCond.getE01AMN()%>" size="15" maxlength="15">
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Comentarios: </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E01OBS" size="35" maxlength="35" value="<%= rtCond.getE01OBS()%>">
            </td>
          </tr>
  </table>     
  <br>

  <table class="tbenter" width=100% align=center>
      <td class=TDBKG width="20%"> 
	    <div align="center"><input id="EIBSBTN" type=submit name="Submit" value="Enviar"></div>
      </td>
      <td class=TDBKG width="20%"> 
        <div align="center"><a id="EIBSBTN" href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
  </table>

</form>
</body>
</html>

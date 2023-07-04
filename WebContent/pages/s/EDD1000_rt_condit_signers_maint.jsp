<html>
<head>
<title>Condiciones Especiales Firmantes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "signersList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "rtFirm" class= "datapro.eibs.beans.EDD570001Message"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
<% 
 boolean newSigner = true;
 int row = 0;
 
	if ( error.getERRNUM().equals("0")  ) {
 
	    try {		  	
		  	row = Integer.parseInt(request.getParameter("ROW")); 
		  	signersList.setCurrentRow(row);
        	rtFirm = (datapro.eibs.beans.EDD570001Message ) signersList.getRecord(); 
		 } 
		 catch (Exception e) {
				row = 0;	    
		 }
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
  <INPUT TYPE=HIDDEN NAME="OPTION" VALUE="">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  <INPUT TYPE=HIDDEN NAME="E01ACC" VALUE="<%= rtFirm.getE01ACC() %>">
  <INPUT TYPE=HIDDEN NAME="E01SQN" VALUE="<%= rtFirm.getE01SQN() %>">
  <INPUT TYPE=HIDDEN NAME="E01CUN" VALUE="<%= rtFirm.getE01CUN() %>">
  <INPUT TYPE=HIDDEN NAME="H01WK1" VALUE="<%= rtFirm.getH01WK1() %>">
  <INPUT TYPE=HIDDEN NAME="E01FL1" VALUE="<%= rtFirm.getE01FL1() %>">
  <INPUT TYPE=HIDDEN NAME="action" VALUE="<%= rtFirm.getE01FL1() %>">
  <INPUT TYPE=HIDDEN NAME="E01CCY" VALUE="<%= rtFirm.getE01CCY() %>">
  <INPUT TYPE=HIDDEN NAME="E01FDM" VALUE="<%= rtFirm.getE01FDM() %>">
  <INPUT TYPE=HIDDEN NAME="E01FDD" VALUE="<%= rtFirm.getE01FDD() %>">
  <INPUT TYPE=HIDDEN NAME="E01FDY" VALUE="<%= rtFirm.getE01FDY() %>">
  <INPUT TYPE=HIDDEN NAME="E01TDM" VALUE="<%= rtFirm.getE01TDM() %>">
  <INPUT TYPE=HIDDEN NAME="E01TDD" VALUE="<%= rtFirm.getE01TDD() %>">
  <INPUT TYPE=HIDDEN NAME="E01TDY" VALUE="<%= rtFirm.getE01TDY() %>">
  <INPUT TYPE=HIDDEN NAME="E01BRN" VALUE="<%= rtFirm.getE01BRN() %>">
  <INPUT TYPE=HIDDEN NAME="E01STS" VALUE="<%= rtFirm.getE01STS() %>">
  <INPUT TYPE=HIDDEN NAME="E01DOC" VALUE="<%= rtFirm.getE01DOC() %>">
  
  <table id="mainTable" class="tableinfo">
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Tipo de Firma: </div>
            </td>
            <td width="23%" nowrap> 
                <% if (rtFirm.getE01TSG().equals("1")) out.print("Firma Individual"); %>
                <% if (rtFirm.getE01TSG().equals("2")) out.print("Firma Mancomunada"); %>
				<% if (rtFirm.getE01TSG().equals("3")) out.print("Firma Indistinta"); %>
            </td>
          </tr>         
    	<tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Número de Cliente : </div>
            </td>
            <td nowrap> 
               <input type="text" name="E01SG1" maxlength="12" size="12" value="<%= rtFirm.getE01SG1()%>" readonly>
            </td>
          </tr>

	     <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Nombre Legal : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E01NM1" maxlength="45" size="46" value="<%= rtFirm.getE01NM1()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Categoría de Firma : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E01CS1" maxlength="1" size="2" value="<%= rtFirm.getE01CS1()%>" readonly>
            </td>
          </tr>
    	<tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Número de Cliente : </div>
            </td>
            <td nowrap> 
               <input type="text" name="E01SG2" maxlength="12" size="12" value="<%= rtFirm.getE01SG2()%>" readonly>
            </td>
          </tr>

	     <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Nombre Legal : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E01NM2" maxlength="45" size="46" value="<%= rtFirm.getE01NM2()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Categoría de Firma : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E01CS2" maxlength="1" size="2" value="<%= rtFirm.getE01CS2()%>" readonly>
            </td>
          </tr>
    	<tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Número de Cliente : </div>
            </td>
            <td nowrap> 
               <input type="text" name="E01SG3" maxlength="12" size="12" value="<%= rtFirm.getE01SG3()%>" readonly>
            </td>
          </tr>

	     <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Nombre Legal : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E01NM3" maxlength="45" size="46" value="<%= rtFirm.getE01NM3()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Categoría de Firma : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E01CS3" maxlength="1" size="2" value="<%= rtFirm.getE01CS3()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" > 
              <div align="right">Monto L&iacute;mite: </div>
            </td>
            <td width="23%" nowrap > 
              <input type="text" name="E01AMN" value="<%= rtFirm.getE01AMN()%>" size="15" maxlength="15" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Comentarios: </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E01OBS" size="35" maxlength="35" value="<%= rtFirm.getE01OBS()%>" readonly>
            </td>
          </tr>
  </table>     
  <br>

  <table class="tbenter" width=100% align=center>
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
  </table>

</form>
</body>
</html>

<html>
<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.ESD059001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 


<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">Mantenimiento del Maestro de Agencias <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="branch_maintenance.jsp, ESD0590"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0590" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Banco :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01BRNBNK" size="3" maxlength="2"  value="<%= brnDetails.getE01BRNBNK().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Agencia :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2"> 
                <input type="text" name="E01BRNNUM" size="4"  maxlength="3" value="<%= brnDetails.getE01BRNNUM().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Informaci&oacute;n de la Agencia</h4>  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap width="40%"> 
              <input type="text" name="E01BRNNME" maxlength="35" size="36" value="<%= brnDetails.getE01BRNNME().trim()%>" >
            </td>
            <td nowrap width="16%"> 
              <div align="right">Notario :</div>
            </td>
            <td nowrap width="28%"> 
              <input type="text" name="E01BRNF012" maxlength="3" size="4" value="<%= brnDetails.getE01BRNF012().trim()%>" >
            <A
					href="javascript:GetTypeBroker('E01BRNF012','')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="help"
					 border="0"></A></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Direcci&oacute;n :</div>
            </td>
            <td nowrap height="23" colspan="3"> 
              <input type="text" name="E01BRNADR" size="36" maxlength="35" value="<%= brnDetails.getE01BRNADR().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Ciudad :</div>
            </td>
            <td nowrap height="19" colspan="3"> 
              <input type="text" name="E01BRNF011" maxlength="4" size="5" value="<%= brnDetails.getE01BRNF011().trim()%>" >
              <input type="text" name="E01BRNCIT" maxlength="35" size="36" value="<%= brnDetails.getE01BRNCIT().trim()%>" >
              <a href="javascript:GetCodeDescCNOFC('E01BRNF011','E01BRNCIT','84')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Telef&oacute;no :</div>
            </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01BRNPHN" size="12" maxlength="11" value="<%= brnDetails.getE01BRNPHN().trim()%>" >
            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Oficina de Impresi&oacute;n : </div>
            </td>
            <td nowrap width="28%" height="19"> 
              <input type="text" name="E01BRNDID" size="5" maxlength="3" value="<%= brnDetails.getE01BRNDID().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Oficina Matriz :</div>
            </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01BRNCLB" size="5" maxlength="4" value="<%= brnDetails.getE01BRNCLB().trim()%>">
			  <%-- <input type="text" name="E01US1NME" size="36" maxlength="35" value="<%= brnDetails.getE01US1NME().trim()%>"> --%>
	          <a href="javascript:GetBranch('E01BRNCLB',document.forms[0].E01BRNBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
              <%-- <a href="javascript:GetCodeDescCNOFC('E01BRNUS1','E01US1NME','80')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a>  --%>

            </td>
            <td nowrap width="16%" height="19"> 
              <div align="right">Tipo de Oficina :</div>
            </td>
            <td nowrap width="28%" height="19"> 
               	<SELECT name="E01BRNFL1">
					<OPTION value="L"
						<%if (brnDetails.getE01BRNFL1().equals("L")) { out.print("selected"); }%>>Local
					</OPTION>
					<OPTION value="I"
						<%if (brnDetails.getE01BRNFL1().equals("I")) { out.print("selected"); }%>>Internacional
					</OPTION>
					<OPTION value="M"
						<%if (brnDetails.getE01BRNFL1().equals("M")) { out.print("selected"); }%>>Casa Matriz
					</OPTION>
					<OPTION value="O"
						<%if (brnDetails.getE01BRNFL1().equals("O")) { out.print("selected"); }%>>Otra
					</OPTION>
				</SELECT>
			</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Centro de Canje :</div>
            </td>
            <td nowrap width="40%" height="19"> 
              <input type="text" name="E01BRNUS2" size="5" maxlength="4" value="<%= brnDetails.getE01BRNUS2().trim()%>">
              <%-- <input type="text" name="E01US2NME" size="36" maxlength="35" value="<%= brnDetails.getE01US2NME().trim()%>"> --%>
              <a href="javascript:GetBranch('E01BRNUS2',document.forms[0].E01BRNBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
            </td>
            <%if(currUser.getE01INT().equals("04")){%>
            <td nowrap width="16%" height="19"> 
              <div align="right">Iva Preferencial :</div>
            </td>
            <% } else {%>
            <td nowrap width="16%" height="19"> 
              <div align="right">Canje Autom&aacute;tico :</div>
            </td>
            <%}%>
            <td nowrap width="28%" height="19"> 
              <input type="radio" name="E01BRNFL2" value="Y"  <%if (brnDetails.getE01BRNFL2().equals("Y")) out.print("checked"); %>>
              Si 
              <input type="radio" name="E01BRNFL2" value="N"  <%if (brnDetails.getE01BRNFL2().equals("N")) out.print("checked"); %>>
              No</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Regi&oacute;n :</div>
            </td>
            <td nowrap height="19" colspan="3"> 
              <input type="text" name="E01BRNRGN" size="5" maxlength="4" value="<%= brnDetails.getE01BRNRGN().trim()%>">
              <input type="text" name="E01RGNNME" size="36" maxlength="35" value="<%= brnDetails.getE01RGNNME().trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E01BRNRGN','E01RGNNME','07')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a> 
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="16%" height="19"> 
              <div align="right">Localidad :</div>
            </td>
            <td nowrap height="19" colspan="3"> 
              <input type="text" name="E01BRNSBR" size="5" maxlength="4" value="<%= brnDetails.getE01BRNSBR().trim()%>">
              <input type="text" name="E01SBRNME" size="36" maxlength="35" readonly value="<%= brnDetails.getE01SBRNME().trim()%>">
              <a href="javascript:GetCodeDescCNOFC('E01BRNSBR','E01SBRNME','27')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></a>               
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
  </form>
</body>
</html>

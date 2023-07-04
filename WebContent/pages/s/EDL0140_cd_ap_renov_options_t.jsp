<html>
<head>
<title>Instrucciones de Renovaci&oacute;n de Certificados de Dep&oacute;sito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="cdRenov" class="datapro.eibs.beans.EDL013008Message"   scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "pmnt" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

 
   <% 
    if (userPO.getOption().equals("TREASURY") && userPO.getHeader16().equals("N")) {  
   %>
		builtNewMenu(cdt_a_act_opt);
  <%      
   }
    else if (userPO.getOption().equals("TREASURY") && (!userPO.getHeader16().equals("N"))) {
   %>
		builtNewMenu(cdt_a_opt);
   <%
   }
    
   %>
	

</SCRIPT>


<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
	 

     out.println("<SCRIPT> initMenu();  </SCRIPT>");
%> 
<h3 align="center">Instrucciones de Renovaci&oacute;n de Certificados de Dep&oacute;sito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cd_ap_renov_options,EDL0140"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="34">
  <INPUT TYPE=HIDDEN NAME="E08DEABNK" VALUE="<%= cdRenov.getE08DEABNK().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E08DEACCY" VALUE="<%= cdRenov.getE08DEACCY().trim()%>">
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E08DEACUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E08CUSNA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E08DEAACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>" readonly>
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
                <input type="text" name="E08DEAPRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>&nbsp;</h4>
  <table class="tableinfo">
    <tr> 
      <td>
        <table cellpadding=2 cellspacing=0 align=center width=100% >
          <tr id="trdark"> 
            <td width=41 height="46"><b><b> </b><b><b><b> 
              <input type="radio" disabled  name="E08DEAROC" value="D" 
			  <%if (cdRenov.getE08DEAROC().equals("D")) out.print("checked");%>>
              </b></b></b>D</b></td>
            <td height="46" colspan="2" > Al vencimiento cancelar el Dep&oacute;sito 
              m&aacute;s los Intereses acreditando a la Cuenta Contable n&uacute;mero 
              <input type="text" readonly name="E08DDDGLN" size="16" maxlength="16" value="<%= cdRenov.getE08DDDGLN().trim()%>">
              ,emitiendo un 
              <input type="hidden" name="E08DDDFLV" value="">
              <input type="radio" disabled  name="CE08DDDFLV" value="C" onClick="document.forms[0].E08DDDFLV.value='C'"
			  <%if (cdRenov.getE08DDDFLV().equals("C")) out.print("checked");%>>
              Cheque de Gerencia, o generando una 
              <input type="radio" disabled   name="CE08DDDFLV" value="" onClick="document.forms[0].E08DDDFLV.value='V'"
			  <%if (cdRenov.getE08DDDFLV().equals("V")) out.print("checked");%>>
              Transferencia de Fondo Via <input type="text" readonly name="E08DDDPVI" value="<% if (cdRenov.getE08DDDPVI().equals("F")) out.print("Fed");
							else if (cdRenov.getE08DDDPVI().equals("Q")) out.print("Emision Cupons");
                            else if (cdRenov.getE08DDDPVI().equals("T")) out.print("Telex");
                            else if (cdRenov.getE08DDDPVI().equals("1")) out.print("Swift Format MT-100");
                            else if (cdRenov.getE08DDDPVI().equals("2")) out.print("Swift Format MT-200");
                            else if (cdRenov.getE08DDDPVI().equals("3")) out.print("Swift Format MT-202");
							else out.print("");%>">
              . </td>
          </tr>
          <tr id="trclear"> 
            <td width=41></td>
            <td colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width=41 height="36"><b><b> </b><b> 
              <input type="radio" disabled  name="E08DEAROC" value="E" 
			  <%if (cdRenov.getE08DEAROC().equals("E")) out.print("checked");%>>
              </b>E</b></td>
            <td height="36" colspan="2" >Al vencimiento cancelar el Dep&oacute;sito 
              m&aacute;s los Intereses acreditandolos a la Cuenta n&uacute;mero 
              <input type="text" readonly name="E08EEEACC" size="12" maxlength="12" value="<%= cdRenov.getE08EEEACC().trim()%>">
              (Corriente, Ahorro, etc.) . </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>

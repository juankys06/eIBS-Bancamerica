<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Tarjeta Adicional</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "msgCard" class= "datapro.eibs.beans.ECC006001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT Language="Javascript">

<%if (userPO.getHeader2().equals("D")){%>
	builtNewMenu(dc_i_opt);
<%
 } else {
%>
	builtNewMenu(cc_i_opt);
<%
 }
%>

function setShowStatus(){
	if(document.forms[0].E01CCRIST.value == "A" || document.forms[0].E01CCRIST.value == "C" ) {
		causalinput.style.visibility = "hidden";
		causallabel.style.visibility = "hidden";
	} else {
		causalinput.style.visibility = "visible";
		causallabel.style.visibility = "visible";
	}
}

</SCRIPT>

</head>
<body>

<SCRIPT> initMenu(); </SCRIPT>

<h3 align="center">Tarjeta de <% if (userPO.getHeader2().equals("D")) out.print("Débito"); else out.print("Crédito"); %>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_inq_aditional_card.jsp, ECC0060"> 
</h3>
<hr size="4">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
%> 
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060I" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="E01CCRTCL" VALUE="S">
  <INPUT TYPE=HIDDEN NAME="E01TARTYP" VALUE="<%= userPO.getHeader2().trim()%>">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente Primario: </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01PRICUN" size="9" maxlength="9" readonly value="<%= userPO.getCusNum().trim()%>">
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre Primario: </b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01PRINA1" size="45" maxlength="45" readonly value="<%= userPO.getCusName().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Identificaci&oacute;n: </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01CCRCID" size="12" maxlength="9" readonly value="<%=msgCard.getE01CCRCID().trim()%>">
			  </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta: </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E01CCRCRA" size="12" maxlength="9" value="<%= userPO.getAccNum().trim() %>">
              </div>
            </td>
          </tr>    
          <tr id="trdark"> 

			<td>
			</td>
			<td>
			</td>

            <%-- 
            <td nowrap width="16%"> 
              <div align="right"><b>Tipo Tarjeta:</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CCRPRIM" size="21" maxlength="20" value="Secundaria" readonly>
              </div>
            </td>
			--%>
            <td nowrap width="16%"> 
              <div align="right"><b>Numero Tarjeta: </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E01CCRNUM" size="21" maxlength="20" value="<%= msgCard.getE01CCRNUM().trim()%>">
                </b> </div>
            </td>
          </tr>            
        </table>
      </td>
    </tr>
  </table>
  <h4> Informacion Basica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
           <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Estado :</div>
            </td>
            <td nowrap width="23%"> 
              <select name="E01CCRIST" disabled>
                <option value="I" <% if(msgCard.getE01CCRIST().equals("I")) out.print("selected");%>>Inactiva</option>
                <option value="A" <% if(msgCard.getE01CCRIST().equals("A")) out.print("selected");%>>Activa</option>        
              </select>
            </td>
            <td nowrap width="25%"> 
              <div id="causallabel" style="visibility:hidden;" align="right">Causal de Estado :</div>
            </td>
            <td nowrap width="27%"> 
           		<div id="causalinput" style="visibility:hidden;">
				<input type="text" name="E01CCRDSC" size="17" maxlength="15" value="<%= msgCard.getE01CCRDSC().trim()%>" readonly>             
				</div>
 			</td>
          </tr>     
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha Expiracion:</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01CCREXD" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCREXD().trim()%>" readonly>
			  <%-- 
              <input type="text" name="E01CCREXM" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCREXM().trim()%>" readonly>
              --%>
              <input type="text" name="E01CCREXY" readonly size="6" maxlength="4" value="<%= msgCard.getE01CCREXY().trim()%>" readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha de Emision :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCRISD" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCRISD().trim()%>" readonly>
              <input type="text" name="E01CCRISM" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCRISM().trim()%>" readonly>
              <input type="text" name="E01CCRISY" readonly size="6" maxlength="4" value="<%= msgCard.getE01CCRISY().trim()%>" readonly>
  			</td>
          </tr>                                                                                           
        </table>
      </td>
    </tr>
  </table>  
  <br>
  </form>
  
<SCRIPT language="JavaScript">     
	setShowStatus();     
</SCRIPT>
  
</body>
</html>


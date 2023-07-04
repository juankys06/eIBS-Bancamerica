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

<script language="JavaScript">
/*
function setShowStatus(){
	if(document.forms[0].E01CCRIST.value == "A") {
		document.forms[0].E01CCRSTS.value = "";
		causalinput.style.visibility = "hidden";
		causallabel.style.visibility = "hidden";
	} else {
		causalinput.style.visibility = "visible";
		causallabel.style.visibility = "visible";
	}
}
*/
</script>

</head>
<body>
<h3 align="center">Tarjeta de <% if (userPO.getHeader2().equals("D")) out.print("Débito"); else out.print("Crédito"); %>
<BR>Mantenimiento
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cc_aditional_card.jsp,ECC0060"> 
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
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="E01CCRTCL" VALUE="S">
  <INPUT TYPE=HIDDEN NAME="E01TARTYP" VALUE="<%= userPO.getHeader2().trim()%>">

  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><B>Tipo de Tarjeta :</B> </div>
            </td>
            <td nowrap width="16%"> 
              	<input type="text" name="E01TARTY2" size="25" maxlength="14" value="<% if (userPO.getHeader2().equals("D")) out.print("Tarjeta de Debito"); else out.print("Tarjeta de Credito"); %>" readonly>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><!-- <b>Tipo de Tarjeta :</b>  --></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
              	<!-- 
                <input type="text" name="E01CCRPRIM" size="21" maxlength="20" value="Secundaria" readonly>
                 -->
              </div>
            </td>
          </tr>    
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
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E01CCRCRA" size="12" maxlength="9" value="<%= userPO.getAccNum().trim() %>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Numero de Tarjeta: </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E01CCRNUM" size="21" maxlength="20" value="<%= msgCard.getE01CCRNUM().trim()%>">
                </b> </div>
            </td>
          </tr>    
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente Secundario: </b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="E01SECCUN" size="9" maxlength="9" value="<%= msgCard.getE01SECCUN().trim()%>">
                <a href="javascript:GetCustomerDescId('E01SECCUN','E01SECNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
			  </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre Secundario: </b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E01SECNA1" size="45" maxlength="45" readonly value="<%= msgCard.getE01SECNA1().trim()%>">
              </div>
            </td>
          </tr>            
        </table>
      </td>
    </tr>
  </table>
  <h4> Informacion Basica</h4> 
  <table class="tableinfo" align="center" >
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0" align="center" >     
          <tr id="trdark"> 
            <TD nowrap width="25%"> 
              <DIV align="right">Fecha de Expiracion:</DIV>
            </TD><TD nowrap width="23%"> 
              <!-- <INPUT type="text" name="E01CCREXD" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCREXD().trim()%>" onkeypress="enterInteger()">  -->
              <INPUT type="text" name="E01CCREXM" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCREXM().trim()%>" onkeypress="enterInteger()">
              <INPUT type="text" name="E01CCREXY" readonly size="6" maxlength="4" value="<%= msgCard.getE01CCREXY().trim()%>" onkeypress="enterInteger()">
               
            </TD>
            
            <td nowrap width="25%"> 
              <div align="right">Fecha de Emision :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01CCRISD" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCRISD().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCRISM" readonly size="3" maxlength="2" value="<%= msgCard.getE01CCRISM().trim()%>" onkeypress="enterInteger()">
              <input type="text" name="E01CCRISY" readonly size="6" maxlength="4" value="<%= msgCard.getE01CCRISY().trim()%>" onkeypress="enterInteger()">
               
  			</td>
          </tr>                                                                                           
        </table>
      </td>
    </tr>
  </table>  
  
<SCRIPT language="JavaScript">     
	//setShowStatus();     
</SCRIPT>
  
  <br>
  <div align="center"> 
	   <input id="EIBSBTN" type=submit name="Submit" value="Aceptar">
  </div>
  </form>
</body>
</html>

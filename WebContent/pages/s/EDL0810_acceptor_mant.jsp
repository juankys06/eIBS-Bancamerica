<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Aceptante</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script>

function eliminar(){
	a = confirm("¿Desea eliminar el Aceptante?");
	if (a) {
		document.forms[0].ACCION.value = "E";
		document.forms[0].submit();;
	}
}

function enviar(){
	document.forms[0].ACCION.value = "A";
	document.forms[0].submit();;
}

function GetCodeAcpt(name, flag)
{
	page= prefix +language + "EWD0170_dft_hlp_acpt.jsp?codeflag=" + flag;
	fieldName=name;
	CenterWindow(page,400,300,1);
}
</script>

<jsp:useBean id="dftBasic" class="datapro.eibs.beans.EDL081001Message"  scope="session" />
<jsp:useBean id="dftAcceptor" class="datapro.eibs.beans.EDL081003Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body nowrap>
<% 
 String strBlank = "";
 String strGrp = "";
 String strReadonly = "";
 try {
       strGrp = request.getParameter("ACTION");
       if (strGrp == null) strGrp = "";
 } catch (Exception e) {
       strGrp = "";
 }

 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
  
  String DEAIPD, DEAPPD;
  String E01FLGDED, E01FLGREF,E01FLGPAY,E01FLGCOL,E01DEACLF;
  boolean isDEAIPDNum = true;
  boolean isDEAPPDNum = true;
  
  
%> 
<h3 align="center">Mantenimiento de Aceptantes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="dft_acceptor_mant.jsp,EDL0810"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0810" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3300">
  <INPUT TYPE=HIDDEN NAME="GRP" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="ACCION" VALUE="">
  <INPUT TYPE=HIDDEN NAME="GENDOC" VALUE="1">
  <h4>Información Básica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Identificación :</div>
            </td>
            <td nowrap colspan="3"> 
              <INPUT type="text" name="ideacc" size="17" maxlength="15" value="<%= dftAcceptor.getE03DLDIDA() %>" <%=strReadonly %> readonly>
            </td>
	          <td nowrap width="25%">
	            <div align="right">Codigo de Direccion :</div>
	          </td>
	          <td nowrap>
	              <INPUT type="text" name="E03DLDNDA" size="3" maxlength="2" value="<%= dftAcceptor.getE03DLDNDA() %>" <%=strReadonly %> readonly>
	           </td>          	           
          </tr>

          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Nombre :</div>
            </td>
            <Td nowrap colspan="5"> 
              <INPUT type="text" name="E03DLDMA1" size="45" maxlength="45" value="<%= dftAcceptor.getE03DLDMA1() %>" <%=strReadonly %>>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Dirección :</div>
            </td>
            <TD nowrap colspan="5"> 
              <INPUT type="text" name="E03DLDMA2" size="35" maxlength="35" value="<%= dftAcceptor.getE03DLDMA2() %>" <%=strReadonly %>>
             </TD>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right"></div>
            </td>
            <TD nowrap colspan="5"> 
              <INPUT type="text" name="E03DLDMA3" size="35" maxlength="35" value="<%= dftAcceptor.getE03DLDMA3() %>" <%=strReadonly %>>
            </TD>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Teléfono :</div>
            </td>
            <td nowrap colspan="5"> 
              <INPUT type="text" name="E03DLDHPN" size="17" maxlength="17" value="<%= dftAcceptor.getE03DLDHPN() %>" <%=strReadonly %>>
            </td>
            
          </tr>
          <tr id="trclear">             
            <td nowrap> 
              <div align="right">Comuna :</div>
            </td>
            <td nowrap colspan=5> 
              <input type="text" name="E03DLDCOM" maxlength="4" size="5" value="<%= dftAcceptor.getE03DLDCOM()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC1('E03DLDCOM','D03DLDCOM','E03DLDCIU','D03DLDCIU','80')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>	
              <input type="text" readonly name="D03DLDCOM" maxlength="30" size="35" value="<%= dftAcceptor.getD03DLDCOM()%>">
            </td>
            
          </tr>
         <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Ciudad :</div>
            </td>
            <td nowrap colspan=5> 
              <input type="text" name="E03DLDCIU" maxlength="4" size="5" value="<%= dftAcceptor.getE03DLDCIU()%>" readonly>
              <a href="javascript:GetCodeDescCNOFC('E03DLDCIU','D03DLDCIU','84')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>	
              <input type="text" readonly name="D03DLDCIU" maxlength="30" size="35" value="<%= dftAcceptor.getD03DLDCIU()%>">
            </td>
            
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Código Postal:</div>
            </td>
            <td nowrap width="25%" > 
              <INPUT type="text" name="E03DLDZPC" size="15" maxlength="15" value="<%= dftAcceptor.getE03DLDZPC() %>" <%=strReadonly %>>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Casilla Postal:</div>
            </td>
            <td nowrap width="25%" > 
              <INPUT type="text" name="E03DLDPOB" size="10" maxlength="10" value="<%= dftAcceptor.getE03DLDPOB() %>" <%=strReadonly %>>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Ruta de Cobro:</div>
            </td>
            <td nowrap colspan="5">
            <INPUT type="text" name="E03DLDREW" size="7" maxlength="6" value="<%= dftAcceptor.getE03DLDREW() %>" <%=strReadonly %>>
            <a href="javascript:GetCodeCNOFC('E03DLDREW','42')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a> 
	        </td>
            
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Plaza de Pago:</div>
            </td>
            <td nowrap width="23%" colspan="3"> 
              <INPUT type="text" name="E03DLDPYW" size="7" maxlength="6" value="<%= dftAcceptor.getE03DLDPYW()  %>" <%=strReadonly %>>
              <a href="javascript:GetCodeAcpt('E03DLDPYW','02')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a>
           </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Agente Cobrador:</div>
            </td>
            <td nowrap colspan="5" > 
              <INPUT type="text" name="E03DLDREM" size="7" maxlength="6" value="<%= dftAcceptor.getE03DLDREM() %>" <%=strReadonly %>>
              <a href="javascript:GetCodeAcpt('E03DLDREM','01')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"></a>
            </td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
  <p  align="center"> 
	<INPUT id="EIBSBTN" type="button" name ="btenviar" value="Enviar"   onClick="enviar()">
	<INPUT id="EIBSBTN" type="button" name ="btenviar" value="Eliminar"   onClick="eliminar()">
  </p>
  </form>
</body>
</html>

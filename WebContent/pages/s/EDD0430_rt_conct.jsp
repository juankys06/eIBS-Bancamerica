
<%@ page import = "datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Concentración de Cuentas
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "cifList" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  function goAction(op) {

     document.forms[0].opt.value = op;
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "EWDRAC") 
      	{
        		ok = true;
        		break;
      	}
      }
	 if ( ok || (op == 3) ) {
          document.forms[0].submit();
     }
     else {
			alert("Seleccione una cuenta antes de realizar esta operación");	   
     }

  }
</SCRIPT>
</HEAD>


<BODY>

<h3 align="center">Concentraci&oacute;n de Cuentas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="rt_conct.jsp,EDD0430"> 
  </h3>
  <hr size="4">

<form method="post"  action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0430">
  <input TYPE=HIDDEN name="SCREEN" value="500">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
<%
	if ( cifList.getNoResult() ) {
%>
 		
<TABLE class="tbenter" height=60% width="100%">
  <TR>      
    <TD> 
      <div align="center"> 
        <p>No existen resultados que correspondan a su criterio de busqueda, por 
          favor escoja entre las siguientes opciones<font size="3"><b> </b></font> 
        </p>
        <table class="tbenter" width="100%">
          <div align="center"></div>
          <tr> 
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction(3)"><b>Nueva</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </TD> 
          </tr>
        </table>
      </div>
    </TD>
  </TR>
</TABLE>
<%
	}
	else {
%> 
<BR> 
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right"><b>Cliente:</b></div>
            </td>
            <td nowrap > 
              <input type="text" name="E02ACMCUN" size="9" maxlength="9" value="<%= userPO.getHeader2().trim()%>" readonly>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E02CUSNA1" size="45" maxlength="45" value="<%= userPO.getHeader3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap >               
              <div align="right"><b>Cuenta:</b></div>
            </td>
            <td nowrap > 
              <input type="text" name="E02ACMACC" size="12" maxlength="12" value="<%= userPO.getIdentifier().trim()%>">
            </td>
            <td nowrap> 
              <div align="right"><b>Producto:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E02ACMPRO" size="4" maxlength="4" value="<%= userPO.getHeader1().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

<TABLE class="tbenter">
 <TR>
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="javascript:goAction(2)"><b>Modificar</b></a></div>
      </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </TD> 
 </TR>
</TABLE>
<TABLE class="tableinfo">
    <TR id=trdark>
      <TH ALIGN=CENTER nowrap>&nbsp;</TH>
      <TH ALIGN=CENTER nowrap>Cuenta de Relaci&oacute;n</TH>
      <TH ALIGN=CENTER nowrap>Nombre</TH>
      <TH ALIGN=CENTER nowrap>Moneda</TH>
      <TH ALIGN=CENTER nowrap>Monto M&aacute;ximo</TH>
      <TH ALIGN=CENTER nowrap>Fecha de Vencimiento</TH>
    </TR>
    <%
                cifList.initRow();
                while (cifList.getNextRow()) {
                    if (cifList.getFlag().equals("")) {
                    		out.println(cifList.getRecord());
                    }
                }
              %> 
  </TABLE>

  <%
  }
%> 
</FORM>

</BODY>
</HTML>

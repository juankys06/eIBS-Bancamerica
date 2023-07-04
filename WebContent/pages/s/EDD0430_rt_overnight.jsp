<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Inversiones Nocturnas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="rtNight" class="datapro.eibs.beans.EDD043001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
  }
</SCRIPT>
</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>

<H3 align="center">Inversiones Nocturnas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_overnight,EDD0430"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0430" >
  <input type=HIDDEN name="SCREEN" value="2">
 <table class="tableinfo">
    <tr> 
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cliente:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E01ACMCUN" size="9" maxlength="9" value="<%= rtNight.getE01ACMCUN().trim()%>">
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= rtNight.getE01CUSNA1().trim()%>">
            </td>            
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cuenta:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E01ACMACC" size="12" maxlength="12" value="<%= rtNight.getE01ACMACC().trim()%>">
            </td>
            <td nowrap> 
              <div align="right"><b>Producto:</b></div>
            </td>
            <td nowrap> 
              <input type="text" name="E01ACMPRO" size="4" maxlength="4" value="<%= rtNight.getE01ACMPRO().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Tabla de Tasas de Inversi&oacute;n:</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01INVRTB" size="2" maxlength="1" value="<%= rtNight.getE01INVRTB().trim()%>">
                <a href="javascript:GetOvernightTable('E01INVRTB','<%= rtNight.getE01INVRBK().trim()%>')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="middle" border="0" ></a></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Sobretasa de Inversi&oacute;n:</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01INVOIS" size="9" maxlength="9" value="<%= rtNight.getE01INVOIS().trim()%>" onKeypress="enterSignDecimal(6)">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Cuenta Contable de Inversi&oacute;n:</div>
            </td>
            <td nowrap>              
              <INPUT type="text" name="E01INVRBK" size="2" maxlength="2" value="<%= rtNight.getE01INVRBK().trim()%>">
              <INPUT type="text" name="E01INVRBR" size="3" maxlength="3" value="<%= rtNight.getE01INVRBR().trim()%>" 
                <% if (!userPO.getPurpose().equals("APPROVAL_INQ")) {%>oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01INVRBK.value,'','','',''); return false;"<% } %>>
              <INPUT type="text" name="E01INVRCY" size="3" maxlength="3" value="<%= rtNight.getE01INVRCY().trim()%>" 
                <% if (!userPO.getPurpose().equals("APPROVAL_INQ")) {%>oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01INVRBK.value,'','','',''); return false;"<% } %>>              
              <input type="text" name="E01INVRGL" size="16" maxlength="16" value="<%= rtNight.getE01INVRGL().trim()%>" onKeypress="enterInteger()" 
                <% if (!userPO.getPurpose().equals("APPROVAL_INQ")) {%>oncontextmenu="showPopUp(ledgerHelp,this.name,document.forms[0].E01INVRBK.value,document.forms[0].E01INVRCY.value,'','',''); return false;"<% } %>>              
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap> 
              <div align="right">Cuenta de Detalle de Inversi&oacute;n:</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01INVRAC" size="16" maxlength="16" value="<%= rtNight.getE01INVRAC().trim()%>" onKeypress="enterInteger()"
				 <% if (!userPO.getPurpose().equals("APPROVAL_INQ")) {%>oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01INVRBK.value,'','','','RT'); return false;"<% } %>>
			  </div>	 
            </td>             
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Transferir en Multiplos de :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01INVMUL" size="13" maxlength="13" value="<%= rtNight.getE01INVMUL().trim()%>" onKeypress="enterInteger()">
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="45%"> 
              <div align="right">Balance M&iacute;nimo en Cuenta:</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01INVMIN" size="13" maxlength="13" value="<%= rtNight.getE01INVMIN().trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha de Inicio Relaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01INVSD1" size="2" maxlength="2" value="<%= rtNight.getE01INVSD1().trim()%>" onKeypress="enterInteger()">
              <input type="text" name="E01INVSD2" size="2" maxlength="2" value="<%= rtNight.getE01INVSD2().trim()%>" onKeypress="enterInteger()">
              <input type="text" name="E01INVSD3" size="2" maxlength="2" value="<%= rtNight.getE01INVSD3().trim()%>" onKeypress="enterInteger()">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Fecha Vencimiento Relaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01INVMD1" size="2" maxlength="2" value="<%= rtNight.getE01INVMD1().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E01INVMD2" size="2" maxlength="2" value="<%= rtNight.getE01INVMD2().trim()%>" onKeypress="enterInteger()">
                <input type="text" name="E01INVMD3" size="2" maxlength="2" value="<%= rtNight.getE01INVMD3().trim()%>" onKeypress="enterInteger()">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <% if (userPO.getPurpose().equals("APPROVAL_INQ")) {%>
 <SCRIPT Language="Javascript">
   var j=document.forms[0].elements.length;
    var felem=document.forms[0].elements;
    for(i=0;i< j;i++){
       if (felem[i].tagName!=="select"){
         if (felem[i].type=="text") felem[i].readOnly=true; else felem[i].disabled=true;
       } 
       else { felem[i].disabled=true;}
    }
    
</SCRIPT>
 
 <% } else { %> 
  
          <div align="center"> 
            <p><input id="EIBSBTN" type=submit name="Submit" value="Enviar"></p>
          </div>

 <% } %>  
  </form>
</body>
</html>

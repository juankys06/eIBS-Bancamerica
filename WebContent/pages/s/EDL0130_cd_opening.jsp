<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Apertura de Certificados</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,datapro.eibs.master.*,java.math.*" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id="cdNew" class="datapro.eibs.beans.EDL013001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="javascript">
 function CheckSubmit(act)
{
   document.forms[0].ACTION.value=act;
   document.forms[0].submit();
}
</SCRIPT>
</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
     
 boolean protect = JSEIBSProp.getProtectedBNKBRN();
%>
<SCRIPT LANGUAGE="javascript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   if (field.substring(0,8) == "E01OFFAC"){
     var index = field.substr(8,1);
     var concepto=document.getElementById("E01OFFOP"+index).value;
     if (concepto == "01")
       Client=document.getElementById("E01DEACUN").value;
     else
       Client="";
   }
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
</SCRIPT>
<h3 align="center">Apertura de <%=cdNew.getE01DEANR1() %> <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="cd_opening.jsp,EDL0130" width="32" height="32"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="F">
  <input type=HIDDEN name="E01DEAACD" value="<%= cdNew.getE01DEAACD().trim()%>"">
  <input type=HIDDEN name="E01DEAACC" value="<%= cdNew.getE01DEAACC().trim()%>"">
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
                <input type="text" name="E01DEACUN" size="9" maxlength="9" value="<%= cdNew.getE01DEACUN().trim()%>">
                <a href="javascript:GetCustomerDescId('E01DEACUN','E01CUSNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= cdNew.getE01CUSNA1().trim()%>">
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E01DEAACC2" size="15" maxlength="12" value="NUEVA CUENTA">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= cdNew.getE01DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E01DEAPRO" size="4" maxlength="4" value="<%= cdNew.getE01DEAPRO().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Datos B&aacute;sicos de la Operaci&oacute;n</h4> 
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Nombre del Certificado :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEANME" size="81" maxlength="80" value="<%= cdNew.getE01DEANME().trim()%>">                       
            </td>
            <td nowrap width="25%">    
            </td>
            <td nowrap width="27%">  
            </td>
          </tr>        
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAOD1" size="2" maxlength="2" value="<%= cdNew.getE01DEAOD1().trim()%>">
              <input type="text" name="E01DEAOD2" size="2" maxlength="2" value="<%= cdNew.getE01DEAOD2().trim()%>">
              <input type="text" name="E01DEAOD3" size="2" maxlength="2" value="<%= cdNew.getE01DEAOD3().trim()%>">
            </td>
            <td nowrap width="25%"> 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEATRM" size="4" maxlength="4" value="<%= cdNew.getE01DEATRM().trim()%>">
              <select name="E01DEATRC">
                <option value=" " <% if (!(cdNew.getE01DEATRC().equals("D") ||cdNew.getE01DEATRC().equals("M")||cdNew.getE01DEATRC().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(cdNew.getE01DEATRC().equals("D")) out.print("selected");%>>D&iacute;a(s)</option>
                <option value="M" <% if(cdNew.getE01DEATRC().equals("M")) out.print("selected");%>>Mes(es)</option>
                <option value="Y" <% if(cdNew.getE01DEATRC().equals("Y")) out.print("selected");%>>A&ntilde;o(s)</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAMD1" size="2" maxlength="2" value="<%= cdNew.getE01DEAMD1().trim()%>">
              <input type="text" name="E01DEAMD2" size="2" maxlength="2" value="<%= cdNew.getE01DEAMD2().trim()%>">
              <input type="text" name="E01DEAMD3" size="2" maxlength="2" value="<%= cdNew.getE01DEAMD3().trim()%>">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Tasa Inter&eacute;s/Spread :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEARTE" size="10" maxlength="9" value="<%= cdNew.getE01DEARTE().trim()%>" >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Tasa Flotante :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAFTB" size="2" maxlength="2" value="<%= cdNew.getE01DEAFTB().trim()%>">
              <a href="javascript:GetFloating('E01DEAFTB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" ></a> 
              <select name="E01DEAFTY">
                <option value=" " <% if (!(cdNew.getE01DEAFTY().equals("FP") ||cdNew.getE01DEAFTY().equals("FS"))) out.print("selected"); %>></option>
                <option value="FP" <% if (cdNew.getE01DEAFTY().equals("FP")) out.print("selected"); %>>FP</option>
                <option value="FS" <% if (cdNew.getE01DEAFTY().equals("FS")) out.print("selected"); %>>FS</option>
              </select>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa Variable :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01FLTRTE" size="9" maxlength="9" value="<%= cdNew.getE01FLTRTE().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Ciclo/Fecha Revis.Tasa :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEARRP" size="3" maxlength="3" value="<%= cdNew.getE01DEARRP().trim()%>" onblur="rightAlignCharNumber()">
              /
              <input type="text" name="E01DEARD1" size="2" maxlength="2" value="<%= cdNew.getE01DEARD1().trim()%>" onKeypress="enterDecimal()">
              <input type="text" name="E01DEARD2" size="2" maxlength="2" value="<%= cdNew.getE01DEARD2().trim()%>">
              <input type="text" name="E01DEARD3" size="2" maxlength="2" value="<%= cdNew.getE01DEARD3().trim()%>"> 
            </td>
            <td nowrap width="25%" > 
              <div align="right">N&uacute;mero Referencia :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAREF" size="12" maxlength="12" value="<%= cdNew.getE01DEAREF().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Monto :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAOAM" size="15" maxlength="15" value="<%= cdNew.getE01DEAOAM().trim()%>" onKeypress="enterDecimal()">
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" ></td>
            <td nowrap width="25%" > 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAEXR" size="11" maxlength="11" value="<%= cdNew.getE01DEAEXR().trim()%>" onKeypress="enterDecimal()">
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="25%" >
              <div align="right">Moneda :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" name="E01DEACCY2" size="4" maxlength="3" value="<%= cdNew.getE01DEACCY().trim()%>" readonly>
            </td>
            <td nowrap width="25%" >
              <div align="right">Centro de Costo : </div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01DEACCN" size="9" maxlength="8" value="<%= cdNew.getE01DEACCN().trim()%>">
              <a href="javascript:GetCostCenter('E01DEACCN','01')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Centros de Costo" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tabla de Tasa :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" name="E01DEARTB" size="1" maxlength="1" value="<%= cdNew.getE01DEARTB().trim()%>">
              <a href="javascript:GetRateTable('E01DEARTB')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            </td>
            <td nowrap > 
              <div align="right">Clase de Certificado :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E01DEACLF" size="2" maxlength="1" value="<%= cdNew.getE01DEACLF().trim()%>">
              <a href="javascript:GetCode('E01DEACLF','STATIC_cd_class.jsp')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a> 
            </td>
          </tr>
          <tr id="trclear">            
            <td nowrap width="25%" >
              <div align="right">Documento en Custodia :</div>
            </td>
            <td nowrap width="23%">
             <SELECT name="E01DEASOF">
                <OPTION value="N" <% if (!(cdNew.getE01DEASOF().equals("1") || cdNew.getE01DEASOF().equals("2"))) out.print("selected"); %>>No Custodia</OPTION>
                <OPTION value="1" <% if (cdNew.getE01DEASOF().equals("1")) out.print("selected"); %>>Electronica</OPTION>
                <OPTION value="2" <% if (cdNew.getE01DEASOF().equals("2")) out.print("selected"); %>>Impreso</OPTION>
              </SELECT>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Banco/Sucursal :</div>
            </td>
            <td nowrap width="23%" >
            <% if (!protect) {%>
              <input type="text" name="E01DEABNK" size="2" maxlength="2" value="<%= cdNew.getE01DEABNK().trim()%>" >
              <input type="text" name="E01DEABRN" size="2" maxlength="3" value="<%= cdNew.getE01DEABRN().trim()%>">
              <a href="javascript:GetBranch('E01DEABRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0"  ></a>
            <% } else { %>
              <input type="text" name="E01DEABNK" size="2" maxlength="2" value="<%= cdNew.getE01DEABNK().trim()%>" readonly>
              <input type="text" name="E01DEABRN" size="2" maxlength="3" value="<%= cdNew.getE01DEABRN().trim()%>" readonly>
            <% } %>
            </td>      
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Origen de Fondos</h4>
  <TABLE id="mainTable" class="tableinfo">
  <TR><TD>
  
   <table id="headTable" >
    <tr id="trdark"> 
      <td nowrap align="center" > Concepto</td>
      <td nowrap align="center" >Banco </td>
      <td nowrap align="center" >Sucursal</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >Referencia</td>
      <td nowrap align="center" >Monto</td>
    </tr>
    </table> 
      
    <div id="dataDiv" style="height:60; overflow-y :scroll; z-index:0" >
     <table id="dataTable" >
          <%
  				   int amount = 9;
 				   String name;
  					for ( int i=1; i<=amount; i++ ) {
   					  name = i + "";
   			%> 
          <tr id="trclear"> 
            <td nowrap > 
              <div align="center" nowrap> 
                <input type="text" name="E01OFFOP<%= name %>" id="E01OFFOP<%= name %>" value="<%= cdNew.getField("E01OFFOP"+name).getString().trim()%>" size="3" maxlength="3">
                <input type="hidden" name="E01OFFGL<%= name %>" value="<%= cdNew.getField("E01OFFGL"+name).getString().trim()%>">
                <input type="text" name="E01OFFCO<%= name %>" size="21" maxlength="20" readonly value="<%= cdNew.getField("E01OFFCO"+name).getString().trim()%>" 
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01DEABNK.value,'','E01OFFGL<%= name %>','E01OFFOP<%= name %>',document.forms[0].E01DEAACD.value); return false;">
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01OFFBK<%= name %>" size="2" maxlength="2" value="<%= cdNew.getField("E01OFFBK"+name).getString().trim()%>">
              </div>
            </td>
            <td nowrap > 
              <div align="left"> 
                <input type="text" name="E01OFFBR<%= name %>" size="3" maxlength="3" value="<%= cdNew.getField("E01OFFBR"+name).getString().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01DEABNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01OFFCY<%= name %>" size="3" maxlength="3" value="<%= cdNew.getField("E01OFFCY"+name).getString().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01DEABNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap > 
              <div align="center"> 
                <input type="text" name="E01OFFAC<%= name %>" id="E01OFFAC<%= name %>" size="12" maxlength="12"  value="<%= cdNew.getField("E01OFFAC"+name).getString().trim()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01DEABNK.value,'','','','RT'); return false;">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01OFFAM<%= name %>" size="23" maxlength="15"  value="<%= cdNew.getField("E01OFFAM"+name).getString().trim()%>" onKeypress="enterDecimal()">
              </div>
            </td>
          </tr>
          <%
    		}
    		%> 
    	  </table>
        </div>
        
     <table id="footTable" > 
          <tr id="trdark"> 
            <td nowrap  align="right"><b>Equivalente Moneda del Certificado :</b> 
            </td>
            <td nowrap align="center"><b><i><strong> 
                <input type="text" name="E01OFFEQV" size="23" maxlength="17" readonly value="<%= cdNew.getE01OFFEQV().trim()%>">
                </strong></i></b>
            </td>
          </tr>
        </table>
  
</TD>  
</TR>	
</TABLE>  
  
 <SCRIPT language="javascript">
    function tableresize() {
     adjustEquTables(headTable,dataTable,dataDiv,0,true);
   }
  tableresize();
  window.onresize=tableresize;
  </SCRIPT>
  <% if(error.getERWRNG().equals("Y")){%>
   <h4 style="text-align:center"><input type="checkbox" name="H01FLGWK2" value="A" <% if(cdNew.getH01FLGWK2().equals("A")){ out.print("checked");} %>>
      Aceptar con Errores</h4>
  <% } %>         
  <p align="center"> 
    <input id="EIBSBTN" type=button name="Submit"  onClick="CheckSubmit('F')" value="Enviar">
  </p>

  
  </form>
</body>
</html>

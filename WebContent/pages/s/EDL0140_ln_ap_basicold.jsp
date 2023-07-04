<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page import = "datapro.eibs.master.*" %>
<html>
<head>
<title>Mantenimiento de Prestamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id="apBasic" class="datapro.eibs.beans.EDL015001Message"  scope="session" />

<jsp:useBean id= "ded" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "tax" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "com" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "iva" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "ins" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "coll" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "pmnt" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "pmntPlus" class= "datapro.eibs.beans.JBListRec"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 

<SCRIPT Language="Javascript">

   //builtNewMenu(ln_i_2_opt); 
   builtNewMenu(ln_a_opt); 

  builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
  function getNewDeduct(acc){
   page = webapp + "/servlet/datapro.eibs.products.JSEDL0370?SCREEN=7&ACCNUM=" + acc;
   CenterWindow(page,600,500,2);
  }
  
  function getDeduct(acc,cod){
   var row;
   for(var i=1;i < TBDEDUCT.rows.length;i++){
     if (TBDEDUCT.rows[i].cells[0].innerText == cod) {
      row = i;
      break
     }
   }
   page = webapp + "/servlet/datapro.eibs.products.JSEDL0370?SCREEN=3&ACCNUM=" + acc + "&COD=" + cod + "&ROW="+ row;
   CenterWindow(page,600,500,2);
  }
  
 function UpdateFlag(val) {
  document.forms[0].E01RCLFLC.value = (val==true)?"X":"";
 }
 
 function setRecalculate() {
  document.forms[0].RECALC.checked = true;
  document.forms[0].RECALC.disabled = true;
  UpdateFlag(true);  
 }
</SCRIPT>

</head>
<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
//if (userPO.getPurpose().equals("MAINTENANCE")){
 out.println("<SCRIPT> initMenu(); </SCRIPT>");
 //}

  String DEAIPD, DEAPPD;
  String E01FLGDED, E01FLGREF,E01FLGPAY,E01FLGCOL,E01DEACLF;
  boolean isDEAIPDNum = true;
  boolean isDEAPPDNum = true;
  DEAIPD = apBasic.getE01DEAIPD().trim();
  DEAPPD = apBasic.getE01DEAPPD().trim();
  E01FLGDED = apBasic.getE01FLGDED().trim();
  E01FLGREF = apBasic.getE01FLGREF().trim();
  E01FLGPAY = apBasic.getE01FLGPAY().trim();
  E01FLGCOL = apBasic.getE01FLGCOL().trim();
  E01DEACLF = apBasic.getE01DEACLF().trim();
  
  try {
    int i = Integer.parseInt(DEAIPD);
  }
  catch (Exception e) {
    isDEAIPDNum = false;
  }
  try {
    int i = Integer.parseInt(DEAPPD);
  }
  catch (Exception e) {
    isDEAPPDNum = false;
  }

  String genFlag = "";
  try {
      genFlag = request.getParameter("generic").trim();
      if (genFlag == null) genFlag = "";
  } catch (Exception e) {
    genFlag = apBasic.getE01DEACLF().trim();
  }

  boolean protect = JSEIBSProp.getProtectedBNKBRN();
%> 
<h3 align="center"> <%= apBasic.getE01DSCPRO().trim()%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ln_ap_basic.jsp,EDL0140"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0150" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="502">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="F">
  <!--INPUT TYPE=HIDDEN NAME="E01DEABNK" value="<%= apBasic.getE01DEABNK().trim()%>"-->
  <INPUT TYPE=HIDDEN NAME="E01DEARD1" value="<%= apBasic.getE01DEARD1().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DEARD2" value="<%= apBasic.getE01DEARD2().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DEARD3" value="<%= apBasic.getE01DEARD3().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DLCNC1" value="<%= apBasic.getE01DLCNC1().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DLCVA1" value="<%= apBasic.getE01DLCVA1().trim()%>">
  <input type=HIDDEN name="E01DLCFP1" value="<%= apBasic.getE01DLCFP1().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01DLCTP1" value="<%= apBasic.getE01DLCTP1().trim()%>">
  <input type=HIDDEN name="E01DEATYP" value="<%= apBasic.getE01DEATYP().trim()%>">
  
  <INPUT TYPE=HIDDEN NAME="E01RCLFLC" VALUE="<%= apBasic.getE01RCLFLC().trim()%>">
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
                <input type="text" name="E01DEACUN" <% if (apBasic.getF01DEACUN().equals("Y")) out.print("id=\"txtchanged\""); %> size="9" maxlength="9" value="<%= apBasic.getE01DEACUN().trim()%>" readonly>
                <a href="javascript:GetCustomerDescId('E01DEACUN','E01CUSNA1','')"></a></div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"><font face="Arial"><font face="Arial"><font size="2">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= apBasic.getE01CUSNA1().trim()%>" readonly>
                </font></font></font></div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01DEAACC" size="12" maxlength="12"  readonly
					value="<% if(userPO.getPurpose().equals("MAINTENANCE")) {out.print(apBasic.getE01DEAACC().trim());} else { 
					if (apBasic.getE01DEAACC().trim().equals("999999999999")) out.print("NUEVA CUENTA"); else out.print(apBasic.getE01DEAACC().trim());} %>"
				>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="4" maxlength="3" value="<%= apBasic.getE01DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" readonly name="E01DEAPRO" size="4" maxlength="4" value="<%= apBasic.getE01DEAPRO().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Datos B&aacute;sicos de la Operaci&oacute;n</h4> 
<%if ( genFlag.equals("U") == false ) {%>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%">` 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAOD1" <% if (apBasic.getF01DEAOD1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAOD1().trim()%>" onChange="setRecalculate()" <% if (!(apBasic.getH01FLGMAS().equals("N") )) out.print("disabled"); %> readonly>
              <input type="text" name="E01DEAOD2" <% if (apBasic.getF01DEAOD2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAOD2().trim()%>" onChange="setRecalculate()" <% if (!(apBasic.getH01FLGMAS().equals("N") )) out.print("disabled"); %> readonly>
              <input type="text" name="E01DEAOD3" <% if (apBasic.getF01DEAOD3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAOD3().trim()%>" onChange="setRecalculate()" <% if (!(apBasic.getH01FLGMAS().equals("N") )) out.print("disabled"); %> readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEATRM" <% if (apBasic.getF01DEATRM().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="4" value="<%= apBasic.getE01DEATRM().trim()%>" onChange="setRecalculate()" <% if (!(apBasic.getH01FLGMAS().equals("N") )) out.print("disabled"); %> readonly>
              <select name="E01DEATRC" onChange="setRecalculate()" <% if (!(apBasic.getH01FLGMAS().equals("N") )) out.print("disabled"); %> disabled>
                            <OPTION value=" " <% if (!(apBasic.getE01DEATRC().equals("D") ||apBasic.getE01DEATRC().equals("M")||apBasic.getE01DEATRC().equals("Y"))) out.print("selected"); %>></OPTION>
                            <OPTION value="D" <% if(apBasic.getE01DEATRC().equals("D")) out.print("selected");%>>D&iacute;a(s)</OPTION>
                            <OPTION value="M" <% if(apBasic.getE01DEATRC().equals("M")) out.print("selected");%>>Mes(es)</OPTION>
                            <OPTION value="Y" <% if(apBasic.getE01DEATRC().equals("Y")) out.print("selected");%>>A&ntilde;o(s)</OPTION>
                        </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha Vencimiento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAMD1" <% if (apBasic.getF01DEAMD1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAMD1().trim()%>" onChange="setRecalculate()" <% if (!(apBasic.getH01FLGMAS().equals("N") )) out.print("disabled"); %> readonly>
              <input type="text" name="E01DEAMD2" <% if (apBasic.getF01DEAMD2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAMD2().trim()%>" onChange="setRecalculate()" <% if (!(apBasic.getH01FLGMAS().equals("N") )) out.print("disabled"); %> readonly>
              <input type="text" name="E01DEAMD3" <% if (apBasic.getF01DEAMD3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAMD3().trim()%>" onChange="setRecalculate()" <% if (!(apBasic.getH01FLGMAS().equals("N") )) out.print("disabled"); %> readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
            <% if (!apBasic.getH01FLGMAS().trim().equals("N")) {%>
            <td nowrap width="25%"> 
              <div align="right">Monto :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEAOAM" <% if (apBasic.getF01DEAOAM().equals("Y")) out.print("id=\"txtchanged\""); %> size="15" maxlength="15" value="<%= apBasic.getE01DEAOAM().trim()%>" onKeypress="enterDecimal()" onChange="setRecalculate()" <% if (!(apBasic.getH01FLGMAS().equals("N") )) out.print("disabled"); %> readonly>
            </td>
            <% } %>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo Tasa Flotante :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01DEAFTB" <% if (apBasic.getF01DEAFTB().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAFTB().trim()%>" onChange="setRecalculate()" readonly>
              <a href="javascript:GetFloating('E01DEAFTB')"></a> 
              <select name="E01DEAFTY" onChange="setRecalculate()" disabled>
                <option value=" " <% if (!(apBasic.getE01DEAFTY().equals("FP") ||apBasic.getE01DEAFTY().equals("FS"))) out.print("selected"); %>></option>
                <option value="FP" <% if (apBasic.getE01DEAFTY().equals("FP")) out.print("selected"); %>>FP</option>
                <option value="FS" <% if (apBasic.getE01DEAFTY().equals("FS")) out.print("selected"); %>>FS</option>
              </select>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa Flotante :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01FLTRTE" <% if (apBasic.getF01FLTRTE().equals("Y")) out.print("id=\"txtchanged\""); %> size="9" maxlength="9" value="<%= apBasic.getE01FLTRTE().trim()%>" <% if (!(apBasic.getH01FLGMAS().equals("N") )) out.print("disabled"); %> readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Tasa/Cargo por Mora :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAPEI" <% if (apBasic.getF01DEAPEI().equals("Y")) out.print("id=\"txtchanged\""); %> size="8" maxlength="7" value="<%= apBasic.getE01DEAPEI().trim()%>" readonly>
              <input type="text" name="E01DEAPIF" <% if (apBasic.getF01DEAPIF().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPIF().trim()%>" readonly>
              <a href="javascript:lnGetFact('E01DEAPIF','STATIC_ln_fact.jsp')"></a> 
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa Int./Spread :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEARTE" <% if (apBasic.getF01DEARTE().equals("Y")) out.print("id=\"txtchanged\""); %> size="10" maxlength="9" value="<%= apBasic.getE01DEARTE().trim()%>" onKeypress="enterSignDecimal()" onChange="setRecalculate()" <% if (!(apBasic.getH01FLGMAS().equals("N") )) out.print("disabled"); %> readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Linea de Cr&eacute;dito :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEACMC" <% if (apBasic.getF01DEACMC().equals("Y")) out.print("id=\"txtchanged\""); %> size="9" maxlength="9" value="<%= apBasic.getE01DEACMC().trim()%>" readonly>
              <input type="text" name="E01DEACMN" <% if (apBasic.getF01DEACMN().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="4" value="<%= apBasic.getE01DEACMN().trim()%>" readonly>
               
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
            <td nowrap width="25%" > 
              <div align="right">Nro  Referencia :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAREF" <% if (apBasic.getF01DEAREF().equals("Y")) out.print("id=\"txtchanged\""); %> size="12" maxlength="12" value="<%= apBasic.getE01DEAREF().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Ciclo de Revisi&oacute;n :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEARRP" <% if (apBasic.getF01DEARRP().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="3" value="<%= apBasic.getE01DEARRP().trim()%>" readonly>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Fecha de Revisi&oacute;n :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEARR1" <% if (apBasic.getF01DEARR1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEARR1().trim()%>" readonly>
              <input type="text" name="E01DEARR2" <% if (apBasic.getF01DEARR2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEARR2().trim()%>" readonly>
              <input type="text" name="E01DEARR3" <% if (apBasic.getF01DEARR3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEARR3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Retenci&oacute;n  Impuestos : </div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAWHF" <% if (apBasic.getF01DEAWHF().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAWHF().trim()%>" readonly>
               
            </td>
            <td nowrap width="25%" > 
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEACCN" <% if (apBasic.getF01DEACCN().equals("Y")) out.print("id=\"txtchanged\""); %> size="8" maxlength="8" value="<%= apBasic.getE01DEACCN().trim()%>" readonly>
               
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Pr&eacute;stamo a Demanda :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="hidden" name="E01DEALNC" value="<%= apBasic.getE01DEALNC()%>">
              <input type="radio" name="CE01DEALNC" value="Y" onClick="document.forms[0].E01DEALNC.value='Y'"
			  <%if(apBasic.getE01DEALNC().equals("Y")) out.print("checked");%> disabled>
              S&iacute; 
              <input type="radio" name="CE01DEALNC" value="N" onClick="document.forms[0].E01DEALNC.value='N'"
			  <%if(apBasic.getE01DEALNC().equals("N")) out.print("checked");%> disabled>
              No </td>
            <td nowrap width="25%" > 
              <div align="right">Direcci&oacute;n  Correo :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAMLA" <% if (apBasic.getF01DEAMLA().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAMLA().trim()%>" readonly>
               
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Cambio :</div>
            </td>
            <td nowrap width="23%" > 
				<input type="text" name="E01DEAEXR" size="13" maxlength="12" value="<%= apBasic.getE01DEAEXR().trim()%>" readonly>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Clase de Cr&eacute;dito :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEACLF" <% if (apBasic.getF01DEACLF().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEACLF().trim()%>" readonly>
               
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Comisionista :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEABRK" <% if (apBasic.getF01DEABRK().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DEABRK().trim()%>" readonly>
               
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa  Comisionista :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEABCP" <% if (apBasic.getF01DEABCP().equals("Y")) out.print("id=\"txtchanged\""); %> size="10" maxlength="9" value="<%= apBasic.getE01DEABCP().trim()%>" onKeypress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trdark">         		          
            <td nowrap width="25%" > 
              <div align="right">C&oacute;digo de Obligaci&oacute;n :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAOBL" <% if (apBasic.getF01DEAOBL().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= apBasic.getE01DEAOBL().trim()%>" readonly>
            </td>
            <td nowrap width="25%" > 
              <div align="right">C&oacute;digo de Riesgo :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEARRC" <% if (apBasic.getF01DEARRC().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= apBasic.getE01DEARRC().trim()%>" readonly>
            </td>		 
          </tr>         
          <tr id="trclear">         		          
            <td align=right nowrap>
              <div align="right">Banco/Sucursal :</div>
            </td>
            <td nowrap>
             <% if( protect || !apBasic.getH01FLGMAS().trim().equals("N")) {%>
              <input readonly type="text" size="2" maxlength="2" value="<%= apBasic.getE01DEABNK().trim()%>" name="E01DEABNK" onChange="setRecalculate()">
              <input readonly type="text" name="E01DEABRN" size="2" maxlength="3" value="<%= apBasic.getE01DEABRN().trim()%>"  onchange="setRecalculate()">          
			<%
            } else {
            %>      
              <input type="text" size="2" maxlength="2" value="<%= apBasic.getE01DEABNK().trim()%>" name="E01DEABNK" onChange="setRecalculate()" readonly>
              <input type="text" name="E01DEABRN" size="2" maxlength="3" value="<%= apBasic.getE01DEABRN().trim()%>" onChange="setRecalculate()" readonly>
              
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" border="0" >
            <%} %>
            </td>
            <td nowrap width="25%" >
            </td>
            <td nowrap width="27%" >
            </td>		 
          </tr>                    
          <%if (userPO.getPurpose().equals("MAINTENANCE")){%>  
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Inter&eacute;s :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAICT" <% if (apBasic.getF01DEAICT().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAICT().trim()%>" readonly>
              </td>
            <td nowrap width="25%" > 
              <div align="right">Per&iacute;odo Base :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEABAS" <% if (apBasic.getF01DEABAS().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DEABAS().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">C&aacute;lculo  Int./Normal :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAIFL" <% if (apBasic.getF01DEAIFL().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAIFL().trim()%>" readonly>
               
            </td>
            <td nowrap width="25%" > 
              <div align="right">C&aacute;lculo  Int./Mora :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAPCL" <% if (apBasic.getF01DEAPCL().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPCL().trim()%>" readonly>
              </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tabla Cargos :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEATLN" <% if (apBasic.getF01DEATLN().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEATLN().trim()%>" readonly>
               
            </td>
            <td nowrap width="25%" > 
              <div align="right">Per&iacute;odo de Gracia :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAGPD" <% if (apBasic.getF01DEAGPD().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= apBasic.getE01DEAGPD().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Tabla Tasas :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEARTB" <% if (apBasic.getF01DEARTB().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEARTB().trim()%>" readonly>
               
            </td>
            <td nowrap width="25%" > 
              <div align="right">Condici&oacute;n  Cr&eacute;dito :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEADLC" <% if (apBasic.getF01DEADLC().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEADLC().trim()%>" readonly>
               
            </td>
          </tr>
          <%}%> 
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Tipo de Relacion 1 :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAPAR" <% if (apBasic.getF01DEAPAR().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPAR().trim()%>" readonly>
               
            </td>
            <td nowrap width="25%" > 
              <div align="right">Cuenta  Relacion 1 :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEAPAC" <% if (apBasic.getF01DEAPAC().equals("Y")) out.print("id=\"txtchanged\""); %> size="12" maxlength="12" value="<%= apBasic.getE01DEAPAC().trim()%>" readonly>
               
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="25%" >
              <div align="right">Tipo de Relacion 2 :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" name="E01DEARET" <% if (apBasic.getF01DEARET().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEARET().trim()%>" readonly>
              </td>
            <td nowrap width="25%" >
              <div align="right">Cuenta  Relacion 2 :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01DEAREA" <% if (apBasic.getF01DEAREA().equals("Y")) out.print("id=\"txtchanged\""); %> size="12" maxlength="12" value="<%= apBasic.getE01DEAREA().trim()%>" readonly>
              </td>
          </tr>          
        </table>
      </td>
    </tr>
  </table>
<% } else {%>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAOD1" <% if (apBasic.getF01DEAOD1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAOD1().trim()%>" readonly>
              <input type="text" name="E01DEAOD2" <% if (apBasic.getF01DEAOD2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAOD2().trim()%>" readonly>
              <input type="text" name="E01DEAOD3" <% if (apBasic.getF01DEAOD3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAOD3().trim()%>" readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">T&eacute;rmino :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEATRM" <% if (apBasic.getF01DEATRM().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="4" value="<%= apBasic.getE01DEATRM().trim()%>" readonly>
              <select name="E01DEATRC" disabled>
                <option value=" " <% if (!(apBasic.getE01DEATRC().equals("D") ||apBasic.getE01DEATRC().equals("M")||apBasic.getE01DEATRC().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(apBasic.getE01DEATRC().equals("D")) out.print("selected");%>>D&iacute;a(s)</option>
                <option value="M" <% if(apBasic.getE01DEATRC().equals("M")) out.print("selected");%>>Mes(es)</option>
                <option value="Y" <% if(apBasic.getE01DEATRC().equals("Y")) out.print("selected");%>>A&ntilde;o(s)</option>
              </select>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Vencimiento :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAMD1" <% if (apBasic.getF01DEAMD1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAMD1().trim()%>" readonly>
              <input type="text" name="E01DEAMD2" <% if (apBasic.getF01DEAMD2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAMD2().trim()%>" readonly>
              <input type="text" name="E01DEAMD3" <% if (apBasic.getF01DEAMD3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAMD3().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
            <% if (!apBasic.getH01FLGMAS().trim().equals("N")) {%>
            <td nowrap width="25%"> 
              <div align="right">Monto :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEAOAM" <% if (apBasic.getF01DEAOAM().equals("Y")) out.print("id=\"txtchanged\""); %> size="15" maxlength="15" value="<%= apBasic.getE01DEAOAM().trim()%>" onKeypress="enterDecimal()" readonly>
            </td>
            <% }%>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" >
            	<div align="right">N&uacute;mero de Referencia :</div>
            </td>
            <td nowrap width="23%" ><input type="text" name="E01DEAREF" <% if (apBasic.getF01DEAREF().equals("Y")) out.print("id=\"txtchanged\""); %> size="12" maxlength="12" value="<%= apBasic.getE01DEAREF().trim()%>" readonly></td>
            <td nowrap width="25%" ><div align="right">Centro de Costo :</div></td>
            <td nowrap width="27%" ><input type="text" name="E01DEACCN" <% if (apBasic.getF01DEACCN().equals("Y")) out.print("id=\"txtchanged\""); %> size="8" maxlength="8" value="<%= apBasic.getE01DEACCN().trim()%>" readonly>
               </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" ><div align="right">Direcci&oacute;n de Correos :</div></td>
            <td nowrap width="23%" ><input type="text" name="E01DEAMLA" <% if (apBasic.getF01DEAMLA().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAMLA().trim()%>" readonly>
               </td>
            <td nowrap width="25%" > 
              
            </td>
            <td nowrap width="27%" > 
              
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" ><div align="right">C&oacute;digo de Obligaci&oacute;n :</div></td>
            <td nowrap width="23%" ><input type="text" name="E01DEAOBL" <% if (apBasic.getF01DEAOBL().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= apBasic.getE01DEAOBL().trim()%>" readonly></td>
            <td nowrap width="25%" > 
              <div align="right">C&oacute;digo de Riesgo :</div>            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEARRC" <% if (apBasic.getF01DEARRC().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= apBasic.getE01DEARRC().trim()%>" readonly>
            </td>
          </tr>
          
          
        </table>
      </td>
    </tr>
  </table>

<% } %>
<%if ( genFlag.equals("U") == false ) {%>
  <%if (userPO.getPurpose().equals("MAINTENANCE")){%> 
  <h4>Prioridad de Pagos </h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"  > 
              <div align="center"> 
                <input type="text" name="E01DEAPP1" <% if (apBasic.getF01DEAPP1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPP1().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="14%"  > 
              <div align="center"> 
                <input type="text" name="E01DEAPP2" <% if (apBasic.getF01DEAPP2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPP2().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" name="E01DEAPP3" <% if (apBasic.getF01DEAPP3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPP3().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="22%"  > 
              <div align="center"> 
                <input type="text" name="E01DEAPP4" <% if (apBasic.getF01DEAPP4().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPP4().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="17%" > 
              <div align="center"> 
                <input type="text" name="E01DEAPP5" <% if (apBasic.getF01DEAPP5().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPP5().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="15%"  > 
              <div align="center"> 
                <input type="text" name="E01DEAPP6" <% if (apBasic.getF01DEAPP6().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPP6().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="center">Principal</div>
            </td>
            <td nowrap width="14%" > 
              <div align="center">Intereses</div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">Mora</div>
            </td>
            <td nowrap width="22%" > 
              <div align="center">Impuestos / Comisiones</div>
            </td>
            <td nowrap width="17%" > 
              <div align="center">Deducciones</div>
            </td>
            <td nowrap width="15%" > 
              <div align="center">I.V.A.</div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  
  <%if (userPO.getPurpose().equals("MAINTENANCE")){%> 
  <table class="tbenter">
    <tr > 
      <td nowrap>
       <B>Lista de Deducciones</B>
      </td>
    </tr>
  </table> 
  
  <table class="tableinfo" >
    <tr > 
      <td nowrap> 
        <table  id="TBDEDUCT" cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="center">C&oacute;digo</div>
            </td>
            <td nowrap width="30%" > 
              <div align="center">Compa&ntilde;&iacute;a</div>
            </td>
            <td nowrap width="20%"> 
              <div align="center">P&oacute;liza N&uacute;mero</div>
            </td>
            <td nowrap width="20%" > 
              <div align="center">Deducci&oacute;n</div>
            </td>
            <td nowrap width="10%" > 
              <div align="center">Factor</div>
            </td>
          </tr>
          <% if (!ded.getNoResult()) {
                ded.initRow();
                while (ded.getNextRow()) {
                    if (ded.getFlag().equals("")) {
                    		//out.println(list.getRecord());
	      %> 
          <tr id="trclear">             
            <td nowrap width="20%" > 
              <div align="center"><a href="javascript:getDeduct('<%= apBasic.getE01DEAACC().trim() %>','<%= ded.getRecord(4) %>')"><%= ded.getRecord(4) %></a></div>
            </td>
            <td nowrap width="30%" > 
              <div align="center"><a href="javascript:getDeduct('<%= apBasic.getE01DEAACC().trim() %>','<%= ded.getRecord(4) %>')"><%= ded.getRecord(10) %></a></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><a href="javascript:getDeduct('<%= apBasic.getE01DEAACC().trim() %>','<%= ded.getRecord(4) %>')"><%= ded.getRecord(16) %></a></div>
            </td>
            <td nowrap width="20%" > 
              <div align="center"><a href="javascript:getDeduct('<%= apBasic.getE01DEAACC().trim() %>','<%= ded.getRecord(4) %>')"><%= ded.getRecord(5) %></a></div>
            </td>
            <td nowrap width="10%" > 
              <div align="center"><a href="javascript:getDeduct('<%= apBasic.getE01DEAACC().trim() %>','<%= ded.getRecord(4) %>')"><%= ded.getRecord(6) %></a></div>
            </td>
          </tr>
          <%
                    }
                }
   		  } else {  %>         
         <tr id="trclear">
            <td colspan=5>
              <h5 align="center">No existen Deducciones asignadas</h5>
            </td>
         </tr>
       <%  }   %>
       </table>
        </td>
        </tr>
  </table>
  <%  }   %> 
  
  <h4>Condiciones de Pago<% if(DEAIPD.equals("MAT") && DEAPPD.equals("MAT")) { %> 
  </h4>
  <h5 align="center">Pagar el Inter&eacute;s y el Capital al Vencimiento (MAT) </h5>
  <%}%> <% if(isDEAIPDNum && DEAPPD.equals("MAT")){ %> 
  <h5 align="center">Pagar el Capital al Vencimiento y el Inter&eacute;s seg&uacute;n 
    su Ciclo</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago de Intereses :</div>
            </td>
            <td nowrap width="15%"> <input type="text" size="4" maxlength="3" name="E01DEAIPD" value="<%= apBasic.getE01DEAIPD().trim()%>" readonly> </td>
            <td nowrap width="22%"> 
              <div align="right">Pr&oacute;xima Fecha de Pago :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="PRXPAG01" size="2" maxlength="2" value="<%= apBasic.getE01DEARD1().trim()%>" onBlur="document.forms[0].E01DEARD1.value = document.forms[0].PRXPAG01.value" readonly>
              <input type="text" name="PRXPAG02" size="2" maxlength="2" value="<%= apBasic.getE01DEARD2().trim()%>" onBlur="document.forms[0].E01DEARD2.value = document.forms[0].PRXPAG02.value" readonly>
              <input type="text" name="PRXPAG03" size="2" maxlength="2" value="<%= apBasic.getE01DEARD3().trim()%>" onBlur="document.forms[0].E01DEARD3.value = document.forms[0].PRXPAG03.value" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" width="16" height="20"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago Capital :</div>
            </td>
            <td nowrap width="15%"><input type="text" name="E01DEAPPD" <% if (apBasic.getF01DEAPPD().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DEAPPD().trim()%>"> </td>
            <td nowrap width="22%"> 
              <div align="right">Mes excluido en Pagos :</div>
            </td>
            <td nowrap colspan="2">
            	<INPUT type="text" name="E01DLCMN1" size="3" maxlength="2" value="<%= apBasic.getE01DLCMN1().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <% if(isDEAIPDNum && isDEAPPDNum){ %>
  <h5 align="center">Pagar el Inter&eacute;s y el Capital seg&uacute;n sus ciclos</h5>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago de Intereses :</div>
            </td>
            <td nowrap width="15%"><input type="text" name="E01DEAIPD" <% if (apBasic.getF01DEAIPD().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DEAIPD().trim()%>" readonly> </td>
            <td nowrap width="22%"> 
              <div align="right">Pr&oacute;xima Fecha de Pago :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="PRXPAG11" size="2" maxlength="2" value="<%= apBasic.getE01DEARD1().trim()%>" onBlur="document.forms[0].E01DEARD1.value = document.forms[0].PRXPAG11.value" readonly>
              <input type="text" name="PRXPAG12" size="2" maxlength="2" value="<%= apBasic.getE01DEARD2().trim()%>" onBlur="document.forms[0].E01DEARD2.value = document.forms[0].PRXPAG12.value" readonly>
              <input type="text" name="PRXPAG13" size="2" maxlength="2" value="<%= apBasic.getE01DEARD3().trim()%>" onBlur="document.forms[0].E01DEARD3.value = document.forms[0].PRXPAG13.value" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" width="16" height="20"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago Capital :</div>
            </td>
            <td nowrap width="15%"><input type="text" name="E01DEAPPD" <% if (apBasic.getF01DEAPPD().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DEAPPD().trim()%>" readonly> </td>
            <td nowrap width="22%"> 
              <div align="right">Pr&oacute;xima Fecha de Pago :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01DEAHE1" <% if (apBasic.getF01DEAHE1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAHE1().trim()%>" readonly>
              <input type="text" name="E01DEAHE2" <% if (apBasic.getF01DEAHE2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAHE2().trim()%>" readonly>
              <input type="text" name="E01DEAHE3" <% if (apBasic.getF01DEAHE3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAHE3().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" width="16" height="20"  > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" > 
              <div align="right">Valor de la Cuota :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" name="E01DEAROA" <% if (apBasic.getF01DEAROA().equals("Y")) out.print("id=\"txtchanged\""); %> size="15" maxlength="15" value="<%= apBasic.getE01DEAROA().trim()%>" onKeypress="enterDecimal()" readonly>
            </td>
            <td nowrap width="22%" >
              <div align="right">Incluye Intereses Pago :</div>
            </td>
            <td nowrap colspan="2" >
              <input type="hidden" name="E01DEAIIP" value="<%= apBasic.getE01DEAIIP()%>">
              <input type="radio" name="CE01DEAIIP" value="Y" onClick="document.forms[0].E01DEAIIP.value='Y'"
			  <%if(apBasic.getE01DEAIIP().equals("Y")) out.print("checked");%> disabled>
              S&iacute; 
              <input type="radio" name="CE01DEAIIP" value="N" onClick="document.forms[0].E01DEAIIP.value='N'"
			  <%if(apBasic.getE01DEAIIP().equals("N")) out.print("checked");%> disabled>
              No <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0" width="16" height="20"  ></td>
          </tr>
          <TR id="trclear">
            <td nowrap width="33%" > 
                 <div align="right">Mes excluido en Pagos :</div>
            </td>
            <td nowrap width="15%" >
                <input type="text" name="E01DLCMN1" size="2" maxlength="2" value="<%= apBasic.getE01DLCMN1().trim()%>" readonly>              
            </td>
            <td nowrap width="22%" >
                 
            </td>
            <td nowrap colspan="2" > 
                         
            </td>
          </TR>             
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  
    <% if(DEAIPD.equals("SC3") && DEAPPD.equals("SC3")){ %>
  <h5 align="center">Cuotas Niveladas (SC3)</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="33%"> 
              <div align="right">Total de Cuotas :</div>
            </td>
            <td nowrap width="15%"> 
              <input type="text" name="VALNC01" size="4" maxlength="3" value="<%= apBasic.getE01DLCNC1().trim()%>" onBlur="document.forms[0].E01DLCNC1.value = document.forms[0].VALNC01.value" readonly>
            </td>
            <td nowrap width="22%"> 
              <div align="right">Frecuencia de Pagos :</div>
            </td>
            <td nowrap colspan="2">
              <input type="text" name="FRCFP01" size="4" maxlength="3" value="<%= apBasic.getE01DLCFP1().trim()%>" onBlur="document.forms[0].E01DLCFP1.value = document.forms[0].FRCFP01.value" readonly>
              <select name="FRCTP01" onBlur="document.forms[0].E01DLCTP1.value = document.forms[0].FRCTP01.value">
                <option value=" " <% if (!(apBasic.getE01DLCTP1().equals("D") ||apBasic.getE01DLCTP1().equals("M")||apBasic.getE01DLCTP1().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(apBasic.getE01DLCTP1().equals("D")) out.print("selected");%>>D&iacute;a(s)</option>
                <option value="M" <% if(apBasic.getE01DLCTP1().equals("M")) out.print("selected");%>>Mes(es)</option>
                <option value="Y" <% if(apBasic.getE01DLCTP1().equals("Y")) out.print("selected");%>>A&ntilde;o(s)</option>
              </select>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" > 
              <div align="right">Intereses Prepagados Hasta :</div>
            </td>
            <td nowrap width="15%" >
              <input type="text" name="E01DLCPM1" <% if (apBasic.getF01DLCPM1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCPM1().trim()%>" readonly>
              <input type="text" name="E01DLCPD1" <% if (apBasic.getF01DLCPD1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCPD1().trim()%>" readonly>
              <input type="text" name="E01DLCPY1" <% if (apBasic.getF01DLCPY1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCPY1().trim()%>" readonly>
            </td>
            <td nowrap width="22%" > 
              <div align="right">Per&iacute;odo de Gracia :</div>
            </td>
            <td nowrap colspan="2" > 
              <input type="text" name="E01DLCGM1" <% if (apBasic.getF01DLCGM1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCGM1().trim()%>" readonly>
              <input type="text" name="E01DLCGD1" <% if (apBasic.getF01DLCGD1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCGD1().trim()%>" readonly>
              <input type="text" name="E01DLCGY1" <% if (apBasic.getF01DLCGY1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCGY1().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > 
              <div align="right">Primera Fecha de Pago : </div>
            </td>
            <td nowrap width="15%" >
              <input type="text" name="E01DLCXM1" <% if (apBasic.getF01DLCXM1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCXM1().trim()%>" readonly>
              <input type="text" name="E01DLCXD1" <% if (apBasic.getF01DLCXD1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCXD1().trim()%>" readonly>
              <input type="text" name="E01DLCXY1" <% if (apBasic.getF01DLCXY1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCXY1().trim()%>" readonly>
            </td>
            <td nowrap width="22%" > 
              <div align="right">Valor de la Cuota :</div>
            </td>
            <td nowrap colspan="2" >
              <input type="text" name="VALVA01" size="14" maxlength="13" value="<%= apBasic.getE01DLCVA1().trim()%>" onBlur="document.forms[0].E01DLCVA1.value = document.forms[0].VALVA01.value" readonly>
            </td>
          </tr>
          <TR id="trdark">
            <td nowrap width="33%" > 
                 <div align="right">Mes excluido en Pagos :</div>
            </td>
            <td nowrap width="15%" >
                <input type="text" name="E01DLCMN1" size="2" maxlength="2" value="<%= apBasic.getE01DLCMN1().trim()%>" readonly>              
            </td>
            <td nowrap width="22%" >
                 
            </td>
            <td nowrap colspan="2" > 
                         
            </td>
          </TR>             
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  
  
  <% if(DEAIPD.equals("SC1") && DEAPPD.equals("SC1")){ %>
  <h5 align="center">Cuotas Niveladas (SC1)</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="33%"> 
              <div align="right">Total de Cuotas :</div>
            </td>
            <td nowrap width="15%"> 
              <input type="text" name="VALNC01" size="4" maxlength="3" value="<%= apBasic.getE01DLCNC1().trim()%>" onBlur="document.forms[0].E01DLCNC1.value = document.forms[0].VALNC01.value" readonly>
            </td>
            <td nowrap width="22%"> 
              <div align="right">Frecuencia de Pagos :</div>
            </td>
            <td nowrap colspan="2">
              <input type="text" name="FRCFP01" size="4" maxlength="3" value="<%= apBasic.getE01DLCFP1().trim()%>" onBlur="document.forms[0].E01DLCFP1.value = document.forms[0].FRCFP01.value" readonly>
              <select name="FRCTP01" onBlur="document.forms[0].E01DLCTP1.value = document.forms[0].FRCTP01.value" disabled>
                <option value=" " <% if (!(apBasic.getE01DLCTP1().equals("D") ||apBasic.getE01DLCTP1().equals("M")||apBasic.getE01DLCTP1().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(apBasic.getE01DLCTP1().equals("D")) out.print("selected");%>>D&iacute;a(s)</option>
                <option value="M" <% if(apBasic.getE01DLCTP1().equals("M")) out.print("selected");%>>Mes(es)</option>
                <option value="Y" <% if(apBasic.getE01DLCTP1().equals("Y")) out.print("selected");%>>A&ntilde;o(s)</option>
              </select>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" > 
              <div align="right">Intereses Prepagados Hasta :</div>
            </td>
            <td nowrap width="15%" >
              <input type="text" name="E01DLCPM1" <% if (apBasic.getF01DLCPM1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCPM1().trim()%>" readonly>
              <input type="text" name="E01DLCPD1" <% if (apBasic.getF01DLCPD1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCPD1().trim()%>" readonly>
              <input type="text" name="E01DLCPY1" <% if (apBasic.getF01DLCPY1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCPY1().trim()%>" readonly>
            </td>
            <td nowrap width="22%" > 
              <div align="right">Per&iacute;odo de Gracia :</div>
            </td>
            <td nowrap colspan="2" > 
              <input type="text" name="E01DLCGM1" <% if (apBasic.getF01DLCGM1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCGM1().trim()%>" readonly>
              <input type="text" name="E01DLCGD1" <% if (apBasic.getF01DLCGD1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCGD1().trim()%>" readonly>
              <input type="text" name="E01DLCGY1" <% if (apBasic.getF01DLCGY1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCGY1().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > 
              <div align="right">Primera Fecha de Pago : </div>
            </td>
            <td nowrap width="15%" >
              <input type="text" name="E01DLCXM1" <% if (apBasic.getF01DLCXM1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCXM1().trim()%>" readonly>
              <input type="text" name="E01DLCXD1" <% if (apBasic.getF01DLCXD1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCXD1().trim()%>" readonly>
              <input type="text" name="E01DLCXY1" <% if (apBasic.getF01DLCXY1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCXY1().trim()%>" readonly>
            </td>
            <td nowrap width="22%" > 
              <div align="right">Valor de la Cuota :</div>
            </td>
            <td nowrap colspan="2" >
              <input type="text" name="VALVA01" size="14" maxlength="13" value="<%= apBasic.getE01DLCVA1().trim()%>" onBlur="document.forms[0].E01DLCVA1.value = document.forms[0].VALVA01.value" readonly>
            </td>
          </tr>
          <TR id="trdark">
            <td nowrap width="33%" > 
                 <div align="right">Mes excluido en Pagos :</div>
            </td>
            <td nowrap width="15%" >
                <input type="text" name="E01DLCMN1" size="2" maxlength="2" value="<%= apBasic.getE01DLCMN1().trim()%>" readonly>              
            </td>
            <td nowrap width="22%" >
                 
            </td>
            <td nowrap colspan="2" > 
                         
            </td>
          </TR>             

        </table>
      </td>
    </tr>
  </table>
  <h5>Pagos Irregulares </h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap>
        <table class="tableinfo" style="filter:''">
          <tr > 
            <td nowrap> 
              <table cellpadding=2 cellspacing=0 width="100%" border="0">
                <tr id="trdark"> 
                  <td nowrap width="25%"> 
                    <div align="center">Cuota No. </div>
                  </td>
                  <td nowrap width="25%"> 
                    <div align="center">Principal</div>
                  </td>
                </tr>
                <%
                pmntPlus.initRow();
                while (pmntPlus.getNextRow()) {
                    if (pmntPlus.getFlag().equals("")) {
	      %> 
                <tr id="trclear"> 
                  <td nowrap width="25%"> 
                    <div align="center"><%= pmntPlus.getRecord(0) %></div>
                  </td>
                  <td nowrap width="25%"> 
                    <div align="center"><%= pmntPlus.getRecord(1) %></div>
                  </td>
                </tr>
                <%
                    }
                }
    %> 
              </table>
            </td>
          </tr>
        </table>

        <p align="center">&nbsp;</p>
        
      </td>
    </tr>
  </table>  
  <%}%>
  <% if(DEAIPD.equals("SC2") && DEAPPD.equals("SC2")){ %>
  <h5 align="center"> Doble Esquema de Pago (SC2)</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="33%"><b>Plan 1</b></td>
            <td nowrap width="15%">&nbsp;</td>
            <td nowrap width="22%">&nbsp;</td>
            <td nowrap colspan="2">&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Porcentaje Capital :</div>
            </td>
            <td nowrap width="15%"> 
              <input type="text" name="E01DLCOR1" <% if (apBasic.getF01DLCOR1().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DLCOR1().trim()%>" readonly>
              % </td>
            <td nowrap width="22%"> 
              <div align="right">Valor Capital :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01DLCPP1" <% if (apBasic.getF01DLCPP1().equals("Y")) out.print("id=\"txtchanged\""); %> size="14" maxlength="13" value="<%= apBasic.getE01DLCPP1().trim()%>" onKeypress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" height="31"> 
              <div align="right">N&uacute;mero de Cuotas :</div>
            </td>
            <td nowrap width="15%" height="31"> 
              <input type="text" name="VALNC11" size="4" maxlength="3" value="<%= apBasic.getE01DLCNC1().trim()%>" onBlur="document.forms[0].E01DLCNC1.value = document.forms[0].VALNC11.value" readonly>
            </td>
            <td nowrap width="22%" height="31"> 
              <div align="right">Frecuencia de Pagos :</div>
            </td>
            <td nowrap colspan="2" height="31"> 
              <input type="text" name="FRCFP11" size="4" maxlength="3" value="<%= apBasic.getE01DLCFP1().trim()%>" onBlur="document.forms[0].E01DLCFP1.value = document.forms[0].FRCFP11.value" readonly>
              <select name="FRCTP11" onBlur="document.forms[0].E01DLCTP1.value = document.forms[0].FRCTP11.value" disabled>
                <option value=" " <% if (!(apBasic.getE01DLCTP1().equals("D") ||apBasic.getE01DLCTP1().equals("M")||apBasic.getE01DLCTP1().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(apBasic.getE01DLCTP1().equals("D")) out.print("selected");%>>D&iacute;a(s)</option>
                <option value="M" <% if(apBasic.getE01DLCTP1().equals("M")) out.print("selected");%>>Mes(es)</option>
                <option value="Y" <% if(apBasic.getE01DLCTP1().equals("Y")) out.print("selected");%>>A&ntilde;o(s)</option>
              </select>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" > 
              <div align="right">Valor de la Cuota :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" name="VALVA11" size="14" maxlength="13" value="<%= apBasic.getE01DLCVA1().trim()%>" onBlur="document.forms[0].E01DLCVA1.value = document.forms[0].VAlVA11.value" readonly>
            </td>
            <td nowrap width="22%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2" >&nbsp; </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > 
              <div align="right"></div>
            </td>
            <td nowrap width="15%" >&nbsp; </td>
            <td nowrap width="22%" > 
              <div align="right"></div>
            </td>
            <td nowrap colspan="2" >&nbsp; </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" ><b>Plan 2</b></td>
            <td nowrap width="15%" >&nbsp;</td>
            <td nowrap width="22%" >&nbsp;</td>
            <td nowrap colspan="2" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%"> 
              <div align="right">Porcentaje Capital :</div>
            </td>
            <td nowrap width="15%"> 
              <input type="text" name="E01DLCOR2" <% if (apBasic.getF01DLCOR2().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DLCOR2().trim()%>" onKeypress="enterDecimal()" readonly>
              % </td>
            <td nowrap width="22%"> 
              <div align="right">Valor Capital :</div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" name="E01DLCPP2" <% if (apBasic.getF01DLCPP2().equals("Y")) out.print("id=\"txtchanged\""); %> size="14" maxlength="13" value="<%= apBasic.getE01DLCPP2().trim()%>" onKeypress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" height="31"> 
              <div align="right">N&uacute;mero de Cuotas :</div>
            </td>
            <td nowrap width="15%" height="31"> 
              <input type="text" name="E01DLCNC2" <% if (apBasic.getF01DLCNC2().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DLCNC2().trim()%>" readonly>
            </td>
            <td nowrap width="22%" > 
              <div align="right">Frecuencia de Pagos :</div>
            </td>
            <td nowrap colspan="2" > 
              <input type="text" name="E01DLCFP2" <% if (apBasic.getF01DLCFP2().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DLCFP2().trim()%>" readonly>
              <select name="E01DLCTP2" disabled>
                <option value=" " <% if (!(apBasic.getE01DLCTP2().equals("D") ||apBasic.getE01DLCTP2().equals("M")||apBasic.getE01DLCTP2().equals("Y"))) out.print("selected"); %>></option>
                <option value="D" <% if(apBasic.getE01DLCTP2().equals("D")) out.print("selected");%>>D&iacute;a(s)</option>
                <option value="M" <% if(apBasic.getE01DLCTP2().equals("M")) out.print("selected");%>>Mes(es)</option>
                <option value="Y" <% if(apBasic.getE01DLCTP2().equals("Y")) out.print("selected");%>>A&ntilde;o(s)</option>
              </select>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > 
              <div align="right">Valor de la Cuota :</div>
            </td>
            <td nowrap width="15%" > 
              <input type="text" name="E01DLCVA2" <% if (apBasic.getF01DLCVA2().equals("Y")) out.print("id=\"txtchanged\""); %> size="14" maxlength="13" value="<%= apBasic.getE01DLCVA2().trim()%>" onKeypress="enterDecimal()" readonly>
            </td>
            <td nowrap width="22%" >&nbsp;</td>
            <td nowrap colspan="2" >&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%> <% if(DEAIPD.equals("SCH") && DEAPPD.equals("SCH")){ %> 
  <h5>Plan de Pagos </h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago de Intereses :</div>
            </td>
            <td nowrap width="15%"> <%= apBasic.getE01DEAIPD().trim()%> </td>
            <td nowrap width="22%"> 
              <div align="right">Ciclo Pago Capital :</div>
            </td>
            <td nowrap colspan="2"><%= apBasic.getE01DEAPPD().trim()%> </td>
          </tr>
        </table>
        <table class="tableinfo" style="filter:''">
          <tr > 
            <td nowrap> 
              <table cellpadding=2 cellspacing=0 width="100%" border="0">
                <tr id="trdark"> 
                  <td nowrap width="25%"> 
                    <div align="center">Cuota No. </div>
                  </td>
                  <td nowrap width="23%"> 
                    <div align="center">Fecha</div>
                  </td>
                  <td nowrap width="25%"> 
                    <div align="center">Principal</div>
                  </td>
                  <td nowrap width="27%"> 
                    <div align="center">Intereses</div>
                  </td>
                  <td nowrap width="27%">
                    <div align="center">Intereses Incluidos en Pago </div>
                  </td>
                </tr>
                <%
                pmnt.initRow();
                while (pmnt.getNextRow()) {
                    if (pmnt.getFlag().equals("")) {
                    		//out.println(coll.getRecord());
	      %> 
                <tr id="trclear"> 
                  <td nowrap width="25%"> 
                    <div align="center"><%= pmnt.getRecord(0) %></div>
                  </td>
                  <td nowrap width="23%"> 
                    <div align="center"><%= Util.formatDate(pmnt.getRecord(1),pmnt.getRecord(2),pmnt.getRecord(3))%></div>
                  </td>
                  <td nowrap width="25%"> 
                    <div align="center"><%= pmnt.getRecord(4) %></div>
                  </td>
                  <td nowrap width="27%"> 
                    <div align="center"><%= pmnt.getRecord(5) %></div>
                  </td>
                  <td nowrap width="27%">
                    <div align="center"><% if(pmnt.getRecord(6).equals("Y")) out.print("Sí");
					else if(pmnt.getRecord(6).equals("N")) out.print("No");
				    else out.print("Sí"); %></div>
                  </td>
                </tr>
                <%
                    }
                }
    %> 
              </table>
            </td>
          </tr>
        </table>

        <p align="center">&nbsp;</p>
        
      </td>
    </tr>
  </table>
  <%}%>
  <% if(isDEAIPDNum && DEAPPD.equals("SCH")){ %>
  <h5 align="center">Pagar el Inter&eacute;s seg&uacute;n Ciclo Preestablecido 
    y el Capital seg&uacute;n Plan de Pagos Irregular</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Ciclo Pago de Intereses :</div>
            </td>
            <td nowrap width="15%"><input type="text" name="E01DEAIPD" <% if (apBasic.getF01DEAIPD().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DEAIPD().trim()%>" readonly> </td>
            <td nowrap width="22%"> 
              <div align="right">Ciclo Pago Capital :</div>
            </td>
            <td nowrap colspan="2"><%= apBasic.getE01DEAPPD().trim()%> </td>
          </tr>
        </table>
        <p><b>Intereses</b></p>
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="right">Pr&oacute;xima Fecha de Pago :</div>
            </td>
            <td nowrap width="67%"> 
              <input type="text" name="PRXPAG21" size="2" maxlength="2" value="<%= apBasic.getE01DEARD1().trim()%>" onBlur="document.forms[0].E01DEARD1.value = document.forms[0].PRXPAG21.value" readonly>
              <input type="text" name="PRXPAG22" size="2" maxlength="2" value="<%= apBasic.getE01DEARD2().trim()%>" onBlur="document.forms[0].E01DEARD2.value = document.forms[0].PRXPAG22.value" readonly>
              <input type="text" name="PRXPAG23" size="2" maxlength="2" value="<%= apBasic.getE01DEARD3().trim()%>" onBlur="document.forms[0].E01DEARD3.value = document.forms[0].PRXPAG23.value" readonly>
            </td>
          </tr>
        </table>
        <p><b>Capital </b></p>
        <table class="tableinfo" style="filter:''">
          <tr > 
            <td nowrap> 
              <table cellpadding=2 cellspacing=0 width="100%" border="0">
                <tr id="trdark"> 
                  <td nowrap width="25%"> 
                    <div align="center">Cuota No. </div>
                  </td>
                  <td nowrap width="23%"> 
                    <div align="center">Fecha</div>
                  </td>
                  <td nowrap width="25%"> 
                    <div align="center">Principal</div>
                  </td>
                </tr>
                <%
                pmnt.initRow();
                while (pmnt.getNextRow()) {
                    if (pmnt.getFlag().equals("")) {
                    		//out.println(coll.getRecord());
	      %> 
                <tr id="trclear"> 
                  <td nowrap width="25%"> 
                    <div align="center"><%= pmnt.getRecord(0) %></div>
                  </td>
                  <td nowrap width="23%"> 
                    <div align="center"><%= Util.formatDate(pmnt.getRecord(1),pmnt.getRecord(2),pmnt.getRecord(3))%></div>
                  </td>
                  <td nowrap width="25%"> 
                    <div align="center"><%= pmnt.getRecord(4) %></div>
                  </td>
                </tr>
                <%
                    }
                }
    %> 
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
<% } %>
  <h4>Cuenta de Pago</h4>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="33%"> 
              <div align="center">Concepto</div>
            </td>
            <td nowrap width="15%"> 
              <div align="center">Banco </div>
            </td>
            <td nowrap width="22%"> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap> 
              <div align="center">Moneda </div>
            </td>
            <td nowrap> 
              <div align="center">Referencia</div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > 
              <div align="center"> 
                <input type="text" name="E01PAGOPE" value="<%= apBasic.getE01PAGOPE().trim()%>" size="3" maxlength="3" readonly>
                <input type="hidden" name="E01PAGGLN" value="<%= apBasic.getE01PAGGLN().trim()%>">
                <input type="text" name="E01PAGCON" size="25" maxlength="25" readonly value="<%= apBasic.getE01PAGCON().trim()%>">
				</div>
            </td>
            <td nowrap width="15%" > 
              <div align="center"> 
                <input type="text" name="E01PAGBNK" <% if (apBasic.getF01PAGBNK().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01PAGBNK().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="22%" > 
              <div align="center"> 
                <input type="text" name="E01PAGBRN" <% if (apBasic.getF01PAGBRN().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="3" value="<%= apBasic.getE01PAGBRN().trim()%>" readonly> 
               </div>
            </td>
            <td nowrap width="14%" > 
              <div align="center"> 
                <input type="text" name="E01PAGCCY" <% if (apBasic.getF01PAGCCY().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="3" value="<%= apBasic.getE01PAGCCY().trim()%>" readonly>
              </div>
	      </td>
            <td nowrap width="16%" > 
              <div align="center"> 
                <input type="text" name="E01PAGACC" <% if (apBasic.getF01PAGACC().equals("Y")) out.print("id=\"txtchanged\""); %> size="12" maxlength="12"  value="<%= apBasic.getE01PAGACC().trim()%>" readonly>
              </div>
			</td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="33%" >&nbsp;</td>
            <td nowrap width="15%" >&nbsp;</td>
            <td nowrap width="22%" >&nbsp;</td>
            <td nowrap width="14%" >&nbsp;</td>
            <td nowrap width="16%" >&nbsp;</td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="33%" > Autoriza Sobregiro : 
              <input type="text" name="E01DEAODA" <% if (apBasic.getF01DEAODA().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAODA().trim()%>" readonly>
               
            </td>
            <td nowrap width="15%" > 
              <div align="center"> </div>
            </td>
            <td nowrap width="22%" > 
              <div align="right">Autoriza Pagos en Feriados : </div>
            </td>
            <td nowrap colspan="2" > 
              <div align="left">
                <input type="hidden" name="E01DEAHFQ" value="<%= apBasic.getE01DEAHFQ()%>">
                <input type="radio" name="CE01DEAHFQ" value="1" onClick="document.forms[0].E01DEAHFQ.value='1'"
			  <%if(apBasic.getE01DEAHFQ().equals("1")) out.print("checked");%> disabled>
                S&iacute; 
                <input type="radio" name="CE01DEAHFQ" value="2" onClick="document.forms[0].E01DEAHFQ.value='2'"
			  <%if(apBasic.getE01DEAHFQ().equals("2")) out.print("checked");%> disabled>
                No </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4><% if(E01DEACLF.equals("A")) { %>Activos Fijos</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">N&uacute;mero del Activo :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01FIXASN" <% if (apBasic.getF01FIXASN().equals("Y")) out.print("id=\"txtchanged\""); %> size="9" maxlength="9" value="<%= apBasic.getE01FIXASN().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Cuenta Contable :</div>
            </td>
            <td nowrap width="27%">
              <input type="text" name="E01FIXGLN" <% if (apBasic.getF01FIXGLN().equals("Y")) out.print("id=\"txtchanged\""); %> size="16" maxlength="16" value="<%= apBasic.getE01FIXGLN().trim()%>" onKeypress="enterInteger()" readonly>
               
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Marca del Activo :</div>
            </td>
            <td nowrap width="23%">
              <input type="text" name="E01FIXMAR" <% if (apBasic.getF01FIXMAR().equals("Y")) out.print("id=\"txtchanged\""); %> size="20" maxlength="20" value="<%= apBasic.getE01FIXMAR().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
            <td nowrap width="25%"> 
              <div align="right">Centro de Costo :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01FIXCCN" <% if (apBasic.getF01FIXCCN().equals("Y")) out.print("id=\"txtchanged\""); %> size="8" maxlength="8" value="<%= apBasic.getE01FIXCCN().trim()%>" readonly>
               
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">M&oacute;dulo del Activo :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" name="E01FIXMOD" <% if (apBasic.getF01FIXMOD().equals("Y")) out.print("id=\"txtchanged\""); %> size="15" maxlength="15" value="<%= apBasic.getE01FIXMOD().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tasa de Cambio :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01FIXTIC" <% if (apBasic.getF01FIXTIC().equals("Y")) out.print("id=\"txtchanged\""); %> size="11" maxlength="11" value="<%= apBasic.getE01FIXTIC().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Valor del Activo :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" name="E01FIXPPR" <% if (apBasic.getF01FIXPPR().equals("Y")) out.print("id=\"txtchanged\""); %> size="15" maxlength="15" value="<%= apBasic.getE01FIXPPR().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
            <td nowrap width="25%" > 
              <div align="right">Tipo de Depreciaci&oacute;n :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01FIXDTY" <% if (apBasic.getF01FIXDTY().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01FIXDTY().trim()%>" readonly>
               
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">No. de Serie :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" name="E01FIXSER" <% if (apBasic.getF01FIXSER().equals("Y")) out.print("id=\"txtchanged\""); %> size="15" maxlength="15" value="<%= apBasic.getE01FIXSER().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
            <td nowrap width="25%" > 
              <div align="right">Porcentaje de Depreciaci&oacute;n :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01FIXPRT" <% if (apBasic.getF01FIXPRT().equals("Y")) out.print("id=\"txtchanged\""); %> size="5" maxlength="4" value="<%= apBasic.getE01FIXPRT().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Ubicaci&oacute;n :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" name="E01FIXLOC" <% if (apBasic.getF01FIXLOC().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01FIXLOC().trim()%>" readonly>
               
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
            <td nowrap width="25%" > 
              <div align="right">N&uacute;mero de Meses :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01FIXMTH" <% if (apBasic.getF01FIXMTH().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01FIXMTH().trim()%>" onKeypress="enterInteger()" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="25%" >
              <div align="right">Clase :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" name="E01FIXCLS" <% if (apBasic.getF01FIXCLS().equals("Y")) out.print("id=\"txtchanged\""); %> size="5" maxlength="4" value="<%= apBasic.getE01FIXCLS().trim()%>" readonly>
               
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
            <td nowrap width="25%" >
              <div align="right">Comentarios :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01FIXRMK" <% if (apBasic.getF01FIXRMK().equals("Y")) out.print("id=\"txtchanged\""); %> size="30" maxlength="25" value="<%= apBasic.getE01FIXRMK().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Valor Residual :</div>
            </td>
            <td nowrap width="23%" >
              <input type="text" name="E01FIXRVA" <% if (apBasic.getF01FIXRVA().equals("Y")) out.print("id=\"txtchanged\""); %> size="15" maxlength="15" value="<%= apBasic.getE01FIXRVA().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
            <td nowrap width="25%" > 
              <div align="right">N&uacute;mero del Proveedor :</div>
            </td>
            <td nowrap width="27%" >
              <input type="text" name="E01FIXPIN" <% if (apBasic.getF01FIXPIN().equals("Y")) out.print("id=\"txtchanged\""); %> size="10" maxlength="9" value="<%= apBasic.getE01FIXPIN().trim()%>" readonly>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0"  > 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%> <% if(E01FLGREF.equals("1")||E01FLGREF.equals("2") || E01FLGREF.equals("3")) { %> 
  <h4>Refinanciamiento<% if(E01FLGREF.equals("1")) { %> </h4>
  <h5 align="center">Refinanciamiento de Intereses</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">Ciclo de Refinanciamiento :</div>
            </td>
            <td nowrap width="60%"> <%= apBasic.getE01DEAXRC().trim()%> </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">Fecha Pr&oacute;ximo Refinanciamiento :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="E01DEAXR1" <% if (apBasic.getF01DEAXR1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAXR1().trim()%>" readonly>
              <input type="text" name="E01DEAXR2" <% if (apBasic.getF01DEAXR2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAXR2().trim()%>" readonly>
              <input type="text" name="E01DEAXR3" <% if (apBasic.getF01DEAXR3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEAXR3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" > 
              <div align="right">Refinanciar hasta :</div>
            </td>
            <td nowrap width="60%" > 
              <input type="text" name="E01DEAPC1" <% if (apBasic.getF01DEAPC1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPC1().trim()%>" readonly>
              <input type="text" name="E01DEAPC2" <% if (apBasic.getF01DEAPC2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPC2().trim()%>" readonly>
              <input type="text" name="E01DEAPC3" <% if (apBasic.getF01DEAPC3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="1" value="<%= apBasic.getE01DEAPC3().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <% if(E01FLGREF.equals("2")) { %> 
  <h5 align="center">Refinanciamiento Parcial Cuota con Incremento al mismo Cr&eacute;dito</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">Monto a Pagar :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="REFPAG01" size="15" maxlength="15" value="<%= apBasic.getE01REFPAG().trim()%>" onKeypress="enterDecimal()" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <% if(E01FLGREF.equals("3")) { %>
  <h5 align="center">Refinanciamiento Parcial Cuota con Incremento a otro Cr&eacute;dito</h5>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark">
            <td nowrap width="40%"> 
              <div align="right">Monto a Pagar :</div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="REFPAG11" size="15" maxlength="15" value="<%= apBasic.getE01REFPAG().trim()%>" onKeypress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">No. de Cr&eacute;dito para Aplicar Refinanciamiento 
                : </div>
            </td>
            <td nowrap width="60%"> 
              <input type="text" name="E01REFACC" <% if (apBasic.getF01REFACC().equals("Y")) out.print("id=\"txtchanged\""); %> size="12" maxlength="12" value="<%= apBasic.getE01REFACC().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%>
  <%}%>
  <% if(E01FLGPAY.equals("Y")) { %>
  <h4>Proyecci&oacute;n de Cuota a Pagar</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Monto Pago Final :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEABAP" <% if (apBasic.getF01DEABAP().equals("Y")) out.print("id=\"txtchanged\""); %> size="15" maxlength="15" value="<%= apBasic.getE01DEABAP().trim()%>" onKeypress="enterDecimal()" readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Fecha Pago Final :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEABA1" <% if (apBasic.getF01DEABA1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEABA1().trim()%>" readonly>
              <input type="text" name="E01DEABA2" <% if (apBasic.getF01DEABA2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEABA2().trim()%>" readonly>
              <input type="text" name="E01DEABA3" <% if (apBasic.getF01DEABA3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEABA3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">% Incremento Pago :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DEAPAP" <% if (apBasic.getF01DEAPAP().equals("Y")) out.print("id=\"txtchanged\""); %> size="10" maxlength="9" value="<%= apBasic.getE01DEAPAP().trim()%>" onKeypress="enterDecimal()" readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">Factor :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E01DEA2TC" <% if (apBasic.getF01DEA2TC().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="1" value="<%= apBasic.getE01DEAOAM().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Ciclo Pr&oacute;ximo Incremento Pago : </div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DEAPCU" <% if (apBasic.getF01DEAPCU().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DEAPCU().trim()%>" onKeypress="enterInteger()" readonly>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Fecha :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DEALS1" <% if (apBasic.getF01DEALS1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEALS1().trim()%>" readonly>
              <input type="text" name="E01DEALS2" <% if (apBasic.getF01DEALS2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEALS2().trim()%>" readonly>
              <input type="text" name="E01DEALS3" <% if (apBasic.getF01DEALS3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DEALS3().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="25%" > 
              <div align="right">Abono a Capital :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLCABA" <% if (apBasic.getF01DLCABA().equals("Y")) out.print("id=\"txtchanged\""); %> size="14" maxlength="13" value="<%= apBasic.getE01DLCABA().trim()%>" onKeypress="enterDecimal()" readonly>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Factor :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DLCABF" <% if (apBasic.getF01DLCABF().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="1" value="<%= apBasic.getE01DLCABF().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" > 
              <div align="right">Ciclo Pr&oacute;ximo Abono :</div>
            </td>
            <td nowrap width="23%" > 
              <input type="text" name="E01DLCABC" <% if (apBasic.getF01DLCABC().equals("Y")) out.print("id=\"txtchanged\""); %> size="4" maxlength="3" value="<%= apBasic.getE01DLCABC().trim()%>" onKeypress="enterInteger()" readonly>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Fecha :</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DLCAB1" <% if (apBasic.getF01DLCAB1().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCAB1().trim()%>" readonly>
              <input type="text" name="E01DLCAB2" <% if (apBasic.getF01DLCAB2().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCAB2().trim()%>" readonly>
              <input type="text" name="E01DLCAB3" <% if (apBasic.getF01DLCAB3().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= apBasic.getE01DLCAB3().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <%}%> 
  
  <% if(datapro.eibs.master.JSEIBSProp.getShowCollateralList()) { %>
  <h4>Lista de Garant&iacute;as</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="center">Garant&iacute;a No. </div>
            </td>
            <td nowrap width="23%"> 
              <div align="center">Tipo de Garant&iacute;a</div>
            </td>
            <td nowrap width="25%"> 
              <div align="center">Moneda</div>
            </td>
            <td nowrap width="27%"> 
              <div align="center">Monto </div>
            </td>
          </tr>
          <%
                coll.initRow();
                while (coll.getNextRow()) {
                    if (coll.getFlag().equals("")) {
                    		//out.println(coll.getRecord());
	      %> 
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="center"><%= coll.getRecord(0) %></div>
            </td>
            <td nowrap width="23%"> 
              <div align="center"><%= coll.getRecord(1) %></div>
            </td>
            <td nowrap width="25%"> 
              <div align="center"><%= coll.getRecord(2) %></div>
            </td>
            <td nowrap width="27%"> 
              <div align="center"><%= Util.formatCCY(coll.getRecord(3)) %></div>
            </td>
          </tr>
          <%
                    }
                }
    %> 
        </table>
      </td>
    </tr>
  </table>
  <%}%> 

<% if (apBasic.getH01FLGMAS().trim().equals("N")) {%>

<h4>Desembolso</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Monto del Pr&eacute;stamo :</b></div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" id="txtright" name="E01DEAOAM" size="15" maxlength="15" value="<%= apBasic.getE01DEAOAM().trim()%>" onKeypress="enterDecimal()" onChange="setRecalculate()" readonly>
            </td>
          </tr>
          <%
          if (!tax.getNoResult()){ %> <%
                tax.initRow();
                while (tax.getNextRow()) {
                    if (tax.getFlag().equals("")) {
	      %> 
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><%= tax.getRecord(10) %> : </div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E02DEDAMTT<%=tax.getCurrentRow()%>" size="15" maxlength="15" value="<%= tax.getRecord(2) %>" onKeypress="enterDecimal()" readonly>
            </td>
            <td nowrap> </td>
          </tr>
          <%
                    }
                }
           }
    	  %> <%
          if (!com.getNoResult()){ %> <%
                com.initRow();
                while (com.getNextRow()) {
                    if (com.getFlag().equals("")) {
	      %> 
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><%= com.getRecord(10) %> : </div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E02DEDAMTC<%=com.getCurrentRow()%>" size="15" maxlength="15" value="<%= com.getRecord(2) %>" onKeypress="enterDecimal()" readonly>
            </td>
            <td nowrap> </td>
          </tr>
          <%
                    }
                }
           }
    	  %> <%
          if (!ins.getNoResult()){ %> <%
                ins.initRow();
                while (ins.getNextRow()) {
                    if (ins.getFlag().equals("")) {
	      %> 
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><%= ins.getRecord(10) %> : </div>
            </td>
            <td nowrap colspan="2"> 
              <input type="text" id="txtright" name="E02DEDAMTI<%=ins.getCurrentRow()%>" size="15" maxlength="15" value="<%= ins.getRecord(2) %>" onKeypress="enterDecimal()" readonly>
              <% if (ins.getRecord(22).trim().equals("3")) {%> Base : 
              <input type="text" id="txtright" name="E02BSEAMTI<%=ins.getCurrentRow()%>" size="15" maxlength="15" value="<%= ins.getRecord(0) %>" onKeypress="enterDecimal()" readonly>
              <% }%> D&eacute;bito a : 
              <input type="radio" name="E02DLIPBCI<%=ins.getCurrentRow()%>" value="1" <% if (!(ins.getRecord(21).trim().equals("2"))) out.print(" checked");%> disabled>
              Cliente 
              <input type="radio" name="E02DLIPBCI<%=ins.getCurrentRow()%>" value="2" <% if (ins.getRecord(21).trim().equals("2")) out.print(" checked");%> disabled>
              Banco </td>
          </tr>
          <%
                    }
                }
           }
    	  %> <%
          if (!iva.getNoResult()){ %> <%
                iva.initRow();
                while (iva.getNextRow()) {
                    if (iva.getFlag().equals("")) {
	      %> 
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right"><%= iva.getRecord(10) %> : </div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E02DEDAMTV<%=iva.getCurrentRow()%>" size="15" maxlength="15" value="<%= iva.getRecord(2) %>" onKeypress="enterDecimal()" readonly>
            </td>
            <td nowrap> </td>
          </tr>
          <%
                    }
                }
           }
    	  %> 
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Saldo Neto :</b></div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01BALNET" size="15" maxlength="15" value="<%= apBasic.getE01BALNET().trim()%>" onKeypress="enterDecimal()" onChange="setRecalculate()" readonly>
            </td>
            <td nowrap>&nbsp; </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap>
              <div align="right"><b>Recalcular</b> <b>Nuevamente :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="checkbox" name="RECALC" value="" onClick="UpdateFlag(this.checked)" <% if (apBasic.getE01RCLFLC().trim().equals("X")) out.print(" checked disabled");%> disabled>
                <b> </b></div>
            </td>
            <td nowrap>&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

<% }%>

  <p align="center">
  
  </p>
  </form>
</body>
</html>

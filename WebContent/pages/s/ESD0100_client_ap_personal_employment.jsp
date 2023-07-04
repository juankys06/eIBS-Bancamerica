<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Datos de Empleo</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "employment" class= "datapro.eibs.beans.ESD008006Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<SCRIPT Language="Javascript">

   builtNewMenu(client_ap_personal_opt);
   initMenu();
</SCRIPT>

</head>

<body bgcolor="#FFFFFF">


<h3 align="center">Datos del Empleo <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_ap_personal_employment, ESD0100"></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="6">
   
<table class="tableinfo">
  <tr > 
    <td nowrap> 
      <table cellspacing="0" cellpadding="2" width="100%" class="tbhead">
        <tr>
          <td nowrap align="right"> Cliente :</td>
          <td nowrap align="left"><%= userPO.getHeader1()%></td>
          <td nowrap align="right"> ID. :</td>
          <td nowrap align="left"><%= userPO.getHeader2()%></td>
          <td nowrap align="right"> Nombre :</td>
          <td nowrap align="left"><%= userPO.getHeader3()%></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<br>
<table class="tableinfo" >
    <tr > 
      <td wrap >
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trclear"> 
            <td wrap colspan="2"> 
              <div align="right">Nombre de la Empresa :</div>
            </td>
            <td wrap colspan="2"> 
              <input type="text" name="E06CP1" <% if (employment.getF06CP1().equals("Y")) out.print("id=\"txtchanged\""); %> size="31" maxlength="30" value="<%= employment.getE06CP1().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td wrap colspan="2"> 
              <div align="right">Direcci&oacute;n :</div>
            </td>
            <td wrap colspan="2"> 
              <input type="text" name="E06CP2" size="31" <% if (employment.getF06CP2().equals("Y")) out.print("id=\"txtchanged\""); %> maxlength="30" value="<%= employment.getE06CP2().trim()%>">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo Empresa :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E06EPT" size="6" <% if (employment.getF06EPT().equals("Y")) out.print("id=\"txtchanged\""); %> maxlength="4" value="<%= employment.getE06EPT().trim()%>">
              <%-- <input type="text" name="D06EPT" size="30" <% if (employment.getF06EPT().equals("Y")) out.print("id=\"txtchanged\""); %> maxlength="30" value="<%= employment.getD06EPT().trim()%>"> --%>
              </td>
            <td nowrap > 
              <div align="right">Tipo de Negocio : </div>
            </td>
            <td nowrap > 
              <input type="text" name="E06UC5" <% if (employment.getF06UC5().equals("Y")) out.print("id=\"txtchanged\""); %> value="<%= employment.getE06UC5().trim()%>" size="5" maxlength="4">
              <%-- <input type="text" name="D06UC5" <% if (employment.getF06UC5().equals("Y")) out.print("id=\"txtchanged\""); %> value="<%= employment.getD06UC5().trim()%>" size="30" maxlength="30"> --%>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">ID. Empresa :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E06RUC" <% if (employment.getF06RUC().equals("Y")) out.print("id=\"txtchanged\""); %> size="16" maxlength="15" value="<%= employment.getE06RUC().trim()%>">
            </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap >              
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Cargo :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E06FC3" <% if (employment.getF06FC3().equals("Y")) out.print("id=\"txtchanged\""); %> size="5" maxlength="4" value="<%= employment.getE06FC3().trim()%>">
              <%-- <input type="text" name="D06FC3" <% if (employment.getF06FC3().equals("Y")) out.print("id=\"txtchanged\""); %> size="30" maxlength="30" value="<%= employment.getD06FC3().trim()%>"> --%>
            </td>
            <td nowrap > 
              <div align="right">Fecha Ingreso Empleo Acual :</div>
            </td>
            <td nowrap >
             <input type="text" name="E06SWM" <% if (employment.getF06SWM().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06SWM().trim()%>">
             <input type="text" name="E06SWD" <% if (employment.getF06SWD().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06SWD().trim()%>">
             <input type="text" name="E06SWY" <% if (employment.getF06SWY().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06SWY().trim()%>">             
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap>  
              <div align="right">Tiempo de Empleo :</div>
            </td>
            <td nowrap > 
              <input type="text" name="E06TIM" <% if (employment.getF06TIM().equals("Y")) out.print("id=\"txtchanged\""); %> size="6" maxlength="2" value="<%= employment.getE06TIM().trim()%>">
            </td>
            <td nowrap > 
              <div align="right">Fecha Ingreso Empleo Anterior :</div>
            </td>
            <td nowrap> 
             <input type="text" name="E06IA1" <% if (employment.getF06IA1().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06IA1().trim()%>"  >
             <input type="text" name="E06IA2" <% if (employment.getF06IA2().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06IA2().trim()%>"  >
             <input type="text" name="E06IA3" <% if (employment.getF06IA3().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06IA3().trim()%>"  >                          
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Tipo de Contrato :</div>
            </td>
            <td nowrap >
              <INPUT type="text" name="E06NEM" <% if (employment.getF06NEM().equals("Y")) out.print("id=\"txtchanged\""); %> size="6" maxlength="5" value="<% if (employment.getE06NEM().trim().equals("1")) out.print("Fijo"); 
               else if (employment.getE06NEM().trim().equals("2")) out.print("Indefinido"); %>">
            </td>
            <td nowrap > 
              <div align="right">Fecha Termino Empleo Anterior :</div>
            </td>
            <td nowrap > 
             <input type="text" name="E06FA1" <% if (employment.getF06FA1().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06FA1().trim()%>"  >
             <input type="text" name="E06FA2" <% if (employment.getF06FA2().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06FA2().trim()%>"  >
             <input type="text" name="E06FA3" <% if (employment.getF06FA3().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06FA3().trim()%>"  >                          
            </td>
          </tr>
          
        </table>
      </td>
    </tr>
  </table>
    
  <br>
  <table class="tableinfo" >
    <tr > 
      <td nowrap > 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Salario Actual :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E06AMT" <% if (employment.getF06AMT().equals("Y")) out.print("id=\"txtchanged\""); %> size="16" maxlength="15" value="<%= employment.getE06AMT().trim()%>"  >
            </td>
            <td nowrap>
              <div align="right">Fecha Eval. Salario :</div>
            </td>
            <td nowrap>
             <INPUT type="text" name="E06LSM" <% if (employment.getF06LSM().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06LSM().trim()%>"  >
             <INPUT type="text" name="E06LSD" <% if (employment.getF06LSD().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06LSD().trim()%>"  >
             <INPUT type="text" name="E06LSY" <% if (employment.getF06LSY().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06LSY().trim()%>"  > </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Tipo de Salario :</div>
            </td>
            <td nowrap >
               <SELECT name="E06SAT">
                       <OPTION value=" " <% if (!(employment.getE06SAT().equals("W") || employment.getE06SAT().equals("M") || employment.getE06SAT().equals("B")|| employment.getE06SAT().equals("S") || employment.getE06SAT().equals("Y"))) out.print("selected");%>></OPTION>
                       <OPTION value="W" <% if (employment.getE06SAT().equals("W")) out.print("selected"); %>="">Semanal</OPTION>
					   <OPTION value="B" <% if (employment.getE06SAT().equals("B")) out.print("selected"); %>="">Quincenal</OPTION>
                       <OPTION value="M" <% if (employment.getE06SAT().equals("M")) out.print("selected"); %>="">Mensual</OPTION>
                       <OPTION value="S" <% if (employment.getE06SAT().equals("S")) out.print("selected"); %>="">Semestral</OPTION>
                       <OPTION value="Y" <% if (employment.getE06SAT().equals("Y")) out.print("selected"); %>="">Anual</OPTION>
               </SELECT>
            </td>
            <td nowrap > 
              <div align="right">Día de Pago en el Mes :</div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E06MPA" <% if (employment.getF06MPA().equals("Y")) out.print("id=\"txtchanged\""); %> size="2" maxlength="2" value="<%= employment.getE06MPA().trim()%>"  >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Patrimonio Neto :</div>
            </td>
            <td nowrap ><INPUT type="text" name="E06CAP" <% if (employment.getF06CAP().equals("Y")) out.print("id=\"txtchanged\""); %> size="16" maxlength="15" value="<%= employment.getE06CAP().trim()%>"  > </td>
            <td nowrap > 
              <div align="right">Fecha Patrimonio :</div>
            </td>
            <td nowrap >
             <INPUT type="text" name="E06DP1" <% if (employment.getF06DP1().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06DP1().trim()%>"  >
             <INPUT type="text" name="E06DP2" <% if (employment.getF06DP2().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06DP2().trim()%>"  >
             <INPUT type="text" name="E06DP3" <% if (employment.getF06DP3().equals("Y")) out.print("id=\"txtchanged\""); %> size="3" maxlength="2" value="<%= employment.getE06DP3().trim()%>"  >
            </td>
          </tr>          
          <tr id="trclear"> 
            <td nowrap width="25%"> 
              <div align="right">Ingresos :</div>
            </td>
            <td nowrap width="21%"><INPUT type="text" name="E06CAI" <% if (employment.getF06CAI().equals("Y")) out.print("id=\"txtchanged\""); %>="" size="16" maxlength="15" value="<%= employment.getE06CAI().trim()%>"  >
            </td>
            <td nowrap width="27%"> 
              <div align="right">Origen de Ingresos :</div>
            </td>
            <td nowrap width="27%"> 
              <input type="text" name="E06ORI" <% if (employment.getF06ORI().equals("Y")) out.print("id=\"txtchanged\""); %> value="<%= employment.getE06ORI().trim()%>" size="5" maxlength="4">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Otros Ingresos :</div>
            </td>
            <td nowrap ><INPUT type="text" name="E06CAS" <% if (employment.getF06CAS().equals("Y")) out.print("id=\"txtchanged\""); %>="" size="16" maxlength="15" value="<%= employment.getE06CAS().trim()%>"  > </td>
            <td nowrap > 
              <div align="right"></div>
            </td>
            <td nowrap > </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap > 
              <div align="right">Egreso en Educación :</div>
            </td>
            <td nowrap ><INPUT type="text" name="E06AM3" <% if (employment.getF06AM3().equals("Y")) out.print("id=\"txtchanged\""); %>="" size="16" maxlength="15" value="<%= employment.getE06AM3().trim()%>"  > </td>
            <td nowrap > 
              <div align="right">Otros Egresos :</div>
            </td>
            <td nowrap ><INPUT type="text" name="E06AM4" <% if (employment.getF06AM4().equals("Y")) out.print("id=\"txtchanged\""); %>="" size="16" maxlength="15" value="<%= employment.getE06AM4().trim()%>"  ></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap > 
              <div align="right">Tipo de Renta :</div>
            </td>
            <td nowrap ><SELECT name="E06TYR">
                            <OPTION value=" " <% if (!(employment.getE06TYR().equals("1") || employment.getE06TYR().equals("2")) ) out.print("selected");%>=""></OPTION>
                            <OPTION value="1" <% if (employment.getE06TYR().equals("1")) out.print("selected"); %>="">Fija</OPTION>
                            <OPTION value="2" <% if (employment.getE06TYR().equals("2")) out.print("selected"); %>="">Variable</OPTION>
                        </SELECT> 
            </td>
            <td nowrap > 
              <div align="right">Arriendo :</div>
            </td>
            <td nowrap ><INPUT type="text" name="E06AM2" <% if (employment.getF06AM2().equals("Y")) out.print("id=\"txtchanged\""); %>="" size="16" maxlength="15" value="<%= employment.getE06AM2().trim()%>"  > </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
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
  </form>
</body>
</html>

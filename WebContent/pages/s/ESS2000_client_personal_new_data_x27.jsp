<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Corporate User</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="cusdata" class="datapro.eibs.beans.ESS200001Message" scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="Javascript1.1">
  function SelCuentaCli(CampoDest,Campo1,Campo2,Campo3,Cliente){
     if(Cliente =="" || Cliente=="0"){
        alert("No. de Cliente debe ser Seleccionado");
        return;
     }
     GetAccByClient(CampoDest,'','','',document.forms[0].E01EUSCUN.value)
  }
  
  function GeneratePassword(){
     if(confirm("Esta seguro que desea generar una nueva contrasena para este usuario")){
        //page = webapp + "/servlet/com.datapro.eibs.internet.JSESS2000?SCREEN=425&E01EUSUSR="+document.forms[0].E01EUSUSR.value;
        page = webapp + "/pages/s/ESS2000_personal_password_generated.jsp?E01EUSUSR="+document.forms[0].E01EUSUSR.value;
        //page = webapp + "/servlet/com.datapro.eibs.internet.SilentPrintServlet";
        CenterWindow(page,600,450,1);
     }
  }
</script>
 
</head>

<body bgcolor="#FFFFFF">

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0") ;
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 %> 
 
 
<h3 align="center">Usuario Personal Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cliente_personal_new_data, ESS2000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2000" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="201">
  <INPUT TYPE=HIDDEN NAME="E01EUSCTY" VALUE="2">

  <h4>General Information</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Usuario :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" READONLY ID="MANDATORY" name="E01EUSUSR" size="35" maxlength="10" READONLY value="<%= cusdata.getE01EUSUSR().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
<%
if(userPO.getPurpose().equals("")){
%>          
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Contraseña :</div></td>
            <td nowrap width="60%" ><div align="left"> 
                <input type="text" ID="MANDATORY" name="E01EUSACP" size="10" maxlength="10" READONLY> Autogenerada</div>
                  <INPUT TYPE=HIDDEN NAME="NEWUSER" VALUE="Y">
            </td>
          </tr>
<%
}
else{
%> 
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Contraseña :</div></td>
            <td nowrap width="60%" >
               <div align="left"> 
                  <input type="text" name="E01EUSACP" readonly="readonly" size="35" maxlength="10" value="<%= cusdata.getE01EUSACP().trim()%>">
                  <a href="JavaScript:GeneratePassword()" ><img src="<%=request.getContextPath()%>/images/keygen.gif" ALT="Regenerar Password" align="absbottom" border="0"  ></a>                   
               </div>
               <INPUT TYPE=HIDDEN NAME="NEWUSER" VALUE="N">
            </td>
          </tr>
<%
}
%>                   
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Requiere Tarjeta de Vectores :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSOPR">
             <OPTION VALUE="0" <%if (cusdata.getE01EUSOPR().equals("0")) {out.print("selected"); }%>>No Requiere</OPTION>
             <OPTION VALUE="1" <%if (cusdata.getE01EUSOPR().equals("1")) {out.print("selected"); }%>>Si Requiere</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Tarjeta Vectores :</div></td>
            <td nowrap width="60%" ><div align="left"> 
            <input type="text" ID="MANDATORY" name="E01EUSOPP" size="35" maxlength="10" value="<%= cusdata.getE01EUSOPP().trim()%>">
             </div>
           </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Primaria :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSACC" size="35" maxlength="9" readonly value="<%= cusdata.getE01EUSACC().trim()%>" onkeypress="enterInteger()">
                                    <!-- a href="javascript:GetAccount('E01EUSACC','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a -->
                                    <a href="javascript:SelCuentaCli('E01EUSACC','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td> 
          </tr>

          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">No. Cliente :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSCUN" size="35" maxlength="9" value="<%= cusdata.getE01EUSCUN().trim()%>" <%= !cusdata.getE01EUSCUN().trim().equals("0")?"readonly":"" %>>
            <% if(cusdata.getE01EUSCUN().trim().equals("0")){ %>
                                    <a href="javascript:GetCustomerDescId('E01EUSCUN','E01EUSCON','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  >
  			<% } %>
             </div>
           </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Primaria :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSACC" size="35" maxlength="9" readonly value="<%= cusdata.getE01EUSACC().trim()%>" onkeypress="enterInteger()">
                                    <!-- a href="javascript:GetAccount('E01EUSACC','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a -->
                                    <a href="javascript:SelCuentaCli('E01EUSACC','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td> 
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Tipo Acumulación :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT NAME="E01EUSMXT">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSMXT().trim().equals("1")) {out.print("selected"); }%>>DIARIO</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSMXT().trim().equals("2")) {out.print("selected"); }%>>SEMANAL</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Permite Transferencias Internas :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT NAME="E01EUSTRA">
             <OPTION VALUE="Y" <%if (cusdata.getE01EUSTRA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             <OPTION VALUE="N" <%if (cusdata.getE01EUSTRA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Permite Transferencias a Terceros :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSTPT">
             <OPTION VALUE="Y" <%if (cusdata.getE01EUSTPT().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             <OPTION VALUE="N" <%if (cusdata.getE01EUSTPT().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Permite Transferencias Externas :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSEXT">
             <OPTION VALUE="Y" <%if (cusdata.getE01EUSEXT().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             <OPTION VALUE="N" <%if (cusdata.getE01EUSEXT().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Permite Pagos a Cuentas  :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSBPA">
             <OPTION VALUE="Y" <%if (cusdata.getE01EUSBPA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             <OPTION VALUE="N" <%if (cusdata.getE01EUSBPA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Permite Cartas de Créditos :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSLCA">
             <OPTION VALUE="Y" <%if (cusdata.getE01EUSLCA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             <OPTION VALUE="N" <%if (cusdata.getE01EUSLCA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
           <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Permite Pagos Préstamos :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSLNA">
             <OPTION VALUE="Y" <%if (cusdata.getE01EUSLNA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             <OPTION VALUE="N" <%if (cusdata.getE01EUSLNA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Permite Pagos de Tarjetas :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSCCP">
             <OPTION VALUE="Y" <%if (cusdata.getE01EUSCCP().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             <OPTION VALUE="N" <%if (cusdata.getE01EUSCCP().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Monto Máximo Transferencias Internas :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSMAX" size="35" maxlength="13" value="<%= cusdata.getE01EUSMAX().trim()%>"></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Monto Máximo Acumulado Transferencias Internas :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSAMX" size="35" maxlength="13" value="<%= cusdata.getE01EUSAMX().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Monto Máximo Transferencias a Terceros :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSTTL" size="35" maxlength="13" value="<%= cusdata.getE01EUSTTL().trim()%>"></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Monto Máximo Acumulado Transferencias a Terceros :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSTAL" size="35" maxlength="13" value="<%= cusdata.getE01EUSTAL().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Monto Máximo Transferencias Externas :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSETL" size="35" maxlength="13" value="<%= cusdata.getE01EUSETL().trim()%>"></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Monto Máximo Acumulado Transferencias Externas :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSEAL" size="35" maxlength="13" value="<%= cusdata.getE01EUSEAL().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Persona Responsable :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSCON" size="35" maxlength="30" value="<%= cusdata.getE01EUSCON().trim()%>"></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Correo Electrónico :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSIAD" size="35" maxlength="45" value="<%= cusdata.getE01EUSIAD().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Tipo Confirmación :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSCFM">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSCFM().equals("1")) {out.print("selected"); }%>>NO CONFIRMATION REQUERIDA</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSCFM().equals("2")) {out.print("selected"); }%>>CONFIRMACION TELEFONICA</OPTION>
             <OPTION VALUE="3" <%if (cusdata.getE01EUSCFM().equals("3")) {out.print("selected"); }%>>AVISO POR INTERNET</OPTION>
             <OPTION VALUE="4" <%if (cusdata.getE01EUSCFM().equals("4")) {out.print("selected"); }%>>E-MAIL AL CLIENTE</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Incluir/Excluir Cuentas :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSIEF">
             <OPTION VALUE=" " <%if (cusdata.getE01EUSIEF().equals(""))  {out.print("selected"); }%>>NO</OPTION>
             <OPTION VALUE="I" <%if (cusdata.getE01EUSIEF().equals("I")) {out.print("selected"); }%>>INCLUIR</OPTION>
             <OPTION VALUE="E" <%if (cusdata.getE01EUSIEF().equals("E")) {out.print("selected"); }%>>EXCLUIR</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #1 :</div></td>
            <td nowrap width="60%" >
               <div align="left">
                 <input type="text" name="E01EUSA01" size="15" maxlength="12" value="<%= cusdata.getE01EUSA01().trim()%>" >
                 <a href="javascript:SelCuentaCli('E01EUSA01','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
               </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #2 :</div></td>
            <td nowrap width="60%" ><div align="left">
               <input type="text" name="E01EUSA02" size="15" maxlength="12" value="<%= cusdata.getE01EUSA02().trim()%>" >
               <a href="javascript:SelCuentaCli('E01EUSA02','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>                             
               </div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #3 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSA03" size="15" maxlength="12" value="<%= cusdata.getE01EUSA03().trim()%>" >
			<a href="javascript:SelCuentaCli('E01EUSA03','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>                                         
            </div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #4 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSA04" size="15" maxlength="12" value="<%= cusdata.getE01EUSA04().trim()%>" >
            <a href="javascript:SelCuentaCli('E01EUSA04','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>                             
            </div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #5 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSA05" size="15" maxlength="12" value="<%= cusdata.getE01EUSA05().trim()%>" >
            <a href="javascript:SelCuentaCli('E01EUSA05','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>                             
            </div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #6 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSA06" size="15" maxlength="12" value="<%= cusdata.getE01EUSA06().trim()%>" >
            <a href="javascript:SelCuentaCli('E01EUSA06','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>                             
            </div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #7 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSA07" size="15" maxlength="12" value="<%= cusdata.getE01EUSA07().trim()%>" >
            <a href="javascript:SelCuentaCli('E01EUSA07','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>                             
            </div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #8 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSA08" size="15" maxlength="12" value="<%= cusdata.getE01EUSA08().trim()%>" >
            <a href="javascript:SelCuentaCli('E01EUSA08','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>                             
            </div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #9 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSA09" size="15" maxlength="12" value="<%= cusdata.getE01EUSA09().trim()%>" >
            <a href="javascript:SelCuentaCli('E01EUSA09','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>                             
            </div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #10 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSA10" size="15" maxlength="12" value="<%= cusdata.getE01EUSA10().trim()%>" >
            <a href="javascript:SelCuentaCli('E01EUSA10','','','',document.forms[0].E01EUSCUN.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>                             
            </div></td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    
  <p align="center"> <input id="EIBSBTN" type=submit name="Submit" value="Enviar"> </p>
</form>
</body>
</html>

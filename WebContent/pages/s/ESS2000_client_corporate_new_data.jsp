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


<h3 align="center">Internet Banking Corporate User<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cliente_corporate_new_data, ESS2000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2000" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="101">
  <INPUT TYPE=HIDDEN NAME="E01EUSCTY" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="E01EUSCFM" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="E01EUSTRA" VALUE="Y">
  <INPUT TYPE=HIDDEN NAME="E01EUSTPT" VALUE="Y">
  <INPUT TYPE=HIDDEN NAME="E01EUSEXT" VALUE="Y">
  <INPUT TYPE=HIDDEN NAME="E01EUSOPR" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="E01EUSSTS" VALUE="<%= cusdata.getE01EUSSTS() %>">
  <INPUT TYPE=HIDDEN NAME="H01FLGWK2" VALUE="<%= cusdata.getH01FLGWK2() %>">  
  
    <p align="right"><font color="RED"><B> <%= cusdata.getH01FLGWK2().equals("Y")?"NEW USER":"USER MAINTENANCE"   %></B></font> </p>  
  <table class="tableinfo">
      <tr >
        <td nowrap>

        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Entidad  :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSUSR" size="35" maxlength="10" READONLY value="<%= cusdata.getE01EUSUSR().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">ID de Administrador :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" <% if(userPO.getPurpose().equals("MAINT")){ %> READONLY <% } %> ID="MANDATORY" name="E01EUSENT" size="35" maxlength="10" value="<%= cusdata.getE01EUSENT().trim()%>" >
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
<%
if(userPO.getPurpose().equals("")){
%>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Password Administrador :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="password" ID="MANDATORY" name="E01EUSACP" size="35" maxlength="10" value="<%= cusdata.getE01EUSACP().trim()%>" READONLY>Autogenerado
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div>
                                    <INPUT TYPE=HIDDEN NAME="NEWUSER" VALUE="Y"> 
            </td>
          </tr>
<%
}
else{
%>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right"> Password Administrador :</div></td>
            <td nowrap width="60%" ><div align="left"> 
                                        <input type="password" name="E01EUSACP" size="35" maxlength="10" value="<%= cusdata.getE01EUSACP().trim()%>" READONLY>
                                        <a href="JavaScript:GeneratePassword()" ><img src="<%=request.getContextPath()%>/images/keygen.gif" ALT="Regenerar Password" align="absbottom" border="0"  ></a> 
                                    </div>
            <INPUT TYPE=HIDDEN NAME="NEWUSER" VALUE="N"> 
            </td>
          </tr>
<%
}
%>
          <!-- tr id="teclear">
            <td nowrap width="40%" ><div align="right">Administrator User Status :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSSTS">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSSTS().equals("1")) {out.print("selected"); }%>>ACTIVE</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSSTS().equals("2")) {out.print("selected"); }%>>INACTIVE</OPTION>
             <OPTION VALUE="3" <%if (cusdata.getE01EUSSTS().equals("3")) {out.print("selected"); }%>>SUSPENDED</OPTION>
             <OPTION VALUE="4" <%if (cusdata.getE01EUSSTS().equals("4")) {out.print("selected"); }%>>PENDING ACTIVATION</OPTION>
             </SELECT></div></td>
          </tr -->
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right"> Numero de Cliente :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSCUN" size="35" maxlength="9" value="<%= cusdata.getE01EUSCUN().trim()%>">
                                    <a href="javascript:GetCustomerDescId('E01EUSCUN','E01EUSCON','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0" ></a>
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Cuenta Primaria :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSACC" size="35" maxlength="9" value="<%= cusdata.getE01EUSACC().trim()%>" onkeypress="enterInteger()">
                                    <a href="javascript:GetAccount('E01EUSACC','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0" ></a>
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Tipo Maximo Acumulado :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT NAME="E01EUSMXT">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSMXT().equals("1")) {out.print("selected"); }%>>DAILY MAXIMUN AMOUNT PERMITTED</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSMXT().equals("2")) {out.print("selected"); }%>>WEEKLY MAXMIMUN AMOUNT PERMITTED</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" colspan="2" align="center">
				   <TABLE width="70%">
				      <TR>
				         <TD colspan="4" align="center" ><b>PERMISOS</b></TD>
				      </TR>
					  <tr align="center" id="trdark">
					     <TD>Transaccion</TD>
					     <TD>Limite por Transaccion</TD>
					     <TD>Limite Acumulado</TD>
					  </tr>
					  <tr align="center">
					     <TD align="right">Transferencia Interna :</TD>
					     <TD><input type="text" name="E01EUSMAX" size="25" maxlength="13" value="<%= cusdata.getE01EUSMAX().trim()%>"></TD>
					     <TD><input type="text" name="E01EUSAMX" size="25" maxlength="13" value="<%= cusdata.getE01EUSAMX().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Transferencia a Terceros :</TD>
					     <TD><input type="text" name="E01EUSTTL" size="25" maxlength="13" value="<%= cusdata.getE01EUSTTL().trim()%>"></TD>
					     <TD><input type="text" name="E01EUSTAL" size="25" maxlength="13" value="<%= cusdata.getE01EUSTAL().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Transferencia Externas :</TD>
					     <TD><input type="text" name="E01EUSETL" size="25" maxlength="13" value="<%= cusdata.getE01EUSETL().trim()%>"></TD>
					     <TD><input type="text" name="E01EUSEAL" size="25" maxlength="13" value="<%= cusdata.getE01EUSEAL().trim()%>"></TD>
					  </tr>					  
				   </TABLE>            
            </td>
          </tr>                   
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Persona Responsable :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSCON" size="35" maxlength="30" value="<%= cusdata.getE01EUSCON().trim()%>"></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">E-Mail  :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSIAD" size="35" maxlength="45" value="<%= cusdata.getE01EUSIAD().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
        </table>
        </td>
      </tr>
    </table>

  <p align="center"> <input id="EIBSBTN" type=submit name="Submit" value="Submit"> </p>
</form>
</body>
</html>

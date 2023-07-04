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


<h3 align="center">Usuario Personal Banca por  Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cliente_corporate_new_data, ESS2000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2000" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="201">
  <INPUT TYPE=HIDDEN NAME="E01EUSCTY" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="E01EUSSTS" VALUE="<%= cusdata.getE01EUSSTS() %>">
  <INPUT TYPE=HIDDEN NAME="H01FLGWK2" VALUE="<%= cusdata.getH01FLGWK2() %>">
   
    <p align="right"><font color="RED"><B> <%= cusdata.getH01FLGWK2().equals("Y")?"NUEVO USUARIO":"MANTENIMIENTO DE USUARIO"   %></B></font> </p>  
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
if(cusdata.getH01FLGWK2().equals("Y")){
%>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right"> Password :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="password" ID="MANDATORY" name="E01EUSACP" size="35" maxlength="10" value="<%= cusdata.getE01EUSACP().trim()%>" READONLY>Autogenerado
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div>
                                    <INPUT TYPE=HIDDEN NAME="NEWUSER" VALUE="Y">
            </td>
          </tr>
<%
}
else{
%>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right"> Password :</div></td>
            <td nowrap width="60%" >
              <div align="left">
                <input type="password" name="E01EUSACP" size="35" maxlength="10" value="<%= cusdata.getE01EUSACP().trim()%>">
                <a href="JavaScript:GeneratePassword()" ><img src="<%=request.getContextPath()%>/images/keygen.gif" ALT="Regenerar Password" align="absbottom" border="0"  ></a> 
              </div>
              <INPUT TYPE=HIDDEN NAME="NEWUSER" VALUE="N">
            </td>
          </tr>
<%
}
%>
          <!-- tr id="trdark">
            <td nowrap width="40%" ><div align="right">User Status :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSSTS">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSSTS().equals("1")) {out.print("selected"); }%>>ACTIVE</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSSTS().equals("2")) {out.print("selected"); }%>>INACTIVE</OPTION>
             <OPTION VALUE="3" <%if (cusdata.getE01EUSSTS().equals("3")) {out.print("selected"); }%>>SUSPENDED</OPTION>
             <OPTION VALUE="4" <%if (cusdata.getE01EUSSTS().equals("4")) {out.print("selected"); }%>>PENDING ACTIVATION</OPTION>
             </SELECT></div></td>
          </tr -->
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Numero de Cliente :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSCUN" size="35" maxlength="9" value="<%= cusdata.getE01EUSCUN().trim()%>">
                                    <a href="javascript:GetCustomerDescId('E01EUSCUN','E01EUSCON','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0" ></a>
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Cuenta Primaria :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSACC" size="35" maxlength="12" value="<%= cusdata.getE01EUSACC().trim()%>" onkeypress="enterInteger()">
                                    <a href="javascript:GetAccount('E01EUSACC','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0" ></a>
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
			<TR>
				<TD nowrap width="40%" colspan="2" align="center">
				   <TABLE width="70%">
				      <TR>
				         <TD colspan="4" align="center" ><b>PERMISOS</b></TD>
				      </TR>
					  <tr align="center" id="trdark">
					     <TD>Transaccion</TD>
					     <TD>Permitir</TD>
					     <TD>Limite por Transaccion</TD>
					     <TD>Limite Acumulado</TD>
					  </tr>
					  <tr align="center">
					     <TD align="right">Transferencia Interna :</TD>
					     <TD> <SELECT NAME="E01EUSTRA">
             					 <OPTION VALUE="N" <%if (cusdata.getE01EUSTRA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
                                 <OPTION VALUE="Y" <%if (cusdata.getE01EUSTRA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD><input type="text" name="E01EUSMAX" size="25" maxlength="13" value="<%= cusdata.getE01EUSMAX().trim()%>"></TD>
					     <TD><input type="text" name="E01EUSAMX" size="25" maxlength="13" value="<%= cusdata.getE01EUSAMX().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Transferencia a Terceros :</TD>
					     <TD> <SELECT NAME="E01EUSTPT">
             					 <OPTION VALUE="N" <%if (cusdata.getE01EUSTPT().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (cusdata.getE01EUSTPT().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD><input type="text" name="E01EUSTTL" size="25" maxlength="13" value="<%= cusdata.getE01EUSTTL().trim()%>"></TD>
					     <TD><input type="text" name="E01EUSTAL" size="25" maxlength="13" value="<%= cusdata.getE01EUSTAL().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Transferencias Externas :</TD>
					     <TD> <SELECT NAME="E01EUSEXT">
             					 <OPTION VALUE="N" <%if (cusdata.getE01EUSEXT().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (cusdata.getE01EUSEXT().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD><input type="text" name="E01EUSETL" size="25" maxlength="13" value="<%= cusdata.getE01EUSETL().trim()%>"></TD>
					     <TD><input type="text" name="E01EUSEAL" size="25" maxlength="13" value="<%= cusdata.getE01EUSEAL().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Perimitir Pagos :</TD>
					     <TD> <SELECT NAME="E01EUSBPA">
             					 <OPTION VALUE="N" <%if (cusdata.getE01EUSBPA().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (cusdata.getE01EUSBPA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD></TD>
					     <TD></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Permite Cartas de Credito :</TD>
					     <TD> <SELECT NAME="E01EUSLCA">
             					 <OPTION VALUE="N" <%if (cusdata.getE01EUSLCA().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (cusdata.getE01EUSLCA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD></TD>
					     <TD></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Permite Pagos de Prestamos : </TD>
					     <TD> <SELECT NAME="E01EUSLNA">
             					 <OPTION VALUE="N" <%if (cusdata.getE01EUSLNA().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (cusdata.getE01EUSLNA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD></TD>
					     <TD></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Perimite Pagos a Tarjeta de Credito : </TD>
					     <TD> <SELECT NAME="E01EUSCCP">
             					 <OPTION VALUE="N" <%if (cusdata.getE01EUSCCP().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (cusdata.getE01EUSCCP().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD></TD>
					     <TD></TD>
					  </tr>					  
				   </TABLE>
				</TD>
		  </TR>	
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Tipo de Maximo Acumulado :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT NAME="E01EUSMXT">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSMXT().equals("1")) {out.print("selected"); }%>>ACUMULADO DIARIO</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSMXT().equals("2")) {out.print("selected"); }%>>ACUMULADO SEMANAL</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Requiere Clave Transaccional :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT NAME="E01EUSOPR">
                                                        <OPTION VALUE="0" <%if (cusdata.getE01EUSOPR().equals("0")) {out.print("selected"); }%>>NO</OPTION>
             					 						<OPTION VALUE="1" <%if (cusdata.getE01EUSOPR().equals("1")) {out.print("selected"); }%>>SI</OPTION>
             				 							 </SELECT>             			               			
	 			</div>				 
            </td>
          </tr>  
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Clave de Transacciones :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="PASSWORD" name="E01EUSOPP" size="15" maxlength="10" value=""></div></td>
          </tr>                  
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Nombre Responsable :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSCON" size="35" maxlength="30" value="<%= cusdata.getE01EUSCON().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Cuenta de E-Mail :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSIAD" size="50" maxlength="40" value="<%= cusdata.getE01EUSIAD().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Tipo de Confirmación :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSCFM">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSCFM().equals("1")) {out.print("selected"); }%>>NO REQUERIDA</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSCFM().equals("2")) {out.print("selected"); }%>>REQUIERE LLAMADA</OPTION>
             <OPTION VALUE="3" <%if (cusdata.getE01EUSCFM().equals("3")) {out.print("selected"); }%>>CONFIRMACION DE INTERNET</OPTION>
             <OPTION VALUE="4" <%if (cusdata.getE01EUSCFM().equals("4")) {out.print("selected"); }%>>E-MAIL A CLIENTE</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Incluir/Excluir Cuentas :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT NAME="E01EUSIEF">
             <OPTION VALUE=" " <%if (cusdata.getE01EUSIEF().equals(""))  {out.print("selected"); }%>>INCLUIR TODAS</OPTION>
             <OPTION VALUE="I" <%if (cusdata.getE01EUSIEF().equals("I")) {out.print("selected"); }%>>INCLUIR SIGUIENTES CUENTAS</OPTION>
             <OPTION VALUE="E" <%if (cusdata.getE01EUSIEF().equals("E")) {out.print("selected"); }%>>EXCLUIR SIGUIENTES CUENTAS</OPTION>
             </SELECT></div></td>
          </tr>
          <TR>
           <TD colspan="2" align="CENTER">
             <TABLE width="80%">
               <TR align="CENTER">
                  <TD><input type="text" name="E01EUSA01" size="25" maxlength="12" value="<%= cusdata.getE01EUSA01().trim()%>"></TD>
                  <TD><input type="text" name="E01EUSA02" size="25" maxlength="12" value="<%= cusdata.getE01EUSA02().trim()%>"></TD>
                  <TD><input type="text" name="E01EUSA03" size="25" maxlength="12" value="<%= cusdata.getE01EUSA03().trim()%>"></TD>
                  <TD><input type="text" name="E01EUSA04" size="25" maxlength="12" value="<%= cusdata.getE01EUSA04().trim()%>"></TD>
               </TR>
               <TR align="CENTER">
                  <TD><input type="text" name="E01EUSA05" size="25" maxlength="12" value="<%= cusdata.getE01EUSA05().trim()%>"></TD>
                  <TD><input type="text" name="E01EUSA06" size="25" maxlength="12" value="<%= cusdata.getE01EUSA06().trim()%>"></TD>
                  <TD><input type="text" name="E01EUSA07" size="25" maxlength="12" value="<%= cusdata.getE01EUSA07().trim()%>"></TD>
                  <TD><input type="text" name="E01EUSA08" size="25" maxlength="12" value="<%= cusdata.getE01EUSA08().trim()%>"></TD>
               </TR>
               <TR align="CENTER">
                  <TD><input type="text" name="E01EUSA09" size="25" maxlength="12" value="<%= cusdata.getE01EUSA09().trim()%>"></TD>
                  <TD><input type="text" name="E01EUSA10" size="25" maxlength="12" value="<%= cusdata.getE01EUSA10().trim()%>"></TD>
                  <TD></TD>
                  <TD></TD>
               </TR>
             </TABLE>
           </TD>
          </TR>
        </table>
        </td>
      </tr>
    </table>
  <p align="center"> <input id="EIBSBTN" type=submit name="Submit" value="Aceptar"> </p>
</form>
</body>
</html>

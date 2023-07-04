<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Corporate User</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />
<jsp:useBean id="userDetail" class="datapro.eibs.beans.ESS204002Message" scope="session" /> 

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<style TYPE="text/css">
.textchanged
{
	font-family: "Verdana, Arial, Helvetica, sans-serif";
	font-size:8pt;
	color:#01016E;
	background-color:#FFFF80;
}
</style>
<SCRIPT language="JavaScript">
  function Action(op){
     document.forms[0].ACTION.value = op;
     document.forms[0].submit();
  }
</SCRIPT>
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


<h3 align="center">Internet Banking Personal User<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cliente_corporate_new_data, ESS2000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2040" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="X">
  <% if(userDetail.getH02FLGWK3().trim().equals("Y")){ %>
    <h4><FONT color="GREEN">Información de Usuario Nuevo</FONT></h4>
  <% }else{%>
    <h4><FONT color="RED">Información de Usuario Modificado</FONT></h4>
  <% }%>
  <table class="tableinfo">
      <tr >
        <td nowrap>

        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">ID Usuario :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="text" READONLY ID="MANDATORY" name="E02EUSUSR" class="<%= userDetail.getF02EUSUSR().equals("Y")?"textchanged":"" %>" size="35" maxlength="10" READONLY value="<%= userDetail.getE02EUSUSR().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Password :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="password" name="E02EUSACP" class="<%= userDetail.getF02EUSACP().equals("Y")?"textchanged":"" %>" size="35" maxlength="10" value="<%= userDetail.getE02EUSACP().trim()%>"></div></td>
          </tr>
          <!-- tr id="trdark">
            <td nowrap width="40%" ><div align="right">User Status :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSSTS">
             <OPTION VALUE="1" <%if (userDetail.getE02EUSSTS().equals("1")) {out.print("selected"); }%>>ACTIVE</OPTION>
             <OPTION VALUE="2" <%if (userDetail.getE02EUSSTS().equals("2")) {out.print("selected"); }%>>INACTIVE</OPTION>
             <OPTION VALUE="3" <%if (userDetail.getE02EUSSTS().equals("3")) {out.print("selected"); }%>>SUSPENDED</OPTION>
             <OPTION VALUE="4" <%if (userDetail.getE02EUSSTS().equals("4")) {out.print("selected"); }%>>PENDING ACTIVATION</OPTION>
             </SELECT></div></td>
          </tr -->
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Numero de Cliente :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="text" ID="MANDATORY" name="E02EUSCUN" class="<%= userDetail.getF02EUSCUN().equals("Y")?"textchanged":"" %>" size="35" maxlength="9" value="<%= userDetail.getE02EUSCUN().trim()%>">
                                    </div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Cuenta Primaria :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="text" ID="MANDATORY" name="E02EUSACC" class="<%= userDetail.getF02EUSACC().equals("Y")?"textchanged":"" %>" size="35" maxlength="12" value="<%= userDetail.getE02EUSACC().trim()%>" onkeypress="enterInteger()">
                                    </div></td>
          </tr>
			<TR>
				<TD nowrap width="40%" colspan="2" align="center">
				   <TABLE width="70%">
				      <TR>
				         <TD colspan="4" align="center" >PERMISOS</TD>
				      </TR>
					  <tr align="center" id="trdark">
					     <TD>Transacción</TD>
					     <TD>Permitir</TD>
					     <TD>Limite por Transacción</TD>
					     <TD> Limite Acumulado</TD>
					  </tr>
					  <tr align="center">
					     <TD align="right">Transferencia Interna:</TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSTRA" class="<%= userDetail.getF02EUSTRA().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSTRA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSTRA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD><input readonly  type="text" name="E02EUSMAX" class="<%= userDetail.getF02EUSMAX().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSMAX().trim()%>"></TD>
					     <TD><input readonly  type="text" name="E02EUSAMX" class="<%= userDetail.getF02EUSAMX().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSAMX().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Transferencia a Terceros:</TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSTPT" class="<%= userDetail.getF02EUSTPT().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSTPT().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSTPT().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD><input readonly  type="text" name="E02EUSTTL" class="<%= userDetail.getF02EUSTTL().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSTTL().trim()%>"></TD>
					     <TD><input readonly  type="text" name="E02EUSTAL" class="<%= userDetail.getF02EUSTAL().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSTAL().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Transferencia Externas:</TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSEXT" class="<%= userDetail.getF02EUSEXT().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSEXT().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSEXT().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD><input readonly  type="text" name="E02EUSETL" class="<%= userDetail.getF02EUSETL().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSETL().trim()%>"></TD>
					     <TD><input readonly  type="text" name="E02EUSEAL" class="<%= userDetail.getF02EUSEAL().equals("Y")?"textchanged":"" %>" size="25" maxlength="13" value="<%= userDetail.getE02EUSEAL().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Pagos Permitidos :</TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSBPA" class="<%= userDetail.getF02EUSBPA().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSBPA().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSBPA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD></TD>
					     <TD></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Cartas de Credito Permitidos :</TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSLCA" class="<%= userDetail.getF02EUSLCA().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSLCA().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSLCA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD></TD>
					     <TD></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Pagos de Prestmos Permitido : </TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSLNA" class="<%= userDetail.getF02EUSLNA().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSLNA().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSLNA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD></TD>
					     <TD></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Pagos a Tarjeta de Credito Permitido: </TD>
					     <TD> <SELECT DISABLED  NAME="E02EUSCCP" class="<%= userDetail.getF02EUSCCP().equals("Y")?"textchanged":"" %>">
             					 <OPTION VALUE="N" <%if (userDetail.getE02EUSCCP().equals("N")) {out.print("selected"); }%>>NO</OPTION>					     
                                 <OPTION VALUE="Y" <%if (userDetail.getE02EUSCCP().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             				  </SELECT>
             			 </TD>
					     <TD></TD>
					     <TD></TD>
					  </tr>					  
				   </TABLE>
				</TD>
		  </TR>		
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Tipo de Acumulación :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT DISABLED  NAME="E02EUSMXT" class="<%= userDetail.getF02EUSMXT().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="1" <%if (userDetail.getE02EUSMXT().equals("1")) {out.print("selected"); }%>>MAXIMO DIARIO</OPTION>
             <OPTION VALUE="2" <%if (userDetail.getE02EUSMXT().equals("2")) {out.print("selected"); }%>>MAXIMO SEMANAL</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Requiere Clave de Operaciones :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT DISABLED  NAME="E02EUSOPR" class="<%= userDetail.getF02EUSOPR().equals("Y")?"textchanged":"" %>">
                                                        <OPTION VALUE="0" <%if (userDetail.getE02EUSOPR().equals("0")) {out.print("selected"); }%>>NO</OPTION>
             					 						<OPTION VALUE="1" <%if (userDetail.getE02EUSOPR().equals("1")) {out.print("selected"); }%>>SI</OPTION>
             				 							 </SELECT>             			               			
	 			</div>				 
            </td>
          </tr>  
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Clave de Operaciones :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="PASSWORD" name="E02EUSOPP" class="<%= userDetail.getF02EUSOPP().equals("Y")?"textchanged":"" %>" size="15" maxlength="10" value="<%= userDetail.getE02EUSOPP() %>"></div></td>
          </tr>                  
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Persona Responsable :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="text" name="E02EUSCON" class="<%= userDetail.getF02EUSCON().equals("Y")?"textchanged":"" %>" size="35" maxlength="30" value="<%= userDetail.getE02EUSCON().trim()%>"></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">E-Mail  :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly  type="text" ID="MANDATORY" name="E02EUSIAD" class="<%= userDetail.getF02EUSIAD().equals("Y")?"textchanged":"" %>" size="50" maxlength="40" value="<%= userDetail.getE02EUSIAD().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Tipo de Confirmacion :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSCFM" class="<%= userDetail.getF02EUSCFM().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="1" <%if (userDetail.getE02EUSCFM().equals("1")) {out.print("selected"); }%>>NO REQUIERE</OPTION>
             <OPTION VALUE="2" <%if (userDetail.getE02EUSCFM().equals("2")) {out.print("selected"); }%>>LLAMADA REQUERIDA</OPTION>
             <OPTION VALUE="3" <%if (userDetail.getE02EUSCFM().equals("3")) {out.print("selected"); }%>>CONFIRMACION DE INTERNET</OPTION>
             <OPTION VALUE="4" <%if (userDetail.getE02EUSCFM().equals("4")) {out.print("selected"); }%>>E-MAIL A CLIENTE</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Incluir/Excluir Cuentas :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED  NAME="E02EUSIEF" class="<%= userDetail.getF02EUSIEF().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE=" " <%if (userDetail.getE02EUSIEF().equals(""))  {out.print("selected"); }%>>INCLUIR TODAS</OPTION>
             <OPTION VALUE="I" <%if (userDetail.getE02EUSIEF().equals("I")) {out.print("selected"); }%>>INCLUIR SOLO LAS SIGUIENTES CUENTAS</OPTION>
             <OPTION VALUE="E" <%if (userDetail.getE02EUSIEF().equals("E")) {out.print("selected"); }%>>EXCLUIR LAS SIGUIENTES CUENTAS</OPTION>
             </SELECT></div></td>
          </tr>
          <TR>
           <TD colspan="2" align="CENTER">
             <TABLE width="80%">
               <TR align="CENTER">
                  <TD><input readonly  type="text" name="E02EUSA01" size="25" maxlength="12" value="<%= userDetail.getE02EUSA01().trim()%>" class="<%= userDetail.getF02EUSA01().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA02" size="25" maxlength="12" value="<%= userDetail.getE02EUSA02().trim()%>" class="<%= userDetail.getF02EUSA02().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA03" size="25" maxlength="12" value="<%= userDetail.getE02EUSA03().trim()%>" class="<%= userDetail.getF02EUSA03().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA04" size="25" maxlength="12" value="<%= userDetail.getE02EUSA04().trim()%>" class="<%= userDetail.getF02EUSA04().equals("Y")?"textchanged":"" %>"></TD>
               </TR>
               <TR align="CENTER">
                  <TD><input readonly  type="text" name="E02EUSA05" size="25" maxlength="12" value="<%= userDetail.getE02EUSA05().trim()%>" class="<%= userDetail.getF02EUSA05().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA06" size="25" maxlength="12" value="<%= userDetail.getE02EUSA06().trim()%>" class="<%= userDetail.getF02EUSA06().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA07" size="25" maxlength="12" value="<%= userDetail.getE02EUSA07().trim()%>" class="<%= userDetail.getF02EUSA07().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA08" size="25" maxlength="12" value="<%= userDetail.getE02EUSA08().trim()%>" class="<%= userDetail.getF02EUSA08().equals("Y")?"textchanged":"" %>"></TD>
               </TR>
               <TR align="CENTER">
                  <TD><input readonly  type="text" name="E02EUSA09" size="25" maxlength="12" value="<%= userDetail.getE02EUSA09().trim()%>"  class="<%= userDetail.getF02EUSA09().equals("Y")?"textchanged":"" %>"></TD>
                  <TD><input readonly  type="text" name="E02EUSA10" size="25" maxlength="12" value="<%= userDetail.getE02EUSA10().trim()%>"  class="<%= userDetail.getF02EUSA10().equals("Y")?"textchanged":"" %>"></TD>
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
</form>
</body>
</html>

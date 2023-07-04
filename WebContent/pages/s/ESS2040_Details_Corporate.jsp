<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Corporate User</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id="userDetail" class="datapro.eibs.beans.ESS204002Message" scope="session" /> 
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

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


<h3 align="center">Usuario de Internet Banking Corporativo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cliente_corporate_new_data, ESS2000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2040" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="4">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="X">
  
  <% if(userDetail.getH02FLGWK3().trim().equals("Y")){ %>
    <h4><FONT color="GREEN">Informacion General de Nuevo Usuario</FONT></h4>
  <% }else{%>
    <h4><FONT color="RED">Información General de Cambio en Usuario</FONT></h4>
  <% }%>
  <table class="tableinfo">
      <tr >
        <td nowrap>

        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">ID Entitdad :</div></td>
            <td nowrap width="60%" ><div align="left"> <input READONLY type="text" ID="MANDATORY" name="E02EUSUSR" class="<%= userDetail.getF02EUSUSR().equals("Y")?"textchanged":"" %>" size="35" maxlength="10" READONLY value="<%= userDetail.getE02EUSUSR().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Usuario Administrador :</div></td>
            <td nowrap width="60%" ><div align="left"> <input READONLY type="text" <% if(userPO.getPurpose().equals("MAINT")){ %> READONLY <% } %> ID="MANDATORY" name="E02EUSENT" class="<%= userDetail.getF02EUSENT().equals("Y")?"textchanged":"" %>" size="35" maxlength="10" value="<%= userDetail.getE02EUSENT().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Password Administrador :</div></td>
            <td nowrap width="60%" ><div align="left"> <input READONLY type="password" ID="MANDATORY" name="E02EUSACP" class="<%= userDetail.getF02EUSACP().equals("Y")?"textchanged":"" %>" size="35" maxlength="10" value="<%= userDetail.getE02EUSACP().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <!-- tr id="teclear">
            <td nowrap width="40%" ><div align="right">Administrator User Status :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT DISABLED NAME="E02EUSSTS">
             <OPTION VALUE="1" <%if (userDetail.getE02EUSSTS().equals("1")) {out.print("selected"); }%>>ACTIVE</OPTION>
             <OPTION VALUE="2" <%if (userDetail.getE02EUSSTS().equals("2")) {out.print("selected"); }%>>INACTIVE</OPTION>
             <OPTION VALUE="3" <%if (userDetail.getE02EUSSTS().equals("3")) {out.print("selected"); }%>>SUSPENDED</OPTION>
             <OPTION VALUE="4" <%if (userDetail.getE02EUSSTS().equals("4")) {out.print("selected"); }%>>PENDING ACTIVATION</OPTION>
             </SELECT></div></td>
          </tr -->
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Numero de Cliente :</div></td>
            <td nowrap width="60%" ><div align="left"> <input READONLY type="text" ID="MANDATORY" name="E02EUSCUN" class="<%= userDetail.getF02EUSCUN().equals("Y")?"textchanged":"" %>" size="35" maxlength="9" value="<%= userDetail.getE02EUSCUN().trim()%>">
                                    <a href="javascript:GetCustomerDescId('E02EUSCUN','E02EUSCON','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0" ></a>
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Cuenta Primaria :</div></td>
            <td nowrap width="60%" ><div align="left"> <input READONLY type="text" ID="MANDATORY" name="E02EUSACC" class="<%= userDetail.getF02EUSACC().equals("Y")?"textchanged":"" %>" size="35" maxlength="9" value="<%= userDetail.getE02EUSACC().trim()%>" onkeypress="enterInteger()">
                                    <a href="javascript:GetAccount('E02EUSACC','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0" ></a>
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Tipo de Acumulación:</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT DISABLED NAME="E02EUSMXT" class="<%= userDetail.getF02EUSMXT().equals("Y")?"textchanged":"" %>">
             <OPTION VALUE="1" <%if (userDetail.getE02EUSMXT().equals("1")) {out.print("selected"); }%>>DAILY MAXIMUN AMOUNT PERMITTED</OPTION>
             <OPTION VALUE="2" <%if (userDetail.getE02EUSMXT().equals("2")) {out.print("selected"); }%>>WEEKLY MAXMIMUN AMOUNT PERMITTED</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" colspan="2" align="center">
				   <TABLE width="70%">
				      <TR>
				         <TD colspan="4" align="center" ><b>PERMISOS</b></TD>
				      </TR>
					  <tr align="center" id="trdark">
					     <TD>Transacción</TD>
					     <TD>Limite por Transacción</TD>
					     <TD>Limite Acumulado</TD>
					  </tr>
					  <tr align="center">
					     <TD align="right">Transferencias Internas :</TD>
					     <TD><input READONLY type="text" name="E02EUSMAX" size="25" maxlength="13" value="<%= userDetail.getE02EUSMAX().trim()%>"></TD>
					     <TD><input READONLY type="text" name="E02EUSAMX" size="25" maxlength="13" value="<%= userDetail.getE02EUSAMX().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Transferencias a Terceros :</TD>
					     <TD><input READONLY type="text" name="E02EUSTTL" size="25" maxlength="13" value="<%= userDetail.getE02EUSTTL().trim()%>"></TD>
					     <TD><input READONLY type="text" name="E02EUSTAL" size="25" maxlength="13" value="<%= userDetail.getE02EUSTAL().trim()%>"></TD>
					  </tr>					  
					  <tr align="center">
					     <TD align="right">Transferencias Externas :</TD>
					     <TD><input READONLY type="text" name="E02EUSETL" size="25" maxlength="13" value="<%= userDetail.getE02EUSETL().trim()%>"></TD>
					     <TD><input READONLY type="text" name="E02EUSEAL" size="25" maxlength="13" value="<%= userDetail.getE02EUSEAL().trim()%>"></TD>
					  </tr>					  
				   </TABLE>            
            </td>
          </tr>
          <!--  tr id="trdark">
            <td nowrap width="40%" ><div align="right">Requiere Clave de Operaciones :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT DISABLED NAME="E02EUSOPR" class="<%= userDetail.getF02EUSOPR().equals("Y")?"textchanged":"" %>">
                                                        <OPTION VALUE="0" <%if (userDetail.getE02EUSOPR().equals("0")) {out.print("selected"); }%>>NO</OPTION>
             					 						<OPTION VALUE="1" <%if (userDetail.getE02EUSOPR().equals("1")) {out.print("selected"); }%>>SI</OPTION>
             				 							 </SELECT>             			               			
	 			</div>				 
            </td>
          </tr>  
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">Clave de Operaciones:</div></td>
            <td nowrap width="60%" ><div align="left"> <input READONLY type="PASSWORD" name="E02EUSOPP" class="<%= userDetail.getF02EUSOPP().equals("Y")?"textchanged":"" %>" size="15" maxlength="10" value="<%= userDetail.getE02EUSOPP() %>"></div></td>
          </tr -->
                    
          <tr id="trdark">
            <td nowrap width="40%" ><div align="right">Persona Responsable :</div></td>
            <td nowrap width="60%" ><div align="left"> <input READONLY type="text" name="E02EUSCON" class="<%= userDetail.getF02EUSCON().equals("Y")?"textchanged":"" %>" size="35" maxlength="30" value="<%= userDetail.getE02EUSCON().trim()%>"></div></td>
          </tr>
          <tr id="teclear">
            <td nowrap width="40%" ><div align="right">E-Mail  :</div></td>
            <td nowrap width="60%" ><div align="left"> <input READONLY type="text" ID="MANDATORY" name="E02EUSIAD" class="<%= userDetail.getF02EUSIAD().equals("Y")?"textchanged":"" %>" size="35" maxlength="45" value="<%= userDetail.getE02EUSIAD().trim()%>">
                                    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
</form>
</body>
</html>

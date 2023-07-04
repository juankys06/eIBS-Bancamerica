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

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.js"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.js"> </SCRIPT>
 
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
 
 
<h3 align="center">Usuario Corporativo Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_inq_corporate_new_data, ESS2000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2000" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="101">
  <INPUT TYPE=HIDDEN NAME="E01EUSCTY" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="E01EUSCFM" VALUE="1">
  <INPUT TYPE=HIDDEN NAME="E01EUSTPT" VALUE="N">
  <INPUT TYPE=HIDDEN NAME="E01EUSMAX" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="E01EUSAMX" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="E01EUSTTL" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="E01EUSTAL" VALUE="0">
  <h4>Información General</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Entidad :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSUSR" size="12" maxlength="10" READONLY value="<%= cusdata.getE01EUSUSR().trim()%>">
                                    </div></td>
          </tr>
          <tr id="tedark"> 
            <td nowrap width="40%" ><div align="right">Usuario Administrador :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" <% if(userPO.getPurpose().equals("MAINT")){ %> READONLY <% } %> ID="MANDATORY" name="E01EUSENT" size="12" maxlength="10" value="<%= cusdata.getE01EUSENT().trim()%>">
                                    </div></td>
          </tr>

          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Estado Usuario Administrador :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT disabled NAME="E01EUSSTS">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSSTS().equals("1")) {out.print("selected"); }%>>ACTIVO</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSSTS().equals("2")) {out.print("selected"); }%>>INACTIVO</OPTION>
             <OPTION VALUE="3" <%if (cusdata.getE01EUSSTS().equals("3")) {out.print("selected"); }%>>SUSPENDIDO</OPTION>
             <OPTION VALUE="4" <%if (cusdata.getE01EUSSTS().equals("4")) {out.print("selected"); }%>>PENDIENTE ACTIVACION</OPTION>
             <OPTION VALUE="5" <%if (cusdata.getE01EUSSTS().equals("5")) {out.print("selected"); }%>>ACUERDO</OPTION>
             
             
             </SELECT></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">No. Cliente :</div></td>
            <td nowrap width="60%" ><div align="left"> <input READONLY type="text" ID="MANDATORY" name="E01EUSCUN" size="10" maxlength="9" value="<%= cusdata.getE01EUSCUN().trim()%>">
                                    </div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Cuenta Primaria :</div></td>
            <td nowrap width="60%" ><div align="left"> <input READONLY type="text" ID="MANDATORY" name="E01EUSACC" size="10" maxlength="9" value="<%= cusdata.getE01EUSACC().trim()%>" onkeypress="enterInteger()">
                                    </div></td> 
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Tipo de Acumulación :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT disabled  NAME="E01EUSMXT">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSMXT().equals("1")) {out.print("selected"); }%>>DIARIA</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSMXT().equals("2")) {out.print("selected"); }%>>SEMANAL</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Permite Transferencias Internas :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT disabled NAME="E01EUSTRA">
             <OPTION VALUE="Y" <%if (cusdata.getE01EUSTRA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             <OPTION VALUE="N" <%if (cusdata.getE01EUSTRA().equals("N")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Monto Máximo para Retiros :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSMAX" size="15" maxlength="13" value="<%= cusdata.getE01EUSMAX().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Monto Máximo Acumulado :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" name="E01EUSAMX" size="15" maxlength="13" value="<%= cusdata.getE01EUSAMX().trim()%>"></div></td>
          </tr>
          
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Persona Responsable :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSCON" size="35" maxlength="30" value="<%= cusdata.getE01EUSCON().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Correo Electrónico :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" ID="MANDATORY" name="E01EUSIAD" size="45" maxlength="45" value="<%= cusdata.getE01EUSIAD().trim()%>">
                                    </div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Tipo Confirmación :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT disabled NAME="E01EUSCFM">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSCFM().equals("1")) {out.print("selected"); }%>>NO CONFIRMACION REQUIRIDA</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSCFM().equals("2")) {out.print("selected"); }%>>CONFIRMACION TELEFONICA</OPTION>
             <OPTION VALUE="3" <%if (cusdata.getE01EUSCFM().equals("3")) {out.print("selected"); }%>>AVISO POR INTERNET AL CLIENTE</OPTION>
             <OPTION VALUE="4" <%if (cusdata.getE01EUSCFM().equals("4")) {out.print("selected"); }%>>E-MAIL AL CLIENTE</OPTION>
             </SELECT></div></td>
          </tr>          
        </table>
        </td>
      </tr>
    </table>
    
 
</form>
</body>
</html>

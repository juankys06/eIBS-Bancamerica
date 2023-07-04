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
 
 
<h3 align="center">Usuario Personal Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_inq_corporate_new_data, ESS2000" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2000" onsubmit="return FieldNotBlank(this)">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="201">
  <INPUT TYPE=HIDDEN NAME="E01EUSCTY" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="E01EUSOPR" VALUE="0">
  <h4>Información General</h4>
  <table class="tableinfo">
      <tr > 
        <td nowrap> 
          
        <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Usuario :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" READONLY ID="MANDATORY" name="E01EUSUSR" size="11" maxlength="10" READONLY value="<%= cusdata.getE01EUSUSR().trim()%>">
                                    </div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Contraseña para Operaciones Requerida :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT disabled NAME="E01EUSOPR">
             <OPTION VALUE="0" <%if (cusdata.getE01EUSOPR().equals("0")) {out.print("selected"); }%>>No</OPTION>
             <OPTION VALUE="1" <%if (cusdata.getE01EUSOPR().equals("1")) {out.print("selected"); }%>>Si</OPTION>
             </SELECT></div></td>
          </tr>     
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Estado Usuario :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT disabled NAME="E01EUSSTS">
             <OPTION VALUE="0" <%if (cusdata.getE01EUSSTS().equals("0")) {out.print("selected"); }%>>ACUERDO</OPTION>
             <OPTION VALUE="1" <%if (cusdata.getE01EUSSTS().equals("1")) {out.print("selected"); }%>>ACTIVO</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSSTS().equals("2")) {out.print("selected"); }%>>INACTIVO</OPTION>
             <OPTION VALUE="3" <%if (cusdata.getE01EUSSTS().equals("3")) {out.print("selected"); }%>>SUSPENDIDO</OPTION>
             <OPTION VALUE="4" <%if (cusdata.getE01EUSSTS().equals("4")) {out.print("selected"); }%>>PENDIENTE ACTIVACION</OPTION>
             
             </SELECT></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">No. Cliente :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" ID="MANDATORY" name="E01EUSCUN" size="10" maxlength="9" value="<%= cusdata.getE01EUSCUN().trim()%>">
			</div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Primaria :</div></td>
            <td nowrap width="60%" ><div align="left"> <input type="text" ID="MANDATORY" name="E01EUSACC" size="10" maxlength="9" value="<%= cusdata.getE01EUSACC().trim()%>" onkeypress="enterInteger()">
                                    <a href="javascript:GetAccount('E01EUSACC','','','')"><img src="/eIBS_R04M08/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
                                    <img src="/eIBS_R04M08/images/Check.gif" alt="mandatory field" align="absbottom" border="0"  ></div></td> 
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Tipo Acumulación :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT disabled NAME="E01EUSMXT">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSMXT().equals("1")) {out.print("selected"); }%>>DIARIO</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSMXT().equals("2")) {out.print("selected"); }%>>SEMANAL</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Permite Transferencias :</div></td>
            <td nowrap width="60%" ><div align="left"> <SELECT disabled NAME="E01EUSTRA">
             <OPTION VALUE="N" <%if (cusdata.getE01EUSTRA().equals("N")) {out.print("selected"); }%>>No Permitidas</OPTION>
             <OPTION VALUE="1" <%if (cusdata.getE01EUSTRA().equals("1")) {out.print("selected"); }%>>Internas</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSTRA().equals("2")) {out.print("selected"); }%>>Internas/Externas</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Permite Pago de Cuentas :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT disabled NAME="E01EUSBPA">
             <OPTION VALUE="Y" <%if (cusdata.getE01EUSBPA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             <OPTION VALUE="N" <%if (!cusdata.getE01EUSBPA().equals("Y")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Permite Cartas de Crédito :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT disabled NAME="E01EUSLCA">
             <OPTION VALUE="Y" <%if (cusdata.getE01EUSLCA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             <OPTION VALUE="N" <%if (!cusdata.getE01EUSLCA().equals("Y")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
           <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Permite Pago de Préstamos :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT disabled NAME="E01EUSLNA">
             <OPTION VALUE="Y" <%if (cusdata.getE01EUSLNA().equals("Y")) {out.print("selected"); }%>>SI</OPTION>
             <OPTION VALUE="N" <%if (!cusdata.getE01EUSLNA().equals("Y")) {out.print("selected"); }%>>NO</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Monto Máximo Retiros :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSMAX" size="15" maxlength="13" value="<%= cusdata.getE01EUSMAX().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Monto Máximo Acumulado :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSAMX" size="15" maxlength="13" value="<%= cusdata.getE01EUSAMX().trim()%>"></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Persona Responsable :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSCON" size="35" maxlength="30" value="<%= cusdata.getE01EUSCON().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Correo Electrónico :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" ID="MANDATORY" name="E01EUSIAD" size="35" maxlength="45" value="<%= cusdata.getE01EUSIAD().trim()%>">
                                    </div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Tipo Confirmación :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT disabled NAME="E01EUSCFM">
             <OPTION VALUE="1" <%if (cusdata.getE01EUSCFM().equals("1")) {out.print("selected"); }%>>NO CONFIRMACION REQUIRIDO</OPTION>
             <OPTION VALUE="2" <%if (cusdata.getE01EUSCFM().equals("2")) {out.print("selected"); }%>>CONFIRMACION TELEFONICA</OPTION>
             <OPTION VALUE="3" <%if (cusdata.getE01EUSCFM().equals("3")) {out.print("selected"); }%>>AVISO POR INTERNET</OPTION>
             <OPTION VALUE="4" <%if (cusdata.getE01EUSCFM().equals("4")) {out.print("selected"); }%>>E-MAIL AL CLIENTE</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Incluir/Excluir Cuentas :</div></td>
            <td nowrap width="60%" ><div align="left"><SELECT disabled NAME="E01EUSIEF">
             <OPTION VALUE=" " <%if (cusdata.getE01EUSIEF().equals(""))  {out.print("selected"); }%>>NO</OPTION>
             <OPTION VALUE="I" <%if (cusdata.getE01EUSIEF().equals("I")) {out.print("selected"); }%>>INCLUIR</OPTION>
             <OPTION VALUE="E" <%if (cusdata.getE01EUSIEF().equals("E")) {out.print("selected"); }%>>EXCLUIR</OPTION>
             </SELECT></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #1 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSA01" size="10" maxlength="9" value="<%= cusdata.getE01EUSA01().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #2 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSA02" size="10" maxlength="9" value="<%= cusdata.getE01EUSA02().trim()%>"></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #3 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSA03" size="10" maxlength="9" value="<%= cusdata.getE01EUSA03().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #4 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSA04" size="10" maxlength="9" value="<%= cusdata.getE01EUSA04().trim()%>"></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #5 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSA05" size="10" maxlength="9" value="<%= cusdata.getE01EUSA05().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #6 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSA06" size="10" maxlength="9" value="<%= cusdata.getE01EUSA06().trim()%>"></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #7 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSA07" size="10" maxlength="9" value="<%= cusdata.getE01EUSA07().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #8 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSA08" size="10" maxlength="9" value="<%= cusdata.getE01EUSA08().trim()%>"></div></td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #9 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSA09" size="10" maxlength="9" value="<%= cusdata.getE01EUSA09().trim()%>"></div></td>
          </tr>
          <tr id="teclear"> 
            <td nowrap width="40%" ><div align="right">Cuenta Acceso #10 :</div></td>
            <td nowrap width="60%" ><div align="left"> <input readonly type="text" name="E01EUSA10" size="10" maxlength="9" value="<%= cusdata.getE01EUSA10().trim()%>"></div></td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    
  
</form>
</body>
</html>

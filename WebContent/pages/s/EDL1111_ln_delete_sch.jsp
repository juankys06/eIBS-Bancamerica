<%@ page import = "datapro.eibs.beans.EDL111101Message" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Banesco - Abonos Masivos, opcion automática</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">


<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.js"> </SCRIPT>

<jsp:useBean id= "extList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>

<body nowrap>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0"); 
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 	int row;
	try {
		row = Integer.parseInt(request.getParameter("ROW"));
	} 
	catch (Exception e) {
		row = 0;
	}
	extList.setCurrentRow(row);
	EDL111101Message msgLN = (EDL111101Message) extList.getRecord();
%>

<SCRIPT LANGUAGE="JavaScript">

 builtHPopUp();

 function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
 }
 
</SCRIPT>

<h3 align="center">Abonos Masivos, Proceso Automático - Eliminación<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_maint_sch.jsp, EDL1111"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL1111" id="form1">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="OPT" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%=row%>">  
  <INPUT TYPE=HIDDEN NAME="E01DLSSEQ" VALUE="99">            
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Préstamo : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01DEAACC" size="12" maxlength="12" value="<%= msgLN.getE01DEAACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Cliente : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01CUSNA1" size="48" maxlength="45" value="<%= msgLN.getE01CUSNA1().trim()%>" readonly>
              </div>
            </td>

          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información de Pago</h4>
  <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0" id="INFOCHG">

          <tr id="trdark"> 
            <td width="30%"> 
              <div align="right">Valor de Pago :</div>
            </td>
            <td > 
              <input type="text" name="E01DLCVA1" size="17" maxlength="15" value="<%= msgLN.getE01DLCVA1().trim()%>" readonly>
            </td>
          </tr>


        </table>                       
      </td>
    </tr>
  </table>


<% if ( msgLN.getE01ORDLST().equals("1")  ) { %>


  <h4>Información Recibida </h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Número Empleado : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK1EMP" size="11" maxlength="11" value="<%= msgLN.getE01WK1EMP().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Nombre : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK1NOM" size="25" maxlength="25" value="<%= msgLN.getE01WK1NOM().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Cédula : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK1CED" size="11" maxlength="11" value="<%= msgLN.getE01WK1CED().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Seguro Social : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK1SSN" size="9" maxlength="9" value="<%= msgLN.getE01WK1SSN().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Código Descuento : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK1DES" size="6" maxlength="6" value="<%= msgLN.getE01WK1DES().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Descuento Quincenal : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK1DQI" size="6" maxlength="6" value="<%= msgLN.getE01WK1DQI().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Monto Compromiso : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK1COM" size="9" maxlength="9" value="<%= msgLN.getE01WK1COM().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Saldo : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK1SAI" size="9" maxlength="9" value="<%= msgLN.getE01WK1SAI().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Fecha : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK1FEC" size="9" maxlength="9" value="<%= msgLN.getE01WK1FEC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b> </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
              <div align="right"><b> </b></div>
              </div>
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>
<% } %>



<% if ( msgLN.getE01ORDLST().equals("2")  ) { %>

  <h4>Información Recibida </h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Código Descuento : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK2DES" size="3" maxlength="3" value="<%= msgLN.getE01WK2DES().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Número de Planilla : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK2PLA" size="2" maxlength="2" value="<%= msgLN.getE01WK2PLA().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Seguro Social : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK2SSN" size="9" maxlength="9" value="<%= msgLN.getE01WK2SSN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Letra : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK2LET" size="1" maxlength="1" value="<%= msgLN.getE01WK2LET().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Nombre : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK2NOM" size="30" maxlength="30" value="<%= msgLN.getE01WK2NOM().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Descuento Quincenal : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK2DQI" size="6" maxlength="6" value="<%= msgLN.getE01WK2DQI().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Monto Compromiso : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK2COM" size="9" maxlength="9" value="<%= msgLN.getE01WK2COM().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Pagado : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK1PAG" size="9" maxlength="9" value="<%= msgLN.getE01WK2PAG().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Cédula : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK2CED" size="11" maxlength="11" value="<%= msgLN.getE01WK2CED().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b> </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
              <div align="right"><b> </b></div>
              </div>
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>
<% } %>


<% if ( msgLN.getE01ORDLST().equals("3")  ) { %>

  <h4>Información Recibida </h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Cédula : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3PRV" size="4" maxlength="4" value="<%= msgLN.getE01WK3PRV().trim()%>" readonly>
                <input type="text" name="E01WK3TOM" size="4" maxlength="4" value="<%= msgLN.getE01WK3TOM().trim()%>" readonly>                
                <input type="text" name="E01WK3ASI" size="5" maxlength="5" value="<%= msgLN.getE01WK3ASI().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Tipo Descuento : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3TDS" size="1" maxlength="1" value="<%= msgLN.getE01WK3TDS().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Fecha Aplicación : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3FAP" size="9" maxlength="9" value="<%= msgLN.getE01WK3FAP().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Código Aplicación : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3CAP" size="1" maxlength="1" value="<%= msgLN.getE01WK3CAP().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Ministerio : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3ARE" size="1" maxlength="1" value="<%= msgLN.getE01WK3ARE().trim()%>" readonly>
                <input type="text" name="E01WK3ENT" size="2" maxlength="2" value="<%= msgLN.getE01WK3ENT().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Planilla/Posición : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3NPL" size="5" maxlength="5" value="<%= msgLN.getE01WK3NPL().trim()%>" readonly>
                <input type="text" name="E01WK3NPO" size="5" maxlength="5" value="<%= msgLN.getE01WK3NPO().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Estado Empleado : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3EST" size="2" maxlength="2" value="<%= msgLN.getE01WK3EST().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Clave/Subclave : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3CLA" size="9" maxlength="9" value="<%= msgLN.getE01WK3CLA().trim()%>" readonly>
                <input type="text" name="E01WK3SUB" size="9" maxlength="9" value="<%= msgLN.getE01WK3SUB().trim()%>" readonly>
                <input type="text" name="E01WK3POS" size="2" maxlength="2" value="<%= msgLN.getE01WK3POS().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Monto Inicial : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3INI" size="9" maxlength="9" value="<%= msgLN.getE01WK3INI().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Valor a Efectuar : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3EFE" size="9" maxlength="9" value="<%= msgLN.getE01WK3EFE().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Valor Efectuado : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3VEF" size="9" maxlength="9" value="<%= msgLN.getE01WK3VEF().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Saldo Descuento : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3SAL" size="9" maxlength="9" value="<%= msgLN.getE01WK3SAL().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Demandante : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3DEM" size="40" maxlength="40" value="<%= msgLN.getE01WK3DEM().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Código Préstamo : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3CPR" size="1" maxlength="1" value="<%= msgLN.getE01WK3CPR().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="20%" > 
              <div align="right"><b>Nombre : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3NOM" size="20" maxlength="20" value="<%= msgLN.getE01WK3NOM().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="20%" > 
              <div align="right"><b>Apellido : </b></div>
            </td>
            <td nowrap width="30%" > 
              <div align="left"> 
                <input type="text" name="E01WK3APE" size="15" maxlength="15" value="<%= msgLN.getE01WK3APE().trim()%>" readonly>
              </div>
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>
<% } %>



  <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
</form>
</body>
</html>

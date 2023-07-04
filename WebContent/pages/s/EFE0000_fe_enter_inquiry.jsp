<html>
<head>
<title>Front Office Input</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
 
<script language="JavaScript">

function RefreshList(){

  document.forms[0].action = "<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0000?SCREEN=300";
  document.forms[0].submit();

}

</script>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<jsp:useBean id= "custList" class= "datapro.eibs.beans.JBList"   scope="session"/>
<jsp:useBean id= "prdList" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<body bgcolor="#FFFFFF">
<H3 align="center"> Tesorer&iacute;a - Consultas- <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fe_enter_inquiry,EFE0000"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0000">
<input type=HIDDEN name="SCREEN" value="200">
<input type=HIDDEN name="E01FESTYP">
  <input type=HIDDEN name="E01FESREF">
  
  <center>
    <table border="0" class="tableinfo" width="100%">
      <tr id="trdark"> 
        <td width="313"> 
          <div align="center"><b>Consultas - Moneda</b></div>
        </td>
        <td width="350"> 
          <div align="center"></div>
          <div align="center"><b>Consultas - Front Office </b></div>
        </td>
        <td width="309">
          <div align="center"><b>Consultas - Contratos</b></div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td width="313"> 
          <div align="left"> 
            <input type="radio" name="INPTOPT" value="65">
            Tasas de Monedas</div>
        </td>
        <td width="350"> 
          <input type="radio" name="INPTOPT" value="68">
          Contratos Incompletos</td>
        <td width="309"> 
          <div align="left"><b> 
            <input type="radio" name="INPTOPT" value="110">
            </b>Cuentas de Moneda Extranjera<b> </b></div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td width="313"> 
          <div align="left"> 
            <input type="radio" name="INPTOPT" value="66">
            Posiciones de Monedas</div>
        </td>
        <td width="350"><b> 
          <input type="radio" name="INPTOPT" value="69">
          </b>Contratos en Proceso</td>
        <td width="309"> 
          <div align="left"> 
            <input type="radio" name="INPTOPT" value="111">
            Cuentas FRA</div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td width="313">&nbsp;</td>
        <td>&nbsp;</td>
        <td>
          <input type="radio" name="INPTOPT" value="112">
          Cuentas Money Market</td>
      </tr>
	<TR id="trclear">
		<TD width="313"></TD>
		<TD></TD>
		<TD>Cuenta Inicial : <INPUT type="text" name="ACCOUNT" size="14" maxlength="12"> (En blanco todas)</TD>
	</TR>
	<tr id="trdark"> 
        <td width="313"> 
          <div align="center"><b>Consultas - Generales</b></div>
        </td>
        <td colspan="2"> 
          <div align="center"><b>Consulta - Clientes</b></div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td width="313"> 
          <div align="left"> <b> 
            <input type="radio" name="INPTOPT" value="120">
            </b>Log de Transferencias<b><b> 
            <input type="text" name="ACCNUM" size="12" maxlength="12">
            <a href="javascript:GetFexAcc('ACCNUM')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></b> 
            </b></div>
        </td>
        <td width="350"> 
          <div align="left"> 
            <input type="radio" name="INPTOPT" value="61" checked>
            Detalles del Cliente</div>
        </td>
        <td> 
          <div align="left">
            <input type="radio" name="INPTOPT" value="43">
            Contratos del Cliente</div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td width="313">&nbsp; </td>
        <td width="350"> 
          <div align="left"> 
            <input type="radio" name="INPTOPT" value="62">
            L&iacute;mites del Cliente</div>
        </td>
        <td>&nbsp;</td>
      </tr>
      <tr id="trclear"> 
        <td width="313">&nbsp; </td>
        <td colspan="2">Cliente : 
          <input type="text" name="CUNCOD" size="9" maxlength="9">
          <input type="text" name="CUNDSC" size="25" maxlength="25">
          <a href="javascript:GetCustomerDescId('CUNCOD','CUNDSC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
          , o seleccione un cliente de la lista</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
      <tr bgcolor="#FFFFFF"> 
        <td colspan="2" width="33%">&nbsp;</td>
      </tr>
      <tr bgcolor="#FFFFFF"> 
        <td width="33%"> 
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
        </td>
        <td width="33%"> 
          <div align="center"> 
            <input id="EIBSBTN" type=button name="Refresh" value="Refrescar" onClick="RefreshList()">
          </div>
        </td>
      </tr>
      <tr bgcolor="#FFFFFF"> 
        <td> </td>
      </tr>
    </table>
<%
	  String Type = (String)session.getAttribute("Type");
  %>
    <table class="tableinfo" width="28%" >
      <tr id="trintro"> 
        <td width="25%"> 
          <input type="radio" name="SOURCE" value="T" <%if(Type.equals("T"))out.print("checked");%>>
          Tesorería </td>
        <td width="25%"> 
          <input type="radio" name="SOURCE" value="F" <%if(Type.equals("F"))out.print("checked");%>>
          Fideicomiso </td>
        <td width="25%"> 
          <input type="radio" name="SOURCE" value="E" <%if(Type.equals("E"))out.print("checked");%>>
          FEM </td>
        <td width="25%"> 
          <input type="radio" name="SOURCE" value="R" <%if(Type.equals("R"))out.print("checked");%>>
          Terceros </td>
      </tr>
    </table>
<br>
  </center>
  <center>
    <table  id="mainTable" class="tableinfo" height="30" width="14%">
      <tr> 
        <td nowrap valign="top" width="100%" height="60"> 
          <table id="headTable">
            <tbody> 
            <tr id="trintrot"> 
              <th align="CENTER" nowrap></th>
              <th align="CENTER" nowrap>N&uacute;mero</th>
              <th align="CENTER" nowrap>Nombre</th>
              <th align="CENTER" nowrap>Id</th>
            </tr>
            </tbody> 
          </table>
          <div id="dataDiv1" class="scbarcolor"  style="overflow-Y:scroll;height:100"> 
            <table id="dataTable"  style="font-size:7pt">
              <%
                custList.initRow();
                int k=1;
                while (custList.getNextRow()) {
                    if (custList.getFlag().equals("")) {
                    		out.println(custList.getRecord());
                    k++;
                    }        
                }
            %> 
            </table>
          </div>
        </td>
      </tr>
    </table>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  </center>
      
  
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
  <%
 }
%> <b>
  <input type=HIDDEN name="totalRow" value="1">
  </b>
<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="11";
     function resizeDoc() {
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
     }
     showChecked("ACCNUM");
     resizeDoc();
     window.onresize = resizeDoc;
function divResize2() {
  var minValue= mainTable.offsetTop + dataDiv1.offsetTop + 30;
  var h = 400 - minValue ;
  var totalrow= parseInt(document.forms[0].totalRow.value);
  var maxHeight= totalrow * 20; 
  
  if (totalrow > 6) {
     dataDiv1.style.height= maxHeight + ""; 
     dataDiv1.style.overflowY="scroll";
  } else {
	dataDiv1.style.height= maxHeight + ""; 
   	dataDiv1.style.overflowY="";
  } 
}
     
</SCRIPT>

</form>
</body>

</html>

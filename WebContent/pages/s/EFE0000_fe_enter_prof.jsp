<html>
<head>
<title>Front Office Input</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "custList" class= "datapro.eibs.beans.JBList"   scope="session"/>


<body bgcolor="#FFFFFF">
<H3 align="center"> Tesorer&iacute;a - Rentabilidad<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fe_enter_prof,EFE0000"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0000">
<input type=HIDDEN name="SCREEN" value="200">
<input type=HIDDEN name="E01FESTYP">
<center>
    <table class="tableintro" width="58%">
      <tr id="trintrot"> 
        <td colspan="7"><%= userPO.getHeader1()%></td>
        <td> 
          <div align="right"><%= userPO.getHeader2()%></div>
        </td>
      </tr>
      <tr id="trintro"> 
        <td width="4%"> 
          <input type="radio" name="INPTOPT" value="31">
        </td>
        <td width="23%"> 
          <div align="left">Ganancia por Diferencial</div>
        </td>
        <td width="4%"> 
          <div align="center"> 
            <input type="radio" name="INPTOPT" value="33">
          </div>
        </td>
        <td width="18%"> 
          <div align="left"> Puntos</div>
        </td>
        <td width="4%"> 
          <div align="right">
            <input type="radio" name="INPTOPT" value="32">
          </div>
        </td>
        <td colspan="3">Revaluaci&oacute;n de la Ganancia</td>
      </tr>
      <tr id="trdark"> 
        <td colspan="8">&nbsp;</td>
      </tr>
      <tr id="trintro"> 
        <td colspan="5"> 
          <div align="left">Cliente : 
            <input type="text" name="CUNCOD" size="9" maxlength="9">
            <input type="text" name="CUNDSC" size="25" maxlength="25">
            <a href="javascript:GetCustomerDescId('CUNCOD','CUNDSC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></div>
        </td>
        <td colspan="3">N&uacute;mero de Referencia: 
          <input type="hidden" name="E01FESDID">
          <input type="text" name="E01FESREF" size="9" maxlength="9">
          <a href="javascript:GetFeRef('E01FESDID','E01FESREF',document.forms[0].E01FESTYP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
      <tr bgcolor="#FFFFFF">
        <td width="33%">&nbsp;</td>
      </tr>
      <tr bgcolor="#FFFFFF"> 
        <td width="33%"> 
            <div align="center"> 
	        <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </div>
        </td>
      </tr>
      <tr bgcolor="#FFFFFF"> 
        <td> </td>
      </tr>
    </table>

  </center>
  <center>
    <table  id="mainTable" class="tableinfo" height="30" width="0">
    
        <TR>
            <TD nowrap valign="top" width="100%" height="60">
            
          <TABLE id="headTable">
            <TBODY> 
            <TR id="trintrot"> 
              <TH align="CENTER" nowrap></TH>
              <TH align="CENTER" nowrap>N&uacute;mero</TH>
              <TH align="CENTER" nowrap>Nombre</TH>
              <TH align="CENTER" nowrap>Id</TH>
            </TR>
            </TBODY> 
          </TABLE>
            <DIV id="dataDiv1" class="scbarcolor"  style="overflow-Y:scroll;height:100">
            <TABLE id="dataTable"  style="font-size:7pt">
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
            </TABLE>
            </DIV>
            </TD>
        </TR>
        
    
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
       //divResize2(true);
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

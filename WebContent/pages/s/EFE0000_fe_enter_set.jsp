<html>
<head>
<title>Front Office Input</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
 function Sendet(){
   if(!document.forms[0].CUNCOD.value == ''){
		document.forms[0].CUSTOMER.value = document.forms[0].CUNCOD.value;
   }
 document.forms[0].Submit();
}
</SCRIPT>

</head>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "custList" class= "datapro.eibs.beans.JBList"   scope="session"/>
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<body bgcolor="#FFFFFF">
<H3 align="center"> Tesorer&iacute;a - L&iacute;mites Diarios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fe_enter_set,EFE0000"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0000">
<input type=HIDDEN name="SCREEN" value="200">
<input type=HIDDEN name="E01FESTYP">
  <input type="hidden" name="E01FESREF" size="9" maxlength="9">
  <input type="hidden" name="INPTOPT" value="102">
  <center>
    <table class="tableinfo" >
      <tr id="trintrot"> 
        <td colspan="7"><%= currUser.getH01USR()%></td>
        <td> 
          <div align="right"><%= datapro.eibs.master.Util.formatDate(currUser.getE01RDM(),currUser.getE01RDD(),currUser.getE01RDY())%></div>
        </td>
      </tr>
      <tr id="trintro"> 
        <td colspan="8"> 
          <div align="left">Cliente : 
            <input type="text" name="CUNCOD" size="9" maxlength="9">
            <input type="text" name="CUNDSC" size="25" maxlength="25">
            <a href="javascript:GetCustomerDescId('CUNCOD','CUNDSC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a></div>
        </td>
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
  </div>        </td>
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

<html>
<head>
<title>Front Office Input</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>




</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "custList" class= "datapro.eibs.beans.JBList"   scope="session"/>

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="javascript">
  function goHelp() {

     var formLength= document.forms[0].elements.length;
     var ok = false;
	 var cun = "";
     for(n=0;n<formLength;n++) {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "CUSTOMER") {
				if (document.forms[0].elements[n].checked == true) {
					cun = document.forms[0].elements[n].value;
        			ok = true;
        			break;
				}
      	}
     }
     if ( ok ) {
		GetCreditLineT('E01FESREF', cun);
     }
     else {
		alert("Please select an account number to continue.");	   
     }
}
</script>

<body bgcolor="#FFFFFF">
<H3 align="center"> Tesorer&iacute;a - L&iacute;neas de Cr&eacute;dito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fe_enter_lin,EFE0000"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0000">
<input type=HIDDEN name="SCREEN" value="200">
<input type=HIDDEN name="E01FESTYP">
<input type=HIDDEN name="E01FESTYPO" value="42">
  <input type="hidden" name="INPTOPT" value="42">
  <center>
    <table class="tableinfo" width="58%">
      <tr id="trintrot"> 
        <td colspan="2"><%= currUser.getH01USR()%></td>
        <td> 
          <div align="right"><%= datapro.eibs.master.Util.formatDate(currUser.getE01RDM(),currUser.getE01RDD(),currUser.getE01RDY())%></div>
        </td>
      </tr>
      <tr id="trintro"> 
        <td colspan="3"> 
          <div align="center">L&iacute;nea de Cr&eacute;dito : 
            <input type="text" name="E01FESREF" size="9" maxlength="9">
            <a href="javascript:goHelp()"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            <input type="hidden" name="CUNCOD">
          </div>
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
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
    <SCRIPT language="Javascript">
            showErrors();
     </SCRIPT>
  <%
 }
%> <B> <INPUT type="HIDDEN" name="totalRow" value="1"> </B>
<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="11";
     function resizeDoc() {
       divResize2();
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
     }
     showChecked("ACCNUM");
     resizeDoc();
     window.onresize = resizeDoc;
function divResize2() {
  var minValue= mainTable.offsetTop + dataDiv1.offsetTop + 30;
  var h = 400 - minValue ;
  var totalrow= parseInt(document.forms[0].totalRow.value);
  var maxHeight= totalrow * 18; 
  
  if (totalrow > 6) {
     dataDiv1.style.height= maxHeight + ""; 
     dataDiv1.style.overflowY="scroll";
  } else {
	dataDiv1.style.height= maxHeight + ""; 
   	dataDiv1.style.overflowY="";
  } 
}
     
</SCRIPT></p>
    
  </center>
      
  

</form>
</body>
</html>

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


<body bgcolor="#FFFFFF">
<H3 align="center"> SWIFT - Formatos Libres<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="swift_free_format_enter_new,ESWF000"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0000">
<input type=HIDDEN name="SCREEN" value="200">
<input type=HIDDEN name="E01FESTYP">
<center>
    <table class="tableintro" width="58%">
      <tr id="trintro"> 
        <td width="6%" height="10"> 
          <input type="radio" name="INPTOPT" value="01"
		  onClick="document.forms[0].E01FESTYP.value='SPT'" checked>
        </td>
        <td width="46%" height="10">Formato 199</td>
        <td width="5%" height="10"> 
          <div align="center"> 
            <input type="radio" name="INPTOPT" value="05"
			onClick="document.forms[0].E01FESTYP.value='SPT'">
          </div>
        </td>
        <td width="43%" height="10">Formato 799</td>
      </tr>
      <tr id="trintro"> 
        <td width="6%" height="24"> 
          <input type="radio" name="INPTOPT" value="02"
		  onClick="document.forms[0].E01FESTYP.value='SPT'">
        </td>
        <td width="46%" height="24">Formato 299</td>
        <td width="5%" height="24"> 
          <div align="center"> 
            <input type="radio" name="INPTOPT" value="06">
          </div>
        </td>
        <td width="43%" height="24">Formato 999</td>
      </tr>
      <tr id="trintro"> 
        <td width="6%"> 
          <input type="radio" name="INPTOPT" value="03"
		  onClick="document.forms[0].E01FESTYP.value='SPT'">
        </td>
        <td width="46%">Formato 499</td>
        <td width="5%"> 
          <div align="center"> </div>
        </td>
        <td width="43%">&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
      <tr bgcolor="#FFFFFF">
        <td width="33%">&nbsp;</td>
      </tr>
      <tr bgcolor="#FFFFFF"> 
        <td width="33%"> 
          <div align="center"> 
            <input type=image class="imgfilter" name="Submit" src="<%=request.getContextPath()%>/images/s/bt_submit.gif" 
  		onMouseDown="this.className='imgfilterpress'" onMouseUp="this.className='imgfilter'">
          </div>
        </td>
      </tr>
      <tr bgcolor="#FFFFFF"> 
        <td> </td>
      </tr>
    </table>

  </center>
  <center>
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

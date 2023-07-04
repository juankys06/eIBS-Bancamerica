<html>
<head>
<title>Transactions</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="JavaScript">

function showGInfo(idxRow){
  var i= parseInt(document.forms[0].ActGRow.value);
  var j= parseInt(document.forms[0].ActSGRow.value);
  var maxRow=0;
  gTable.rows[i].className="trnormal";
  gTable.rows[idxRow].className="trhighlight";
  document.forms[0].ActGRow.value=""+idxRow;
  document.all["sgTable"+i].style.display="none";
  document.all["sgTable"+idxRow].style.display="";
  document.all["ccTable"+i+"_"+j].style.display="none";
  document.all["ccTable"+idxRow+"_0"].style.display="";
  maxRow=document.all["sgTable"+idxRow].rows.length;
  for(k=0;k<maxRow;k++){
	document.all["sgTable"+idxRow].rows[k].className="trnormal";
  }
  document.all["sgTable"+idxRow].rows[0].className="trhighlight";
  document.forms[0].ActGRow.value=""+idxRow;
  document.forms[0].ActSGRow.value="0";
  document.forms[0].ActCCRow.value="0";
}    

function showSGInfo(idxRow){
  var i= parseInt(document.forms[0].ActGRow.value);
  var j= parseInt(document.forms[0].ActSGRow.value);

  document.all["sgTable"+i].rows[j].className="trnormal";
  document.all["sgTable"+i].rows[idxRow].className="trhighlight";
  document.all["ccTable"+i+"_"+j].style.display="none";
  document.all["ccTable"+i+"_"+idxRow].style.display="";  
  document.forms[0].ActSGRow.value=""+idxRow;  
}

function clickSGckBox(val,idxRow){
  showSGInfo(idxRow);
  var i= parseInt(document.forms[0].ActGRow.value);
  var j= parseInt(document.forms[0].ActSGRow.value);
  
  var oObject = document.all.item("chkCCenter"+i+"_"+j);
   if (oObject != null){
     if (oObject.length != null){
      for(k=0;k>oObject.length;k++){
       oObject(k).checked=val;}
     }
     else{
      oObject.checked=val;
     }
   } 
}

function clickGckBox(val,idxRow){
  showGInfo(idxRow); 
  var oObject = document.all.item("chkSGroup"+idxRow);
   if (oObject != null){
     if (oObject.length != null){
      for(j=0;j < oObject.length;j++){
       oObject(j).checked=val;
	   var oObject1 = document.all.item("chkCCenter"+idxRow+"_"+j);
   		if (oObject1 != null){
     		if (oObject1.length != null){
      		for(k=0;k<oObject1.length;k++){
       			oObject1(k).checked=val;}
     		}
     		else{
     		 oObject1.checked=val;
     		}
   		} 
	   }
     }
     else{
      oObject.checked=val;
	  clickSGckBox(val,0);	
     }
   }
//  showGInfo(idxRow); 
}
</SCRIPT>



</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">
<H3 align="center">Entradas Dealer Slip - Moneda Extranjera <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fe_enter_maint,EFE0120P"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFE0120P">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
    
  </p>
  <table class="tbenter" height="75%" width="100%" border="0">
    <tr> 
      <td nowrap align="center"> 
        <table class="tableinfo">
          <tr id="trdark"> 
            <td width="36%"> 
              <div align="right">Tipo de Contrato :</div>
            </td>
            <td width="64%"> 
              <input type="hidden" name="E01FESTYP" >
              <input type="radio" name="OPTPRD" value="SPT" 
			  onClick="document.forms[0].E01FESTYP.value='SPT'">
              Spot 
              <input type="radio" name="OPTPRD" value="FWR"  
			  onClick="document.forms[0].E01FESTYP.value='FWR'">
              Forward 
              <input type="radio" name="OPTPRD" value="OPT" 
			  onClick="document.forms[0].E01FESTYP.value='OPT'">
              Opci&oacute;n 
              <input type="radio" name="OPTPRD" value="SWP"  
			  onClick="document.forms[0].E01FESTYP.value='SWP'">
              Swap</td>
          </tr>
          <tr id="trclear"> 
            <td width="36%"> 
              <div align="right">Moneda / Clasificaci&oacute;n :</div>
            </td>
            <td width="64%"> 
              <input type="text" name="E01FESCCY" size="9" maxlength="9">
              <input type="text" name="E01FESCLS" size="9" maxlength="9">
              <a href="javascript:GetTypPar('E01FESCCY','E01FESCLS',document.forms[0].E01FESTYP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="36%" height="2"> 
              <div align="right">Nombre Cliente :</div>
            </td>
            <td width="64%" height="2"> 
              <input type="text" name="E01FESCUN" size="9" maxlength="9">
              <input type="text" name="E01FESNA1" size="45" maxlength="45">
              <a href="javascript:GetCustomerDescId('E01FESCUN','E01FESNA1','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="36%" height="2">&nbsp;</td>
            <td width="64%" height="2">&nbsp;</td>
          </tr>
          <tr id="trdark"> 
            <td width="36%" height="2"> 
              <div align="right">N&uacute;mero Referencia :</div>
            </td>
            <td width="64%" height="2"> 
              <input type="hidden" name="E01FESDID">
              <input type="text" name="E01FESREF" size="9" maxlength="9">
              <a href="javascript:GetFeRef('E01FESDID','E01FESREF',document.forms[0].E01FESTYP.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0" ></a> 
            </td>
          </tr>
        </table>
        <p><input type=image class="imgfilter" name="Submit" src="<%=request.getContextPath()%>/images/s/bt_submit.gif" 
  		onMouseDown="this.className='imgfilterpress'" onMouseUp="this.className='imgfilter'">
      </p></td>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p align="center">&nbsp; </p>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>

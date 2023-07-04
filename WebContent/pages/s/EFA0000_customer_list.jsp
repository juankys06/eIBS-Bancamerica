<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>I.B.S.</title>
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="facusdata" class="datapro.eibs.beans.EFA000002Message"  scope="session" />
<jsp:useBean id="facuslist" class="com.datapro.generics.BeanList"  scope="session" />

<script language="JavaScript">
var posif
var posit
var facc

function FValidateControlF(control) {
  var clt
  var checkedt = false
  clt = control.length

  for(i=0;i<clt;i++){
    if(control[i].checked){
      posit =i
      facc = control[i].value
      checkedt = true
      break
    }
  }
  
  if(clt == undefined){
    facc = document.forms[0].RECSEL.value;
    checkedt = true
  }
  
  return checkedt
}

function goAction(op) {
  if(op=='1'){
	  if (!FValidateControlF(document.forms[0].RECSEL)){
    	  alert("Debe de serleccionar una Factura a Imprimir");
          return;
  	  }
    var xnum = 0;
    var oldpos = 0;
    var newpos = 0;
    while(facc.indexOf("|" ,oldpos) != -1){
      newpos = facc.indexOf("|" ,oldpos);
      var SValue = facc.substring(oldpos,newpos);
      oldpos=newpos+1;
      xnum++;
      switch(xnum){
		case 1:
 		   document.forms[0].E01TRFACR.value = SValue;
		   break;
		case 2:
		   document.forms[0].E01TRFURN.value = SValue;
		   break;
		case 3:   
		   document.forms[0].E01TRFDT1.value = SValue;
		   break;
		case 4:
		   document.forms[0].E01TRFDT2.value = SValue;
		   break;
		case 5:
		   document.forms[0].E01TRFDT3.value = SValue;
		   break;
	  }	   		   		
    }
  }
  if(op=='2'){
     document.forms[0].SCREEN.value='100';

  }
  document.forms[0].submit();  
}
</script>  
</head>

<body>

 <h3 align="center">Impresion de Comprobante Fiscal<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="enter_customer,EFA0000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEFA0000">
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
    <INPUT TYPE=HIDDEN NAME="E01TRFACR" VALUE="">
    <INPUT TYPE=HIDDEN NAME="E01TRFURN" VALUE="">
    <INPUT TYPE=HIDDEN NAME="E01TRFDT1" VALUE="">
    <INPUT TYPE=HIDDEN NAME="E01TRFDT2" VALUE="">
    <INPUT TYPE=HIDDEN NAME="E01TRFDT3" VALUE="">
    <INPUT TYPE=HIDDEN NAME="E01OPESEL" VALUE="<%= facusdata.getH02FLGWK3() %>"> 
        
  </p>
  <h4 align="center">Seleccione la transaccion que desea imprimir</h4>
  <p>&nbsp; </p>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap align="right" valign="middle" >Cliente :</td>
            <td nowrap align="left" valign="middle"  >
                <INPUT type="text" name="E02CUSCUN" value="<%= facusdata.getE02CUSCUN() %>" maxlength="10" size="10" readonly> 
                <INPUT type="text" name="E02CUSNA1" value="<%= facusdata.getE02CUSNA1() %>"  size="50" readonly> 
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>

    <table class="tbenter" width="100%">
    <tr> 
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction(1)"><b><%= facusdata.getH02FLGWK3().equals("R")?"Reimprimir Factura":"Imprimir Factura" %></b></a></div>
      </TD>
      <TD class=TDBKG width="50%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Cancelar</b></a></div>
      </TD>
    </tr>
  </table>
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap align="center" valign="middle" >[]</td>
            <td nowrap align="center" valign="middle"  > Fecha</td>
            <td nowrap align="right" valign="middle"  > Monto Base </td>
            <td nowrap align="right" valign="middle"  > Monto Impuesto</td>
            <td nowrap align="left" valign="middle"  > Descripcion</td>
            <td nowrap align="left" valign="middle"  > <%= facusdata.getH02FLGWK3().equals("R")?"NCF":"REFERENCIA" %></td>
          </tr>
          <% facuslist.initRow();
             while(facuslist.getNextRow()){
                 datapro.eibs.beans.EFA000001Message list = (datapro.eibs.beans.EFA000001Message) facuslist.getRecord(); 
                 %>
	          <tr id="trlight"> 
               <td nowrap align="center" valign="middle"> <input name="RECSEL" type="RADIO" value="<%= list.getE01TRFACR() + "|" + list.getE01TRFURN() + "|" + list.getE01TRFDT1() + "|" +list.getE01TRFDT2() + "|" + list.getE01TRFDT3() + "|" %>"> </td>
               <td nowrap align="center" valign="middle"> <%= list.getE01TRFDT1() + "/" + list.getE01TRFDT2() + "/" + list.getE01TRFDT3() %></td>
               <td nowrap align="right" valign="middle" > <%= list.getE01TRFAMT()  %></td>
               <td nowrap align="right" valign="middle" > <%= list.getE01TRFMIV()  %></td>
               <td nowrap align="left" valign="middle"  > <%= list.getE01TRFDSC()  %></td>
               <td nowrap align="left" valign="middle"  > <%= list.getE01TRFURN().length()>0?list.getE01TRFURN():list.getE01TRFACR()  %></td>               
              </tr>              
          <%  }   %>
        </table>
      </td>
    </tr>
  </table>

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

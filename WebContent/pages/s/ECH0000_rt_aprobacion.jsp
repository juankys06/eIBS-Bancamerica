<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>Cuentas Corrientes</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="javascript">

 function showAddInfo(idxRow)
 {
	document.forms[0].E01CHABNK.value = document.forms[0]["BNK" + idxRow].value;
	document.forms[0].E01CHABRN.value = document.forms[0]["BRN" + idxRow].value;	
	document.forms[0].E01CHACCY.value = document.forms[0]["CCY" + idxRow].value;   
	document.forms[0].E01CHAACC.value = document.forms[0]["ACC" + idxRow].value;
	document.forms[0].E01CHATIM.value = document.forms[0]["TIM" + idxRow].value;	
  }    

</script>


<META name="GENERATOR" content="IBM WebSphere Studio">
</head>

<jsp:useBean id= "rtBasic" class= "datapro.eibs.beans.JBList"   scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<% 
 if ( !error.getERRNUM().equals("0")  ) 
 {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }


%> 
<H3 align="center">Aprobación

<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ECH0000_rt_aprobacion.jsp, ECH0000"></H3>

<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0000" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="31">

  <INPUT TYPE=HIDDEN NAME="E01CHABNK">
  <INPUT TYPE=HIDDEN NAME="E01CHABRN">
  <INPUT TYPE=HIDDEN NAME="E01CHACCY">
  <INPUT TYPE=HIDDEN NAME="E01CHAACC">
  <INPUT TYPE=HIDDEN NAME="E01CHATIM">

  <INPUT TYPE=HIDDEN NAME="reason">
  <INPUT TYPE=HIDDEN NAME="E01CHAOCT">
  
  <TABLE class="tbenter">
    <TR>
      <TD ALIGN="CENTER" class="TDBKG">
  			<a href="javascript:goAction('A')" id="linkApproval">Aprobar</a>
      </TD>
      <TD ALIGN="CENTER" class="TDBKG">
  			<a href="javascript:enterReason('R')" id="linkReject">Rechazar</a>
      </TD>
     
      <TD ALIGN="CENTER" class="TDBKG">
  			<a href="javascript:checkClose()">Salir</a>
      </TD>
     </TR>
  </TABLE>
  
<BR>
<table id="mainTable" class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
       <div id="dataDiv1" class="scbarcolor" >
        <table id="dataTable" cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap>Seleccion</td>
            <td nowrap>Cuenta</td>
            <td nowrap>Cliente</td>
            <td nowrap>Tipo Talonario</td>
            <td nowrap>Cantidad</td>
            <td nowrap>Fecha</td>
            <td nowrap>Hora</td>
            <td nowrap>Motivo de Rechazo</td>
            <td nowrap>Agencia de Entrega</td>
          </tr>
	      <%
	      rtBasic.initRow();
              while (rtBasic.getNextRow()) 
              {
               if (rtBasic.getFlag().equals("")) 
               {
            	   out.println(rtBasic.getRecord());
               }
              }
          %> 
        </table>
        </div>
      </td>
    </tr>
</table>
<BR>

</form>

<script language="javascript">
  var reason = '';
 
  function goAction(op) {

     document.forms[0].E01CHAOCT.value = op;
     document.forms[0].reason.value = reason;
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(var n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ACCNUM") 
      	{
        		ok = true;
        		break;
      	}
      }
	  if ( ok ) {
          document.forms[0].submit();
     }
     else {
 		alert("Seleccione una cuenta.");	   
     }

 }
 
 function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }
  
</script>

</body>
</html>

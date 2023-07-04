<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>Cuentas Corrientes</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="javascript">

 function showAddInfo(idxRow)
 {
	document.forms[0].E02CHMACC.value = document.forms[0]["ACC" + idxRow].value;   
	document.forms[0].E02CHMNCB.value = document.forms[0]["NCB" + idxRow].value;
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

<H3 align="center">Anulación de Chequera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_list_chk.jsp,ECH39001"></H3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0390" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">

  <INPUT TYPE=HIDDEN NAME="E02CHMACC">
  <INPUT TYPE=HIDDEN NAME="E02CHMNCB">
  <INPUT TYPE=HIDDEN NAME="E02ACCION">
  <INPUT TYPE=HIDDEN NAME="reason">
  
<br>
  <table class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left"> 
                <input type="text" name="CUSCUN" size="9" maxlength="9" value="<%= userPO.getHeader2().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="CUSNA1" size="45" readonly maxlength="45" value="<%= userPO.getHeader3().trim()%>" >
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E01CHMACC" size="12" readonly maxlength="12" value="<%= userPO.getIdentifier().trim()%>" >
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="PROCCY" size="4" readonly maxlength="4" value="<%= userPO.getCurrency().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="PROCOD" size="4" readonly maxlength="4" value="<%= userPO.getHeader1().trim()%>">
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>

  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:enterReason('A')" id="linkReject" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/anular_b.gif',1)"><img name="Image2" alt="Anular" border="0" src="<%=request.getContextPath()%>/images/s/anular_c.gif" ></a>
      </TD>
      <TD ALIGN=CENTER width="25%">
  			<a href="javascript:enterReason('R')" id="linkReject" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','<%=request.getContextPath()%>/images/s/restaurar_b.gif',1)"><img name="Image2" alt="Restaurar" border="0" src="<%=request.getContextPath()%>/images/s/restaurar_c.gif" ></a>
      </TD>
     </TR>

  </TABLE>
  
<table id="mainTable" class="tableinfo">
          <tr id="trdark"> 
            <td>Seleccion</td>
            <td>Fecha Solicitud</td>
            <td>Fecha Ultima Etapa</td>
            <td>Número Chequera</td>
            <td>Estatus Chequera</td>
            <td>Número de Cheques</td>
            <td>Cheque Inicial</td>
            <td>Cheque Final</td>
            <td>Chr Sol</td>
            <td>Rec Sol</td>
            <td>Env Suc</td>
            <td>Rec Suc</td>
            <td>ent Cli</td>
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
<BR>

</form>

<script language="javascript">
 
  function enterReason(op) {

     document.forms[0].E02ACCION.value = op;

     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(var n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	if(elementName == "ACCNUM") 
      	{
  			if (document.forms[0].elements[n].checked)
  			{
        		ok = true;
        		break;
        	}
      	}
      }
	  if ( ok ) 
	  {
          document.forms[0].submit();
      }
      else 
      {
 		alert("Seleccione una cuenta.");	   
      }
 }
 
 function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";
  }
  
</script>

</body>
</html>

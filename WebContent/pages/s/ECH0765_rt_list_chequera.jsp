<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>Cuentas Corrientes</title>
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<SCRIPT Language="Javascript">


function PrintPreview() 
{
	div1.style.display="none";
	window.print();
	div1.style.display="";
}

</SCRIPT>



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

String tit = "";
String tit2 = "";


if (userPO.getHeader22().equals("1") )
{
	tit="en Imprenta";
	tit2="Fecha Solicitud";
}


if (userPO.getHeader22().equals("2"))
{
	tit="en Stock";
	tit2="Fecha Recepción";
}

if (userPO.getHeader22().equals("3") )
{
	tit="Vendidas";
	tit2="Fecha Venta";
}

%> 

<h3 align="center">Consulta de Chequeras <%=tit%><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ECH076501_rt_list_chequera.jsp"></h3>
<hr size="4">


<form>
<BR>
<H3 align="left">Sucursal : <%= userPO.getHeader23().trim()%></H3>

<table id=dataTable cellspacing=0 cellpadding=2 width=100% border=1>
		<tr id=trdark>
		
			<td>Estado</td>
			<td><%=tit2%></td>
			<td>Num. Cuenta</td>
			<td>Cliente</td>
			<td>Rut</td>
			<td>Tipo de Chequera</td>
			<td>Nro.de Chequera</td>
			<td>Cantidad de Cheques</td>
			<td>Nro.Inicial de Cheque</td>
			<td>Nro.Final de Cheque</td>
			<td>Rut (Retiro)</td>
			<td>Nombre (Retiro)</td>
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

<div align="center" id ="div1">
   <img style="cursor: hand;" name="Submit" src="<%=request.getContextPath()%>/images/s/bt_imprimir.gif"  onMouseDown="this.className='' "  onMouseUp="this.className='imgfilter' "  OnClick="PrintPreview();" >
</div>

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

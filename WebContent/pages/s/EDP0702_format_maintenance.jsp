<html>
<head>
<title>Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP070201Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>

<script language="JavaScript">

function goConfirm() {

		
	
      ok = confirm("Esta seguro que desea crear/modificar este registro?");
      
	  if (ok) 
	       {

// begin test
//          page= prefix +language + "EDP0702_format_test.jsp"
//			CenterWindow(page,600,500,4);
// end test

	       document.forms[0].SCREEN.value="600";
	       document.forms[0].submit();
	        
	       }  
	  
		  
}

function goCancel() {

document.forms[0].SCREEN.value="100";
document.forms[0].submit(); 
	  		  
}

</script>

<H3 align="center">Mantenimiento Códigos de Formatos<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="format_maintenance.jsp, EDP0702"></H3>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0702" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
      </td>
    </tr>
  </table>
  
  
  <h4>Formato:</h4>  
  
   
  
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right">Código:</div>
            </td>
            <td nowrap width="55%">  
             <INPUT type="text" name="E01DPFFMT" maxlength="1" size="1"
					value="<%= brnDetails.getE01DPFFMT().trim()%>" ;
					<% if(userPO.getHeader16().equals("2")){out.print("readonly");} %> >					
			</td>
			<TD width="14"></td>
            <td nowrap width="28%"></td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Descripción:</div>
            </td>
            <td nowrap height="23" colspan="3" width="372"> 

      			<INPUT type="text" name="E01DPFDSC" size="35" maxlength="35" value="<%= brnDetails.getE01DPFDSC().trim()%>";
					<% if(userPO.getHeader16().equals("3")){out.print("readonly");} %> >
			</td>
          </tr>
          
          
          <tr id="trdark"> 
            <td nowrap width="16%" height="19"> 
              <div align="right">Tipo de Formato:</div>
            </td>
            <td nowrap height="19" colspan="3">
			<SELECT name="E01DPFLGT" >
					<OPTION value=""
						<%if (brnDetails.getE01DPFLGT().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="1"
						<%if (brnDetails.getE01DPFLGT().equals("1")) { out.print("selected"); }%>>Cliente Persona Juridica</OPTION>
					<OPTION value="2"
						<%if (brnDetails.getE01DPFLGT().equals("2")) { out.print("selected"); }%>>Cliente Persona Natural</OPTION>						
			</SELECT>
			</td>
		</tr>
			
		<tr id="trclear">
		    <td nowrap width="16%" height="19">
				<DIV align="right">Validar Formato :</DIV>
			</td>
			<td nowrap height="19" colspan="3">
								<input type="radio" name="E01DPFVAL"  value="1" <%if(brnDetails.getE01DPFVAL().equals("1")) out.print("checked");%>>
              					Sí 
              					<input type="radio" name="E01DPFVAL" value="0" 	<%if(!brnDetails.getE01DPFVAL().equals("1")) out.print("checked");%>>
              					No 
			</td>
		</tr>
		
									
			
 		 <tr id="trdark"> 
            <td nowrap width="16%" height="19">
              <div align="right">Clase de Formato:</div>
            </td>
            <td nowrap height="19" colspan="3">
			<SELECT name="E01DPFCLF" >
					<OPTION value=""
						<%if (brnDetails.getE01DPFCLF().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="B"
						<%if (brnDetails.getE01DPFCLF().equals("B")) { out.print("selected"); }%>>Balance</OPTION>
					<OPTION value="G"
						<%if (brnDetails.getE01DPFCLF().equals("G")) { out.print("selected"); }%>>G&P</OPTION>
					<OPTION value="F"
						<%if (brnDetails.getE01DPFCLF().equals("F")) { out.print("selected"); }%>>Flujo de Caja</OPTION>
					<OPTION value="O"
						<%if (brnDetails.getE01DPFCLF().equals("O")) { out.print("selected"); }%>>Otros</OPTION>
			</SELECT>
			</td>
         </tr>
       
       
         <tr id="trclear"> 
         	<td nowrap width="16%" height="19">
              <div align="right">Frecuencia Vencimiento Real:</div>
            </td>
            <td nowrap height="19" colspan="3">
      			<INPUT type="text" name="E01DPFFVN" size="5" maxlength="5"
						onKeypress="enterInteger()"
					value="<%= brnDetails.getE01DPFFVN().trim()%>"
					;
					<% if(userPO.getHeader16().equals("3")){out.print("readonly");} %>>
			</td>
			            
         </tr>
        <tr id="trdark"> 
          	<td nowrap width="16%" height="19">
              <div align="right">Frecuencia Vencimiento Interino:</div>
            </td>
            <td nowrap height="19" colspan="3">
      			<INPUT type="text" name="E01DPFFV1" size="5" maxlength="5" 
      			       onKeypress="enterInteger()" 
					value="<%= brnDetails.getE01DPFFV1().trim()%>"
					;
					<% if(userPO.getHeader16().equals("3")){out.print("readonly");} %>>
			</td>	            
         </tr>
       
       
         <tr id="trclear"> 
            <td nowrap width="16%" height="19">
              <div align="right">Unidad Vencimiento:</div>
            </td>
            <td nowrap height="19" colspan="3">
			<SELECT name="E01DPFUVN" >
					<OPTION value=""
						<%if (brnDetails.getE01DPFUVN().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="1" 
					     <%if (brnDetails.getE01DPFUVN().equals("1")) { out.print("selected"); }%>>Dia(s)</OPTION>
					<OPTION value="2" 
					     <%if (brnDetails.getE01DPFUVN().equals("2")) { out.print("selected"); }%>>Semana(s)</OPTION>
					<OPTION value="3" 
					     <%if (brnDetails.getE01DPFUVN().equals("3")) { out.print("selected"); }%>>Mes(es)</OPTION>
					<OPTION value="4" 
					     <%if (brnDetails.getE01DPFUVN().equals("4")) { out.print("selected"); }%>>Año(s)</OPTION>
			</SELECT>
			</td>
         </tr>
    	<tr id="trdark">
		    <td nowrap width="16%" height="19">
				<DIV align="right">Presentar Comparativo :</DIV>
			</td>
			<td nowrap height="19" colspan="3">
								<input type="radio" name="E01DPFCMP"  value="1" <%if(brnDetails.getE01DPFCMP().equals("1")) out.print("checked");%>>
              					Sí 
              					<input type="radio" name="E01DPFCMP" value="0" 	<%if(!brnDetails.getE01DPFCMP().equals("1")) out.print("checked");%>>
              					No 
			</td>
		</tr>
		
       <tr id="trclear">
		    <td nowrap width="16%" height="19">
				<DIV align="right">Formato Automatico :</DIV>
			</td>
			<td nowrap height="19" colspan="3">
								<input type="radio" name="E01DPFAUT"  value="1" <%if(brnDetails.getE01DPFAUT().equals("1")) out.print("checked");%>>
              					Sí 
              					<input type="radio" name="E01DPFAUT" value="0" 	<%if(!brnDetails.getE01DPFAUT().equals("1")) out.print("checked");%>>
              					No 
			</td>
		</tr>
          </table>
      </td>
    </tr>
  </table>
          <div align="center"> 
	        <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm()"> 
            <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel()">
	</div>
          
          <script language="JavaScript">
  		  
 		  
  		  </script>
          
  </form>
</body>
</html>

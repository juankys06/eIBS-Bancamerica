<html>
<head>
<title>Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>

<jsp:useBean id="l0725" class="datapro.eibs.beans.EDP072501Message"  scope="session" />

<jsp:useBean id="l0723" class="datapro.eibs.beans.EDP072301Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<body onload=javascript:init()>

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

// alert(document.forms[0].ACTION.value);

var op = document.forms[0].ACTION.value
	switch (op) {
	case  "1": 	
      ok = confirm("Esta seguro que desea crear este Titular?");
	       document.forms[0].SCREEN.value="450";
	       document.forms[0].submit();
		break; 
	case  "2":  
    	  ok = confirm("Esta seguro que desea modificar este Titular?");
	    	   document.forms[0].SCREEN.value="450";
	    	   document.forms[0].submit();
		break;   
	case  "3":  
    	  ok = confirm("Esta seguro que desea eliminar este Titular?");
	    	   document.forms[0].SCREEN.value="430";
	    	   document.forms[0].submit();
		break;
	};

}

function init()  
{


}


function goCancel() {

document.forms[0].SCREEN.value="440";
document.forms[0].submit(); 
	  		  
}

</script>

<H3 align="center">Titulares por Garantía<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="guarantee_titulares.jsp, EDP0725"></H3>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0725" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="<%= userPO.getHeader17().trim()%>">

  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
      
      
      </td>
    </tr>
  </table>
   
   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E02DPCCUN" size="12" readonly value="<%= userPO.getCusNum()%>">
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E02CUSNA1" size="45" readonly value="<%=userPO.getCusName()%>">
      </td>         
    </tr> 
    <tr id="trdark"> 
      <td align="right"  >
         <b>Nro. Propuesta :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E02DPTNPR" size="15" readonly value="<%=l0723.getE02DPTNPR().trim()%>">
      </td>
      <td nowrap align="right">
         <b>Secuencia Garantía:</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E02DPTSEQ" size="5" readonly value="<%=userPO.getHeader7()%>">
      </td>
    </tr>  

  </table> 
<% if(userPO.getHeader17().equals("1")){ %>
  <h4>Crear Titular</h4>
<% } %> 
<% if(userPO.getHeader17().equals("2")){ %>
  <h4>Modificar Titular</h4>
<% } %> 
<% if(userPO.getHeader17().equals("3")){ %>
  <h4>Borrar Titular</h4>
<% } %> 

<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
 		 <tr id="trclear"> 
            <td nowrap width="5%" height="19" align="right">Identificación Titular</td>
            
            <td nowrap height="19" width="55%">
      			<INPUT type="text" name="E02DPTIDN" size="15" maxlength="15"
					value="<%= l0723.getE02DPTIDN().trim()%>";
					<% if((userPO.getHeader17().equals("3"))||(userPO.getHeader17().equals("5"))){out.print("readonly");} %> 
			>
			<A href="javascript:GetCustomerDescId('E02DPTIDN','E02DPTNOM','')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ."
					align="absmiddle" border="0"></A></td>
			
			<td nowrap height="19" width="2%"> 
			<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>	
         </tr>
          <tr id="trdark"> 
            <td nowrap width="16%" height="23"> 
              <div align="right">Nombre Titular:</div>
            </td>
            <td nowrap height="23" colspan="1" width="372"> 

      			<INPUT type="text" name="E02DPTNOM" size="50" maxlength="50"
					value="<%= l0723.getE02DPTNOM().trim()%>";
					<% if((userPO.getHeader17().equals("3"))||(userPO.getHeader17().equals("5"))){out.print("readonly");} %> >
			</td>
            <td nowrap height="23" colspan="1" width="14"> 
              
            </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            
          </tr>

 		 <tr id="trclear"> 
            <td nowrap width="5%" height="19" align="right">Fecha de Registro:</td>
            
            <td nowrap height="19" width="55%">
			<INPUT type="text" name="E02DPTCND" size="2" maxlength="2"
					value="<%= l0723.getE02DPTCND().trim()%>"
					<% if((userPO.getHeader17().equals("3"))||(userPO.getHeader17().equals("5"))){out.print("readonly");} %> >
			<INPUT type="text" name="E02DPTCNM" size="2" maxlength="2"
					value="<%= l0723.getE02DPTCNM().trim()%>"
					<% if((userPO.getHeader17().equals("3"))||(userPO.getHeader17().equals("5"))){out.print("readonly");} %> >
			<INPUT type="text" name="E02DPTCNY" size="2" maxlength="2" value="<%= l0723.getE02DPTCNY().trim()%>"
					<% if((userPO.getHeader17().equals("3"))||(userPO.getHeader17().equals("5"))){out.print("readonly");} %> >
					<% if(!userPO.getHeader17().equals("5")){ %>
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"  >
              <a href="javascript:DatePicker(document.forms[0].E02DPTCND,document.forms[0].E02DPTCNM,document.forms[0].E02DPTCNY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
					<% } %>
					</td>
			
			<td nowrap height="19" width="2%"> 
			<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>	
         </tr>
 		 <tr id="trdark"> 
            <td nowrap width="5%" height="19" align="right">Porcentaje de Participación:</td>
            
            <td nowrap height="19" width="55%">
            <INPUT type="text" name="E02DPTRTE" size="10" maxlength="10" onkeypress="enterDecimal()"
					value="<%= l0723.getE02DPTRTE().trim()%>"
					<% if((userPO.getHeader17().equals("3"))||(userPO.getHeader17().equals("5"))){out.print("readonly");} %> >
			</td>
			
			<td nowrap height="19" width="2%"> 
			<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>	
         </tr>

	</table>
    </tr>

</table>

<div align="left">
</div>

<div align="center"> 
	        <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm()"> 
            <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel()">
</div>
          
          <script language="JavaScript">
  		  
  		  <% if(userPO.getHeader17().equals("1")){out.print("document.forms[0].E02DPTIDN.focus()");} %>
  		  <% if(userPO.getHeader17().equals("2")){out.print("document.forms[0].E02DPTNOM.focus()");} %>

  		  </script>

  </form>
</body>
</html>

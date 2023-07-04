<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "optCN05" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCN19" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id="l0725" class="datapro.eibs.beans.EDP072501Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

	builtNewMenu(pc_optionGaran);


function callInfF() {
var customer = document.forms[0].E01DPGIDN.value;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=100&CUSTOMER="+ customer;
    	CenterNamedWindow(page,'Information',700,600,2);

}


function goConfirm() {

// alert(document.forms[0].ACTION.value);
	if(document.forms[0].ACTION.value == "6"){
      ok = confirm("Esta seguro que desea crear este Garante?");
	       document.forms[0].SCREEN.value="380";
	       document.forms[0].submit();
    } else {
		if(document.forms[0].ACTION.value == "7"){
    	  ok = confirm("Esta seguro que desea modificar este Garante?");
	    	   document.forms[0].SCREEN.value="380";
	    	   document.forms[0].submit();
 	   }
    }  
}

function init(){  

//var boxLength = document.forms[0].E01DPGTIP.length;
//var i=0;
//var thisvalue;
 
//if (boxLength != 0) {
//for (i = 0; i < boxLength; i++) {
//thisvalue = document.forms[0].E01DPGTIP.options[i].value;
//if (thisvalue == document.forms[0].TYPEG.value) {
//	document.forms[0].E01DPGTIP.selectedIndex=i;
//	break;
//   }
//}
//}

}


function goCancel() {

document.forms[0].SCREEN.value="200";
document.forms[0].submit(); 
	  		  
}

</script>
</HEAD>
<body onload=javascript:init()>

<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>


<H3 align="center">Aval/Co-Solicitante/Fiador<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="garante_maintenance.jsp, EDP0725"></H3>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0725" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="ACTION" VALUE="<%= userPO.getHeader16().trim()%>">
  <INPUT TYPE=HIDDEN NAME="TYPEG" VALUE="<%= l0725.getE01DPGTIP().trim()%>">

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
         <input type="text" name="E01DPCCUN" size="12" readonly value="<%= userPO.getCusNum()%>">
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E01CUSNA1" size="45" readonly value="<%=userPO.getCusName()%>">
      </td>         
    </tr> 
    <tr id="trdark"> 
      <td align="right"  >
         <b>Nro. Propuesta :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01DPCNPR" size="15" readonly value="<%=userPO.getHeader4()%>">
      </td>
      <td nowrap align="right">
         <b>Secuencia:</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E01DPCSEQ" size="5" readonly value="<%=userPO.getHeader7()%>">
      </td>
    </tr>  

  </table> 

<% if(l0725.getE01DPGCPR().equals("80")){ %>
   <BR>
<TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trclear"> 
      <td align="right"  >
         <b>Número de Cuenta:</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01DPGACC" size="12" readonly value="<%=l0725.getE01DPGACC().trim()%>">
      </td>
      <td align="right"  >
         <b>Line Com/Number:</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E01DPGLNU" size="5" readonly value="<%=l0725.getE01DPGLNU().trim()%>">
      </td>         
    </tr> 
    <tr id="trdark"> 
      <td align="right"  >
         <b>Código Aplicación:</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01DPGACD" size="3" readonly value="<%=l0725.getE01DPGACD().trim()%>">
      </td>
      <td nowrap align="right">
         <b>Estado:</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E01DPGCPR" size="15" readonly value="<%=l0725.getE01DPGCPR().trim()%>">
      </td>
    </tr>  

  </table> 
<% } %>


  <h4><% if(userPO.getHeader16().equals("7")){out.print("Modificar Garante");} else {out.print("Crear Garante:"); } %> </h4> 

<table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
            			
 		 <tr id="trclear"> 
            <td nowrap width="5%" height="19" align="right">Identificación </td>
            
            <td nowrap height="19" width="55%">
      			<INPUT type="text" name="E01DPGIDN" size="15" maxlength="15" value="<%= l0725.getE01DPGIDN().trim()%>";
					<% if(userPO.getHeader16().equals("9")){out.print("readonly");} %> 
			>
			<A href="javascript:GetCustomerDescId('E01DPGIDN','E01DPGDES','E01DPGIDN')"><IMG
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
              <div align="right">Nombre :</div>
            </td>
            <td nowrap height="23" colspan="1" width="372"> 

      			<INPUT type="text" name="E01DPGDES" size="50" maxlength="50"
					value="<%= l0725.getE01DPGDES().trim()%>";
					<% if(userPO.getHeader16().equals("9")){out.print("readonly");} %> >
			</td>
            <td nowrap height="23" colspan="1" width="14"> 
              
            </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            <td nowrap height="23" colspan="1"> 
              
            </td>
            
          </tr>
<%--
<div id="FechaRegistro" style="display:none">
   <table class="tableinfo">


 		 <tr id="trclear"> 

            <td nowrap width="5%" height="19" align="right">Fecha de Registro:</td>
            
            <td nowrap height="19" width="55%">
			<INPUT type="text" name="E01DPGIPD" size="2" maxlength="2"
					value="<%= l0725.getE01DPGIPD().trim()%>"
					<% if(userPO.getHeader16().equals("9")){out.print("readonly");} %>> 
			<INPUT type="text" name="E01DPGIPM" size="2" maxlength="2"
					value="<%= l0725.getE01DPGIPM().trim()%>"
					<% if(userPO.getHeader16().equals("9")){out.print("readonly");} %>>
			<INPUT type="text" name="E01DPGIPY" size="2" maxlength="2" value="<%= l0725.getE01DPGIPY().trim()%>"
					<% if(userPO.getHeader16().equals("9")){out.print("readonly");} %>>
					<% if(!userPO.getHeader16().equals("9")){ %>
            <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"  >
              <a href="javascript:DatePicker(document.forms[0].E01DPGIPD,document.forms[0].E01DPGIPM,document.forms[0].E01DPGIPY)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
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
            <INPUT type="text" name="E01DPGRTE" size="10" maxlength="10" onkeypress="enterDecimal()"
					value="<%= l0725.getE01DPGRTE().trim()%>"
					<% if(userPO.getHeader16().equals("9")){out.print("readonly");} %>></td>
		
			<td nowrap height="19" width="2%"> 
			<div align="right"> </div>
			</td>
		    
		    <td nowrap width="28%" height="19"> 
			<div align="right"> </div>
			</td>	
         </tr>
	</table>
</div>
--%>
		<tr id="trclear">
								<td	nowrap width="30%">
   									<DIV align="right">Tipo</DIV>
								</td>
								  
								<td nowrap align="left" width="100%">
									<input type="radio" name="E01DPGCRG" value="1" <%if(l0725.getE01DPGCRG().equals("1")) out.print("checked");%>>
              						Avalista 
              						<input type="radio" name="E01DPGCRG" value="2" <%if(l0725.getE01DPGCRG().equals("2")) out.print("checked");%>>
              						Co-Solicitante 
              						<input type="radio" name="E01DPGCRG" value="3" <%if(l0725.getE01DPGCRG().equals("3")) out.print("checked");%>>
              						Fiador
								</td>
								
								<td nowrap width="25%">	
								</td>						
		</tr>
		
          <tr id="trdark"> 
            <td nowrap >
             Productos a Garantizar:</td>
            <td nowrap > 
            Valor</td>
            <td nowrap >               
            </td>
          </tr>

		<% if(!l0725.getE01DPGP00().equals("")){%>
          <tr id="trclear"> 
            <td nowrap >               
            <INPUT type="text" name="E01DPGP00" size="4" maxlength="5" readonly value="<%= l0725.getE01DPGP00().trim()%>">
            <INPUT type="text" name="E01DPGPD0" size="35" maxlength="35" readonly value="<%= l0725.getE01DPGPD0().trim()%>">
			</td>
            <td nowrap > 
            <INPUT type="text" name="E01DPGA00" size="16" maxlength="16" onkeypress="enterDecimal()" readonly value="<%= l0725.getE01DPGA00().trim()%>" 
            <% if((userPO.getHeader16().equals("9"))){out.print("readonly");} %>
            >
			</td>
            <td nowrap >
            </td>
          </tr>
		<%}%>
		<% if(!l0725.getE01DPGP01().equals("")){%>
          <tr id="trdark"> 
            <td nowrap >               
            <INPUT type="text" name="E01DPGP01" size="4" maxlength="5" readonly value="<%= l0725.getE01DPGP01().trim()%>">
            <INPUT type="text" name="E01DPGPD1" size="35" maxlength="35" readonly value="<%= l0725.getE01DPGPD1().trim()%>">
			</td>
            <td nowrap > 
            <INPUT type="text" name="E01DPGA01" size="16" maxlength="16" onkeypress="enterDecimal()" value="<%= l0725.getE01DPGA01().trim()%>" 
            <% if((userPO.getHeader16().equals("9"))){out.print("readonly");} %>
            >
			</td>
            <td nowrap >
            </td>
          </tr>
		<%}%>
		<% if(!l0725.getE01DPGP02().equals("")){%>
          <tr id="trclear"> 
            <td nowrap >               
            <INPUT type="text" name="E01DPGP02" size="4" maxlength="5" readonly value="<%= l0725.getE01DPGP02().trim()%>">
            <INPUT type="text" name="E01DPGPD2" size="35" maxlength="35" readonly value="<%= l0725.getE01DPGPD2().trim()%>">
			</td>
            <td nowrap > 
            <INPUT type="text" name="E01DPGA02" size="16" maxlength="16" onkeypress="enterDecimal()" value="<%= l0725.getE01DPGA02().trim()%>" <% if(userPO.getHeader16().equals("7")){out.print("readonly");} %>
            >
			</td>
            <td nowrap >
            </td>
          </tr>
		<%}%>
		<% if(!l0725.getE01DPGP03().equals("")){%>
          <tr id="trdark"> 
            <td nowrap >               
            <INPUT type="text" name="E01DPGP03" size="4" maxlength="5" readonly value="<%= l0725.getE01DPGP03().trim()%>">
            <INPUT type="text" name="E01DPGPD3" size="35" maxlength="35" readonly value="<%= l0725.getE01DPGPD3().trim()%>">
			</td>
            <td nowrap > 
            <INPUT type="text" name="E01DPGA03" size="16" maxlength="16" onkeypress="enterDecimal()" value="<%= l0725.getE01DPGA03().trim()%>" <% if(userPO.getHeader16().equals("7")){out.print("readonly");} %>
            >
			</td>
            <td nowrap >
            </td>
          </tr>
		<%}%>
		<% if(!l0725.getE01DPGP04().equals("")){%>
          <tr id="trclear"> 
            <td nowrap >               
            <INPUT type="text" name="E01DPGP04" size="4" maxlength="5" readonly value="<%= l0725.getE01DPGP04().trim()%>">
            <INPUT type="text" name="E01DPGPD4" size="35" maxlength="35" readonly value="<%= l0725.getE01DPGPD4().trim()%>">
			</td>
            <td nowrap > 
            <INPUT type="text" name="E01DPGA04" size="16" maxlength="16" onkeypress="enterDecimal()" value="<%= l0725.getE01DPGA04().trim()%>" <% if(userPO.getHeader16().equals("7")){out.print("readonly");} %>
			>
			</td>
            <td nowrap >
            </td>
          </tr>
		<%}%>

          <tr id="trdark"> 
            <td nowrap > 
            </td>
            <td nowrap >               
			</td>
            <td nowrap > 
			</td>
            <td nowrap >
            </td>
          </tr>


          
        </table>

        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="100%" align="center">
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="100%" align="center"> 
			</td>
          </tr>



        </table>
  </table>
<div align="left">
</div>

<div align="center"> 
	        <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm()"> 
            <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel()">
</div>
          
          <script language="JavaScript">
  		  
  		  <% if(userPO.getHeader16().equals("1")){out.print("document.forms[0].E01DPGIDN.focus()");} %>
  		  <% if(userPO.getHeader16().equals("2")){out.print("document.forms[0].E01DPGIPD.focus()");} %>

  		  </script>

  </form>
</body>
</html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 

<TITLE>Consulta Facturas</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "msgList" class= "datapro.eibs.beans.EBP016001Message"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<SCRIPT src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSBillsP.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

function vendors(){
  document.getElementById("E01REQTYP").value = 'V';
  document.getElementById("E01REQTYPC").checked  = false;
  document.getElementById("E01REQTYPP").checked  = false;
}

function customers(){
  document.getElementById("E01REQTYP").value = 'C';
  document.getElementById("E01REQTYPV").checked  = false;
  document.getElementById("E01REQTYPP").checked  = false;
}

function purcharse(){
  document.getElementById("E01REQTYP").value = 'P';
  document.getElementById("E01REQTYPV").checked  = false;
  document.getElementById("E01REQTYPC").checked  = false;
}


function checkInfo(){
  	
   	document.forms[0].SCREEN.value = '2';
	document.forms[0].submit();
 }

</SCRIPT>

</HEAD>

<body>

 <h3 align="center"> Consulta Facturas por Selección<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="bill_inquiry_search, EBP0160"></h3>
<hr size="4">
 <FORM METHOD="POST" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0160">	
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <INPUT TYPE=HIDDEN NAME="E01REQTYP" VALUE="V">
    <INPUT TYPE=HIDDEN NAME="E01REQDTE" VALUE="D">
    <INPUT TYPE=HIDDEN name="VENNAME" VALUE=" " size="30" maxlength="30" >  
    
  <table id="tbenter" align="center" style="width:85%" border="1">
    <tr> 
      <td> 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr>
			<td valign="middle" align="left" colspan="3" height=33>
				<INPUT type="radio" name="E01REQTYPV" value="V" onclick="JavaScript:vendors()"
					<% if (msgList.getE01REQTYP().equals("V")) out.print(" checked"); %>> <B>
					<FONT color="navy"></FONT><FONT color="navy">Facturas Proveedores</FONT></B> <BR>
				<INPUT type="radio" name="E01REQTYPC" value="C" onclick="JavaScript:customers()"
					<% if (msgList.getE01REQTYP().equals("C")) out.print(" checked"); %>> <B>
				    <FONT color="green"></FONT><FONT color="green">Facturas Clientes</FONT></B> <BR>
				<INPUT type="radio" name="E01REQTYPP" value="P" onclick="JavaScript:purcharse()"
					<% if (msgList.getE01REQTYP().equals("P")) out.print(" checked"); %>> <B>
				    <FONT color="olive"></FONT><FONT color="olive">Facturas Ordenes de Compra</FONT></B> <BR>
			</td>
		  </tr>
          <tr> 
            <td width="20%" align="left"> </td>
            <td width="30%" align="right"> Banco : </td>
            <td width="50%" align="left">
            	<INPUT type="text" name="E01REQBNK" size="3" maxlength="2" onkeypress="enterInteger()">
            </td>
          </tr>
          <tr> 
            <td width="20%" align="right"></td>
            <td width="30%" align="right"> Agencia : </td>
            <td width="50%" align="left">
            	<INPUT type="text" name="E01REQBRN" size="4" maxlength="3" onkeypress="enterInteger()">
                 <A href="javascript:GetBranch('E01REQBRN', document.forms[0].E01REQBNK.value)">
                 <IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Help" border="0">
                 </A> (999 = All Branches)
            </td>
          </tr>
          <tr> 
            <td width="20%" align="right"></td>
            <td width="30%" align="right"> Código : </td>
            <td width="50%" align="left">
            	<INPUT type="text" name="E01REQCOD" size="10" maxlength="9" onkeypress="enterInteger()">
            	 <a href="javascript:GetVendorBP('E01REQCOD','VENNAME')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda Proveedores" align="middle" border="0"></a>
                 <a href="javascript:GetCustomerDescId('E01REQCOD','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda Clientes" align="middle" border="0"></a>
                 (0 = Todos los Códigos)
            </td>
          </tr>
          <tr> 
            <td width="20%" align="right"></td>
            <td width="30%" align="right"> Estatus Factura : </td>
            <td width="50%" align="left">
             	<select name="E01REQSTS">
             		<option <%= msgList.getE01REQSTS().trim().equals("T")?"selected":"" %> value="T">Todas</option>
			    	<option <%= msgList.getE01REQSTS().trim().equals("W")?"selected":"" %> value="W">Pendientes por Aprobación</option>
				    <option <%= msgList.getE01REQSTS().trim().equals("A")?"selected":"" %> value="A">Activa (Aprobada)</option>
				    <option <%= msgList.getE01REQSTS().trim().equals("P")?"selected":"" %> value="P">Pagada</option>
				    <option <%= msgList.getE01REQSTS().trim().equals("S")?"selected":"" %> value="S">Suspendida</option>
				    <option <%= msgList.getE01REQSTS().trim().equals("R")?"selected":"" %> value="R">Rechazada</option>
			    </select>
            </td>
          </tr>
          <tr> 
            <td width="20%" align="right"></td>
            <td width="30%" align="right"> Fecha : </td>
            <td width="50%" align="left">
            	<input type="radio" name="E01REQDTED" value="D" <% if (msgList.getE01REQDTE().equals("D")) out.print("checked"); %>>											
                Fecha Vencimiento Pago
            	<input type="radio" name="E01REQDTEP" value="P" <% if (msgList.getE01REQDTE().equals("P")) out.print("checked"); %>>
                Fecha Ultimo Pago
                
            </td>
          </tr>
          <tr>
            <td width="20%" align="center"> </td>
			<td width="30%" align="right">Fecha Desde : </td>
			<td width="50%" align="left">
            	<INPUT type="text" size="2" maxlength="2" name="E01REQDF1" onkeypress="enterInteger()">
            	<INPUT type="text" size="2" maxlength="2" name="E01REQDF2" onkeypress="enterInteger()">
            	<INPUT type="text" size="2" maxlength="2" name="E01REQDF3" onkeypress="enterInteger()">
            		<A href="javascript:DatePicker(document.forms[0].E01REQDF1,document.forms[0].E01REQDF2,document.forms[0].E01REQDF3)">
					<IMG src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></A>
			<B><%=currUser.getE01DTF()%></B></td>  
          </tr>
          <tr> 
            <td width="20%" align="center"></td>
				<td width="30%" align="right">Fecha Hasta : </td>
				<td width="50%" align="left"> 
              <input type="text" size="2" maxlength="2" name="E01REQDT1" onKeypress="enterInteger()">
              <input type="text" size="2" maxlength="2" name="E01REQDT2" onKeyPress="enterInteger()">
              <input type="text" size="2" maxlength="2" name="E01REQDT3" onKeyPress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01REQDT1,document.forms[0].E01REQDT2,document.forms[0].E01REQDT3)">
              <img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></a> 
            <B><%=currUser.getE01DTF()%></B></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <table id="tbenter" align="center" style="width:85%">
   		<tr>
  			<td width="85%"> 
  		  		<div align="center"> 
     				<input id="EIBSBTN" type="Button" name="Process" value="Enviar" onClick="JavaScript:checkInfo()">
     	  		</div>	
  			</td>
  		</tr>	
  </table>

<SCRIPT language="JavaScript">

	<% if (currUser.getE01DTF().equals("MDY")) { %>
			document.getElementById("E01REQDF1").value = '<%=currUser.getE01RDM()%>';
    		document.getElementById("E01REQDF2").value = '<%=currUser.getE01RDD()%>';
    		document.getElementById("E01REQDF3").value = '<%=currUser.getE01RDY()%>';
    		document.getElementById("E01REQDT1").value = '<%=currUser.getE01RDM()%>';
    		document.getElementById("E01REQDT2").value = '<%=currUser.getE01RDD()%>';
    		document.getElementById("E01REQDT3").value = '<%=currUser.getE01RDY()%>';
    <% } else if (currUser.getE01DTF().equals("DMY")) { %>    
    		document.getElementById("E01REQDF1").value = '<%=currUser.getE01RDD()%>';
    		document.getElementById("E01REQDF2").value = '<%=currUser.getE01RDM()%>';
			document.getElementById("E01REQDF3").value = '<%=currUser.getE01RDY()%>';
			document.getElementById("E01REQDT1").value = '<%=currUser.getE01RDD()%>';
    		document.getElementById("E01REQDT2").value = '<%=currUser.getE01RDM()%>';
    		document.getElementById("E01REQDT3").value = '<%=currUser.getE01RDY()%>';  	
    	   			
    <% } else { %>
    		document.getElementById("E01REQDF1").value = '<%=currUser.getE01RDY()%>';
    		document.getElementById("E01REQDF2").value = '<%=currUser.getE01RDM()%>';
    	   	document.getElementById("E01REQDF3").value = '<%=currUser.getE01RDD()%>'; 
    	   	document.getElementById("E01REQDT1").value = '<%=currUser.getE01RDY()%>';
    		document.getElementById("E01REQDT2").value = '<%=currUser.getE01RDM()%>';
    	   	document.getElementById("E01REQDT3").value = '<%=currUser.getE01RDD()%>'; 
    <% } %>			
	
	document.getElementById("E01REQSTS").value = 'P';
	document.getElementById("E01REQTYPV").checked = true;
	document.getElementById("E01REQDTED").checked = true;
	document.getElementById("E01REQDTEP").checked = false;    			
	document.getElementById("E01REQBNK").value = '<%=currUser.getE01UBK()%>';
	document.getElementById("E01REQBRN").value = '<%=currUser.getE01UBR()%>';
	
</SCRIPT>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %> <SCRIPT language="Javascript">
            showErrors();
     </SCRIPT> <%
 }
 %>
</FORM>
</BODY>
</HTML>
 
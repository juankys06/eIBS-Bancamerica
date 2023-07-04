<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css"> 

<TITLE>Bills</TITLE>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "msgList" class= "datapro.eibs.beans.EBP013001Message"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSBillsP.jsp"> </SCRIPT>
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<SCRIPT Language="javascript">


function ordCustomer(){
  document.getElementById("E01REQORD").value = 'C';
  document.getElementById("E01REQORDD").checked = false;
  document.getElementById("E01REQORDN").checked = false;
  document.getElementById("E01REQORDB").checked = false;
  document.getElementById("E01REQNUM").value='0';
  document.getElementById("E01REQBIL").value='0';
  document.getElementById("E01REQCUS").value='0';
}

function ordDate(){
  document.getElementById("E01REQORD").value = 'D';
  document.getElementById("E01REQORDC").checked = false;
  document.getElementById("E01REQORDN").checked = false;
  document.getElementById("E01REQORDB").checked = false;
  document.getElementById("E01REQCUS").value='0';
  document.getElementById("E01REQNUM").value='0';
  document.getElementById("E01REQBIL").value=' ';
}

function ordNumber(){
  document.getElementById("E01REQORD").value = 'N';
  document.getElementById("E01REQORDC").checked = false;
  document.getElementById("E01REQORDD").checked = false;
  document.getElementById("E01REQORDB").checked = false;
  document.getElementById("E01REQCUS").value='0';
  document.getElementById("E01REQBIL").value=' ';
  document.getElementById("E01REQNUM").value='0';
}

function ordBill(){
  document.getElementById("E01REQORD").value = 'B';
  document.getElementById("E01REQORDC").checked = false;
  document.getElementById("E01REQORDD").checked = false;  
  document.getElementById("E01REQORDN").checked = false;  
  document.getElementById("E01REQCUS").value='0';
  document.getElementById("E01REQNUM").value='0';
  document.getElementById("E01REQBIL").value=' ';
}

function stsPending(){
  document.getElementById("E01REQSTS").value = 'W';
  document.getElementById("E01REQSTSR").checked = false;
  document.getElementById("E01REQSTSA").checked = false;
}

function stsRejected(){
  document.getElementById("E01REQSTS").value = 'R';
  document.getElementById("E01REQSTSW").checked = false;
  document.getElementById("E01REQSTSA").checked = false;
}

function stsApproved(){
  document.getElementById("E01REQSTS").value = 'A';
  document.getElementById("E01REQSTSW").checked = false;
  document.getElementById("E01REQSTSR").checked = false;
}

function checkInfo(){
  	if (document.getElementById("E01REQORD").value == "C") {
		document.getElementById("E01REQFRM").value = document.getElementById("E01REQCUS").value;
	} else if (document.getElementById("E01REQORD").value == "N") {
		document.getElementById("E01REQFRM").value = document.getElementById("E01REQNUM").value;
	} else if (document.getElementById("E01REQORD").value == "B") {
		document.getElementById("E01REQFRM").value = document.getElementById("E01REQBIL").value;
	} 
   	document.forms[0].SCREEN.value = '2';
	document.forms[0].submit();
 }

</SCRIPT>

</HEAD>

<body>

 <h3 align="center">Facturas
 	<%	if (msgList.getE01REQTYP().equals("V")) { 
		 	out.println(" Proveedor "); 
		} else if (msgList.getE01REQTYP().equals("C")) {
		    out.println(" Cliente "); 
		   	} else { out.println(" Orden de Compra ");
		}  	
	%> <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="bills_search, EBP0130"></h3>
<hr size="4">
 <FORM METHOD="POST" action="<%=request.getContextPath()%>/servlet/datapro.eibs.bap.JSEBP0130">	
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
    <INPUT TYPE=HIDDEN NAME="E01REQTYP" VALUE="<%=msgList.getE01REQTYP()%>"> 
    <INPUT TYPE=HIDDEN NAME="E01REQORD" VALUE="N">
    <INPUT TYPE=HIDDEN NAME="E01REQSTS" VALUE="W">
    <INPUT TYPE=HIDDEN NAME="E01REQFRM" VALUE="0">
    
  <table id="tbenter" align="center" style="width:85%" border="1">
    <tr> 
      <td> 
        <table id="tbenter" width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr> 
            <td width="20%" align="right"><B>Orden Por : </B></td>
            <td width="30%"> </td>
            <td width="50%" align="left"><B>Desde : </B></td>
          </tr>
          <tr>
            <td width="20%" align="center"> </td>
            <td width="30%" align="left">
				 <INPUT type="radio" name="E01REQORDC" value="C" onclick="JavaScript:ordCustomer()"
					<% if (msgList.getE01REQORD().equals("C")) out.print(" checked"); %>
				  > Vendor/Customer Number :
			</td>
            <td width="50%" align="left">
              <input type="text" name="E01REQCUS"  size=10 maxlength="9" onKeyPress="enterInteger()">
			  <a href="javascript:GetVendorBP('E01REQCUS','VENNAME')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Vendor Help" align="middle" border="0"></a>
              <a href="javascript:GetCustomerDescId('E01REQCUS','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Customer Help" align="middle" border="0"></a>
			<INPUT type="text" name="VENNAME" size="30" maxlength="30" readonly></td>  
          </tr>
          <tr> 
            <td width="20%" align="center"></td>
            <td width="30%" align="left">
				<INPUT type="radio" name="E01REQORDD" value="D" onclick="JavaScript:ordDate()"
					<% if (msgList.getE01REQORD().equals("D")) out.print(" checked"); %>
				> Fecha Vencimiento de Pago :
				</td>
            <td width="50%" align="left"> 
              <input type="text" size="2" maxlength="2" name="E01REQDA1" onKeypress="enterInteger()">
              <input type="text" size="2" maxlength="2" name="E01REQDA2" onKeyPress="enterInteger()">
              <input type="text" size="2" maxlength="2" name="E01REQDA3" onKeyPress="enterInteger()">
              <a href="javascript:DatePicker(document.forms[0].E01REQDA1,document.forms[0].E01REQDA2,document.forms[0].E01REQDA3)">
              <img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="middle" border="0"></a> 
            <B><%=currUser.getE01DTF()%></B>
            </td>
          </tr>
          <tr> 
            <td width="20%" align="center"></td>
            <td width="30%" align="left">
            	<INPUT type="radio" name="E01REQORDN" value="N" onclick="JavaScript:ordNumber()"
					<% if (msgList.getE01REQORD().equals("N")) out.print(" checked"); %>
					>  Número Interno :
			</td>
            <td width="50%" align="left">
            	<INPUT type="text" name="E01REQNUM" size="10" maxlength="9" onkeypress="enterInteger()">
            </td>
          </tr>
          <tr> 
            <td width="20%" align="center"></td>
            <td width="30%" align="left">
				<INPUT type="radio" name="E01REQORDB" value="B" onclick="JavaScript:ordBill()"
					<% if (msgList.getE01REQORD().equals("B")) out.print(" checked"); %>
					> Número Factura :
			</td>
            <td width="50%" align="left"> 
                <input type="text" name="E01REQBIL"  size=26 maxlength="25" >
            </td>
          </tr>
          <tr>
			<td width="20%" align="right"><B>Con Estatus :</B></td>
			<td width="30%" align="left"></td>
            <td width="50%" align="left"></td>
          </tr>
          <tr> 
            <td width="20%" align="center"></td>
            <td width="30%" align="left">
				<INPUT type="radio" name="E01REQSTSW" value="W" onclick="JavaScript:stsPending()"
					<% if (msgList.getE01REQSTS().equals("W")) out.print(" checked"); %>
				> Pendiente por Aprobación
			</td>
            <td width="50%" align="left"></td>
          </tr>
          <tr> 
            <td width="20%" align="center"></td>
            <td width="30%" align="left">
				<INPUT type="radio" name="E01REQSTSR" value="R" onclick="JavaScript:stsRejected()"
					<% if (msgList.getE01REQSTS().equals("R")) out.print(" checked"); %>
				> Rechazado
			</td>
            <td width="50%" align="left"></td>
          </tr>
          <tr> 
            <td width="20%" align="center"></td>
            <td width="30%" align="left">
				<INPUT type="radio" name="E01REQSTSA" value="A" onclick="JavaScript:stsApproved()"
					<% if (msgList.getE01REQSTS().equals("A")) out.print(" checked"); %>
				> Activa (Aprobada)
			</td>
            <td width="50%" align="left"></td>
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
			document.getElementById("E01REQDA1").value = '<%=currUser.getE01RDM()%>';
    		document.getElementById("E01REQDA2").value = '<%=currUser.getE01RDD()%>';
    		document.getElementById("E01REQDA3").value = '<%=currUser.getE01RDY()%>';
    		
    <% } else if (currUser.getE01DTF().equals("DMY")) { %>    
    		document.getElementById("E01REQDA1").value = '<%=currUser.getE01RDD()%>';
    		document.getElementById("E01REQDA2").value = '<%=currUser.getE01RDM()%>';
    	   	document.getElementById("E01REQDA3").value = '<%=currUser.getE01RDY()%>';
    			
    <% } else { %>
    		document.getElementById("E01REQDA1").value = '<%=currUser.getE01RDY()%>';
    		document.getElementById("E01REQDA2").value = '<%=currUser.getE01RDM()%>';
    	   	document.getElementById("E01REQDA3").value = '<%=currUser.getE01RDD()%>'; 
    	   	
    <% } %>	
  document.getElementById("E01REQORDN").checked = true;
  document.getElementById("E01REQSTSW").checked = true;
  ordNumber();
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
 
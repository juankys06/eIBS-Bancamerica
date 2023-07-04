<html>
<head>
<title>Firmantes</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "rtFirm" class= "datapro.eibs.beans.ESD000004Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

<%
if ( userPO.getOption().equals("RT") ) {
%>
	builtNewMenu(rt_i_opt);
<%   
}
else if ( userPO.getOption().equals("SV") ) {
%>
	builtNewMenu(sv_i_opt);
<%   
}
%>

var divID="1";


function GetSignerByCustomer(customer,name,idnumber,idtype,idcountry,address2,address3,address4,city,state,country,pob,zip,citizen,prof,gender,marital,phone,signerId){

    // Coloca el ID en cuestion
    divID=signerId;
    
    // Limpia flag de control de pais
    if (document.getElementById('E'+divID+'4FL2') )
      document.getElementById('E'+divID+'4FL2').value='';
    
    // Invoca Servlet para llenar info. del firmante
    GetCustomerDetails(customer,name,idnumber,idtype,idcountry,address2,address3,address4,city,state,country,pob,zip,citizen,prof,gender,marital,phone);
    
}

function displayDir(){
    
  
    if ( (document.getElementById('E'+divID+'4FL2') && document.getElementById('E'+divID+'4FL2').value == '1' ) ||
          (document.getElementById('E'+divID+'4CTR') && document.getElementById('E'+divID+'4CTR').value.toUpperCase() == 'PANAMA')){
        // muestra div de Panama
      showDiv('PADSCMA2'+ divID);
      hideDiv('EXDSCMA2'+ divID);
      showDiv('PADSCMA3'+ divID);
      showDiv('PADSCMA4'+ divID);
      //showDiv('PADSCTID'+ divID);
      //showDiv('PADSCPID'+ divID);
      showDiv('PATID'+ divID);
      showDiv('PAPID'+ divID);
      showDiv('PADSCSTE'+ divID);
      hideDiv('EXDSCSTE'+ divID);
      showDiv('PAID'+divID);
      hideDiv('EXID'+divID);
      if(document.getElementById('E'+divID+'4ID').value != ''){
        document.getElementById('E'+divID+'4NME').value=document.getElementById('E'+divID+'4ID').value;
      }  
      //document.getElementById('E'+divID+'4BNI').value='';
       
    }
      else {
     
      hideDiv('PADSCMA2'+ divID);
      showDiv('EXDSCMA2'+ divID);
      hideDiv('PADSCMA3'+ divID);
      hideDiv('PADSCMA4'+ divID);
      //hideDiv('PADSCTID'+ divID);
      //hideDiv('PADSCPID'+ divID);
      document.getElementById('E'+divID+'4PID').value=''; 
      document.getElementById('E'+divID+'4TID').value='';
      document.getElementById('D'+divID+'4PID').value='';
      document.getElementById('D'+divID+'4TID').value='';
      hideDiv('PATID'+ divID);
      hideDiv('PAPID'+ divID);
      //document.getElementById('E'+divID+'4NME').value='';
      if(document.getElementById('E'+divID+'4ID').value != ''){
        document.getElementById('E'+divID+'4BNI').value=document.getElementById('E'+divID+'4ID').value;
      }  
      hideDiv('PADSCSTE'+ divID);
      showDiv('EXDSCSTE'+ divID);
      hideDiv('PAID'+divID);
      showDiv('EXID'+divID);
    }   
    
 }



function hideDiv(id) {
	//safe function to hide an element with a specified id
	if (document.getElementById) { // DOM3 = IE5, NS6
		document.getElementById(id).style.display = 'none';
	}
	else {
		if (document.layers) { // Netscape 4
			document.id.display = 'none';
		}
		else { // IE 4
			document.all.id.style.display = 'none';
		}
	}
}

function showDiv(id) {
	//safe function to show an element with a specified id
		  
	if (document.getElementById) { // DOM3 = IE5, NS6
		document.getElementById(id).style.display = 'block';
	}
	else {
		if (document.layers) { // Netscape 4
			document.id.display = 'block';
		}
		else { // IE 4
			document.all.id.style.display = 'block';
		}
	}
}


</SCRIPT>

</head>

<body bgcolor="#FFFFFF">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
  
    out.println("<SCRIPT> initMenu(); </SCRIPT>");
 
%>

<h3 align="center">Firmantes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="rt_inq_firm_pa.jsp, EDD0000"></h3>
<hr size="4">
<FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000" >
  <INPUT TYPE="hidden" NAME="SCREEN" VALUE="20">  
  <INPUT TYPE="hidden" NAME="COUNTRY" VALUE="PA">
  <INPUT TYPE="hidden" NAME="E04RTP" VALUE="S"> 
  <INPUT TYPE="hidden" NAME="H04SCR" VALUE="07">
<p>
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
                <input type="text" name="E02CUN" size="9" maxlength="9" readonly value="<%= userPO.getHeader2().trim()%>">
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left"> 
                <input type="text" name="E02NA1" size="45" maxlength="45" readonly value="<%= userPO.getHeader3().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" name="E04CUN" size="12" maxlength="12" value="<%= rtFirm.getE04CUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="3" maxlength="3" value="<%= userPO.getCurrency().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <div id="dataDiv" align="center" style="height:351; overflow-y:scroll; width:90%; padding-left:50; padding-right:10" class="scbarcolor">
<%
  
  String name;
  for ( int i=1; i<10; i++ ) {
    if ( i == 10 ) {
      name = "A"; 
    }
    else {
      name = i + "";
    }
    
    out.println("<table class=\"tbenter\" border=\"0\" width=\"100%\">");
    out.println("<tr>");
    
    switch ( i ) {
        case 1 :     
%> 
 <td align="left"><h4>Primero</h4></td>
<%          
           break;    
        case 2 :     
%> 
 <td align="left"><h4>Segundo</h4></td>
<%          
           break;    
        case 3 :     
%> 
 <td align="left"><h4>Tercero</h4></td>
<%          
           break;    
        case 4 :     
%> 
 <td align="left"><h4>Cuarto</h4></td>
<%          
           break;    
        case 5 :     
%> 
 <td align="left"><h4>Quinto</h4></td>
<%          
           break;    
        case 6 :     
%> 
 <td align="left"><h4>Sexto</h4></td>
<%          
           break;    
        case 7 :     
%> 
 <td align="left"><h4>S&eacute;ptimo</h4></td>
<%          
           break;
        case 8 : 
%> 
 <td align="left"><h4>Octavo</h4></td>
<%          
           break;
        case 9 : 
%> 
<td align="left"><h4>Noveno</h4></td>
<%          
           break;
        case 'A' : 
%> 
<td align="left"><h4>D&eacute;cimo</h4></td>
<%          
           break;
     }
  out.println("<td width=\"30%\" align=\"right\">");
  out.println("<h4>");
  out.print("<A href=\"javascript:go(1)\">1</A>,<A href=\"javascript:go(2)\">2</A>,<A href=\"javascript:go(3)\">3</A>,<A href=\"javascript:go(4)\">4</A>,<A href=\"javascript:go(5)\">5</A>");
 out.print("<A href=\"javascript:go(6)\">6</A>,<A href=\"javascript:go(7)\">7</A>,<A href=\"javascript:go(8)\">8</A>,<A href=\"javascript:go(9)\">9</A>,<A href=\"javascript:go(10)\">10</A>");
   out.println("</h4>"); 
  out.println("</td>");   
  out.println("</tr>");
  out.println("</table>"); 
%>
  <input type="hidden" id="E<%=name%>4FL2" value="<%=rtFirm.getField("E" + name + "4FL2").getString().trim()%>" />
  <input type="hidden" id="E<%=name%>4ID" value="" />
  <table id="mainTable<%= name %>" class="tableinfo">
    <tr bgcolor="#FFFFFF" bordercolor="#FFFFFF"> 
      <td> 
        <table  cellspacing="0" cellpadding="2" width="100%" border="0" name="TB_rtFirmFICIARY_CORP" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">
        <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Número de Cliente : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E<%= name %>4RCN" maxlength="12" size="12" value="<%= rtFirm.getField("E" + name + "4RCN").getString().trim()%>" readonly>
			 
            </td>
          </tr>

	     <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Nombre Legal : </div>
            </td>
            <td nowrap> 
              <input type="text" name="E<%= name %>4MA1" maxlength="45" size="46" value="<%= rtFirm.getField("E" + name + "4MA1").getString().trim()%>" readonly>
            </td>
          </tr>
          
<%-- INICIO SECCION DE DIRECCION --%>

          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div id="EXDSCMA2<%=name%>" align="right" style="display:none"> Direcci&oacute;n :</div>
              <div id="PADSCMA2<%=name%>" align="right" > Calle :</div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4MA2" maxlength="35" size="36" value="<%= rtFirm.getField("E" + name + "4MA2").getString().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right"><div id="PADSCMA3<%=name%>" align="right" > Residencia/Edificio :</div></div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4MA3" maxlength="35" size="36" value="<%= rtFirm.getField("E" + name + "4MA3").getString().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right"><div id="PADSCMA4<%=name%>" align="right" > No. Casa/Apto. :</div></div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4MA4" maxlength="35" size="36" value="<%= rtFirm.getField("E" + name + "4MA4").getString().trim()%>" readonly>
            </td>
          </tr> 

          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div  id="EXDSCSTE<%=name%>" align="right" style="display:none" > Estado : </div>
              <div  id="PADSCSTE<%=name%>" align="right">Provincia : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4STE" maxlength="4" size="5" value="<%= rtFirm.getField("E" + name + "4STE").getString().trim()%>" readonly>
              <input type="text" name="D<%= name %>4STE" maxlength="30" size="31" value="<%= rtFirm.getField("D" + name + "4STE").getString().trim()%>" readonly>

            </td>
          </tr>
          
		  <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Ciudad : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4CTY" maxlength="30" size="31" value="<%= rtFirm.getField("E" + name + "4CTY").getString().trim()%>" readonly>
            </td>
          </tr>          
          
          <tr id="PATID<%=name%>" class="trclear" > 
            <td width="13%" nowrap> 
              <div align="right"> Distrito :</div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4TID" maxlength="4" size="5" value="<%= rtFirm.getField("E" + name + "4TID").getString().trim()%>" readonly>
              <input type="text" name="D<%= name %>4TID" maxlength="35" size="36" value="<%= rtFirm.getField("D" + name + "4TID").getString().trim()%>" readonly>
            </td>
          </tr>
          <tr  id="PAPID<%=name%>"  class="trdark" > 
            <td width="13%" nowrap> 
              <div align="right"> Corregimiento :</div>
            </td>
            <td width="23%" nowrap> 
              
                <input type="text" name="E<%= name %>4PID" maxlength="4" size="5" value="<%= rtFirm.getField("E" + name + "4PID").getString().trim()%>" readonly>
                <input type="text" name="D<%= name %>4PID" maxlength="35" size="36" value="<%= rtFirm.getField("D" + name + "4PID").getString().trim()%>" readonly>
              
            </td>
          </tr>                    
		
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Pa&iacute;s : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%=name%>4CTR" maxlength="30" size="31" value="<%= rtFirm.getField("E" + name + "4CTR").getString().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Apartado Postal : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4POB" maxlength="10" size="11" value="<%= rtFirm.getField("E" + name + "4POB").getString().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">C&oacute;digo Postal : </div>
            </td>
            <td width="23%" nowrap>
              <input type="text" name="E<%= name %>4ZPC" maxlength="15" size="16" value="<%= rtFirm.getField("E" + name + "4ZPC").getString().trim()%>" readonly>
            </td>
          </tr>
           
 <%-- FIN SECCION DE DIRECCION --%>
            
           <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Identificaci&oacute;n : </div>
            </td>
            <td width="23%" nowrap>
              <div id="EXID<%= name %>"  style="display:none" >
                <input type="text" name="E<%= name %>4BNI" maxlength="30" size="31" value="<%= rtFirm.getField("E" + name + "4BNI").getString().trim()%>" readonly> 
              </div>
              <div id="PAID<%= name %>"  >
                <input type="text" name="E<%= name %>4NME" maxlength="30" size="31" value="<%= rtFirm.getField("E" + name + "4NME").getString().trim()%>" readonly>
               </div>
            </td>
          </tr>
 
   
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Tel&eacute;fono : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4HPN" maxlength="11" size="12" value="<%= rtFirm.getField("E" + name + "4HPN").getString().trim()%>" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Tipo de Firma: </div>
            </td>
            <td width="23%" nowrap> 
              <select name="E<%= name %>4FL1" disabled>
                <option value=" " <% if (!(rtFirm.getField("E" + name + "4FL1").getString().equals("1") || rtFirm.getField("E" + name + "4FL1").getString().equals("2") || rtFirm.getField("E" + name + "4FL1").getString().equals("3"))) out.print("selected"); %>></option>
                <option value="1" <% if (rtFirm.getField("E" + name + "4FL1").getString().equals("1")) out.print("selected"); %>>Firma Indistinta</option>
                <option value="2" <% if (rtFirm.getField("E" + name + "4FL1").getString().equals("2")) out.print("selected"); %>>Firma Mancomunada</option>
				<option value="3" <% if (rtFirm.getField("E" + name + "4FL1").getString().equals("3")) out.print("selected"); %>>Firma Individual</option>
				</select>
            </td>
          </tr>
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Categoría de Firma : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>4FL3" maxlength="1" size="2" value="<%= rtFirm.getField("E" + name + "4FL3").getString().trim()%>" readonly>
            </td>
          </tr>
          
          <tr id="trdark"> 
            <td width="13%" > 
              <div align="right">Monto L&iacute;mite: </div>
            </td>
            <td width="23%" nowrap > 
              <input type="text" name="E<%= name %>4AM1" value="<%= rtFirm.getField("E" + name + "4AM1").getString().trim()%>" size="15" maxlength="15" readonly>
            </td>
          </tr>
          <%--
          <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Comentarios: </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="D<%= name %>4CIT" size="35" maxlength="35" value="<%= rtFirm.getField("D" + name + "4CIT").getString().trim()%>">
            </td>
          </tr>
          --%>
        </table>
      </td>
    </tr>
  </table>
  <Script Language="JavaScript">
    divID='<%=name%>';
    displayDir();
  </Script>
<%
  }
%>

</div>
<SCRIPT Language="Javascript">
   dataDiv.style.height=  mainTable1.offsetTop + mainTable1.offsetHeight +"";
</SCRIPT>
  
  <br>
</form>
</body>
</html>

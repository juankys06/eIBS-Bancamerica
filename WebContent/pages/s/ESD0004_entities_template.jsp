<%
	// Obtiene el mensaje a desplegar
	String messageName = request.getParameter("messageName");
	datapro.eibs.sockets.MessageRecord mr = 
		(datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);
	datapro.eibs.beans.ESD000004Message entity = (datapro.eibs.beans.ESD000004Message)mr;
	session.setAttribute("entity", entity);
		
	boolean isReadOnly = true;
	String readOnly = "true";
	// Determina si es solo lectura
	if (request.getParameter("readOnly") != null){
		readOnly = request.getParameter("readOnly").toString();
		isReadOnly = Boolean.valueOf(readOnly).booleanValue();
	}
	
	//Flag de aprobación
	String approval = "false";
	boolean isApproval = false;
	if(request.getParameter("approval") != null){
		approval = request.getParameter("approval").toString();
		isApproval =  Boolean.valueOf(approval).booleanValue();
	}
	
	String recordType = entity.getField("E04RTP").toString().trim();
	String bankCountry = "DR";//entity.getH04SCR();
%>	

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/Ajax.js"></script>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/Address.js"></script>
<script language="JavaScript">
function go(op) {
	dataDiv.scrollTop = document.getElementById("tbenter"+op).offsetTop;
}

var imgHelpHeight = "0";
var imgHelpWidth = "0";
var imgRequiredHeight = "0";
var imgRequiredWidth = "0";

function setReadOnly(divName, what){
	var felem = document.getElementById(divName).getElementsByTagName('*');
	var j=felem.length;
	for(i=0; i<j; i++){
		if (felem[i].tagName != "img" && felem[i].tagName != "IMG"){
			if (felem[i].type=="text"){
				felem[i].readOnly=what;
			} else if (felem[i].tagName=="TEXTAREA" || felem[i].tagName=="textarea") {
				felem[i].readOnly=what;	
			} else if (felem[i].type=="hidden") {
			} else { 
				felem[i].disabled=what;
			}
		} else if (felem[i].tagName == "img" || felem[i].tagName == "IMG"){
			if(imgHelpHeight == "0" && felem[i].src.indexOf("1b.gif") > 0){
				imgHelpHeight = felem[i].height;
				imgHelpWidth = felem[i].width;
			}
			if(imgRequiredHeight == "0" && felem[i].src.indexOf("Check.gif") > 0){
				imgRequiredHeight = felem[i].height;
				imgRequiredWidth = felem[i].width;
			}
			if(what){
				felem[i].height="0";
				felem[i].width="0";
			} else {
				if(felem[i].src.indexOf("1b.gif") > 0){
					felem[i].height=imgHelpHeight;
					felem[i].width=imgHelpWidth;
				} else if(felem[i].src.indexOf("Check.gif") > 0){
					felem[i].height=imgRequiredHeight;
					felem[i].width=imgRequiredWidth;
				}
			}
		}
	}
}

function showAddressFields(block, view){
	if(view){
		document.getElementById('entity'+block).style.display='none';
		document.getElementById('customer'+block).style.display='block';
		setReadOnly('entity'+block, false);
	} else {
		document.getElementById('entity'+block).style.display='block';
		var customerNumber = trim(document.getElementsByName('E' + block + 'RCN')[0].value);
		if(customerNumber == '' || customerNumber == '0'){
			document.getElementById('customer'+block).style.display='none';
			setReadOnly('entity'+block, false);
		} else {
			setReadOnly('entity'+block, true);
		}
	}
}

function changeAddressBlock(block, country, dCountry){
	var url = "<%= request.getContextPath() %>/servlet/datapro.eibs.client.JSAddress?";
	url = url + 'messageName=<%=messageName%>&index='+block+'&readOnly=false&firstColor=trclear&country='+country+'&dCountry='+dCountry;
	GetXMLResponse(url, divCallback, false);
}

function changeEntityAddressFields(block){
	var countryResidence 	= document.getElementsByName('E' + block + 'UC1')[0];
	var countryAddress 		= document.getElementsByName('C' + block + 'CTR')[0];
	var countryDescription 	= document.getElementsByName('E' + block + 'CTR')[0];

	if(checkResidentBlock(block, countryAddress.value)){
		countryAddress.value = '<%= bankCountry %>';
	}
	if(countryAddress.value != countryResidence.value){
		changeAddressBlock(block, countryAddress.value, countryDescription.value);
	}
	countryResidence.value = countryAddress.value;
}

function checkResidentBlock(block, country){
	var isResident = true;
	if(country != '<%= bankCountry %>'){
		isResident = false;
	}
	var residentBlock = document.getElementById("resident"+block);
	if(residentBlock.checked != isResident){
		var warning = "El país de la dirección no coincide con el estado de residencia";
		var proceed = true;
		//var proceed = confirm(warning + ", ¿Desea continuar?");
		//alert(warning);
		if(!proceed){
			return residentBlock.checked;
		}
	}
	residentBlock.checked = isResident;
	return residentBlock.checked;
}

function changeResidentFields(block, isResident){
	var countryAddress	= document.getElementsByName('C' + block + 'CTR')[0];
	if(isResident){
		countryAddress.value = '<%= bankCountry %>';
	} else {
		countryAddress.value = '';	
	}
	changeEntityAddressFields(block);
}
</script>

<div id="dataDiv" align="center" style="height:351; overflow-y:scroll; width:90%; padding-left:50; padding-right:5" class="scbarcolor">
<%
	
  int entityCount = 10;
  String name;
  for ( int i=1; i<=entityCount; i++ ) {
    if (i == 10) {
      name = "A4"; 
    } else {
      name = i + "4";
    }
    boolean isCustomer = !entity.getField("E" + name + "RCN").getString().trim().equals("0");

    String residenceCountry = entity.getField("E" + name + "UC1").getString().trim();
    residenceCountry = residenceCountry.equals("") ? bankCountry : residenceCountry;
    boolean isResident = residenceCountry.equals(bankCountry);
    
    String blockCountry = isResident ? "21" : "01";    	//I do not like this
    
	out.println("<table id=\"tbenter"+i+ "\" class=\"tbenter\" border=\"0\" width=\"100%\">");
    switch ( i ) {
        case 1 :     
%> 
<tr>
 <td width="20%" align="left"><h4>Primero</h4></td>
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
<td align="left"><h4>Séptimo</h4></td>
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
        case 10 : 
%> 
<td align="left"><h4>Décimo</h4></td>
<%          
           break;
}
if(!isReadOnly && !isApproval){
	if(!recordType.equals("9")){//Activos
%>
	<td width="20%" align="left">
		<h4><input type="checkbox" id="type<%= name %>" name="E<%= name %>TYP" value="1" 
			onclick="showAddressFields('<%= name %>', this.checked);" <% if (isCustomer) { out.print("checked"); } %>>Es Cliente del Banco
		</h4>
	</td>
	<td width="20%" align="left">
		<input name="E<%= name %>UC1" type="hidden" value="<%= residenceCountry %>"/>
		<h4><input type="checkbox" id="resident<%= name %>" name="E<%= name %>RES" value="1" 
			onclick="changeResidentFields('<%= name %>', this.checked);" <% if (isResident) { out.print("checked"); } %>>Es Residente
		</h4>
	</td>
<%
	}
}
%>
	<td width="40%" align="left">
	<h4>
	<A href="javascript:go(1)">1</A>,<A href="javascript:go(2)">2</A>,<A href="javascript:go(3)">3</A>,<A href="javascript:go(4)">4</A>,<A href="javascript:go(5)">5</A>
	<A href="javascript:go(6)">6</A>,<A href="javascript:go(7)">7</A>,<A href="javascript:go(8)">8</A>,<A href="javascript:go(9)">9</A>,<A href="javascript:go(10)">10</A>
	</h4>
	</td> 
	</tr>
	</table> 
  <table id="mainTable<%= ""+i %>" class="tableinfo" width="100%">
    <tr bgcolor="#FFFFFF" bordercolor="#FFFFFF"> 
      <td> 
	<div id="customer<%= name %>" style="position:relative; display:block">
        <table cellspacing="0" cellpadding="2" width="100%" border="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">        
          <tr id="trdark"> 
           <td width="40%" nowrap> 
              <div align="right">Número de Cliente : </div>
           </td>
           <td width="66%" nowrap> 
             <input type="text" name="E<%= name %>RCN" maxlength="12" size="12" value="<%= entity.getField("E" + name + "RCN").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
	              		out.print(mr.getField("F"+name + "RCN").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
				<%	if(!isReadOnly && !isApproval){ %>
				<a href="javascript:GetCustomer('E<%=name%>RCN')">
             	<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="bottom" border="0" ></a>	
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"> 
				<%	} %>
           </td>
          </tr>
         </table>
	</div>
  	<div id="entity<%= name %>" style="position:relative; display:block;">
        <table cellspacing="0" cellpadding="2" width="100%" border="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">        
		<% if(!recordType.equals("9")){//Activos%> 
          <tr id="trclear"> 
            <td width="40%" nowrap> 
              <div align="right">Identificaci&oacute;n :</div>
            </td>
            <td width="66%" nowrap> 
              <input type="text" name="E<%= name %>BNI" maxlength="15" size="16" value="<%= entity.getField("E" + name + "BNI").getString().trim()%>"
			<%	if(isReadOnly) out.print(" readonly=\"true\"");
				if(isApproval){
              		out.print(mr.getField("F"+name + "BNI").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
				Tipo :
              <input type="text" name="E<%= name %>TID" value="<%= entity.getField("E" + name + "TID").getString().trim()%>" size="5" maxlength="4"
			<%	if(isReadOnly) out.print(" readonly=\"true\"");
				if(isApproval){
              		out.print(mr.getField("F"+name + "TID").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
			<%	if(!isReadOnly && !isApproval){ %>
			  <a href="javascript:GetCodeCNOFC('E<%= name %>TID','34')">
			  <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			<% } %>
				País :
              <input type="text" name="E<%= name %>PID" size="5" maxlength="4" value="<%= entity.getField("E" + name + "PID").getString().trim()%>"
			<%	if(isReadOnly) out.print(" readonly=\"true\"");
				if(isApproval){
              		out.print(mr.getField("F"+name + "PID").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
			<%	if(!isReadOnly && !isApproval){ %>
			  <a href="javascript:GetCodeCNOFC('E<%= name %>PID','03')">
			  <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>	
			<% } %>
             </td>  
          </tr>
          <tr id="trdark"> 
            <td width="40%" nowrap> 
              <div align="right">Nombre Legal :</div>
            </td>
            <td nowrap width="66%"> 
              <input type="text" name="E<%= name %>MA1" maxlength="45" size="46" value="<%= entity.getField("E" + name + "MA1").getString().trim()%>"
			<% if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
             		out.print(mr.getField("F"+name + "MA1").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
			<% if(!isReadOnly && !isApproval){ %>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="bottom" border="0"> 
			<% } %>
              </td>      
          </tr>
		</table>
		<% } %> 
	  	<div id="address<%= name %>" style="position:relative; display:block;">
        <table width="100%" align="center">
          <% String pageName = "ESD0080_address_template_country" + blockCountry + ".jsp"; %>
			<jsp:include page="<%= pageName %>" flush="true">
				<jsp:param name="messageName" value="entity" />
				<jsp:param name="firstColor" value="trclear" />
				<jsp:param name="basic" value="false" />
				<jsp:param name="index" value='<%=name%>' />
				<jsp:param name="readOnly" value='<%=readOnly%>' />
				<jsp:param name="approval" value='<%=approval%>' />
			</jsp:include>
		</table>
		</div>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">        
          <tr id="trdark"> 
            <td width="40%" nowrap> 
              <div align="right">Tel&eacute;fono :</div>
            </td>
            <td width="66%" nowrap> 
              <input type="text" name="E<%= name %>HPN" maxlength="11" size="12" value="<%= entity.getField("E" + name + "HPN").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "HPN").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
              </td>
          </tr>
          <% if(!recordType.equals("9")){//Activos%> 
          <tr id="trclear"> 
            <td width="40%" nowrap> 
              <div align="right">Nacionalidad :</div>
            </td>
            <td width="66%" nowrap> 
              <input type="text" name="E<%= name %>BNC" maxlength="4" size="5" value="<%= entity.getField("E" + name + "BNC").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "BNC").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
			<% if(!isReadOnly && !isApproval){ %>
              <a href="javascript:GetCodeCNOFC('E<%= name %>BNC','03')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a> 
			<% } else { %>
				<input type="text" name="D<%= name %>BNC" size="40" value="<%= entity.getField("D" + name + "BNC").getString().trim()%>" readonly>
			<% } %>
              </td>
          </tr>
          <% } %> 
          <tr id="trdark"> 
            <td width="40%" nowrap> 
				<% if(recordType.equals("9")){//Activos %>
              <div align="right">Fecha de Compra :</div>
				<% } else { %>
              <div align="right">Fecha de Nacimiento/Fundación :</div>
				<% } %>
            </td>
            <td width="66%" nowrap> 
              <input type="text" name="E<%= name %>DT2" maxlength="2" size="2" value="<%= entity.getField("E" + name + "DT2").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "DT1").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
              <input type="text" name="E<%= name %>DT1" maxlength="2" size="2" value="<%= entity.getField("E" + name + "DT1").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "DT1").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
              <input type="text" name="E<%= name %>DT3" maxlength="2" size="2" value="<%= entity.getField("E" + name + "DT3").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "DT1").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
              </td>
          </tr>
       		<% if(!recordType.equals("7")//Referencias Comerciales
       		&& !recordType.equals("9")){//Activos %>
          <tr id="trclear"> 
            <td width="40%" nowrap > 
              <div align="right">Estado Civil :</div>
            </td>
            <td width="66%" nowrap > 
              <select name="E<%= name %>BMS"
				<%	if(isReadOnly) out.print(" disabled=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "BMS").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
                <option value=1 <% if (entity.getField("E" + name + "BMS").getString().equals("1")) out.print("selected"); %>>Solter(o)</option>
                <option value=2 <% if (entity.getField("E" + name + "BMS").getString().equals("2")) out.print("selected"); %>>Casado(a)</option>
                <option value=3 <% if (entity.getField("E" + name + "BMS").getString().equals("3")) out.print("selected"); %>>Divorciado(a)</option>
                <option value=4 <% if (entity.getField("E" + name + "BMS").getString().equals("4")) out.print("selected"); %>>Viudo(a)</option>
                <option value=5 <% if (entity.getField("E" + name + "BMS").getString().equals("5")) out.print("selected"); %>>Otros</option>
                <option value=0 <% if (!(entity.getField("E" + name + "BMS").getString().equals("1") 
                || entity.getField("E" + name + "BMS").getString().equals("2") 
                || entity.getField("E" + name + "BMS").getString().equals("3") 
                || entity.getField("E" + name + "BMS").getString().equals("4") 
                || entity.getField("E" + name + "BMS").getString().equals("5"))) out.print("selected"); %>></option>
              </select>
              </td>
          </tr>
          <tr id="trdark"> 
            <td width="40%" nowrap> 
              <div align="right">Sexo :</div>
            </td>
            <td width="66%" nowrap> 
              <input type="radio" name="E<%= name %>BSX" value="F" <% 
              	if (entity.getField("E" + name + "BSX").getString().equals("F")) 
              		out.print("checked"); 
				if(isReadOnly) out.print(" readonly=\"true\"");
				if(isApproval){
              		out.print(mr.getField("F"+name + "BSX").getString().equals("Y") ? "id=\"txtchanged\"" : "");
              	} 
              	%>> Femenino 
              <input type="radio" name="E<%= name %>BSX" value="M" <% 
              	if (entity.getField("E" + name + "BSX").getString().equals("M")) 
              		out.print("checked"); 
				if(isReadOnly) out.print(" readonly=\"true\"");
				if(isApproval){
              		out.print(mr.getField("F"+name + "BSX").getString().equals("Y") ? "id=\"txtchanged\"" : "");
              	} 
              	%>> Masculino </td>
          </tr>
		 <tr id="trclear"> 
            <td width="40%" nowrap > 
              <div align="right">Profesi&oacute;n/Ocupaci&oacute;n :</div>				
            </td>
            <td width="66%" nowrap > 
              <p> 
			<% if(!isReadOnly){ %>
	                <input type="text" name="E<%= name %>UC4" value="<%= entity.getField("E" + name + "UC4").getString().trim()%>" size="5" maxlength="4">
			<% } 
			   if(!isReadOnly && !isApproval){ %>
                <a href="javascript:GetCodeCNOFC('E<%= name %>UC4','49')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"  ></a>	
			<% } else { %>
				<input type="text" name="D<%= name %>UC4" size="40" value="<%= entity.getField("D" + name + "UC4").getString().trim()%>" readonly
			<% 	if(isApproval){
              		out.print(mr.getField("F"+name + "UC4").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %>>
			<% } %>   
                </p>
            </td>
          </tr>
       		<% } %>
       		<% if(!recordType.equals("9")){//Activos%> 
          <tr id="trdark"> 
            <td width="40%" nowrap > 
              <div align="right">C&oacute;digo de Industria :</div>				
            </td>
            <td width="66%" nowrap > 
              <p> 
			<% if(!isReadOnly){ %>
	                <input type="text" name="E<%= name %>INC" value="<%= entity.getField("E" + name + "INC").getString().trim()%>" size="5" maxlength="4">
			<% } 
			   if(!isReadOnly && !isApproval){ %>
                <a href="javascript:GetCodeCNOFC('E<%= name %>INC','09')">
                <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"  ></a>	
			<% } else { %>
				<input type="text" name="D<%= name %>INC" size="40" value="<%= entity.getField("D" + name + "INC").getString().trim()%>" readonly
			<% 	if(isApproval){
              		out.print(mr.getField("F"+name + "INC").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %>>
			<% } %>   
                </p>
            </td>
          </tr>
          <% } %>
		 </table>
          </div>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#FFFFFF"  align="center">        
		<% if(recordType.equals("2")
				|| recordType.equals("3")
				|| recordType.equals("5")){//Accionistas, Junta Directiva, Representantes Legales %>
          <tr id="trclear"> 
            <td width="40%" nowrap> 
              <div align="right">Cargo :</div>
            </td>
            <td width="66%" nowrap> 
              <input type="text" name="E<%= name %>NME" maxlength="35" size="36" value="<%= entity.getField("E" + name + "NME").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "NME").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
              </td>
          </tr>
		<% } else { %>
          <tr id="trclear"> 
            <td width="40%" nowrap> 
              <div align="right">Comentarios/Descripción : </div>
            </td>
            <td width="66%" nowrap> 
              <input type="text" name="E<%= name %>NME" maxlength="35" size="36" value="<%= entity.getField("E" + name + "NME").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "NME").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
            </td>
          </tr>		
		<% }
			if(recordType.equals("9")){//Activos%>
          <tr id="trclear"> 
            <td nowrap width="40%"> 
              <div align="right">Valor Compra :</div>
            </td>
            <td nowrap width="66%">
              <input type="text" name="E<%= name %>AM1" maxlength="15" size="16" value="<%= entity.getField("E" + name + "AM1").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "AM1").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40%"> 
              <div align="right">Valor Actual :</div>
            </td>
            <td nowrap width="66%" >
              <input type="text" name="E<%= name %>AM2" maxlength="15" size="16" value="<%= entity.getField("E" + name + "AM2").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "AM2").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
            </td>
          </tr>
          <tr id="trclear">
            <td nowrap width="40%">
              <div align="right">Tipo de Activo :</div>
            </td>
            <td nowrap width="66%" >
              <div align="left"> 
				<select name="E<%= name %>FL1" 
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "FL1").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
                  <option value="" <%if (entity.getField("E" + name + "FL1").getString().equals("")) {out.print("selected"); }%>></option>        
                  <option value="1" <%if (entity.getField("E" + name + "FL1").getString().equals("1")) {out.print("selected"); }%>>Circulante</option>
                  <option value="2" <%if (entity.getField("E" + name + "FL1").getString().equals("2")) { out.print("selected"); }%>>Fijo</option>
                  <option value="3" <%if (entity.getField("E" + name + "FL1").getString().equals("3")) { out.print("selected"); }%>>Otros</option>
                </select>
              </div>
            </td>
          </tr>
		<% } else if(recordType.equals("S")){//Firmantes %>         
           <tr id="trclear"> 
            <td width="13%" nowrap> 
              <div align="right">Tipo de Firma: </div>
            </td>
            <td width="23%" nowrap> 
              <select name="E<%= name %>4FL1"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){ out.print(mr.getField("F"+name + "FL1").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
                <option value=" " <% if (!(entity.getField("E" + name + "FL1").getString().equals("1") 
			|| entity.getField("E" + name + "FL1").getString().equals("2") 
			|| entity.getField("E" + name + "FL1").getString().equals("3"))) 
			out.print("selected"); %>></option>
                <option value="1" <% if (entity.getField("E" + name + "FL1").getString().equals("1")) out.print("selected"); %>>Firma Indistinta</option>
                <option value="2" <% if (entity.getField("E" + name + "FL1").getString().equals("2")) out.print("selected"); %>>Firma Mancomunada</option>
				<option value="3" <% if (entity.getField("E" + name + "FL1").getString().equals("3")) out.print("selected"); %>>Firma Individual</option>
				</select>
            </td>
          </tr>
          <tr id="trdark"> 
            <td width="13%" nowrap> 
              <div align="right">Categoría de Firma : </div>
            </td>
            <td width="23%" nowrap> 
              <input type="text" name="E<%= name %>FL3" maxlength="1" size="2" value="<%= entity.getField("E" + name + "FL3").getString().trim()%>"
              	<% if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){out.print(mr.getField("F"+name + "FL3").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
				<% if(!isReadOnly && !isApproval){ %>
              <a href="javascript:GetCodeCNOFC('E<%= name %>FL3','FI')">
              <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"></a>	
			<% } else { %>
				<input type="text" name="D<%= name %>FL3" size="40" value="<%= entity.getField("D" + name + "FL3").getString().trim()%>" readonly>
			<% } %>
            </td>
          </tr>          
          <tr id="trclear"> 
            <td width="13%" > 
              <div align="right">Monto L&iacute;mite: </div>
            </td>
            <td width="23%" nowrap > 
              <input type="text" readonly name="E<%= name %>AM1" maxlength="15" size="16" value="<%= entity.getField("E" + name + "AM1").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "AM1").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
            </td>
          </tr>
        </table>
		<% 	} else if(recordType.equals("2")//Accionistas
				|| recordType.equals("3")//Junta Directiva
				|| recordType.equals("7")){
				//Referencias Comerciales
			} else { %> 
          <tr id="trdark"> 
            <td width="40%" nowrap> 
              <div align="right">Parentesco :</div>
            </td>
            <td width="66%" nowrap> 
				<input type="text" name="E<%= name %>MLC" maxlength="4" size="5" value="<%= entity.getField("E" + name + "MLC").getString().trim()%>"
				<%	if(isReadOnly) out.print(" readonly=\"true\"");
					if(isApproval){
              		out.print(mr.getField("F"+name + "MLC").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
				<% if(!isReadOnly && !isApproval){ %>
	            <a href="javascript:GetCodeCNOFC('E<%= name %>MLC', 'Z6')">
	            <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0"  ></a>	
				<% } else { %>
				<input type="text" name="D<%= name %>MLC" maxlength="35" size="38" value="<%= entity.getField("D" + name + "MLC").getString().trim()%>" readonly
				<%	if(isApproval){
              		out.print(mr.getField("F"+name + "MLC").getString().equals("Y") ? "id=\"txtchanged\"" : "");} %> >
				<% } %>
              </td>
          </tr>		
		<% } %>
        </table>
        
      </td>
    </tr>
  </table>  
<script language="JavaScript">
	<% if(i == 1){ %> 
		dataDiv.style.height = mainTable1.offsetTop + mainTable1.offsetHeight +"";		
	<% } %> 	
	<% if(!isReadOnly && !isApproval){ %> 
		showAddressFields('<%= name %>', <%= isCustomer %>);
	<% } %> 	
</script>
		<% } %>
</div>


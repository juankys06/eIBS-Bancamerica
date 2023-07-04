<%@ page import="datapro.eibs.sockets.*, datapro.eibs.beans.*" %>
<%!
	datapro.eibs.sockets.MessageRecord mr;
 
	String title;
	String messageName;
	String suffix;
	boolean readOnly=false;
	String READ_ONLY_TAG="";
	
	String suffixField0;
	String suffixField1;
	String suffixField2;
	String suffixField3;
	String suffixField4;
	String suffixFieldHidden1;
%>

<%
	//Obtiene el titulo del segmento de direccion
	title = request.getParameter("title");
	if (title == null ) title = "Dirección";

	// Obtiene el sufijo de los campos dependiendo del tipo de mensaje a desplegar.
	// Si el mensaje es del tipo ESD008001, el prefijo usado es E01, 
	// si el mensaje es del tipo ESD008002, el prefijo usado es E02

	messageName = request.getParameter("messageName");
	mr = (datapro.eibs.sockets.MessageRecord) session.getAttribute(messageName);
   
	if (mr instanceof ESD008001Message){
		suffix = "1";
	} else {
		suffix = "2";
	}

	// Determina si es solo lectura
	if (request.getParameter("readOnly") != null )
		if (request.getParameter("readOnly").toLowerCase().equals("true")){
			readOnly=true;
			READ_ONLY_TAG="readonly";
		} else {
			READ_ONLY_TAG="";
		}
	else READ_ONLY_TAG="";

	suffixFieldHidden1 = "LN3";
		
	suffixField0 = "NA2";
	suffixField1 = "NA3";
	suffixField2 = "NA4";
	suffixField3 = "LN3A";
	suffixField4 = "LN3B";
	
%>

<script language=javascript>

	var fieldHidden1 = "";

	function changeAddress(){
		var valField0 = "";
	    if("" != trim(document.forms[0].NA0_2.value)){ 
		  valField0 = trim(document.forms[0].NA0_1.value + " " );
		  valField0 = valField0 + trim(document.forms[0].NA0_2.value + " " );
		}
		else valField0="";  
		
		var valField1 = "";
		if("" != trim(document.forms[0].NA1_2.value)){
		  valField1 = trim(document.forms[0].NA1_1.value + " " );
		  valField1 = valField1 + trim(document.forms[0].NA1_2.value + " " );
		}
		else valField1="";  
		
		var valField2 = "";
		if("" != trim(document.forms[0].NA2_2.value)){
		  valField2 = trim(document.forms[0].NA2_1.value + " " );
		  valField2 = valField2 + trim(document.forms[0].NA2_2.value + " " );
		}
		else valField2="";  
		
		var valField3 = "";
		if("" != trim(document.forms[0].NA3_2.value)){
		  valField3 = trim(document.forms[0].NA3_1.value + " " );
		  valField3 = valField3 + trim(document.forms[0].NA3_2.value + " " );
		}
		else valField3="";  
		
		var valField4 = "";
		if("" != trim(document.forms[0].NA4_2.value)){
		  valField4 = trim(document.forms[0].NA4_1.value + " " );
		  valField4 = valField4 + trim(document.forms[0].NA4_2.value + " " );
		}
		else valField4="";
		
		document.forms[0][fieldName].value = valField0;
		document.forms[0][fieldAux1].value = valField1;
		document.forms[0][fieldAux2].value = valField2;
		document.forms[0][fieldAux3].value = valField3;
		document.forms[0][fieldAux4].value = valField4;
		
		while (valField3.length < 10)  valField3=valField3 + ' ';
		while (valField4.length < 10)  valField4=valField4 + ' ';
		document.forms[0][fieldHidden1].value=valField3+valField4;
		
		closeHiddenDivAddress();
	}
	
	function setTempValues(source, temp1, temp2){
	  var spaceIndex = source.value.indexOf(" ");
	  var optionSelected = source.value.substring(0,spaceIndex+1);
	  var valueEntered = source.value.substring(spaceIndex, source.value.length);
	  temp2.value = valueEntered;
	  
	  for ( i=0;i< temp1.options.length - 1; i++){
	    if (temp1.options[i].selected) temp1.options[i].selected=false;
	    if (temp1.options[i].text == optionSelected) temp1.options[i].selected=true;
	  }
	}
	
	function closeHiddenDivAddress(){
		setVisibility(document.getElementById("hiddenDivAddress"), "hidden");
	}
	
	function showHiddenDivAddress(evt) {
		evt = (evt) ? evt : ((window.event) ? window.event : "");
		var y=evt.clientY + document.body.scrollTop;
		var x=evt.clientX + document.body.scrollLeft;
	
		var elem = getEventElement(evt);
		cancelBub(evt);
		
		fieldName = elem.id + <%= suffixField0 %>;
		fieldAux1 = elem.id + <%= suffixField1 %>;
		fieldAux2 = elem.id + <%= suffixField2 %>;
		fieldAux3 = elem.id + <%= suffixField3 %>;
		fieldAux4 = elem.id + <%= suffixField4 %>;
		fieldHidden1 = elem.id + <%= suffixFieldHidden1 %>;
		
		setTempValues(document.getElementById(fieldName),document.getElementById('NA0_1'),document.getElementById('NA0_2'));
		setTempValues(document.getElementById(fieldAux1),document.getElementById('NA1_1'),document.getElementById('NA1_2'));
		setTempValues(document.getElementById(fieldAux2),document.getElementById('NA2_1'),document.getElementById('NA2_2'));
		setTempValues(document.getElementById(fieldAux3),document.getElementById('NA3_1'),document.getElementById('NA3_2'));
		setTempValues(document.getElementById(fieldAux4),document.getElementById('NA4_1'),document.getElementById('NA4_2'));
	 	
		var hiddenDivNew = document.getElementById("hiddenDivAddress");
	
		moveElement(hiddenDivAddress, y - 20, x - 310);
		setVisibility(hiddenDivAddress, "visible");
		 
		document.forms[0].NA0_2.focus();
	}

   	document.onclick=closeHiddenDivAddress;

</script>

<div id="hiddenDivAddress" class="hiddenDiv">
<TABLE id=tbhelp
	style="border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; border-color: #0b23b5; border-style: solid solid solid solid; filter: progid : DXImageTransform . Microsoft . dropshadow(OffX=3, OffY=3, Color='gray', Positive='true')">
	<tr id="trdark">
		<td>
			<select name="NA0_1">
				<option value="URB">URB</option>
				<option value="BARRIO">BARRIO</option>
			</select>
		</td>
		<td align=LEFT>
			<div align="left">
				<input type="text" name="NA0_2" size="35" maxlength="28">
			</div>
		</td>
	</tr>
	<tr id="trclear">
		<td>
			<select name="NA1_1">
				<option value="AV">AV</option>
				<option value="CALLE">CALLE</option>
				<option value="CARR">CARR</option>
				<option value="CALLEJ">CALLEJ</option>
				<option value="PASAJE">PASAJE</option>
				<option value="SECT">SECT</option>
				<option value="ZONA">ZONA</option>
				<option value="VERDA">VERDA</option>
				<option value="MZNA">MZNA</option>
				<option value="PARC">PARC</option>
			</select>
		</td>
		<td align=LEFT>
			<div align="left">
				<input type="text" name="NA1_2" size="35" maxlength="28">
			</div>
		</td>
	</tr>
	<tr id="trdark">
		<td>
			<select name="NA2_1">
				<option value="QTA">QTA</option>
				<option value="CASA">CASA</option>
				<option value="EDF">EDF</option>
				<option value="RES">RES</option>
				<option value="CONJ">CONJ</option>
				<option value="TRR">TRR</option>
				<option value="BLOQUE">BLOQUE</option>
				<option value="PASAJE">PASAJE</option>
				<option value="CC">CC</option>
			</select>
		</td>
		<td align=LEFT>
			<div align="left">
				<input type="text" name="NA2_2" size="35" maxlength="28">
			</div>
		</td>
	</tr>
	<tr id="trclear">
		<td>
			<select name="NA3_1">
				<option value="NUM">NUMERO</option>
				<option value="PISO">PISO</option>
				<option value="NIVEL">NIVEL</option>
				<option value="MEZA.">MEZANINE</option>
			</select>
		</td>
		<td align=LEFT>
			<div align="left">
				<input type="text" name="NA3_2" size="35" maxlength="28">
			</div>
		</td>
	</tr>
	<tr id="trdark">
		<td>
			<select name="NA4_1">
				<option value="LOCAL">LOCAL</option>
				<option value="OFIC.">OFICINA</option>
				<option value="APTO.">APARTAMENTO</option>
			</select>
		</td>
		<td align=LEFT>
			<div align="left">
				<input type="text" name="NA4_2" size="35" maxlength="28">
			</div>
		</td>
	</tr>
	<tr id="trclear">
		<TD ALIGN=center nowrap colspan="2">
			<input id="EIBSBTN" type=button name="OKAddress" value="OK" onclick="changeAddress()"> 
			<input id="EIBSBTN" type=button name="CancelAddress" value="Cancelar" onclick="closeHiddenDivAddress()">
		</TD>
	</tr>
</TABLE>
</div>

<%
	int index = 0;
	
	String valueField0 = mr.getField("E" + index + suffix + suffixField0).getString().trim();
	String valueField1 = mr.getField("E" + index + suffix + suffixField1).getString().trim();
	String valueField2 = mr.getField("E" + index + suffix + suffixField2).getString().trim();
	String valueField3 = "";
	String valueField4 = "";
	
	String valueHidden1 = mr.getField("E" + index + suffix + suffixFieldHidden1).getString().trim();
	if ( valueHidden1 != null && !valueHidden1.equals("") ){
		try {
			if (valueHidden1.length() < 10)
				valueField3 = valueHidden1.substring(0,valueHidden1.length());
			else {
				valueField3 = valueHidden1.substring(0,10);
				valueField4 = valueHidden1.substring(10);
			}    
		} catch (Exception e) {}
	}
%>

<h4><%= title %></h4>
<INPUT TYPE=HIDDEN NAME="E<%= index %><%= suffix %><%= suffixFieldHidden1 %>" VALUE="">
<TABLE class="tableinfo">
	<TBODY>
		<TR>
			<TD nowrap>
			<TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
				<TBODY>
					<TR id="trdark0">
						<TD nowrap width="32%">
							<DIV align="right">Direcci&oacute;n :</DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %><%= suffixField0 %>" size="45" maxlength="35" 
								value="<%= valueField0 %>" readonly> 
							<%
								if (!readOnly) {
							%>
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" style="cursor: hand"
								id="address.<%= index %>.<%= suffix %>" onclick="showHiddenDivAddress()">
							<%
								}
							%>
							<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0">
						</TD>
					</TR>
					<TR id="trclear0">
						<TD nowrap width="32%">
							<DIV align="right"></DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %><%= suffixField1 %>" size="45" maxlength="35" 
								value="<%= valueField1 %>" readonly>
						</TD>
					</TR>
					<TR id="trdark0">
						<TD nowrap width="32%">
							<DIV align="right"></DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %><%= suffixField2 %>" size="45" maxlength="35" 
								value="<%= valueField2 %>" readonly>
						</TD>
					</TR>
					<TR id="trclear0">
						<TD nowrap width="32%">
							<DIV align="right"></DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %><%= suffixField3 %>" size="45" maxlength="35" 
								value="<%= valueField3 %>" readonly>
						</TD>
					</TR>
					<TR id="trdark0">
						<TD nowrap width="32%">
							<DIV align="right"></DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %><%= suffixField4 %>" size="45" maxlength="35" 
								value="<%= valueField4 %>" readonly>
						</TD>
					</TR>
					<TR id="trclear0">
						<TD nowrap width="32%">
							<DIV align="right">Parroquia</DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %>UC4" size="7" maxlength="6" <%=READ_ONLY_TAG%>
								value="<%= mr.getField("E" + index + suffix + "UC4").getString().trim() %>"> 
							<%
								if (!readOnly) {
							%>
								<A href="javascript:GetCodeCNOFC('E<%= index %><%= suffix %>UC4','80')">
								<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></A> 
							<%
								}
							%>
								<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0">
						</TD>
					</TR>
					<TR id="trdark0">
						<TD nowrap width="32%">
							<DIV align="right">Localidad :</DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %>UC7" size="5" maxlength="4" <%=READ_ONLY_TAG%>
								value="<%=mr.getField("E" + index + suffix + "UC7").getString().trim()%>"> 
							<INPUT type="text" name="E<%= index %><%= suffix %>CTY" size="30" maxlength="30" <%=READ_ONLY_TAG%>
								value="<%=mr.getField("E" + index + suffix + "CTY").getString().trim()%>"> 
							<%
								if (!readOnly) {
							%>
							<A href="javascript:GetCodeDescCNOFC('E<%= index %><%= suffix %>UC7','E<%= index %><%= suffix %>CTY','84')"> 
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></A> 
							<%
								}
							%>
							<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0">
						</TD>
					</TR>
					<TR id="trclear0">
						<TD nowrap width="32%">
							<DIV align="right">Apartado Postal :</DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %>POB" size="15" maxlength="10" <%=READ_ONLY_TAG%>
							value="<%=mr.getField("E" + index + suffix + "POB").getString().trim()%>">
						</TD>
					</TR>

					<TR id="trdark0">
						<TD nowrap width="32%">
							<DIV align="right">Zona Postal :</DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %>ZPC" size="10" maxlength="15" <%=READ_ONLY_TAG%>
								value="<%=mr.getField("E" + index + suffix + "ZPC").getString().trim()%>">
						</TD>
					</TR>
					<TR id="trclear0">
						<TD nowrap width="32%">
							<DIV align="right">Estado :</DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %>STE" size="6" maxlength="4" <%=READ_ONLY_TAG%>
								value="<%=mr.getField("E" + index + suffix + "STE").getString().trim()%>"> 
							<%
								if (!readOnly) {
							%>
							<A href="javascript:GetCodeCNOFC('E<%= index %><%= suffix %>STE','27')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></A> 
							<%
								}
							%>
							<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0">
						</TD>
					</TR>
					<TR id="trdark0">
						<TD nowrap width="32%">
							<DIV align="right">Pa&iacute;s :</DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="hidden" name="CTRCODE" value=""> 
							<INPUT type="text" name="E<%= index %><%= suffix %>CTR" size="21" maxlength="20" <%=READ_ONLY_TAG%>
								value="<%=mr.getField("E" + index + suffix + "CTR").getString().trim()%>"> 
							<%
								if (!readOnly) {
							%>
							<A href="javascript:GetCodeDescCNOFC('CTRCODE','E<%= index %><%= suffix %>CTR','03')">
							<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0"></A> 
							<%
								}
							%>
							<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0">
						</TD>
					</TR>

					<TR id="trclear0">
						<TD nowrap width="32%">
							<DIV align="right">Tipo de Correo :</DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %>MLC" size="6" maxlength="4" <%=READ_ONLY_TAG%>
								value="<%=mr.getField("E" + index + suffix + "MLC").getString().trim()%>">
						</TD>
					</TR>
					<TR id="trdark0">
						<TD nowrap width="32%">
							<DIV align="right">E-mail :</DIV>
						</TD>
						<TD nowrap width="68%">
							<INPUT type="text" name="E<%= index %><%= suffix %>IAD" size="30" maxlength="40" <%=READ_ONLY_TAG%>
								 value="<%=mr.getField("E" + index + suffix + "IAD").getString().trim()%>" >
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			</TD>
		</TR>
	</TBODY>
</TABLE>

<script language=javascript>

 	document.getElementById("hiddenDivAddress").onclick=cancelBub;
 	if (document.getElementById("address.<%= index %>.<%= suffix %>")) 
 		document.getElementById("address.<%= index %>.<%= suffix %>").onclick=showHiddenDivAddress;

</script>
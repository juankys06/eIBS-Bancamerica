<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "msgHelp" class= "datapro.eibs.beans.JBObjList"   scope="session"/>

<script language="JavaScript">

function enterAction(code,desc) {
	var form = top.top.opener.document.forms[0];
	
	if(top.top.opener.fieldDesc != ""){form[top.top.opener.fieldDesc].value = desc;}
	if(top.top.opener.fieldName != ""){
		form[top.top.opener.fieldName].value = code;
		form[top.top.opener.fieldName].focus();
		form[top.top.opener.fieldName].select();
	}	  
	top.close();
}

function enter(){
	var NameSearch = document.forms[0].NameSearch.value;
	var FromRecord = 0;
	var Type = "";
	if(top.top.opener.fieldAux1 != undefined) {
		Type = top.top.opener.fieldAux1;
	}
	
	if (NameSearch.length < 1){
		NameSearch=".";
	}
	var params = "?URL='<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0360";
	params = params + "?NameSearch=" + NameSearch + "@Type=" + Type + "@FromRecord=" + FromRecord + "'";
    parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp"+params;
}
</script>
<META name="GENERATOR" content="IBM WebSphere Studio">
<title>Formulas de Calculo de Seguro - Info Adicional</title>
</head>

<body>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.helps.JSEWD0370&SCREEN=1">

<%
	int index = Integer.parseInt(request.getParameter("position"));
	msgHelp.setCurrentRow(index);
	datapro.eibs.beans.EWD0370DSMessage msg = (datapro.eibs.beans.EWD0370DSMessage) msgHelp.getRecord();
	if ( msg ==null ) {

%>
		<a href="javascript:getMoreInfo('<%= msg.getEWDTBL()%>');"><img src="<%=request.getContextPath()%>/images/warning.gif" align="middle" border="0" ></a>

<% 
		}
	else {
%>	
<H4 align="center">Tabla de Tasas de Seguros - Info Adicional</H4>
<TABLE class="tableinfo" align="center" style="width:'95%'">
	<tr id="trdark"> 
    	<td nowrap> 
          <div align="right"><b>Código de Tabla: </b></div>
        </td>
        <td nowrap> 
            <input type="text" name="EWDTBL" size="4" maxlength="3" value="<%= msg.getEWDTBL()%>" readonly>
        </td>  	
		<td nowrap> 
          <div align="right"><b>Descripción: </b></div>
        </td>
        <td nowrap> 
            <input type="text" name="EWDDSC" size="30" maxlength="35" value="<%= msg.getEWDDSC()%>" readonly>
        </td>
	</tr>
	<tr id="trdark"> 
		<td nowrap> 
          <div align="right"><b>Monto por Unidad de Capital : </b></div>
        </td>
        <td nowrap colspan="3"> 
            <input type="text" name="EWDSAMT" size="4" maxlength="3" value="<%= msg.getEWDSAMT()%>" readonly>
        </td>
  	</tr>
</TABLE>
<h4>Rango de Edad</h4>
<TABLE class="tableinfo" align="center" style="width:'95%'">

	<tr id="trdark"> 
		<th colspan="2">Edad</th>
		<th colspan="3">Titulares</th>
  	</tr>
	<tr id="trdark"> 
		<td>Lim. Inferior</td>
		<td>Lim. Superior</td>
		<td>Titular 1</td>
		<td>Titular 2</td>
		<td>Titular 3</td>
  	</tr>
	<%
			
			int count = 1;
			
			String fieldN = "EWDE" +count+"1";
			String fieldS = "EWDE" +count+"2";
			String fieldT1 = "EWDP" +count+"1";
			String fieldT2 = "EWDP" +count+"2";
			String fieldT3 = "EWDP" +count+"3";
			while(!msg.getField(fieldN).getString().equals("0")){
%>
	
	<tr id="trclear">
		<td nowrap align="center"><a><%= msg.getField(fieldN).getString()%></a></td>
		<td nowrap align="center"><a><%= msg.getField(fieldS).getString()%></a></td>
		<td nowrap align="left"><a><%= msg.getField(fieldT1).getString()%></a></td>
		<td nowrap align="center"><a><%= msg.getField(fieldT2).getString()%></a></td>
		<td nowrap align="center"><a><%= msg.getField(fieldT3).getString()%></a></td>
	</tr>
<%			count++;
			fieldN = "EWDE" +count+"1";
			fieldS = "EWDE" +count+"2";
			fieldT1 = "EWDP" +count+"1";
			fieldT2 = "EWDP" +count+"2";
			fieldT3 = "EWDP" +count+"3";

		
		}



		 %>

	 
      
</TABLE>
 
<%
   }  
%>
<TABLE align="center">

	<tr> 
		<td align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp" onClick="top.close()" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/exit_over.gif',1)" ><img name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" align="middle"></a></td>
  	</tr>
</TABLE>
</form>
</body>
</html>

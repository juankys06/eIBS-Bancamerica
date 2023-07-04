<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Boleta de Garantia</title>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "planDis" class= "datapro.eibs.beans.ELC500005Message" scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
   builtNewMenu(bg_m_opt);
   initMenu();

   function enviar(nn){
		f = document.forms[0];
		a = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
		fechas = new Array(15);
		fechaEx = ""		+ f.elements["E05LCPEXY"].value;
		fechaEx = fechaEx	+ f.elements["E05LCPEXM"].value;
		fechaEx = fechaEx	+ f.elements["E05LCPEXD"].value;
		nContador = 0;
		for( i=1; i<=15; i++ ){
			ind = ((i<10)?"0":"") + i;
			fechaD	= f.elements["E05L" + ind + "EXD"];
			fechaM	= f.elements["E05L" + ind + "EXM"];
			fechaA	= f.elements["E05L" + ind + "EXA"];
			monto	= f.elements["E05L" + ind + "DIS"];
			if( fechaD.value!='' && fechaM.value!='' && fechaA.value!='' && monto.value!='' ){
				vfechaD	= fechaD.value;
				vfechaM	= fechaM.value;
				vfechaA	= fechaA.value;
				vmonto	= monto.value;
				if( !esNumerico(vfechaD, '') || !esNumerico(vfechaM, '') || !esNumerico(vfechaA, '') ){
					alert("Error de datos en la Columna Nº " + ind + ". Error en Fecha." );
					return;
				}
				if( !esNumerico(vmonto, '.,') ){
					alert("Error de datos en la Columna Nº " + ind + ". Error en Monto." );
					return;
				}
				if(vfechaM<1 || vfechaM>12){
					alert("Error de datos en la Columna Nº " + ind + ", Fecha no corresponde." );
					return;
				}
				nDia = (((vfechaA%4)==0)&&vfechaM==2)?29:a[vfechaM-1];
				if( vfechaD>nDia ){
					alert("Error de datos en la Columna Nº " + ind + ", Fecha no corresponde." );
					return;
				}
				if( nContador>0 ){
					for( j=0; j<nContador; j++ ){
						if( fechas[j]==("" + vfechaA + vfechaM + vfechaD) ){
							alert("Revisar que fechas no se repitan.");
							return;
						}
					}
				}
				fechas[nContador] = "" + vfechaA + vfechaM + vfechaD;
				nContador++;
			}else if( fechaD.value.length>0 || fechaM.value.length>0 || fechaA.value.length>0 || monto.value.length>0 ){
				alert("Error de datos en la Columna Nº " + ind + ". No se han ingresado todos los datos." );
				return;
				/*fechaD.value='';
				fechaM.value='';
				fechaA.value='';
				monto.value='';*/
			}
		}
		if(nContador==0){
			alert("Debe ingresar un registro por lo menos");
			return;
		}else{
			f.elements["flagPlanDis"].value=1;
		}
   		f.SCREEN.value = nn;
   		f.submit();
   }
   function cancelar(nn){
		f = document.forms[0];
   		f.SCREEN.value = nn;
   		f.submit();
   }

	function esNumerico(cadena, otros){
		var num = "0123456789";
		isNum = true;
		for(j = 0, isNum = true; (j < cadena.length) && isNum; j++) {
			car = cadena.charAt(j);
/*			isNum = (num.indexOf(car) != - 1);
			if( otros.length>0 ){
				isNum = (otros.indexOf(car) != -1);
			}*/
			if( otros.length>0 ){
				otrosNum = "" + otros + num;
				isNum = (otrosNum.indexOf(car) != -1);
			}else{
				isNum = (num.indexOf(car) != - 1);
			}
		}
		return isNum;
	}
</SCRIPT>

</head>
<BODY>
<h3 align="center">Datos Plan de Disminucion<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="datos_plan_disminucion.jsp,ELC5000"></h3>
<hr size="4">

<FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000">

	<input type="hidden" name="E05LCPEXD" value="<%= planDis.getE05LCPEXD() %>">
	<input type="hidden" name="E05LCPEXM" value="<%= planDis.getE05LCPEXM() %>">
	<input type="hidden" name="E05LCPEXY" value="<%= planDis.getE05LCPEXY() %>">
	<input type="hidden" name="SCREEN" value="">
	<input type="hidden" name="flagPlanDis" value="<%= (request.getParameter("flagPlanDis")==null?"0":request.getParameter("flagPlanDis")) %>">
<table class=tableinfo>
  <tr>
   <td>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr id=trdark>
    	<th width="5%" align=center nowrap>Nº</th>
 	    <th width="24%" align=center nowrap>Fecha Vencimiento<br>Dia - Mes - Año</th>
 	    <th width="24%" align=center nowrap>Monto</th>
 	    <th width="24%" align=center nowrap>Aumento/Disminucion</th>
 	    <th width="24%" align=center nowrap>Aplicado</th>
	</tr>
    <tr id=trclear>
    	<td align="center" nowrap> 1 </td>
         <td align="center" nowrap>
         	<%	String apli1 = planDis.getE05L01STS();
         		String apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		String apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" maxlength="2" name="E05L01EXD" value="<%= planDis.getE05L01EXD().equals("0")?"":planDis.getE05L01EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" maxlength="2" name="E05L01EXM" value="<%= planDis.getE05L01EXM().equals("0")?"":planDis.getE05L01EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" maxlength="2" name="E05L01EXA" value="<%= planDis.getE05L01EXA().equals("0")?"":planDis.getE05L01EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" maxlength="20"  name="E05L01DIS" value="<%= planDis.getE05L01DIS().equals("0.00")?"":planDis.getE05L01DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L01IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L01IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L01IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L01STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trdark>
    	<td align="center" nowrap> 2 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L02STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L02EXD" maxlength="2" value="<%= planDis.getE05L02EXD().equals("0")?"":planDis.getE05L02EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L02EXM" maxlength="2" value="<%= planDis.getE05L02EXM().equals("0")?"":planDis.getE05L02EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L02EXA" maxlength="2" value="<%= planDis.getE05L02EXA().equals("0")?"":planDis.getE05L02EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L02DIS" maxlength="20" value="<%= planDis.getE05L02DIS().equals("0.00")?"":planDis.getE05L02DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L02IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L02IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L02IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L01STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 3 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L03STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L03EXD" maxlength="2" value="<%= planDis.getE05L03EXD().equals("0")?"":planDis.getE05L03EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L03EXM" maxlength="2" value="<%= planDis.getE05L03EXM().equals("0")?"":planDis.getE05L03EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L03EXA" maxlength="2" value="<%= planDis.getE05L03EXA().equals("0")?"":planDis.getE05L03EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L03DIS" maxlength="20" value="<%= planDis.getE05L03DIS().equals("0.00")?"":planDis.getE05L03DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L03IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L03IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L03IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L03STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trdark>
    	<td align="center" nowrap> 4 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L04STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L04EXD" maxlength="2" value="<%= planDis.getE05L04EXD().equals("0")?"":planDis.getE05L04EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L04EXM" maxlength="2" value="<%= planDis.getE05L04EXM().equals("0")?"":planDis.getE05L04EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L04EXA" maxlength="2" value="<%= planDis.getE05L04EXA().equals("0")?"":planDis.getE05L04EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L04DIS" maxlength="20" value="<%= planDis.getE05L04DIS().equals("0.00")?"":planDis.getE05L04DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L04IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L04IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L04IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L04STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 5 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L05STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L05EXD" maxlength="2" value="<%= planDis.getE05L05EXD().equals("0")?"":planDis.getE05L05EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L05EXM" maxlength="2" value="<%= planDis.getE05L05EXM().equals("0")?"":planDis.getE05L05EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L05EXA" maxlength="2" value="<%= planDis.getE05L05EXA().equals("0")?"":planDis.getE05L05EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L05DIS" maxlength="20" value="<%= planDis.getE05L05DIS().equals("0.00")?"":planDis.getE05L05DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L05IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L05IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L05IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L05STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trdark>
	    <td align="center" nowrap> 6 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L06STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L06EXD" maxlength="2" value="<%= planDis.getE05L06EXD().equals("0")?"":planDis.getE05L06EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L06EXM" maxlength="2" value="<%= planDis.getE05L06EXM().equals("0")?"":planDis.getE05L06EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L06EXA" maxlength="2" value="<%= planDis.getE05L06EXA().equals("0")?"":planDis.getE05L06EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L06DIS" maxlength="20" value="<%= planDis.getE05L06DIS().equals("0.00")?"":planDis.getE05L06DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E06L01IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L06IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L06IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L06STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 7 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L07STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L07EXD" maxlength="2" value="<%= planDis.getE05L07EXD().equals("0")?"":planDis.getE05L07EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L07EXM" maxlength="2" value="<%= planDis.getE05L07EXM().equals("0")?"":planDis.getE05L07EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L07EXA" maxlength="2" value="<%= planDis.getE05L07EXA().equals("0")?"":planDis.getE05L07EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L07DIS" maxlength="20" value="<%= planDis.getE05L07DIS().equals("0.00")?"":planDis.getE05L07DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L07IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L07IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L07IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L07STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trdark>
    	<td align="center" nowrap> 8 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L08STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L08EXD" maxlength="2" value="<%= planDis.getE05L08EXD().equals("0")?"":planDis.getE05L08EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L08EXM" maxlength="2" value="<%= planDis.getE05L08EXM().equals("0")?"":planDis.getE05L08EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L08EXA" maxlength="2" value="<%= planDis.getE05L08EXA().equals("0")?"":planDis.getE05L08EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L08DIS" maxlength="20" value="<%= planDis.getE05L08DIS().equals("0.00")?"":planDis.getE05L08DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L08IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L08IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L08IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L08STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 9 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L09STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L09EXD" maxlength="2" value="<%= planDis.getE05L09EXD().equals("0")?"":planDis.getE05L09EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L09EXM" maxlength="2" value="<%= planDis.getE05L09EXM().equals("0")?"":planDis.getE05L09EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L09EXA" maxlength="2" value="<%= planDis.getE05L09EXA().equals("0")?"":planDis.getE05L09EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L09DIS" maxlength="20" value="<%= planDis.getE05L09DIS().equals("0.00")?"":planDis.getE05L09DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L09IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L09IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L09IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L09STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trdark>
    	<td align="center" nowrap> 10 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L10STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L10EXD" maxlength="2" value="<%= planDis.getE05L10EXD().equals("0")?"":planDis.getE05L10EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L10EXM" maxlength="2" value="<%= planDis.getE05L10EXM().equals("0")?"":planDis.getE05L10EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L10EXA" maxlength="2" value="<%= planDis.getE05L10EXA().equals("0")?"":planDis.getE05L10EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L10DIS" maxlength="20" value="<%= planDis.getE05L10DIS().equals("0.00")?"":planDis.getE05L10DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L10IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L10IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L10IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L10STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 11 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L11STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L11EXD" maxlength="2" value="<%= planDis.getE05L11EXD().equals("0")?"":planDis.getE05L11EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L11EXM" maxlength="2" value="<%= planDis.getE05L11EXM().equals("0")?"":planDis.getE05L11EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L11EXA" maxlength="2" value="<%= planDis.getE05L11EXA().equals("0")?"":planDis.getE05L11EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L11DIS" maxlength="20" value="<%= planDis.getE05L11DIS().equals("0.00")?"":planDis.getE05L11DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L11IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L11IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L11IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L11STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trdark>
    	<td align="center" nowrap> 12 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L12STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L12EXD" maxlength="2" value="<%= planDis.getE05L12EXD().equals("0")?"":planDis.getE05L12EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L12EXM" maxlength="2" value="<%= planDis.getE05L12EXM().equals("0")?"":planDis.getE05L12EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L12EXA" maxlength="2" value="<%= planDis.getE05L12EXA().equals("0")?"":planDis.getE05L12EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L12DIS" maxlength="20" value="<%= planDis.getE05L12DIS().equals("0.00")?"":planDis.getE05L12DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L12IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L12IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L12IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L12STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 13 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L13STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L13EXD" maxlength="2" value="<%= planDis.getE05L13EXD().equals("0")?"":planDis.getE05L13EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L13EXM" maxlength="2" value="<%= planDis.getE05L13EXM().equals("0")?"":planDis.getE05L13EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L13EXA" maxlength="2" value="<%= planDis.getE05L13EXA().equals("0")?"":planDis.getE05L13EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L13DIS" maxlength="20" value="<%= planDis.getE05L13DIS().equals("0.00")?"":planDis.getE05L13DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L13IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L13IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L13IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L13STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trdark>
    	<td align="center" nowrap> 14 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L14STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L14EXD" maxlength="2" value="<%= planDis.getE05L14EXD().equals("0")?"":planDis.getE05L14EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L14EXM" maxlength="2" value="<%= planDis.getE05L14EXM().equals("0")?"":planDis.getE05L14EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L14EXA" maxlength="2" value="<%= planDis.getE05L14EXA().equals("0")?"":planDis.getE05L14EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L14DIS" maxlength="20" value="<%= planDis.getE05L14DIS().equals("0.00")?"":planDis.getE05L14DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L14IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L14IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L14IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L14STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 15 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L15STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L15EXD" maxlength="2" value="<%= planDis.getE05L15EXD().equals("0")?"":planDis.getE05L15EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L15EXM" maxlength="2" value="<%= planDis.getE05L15EXM().equals("0")?"":planDis.getE05L15EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L15EXA" maxlength="2" value="<%= planDis.getE05L15EXA().equals("0")?"":planDis.getE05L15EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L15DIS" maxlength="20" value="<%= planDis.getE05L15DIS().equals("0.00")?"":planDis.getE05L15DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L15IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L15IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L15IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L15STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 16 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L16STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L16EXD" maxlength="2" value="<%= planDis.getE05L16EXD().equals("0")?"":planDis.getE05L16EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L16EXM" maxlength="2" value="<%= planDis.getE05L16EXM().equals("0")?"":planDis.getE05L16EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L16EXA" maxlength="2" value="<%= planDis.getE05L16EXA().equals("0")?"":planDis.getE05L16EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L16DIS" maxlength="20" value="<%= planDis.getE05L16DIS().equals("0.00")?"":planDis.getE05L16DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L16IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L16IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L16IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L16STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
     <tr id=trclear>
    	<td align="center" nowrap> 17 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L17STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L17EXD" maxlength="2" value="<%= planDis.getE05L17EXD().equals("0")?"":planDis.getE05L17EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L17EXM" maxlength="2" value="<%= planDis.getE05L17EXM().equals("0")?"":planDis.getE05L17EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L17EXA" maxlength="2" value="<%= planDis.getE05L17EXA().equals("0")?"":planDis.getE05L17EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L17DIS" maxlength="20" value="<%= planDis.getE05L17DIS().equals("0.00")?"":planDis.getE05L17DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L17IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L17IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L17IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L17STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 18 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L18STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L18EXD" maxlength="2" value="<%= planDis.getE05L18EXD().equals("0")?"":planDis.getE05L18EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L18EXM" maxlength="2" value="<%= planDis.getE05L18EXM().equals("0")?"":planDis.getE05L18EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L18EXA" maxlength="2" value="<%= planDis.getE05L18EXA().equals("0")?"":planDis.getE05L18EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L18DIS" maxlength="20" value="<%= planDis.getE05L18DIS().equals("0.00")?"":planDis.getE05L18DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L18IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L18IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L18IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L18STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
        <tr id=trclear>
    	<td align="center" nowrap> 19 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L19STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L19EXD" maxlength="2" value="<%= planDis.getE05L19EXD().equals("0")?"":planDis.getE05L19EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L19EXM" maxlength="2" value="<%= planDis.getE05L19EXM().equals("0")?"":planDis.getE05L19EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L19EXA" maxlength="2" value="<%= planDis.getE05L19EXA().equals("0")?"":planDis.getE05L19EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L19DIS" maxlength="20" value="<%= planDis.getE05L19DIS().equals("0.00")?"":planDis.getE05L19DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L19IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L19IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L19IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L19STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 20 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L20STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L20EXD" maxlength="2" value="<%= planDis.getE05L20EXD().equals("0")?"":planDis.getE05L20EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L20EXM" maxlength="2" value="<%= planDis.getE05L20EXM().equals("0")?"":planDis.getE05L20EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L20EXA" maxlength="2" value="<%= planDis.getE05L20EXA().equals("0")?"":planDis.getE05L20EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L20DIS" maxlength="20" value="<%= planDis.getE05L20DIS().equals("0.00")?"":planDis.getE05L20DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L20IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L20IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L20IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L20STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 21 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L21STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L21EXD" maxlength="2" value="<%= planDis.getE05L21EXD().equals("0")?"":planDis.getE05L21EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L21EXM" maxlength="2" value="<%= planDis.getE05L21EXM().equals("0")?"":planDis.getE05L21EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L21EXA" maxlength="2" value="<%= planDis.getE05L21EXA().equals("0")?"":planDis.getE05L21EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L21DIS" maxlength="20" value="<%= planDis.getE05L21DIS().equals("0.00")?"":planDis.getE05L21DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L21IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L21IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L21IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L21STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 22 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L22STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L22EXD" maxlength="2" value="<%= planDis.getE05L22EXD().equals("0")?"":planDis.getE05L22EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L22EXM" maxlength="2" value="<%= planDis.getE05L22EXM().equals("0")?"":planDis.getE05L22EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L22EXA" maxlength="2" value="<%= planDis.getE05L22EXA().equals("0")?"":planDis.getE05L22EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L22DIS" maxlength="20" value="<%= planDis.getE05L22DIS().equals("0.00")?"":planDis.getE05L22DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L22IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L22IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L22IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L22STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 23 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L23STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L23EXD" maxlength="2" value="<%= planDis.getE05L23EXD().equals("0")?"":planDis.getE05L23EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L23EXM" maxlength="2" value="<%= planDis.getE05L23EXM().equals("0")?"":planDis.getE05L23EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L23EXA" maxlength="2" value="<%= planDis.getE05L23EXA().equals("0")?"":planDis.getE05L23EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L23DIS" maxlength="20" value="<%= planDis.getE05L23DIS().equals("0.00")?"":planDis.getE05L23DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L23IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L23IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L23IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L23STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 24 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L24STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L24EXD" maxlength="2" value="<%= planDis.getE05L24EXD().equals("0")?"":planDis.getE05L24EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L24EXM" maxlength="2" value="<%= planDis.getE05L24EXM().equals("0")?"":planDis.getE05L24EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L24EXA" maxlength="2" value="<%= planDis.getE05L24EXA().equals("0")?"":planDis.getE05L24EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L24DIS" maxlength="20" value="<%= planDis.getE05L24DIS().equals("0.00")?"":planDis.getE05L24DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L24IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L24IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L24IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L24STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
    <tr id=trclear>
    	<td align="center" nowrap> 25 </td>
         <td align="center" nowrap>
         	<%	apli1 = planDis.getE05L25STS();
         		apli2 = ((apli1.equals(""))?"disabled":(apli1.equalsIgnoreCase("P")?"disabled checked":""));
         		apli3 = apli1.equalsIgnoreCase("P")?"readonly":"";
         	%>
            <input type="text" size="2" name="E05L25EXD" maxlength="2" value="<%= planDis.getE05L25EXD().equals("0")?"":planDis.getE05L25EXD() %>" <%= apli3 %>>
            /
            <input type="text" size="2" name="E05L25EXM" maxlength="2" value="<%= planDis.getE05L25EXM().equals("0")?"":planDis.getE05L25EXM() %>" <%= apli3 %>>
            /
            <input type="text" size="4" name="E05L25EXA" maxlength="2" value="<%= planDis.getE05L25EXA().equals("0")?"":planDis.getE05L25EXA() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <input type="text" size="20" name="E05L25DIS" maxlength="20" value="<%= planDis.getE05L25DIS().equals("0.00")?"":planDis.getE05L25DIS() %>" <%= apli3 %>>
         </td>
         <td align="center" nowrap>
            <SELECT NAME="E05L25IDF" <%= apli3 %>>
            	<OPTION VALUE="D" <%= planDis.getE05L25IDF().equals("D")?"selected":"" %>>Disminucion</OPTION>
            	<OPTION VALUE="I" <%= planDis.getE05L25IDF().equals("I")?"selected":"" %>>Aumento</OPTION>
            </SELECT>
         </td>
         <td align="center" nowrap>
            <input type="checkbox" name="E05L25STS" value="<%= apli1 %>" <%= apli2 %>>
         </td>
    </tr>
  </table>
  </td>
  </tr>
</table>
<P align="center">
	<INPUT type="button" value="Aceptar" id="EIBSBTN" onClick="enviar(320)">
	<INPUT type="button" value="Cancelar" id="EIBSBTN" onClick="cancelar(330)">
</P>
</body>
</html>
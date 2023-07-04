<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language = "java" %>
<%@ page import = "datapro.eibs.master.Util, java.math.BigDecimal" %>
<html>
<head>
<title>Draft Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/graphical.jsp"> </SCRIPT>

<jsp:useBean id= "collBasic" class="datapro.eibs.beans.EDL081001Message"  scope="session" />
<jsp:useBean id= "dftList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "lstAcceptors" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<SCRIPT Language="Javascript">

//function goAction(opt) {
		       
//       	if (opt == '1' || opt == '2') { //New or Maintenance                
//    		page = webapp + "/servlet/datapro.eibs.products.JSEDL0810?SCREEN=1&OPT=" + opt; 
//    		CenterWindow(page,650,550,2);             
//       	}
//       	 else if (opt == '3' ) { //Delete
//       	    document.forms[0].SCREEN.value = "1";
//       	    document.forms[0].OPT.value = "3";
//       	    document.forms[0].submit();
//       	}
//		 else if (opt == '4') { //Acept
//			document.forms[0].SCREEN.value = "4";
//       	    document.forms[0].submit();
//		}            					
//}
function goAction(opt) {
		var exists = false;
       if (document.forms[0].E01DLHNRO.value != "999999999999"){
       	if (opt == 'N') {
                 if (document.forms[0].ideacc.value == "") {
    		   	    page = webapp + "/servlet/datapro.eibs.products.JSEDL0810?SCREEN=600&ACTION=V"+"&ADDRESS="+document.forms[0].address.value;
                 } else {
                 	var formLength= document.forms[0].elements.length;
		   			
		   			for(n=0;n<formLength;n++){
      	  	  			var elementName= document.forms[0].elements[n].name;
      	  	  			if (elementName == "IDACC1" && document.forms[0].ideacc.value == document.forms[0].elements[n].value){
      	  	  	  			exists = true;
      	  	  	  			break;
      	  	  			}
		   			}
		   			if (exists) {
		   			  alert("Aceptante ya existe!");
		   			} else {
			          page = webapp + "/servlet/datapro.eibs.products.JSEDL0810?SCREEN=600&ACTION=V&IDEACC=" + document.forms[0].ideacc.value+"&IDENDA="+document.forms[0].idenda.value+"&ADDRESS="+document.forms[0].address.value;
			        }
                 }
       	}
       	if (opt == 'M') {
       	   page = webapp + "/servlet/datapro.eibs.products.JSEDL0810?SCREEN=600&ACTION=V&IDEACC=" + document.forms[0].IDACC1.value+"&IDENDA="+document.forms[0].idenda1.value;
       	}
		if (opt == 'D') {
           //alert(document.forms[0].idenda1.value)
		   var formLength= document.forms[0].elements.length;
		   var ideacc = "";
		   for(n=0;n<formLength;n++){
      	  	  var elementName= document.forms[0].elements[n].name;
      	  	  if (elementName == "IDACC1" && document.forms[0].elements[n].checked){
      	  	  	  ideacc = document.forms[0].elements[n].value;
      	  	  	  break;
      	  	  }
		   }
           if (ideacc == "") {
             alert("No existen aceptantes");
           } else {
       	     page = webapp + "/servlet/datapro.eibs.products.JSEDL0810?SCREEN=800&ACTION=V&E01DLHNRO=" + document.forms[0].E01DLHNRO.value +"&IDEACC="+ideacc+"&GRP=4&IDENDA="+document.forms[0].idenda1.value;
       	   }
       	}
        if (exists == false) {      
			CenterWindow(page,650,550,2);
		}
	} else {
         alert("Primero debe generar el numero de cuenta ..");
       }
}

function closeHiddenDivNew(){
	setVisibility(document.getElementById("hiddenDivNew"), "hidden");
}

function showHiddenDivNew(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var hiddenDivNew = document.getElementById("hiddenDivNew");

	var y=evt.clientY + document.body.scrollTop;
	var x=evt.clientX + document.body.scrollLeft;
     
	cancelBub(evt);

	moveElement(hiddenDivNew, y, x);
	setVisibility(hiddenDivNew, "visible");
	
    document.forms[0].ideacc.focus();
}

function goNew(evt){

	if (document.forms[0].E01DLHNRO.value != "999999999999"){
		showHiddenDivNew(evt);
	} else {
		alert("Primero debe generar el numero de cuenta ..");
	}
}

function showDirecciones(){

	if (document.forms[0].ideacc.value != ""){
		GetMailing('address',document.forms[0].ideacc.value)
	} else {
		alert("Primero debe ingresar Identificación de Aceptante ..");
        document.forms[0].ideacc.focus();		
	}
}


document.onclick= closeHiddenDivNew;

function setNDA(val) {
//alert(val);
document.forms[0].idenda1.value = val;
}
</SCRIPT>

</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }
%> 

<h3 align="center">Carta Guia de Cobranzas<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="collection_list.jsp,EDL0810"> 
</h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0810" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="1400">
  <INPUT TYPE=HIDDEN NAME="OPT" VALUE="">
 <div id="hiddenDivNew" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<TR>
	  <TD ALIGN=right nowrap>
          Ingrese Identificación de Aceptante : 
      </TD>
	  <TD ALIGN=left nowrap>
          <input type="text" maxlength=15 size=15 name="ideacc" value="">
          <input type="hidden" maxlength=15 size=15 name="idenda" value="">
          <input type="hidden" maxlength=15 size=15 name="idenda1" value="">
	   <a href="javascript:GetAcceptantIdNDA('ideacc','idenda')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a>
      </TD>      
   </TR>
   
	<TR>
	  <TD ALIGN=right nowrap>
          Ingrese Número de Dirección : 
      </TD>
	  <TD ALIGN=left nowrap>
          <input type="text" maxlength=1 size=2 name="address" value="">
		<a href="javascript:showDirecciones()"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absmiddle" border="0" ></a> 
      </TD>
   </TR>
	<TR>
      <TD ALIGN=center nowrap colspan="2">
	     <a href="javascript:goAction('N')"><img name="Image1" border="0" src="<%=request.getContextPath()%>/images/s/applicar_on.gif"></a> 
	  </TD>
   </TR>
   
 </TABLE>
</div>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap> 
                <input type="text" name="E01DLHCUN" size="9" maxlength="9" value="<%= collBasic.getE01DLHCUN() %>" readonly>
            </td>
            <td nowrap> 
              <div align="right"><b>Nombre :</b></div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" value="<%= collBasic.getE01CUSNA1() %>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right"><b>Planilla :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
				<input type="text" name="E01DLHNRO" size="12" maxlength="12" value="<%= collBasic.getE01DLHNRO()  %>" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Moneda :</b></div>
            </td>
            <td nowrap> 
              <div align="left"> 
                <input type="text" name="E01DLHCCY" size="4" maxlength="3" value="<%= collBasic.getE01DLHCCY() %>" readonly>
              </div>
            </td>
            <td nowrap> 
              <div align="right"><b>Producto :</b></div>
            </td>
            <td nowrap> 
              <div align="left">
                <input type="text" name="E01DLHPRD" size="4" maxlength="4" value="<%= collBasic.getE01DLHPRD() %>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Información Basica</h4> 
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellpadding=2 cellspacing=0 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="25%"> 
              <div align="right">Fecha de Apertura :</div>
            </td>
            <td nowrap width="23%"> 
              <input type="text" name="E01DLHOP1" size="2" maxlength="2" value="<%= collBasic.getE01DLHOP1() %>" readonly>
              <input type="text" name="E01DLHOP2" size="2" maxlength="2" value="<%= collBasic.getE01DLHOP2() %>" readonly>
              <input type="text" name="E01DLHOP3" size="2" maxlength="2" value="<%= collBasic.getE01DLHOP3() %>" readonly>
            </td>
            <td nowrap width="25%"> 
              <div align="right">No. de Documentos :</div>
            </td>
            <td nowrap>
              <INPUT type="text" name="E01DLHNDO" size="3" maxlength="2" value="<%= collBasic.getE01DLHNDO() %>" onkeypress="enterInteger()" readonly>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Tipo Cobranza :</div>
            </td>
            <td nowrap> 
              <select name="E01DLHTCB" disabled>
		  		<option value="1" <% if (collBasic.getE01DLHTCB().equals("1")) out.print("selected"); %>>Simple</option>
                <option value="2" <% if (collBasic.getE01DLHTCB().equals("2")) out.print("selected"); %>>Garantia</option>
              </select>
            </td>
            <td nowrap> 
              <div align="right">Monto Original :</div>
            </td>
            <td nowrap>
            	<INPUT type="text" name="E01DLHTAM" size="15" maxlength="15" value="<%= collBasic.getE01DLHTAM() %>" onkeypress="enterDecimal()" readonly>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="25%" ><div align="right">
				<DIV align="right">Tipo de Documento :</DIV>
				
            </td>
            <td nowrap width="23%" > 
            <SELECT name="E01DLHTDO" disabled>
					<OPTION value="1"
						<% if (collBasic.getE01DLHTDO().equals("1")) out.print("selected"); %>>Letras</OPTION>
					<OPTION value="2"
						<% if (collBasic.getE01DLHTDO().equals("2")) out.print("selected"); %>>Pagares</OPTION>
					<OPTION value="3"
						<% if (collBasic.getE01DLHTDO().equals("3")) out.print("selected"); %>>Facturas</OPTION>
					<OPTION value="4"
						<% if (collBasic.getE01DLHTDO().equals("4")) out.print("selected"); %>>Otros</OPTION>
				</SELECT></td>
            <td nowrap width="25%" > 
              <div align="right">Cuenta Corriente Abono:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DLHCAC" size="12" maxlength="12" value="<%= collBasic.getE01DLHCAC() %>" onkeypress="enterInteger()" readonly>
              <a href="javascript:GetAccount('E01DLHCAC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
            </td>
          </tr>
          
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Instrucciones de Protesto:</div>
            </td>
            <td nowrap colspan=1> 
              <select name="E01DLHTPO" disabled>
		  		<option value="1" <% if (collBasic.getE01DLHTPO().equals("1")) out.print("selected"); %>>Con Protesto</option>
                <option value="2" <% if (collBasic.getE01DLHTPO().equals("2")) out.print("selected"); %>>Sin Protesto</option>
                <option value="3" <% if (collBasic.getE01DLHTPO().equals("3")) out.print("selected"); %>>C/Prot Por falta de Acepatcion</option>
              </select>
            </td>
            <td nowrap width="25%" > 
              <div align="right">Cuenta Corriente Cargo:</div>
            </td>
            <td nowrap width="27%" > 
              <input type="text" name="E01DLHDAC" size="12" maxlength="12" value="<%= collBasic.getE01DLHDAC() %>" onkeypress="enterInteger()" readonly>
              <a href="javascript:GetAccount('E01DLHCAC','','RT','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a>
            </td>

          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Nro. Carta Guia:</div>
            </td>
            <td nowrap colspan=3> 
              <input type="text" name="E01DLHNCG" size="12" maxlength="12" value="<%=collBasic.getE01DLHNCG()%>" onkeypress="enterInteger()" readonly>
            </td>
 
          </tr>

         </table>
      </td>
    </tr>
  </table>

<h4>Aceptantes</h4>

<TABLE class="tableinfo">
   <tr > 
      <td nowrap>
            <table cellpadding="2" cellspacing="0" width="100%" border="0">

    <TR bgcolor="#FFFFFF">
    <TD width="14">
    </TD>
    <TD align="center" width="50%">
    	<IMG id="eibsNew" src="<%=request.getContextPath()%>/images/s/aceptante.gif" align="middle" border="0" style="cursor:pointer" 
    	onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image1','','<%=request.getContextPath()%>/images/s/aceptante_over.gif',1)"> 
    </TD>
    <TD colspan="2" align="center"  width="50%"><A href="javascript:goAction('D')" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image5','','<%=request.getContextPath()%>/images/s/documents_over.gif',1)"><IMG name="Image5" border="0" src="<%=request.getContextPath()%>/images/s/documents.gif"></A>
    </TD>
    <TD width="10%">
    </TD>
                    </TR>
    <tr id="trdark">
      <TD nowrap width="14"> 
          <div align="center"></div>
        </TD>
        <TD nowrap> 
          <div align="center">N.Letra</div>
        </TD>
<TD nowrap> 
          <div align="center">Identificación</div>
        </TD>
                        <TD nowrap> 
          <div align="center">Nombre</div>
        </TD>
                        <TD nowrap> 
          <div align="center">Monto</div>
        </TD>
                        <% 
	lstAcceptors.initRow();
	boolean firstTime = true;
	String chk = "";
     	while (lstAcceptors.getNextRow()) {
         if (lstAcceptors.getFlag().equals("")) {
     		out.print("<tr id=\"trclear\">");
		out.print("<TD width=\"14\">");
              if (firstTime) {
                 chk = "checked"; 
                 firstTime = false;
              } else {
                 chk = ""; 
		}
		out.print("<div align=\"center\"><INPUT type=\"radio\" name=\"IDACC1\" value=\"" + lstAcceptors.getRecord(0) +"\" " + chk + " onClick=\"javascript:setNDA('"+ lstAcceptors.getRecord(3)+"')\"></div>");
		out.print("<TD nowrap width=\"162\">");
		out.print("<div align=\"center\"><INPUT size=\"20\" type=\"text\" name=\"DDIB\" value=\"" + lstAcceptors.getRecord(4) + "\" readonly></div>");
		out.print("</TD>");
		out.print("<TD nowrap width=\"162\">");
		out.print("<div align=\"center\"><INPUT size=\"20\" type=\"text\" name=\"IDACCEPTOR\" value=\"" + lstAcceptors.getRecord(0) + "\" readonly></div>");
		out.print("</TD>");
		out.print("<TD nowrap width=\"151\">");
		out.print("<div align=\"center\"><INPUT size=\"35\" type=\"text\" name=\"NMEACCEPTOR\" value=\"" + lstAcceptors.getRecord(1) + "\" readonly></div>");
		out.print("</TD>");
		out.print("<TD nowrap width=\"232\">");
		out.print("<div align=\"center\"><INPUT size=\"15\" type=\"text\" name=\"TOTACCEPTOR\" value=\"" + lstAcceptors.getRecord(2) + "\" readonly></div>");
		out.print("</TD>");
		out.print("</tr>");
         }
       }
%>
    </td>
    </tr>
   </table>
            </tr>
</table>
<p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Validar">
  </p>
  
</form>
<script>

 	document.getElementById("hiddenDivNew").onclick=cancelBub;
	document.getElementById("eibsNew").onclick=goNew;
</script>
</body>
</html>

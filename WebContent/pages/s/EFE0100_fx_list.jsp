<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Foreign Exchange G/L Cross Reference List</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "beanList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">


function goAction(op) {
    document.forms[0].opt.value = op;
    if (op == 'N') {
    	document.forms[0].SCREEN.value = 4;
    } else {
    	document.forms[0].SCREEN.value = 3;
    }
    document.forms[0].submit();
}

function setInfo(bnk, ccy, typ, cls) {
    document.forms[0].BNK.value = bnk;
    document.forms[0].CCY.value = ccy;
    document.forms[0].TYP.value = typ;
    document.forms[0].CLS.value = cls;
}

 function closeEnter(){
   	  enterNew.filters[0].apply();
      enterNew.style.visibility="hidden";
      enterNew.filters[0].Play();
 }
 
 function ShowEnterCod() {	 

	 var y=event.clientY + document.body.scrollTop;
     var x=event.clientX + document.body.scrollLeft;
	 cancelBub();
	 eval('enterNew.style.pixelTop=' + y);
     eval('enterNew.style.pixelLeft=' + x);
	 enterNew.filters[0].apply();
     enterNew.style.visibility="visible";
     enterNew.filters[0].Play();
}
 
function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;

</SCRIPT>
</head>

<body>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
	 error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>

<h3 align="center">Listado Referencias Cruzadas Cuenta Contable M/E<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="fx_list.jsp, EFE0100"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEFE0100">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <INPUT TYPE=HIDDEN NAME="BNK" VALUE="">
  <INPUT TYPE=HIDDEN NAME="CCY" VALUE="">
  <INPUT TYPE=HIDDEN NAME="TYP" VALUE="">
  <INPUT TYPE=HIDDEN NAME="CLS" VALUE="">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
     
 <div id="enterNew" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onClick="cancelBub()"> 
    <table id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
      <tr id="trdark"> 
        <td align=CENTER width="50%"> 
          <div align="right">Banco :</div>
        </td>
        <td align=CENTER width="50%"> 
          <div align="left"> 
            <input type="text" name="NEWBNK" size="3" maxlength="2" value="" onkeypress="enterInteger()">
          </div>
        </td>
      </tr>
      <tr id="trdark"> 
        <td align=CENTER width="50%"> 
          <div align="right">Moneda :</div>
        </td>
        <td align=CENTER width="50%"> 
          <div align="left"> 
            <input type="text" name="NEWCCY" size="4" maxlength="3" value="">
            <a href="javascript:GetCurrency('NEWCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a> 
          </div>
        </td>
      </tr>
      <tr id="trdark"> 
        <td align=CENTER width="50%"> 
          <div align="right">Tipo de Contrato :</div>
        </td>
        <td align=CENTER width="50%"> 
          <div align="left"> 
	            <select name="NEWTYP" >
	              <option value=" " selected></option>
	              <option value="SPOT" >Spot</option>
	              <option value="FWRD" >Forward</option>
	              <option value="OPTI" >Options</option>
	              <option value="SWAP" >Swap</option>
	            </select>
          </div>
        </td>
      </tr>
      <tr id="trdark"> 
        <td align=CENTER width="50%"> 
          <div align="right">Clasificación :</div>
        </td>
        <td align=CENTER width="50%"> 
          <div align="left"> 
            <input type="text" name="NEWCLS" size="5" maxlength="4" value="">
            <a href="javascript:GetCodeCNOFC('NEWCLS','16')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="middle" border="0" ></a>
          </div>
        </td>
      </tr>
       <tr id="trclear"> 
        <td align=center nowrap colspan="2"> 
          <input id="EIBSBTN" type=button name="Submit1" value="Someter" onClick="javascript:goAction('N')">
        </td>
      </tr>
      
    </table>
  </div>
           
<%
	if ( beanList.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No existen resultados para los criterios de busqueda. <br>Click en Nuevo para crear</h4> 
      </div>
      </TD></TR>
   	</TABLE>
   	<TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER class="TDBKG" width="50%">
   		<div align="center" style="cursor:hand" onClick="ShowEnterCod()"><a>Nuevo</a></div>
      </TD>      
      <TD ALIGN=CENTER class="TDBKG" width="50%"><a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a>
      </TD>
    </TR>
  </TABLE>
<%
	}
	else {
%>
  
  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER class="TDBKG" width="33%">
  		<div align="center" style="cursor:hand" onClick="ShowEnterCod()"><a>Nuevo</a></div>
      </TD>
      <TD ALIGN=CENTER class="TDBKG" width="33%"><a href="javascript:goAction('M')">Actualizar</a>
      </TD>      
      <TD ALIGN=CENTER class="TDBKG" width="34%"><a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a>
      </TD>
    </TR>
  </TABLE>
  <TABLE class="tableinfo" id="dataTable">
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap width="1%"></TH> 
      <TH ALIGN=CENTER  nowrap >Banco</TH>
      <TH ALIGN=CENTER  nowrap >Moneda</TH>
      <TH ALIGN=CENTER  nowrap >Tipo de <BR>
		Contrato</TH>
      <TH ALIGN=CENTER  nowrap >Clasificación</TH>      
    </TR>
    
    <%
          boolean firstTime = true;  
          String chk = "";  
          beanList.initRow(); 
          int k=1;              
          while (beanList.getNextRow()) {
               EFE010001Message msgList = (EFE010001Message) beanList.getRecord();		
               if (firstTime) {
	               	chk = "checked";
	               	firstTime = false;
               } else {
               		chk = "";
                }	 
       %>             
        <TR>
          <td align="center" nowrap > 
        	<input type="radio" name="ROW1" <%= chk %> value="<%= beanList.getCurrentRow() %>" onClick="setInfo('<%= msgList.getE01FEGBNK()%>', '<%= msgList.getE01FEGCCY() %>', '<%= msgList.getE01FEGTYP() %>', '<%= msgList.getE01FEGCLS() %>')" >
      	  </td>
          <td NOWRAP align=center>
          	<%=Util.formatCell(msgList.getE01FEGBNK())%>
		  </td>		  
		  <td NOWRAP >
			<%=Util.formatCell(msgList.getE01FEGCCY())%>
		  </td>		  
		  <td NOWRAP >
          	<%=Util.formatCell(msgList.getE01FEGTYP())%>
		  </td>
		  <td NOWRAP >
          	<%=Util.formatCell(msgList.getE01FEGCLS())%>
		  </td>
		</TR>
		
        <% 
        	k++;
         }
         %> 
  </TABLE>
  <SCRIPT language="JavaScript">     
  	document.forms[0].totalRow.value="<%= k %>";
	 showChecked("ROW1");          
  </SCRIPT> 
<%      
  }
%> 
</form>
</body>
</html>

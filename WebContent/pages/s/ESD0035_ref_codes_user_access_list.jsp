<html> 
<head>
<title>Autorización de Accesos a Códigos de Referencia</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import = "datapro.eibs.master.Util" %>

<jsp:useBean id= "codList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

function goAction(op) {
	document.forms[0].opt.value = op;
	if (op == 4){
		if(confirm("¿Está seguro de que desea eliminar este registro?")){
			document.forms[0].submit();
		} 
	} else {
		document.forms[0].submit();
	}
}

function closeEnter(){
   	  setVisibility(document.getElementById("enterData"),"hidden");
}

function showEnterData(evt) {	 
     evt = (evt)? evt: ((window.event) ? window.event : "");
	 document.forms[0].E01CRETCN.value = "";
 	 var enterData= document.getElementById("enterData");
	 var y=evt.clientY + document.body.scrollTop;
     var x=evt.clientX + document.body.scrollLeft;
	 cancelBub(evt);
	 eval('enterData.style.pixelTop=' + y);
     eval('enterData.style.pixelLeft=' + x);
	 moveElement(enterData, y, x);
	 setVisibility(enterData,"visible");
}


</SCRIPT>  

</head>

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
 	 error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>
<BODY>
<h3 align="center">Autorización de Accesos a Códigos de Referencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="ref_codes_user_access_list.jsp, ESD0035"> </h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESD0035" >
  <input type=HIDDEN name="SCREEN" value="4">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CURRENTROW" value="">

  <div id="enterData" class="hiddenDiv">
    <table id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
      <tr id="trdark"> 
        <td align=CENTER width="18%"> 
          <div align="right">Código de Referencia :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
	        <input type="radio" name="E01CREALL" value="*">Todos los Códigos de Referencia
          </div>
        </td>
      </tr>
      <tr id="trdark"> 
        <td align=CENTER width="18%"> 
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
	        <input type="radio" name="E01CREALL" value="N">Seleccione Código de Referencia
    		<input type="text" name="E01CRETCN" size="3" maxlength="2" value="" onFocus="document.forms[0].E01CREALL[1].click()">
    		<a href="javascript:GetReferenceCode('E01CRETCN')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" onClick="document.forms[0].E01CREALL[1].click()"></a>
          </div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td align=center nowrap colspan="2"> 
          <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="javascript:goAction(1);">
        </td>
      </tr>
    </table>
  </div>
  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0">
		    <tr id="trdark"> 
		      <td nowrap width="50%"> 
		      	<div align="right">Usuario :</div>
		      </td> 
		      <td nowrap width="50%"> 
		        <INPUT type="text" name="E01CREUSR" size="12" maxlength="10" value="<%= userPO.getIdentifier() %>" readonly>
		      </td>
		    </tr> 
		</TABLE>
      </td>
    </tr>
  </table>

  <%
	if ( codList.getNoResult() ) {
 %>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
           <tr>
            <td class=TDBKG>
 				<div align="center" style="cursor:hand" onClick="showEnterData()"><a><b>Agregar</b></a></div>
              </td>
              <td class=TDBKG>
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
           </tr>
         </table>
	  </div>

	  </TD>
	</TR>
    </TABLE>
	
  <%  
		}
	else {
%> 
  <p> 

          
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG> 
 		<div align="center" style="cursor:hand" onClick="showEnterData()"><a><b>Agregar</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="javascript:goAction(4)"><b>Eliminar</b></a></div>
      </td>
      <td class=TDBKG> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  
  <TABLE  id=cfTable class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%">
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="2%"></th>
            <th align=CENTER nowrap width="30%">Código</th>
            <th align=CENTER nowrap width="68%">Descripción</th>
          </tr>
     	<%
        codList.initRow(); 
		boolean firstTime = true;
		String chk = "";
        while (codList.getNextRow()) {
			if (firstTime) {
				firstTime = false;
				chk = "checked";
			} else {
				chk = "";
			}
           datapro.eibs.beans.ESD003501Message msgPart = (datapro.eibs.beans.ESD003501Message) codList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= codList.getCurrentRow()%>" <%=chk%> 
				onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01CRETCN())%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=Util.formatCell(msgPart.getE01DESCRI())%></TD>
		</TR>    		
    	<%}%>    
        </table>
    </table>
     
  <%}%>

<SCRIPT language="JavaScript">  
  showChecked("ROW");  
  document.onclick=closeEnter;
  document.getElementById("enterData").onclick=cancelBub;
</SCRIPT>
</form>

</body>
</html>

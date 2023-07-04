<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Lista Cuentas para Cambio Producto</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ESD090001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">



function goAction(op) {
	if (op == "3") {
    	ok = confirm("¿Está seguro de que desea eliminar el registro seleccionado?");
		if (ok) 
	    {
	    	document.forms[0].opt.value = op;
	    	document.forms[0].submit();
	    }  
	}
	else {
		document.forms[0].opt.value = op;
		document.forms[0].submit();
	}
}

function getParams(currrow,apl,acc,oldvalue,newvalue,type) {
	document.forms[0].CURRENTROW.value = currrow;
    document.forms[0].APL.value = apl;
    document.forms[0].ACC.value = acc;
    document.forms[0].OLD.value = oldvalue;
    document.forms[0].NEW.value = newvalue;
    document.forms[0].TYPE.value = type;
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Cambio de Producto <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="product_change_list.jsp, ESD0900"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0900" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="APL" value="">
    <input type=HIDDEN name="ACC" value="">
    <input type=HIDDEN name="OLD" value="">
    <input type=HIDDEN name="NEW" value="">
    <input type=HIDDEN name="TYPE" value="">
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( ESD090001Help.getNoResult() ) {
 %>
  </p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>No hay resultados para su b&uacute;squeda</b></p>
          <table class="tbenter" width=100% align=center>
            <tr> 
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="javascript:goAction(0)"><b>Nuevo Cambio<br>Individual en Cuenta</b></a></div>
              </td>
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="javascript:goAction(1)"><b>Nuevo Cambio Masivo</b></a></div>
              </td>
              <td class=TDBKG width="30%"> 
                <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
              </td>
            </tr>
          </table>
          <p>&nbsp;</p>
          
        </div>

	  </TD>
	</TR>
    </TABLE>
	
  <%  
	}
	else {
%> <% 

String chk = "";
String changeType = "";
String previousValue = "";
String newValue = "";

 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
  <p> 

          
  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction(0)"><b>Nuevo Cambio<br>Individual en Cuenta</b></a></div>
      </td>
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction(1)"><b>Nuevo Cambio Masivo</b></a></div>
      </td>
	  <td class=TDBKG width="20%">
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div> 
      </td> 
	  <td class=TDBKG width="20%"> 
        <div align="center"><a href="javascript:goAction(3)"><b>Eliminar</b></a></div>
      </td>
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="2%">&nbsp;</th>
            <th align=CENTER nowrap width="13%">Módulo</th>
            <th align=CENTER nowrap width="25%"> 
              <div align="center">Descripción</div>
            </th>
            <th align=CENTER nowrap width="20%"> 
              <div align="center">Tipo Cambio</div>
            </th>
            <th align=CENTER nowrap width="8%"> 
              <div align="center">Tipo<br>Producto</div>
            </th>
            <th align=CENTER nowrap width="8%"> 
              <div align="center">Referencia</div>
            </th>
            <th align=CENTER nowrap width="8%"> 
              <div align="center">Valor<br>Anterior</div>
            </th>
            <th align=CENTER nowrap width="8%"> 
              <div align="center">Valor<br>Nuevo</div>
            </th>
            <th align=CENTER nowrap width="8%"> 
              <div align="center">Usuario</div>
            </th>
            
           </tr>
          <%
                ESD090001Help.initRow();
                chk = "checked";
                while (ESD090001Help.getNextRow()) {
                 
                  datapro.eibs.beans.ESD090001Message msgList = (datapro.eibs.beans.ESD090001Message) ESD090001Help.getRecord();
                  
                  if (msgList.getE01CHGTYP().equals("1")) {
                  	changeType = "Cambio Individual en Cuenta";
                  	previousValue = msgList.getE01CHGPRO();
                  	newValue = msgList.getE01CHGPRC();
                  }
                  else if (msgList.getE01CHGTYP().equals("2")) {
                  	changeType = "Cambio Masivo en Producto";
                  	previousValue = msgList.getE01CHGPRO();
                  	newValue = msgList.getE01CHGPRC();
                  }
                  else if (msgList.getE01CHGTYP().equals("3")) {
                  	changeType = "Cambio Masivo en Sucursal";
                  	previousValue = msgList.getE01CHGOBR();
                  	newValue = msgList.getE01CHGNBR();
                  }
                  else if (msgList.getE01CHGTYP().equals("4")) {
                  	changeType = "Cambio Masivo en Oficial";
                  	previousValue = msgList.getE01CHGOFC();
                  	newValue = msgList.getE01CHGNFC();
                  }
                  else {
                  	changeType = "";
                  	previousValue = "";
                  	newValue = "";
                  }
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="2%"> 
              <input type="radio" name="CURRCODE" value="<%= ESD090001Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value,'<%= msgList.getE01CHGAPL() %>','<%= msgList.getE01CHGACC() %>','<%= previousValue %>','<%= newValue %>','<%= msgList.getE01CHGTYP() %>');">
            </td>
            <td NOWRAP  align=CENTER width="13%"><%= msgList.getE01CHGAPL() %></td>
			<td NOWRAP  align=CENTER width="25%"><%= msgList.getE01MODDSC() %></td>
            <td NOWRAP  align=CENTER width="20%"><%= changeType %></td>
            <td NOWRAP  align=CENTER width="8%"><%= msgList.getE01CHGPRT() %></td>
            <td NOWRAP  align=CENTER width="8%"><%= msgList.getE01CHGACC() %></td>
            <td NOWRAP  align=CENTER width="8%"><%= previousValue %></td>
            <td NOWRAP  align=CENTER width="8%"><%= newValue %></td>
            <td NOWRAP  align=CENTER width="8%"><%= msgList.getE01CHGIUS() %></td>

         </tr>
          <%
              				chk = "";     
                }
              %>
        </table>
  </table>
     
  <SCRIPT language="JavaScript">
 showChecked("CURRCODE");
     
</SCRIPT>

<%}%>


  </form>

</body>
</html>

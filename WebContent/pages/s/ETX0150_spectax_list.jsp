<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ETX015001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">
 


function goAction(op) {

	if (op == "3") {
      ok = confirm("Esta seguro que desea eliminar el registro seleccionado?");
	  if (ok) 
	       {
	       document.forms[0].opt.value = op;
	       document.forms[0].submit();
	       }  
	  
	} else 
	  {
	    document.forms[0].opt.value = op;
	    document.forms[0].submit();
	  }
  
}


function getParams(currrow,accnum,pdm,pdd,pdy,pdt) {

	document.forms[0].CURRENTROW.value = currrow;
    document.forms[0].ACCNUM.value = accnum;
    document.forms[0].DTEPDM.value = pdm;
    document.forms[0].DTEPDD.value = pdd;
    document.forms[0].DTEPDY.value = pdy;
    document.forms[0].DTEPDT.value = pdt;
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Control de Pago de Iva Especial<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="spectax_list.jsp, ETX0150"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSETX0150" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="ACCNUM" value="  " size=13 maxlength=12>
    <input type=HIDDEN name="DTEPDM" value="   " size=3 maxlength=2>
    <input type=HIDDEN name="DTEPDD" value="   " size=3 maxlength=2>
    <input type=HIDDEN name="DTEPDY" value="   " size=3 maxlength=2>
    <input type=HIDDEN name="DTEPDT" value="   " size=7 maxlength=6>
    
     
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( ETX015001Help.getNoResult() )   {
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
      
	  <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Registrar<BR>Documento</b></a></div>
      </td>
	  <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(3)"><b>Eliminar</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
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
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="10%">Cuenta</th>
            <th align=CENTER nowrap width="25%"> 
              <div align="center">Cliente</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Nombre</div>
            </th>
            <th align=CENTER nowrap width="25%"> 
              <div align="center">Fecha <BR> Transacci&oacute;n </div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center"> Fecha <BR> L&iacute;mite </div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center"> Monto <BR> Transacci&oacute;n </div>
            </th>
            
           </tr>
          <%
                ETX015001Help.initRow();
                chk = "checked";
                while (ETX015001Help.getNextRow()) {
                 
                  datapro.eibs.beans.ETX015001Message msgList = (datapro.eibs.beans.ETX015001Message) ETX015001Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= ETX015001Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value,<%= msgList.getE01TXCACC() %>,<%= msgList.getE01TXCPDM() %>,<%= msgList.getE01TXCPDD() %>,<%= msgList.getE01TXCPDY() %>,<%= msgList.getE01TXCPDT() %>);">
            </td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= Util.formatCell(msgList.getE01TXCACC()) %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= Util.formatCell(msgList.getE01TXCCUN()) %></td>
            <td NOWRAP  align=CENTER width=\"25%\"><%= Util.formatCell(msgList.getE01CUSNA1()) %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= Util.formatDate(msgList.getE01TXCPDD(),msgList.getE01TXCPDM(),msgList.getE01TXCPDY())+'-'+Util.formatTime(msgList.getE01TXCPDT())  %></td>
            <td NOWRAP  align=CENTER width=\"25%\"><%= Util.formatDate(msgList.getE01TXCLMD(),msgList.getE01TXCLMM(),msgList.getE01TXCLMY()) %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= Util.formatCell(msgList.getE01TXCAMT()) %></td>
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

<%@ page import ="datapro.eibs.master.Util" %>
<html> 
<head>
<title>Maestro de Agencias</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ETX016001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
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


function getParams(currrow,cde) {

	document.forms[0].CURRENTROW.value = currrow;
    document.forms[0].E01T42CDE.value = cde;
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">Timbres Fiscales por Recaudador<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="spectax_list.jsp, ETX0160"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSETX0160" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="E01T42CDE" value="  " size=5 maxlength=4>
    
     
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( ETX016001Help.getNoResult() )   {
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
        <div align="center"><a href="javascript:goAction(2)"><b>Registrar N&uacute;mero<BR>Timbre Fiscal</b></a></div>
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
            <th align=CENTER nowrap width="10%">Codigo</th>
            <th align=CENTER nowrap width="25%"> 
              <div align="center">Descripcion</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center">Numero Timbre Fiscal</div>
            </th>
            <th align=CENTER nowrap width="25%"> 
              <div align="center">Monto a Reportar </div>
            </th>
            <th align=CENTER nowrap width="10%"> 
              <div align="center"> Cantidad de Etiquetas</div>
            </th>
            
           </tr>
          <%
                ETX016001Help.initRow();
                chk = "checked";
                while (ETX016001Help.getNextRow()) {
                 
                  datapro.eibs.beans.ETX016001Message msgList = (datapro.eibs.beans.ETX016001Message) ETX016001Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              <input type="radio" name="CURRCODE" value="<%= ETX016001Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value,<%= msgList.getE01T42CDE() %>)">
            </td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= Util.formatCell(msgList.getE01T42CDE()) %></td>
            <td NOWRAP  align=CENTER width=\"25%\"><%= Util.formatCell(msgList.getE01T42NME()) %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= Util.formatCell(msgList.getE01T42NUM()) %></td>
            <td NOWRAP  align=CENTER width=\"25%\"><%= Util.formatCell(msgList.getE01T42AMT())  %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= Util.formatCell(msgList.getE01T42QTY()) %></td>
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

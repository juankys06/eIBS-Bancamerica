<%@ page import ="datapro.eibs.master.Util" %>
<html> 
<head>
<title>Lista Codigos de Formatos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EDP075001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">

function goAction(op) {	
	var msg1 = "Esta seguro que desea ";
	var msg2 = "el registro seleccionado";
	document.forms[0].opt.value = op;
	
	switch (op) { 
	case  1:  
	 break;	
	case  2:  //ok = confirm(msg1 + " Actualizar " + msg2);
	          //    document.forms[0].SCREEN.value="600";
	 break;   
	case  3: ok = confirm(msg1 + " Eliminar " + msg2);
	             document.forms[0].SCREEN.value="700";
	 break;
	};
	document.forms[0].submit();		  	
}


function getParams(currrow,cacti,dacti) {

	document.forms[0].CURRENTROW.value = currrow;
    //document.forms[0].cacti.value = cacti;
    //document.forms[0].dacti.value = dacti;
}


</SCRIPT>  

</head>

<BODY>
<h3 align="center">TRABAJAR SEGUIMIENTO PROPUESTA</h3>
<P align="center">
	<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="trace_list2.jsp, EDP0750">
</P>
<HR size="4" width="100%" align="right">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0750" >
  <p> 
    <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
    <input type=HIDDEN name="opt">
  </p>
  <p> 
    <%
	if ( EDP075001Help.getNoResult() ) {
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
              <td width="30%"> 
             
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
        <div align="center"><a href="javascript:goAction(5)"><b>Consulta</b></a></div>
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
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="10%"> <div align="center">Propuesta</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="center">Monto Rec.</div> </th>
            <th align=CENTER nowrap width="10%"> <div align="left">Oficial</div> </th>
            <th align=CENTER nowrap width="45%"> <div align="left">Estado</div> </th>
            <th align=CENTER nowrap width="15%"> <div align="center">Secuencia</div> </th>
            <th align=CENTER nowrap width="45%"> <div align="left">Actividad</div> </th>
            <th align=CENTER nowrap width="05%"> <div align="center">Fecha Inic/Fin</div> </th>
            <th align=CENTER nowrap width="05%"> <div align="center">Duración</div> </th>
            <th align=CENTER nowrap width="05%"> <div align="left">OFC EJE</div> </th> 
           </tr>
          <%
                EDP075001Help.initRow();
                chk = "checked";
                while (EDP075001Help.getNextRow()) {
                 
                  datapro.eibs.beans.EDP075001Message msgList = (datapro.eibs.beans.EDP075001Message) EDP075001Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%">              
                <input type="radio" name="CURRCODE2" value="<%= EDP075001Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPSNPR() %></td>
            <td NOWRAP  align=CENTER width=\"10%\"><%= msgList.getE01DPSAMN() %></td> 
            <td NOWRAP  align=LEFT width=\"10%\"><%= msgList.getE01XXXEJE() %></td>
            <td NOWRAP  align=LEFT width=\"10%\"><%= msgList.getE01XXXEST() %></td>
            <td NOWRAP  align=CENTER width=\"15%\"><%= msgList.getE01DPSSEC() %></td>
            <td NOWRAP  align=LEFT width=\"45%\"><%= msgList.getE01DPSACD() %></td>	
            <td NOWRAP  align=LEFT width=\"01%\"  size="30" maxlength="30"><%= msgList.getE01DPSFIN().substring(0,16) %>-<%= msgList.getE01DPSFFI().substring(0,16) %></td>
	       	<td NOWRAP  align=LEFT width=\"10%\"><%= msgList.getE01DPSIDN() %></td>
	       	<td NOWRAP  align=LEFT width=\"10%\"><%= msgList.getE01DPSEJE() %></td>
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

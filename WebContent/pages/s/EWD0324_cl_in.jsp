<html>
<head>
<title>Customer Limits</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "limPos" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBSTreasury.jsp"> </SCRIPT>
<script src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">
  function goAction(op) {

        if(op==1){
		 document.forms[0].opt.value = 1;
        }  
		else if(op==2){
		 document.forms[0].opt.value = 2;
        }  
		if(op==3){
		 document.forms[0].opt.value = 3;
        }  
		document.forms[0].submit();

  }

</SCRIPT>  

<script language="JavaScript">
  function getValor(Typ,Tenor) {

	document.forms[0].typ.value = Typ;
	document.forms[0].tenor.value = Tenor;
    
  }

</SCRIPT>  

<script language="JavaScript">
</script>
</head>

<BODY>
<h3 align="center">Líneas de Crédito - Incidencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="cl_in.jsp, EWD0324"></h3>
<hr size="4">
  <form>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
    
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="2">
  <INPUT TYPE=HIDDEN NAME="opt">
  <input type=HIDDEN name="typ">
  <input type=HIDDEN name="tenor">
  <%
	if ( limPos.getNoResult() ) {
 %> 
  <TABLE class="tbenter" width=100% height=100%>
   		<TR>
      <TD> 
        
      <div align="center"> 
          <p><font size="3"><b> No hay resultados para su
		criterio de busqueda, por favor seleccione
            una de las siguientes opciones</b></font></p>
          <table class="tbenter" width="100%">
            <tr> 
               <TD class=TDBKG> 
			        <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
			   </TD>
      <TD class=TDBKG> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </TD>
            </tr>
          </table>
          <p>&nbsp; </p>
        </div>
      </TD></TR>
   		</TABLE>
  <%   		
	}
	else {
%> 
  <p>
  <table class="tbenter" width="100%">
    <tr> 
       	<TD class=TDBKG> 
		       <div align="center"><a href="javascript:goAction(1)"><b>Nueva</b></a></div>
		</TD>
		<TD class=TDBKG> 
		       <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
		</TD>
		<TD class=TDBKG> 
		       <div align="center"><a href="javascript:goAction(3)"><b>Eliminar</b></a></div>
		</TD>
      	<TD class=TDBKG> 
        	<div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      	</TD>
    </tr>
  </table>
  <center>
    <table class="tableinfo">
      <tr id="trclear"> 
        <th align=CENTER nowrap >&nbsp;</th>
        <th align=CENTER nowrap > 
          <div align="center">Tipo</div>
        </th>
        <th align=CENTER nowrap > 
          <div align="center">Descripción
            </div>
        </th>
        <th align=CENTER nowrap > 
          <div align="center">Tenor <br> desde</div>
        </th>
		<th align=CENTER nowrap > 
          <div align="center">Tenor <br> hasta</div>
        </th>
        <th align=CENTER nowrap > 
          <div align="center">Porciento 
            </div>
        </th>
      </tr>
      <tr> <%
                limPos.initRow();
                while (limPos.getNextRow()) {
                 
                    		out.println(limPos.getRecord());
                    
                }
              %> </tr>
    </table>
  </center>
  <%
     }   
  %> 
 
</form>
</body>
</html>

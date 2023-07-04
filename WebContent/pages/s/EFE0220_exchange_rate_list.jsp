<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Mantenimiento de Tasas de Cambio</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EFE022001Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">



function goAction(op) {

	document.forms[0].opt.value = op;
	document.forms[0].submit();
  
}
</SCRIPT>  

</head>

<BODY>
<h3 align="center">Mantenimiento de Tasas de Cambio<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="exchange_rate_list, EFE0220"></h3>
<hr size="4">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSEFE0220" >
  <p> 
    <input type=HIDDEN name="SCREEN" value="800">
    <input type=HIDDEN name="totalRow" value="0">
	
   
    <input type=HIDDEN name="opt" value="1">
  </p>
  
  <p> 
    <%
	if ( EFE022001Help.getNoResult() ) {
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
                <div align="center" ><a href="javascript:goAction(1)"><b>Nuevo</b></a></div>
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
        <div align="center" ><a href="javascript:goAction(1)"><b>Nuevo</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="javascript:goAction(2)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
   
  <br>
  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER style="width:'95%'">
 			   <TR> 
    			     
      <TD NOWRAP width="100%" height="73" > 
        <TABLE id="headTable" >
  				   <TR id="trdark">  
            		<th align=CENTER nowrap width="5%">&nbsp;</th>
            		
            <th align=CENTER nowrap width="10%">Moneda</th>
            		
            <th align=CENTER nowrap width="10%"> Ultimo <br>Cambio</th>
			<th align=CENTER nowrap width="15%"> Tasa <br>Oficial</th> 	  
		   <th align=CENTER nowrap width="15%"> Tasa <br>Compra</th>
		   <th align=CENTER nowrap width="15%"> Tasa <br>Venta</th>
		   <th align=CENTER nowrap width="15%"> Tasa <br> Eqv. USD</th>
		   <th align=CENTER nowrap width="15%"> Tasa <br> x IVA</th>
		    </TR>
       			</TABLE>
  
   			    <div id="dataDiv1" class="scbarcolor">
    				<table id="dataTable" > 
          <%
				boolean firstTime = true;
				String chk = "";
                EFE022001Help.initRow();
                while (EFE022001Help.getNextRow()) {
				if (firstTime) {
					firstTime = false;
					chk = "checked";
				} else {
					chk = "";
				}                 
                datapro.eibs.beans.EFE022001Message msgList = (datapro.eibs.beans.EFE022001Message) EFE022001Help.getRecord();
		 %>
		 	<tr> 
            <td NOWRAP  align=CENTER width="5%"> 
              <input type="radio" name="CURRCODE" value="<%= EFE022001Help.getCurrentRow() %>" <%=chk%>  >
            </td>
            <td NOWRAP  align=CENTER width="5%"><%= msgList.getE01RATCCY() %></td>
            <td NOWRAP  align=LEFT width="10%"><%= Util.formatDate(msgList.getE01RATPU1(),msgList.getE01RATPU2(),msgList.getE01RATPU3()) %></td>
			<td NOWRAP  align=LEFT width="10%"><%= msgList.getE01RATEXR() %></td>
			<td NOWRAP  align=LEFT width="15%"><%= msgList.getE01RATCPR() %></td>
			<td NOWRAP  align=LEFT width="15%"><%= msgList.getE01RATCSR() %></td>
			<td NOWRAP  align=LEFT width="15%"><%= msgList.getE01RATUSD() %></td>
			<td NOWRAP  align=LEFT width="15%"><%= msgList.getE01RATRF2() %></td>
          </tr>
          <%
                }
              %>
			 </table>
   			</div>
   			</TD>
   		      </TR>	
		    </TABLE>

	  
    				 
  <SCRIPT language="JavaScript">
     showChecked("CURRCODE");
  </SCRIPT>
  <SCRIPT language="JavaScript">
			
			function resizeDoc() {
      		 	divResize();
     		    adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      		}
	 		resizeDoc();   			
     		window.onresize=resizeDoc;        
     </SCRIPT>
  
     


<%}%>


  </form>

</body>
</html>

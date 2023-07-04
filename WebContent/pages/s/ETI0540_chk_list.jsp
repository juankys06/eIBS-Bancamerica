<html> 
<head>
<title>Lista de Cheques Preautorizados</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<script language="JavaScript">



function goAction(op) {
	document.forms[0].SCREEN.value = op;
	document.forms[0].submit();
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
<h3 align="center">Lista de Cheques Preautorizados<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="loan_parties_list, EDL0170"></h3>
<hr size="4">
<P><BR>

	<table class="tableinfo" width=100% align=center>
      <tr id="trdark">       
        <td align=CENTER width="18%">
		<div align="left">Banco :</div>
		</td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
            <input type="text" name="E01CKCBNK" size="3" maxlength="2" value="<%=userPO.getBank()%>" readonly>
          </div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td align=CENTER width="18%"> 
          <div align="left">Cuenta :</div>
        </td>
        <td align=CENTER width="34%"> 
          <div align="left"> 
            <input type="text" name="E01CKCACC" size="13" maxlength="12" value="<%=userPO.getAccNum()%>" readonly>
            
          <%
          appList.initRow(); 
          String name=  "" ;
          if (!appList.getNoResult()){
          appList.getNextRow();
          datapro.eibs.beans.ETI054001Message record = (datapro.eibs.beans.ETI054001Message) appList.getRecord();
          name=  record.getE01CKCNME();
          }
          
            %>  
           <input type="text" name="E01CKCNME" size="51" value="<%=name%>" readonly>
          </div>
        </td>
      </tr>
	</table>

<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.teller.JSETI0540" >
  <input type=HIDDEN name="SCREEN" value="100">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="CURRENTROW" value="">
  <input type=HIDDEN name="E01CKCNME" value="<%=name%>">
  
    
  
  <%
	if ( appList.getNoResult() ) {
 %>
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>There is no match for your search criteria</b></p>
         <table class="tbenter" width=100% align=center>
           <tr>
           <td class=TDBKG>
                <div align="center" style="cursor:hand" onClick="javascript:goAction(200)"><a><b>Nuevo</b></a></div>
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
   	  <td class=TDBKG width="33%">
        <div align="center" style="cursor:hand" onClick="javascript:goAction(200)"><a><b>Nuevo</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
        <div align="center"><a href="javascript:goAction(300)"><b>Mantenimiento</b></a></div>
      </td>
      <td class=TDBKG width="33%"> 
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
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="10%">Num. Cheque</th>
            <th align=CENTER nowrap width="10%">Monto</th>
            <th align=CENTER nowrap width="5%">Moneda</th>
            <th align=CENTER nowrap width="20%">Beneficiario</th>
            <th align=CENTER nowrap width="10%">Fecha Ingr.</th>
            <th align=CENTER nowrap width="10%">Estado</th>
            <th align=CENTER nowrap width="10%">F. Pagado</th>
			</tr>
     	<%
        appList.initRow(); 
		boolean firstTime = true;
		String chk = "";
        while (appList.getNextRow()) {
			if (firstTime) {
				firstTime = false;
				chk = "checked";
			} else {
				chk = "";
			}
           datapro.eibs.beans.ETI054001Message msgPart = (datapro.eibs.beans.ETI054001Message) appList.getRecord();
     	%>               
        <TR>
			<TD NOWRAP width="2%"><input type="radio" name="ROW" value="<%= appList.getCurrentRow()%>" <%=chk%> 
				onClick="document.forms[0].CURRENTROW.value = this.value;"></TD>
				
			<TD NOWRAP ALIGN="CENTER"><%=msgPart.getE01CKCCKN()%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=msgPart.getE01CKCAMT()%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=msgPart.getE01CKCCCY()%></TD>
			<TD nowrap align="LEFT"><%=msgPart.getE01CKCBNF()%></TD>
			<TD NOWRAP ALIGN="CENTER"><%=msgPart.getE01CKCRDD() + '/' + msgPart.getE01CKCRDM() + '/' + msgPart.getE01CKCRDY()%></TD>
			<TD NOWRAP ALIGN="LEFT"><%=msgPart.getD01CKCSTS()%></TD>
			<TD nowrap align="CENTER"><%=msgPart.getE01CKCPAD() + '/' + msgPart.getE01CKCPAM() + '/' + msgPart.getE01CKCPAY()%></TD>
			</TR>    		
    	<%}%> 
    	   
        </table>
    </table>
     
  <%}%>


</form>

</body>
</html>

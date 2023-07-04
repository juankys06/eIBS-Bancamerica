<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Proyección Estados Financieros
</TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="Edp075501" class="datapro.eibs.beans.EDP075501Message" scope="session" />
<jsp:useBean id= "optList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "grpList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "accList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<% 
    if ( !error.getERRNUM().equals("0")  ) {
        out.println("<SCRIPT Language=\"Javascript\">");
        error.setERRNUM("0");
        out.println("       showErrors()");
        out.println("</SCRIPT>");
    }
    
%>
<script language="javascript">

function goUpdVal(){
	document.forms[0].SCREEN.value=500;
	document.forms[0].submit();	 
}
</script>

</HEAD>

<BODY>

<h3 align="center">Proyección Estados Financieros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDP0755_client_financial_proj_main.jsp,EDP0755"></h3>
<hr size="4">
<P align="center"><INPUT type="text" name="TITULO" size="45" readonly
	value="<%=userPO.getHeader20()%>"></P>
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0755">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500"> 

<table class="tableinfo">    
 <TR > 
  <TD NOWRAP >
   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMCUN" size="12" readonly value="<%=userPO.getHeader1()%>">
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="NAMECUM" size="45" readonly value="<%=userPO.getHeader2()%>">
      </td>         
    </tr> 
  </table> 
 </td>        
 </tr>  
</table>

  <table class="tbenter" width=100% align=center>
    <tr> 
      <td class=TDBKG width="30%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  <br>
  
<%
   optList.initRow();
   int k=0;
   int h=0;
   while (optList.getNextRow()) {
      if (optList.getFlag().equals("")) {
%>
	  <H4><%=optList.getRecord(0)%></H4>
      <TABLE  class="tableinfo" BORDER=1>
          <tr id="trdark"> 
	 		<input type=HIDDEN name="E01IFMFEY" value="<%= Edp075501.getE01IFMFEY().trim()%>">
	 		<input type=HIDDEN name="E01IFMFEM" value="<%= Edp075501.getE01IFMFEM().trim()%>">
	 		<input type=HIDDEN name="E01IFMFED" value="<%= Edp075501.getE01IFMFED().trim()%>">
	 		<input type=HIDDEN name="E01DPPFY1" value="<%= Edp075501.getE01DPPFY1().trim()%>">
	 		<input type=HIDDEN name="E01DPPFY2" value="<%= Edp075501.getE01DPPFY2().trim()%>">
	 		<input type=HIDDEN name="E01DPPFY3" value="<%= Edp075501.getE01DPPFY3().trim()%>">
	 		<input type=HIDDEN name="E01DPPFM1" value="<%= Edp075501.getE01DPPFM1().trim()%>">
	 		<input type=HIDDEN name="E01DPPFM2" value="<%= Edp075501.getE01DPPFM2().trim()%>">
	 		<input type=HIDDEN name="E01DPPFM3" value="<%= Edp075501.getE01DPPFM3().trim()%>">
	 		<input type=HIDDEN name="E01IFMCFO" value="<%= Edp075501.getE01IFMCFO().trim()%>">
            <th align=CENTER nowrap width="20%"> 
            	<div align="center">Descripción
            	</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
            	<div align="center">Periodo<BR>
	            	<%=Edp075501.getE01IFMFY1().trim()%>/
   		         	<%=Edp075501.getE01IFMFM1().trim()%>
            	</div>
            </th>
            <th align=CENTER nowrap width="10%"> 
            	<div align="center">Periodo<BR>
	            	<%=Edp075501.getE01IFMFY2().trim()%>/
   		         	<%=Edp075501.getE01IFMFM2().trim()%>
            	</div>
            	</th>
             <th align=CENTER nowrap width="10%"> 
            	<div align="center">Periodo<BR>
	            	<%=Edp075501.getE01IFMFEY().trim()%>/
   		         	<%=Edp075501.getE01IFMFEM().trim()%>
            </th>
             <th align=CENTER nowrap width="8%"> 
            	<div align="center">% Proyec.<BR>
	            	<%=Edp075501.getE01DPPFY1().trim()%>/
   		         	<%=Edp075501.getE01DPPFM1().trim()%>
            	</div>
            </th>
             <th align=CENTER nowrap width="10%"> 
            	<div align="center">Vlr. Proyec.<BR>
	            	<%=Edp075501.getE01DPPFY1().trim()%>/
   		         	<%=Edp075501.getE01DPPFM1().trim()%>
            	</div>
            </th>
             <th align=CENTER nowrap width="8%"> 
            	<div align="center">% Proyec.<BR>
	            	<%=Edp075501.getE01DPPFY2().trim()%>/
   		         	<%=Edp075501.getE01DPPFM2().trim()%>
            	</div>
            </th>
             <th align=CENTER nowrap width="10%"> 
            	<div align="center">Vlr. Proyec.<BR>
	            	<%=Edp075501.getE01DPPFY2().trim()%>/
   		         	<%=Edp075501.getE01DPPFM2().trim()%>
            	</div>
            </th>
             <th align=CENTER nowrap width="8%"> 
            	<div align="center">% Proyec.<BR>
	            	<%=Edp075501.getE01DPPFY3().trim()%>/
   		         	<%=Edp075501.getE01DPPFM3().trim()%>
            	</div>
            </th>
             <th align=CENTER nowrap width="10%"> 
            	<div align="center">Vlr. Proyec.<BR>
	            	<%=Edp075501.getE01DPPFY3().trim()%>/
   		         	<%=Edp075501.getE01DPPFM3().trim()%>
            	</div>
            </th>
           </tr>
      <% 
        grpList.initRow();
        int j = 0;
        int chgGrp =0;
       %> 
      	   <% 
      	       accList.initRow();
               while (accList.getNextRow()) {
           %>
               <TR id=trclear>       			
	            <input type="HIDDEN" name="DPLGLN<%=j%>" value="<%=accList.getRecord(0)%>">
	            <input type="HIDDEN" name="E01DPXLID<%=j%>" value="<%=accList.getRecord(7)%>">
				<% if (accList.getRecord(7).equals("Titulo")) { %>
      			<TH ALIGN=LEFT width="20%"  NOWRAP><%= accList.getRecord(1) %> </TH>
      			<% } else { %>
					<% if(accList.getRecord(7).equals("Detalle")){%> 
      			<TD ALIGN=LEFT width="20%"  NOWRAP><%= accList.getRecord(1) %> </TD>      
	      			<% } else { %>
      			<TH ALIGN=LEFT width="20%"  NOWRAP><%= accList.getRecord(1) %> </TH>
      			<% } %>
      			<% } %>
				<% if (!accList.getRecord(7).equals("Titulo")) { %>
					<% if(accList.getRecord(7).equals("Detalle")){%> 
      			<TD ALIGN=RIGHT width="10%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(8)) %></TD>
      			<TD ALIGN=RIGHT width="10%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(9)) %></TD>
      			<TD ALIGN=RIGHT width="10%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(2)) %></TD>
      			<TD ALIGN=RIGHT width="8%" NOWRAP>
					<INPUT type="text" name="E01DPPRJ1<%=j%>" size="7" maxlength="6" value="<%=accList.getRecord(10)%>" onKeypress="enterDecimal(2)" style="text-align:right"
					<% if(userPO.getOption().equals("4")){out.print("readonly");}%> >
      			</TD>
      			<TD ALIGN=RIGHT width="10%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(11)) %></TD>
      			<TD ALIGN=RIGHT width="8%" NOWRAP>
					<INPUT type="text" name="E01DPPRJ2<%=j%>" size="7" maxlength="6" value="<%=accList.getRecord(12)%>" onKeypress="enterDecimal(2)" style="text-align:right"
					<% if(userPO.getOption().equals("4")){out.print("readonly");}%> >
      			</TD>
      			<TD ALIGN=RIGHT width="10%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(13)) %></TD>
      			<TD ALIGN=RIGHT width="8%" NOWRAP>
					<INPUT type="text" name="E01DPPRJ3<%=j%>" size="7" maxlength="6" value="<%=accList.getRecord(14)%>" onKeypress="enterDecimal(2)" style="text-align:right"
					<% if(userPO.getOption().equals("4")){out.print("readonly");}%> >
      			</TD>
      			<TD ALIGN=RIGHT width="10%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(15)) %></TD>
	      			<% } else { %>
      			<TH ALIGN=RIGHT width="10%"  NOWRAP><%=datapro.eibs.master.Util.fcolorCCY(accList.getRecord(8))%></TH>
      			<TH ALIGN=RIGHT width="10%"  NOWRAP><%=datapro.eibs.master.Util.fcolorCCY(accList.getRecord(9))%></TH>
      			<TH ALIGN=RIGHT width="10%"  NOWRAP><%=datapro.eibs.master.Util.fcolorCCY(accList.getRecord(2))%></TH>
      			<TH ALIGN=RIGHT width="8%"  NOWRAP><%=datapro.eibs.master.Util.fcolorCCY(accList.getRecord(10))%></TH>
      			<TH ALIGN=RIGHT width="10%"  NOWRAP><%=datapro.eibs.master.Util.fcolorCCY(accList.getRecord(11))%></TH>
      			<TH ALIGN=RIGHT width="8%"  NOWRAP><%=datapro.eibs.master.Util.fcolorCCY(accList.getRecord(12))%></TH>
      			<TH ALIGN=RIGHT width="10%"  NOWRAP><%=datapro.eibs.master.Util.fcolorCCY(accList.getRecord(13))%></TH>
      			<TH ALIGN=RIGHT width="8%"  NOWRAP><%=datapro.eibs.master.Util.fcolorCCY(accList.getRecord(14))%></TH>
      			<TH ALIGN=RIGHT width="10%"  NOWRAP><%=datapro.eibs.master.Util.fcolorCCY(accList.getRecord(15))%></TH>

      			<% } %>
      			<% } %>

     		  </TR>
	
			<%j++; h++; 
        		} 
        	%>
      </TABLE> 
<%
       k++;
      }        
   }                 
%>
<input type="HIDDEN" name="RECNUM" value="<%=h%>">
<div align="center"> 
		<INPUT id="EIBSBTN0" type="submit" name="Submit0" value="Enviar" 
	<% if(userPO.getOption().equals("4")){out.print("disabled");}%>  >
</div>


</FORM>

</BODY>
</HTML>

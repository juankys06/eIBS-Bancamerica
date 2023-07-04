<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Variación Estados Financieros
</TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="Edp073901" class="datapro.eibs.beans.EDP073901Message" scope="session" />
<jsp:useBean id= "optList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "grpList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "accList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</HEAD>

<BODY>

<h3 align="center">Variación Absoluta y Variación Relativa Estados Financieros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EDP0739_client_financial_var_comp.jsp,EDP0739"></h3>
<hr size="4">
<P align="center"><INPUT type="text" name="TITULO" size="45" readonly
	value="<%=userPO.getHeader20()%>"></P>
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0739">

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
       
   while (optList.getNextRow()) {
      if (optList.getFlag().equals("")) {
%>
	  <H4><%=optList.getRecord(0)%></H4>
      <TABLE  class="tableinfo">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="25%"> 
            	<div align="center">Descripción
            	</div>
            </th>
            <th align=CENTER nowrap width="12%"> 
            	<div align="center">Periodo<BR>
				<INPUT type="text" name="E01IFMFY1" size="4" readonly
					value="<%=Edp073901.getE01IFMFY1().trim()%>">/
            	<INPUT type="text" name="E01IFMFM1" size="2" readonly value="<%=Edp073901.getE01IFMFM1().trim()%>">
            	<INPUT type="text" name="E01DPBCR1" size="1" readonly value="<%=Edp073901.getE01DPBCR1().trim()%>">
            	</div>
            </th>
            <th align=CENTER nowrap width="12%"> 
            	<div align="center">Periodo<BR>
				<INPUT type="text" name="E01IFMFY2" size="4" readonly
					value="<%=Edp073901.getE01IFMFY2().trim()%>">/
            	<INPUT type="text" name="E01IFMFM2" size="2" readonly value="<%=Edp073901.getE01IFMFM2().trim()%>">
            	<INPUT type="text" name="E01DPBCR2" size="1" readonly value="<%=Edp073901.getE01DPBCR2().trim()%>">
            	</div>
           	</th>
             <th align=CENTER nowrap width="10%"> 
            	<div align="center">%Variación<BR>Relativa
            	</div>
            </th>
             <th align=CENTER nowrap width="10%"> 
            	<div align="center">%Particip.<BR>Integral
            	</div>
            </th>
             <th align=CENTER nowrap width="12%"> 
            	<div align="center">Periodo<BR>
            	<INPUT type="text" name="E01IFMFEY" size="4" readonly
					value="<%=Edp073901.getE01IFMFEY().trim()%>">/
            	<INPUT type="text" name="E01IFMFEM" size="2" readonly value="<%=Edp073901.getE01IFMFEM().trim()%>">
            	<INPUT type="text" name="E01DPBCR3" size="1" readonly value="<%=Edp073901.getE01DPBCR3().trim()%>"></div>
            </th>
             <th align=CENTER nowrap width="10%"> 
            	<div align="center">%Variación<BR>Relativa
            	</div>
            </th>
             <th align=CENTER nowrap width="10%"> 
            	<div align="center">%Particip.<BR>Integral
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
				<% if (accList.getRecord(7).equals("Titulo")) { %>
      			<TH ALIGN=LEFT width="25%"  NOWRAP><%= accList.getRecord(1) %> </TH>
      			<% } else { %>
      			<TD ALIGN=LEFT width="25%"  NOWRAP><%= accList.getRecord(1) %> </TD>      
      			<% } %>
				<% if (!accList.getRecord(7).equals("Titulo")) { %>
      			<TD ALIGN=RIGHT width="12%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(8)) %></TD>
      			<TD ALIGN=RIGHT width="12%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(9)) %></TD>
      			<TD ALIGN=RIGHT width="10%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(14)) %></TD>
      			<TD ALIGN=RIGHT width="10%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(15)) %></TD>
      			<TD ALIGN=RIGHT width="12%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(2)) %></TD>
      			<TD ALIGN=RIGHT width="10%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(11)) %></TD>
      			<TD ALIGN=RIGHT width="10%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(12)) %></TD>
      			<% } %>
     		  </TR>
           <% 	
        		} 
        	%>
           
       <% 
        %>
      </TABLE> 
<%
       k++;
      }        
   }                 
%>


</FORM>

</BODY>
</HTML>

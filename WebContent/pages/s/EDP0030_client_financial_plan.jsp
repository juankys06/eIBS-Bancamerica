<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Información Financiera
</TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "optList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "grpList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "accList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</HEAD>

<BODY>

<h3 align="center">Plan Contable<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_financial_plan.jsp,EDP0030"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDP0030">

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
    <tr id="trdark"> 
      <td align="right"  >
         <b>Industria :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMCIN" size="15" readonly value="<%=userPO.getHeader4()%>">
      </td>
      <td align="right"  >
         <b>Negocio :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMLNE" size="15" readonly value="<%=userPO.getHeader5()%>">
      </td> 
      <td align="right"  >
         <b>Formato :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMCFO" size="2" readonly value="<%=userPO.getHeader3()%>">-
         <input type="text" name="E01IFMFEY" size="2" readonly value="<%=userPO.getHeader6()%>">/
         <input type="text" name="E01IFMFEM" size="2" readonly value="<%=userPO.getHeader7()%>">
      </td>        
    </tr>  
  </table> 
 </td>        
 </tr>  
</table>
  
<%
   optList.initRow();
   int k=0;
       
   while (optList.getNextRow()) {
      if (optList.getFlag().equals("")) {
%>                            
	  <H4><%=optList.getRecord(0)%></H4>
      <TABLE  class="tableinfo">
      <% 
        grpList.initRow();
        int j = 0;
        int chgGrp =0;
        while (grpList.getNextRow()) {
           if (grpList.getFlag().equals(""+k)) {
       %> 
        <TR id=trdark> 
         <TD ALIGN=LEFT colspan="3" NOWRAP><b><%= grpList.getRecord(0) %></b></TD>      
      	 <TD ALIGN=RIGHT NOWRAP><b><%= datapro.eibs.master.Util.fcolorCCY(grpList.getRecord(1)) %></b></TD>
      	</TR>
      	   <% 
      	       if (Integer.parseInt(grpList.getFlag()) != chgGrp) {
                  chgGrp = Integer.parseInt(grpList.getFlag());
                  j=0;
                }
      	       accList.initRow();
               while (accList.getNextRow()) {
                    if (accList.getFlag().equals(grpList.getFlag()+""+j)) {
           %>
               <TR id=trclear>       			
                <TD ALIGN=Left width="2%"><B>-</B>
                </TD>
      			<TD ALIGN=LEFT width="20%" NOWRAP><%= accList.getRecord(0)%></TD>
      			<TD ALIGN=LEFT width="58%" NOWRAP><%= accList.getRecord(1) %></TD>      
      			<TD ALIGN=RIGHT width="20%" NOWRAP><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(2)) %></TD>
     		  </TR>
           <% 	
          			}
        		} 
        	%>
           
       <% 
      		j++;
          }
        } 
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

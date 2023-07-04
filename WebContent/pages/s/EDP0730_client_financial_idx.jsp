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

<jsp:useBean id= "idxFnl" class= "datapro.eibs.beans.EDP073201Message"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT><SCRIPT language="JavaScript">

function ShowFormula() 
{
  
  	page= prefix +language + 'STATIC_creditproposal_financial_idx_for.jsp';
	CenterWindow(page,340,400,2);
  
}
//-->
</SCRIPT>

</HEAD>

<BODY>

<h3 align="center">Indices Financieros<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="creditproposal_financial_idx.jsp,EDP0732"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730">

<table class="tableinfo">    
 <TR > 
  <TD NOWRAP >
   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFDCUN" size="12" readonly value="<%=userPO.getHeader1()%>">
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
         <b>Formato Balance :</b>
      </td>
      <td nowrap >
        <INPUT type="text" name="E01IFDCFO" size="2" readonly value="<%=userPO.getHeader3()%>">
      </td>
      <td align="right"  >
         <b>Año / Mes :</b>
      </td>
      <td nowrap >
       <INPUT type="text" name="E01IFDFEY" size="4" readonly value="<%=userPO.getHeader6()%>">/
       <INPUT type="text" name="E01IFMFEM" size="2" readonly value="<%=userPO.getHeader7()%>">
      </td> 
          
    </tr>  
  </table> 
 </td>        
 </tr>  
</table>

<BR>     
 
<TABLE  class="tableinfo" style="width:90%" align=center>
   <TR>
    <TD>
            <TABLE id="headTable" width="100%" cellpading=0 cellspacing="0">
          <TR>  
             <TD id="trdark" width=50% ALIGN=LEFT NOWRAP></TD>
                        <TD ALIGN="center" NOWRAP><a href="javascript:ShowFormula()">Formulas</a></TD>
                    </TR>
    	  <TR>  
             <TD id="trdark" width=50% ALIGN=LEFT NOWRAP><b>Liquidez</b></TD>      
      	     <TD ALIGN=RIGHT NOWRAP><b><%= datapro.eibs.master.Util.fcolorCCY(idxFnl.getE01LIQUID()) %></b></TD>
      	  </TR>
      	  <TR>  
             <TD id="trdark" ALIGN=LEFT NOWRAP><b>Prueba Acida</b></TD>      
      	     <TD ALIGN=RIGHT NOWRAP><b><%= datapro.eibs.master.Util.fcolorCCY(idxFnl.getE01PRUACI()) %></b></TD>
      	  </TR>
      	  <TR>  
             <TD ALIGN=LEFT NOWRAP></TD>      
      	     <TD ALIGN=RIGHT NOWRAP></TD>
      	  </TR>
      	  <TR>  
             <TD id="trdark" ALIGN=LEFT NOWRAP><b>Periodo Medio Cobranza</b></TD>      
      	     <TD ALIGN=RIGHT NOWRAP><b><%= datapro.eibs.master.Util.fcolorCCY(idxFnl.getE01PEMECO()) %></b></TD>
      	  </TR>
      	  
      	  <TR>  
             <TD id="trdark" ALIGN=LEFT NOWRAP><b>Plazo Rotación Inventario</b></TD>      
      	     <TD ALIGN=RIGHT NOWRAP><b><%= datapro.eibs.master.Util.fcolorCCY(idxFnl.getE01ROTINV()) %></b></TD>
      	  </TR>
      	  <TR>  
             <TD id="trdark" ALIGN=LEFT NOWRAP><b>Periodo Medio de Pagos</b></TD>      
      	     <TD ALIGN=RIGHT NOWRAP><b><%= datapro.eibs.master.Util.fcolorCCY(idxFnl.getE01PEMEPA()) %></b></TD>
      	  </TR>
      	   <TR>  
             <TD ALIGN=LEFT NOWRAP></TD>      
      	     <TD ALIGN=RIGHT NOWRAP></TD>
      	  </TR>
      	  <TR>  
             <TD id="trdark" ALIGN=LEFT NOWRAP><b>Endeudamiento</b></TD>      
      	     <TD ALIGN=RIGHT NOWRAP><b><%= datapro.eibs.master.Util.fcolorCCY(idxFnl.getE01ENDEUD()) %></b></TD>
      	  </TR>
      	  <TR>  
             <TD id="trdark" ALIGN=LEFT NOWRAP><b>Endeudamiento Financiero</b></TD>      
      	     <TD ALIGN=RIGHT NOWRAP><b><%= datapro.eibs.master.Util.fcolorCCY(idxFnl.getE01ENDFIN()) %></b></TD>
      	  </TR>
      	   <TR>  
             <TD ALIGN=LEFT NOWRAP></TD>      
      	     <TD ALIGN=RIGHT NOWRAP></TD>
      	  </TR>
      	  <TR>       			
             <TD id="trdark" ALIGN=LEFT NOWRAP><b>Rendimiento de los Activos</b></TD>
      		 <TD ALIGN=RIGHT NOWRAP><b><%= idxFnl.getE01RENACT() %> %</b></TD>      
      	  </TR>
      	  <TR>       			
             <TD id="trdark" ALIGN=LEFT NOWRAP><b>Rendimiento Patrimonio</b></TD>
      		 <TD ALIGN=RIGHT NOWRAP><b><%= idxFnl.getE01RENPAT() %> %</b></TD>      
      	  </TR>
      	  <TR>       			
             <TD id="trdark" ALIGN=LEFT NOWRAP><b>Margen Neto Utilidad</b></TD>
      		 <TD ALIGN=RIGHT NOWRAP><b><%= idxFnl.getE01MANEUT() %> %</b></TD>      
      	  </TR>
      	  <TR>       			
             <TD id="trdark" ALIGN=LEFT NOWRAP><b>Margen Operacional</b></TD>
      		 <TD ALIGN=RIGHT NOWRAP><b><%= idxFnl.getE01MAROPE() %> %</b></TD>      
      	  </TR>
      	   <TR>  
             <TD ALIGN=LEFT NOWRAP></TD>      
      	     <TD ALIGN=RIGHT NOWRAP></TD>
      	  </TR>
      	  <TR>
      	     <TD id="trdark" ALIGN=LEFT NOWRAP><b>Riesgo/Patrimonio Liquido</b></TD>
      		 <TD ALIGN=RIGHT NOWRAP><b><%= idxFnl.getE01RIEPAT() %> %</b></TD>      
      	  </TR>
      	  <TR>       			
             <TD id="trdark" ALIGN=LEFT NOWRAP><b>Riesgo/Ventas</b></TD>
      		 <TD ALIGN=RIGHT NOWRAP><b><%= idxFnl.getE01RIEVTA() %> %</b></TD>      
      	  </TR>            
        </TABLE>
            </TD> 
	</TR>
</TABLE>

</FORM>
<P><BR>
</P>
</BODY>
</HTML>

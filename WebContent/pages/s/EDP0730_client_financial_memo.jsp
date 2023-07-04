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

<jsp:useBean id="mL0730" class="datapro.eibs.beans.EDP073001Message"  scope="session" />

<jsp:useBean id= "optList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "grpList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "accList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

</HEAD>

<BODY>

<script language="JavaScript">

function goConfirm() {

      ok = confirm("Esta seguro que desea crear/modificar este registro?");
      
	  if (ok) 
	       {
			document.forms[0].ROW.value = "<%=userPO.getIdentifier()%>";
		  document.forms[0].SCREEN.value="5";
	       document.forms[0].submit();
	       
	       }  
}

function goCancel(fmt) {

	document.forms[0].SCREEN.value="400";
	document.forms[0].submit(); 
//	  		           <input type="text" name="E01IFMCFA" size="4" readonly value="<%=userPO.getHeader8()%>">
//        <input type="text" name="E01CNORCD" size="4" readonly value="<%= mL0730.getE01CNORCD().trim()%>">
	  		  
}
</script>

<h3 align="center">Memo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_financial_memo.jsp,EDP0730"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
<INPUT TYPE=HIDDEN NAME="ROW" VALUE="">
<INPUT TYPE=HIDDEN NAME="E01IFMCFA" VALUE="<%=userPO.getHeader8()%>">
<INPUT TYPE=HIDDEN NAME="E01CNORCD" VALUE="<%=mL0730.getE01CNORCD().trim()%>">
<table class="tableinfo">    
 <TR > 
  <TD NOWRAP >
   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMCUN" size="12" readonly value="<%= userPO.getHeader1()%>">
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
         <input type="text" name="E01IFMCIN" size="15" readonly value="<%=userPO.getHeader18()%>">
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
         <input type="text" name="E01IFMFED" size="2" readonly value="<%=mL0730.getE01IFMFED().trim()%>">/
         <input type="text" name="E01IFMFEM" size="2" readonly value="<%=mL0730.getE01IFMFEM().trim()%>">/
         <input type="text" name="E01IFMFEY" size="4" readonly value="<%=mL0730.getE01IFMFEY().trim()%>">
         <input type="text" name="E01IFMCFA" size="4" readonly value="<%=userPO.getHeader8()%>">
      </td>        
    </tr>  

    <tr id="trdark"> 
      <td align="right"  >
         <b>Cuenta :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMGLN" size="12" readonly value="<%= mL0730.getE01IFMGLN().trim()%>">
      </td>
      <td align="right"  >
         <b>Descripción :</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="E01IFMDSC" size="45" readonly value="<%= mL0730.getE01IFMDSC().trim()%>">
      </td>         
    </tr> 

  </table> 
 </td>        
 </tr>  
</table>
<DIV align="center"><INPUT type="text" name="TITULO" size="45" readonly
	value="<%=userPO.getHeader20()%>"></DIV>
<TABLE  class="tableinfo"> 
		<TR id=trdark> 
			<TD ALIGN=LEFT colspan="3" NOWRAP width="51">Sec.<b></b>
			</TD>
			<TD ALIGN="left" NOWRAP width="600">
			<b></b>Descripción
			</TD>
			<TD ALIGN=LEFT colspan="3" NOWRAP width="50"><b></b> 
			</TD>
		</TR>
		
	<%
	// CREAR UN CAMPO NUMERICO A PARTIR DE UNA VARIABLE
	int iR= 0;
	iR = Integer.parseInt(mL0730.getE01DPLSEC());
    String name="";
	for ( int i=1; i<=iR; i++ ){
          if (i<10) name = "0" + i; else name= "" + i;
	%> 

		<TR id=trclear>

		<TD ALIGN=LEFT colspan="3" NOWRAP width="51"><b></b>
		<INPUT type="text" maxlength="3" size="3" name="E01DPMS<%= name %>" value="<%= mL0730.getField("E01DPMS"+name).getString().trim()%>">
		</TD>
		<TD ALIGN="left" NOWRAP width="600">
		<INPUT type="text" maxlength="100" size="100" name="E01DPMM<%= name %>" value="<%= mL0730.getField("E01DPMM"+name).getString().trim()%>" 
		<% if(userPO.getOption().equals("4")){out.print("readonly");}%> 		
		>
		<b></b>
		</TD>
		<TD ALIGN=LEFT colspan="3" NOWRAP width="50"><b></b>
			<SELECT name="E01DPMP<%= name %>" disabled>
				<option value=" " > </option>
				<option value="S"
				<%if (mL0730.getField("E01DPMP"+name).getString().trim().equals("S")) { out.print("selected"); }%>>Si</option>
				<option value="N"
				<%if (mL0730.getField("E01DPMP"+name).getString().trim().equals("N")) { out.print("selected"); }%>>No</option>
			</SELECT>
		</TD>
		       			
		</TR>
    <% }; %> 

		
		<TR id=trclear>       			
			<TD ALIGN=Left><B></B>
            </TD>
      		<TD ALIGN=LEFT NOWRAP></TD>
      		<TD ALIGN=LEFT NOWRAP></TD>
			<TD ALIGN=RIGHT NOWRAP width="64%"></TD>
		</TR>
		
	</TABLE>
  
	<div align="center"> 		
		<% if(!userPO.getOption().equals("4")){%> 		
       <input id="EIBSBTN" type="button" name="Submit" value="Enviar" onclick="goConfirm()"> 
	    <% }; %> 
       <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader9().trim() %>')">
	</div>

</FORM>

</BODY>
</HTML>

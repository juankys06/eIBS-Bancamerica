<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*,java.util.Iterator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Lista de Cuentas a Aprobar
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "jbList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="javascript">
function closeEnter(){
   	  enterACC.filters[0].apply();
      enterACC.style.visibility="hidden";
      enterACC.filters[0].Play();
}

function ShowEnterCod() {	 
	 var y=event.clientY + document.body.scrollTop;
     var x=event.clientX + document.body.scrollLeft;
	 cancelBub();
	 eval('enterACC.style.pixelTop=' + y);
     eval('enterACC.style.pixelLeft=' + x);
	 enterACC.filters[0].apply();
     enterACC.style.visibility="visible";
     enterACC.filters[0].Play();
	 
}

function goNew() {
	document.forms[0].SCREEN.value = "0";
	document.forms[0].submit();
}


function goDelete() {
	var ok = true;
	ok = confirm("Esta Usted seguro de querer borrar esta clausula?");
	if (ok) {
	    document.forms[0].SCREEN.value = "9";
	    document.forms[0].submit();
    }
}

function setInfo(bnk, stn, cun) {
    document.forms[0].STN.value = stn;
    document.forms[0].BNK.value = bnk;
    document.forms[0].CUN.value = cun;
}

function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;

 function SelectReg(fld1,fld2,fld3){
    document.forms[0].E01CLSCDE.value = fld1;
    document.forms[0].E01CLSTXN.value = fld2;
    document.forms[0].E01CLSDSC.value = fld3;
 } 

</SCRIPT>

</HEAD>

<BODY>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }/* else {
   if (userPO.getThereIsMsg()) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showReceipt('"+userPO.getOption()+"')");
     out.println("</SCRIPT>");     
   }
 }
*/ 

if (userPO.getPurpose().equals("VIEW"))
{%>
<SCRIPT language="javascript">
	window.open("<%=request.getContextPath() + userPO.getNextPage()%>");
</SCRIPT>
<%}%>

<FORM name="letterOfCreditForm" method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEWP0010">
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">

 <DIV id="enterACC" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onClick="cancelBub()"> 
    <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
      <TR id="trdark"> 
        <TD align=CENTER width="18%"> 
          <DIV align="right">Clausula :</DIV>
        </TD>
        <TD align=CENTER width="34%"> 
          <DIV align="left"> 
            <INPUT type="text" name="CLSCDE" size="4" maxlength="3">
          </DIV>
        </TD>
      </TR>
      <TR id="trclear"> 
        <TD align=CENTER width="18%"> 
          <DIV align="right">Texto NRW :</DIV>
        </TD>
        <TD align=CENTER width="34%"> 
          <DIV align="left">
            <INPUT type="text" name="CLSTXN" size="3" maxlength="2" onKeyPress="enterInteger()">
          </DIV>
        </TD>
      </TR>
      <TR id="trdark"> 
        <TD nowrap  > 
	              <DIV align="right">Descripcion : </DIV>
	            </TD>
	            <TD nowrap  > 
	              	<INPUT type="text" name="CLSDSC" size="47" maxlength="45">
	            </TD>
      </TR>
      
      <TR id="trclear"> 
        <TD align=center nowrap colspan="2"> 
          <INPUT id="EIBSBTN" type=button name="Submit" value="Aceptar" onClick="javascript:goNew()">
        </TD>
      </TR>
      
    </TABLE>
  </DIV>
	
	
	<H3 align="center">
	Clausulas 
		<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="EWP0010_lc_clauses_list.jsp">
	</H3>
	<HR size="4">
    
	<TABLE class="tbenter" width="100%">
		<TR> 
			<TD width="25%" class=TDBKG> 
  				<div align="center" style="cursor:hand" onClick="ShowEnterCod()"><A><span style="color:darkblue">Nueva</span></A></div>
		  </TD>
			<TD width="25%" class=TDBKG> 
				<DIV align="center"><A href="javascript:document.forms[0].submit();"><B>Mantenimiento</B></A></DIV>
		  </TD>
			<TD width="25%" class=TDBKG> 
				<DIV align="center"><A href="javascript:goDelete()"><B>Borrar</B></A></DIV>
		  </TD>
			<TD width="25%" class=TDBKG> 
				<DIV align="center"><A href="<%=request.getContextPath()%>/pages/background.jsp"><B>Salir</B></A></DIV>
		  </TD>  
	  </TR>
	</TABLE>
  
	<TABLE  id="mainTable" class="tableinfo" >
		<TR> 
			<TD NOWRAP valign="top">
				<TABLE id="headTable" width="100%">
					<TR id="trdark"> 
						<TH height="25" ALIGN=CENTER nowrap></TH>
						<TH ALIGN=CENTER nowrap width="20%">Clausula</TH>
						<TH ALIGN=CENTER nowrap width="20%">Texto</TH>						
						<TH ALIGN=CENTER nowrap width="50%">Descripcion</TH>						
					</TR>
						<% 
							String s1, s2, s3;
					        jbList.initRow(); 
							boolean firstTime = true;
							String chk = "";
					        while (jbList.getNextRow()) {
					        EWP001001Message msgPart = (EWP001001Message) jbList.getRecord();
								if (firstTime) {
									firstTime = false;
									chk = "checked";
									%>
					<INPUT TYPE=HIDDEN NAME="E01CLSCDE" value="<%=msgPart.getE01CLSCDE()%>">
					<INPUT TYPE=HIDDEN NAME="E01CLSTXN" VALUE="<%=msgPart.getE01CLSTXN()%>">
					<INPUT TYPE=HIDDEN NAME="E01CLSDSC" VALUE="<%=msgPart.getE01CLSDSC()%>">
									
									<%
								} else {
									chk = "";
								} 
					           
							   s1 = msgPart.getE01CLSCDE();
							   s2 = msgPart.getE01CLSTXN();
							   s3 = msgPart.getE01CLSDSC();
					    %>               
						        <TR>
									<TD NOWRAP >
									   <INPUT type="radio" name="ACCNUM_TEMP" value="<%= s1%>" onClick="SelectReg('<%=s1%>','<%=s2%>','<%=s3%>');" <%=chk%>>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:SelectReg('<%=s1%>','<%=s2%>','<%=s3%>');document.forms[0].submit();"><%=s1%></A></TD>
									<TD NOWRAP ALIGN="CENTER"><A href="javascript:SelectReg('<%=s1%>','<%=s2%>','<%=s3%>');document.forms[0].submit();"><%=s2%></A></TD>
									<TD NOWRAP ALIGN="LEFT"  ><A href="javascript:SelectReg('<%=s1%>','<%=s2%>','<%=s3%>');document.forms[0].submit();"><%=s3%></A></TD>
								</TR>
				    	<%}%>    
				</TABLE>		
			</TD>	
		</TR>	
	</TABLE>
</FORM>
</BODY>
</HTML>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<TITLE>Services Charges List</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<LINK Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "jbList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">

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
	document.forms[0].SCREEN.value = "102";
	document.forms[0].submit();
}
function goMaint() {
	document.forms[0].SCREEN.value = "103";
	document.forms[0].submit();
}
function goInquiry() {
	document.forms[0].SCREEN.value = "106";
	document.forms[0].submit();
}

function goDelete() {
	var ok = true;
	ok = confirm("Esta Usted seguro de querer borrarla?");
	if (ok) {
	    document.forms[0].SCREEN.value = "104";
	    document.forms[0].submit();
    }
}

function setInfo(bnk, stn, aty) {
    document.forms[0].STN.value = stn;
    document.forms[0].BNK.value = bnk;
    document.forms[0].ATY.value = aty;
}

function cancelBub(){
  event.cancelBubble = true;
}

document.onclick= closeEnter;

</SCRIPT>
</HEAD>

<BODY>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
	 error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>
<H3 align="center">Tabla de Comisiones y Gastos de Cartas de Créditos<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sc_list.jsp, ELC0600"></H3>

<HR size="4">

<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0600">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="101">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <INPUT TYPE=HIDDEN NAME="BNK" VALUE="">
  <INPUT TYPE=HIDDEN NAME="STN" VALUE="">
  <INPUT TYPE=HIDDEN NAME="ATY" VALUE="">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="E01RLCBNK" VALUE="<%=request.getParameter("E01RLCBNK")%>">
  <INPUT TYPE=HIDDEN NAME="E01RLCATY" VALUE="<%=request.getParameter("E01RLCATY")%>">  
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  <DIV id="enterACC" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onClick="cancelBub()"> 
    <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
      <TR id="trdark"> 
        <TD align=CENTER width="18%"> 
          <DIV align="right"><b>Banco :<b></DIV>
        </TD>
        <TD align=CENTER width="34%"> 
          <DIV align="left"> 
            <INPUT type="text" name="NEWBNK" size="3" maxlength="2" value="<%=request.getParameter("E01RLCBNK")%>">
          </DIV>
        </TD>
      </TR>
      <TR id="trclear"> 
        <TD align=CENTER width="18%"> 
          <DIV align="right"><b>Número de Tabla :<b></DIV>
        </TD>
        <TD align=CENTER width="34%"> 
          <DIV align="left"> 
            <INPUT type="text" name="NEWSTN" size="3" maxlength="2" onKeyPress="enterInteger()">
          </DIV>
        </TD>
      </TR>
      <TR id="trdark"> 
        <TD nowrap  > 
	              <DIV align="right"><B>Tipo de Producto :</B> </DIV>
	            </TD>
	            <TD nowrap  > 
		             	<INPUT type="text" name="NEWATY"  size=5 maxlength="8" value="<%=request.getParameter("E01RLCATY")%>">
        				<A href="javascript:GetProductRates('NEWATY','LC')"> 
        				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0" ></A> 
	            </TD>
      </TR>
      
      <TR id="trclear"> 
        <TD align=center nowrap colspan="2"> 
          <INPUT id="EIBSBTN" type=button name="Submit" value="Aceptar" onClick="javascript:goNew()">
        </TD>
      </TR>
      
    </TABLE>
  </DIV>
         
<%
	if ( jbList.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <DIV align="center"> <H4 style="text-align:center"><B>No hay resultados para su b&uacute;squeda</B>. <BR>Oprima Nueva para Crearla</H4> 
      </DIV>
      </TD></TR>
   	</TABLE>
   	<TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER class="TDBKG" width="50%">
   		<DIV align="center" style="cursor: hand" onClick="ShowEnterCod()"><A>Nueva</A>
			</DIV>
   		</TD>      
      <TD ALIGN=CENTER class="TDBKG" width="50%">
  		<DIV align="center"><A href="<%=request.getContextPath()%>/pages/background.jsp"><B>Salir</B></A></DIV>
      </TD>
    </TR>
  </TABLE>
<%
	}
	else {
%>
  
  <CENTER>
<TABLE class="tbenter" width="585">
	<TBODY>
		<TR>
			<TD align="CENTER" class="TDBKG" width="20%">
			<DIV align="center" style="cursor: hand" onClick="ShowEnterCod()"><A>Nueva</A>
			</DIV>
			</TD>
			<TD align="CENTER" class="TDBKG" width="20%"><A
				href="javascript:goMaint()">Mantenimiento</A></TD>
			<TD align="CENTER" class="TDBKG" width="20%"><A
				href="javascript:goInquiry()">Consulta</A></TD>
			<TD align="CENTER" class="TDBKG" width="20%"><A
				href="javascript:goDelete()">Borrar</A></TD>
			<TD align="CENTER" class="TDBKG" width="20%"><A
				href="<%=request.getContextPath()%>/pages/background.jsp">Salir</A></TD>
		</TR>
	</TBODY>
</TABLE>
</CENTER>
  <TABLE class="tableinfo" id="dataTable">
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap width="1%"></TH> 
      <TH ALIGN=CENTER  nowrap >Banco</TH>
      <TH ALIGN=CENTER  nowrap >Número de <BR>Tabla</TH>
      <TH ALIGN=CENTER  nowrap >Moneda de <BR>Tarifa</TH>
      <TH ALIGN=CENTER  nowrap >Moneda de<BR> Cuenta</TH>      
      <TH ALIGN=CENTER  nowrap >Descripción</TH>
    </TR>
    
    
    <%
          boolean firstTime = true;  
          String chk = "";  
          jbList.initRow(); 
          int k=1;              
          while (jbList.getNextRow()) {
               ELC060001Message msgList = (ELC060001Message) jbList.getRecord();		
               if (firstTime) {
	               	chk = "checked";
	               	firstTime = false;
               } else {
               		chk = "";
                }	 
       %>             
        <TR>
          <TD align="center" nowrap > 
        	<INPUT type="radio" name="ROW1" <%= chk %> value="<%= jbList.getCurrentRow() %>" onClick="setInfo('<%= msgList.getE01RLCBNK()%>', '<%= msgList.getE01RLCTAR() %>', '<%= msgList.getE01RLCATY() %>')" >
      	  </TD>
          <TD NOWRAP align=center>
          	<%=Util.formatCell(msgList.getE01RLCBNK())%>
		  </TD>		  
		  <TD NOWRAP align="center">
			<%=Util.formatCell(msgList.getE01RLCTAR())%>
		  </TD>		  
		  <TD NOWRAP align="center">
          	<%=Util.formatCell(msgList.getE01RLCTCY())%>
		  </TD>
		  <TD NOWRAP align="center">
          	<%=Util.formatCell(msgList.getE01RLCACY())%>
		  </TD>
		  <TD NOWRAP align="center">
          	<%=Util.formatCell(msgList.getE01RLCDSC())%>
		  </TD>
		</TR>
		
        <% 
        	k++;
         }
         %> 
  </TABLE>
  <SCRIPT language="JavaScript">     
  	document.forms[0].totalRow.value="<%= k %>";
	 showChecked("ROW1");     
  </SCRIPT> 
<%      
  }
%> 
</FORM>
</BODY>
</HTML>

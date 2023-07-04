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

function getCorrespDescId(name, desc, id){
	page= prefix + language + "EWD0001_corresp_desc_id_help_container.jsp";
	fieldName=name;
	fieldDesc=desc;
	fieldId=id;
	fieldAux1="";
	CenterWindow(page,530,530,1);
}



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
	document.forms[0].SCREEN.value = "302";
	document.forms[0].submit();
}
function goMaint() {
	document.forms[0].SCREEN.value = "303";
	document.forms[0].submit();
}

function goDelete() {
	var ok = true;
	ok = confirm("Esta Usted seguro de querer borrarla?");
	if (ok) {
	    document.forms[0].SCREEN.value = "304";
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

<H3 align="center">Tablas de Comisiones y Gastos de Cartas de Credito<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sc_corresp_list.jsp, ELC0600"></H3>
<HR size="4">

<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0600">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="301">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="">
  <INPUT TYPE=HIDDEN NAME="BNK" VALUE="">
  <INPUT TYPE=HIDDEN NAME="STN" VALUE="">
  <INPUT TYPE=HIDDEN NAME="CUN" VALUE="">
  <INPUT TYPE=HIDDEN NAME="E01RLCBNK" VALUE="<%=request.getParameter("E01RLCBNK")%>">
  <INPUT TYPE=HIDDEN NAME="E01RLCCUN" VALUE="<%=request.getParameter("E01RLCCUN")%>">  
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
  
  <DIV id="enterACC" style="position:absolute; visibility:hidden; left:25px; top:-50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)" onClick="cancelBub()"> 
    <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
      <TR id="trdark"> 
        <TD align=CENTER width="18%"> 
          <DIV align="right">Banco :</DIV>
        </TD>
        <TD align=CENTER width="34%"> 
          <DIV align="left"> 
            <INPUT type="text" name="NEWBNK" size="3" maxlength="2" onKeyPress="enterInteger()" value="<%=request.getParameter("E01RLCBNK")%>">
          </DIV>
        </TD>
      </TR>
      <TR id="trclear"> 
        <TD align=CENTER width="18%"> 
          <DIV align="right">Número de Tabla :</DIV>
        </TD>
        <TD align=CENTER width="34%"> 
          <DIV align="left"> 
            <INPUT type="text" name="NEWSTN" size="3" maxlength="2" onKeyPress="enterInteger()">
          </DIV>
        </TD>
      </TR>
      <TR id="trdark"> 
        <TD nowrap  > 
	              <DIV align="right"><B>Número de Cliente :</B> </DIV>
	            </TD>
	            <TD nowrap  > 
	              	<INPUT type="text" name="NEWCUN" size="19" maxlength="9" value="<%=request.getParameter("E01RLCCUN")%>">
            		<A href="javascript:getCorrespDescId('NEWCUN','','')">
            		<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"></A> 
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

   		<DIV align="center" style="cursor:hand" onClick="ShowEnterCod()"><A><B>Nueva</B></A></DIV>
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
  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER class="TDBKG" width="33%">
  		<div align="center" style="cursor:hand" onClick="ShowEnterCod()"><A>Nueva</A>
      </TD>
      <TD ALIGN=CENTER class="TDBKG" width="33%">
  		<A href="javascript:goMaint()">Mantenimiento</A>
      </TD>   
      <TD ALIGN=CENTER class="TDBKG" width="33%"><A href="javascript:goDelete()">Borrar</A>
      </TD>               
      <TD ALIGN=CENTER class="TDBKG" width="34%"><A href="<%=request.getContextPath()%>/pages/background.jsp">Salir</A>
      </TD>
    </TR>
  </TABLE>
  </CENTER>
  <TABLE class="tableinfo" id="dataTable">
    <TR id="trdark">
      <TH ALIGN=CENTER  nowrap width="1%"></TH> 
      <TH ALIGN=CENTER  nowrap >Banco</TH>
      <TH ALIGN=CENTER  nowrap >Número de<BR>Cliente</TH>
      <TH ALIGN=CENTER  nowrap >Número de<BR>Tabla
		</TH>
      <TH ALIGN=CENTER  nowrap >Moneda<BR>Tarifa
		</TH>
      <TH ALIGN=CENTER  nowrap >Moneda<BR>Contable</TH>      
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
        	<INPUT type="radio" name="ROW1" <%= chk %> value="<%= jbList.getCurrentRow() %>" onClick="setInfo('<%= msgList.getE01RLCBNK()%>', '<%= msgList.getE01RLCTAR() %>', '<%= msgList.getE01RLCCUN() %>')" >
      	  </TD>
          <TD NOWRAP align=center>
          	<%=Util.formatCell(msgList.getE01RLCBNK())%>
		  </TD>		  
          <TD NOWRAP align=center>
          	<%=Util.formatCell(msgList.getE01RLCCUN())%>
		  </TD>		  
		  <TD NOWRAP align=center>
			<%=Util.formatCell(msgList.getE01RLCTAR())%>
		  </TD>		  
		  <TD NOWRAP align=center>
          	<%=Util.formatCell(msgList.getE01RLCTCY())%>
		  </TD>
		  <TD NOWRAP  align=center>
          	<%=Util.formatCell(msgList.getE01RLCACY())%>
		  </TD>
		  <TD NOWRAP  align=center>
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

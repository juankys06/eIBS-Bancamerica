<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Reserved Word
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>
<jsp:useBean id= "ESS0035MttoWord" class= "java.lang.String"  scope="session"/>
<jsp:useBean id= "ESS0035Action"   class= "java.lang.String"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
<!--

 function goSubmit() {
    var txto = "";
    var rpl = "";
    if(document.forms[0].ACTION.value == 'A'){
       txto = "Esta seguro que desea AGREGAR esta palabra a lista?";
    } 
    if(document.forms[0].ACTION.value == 'D'){
       txto = "Esta seguro que desea ELIMINAR esta palabra de la lista?";
    } 
    if(document.forms[0].ACTION.value == 'U'){
      for (var i=0; i<document.forms[0].OPT.length; i++)  {
	    if (document.forms[0].OPT[i].checked)  {
	   	  rpl = document.forms[0].OPT[i].value
		}
	  }
	  txto = "Esta seguro que desea agregar este archivo a lista?";
	  if(rpl == 'R'){
   	    txto = "Esta seguro que desea REEMPLAZAR esta archivo en la lista?";
	  }              
    }     
    if(confirm(txto)){
       var STT = "<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS0035";  
       STT = STT + "?SCREEN=300";
       STT = STT + "&ACTION=" + document.forms[0].ACTION.value;
       STT = STT + "&RWORD=" + document.forms[0].RWORD.value;
       STT = STT + "&OPT=" + rpl;
       document.forms[0].action = STT;
       document.forms[0].submit();    
    }
  }
  
   function goCancel() {
    var STT = "<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS0035?SCREEN=100";  
    document.forms[0].action = STT;
    document.forms[0].submit();
  }
</script>

</HEAD>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY>

<FORM METHOD='POST'  enctype='multipart/form-data'>
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
<INPUT TYPE=HIDDEN NAME="ACTION" VALUE="<%= ESS0035Action %>">
  <h3 align="center">Palabras Reservadas para Claves de Internet<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="reserved_word_mtto,ESS0035"> 
  </h3>
<hr size="4">
 <div id="MTTO"> 
   <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark">  
            <TD>Palabra Reservada : </TD>
            <TD><INPUT TYPE="TEXT" NAME="RWORD" VALUE="<%= ESS0035MttoWord %>" <%= ESS0035MttoWord.trim().length()>0?"readonly":" " %>> </TD>
		  </TR>
 		</table>
 	  </td>
 	</tr>  	
  </table>
</div>
<div id="UPLOAD"> 
   <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark">  
            <TD>Accion a Realizar: </TD>
            <TD><INPUT TYPE="RADIO" NAME="OPT" VALUE="A" checked="checked">Agregar Lista de Palabras a Archivo
                <INPUT TYPE="RADIO" NAME="OPT" VALUE="R">Reemplazar Lista de Palabras a Archivo
             </TD>
		  </TR>
          <tr id="trdark">  
            <TD>Seleccione el Archivo a Cargar: </TD>
            <TD><INPUT TYPE="FILE" NAME="UFILE" > </TD>
		  </TR>		  
 		</table>
 	  </td>
 	</tr>  	
  </table>
</div>
 
 <br> 
 <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER width="50%" class=TDBKG> <a href="javascript:goSubmit()">Aceptar</a></TD>
      <TD ALIGN=CENTER width="50%" class=TDBKG> <a href="javascript:goCancel()">Cancelar</a></TD>
    </TR>
  </TABLE>
<SCRIPT LANGUAGE="JavaScript"> 
  if(document.forms[0].ACTION.value=='U'){
    MTTO.style.display="none";
    UPLOAD.style.display="";
  }else{
    MTTO.style.display="";
    UPLOAD.style.display="none";  
  }  
</script>  
</FORM>

</BODY>
</HTML>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "listaswift" class= "datapro.eibs.beans.JBList"   scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<TITLE>
Mensage Swift
</TITLE>

<script language="javascript">
</script>
</HEAD>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 String opt = ""; 
%>
<script language="javascript">
function Imprimir(){

	div1.style.display="none";
	window.print();
	div1.style.display="";
}
</script>

<BODY>
<h3 align="center">Mensage Swift 
</h3>
<hr size="4">
<FORM Method="post" name="form1" >
   <table cellspacing="0" cellpadding="2" width="100%" border="1">
    <tr > 
      <td NOWRAP valign="top" width="100%" > 
		   <table cellspacing="0" cellpadding="2" width="100%" border="0">
		    <tr > 
		      <td NOWRAP valign="top" width="100%" > 
            <%
                listaswift.initRow();
                int k=1;
                while (listaswift.getNextRow()) {
                    if (listaswift.getFlag().equals("")) {
                    		out.println(listaswift.getRecord());
                    k++;
                    }        
                }%> 
		      </td>
		    </tr>
		</table>
      </td>
    </tr>
</table>

<center>
 <div align="center" id ="div1">
   	<INPUT id="EIBSBTN" type="button" value="Imprimir"  onClick="Imprimir()">  
 </div>
</center>

<BR>

</FORM>

</BODY>
</HTML>

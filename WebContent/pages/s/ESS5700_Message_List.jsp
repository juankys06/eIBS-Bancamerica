<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Affiliation Request</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "ESS5701List" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>

<script language="javascript">
<!--

 function goSubmit(Op) {
 	document.forms[0].SCREEN.value = Op;
 	if(Op=='110' && document.forms[0].E01IMFCDE.value ==''){
 	   alert("Debe de Seleccionar Mensaje");
 	}
    document.forms[0].submit();
  }
  
  function Asigna(Msg, Tipo){
    document.forms[0].E01IMFLIF.value = Tipo;
    document.forms[0].E01IMFCDE.value = Msg;
  }
</script>

</HEAD>
<BODY>

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS5700">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="110">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="E01IMFLIF" VALUE="">
<INPUT TYPE=HIDDEN NAME="E01IMFCDE" VALUE="">
  <h3 align="center">Mensajes de Confirmación <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="Affiliation_List.jsp,ESS2060"> 
  </h3>
<hr size="4">

 <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER width="50%" class=TDBKG> <a href="javascript:goSubmit('120')">Nuevo</a> 
      <TD ALIGN=CENTER width="50%" class=TDBKG> <a href="javascript:goSubmit('110')">Mantenimiento</a> 
      </TD>
    </TR>
  </TABLE>

 <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%" colspan="1"> 
        <table id="headTable" width="100%">
          <tr id="trdark">  
		      <TH ALIGN=CENTER width=5%>Selección</TH>
		      <TH ALIGN=CENTER width=10%>Idioma</TH>		      
		      <TH ALIGN=CENTER width=10%>Código</TH>
		      <TH ALIGN=CENTER width=80%>Descripción</TH>
		  </TR>
       <% 
          String txtcls = "rowdark";
       	  int i = 0;
          ESS5701List.initRow();               
          while (ESS5701List.getNextRow()) {
             i++;
             datapro.eibs.beans.ESS570001Message objList = (datapro.eibs.beans.ESS570001Message) ESS5701List.getRecord();	
             txtcls = (i%2)==0?"trdark":"teclear";
       %>  
          <tr class="<%= txtcls %>">
             <td align="center">
                <input type="radio" name="XE01IMFCDE" value ="" onclick="JavaScript:Asigna('<%= objList.getE01IMFCDE() %>','<%= objList.getE01IMFLIF() %>')"></td>
             <td><%= objList.getE01IMFLIF() %></td>                  
             <td><%= objList.getE01IMFCDE() %></td>   
             <td><%= objList.getE01IMFLN1() + objList.getE01IMFLN2() + objList.getE01IMFLN3()  %></td>          
          </tr>
        <% } %>  
      </table>
  </table>
</FORM>

</BODY>
</HTML>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head> 
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title></title>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<jsp:useBean id= "ecd0009List" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />


<script language="Javascript1.1">
//<!-- Hide from old browsers

  //var Plastico="";

  function create() {

    document.getElementById("SCREEN").value="11";
	document.forms[0].submit();

  }
 
  function edit(){
  
    if (validate()){
      document.getElementById("SCREEN").value="51";
  //    document.getElementById("E01CDRTBL").value=Plastico;
	  document.forms[0].submit();
    }
 
  }

  function del(){
    document.getElementById("SCREEN").value="4";
    if (confirm("Esta seguro de eliminar este registro?"))
	  document.forms[0].submit();

  } 


  function query(codigoPlastico){
    var page = webapp + "/servlet/datapro.eibs.products.JSECD0009?SCREEN=2&E01CDRTBL="+codigoPlastico;
	CenterWindow(page,700,500,1);
  }


  function validate(){
    var radio_choice = false;
    // Loop from zero to the one minus the number of radio button selections
    for (counter = 0; counter < form1.E01CDRTBL.length; counter++)
    {
     // If a radio button has been selected it will return true
     // (If not it will return false)
      if (form1.E01CDRTBL[counter].checked)
       radio_choice = true; 
    }
    if (form1.E01CDRTBL.length == null) {
    	if (form1.E01CDRTBL.checked) radio_choice = true;
    }
    if (!radio_choice)
    {
     // If there were no selections made display an alert box 
      alert("Seleccione un plástico")
      return (false);
    }
    else
     return true;
  } 
  


//-->
</script>

</head>

<body>

<h3 align="center">Control de Plástico<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="Card_List.jsp, ECD0009">
</h3>
<hr size="4">

<form name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECD0009" >

  <input type="hidden" name="SCREEN" id="SCREEN" value="2" />
 

 <table class="tbenter" width="100%" align="center">
    <tr> 
      <td class="TDBKG" width="25%"> 
        <div align="center" style="cursor:hand" onClick="create()"><a><b>Nueva</b></a></div>
      </td>
      <td class="TDBKG" width="25%"> 
        <div align="center" ><a href="javascript:edit()"><b>Mantenimiento</b></a></div>
      </td>
      <td class="TDBKG" width="25%"> 
        <div align="center" style="cursor:hand" onClick="del()"><a><b>Borrar</b></a></div>
      </td>
      <td class="TDBKG" width="25%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
      
    </tr>
  </table>
 

<%
	if ( ecd0009List.getNoResult() ) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No se tienen plásticos registrados en el archivo de control</b></font> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
 
  <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER>
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap >Código Plástico</TH>
      <TH ALIGN=CENTER  nowrap >Descripción</TH>
      <TH ALIGN=CENTER  nowrap >Tipo de Producto</TH>
    </TR>
    <%
     ecd0009List.initRow();               
                while (ecd0009List.getNextRow()) {
                    ECD0009DSMessage msgHelp = (ECD0009DSMessage) ecd0009List.getRecord();			 
                    
       %>             
                    
        <TR>
           <td NOWRAP >
           <input type="radio" value="<%=msgHelp.getE01CDRTBL()%>" name="E01CDRTBL" >
          	<a href="javascript:query('<%=msgHelp.getE01CDRTBL()%>')">
										<%=Util.formatCell(msgHelp.getE01CDRTBL())%></a>
		  </td>
		  <td NOWRAP >
			<a href="javascript:query('<%=msgHelp.getE01CDRTBL()%>')">
										<%=Util.formatCell(msgHelp.getE01CDRDSC())%></a>
		  </td>
		
		  <td NOWRAP >
          	<a href="javascript:query('<%=msgHelp.getE01CDRTBL()%>')">
										<%="D".equals(msgHelp.getE01CDRDOC())?Util.formatCell("Débito"):Util.formatCell("Crédito")%></a>
		  </td>
		 
	   </TR>
        <% 
          }
        %> 
  </TABLE>
  <%--
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25">
       <%
        if ( ecd0009.getShowPrev() ) {
      			int pos =ewd0005Help.getFirstRec() - 21;
      			   out.print("<A HREF=\"" + request.getContextPath() + "/servlet/datapro.eibs.helps.JSEWD0005?NameSearch=" + NameSearch + "&FromRecord=" + pos +  "&shrBank=" + shrBank + "&shrAppCode=" + shrAppCode + "&shrSelect=" + shrSelect + "&shrClient=" + shrClient + "&shrAcc=" + shrAcc + "&shrType=" + shrType + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
       %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25">
 	   <%       
        if ( ecd0009.getShowNext() ) {
      			int pos = ewd0005Help.getLastRec();
      			out.print("<A HREF=\"" + request.getContextPath()+ "/servlet/datapro.eibs.helps.JSEWD0005?NameSearch=" + NameSearch + "&FromRecord=" + pos +  "&shrBank=" + shrBank + "&shrAppCode=" + shrAppCode + "&shrSelect=" + shrSelect + "&shrClient=" + shrClient + "&shrAcc=" + shrAcc + "&shrType=" + shrType + "\" ><img src=\""+ request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

        }
        %>
       </TD>
	 </TR>
    </TABLE>
  --%>	 
<%      
  }
%> 
</FORM>

</BODY>
</HTML>
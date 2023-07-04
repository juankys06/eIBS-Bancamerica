<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Asignación de Puntos por Módulo</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "EDP070801Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<script language="JavaScript">
function goAction(op) {	
	var msg1 = "Esta seguro que desea ";
	var msg2 = "el registro seleccionado";
	document.forms[0].opt.value = op;
	switch (op) { 
	case  3:
       document.forms[0].SCREEN.value="200";
	 break;   
	};
	document.forms[0].submit();		  	       	       
}

function getParams(currrow,cacti,dacti) {

	document.forms[0].CURRENTROW.value = currrow;
    //document.forms[0].cacti.value = cacti;
    //document.forms[0].dacti.value = dacti;
}


</SCRIPT>  
</head>
<BODY>
<h3 align="center">Asignación de Puntos por Módulo</h3>
<P align="center">
	<IMG src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="modules_enter.jsp, EDP0840">
</P>
<HR size="4" width="100%" align="right">
<FORM name="form1" METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0840" >
  <p> 
   <INPUT TYPE=HIDDEN NAME="CURRENTROW" VALUE="0"> 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="">
    <input type=HIDDEN name="opt">
  </p>
  <p> 
<% 
String chk = "";

 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 
  <p> 
  
  <table class="tbenter" width=100% align=center>
    <tr> 
    <td class=TDBKG width="20%">
        <div align="center"><a href="javascript:goAction(3)"><b>Enviar</b></a></div> 
    </td> 
      <td class=TDBKG width="20%"> 
        <div align="center"><a href="<%=request.getContextPath()%>/pages/background.jsp"><b>Salir</b></a></div>
      </td>
    </tr>
  </table>
  
  
  <table  id=cfTable class="tableinfo">
    <tr > 
      <td NOWRAP valign="top" width="100%"> 
        <table id="headTable" width="100%">
          <tr id="trdark"> 
            <th align=CENTER nowrap width="10%">&nbsp;</th>
            <th align=CENTER nowrap width="10%"> <div align="center">Código</div> </th>
            <th align=CENTER nowrap width="30%">Descripción</th>
            <th align=CENTER nowrap width="10%"> <div align="center">Porcentaje
              <input type=TEXT name="T01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </div> </th>
           </tr>
          <%
                EDP070801Help.initRow();
                chk = "checked";
                while (EDP070801Help.getNextRow()) {
                 
                  datapro.eibs.beans.EDP070801Message msgList = (datapro.eibs.beans.EDP070801Message) EDP070801Help.getRecord();
		 %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
              
                <input type="radio" name="CURRCODE2" value="<%= EDP070801Help.getCurrentRow() %>" <%=chk%> onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width=\"5%\"><%= msgList.getE01DPPSEC() %></td>
            <td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01DPPACD() %></td>
            <td NOWRAP  align=CENTER width=\"25%\"><%= msgList.getE01DPPTAD() %></td>
          </tr>
          <%
              				chk = "";     
                }
              %>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
          <tr> 
            <td NOWRAP  align=CENTER width="10%"> 
                <input type="radio" name="CURRCODE2" onClick="getParams(this.value);">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODCOD" size=5 maxlength=4 value="">
            </td>
            <td NOWRAP  align=CENTER width="30%"> 
                <input type=TEXT name="E01MODDSC" size=50 maxlength=50 value="">
            </td>
            <td NOWRAP  align=CENTER width="10%"> 
                <input type=TEXT name="E01MODPER" size=10 maxlength=9 value="" <% if(!userPO.getPurpose().equals("INQUIRY")){out.print("onkeypress=enterDecimal()");} %>>
            </td>
          </tr>
 
         </table>
  </table>
  <SCRIPT language="JavaScript">
 showChecked("CURRCODE");
</SCRIPT>
</form>
</body>
</html>
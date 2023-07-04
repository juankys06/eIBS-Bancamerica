<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import ="datapro.eibs.master.Util" %>
<HTML>
<HEAD>
<TITLE>
Lista de Avales
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "bolaval" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "bolgaran" class= "datapro.eibs.beans.ELC500001Message"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

    builtNewMenu(bg_m_opt);
    initMenu();

</SCRIPT>

<script language="JavaScript">
<!--
//-->
  function goAction(op) {

    document.forms[0].opt.value = op;

    if (op == 3) {
//						 Ask for confirmation in order to delete
		if (!confirm("Esta seguro que desea eliminar este aval?")) return;
	}
	document.forms[0].submit();


  }

//-->
function showAddInfo(idxRow){
   for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].actRow.value = idxRow;
  }
</script>
</head>

<body>
<%
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }

%>
<h3 align="center">Listado de Avales <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bg_list_aval.jsp,ELC5000">
</h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="opt" VALUE="1">
<INPUT TYPE=HIDDEN NAME="actRow" VALUE="">
<INPUT TYPE=HIDDEN NAME="SEQ" VALUE="">
<table class=tableinfo>
  <tr>
   <td>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr id=trdark>
 	     <td align=right nowrap>Boleta :</td>
	     <td colspan= 3>
	      <input size="15" type="text" name="E01LCMACC" value="<%= bolgaran.getE01LCMACC().trim() %>" readonly>
	     </td>
	</tr>
    <tr id=trclear>
         <td align=right nowrap>Sucursal :</td>
         <td><input size="5" type="text" name="E01LCMBRN" maxlength="3" value="<%=bolgaran.getE01LCMBRN()%>"></td>
    	 <td align=right nowrap>Producto :</td>
         <td>
         	<input size="5" type="text" name="E01LCMPRO" readonly value="<%= bolgaran.getE01LCMPRO()%>">
            <input size="35" type="text" name="E01PRDNME" readonly value="<%= bolgaran.getE01PRDNME()%>">
         </td>
    </tr>
    <tr id=trdark>
	      <td align=right nowrap>Fecha Emisión :</td>
	      <td>
	        <input size="3" type="text" name="E01LCMIDM" maxlength="2" value="<%=bolgaran.getE01LCMIDM()%>" readonly>
			<INPUT size="3" type="text" name="E01LCMIDD" maxlength="2" value="<%=bolgaran.getE01LCMIDD()%>" readonly>
			<INPUT size="5" type="text" name="E01LCMIDY" maxlength="4" value="<%=bolgaran.getE01LCMIDY()%>" readonly>
		  </td>
	      <td align=right nowrap>Fecha Vcto. :</td>
          <td>
            <input size="3" type="text" name="E01LCMEXM" maxlength="2" value="<%=bolgaran.getE01LCMEXM()%>" readonly>
            <INPUT size="3" type="text" name="E01LCMEXD" maxlength="2" value="<%=bolgaran.getE01LCMEXD()%>" readonly>
            <INPUT size="5" type="text" name="E01LCMEXY" maxlength="4" value="<%=bolgaran.getE01LCMEXY()%>" readonly>
          </td>
    </tr>
  </table>
  </td>
  </tr>
 </table>
<%
	if ( bolaval.getNoResult() ) {

       String action = "";
	   try {
		action = userPO.getHeader20();
		//userPO.setHeader20("");
       }
       catch (Exception e) {
       	action = "";
       }
       String pagename = "";
       try {
       	pagename = userPO.getHeader21();
       	//userPO.setHeader21("");
       }
       catch (Exception e) {
       	pagename = "";
       }

       if ( action.equals("DO_MAINT") || action.equals("DO_NEW")) {
%>
       	<SCRIPT Language="Javascript">
<%
              if ( !pagename.equals("") ) {
%>
	   		CenterWindow('<%= pagename %>',620,500,2);
<%
              }
%>
              </SCRIPT>
<%
	}
%>

  <TABLE class="tbenter" width=100% height=40%>
   	<TR>
      <TD>
        <div align="center">

          <p>&nbsp;</p>
          <p><b>La Boleta seleccionada a&uacute;n no posee Avales
            asignados, por favor elija una de las siguientes opciones</b></p>
          <table class="tbenter" width="100%">
            <tr>
              <td class=TDBKG><a href="javascript:goAction(1)">Nuevo</a></td>
              <td class=TDBKG><a href="<%=request.getContextPath()%>/pages/background.htm">Salir</a></td>
            </tr>
          </table>
          <p>&nbsp; </p>
        </div>
      </TD>
     </TR>
   </TABLE>
  <%
	}
	else {

      	String action = "";
		try {
			action = userPO.getHeader20();
			//userPO.setHeader20("");
        }
        catch (Exception e) {
       		action = "";
        }
        String pagename = "";
        try {
       	 	pagename = userPO.getHeader21();
       	 	//userPO.setHeader21("");
        }
        catch (Exception e) {
       		pagename = "";
       	}

       if ( action.equals("DO_MAINT") || action.equals("DO_NEW")) {
%>
       	<SCRIPT Language="Javascript">
<%
              if ( !pagename.equals("") ) {
%>
	   		CenterWindow('<%= pagename %>',620,500,2);
<%
              }
%>
        </SCRIPT>
<%
	}
%>

  <INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">

  <table class="tbenter" width="100%">
    <tr>

      <td class=TDBKG><a href="javascript:goAction(1)">Nuevo</a></td>
      <td class=TDBKG><a href="javascript:goAction(2)">Modificar</a></td>
      <td class=TDBKG><a href="javascript:goAction(3)">Borrar</a></td>
      <td class=TDBKG><a href="<%=request.getContextPath()%>/pages/background.htm">Salir</a></td>

    </tr>
  </table>
 <TABLE  id="mainTable" class="tableinfo">
 <TR>
   <TD NOWRAP valign="top">
        <TABLE id="headTable">
          <TR id="trdark">
            <TH ALIGN=CENTER NOWRAP></TH>
            <TH ALIGN=CENTER NOWRAP>Cod. Cliente</TH>
            <TH ALIGN=CENTER NOWRAP>Nombre</TH>
            <TH ALIGN=CENTER NOWRAP>% Avala</TH>
          </TR>
        </TABLE>

    <div id="dataDiv1" class="scbarcolor" style="padding:0">
        <table id="dataTable">
    <%
         int row;
		 try{row = Integer.parseInt(request.getParameter("ROW"));}catch(Exception e){row = 0;}
         int k=1;
         bolaval.initRow();
         while (bolaval.getNextRow()) {
            datapro.eibs.beans.ELC500002Message msgAval = (datapro.eibs.beans.ELC500002Message) bolaval.getRecord();
     %>
            <TR>
              <td ALIGN=CENTER NOWRAP>
                <INPUT TYPE="radio" NAME="ROW" VALUE="<%= bolaval.getCurrentRow() %>" <% if (bolaval.getCurrentRow() == row) out.print("checked"); %> onClick="showAddInfo(<%= bolaval.getCurrentRow() %>)">
              </td>
              <td align=center nowrap><%=msgAval.getE02CUMBNI() %></td>
         	  <td nowrap><%=msgAval.getE02CUMMA1() %></td>
         	  <td align=center nowrap><%=msgAval.getE02CUMNST() %></td>
    		</TR>
      <%
              k++;
         }
    %>
          </table>
   </div>
   </TD>
  </TR>
</TABLE>

<SCRIPT language="JavaScript">
  document.forms[0].totalRow.value="<%= k %>";
  function resizeDoc() {
       divResize();
       adjustEquTables(headTable, dataTable, dataDiv1,1,false);
  }
  showChecked("ROW");
  resizeDoc();
  window.onresize=resizeDoc;

</SCRIPT>


  <%
  }
%>
</FORM>

</BODY>
</HTML>

<html>
<head>
<title>Special Instructions History</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0701Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Historia de Instrucciones Especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="spins_history_list.jsp, EWD0701"></h3>
<hr size="4">
<FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.general.JSEWD0701" >
  <input type=HIDDEN name="SCREEN" value="1">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="selRow" value="0">
  <%
	if ( EWD0701Help.getNoResult() ) {
 %> 
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>Historia Vacia por el momento</b></p>
        </div>

	  </TD>
	</TR>
    </TABLE>
	
  <%  
		}
	else {
%> <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }

%> 

  <table class="tableinfo" width="100%">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="10%" align="right"><b>Cliente :</b></td>
            <td nowrap width="20%" align="left">
				<input type="text" name="SPHCUN" size="9" readonly value="<%= userPO.getCusNum().trim()%>">
            </td>
            <td nowrap width="10%" align="right"><b>Nombre :</b></td>
            <td nowrap width="40%" align="left"> 
                <input type="text" name="EWDNA1" size="45" readonly value="<%= userPO.getCusName().trim()%>">
            </td>
            <td nowrap width="10%" align="right"><b>Producto : </b></td>
            <td nowrap width="10%"> 
              	<input type="text" name="EWDPRO" size="5" readonly value="<%= userPO.getProdCode().trim()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="10%" align="right"><b>Cuenta : </b></td>
            <td nowrap width="20%" align="left"> 
                <input type="text" name="EWDACC" size="17" value="<%= userPO.getAccNum().trim()%>" readonly>
            </td>
            <td nowrap width="10%" align="right"><B>Oficial : </B></td>
            <td nowrap width="40%" align="left"> 
                <input type="text" name="EWDOFC" size="30" readonly value="<%= userPO.getOfficer().trim()%>">
            </td>
            <td nowrap width="10%" align="right"><b>Moneda : </b></td>
            <td nowrap width="10%" align="left"> 
                <input type="text" name="EWDCCY size="4" value="<%= userPO.getCurrency().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp; </p>

<TABLE  id="mainTable" class="tableinfo" width="100%"> 
 <TR > 
    <TD NOWRAP valign="top" >
        <table id="headTable" width="100%" >
          <tr id="trdark"> 
            <th align=CENTER nowrap width="80%">Instrucción</th>
            <th align=CENTER nowrap width="20%">Fecha - Hora</th>
          </tr>
        </table>
	
     <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" width="100%"> 
			<%
                EWD0701Help.initRow();
				int k=1;
                while (EWD0701Help.getNextRow()) {                 
                  datapro.eibs.beans.EWD0701DSMessage msgList = (datapro.eibs.beans.EWD0701DSMessage) EWD0701Help.getRecord();
                  %>                  
                  <TR>
					<TD NOWRAP ALIGN=LEFT>
		                <input type="text" name="EWDDSC" size="90" value="<%=msgList.getEWDDSC()%>" readonly>
					</TD>
						<TD NOWRAP ALIGN=CENTER><%=msgList.getEWDDTE()%></TD>
				 </TR>
               <%   k++;   
                }
              %> 
    </table>
   </div>
</Table>

<SCRIPT language="JavaScript">
     document.forms[0].totalRow.value="<%= k %>";
     function resizeDoc() {
        divResize(false);
     	adjustEquTables(headTable, dataTable, dataDiv1,1,false);
      }
     resizeDoc();
     window.onresize=resizeDoc;
     
</SCRIPT>

<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
		String type = userPO.getType().trim();
		String code = userPO.getAccNum().trim();
        if ( EWD0701Help.getShowPrev() ) {
      			int pos = EWD0701Help.getFirstRec() - 21;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.general.JSEWD0701?SCREEN=1&TYPE=" + type + "&CODE=" + code +"&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
   %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <% 
        if ( EWD0701Help.getShowNext() ) {
      			int pos = EWD0701Help.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.general.JSEWD0701?SCREEN=1&TYPE=" + type + "&CODE=" + code +"&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
        }
  %> 
   </TD>
 	</TR>
 	</TABLE>
<%}%>


  </form>

</body>
</html>

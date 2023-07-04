<html>
<head>
<title>User Spool</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "EWD0900Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<script language="JavaScript">

function goAction(op) {

	document.forms[0].opt.value = op;
	if (op == 1) {
	    var row = document.forms[0].selRow.value;
		var page = "<%=request.getContextPath()%>/servlet/datapro.eibs.general.JSEWD0900?SCREEN=2&ROW=" + row + "&opt=" + op;
		CenterNamedWindow(page,'PDF',500,500,7);		
		//document.forms[0].target="PDF"
	} else {
		document.forms[0].target="";
		var ok = confirm("El documento seleccionado será borrado");
		if (!ok) return;
		document.forms[0].submit();
	}
	
  
}

function openCSVFile(nameOfFile, row) {
   pg = "<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSPDFParserSend?source='"+nameOfFile+"'&ROW=" +row ;
   CenterWindow(pg,600,500,2);
}

</SCRIPT>  

</head>

<BODY>
<h3 align="center">Trabajar con lista de archivos del Usuario<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="sel_spool.jsp, EWD0900"></h3>
<hr size="4">
<FORM METHOD="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.general.JSEWD0900" >
  <input type=HIDDEN name="SCREEN" value="2">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="totalRow" value="0">
  <input type=HIDDEN name="selRow" value="0">
  <%
	if ( EWD0900Help.getNoResult() ) {
 %> 
  <p>&nbsp;</p><TABLE class="tbenter" width="100%" >
    <TR>
      <TD > 
        <div align="center"> 
          <p><b>Lista Vacia por el momento</b></p>
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
  <p> 

          
  <table class="tbenter" width=100% align=center>
    <tr> 
      <TD align="CENTER" class="EIBSBTN" width="33%"><A href="javascript:goAction(1)" id="linkInquiry">
      		<img name="Image1" border="0" src="<%=request.getContextPath()%>/images/s/INQUIRY.gif" ></a> 
      </TD>
      <TD align="CENTER" class="EIBSBTN" width="33%"><A href="javascript:goAction('2')" id="linkDelete">
      		<img name="Image2" border="0" src="<%=request.getContextPath()%>/images/s/delete.gif" ></a> 
      </TD>
	  <TD align="CENTER" class="EIBSBTN" width="34%"><A href="<%=request.getContextPath()%>/pages/background.jsp" id=linkExit">
      		<img name="Image7" border="0" src="<%=request.getContextPath()%>/images/s/EXIT.gif" ></a>
	  </TD>
    </tr>
  </table>
   
  <br>
  
  <TABLE  id="mainTable" class="tableinfo">
 <TR > 
    <TD NOWRAP valign="top" width="100%" >
        <table id="headTable" width="100%" >
          <tr id="trdark"> 
            <th align=CENTER nowrap >&nbsp;</th>
            <th align=CENTER nowrap > 
              <div align="center">File</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Ref</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Descripción</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Usuario<br> </div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Tipo de<BR>Archivo</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Paginas</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Fecha</div>
            </th>
            <th align=CENTER nowrap > 
              <div align="center">Hora</div>
            </th>
			<th align=CENTER nowrap > 
              <div align="center">Excel</div>
            </th>
            
          </tr>
        </table>
     <div id="dataDiv1" class="scbarcolor" >
    <table id="dataTable" > 
			<%
                EWD0900Help.initRow();
				int k=1;
                while (EWD0900Help.getNextRow()) {                 
                  datapro.eibs.beans.EWD0900DSMessage msgList = (datapro.eibs.beans.EWD0900DSMessage) EWD0900Help.getRecord();
                  %>                  
                  <TR>
						<TD NOWRAP ALIGN=RIGHT>
							<input type="radio" name="ROW" value="<%=EWD0900Help.getCurrentRow()%>" onclick ="document.forms[0].selRow.value=<%=EWD0900Help.getCurrentRow()%>" <% if (EWD0900Help.getCurrentRow()==0) out.print("checked");%>>
						</TD>
						<TD NOWRAP ALIGN=LEFT><%=msgList.getSWDFIL()%></TD>
						<TD NOWRAP ALIGN=CENTER><%=msgList.getSWDPLN()%></TD>
						<TD NOWRAP ALIGN=LEFT><%=msgList.getSWDOUT()%></TD>
						<TD NOWRAP ALIGN=CENTER><%=msgList.getSWDSRD()%></TD>
						<TD NOWRAP ALIGN=CENTER><%=msgList.getSWDSTS()%></TD>
						<TD NOWRAP ALIGN=CENTER><%=msgList.getSWDPAG()%></TD>
						<TD NOWRAP ALIGN=CENTER><%=msgList.getSWDDAT()%></TD>
						<TD NOWRAP ALIGN=CENTER><%=msgList.getSWDTIM()%></TD>
				 
               <%  
					String pdfPath = datapro.eibs.master.JSEIBSProp.getEODPDFURL() + "/" + msgList.getSWDPTH();	            
					%>
						<TD nowrap align="center" width="1%"><a href="javascript:openCSVFile('<%=pdfPath %>', <%=k %>)"><img src="<%=request.getContextPath() +"/images/calendar.gif" %>" align="middle" vspace="0" hspace="0" border="0"></a></TD>
					</TR>
				<%

					 k++;   
					 

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
     
     try {
	     document.forms[0].ROW[0].click();
	 } catch (e) {
	     document.forms[0].ROW.click();
	 }
     
</SCRIPT>
<TABLE class="tbenter" WIDTH="98%" ALIGN=CENTER>
  <TR>
  <TD WIDTH="50%" ALIGN=LEFT>
  <%
        if ( EWD0900Help.getShowPrev() ) {
      			int pos = EWD0900Help.getFirstRec() - 21;
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.general.JSEWD0900?SCREEN=1&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/previous_records.gif\" ></A>");
        }
   %>  
  </TD>
  <TD WIDTH="50%" ALIGN=RIGHT>     
 <% 
        if ( EWD0900Help.getShowNext() ) {
      			int pos = EWD0900Help.getLastRec();
      			out.println("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.general.JSEWD0900?SCREEN=1&Pos=" + pos +"\"><IMG border=\"0\" src=\""+request.getContextPath()+"/images/s/next_records.gif\" ></A>");
        }
  %> 
   </TD>
 	</TR>
 	</TABLE>
<%}%>


  </form>

</body>
</html>

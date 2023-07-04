<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Información Financiera
</TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="brnDetails" class="datapro.eibs.beans.EDP073001Message"  scope="session" />


<jsp:useBean id= "optListF" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "grpList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "accList" class= "datapro.eibs.beans.JBListRec"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 

<script language="javascript">

function showOption(obj){
 var i= obj[obj.selectedIndex].value; 
   document.all["dataGrpTable"+ document.forms[0].ActOpt.value].style.display="none"
   document.all["dataGrpTable"+ i].style.display=""
   document.forms[0].ActOpt.value = i;
   adjustEquTables(headTable1, document.all["dataGrpTable"+ i], dataDiv1,1,false);
   showChecked("GRPNUM"+i);
}

function showGrpAcc(flg,idxRow){
  var i= document.forms[0].ActAccTb.value;
  var j= document.forms[0].ActOpt.value;
  var k= document.all["dataGrpTable"+j].rows.length;
  
  for(var l=0;l<k;l++) {
    document.all["dataGrpTable"+j].rows[l].className="trnormal";
    }
  document.all["dataGrpTable"+j].rows[idxRow].className="trhighlight";
  document.all["dataAccTable"+i].style.display="none";
  document.forms[0].ActAccTb.value=flg;
  try {
  	document.all["dataAccTable"+flg].style.display="";
  	if (document.all["dataAccTable"+flg].rows.length > 0) {
  	ACCINFO.style.display="";
  	adjustEquTables(headTable2, document.all["dataAccTable"+flg], dataDiv2,1,false);
  	showChecked("ACCNUM"+flg);
  	} else ACCINFO.style.display="none";
  } catch(e){}
  finally {}  
  
}    

function setAccInfo(valNA1,idxRow,idxAbsRow){

// alert(document.forms[0].E01DPXLID.text);

  var dataTable=document.all["dataAccTable"+document.forms[0].ActAccTb.value];
  for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idxRow].className="trhighlight";
   document.forms[0].E01IFMNA1.value=valNA1;
   document.forms[0].E01IFMGLN.value=trim(dataTable.rows[idxRow].cells[1].innerText);
   document.forms[0].E01IFMDSC.value=trim(dataTable.rows[idxRow].cells[2].innerText);
   document.forms[0].E01IFMAMT.value=trim(replaceAll(dataTable.rows[idxRow].cells[3].innerText,","));
   document.forms[0].E01CNORCD.value=trim(dataTable.rows[idxRow].cells[5].innerText);
   document.forms[0].E01DPMS01.value="<%= brnDetails.getE01DPMS01().trim()%>";
   document.forms[0].E01DPXLID.value=trim(dataTable.rows[idxRow].cells[7].innerText);
   document.forms[0].ROW.value=idxAbsRow;

// alert(document.forms[0].E01DPXLID.value);

}



 function showAMT(evt) {
     evt = (evt) ? evt: (window.event)?window.event : "");
     var AMT=document.getElementById("AMT");	 
	 var y= evt.ClientY + document.body.scrollTop;
     var x= evt.ClientX + document.body.scrollLeft;
	 cancelBub(evt);
	 moveElement(AMT, y,x);
	 setVisibility(AMT, "visible");
 }

 function hideAMT(){
      //AMT.style.visibility="hidden";
      setVisibility(document.getElementById("ATM"),"hidden");
 }

 
 function goAction(op) {
   //document.forms[0].opt.value = op;
   switch (op) {
    case 1 :{
    
		  	if (document.forms[0].E01DPXLID.value != "Titulo") {
			  	if (document.forms[0].E01DPXLID.value != "Calculo") {
		            ShowAMT();
    				document.forms[0].E01IFMAMT.focus();
	            }
            }
            }
            break;
    case 2 :{
            page= prefix +language + "EDP0730_client_financial_plan.jsp"
 			CenterWindow(page,600,500,4);
            break;
            }
    case 3 :{
            page = webapp + "/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=3";
			CenterWindow(page,600,500,3);
    		break;
            }
    case 4 :{
//       alert(document.forms[0].E01IFMDSC.value)

	    document.forms[0].SCREEN.value="4";
     	document.forms[0].submit();	  	
//			CenterWindow(page,600,500,3);

    		break;
            }
    case 5 :{
            page = webapp + "/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=200&E01IFMCUN="
							+ userPO.getHeader1()
							+ "&E01IFMCFO="
							+ userPO.getHeader3()
							+ "&E01IFMFEY="
							+ userPO.getHeader6()
							+ "&E01IFMFEM="
							+ userPO.getHeader7()
							+ "&ROW="
							+ req.getParameter("ROW")
							+ "&E01IFMCFA="
							+ userPO.getHeader8());

    		break;
            }
   }  
   
 }
 
 function goExit(){
  window.location.href="<%=request.getContextPath()%>/pages/background.jsp";

  }

function goConfirm() {

	document.forms[0].submit();	  		  
}



</script>

</HEAD>

<BODY>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %> 
<SCRIPT Language="Javascript">
      showErrors();
</SCRIPT>
 <%
 }
%>

<h3 align="center">Información Financiera Propuesta de Crédito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_financial.jsp,EDP0730"></h3>
<hr size="4">
<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
<INPUT TYPE=HIDDEN NAME="ActRow" VALUE="0">
<INPUT TYPE=HIDDEN NAME="ActAccTb" VALUE="0">
<INPUT TYPE=HIDDEN NAME="ActGrpTb" VALUE="0">
<INPUT TYPE=HIDDEN NAME="ActOpt" VALUE="0">

<INPUT type=HIDDEN NAME="E01IFMDSC" value="">
<INPUT type=HIDDEN NAME="E01IFMTYP" value="D">
<INPUT type=HIDDEN NAME="E01IFMNA1" value="">
<INPUT type=HIDDEN NAME="E01DPMS01" value="">
<INPUT type=HIDDEN NAME="E01DPXLID" value="">
<INPUT type=HIDDEN NAME="ROW" value="">

<!--
<div id="AMT" style="position:absolute; visibility:hidden; left : 0px; top : -50px; z-index:3; filter:progid:DXImageTransform.Microsoft.Fade(duration=1.0,overlap=1.0)}" onclick="cancelBub()">
-->
<div id="AMT" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<TR>
	  <TD ALIGN=center colspan=2>
          <b>Entrada Contable</b>  
      </TD>
   </TR>
	<TR>
	  <TD ALIGN=right nowrap>
          <b>Cuenta :</b>  
      </TD>
      <TD ALIGN=Left nowrap>
	     <input type="text" size=17 name="E01IFMGLN" value="" readonly>  
	  </TD>
      <TD ALIGN=Left nowrap>
	     <input type="text" size=5 name="E01CNORCD" value="" readonly>  
	  </TD>
   </TR>
   <TR>
	  <TD ALIGN=right nowrap>
          <b>Monto:</b>  
      </TD>
      <TD ALIGN=Left nowrap>
	     <input type="text" maxlength=15 size=16 name="E01IFMAMT" value="" onKeypress="enterDecimal()">  
	 </TD>
   </TR>
   <TR>
	  <TD ALIGN=center colspan=2>
	    <p> </p>
	  </TD>
   </TR>
   <TR>
	  <TD ALIGN=center colspan=2>
	     <p>
  <div align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar"  onclick="goConfirm()">
  </div>

      </TD>
   </TR>
 </TABLE>
</div>
<table class="tableinfo">    
 <TR > 
  <TD NOWRAP >
   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMCUN" size="12" readonly value="<%=userPO.getHeader1()%>">
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
         <input type="text" name="NAMECUM" size="45" readonly value="<%=userPO.getHeader2()%>">
      </td>         
    </tr> 
    <tr id="trdark"> 
      <td align="right"  >
         <b>Industria :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMCIN" size="16" readonly
					value="<%=userPO.getHeader4()%>">
      </td>
      <td align="right"  >
         <b>Negocio :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMLNE" size="16" readonly
					value="<%=userPO.getHeader5()%>">
      </td> 
      <td align="right"  >
         <b>Formato :</b>
      </td>
      <td nowrap > 
         <input type="text" name="E01IFMCFOW" size="2" readonly value="<%=userPO.getHeader3()%>">-
         <input type="text" name="E01IFMFEY" size="4" readonly value="<%=userPO.getHeader6()%>">/
         <input type="text" name="E01IFMFEM" size="2" readonly value="<%=userPO.getHeader7()%>">
         <input type="text" name="E01IFMCFA" size="4" readonly value="<%=userPO.getHeader8()%>">
      </td>        
    </tr>  
  </table> 
 </td>        
 </tr>  
</table>
<%
	if ( accList.getNoResult() ) {
 %>
   		<TABLE class="tbenter" width=100% height=90%>
   		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda 
        </b></font> </div>
      </TD></TR>
   		</TABLE>
<%   		
	}
	else {
%>
  
  <TABLE class="tbenter">
    <TR>



      <TD ALIGN=LEFT width="25%" nowrap> 
       <SELECT name="OPTION" onChange="showOption(this)">
       <%
                optList.initRow();
                int k=0;
                while (optList.getNextRow()) {
                    if (optList.getFlag().equals("")) {
                    %>
          <option value="<%=optList.getCurrentRow() %>"><%=optList.getRecord(0)%></option>
        <%            k++;
                    }        
                }                 
    		%>
		</SELECT>
	  <SELECT name="E01IFMCFO">
			<%
                optListF.initRow();
                int k2=1;
                while (optListF.getNextRow()) {
                    if (optListF.getFlag().equals("")) {
                    		out.println(optListF.getRecord());
                    k2++;
                    }        
                }                 
    			%>
		</SELECT></TD>
      <TD ALIGN=CENTER width="25%" class=tdbkg> <a href="javascript:goAction(2)"><b>Plan Contable</b></a> 
      </TD>
      <TD ALIGN=CENTER width="25%" class=tdbkg> <a href="javascript:goAction(3)"><b>Indices Financieros</b></a> 
      </TD>
      <TD ALIGN=CENTER width="25%" class=tdbkg> <a href="javascript:goExit()" ><b>Salir</b></a> 
      </TD>
    </TR>
  </TABLE>
        
  
  <TABLE  id="mainTable" class="tableinfo" ALIGN=CENTER width="100%">
    <TR> 
      <TD NOWRAP valign="top" width="100%"> 
        <TABLE id="headTable1" >
     		<TR id="trdark"> 
      			<TH ALIGN=CENTER NOWRAP></TH>
      			<TH ALIGN=CENTER NOWRAP>Descripción</TH>      
     		</TR>
    	 </TABLE>
  
      <div id="dataDiv1" class="scbarcolor">
   <%  
         boolean firstTime = true;
		 String grpChk = "";
		 String flg = "";
		 int accRow = 0;
		 int grpRow = 0;
		 int selOpt = 0;
		 int actAccTb = 0;
		 int actGrpTb = 0;
		 
		 try { 
		   accRow = Integer.parseInt(request.getParameter("ROW")); 
		 } catch (Exception e) {};
		 accList.setCurrentRow(accRow);
		 flg = accList.getFlag();
		 actAccTb = Integer.parseInt(flg); //
		 try {
			 grpRow = Integer.parseInt(flg.substring(1,flg.length()));
			 selOpt = Integer.parseInt(flg.substring(0,1));	
		  } catch (Exception e) {};	
                   
         for (int i=0;i<k;i++) { %>  
    	<table id="dataGrpTable<%= i%>" style="display:none;">
  			<%
                int j = 0;
			    firstTime = true;
                grpList.initRow();
                while (grpList.getNextRow()) {
                    if (grpList.getFlag().equals(""+i)) {
                    if (i == selOpt) {
                      grpChk=(grpRow==j)?"checked":"";
                    } else {
                      grpChk=(firstTime)?"checked":"";
                    }
                    firstTime = false;	
 			 %> 
     		<TR>       			
                <TD ALIGN=Center NOWRAP> 
                <input type="radio" name="GRPNUM<%= i%>" value="<%= grpList.getFlag() + "" + j %>" onClick="showGrpAcc(this.value,<%= j %>)" <%= grpChk %>>
				</TD>
      			<TD ALIGN=LEFT NOWRAP><a href="javascript:radioClick('GRPNUM<%= i%>',<%= j %>)"><DIV NOWRAP><%= grpList.getRecord(0) %></DIV></a></TD>      
      		</TR>
  <% 
                     j++;
                    }
                }
  %>
 	</table>
  <% 
       }
  %>
   </div>
   </TD>
  </TR>	
</TABLE>
<DIV id="ACCINFO">
<TABLE class="tbenter" id="opTable">
  <TR>  
   <TD ALIGN=CENTER class=tdbkg><a href="javascript:goAction(1)"><b>Modificar</b></a></TD>
   <TD ALIGN=CENTER class=tdbkg><a href="javascript:goAction(4)"><b>Memo</b></a></TD>
  </TR>
</TABLE>
  
<TABLE  id="mainTable2" class="tableinfo" ALIGN=CENTER width="100%">
    <TR> 
      <TD NOWRAP valign="top" width="100%" > 
        <TABLE id="headTable2" >
     		<TR id="trdark"> 
      			<TH ALIGN=CENTER NOWRAP></TH>
				<TH ALIGN=CENTER NOWRAP>Cuenta</TH>
				<TH ALIGN=CENTER NOWRAP>Descripción</TH>
      			<TH ALIGN=CENTER NOWRAP>Monto</TH>      			
      			<TH ALIGN=CENTER NOWRAP>Audit</TH>      			
      			<TH ALIGN=CENTER NOWRAP>Grupo</TH>      			
      			<TH ALIGN=CENTER NOWRAP>Sec.</TH>      			
      			<TH ALIGN=CENTER NOWRAP>Tipo</TH>      			
     		</TR>
    	 </TABLE>
  
   <div id="dataDiv2" class="scbarcolor">
    <%
             grpList.initRow();
             int g=0;
             int chgGrp =0;			 
             while (grpList.getNextRow()) {
               if (Integer.parseInt(grpList.getFlag()) != chgGrp) {
                  chgGrp = Integer.parseInt(grpList.getFlag());
                  g=0;
                }
    %> 
   <table id="dataAccTable<%=grpList.getFlag()+""+g%>" style="display:none;">
  <%
          	    String accChk = "";
          	    accList.initRow();
			    int j = 0;
				firstTime = true;
                while (accList.getNextRow()) {
                    if (accList.getFlag().equals(grpList.getFlag()+""+g)) {
						if (accList.getFlag().equals(flg)) {
                         accChk=(accRow==accList.getCurrentRow())?"checked":"";
                    	} else {
                         accChk=(firstTime)?"checked":"";
                    	}
                    	firstTime = false;
                    	
  %> 
   		<TR> 
      			
            <TD ALIGN=Left NOWRAP>
                <input type="radio" name="ACCNUM<%= accList.getFlag() %>" value="<%= accList.getRecord(3) %>" <%=accChk%> onClick="setAccInfo(this.value,'<%=j%>','<%= accList.getCurrentRow() %>')">                
			</TD>
      		<TD ALIGN=LEFT NOWRAP><div nowrap><a href="javascript:radioClick('ACCNUM<%= accList.getFlag() %>',<%= j %>)"><%= accList.getRecord(0) %></a></DIV>
      		</TD>
      		<TD ALIGN=LEFT NOWRAP><div nowrap><a href="javascript:radioClick('ACCNUM<%= accList.getFlag() %>',<%= j %>)"><%= accList.getRecord(1) %></a></DIV>
      		</TD>      
      		<TD ALIGN=RIGHT NOWRAP><div nowrap><a href="javascript:radioClick('ACCNUM<%= accList.getFlag() %>',<%= j %>)"><%= datapro.eibs.master.Util.fcolorCCY(accList.getRecord(2)) %></a></DIV>
      		</TD>
      		<TD ALIGN=LEFT NOWRAP><div nowrap><a href="javascript:radioClick('ACCNUM<%= accList.getFlag() %>',<%= j %>)"><%= accList.getRecord(4) %></a></DIV>
      		</TD>
      		<TD ALIGN=LEFT NOWRAP><div nowrap><a href="javascript:radioClick('ACCNUM<%= accList.getFlag() %>',<%= j %>)"><%= accList.getRecord(5) %></a></DIV>
      		</TD>
      		<TD ALIGN=LEFT NOWRAP><div nowrap><a href="javascript:radioClick('ACCNUM<%= accList.getFlag() %>',<%= j %>)"><%= accList.getRecord(6) %></a></DIV>
      		</TD>
      		<TD ALIGN=LEFT NOWRAP><div nowrap><a href="javascript:radioClick('ACCNUM<%= accList.getFlag() %>',<%= j %>)"><%= accList.getRecord(7) %></a></DIV>
      		</TD>
   		</TR>
  <% 
						j++;
                    }		
                }
  %>
 	</table>
  <%  
               g++;      
            }
   %>
   </div>
  </TR>	
</TABLE>
</DIV>
<SCRIPT language="JavaScript">
   document.forms[0].ActRow.value=<%=accRow%>;
   document.forms[0].ActAccTb.value="<%=flg%>";
   document.forms[0].ActGrpTb.value="<%=selOpt%>";
   document.forms[0].ActOpt.value=<%=selOpt%>;

   document.forms[0].OPTION.value=<%=selOpt%>;
   document.forms[0].OPTION.value="0";

   showOption(document.forms[0].OPTION);  
   function resizeDoc() {
      var actvTbGrp = document.forms[0].ActGrpTb.value;
      var actvTbAcc = document.forms[0].ActAccTb.value;
      var dataTGrp = document.all["dataGrpTable"+actvTbGrp];
      var dataTAcc = document.all["dataAccTable"+actvTbAcc];
       adjustEquTables(headTable1, dataTGrp, dataDiv1,1,false);
       adjustEquTables(headTable2, dataTAcc, dataDiv2,1,false);
   }
    
   resizeDoc();
   window.onresize=resizeDoc;
	 	 
</SCRIPT>
<%           
  }
%>
</FORM>
 <script language="JavaScript">
    	  document.onclick=hideAMT;
  		  document.getElementById("AMT").onclick=cancelBub;
 </script>
</BODY>
</HTML>

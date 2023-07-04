<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>

<%@ page import = "datapro.eibs.master.Util" %>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="javascript">
//<!-- Hide from old browsers

function enter(code1,code2,code3,code4,code5,code6,code7,code8,code9,code10,code11,code12,code13,code14,code15,code16,code17,code18,code19,code20,code21,code22,code23,code24,code25,code26,code27) {

	var oForm = top.opener.document.forms[0];

	if(top.opener.fieldAux1 != "" && oForm[top.opener.fieldAux1]){oForm[top.opener.fieldAux1].value = code1}
 	if(top.opener.fieldAux2 != "" && oForm[top.opener.fieldAux2]){oForm[top.opener.fieldAux2].value = code2}
	if(top.opener.fieldAux3 != "" && oForm[top.opener.fieldAux3]){oForm[top.opener.fieldAux3].value = code3}
  
	if(top.opener.fieldAux4 != "" && oForm[top.opener.fieldAux4] && top.opener.fieldAux4 != 'C' && top.opener.fieldAux4 != '4' && top.opener.fieldAux4 != '5')
	{oForm[top.opener.fieldAux4].value = code4}
  
	if(top.opener.fieldAux5 != "" && oForm[top.opener.fieldAux5]){oForm[top.opener.fieldAux5].value = code5}
	if(top.opener.fieldAux6 != "" && oForm[top.opener.fieldAux6]){oForm[top.opener.fieldAux6].value = code6}
	if(top.opener.fieldAux7 != "" && oForm[top.opener.fieldAux7]){oForm[top.opener.fieldAux7].value = code7}
	if(top.opener.fieldAux8 != "" && oForm[top.opener.fieldAux8]){oForm[top.opener.fieldAux8].value = code8}
	if(top.opener.fieldAux9 != "" && oForm[top.opener.fieldAux9]){oForm[top.opener.fieldAux9].value = code9}
	if(top.opener.fieldAux10 != "" && oForm[top.opener.fieldAux10]){oForm[top.opener.fieldAux10].value = code10}
	if(top.opener.fieldAux11 != "" && oForm[top.opener.fieldAux11]){oForm[top.opener.fieldAux11].value = code11}
	if(top.opener.fieldAux12 != "" && oForm[top.opener.fieldAux12]){oForm[top.opener.fieldAux12].value = code12}
	if(top.opener.fieldAux13 != "" && oForm[top.opener.fieldAux13]){oForm[top.opener.fieldAux13].value = code13}
	if(top.opener.fieldAux14 != "" && oForm[top.opener.fieldAux14]){oForm[top.opener.fieldAux14].value = code14}
	if(top.opener.fieldAux15 != "" && oForm[top.opener.fieldAux15]){oForm[top.opener.fieldAux15].value = code15}
	if(top.opener.fieldAux16 != "" && oForm[top.opener.fieldAux16]){oForm[top.opener.fieldAux16].value = code16}
	if(top.opener.fieldAux17 != "" && oForm[top.opener.fieldAux17]){oForm[top.opener.fieldAux17].value = code17}
	if(top.opener.fieldAux18 != "" && oForm[top.opener.fieldAux18]){oForm[top.opener.fieldAux18].value = code18}
	if(top.opener.fieldAux19 != "" && oForm[top.opener.fieldAux19]){oForm[top.opener.fieldAux19].value = code19}
	if(top.opener.fieldAux20 != "" && oForm[top.opener.fieldAux20]){oForm[top.opener.fieldAux20].value = code20}
	if(top.opener.fieldAux21 != "" && oForm[top.opener.fieldAux21]){oForm[top.opener.fieldAux21].value = code21}
	if(top.opener.fieldAux22 != "" && oForm[top.opener.fieldAux22]){oForm[top.opener.fieldAux22].value = code22}
	if(top.opener.fieldAux23 != "" && oForm[top.opener.fieldAux23]){oForm[top.opener.fieldAux23].value = code23}
	if(top.opener.fieldAux24 != "" && oForm[top.opener.fieldAux24]){oForm[top.opener.fieldAux24].value = code24}
	if(top.opener.fieldAux25 != "" && oForm[top.opener.fieldAux25]){oForm[top.opener.fieldAux25].value = code25}
	if(top.opener.fieldAux26 != "" && oForm[top.opener.fieldAux26]){oForm[top.opener.fieldAux26].value = code26}
	if(top.opener.fieldAux27 != "" && oForm[top.opener.fieldAux27]){oForm[top.opener.fieldAux27].value = code27}
  
	top.close();
 }


//-->
</script>
</head>

<jsp:useBean id= "EWD0700Help" class= "datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<%
	if ( EWD0700Help.getNoResult() ) {
		String sMsg = "";
		if (userPO.getHeader1().equals("*")) {
			sMsg = "Usuario no tiene permisos para ver este Cliente";
		} else {
			//sMsg = "There is no match for your search criteria";
			sMsg = "No hay datos que correspondan con su criterio de busqueda";
		}	
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <b> <%= sMsg %></b> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
 
  <TABLE class="tableinfo" align="center" style="width:'95%'">
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="10%">Cliente</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Identificación</TH>
      <TH ALIGN=CENTER  nowrap width="30%">Nombre Legal</TH>
      <TH ALIGN=CENTER  width="50%">Dirección</TH>
    </TR>
    <%
                String Type = (String)session.getAttribute("Type");
                String NameSearch = (String)session.getAttribute("NameSearch");
                EWD0700Help.initRow();
                while (EWD0700Help.getNextRow()) {
                   	datapro.eibs.beans.EWD0700SMessage msgHelpS = (datapro.eibs.beans.EWD0700SMessage) EWD0700Help.getRecord();
                    	String idn = "";
						if (msgHelpS.getSWDIDN().equals(""))
							idn = msgHelpS.getSWDLN3();
						else
							idn = msgHelpS.getSWDIDN();
						String parameters = msgHelpS.getSWDCUN()
										+ "','"
										+ msgHelpS.getSWDNA1()
										+ "','"
										+ idn
										+ "','"
										+ msgHelpS.getSWDTID()
										+ "','"
										+ msgHelpS.getSWDPID()
										+ "','"
										+ msgHelpS.getSWDCTR()
										+ "','"
										+ msgHelpS.getSWDSTE()
										+ "','"
										+ msgHelpS.getSWDUC8()
										+ "','"
										+ msgHelpS.getSWDMUN()
										+ "','"
										+ msgHelpS.getSWDCTY()
										+ "','"
										+ msgHelpS.getSWDPAR()
										+ "','"
										+ msgHelpS.getSWDZPC()
										+ "','"
										+ msgHelpS.getSWDNA2()
										+ "','"
										+ msgHelpS.getSWDNM4()
										+ "','"
										+ msgHelpS.getSWDNM5()
										+ "','"
										+ msgHelpS.getSWDNIV()
										+ "','"
										+ msgHelpS.getSWDAPT()
										+ "','"
										+ msgHelpS.getSWDNA4()
										+ "','"
										+ msgHelpS.getSWDPOB()
										+ "','"
										+ msgHelpS.getSWDCCS()
										+ "','"
										+ msgHelpS.getSWDEDL()
										+ "','"
										+ msgHelpS.getSWDSEX()
										+ "','"
										+ msgHelpS.getSWDMST()
										+ "','"
										+ msgHelpS.getSWDHPN()										
										+ "','"
										+ msgHelpS.getSWDNA3()										
										+ "','"
										+ msgHelpS.getSWDLN3()										
										+ "','"
										+ msgHelpS.getSWDSHN();
	%>
			<TR>
				<td NOWRAP valign="top"><A HREF="javascript:enter('<%= parameters %>')"><%= msgHelpS.getSWDCUN() %></A></td>
				<td NOWRAP valign="top"><A HREF="javascript:enter('<%= parameters %>')"><%= msgHelpS.getSWDIDN() %></A></td>
				<td valign="top"><A HREF="javascript:enter('<%= parameters %>')"><%= msgHelpS.getSWDNA1() %></A></td>
				<td valign="top"><A HREF="javascript:enter('<%= parameters %>')"><%= 
					msgHelpS.getSWDNA2() + " " + msgHelpS.getSWDNA3() + " " + msgHelpS.getSWDNA4() %></A></td>
	<%
                }
 	 %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( EWD0700Help.getShowPrev() ) {
      			int pos =EWD0700Help.getFirstRec() - 81;
      			   out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0700?NameSearch=" + NameSearch + "&FromRecord=" + pos + "&Type=" + Type + "\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( EWD0700Help.getShowNext() ) {
      			int pos = EWD0700Help.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0700?NameSearch=" + NameSearch + "&FromRecord=" + pos + "&Type=" + Type + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

        }
%> </TD>
	 </TR>
	 </TABLE>
<%      
  }
%> 
</FORM>

</BODY>
</HTML>


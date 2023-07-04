<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Split</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">



<jsp:useBean id="invSplit" class="datapro.eibs.beans.EIE022001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/e/javascripts/optMenu.jsp"> </SCRIPT>
 
</head>
<body nowrap>
<%
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
     }


%>
<div align="center">
  <h3> Split Inquiry<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="inv_split_inq.jsp,EIE0240"></h3>
</div>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIE0240" >
    <input type="hidden" name="SCREEN"  value="400" >

<h4>Instrument Information</h4>

	<TR>
		<TD nowrap height="222">
			<TABLE class="tableinfo" cellpadding="2" cellspacing="0" width="100%" border="0">
				<TBODY>

				<TR id="trdark">
					<TD nowrap width="17%">
						<DIV align="right">  Instrument :</DIV>
					</TD>
					<TD nowrap width="56%">
						<INPUT type="text" name="E01ISIIIC" readonly size="4" maxlength="3" value="<%= invSplit.getE01ISIIIC()%>" onkeypress="enterDecimal()">
						<INPUT type="text" name="E01ISIDSC" readonly size="35" maxlength="30" value="<%= invSplit.getE01ISIDSC()%>" onkeypress="enterDecimal()">
						 
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="17%" height="33">
						<DIV align="right">Instrument Type :</DIV>
					</TD>
					<TD nowrap height="33" width="56%"><input type="text" name="E01ISIPTY" readonly size="15" maxlength="15" value="<%= invSplit.getE01ISIPTY()%>" onKeyPress="enterDecimal()"> Currency: 
						<INPUT type="text" name="E01ISICCY" readonly size="15" maxlength="15"
						value="<%= invSplit.getE01ISICCY()%>" onkeypress="enterDecimal()">
					</TD>
				</TR>

        	</table>
      	</td>
    </tr>
  
<h4>Outstanding Balance</h4>

	<TABLE class="tableinfo" cellpadding="2" cellspacing="0" width="100%" border="0">
		<TBODY>
		<TR id="trdark">

			<TD nowrap width="17%" >
				<DIV align="right"><A href="javascript:showOpenDetailInstrument(document.forms[0].E01ISIIIC.value)"><IMG src="<%=request.getContextPath()%>/images/aquire.gif" alt="Help" border="0" > Current Quantity :</A></DIV>
			</TD>
			<TD nowrap height="35" width="56%">
				<input type="text" name="E01QUANTY" readonly size="17" maxlength="15" value="<%= invSplit.getE01QUANTY()%>" onKeyPress="enterDecimal()">
			</TD>
		</TR>
		<TR id="trclear">
			<TD nowrap width="17%" height="40">
				<DIV align="right">Market Price :</DIV>
			</TD>
			<TD nowrap height="40" width="56%">
				<input type="text" name="E01UNTPRI" readonly size="17" maxlength="15" value="<%= invSplit.getE01UNTPRI()%>" onKeyPress="enterDecimal()">
			</TD>
		</TR>

		<TR id="trdark">
			<TD nowrap width="17%" height="40">
				<DIV align="right">Market Value :</DIV>
			</TD>
			<TD nowrap height="40" width="56%">
				<input type="text" name="E01NOMVAL" readonly size="17" maxlength="15" value="<%= invSplit.getE01NOMVAL()%>" onKeyPress="enterDecimal()">
			</TD>
		</TR>
		</TBODY>
	</TABLE>

<h4>Split Information</h4>
	<TABLE class="tableinfo" cellpadding="2" cellspacing="0" width="100%" border="0">
		<TBODY>
		<TR id="trclear">
			<TD nowrap width="17%">
				<DIV align="right">Split Number :</DIV>
			</TD>
			<TD nowrap width="56%">
				<input type="text" name="E01SPLNUM"  readonly size="9" maxlength="9" value="<%= invSplit.getE01SPLNUM()%>" onKeyPress="enterDecimal()">
			</TD>
		</TR>
		<TR id="trdark">
			<TD nowrap width="17%">
				<DIV align="right">Factor :</DIV>
			</TD>
			<TD nowrap width="56%">
				<input type="text" name="E01SCRFAC" readonly  size="11" maxlength="15" value="<%= invSplit.getE01SCRFAC()%>" onKeyPress="enterDecimal()">
			</TD>
		</TR>
		<TR id="trclear">
			<TD nowrap width="17%">
				<DIV align="right">New Market Price :</DIV>
			</TD>
			<TD nowrap width="56%">
				<INPUT type="text" name="E01NEWMKP" readonly  size="17" maxlength="15" value="<%= invSplit.getE01NEWMKP()%>" onkeypress="enterDecimal()">
			</TD>
		</TR>
		
		<TR id="trdark">
            <td nowrap width="18%" > 
              <div align="left">Record   Date :</div>
            </td> 
            <td nowrap colspan="3" >     
              <input type="text" name="E01RECDT1" readonly size="3" maxlength="2" value="<%= invSplit.getE01RECDT1() %>">
              <input type="text" name="E01RECDT2" readonly size="3" maxlength="2" value="<%= invSplit.getE01RECDT2() %>">
              <input type="text" name="E01RECDT3" readonly size="3" maxlength="2" value="<%= invSplit.getE01RECDT3()%>">
               
           <td >
        </tr >         
       
		<TR id="trdark">
            <td nowrap width="18%" > 
              <div align="left">Effecctive   Date :</div>
            </td> 
            <td nowrap colspan="3" >     
              <input type="text" name="E01PAYDT1" readonly size="3" maxlength="2" value="<%= invSplit.getE01PAYDT1() %>">
              <input type="text" name="E01PAYDT2" readonly size="3" maxlength="2" value="<%= invSplit.getE01PAYDT2() %>">
              <input type="text" name="E01PAYDT3" readonly size="3" maxlength="2" value="<%= invSplit.getE01PAYDT3()%>">
               
           <td >
        </tr >         
           
		</TBODY>
	</TABLE> 
<h4>Audit Information</h4>

	<TR>
		<TD nowrap height="222">
			<TABLE class="tableinfo" cellpadding="2" cellspacing="0" width="100%" border="0">
				<TBODY>

				<TR id="trdark">
					<TD nowrap width="17%">
						<DIV align="right">  User :</DIV>
					</TD>
					<TD nowrap width="56%">
						<INPUT type="text" name="E01SPLUSR" readonly size="15" maxlength="15" value="<%= invSplit.getE01SPLUSR()%>">
 					 
					</TD>
				</TR>
				<TR id="trclear">
					<TD nowrap width="17%" height="33">
						<DIV align="right">Request Date :</DIV>
					</TD>
					<td nowrap colspan="3" >     
              			<input type="text" name="E01SPLUP2" readonly size="3" maxlength="2" value="<%= invSplit.getE01SPLUP1() %>">
              			<input type="text" name="E01SPLUP1" readonly size="3" maxlength="2" value="<%= invSplit.getE01SPLUP2() %>">
              			<input type="text" name="E01SPLUP3" readonly size="3" maxlength="2" value="<%= invSplit.getE01SPLUP3()%>">
               
           			<td >
				</TR>

        	</table>
      	</td>
    </tr>
  


  </form>
</body>
</html>


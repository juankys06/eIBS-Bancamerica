<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Selección de Fecha</TITLE>

<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"></script>

<STYLE TYPE='text/css'>
<!--
INPUT {
  width: 30;
}
-->
</STYLE>

</HEAD>

<BODY>
<FORM NAME='CalendarForm'>
<TABLE BORDER='1' CELLSPACING='0' CELLPADDING='0' WIDTH='100%' HEIGHT='100%'>
  <TR WIDTH='210'>
    <TD><INPUT name='previous' id='previous' onclick='gotoPreviousMonth()' HEIGHT='24' WIDTH='30' type='button' value='&lt;'></TD>
    <TD colSpan='3'><SELECT name=s1 id=s1 onchange='gotoSelectedMonth()' HEIGHT'22' WIDTH='100'>
      <OPTION>Enero</OPTION><OPTION>Febrero</OPTION><OPTION>Marzo</OPTION><OPTION>Abril</OPTION>
      <OPTION>Mayo</OPTION><OPTION>Junio</OPTION><OPTION>Julio</OPTION><OPTION>Agosto</OPTION>
      <OPTION>Septiembre</OPTION><OPTION>Octubre</OPTION><OPTION>Noviembre</OPTION><OPTION>Diciembre</OPTION>
      </SELECT></TD>
    <TD colSpan='2'><SELECT name=s2 id=s2 onchange='gotoSelectedYear()' HEIGHT='22' WIDTH='60'>
      <SCRIPT>
      for(i=1900;i<2100;i++) {
        document.write('<OPTION>' + i + '</OPTION>')
      }
      </SCRIPT>
      </SELECT></TD>
    <TD><INPUT name='next' id='next' onclick='gotoNextMonth()' HEIGHT='24' WIDTH='30' type=button value='&gt;'></TD>
  </TR>

  <TR align=middle bgColor=#0066cc borderColor=ivory>
    <TD WIDTH='30' ALIGN='CENTER'><font color=#ffffff>Dom</font></TD>
    <TD WIDTH='30' ALIGN='CENTER'><font color=#ffffff>Lun</font></TD>
    <TD WIDTH='30' ALIGN='CENTER'><font color=#ffffff>Mar</font></TD>
    <TD WIDTH='30' ALIGN='CENTER'><font color=#ffffff>Mie</font></TD>
    <TD WIDTH='30' ALIGN='CENTER'><font color=#ffffff>Jue</font></TD>
    <TD WIDTH='30' ALIGN='CENTER'><font color=#ffffff>Vie</font></TD>
    <TD WIDTH='30' ALIGN='CENTER'><font color=#ffffff>Sab</font></TD>
  </TR>

  <TR>
    <TD><INPUT WIDTH='30' name=b1 id=b1 onclick=updateParentWindowDateElement(1) type=button></TD>
    <TD><INPUT WIDTH='30' name=b2 id=b2 onclick=updateParentWindowDateElement(2) type=button></TD>
    <TD><INPUT WIDTH='30' name=b3 id=b3 onclick=updateParentWindowDateElement(3) type=button></TD>
    <TD><INPUT WIDTH='30' name=b4 id=b4 onclick=updateParentWindowDateElement(4) type=button></TD>
    <TD><INPUT WIDTH='30' name=b5 id=b5 onclick=updateParentWindowDateElement(5) type=button></TD>
    <TD><INPUT WIDTH='30' name=b6 id=b6 onclick=updateParentWindowDateElement(6) type=button></TD>
    <TD><INPUT WIDTH='30' name=b7 id=b7 onclick=updateParentWindowDateElement(7) type=button></TD>
  </TR>
  <TR>
    <TD><INPUT WIDTH='30' name=b8 id=b8 onclick=updateParentWindowDateElement(8) type=button></TD>
    <TD><INPUT WIDTH='30' name=b9 id=b9 onclick=updateParentWindowDateElement(9) type=button></TD>
    <TD><INPUT WIDTH='30' name=b10 id=b10 onclick=updateParentWindowDateElement(10) type=button></TD>
    <TD><INPUT WIDTH='30' name=b11 id=b11 onclick=updateParentWindowDateElement(11) type=button></TD>
    <TD><INPUT WIDTH='30' name=b12 id=b12 onclick=updateParentWindowDateElement(12) type=button></TD>
    <TD><INPUT WIDTH='30' name=b13 id=b13 onclick=updateParentWindowDateElement(13) type=button></TD>
    <TD><INPUT WIDTH='30' name=b14 id=b14 onclick=updateParentWindowDateElement(14) type=button></TD>
  </TR>
  <TR>
    <TD><INPUT WIDTH='30' name=b15 id=b15 onclick=updateParentWindowDateElement(15) type=button></TD>
    <TD><INPUT WIDTH='30' name=b16 id=b16 onclick=updateParentWindowDateElement(16) type=button></TD>
    <TD><INPUT WIDTH='30' name=b17 id=b17 onclick=updateParentWindowDateElement(17) type=button></TD>
    <TD><INPUT WIDTH='30' name=b18 id=b18 onclick=updateParentWindowDateElement(18) type=button></TD>
    <TD><INPUT WIDTH='30' name=b19 id=b19 onclick=updateParentWindowDateElement(19) type=button></TD>
    <TD><INPUT WIDTH='30' name=b20 id=b20 onclick=updateParentWindowDateElement(20) type=button></TD>
    <TD><INPUT WIDTH='30' name=b21 id=b21 onclick=updateParentWindowDateElement(21) type=button></TD>
  </TR>
  <TR>
    <TD><INPUT WIDTH='30' name=b22 id=b22 onclick=updateParentWindowDateElement(22) type=button></TD>
    <TD><INPUT WIDTH='30' name=b23 id=b23 onclick=updateParentWindowDateElement(23) type=button></TD>
    <TD><INPUT WIDTH='30' name=b24 id=b24 onclick=updateParentWindowDateElement(24) type=button></TD>
    <TD><INPUT WIDTH='30' name=b25 id=b25 onclick=updateParentWindowDateElement(25) type=button></TD>
    <TD><INPUT WIDTH='30' name=b26 id=b26 onclick=updateParentWindowDateElement(26) type=button></TD>
    <TD><INPUT WIDTH='30' name=b27 id=b27 onclick=updateParentWindowDateElement(27) type=button></TD>
    <TD><INPUT WIDTH='30' name=b28 id=b28 onclick=updateParentWindowDateElement(28) type=button></TD>
  </TR>
  <TR>
    <TD><INPUT WIDTH='30' name=b29 id=b29 onclick=updateParentWindowDateElement(29) type=button></TD>
    <TD><INPUT WIDTH='30' name=b30 id=b30 onclick=updateParentWindowDateElement(30) type=button></TD>
    <TD><INPUT WIDTH='30' name=b31 id=b31 onclick=updateParentWindowDateElement(31) type=button></TD>
    <TD><INPUT WIDTH='30' name=b32 id=b32 onclick=updateParentWindowDateElement(32) type=button></TD>
    <TD><INPUT WIDTH='30' name=b33 id=b33 onclick=updateParentWindowDateElement(33) type=button></TD>
    <TD><INPUT WIDTH='30' name=b34 id=b34 onclick=updateParentWindowDateElement(34) type=button></TD>
    <TD><INPUT WIDTH='30' name=b35 id=b35 onclick=updateParentWindowDateElement(35) type=button></TD>
  </TR>
  <TR>
    <TD><INPUT WIDTH='30' name=b36 id=b36 onclick=updateParentWindowDateElement(36) type=button></TD>
    <TD><INPUT WIDTH='30' name=b37 id=b37 onclick=updateParentWindowDateElement(37) type=button></TD>
    <TD><INPUT WIDTH='30' name=b38 id=b38 onclick=updateParentWindowDateElement(38) type=button></TD>
    <TD><INPUT WIDTH='30' name=b39 id=b39 onclick=updateParentWindowDateElement(39) type=button></TD>
    <TD><INPUT WIDTH='30' name=b40 id=b40 onclick=updateParentWindowDateElement(40) type=button></TD>
    <TD><INPUT WIDTH='30' name=b41 id=b41 onclick=updateParentWindowDateElement(41) type=button></TD>
    <TD><INPUT WIDTH='30' name=b42 id=b42 onclick=updateParentWindowDateElement(42) type=button></TD>
  </TR>
</TABLE>
<SCRIPT language=JavaScript>
var cMonths=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
var currentDate = new Date();
var userMonth, userDay, userYear;

function padZeroIfSingleDigit(str)
{
  if (str.toString().length==1)
  {
    str = '0' + str.toString();
  }
  return str;
}

function getDaysInMonth(e, day) {
  var MDays=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
  D=MDays[e];
  if(day.getFullYear()%4==0 && e==1) {
    D=29;
  }
  return D;
}

function updateParentWindowDateElement(k) {
  var month;
  var day;
  var year;
  if (document.all) {
    id=document.all("b"+k);
    if (id.style.color=="white") {
      if (k < 8) {
        // gotoPreviousMonth();
        if (document.all.s1.selectedIndex==0) {
		month = "12";
		day = padZeroIfSingleDigit(id.value);
		year = ((document.all.s2.selectedIndex==0)?"2099":document.all.s2.selectedIndex+1900-1);
        }
	 else {
		month = padZeroIfSingleDigit(document.all.s1.selectedIndex);
		day = padZeroIfSingleDigit(id.value);
		year = (document.all.s2.selectedIndex+1900);
	 }
        enter(month, day, year);
        return;
      }
      else
      {
        // gotoNextMonth();
        if (document.all.s1.selectedIndex==11) {
		month = "01";
		day = padZeroIfSingleDigit(id.value);
		year = ((document.all.s2.selectedIndex==119)?"1900":document.all.s2.selectedIndex+1900+1);
	 }
        else {
		month = padZeroIfSingleDigit(document.all.s1.selectedIndex+2);
		day = padZeroIfSingleDigit(id.value);
		year = (document.all.s2.selectedIndex+1900);
	 }
        enter(month, day, year);
        return;
      }
    } 
    else {
      month = padZeroIfSingleDigit(document.all.s1.selectedIndex+1);
      day = padZeroIfSingleDigit(id.value);
      year = (document.all.s2.selectedIndex+1900);
    }
  } 
  else {
    id=document.CalendarForm.elements["b"+k];
    if (id.value=="") return;
    month = padZeroIfSingleDigit(document.CalendarForm.s1.selectedIndex+1);
    day = padZeroIfSingleDigit(id.value);
    year = (document.CalendarForm.s2.selectedIndex+1900);
  }
  enter(month, day, year);
}

function getCurrentFullYear()
{
  var currentFullYear = currentDate.getFullYear();
  if (currentFullYear.toString().length==3) currentFullYear = "20" + currentFullYear.toString().substring(1,3);
  return currentFullYear;
}

function gotoSelectedMonth()
{

  var currentFullYear = getCurrentFullYear();
  var currentDateDay = currentDate.getDate();
  if (currentDateDay>getDaysInSelectedMonth()) currentDateDay = getDaysInSelectedMonth();
  if (document.all)
  {
    currentDate = new Date(currentFullYear, document.all.s1.selectedIndex, currentDateDay);
  }
  else
  {
    currentDate = new Date(currentFullYear, document.CalendarForm.s1.selectedIndex, currentDateDay);
  }
  showCalendarUltra();
}

function gotoSelectedYear()
{
  var currentDateDay = currentDate.getDate();
  if (currentDateDay>getDaysInSelectedYearMonth()) currentDateDay = getDaysInSelectedYearMonth();
  if (document.all)
  {
    currentDate = new Date(document.all.s2.selectedIndex+1900, currentDate.getMonth(), currentDateDay);
  }
  else
  {
    currentDate = new Date(document.CalendarForm.s2.selectedIndex+1900, currentDate.getMonth(), currentDateDay);
  }
  showCalendarUltra();
}

function gotoPreviousMonth()
{

  var currentFullYear = getCurrentFullYear();
  var currentDateDay = currentDate.getDate();
  if (currentDateDay>getDaysInPreviousMonth()) currentDateDay = getDaysInPreviousMonth();
  if (currentDate.getMonth()==0)
  {
    if (currentFullYear==1900) currentFullYear = 2099;
    else currentFullYear = currentFullYear - 1;
    currentDate = new Date(currentFullYear, 11, currentDateDay);
  }
  else
  {
    currentDate = new Date(currentFullYear, currentDate.getMonth()-1, currentDateDay);
  }
  //alert(currentDate);
  showCalendarUltra();
}

function gotoNextMonth()
{

  var currentFullYear = getCurrentFullYear();
  var currentDateDay = currentDate.getDate();
  if (currentDateDay>getDaysInNextMonth()) currentDateDay = getDaysInNextMonth();
  if (currentDate.getMonth()==11)
  {
    if (currentFullYear==2099) currentFullYear = 1900;
    else currentFullYear = currentFullYear + 1;
    currentDate = new Date(currentFullYear, 0, currentDateDay);
  }
  else
  {
    currentDate = new Date(currentFullYear, currentDate.getMonth()+1, currentDateDay);
  }
  //alert(currentDate);
  showCalendarUltra();
}

function getDaysInPreviousMonth()
{
  var currentFullYear = getCurrentFullYear();
  var tempDate;
  if (currentDate.getMonth()==0)
  {
    if (currentFullYear==1900) currentFullYear = 2099;
    else currentFullYear = currentFullYear - 1;
    tempDate = new Date(currentFullYear, 11);
  }
  else
  {
    tempDate = new Date(currentFullYear, currentDate.getMonth()-1);
  }
  return getDaysInMonth(tempDate.getMonth(), tempDate);
}

function getDaysInNextMonth()
{
  var currentFullYear = getCurrentFullYear();
  var tempDate;
  if (currentDate.getMonth()==11)
  {
    if (currentFullYear==2099) currentFullYear = 1900;
    else currentFullYear = currentFullYear + 1;
    tempDate = new Date(currentFullYear, 0);
  }
  else
  {
    tempDate = new Date(currentFullYear, currentDate.getMonth()+1);
  }
  return getDaysInMonth(tempDate.getMonth(), tempDate);
}

function getDaysInSelectedMonth()
{
  var currentFullYear = getCurrentFullYear();
  var tempDate;
  var currentMonth;
  if (document.all) {
    currentMonth = document.all.s1.selectedIndex;
  }
  else {
    currentMonth = document.CalendarForm.s1.selectedIndex;
  }
  tempDate = new Date(currentFullYear, currentMonth);
  return getDaysInMonth(tempDate.getMonth(), tempDate);
}

function getDaysInSelectedYearMonth()
{
  var tempDate;
  var currentMonth;
  if (document.all) {
    currentMonth = document.all.s1.selectedIndex;
    currentFullYear = document.all.s2.selectedIndex + 1900;
  }
  else {
    currentMonth = document.CalendarForm.s1.selectedIndex;
    currentFullYear = document.CalendarForm.s2.selectedIndex + 1900;
  }
  tempDate = new Date(currentFullYear, currentMonth);
  return getDaysInMonth(tempDate.getMonth(), tempDate);
}

function showCalendarUltra()
{
  var currentFullYear = getCurrentFullYear();
  if (document.all) {
    document.all.s2.selectedIndex=currentFullYear-1900;
    document.all.s1.selectedIndex=currentDate.getMonth();
  } else {
    document.CalendarForm.s2.selectedIndex=currentFullYear-1900;
    document.CalendarForm.s1.selectedIndex=currentDate.getMonth();
  }
  var currentDateDaysInMonth=getDaysInMonth(currentDate.getMonth(), currentDate);
  var daysInPreviousMonth=getDaysInMonth(currentDate.getMonth()==0?11:currentDate.getMonth()-1, currentDate);
  var firstDayDate = new Date(currentFullYear, currentDate.getMonth(), 1);
  var firstDay = firstDayDate.getDay();
  for(var x=1; x<=firstDay; x++)
  {
    var id;
    if (document.all) {
      id=document.all("b"+x);
      id.style.backgroundColor="#C0C0C0";
      id.style.color="white";
      id.value=daysInPreviousMonth-firstDay+x;
    } else {
      id=document.CalendarForm.elements["b"+x];
      id.value="";
    }
  }
  var currentDateDay = currentDate.getDate();
  for(var x=1; x<=currentDateDaysInMonth; x++)
  {
    var id;
    if (document.all) {
      id=document.all("b"+(x+firstDay));
      if (currentDateDay==x) {
        id.style.backgroundColor="#0066CC";
        id.style.color="black";
      }
      else {
        id.style.backgroundColor="#C0C0C0";
        id.style.color="black";
      }
    } else {
      id=document.CalendarForm.elements["b"+(x+firstDay)];
    }
    id.value=x;
  }
  var counter = 1
  for(var x=currentDateDaysInMonth+1; x<=42-firstDay; x++)
  {
    var id;
    if (document.all) {
      id=document.all("b"+(x+firstDay));
      id.style.backgroundColor="#C0C0C0";
      id.style.color="white";
      id.value=counter;
      counter=counter+1;
    } else {
      id=document.CalendarForm.elements["b"+(x+firstDay)];
      id.value="";
    }
  }
}

function enter(month, day, year) {
   if (top.opener.fieldAux1 == true) {  // DatePicker
       var auxYear = year + "";
       auxYear = auxYear.substring(2,4);
	// Logic for Date
<%
	if ( currUser.getE01DTF().equals("MDY") ) {
%>
   		top.opener.fieldDate1.value = month;
   		top.opener.fieldDate2.value = day;
   		top.opener.fieldDate3.value = auxYear;
<%
	}
	else if ( currUser.getE01DTF().equals("DMY") ) {
%>
   		top.opener.fieldDate2.value = month;
   		top.opener.fieldDate1.value = day;
   		top.opener.fieldDate3.value = auxYear;
<%
	}
	else {
%>
   		top.opener.fieldDate2.value = month;
   		top.opener.fieldDate3.value = day;
   		top.opener.fieldDate1.value = auxYear;
<%
	}
%>
   }
   else {					  // DOBPicker
   	top.opener.fieldDate1.value = month;
   	top.opener.fieldDate2.value = day;
   	top.opener.fieldDate3.value = year;
   }
   top.close();
}

function isValidDate()
{
    var validUserDate=0;
    if (top.opener.fieldAux1 == true) {  // DatePicker
    	// Logic for Date
<%
	if ( currUser.getE01DTF().equals("MDY") ) {
%>
    		userMonth=top.opener.fieldDate1.value;
    		userDay=top.opener.fieldDate2.value;
    		userYear=top.opener.fieldDate3.value;
    		userYear = padZeroIfSingleDigit(userYear)
<%
	}
	else if ( currUser.getE01DTF().equals("DMY") ) {
%>
    		userMonth=top.opener.fieldDate2.value;
    		userDay=top.opener.fieldDate1.value;
    		userYear=top.opener.fieldDate3.value;
    		userYear = padZeroIfSingleDigit(userYear)
<%
	}
	else {
%>
    		userMonth=top.opener.fieldDate2.value;
    		userDay=top.opener.fieldDate3.value;
    		userYear=top.opener.fieldDate1.value;
    		userYear = padZeroIfSingleDigit(userYear)
<%
	}
%>
    }
    else {					  // DOBPicker
    	userMonth=top.opener.fieldDate1.value;
    	userDay=top.opener.fieldDate2.value;
    	userYear=top.opener.fieldDate3.value;
    	userYear = padZeroIfSingleDigit(userYear)
    }
    if ((userYear.toString().length==2)) {
      if (userYear < 51) {
	 userYear = "20" + userYear
      }
      else {   
	 userYear = "19" + userYear
      }
    }
    
    if (isNaN(userMonth) || isNaN(userDay) || isNaN(userYear)) {
      validUserDate=1;
    }
    else if (userMonth=='' || userYear=='' || userDay=='') {
      validUserDate=1;
    }
    else if ((userMonth<1 || userMonth>12) || (userYear<1900) || (userYear>2099)) {
      validUserDate=1;
    }
    else {
      userDate = new Date(userYear, userMonth);
      dpDay=getDaysInMonth(userMonth-1, userDate);
      if (userDay>dpDay || userDay<0) {
        validUserDate=1;
      }
    }
    return validUserDate;
}

function init()
{
  if (isValidDate()==0) { //valid user date, update currentDate
    currentDate = new Date(userYear, userMonth-1, userDay);
  }
  showCalendarUltra();
}


init();

</SCRIPT>
</FORM>

</BODY>
</HTML>
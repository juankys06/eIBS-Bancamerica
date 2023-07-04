<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Visor de Firmas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

</head>
<body>

<%
int opt=0;
int len=0;
String ACCNUM="";
  try {opt  = Integer.parseInt(request.getParameter("opt")); } catch (Exception e) {};
  try {ACCNUM  = request.getParameter("ACCNUM"); } catch (Exception e) {};
  len = ACCNUM.trim().length();
  if (len < 10)
  {
  switch(len)
  {
  case 9:
   ACCNUM = "0"+ACCNUM.trim();
  break;
  case 8:
  ACCNUM = "00"+ACCNUM.trim();
  break;
  case 7:
   ACCNUM = "000"+ACCNUM.trim();
  break;
  case 6:
   ACCNUM = "0000"+ACCNUM.trim();
  break;
  case 5:
   ACCNUM = "00000"+ACCNUM.trim();
  break;
   case 4:
   ACCNUM = "000000"+ACCNUM.trim();
  break; 
   case 3:
   ACCNUM = "0000000"+ACCNUM.trim();
  break;
  case 2:
   ACCNUM = "00000000"+ACCNUM.trim();
  break;
 case 1:
   ACCNUM = "000000000"+ACCNUM.trim();
  break;  
  }
  }
if (opt==5)
{%>

<%
}

%>
</body>
</html>


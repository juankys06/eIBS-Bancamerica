
//     JAVASCRIPT INCLUDE FILE - (c) J R Stockton  >= 2002-05-09
//             http://www.merlyn.demon.co.uk/include1.js
//       Routines may be copied, but URL must not be linked to.


var spqr = 'Press "OK", read it, then press "Back"'


// DynWrite(target, text) (Jim Ley) works on controls, after page load :
// it is a computed function :

// Classify browser :
var nCheck = 0
DocDom = (document.getElementById?true:false) // NS6 also IE5
                                        if (DocDom) nCheck++
DocLay = (document.layers?true:false) ; if (DocLay) nCheck++  // NS4
DocAll = (document.all?true:false) ;    if (DocAll) nCheck++  // IE4

function ReportDocVars() {
// if (nCheck!=1) alert('Browser classification problem!  nCheck = ' +
//  String(nCheck) + '\nPlease let me know how the page works and what' +
//  ' the\nbrowser is; and, if possible, what needs to be done about it.')
 if (nCheck==0) document.write(' None of DocAll, DocLay, DocDom is set;',
  ' Dynamic Write will fail.'.italics(), '<p>')
 if (DocAll) document.write(' DocAll is set. ')
 if (DocLay) document.write(' DocLay is set. ')
 if (DocDom) document.write(' DocDom is set. ')
 }

function TableDocVars() {
 document.writeln('<br><br><table summary="Browser class info"',
  ' bgcolor=blue align=center cellpadding=10 border=4>',
  '<tr><th bgcolor=wheat>According to the displaying computer :<\/th>',
  '<\/tr><tr><td bgcolor=gainsboro align=center>',
  'Your browser gives<br>',
  '<b>DocAll = ', DocAll, ' ; DocDom = ', DocDom,
  ' ; DocLay = ', DocLay, '.<\/b><br>',
  'Check : ', nCheck, ' browser classification(s) set.',
  '<\/td><\/tr><\/table>')
 }

// Define Function DynWrite(Where, What) to suit browser :
DocStr=''
if (DocLay) DocStr="return document.layers['NS_'+id]"
if (DocAll) DocStr="return document.all[id]"
if (DocDom) DocStr="return document.getElementById(id)"
GetRef=new Function("id", DocStr)

// DocLay = true ; DocAll = DocDom = false // Simulate NS4.7
DynWarn = 0

if (DocStr=='') { DynWrite=new Function("return false") } else {
 if (DocAll || DocDom) {
  DynWrite=new Function("id", "S", "GetRef(id).innerHTML=S; return true")
  }
// if (DocLay) DynWrite=new Function("id", "S", "var x=GetRef(id).document;"+
//  "x.open('text/html'); x.write(S); x.close(); return true")
 if (DocLay) DynWrite=new Function(
  "if (0==DynWarn++)"+
  " alert('DynWrite not supported in \".layers\" browsers');"+
  "return false")
 }

function NoDynLay() {
 if (DocLay) document.writeln(
  '<p><i>Dynamic Writing in a ".layers" browser ',
  'such as yours is difficult and I cannot now test it.  Therefore, ',
  'I don\'t now attempt it. An alert will be given in the first use in ',
  'each load/reload of a page.<\/i>') }

// DynWrite() end.



// General Utilities :

function LZ(x) {return(x<0||x>9?"":"0")+x}

function LZZ(x) {return(x<0||x>99?x:"0"+LZ(x))}

if (!String.substr) {
 String.prototype.substr =
  new Function("J", "K", "return this.substring(J, J+K)") }

function TrimS() { // Grant Wagner
 return (this.toString() ? this.toString().replace(/\s+$|^\s*/g, "") : "") }
String.prototype.trim = TrimS

function Sign(y) {return(y>0?'+':y<0?'-':' ')}

function Prepend(Q, L, c) { var S = Q+'' // ??
 // if (!c) var c = ' '
 if (c.length>0) while (S.length<L) { S = c+S } ;
 return S }
 
function StrU(X, M, N) { // X>=0.0
 var T, S=new String(Math.round(X*Number("1e"+N)))
// if (S.search && S.search(/\D/)!=-1) { return ''+X }
 if (/\D/.test(S)) { return ''+X }
 with (new String(Prepend(S, M+N, '0')))
  return substring(0, T=(length-N)) + '.' + substring(T) }

function StrT(X, M, N) { return Prepend(StrU(X, 1, N), M+N+2, ' ') }

function StrS(X, M, N) { return Sign(X)+StrU(Math.abs(X), M, N) }

function StrW(X, M, N) { return Prepend(StrS(X, 1, N), M+N+2, ' ') }

Number.prototype.toFixed = new Function('n', 'return StrS(this,1,n)') // JL

function SigFigExp(X, N) { // N<22
   if (X==0) return SigFigExp(1, N).replace(/\+1/, ' 0')
   var p = Math.floor(Math.log(Math.abs(X))/Math.LN10)
   if (!isFinite(p)) return X
   return (X>0?'+':'') +
    String(Math.round(X*Math.pow(10, N-p-1))).replace(/(\d)/, "$1.") +
    (p>=0?"e+":"e-") + LZ(Math.abs(p)) } // All OK?


function Div(a, b) { return Math.floor(a/b) }

function Mod(a, b) { return a-Math.floor(a/b)*b }

function Random(N) { return Math.floor(N*(Math.random()%1)) } // as Pascal

var span = Math.pow(2, 32), RandSeed = 0
function SeedRand() { // undertested; different sequence to Math.random()
 return (RandSeed = (134775813*RandSeed + 1) % span )/span }

function Deal(N) { // Numbers 0..(N-1) in random order
 var J, K, A = new Array(N) ; A[0] = 0
 for (J=1; J<N; J++) { K = Random(J+1) ; A[J] = A[K] ; A[K] = J }
 return A }


// end.


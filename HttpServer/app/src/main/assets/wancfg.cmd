
<html><head>
<link rel=stylesheet href='stylemain.css' type='text/css'>
<link rel=stylesheet href='colors.css' type='text/css'>
<meta HTTP-EQUIV='Pragma' CONTENT='no-cache'>
<script language='javascript' src='portName.js'></script>
<script language='javascript'>
<!-- hide

var brdId = '96318REF_P300';
var intfDisp = '';
var brdIntf = '';
var lanCur = 'eth1';
function frmLoad(){ 
   document.getElementById('oprMode1').checked = true;
   oprClick();
}
function oprClick(){
   if (document.getElementById('oprMode1').checked == true){
      document.getElementById('tbAtmAdd').style.display = 'block';
      document.getElementById('tbEthAdd').style.display = 'none';
      document.getElementById('tbEthAddAlert').style.display = 'none';
   }else if (document.getElementById('oprMode2').checked == true){
      document.getElementById('tbAtmAdd').style.display = 'none';
      if(lanCur == 'eth0'){
         document.getElementById('tbEthAddAlert').style.display = 'block';
         document.getElementById('tbEthAdd').style.display = 'none';
      }else {
         document.getElementById('tbEthAddAlert').style.display = 'none';
         document.getElementById('tbEthAdd').style.display = 'block';
      }
   }
}

function addWanAtmClick() {
   var loc = 'wanatmconf.cgi?serviceId=0&ntwkPrtcl=0';

   loc += '&sessionKey=1945511898';
   var code = 'location="' + loc + '"';
   eval(code);
}

function addWanEthClick(ethCur) {
   var loc = 'wanethconf.cgi?serviceId=0';

   loc += '&sessionKey=1945511898';
   loc += '&enblDhcpClnt='+ethCur;
   var code = 'location="' + loc + '"';
   eval(code);
}

function editClick(ifName, ntwkPrtcl) {
   var loc = 'wanL3Edit.cmd?serviceId=1&wanIfName=' + ifName + '&ntwkPrtcl=' + ntwkPrtcl;

   var code = 'location="' + loc + '"';
   eval(code);
}

function removeOneClick(l2IfName, ifName) {
   var lstAtm = '';
   var lstEth = '';
   if (l2IfName.substr(0,3)=='lan'){
      var lstemp = l2IfName;
       if (lstemp=='lan1'){
           lstemp = 'eth0';
      }else if (lstemp=='lan2'){
           lstemp = 'eth1';
      }else if (lstemp=='lan3'){
           lstemp = 'eth2';
      }else if (lstemp=='lan4'){
           lstemp = 'eth3';
      }
      lstEth += lstemp+', ';
   }
   else if (l2IfName == 'N/A'){
      lstEth = '';
      lstAtm = '';
   }
   else 
      lstAtm += l2IfName+', ';
   var loc = 'wancfg.cmd?action=remove&rmLstEth=' + lstEth + '&rmLstAtm=' + lstAtm + '&rmLst=' + ifName;

   var code = 'location="' + loc + '"';
   eval(code);
}

function removeClick(rml) {
   var lst = '';
   var lstEth = '';
   var lstWan = '';
   var lstAtm = '';
   var lstAE = '';
   if (rml.length > 0)
      for (i = 0; i < rml.length; i++) {
         if ( rml[i].checked == true )
            lst += rml[i].value + '|';
      }
   else if ( rml.checked == true )
      lst = rml.value + '|';
 var lsts = lst.split('|');
 for(i=0; i<lsts.length-1; i++){
    var x=lsts[i].split('/');
    lstAE += x[1]+'|';
    lstWan += x[0]+', ';
 }
 var lstAEs = lstAE.split('|');
 for(i=0; i<lstAEs.length-1; i++){
   if (lstAEs[i].substr(0,3)=='lan'){
      var lstemp = lstAEs[i];
       if (lstemp=='lan1'){
           lstemp = 'eth0';
      }else if (lstemp=='lan2'){
           lstemp = 'eth1';
      }else if (lstemp=='lan3'){
           lstemp = 'eth2';
      }else if (lstemp=='lan4'){
           lstemp = 'eth3';
      }
      lstEth += lstemp+', ';
   }
   else 
      lstAtm += lstAEs[i]+', ';
}


   var loc = 'wancfg.cmd?action=remove&rmLstEth=' + lstEth + '&rmLstAtm=' + lstAtm + '&rmLst=' + lstWan;

   loc += '&sessionKey=1945511898';
   var code = 'location="' + loc + '"';
   eval(code);
}

// done hiding -->
</script>
<title></title>
</head>
<body onload='frmLoad()'>
<blockquote>
<form>
<table border='1' cellpadding='4' cellspacing='0' style='display:none'>
   <tr align='center'>
      <td class='hd'>Interface</td>
      <td class='hd'>Vpi</td>
      <td class='hd'>Vci</td>
      <td class='hd'>DSL Latency</td>
      <td class='hd'>Category</td>
      <td class='hd'>Peak Cell Rate(cells/s)</td>
      <td class='hd'>Sustainable Cell Rate(cells/s)</td>
      <td class='hd'>Max Burst Size(bytes)</td>
      <td class='hd'>Min Cell Rate(cells/s)</td>
      <td class='hd'>Link Type</td>
      <td class='hd'>Conn Mode</td>
      <td class='hd'>IP QoS</td>
      <td class='hd'>MPAAL Prec/Alg/Wght</td>
      <td class='hd'>Remove</td>
   </tr>
</table>
<table border='1' cellpadding='4' cellspacing='0' style='display:none'>
</table>
<table><tr><td class='h1'>Wide Area Network (WAN) Service Setup</td></tr></table><br><br>
Choose Add, Remove or Edit to configure a WAN service over a selected interface.<br><br>
<table border='1' cellpadding='4' cellspacing='0'>
   <tr align='center'>
      <td class='hd'>L2 Interface Name</td>
      <td class='hd'>Operation Mode</td>
      <td class='hd'>Interface</td>
      <td class='hd'>Description</td>
      <td class='hd'>Type</td>
      <td class='hd'>Vlan8021p</td>
      <td class='hd'>VlanMuxId</td>
      <td class='hd'>Igmp</td>
      <td class='hd'>NAT</td>
      <td class='hd'>Firewall</td>
      <td class='hd'>IPv6</td>
      <td class='hd'>Mld</td>
      <td class='hd'>Edit</td>
      <td class='hd'>Remove</td>
   </tr>
</table><br><br>
<table cellSpacing='4' cellPadding='0' border='0' width='550'>
<tr>
  <td width='350'><b>Select Operation Mode to add new WAN Service:</b></td>
  <td><input type='radio' name='operationMode' id='oprMode1' onClick='oprClick()'><b>ADSL Mode<b></td>
</tr>
<tr>
  <th></th>
  <td><input type='radio' name='operationMode' id='oprMode2' onClick='oprClick()'><b>Access Point Mode</b></td>
</tr>
</table>
<table cellSpacing='4' cellPadding='0' border='0' width='550' id='tbAtmAdd'>
<tr>
  <th width='350'></th>
  <td>
<input type='button' onClick='addWanAtmClick()' value='Add'>
</td></tr>
</table>
<div id='tbEthAddAlert'>
<table cellSpacing='4' cellPadding='0' border='0' width='550'>
<tr>
<td><b><i>Port "lan1" will be used as WAN port in Access Point Mode. This port is busy, please unplug Ethernet cable from port "lan1" on the iGate AW300N and refresh your web browser to continue, then plug the cable to port "lan1" upon finished the configuration</i></b></td>
</tr>
</table>
<table cellSpacing='4' cellPadding='0' border='0' width='550'>
<tr>
  <th width='350'></th>
  <td>
<input type='button' onClick='addWanEthClick()' value='Add' disabled='1'>
</td></tr>
</table>
</div>
<table cellSpacing='4' cellPadding='0' border='0' width='550' id='tbEthAdd'>
<tr>
  <th width='350'></th>
  <td>
<input type='button' onClick='addWanEthClick(1)' value='Add'>
</td></tr>
</table>
</form>
</blockquote>
</body>
</html>

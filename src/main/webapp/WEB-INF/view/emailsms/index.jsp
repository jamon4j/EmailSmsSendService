<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>金山云信息发送平台</title>
<link rel="shortcut icon" href="/html/icon/ksyun.ico" />
<link href="/css/admin.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/admin.js"></script>

<style type="text/css">
    body {
    font: 16 Arial, Helvetica, sans-serif;
    color: #222;
    background: #fff;
    }
</style>

</head>
<body>

<div id="header">
	<h1>金山云信息发送平台</h1>
	<p>
	<span>
	<font color="black"><b>金山云信息发送平台</b></font>
	</span>
	</p>
</div>

<div id="side-menu" style="height：790px">
<h2 class="first">信息发送目标条件选项</h2>
<form class="query_form" name="queryForm" method="post" action="/g/emailsms/sendEmailSms" >

发送方式:
<select id="sendMethod" name="sendMethod" onchange="setChange()">
  <option id="SMS" value="sms">短信</option>	
  <option id="Email" value="email">email</option>
  <option id="SMSAndEmail" value="smsAndEmail">短信和email</option>
</select>

</br><br/>
区域选择：
<label><input id="RegionSelectAll" type="checkbox" value="" onclick="selectAll()"/>全选 </label> 
<label><input id="Bj" name="region_checkbox" type="checkbox" value="beijing" />北京 </label> 
<label><input id="Sh" name="region_checkbox" type="checkbox" value="shanghai" />上海 </label> 
<label><input id="Yz" name="region_checkbox" type="checkbox" value="beijing1" />亦庄 </label> 

</br><br/>
产品选择：
<label><input id="ProductSelectAll" type="checkbox" value="" onclick="selectAll()"/>全选 </label> 
<label><input id="RDS" name="product_checkbox" type="checkbox" value="20,21" />RDS </label> 
<label><input id="KVM" name="product_checkbox" type="checkbox" value="1,7,8" />KVM </label> 
<label><input id="LBS" name="product_checkbox" type="checkbox" value="5" />LBS </label>
<label><input id="LBS" name="product_checkbox" type="checkbox" value="3" />EBS </label>  
<label><input id="EIP" name="product_checkbox" type="checkbox" value="9" />EIP </label> 
</br>

<p>短信正文：</p>
  <textarea id="sms_content" name="sms_content" cols=40 rows=15"></textarea>
</br></br>
 邮件标题： <input id="email_subject" name="email_subject" type="text" size=30 readonly=true/>
<p>邮件正文：</p>
  <textarea id="email_content" name="email_content" cols=40 rows=15 readonly=true></textarea>
<p><input type="submit" name="submit" value="发送" onclick="return checkInputText()"><span id="usrErr"></span></p>

</form>
</div>

<div id="mainDiv" class="main-frame" style="height:750px">

</div>
</body>
</html>

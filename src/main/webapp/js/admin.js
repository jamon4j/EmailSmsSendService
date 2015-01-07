
function setChange()
{
	var sendMethod = document.queryForm.sendMethod.value;
    if(sendMethod == "sms")
    {
    	var email_subject = document.getElementById("email_subject");
    	document.getElementById("email_subject").readOnly=true;
    	var email_content = document.getElementById("email_content");
    	document.getElementById("email_content").readOnly=true;
    	var sms_content = document.getElementById("sms_content");
    	document.getElementById("sms_content").readOnly=false;
    }
    
    if(sendMethod == "email")
    {
    	var email_subject = document.getElementById("email_subject");
    	document.getElementById("email_subject").readOnly=false;
    	var email_content = document.getElementById("email_content");
    	document.getElementById("email_content").readOnly=false;
    	var sms_content = document.getElementById("sms_content");
    	document.getElementById("sms_content").readOnly=true;
    }
    
    if(sendMethod == "smsAndEmail")
    {
    	var email_subject = document.getElementById("email_subject");
    	document.getElementById("email_subject").readOnly=false;
    	var email_content = document.getElementById("email_content");
    	document.getElementById("email_content").readOnly=false;
    	var sms_content = document.getElementById("sms_content");
    	document.getElementById("sms_content").readOnly=false;
    }
}



//复选框全选与反选
function selectAll()
{
	var RegionSelectAll=document.getElementById("RegionSelectAll");  
	var ProductSelectAll=document.getElementById("ProductSelectAll"); 
    var region_checkbox=document.getElementsByName("region_checkbox"); 
    var product_checkbox=document.getElementsByName("product_checkbox"); 
    
    if(RegionSelectAll.checked==true){  
        for(var i=0;i<region_checkbox.length;i++){  
        	region_checkbox[i].checked=true;  
        }  
    }else{  
        for(var i=0;i<region_checkbox.length;i++){  
        	region_checkbox[i].checked=false;  
        }  
    }
    
    if(ProductSelectAll.checked==true){  
        for(var i=0;i<product_checkbox.length;i++){  
        	product_checkbox[i].checked=true;  
        }  
    }else{  
        for(var i=0;i<product_checkbox.length;i++){  
        	product_checkbox[i].checked=false;  
        }  
    }
}

//验证表单项目是否为空
function checkInputText()
{    
		if(!checkRegion()) return false;
		if(!checkProduct()) return false;
	    var sendMethod = document.queryForm.sendMethod.value;
	    if(sendMethod == "sms")
	    {
	    	if(!checkSms()) return false;
	    }
	    
	    if(sendMethod == "email")
	    {
	    	if(!checkEmail()) return false;
	    }
	    
	    if(sendMethod == "smsAndEmail")
	    {
	    	if(!checkSms()||!checkEmail()) return false;
	    }
	    alert("信息已发送！");
        return true;
}

function checkRegion()
{
	 var region_checkbox=document.getElementsByName("region_checkbox"); 
	 var isNotEmpty = false;
	 for(var i=0;i<region_checkbox.length;i++){  
     	if(region_checkbox[i].checked==true)
 		{
     		isNotEmpty = true;
 		}
     }
	 if(!isNotEmpty) document.getElementById("usrErr").innerHTML="<font color='red'>请选择区域</font>";
	 return isNotEmpty;
}

function checkProduct()
{
	var product_checkbox=document.getElementsByName("product_checkbox"); 
	var isNotEmpty = false;
	 for(var i=0;i<product_checkbox.length;i++){  
    	if(product_checkbox[i].checked==true)
		{
    		isNotEmpty = true;
		}
    }
	 if(!isNotEmpty) document.getElementById("usrErr").innerHTML="<font color='red'>请选择产品</font>";
	 return isNotEmpty;
}

function checkSms()
{
	var sms_content = document.getElementById('sms_content').value;
    
    if(sms_content.length==0)
    {   
            document.getElementById("usrErr").innerHTML="<font color='red'>短信信息不能为空</font>";
            return false;
    }
    return true;
}

function checkEmail()
{
	var email_subject = document.getElementById('email_subject').value;
	var email_content = document.getElementById('email_content').value;
    
    if(email_subject.length==0)
    {   
            document.getElementById("usrErr").innerHTML="<font color='red'>邮件标题不能为空</font>";
            return false;
    }
    if(email_content.length==0)
    {   
            document.getElementById("usrErr").innerHTML="<font color='red'>邮件正文不能为空</font>";
            return false;
    }
    return true;
}
                            
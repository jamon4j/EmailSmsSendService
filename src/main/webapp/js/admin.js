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

function setChange()
{
	var region = document.queryForm.region.value;
	var query_method = document.queryForm.method.value;
	
	//若地区为上海，则只能使用email查询
	if(region == "RegionOne")
	{	
		document.getElementById("method").options[0].disabled=true;      
		document.getElementById("method").options[1].selected=true;
		document.getElementById("ip_text").innerHTML = "输入查询的email：";
	}
	
	//若地区为北京，则能使用email查询或eip查询
	else if(region == "SHRegionOne")
	{
		document.getElementById("method").options[0].disabled=false;    
		if(query_method == "eip")
		{
			document.getElementById("ip_text").innerHTML = "输入查询的eip：";
		}
		else if(query_method == "email")
		{
			document.getElementById("ip_text").innerHTML = "输入查询的email：";
		}
	}
}

//验证文本输入框的合法性
function checkInputText()
{    
        var ssn = document.getElementById('sms_content').value;
        var count = ssn.split("\n").length;

        var query_method = document.queryForm.method.value;
        if(ssn.length==0)
        {   
                document.getElementById("usrErr").innerHTML="<font color='red'>短信信息不能为空</font>";
                return false;
        }   

        var email = document.getElementById('email').value;
        if(!validate_email(email))
        {   
           alert("您接收查询结果的email格式不正确，请重新输入");
           return false;
        }  
        
        if(query_method=="email")
        {   
           if(count>1)
           {   
            alert("为避免超时，每次只限查询一个email");
            return false;
           }   
           var email=ssn.split("\n");
           if(validate_email(email[0])) 
           {   
               return true;
           }   
           else 
           {   
               alert("输入的email格式不正确，请重新输入");
               return false;
           }   
        }   

        document.getElementById("usrErr").innerHTML="";
        return true;
}

//正则表达式验证email格式
function validate_email(email)
{
		alert("email:"+email);
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$/;
        if(reg.test(email))
        {   
            return true;
        }   
        else
        {   
            return false;
        }             
}                               
function check(){  //判断用户名或密码是否为空
      var username=document.getElementById("username"); //获得username
      var password=document.getElementById("password"); //获得password
      if(username.value.length==0){
         alert("用户名不能为空")  
         return false;
      }else if(password.value.length==0){
         alert("密码不能为空")  
         return false;
      }else return true;
}
      
    
   
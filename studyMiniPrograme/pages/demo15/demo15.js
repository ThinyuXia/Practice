// pages/demo15/demo15.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        gender:""
    },
    
    handleChange(e){
       // 1 获取单选框中的值
       let gender = e.detail.value;
       // 2 把值赋给data中的数据
       this.setData({
           //gerder : gender 等价下面这种写法
           gender
       })
    }
  
})
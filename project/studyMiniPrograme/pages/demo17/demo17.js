// pages/demo17/demo17.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        tabs:[
            {
                id:0,
                name:"首页",
                isActive:true
            },
            {
                id:1,
                name:"原创",
                isActive:false
            },
            {
                id:2,
                name:"分类",
                isActive:false
            },
            {
                id:3,
                name:"关于",
                isActive:false
            }
        ]
    },

   // 自定义事件接收子组件传递的数据的
   handleItemChange(e){
        const {index} = e.detail;
        let {tabs} = this.data;

        // [].forEach 遍历数组
        // 遍历数组时v被修改，会导致原数组被修改
        tabs.forEach((v,i)=>i===index ? v.isActive = true : v.isActive = false)
        this.setData({
        tabs
        })
   }
})
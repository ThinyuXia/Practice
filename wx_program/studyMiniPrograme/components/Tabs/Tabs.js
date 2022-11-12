// components/Tabs/Tabs.js
Component({
    /**
     * 存放从父组件接收的数据
     */
    properties: {
        //要接收的数据名称
        tabs:{
            type:Array,
            value:[]
        }
    },

    /**
     * 组件的初始数据
     */
    data: {
    },

    /**
     1 页面.js 文件中存放事件的回调函数的时候 存放在data同层级下！！！
     2 组件.js 文件中存放事件的回调函数的时候 必须要存放在methods中
     */
    methods: {
        handleItemTap(e){
            /*
            1 绑定点击事件 需要在methods中绑定
            2 获取被点击的索引
            3 获取原数组
            4 对数组循环
                1 给每一个循环项选中属性改为false
                2 给当前的索引的项添加激活选中效果
            5 点击事件触发的时候
                触发父组件中的自定义事件同时传递数据给父组件
                this.triggerEvent("父组件自定义事件的名称",要传递的参数)
            */
           const {index} = e.currentTarget.dataset;
                  //5
                //  |    
           this.triggerEvent("itemChange",{index})
           
        //    let {tabs} = this.data;

        //    // [].forEach 遍历数组
        //    // 遍历数组时v被修改，会导致原数组被修改
        //    tabs.forEach((v,i)=>i===index ? v.isActive = true : v.isActive = false)
        //    this.setData({
        //     tabs
        //    })
            
        }

    }
})

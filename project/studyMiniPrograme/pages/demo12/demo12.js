// pages/demo12/demo12.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        // html:'<div data-spm="201866" data-kpm="mytao" data-name="mytao" class="tbh-mytao"><div class="mytao-content"><a href="https://shoucang.taobao.com/item_collect.htm"><span class="tb-ifont mytao-icon"></span><p>收藏的宝贝</p></a></div><div class="mytao-content"><a href="https://favorite.taobao.com/list_bought_shops_n.htm"><span class="tb-ifont mytao-icon"></span><p>买过的店铺</p></a></div><div class="mytao-content"><a href="https://shoucang.taobao.com/shop_collect_list_n.htm"><span class="tb-ifont mytao-icon"></span><p>收藏的店铺</p></a></div><div class="mytao-content newlast"><a href="https://www.taobao.com/markets/footmark/tbfoot"><span class="tb-ifont mytao-icon"></span><p>我的足迹</p></a></div></div>'
        html:[
            {
                //1 name属性来指定div标签
                name:"div",
                //2 标签上有哪些属性
                attrs:{
                    //标签上的属性 class style
                    class:"my_div",
                    style:"color:red;"
                },
                //3 子节点 children 要接收的数据类型 nodes第二种渲染方式的数据类型一致
                children:[
                    {
                        name:"p",
                        attrs:{},
                        // 放文本
                        children:[{
                            type:"text",
                            text:"hello"
                        }]
                    }
                ]

            }
        ]
    },
})
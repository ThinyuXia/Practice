// pages/demo18/demo18.js
Page({

    /**
     * 页面的初始数据
     */
    data: {

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        console.log("onLoad");
        // onLoad发送异步请求来初始化页面数据
    },

 
    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
        console.log("onShow");
    },

       /**
     * 生命周期函数--监听页面初次渲染完成
     */
        onReady: function () {
            console.log("onReady");  
        },
    


    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {
        console.log("onHide");
    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {
        console.log("onUnload");
    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {
        console.log("onPullDownRefresh");
        // 页面的数据 或者效果 重新刷新获取
    },

    /**
     * 页面上拉触底事件的处理函数
     * 需要让页面出现上下滚动才行
     */
    onReachBottom: function () {
        // 场景：上拉加载下一页数据
        console.log("onReachBottom");

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {
        console.log("onShareAppMessage");
    },

    /**
     * 页面滚动就会触发
     */
    onPageScroll(){
        console.log("onPageScroll");
    },

    /**
     * 页面尺寸发生改变的时候触发
     * 横屏竖屏切换
     */
    onResize(){
        console.log("onResize");
    },

    /**
     * 1 必须要求当前页面时tabbar页面
     * 2 点击自己的tab item时触发
     */
    onTabItemTap(){
        console.log("onTabItemTap");
    }
})
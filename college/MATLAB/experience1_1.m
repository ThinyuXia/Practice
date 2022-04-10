x = pi / -2:0.05:pi / 2;
y = tan(x);
plot(x,y)
grid on % 绘图连续
axis([pi / -2,pi / 2,-20,20]) % 对坐标轴进行缩放操作
x = -100 * pi : pi / 2: 100 * pi;
y = x .^ 3 + sin(x);   %对x向量中每个数取立方
plot(x,y);

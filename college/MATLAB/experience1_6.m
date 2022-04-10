x = -3 : 0.01 : 3;
y = -3 : 0.01 : 3
[X,Y] = meshgrid(x,y);
Z = Y .^ 2 / 3 - X .^ 2 * 9 / 4;
mesh(X,Y,Z);
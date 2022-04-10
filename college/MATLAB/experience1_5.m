x = -3 : 0.01 : 3;
y = -3 : 0.01 : 3;
[X,Y] = meshgrid(x,y);
Z = X .^ 2 / 4 + Y .^ 2 / 9;
mesh(X,Y,Z);
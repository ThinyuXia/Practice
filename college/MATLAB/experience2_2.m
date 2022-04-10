fun = @(x)3*x(1)^2 + x(2)^2 - 12*x(1) - 8*x(2);
[x,fval] = fminunc(fun,[1 1]);
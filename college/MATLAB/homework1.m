c = [1,2];
A = [2,3;-1,-1];
b = [6,-4];
Aeq = [1,-1];
beq = 3;
vlb = [0,-inf];
vub = [inf,inf];
[x,fval] = linprog(-c,A,b,Aeq,beq,vlb,vub);

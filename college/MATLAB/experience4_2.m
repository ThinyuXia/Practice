x = 20 : 5 : 65
X = [ones(10,1) x']
Y = [13.2 15.1 16.4 17.1 17.9 18.7 19.6 21.2 22.5 24.3]'

[b,bint,r,rint,stats]=regress(Y,X)
c = x'
[p,S] = polyfit(c,Y,1)
[A,DELTA] = polyconf(p,42,S,0.05)
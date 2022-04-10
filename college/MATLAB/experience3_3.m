
T = [68 68 87 87 106 106 140 140];
P = [9.7981 13.324 9.0078 13.355 9.7918 14.277 9.6563 12.463];
K = [0.0848 0.0897 0.0762 0.0807 0.0696 0.0753 0.0611 0.0651];
Ti = 99;
Pi = 10.3;
% 最邻近插值
K1 = griddata(T,P,K,Ti,Pi,'nearest');
% 双线性插值
K2 = griddata(T,P,K,Ti,Pi,'linear');
% 双三次插值
K3 = griddata(T,P,K,Ti,Pi,'cubic');
% v4
K4 = griddata(T,P,K,Ti,Pi,'v4');
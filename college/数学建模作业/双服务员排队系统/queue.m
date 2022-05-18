%设第i个人到达的时间为a（i）,开始接受服务的时刻为b（i），离开服务系统的时刻为c（i）
%假设这是一个双服务员排队系统，总共模拟n个人
n=1000;%模拟1000个人
a=zeros(1,n); %顾客到达时间
b=zeros(1,n); %顾客排队等待时间
c=zeros(1,n); %顾客接受服务后离开时间
dt = poissrnd(3,1,n);    %模拟人数到达的时间间隔，到达率体现在这里
st = exprnd(1,1,n);    %每个人的服务时间，参数自己定,服务率体现在这里
a(1)=0;
for i=2 :n
    a(i)=a(1,i-1)+dt(i-1);%计算每个人到达的时刻
end
b(1)=0;
b(2)=0;
c(1)=b(1)+st(1);
c(2)=a(2) + b(2)+st(2);
for i=3 :n
    if a(i)<c(i-1) && a(i) < c(i-2) %说明下一个人提前到了并且他的前两位顾客都在接受服务，说明没有空闲的服务员
        if c(i-1) <= c(i-2)
           b(i)=c(i-1); %要等到前一个人出来才能接受服务
        else
           b(i)=c(i-2);
        end
    else
        b(i)=a(i);%直接接受服务
    end
     c(i) = b(i) + st(i);%计算第i个人出来的时间
end

%计算每个人在系统中消耗的时间
z=zeros(1,n);
for i=1:n
    z(i)=c(i)-a(i);
end
%计算每个人的排队时间
q=zeros(1,n);
for i=1:n
q(i)=b(i)-a(i);
end


%每个人的进入系统时间和离开系统时间图
figure
plot(1:n,c); hold on; plot(1:n,a); legend('离开时刻','到达时刻');xlabel('人');ylabel('时刻')
 title('全部模拟人数的进入系统与离开系统的时刻图')  
%每个人在系统中逗留的时间图
 figure 
plot(1:n,z);title('每个模拟乘客在接受服务时逗留的时间图');xlabel('人');ylabel('时间');
%每个乘客排队时间图
figure
plot(1:n,q);title('每个模拟乘客在接受服务时排队时间图');xlabel('人');ylabel('时间');


  
    
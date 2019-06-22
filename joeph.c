#include  <stdio.h>
#include  <stdlib.h>
/*--------------------------结构体------------*/
typedef struct Lnode          /*  结点的结构定义  */

   {  int  num;              /*  编号子域        */
      int  sm;               /*  密码子域        */
      struct Lnode *next;        /*  指针域          */

   }Lnode,*LinkList;
/* 函数声明   */
LinkList creat();
void  print1(LinkList  h);
void  print2(LinkList  h);
LinkList shanchu(LinkList h);
void outs(LinkList  h, int m);
 
/*  主函数  */
 void main()
 { int  m;  LinkList  head;
   head=creat();
   print1(head);//带头结点输出（其实是跳过了头结点）
   head=shanchu(head);
   print2(head);//删除头结点后输出
   printf("\n  输入开始的密码值, m=（m>1）：");   scanf("%d",&m);
   outs(head, m);//按照密码输出
 }  /* main  */

/*  创建约瑟夫环 带头结点的单链表 */
 LinkList  creat()
{  int  i=0, mi,j=1;
   LinkList  h,p2,p1;
   h=( LinkList)malloc(sizeof(Lnode)); 
   p1=h; 
   p1->next =h;
   printf("\n 初始化信息，密码为-1，结束信息输入： ");
   printf("\n 第1个人密码为： ");  
   scanf("%d",&mi);

   while( mi != -1)                    //  密码为-1，结束循环  
    { 
   p2=( LinkList)malloc(sizeof(Lnode));  //  申请数据元素结点空间

       i++; 
	   j++;
   p2->num=i;
   p2->sm=mi;
   p2->next=h;//连成环 
   p1->next=p2;
   p1=p2;
      printf("第");
	  printf("%d",j);
      printf("个人密码为:");  
	  scanf("%d",&mi);   //读入下一个密码  
	 // printf(" \n ");
   }  //while  
   return(h);
 }   
/*---------------------------------------带头结点输出（跳过头结点输出）----------------*/
 void  print1(LinkList  h)
   {
    LinkList q;
q=h->next;
printf("带头结点链表输出：\n");
// 与头插法创建链表对应的输出函数
while (q!=h)
{   
printf("%6d %6d  \n",q->num,q->sm);
q=q->next;
}
 }
   LinkList shanchu(LinkList h){
    LinkList m,p;
m=h;
for(p=h;p->next!=h;p=p->next);
p->next=h->next;
h=h->next;
free(m);
return(h);
   }
/*----------------------删除头结点后输出-------------------*/
   void  print2(LinkList  h)
   {
    LinkList r;
r=h;
printf("不带头结点链表输出：\n");
    while (r->next  !=h )
{  
printf("%6d %6d  \n",r->num,r->sm);
r=r->next;
}
   printf("%6d %6d  \n",r->num,r->sm);// 头指针指向第一个数据元素结点

  }
/*--------------------------------按照密码输出某个人------------*/
  void outs(LinkList  h, int m)
{  int i;   LinkList q=h,p; 
  printf("\n "); 
  printf("出列顺序为：\n"); 
  while (q->next!=q)
   { for(i=1;i<m;i++){ p=q; q=q->next;}      // 报数到第m个人

     printf("%6d %6d\n ",q->num,q->sm);     //  输出第m个人的编号和密码  
     m=q->sm;
     p->next=q->next;  free(q);           //  第m个人出列

     q=p->next;
    }
  printf("%6d %6d\n",q->num,q->sm);         //   输出最后一个结点的编号值

  free(q);
} //outs 
#include  <stdio.h>
#include  <stdlib.h>
/*--------------------------�ṹ��------------*/
typedef struct Lnode          /*  ���Ľṹ����  */

   {  int  num;              /*  �������        */
      int  sm;               /*  ��������        */
      struct Lnode *next;        /*  ָ����          */

   }Lnode,*LinkList;
/* ��������   */
LinkList creat();
void  print1(LinkList  h);
void  print2(LinkList  h);
LinkList shanchu(LinkList h);
void outs(LinkList  h, int m);
 
/*  ������  */
 void main()
 { int  m;  LinkList  head;
   head=creat();
   print1(head);//��ͷ����������ʵ��������ͷ��㣩
   head=shanchu(head);
   print2(head);//ɾ��ͷ�������
   printf("\n  ���뿪ʼ������ֵ, m=��m>1����");   scanf("%d",&m);
   outs(head, m);//�����������
 }  /* main  */

/*  ����Լɪ�� ��ͷ���ĵ����� */
 LinkList  creat()
{  int  i=0, mi,j=1;
   LinkList  h,p2,p1;
   h=( LinkList)malloc(sizeof(Lnode)); 
   p1=h; 
   p1->next =h;
   printf("\n ��ʼ����Ϣ������Ϊ-1��������Ϣ���룺 ");
   printf("\n ��1��������Ϊ�� ");  
   scanf("%d",&mi);

   while( mi != -1)                    //  ����Ϊ-1������ѭ��  
    { 
   p2=( LinkList)malloc(sizeof(Lnode));  //  ��������Ԫ�ؽ��ռ�

       i++; 
	   j++;
   p2->num=i;
   p2->sm=mi;
   p2->next=h;//���ɻ� 
   p1->next=p2;
   p1=p2;
      printf("��");
	  printf("%d",j);
      printf("��������Ϊ:");  
	  scanf("%d",&mi);   //������һ������  
	 // printf(" \n ");
   }  //while  
   return(h);
 }   
/*---------------------------------------��ͷ������������ͷ��������----------------*/
 void  print1(LinkList  h)
   {
    LinkList q;
q=h->next;
printf("��ͷ������������\n");
// ��ͷ�巨���������Ӧ���������
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
/*----------------------ɾ��ͷ�������-------------------*/
   void  print2(LinkList  h)
   {
    LinkList r;
r=h;
printf("����ͷ������������\n");
    while (r->next  !=h )
{  
printf("%6d %6d  \n",r->num,r->sm);
r=r->next;
}
   printf("%6d %6d  \n",r->num,r->sm);// ͷָ��ָ���һ������Ԫ�ؽ��

  }
/*--------------------------------�����������ĳ����------------*/
  void outs(LinkList  h, int m)
{  int i;   LinkList q=h,p; 
  printf("\n "); 
  printf("����˳��Ϊ��\n"); 
  while (q->next!=q)
   { for(i=1;i<m;i++){ p=q; q=q->next;}      // ��������m����

     printf("%6d %6d\n ",q->num,q->sm);     //  �����m���˵ı�ź�����  
     m=q->sm;
     p->next=q->next;  free(q);           //  ��m���˳���

     q=p->next;
    }
  printf("%6d %6d\n",q->num,q->sm);         //   ������һ�����ı��ֵ

  free(q);
} //outs 
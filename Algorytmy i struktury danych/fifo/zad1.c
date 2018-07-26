#include<stdio.h>
#include<stdlib.h>

typedef struct FIFO
{
	int value;
	struct FIFO * next;
}FIFO;

void push (FIFO ** fifo, int element)
{
	FIFO * queue = (FIFO *)malloc(sizeof(FIFO));
	queue->value=element;
	queue->next = NULL;
	//Jeœli lista jest pusta to nowy element bêdzie pierwszym
	if(*fifo==NULL){
	*fifo=queue;
	}
	else
	{
		//Ustawianie wskaŸnika pomocniczego na pierwszy element
		FIFO * help = *fifo;
		//Przesuwamy do ostatniego elementu i wstawiamy za nim
		while(help->next!=NULL)
			help=help->next;
		help->next = queue;

	}


}

void pop(FIFO ** fifo)
{	//Jeœli lista pusta to brak elementu
	FIFO * help = (*fifo);
	if((*fifo)==NULL)
	{
		printf("Brak elementu");
	}
	else if((*fifo)->next==NULL){
	printf("Usunieto %d\n", help->value);
	*fifo=NULL;
	}
	else{
		printf("Usunieto %d\n", help->value);
		(*fifo)=(*fifo)->next; //pocz¹tkiem listy nastêpny element
		free(help);
	}
}
void showList(FIFO * fifo)
{

	if(fifo==NULL)
	printf("Brak elementu\n");
	else while(fifo!=NULL)
	{
	printf("%d\n",fifo->value);
	fifo=fifo->next;
	}
}


int main()

{
	FIFO * fifo = NULL;
	push(&fifo,1);
	push(&fifo,2);
	push(&fifo,3);
	push(&fifo,5);
	pop(&fifo);
	pop(&fifo);
	pop(&fifo);
	pop(&fifo);
	showList(fifo);


}


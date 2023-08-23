/*
*	Dada una lista de valores enteros, que representa las notas de los exámenes finales del rendimiento
*	académico de un estudiante, escriba un programa, en el lenguaje C, que calcule el promedio con
*	aplazos y sin aplazos del estudiante.
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct TLista{
  int valor;
  struct TLista* next;
} TLista;

float PromedioSinAplazos(TLista *l);
float PromedioConAplazos(TLista *l);
//Crea un nodo
struct TLista* CrearNodo();

//Inserta un nodo al comienzo de la lista
void InsertarC(TLista *l);

//Muestra la lista
void MostrarLista(TLista *l);

int main( int argc, char **argv ) {
  
  
  
  return 0;
}

float PromedioConAplazos(TLista *l){
  float resConAplazo = 0;
  int cantMaterias = 0;
  TLista *aux = l;

  while (aux != NULL){
    if (aux->valor < 5){
      resConAplazo += aux->valor;
    }
    cantMaterias += 1;
    aux = aux->next;
  }

  return resConAplazo/cantMaterias;
}

float PromedioSinAplazos(TLista *l){
  float resSinAplazo = 0;
  int cantMaterias = 0;
  TLista *aux = l;

  while (aux != NULL){
    if (aux->valor >= 5){
      resSinAplazo += aux->valor;
    }
    cantMaterias += 1;
    aux = aux->next;
  }

  return resSinAplazo/cantMaterias;
}

// Módulos de manejo de listas, 2022 César Cornejo
//Crea un nodo
struct TLista* CrearNodo() {
  struct TLista * nuevo;
  nuevo = (struct TLista *) malloc (sizeof(struct TLista *));
  if (nuevo==NULL){
     exit(EXIT_FAILURE); 
  }
  else {
    printf("Numero: "); fflush(stdout);
    scanf("%d",&nuevo->valor);
    nuevo->next = NULL;
    return nuevo;
  }
}

//Inserta un nodo al comienzo de la lista
void InsertarC(TLista *l) {
  struct TLista * nuevo;
  nuevo = CrearNodo();
  if (l == NULL) {
    //primer elemento
     l = nuevo;
  }
  else {
    nuevo->next = l;	 
    l = nuevo;
  }
}

//Muestra la lista
void MostrarLista(TLista *l) {
    int i=0;
    printf("\nMostrando la lista completa:\n");
    while (l != NULL) {
          printf( "Numero: %d \n", l->valor);
          l = l->next;
          i++;
    }
    if (i==0) printf( "\nLa lista esta vacia!!\n" );
}

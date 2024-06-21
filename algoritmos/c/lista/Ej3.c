/*
 *	Dada una lista de valores enteros, que representa las notas de los exámenes finales del rendimiento
 *	académico de un estudiante, escriba un programa, en el lenguaje C, que calcule el promedio con
 *	aplazos y sin aplazos del estudiante.
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct TLista
{
  int valor;
  struct TLista *next;
} TLista;

float PromedioSinAplazos(TLista *l);
float PromedioConAplazos(TLista *l);
// Crea un nodo
struct TLista *CrearNodo(int valor);

// Inserta un nodo al comienzo de la lista
void InsertarC(TLista **l, int valor);

// Muestra la lista
void MostrarLista(TLista *l);

int main(int argc, char **argv)
{
  TLista *lista = NULL;
  int valor;

  printf("Ingrese las notas (ingrese un valor negativo para terminar):\n");
  while (1)
  {
    printf("Nota: ");
    scanf("%d", &valor);
    if (valor < 0)
      break;
    InsertarC(&lista, valor);
  }

  MostrarLista(lista);

  printf("Promedio con aplazos: %.2f\n", PromedioConAplazos(lista));
  printf("Promedio sin aplazos: %.2f\n", PromedioSinAplazos(lista));

  return 0;
}

float PromedioConAplazos(TLista *l)
{
  float resConAplazo = 0;
  int cantMaterias = 0;
  TLista *aux = l;

  while (aux != NULL)
  {
    resConAplazo += aux->valor;
    cantMaterias++;
    aux = aux->next;
  }

  return (cantMaterias == 0) ? 0 : resConAplazo / cantMaterias;
}

float PromedioSinAplazos(TLista *l)
{
  float resSinAplazo = 0;
  int cantMaterias = 0;
  TLista *aux = l;

  while (aux != NULL)
  {
    if (aux->valor >= 5)
    {
      resSinAplazo += aux->valor;
      cantMaterias++;
    }
    aux = aux->next;
  }

  return (cantMaterias == 0) ? 0 : resSinAplazo / cantMaterias;
}

// Módulos de manejo de listas, 2022 César Cornejo
// Crea un nodo
struct TLista *CrearNodo(int valor)
{
  struct TLista *nuevo;
  nuevo = (TLista *)malloc(sizeof(TLista *));
  if (nuevo == NULL)
  {
    exit(EXIT_FAILURE);
  }
  else
  {
    nuevo->valor = valor;
    nuevo->next = NULL;
    return nuevo;
  }
}

// Inserta un nodo al comienzo de la lista
void InsertarC(TLista **l, int valor)
{
  struct TLista *nuevo = CrearNodo(valor);
  nuevo->next = *l;
  *l = nuevo;
}

// Muestra la lista
void MostrarLista(TLista *l)
{
  if (l == NULL)
  {
    printf("La lista esta vacia\n");
    return;
  }
  printf("\nMostrando la lista completa:\n");
  while (l != NULL)
  {
    printf("Nota: %d \n", l->valor);
    l = l->next;
  }
}

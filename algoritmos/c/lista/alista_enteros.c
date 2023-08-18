#include <stdio.h>
#include <stdlib.h>
#include "lista_enteros.h"


// Implementacion de lista de arreglos de enteros (Ejercicio 2.b)

/*
*	estructura_lista es la implementacion del tipo_lista 
*	declarado en lista_enteros.h.
*/
typedef struct estructura_lista{
	int valor[NMax];
    int cant;  // cardinal
} TData;

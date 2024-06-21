#include <stdio.h>
#include <stdlib.h>
#include "pilas.h"

struct TListaInt
{
	int infoInt;
	struct TListaInt *next;
};

struct TListaChar
{
	char infoChar;
	struct TListaChar *next;
};

// Definición del tipo_lista del header.
/* Una pila que contiene:
 * 	El tipo de la pila.
 * 	Una lista de int por si es pila de int.
 * 	Una de char por si es char.
 *	La cantidad de elementos en ella. */
struct estructura_pila
{
	tipo_elemento tipo; // enum: Char e Int, respectivamente.
	struct TListaInt *lista_int;
	struct TListaChar *lista_char;
	int cant;
};

// Crea una pila nueva.
tipo_pila Crear(tipo_elemento tipo)
{
	tipo_pila *newStack = malloc(sizeof(tipo_pila));
	(*newStack)->tipo = tipo;
	(*newStack)->cant = 0;

	if (tipo == Char)
	{
		(*newStack)->lista_int = NULL;
		(*newStack)->lista_char = malloc(sizeof(struct TListaChar));
	}
	else
	{
		(*newStack)->lista_int = malloc(sizeof(struct TListaInt));
		(*newStack)->lista_char = NULL;
	}

	return *newStack;
}

// Retornar el elemento del tope de la pila. No debe estar vacía.
tipo_elemento Tope(tipo_pila pila)
{
	// !(pila->cant)

	if (pila->tipo == Char)
	{
		return pila->lista_char->infoChar;
	}
	else
	{
		return pila->lista_int->infoInt;
	}
}

// Poner un elemento en el tope de la pila.
// Devuelve 1 si fue exitosa la operación, sino devuelve 0.
int Apilar(tipo_pila pila, tipo_elemento elemento)
{
	if (elemento == Char)
	{
		struct TListaChar *newNodito = malloc(sizeof(struct TListaChar));
		(*newNodito).infoChar = elemento;
		(*newNodito).next = pila->lista_char;
		pila->lista_char = newNodito;
	}
	else
	{
		if (elemento == Int)
		{
			struct TListaInt *newNodito = malloc(sizeof(struct TListaInt));
			(*newNodito).infoInt = elemento;
			(*newNodito).next = pila->lista_int;
			pila->lista_int = newNodito;
		}
		else
		{
			// No coincide ni char ni int, no se puede agregar el elemento.
			return 0;
		}
	}

	// Se agregó por Char o por Int.
	return 1;
}

// Saca un elemento del tope de la pila. No debe estar vacía.
// Devuelve el elemento que se sacó.
tipo_elemento Desapilar(tipo_pila pila)
{
	tipo_elemento elemTope = pila->tipo;

	// !(pila->cant)
	if (pila->tipo == Char)
	{
		struct TListaChar *aux = pila->lista_char;
		pila->lista_char = pila->lista_char->next;
		elemTope = aux->infoChar;
		free(aux);
	}
	else
	{
		struct TListaInt *aux = pila->lista_int;
		pila->lista_int = pila->lista_int->next;
		elemTope = aux->infoInt;
		free(aux);
	}
	pila->cant--;

	return elemTope;
}

// Vacía una pila.
tipo_pila Vaciar(tipo_pila pila)
{
	tipo_pila aux = pila;

	if (pila->tipo == Char)
	{
		do
		{
			aux->lista_char = pila->lista_char;
			pila->lista_char = pila->lista_char->next;
			free(aux);
			pila->cant--;
		} while (pila->cant > 0);
	}
	else
	{
		do
		{
			aux->lista_int = pila->lista_int;
			pila->lista_int = pila->lista_int->next;
			free(aux);
			pila->cant--;
		} while (pila->cant > 0);
	}

	return pila;
}

// Devuelve la cantidad de elementos pertenecientes a la pila.
int Elementos(tipo_pila pila)
{
	return pila->cant;
}

void MostrarPila(tipo_pila pila)
{
	if (pila->cant == 0)
	{
		printf("La pila está vacía \n");
		return;
	}

	tipo_pila recorredor = pila;
	int i = 0;
	if (recorredor->tipo == Int)
	{
		while (i <= recorredor->cant)
		{
			printf("Elemento nro. %d de la pila: ", i);
			printf("| %d |", recorredor->lista_int->infoInt);
			printf("\n");
			recorredor->lista_int = recorredor->lista_int->next;
			recorredor->cant--;
			i++;
		}
	}
	else
	{
		while (i <= recorredor->cant)
		{
			printf("Elemento nro. %d de la pila: ", i);
			printf("| %s |", recorredor->lista_int->infoInt);
			printf("\n");
			recorredor->lista_int = recorredor->lista_int->next;
			recorredor->cant--;
			i++;
		}
	}
	return;
}

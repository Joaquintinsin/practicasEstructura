#include <stdio.h>
#include <stdlib.h>
#include "lista_enteros.h"

// Implementacion de lista de arreglos de enteros (Ejercicio 2.b)

/*
 *	estructura_arreglo es la implementacion del tipo_arreglo
 *	declarado en lista_enteros.h
 */

struct estructura_lista
{
    int arreglo[NMax];
    int elementos; // cant
};

tipo_lista crear(void)
{
    tipo_lista linkable = (tipo_lista)malloc(sizeof(struct estructura_lista));

    linkable->elementos = 0;

    return linkable;
}

int es_vacia(tipo_lista arr)
{
    return (arr->elementos == 0);
}

tipo_lista agregar(tipo_lista arr, int elem)
{
    if (arr->elementos == NMax) // El arreglo está lleno, devuelve el mismo arreglo.
        return arr;

    // Falta correr los elementos (Ver si hace falta)

    arr->arreglo[arr->elementos] = elem;
    arr->elementos = (arr->elementos) + 1;

    return arr;
}

int ins(tipo_lista arr, int elem, int position)
{
    if (position < 0 || position > arr->elementos)
        return -1;

    for (int i = position; i < arr->elementos; i++)
        arr->arreglo[i + 1] = arr->arreglo[i];

    arr->arreglo[position] = elem;

    return 0;
}

tipo_lista fin(tipo_lista arr, int elem)
{
    ins(arr, elem, arr->elementos);

    return arr;
}

int eliminar_comienzo(tipo_lista arr)
{
    if (es_vacia(arr))
        return -1;

    for (int i = 0; i < arr->elementos; i++)
        arr->arreglo[i] = arr->arreglo[i + 1];
    arr->elementos = (arr->elementos) - 1;

    return 0;
}

int eliminar(tipo_lista arr, int posicion)
{
    if (posicion < 0 || posicion > arr->elementos)
        return -1;

    if (es_vacia(arr))
        return -1;

    if (!posicion)
    {
        eliminar_comienzo(arr);
        return 0;
    }

    int i = 0;
    while (i < posicion)
        i++;

    while (i < arr->elementos)
    {
        arr->arreglo[i] = arr->arreglo[i + 1];
        i++;
    }

    arr->elementos = (arr->elementos) - 1;

    return 0;
}

int obtener(tipo_lista arr, int posicion)
{
    if (posicion < 0 || posicion > arr->elementos)
        return -1;

    return arr->arreglo[posicion];
}

tipo_lista reversa(tipo_lista arr)
{
    tipo_lista resultado = crear();

    for (int i = 0; i < arr->elementos; i++)
        agregar(resultado, obtener(arr, i));

    return resultado;
}

tipo_lista concat(tipo_lista ini, tipo_lista cola)
{
    tipo_lista resultado = crear();

    for (int i = 0; i < ini->elementos; i++)
        fin(resultado, obtener(ini, i));

    for (int i = 0; i < cola->elementos; i++)
        fin(resultado, obtener(cola, i));

    return resultado;
}

tipo_lista sub(tipo_lista arr, int c, int f)
{
    tipo_lista resultado = crear();

    for (int i = c; i < f; i++)
        fin(resultado, obtener(arr, i));

    return resultado;
}

tipo_lista copia(tipo_lista arr)
{
    return sub(arr, 0, arr->elementos);
}

int cabeza(tipo_lista arr)
{
    return arr->arreglo[0];
}

tipo_lista cola(tipo_lista arr)
{
    return sub(arr, 1, arr->elementos);
}

void mostrar(tipo_lista arr)
{
    printf("[ ");

    for (int i = 0; i < arr->elementos; i++)
        printf("%i ", arr->arreglo[i]);

    printf("]");
}

// Ejercicio 2, a)
int reemplazar(tipo_lista arr, int e, int i)
{
    tipo_lista aux = crear();

    if (i < 0 || i > arr->elementos - 1)
    {
        printf("Posición inexistente \n");
        return -1;
    }

    agregar(aux, obtener(arr, i));

    int resIns = ins(arr, e, i);
    int resElim = eliminar(arr, i + 1);
    if (resIns || resElim)
    {
        printf("Hay un error en la inserción o eliminación \n");
        return -1;
    }

    return obtener(arr, i);
}

// Ejercicio 2, a)
int intercambiar(tipo_lista l, int p1, int p2)
{
    int aux;

    if (p1 < 0 || p1 > l->elementos - 1)
    {
        printf("Posición 1 inexistente \n");
        return -1;
    }
    if (p2 < 0 || p2 > l->elementos - 1)
    {
        printf("Posición 2 inexistente \n");
        return -1;
    }

    aux = l->arreglo[p1];
    aux = l->arreglo[p1];
    l->arreglo[p1] = l->arreglo[p2];
    l->arreglo[p2] = aux;

    return 0;
}

// Ejercicio 2, c)
int masPositivosQueNegativos(tipo_lista l)
{
    int pos = 0, neg = 0;

    for (int i = 0; i < l->elementos; i++)
    {
        if (l->arreglo[i] > 0)
            pos++;
        else if (l->arreglo[i] < 0)
            neg++;
    }

    return (pos > neg);
}

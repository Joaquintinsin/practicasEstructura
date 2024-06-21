#include <stdio.h>
#include <stdlib.h>
#include "pilas.h"

int main(int argc, char **argv)
{
	tipo_pila pilaDeEnteros = Crear(2);
	tipo_pila pilaDeCaracteres = Crear(1);

	printf("Apilar el 1 en la pila entera vacía \n");
	Apilar(pilaDeEnteros, 1);
	printf("Apilar el 2 en la pila entera vacía \n");
	Apilar(pilaDeEnteros, 2);
	printf("Apilar el 3 en la pila entera vacía \n");
	Apilar(pilaDeEnteros, 3);
	printf("Apilar el 4 en la pila entera vacía \n");
	Apilar(pilaDeEnteros, 4);

	printf("Mostrar pila: \n");
	MostrarPila(pilaDeEnteros);

	return 0;
}

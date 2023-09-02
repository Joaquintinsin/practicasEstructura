// Tipo estructurado de pilas polimórficas, redefinición de nombre
typedef struct estructura_pila* tipo_pila;

typedef enum {Char, Int} tipo_elemento;

// Crea una pila nueva.
tipo_pila Crear (tipo_elemento tipo);

// Retornar el elemento del tope de la pila. No debe estar vacía.
tipo_elemento Tope (tipo_pila pila);

// Poner un elemento en el tope de la pila.
// Devuelve 1 si fue exitosa la operación, sino devuelve 0.
int Apilar (tipo_pila pila, tipo_elemento elemento);

// Saca un elemento del tope de la pila. No debe estar vacía.
// Devuelve el elemento que se sacó.
tipo_elemento Desapilar (tipo_pila pila);

// Vacía una pila.
tipo_pila Vaciar (tipo_pila pila);

// Devuelve la cantidad de elementos pertenecientes a la pila.
int Elementos (tipo_pila pila);

// Muestra la pila.
void MostrarPila(tipo_pila pila);
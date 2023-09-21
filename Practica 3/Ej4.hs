{-
4. Dada el álgebra de conjuntos: ({A}, ∅, ins, ∪, ∈), se pide dar una implementación concreta con listas
ordenadas y sin repetidos. Dé el invariante de representación y demuestre la corrección de sus operaciones.
Implemente este TAD utilizando el lenguaje de programación Haskell.
-}

abstracto llaves concreto corchetes
(-[A]-, -empty-, -ins-, -u-, -e-)

[||] :: -[A]- -> {A}
[||] [] = {}
[||] (x:xs) = {x} u [|xs|]

Suryectividad: usando cardinalidad de conjuntos
forall s e {A} : Exs e [A] : [|xs|] = s
Caso Base: #s = 0
Existe xs e [A] : [|xs|] = {}
= existe la lista vacía:
# [|[]|] = 0

Caso Inductivo: #s = n+1
H.Inductiva: forall s e {A} : Exs e [A] : [|xs|] = s
Probar: forall s' e {A} : Eys e [A] : [|ys|] = s'

Existe (x:xs) e [A] : [|x:xs|] = {x} u [|xs|]
#({x} u [|xs|]) = #s+1 porque #s es h.i. y +1 es porque #{x}=1, es un elemento no repetido por enunciado, entonces suma 1 siempre


-empty- :: -[A]-
-empty- = []

-ins- :: a -> -[A]- -> [A]
-ins- a -[]- = -[a]-
-ins- a -(x:xs)- = | a < x = a:-(x:xs)-
                   | a > x = x:(-ins- a -xs-)
                   | otherwise = -(x:xs)-

-u- :: -[A]- -> -[A]- -> -[A]-
-u- -[]- -[]- = -[]-
-u- -xs- -[]- = -xs-
-u- -[]- -ys- = -ys-
-u- -(x:xs)- -(y:ys)- = | x < y = x : -u- -xs- -(y:ys)-
                        | x > y = y : -u- -(x:xs)- -ys-
                        | otherwise = -u- -xs- -ys-

-e- :: a -> -[A]- -> Bool
-e- n -[]- = False
-e- n -(x:xs)- = | n == x = True
                 | otherwise = -e- n -xs-

inv :: -[A]- -> Bool
inv -[]- = True
inv -[x]- = True
inv -(x:y:xs)- = x < y && inv -(y:xs)-


para probar invariante:
INVARIANTE DE INSERTAR:
inv xs -> inv -ins- e xs

Caso Base: inv [] -> inv -ins- e []
inv [] -> inv -ins- e []

inv -ins- e []
= {def -ins-}
inv [e]
= {def inv}
True

Caso Inductivo: inv (x:xs) -> inv -ins- e (x:xs)
inv (x:xs) -> inv -ins- e (x:xs)

inv -ins- e (x:xs)
= {def -ins-}

Caso 1: e < x
inv (e:x:xs)
= {def inv}
e < x && repOk x:xs
= {H.I. e hipotesis Caso 1}
True && True
= {logica}
True

Caso 2: e > x
inv x:(-ins- e xs)
= {x mas chico de la lista y menor que e, y por H.I.}
True

Caso 3: e == x
inv (x:xs)
= {antecedente}
True


INVARIANTE DE UNION:
inv xs ^ inv ys -> inv -u- xs ys

Caso Base 1: inv [] ^ inv ys -> inv -u- [] ys
inv [] ^ inv ys -> inv -u- [] ys

inv -u- [] ys
= {def -u-}
inv ys
= {antecedente}
True

Caso Base 2: inv xs ^ inv [] -> inv -u- xs []
inv xs ^ inv [] -> inv -u- xs []

inv -u- xs []
= {def -u-}
inv xs
= {antecedente}
True

Caso Base 3: inv [] ^ inv [] -> inv -u- [] []
inv [] ^ inv [] -> inv -u- [] []

inv -u- [] []
= {def -u-}
inv []
= {antecedente y def inv}
True

Caso Inductivo 1: inv (x:xs) ^ inv (y:ys) -> inv -u- (x:xs) (y:ys)
H.I.: inv xs ^ inv ys -> inv -u- xs ys
Probar: inv (x:xs) ^ inv (y:ys) -> inv -u- (x:xs) (y:ys)

inv -u- (x:xs) (y:ys)
= {def -u-}

Caso 1: (x < y)
inv -u- (x:xs) (y:ys)
= {def -u-}
inv x: -u- xs (y:ys)
= {def -u-}

Caso 1.1: xs = []
inv x: -u- [] (y:ys)
= {def -u-}
inv x: (y:ys)
= {def inv e hipótesis de caso 1}
True

Caso 2.2: xs = [a]
inv x: -u- [a] (y:ys)




= x : -u- -xs- -(y:ys)-
| x > y = y : -u- -(x:xs)- -ys-
| otherwise = -u- -xs- -ys-








el algebra abstracta es la que yo le voy a dar la implementacion del algebra concreta
el algebra concreta es la que yo implemento
    en el mundo concreto trabajamos con elementos del mundo concreto
invariante es para mejoras y preserverancia de eficacia
    que los elementos tengan cierta forma si o si, por ej para despues buscar
ver que las operaciones conmuten


           TxA ----> A
            /\      /\
            |        |
 id x [||]  |        |  [||]
            |        |
           TxC ----> C


ver que conmuta:
[|-ins- a xs|] = -ins- a [|xs|]


ej del profe en clase:
[||] : [A] -> Queue[A]
[|[]|] = []q
[|xs:x|] = ins x [|xs|]

-ins- :: A -> [A] -> [A]
-ins- x xs = xs:x

-rem- :: [A] -> [A]
-rem- [x] = []
-rem- (x:xs) = xs

ver que conmuta: [|-rem- xs|] = rem [|xs|]
Caso Base: xs = [x] tal que [|-rem- [x]|] = rem [|[x]|]
[|-rem- [x]|]
= {def -rem-}
[|[]|]
= {def [||]}
[]q
= {def rem de elem unitario de colas}
rem (ins x []q)
= {def [||]}
rem ()


e :: a -> [a] -> Bool
e a [] = False
e a [x] = a == x
e a (x:xs) = a == x || e a xs

-e- x xs = e x [|xs|]

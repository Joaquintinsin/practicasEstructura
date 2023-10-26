data Tree a = Nil | Node a (Tree a) (Tree a) deriving Show

altura :: Tree a -> Int
altura Nil = 0
altura (Node x hi hd) = 1 + (max (altura hi) (altura hd))

size :: Tree a -> Int
size Nil = 0
size (Node x hi hd) = 1 + size hi + size hd

espejo :: Tree a -> Tree a
espejo Nil = Nil
espejo (Node x hi hd) = Node x (espejo hd) (espejo hi)

mapTree :: (a -> b) -> Tree a -> Tree b
mapTree f Nil = Nil
mapTree f (Node x hi hd) = Node (f x) (mapTree f hi) (mapTree f hd)


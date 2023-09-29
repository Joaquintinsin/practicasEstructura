{- función de abstracción
[||] :: [a] -> {a}
[||] [] = {}
[||] (x:xs) = {x} u [|xs|]
-}

empty :: [a]
empty = []

insertarRayita :: Ord a => a -> [a] -> [a]
insertarRayita a [] = [a]
insertarRayita a (x:xs) | a < x = a:(x:xs)
                        | a > x = x:(insertarRayita a xs)
                        | otherwise = (x:xs)

unionRayita :: Ord a => [a] -> [a] -> [a]
unionRayita [] [] = []
unionRayita xs [] = xs
unionRayita [] ys = ys
unionRayita (x:xs) (y:ys) | x < y = x : unionRayita xs (y:ys)
                          | x > y = y : unionRayita (x:xs) ys
                          | otherwise = unionRayita xs ys

pertRayita :: Eq a => a -> [a] -> Bool
pertRayita n [] = False
pertRayita n (x:xs) | n == x = True
                    | otherwise = pertRayita n xs

invariante :: Ord a => [a] -> Bool
invariante [] = True
invariante [x] = True
invariante (x:y:xs) = x < y && invariante (y:xs)

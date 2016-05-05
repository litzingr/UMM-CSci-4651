# Haskell Lab
Shawn Seymour, Zach Litzinger, and Kyle Hakala.
## Exercise 1
By typing ```:type count```, we get the following:

```haskell
count :: Num a => (t -> Bool) -> [t] -> a
```
This is telling us count takes an anonymous function that returns a boolean and also a list. It then returns a number (Num a).

In the example, we count all elements in the list (of numbers) less than 5. We get a return value of 2:
```haskell
count (\x -> x < 5) [2, 6, 4, 7, 8] -- returns 2
```

We can use this on booleans as follows:
```haskell
count (\x -> x == True) [True, False, True, True, False] -- returns 3
```

We can use it on strings as well:
```haskell
count (\x -> x == "hello") ["world", "hello", "test", "3"] -- returns 1
```

Another example with strings:
```haskell
count (\x -> x /= "hello") ["world", "hello", "test", "3"] -- returns 3
```

We can count things in a list of a certain type.

## Exercise 2

firstTrue takes a function that takes a boolean and returns a boolean. Our function then takes a list of booleans. It will also return a boolean. This is not very generic. The type is shown:

```haskell
firstTrue :: (Bool -> Bool) -> [Bool] -> Bool
```

We could do something like:
```haskell
firstTrue (not) [False, True, True] -- returns False
firstTrue (\x -> x) [False, True, True] -- returns True
firstTrue (\x -> x) [False, False] -- returns False
```

Next, we check the type of firstTrue2. This function takes a function that takes an item of type t and returns a boolean. We then also take a list of items of type t. It will return a singleton of type t.

```haskell
firstTrue2 :: (t -> Bool) -> [t] -> [t]
```

We can do some examples with it as follows:
```haskell
firstTrue2 (\x -> x > 5) [1,2,3,4,5,6,7] -- returns [6]
```

This returns a singleton of 6 because 6 is the first item in the list to satisfy our predicate x > 5. A few more examples:

```haskell
firstTrue2 (\x -> x > 50) [1,2,3,4,5,6,7] -- returns [] because nothing satisfied our predicate
```

This generality is created because firstTrue2 does not have to return a boolean on nothing found. Rather, firstTrue2 will return an empty list. Thus, we can have a list of any type.

## Exercise 3

Our mystery function has an interesting type. It takes in a list of functions that take one input and return the same type. It also takes a list of that type. It will output a list containing items of that same type. It also only works up until the smaller list as shown in examples.

```haskell
mystery :: [t1 -> t] -> [t1] -> [t]
```

Examples:
```haskell
mystery [(+) 3] [2] -- returns [5]
mystery [(+) 3, (*) 3] [3, 3] -- returns [6, 9]
mystery [(+) 3, (*) 3] [3, 3, 7] -- returns [6, 9]
mystery [(+) 3, (*) 3, (*) 33333] [3, 3] -- returns [6, 9]
mystery [] [] -- returns []
mystery [(*) 3] [] -- returns []
mystery [] [3] -- returns []
```

## Exercise 4
Here is what I wrote for my reverse function. It reverses a list of any type. I use the ++ concatenate function and add the first element to the end of the list and traverse our list effectively reversing the list.

```haskell
reverseList [] = []
reverseList(x:xs) = reverseList xs ++ [x]
```

The type of this function is [t] -> [t]. This means it takes a list of type t and returns a list of the same type t. We can use it as follows:

```haskell
reverseList [] -- returns []
reverseList [1,2,3,4] -- returns [4,3,2,1]
reverseList ["shawn", "is", "name", "my", "world", "hello"] -- returns ["hello","world","my","name","is","shawn"]
```

## Exercise 5
We wrote our base function as follows:

```haskell
ourfold func threshold (x:xs) = ourfoldhelper func x threshold xs
```

It takes a two-parameter function, a threshold value, and a list. We assume non-empty. We then wrote a helper function that has a current value that we add up to check against the threshold.

```haskell
ourfoldhelper func current threshold [] = current
ourfoldhelper func current threshold (x:xs) = if current >= threshold then current else (ourfoldhelper func (func x current) threshold xs)
```

We wrote some tests:

```haskell
ourfold (+) 3 [1,1,1,4] -- returns 3
ourfold (+) 4 [1,1,1,4] -- returns 7
ourfold (+) 400 [1,1,1,4] -- returns 7
ourfold (+) 9 [3,3,3] -- returns 9
ourfold (+) 9 [3,3,6] -- returns 12
ourfold (*) 9 [3,3,3] -- returns 9
ourfold (*) 10 [3,3,3] -- returns 27
ourfold (*) 9 [10] -- returns 10
ourfold (*) 9 [101] -- returns 101
ourfold (*) 100 [10,10,20] -- returns 100
```

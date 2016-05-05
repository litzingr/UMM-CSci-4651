# Problem 1
#### Fragment 1:

```cpp
int main() {
  B b1;
  B b2;

  method1(b1, b2);
  b1.print();
  b2.print();

  method2(&b1, &b2);
  b1.print();
  b2.print();

}
```

In method1, we take an object a1 and a2 of type A. This means we can pass objects of type B as well as B is a subtype of A.

Method1 calls the increment / decrement function on a1 / a2 respectively. As it is of type A, we use the increment and decrement function of class A. Thus, we get the following results after the increment / decrement:

```cpp
a1.print(); // prints x = 1 because we have increased the x value by 1 due to using the increment function of class A.
a2.print(); // prints x = -1 because we have decreased the x value by 1 due to using the decrement function of class A.
```
Now, in main we call some more prints. Because method1 takes objects rather than pointers, our objects are never updated to these new values. Thus, we get the following:

```cpp
b1.print(); // prints x = 0 y = 0
b2.print(); // prints x = 0 y = 0
```

In method2, we take pointers for objects of class A (or any subtype of it). We now call increment and decrement on a1 and a2 respectively. This is different than method1 because we are using pointers and thus will update their values as well as utilize functions of class B. Note this method does not effect y values at all.

```cpp
a1 -> print(); // prints x = 1 y = 0. This is a bit surprising, but, this is because increment is not a virtual function. Thus, we will use class A's increment function because a1 is a pointer to an object of class A.
a2 -> print(); // prints x = -2 y = 0. This is because we have a virtual decrement function and thus we are going to use class B's decrement function.
```

Now, in main we call some more prints. We have updated the values because method2 worked on pointers rather than objects.

```cpp
b1.print(); // prints x = 1 y = 0.
b2.print(); // prints x = -2 y = 0.
```

These print the same things that we found inside of method2 as we updated the b1 and b2 through method2.

#### Fragment 2:
We create pointers to new objects of type B. We then call increment and decrement on them:

```cpp
B *bptr1 = new B();
bptr1 -> increment();
bptr1 -> print(); // prints x = 2 y = 0. This is because bptr1 is a pointer to a type B object and thus we call class B's increment function and add 2 to 0.
B *bptr2 = new B();
bptr2 -> decrement();
bptr2 -> print(); // prints x = -2 y = 0. This is because ptr2 is a pointer to a type B object and thus we call class B's decrement function and subtract 2 from 0.
```

swap1 takes objects of type A and we pass it objects of type B. We can now see what happens in the code:

```cpp
void swap1(A a1, A a2) {
  A temp = a1;
  a1 = a2;
  a2 = temp; // we have switched our objects a1 and a2 to be each other. Thus, we will get them swapped when we call print:
  a1.print(); // prints x = -2. This is calling the print function of type A as a1 is of type A, even though we passed its subtype B.
  a2.print(); // prints x = 2. Similar to above. Note that a1 and a2 have swapped as we expect.
}
```

However, we can see that as we did not update pointers, that we did not actually swap anything. The next lines of code show us this:
```cpp
bptr1 -> print(); // prints x = 2 y = 0. This has not been swapped!
bptr2 -> print(); // prints x = -2 y = 0. This has not been swapped!
```

swap2 takes pointers to objects of type A and we pass it pointers of type B. This is OK as B is a subtype of A. We create a temp variable and swap the pointers to these objects.

```cpp
void swap2(A *a1, A *a2) {
  A temp = *a1;
  *a1 = *a2;
  *a2 = temp;
  a1 -> print(); // prints x = -2 y = 0. This is because our swap worked. As they are pointers to objects of type B, we use type B's print function.
  a2 -> print(); // prints x = 2 y = 0. This is because our swap worked.
}
```

We now print the pointers again in main. This time, we can see that the values have actually been swapped:
```cpp
bptr1 -> print(); // prints x = -2 y = 0.
bptr2 -> print(); // prints x = 2 y = 0.
```

This is what we expect to print as they are using the print function of type B and our swap changed the pointers and thus updated our values.

***

# Problem 2
For these questions, I go through each assignment in order. For reference, they are numbered:

1. ```s1.combine(s2);```
2. ```s2.combine(s1);```
3. ```sq1.combine(s1);```
4. ```sq1.combine(sq1);```
5. ```c1.combine(s1);```
6. ```c1.combine(sq1);```

#### Question 1:
1. This will call Shape's combine function because we are passing it a "Shape s2".
2. This will call Shape's combine function as we are passing it a "Shape s1".
3. This will call Shape's combine function because we are passing combine a "Shape s1", so we use Shape's combine function.
4. This will call Square's combine function as they are both squares and we are passing it a "Square sq1".
5. This will call Shape's combine function as we are passing the combine function a "Shape s1".
6. This will probably call Shape's combine function as Circle does not have a combine function that takes a square, thus, we will use the parent's (Shape) combine function which takes any subtype of Shape.

After running the code, I can see that my predictions were correct. The only one I was unsure about was number 6 but it turns out I was correct for the reasons I outlined above. :)

#### Question 2:
For this, I tried different assignments for each call.
1. We are trying to combine a shape and a square that is cast as a shape. Thus, we can easily assign this to a new shape:

```java
Shape s = s1.combine(s2); // this compiles and runs fine
Square sq = s1.combine(s2); // error in Eclipse / compile time
Square sqq = (Square) s1.combine(s2); // compiles, error at run time
```

* The first line compiles and runs fine as we are combining a shape and a square and assigning it to a variable of type Shape.
* The second one gives an error right away in Eclipse, before even compiling, as we cannot convert type Shape to Square. Thus it will break while compiling.
* The third one does not raise an error in Eclipse and will compile but will throw an error at run time because we cannot cast a Shape to a Square as Shape is not a subtype of Square.

2. This is almost the exact same as number 1. This time we are passing a Shape to the combine function, which means we call the combine function of Shape. Thus, we will get a shape and we have similar results to part 1, we will only be able to assign it to a variable of type Shape.

3. Here, we are passing a shape (s1) to the combine function of a Square. This will use the combine function of Shape, however, as we are passing a shape. We can only assign this to a variable of type Shape then.

```java
Shape s = sq1.combine(s1); // compiles and runs fine
Square sq = sq1.combine(s1); // error at compile time
Square sqq = (Square) sq1.combine(s1); // error at run time
```

This is similar to number 1. We get an error at compile time for the second line because we cannot assign a type Shape to Square. When we cast it in line 3, we are allowed to compile, but it will break during run time when we try to cast a Shape to type Square because Shape is not a subtype of Square.

4. Here we are combing two squares (specifically, one square with itself).

```java
Shape s = sq1.combine(sq1); // compiles and runs fine
Square sq = sq1.combine(sq1); // compiles and runs fine
```

As this combine uses the Square combine function, the resulting shape is a Square. Thus, we can assign this result to a Square as in line 2 or also a Shape in line 1 as a Square is a subtype of Shape.

5. Here we are passing a shape to the combine function of a circle. This is very similar to number 3. Because we are passing a shape, we actually use the combine function of Shape.

```java
Shape s = c1.combine(s1); // compiles and runs fine
Circle c = c1.combine(s1); // breaks at compile time
Circle cc = (Circle) c1.combine(s1); // compiles fine but breaks at run time
```

* The first line compiles and runs fine as we are creating a shape by combining a circle with a shape and assigning it to a variable of type Shape.
* The second line will not compile as we are trying to assign a Shape to a type Circle, which we cannot do because Shape is not a subtype of Circle.
* The third line will compile as we are casting our Shape to a Circle, but will break at run time as we actually cannot cast a Shape to a Circle.

6. Here is the most compilcated: we are combining a circle and a square. We can see that we can only assign this as a variable of type Shape because our resulting object is a Shape and not a subtype of Shape.

```java
Shape s = c1.combine(sq1); // compiles and runs fine
Circle c = c1.combine(sq1); // error at compile time
Square sq = c1.combine(sq1); // error at compile time
Circle cc = (Circle) c1.combine(sq1); // error at run time
Square sqq = (Square) c1.combine(sq1); // error at run time
```

* The first line compiles and runs fine as we are assigning the result (a Shape) to a variable of type Shape.
* The second line will throw an error at compile time because we are trying to assign a Shape to a variable of type Circle. Shape is not a subtype of Circle.
* The third line will throw an error at compile time because we are trying to assign a Shape to a variable of type Square. Shape is not a subtype of Square.
* The fourth line will compile as we are casting Circle and assigning it to a variable of type Circle. It will fail at run time, however, as we cannot cast a Shape to a Circle because Shape is not a subtype of Circle.
* The fifth line will compile as we are casting Square and assigning it to a variable of type Square. It will fail at run time, however, as we cannot cast a Shape to a Square because Shape is not a subtype of Square.

These decisions make sense in Java because we do not want to be able to assign variables of a certain type to a different type that is not a subtype of that type. For example, we cannot assign a Shape to a type Square because we cannot guarantee that this shape is for sure a square. We do want it to compile when we cast it, such as casting a Shape to be a square and assigning it to a variable of type square. This makes sense as the compiling just wants to check types and not if it will actually run fine. By casting, we are saying it is okay for this to compile as we are casting this object to be this type.

# Problem 3

Although it was not required to implement this in any way, I do some implementation to easily demonstrate what I am thinking.

#### Java Implementation

To implement this in Java, we would start with an abstract class called *Game*.
```java
public abstract class Game {
  public void playRound() {

  }
}
```

We would then have *SinglePlayer* and *MultiPlayer* as abstract classes extend *Game* as they are mutually exclusive and will be what each game is built on.

```java
public abstract class SinglePlayer extends Game {
  public Person player;
}

public abstract class MultiPlayer extends Game {
  public Person[] players;
}
```

Note that each of these have some sort of variable for the player(s).

We now have four "game types" in which games can be none of or all of them. These in Java will be implemented as interfaces. I am not putting return types or inputs for most of these as they are not specified.

```java
public interface CardGame {
  public dealCards();
  public getCard();
}

public interface DiceGame {
  public rollDice();
}

public interface BoardGame {
  public updateBoard();
}

public interface ScoreBasedGame {
  public int getScore(Person p);
}
```

Lastly, we have the three age categories. These can also be implemented as interfaces, even though they are most likely mutually exclusive.

```java
public interface ChildrensGame {
  // any children-specific methods go here
}

public interface GrownUpsGame {
  // any grownup-specific methods go here
}

public interface MixedAgeGame {
  // any mixed-age-specific methods go here
}
```

This allows us to create a game fairly easily. As in the example given to us, a *MultiPlayer, Card and Board ScoreBased Children's Game* would be like so:

```java
public class OurGame extends MultiPlayer implements CardGame, BoardGame, ScoreBasedGame, ChildrensGame {
  // then we would have our implemented methods and extended stuff here
}
```

We made *Game*, *SinglePlayer*, and *MultiPlayer* abstract classes as we wanted them to extend *Game* but not be able to create own instances of them, only extend them. We made the rest of the game types interfaces because they do not need to extend a class and just need to be able to implement none, one, or multiple of them. This way we can implement multiple game types while extending either a *SinglePlayer* or *MultiPlayer* game.

#### C++ Implementation

For C++, we have a similar implementation except it is even easier. We start with our base class *Game* which then has subclasses *SinglePlayer* and *MultiPlayer*. We would want playRound to be virtual because we want its subclasses to overwrite it.

```cpp
class Game {
public:
  virtual void playRound();
}

class SinglePlayer : public virtual Game {
  // variables like player
}

class MultiPlayer : public virtual Game {
  // variables like players
}
```

We would then implement our four game types and three categories as just classes with functions.

```cpp
class BoardGame {
  // Board game methods go here -- these methods would be virtual because we want them to be able to be overwritten
}
```

We would do the same as above for *CardGame*, *DiceGame*, *ScoreBasedGame*, *ChildrensGame*, *GrownUpsGame*, and *MixedAgeGame*. We could then implement our example *MultiPlayer, Card and Board ScoreBased Children's Game* like so:

```cpp
class OurGame : public MultiPlayer, public CardGame, public BoardGame, public ScoreBasedGame, public ChildrensGame {
  // make our game specific methods here and overwrite our inherited methods
}
```

We get less of a hierarchy here than with Java as we are not dealing with abstract classes / interfaces. We just have *SinglePlayer* and *MultiPlayer* which extends our master *Game* class. The rest of them do not need to extend our *Game* class because when we make our game, we will always have either *SinglePlayer* or *MultiPlayer*.

# Problem 4
I implemented this OrderedList using Eclipse and Java 8. The files can be found in this GitHub repository.

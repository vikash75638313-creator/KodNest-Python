print("22-10-2026")
#based on I/P (parameters) and O/P (return type) we have 4 types of functions
# 1. No I/P and No O/P ( no parameter and no return type)
def square():
    number = 10  * 10
    print(number)

square()    

#2 No I/P but Yes O/P (No  parameters and retrun  the value)
def sqare():
    number = 10 * 10
    return number
print(sqare()) 

#3. Yes I/P but No O/P (Yes parameters and No return type)
def sqare(number):
    result = number * number
    print(result)

sqare(10) 

#4. Yes I/P and Yes O/P (Yes parameters and Yes return type)
def sqare(number):
    result = number * number
    return result

print(sqare(10))    


print("----------")
print("23-10-2026")
print("Global and Local Variables")

# Example 1: Global variable inside a function
c = 30

def add():
    a = 10
    b = 20  # Local variables

    print("Sum:", a + b)
    print("Global c:", c)

add()
print("Outside c:", c)


# Example 2: Local variable and Global variable
x = 100  # Global variable

def display():
    x = 50  # Local variable
    print("Local x:", x)

display()
print("Global x:", x)


# Example 3: Updating a Global variable
x = 10

def display_global():
    global x

    print("Before update:", x)
    x = 20
    print("After update:", x)

display_global()

x = 30
print("Final x:", x)
      

# *args - 0 to n positional arguments -> tuple

def add(*a):
    print("Arguments:", a)
    print("Sum:", sum(a))
    print()

add(10, 20)
add(10, 20, 30)
add(10)
add()


# **kwargs - 0 to n keyword arguments -> dictionary

def stu_info(**kwargs):
    print("Dictionary:", kwargs)
    print("Type:", type(kwargs))
    print("Name:", kwargs.get("name"))
    print("Age:", kwargs.get("age"))
    print("City:", kwargs.get("city"))
    print()

stu_info(name="John", age=20, city="New York")
stu_info(name="John", age=20)
stu_info(name="John")
stu_info()




# count from 5 4 3 2 1 
def coun(n):
    if n == 0:
        return
    else:
        print(n)
        coun(n-1)
coun(5) 

# Factorial
def fact(n):
    if n == 1:
        return 1
    else:
        return n * fact(n-1)

print(fact(5))


# fibbbonaci series  0 1 1 2 3 5 8 13.....return
def fib(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fib(n-1) + fib(n-2)    

print(fib(5))        
        
    
    
    

    
    
    





# ==================================================
# TUPLE IN PYTHON
# ==================================================

# Tuple -> () ordered, immutable, heterogeneous
# Ordered: Maintains insertion order
# Immutable: Cannot modify elements after creation
# Heterogeneous: Can store different data types

print("---------------------------------------------")

num = (1, 2, 3)

# num[0] = 100  # TypeError: Tuple is immutable

print(num, type(num))


print("---------------------------------------------")

# Creating Tuple

t1 = (10, 20, 30)
t2 = (10,)       # Single element tuple
t3 = ()          # Empty tuple
t4 = tuple()     # Empty tuple using constructor

print(t1)
print(t2, type(t2))
print(t3)
print(t4)


print("---------------------------------------------")

# Indexing and Slicing

stu_info = ("vikash", True, 7.93, 22)

print(stu_info)
print(stu_info[0])       # Positive indexing
print(stu_info[3])
print(stu_info[0:3])     # Slicing
print(stu_info[-2])      # Negative indexing
print(stu_info[::-1])    # Reverse tuple


print("---------------------------------------------")

# Tuple Repetition

name = ("vikash",) * 3

print(name)
print(type(name))


print("---------------------------------------------")

# Tuple Constructor

my_list = [1, 2, 3]

print(my_list, type(my_list))

rgb = tuple(my_list)

print(rgb)
print(type(rgb))


print("---------------------------------------------")

# Converting String into Tuple

rgb = tuple(["r", "e", "d"])

print(rgb)
print(type(rgb))


print("---------------------------------------------")

# Tuple Concatenation

t1 = (10, 20)
t2 = (30, 40)

t3 = t1 + t2

print(t3)


print("---------------------------------------------")

# Adding Elements to Tuple
# Tuple is immutable, so we create a new tuple

t = (10, 20, 30)

t = t + (40,)

print(t)


print("---------------------------------------------")

# Removing Elements from Tuple
# Tuple is immutable, so we create a new tuple

t = (10, 20, 30, 40)

t = t[:1] + t[2:]

print(t)


print("---------------------------------------------")

# Updating Tuple Elements
# Direct updating is not allowed
# Convert tuple into list, update, then convert back

t = (10, 20, 30)

temp = list(t)

temp[1] = 100

t = tuple(temp)

print(t)


print("---------------------------------------------")

# Loop through Tuple

z = (10, 11, 12, 13, 14)

for i in range(len(z)):
    print(z[i])


print("---------------------------------------------")

# Direct Tuple Iteration

for i in z:
    print(i)


print("---------------------------------------------")

# Tuple Methods

z = (10, 11, 12, 13, 10, 14)

print(z.count(10))       # Count occurrences
print(z.index(10))       # First occurrence index
print(z.index(10, 2))    # Search from index 2


print("---------------------------------------------")

# Tuple Unpacking

emp = ("vikash", 22, "Developer")

print(emp, type(emp))

(name, age, role) = emp

print(name)
print(age)
print(role)


print("---------------------------------------------")

# Extended Tuple Unpacking

s = (2, 4, 6, 8, 10)

(a, *b, c) = s

print(a)
print(b)
print(c)


print("---------------------------------------------")

# Swapping Variables Using Tuple

a = 10
b = 20

a, b = b, a

print("a =", a)
print("b =", b)


print("---------------------------------------------")

# Nested Tuple

nested = ((10, 20), (30, 40), (50, 60))

print(nested)
print(nested[0])
print(nested[1][0])


print("---------------------------------------------")

# Tuple Inside a Tuple

t = (10, (20, 30), 40)

print(t[1])
print(t[1][1])


print("---------------------------------------------")

# Other Built-in Functions

t = (10, 20, 30, 40, 50)

print(len(t))       # Length
print(max(t))       # Maximum value
print(min(t))       # Minimum value
print(sum(t))       # Sum of elements
print(sorted(t))    # Returns a sorted list
print(any(t))       # True if at least one is True
print(all(t))       # True if all are True
print(10 in t)      # Membership operator
print(100 not in t)


print("---------------------------------------------")

# Tuple Concatenation and Repetition

t1 = (1, 2)
t2 = (3, 4)

print(t1 + t2)      # Concatenation
print(t1 * 3)        # Repetition


print("---------------------------------------------")

# Tuple Comparison

t1 = (1, 2, 3)
t2 = (1, 2, 4)

print(t1 == t2)
print(t1 != t2)
print(t1 < t2)


print("---------------------------------------------")

# Tuple Membership

t = (10, 20, 30, 40)

print(20 in t)
print(50 in t)


print("---------------------------------------------")

# Tuple Length and Nested Tuple Length

t = (10, 20, (30, 40), 50)

print(len(t))        # Counts top-level elements


print("---------------------------------------------")

# Tuple to List Conversion

t = (10, 20, 30)

my_list = list(t)

print(my_list)
print(type(my_list))


print("---------------------------------------------")

# List to Tuple Conversion

my_list = [100, 200, 300]

t = tuple(my_list)

print(t)
print(type(t))


print("---------------------------------------------")

# Delete Entire Tuple

t = (10, 20, 30)

del t

# print(t)  # NameError because tuple is deleted

print("Tuple deleted successfully")

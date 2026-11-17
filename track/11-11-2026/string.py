print("-----------------------")
str_ = "all is well"
str_1 = """all is 
well
vikash"""
print(str_) 
print(str_1)

print("-----------------------")
str_11 = 'my name is "vikash" and i study in  '
str_2 = "my name is \"vikash\" and i study in \'Kodnest\'  " 
print(str_2) 

print("-----------------------")
a = "hello"
b = "world"
c = a + b
print(c)
print(a + " " + b)

print("-----------------------")
age = 23
print(f"My age is {age}")
print("my age is " + str(age))

age_1_2 = "23"
print(int(age_1_2))

print("-----------------------")
s1 = ""
res = bool(s1)
print(res)


print("-----------------------")
a = [1,2,3]
b = a
c = [1,2,3]

print(a is b)
print(a is c)
print(a == c)

print("-----------------------")

s1 = "python"
s2 = "python"
print(s1, id(s2))
print(s2, id(s1))
print(s1 is s2)
print(s1 == s2)

print("-----------------------")
s1 = "Hello"
print(s1, id(s1))
s1 = s1 + "World"
print(s1, id(s1))

print("-----------------------")
s = "vikash"
print(s.upper())
print(s.lower())
print(len(s))
print(s.capitalize())
print(s.title())

print("-----------------------")
#string slicing in python

name = "Kodnest"
#kodnest
print(name[0:4])
print(name[2:6])
print(name[:])
print(name[2:])
print(name[:5])
print(name[2:7])
print(name[2:7:2])
print(name[2:5:3])
print(name[: : -1])


print("-----------------------")
s = "python"
print(s[0:3])
print(s[2:6])
print(s[:])
print(s[2:])
print(s[:4])
print(s[0:4:2])
print(s[2:6:3])
print(s[0:3:-1])
print(s[0:3:-1])

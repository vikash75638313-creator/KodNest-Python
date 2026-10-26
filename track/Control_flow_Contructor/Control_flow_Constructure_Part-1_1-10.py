#Part 1 - if, if - else, if-elif-else(12 Program)

# Program 1: check whether a number is positive or negative
print("program 1: check whether a number is positive or negative")
number = int(input("Enetr the number:"))

if number > 0:
    print("The number is positive: ", number)
elif number < 0:
    print("The number is negative: ", number)
else:
    print("The number is zero: ", number) 

#program 2: check if a person is eligible to donate blood (Age >= 18)    
print("program 2: check if a person is eligible to donate blood (Age >= 18) ")

age = int(input("Enter the person Age: "))
if age >= 18:
    print("Person is eligible to donate blood:", age)
else:
    print("Person is not eligible to donate blood:", age) 

#Program 3: Check if the temperature is above 30 degree c and print "Hot Day"       
print("Program 3: Check if the temperature is above 30 degree c and print \"Hot Day\"")

temperature = int(input("Enter THe Temperture in degree celciouss: "))
if temperature > 30:
    print("Hot Day")
elif temperature < 30:
    print("Cold Day")
else:
    print("Temperture is between 30 and 40 degree celciouss")

#Program 4: check wether a student has attended at least 75% of the classes to be eligible for the final examination.    
print("Program 4: check wether a student has attended at least 75% of the classes to be eligible for the final examination.")

attended = int(input("Enter the number of classes attended: "))
total = int(input("Enter the total number of classes: "))
percentage = (attended / total) * 100

if percentage >= 75:
    print("Student is eligible for the final examination")
else:
    print("Student is not eligible for the final examination") 

#Program 5: Check weither a number is even and odd
print("Program 5: Check weither a number is even and odd")    

number = 12

if number % 2 == 0:
    print("Number is Even:", number)
else:
    print("Number is odd:", number)    

# Program 6: check weither a person is eligible to apply for driving lisence (Age >= 18)    
print("Program 6: check weither a person is eligible to apply for driving lisence (Age >= 18)")

age = 25

if age >= 18:
    print("Eligible to apply for driving lisence:", age)
else:
    print("Not Eligible to apply for driving lisence:", age) 

#Program 7: check weither a year is a leap uear
print("Program 7: check weither a year is a leap uear")

year = 2026

if year % 4 == 0:
    print(year, "is a leap year")
else:
    print(year, "is not a leap year")

#Program 8: compare two number and print the greatest number 
print("Program 8: compare two number and print the greatest number")  

a = 20
b = 30

if a > b:
    print("a is grater:", a)
else:
    print("b is grater:", b)    

#Program 9: assign grade based on marks
# Grade A: >=90
# Grade B: >=80
# Grade C: >=70
# Grade D: >=60
# Grade F: <60 
print("Program 9: assign grade based on marks")

marks = int(input("Enter the marks: "))

if marks >= 90:
    print("Grade A")
elif marks >= 80:
    print("Grade B")
elif marks >= 70:
    print("Grade C")
elif marks >= 60:
    print("Grade D")
else:
    print("Grade F")    

#Program 10: categorize electicity usage
# above 500 -- High
# 301 - 500 -- Medium
# 101 - 300 -- Low
# 1 - 100 --very Low
print("program 10: categorize electicity usage")

electricity_usage =  500

if electricity_usage > 500:
    print("High",electricity_usage)
elif electricity_usage > 300 and electricity_usage <= 500:
    print("Medium",electricity_usage)
elif electricity_usage > 100 and electricity_usage <= 300:
    print("Low",electricity_usage)
else:
    print("very Low",electricity_usage) 

    
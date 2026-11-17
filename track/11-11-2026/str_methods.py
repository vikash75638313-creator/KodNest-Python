
# Inbuilt String Methods - Single Program

s = "  kodNest Technologies 123  "

# Original String
print("Original String:", s)

print("-----------------------")
# Case Conversion Methods
print("upper():", s.upper())
print("lower():", s.lower())
print("capitalize():", s.capitalize())
print("title():", s.title())
print("swapcase():", s.swapcase())

print("-----------------------")
# Searching & Counting
print("find('Tech'):", s.find("Tech"))
print("count('o'):", s.count("o"))

print("-----------------------")
# Replace
print("replace('123', '2025'):", s.replace("123", "2025"))

print("-----------------------")
# Start & End Check
print("startswith('  kod'):", s.startswith("  kod"))
print("endswith('123  '):", s.endswith("123  "))

print("-----------------------")
# Split & Join
words = s.split()
print("split():", words)
print("join():", "-".join(words))

print("-----------------------")
# Strip Spaces
print("strip():", s.strip())
print("lstrip():", s.lstrip())
print("rstrip():", s.rstrip())

print("-----------------------")
# Checking Methods
print("isalpha():", s.isalpha())
print("isdigit():", s.isdigit())
print("isalnum():", s.isalnum())

print("-----------------------")
# Length
print("Length of string:", len(s))



'''
Methods covered in this program
Category	Methods
Case Conversion	upper(), lower(), capitalize(), title(), swapcase()
Searching & Counting	find(), count()
Replace	replace()
Start & End Check	startswith(), endswith()
Split & Join	split(), join()
Remove Spaces	strip(), lstrip(), rstrip()
Checking	isalpha(), isdigit(), isalnum()
Length	len()
'''